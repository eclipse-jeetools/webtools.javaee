/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IProxyMethod.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
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
