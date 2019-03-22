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
package org.eclipse.jem.internal.proxy.remote;
/*


 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import java.math.BigInteger;
/**
 * Remote Proxy for BigIntegers.
 */
class REMBigIntegerBeanProxy extends REMNumberBeanProxy {

REMBigIntegerBeanProxy(REMProxyFactoryRegistry aRegistry, BigInteger aBigInteger) {
	super(aRegistry, aBigInteger);
}

/**
 * Get the beantype
 */
public IBeanTypeProxy getTypeProxy() {
	return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).bigIntegerClass;
}

/**
 * Render the bean into value object.
 */
public void renderBean(Commands.ValueObject value) {
	value.set(numberValue(), ((IREMBeanTypeProxy) getTypeProxy()).getID().intValue());
}
}


