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
 *  $RCSfile: IVMServer.java,v $
 *  $Revision: 1.3 $  $Date: 2005/07/08 17:51:47 $ 
 */
package org.eclipse.jem.internal.proxy.common;
 
/**
 * This is the base server for the vm on the vm side (i.e. not the client side).
 * <p>
 * It can be passed to proxies if they need to access the vm server.
 * @since 1.1.0
 */
public interface IVMServer {

	/**
	 * Add a runnable that will be called if shutdown is requested. If already added,
	 * it will not be added again.
	 * <p>
	 * The body of the runnable must not do any vm server calls. That is because
	 * the server is shutdown at this point. The body must simply do clean up code.
	 * The shutdown will not continue until the call to the runnable is returned.
	 * <p>
	 * Calls to this will be ignored if shutdown has already started.
	 * @param runnable
	 * 
	 * @since 1.1.0
	 */
	public void addShutdownListener(Runnable runnable);

	/**
	 * Remove the shutdown runnable from the list. If not added, then not an error to call this.
	 * <p>
	 * Calls to this will be ignored if shutdown has already started.
	 * @param runnable
	 * 
	 * @since 1.1.0
	 */
	public void removeShutdownListener(Runnable runnable);

}
