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
 *  $RCSfile: IDECharacterClassBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:36 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * Char BeanType Proxy.
 */
final class IDECharacterClassBeanTypeProxy extends IDEBeanTypeProxy {
	
protected IDECharacterClassBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
}
ICharacterBeanProxy createCharacterBeanProxy(Character aChar){
	return new IDECharacterBeanProxy(fProxyFactoryRegistry,aChar,this);
}
}
