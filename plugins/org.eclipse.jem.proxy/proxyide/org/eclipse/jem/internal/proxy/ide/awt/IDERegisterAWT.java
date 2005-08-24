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
package org.eclipse.jem.internal.proxy.ide.awt;
/*
 *  $RCSfile: IDERegisterAWT.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:07 $ 
 */


import org.eclipse.jem.internal.proxy.ide.IDEProxyFactoryRegistry;
/**
 * This class is used to register the AWT factories.
 */
public final class IDERegisterAWT {
	public static void registerAWT(IDEProxyFactoryRegistry registry) {
		new IDEStandardAWTBeanTypeProxyFactory(registry);
		new IDEStandardAWTBeanProxyFactory(registry);
	}
}


