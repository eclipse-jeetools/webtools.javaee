package org.eclipse.jem.internal.proxy.core;
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
 *  $RCSfile: IBeanProxyFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * Bean Proxy Factory for creating bean proxies.
 * Creation methods are actually package protected.
 * All creation is done through the IBeanTypeProxy.
 * Specific factories may have helper methods that
 * allow creation of specific types, such as the
 * standard factory allows creation of Boolean, int's, etc.
 * Creation date: (12/3/99 11:52:09 AM)
 * @author: Joe Winchester
 */
public interface IBeanProxyFactory {
	/**
	 * The factory is being terminated. It should clean up its resources.
	 * It should not reference any other factory because they could of
	 * already been terminated.
	 *
	 * For example, if it is holding onto IREMBeanProxy's, it doesn't
	 * need to call release on them except if they are constants because
	 * the BeanProxyFactory has all non-constant bean proxies registered
	 * and will call the release itself.
	 */
	public void terminateFactory(); 
}
