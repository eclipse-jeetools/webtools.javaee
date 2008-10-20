/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.tests;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.ActivationConfigProperty;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;

/**
 * @author  Kiril Mitov k.mitov@sap.com
 *
 */
public class TestUtils {
	
	public static String getFileContent(IFile file) throws CoreException, IOException {
		InputStream stream = file.getContents();
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			int i = 0;
			while ((i = stream.read()) != -1)
				os.write(i);
			return os.toString();
		} finally {
			stream.close();
		}
	}

	public static EjbLocalRef findLocalRefByName(List<EjbLocalRef> localRefs, String refName) {
		for (Iterator iter = localRefs.iterator(); iter.hasNext();) {
			EjbLocalRef ref = (EjbLocalRef) iter.next();
			if (refName.equals(ref.getEjbRefName())) {
				return ref;
			}
		}
		return null;
	}

	public static RemoveMethodType findRemoveMethodByName(SessionBean bean, String methodName) {
		for (Iterator iter = bean.getRemoveMethods().iterator(); iter.hasNext();) {
			RemoveMethodType removeMethod = (RemoveMethodType) iter.next();
			if (methodName.equals(removeMethod.getBeanMethod().getMethodName()))
				return removeMethod;
		}
		return null;
	}

	public static InitMethodType findInitMethodByName(SessionBean bean, String methodName) {
		for (Iterator iter = bean.getInitMethods().iterator(); iter.hasNext();) {
			InitMethodType iniMethod = (InitMethodType) iter.next();
			if (methodName.equals(iniMethod.getBeanMethod().getMethodName()))
				return iniMethod;
		}
		return null;
	}

	public static LifecycleCallback findLifecycleMethod(List callbacks, String name) {
		for (Iterator iter = callbacks.iterator(); iter.hasNext();) {
			LifecycleCallback callback = (LifecycleCallback) iter.next();
			if (name.equals(callback.getLifecycleCallbackMethod()))
				return callback;
		}
		return null;
	}

	public static ResourceRef findResourceRefByName(List<ResourceRef> resourceRefs, String name) {
		for (Iterator iter = resourceRefs.iterator(); iter.hasNext();) {
			ResourceRef ref = (ResourceRef) iter.next();
			if (name.equals(ref.getResRefName())) {
				return ref;
			}
		}
		return null;
	}

	public static ActivationConfigProperty findActivationConfigProperty(MessageDrivenBean result, String name) {
		for (Iterator iter = result.getActivationConfig().getActivationConfigProperties().iterator(); iter.hasNext();) {
			ActivationConfigProperty prop = (ActivationConfigProperty) iter.next();
			if (name.equals(prop.getActivationConfigPropertyName()))
				return prop;
		}
		return null;
	}

	public static SecurityRole findSecurityRole(List securityRoles, String roleName) {
		for (Iterator iter = securityRoles.iterator(); iter.hasNext();) {
			SecurityRole role = (SecurityRole) iter.next();
			if (role.getRoleName().equals(roleName))
				return role;
		}
		return null;
	}

	public static SecurityRoleRef findSecurityRoleRef(List securityRefs, String string) {
		for (Iterator iter = securityRefs.iterator(); iter.hasNext();) {
			SecurityRoleRef ref = (SecurityRoleRef) iter.next();
			if (ref.getRoleName().equals(string)) {
				return ref;
			}
		}
		return null;
	}

	public static Servlet findServletByName(WebApp app, String servletName) {
		for (Iterator iter = app.getServlets().iterator(); iter.hasNext();) {
			Servlet servlet = (Servlet) iter.next();
			if (servlet.getServletName().equals(servletName)) {
				return servlet;
			}
		}
		return null;
	}

	public static SessionBean getSessionBean(EJBJar jar, String name) {
		if (jar.getEnterpriseBeans() == null)
			return null;
		for (Iterator iter = jar.getEnterpriseBeans().getSessionBeans().iterator(); iter.hasNext();) {
			SessionBean bean = (SessionBean) iter.next();
			if (name.equals(bean.getEjbName())) {
				return bean;
			}
		}
		return null;
	}

	public static MessageDrivenBean getMessageDrivenBean(EJBJar jar, String name) {
		if (jar.getEnterpriseBeans() == null)
			return null;
		for (Iterator iter = jar.getEnterpriseBeans().getMessageDrivenBeans().iterator(); iter.hasNext();) {
			MessageDrivenBean bean = (MessageDrivenBean) iter.next();
			if (name.equals(bean.getEjbName())) {
				return bean;
			}
		}
		return null;
	}

}
