package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonActionProviderConfig;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;
import org.eclipse.ui.navigator.WizardActionGroup;
import org.eclipse.ui.navigator.resources.internal.plugin.WorkbenchNavigatorMessages;

public class J2EENewActionProvider extends CommonActionProvider {

	private WizardActionGroup newWizardActionGroup;
	
	public J2EENewActionProvider() {
		super();
	}

	public void init(CommonActionProviderConfig aConfig) {
		if (aConfig.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			IWorkbenchWindow window = ((ICommonViewerWorkbenchSite) aConfig.getViewSite()).getWorkbenchWindow();
			newWizardActionGroup = new WizardActionGroup(window, PlatformUI.getWorkbench().getNewWizardRegistry(),WizardActionGroup.TYPE_NEW);
		}
	}
	
	/**
	 * Adds a submenu to the given menu with the name
	 * {@value ICommonMenuConstants#GROUP_NEW } (see
	 * {@link ICommonMenuConstants#GROUP_NEW}). 
	 */
	public void fillContextMenu(IMenuManager menu) {
		IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if (!(selection.getFirstElement() instanceof ItemProvider))
			return;
		IMenuManager submenu = null;
		if (submenu == null)
			submenu = new MenuManager(WorkbenchNavigatorMessages.Workbench_new, ICommonMenuConstants.GROUP_NEW);
		
		// fill the menu from the commonWizard contributions
		newWizardActionGroup.setContext(getContext());
		newWizardActionGroup.fillContextMenu(submenu);

		submenu.add(new Separator(ICommonMenuConstants.GROUP_ADDITIONS));

		// append the submenu after the GROUP_NEW group.
		menu.insertAfter(ICommonMenuConstants.GROUP_NEW, submenu);
	}

}
