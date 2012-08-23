/*******************************************************************************
 * Copyright (c) 2008, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.javaee.ltk.core.nls;

import org.eclipse.osgi.util.NLS;

public class RefactoringResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "refactoring";//$NON-NLS-1$
	
	public static String Delete_EB_From_DD;
	public static String Delete_MDB_From_DD;
	public static String Delete_SB_From_DD;
	public static String Remove_JavaEE_References;
	public static String JavaEE_Refactorings_Participant_title;
	public static String Delete_EJB_Client_Project;
	public static String JavaEE_Server_Cleanup;

	public static String CheckStateTester_Could_not_find_Check_State_Tester_E_;
	
	private RefactoringResourceHandler() {
		// Do not instantiate
	}
	
	static {
		NLS.initializeMessages(BUNDLE_NAME, RefactoringResourceHandler.class);
	}	
}
