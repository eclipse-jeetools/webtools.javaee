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
