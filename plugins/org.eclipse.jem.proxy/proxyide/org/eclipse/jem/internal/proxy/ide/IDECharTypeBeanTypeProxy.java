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
 *  $RCSfile: IDECharTypeBeanTypeProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:39:06 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

/**
 * char BeanType Proxy.
 */
final class IDECharTypeBeanTypeProxy extends IDEPrimitiveBeanTypeProxy {
protected IDECharTypeBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
}
ICharacterBeanProxy createCharBeanProxy(char aChar){
	return new IDECharacterBeanProxy(fProxyFactoryRegistry,new Character(aChar),this);
}
int getPrimitiveType(){
	return CHAR;
}
protected IIDEBeanProxy newBeanProxy(Object anObject){
	Character c = anObject instanceof Character ? (Character) anObject : new Character((char)((Number) anObject).intValue());
	return new IDECharacterBeanProxy(fProxyFactoryRegistry, c, this);
	
}
}


