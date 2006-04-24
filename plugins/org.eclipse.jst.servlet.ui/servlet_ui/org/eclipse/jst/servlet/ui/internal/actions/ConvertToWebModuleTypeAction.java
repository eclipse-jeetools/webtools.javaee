package org.eclipse.jst.servlet.ui.internal.actions;

/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.internal.actions.AbstractOpenWizardWorkbenchAction;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
/**
 * @deprecated to be removed in WTP 2.0
 */
public class ConvertToWebModuleTypeAction extends AbstractOpenWizardWorkbenchAction {

	IStructuredSelection fSelection = null;
	IProject project = null;
	IWorkbenchWindow fWindow;

	/**
	 * ConvertLinksDialog constructor comment.
	 */
	public ConvertToWebModuleTypeAction() {
		super();
	}

	public ConvertToWebModuleTypeAction(IWorkbench workbench, String label, Class[] acceptedTypes) {
		super(workbench, label, acceptedTypes, false);
	}

	protected Wizard createWizard() {
        //TODO: reimplement
/*		ConvertToWebComponentTypeWizard wizard = new ConvertToWebComponentTypeWizard(new ConvertWebProjectDataModel());
		WebComponentCreationDataModel model = (WebComponentCreationDataModel) wizard.getModel();
		model.setProperty(EditModelOperationDataModel.PROJECT_NAME, project.getName());
		model.setBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR, true);

		StaticWebNatureRuntime nature;
		try {
			nature = (StaticWebNatureRuntime) project.getNature(IWebNatureConstants.STATIC_NATURE_ID);
			String webContent = nature.getRootPublishableFolder().getName();
			String contextRoot = nature.getContextRoot();
			//model.setProperty(WebComponentCreationDataModel.WEB_CONTENT, webContent);
			model.setProperty(WebComponentCreationDataModel.CONTEXT_ROOT, contextRoot);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			Logger.getLogger().logError(e);
		}

		// wizard.setWindowTitle("Convert to Dynamic Web Project");

		return wizard;*/
        return null;
	}

	/**
	 * make sure a web project is selected.
	 */
	public boolean isValidProject(IProject aProject) {
		return J2EEProjectUtilities.isStaticWebProject(aProject);
	}

	/**
	 * selectionChanged method comment.
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		boolean bEnable = false;
		if (selection instanceof IStructuredSelection) {
			fSelection = (IStructuredSelection) selection;
			bEnable = validateSelected(fSelection);
		}
		((Action) action).setEnabled(bEnable);
	}

	/**
	 * selectionChanged method comment.
	 */
	public boolean validateSelected(ISelection selection) {
		if (!(selection instanceof IStructuredSelection))
			return false;

		fSelection = (IStructuredSelection) selection;

		Object selectedProject = fSelection.getFirstElement();
		if (!(selectedProject instanceof IProject))
			return false;

		project = (IProject) selectedProject;
		return isValidProject(project);
	}
}