/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;



import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.wizard.J2EEApplicationCreationWizard;
import org.eclipse.ui.IWorkbench;


public class NewEARProjectAction extends AbstractOpenWizardWorkbenchAction {
	// TODO MDE 02-28 Find correct label
	public static String LABEL = J2EEUIMessages.getResourceString("NewEARProjectAction_UI_0"); //$NON-NLS-1$
	private static final String ICON = "newear_wiz"; //$NON-NLS-1$

	public NewEARProjectAction() {
		setText(LABEL);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	public NewEARProjectAction(IWorkbench workbench, String label, Class[] acceptedTypes) {
		super(workbench, label, acceptedTypes, false);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	protected Wizard createWizard() {
		return new J2EEApplicationCreationWizard();
	}

	protected boolean shouldAcceptElement(Object obj) {
		return true; /* NewGroup.isOnBuildPath(obj) && !NewGroup.isInArchive(obj); */
	}

	protected String getDialogText() {
		return null;
	}
}