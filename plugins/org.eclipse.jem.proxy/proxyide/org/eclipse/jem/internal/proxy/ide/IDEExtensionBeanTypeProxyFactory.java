package org.eclipse.jem.internal.proxy.ide;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IDEExtensionBeanTypeProxyFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

public interface IDEExtensionBeanTypeProxyFactory extends IBeanTypeProxyFactory {

public IDEBeanTypeProxy getExtensionBeanTypeProxy(String className);

public IDEBeanTypeProxy getExtensionBeanTypeProxy(String typeName, IBeanTypeProxy superType);	


}