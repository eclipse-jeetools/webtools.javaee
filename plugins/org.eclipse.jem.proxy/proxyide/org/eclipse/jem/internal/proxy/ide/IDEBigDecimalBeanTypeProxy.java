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
 *  $RCSfile: IDEBigDecimalBeanTypeProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/05/11 22:41:24 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import java.math.*;

final class IDEBigDecimalBeanTypeProxy extends IDENumberBeanTypeProxy {
// Some values are cache'd for speed
IDEBigDecimalBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass,new BigDecimal(0));
}
/**
 * Static helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createBigDecimalBeanProxy(BigDecimal aBigDecimal) {
	if ( aBigDecimal == null || aBigDecimal.intValue() == 0 ) {
		return zeroProxy;
	} else {
		return new IDENumberBeanProxy(fProxyFactoryRegistry,aBigDecimal,this);
	}
}
}

