package org.eclipse.jem.internal.proxy.ide;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IDEExtensionBeanTypeProxyFactory.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:57:26 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

public interface IDEExtensionBeanTypeProxyFactory extends IBeanTypeProxyFactory {

public IDEBeanTypeProxy getExtensionBeanTypeProxy(String className);

public IDEBeanTypeProxy getExtensionBeanTypeProxy(String typeName, IBeanTypeProxy superType);	


}