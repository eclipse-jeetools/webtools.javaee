/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: IConstructorProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:35:20 $ 
 */


/**
 * This is a proxy that allows a constructor to exist in the target VM
 * and be referenced in the IDE VM
 * Creation date: (1/17/00 1:21:52 PM)
 * @author: Joe Winchester
 */
public interface IConstructorProxy extends IAccessibleObjectProxy {
/**
 * Return a new instance with no creation arguments,
 * i.e. for a null constructor
 * Creation date: (1/17/00 1:22:11 PM)
 */
IBeanProxy newInstance() throws ThrowableProxy;
/**
 * Return a new instance with the specified creation arguments
 * Creation date: (1/17/00 1:22:11 PM)
 */
IBeanProxy newInstance(IBeanProxy[] creationArguments) throws ThrowableProxy;

IBeanProxy newInstanceCatchThrowableExceptions();
IBeanProxy newInstanceCatchThrowableExceptions(IBeanProxy[] creationArguments);

}
