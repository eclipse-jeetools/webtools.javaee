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
package org.eclipse.jst.jee.ui.internal.navigator.appclient;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Application Client 5.0 Deployment Descriptor node.
 * 
 * @author Dimitar Giormov
 * 
 */
public class GroupAppClientProvider {

	
	private static final String PROJECT_RELATIVE_PATH = "META-INF/application-client.xml"; //$NON-NLS-1$
	
	private static Image APP_CLIENT50;

	private IProject project = null;

	private ApplicationClient model = null;

	protected List children;

	private Object javaee = null;

	private IFile ddFile;

	public GroupAppClientProvider(IProject project) {
		this.project = project;
		javaee = getModel();
	}

	private ApplicationClient getModel() {
		if (this.model != null)
			return this.model;
		IModelProvider modelProvider = ModelProviderManager
		.getModelProvider(project);
		Object modelObject = null;
		try {
			modelObject = modelProvider.getModelObject();
		} catch (Exception e) {
			JEEUIPlugin.logError("Error during initializing model", e); //$NON-NLS-1$
		}

		if (modelObject != null && modelObject instanceof ApplicationClient) {
			return (ApplicationClient) modelObject;
		}
		return null;
	}

	public String getText() {
		String result = null;
		if (this.project == null) {
			result = Messages.DEPLOYMENT_DESCRIPTOR;
		} else {
			result = Messages.DEPLOYMENT_DESCRIPTOR + this.project.getName();
		}
		return result;
	}

	public List getChildren() {
		List result = new ArrayList();
		if (javaee != null){
			result.addAll(((ApplicationClient) javaee).getEjbRefs());
			//result.addAll(((ApplicationClient) javaee).getEjbLocalRefs());
			result.addAll(((ApplicationClient) javaee).getResourceRefs());
			result.addAll(((ApplicationClient) javaee).getResourceEnvRefs());
			result.addAll(((ApplicationClient) javaee).getEnvEntries());
			result.addAll(((ApplicationClient) javaee).getMessageDestinationRefs());
			//result.addAll(((ApplicationClient) javaee).getPersistenceContextRefs());
			result.addAll(((ApplicationClient) javaee).getPersistenceUnitRefs());
			result.addAll(((ApplicationClient) javaee).getServiceRefs());
		}
		return result;
	}
	
	public static Image getImage() {
		if (APP_CLIENT50 == null) {
			ImageDescriptor imageDescriptor = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.APP_CLIENT50);
			APP_CLIENT50 = imageDescriptor.createImage();
		}
		return APP_CLIENT50;
	}
	
	public IFile getDDFile() {
		if (ddFile != null){
			return ddFile;
		}
		IVirtualFolder virtualFolder = ComponentCore.createComponent(project).getRootFolder();
		ddFile = virtualFolder.getFile(PROJECT_RELATIVE_PATH).getUnderlyingFile();
		return ddFile;
	}
}
