package org.eclipse.jem.internal.proxy.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMBigDecimalBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import java.math.BigDecimal;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Proxy for BigDecimals.
 */
class REMBigDecimalBeanProxy extends REMNumberBeanProxy {

REMBigDecimalBeanProxy(REMProxyFactoryRegistry aRegistry, BigDecimal aBigDecimal) {
	super(aRegistry, aBigDecimal);
}

/**
 * Get the beantype
 */
public IBeanTypeProxy getTypeProxy() {
	return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).bigDecimalClass;
}

/**
 * Render the bean into value object.
 */
public void renderBean(Commands.ValueObject value) {
	value.set(numberValue(), ((IREMBeanTypeProxy) getTypeProxy()).getID().intValue());
}	
}


