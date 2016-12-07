/*******************************************************************************
 * Copyright (c) 2016 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/
package org.gemoc.executionframework.extensions.sirius.modelloader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditorWithFlyOutPalette;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.diagram.ui.business.internal.command.RefreshDiagramOnOpeningCommand;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.ToolFilter;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.XtextPlatformResourceURIHandler;
import org.eclipse.xtext.util.StringInputStream;
import org.gemoc.commons.eclipse.emf.EMFResource;
import org.gemoc.executionframework.engine.core.CommandExecution;
import org.gemoc.executionframework.extensions.sirius.Activator;
import org.gemoc.executionframework.extensions.sirius.debug.DebugSessionFactory;
import org.gemoc.executionframework.extensions.sirius.services.AbstractGemocAnimatorServices;
import org.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.gemoc.xdsmlframework.api.core.IModelLoader;

import fr.inria.diverse.melange.adapters.EObjectAdapter;
import fr.inria.diverse.melange.resource.MelangeRegistry;
import fr.inria.diverse.melange.resource.MelangeResourceImpl;
import fr.obeo.dsl.debug.ide.sirius.ui.services.AbstractDSLDebuggerServices;

public class DefaultModelLoader implements IModelLoader {

	public Resource loadModel(IExecutionContext context) throws RuntimeException {
		Resource resource = null;
		ResourceSet resourceSet;
		resourceSet = new ResourceSetImpl();
		resource = resourceSet.createResource(context.getRunConfiguration().getExecutedModelURI());
		try {
			resource.load(null);
		} catch (IOException e) {
			new RuntimeException(e);
		}
		return resource;
	}

	public Resource loadModelForAnimation(IExecutionContext context) throws RuntimeException {
		Resource resource = null;
		ResourceSet resourceSet;
		if (context.getRunConfiguration().getAnimatorURI() != null) {
			killPreviousSiriusSession(context.getRunConfiguration().getAnimatorURI());
			Session session;
			try {
				session = openNewSiriusSession(context, context.getRunConfiguration().getAnimatorURI());
				resourceSet = session.getTransactionalEditingDomain().getResourceSet();
			} catch (CoreException e) {
				throw new RuntimeException(e);
			}
			// At this point Sirius has loaded the model, so we just need to
			// find it
			boolean useMelange = context.getRunConfiguration().getMelangeQuery() != null
					&& !context.getRunConfiguration().getMelangeQuery().isEmpty();
			// calculating model URI as MelangeURI
			URI modelURI = useMelange ? context.getRunConfiguration().getExecutedModelAsMelangeURI()
					: context.getRunConfiguration().getExecutedModelURI();
			for (Resource r : resourceSet.getResources()) {
				if (r.getURI().equals(modelURI)) {
					resource = r;
					break;
				}
			}

			return resource;
		} else {
			// animator not available; fall back to classic load
			return loadModel(context);
		}
	}

	private void killPreviousSiriusSession(URI sessionResourceURI) {
		final Session session = SessionManager.INSTANCE.getExistingSession(sessionResourceURI);
		if (session != null) {
			final IEditingSession uiSession = SessionUIManager.INSTANCE.getUISession(session);
			DebugPermissionProvider permProvider = new DebugPermissionProvider();
			if (!permProvider.provides(session.getTransactionalEditingDomain().getResourceSet())) {
				// this is a not debugSession (ie. a normal editing session)
				if (uiSession != null) {
					for (final DialectEditor editor : uiSession.getEditors()) {
						final IEditorSite editorSite = editor.getEditorSite();
						if (editor.getSite() == null) {
							editorSite.getShell().getDisplay().syncExec(new Runnable() {
								@Override
								public void run() {
									editorSite.getPage().closeEditor(editor, true);
								}
							});
						}
					}
					PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
						@Override
						public void run() {
							uiSession.close();
						}
					});
				}
			}
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					session.close(new NullProgressMonitor());
					SessionManager.INSTANCE.remove(session);
				}
			});
		}
	}

	private Session openNewSiriusSession(final IExecutionContext context, URI sessionResourceURI) throws CoreException {

		SubMonitor subMonitor = SubMonitor.convert(this.progressMonitor, 10);

		boolean useMelange = context.getRunConfiguration().getMelangeQuery() != null
				&& !context.getRunConfiguration().getMelangeQuery().isEmpty();
		if (useMelange) {
			subMonitor.setTaskName("Loading model for animation with melange");
		} else {
			subMonitor.setTaskName("Loading model for animation");
		}
		// calculating model URI as MelangeURI
		URI modelURI = useMelange ? context.getRunConfiguration().getExecutedModelAsMelangeURI()
				: context.getRunConfiguration().getExecutedModelURI();

		subMonitor.subTask("Configuring ResourceSet");
		subMonitor.newChild(1);
		// create and configure resource set
		HashMap<String, String> nsURIMapping = getnsURIMapping(context);
		// final ResourceSet rs = createAndConfigureResourceSet(modelURI,
		// nsURIMapping);
		final ResourceSet rs = createAndConfigureResourceSet(modelURI, nsURIMapping);

		subMonitor.subTask("Loading model");
		subMonitor.newChild(3);
		// load model resource and resolve all proxies
		Resource r = rs.getResource(modelURI, true);
		EcoreUtil.resolveAll(rs);
		// EObject root = r.getContents().get(0);
		// force adaptee model resource in the main ResourceSet
		if (r instanceof MelangeResourceImpl) {
			MelangeResourceImpl mr = (MelangeResourceImpl) r;
			rs.getResources().add(mr.getWrappedResource());

			if (!r.getContents().isEmpty() && r.getContents().get(0) instanceof EObjectAdapter) {
				Resource realResource = ((EObjectAdapter<?>) r.getContents().get(0)).getAdaptee().eResource();
				rs.getResources().add(realResource);
			}
		}

		// calculating aird URI
		/*
		 * URI airdURI = useMelange ?
		 * URI.createURI(sessionResourceURI.toString() .replace("platform:/",
		 * "melange:/")) : sessionResourceURI;
		 */
		URI airdURI = sessionResourceURI;
		// URI airdURI = sessionResourceURI;

		subMonitor.subTask("Creating Sirius session");
		subMonitor.newChild(1);
		// create and load sirius session
		final Session session = DebugSessionFactory.INSTANCE.createSession(rs, airdURI);

		// final IProgressMonitor monitor = new NullProgressMonitor();
		final TransactionalEditingDomain editingDomain = session.getTransactionalEditingDomain();
		// Specific to MelangeResource
		if (r.getContents().size() > 0) {
			Resource res = r.getContents().get(0).eResource(); // get the used
																// resource
			res.eAdapters().add(new SessionTransientAttachment(session)); // link
																			// the
																			// resource
																			// with
																			// Sirius
																			// session
			RecordingCommand cmd = new RecordingCommand(editingDomain) {
				@Override
				protected void doExecute() {
					DAnalysisSessionImpl sessionImpl = (DAnalysisSessionImpl) session;
					EList<ResourceDescriptor> srList = sessionImpl.getAnalyses().get(0).getSemanticResources();
					srList.clear();
					srList.add(new ResourceDescriptor(modelURI));
				}
			};
			try {
				CommandExecution.execute(editingDomain, cmd);
			} catch (Exception e) {
				// TODO: error msg
			}

		}

		subMonitor.subTask("Opening Sirius session");
		session.open(subMonitor.newChild(2));
		// EcoreUtil.resolveAll(rs);
		// activating layers
		subMonitor.subTask("Opening Sirius editors");
		SubMonitor openEditorSubMonitor = subMonitor.newChild(2);

		// for each representation in the selected views
		for (DView view : session.getSelectedViews()) {
			for (DRepresentationDescriptor repDescriptor : view.getOwnedRepresentationDescriptors()) {
				DRepresentation representation = repDescriptor.getRepresentation();

				final DSemanticDiagram diagram = (DSemanticDiagram) representation;
				openEditorSubMonitor.subTask(diagram.getName());
				final List<EObject> elements = new ArrayList<EObject>();
				elements.add(diagram);

				final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session,
						representation, openEditorSubMonitor.newChild(1));
				if (editorPart instanceof DDiagramEditor) {
					((DDiagramEditor) editorPart).getPaletteManager().addToolFilter(new ToolFilter() {
						@Override
						public boolean filter(DDiagram diagram, AbstractToolDescription tool) {
							return true;
						}
					});
				}
				try {
					RefreshDiagramOnOpeningCommand refresh = new RefreshDiagramOnOpeningCommand(editingDomain, diagram);
					CommandExecution.execute(editingDomain, refresh);
				} catch (Exception e) {
					Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
							"Problem refreshing diagrams : " + diagram, e));
				}

				if (editorPart instanceof DiagramEditorWithFlyOutPalette) {
					PaletteUtils.colapsePalette((DiagramEditorWithFlyOutPalette) editorPart);
				}

				RecordingCommand command = new RecordingCommand(editingDomain, "Activating animator and debug layers") {
					@Override
					protected void doExecute() {
						boolean hasADebugLayer = false;
						Set<Layer> layers = new HashSet<Layer>();
						layers.addAll(diagram.getDescription().getAdditionalLayers());
						Collection<Viewpoint> selectedVp = session.getSelectedViewpoints(true);
						for (Viewpoint vp : selectedVp) {
							for (RepresentationExtensionDescription extension : vp.getOwnedRepresentationExtensions()) {
								if (extension instanceof DiagramExtensionDescription) {
									layers.addAll(((DiagramExtensionDescription) extension).getLayers());
								}
							}
						}
						for (Layer l : layers) {
							String descName = diagram.getDescription().getName();
							String layerName = l.getName();
							boolean mustBeActiveForDebug = AbstractDSLDebuggerServices.LISTENER
									.isRepresentationToRefresh(context.getRunConfiguration().getDebugModelID(),
											descName, layerName)
									|| layerName.equalsIgnoreCase("Debug");
							boolean mustBeActiveForAnimation = AbstractGemocAnimatorServices.ANIMATOR
									.isRepresentationToRefresh(descName, layerName)
									|| layerName.equalsIgnoreCase("Animation");
							boolean mustBeActive = mustBeActiveForAnimation || mustBeActiveForDebug;
							hasADebugLayer = hasADebugLayer || mustBeActiveForDebug;
							if (mustBeActive && !diagram.getActivatedLayers().contains(l)) {
								ChangeLayerActivationCommand c = new ChangeLayerActivationCommand(editingDomain,
										diagram, l, openEditorSubMonitor.newChild(1));
								c.execute();
							}
						}
						if (!hasADebugLayer) {
							// no debug layer defined in the odesign for
							// debugmodelID
							Activator.getDefault().getLog()
									.log(new Status(IStatus.WARNING, Activator.PLUGIN_ID,
											"No debug service defined in the odesign for the debug model id : "
													+ context.getRunConfiguration().getDebugModelID()));
						}
					}
				};
				CommandExecution.execute(editingDomain, command);
			}
		}

		return session;
	}

	private ResourceSet createAndConfigureResourceSet(URI modelURI, HashMap<String, String> nsURIMapping) {
		final ResourceSet rs = ResourceSetFactory.createFactory().createResourceSet(modelURI);
		final String fileExtension = modelURI.fileExtension();
		// indicates which melange query should be added to the xml uri handler
		// for a given extension
		final XMLURIHandler handler = new XMLURIHandler(modelURI.query(), fileExtension); // use
																							// to
																							// resolve
																							// cross
																							// ref
		// URI during XMI parsing
		// final XtextPlatformResourceURIHandler handler = new
		// XtextPlatformResourceURIHandler();
		handler.setResourceSet(rs);
		rs.getLoadOptions().put(XMLResource.OPTION_URI_HANDLER, handler);

		final MelangeURIConverter converter = new MelangeURIConverter(nsURIMapping);
		// final ExtensibleURIConverterImpl converter = new
		// ExtensibleURIConverterImpl();
		rs.setURIConverter(converter);
		// fix sirius to prevent non intentional model savings
		converter.getURIHandlers().add(0, new DebugURIHandler(converter.getURIHandlers()));

		return rs;
	}

	// TODO must be extended to support more complex mappings, currently use
	// only the first package in the genmodel
	protected HashMap<String, String> getnsURIMapping(IExecutionContext context) {
		HashMap<String, String> nsURIMapping = new HashMap<String, String>();

		final String langQuery = "lang=";
		String melangeQuery = context.getRunConfiguration().getExecutedModelAsMelangeURI().query();
		if (melangeQuery != null && !melangeQuery.isEmpty() && melangeQuery.contains(langQuery)) {

			String targetLanguage = melangeQuery.substring(melangeQuery.indexOf(langQuery) + langQuery.length());
			if (targetLanguage.contains("&")) {
				targetLanguage = targetLanguage.substring(0, targetLanguage.indexOf("&"));
			}
			String targetLanguageNsURI = MelangeRegistry.INSTANCE.getLanguageByIdentifier(targetLanguage).getUri();

			// simply open the original model file in a separate ResourceSet
			// and ask its root element class nsURI
			Object o = EMFResource.getFirstContent(context.getRunConfiguration().getExecutedModelURI());
			if (o instanceof EObject) {
				EPackage rootPackage = ((EObject) o).eClass().getEPackage();
				while (rootPackage.getESuperPackage() != null) {
					rootPackage = rootPackage.getESuperPackage();
				}
				nsURIMapping.put(rootPackage.getNsURI(), targetLanguageNsURI);
			}
		}

		return nsURIMapping;
	}

	class MelangeURIConverter extends ExtensibleURIConverterImpl {

		private HashMap<String, String> _nsURIMapping;

		public MelangeURIConverter(HashMap<String, String> nsURIMapping) {
			_nsURIMapping = nsURIMapping;
		}

		@SuppressWarnings("resource")
		@Override
		public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
			InputStream result = null;

			// do not modify content of files loaded using melange:/ scheme
			// melange is supposed to do the job
			// if (uri.scheme()!= null && uri.scheme().equals("melange")) {
			// return super.createInputStream(uri);
			// }
			if (uri.fileExtension() == null || !uri.fileExtension().equals("aird")) {
				// only the root aird must be adapted
				return super.createInputStream(uri, options);
			}

			InputStream originalInputStream = null;
			try {
				originalInputStream = super.createInputStream(uri, options);
				String originalContent = convertStreamToString(originalInputStream);
				String modifiedContent = originalContent;
				for (Entry<String, String> entry : _nsURIMapping.entrySet()) {
					modifiedContent = modifiedContent.replace(entry.getKey(), entry.getValue());
				}
				result = new StringInputStream(modifiedContent);
				return result;
			} finally {
				if (originalInputStream != null) {
					originalInputStream.close();
				}
			}

		}

		private String convertStreamToString(java.io.InputStream is) {
			java.util.Scanner s1 = new java.util.Scanner(is);
			java.util.Scanner s2 = s1.useDelimiter("\\A");
			String result = s2.hasNext() ? s2.next() : "";
			s1.close();
			s2.close();
			return result;
		}
	}

	/**
	 * change scheme to melange:// for files with the given fileextension when a
	 * melange query is active
	 * 
	 * @author dvojtise
	 *
	 */
	class XMLURIHandler extends XtextPlatformResourceURIHandler {

		private String _queryParameters;
		private String _fileExtension;

		public XMLURIHandler(String queryParameters, String fileExtension) {
			_queryParameters = queryParameters;
			if (_queryParameters == null)
				_queryParameters = "";
			else
				_queryParameters = "?" + _queryParameters;
			_fileExtension = fileExtension;
		}

		@Override
		public URI resolve(URI uri) {
			URI resolvedURI = super.resolve(uri);
			if (!_queryParameters.isEmpty() && resolvedURI.scheme() != null && !resolvedURI.scheme().equals("melange")
					&& resolvedURI.fileExtension() != null && resolvedURI.fileExtension().equals(_fileExtension)) {

				String fileExtensionWithPoint = "." + _fileExtension;
				int lastIndexOfFileExtension = resolvedURI.toString().lastIndexOf(fileExtensionWithPoint);
				String part1 = resolvedURI.toString().substring(0, lastIndexOfFileExtension);
				part1 = part1.replaceFirst("platform:/", "melange:/");
				String part2 = fileExtensionWithPoint + _queryParameters;
				String part3 = resolvedURI.toString()
						.substring(lastIndexOfFileExtension + fileExtensionWithPoint.length());
				String newURIAsString = part1 + part2 + part3;
				return URI.createURI(newURIAsString);
			}
			return resolvedURI;
		}
	}

	IProgressMonitor progressMonitor;

	@Override
	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		this.progressMonitor = progressMonitor;
	}

}
