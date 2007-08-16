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
package org.eclipse.jst.servlet.ui.internal.actions;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.internal.actions.AbstractOpenWizardWorkbenchAction;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.jst.servlet.ui.project.facet.WebProjectWizard;
import org.eclipse.ui.IWorkbench;

public class NewWebComponentAction extends AbstractOpenWizardWorkbenchAction {
	
	public static String LABEL = WEBUIMessages.WEB_MODULE_WIZ_TITLE;
	private static final String ICON = "war_wiz"; //$NON-NLS-1$
	
	public NewWebComponentAction() {
		super();
		setText(LABEL); 
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}
	
	public NewWebComponentAction(IWorkbench workbench, String label, Class[] activatedOnTypes, boolean acceptEmptySelection) {
		super(workbench, label, activatedOnTypes, acceptEmptySelection);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}
	
	public NewWebComponentAction(IWorkbench workbench, String label, boolean acceptEmptySelection) {
		super(workbench, label, acceptEmptySelection);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	protected Wizard createWizard() {
		return new WebProjectWizard();
	}
	
	protected boolean shouldAcceptElement(Object obj) { 
		return true; /* NewGroup.isOnBuildPath(obj) && !NewGroup.isInArchive(obj); */
	}
	protected String getDialogText() {
		return null;
	}
}
