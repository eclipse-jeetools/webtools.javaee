/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProxyLaunchMenuDelegate.java,v $
 *  $Revision: 1.1 $  $Date: 2004/03/04 16:14:29 $ 
 */
package org.eclipse.jem.internal.ui.proxy;

import org.eclipse.debug.ui.actions.OpenLaunchDialogAction;

import org.eclipse.jem.internal.proxy.core.IProxyConstants;
 
/**
 * Menu delegate to launch the Proxy Launch Manager.
 * @since 1.0.0
 */
public class ProxyLaunchMenuDelegate extends OpenLaunchDialogAction {
	
	public ProxyLaunchMenuDelegate() {
		super(IProxyConstants.ID_PROXY_LAUNCH_GROUP);
	}

}
