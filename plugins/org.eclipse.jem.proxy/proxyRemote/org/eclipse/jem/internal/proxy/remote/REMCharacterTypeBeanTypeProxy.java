package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: REMCharacterTypeBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * Character.TYPE BeanType Proxy.
 * Creation date: (2/23/00 1:59:02 PM)
 * @author: Richard Lee Kulp
 */
final class REMCharacterTypeBeanTypeProxy extends REMPrimitiveBeanTypeProxy {

	final REMCharacterTypeBeanProxy nilProxy;	// Value of '\0'
/**
 * REMCharacterBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMCharacterTypeBeanTypeProxy(REMProxyFactoryRegistry aRegistry) {
	super(aRegistry, new Integer(Commands.CHARACTER_TYPE), Character.TYPE.getName());
	
	nilProxy = new REMCharacterTypeBeanProxy(aRegistry, '\0');
}

/**
 * Static helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 * Use the cache.
 */
ICharacterBeanProxy createCharacterBeanProxy(char aCharacter) {
	return new REMCharacterTypeBeanProxy(fRegistry, aCharacter);
}

/**
 * newInstance method. Character doesn't have a default ctor, so the standard way won't work.
 * Return the nilProxy for this case because that is the default value.
 */
public IBeanProxy newInstance() {
	return nilProxy;
}

/**
 * Create a new bean proxy the ValueObject passed in.
 * We will assume the object is correct type.
 */
public IBeanProxy newBeanProxy(Commands.ValueObject value) {
	return createCharacterBeanProxy(value.aChar);
}
}


