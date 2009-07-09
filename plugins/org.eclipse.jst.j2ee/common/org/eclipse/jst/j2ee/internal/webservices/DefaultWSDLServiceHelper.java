/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 21, 2005
 */
package org.eclipse.jst.j2ee.internal.webservices;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author cbridgha
 */
public class DefaultWSDLServiceHelper implements WSDLServiceHelper {

	/**
	 * 
	 */
	public DefaultWSDLServiceHelper() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getPortName(java.lang.Object)
	 */
	public String getPortName(Object port) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServicePorts(java.lang.Object)
	 */
	public Map getServicePorts(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceNamespaceURI(java.lang.Object)
	 */
	public String getServiceNamespaceURI(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceDefinitionLocation(org.eclipse.emf.ecore.EObject)
	 */
	public Object getServiceDefinitionLocation(EObject aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getPortBindingNamespaceURI(java.lang.Object)
	 */
	public String getPortBindingNamespaceURI(Object aPort) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceLocalPart(java.lang.Object)
	 */
	public String getServiceLocalPart(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceQName(java.lang.Object)
	 */
	public Object getServiceQName(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getDefinitionServices(java.lang.Object)
	 */
	public Map getDefinitionServices(Object aDefinition) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getWSDLDefinition(java.lang.String)
	 */
	public Object getWSDLDefinition(String wsdlURL) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getWSDLDefinition(org.eclipse.emf.ecore.resource.Resource)
	 */
	public Object getWSDLDefinition(Resource wsdlResource) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getWsdlServicesFromWsilFile(org.eclipse.core.resources.IFile)
	 */
	public List getWsdlServicesFromWsilFile(IFile wsil) {
		return null;
	}

	public boolean isService(Object aService) {
		return false;
	}
	public boolean isWSDLResource(Object aResource) {
		return false;
	}
	public boolean isDefinition(Object aDefinition) {
		return false;
	}

	public List get13ServiceRefs(EObject j2eeObject) {
		return null;
	}
}
