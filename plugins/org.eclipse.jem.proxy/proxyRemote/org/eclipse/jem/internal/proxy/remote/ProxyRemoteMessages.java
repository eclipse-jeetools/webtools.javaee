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
package org.eclipse.jem.internal.proxy.remote;

import org.eclipse.osgi.util.NLS;

public final class ProxyRemoteMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.proxy.remote.messages";//$NON-NLS-1$

	private ProxyRemoteMessages() {
		// Do not instantiate
	}

	public static String NO_OUTPUT_FOLDER_EXC_;
	public static String VM_COMMAND_LINE;
	public static String VM_TERMINATED_INFO_;
	public static String VM_TERMINATED_LINE1;
	public static String VM_TERMINATED_LINE2;
	public static String VM_TERMINATED_LINE3;
	public static String REMOTE_VM_TRACE_INFO_;
	public static String REMOTE_VM_LOCAL_TRACE_INFO_;
	public static String Classcast_EXC_;
	public static String Instantiate_EXC_;
	public static String RemoteCmd_EXC_;
	public static String ExceptionErrorMsg_EXC_;
	public static String OK_7;
	public static String Proxy_NoRunner_ERROR_;
	public static String Proxy_Error_Title;
	public static String Proxy_Terminated_too_soon_ERROR_;
	public static String ProxyRemoteVMName;
	public static String ProxyRemoteVMNameWithComment;
	public static String ProxyRemoteNoLaunchKey;
	public static String ProxyRemoteLaunchVM;
	public static String CleanupJob_title;
	public static String REMProxyFactoryRegistry_CallbackConnectionNotWorking_EXC_;
	public static String REMProxyFactoryRegistry_ConnectionCreationFailed_INFO_;
	public static String REMProxyFactoryRegistry_Job_TerminateProcess_Title;
	public static String REMProxyFactoryRegistry_CouldNotCreateSocketConnectionToRemoteVM_EXC_;
	public static String REMExpression_IOExceptionSeeLog_INFO_;
	public static String REMExpression_CommandExceptionSeeLog_INFO_;
	public static String LocalProxyLaunchDelegate_Monitor_PrintRemoteTrace_Text;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProxyRemoteMessages.class);
	}
}