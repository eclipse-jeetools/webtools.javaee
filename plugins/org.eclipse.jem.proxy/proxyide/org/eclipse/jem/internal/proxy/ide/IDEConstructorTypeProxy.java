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
package org.eclipse.jem.internal.proxy.ide;
/*


 */


/**
 * @version 	1.0
 * @author
 */
public class IDEConstructorTypeProxy extends IDEBeanTypeProxy {
	
	IDEConstructorTypeProxy(IDEProxyFactoryRegistry registry) {
		super(registry, java.lang.reflect.Constructor.class);
	}

	/*
	 * @see IDEBeanTypeProxy#newBeanProxy(Object)
	 */
	protected IIDEBeanProxy newBeanProxy(Object anObject) {
		return new IDEConstructorProxy(fProxyFactoryRegistry, (java.lang.reflect.Constructor) anObject);
	}

}
