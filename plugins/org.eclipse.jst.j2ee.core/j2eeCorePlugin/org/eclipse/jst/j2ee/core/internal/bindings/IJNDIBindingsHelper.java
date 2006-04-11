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
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.webapplication.WebApp;

/**
 * This is used to load jndi bindings from various J2EE artifacts. Clients should acquire
 * IBindingsHelper from the {@link JNDIBindingsHelperManager}.
 */
public interface IJNDIBindingsHelper {

	/**
	 * Clients should not call this method. This method is used by the
	 * {@link JNDIBindingsHelperManager}.
	 * 
	 * @param project
	 * @return true if this {@link IJNDIBindingsHelper} can be used to load bindings for specified
	 *         project.
	 */
	public boolean appliesFor(IProject project);

	/**
	 * Clients should not call this method. This method is used by the
	 * {@link JNDIBindingsHelperManager}.
	 * 
	 * @param project
	 * @return true if this {@link IJNDIBindingsHelper} can be used to load bindings for specified
	 *         archive.
	 */
	public boolean appliesFor(Archive archive);

	/**
	 * Returns the specified bean's JNDI name.
	 * 
	 * @param bean
	 * @return the specified bean's JNDI name.
	 */
	public String getJNDIName(EnterpriseBean bean);

	/**
	 * Returns the JNDI name for the specified CMP's default datasource.
	 * 
	 * @param bean
	 * @return the JNDI name for the specified CMP's default datasource.
	 */
	public String getJNDINameForDefaultDataSource(ContainerManagedEntity bean);

	/**
	 * Returns the specified bean's resource reference's JNDI name.
	 * 
	 * @param bean
	 * @param resourceRef
	 * @return the specified bean's resource reference's JNDI name.
	 */
	public String getJNDINameForRef(EnterpriseBean bean, ResourceRef resourceRef);

	/**
	 * Returns the specified bean's resource environment reference's JNDI name.
	 * 
	 * @param bean
	 * @param resourceEnvRef
	 * @return the specified bean's resource environment reference's JNDI name.
	 */
	public String getJNDINameForRef(EnterpriseBean bean, ResourceEnvRef resourceEnvRef);

	/**
	 * Returns the specified bean's security role referenc's JNDI name.
	 * 
	 * @param bean
	 * @param securityRoleRef
	 * @return the specified bean's security role referenc's JNDI name.
	 */
	public String getJNDINameForRef(EnterpriseBean bean, SecurityRoleRef securityRoleRef);

	/**
	 * Reuturns the specified web application's resource reference's JNDI name.
	 * 
	 * @param webApp
	 * @param resourceRef
	 * @return the specified web application's resource reference's JNDI name.
	 */
	public String getJNDINameForRef(WebApp webApp, ResourceRef resourceRef);

	/**
	 * Returns the specified web application's resource environment reference's JNDI name.
	 * 
	 * @param webApp
	 * @param resourceEnvRef
	 * @return the specified web application's resource environment reference's JNDI name.
	 */
	public String getJNDINameForRef(WebApp webApp, ResourceEnvRef resourceEnvRef);

	/**
	 * Returns the specified Object's JNDI name. This method should only be used if none of the
	 * others apply, as it will likely be inefficient due to instanceof checks.
	 * 
	 * @param obj
	 * @return the specified Object's JNDI name.
	 */
	public String getJNDIName(Object obj);

}
