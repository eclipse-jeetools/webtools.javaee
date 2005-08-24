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
package org.eclipse.jem.internal.proxy.common;
/*
 *  $RCSfile: ICallback.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:39:07 $ 
 */

/**
 * This interface is to be implemented by any callback.
 * The callback will use the IVMCallbackServer passed in to
 * get callback handlers, and the id passed in is
 * to be passed to these handlers.
 */
public interface ICallback {
	
	/**
	 * Initialize the callback
	 */
	public void initializeCallback(IVMCallbackServer vmServer, int callbackID);

}
