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
package org.eclipse.jem.internal.proxy.remote;
/*


 */


/**
 * Tag interface to indicate this proxy is a constant proxy, i.e. there
 * is no bean on the server representing this (e.g. Strings and Integers).
 */

public interface IREMConstantBeanProxy extends IREMBeanProxy {

}
