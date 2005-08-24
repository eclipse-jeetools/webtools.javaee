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
 *  $RCSfile: IBeanTypeProxyFactory.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:05 $ 
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
