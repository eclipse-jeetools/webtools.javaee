/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IRemoteProxyConstants.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:04 $ 
 */
package org.eclipse.jem.internal.proxy.remote;
 
/**
 * These are API constants needed to work with the the remote vm proxy support. 
 * @since 1.0.0
 */
public interface IRemoteProxyConstants {

	/**
	 * Launch configuration type for local proxy. It is local in that it is on the same machine, but a different
	 * VM then the one running the IDE. 
	 */
	public static final String LOCAL_LAUNCH_TYPE = "org.eclipse.jem.proxy.LocalProxyLaunchConfigurationType";

}
