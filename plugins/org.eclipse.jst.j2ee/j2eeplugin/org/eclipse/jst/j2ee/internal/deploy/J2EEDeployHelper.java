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
/*
 * Created on Aug 4, 2004
 */
package org.eclipse.jst.j2ee.internal.deploy;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.internal.emf.utilities.ICommandContext;


/**
 * @author cbridgha
 * 
 *  
 */
public class J2EEDeployHelper {

	/**
	 * @param resource
	 * @param context
	 * @returns first EJBJar found in the project
	 */
	public static EJBJar getEJBJar(IResource resource, ICommandContext context) {
		IFlexibleProject virtualProj = null;
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				virtualProj = ComponentCore.createFlexibleProject((IProject)resource);
				IVirtualComponent[] comps = virtualProj.getComponents();
				for (int i = 0; i < comps.length; i++) {
					IVirtualComponent component = comps[i];
					if (IModuleConstants.JST_EJB_MODULE.equals(component.getComponentTypeId())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(component);
						return (EJBJar)edit.getDeploymentDescriptorRoot();
					}
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
		IFlexibleProject virtualProj = null;
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				virtualProj = ComponentCore.createFlexibleProject((IProject)resource);
				IVirtualComponent[] comps = virtualProj.getComponents();
				for (int i = 0; i < comps.length; i++) {
					IVirtualComponent component = comps[i];
					if (IModuleConstants.JST_EAR_MODULE.equals(component.getComponentTypeId())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(component);
						return (Application)edit.getDeploymentDescriptorRoot();
					}
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
		IFlexibleProject virtualProj = null;
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				virtualProj = ComponentCore.createFlexibleProject((IProject)resource);
				IVirtualComponent[] comps = virtualProj.getComponents();
				for (int i = 0; i < comps.length; i++) {
					IVirtualComponent component = comps[i];
					if (IModuleConstants.JST_APPCLIENT_MODULE.equals(component.getComponentTypeId())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(component);
						return (ApplicationClient)edit.getDeploymentDescriptorRoot();
					}
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

		IFlexibleProject virtualProj = null;
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				virtualProj = ComponentCore.createFlexibleProject((IProject)resource);
				IVirtualComponent[] comps = virtualProj.getComponents();
				for (int i = 0; i < comps.length; i++) {
					IVirtualComponent component = comps[i];
					if (IModuleConstants.JST_WEB_MODULE.equals(component.getComponentTypeId())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(component);
						return (WebApp)edit.getDeploymentDescriptorRoot();
					}
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
		IFlexibleProject virtualProj = null;
		EnterpriseArtifactEdit edit = null;
		try {
			if (resource instanceof IProject) {
				virtualProj = ComponentCore.createFlexibleProject((IProject)resource);
				IVirtualComponent[] comps = virtualProj.getComponents();
				for (int i = 0; i < comps.length; i++) {
					IVirtualComponent component = comps[i];
					if (IModuleConstants.JST_CONNECTOR_MODULE.equals(component.getComponentTypeId())) {
						edit = (EnterpriseArtifactEdit)ComponentUtilities.getArtifactEditForRead(component);
						return (Connector)edit.getDeploymentDescriptorRoot();
					}
				}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return null;
	}

}