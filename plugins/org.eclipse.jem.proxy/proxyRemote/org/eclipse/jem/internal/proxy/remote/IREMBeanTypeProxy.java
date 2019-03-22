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


import org.eclipse.jem.internal.proxy.core.*;
/**
 * All bean type proxies in remote vm must implement this interface.
 *
 * NOTE: This is an internal interface to the Remote VM Proxy.
 * It MUST not be used outside of the proxy factory family. 
 */
public interface IREMBeanTypeProxy extends IBeanTypeProxy, IREMBeanProxy {
	
/**
 * Create a new bean proxy with the specified id.
 */
public IREMBeanProxy newBeanProxy(Integer anID);

/**
 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
 * This is because subclasses, unless explicitly overridden, should use the same kind
 * of beantype proxy. This way if anything special is needed for a specific type, then
 * that can be done.
 */
public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract);


}
