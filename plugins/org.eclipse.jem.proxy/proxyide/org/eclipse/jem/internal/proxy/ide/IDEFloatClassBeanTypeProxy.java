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
 *  $RCSfile: IDEFloatClassBeanTypeProxy.java,v $
 *  $Revision: 1.4 $  $Date: 2005/02/15 22:57:26 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * Float BeanType Proxy.
 */
final class IDEFloatClassBeanTypeProxy extends IDENumberBeanTypeProxy {

protected IDEFloatClassBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass, new Float(0));
}
INumberBeanProxy createFloatBeanProxy(Float aFloat){
	return new IDENumberBeanProxy(fProxyFactoryRegistry,aFloat,this);
}
}

