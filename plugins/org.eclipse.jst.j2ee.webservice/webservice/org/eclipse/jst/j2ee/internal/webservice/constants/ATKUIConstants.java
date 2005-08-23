/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.constants;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.internal.webservice.plugin.WebServicePlugin;
import org.eclipse.jst.j2ee.webservice.internal.util.QNameHelper;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLPort;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;


public class ATKUIConstants {
	// init-param
	public String defaultInitParamName() {
		return new String(WebServicePlugin.getMessage("%LABEL_TABLE_NAME")); //$NON-NLS-1$
	}

	public String defaultInitParamValue() {
		return new String(WebServicePlugin.getMessage("%LABEL_TABLE_VALUE")); //$NON-NLS-1$
	}

	public String defaultDescription() {
		return new String(WebServicePlugin.getMessage("%LABEL_TABLE_DESCRIPTION")); //$NON-NLS-1$
	}

	// QName
	private String DEFAULT_PREFIX = "pfx"; //$NON-NLS-1$
	private String DEFAULT_NAMESPACEURI = "NamespaceURI"; //$NON-NLS-1$
	private String DEFAULT_LOCALPART = "LocalPart"; //$NON-NLS-1$

	public String defaultPrefix() {
		return new String(DEFAULT_PREFIX);
	}

	public String defaultNamespaceURI() {
		return new String(DEFAULT_NAMESPACEURI);
	}

	public String defaultLocalPart() {
		return new String(DEFAULT_LOCALPART);
	}

	// soap role
	public String defaultSOAPRole() {
		return new String(WebServicePlugin.getMessage("%LABEL_TABLE_SOAP_ROLE")); //$NON-NLS-1$
	}

	// port name
	public String defaultPortName() {
		return new String(WebServicePlugin.getMessage("%LABEL_TABLE_PORT_NAME")); //$NON-NLS-1$
	}

	//port component
	public String defaultServiceEndpointInterface() {
		return new String("java.rmi.Remote"); //$NON-NLS-1$
	}


	// port component ref
	public JavaClass defaultClientServiceEndpointInterface() {
		return JavaRefFactory.eINSTANCE.createClassRef("java.rmi.Remote"); //$NON-NLS-1$
	}

	public String defaultPortComponentLink() {
		return new String(WebServicePlugin.getMessage("%LABEL_TABLE_PORT_COMPONENT_LINK")); //$NON-NLS-1$
	}

	// service-ref
	public ServiceRef defaultServiceRef() {
		ServiceRef serviceRef = Webservice_clientFactory.eINSTANCE.createServiceRef();
		serviceRef.setServiceRefName(defaultServiceRefName());
		serviceRef.setServiceInterface(defaultServiceInterface());
		return serviceRef;
	}

	public String defaultServiceRefName() {
		StringBuffer sb = new StringBuffer("service/"); //$NON-NLS-1$
		sb.append(WebServicePlugin.getMessage("%_UI_ServiceRef_type")); //$NON-NLS-1$
		return sb.toString();
	}

	public JavaClass defaultServiceInterface() {
		return JavaRefFactory.eINSTANCE.createClassRef("javax.xml.rpc.Service"); //$NON-NLS-1$
	}

	// port component name
	public String defaultPortComponentName() {
		return new String(WebServicePlugin.getMessage("%_UI_PortComponent_type")); //$NON-NLS-1$
	}

	// service impl bean
	public ServiceImplBean defaultServiceImplBean() {
		ServiceImplBean serviceImplBean = WsddFactory.eINSTANCE.createServiceImplBean();
		serviceImplBean.setEServletLink(defaultServletLink());
		return serviceImplBean;
	}

	// servlet link
	public ServletLink defaultServletLink() {
		ServletLink servletLink = WsddFactory.eINSTANCE.createServletLink();
		servletLink.setServletLink(WebServicePlugin.getMessage("%LABEL_SERVLET_LINK")); //$NON-NLS-1$
		return servletLink;
	}

	// WSDL port
	public WSDLPort defaultWSDLPort(int versionID) {
		WSDLPort wsdlport = WsddFactory.eINSTANCE.createWSDLPort();
		QNameHelper.setQNameValue(wsdlport, defaultNamespaceURI(), defaultLocalPart(), defaultPrefix(), versionID);
		return wsdlport;
	}

	// port component
	public PortComponent defaultPortComponent(int versionID) {
		PortComponent portComponent = WsddFactory.eINSTANCE.createPortComponent();
		portComponent.setPortComponentName(defaultPortComponentName());
		portComponent.setWsdlPort(defaultWSDLPort(versionID));
		portComponent.setServiceEndpointInterface(defaultServiceEndpointInterface());
		portComponent.setServiceImplBean(defaultServiceImplBean());
		return portComponent;
	}

	// JAX RPC mapping
	public String defaultJaxRpcMappingFile() {
		return new String("jaxrpcmapping.xml"); //$NON-NLS-1$
	}

	// component scoped reference
	public String defaultComponentName() {
		return new String(WebServicePlugin.getMessage("%_UI_PortComponentRef_type")); //$NON-NLS-1$
	}
}