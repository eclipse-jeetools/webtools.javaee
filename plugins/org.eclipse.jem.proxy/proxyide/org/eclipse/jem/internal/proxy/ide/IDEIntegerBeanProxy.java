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
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDEIntegerBeanProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:35:20 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * IDE Proxy for Integers. Since Integers were first implemented
 * in the VCE, and because they are often referenced, we've created
 * a subclass to handle them. That way we wouldn't need to change
 * everyone that was using the IIntegerProxy interface and we
 * can store the int value to speed up access.
 */
public class IDEIntegerBeanProxy extends IDENumberBeanProxy implements IIntegerBeanProxy {
	protected int fIntValue;
IDEIntegerBeanProxy(IDEProxyFactoryRegistry aRegistry, Number anInteger, IBeanTypeProxy aBeanTypeProxy) {
	super(aRegistry, anInteger , aBeanTypeProxy);
	fIntValue = anInteger.intValue();
}
/**
 * Return the int value
 */
public int intValue() {
	return fIntValue;
}
}
