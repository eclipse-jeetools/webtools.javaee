/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Stefan Dimov, stefan.dimov@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.widgets.Shell;

public class DependencyConflictResolveDialog extends MessageDialogWithToggle {
	
	public static final int BTN_ID_OK = 0;
	public static final int BTN_ID_CANCEL = 1;
	
	public static final int DLG_TYPE_1 = 1;
	public static final int DLG_TYPE_2 = 2;
	
	public static final String DONT_SHOW_AGAIN = "DependencyConflictResolveDialog.DONT_SHOW_AGAIN";  //$NON-NLS-1$
	
	public DependencyConflictResolveDialog(Shell parentShell, 
			int dlgType) {
		
		super(parentShell, 
				J2EEUIMessages.getResourceString(J2EEUIMessages.DEPENDENCY_CONFLICT_TITLE), 
				null, 
				J2EEUIMessages.getResourceString((dlgType == DLG_TYPE_1) ? 
						J2EEUIMessages.DEPENDENCY_CONFLICT_MSG_1 :
						J2EEUIMessages.DEPENDENCY_CONFLICT_MSG_2)
						
				, MessageDialog.WARNING,
				
				new String[] { J2EEUIMessages.OK_BUTTON, 
					J2EEUIMessages.CANCEL_BUTTON }, 
				BTN_ID_CANCEL,
				J2EEUIMessages.getResourceString(J2EEUIMessages.DO_NOT_SHOW_WARNING_AGAIN),
				false);
	}
		
	@Override
	public int open() {
		if (getPrefStore().getBoolean(DONT_SHOW_AGAIN))
			return BTN_ID_OK;
		setToggleState(getPrefStore().getBoolean(DONT_SHOW_AGAIN));
		return super.open();
	}
	
	@Override
	public boolean close() {
		getPrefStore().setValue(DONT_SHOW_AGAIN, getToggleState());
		return super.close();
	}
	
	@Override
	public IPreferenceStore getPrefStore() {
		return J2EEUIPlugin.getDefault().getPreferenceStore();
	}
}
