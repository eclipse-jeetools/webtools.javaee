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
 *  $RCSfile: IDEDoubleClassBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:35:20 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * Double BeanType Proxy.
 */
final class IDEDoubleClassBeanTypeProxy extends IDENumberBeanTypeProxy {

protected IDEDoubleClassBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass, new Double(0));
}
INumberBeanProxy createDoubleBeanProxy(Double aDouble){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,aDouble,this);
}
}


