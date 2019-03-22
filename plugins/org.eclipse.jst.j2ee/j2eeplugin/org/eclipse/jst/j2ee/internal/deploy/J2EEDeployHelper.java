/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Aug 4, 2004
 */
package org.eclipse.jst.j2ee.internal.deploy;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.internal.emf.utilities.ICommandContext;

/**
 * @author cbridgha
 * 
 */
public class J2EEDeployHelper {

	/**
	 * @param resource
	 * @param context
	 * @returns first EJBJar found in the project
	 */
	public static EJBJar getEJBJar(IResource resource, ICommandContext context) {
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				IVirtualComponent comp = ComponentCore.createComponent((IProject)resource);
				if (JavaEEProjectUtilities.isEJBProject(comp.getProject())) {
					edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(comp);
					return (EJBJar)edit.getDeploymentDescriptorRoot();
				}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static Application getApplication(IResource resource, ICommandContext context) {
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				IVirtualComponent comp = ComponentCore.createComponent((IProject)resource);
					if (JavaEEProjectUtilities.isEARProject(comp.getProject())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(comp);
						return (Application)edit.getDeploymentDescriptorRoot();
					}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static ApplicationClient getAppClient(IResource resource, ICommandContext context) {
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				IVirtualComponent comp = ComponentCore.createComponent((IProject)resource);
					if (JavaEEProjectUtilities.isApplicationClientProject(comp.getProject())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(comp);
						return (ApplicationClient)edit.getDeploymentDescriptorRoot();
					}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static WebApp getWebApp(IResource resource, ICommandContext context) {
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				IVirtualComponent comp = ComponentCore.createComponent((IProject)resource);
					if (JavaEEProjectUtilities.isDynamicWebProject(comp.getProject())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(comp);
						return (WebApp)edit.getDeploymentDescriptorRoot();
					}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static Connector getConnector(IResource resource, ICommandContext context) {
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				IVirtualComponent comp = ComponentCore.createComponent((IProject)resource);
					if (JavaEEProjectUtilities.isJCAProject(comp.getProject())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(comp);
						return (Connector)edit.getDeploymentDescriptorRoot();
					}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return null;
	}
}
