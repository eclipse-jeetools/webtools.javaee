/***************************************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;

import org.eclipse.emf.common.notify.Adapter;


public class ATKUIWscddAdapterFactory extends Webservice_clientItemProviderAdapterFactory {
	public ATKUIWscddAdapterFactory() {
		super();
	}

	@Override
	public Adapter createWebServicesClientAdapter() {
		if (webServicesClientItemProvider == null)
			webServicesClientItemProvider = new ATKUIWebServicesClientItemProvider(this);
		return webServicesClientItemProvider;
	}

	@Override
	public Adapter createComponentScopedRefsAdapter() {
		if (componentScopedRefsItemProvider == null)
			componentScopedRefsItemProvider = new ATKUIComponentScopedRefsItemProvider(this);
		return componentScopedRefsItemProvider;
	}

	@Override
	public Adapter createPortComponentRefAdapter() {
		if (portComponentRefItemProvider == null)
			portComponentRefItemProvider = new ATKUIPortComponentRefItemProvider(this);
		return portComponentRefItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservice.wsclient.util.Webservice_clientAdapterFactory#createServiceRefAdapter()
	 */
	@Override
	public Adapter createServiceRefAdapter() {
		if (serviceRefItemProvider == null)
			serviceRefItemProvider = new ATKUIServiceRefItemProvider(this);
		return serviceRefItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.webservice.wsclient.util.Webservice_clientAdapterFactory#createHandlerAdapter()
	 */
	@Override
	public Adapter createHandlerAdapter() {
		if (handlerItemProvider == null)
			handlerItemProvider = new ATKUIHandlerItemProvider(this);
		return handlerItemProvider;
	}

}