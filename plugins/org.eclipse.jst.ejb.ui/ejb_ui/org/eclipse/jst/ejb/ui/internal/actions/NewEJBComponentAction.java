/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.ejb.ui.internal.actions;



import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.ejb.ui.project.facet.EjbProjectWizard;
import org.eclipse.jst.j2ee.internal.actions.AbstractOpenWizardWorkbenchAction;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.IWorkbench;




public class NewEJBComponentAction extends AbstractOpenWizardWorkbenchAction {

	// TODO MDE 02-28 Find correct label
	public static String LABEL = EJBUIMessages.NewEJBModuleAction;
	private static final String ICON = "newejbprj_wiz"; //$NON-NLS-1$

	public NewEJBComponentAction() {
		setText(LABEL);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	public NewEJBComponentAction(IWorkbench workbench, String label, Class[] acceptedTypes) {
		super(workbench, label, acceptedTypes, false);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	protected Wizard createWizard() {
		return new EjbProjectWizard();
	}

	protected boolean shouldAcceptElement(Object obj) {
		return true; /* NewGroup.isOnBuildPath(obj) && !NewGroup.isInArchive(obj); */
	}

	protected String getDialogText() {
		return null;
	}
	
	public String getId() {
		return EJBUIMessages.NewEJBModuleAction;
	}
}