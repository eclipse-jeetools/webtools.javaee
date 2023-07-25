/*******************************************************************************
 * Copyright (c) 2008, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Stefan Dimov, stefan.dimov@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.widgets.Shell;

@Deprecated
public class ChangeLibDirDialog extends InputDialog {
	private boolean warnBlank;
	
    public ChangeLibDirDialog(Shell parentShell, String initialValue, boolean warnBlank) { 
    	super(parentShell, J2EEUIMessages.getResourceString(J2EEUIMessages.CHANGE_LIB_DIR_HEAD),
    			J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_LIB_DIR_PROPMPT), initialValue, null);
    	this.warnBlank = warnBlank;
    }
    
    @Override
	protected void buttonPressed(int buttonId) {
        if (buttonId == IDialogConstants.OK_ID) {
        	String res = getText().getText().trim(); 
        	if (res.length() == 0) {
    			if (warnBlank)
    				if (!MessageDialog.openQuestion(this.getShell(), 
    					J2EEUIMessages.getResourceString(J2EEUIMessages.BLANK_LIB_DIR), 
    					J2EEUIMessages.getResourceString(J2EEUIMessages.BLANK_LIB_DIR_CONFIRM))) return;
        	} else {
    			res = res.substring(1);	
    			String[] segments = res.split("" + IPath.SEPARATOR); //$NON-NLS-1$
    			Path p = new Path(""); //$NON-NLS-1$
    			boolean valid = true;
    			for (int i = 0; i < segments.length; i++) {
    				valid = p.isValidSegment(segments[i]);
    				if (!valid)
    					break;
    			}
    			if (!valid) {
    				MessageDialog.openError(null, 
    						J2EEUIMessages.getResourceString(J2EEUIMessages.INVALID_PATH), 
    						J2EEUIMessages.getResourceString(J2EEUIMessages.INVALID_PATH_MSG));	
    				return;
    			}	        		
        	}
        } 
        super.buttonPressed(buttonId);
    }    
}

