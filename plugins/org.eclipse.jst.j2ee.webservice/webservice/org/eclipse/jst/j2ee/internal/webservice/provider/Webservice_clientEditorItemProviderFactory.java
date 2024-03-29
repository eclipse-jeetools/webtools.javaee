/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 26, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.provider;

import org.eclipse.emf.common.notify.Adapter;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class Webservice_clientEditorItemProviderFactory extends Webservice_clientItemProviderAdapterFactory {
	/**
	 *  
	 */
	public Webservice_clientEditorItemProviderFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservice.wsclient.util.Webservice_clientAdapterFactory#createServiceRefAdapter()
	 */
	@Override
	public Adapter createServiceRefAdapter() {
		if (serviceRefItemProvider == null) {
			serviceRefItemProvider = new ServiceRefEditorItemProvider(this);
		}

		return serviceRefItemProvider;
	}
}