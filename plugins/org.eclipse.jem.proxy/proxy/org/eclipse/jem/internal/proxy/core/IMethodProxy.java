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
/*


 */
package org.eclipse.jem.internal.proxy.core;

/**
 * Interface for Method Proxies Creation date: (12/3/99 11:36:29 AM)
 * 
 * @author: Joe Winchester
 */
public interface IMethodProxy extends IAccessibleObjectProxy, IInvokable, IProxyMethod {

	/**
	 * Answer the class the method is defined in. Creation date: (12/3/99 11:37:12 AM)
	 * 
	 * @author Joe Winchester
	 */
	IBeanTypeProxy getClassType();

	/**
	 * Answer the name of the method Creation date: (12/3/99 11:37:12 AM)
	 * 
	 * @author Joe Winchester
	 */
	String getName();

	/**
	 * Answer the parameter types of the method Creation date: (12/3/99 11:37:12 AM)
	 * 
	 * @author Joe Winchester
	 */
	IBeanTypeProxy[] getParameterTypes();

	/**
	 * Answer the return type of the method Creation date: (12/3/99 11:37:12 AM)
	 * 
	 * @author Joe Winchester
	 */
	IBeanTypeProxy getReturnType();

}
