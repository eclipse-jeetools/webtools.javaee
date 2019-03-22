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
package org.eclipse.jem.internal.proxy.ide.awt;
/*


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


