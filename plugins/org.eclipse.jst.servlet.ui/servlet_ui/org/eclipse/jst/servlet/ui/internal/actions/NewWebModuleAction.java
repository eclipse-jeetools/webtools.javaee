/*
 * Created on Apr 1, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.ui.internal.actions;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.internal.actions.AbstractOpenWizardWorkbenchAction;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.jst.servlet.ui.internal.wizard.WebComponentCreationWizard;
import org.eclipse.ui.IWorkbench;

public class NewWebModuleAction extends AbstractOpenWizardWorkbenchAction {
	
	public static String LABEL = WEBUIMessages.WEB_MODULE_WIZ_TITLE;
	private static final String ICON = "war_wiz"; //$NON-NLS-1$
	
	public NewWebModuleAction() {
		super();
		setText(LABEL); 
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}
	
	public NewWebModuleAction(IWorkbench workbench, String label, Class[] activatedOnTypes, boolean acceptEmptySelection) {
		super(workbench, label, activatedOnTypes, acceptEmptySelection);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}
	
	public NewWebModuleAction(IWorkbench workbench, String label, boolean acceptEmptySelection) {
		super(workbench, label, acceptEmptySelection);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	protected Wizard createWizard() {
		return new WebComponentCreationWizard();
	}
	
	protected boolean shouldAcceptElement(Object obj) { 
		return true; /* NewGroup.isOnBuildPath(obj) && !NewGroup.isInArchive(obj); */
	}
	protected String getDialogText() {
		return null;
	}
}
