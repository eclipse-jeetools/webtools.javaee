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
package org.eclipse.jst.common.jdt.internal.integration;

import com.ibm.wtp.common.UIContextDetermination;


/**
 * @author mdelder
 * 
 *  
 */
public class WorkingCopyManagerFactory {

	//	protected static Class workingCopyManagerClass;

	public static WorkingCopyManager newRegisteredInstance() {
		return (WorkingCopyManager) UIContextDetermination.createInstance("workingCopyManager"); //$NON-NLS-1$
	}

	//	public static IWorkingCopyManager createWorkingCopyManager() {
	//		if (getWorkingCopyManagerClass() != null)
	//			try {
	//				return (IWorkingCopyManager) getWorkingCopyManagerClass().newInstance();
	//			} catch (InstantiationException e1) {
	//			} catch (IllegalAccessException e2) {
	//			}
	//		return null;
	//	}
	//	
	//	/**
	//	 * Insert the method's description here.
	//	 * Creation date: (4/26/2001 7:53:15 AM)
	//	 * @return java.lang.Class
	//	 */
	//	public static java.lang.Class getWorkingCopyManagerClass() {
	//		return workingCopyManagerClass;
	//	}
	//	
	//	/**
	//	 * Insert the method's description here.
	//	 * Creation date: (4/26/2001 7:53:15 AM)
	//	 * @param newWorkingCopyManagerClass java.lang.Class
	//	 */
	//	public static void setWorkingCopyManagerClass(java.lang.Class newWorkingCopyManagerClass) {
	//		workingCopyManagerClass = newWorkingCopyManagerClass;
	//	}

}