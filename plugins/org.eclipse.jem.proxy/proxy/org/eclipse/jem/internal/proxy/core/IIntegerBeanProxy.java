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
 * Interface to an integer bean proxy.
 * We originally had only an Integer proxy,
 * so the use of it was throughout the system.
 * We are now supporting Number instead, but
 * because there were so many places using int,
 * we've left it in.
 * Creation date: (2/6/00 8:52:42 AM)
 * @author: Joe Winchester
 */
public interface IIntegerBeanProxy extends INumberBeanProxy {
}
