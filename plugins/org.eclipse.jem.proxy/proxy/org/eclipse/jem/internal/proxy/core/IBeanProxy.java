package org.eclipse.jem.internal.proxy.core;
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
 *  $RCSfile: IBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:53:46 $ 
 */


/**
 * Bean Proxy interface. This is the
 * root interface for any bean proxy instance.
 * Creation date: (12/3/99 11:37:01 AM)
 * @author: Joe Winchester
 */
public interface IBeanProxy {
/**
 * equals: Equal if:
 *         1) This proxy == (identity) to the other object
 *         2) Else if other is an IBeanProxy, then if
 *            equals on the server.
 *         3) If this is a constant proxy and the other is too or is a constant
 *            value (e.g. IStringBeanProxy.equals(String), then true if values are equals.
 */
public boolean equals(Object anObject);

/**
 * Test for identity among this bean proxy and the other bean proxy.
 * For some proxy systems, this may be redundent and you can be assured
 * that if the objects being proxied are identical you will receive the
 * same proxy for both. However, other implementations may not have this
 * condition. Therefor this method was created.
 * 
 * There is one restriction, constants may not necessarily be identical.
 * For instance, in some implementations, the Boolean proxy (i.e. an instance of class Boolean) is a constant, i.e. 
 * it cannot be changed. In those cases a new proxy may be created for each
 * access and so they will not be considered to be identical. In those cases false will be returned.
 * But in other implementations a new proxy will not be created and so sameAs will return true. 
 * 
 * Primitives will return true if just equal because in Java primitives are identical if the same value.
 * 
 * @param aBeanProxy
 * @return true if they are identical (i.e. ==) and not just equals.
 */
public boolean sameAs(IBeanProxy aBeanProxy);
  
/**
 * Return the registry this proxy was created with.
 */
public ProxyFactoryRegistry getProxyFactoryRegistry();
/**
 * Return the proxied class of the bean
 * Creation date: (12/3/99 11:37:01 AM)
 * @author: Joe Winchester
 */
public IBeanTypeProxy getTypeProxy();

/**
 * Return a string representation of the bean itself
 * This could be done by finding the toString() method by a findMethod()
 * on the type proxy, etc... but toString() is so ubiquitous that it is
 * explicitly declared on the IBeanProxy interface
 * Creation date: (12/3/99 11:37:01 AM)
 * @author: Joe Winchester
 */
public String toBeanString();
/**
 * Is this bean still valid? It could be invalid because it
 * was specifically released but someone is still holding onto it.
 */
public boolean isValid();
}
