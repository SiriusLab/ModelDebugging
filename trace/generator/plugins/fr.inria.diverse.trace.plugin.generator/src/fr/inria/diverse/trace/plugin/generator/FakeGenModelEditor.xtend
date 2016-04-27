package fr.inria.diverse.trace.plugin.generator

import java.util.Collection
import org.eclipse.core.commands.Command
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.commands.IHandler
import org.eclipse.core.commands.NotEnabledException
import org.eclipse.core.commands.NotHandledException
import org.eclipse.core.commands.ParameterizedCommand
import org.eclipse.core.commands.common.NotDefinedException
import org.eclipse.core.expressions.Expression
import org.eclipse.core.expressions.IEvaluationContext
import org.eclipse.core.resources.IFile
import org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditor
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.jface.action.MenuManager
import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.viewers.ISelectionProvider
import org.eclipse.jface.viewers.StructuredViewer
import org.eclipse.swt.widgets.Event
import org.eclipse.ui.IEditorInput
import org.eclipse.ui.ISourceProvider
import org.eclipse.ui.IWorkbenchPartSite
import org.eclipse.ui.handlers.IHandlerActivation
import org.eclipse.ui.handlers.IHandlerService

public class FakeGenModelEditor extends GenModelEditor {

		val IFile file

		new(IFile file) {
			super()
			this.file = file;

		}
		
		def Resource getResource() {
			return getEditingDomain().getResourceSet().getResources().get(0)
		}

		override setStatusLineManager(ISelection selection) {
		}

		override protected createContextMenuFor(StructuredViewer viewer) {
		}

		override protected setActivePage(int pageIndex) {
		}

		override protected initializePageSwitching() {
			
		}
		
		override getSite() {
			return new IWorkbenchPartSite() {
				
				override getId() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getKeyBindingService() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getPart() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getPluginId() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getRegisteredName() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override registerContextMenu(MenuManager menuManager, ISelectionProvider selectionProvider) {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override registerContextMenu(String menuId, MenuManager menuManager, ISelectionProvider selectionProvider) {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getPage() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getSelectionProvider() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getShell() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getWorkbenchWindow() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override setSelectionProvider(ISelectionProvider provider) {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getAdapter(Class adapter) {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				override getService(Class api) {
					return new IHandlerService() {
						
						override activateHandler(IHandlerActivation activation) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override activateHandler(String commandId, IHandler handler) {
							println("")
							return null
						}
						
						override activateHandler(String commandId, IHandler handler, Expression e) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override activateHandler(String commandId, IHandler handler, Expression e, boolean b) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override activateHandler(String commandId, IHandler handler, Expression e, int i) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override createContextSnapshot(boolean b) {
							
						}
						
						override executeCommandInContext(ParameterizedCommand a, Event b, IEvaluationContext v) {
							
						}
						
						override getCurrentState() {
							return null
						}
						
						
						override createExecutionEvent(Command command, Event event) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override createExecutionEvent(ParameterizedCommand command, Event event) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override deactivateHandler(IHandlerActivation activation) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override deactivateHandlers(Collection activations) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override executeCommand(String commandId, Event event) throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override executeCommand(ParameterizedCommand command, Event event) throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override readRegistry() {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override setHelpContextId(IHandler handler, String helpContextId) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override addSourceProvider(ISourceProvider provider) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override removeSourceProvider(ISourceProvider provider) {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
						override dispose() {
							throw new UnsupportedOperationException("TODO: auto-generated method stub")
						}
						
					}
				}
				
				override hasService(Class api) {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}
				
				}
		}

		override getEditorInput() {
			return new IEditorInput() {

				override exists() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}

				override getImageDescriptor() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}

				override getName() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}

				override getPersistable() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}

				override getToolTipText() {
					throw new UnsupportedOperationException("TODO: auto-generated method stub")
				}

				override getAdapter(Class adapter) {
					if (adapter == IFile) {
						return file
					}
				}

			}
		}

	}
