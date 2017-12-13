/*******************************************************************************
 * Copyright (c) 2010 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.quickfixes.ejb;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.ejb.ui.internal.wizard.AddMessageDrivenBeanWizard;
import org.eclipse.jst.ejb.ui.internal.wizard.AddSessionBeanWizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizard;

@SuppressWarnings("restriction")
public class CreateBeanQuickFix implements IMarkerResolution {
	
	public static enum BEAN_TYPE {
		SESSION,
		MESSAGE_DRIVEN
	}
	
	private BEAN_TYPE beanType;
	private IProject project;

	public CreateBeanQuickFix(BEAN_TYPE bType, IProject proj) {
		this.beanType = bType;
		this.project = proj;
	}
	
	public String getLabel() {
		return (beanType == BEAN_TYPE.SESSION) ? 
					Messages.CREATE_NEW_SESSION_BEAN : 
					Messages.CREATE_NEW_MDB; 
	}

	public void run(IMarker marker) {
		Runnable r = new Runnable() {
			public void run() {
				openNewBeanWizard();
			}
		};
		Display.getDefault().asyncExec(r);
	}

	private void openNewBeanWizard() {
		DataModelWizard wizard = (beanType == BEAN_TYPE.SESSION) ? 
					new AddSessionBeanWizard() : 
					new AddMessageDrivenBeanWizard();
		wizard.getDataModel().setStringProperty(IArtifactEditOperationDataModelProperties.COMPONENT_NAME, project.getName());
		((IWorkbenchWizard) wizard).init(PlatformUI.getWorkbench(), new StructuredSelection(project));
		WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wizard);
		dialog.open();	
	}	
	
}
