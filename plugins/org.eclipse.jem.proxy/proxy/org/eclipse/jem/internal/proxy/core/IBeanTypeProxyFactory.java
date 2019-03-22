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
 * Factory for creating BeanTypeProxy's.
 * This is a common tag interface so that
 * the factory can be registered. But each
 * VM requires a different interface that
 * extends this interface. That extended
 * interface is what must be implemented in
 * each VM.
 * Creation date: (12/3/99 2:26:00 PM)
 * @author: Joe Winchester
 */
public interface IBeanTypeProxyFactory extends IBeanProxyFactory {

}
