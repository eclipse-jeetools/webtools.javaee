package org.eclipse.jem.internal.proxy.remote.awt;
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
 *  $RCSfile: REMRegisterAWT.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.remote.REMProxyFactoryRegistry;
/**
 * This class is used to register the AWT factories. It is not
 * meant to be called by anyone other than ProxyVMStarter.
 */
public final class REMRegisterAWT {
	public static void registerAWT(REMProxyFactoryRegistry registry) {
		new REMStandardAWTBeanTypeProxyFactory(registry);
		new REMStandardAWTBeanProxyFactory(registry);
	}
}