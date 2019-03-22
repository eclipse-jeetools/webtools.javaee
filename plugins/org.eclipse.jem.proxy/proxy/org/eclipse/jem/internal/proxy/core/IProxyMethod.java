/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.core;
 

/**
 * This interface is for IMethodProxy's and MethodExpressionProxy's so that we can lazily
 * get the method proxy and have the expression process it when needed.
 * 
 * @since 1.1.0
 */
public interface IProxyMethod extends IProxy {

}
