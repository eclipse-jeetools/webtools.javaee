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
 *  $RCSfile: REMCharacterClassBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * Character BeanType Proxy for Character.class.
 * Creation date: (2/23/00 1:59:02 PM)
 * @author: Richard Lee Kulp
 */
final class REMCharacterClassBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMConstantBeanTypeProxy {
	final REMCharacterClassBeanProxy nilProxy;	// Value of '\0'

REMCharacterClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy objectType) {
	super(aRegistry, new Integer(Commands.CHARACTER_CLASS), Character.class.getName(), objectType);
	
	nilProxy = new REMCharacterClassBeanProxy(aRegistry, new Character('\0'));
}
/**
 * Static helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
ICharacterBeanProxy createCharacterBeanProxy(Character aCharacter) {
	return (aCharacter.charValue() != '\0') ?
		new REMCharacterClassBeanProxy(fRegistry, aCharacter) : nilProxy;
}

/**
 * Create a new bean proxy with the specified id.
 *
 * For all of the primitive wrapper types, this is an invalid operation. They are created
 * explicitly through the appropriate create method.
 */
public IREMBeanProxy newBeanProxy(Integer anID) {
	throw new UnsupportedOperationException();
}

/**
 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
 * This is because subclasses, unless explicitly overridden, should use the same kind
 * of beantype proxy. This way if anything special is needed for a specific type, then
 * that can be done.
 *
 * For all of the primitive wrapper types, they are final classes, so you can't create a newBeanType 
 * for subclasses.
 */
public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
	throw new UnsupportedOperationException();
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
	return createCharacterBeanProxy(new Character(value.aChar));
}
}


