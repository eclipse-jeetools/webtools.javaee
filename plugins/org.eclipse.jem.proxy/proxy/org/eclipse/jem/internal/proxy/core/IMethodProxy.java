/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IMethodProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/15 22:53:46 $ 
 */
package org.eclipse.jem.internal.proxy.core;

/**
 * Interface for Method Proxies Creation date: (12/3/99 11:36:29 AM)
 * 
 * @author: Joe Winchester
 */
public interface IMethodProxy extends IAccessibleObjectProxy, IInvokable {

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
