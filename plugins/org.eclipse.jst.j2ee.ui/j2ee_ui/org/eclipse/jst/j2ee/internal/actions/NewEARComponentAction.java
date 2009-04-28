/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;



import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.ui.project.facet.EarProjectWizard;
import org.eclipse.ui.IWorkbench;

@Deprecated
//This class is being deprecated in 3.1, and is in plan to be removed
//in 3.2, since it is not being used.
public class NewEARComponentAction extends AbstractOpenWizardWorkbenchAction {
	// TODO MDE 02-28 Find correct label
	public static String LABEL = J2EEUIMessages.getResourceString("NewEARModuleAction_UI_0"); //$NON-NLS-1$
	private static final String ICON = "newear_wiz"; //$NON-NLS-1$

	public NewEARComponentAction() {
		setText(LABEL);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	public NewEARComponentAction(IWorkbench workbench, String label, Class[] acceptedTypes) {
		super(workbench, label, acceptedTypes, false);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	protected Wizard createWizard() {
		return new EarProjectWizard();
	}

	protected boolean shouldAcceptElement(Object obj) {
		return true; /* NewGroup.isOnBuildPath(obj) && !NewGroup.isInArchive(obj); */
	}

	protected String getDialogText() {
		return null;
	}
}
