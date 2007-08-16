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
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.webservices;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author cbridgha
 *
 * This interface is intended to expand the visibility of wsdl api, without a direct dependency
 */
public interface WSDLServiceHelper {
	
	public static final String WSIL_EXT = "wsil"; //$NON-NLS-1$
	
	public String getPortName(Object port);
	public Map getServicePorts(Object aService);
	public String getServiceNamespaceURI(Object aService);
	public Object getServiceDefinitionLocation(EObject aService);
	public String getPortBindingNamespaceURI(Object aPort);
	public String getServiceLocalPart(Object aService);
	public Object getServiceQName(Object aService);
	public Map getDefinitionServices(Object aDefinition);
	public Object getWSDLDefinition(String wsdlURL);
	public Object getWSDLDefinition(Resource wsdlResource);
	public List getWsdlServicesFromWsilFile(IFile wsil);
	public boolean isService(Object aService);
	public boolean isWSDLResource(Object aResource);
	public boolean isDefinition(Object aDefinition);
	public List get13ServiceRefs(EObject j2eeObject);

}
