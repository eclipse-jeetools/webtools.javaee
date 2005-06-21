/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.workbench.utility;

import org.eclipse.osgi.util.NLS;

public final class WorkbenchUtilityMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.workbench.utility.messages";//$NON-NLS-1$

	private WorkbenchUtilityMessages() {
		// Do not instantiate
	}

	public static String ParseTreeCreationFromAST_0;
	public static String ParseTreeCreationFromAST_ExpressionTooComplicated_EXC_;
	public static String ParseTreeCreationFromAST_CannotProcessAnonymousDeclarations_EXC_;
	public static String ParseTreeCreationFromAST_OperatorTooComplicatedToHandle_EXC_;
	public static String ASTBoundResolver_CannotHandleLocalField_EXC_;
	public static String ASTBoundResolver_CannotHandleLocalVariable_EXC_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, WorkbenchUtilityMessages.class);
	}
}