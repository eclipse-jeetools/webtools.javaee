package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ICallbackRegistry.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

/**
 * This is the registry for handling callbacks.
 * It is used to register and deregister callbacks.
 *
 * Callbacks are split into two parts:
 * 1) CallbackProxy. This is any proxy that implements the
 *    interface ICallback. (Or the
 *    equivalent one if not standard remote vm). This is the
 *    object which will actually submit the callback on the remote
 *    vm. For example, any kind of listener. It would implement
 *    both the ICallback and the appropriate listener interface.
 *    Then when it gets called as a listener, it will redirect the
 *    call to the remote vm callback handler to submit it to the
 *    proxy side.
 * 2) Callback. This is on this side and is the object that will
 *    be notified of the callback and will execute at that time.
 *
 */

public interface ICallbackRegistry {
	
	/**
	 * Register this callback proxy and this callback.
	 *
	 * The process is to:
	 * 1) Create the callbackProxy and the callback
	 * 2) register these with registerCallback
	 * 3) Now connect the callbackProxy so that it will start notifying.
	 *    For example, if it is a normal listener, at this time do the
	 *    addListener call to add the callbackProxy to the appropriate object.
	 *    You don't want it to start notifying before registering it.
	 *    (Though it won't hust it, it will just return null).
	 */
	public void registerCallback(IBeanProxy callbackProxy, ICallback cb);
	
	/**
	 * Deregister the callback proxy. This will remove it from the
	 * registry and release the callback.
	 *
	 * The process is to:
	 * 1) Stop the proxy from listening so it no notifies. This is usually
	 *    removeListener on the remote vm. You don't want it notifying
	 *    after it deregister (though it won't hurt it, it will just return null).
	 * 2) deregister it.
	 */
	public void deregisterCallback(IBeanProxy callbackProxy);
}