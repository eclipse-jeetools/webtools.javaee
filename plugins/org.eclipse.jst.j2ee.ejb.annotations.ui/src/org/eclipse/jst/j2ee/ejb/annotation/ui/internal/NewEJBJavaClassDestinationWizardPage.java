/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.ui.internal;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class NewEJBJavaClassDestinationWizardPage extends NewJavaClassDestinationWizardPage {
	public NewEJBJavaClassDestinationWizardPage(WTPOperationDataModel model, String pageName,
			String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}

	/**
	 * Returns a new instance of the Selection Listner for the Container Selection Dialog
	 */
	protected ViewerFilter getContainerDialogViewerFilter() {
		return new ViewerFilter() {
			public boolean select(Viewer viewer, Object parent, Object element) {
				boolean ret = false;
				if (element instanceof IProject) {
					IProject project = (IProject) element;
					ret = (EJBNatureRuntime.hasRuntime(project));
				} else if (element instanceof IFolder) {
					IFolder folder = (IFolder) element;
					// only show source folders
					if (ProjectUtilities.getSourceContainers(folder.getProject()).contains(folder)) {
					//if (((NewJavaClassDataModel)model).isSourceFolder(fullPath)) {
						ret = true;
					}
				}
				return ret;
			}
		};
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#enter()
	 */
	protected void enter() {
		String className = classText.getText();
		// Synch class name with ejb name only when it is not set
		if (className == null || className.trim().length() == 0) {
			IWizardPage firstPage = getWizard().getStartingPage();
			if (firstPage != null && firstPage instanceof IBeanWizardPage) {
				String ejbName = ((IBeanWizardPage)firstPage).getEjbName();
				setClassNameToDisplayName(ejbName+"Bean");
			}
		}
		super.enter();
	}
	
	private void setClassNameToDisplayName(String displayName) {
		char[] textChar = displayName.toCharArray();
		textChar[0] = Character.toUpperCase(textChar[0]);
		String className = String.valueOf(textChar);
		classText.setText(className);
	}

}