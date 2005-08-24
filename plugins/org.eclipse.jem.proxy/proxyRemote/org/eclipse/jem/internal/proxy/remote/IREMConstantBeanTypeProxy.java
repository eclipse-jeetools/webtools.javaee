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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: IREMConstantBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * This interface is used for any BeanType that represents a constant type bean.
 * These are types that are immutable and a copy of the actual value is kept here
 * in the client and not stored as an object id.
 */

public interface IREMConstantBeanTypeProxy extends IBeanTypeProxy {
	
	/**
	 * Create a new bean proxy the ValueObject passed in.
	 */
	public IBeanProxy newBeanProxy(Commands.ValueObject value);
}
