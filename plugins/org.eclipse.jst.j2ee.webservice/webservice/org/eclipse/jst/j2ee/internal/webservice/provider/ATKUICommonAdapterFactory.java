/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jst.j2ee.common.internal.provider.CommonItemProviderAdapterFactory;


public class ATKUICommonAdapterFactory extends CommonItemProviderAdapterFactory {
	public ATKUICommonAdapterFactory() {
		super();
	}

	public Adapter createParamValueAdapter() {
		if (paramValueItemProvider == null)
			paramValueItemProvider = new ATKUIParamValueItemProvider(this);

		return paramValueItemProvider;
	}

	public Adapter createQNameAdapter() {
		if (qNameItemProvider == null) {
			qNameItemProvider = new ATKUIQNameItemProvider(this);
		}

		return qNameItemProvider;
	}


	/*
	 * public Adapter createSOAPHeaderAdapter() { if (soapHeaderItemProvider == null)
	 * soapHeaderItemProvider = new ATKUISOAPHeaderItemProvider(this); return
	 * soapHeaderItemProvider; }
	 * 
	 * public Adapter createSOAPRoleAdapter() { if (soapRoleItemProvider == null)
	 * soapRoleItemProvider = new ATKUISOAPRoleItemProvider(this); return soapRoleItemProvider; }
	 * 
	 * public Adapter createPortNameAdapter() { if (portNameItemProvider == null)
	 * portNameItemProvider = new ATKUIPortNameItemProvider(this); return portNameItemProvider; }
	 *  
	 */
}