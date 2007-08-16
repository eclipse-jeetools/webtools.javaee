/*******************************************************************************
 * Copyright (c) 2005,2006 IBM Corporation
 * Licensed Material - Property of IBM. All rights reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure  v1.0
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.core.internal.bindings;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.webapplication.WebApp;

/**
 * Default implementation of {@link IJNDIBindingsHelper}. Clients should extend this class for their
 * implementations.
 * 
 */
public class AbstractJNDIBindingsHelper implements IJNDIBindingsHelper {

	/**
	 * Default implementation returns <code>false</code>.
	 */
	public boolean appliesFor(IProject project) {
		return false;
	}

	/**
	 * Default implementation returns <code>false</code>.
	 */
	public boolean appliesFor(Archive archive) {
		return false;
	}

	/**
	 * Default implementation returns <code>object.toString()</code>.
	 */
	public String getJNDIName(Object object) {
		return object.toString();
	}

	/**
	 * Default implementation returns <code>bean.getName()</code>.
	 */
	public String getJNDIName(EnterpriseBean bean) {
		return bean.getName();
	}

	/**
	 * Default implementation returns <code>resourceRef.getName()</code>.
	 */
	public String getJNDINameForRef(EnterpriseBean bean, ResourceRef resourceRef) {
		return resourceRef.getName();
	}

	/**
	 * Default implementation returns <code>resourceEnvRef.getName()</code>.
	 */
	public String getJNDINameForRef(EnterpriseBean bean, ResourceEnvRef resourceEnvRef) {
		return resourceEnvRef.getName();
	}

	/**
	 * Default implementation returns <code>securityRoleRef.getName()</code>.
	 */
	public String getJNDINameForRef(EnterpriseBean bean, SecurityRoleRef securityRoleRef) {
		return securityRoleRef.getName();
	}

	/**
	 * Default implementation returns <code>resourceRef.getName()</code>.
	 */
	public String getJNDINameForRef(WebApp webApp, ResourceRef resourceRef) {
		return resourceRef.getName();
	}

	/**
	 * Default implementation returns <code>resourceEnvRef.getName()</code>.
	 */
	public String getJNDINameForRef(WebApp webApp, ResourceEnvRef resourceEnvRef) {
		return resourceEnvRef.getName();
	}

	/**
	 * Default implementation returns <code>((EJBJar) bean.eContainer()).getDisplayName()</code>.
	 */
	public String getJNDINameForDefaultDataSource(ContainerManagedEntity bean) {
		return ((EJBJar) bean.eContainer()).getDisplayName();
	}

}