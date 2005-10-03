/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: StatusHelper.java,v $
 *  $Revision: 1.1 $  $Date: 2005/10/03 23:06:42 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.ui.core.JEMUIPlugin;
 

public class StatusHelper {

	public static final IStatus OK_STATUS = createStatus(IStatus.OK, ""); //$NON-NLS-1$
	public static final IStatus ERROR_STATUS = createStatus(IStatus.ERROR, ""); //$NON-NLS-1$
	
	/**
	 * Creates a status with the provided severity and message
	 * 
	 * @param severity
	 * @param message
	 * @return
	 * 
	 * @since 1.2.0
	 */
	public static IStatus createStatus(int severity, String message){
		return new Status(severity, 
				JEMUIPlugin.getPlugin().getBundle().getSymbolicName(), 
				severity, message, null);
	}

	/**
	 * Finds the most severe status from a array of stati.
	 * An error is more severe than a warning, and a warning is more severe
	 * than ok.
	 * 
	 * @param status
	 * @return
	 * 
	 * @since 1.2.0
	 */
	public static IStatus getMostSevere(IStatus[] status) {
		IStatus max= null;
		for (int i= 0; i < status.length; i++) {
			IStatus curr= status[i];
			if (curr.matches(IStatus.ERROR)) {
				return curr;
			}
			if (max == null || curr.getSeverity() > max.getSeverity()) {
				max= curr;
			}
		}
		return max;
	}

}
