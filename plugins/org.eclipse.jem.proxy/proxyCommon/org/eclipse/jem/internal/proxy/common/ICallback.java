package org.eclipse.jem.internal.proxy.common;
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

/**
 * This interface is to be implemented by any callback.
 * The callback will use the IVMServer passed in to
 * get callback handlers, and the id passed in is
 * to be passed to these handlers.
 */
public interface ICallback {
	
	/**
	 * Initialize the callback
	 */
	public void initializeCallback(IVMServer vmServer, int callbackID);

}