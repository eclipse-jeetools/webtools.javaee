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

package org.eclipse.jst.j2ee.internal.webservice.helper;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceExtManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper;
import org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * This class contains methods to help navigate WSDL files provided in various JSR-109 descriptors
 */
public class WSDLHelper {

	private ComponentHandle handle;

	public WSDLHelper(ComponentHandle aHandle) {
		this.handle = aHandle;
	}

	/**
	 * This method returns the list of wsdl:port@name's contained in the WSDL file referred to by
	 * the specified <service-ref>
	 * 
	 * @param serviceRefName
	 *            The name of the <service-ref>
	 * @param compScopedRefName
	 *            The name of the <component-scoped-refs>
	 * @param wsc
	 *            The root object of the webservicesclient.xml descriptor
	 * @param exclude
	 *            port names to exclude in the returned array
	 * @return String[] An array of wsdl:port@names's. null is returned if there are any problems
	 *         with navigating the webservicesclient.xml or the WSDL.
	 */
	public String[] getPortNames(String serviceRefName, String compScopedRefName, WebServicesClient wsc, String[] exclude) {
		ServiceRef sr = getServiceRef(serviceRefName, compScopedRefName, wsc);
		if (sr == null)
			return null;

		String wsdlURL = getWSDLFileURL(sr);
		if (wsdlURL.length() == 0)
			return null;

		String serviceLocalName = getServiceQname(sr);
		String[] portNames = getPortNames(wsdlURL, serviceLocalName);
		portNames = trim(portNames, exclude);
		return portNames;

	}

	/**
	 * This method returns the namespace of the wsdl:service in WSDL file referred to by the
	 * specified <service-ref>
	 * 
	 * @param serviceRefName
	 *            The name of the <service-ref>
	 * @param compScopedRefName
	 *            The name of the <component-scoped-refs>
	 * @param wsc
	 *            The root object of the webservicesclient.xml descriptor
	 * @param exclude
	 *            port names to exclude in the returned array
	 * @return String The namespace of the wsdl:service. null is returned if there are any problems
	 *         with navigating the webservicesclient.xml or the WSDL.
	 */
	public String getPortNamespace(String serviceRefName, String compScopedRefName, WebServicesClient wsc) {
		ServiceRef sr = getServiceRef(serviceRefName, compScopedRefName, wsc);
		if (sr == null)
			return null;

		String wsdlURL = getWSDLFileURL(sr);
		if (wsdlURL.length() == 0)
			return ""; //$NON-NLS-1$

		String serviceLocalName = getServiceQname(sr);
		String namespace = getPortNamespace(wsdlURL, serviceLocalName);
		return namespace;
	}

	/**
	 * This method returns the list of wsdl:port@name's contained in the specified wsdl:service in
	 * the specified WSDL file.
	 * 
	 * @param wsdlURL
	 *            The URL of the WSDL file.
	 * @param serviceLocalName
	 *            The local name of the wsdl:service to look in.
	 * @return String[] An array of wsdl:port@name's. Null is returned a wsdl:service with the
	 *         specified local name could not be found.
	 */
	private String[] getPortNames(String wsdlURL, String serviceLocalName) {
		WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
		Object targetService = getTargetService(wsdlURL, serviceLocalName);

		if (targetService == null) {
			//System.out.println("getPortNames ... targetService null.");
			return null;
		}

		Map ports = serviceHelper.getServicePorts(targetService);
		int numberOfPorts = ports.size();
		String[] portNames = new String[numberOfPorts];
		Iterator k = ports.values().iterator();
		for (int m = 0; m < numberOfPorts; m++) {
			Object port = k.next();
			portNames[m] = serviceHelper.getPortName(port);
		}

		return portNames;
	}

	/**
	 * This method returns the namespace of the specified wsdl:service in the specified WSDL file.
	 * 
	 * @param wsdlURL
	 *            The URL of the WSDL file.
	 * @param serviceLocalName
	 *            The local name of the wsdl:service to look in.
	 * @return String[] An array of wsdl:port@name's. An empty String is returned if a wsdl:service
	 *         with the specified local name could not be found.
	 */
	private String getPortNamespace(String wsdlURL, String serviceLocalName) {
		Object targetService = getTargetService(wsdlURL, serviceLocalName);

		if (targetService == null) {
			//System.out.println("getPortNamespace ... targetService null.");
			return ""; //$NON-NLS-1$
		}

		return WSDLServiceExtManager.getServiceHelper().getServiceNamespaceURI(targetService);
	}

	private Object getTargetService(String wsdlURL, String serviceLocalName) {
		WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
		Object definition = getWSDLDefinition(wsdlURL);
		if (definition == null) {
			return null;
		}

		Object targetService = null;
		Map services = serviceHelper.getDefinitionServices(definition);
		if (!services.isEmpty()) {
			if ((serviceLocalName == null || serviceLocalName.length() == 0) && services.size() > 1) //Multiple
			// services
			// without
			// one
			// designated
			// is
			// an
			// error
			{
				return null;
			}

			if ((serviceLocalName == null || serviceLocalName.length() == 0) && services.size() == 1) {
				//Return the port names in the one service
				Iterator i = services.values().iterator();
				targetService = i.next();
			} else //There are multiple services but one is designated
			{
				Iterator j = services.values().iterator();
				while (j.hasNext()) {
					Object service = j.next();
					String serviceName = serviceHelper.getServiceLocalPart(service);
					if (serviceName.equals(serviceLocalName)) {
						targetService = service;
						break; //Found a match. Break out of loop. We must be assuming unique names
						// on services.
					}
				}
			}
		}

		return targetService;

	}


	private Object getWSDLDefinition(String wsdlURL) {
		WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
		return serviceHelper.getWSDLDefinition(wsdlURL);
		
		/*try {
			WSDLResourceImpl res = (WSDLResourceImpl) WorkbenchResourceHelperBase.getResource(URI.createURI(wsdlURL), true);
			if (res == null)
				return null;
			return res.getDefinition();
		} catch (Exception wsdle) {
			return null;
		}*/
	}

	/**
	 * Returns the file URL of the <wsdl-file>specified in the given <service-ref>. Returns a blank
	 * String if <wsdl-file>is not specified in the provided <service-ref>
	 */
	private String getWSDLFileURL(ServiceRef sr) {
		String wsdlFileRelPath = sr.getWsdlFile();
		if (wsdlFileRelPath == null || wsdlFileRelPath.length() == 0)
			return ""; //$NON-NLS-1$

		// Get a handle on the WSDL file.
		IVirtualComponent component = ComponentCore.createComponent(handle.getProject(),handle.getName());
		if (component != null) {
			IVirtualFolder moduleRoot = component.getFolder("/"); //$NON-NLS-1$
			IFile wsdlFile = moduleRoot.getFile(wsdlFileRelPath).getUnderlyingFile();
			if (!wsdlFile.exists()) {
				return ""; //$NON-NLS-1$
			}
			//Get the fileURL from wsdlFile
			String wsdlURL;
			try {
				wsdlURL = wsdlFile.getLocation().toFile().toURL().toString();
			} catch (MalformedURLException murle) {
				return ""; //$NON-NLS-1$
			}

			if (wsdlURL == null || wsdlURL.length() == 0)
				return ""; //$NON-NLS-1$
			return wsdlURL;
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Returns the local part of the <service-qname>specified in the given <service-ref>. Returns a
	 * blank String if <service-qname>is not specified in the provided <service-ref>
	 */
	private String getServiceQname(ServiceRef sr) {
		QName serviceQname = sr.getServiceQname();
		if (serviceQname == null)
			return ""; //$NON-NLS-1$

		String serviceLocalName = serviceQname.getLocalPart();
		if (serviceLocalName == null)
			return ""; //$NON-NLS-1$

		return serviceLocalName;
	}

	/* If compScopedRefName is empty or null, we may have to check all of them. */
	public ServiceRef getServiceRef(String name, String compScopedRefName, WebServicesClient wsc) {
		Iterator serviceRefs;
		if (compScopedRefName != null && compScopedRefName.length() > 0) {
			ComponentScopedRefs csr = getCompScopedRef(compScopedRefName, wsc);
			serviceRefs = csr.getServiceRefs().iterator();
		} else {
			serviceRefs = wsc.getServiceRefs().iterator();
			//If there are no service refs ... check all component-scoped-refs for the service-ref
			if (!serviceRefs.hasNext()) {
				Iterator csRefs = wsc.getComponentScopedRefs().iterator();

				while (csRefs.hasNext()) {
					ComponentScopedRefs csr = (ComponentScopedRefs) csRefs.next();
					Iterator srs = csr.getServiceRefs().iterator();
					while (srs.hasNext()) {
						ServiceRef sr = (ServiceRef) srs.next();
						String thisName = sr.getServiceRefName();
						if (thisName.equals(name)) {
							return sr;
						}
					}
				}

				return null; //Could not find it in any of the component-scoped-refs
			}

		}

		while (serviceRefs.hasNext()) {
			ServiceRef serviceRef = (ServiceRef) serviceRefs.next();
			String serviceRefName = serviceRef.getServiceRefName();
			if (serviceRefName.equals(name))
				return serviceRef;
		}
		return null;
	}


	private ComponentScopedRefs getCompScopedRef(String name, WebServicesClient wsc) {
		Iterator compScopedRefs = wsc.getComponentScopedRefs().iterator();
		while (compScopedRefs.hasNext()) {
			ComponentScopedRefs compScopedRef = (ComponentScopedRefs) compScopedRefs.next();
			String compScopedRefName = compScopedRef.getComponentName();
			if (compScopedRefName.equals(name))
				return compScopedRef;
		}
		return null;

	}


	private String[] trim(String[] values, String[] exclude) {
		if (values == null || values.length == 0 || exclude == null || exclude.length == 0) //no
			// change
			// required.
			// return
			// as
			// is.
			return values;

		String[] finalValues;
		ArrayList finalList = new ArrayList();
		for (int i = 0; i < values.length; i++) {
			if (!contains(exclude, values[i])) {
				finalList.add(values[i]);
			}
		}

		int finalSize = finalList.size();
		finalValues = new String[finalSize];
		Object[] finalArray = finalList.toArray();
		for (int j = 0; j < finalSize; j++) {
			finalValues[j] = (String) (finalArray[j]);
		}

		return finalValues;

	}

	/**
	 * Returns true if the array of String's contains the specified String. False otherwise.
	 */
	private boolean contains(String[] values, String s) {
		if (values == null)
			return false;

		for (int i = 0; i < values.length; i++) {
			if (values[i].equals(s))
				return true;
		}

		return false;
	}

}