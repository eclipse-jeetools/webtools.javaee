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
 *  $RCSfile: ICharacterBeanProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:39:05 $ 
 */


/**
 * Optimized implementation that should be used for character proxies that
 * allows the IDE VM to get the character value easily
 * Creation date: (2/6/00 8:58:22 AM)
 * @author: Joe Winchester
 */
public interface ICharacterBeanProxy extends IBeanProxy, INumberBeanProxy {
/**
 * Return the proxied character as a char that the IDE can use
 * Creation date: (2/6/00 8:58:32 AM)
 */
char charValue();
/**
 * Return the proxied character as a Character that the IDE can use
 * Creation date: (2/6/00 8:58:32 AM)
 */
Character characterValue();
}
