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
 *  $RCSfile: IDEFloatTypeBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:35:20 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * Float BeanType Proxy.
 */
final class IDEFloatTypeBeanTypeProxy extends IDEPrimitiveBeanTypeProxy {
protected IDEFloatTypeBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
}
INumberBeanProxy createFloatBeanProxy(float aFloat){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,new Float(aFloat),this);
}
int getPrimitiveType(){
	return FLOAT;
}

protected IIDEBeanProxy newBeanProxy(Object anObject){
	Number n = anObject instanceof Character ? new Float(((Character) anObject).charValue()) : (Number) anObject;
	return new IDENumberBeanProxy(fProxyFactoryRegistry, n, this);

}
}


