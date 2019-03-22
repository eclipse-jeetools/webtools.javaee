/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.awt;
/*


 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IBeanProxyFactory;
/**
 * The Standard awt bean proxy factory.
 * This is the Interface that the desktop will talk
 * to.
 * Creation date: (12/3/99 11:52:09 AM)
 * @author: Joe Winchester
 */
public interface IStandardAwtBeanProxyFactory extends IBeanProxyFactory {
	public static final String REGISTRY_KEY = "standard-java.awt"; //$NON-NLS-1$

/**
 * Return a new bean proxy for the dimension argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IDimensionBeanProxy createDimensionBeanProxyWith(int width, int height);
/**
 * Return a new bean proxy for the point argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IPointBeanProxy createPointBeanProxyWith(int x, int y);
/**
 * Return a new bean proxy for the rectangle argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IRectangleBeanProxy createBeanProxyWith(int x, int y, int width, int height);
}
