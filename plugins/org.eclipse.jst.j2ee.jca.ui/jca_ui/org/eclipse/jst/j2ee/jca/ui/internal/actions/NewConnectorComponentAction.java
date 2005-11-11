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
package org.eclipse.jst.j2ee.jca.ui.internal.actions;


import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.internal.actions.AbstractOpenWizardWorkbenchAction;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.jca.ui.internal.util.JCAUIMessages;
import org.eclipse.jst.j2ee.jca.ui.internal.wizard.ConnectorProjectWizard;
import org.eclipse.ui.IWorkbench;



/**
 * Creates an action for the Connector project
 */
public class NewConnectorComponentAction extends AbstractOpenWizardWorkbenchAction {

	// TODO MDE 02-28 Find correct label
	public static String LABEL = JCAUIMessages.JCA_MODULE_WIZ_TITLE;
	private static final String ICON = "newear_wiz"; //$NON-NLS-1$

	/**
	 * Default constructor
	 */
	public NewConnectorComponentAction() {
		setText(LABEL);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}// NewConnectorProjectAction

	/**
	 * Constructor initialization.
	 * 
	 * @param IWorkbench
	 *            workbench - Workbench to add to this wizard too.
	 * @param String
	 *            label - Label for project.
	 * @param Class[]
	 *            acceptedTypes - List of accepted types for this wizard
	 */
	public NewConnectorComponentAction(IWorkbench workbench, String label, Class[] acceptedTypes) {
		super(workbench, label, acceptedTypes, false);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}// NewConnectorProjectAction

	/**
	 * Creates the wizard
	 * 
	 * @return Wizard
	 */
	protected Wizard createWizard() {
		return new ConnectorProjectWizard();
	}// createWizard

	/**
	 * shouldAcceptElement - Always true
	 * 
	 * @param Object
	 *            obj
	 */
	protected boolean shouldAcceptElement(Object obj) {
		return true; /* NewGroup.isOnBuildPath(obj) && !NewGroup.isInArchive(obj); */
	}// shouldAcceptElement

	protected String getDialogText() {
		return null;
	}
}// NewConnectorProjectAction
