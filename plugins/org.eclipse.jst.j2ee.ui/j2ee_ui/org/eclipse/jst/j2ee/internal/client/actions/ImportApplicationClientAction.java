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
/*
 * Created on Mar 27, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.client.actions;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.internal.actions.BaseAction;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.wizard.AppClientComponentImportWizard;
import org.eclipse.swt.widgets.Shell;


/**
 * @author jsholl
 * 
 * To change this generated comment go to Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ImportApplicationClientAction extends BaseAction {

	public static String LABEL = AppClientArchiveUIResourceHandler.getString("Application_Client_Import_UI_"); //$NON-NLS-1$
	private static final String ICON = "appclient_import_wiz"; //$NON-NLS-1$

	public ImportApplicationClientAction() {
		super();
		setText(LABEL);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(ICON));
	}

	protected void primRun(Shell shell) {

		AppClientComponentImportWizard wizard = new AppClientComponentImportWizard();

		J2EEUIPlugin plugin = J2EEUIPlugin.getDefault();

		wizard.init(plugin.getWorkbench(), StructuredSelection.EMPTY);

		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.create();
		dialog.open();
	}

}