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
package org.eclipse.jst.j2ee.internal.web.util;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;
import org.eclipse.wst.common.modulecore.ModuleURIUtil;
import org.eclipse.wst.common.modulecore.UnresolveableURIException;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p> 
 */
public class WebEdit extends EnterpriseArtifactEdit {
	
	public static final Class ADAPTER_TYPE = WebEdit.class;
	
	public static String TYPE_ID = "jst.web"; //$NON-NLS-1$
	
	/**
	 * @param model
	 */
	public WebEdit(ArtifactEditModel model) {
		super(model);
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#getJ2EEVersion()
	 */
	public int getJ2EEVersion() {
		return getWebApplicationXmiResource().getJ2EEVersionID();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#getDeploymentDescriptorRoot()
	 */
	public EObject getDeploymentDescriptorRoot() {
		List contents = getDeploymentDescriptorResource().getContents();
		if(contents.size() > 0)
			return (EObject)contents.get(0);
		addWebAppIfNecessary(getWebApplicationXmiResource());
		return (EObject)contents.get(0);
	}
	
	public WebAppResource getWebApplicationXmiResource() {
		return (WebAppResource)getDeploymentDescriptorResource();
	}

	public Resource getDeploymentDescriptorResource() {
		return getArtifactEditModel().getResource(J2EEConstants.WEBAPP_DD_URI_OBJ);
	}
	
	public WebApp getWebApplication() {
		return (WebApp)getDeploymentDescriptorRoot();
	}
	
	public int getServletVersion() {
		return getWebApplicationXmiResource().getModuleVersionID();
	}
	
	protected void addWebAppIfNecessary(XMLResource aResource) {
		 
		if (aResource != null && aResource.getContents().isEmpty()) {
			WebApp webApp = WebapplicationFactory.eINSTANCE.createWebApp();
			aResource.getContents().add(webApp);
			URI moduleURI = getArtifactEditModel().getModuleURI();			
			try {
				webApp.setDisplayName(ModuleURIUtil.getDeployedName(moduleURI));
			} catch (UnresolveableURIException e) {
			}
			aResource.setID(webApp, J2EEConstants.WEBAPP_ID);

			WelcomeFileList wList = WebapplicationFactory.eINSTANCE.createWelcomeFileList();
			webApp.setFileList(wList);
			List files = wList.getFile();
			WelcomeFile file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("index.html"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("index.htm"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("index.jsp"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("default.html"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("default.htm"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("default.jsp"); //$NON-NLS-1$
			files.add(file);
		}
	}

}
