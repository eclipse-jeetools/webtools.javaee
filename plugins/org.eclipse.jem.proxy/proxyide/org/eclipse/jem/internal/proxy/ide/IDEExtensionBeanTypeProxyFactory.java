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
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDEExtensionBeanTypeProxyFactory.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:39:06 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

public interface IDEExtensionBeanTypeProxyFactory extends IBeanTypeProxyFactory {

public IDEBeanTypeProxy getExtensionBeanTypeProxy(String className);

public IDEBeanTypeProxy getExtensionBeanTypeProxy(String typeName, IBeanTypeProxy superType);	

public IProxyBeanType getExtensionBeanTypeProxy(String typeName, IExpression expression);


}
