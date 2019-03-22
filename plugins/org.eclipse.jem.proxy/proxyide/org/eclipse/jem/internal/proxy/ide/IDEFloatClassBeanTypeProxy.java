/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.ide;
/*


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

