/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 4, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.webservice.helper.WebServicesManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceExtManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper;
import org.eclipse.jst.j2ee.navigator.internal.J2EEEMFAdapterFactory;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServiceAdapterFactory extends J2EEEMFAdapterFactory {

	/**
	 * Default constructor
	 */
	public WebServiceAdapterFactory() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		Resource res = null;
		WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
		if (serviceHelper.isService(adaptableObject))
			res = WebServicesManager.getInstance().getWsddResource((EObject) adaptableObject);
		if (res == null)
			res = WebServicesManager.getInstance().getWSDLResource((EObject) adaptableObject);
		if (res != null && adapterType == J2EEEMFAdapterFactory.IFILE_CLASS)
			return WorkbenchResourceHelper.getFile(res);
		else if (res != null && adapterType == J2EEEMFAdapterFactory.IRESOURCE_CLASS)
			return WorkbenchResourceHelper.getFile(res);
		else
			return super.getAdapter(adaptableObject, adapterType);
	}
}
