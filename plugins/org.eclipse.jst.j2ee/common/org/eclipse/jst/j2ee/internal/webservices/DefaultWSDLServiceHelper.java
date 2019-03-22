/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	@Override
	public String getPortName(Object port) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServicePorts(java.lang.Object)
	 */
	@Override
	public Map getServicePorts(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceNamespaceURI(java.lang.Object)
	 */
	@Override
	public String getServiceNamespaceURI(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceDefinitionLocation(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public Object getServiceDefinitionLocation(EObject aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getPortBindingNamespaceURI(java.lang.Object)
	 */
	@Override
	public String getPortBindingNamespaceURI(Object aPort) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceLocalPart(java.lang.Object)
	 */
	@Override
	public String getServiceLocalPart(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getServiceQName(java.lang.Object)
	 */
	@Override
	public Object getServiceQName(Object aService) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getDefinitionServices(java.lang.Object)
	 */
	@Override
	public Map getDefinitionServices(Object aDefinition) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getWSDLDefinition(java.lang.String)
	 */
	@Override
	public Object getWSDLDefinition(String wsdlURL) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getWSDLDefinition(org.eclipse.emf.ecore.resource.Resource)
	 */
	@Override
	public Object getWSDLDefinition(Resource wsdlResource) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper#getWsdlServicesFromWsilFile(org.eclipse.core.resources.IFile)
	 */
	@Override
	public List getWsdlServicesFromWsilFile(IFile wsil) {
		return null;
	}

	@Override
	public boolean isService(Object aService) {
		return false;
	}
	@Override
	public boolean isWSDLResource(Object aResource) {
		return false;
	}
	@Override
	public boolean isDefinition(Object aDefinition) {
		return false;
	}

	@Override
	public List get13ServiceRefs(EObject j2eeObject) {
		return null;
	}
}
