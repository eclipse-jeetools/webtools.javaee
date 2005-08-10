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
package org.eclipse.jem.internal.proxy.core;

import org.eclipse.osgi.util.NLS;

public final class ProxyMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.proxy.core.messages";//$NON-NLS-1$

	private ProxyMessages() {
		// Do not instantiate
	}

	public static String UnexpectedException_EXC_;
	public static String ClassCast_EXC__IncorrectType;
	public static String ProxyFactory_EXC__NoBeanProxyFactory;
	public static String ProxyFactory_EXC__NoBeanTypeProxyFactory;
	public static String Not_Java_Project_WARN_;
	public static String No_VM_WARN_;
	public static String No_Implementation_WARN_;
	public static String ProxyLaunch;
	public static String ProxyWaitForBuild;
	public static String Expression_InInvalidStateDueTo_EXC_;
	public static String Expression_InInvalidState_EXC_;
	public static String Expression_TypeSentInInvalidOrder_EXC_;
	public static String Expression_ArrayTypeNotAnArray_EXC_;
	public static String Expression_CannotHandleNoReceiveOnFieldAccess_EXC_;
	public static String Expression_MethodsNeedReceiver_EXC_;
	public static String Expression_InvalidMarkNesting;
	public static String ProxyPlugin_CleanupDefaultProxyLaunchConfigurations;
	public static String ProxyLaunchSupport_RegistryCouldNotStartForSomeReason_WARN_;
	public static String Expression_CreateProxyReassignmentExpression_InvalidForReassignment_EXC_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProxyMessages.class);
	}

	public static String Expression_InvalidProxy;
}