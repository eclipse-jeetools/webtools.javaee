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
 *  $RCSfile: IStringBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
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
