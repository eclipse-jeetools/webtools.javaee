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
 *  $RCSfile: IDEByteTypeBeanTypeProxy.java,v $
 *  $Revision: 1.2.4.1 $  $Date: 2004/06/24 18:19:03 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * Byte BeanType Proxy.
 */
final class IDEByteTypeBeanTypeProxy extends IDEPrimitiveBeanTypeProxy {
protected IDEByteTypeBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
}
INumberBeanProxy createByteBeanProxy(byte aByte){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,new Byte(aByte),this);
}
int getPrimitiveType(){
	return BYTE;
}
protected IIDEBeanProxy newBeanProxy(Object anObject){
	Number n = anObject instanceof Character ? new Byte((byte)((Character) anObject).charValue()) : (Number) anObject;
	return new IDENumberBeanProxy(fProxyFactoryRegistry, n, this);
}
}

