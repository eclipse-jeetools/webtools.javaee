/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 29, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.war.ui.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jst.j2ee.internal.web.providers.WebapplicationItemProviderAdapterFactory;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEWebItemProviderAdapterFactory extends WebapplicationItemProviderAdapterFactory {

	/**
	 * Default constructor
	 */
	public J2EEWebItemProviderAdapterFactory() {
		super();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webapplication.util.WebapplicationAdapterFactory#createWebAppAdapter()
	 */
	public Adapter createWebAppAdapter() {
		if (webAppItemProvider == null)
			webAppItemProvider = new J2EEWebAppItemProvider(this);
		return webAppItemProvider;
	}
}