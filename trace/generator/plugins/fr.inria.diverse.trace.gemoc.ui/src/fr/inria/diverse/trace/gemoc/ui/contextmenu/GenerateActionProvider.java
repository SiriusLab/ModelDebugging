package fr.inria.diverse.trace.gemoc.ui.contextmenu;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.eclipse.ui.navigator.CommonActionProvider;

import fr.inria.diverse.melange.metamodel.melange.Element;
import fr.inria.diverse.melange.metamodel.melange.Language;
import fr.inria.diverse.melange.metamodel.melange.ModelTypingSpace;

public class GenerateActionProvider extends CommonActionProvider{

	@Override
	public void fillContextMenu(IMenuManager menu) {

		MenuManager newMenu = new MenuManager("Generate Trace Addon Project", "GenerateTraceMenu");
		
		ISelection selection = getContext().getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1) {
			Object object = ((IStructuredSelection)selection).getFirstElement();
			if (object instanceof IFile) {
				final IFile file = (IFile)object;

				URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				ResourceSet rs = new ResourceSetImpl();
				Resource res = rs.getResource(uri, true);
				ModelTypingSpace root = (ModelTypingSpace)res.getContents().get(0);

				// Populate menu with Languages
				for (Element element : root.getElements()) {
					if (element instanceof Language) {
						Language lang = (Language)element;
						final String languageName = lang.getName();
						newMenu.add(new Action("Language \'"+ languageName + "\'") {
							@Override
							public void run() {
								//TODO
								System.out.println("TODO: generate addon");
							}
						});
					}
				}
			}
		}
		menu.add(newMenu);
	}
}
