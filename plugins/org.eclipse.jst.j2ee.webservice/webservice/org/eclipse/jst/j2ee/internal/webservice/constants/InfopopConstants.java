/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.constants;

import org.eclipse.jst.j2ee.webservice.plugin.WebServicePlugin;

public class InfopopConstants {

	/** *************************Infopops for pages************************ */
	public String getInfopopWebServicesPage() {
		return WebServicePlugin.PLUGIN_ID + ".EJSRP0001"; //$NON-NLS-1$
	}

	public String getInfopopPortComponentPage() {
		return WebServicePlugin.PLUGIN_ID + ".EJSRP0002"; //$NON-NLS-1$
	}

	public String getInfopopWSHandlerPage() {
		return WebServicePlugin.PLUGIN_ID + ".EJSRP0003"; //$NON-NLS-1$
	}

	public String getInfopopServiceRefPage() {
		return WebServicePlugin.PLUGIN_ID + ".EJSRP0004"; //$NON-NLS-1$
	}

	public String getInfopopSRHandlerPage() {
		return WebServicePlugin.PLUGIN_ID + ".EJSRP0005"; //$NON-NLS-1$
	}



	/** *************************Infopops for sections*********************** */
	public String getInfopopDescription() {
		/* CONTEXT_ID EJSR0001 for the description text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0001"; //$NON-NLS-1$
	}

	public String getInfopopDisplayName() {
		/* CONTEXT_ID EJSR0002 for the display name text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0002"; //$NON-NLS-1$
	}

	public String getInfopopSmallIcon() {
		/* CONTEXT_ID EJSR0003 for the small icon display */
		return WebServicePlugin.PLUGIN_ID + "EJSR0003"; //$NON-NLS-1$
	}

	public String getInfopopLargeIcon() {
		/* CONTEXT_ID EJSR0004 for the large icon display */
		return WebServicePlugin.PLUGIN_ID + "EJSR0004"; //$NON-NLS-1$
	}

	public String getInfopopWebServiceDescriptionName() {
		/* CONTEXT_ID EJSR0005 for the Web service description name text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0005"; //$NON-NLS-1$
	}

	public String getInfopopWSDLFile() {
		/* CONTEXT_ID EJSR0006 for the WSDL file location text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0006"; //$NON-NLS-1$
	}

	public String getInfopopJAXRPCMappingFile() {
		/* CONTEXT_ID EJSR0007 for the JAX-RPC mapping file location text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0007"; //$NON-NLS-1$
	}

	public String getInfopopPortComponentName() {
		/* CONTEXT_ID EJSR0008 for the port component name text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0008"; //$NON-NLS-1$
	}

	public String getInfopopWSDLPortNamespaceURI() {
		/* CONTEXT_ID EJSR0009 for the WSDL port namespace URI text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0009"; //$NON-NLS-1$
	}

	public String getInfopopWSDLPortLocalpart() {
		/* CONTEXT_ID EJSR0010 for the WSDL port local part text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0010"; //$NON-NLS-1$
	}

	public String getInfopopServiceEndpointInterface() {
		/*
		 * CONTEXT_ID EJSR0011 for the fully qualified service endpoint interface class name text
		 * field
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0011"; //$NON-NLS-1$
	}

	public String getInfopopServiceImplBean() {
		/*
		 * CONTEXT_ID EJSR0012 for the fully qualified service implementation bean class name text
		 * field
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0012"; //$NON-NLS-1$
	}

	public String getInfopopEJBLink() {
		/* CONTEXT_ID EJSR0013 for the use EJB link radio button */
		return WebServicePlugin.PLUGIN_ID + "EJSR0013"; //$NON-NLS-1$
	}

	public String getInfopopServletLink() {
		/* CONTEXT_ID EJSR0014 for the use servlet link radio button */
		return WebServicePlugin.PLUGIN_ID + "EJSR0014"; //$NON-NLS-1$
	}

	public String getInfopopHandlerName() {
		/* CONTEXT_ID EJSR0015 for the handler name text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0015"; //$NON-NLS-1$
	}

	public String getInfopopHandlerClass() {
		/* CONTEXT_ID EJSR0016 for the fully qualified handler class name text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0016"; //$NON-NLS-1$
	}

	public String getInfopopServiceRefName() {
		/* CONTEXT_ID EJSR0017 for the service reference name text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0017"; //$NON-NLS-1$
	}

	public String getInfopopServiceInterfaceName() {
		/* CONTEXT_ID EJSR0018 for the service interface name text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0018"; //$NON-NLS-1$
	}

	public String getInfopopServiceQNameNamespaceURI() {
		/* CONTEXT_ID EJSR0019 for the service qname namspace URI text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0019"; //$NON-NLS-1$
	}

	public String getInfopopServiceQNameLocalpart() {
		/* CONTEXT_ID EJSR0020 for the service qname local part text field */
		return WebServicePlugin.PLUGIN_ID + "EJSR0020"; //$NON-NLS-1$
	}

	public String getInfopopComboWebServiceDescription() {
		/*
		 * CONTEXT_ID EJSR0021 for the Web service description drop-down (list of Web service
		 * descriptions)
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0021"; //$NON-NLS-1$
	}

	public String getInfopopComboPortComponent() {
		/* CONTEXT_ID EJSR0022 for the port component drop-down (list of port components) */
		return WebServicePlugin.PLUGIN_ID + "EJSR0022"; //$NON-NLS-1$
	}

	public String getInfopopComboComponentScopedRef() {
		/*
		 * CONTEXT_ID EJSR0023 for the component scoped reference drop-down (list of component
		 * scoped reference)
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0023"; //$NON-NLS-1$
	}

	public String getInfopopComboServiceRef() {
		/* CONTEXT_ID EJSR0024 for the service reference drop-down (list of service references) */
		return WebServicePlugin.PLUGIN_ID + "EJSR0024"; //$NON-NLS-1$
	}

	public String getInfopopSectionWebServiceDescription() {
		/* CONTEXT_ID EJSR0025 for the Web service description section in the webservices.xml editor */
		return WebServicePlugin.PLUGIN_ID + "EJSR0025"; //$NON-NLS-1$
	}

	public String getInfopopSectionPortComponent() {
		/* CONTEXT_ID EJSR0026 for the port component section in the webservices.xml editor */
		return WebServicePlugin.PLUGIN_ID + "EJSR0026"; //$NON-NLS-1$
	}

	public String getInfopopSectionHandlerWS() {
		/* CONTEXT_ID EJSR0027 for the handler section in the webservices.xml editor */
		return WebServicePlugin.PLUGIN_ID + "EJSR0027"; //$NON-NLS-1$
	}

	public String getInfopopSectionComponentScopedRef() {
		/*
		 * CONTEXT_ID EJSR0028 for the component scoped reference section in the
		 * webservicesclient.xml editor
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0028"; //$NON-NLS-1$
	}

	public String getInfopopSectionServiceRef() {
		/* CONTEXT_ID EJSR0029 for the service reference section in the webservicesclient.xml editor */
		return WebServicePlugin.PLUGIN_ID + "EJSR0029"; //$NON-NLS-1$
	}

	public String getInfopopSectionPortComponentRef() {
		/*
		 * CONTEXT_ID EJSR0030 for the port component reference section in the webservicesclient.xml
		 * editor
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0030"; //$NON-NLS-1$
	}

	public String getInfopopSectionHandlerWSC() {
		/* CONTEXT_ID EJSR0031 for the handler section in the webservicesclient.xml editor */
		return WebServicePlugin.PLUGIN_ID + "EJSR0031"; //$NON-NLS-1$
	}

	public String getInfopopSectionInitParams() {
		/*
		 * CONTEXT_ID EJSR0032 for the initial parameter section in both webservices.xml and
		 * webservicesclient.xml editor
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0032"; //$NON-NLS-1$
	}

	public String getInfopopSectionSOAPHeaders() {
		/*
		 * CONTEXT_ID EJSR0033 for the SOAP header section in both webservices.xml and
		 * webservicesclient.xml editor
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0033"; //$NON-NLS-1$
	}

	public String getInfopopSectionSOAPRoles() {
		/*
		 * CONTEXT_ID EJSR0034 for the SOAP role section in both webservices.xml and
		 * webservicesclient.xml editor
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0034"; //$NON-NLS-1$
	}

	public String getInfopopSectionPortNames() {
		/* CONTEXT_ID EJSR0035 for the port name section in the webservicesclient.xml editor */
		return WebServicePlugin.PLUGIN_ID + "EJSR0035"; //$NON-NLS-1$
	}

	public String getInfopopResourceTree() {
		/*
		 * CONTEXT_ID EJSR0036 for the popup dialog that allows the user to select a resource in the
		 * workbench
		 */
		return WebServicePlugin.PLUGIN_ID + "EJSR0036"; //$NON-NLS-1$
	}
}