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
package org.eclipse.jst.j2ee.internal.ejb.codegen;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.Session;


/**
 * Insert the type's description here. Creation date: (10/3/00 5:34:42 PM)
 * 
 * @author: Steve Wasleski
 */
public class SessionGenerator extends EnterpriseBeanGenerator {
	private List fGeneratorNames = null;

	/**
	 * CMPEntityBeanGenerator constructor comment.
	 */
	public SessionGenerator() {
		super();
	}

	/**
	 * Subclasses must implement this method to return the logical compilation unit generator names.
	 */
	protected List getCUGeneratorNames() {
		if (fGeneratorNames == null) {
			fGeneratorNames = new ArrayList();
			if (shouldGenRemote())
				fGeneratorNames.add(IEJBGenConstants.SESSION_REMOTE_INTERFACE_CU);
			if (shouldGenHome())
				fGeneratorNames.add(IEJBGenConstants.SESSION_HOME_INTERFACE_CU);
			if (shouldGenLocal())
				fGeneratorNames.add(IEJBGenConstants.SESSION_LOCAL_INTERFACE_CU);
			if (shouldGenLocalHome())
				fGeneratorNames.add(IEJBGenConstants.SESSION_LOCAL_HOME_INTERFACE_CU);
			if (shouldGenEjbClass())
				fGeneratorNames.add(IEJBGenConstants.SESSION_BEAN_CLASS_CU);
			if (shouldGenServiceEndpoint())
				fGeneratorNames.add(IEJBGenConstants.SESSION_SERVICE_ENDPOINT_INTERFACE_CU);
		}
		return fGeneratorNames;
	}

	protected boolean shouldGenServiceEndpoint() {
		if (getEnterpriseBeanHelper().isCreate())
			return getEnterpriseBeanHelper().getServiceEndpointHelper() != null;
		return (getEnterpriseBeanHelper().getServiceEndpointHelper() != null || (((Session) getEjb()).getServiceEndpoint() != null && canModifySource(((Session) getEjb()).getServiceEndpoint())));
	}
}