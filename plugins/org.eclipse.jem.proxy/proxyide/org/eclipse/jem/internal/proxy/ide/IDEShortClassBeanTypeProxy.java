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
 *  $RCSfile: IDEShortClassBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * Short BeanType Proxy.
 */
final class IDEShortClassBeanTypeProxy extends IDENumberBeanTypeProxy {
protected IDEShortClassBeanTypeProxy(ProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass, new Short((short)0));
}
INumberBeanProxy createShortBeanProxy(Short aShort){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,aShort,this);
}
}
