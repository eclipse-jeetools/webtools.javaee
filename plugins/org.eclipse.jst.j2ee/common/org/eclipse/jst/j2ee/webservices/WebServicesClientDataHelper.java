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
package org.eclipse.jst.j2ee.webservices;


/**
 * WebServicesClientDataHelper Interface Extension - For use in generating Web Service Clients by
 * sharing data between J2EE operations and web services operations
 */
public interface WebServicesClientDataHelper {

	/**
	 * @return the URL for the associated original WSDL file referenced by the client
	 */
	public String getWSDLUrl();

	/**
	 * @return the QName of form http://someNamespace:someLocalPart for the associated web service
	 *         for the client
	 */
	public String getServiceQName();

	/**
	 * @return the name of the target project for the web service client
	 */
	public String getProjectName();

	/**
	 * @return the URI for the fileName path for the copied WSDL file into the client project
	 */
	public String getOutputWSDLFileName();

	/**
	 * @return the qualified classname of the generated Service Interface
	 */
	public String getServiceInterfaceName();

	/**
	 * @return the qualified classname of the generated Service Endpoint Interface
	 */
	public String[] getServiceEndpointInterfaceNames();

	/**
	 * @return should generate code for deployment?
	 */
	public boolean shouldDeploy();

	/**
	 * @return whether the extension generated the descriptors or not
	 */
	public boolean shouldGenDescriptors();

	/**
	 * Set the SEI classname for the web service client
	 */
	public void setServiceInterfaceName(String name);

	/**
	 * Set the SI classname for the web service client
	 */
	public void setServiceEndpointInterfaceNames(String[] names);

	/**
	 * Set whether descriptors were generated
	 */
	public void setDidGenDescriptors(boolean b);
}