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
 *  $RCSfile: IBooleanBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:53:46 $ 
 */


/**
 * Optimized implementation that should be used for Boolean proxies that
 * allows the IDE VM to get the boolean value easily
 * Creation date: (2/6/00 8:58:22 AM)
 * @author: Joe Winchester
 */
public interface IBooleanBeanProxy extends IBeanProxy {
/**
 * Return the proxied boolean as a boolean that the IDE can use
 * Creation date: (2/6/00 8:58:32 AM)
 */
boolean booleanValue();
Boolean getBooleanValue();
}
