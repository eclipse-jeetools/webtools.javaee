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
 *  $RCSfile: ICallback.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.io.InputStream;
/**
 * Users will create a callback of this interface
 * and register it with the factory. Then when ever
 * the callback occurs, this callback will be called
 * with data from the proxy. It can return a value too.
 *
 * Or an InputStream can be returned to supply the data.
 */
public interface ICallback {
	
	/**
	 * This is the entry point of the callback.
	 * It will be called whenever the callback
	 * occurred. It will be on its own thread.
	 * A particular thread cannot be requested.
	 *
	 * The implementation MUST return. This is 
	 * because the callback will not be completed
	 * until it is returned, and the process will
	 * not continue on the remote vm until it is
	 * returned.
	 * 
	 * The value returned must either be an
	 * IBeanProxy or IBeanProxy[]. It is typed
	 * to Object to allow either one, but it
	 * will be checked, and if it isn't, then
	 * null will be returned instead to the
	 * caller.
	 */
	public Object calledBack(int msgID, IBeanProxy parm);
	
	/**
	 * This is the entry point of the callback.
	 * It will be called whenever the callback
	 * occurred. It will be on its own thread.
	 * A particular thread cannot be requested.
	 *
	 * The parms will be an array of IBeanProxys,
	 * or an entry could be another array of IBeanProxys.
	 * The final component of any entry will be an
	 * IBeanProxy. It is up to the developers to
	 * agree on the format of the parms.
	 *
	 * The implementation MUST return. This is 
	 * because the callback will not be completed
	 * until it is returned, and the process will
	 * not continue on the remote vm until it is
	 * returned.
	 * 
	 * The value returned must either be an
	 * IBeanProxy or IBeanProxy[]. It is typed
	 * to Object to allow either one, but it
	 * will be checked, and if it isn't, then
	 * null will be returned instead to the
	 * caller.
	 */
	public Object calledBack(int msgID, Object[] parms);
	
	/**
	 * This is the entry point of the callback.
	 * It will be called whenever the callback stream
	 * occurred. It will be on its own thread.
	 * A particular thread cannot be requested.
	 *
	 * The callback should continue to read from the InputStream
	 * until it returns -1 indicating no more data. The stream can
	 * be closed. In that case the next time the remote vm wants to
	 * send data it will first check that the stream has not been closed.
	 * If it has, it will raise an exception on that side.
	 *
	 * The implementation MUST return. This is 
	 * because the callback will not be completed
	 * until it is returned, and the process will
	 * not continue on the remote vm until it is
	 * returned.
	 * 
	 */
	public void calledBackStream(int msgID, InputStream is);	
}