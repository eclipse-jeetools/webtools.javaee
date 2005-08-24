/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: ICallback.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:39:05 $ 
 */

import java.io.InputStream;

/**
 * Users will create a callback of this interface
 * and register it with the factory. Then when ever
 * the callback occurs, this callback will be called
 * with data from the proxy. It can return a value too.
 *
 * Or an InputStream can be returned to supply the data.
 *  
 * @since 1.0.0
 */
public interface ICallback {

	/**
	 * This is the entry point of the callback.
	 * It will be called whenever the callback
	 * occurred. It will be on its own thread.
	 * A particular thread cannot be requested.
	 * <p>
	 * The implementation MUST return. This is 
	 * because the callback will not be completed
	 * until it is returned, and the process will
	 * not continue on the remote vm until it is
	 * returned.
	 * <p>
	 * The value returned must either be an
	 * IBeanProxy or IBeanProxy[]. It is typed
	 * to Object to allow either one, but it
	 * will be checked, and if it isn't, then
	 * null will be returned instead to the
	 * caller.
	 * 
	 * @param msgID
	 * @param parm The beanproxy - will not be null
	 * @return
	 * 
	 * @since 1.0.0
	 */
	public Object calledBack(int msgID, IBeanProxy parm);

	/**
	 * This is the entry point of the callback.
	 * It will be called whenever the callback
	 * occurred. It will be on its own thread.
	 * A particular thread cannot be requested.
	 * <p>
	 * The parms will be an array of IBeanProxys,
	 * or an entry could be another array of IBeanProxys,
	 * or null if null was sent to callBackWithParms.
	 * The final component of any entry will be an
	 * IBeanProxy. It is up to the developers to
	 * agree on the format of the parms.
	 * <p>
	 * The implementation MUST return. This is 
	 * because the callback will not be completed
	 * until it is returned, and the process will
	 * not continue on the remote vm until it is
	 * returned.
	 * <p>
	 * The value returned must either be an
	 * IBeanProxy or IBeanProxy[]. It is typed
	 * to Object to allow either one, but it
	 * will be checked, and if it isn't, then
	 * null will be returned instead to the
	 * caller.
	 * 
	 * @param msgID
	 * @param parms
	 * @return
	 * 
	 * @see org.eclipse.jem.internal.proxy.common.ICallbackHandler#callbackWithParms(int, int, Object[])
	 * @since 1.0.0
	 */
	public Object calledBack(int msgID, Object[] parms);
	
	/**
	 * This is the entry point of the callback.
	 * It will be called whenever the callback
	 * occurred. It will be on its own thread.
	 * A particular thread cannot be requested.
	 * <p>
	 * The parm will be an object. This occurs
	 * if remote vm just wanted to send some objects.
	 * They will not be proxies. It is recommended
	 * that this be used only for small items. For
	 * anything large, the callbackStream should be
	 * used instead.
	 * <p>
	 * The implementation MUST return. This is 
	 * because the callback will not be completed
	 * until it is returned, and the process will
	 * not continue on the remote vm until it is
	 * returned.
	 * <p>
	 * The value returned must either be an
	 * IBeanProxy or IBeanProxy[]. It is typed
	 * to Object to allow either one, but it
	 * will be checked, and if it isn't, then
	 * null will be returned instead to the
	 * caller.
	 * 
	 * @param msgID
	 * @param parm The parm or <code>null</code> if null was sent.
	 * @return
	 * 
	 * @see ICallback#calledBackStream(int, InputStream)
	 * @since 1.0.0
	 */
	public Object calledBack(int msgID, Object parm);

	/**
	 * This is the entry point of the callback.
	 * It will be called whenever the callback stream
	 * occurred. It will be on its own thread.
	 * A particular thread cannot be requested.
	 * <p>
	 * The callback should continue to read from the InputStream
	 * until it returns -1 indicating no more data. The stream can
	 * be closed. In that case the next time the remote vm wants to
	 * send data it will first check that the stream has not been closed.
	 * If it has, it will raise an exception on that side.
	 * <p>
	 * The implementation MUST return. This is 
	 * because the callback will not be completed
	 * until it is returned, and the process will
	 * not continue on the remote vm until it is
	 * returned.
	 * 
	 * @param msgID
	 * @param is
	 * 
	 * @since 1.0.0
	 */
	public void calledBackStream(int msgID, InputStream is);	
}
