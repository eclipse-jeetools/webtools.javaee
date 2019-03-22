/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.common;
/*


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
