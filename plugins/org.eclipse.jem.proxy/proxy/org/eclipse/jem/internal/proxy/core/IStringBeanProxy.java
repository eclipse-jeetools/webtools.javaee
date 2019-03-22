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
package org.eclipse.jem.internal.proxy.core;
/*


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
