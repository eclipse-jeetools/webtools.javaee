package org.eclipse.jem.internal.proxy.ide;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IDEDoubleTypeBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * double BeanType Proxy.
 */
final class IDEDoubleTypeBeanTypeProxy extends IDEPrimitiveBeanTypeProxy {
protected IDEDoubleTypeBeanTypeProxy(ProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
}
INumberBeanProxy createDoubleBeanProxy(double aDouble){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,new Double(aDouble),this);
}
int getPrimitiveType(){
	return DOUBLE;
}
}
