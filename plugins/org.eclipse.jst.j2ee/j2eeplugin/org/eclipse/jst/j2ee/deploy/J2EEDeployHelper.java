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
package org.eclipse.jst.j2ee.deploy;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.wst.common.emf.utilities.ICommandContext;


/**
 * @author cbridgha
 * 
 *  
 */
public class J2EEDeployHelper {

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static EJBJar getEJBJar(IResource resource, ICommandContext context) {

		if ((resource instanceof IProject) && (J2EENature.hasRuntime((IProject) resource, IEJBNatureConstants.NATURE_ID))) {
			String jarURI = J2EENature.getRuntime((IProject) resource, IEJBNatureConstants.NATURE_ID).getDeploymentDescriptorURI();
			return ((EJBResource) context.getResourceSet().getResource(URI.createURI(jarURI), false)).getEJBJar();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static Application getApplication(IResource resource, ICommandContext context) {
		if ((resource instanceof IProject) && (J2EENature.hasRuntime((IProject) resource, IEARNatureConstants.NATURE_ID))) {
			String earURI = J2EENature.getRuntime((IProject) resource, IEARNatureConstants.NATURE_ID).getDeploymentDescriptorURI();
			return ((ApplicationResource) context.getResourceSet().getResource(URI.createURI(earURI), false)).getApplication();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static ApplicationClient getAppClient(IResource resource, ICommandContext context) {
		if ((resource instanceof IProject) && (J2EENature.hasRuntime((IProject) resource, IApplicationClientNatureConstants.NATURE_ID))) {
			String appClientURI = J2EENature.getRuntime((IProject) resource, IApplicationClientNatureConstants.NATURE_ID).getDeploymentDescriptorURI();
			return ((ApplicationClientResource) context.getResourceSet().getResource(URI.createURI(appClientURI), false)).getApplicationClient();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static WebApp getWebApp(IResource resource, ICommandContext context) {

		if ((resource instanceof IProject) && (J2EENature.hasRuntime((IProject) resource, IWebNatureConstants.J2EE_NATURE_ID))) {
			String warURI = J2EENature.getRuntime((IProject) resource, IWebNatureConstants.J2EE_NATURE_ID).getDeploymentDescriptorURI();
			return ((WebAppResource) context.getResourceSet().getResource(URI.createURI(warURI), false)).getWebApp();
		}
		return null;
	}

	/**
	 * @param resource
	 * @param context
	 * @return
	 */
	public static Connector getConnector(IResource resource, ICommandContext context) {
		if ((resource instanceof IProject) && (J2EENature.hasRuntime((IProject) resource, IConnectorNatureConstants.NATURE_ID))) {
			String connURI = J2EENature.getRuntime((IProject) resource, IConnectorNatureConstants.NATURE_ID).getDeploymentDescriptorURI();
			return ((ConnectorResource) context.getResourceSet().getResource(URI.createURI(connURI), false)).getConnector();
		}
		return null;
	}

}