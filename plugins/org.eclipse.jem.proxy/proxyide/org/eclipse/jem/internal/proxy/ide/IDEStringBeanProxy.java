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
 *  $RCSfile: IDEStringBeanProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:39:06 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * IDE Implementation of IStringBeanProxy
 * Creation date: (2/6/00 9:02:42 AM)
 * @author: Joe Winchester
 */
final class IDEStringBeanProxy extends IDEObjectBeanProxy implements IStringBeanProxy {
	protected String fString;
/**
 * Store the bean in the string field to save re-casting each time it is asked for.
 * It is package protected because they are created
 * in a special way and no one else should create them.
 */
IDEStringBeanProxy(IDEProxyFactoryRegistry aRegistry, Object aBean, IBeanTypeProxy aBeanTypeProxy) {
	super(aRegistry, aBean,aBeanTypeProxy);
	fString = (String)aBean;
}
/**
 * Return the cache'd string that is a java.lang.String that the IDE can use
 */
public String stringValue() {
	return fString;
}
}
