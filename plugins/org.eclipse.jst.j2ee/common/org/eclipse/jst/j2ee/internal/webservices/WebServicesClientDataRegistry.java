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
 * Created on Apr 26, 2004
 */
package org.eclipse.jst.j2ee.internal.webservices;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

/**
 * Read the WebServicesClientDataExtensions
 */
public class WebServicesClientDataRegistry extends RegistryReader {

	private static WebServicesClientDataRegistry INSTANCE = null;
	public static final String WS_CLIENT_EXTENSION_POINT = "WebServiceClientGenerator"; //$NON-NLS-1$
	public static final String GENERATOR = "generator"; //$NON-NLS-1$
	public static final String CLASS_NAME = "className"; //$NON-NLS-1$
	public static final String SERVER_TARGET = "serverTarget"; //$NON-NLS-1$
	public static final String RUNTIME = "runtime"; //$NON-NLS-1$
	private List wsClientDataExtensions = null;

	/**
	 * Default constructor
	 */
	public WebServicesClientDataRegistry() {
		super(J2EEPlugin.PLUGIN_ID, WS_CLIENT_EXTENSION_POINT);
	}

	public static WebServicesClientDataRegistry getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new WebServicesClientDataRegistry();
			INSTANCE.readRegistry();
		}
		return INSTANCE;
	}

	/**
	 * @see org.eclipse.wst.common.frameworks.internal.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	public boolean readElement(IConfigurationElement element) {
		if (!element.getName().equals(GENERATOR))
			return false;
		// Get the class specified in the extension point
		WebServiceClientGenerator helper = null;
		try {
			helper = (WebServiceClientGenerator) element.createExecutableExtension(CLASS_NAME);
			// get server target runtimes
			IConfigurationElement[] runtimes = element.getChildren(RUNTIME);
			List runtimeList = new ArrayList();
			for (int i = 0; i < runtimes.length; i++) {
				IConfigurationElement runtime = runtimes[i];
				String serverTarget = runtime.getAttribute(SERVER_TARGET);
				runtimeList.add(serverTarget);
			}
			helper.setRuntime(runtimeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Add data helper class to registry extensions list
		if (helper != null) {
			getWSClientGeneratorExtensions().add(helper);
			return true;
		}
		return false;
	}

	public List getWSClientGeneratorExtensions() {
		if (wsClientDataExtensions == null)
			wsClientDataExtensions = new ArrayList();
		return wsClientDataExtensions;
	}
}