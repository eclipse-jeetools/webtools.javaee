package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IBeanProxyFactory.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:53:46 $ 
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
	 * The factory is being terminated.
	 * <p>
	 * It should clean up its resources.
	 * It should not reference any other factory because they could of
	 * already been terminated.
	 * <p>
	 * For example, if it is holding onto IREMBeanProxy's, it doesn't
	 * need to call release on them except if they are constants because
	 * the BeanProxyFactory has all non-constant bean proxies registered
	 * and will call the release itself.
	 * <p>
	 * Note: This is not meant to be called by users. It will be called internally by the proxy registry when necessary.
	 * 
	 * @param wait <code>true</code> should wait for completion, <code>false</code> don't necessarily wait. False is just
	 * a suggestion. The factory may not permit not waiting. True must be honoured and it must wait.
	 * 
	 * @since 1.0.2
	 */
	public void terminateFactory(boolean wait); 
}
