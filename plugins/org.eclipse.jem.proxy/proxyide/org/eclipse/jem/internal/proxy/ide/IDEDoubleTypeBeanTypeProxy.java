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
 *  $RCSfile: IDEDoubleTypeBeanTypeProxy.java,v $
 *  $Revision: 1.2.4.1 $  $Date: 2004/06/24 18:19:03 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * double BeanType Proxy.
 */
final class IDEDoubleTypeBeanTypeProxy extends IDEPrimitiveBeanTypeProxy {
protected IDEDoubleTypeBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
}
INumberBeanProxy createDoubleBeanProxy(double aDouble){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,new Double(aDouble),this);
}
int getPrimitiveType(){
	return DOUBLE;
}

protected IIDEBeanProxy newBeanProxy(Object anObject){
	Number n = anObject instanceof Character ? new Double(((Character) anObject).charValue()) : (Number) anObject;
	return new IDENumberBeanProxy(fProxyFactoryRegistry, n, this);
}
}
