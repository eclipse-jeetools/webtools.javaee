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
 * Created on Apr 28, 2004
 */
package org.eclipse.jst.j2ee.webservices;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.common.J2EECommonMessages;


/**
 * Extension for generating web service clients
 */
public abstract class WebServiceClientGenerator {

	public static final String GENERIC_J2EE_CONTAINER = J2EECommonMessages.getResourceString("WebServiceClientGenerator_UI_0"); //$NON-NLS-1$

	private List runtime = null;

	/**
	 * Generates Web service client artifacts based on information obatined from the dataModel
	 * (wsdlURL, serviceQName, outputWSDLFilePathName, projectName, shouldDeploy) After generating
	 * the Web service client artifacts, sets the following on the dataModel: serviceInterfaceName,
	 * serviceEndpointInterfaceName, didGenDescriptors
	 * 
	 * @return Status for results
	 */
	public abstract IStatus genWebServiceClientArtifacts(WebServicesClientDataHelper dataModel);

	public void setRuntime(List serverTargets) {
		runtime = serverTargets;
	}

	public List getServerTargets() {
		if (runtime == null)
			runtime = new ArrayList();
		return runtime;
	}

	public boolean isRuntimeSupported(String serverTargetID) {
		return getServerTargets().contains(serverTargetID);
	}

}