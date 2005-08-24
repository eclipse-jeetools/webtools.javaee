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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMStringBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
 */


import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.*;

/**
 * Remote Implementation of the BeanType proxy for java.lang.String
 * Creation date: (2/6/00 9:01:23 AM)
 * @author: Joe Winchester
 */
final class REMStringBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMConstantBeanTypeProxy {
	// Cache a proxy to an empty string for speed
	protected final IStringBeanProxy emptyStringProxy;
/**
 * IDEStringBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMStringBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy objectTypeProxy) {
	super(aRegistry, new Integer(Commands.STRING_CLASS), String.class.getName(), objectTypeProxy);

	// Cache a proxy to an empty string for speed
	emptyStringProxy = new REMStringBeanProxy(aRegistry, "");	//$NON-NLS-1$
}
/**
 * Helper to create string beans
 * Package protected because no-one else outside the package should use this, they
 * must go through the API where you look up a factory first.
 */
IStringBeanProxy createStringBeanProxy(String aString){
	if ( aString.equals("") ) {//$NON-NLS-1$
		return emptyStringProxy;
	} else {
		return new REMStringBeanProxy(fRegistry, aString);
	}
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
 * newInstance method. String has a default ctor, but it results in the emptyStringProxy,
 * so return the zeroProxy for this case because that is the default value.
 */
public IBeanProxy newInstance() {
	return emptyStringProxy;
}

/**
 * newInstance method comment. Put a short cut in for strings so we don't need to go to the remote
 * vm just to create a string.
 */
public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
	if (initializationString.length() <= 1)
		return super.newInstance(initializationString);	// Not large enough for two quotes, so let default handle it.
	// Need a quick check that it is only one quoted string. Anything else needs to be passed on up.
	String s = initializationString.trim();
	int sl = s.length()-1;	
	if (s.charAt(0) != '"' || s.charAt(sl) != '"')
		return super.newInstance(initializationString);	// No leading and trailing quote.

	StringBuffer sb = new StringBuffer(sl);
	boolean escape = false;		
	for (int i=1; i<sl; i++) {
		char c = s.charAt(i);
		if (c == '\\') {
			escape = true;
			continue;
		}
		if (!escape && c == '"')
			return super.newInstance(initializationString);	// Had a quote in the middle without an escape. This means an expression, send on up.
		escape = false;
		sb.append(c);
	}
	
	return getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(sb.toString());
}

/**
 * Create a new bean proxy the ValueObject passed in.
 * We will assume the object is correct type.
 */
public IBeanProxy newBeanProxy(Commands.ValueObject value) {
	return createStringBeanProxy((String) value.anObject);
}

}


