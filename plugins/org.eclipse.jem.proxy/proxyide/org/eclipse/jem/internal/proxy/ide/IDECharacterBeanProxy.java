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
 *  $RCSfile: IDECharacterBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * IDE Implementation of ICharacterBeanBeanProxy..
 */
class IDECharacterBeanProxy extends IDEObjectBeanProxy implements ICharacterBeanProxy {
	protected Character fCharacterValue;
IDECharacterBeanProxy(ProxyFactoryRegistry aRegistry, Object aBean, IBeanTypeProxy aBeanTypeProxy) {
	super(aRegistry, aBean,aBeanTypeProxy);
	fCharacterValue = (Character)aBean;
}
public char charValue() {
	return fCharacterValue.charValue();
}
public Character characterValue() {
	return fCharacterValue;
}
}




