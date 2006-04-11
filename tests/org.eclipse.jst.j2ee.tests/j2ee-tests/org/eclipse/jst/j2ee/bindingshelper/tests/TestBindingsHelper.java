/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.bindingshelper.tests;

import java.io.FileNotFoundException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.core.internal.bindings.AbstractJNDIBindingsHelper;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public abstract class TestBindingsHelper extends AbstractJNDIBindingsHelper {

	public abstract String getBindingsFileName();

	public boolean appliesFor(IProject project) {
		IVirtualComponent component = ComponentCore.createComponent(project);
		if (null == component) {
			return false;
		}
		IFile bindingsFile = component.getRootFolder().getFile(getBindingsFileName()).getUnderlyingFile();
		return bindingsFile.exists();
	}

	public boolean appliesFor(Archive archive) {
		try {
			archive.getFile(getBindingsFileName());
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public static final String OBJ = "obj";//$NON-NLS-1$
	public static final String EJB = "ejb";//$NON-NLS-1$
	public static final String EJB_RES_REF = "ejbResRef";//$NON-NLS-1$
	public static final String EJB_RES_ENV_REF = "ejbResEnvRef";//$NON-NLS-1$
	public static final String EJB_SEC_ROLE_REF = "ejbSecRoleRef";//$NON-NLS-1$
	public static final String WEB_RES_REF = "webResRef";//$NON-NLS-1$
	public static final String WEB_RES_ENV_REF = "webResEnvRef";//$NON-NLS-1$
	public static final String CMP = "cmp";//$NON-NLS-1$

	public String getJNDIName(Object object) {
		return getBindingsFileName() + super.getJNDIName(object) + OBJ;
	}

	public String getJNDIName(EnterpriseBean bean) {
		return getBindingsFileName() + super.getJNDIName(bean) + EJB;
	}

	public String getJNDINameForRef(EnterpriseBean bean, ResourceRef resourceRef) {
		return getBindingsFileName() + super.getJNDINameForRef(bean, resourceRef) + EJB_RES_REF;
	}

	public String getJNDINameForRef(EnterpriseBean bean, ResourceEnvRef resourceEnvRef) {
		return getBindingsFileName() + super.getJNDINameForRef(bean, resourceEnvRef) + EJB_RES_ENV_REF;
	}

	public String getJNDINameForRef(EnterpriseBean bean, SecurityRoleRef securityRoleRef) {
		return getBindingsFileName() + super.getJNDINameForRef(bean, securityRoleRef) + EJB_SEC_ROLE_REF;
	}

	public String getJNDINameForRef(WebApp webApp, ResourceRef resourceRef) {
		return getBindingsFileName() + super.getJNDINameForRef(webApp, resourceRef) + WEB_RES_REF;
	}

	public String getJNDINameForRef(WebApp webApp, ResourceEnvRef resourceEnvRef) {
		return getBindingsFileName() + super.getJNDINameForRef(webApp, resourceEnvRef) + WEB_RES_ENV_REF;
	}

	public String getJNDINameForDefaultDataSource(ContainerManagedEntity bean) {
		return getBindingsFileName() + super.getJNDINameForDefaultDataSource(bean) + CMP;
	}
}
