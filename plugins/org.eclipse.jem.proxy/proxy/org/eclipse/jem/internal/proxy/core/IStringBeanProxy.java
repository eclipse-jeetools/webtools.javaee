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
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: IStringBeanProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:05 $ 
 */


/**
 * Optimized implementation that should be used for string proxies that
 * allows the IDE VM to get the string value easily
 * Creation date: (2/6/00 8:58:22 AM)
 * @author: Joe Winchester
 */
public interface IStringBeanProxy extends IBeanProxy {
/**
 * Return the proxied string as a string that the IDE can use
 * Creation date: (2/6/00 8:58:32 AM)
 */
String stringValue();
}
