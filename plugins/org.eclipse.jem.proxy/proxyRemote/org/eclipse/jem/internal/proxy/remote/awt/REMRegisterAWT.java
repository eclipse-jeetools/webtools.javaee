package org.eclipse.jem.internal.proxy.remote.awt;
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
 *  $RCSfile: REMRegisterAWT.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/11 19:01:12 $ 
 */

import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.internal.proxy.core.IMethodProxy;
import org.eclipse.jem.internal.proxy.remote.REMProxyFactoryRegistry;
/**
 * This class is used to register the AWT factories. It is not
 * meant to be called by anyone other than ProxyVMStarter.
 */
public final class REMRegisterAWT {
	public static void registerAWT(REMProxyFactoryRegistry registry) {
		new REMStandardAWTBeanTypeProxyFactory(registry);
		new REMStandardAWTBeanProxyFactory(registry);
		
		// If we are doing AWT, get the AWT event queue going. This is trying to be a time-saver
		// by having it up right away.
		IBeanTypeProxy starterBeanType = registry.getBeanTypeProxyFactory().getBeanTypeProxy("org.eclipse.jem.internal.proxy.vm.remote.AWTStarter");
		if (starterBeanType != null) {
			IMethodProxy starter = starterBeanType.getMethodProxy("startAWT");
			if (starter != null)
				starter.invokeCatchThrowableExceptions(null);
		}
	}
}