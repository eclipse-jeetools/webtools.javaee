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
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDEFieldTypeProxy.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 22:57:26 $ 
 */

/**
 * @version 	1.0
 * @author
 */
public class IDEFieldTypeProxy extends IDEBeanTypeProxy {
	
	IDEFieldTypeProxy(IDEProxyFactoryRegistry registry) {
		super(registry, java.lang.reflect.Field.class);
	}

	/*
	 * @see IDEBeanTypeProxy#newBeanProxy(Object)
	 */
	protected IIDEBeanProxy newBeanProxy(Object anObject) {
		return new IDEFieldProxy(fProxyFactoryRegistry, (java.lang.reflect.Field) anObject);
	}
}
