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
 *  $RCSfile: REMNullBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Proxy for "null" value.
 */

final class REMNullBeanProxy extends REMConstantBeanProxy {
	
	REMNullBeanProxy(REMProxyFactoryRegistry aFactory) {
		super(aFactory);
	}
	/**
	 * equals: Equal if:
	 *         1) This proxy == (identity) to the other object
	 *         2) Else if other is an IBeanProxy and not a constant one, then if
	 *            equals on the server.
	 *         3) If this is a constant proxy and the other is too or is a constant
	 *            value (e.g. IStringBeanProxy.equals(String), then true if values are equals.
	 * Since there is only one NullProxy then only if identity or anObject is null.
	 */
	public boolean equals(Object anObject) {
		return (anObject == null || this == anObject);	// Identity or anObject is null

	}		
	public boolean isNullProxy() {
		return true;
	}
	
	public String toBeanString() {
		return "null"; //$NON-NLS-1$
	}
	
	/**
	 * Get the beantype
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).voidType;
	}
	
	/**
	 * Render the bean into value object.
	 */
	public void renderBean(Commands.ValueObject value) {
		value.set();
	}	

}