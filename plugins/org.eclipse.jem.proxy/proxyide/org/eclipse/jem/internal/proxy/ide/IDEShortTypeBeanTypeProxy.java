/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
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
 *  $RCSfile: IDEShortTypeBeanTypeProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:39:06 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * Short BeanType Proxy.
 */
final class IDEShortTypeBeanTypeProxy extends IDEPrimitiveBeanTypeProxy {
protected IDEShortTypeBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
}
INumberBeanProxy createShortBeanProxy(short aShort){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,new Short(aShort),this);
}
int getPrimitiveType(){
	return SHORT;
}
protected IIDEBeanProxy newBeanProxy(Object anObject){
	Number n = anObject instanceof Character ? new Short((short) ((Character) anObject).charValue()) : (Number) anObject;
	return new IDENumberBeanProxy(fProxyFactoryRegistry, n, this);
}
}
