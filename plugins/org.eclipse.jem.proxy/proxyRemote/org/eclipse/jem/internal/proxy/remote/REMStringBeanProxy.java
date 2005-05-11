package org.eclipse.jem.internal.proxy.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMStringBeanProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/11 22:41:24 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

/**
 * Remote VM Implementation of IStringBeanProxy
 * Creation date: (2/6/00 9:02:42 AM)
 * @author: Joe Winchester
 */
final class REMStringBeanProxy extends REMConstantBeanProxy implements IStringBeanProxy {
	private final String fString;

/**
 * Store the bean in the string field to save re-casting each time it is asked for.
 * It is package protected because they are created
 * in a special way and no one else should create them.
 */
REMStringBeanProxy(REMProxyFactoryRegistry aRegistry, String value) {
	super(aRegistry);
	fString = value;
}
/**
 * equals: Equal if:
 *         1) This proxy == (identity) to the other object
 *         2) Else if other is an IBeanProxy and not a constant one, then if
 *            equals on the server.
 *         3) If this is a constant proxy and the other is too or is a constant
 *            value (e.g. IStringBeanProxy.equals(String), then true if values are equals.
 */
public boolean equals(Object anObject) {
	if (this == anObject)
		return true;	// Identity
	if (anObject instanceof IStringBeanProxy)
		return fString.equals(((IStringBeanProxy) anObject).stringValue());
	else
		return fString.equals(anObject);
}
/**
 * Return the cache'd string that is a java.lang.String that the IDE can use
 */
public String stringValue() {
	return fString;
}

/**
 * Return the toString value from the bean.
 */
public String toBeanString() {
	return fString.toString();
}

/**
 * Get the beantype
 */
public IBeanTypeProxy getTypeProxy() {
	return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).stringClass;
}

/**
 * Render the bean into value object.
 */
public void renderBean(Commands.ValueObject value) {
	value.set(fString);
}
}


