/***********************************************************************
 * Copyright (c) 2008, 2017 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *     IBM - Java EE 7 and 8 support
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.appclient;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Application Client 5.0 Deployment Descriptor node.
 * 
 * @author Dimitar Giormov
 * @author Kaloyan Raev
 */
public class GroupAppClientProvider extends AbstractAppClientGroupProvider implements IAdaptable {

	
	private final IProject project;


	public GroupAppClientProvider(ApplicationClient javaee, IProject project) {
		super(javaee);
		this.project = project;
	}

	private static final String PROJECT_RELATIVE_PATH = "META-INF/application-client.xml"; //$NON-NLS-1$
	
	private Image appClient50Image;
	private Image appClient60Image;
	private Image appClient70Image;
	private Image appClient80Image;
	
	private IFile ddFile;

	@Override
	public String getText() {
		String result = null;
		if (this.project == null) {
			result = NLS.bind(Messages.DEPLOYMENT_DESCRIPTOR, new Object[] {""}); //$NON-NLS-1$
		} else {
			result = NLS.bind(Messages.DEPLOYMENT_DESCRIPTOR, this.project.getName());
		}
		return result;
	}

	@Override
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
	
	@Override
	public Image getImage() {
		String version = ((ApplicationClient) javaee).getVersion();
		if (J2EEVersionConstants.VERSION_5_TEXT.equals(version)) {
			return getAppClient50Image();
		} else if (J2EEVersionConstants.VERSION_6_TEXT.equals(version)) {
			return getAppClient60Image();
		} else if (J2EEVersionConstants.VERSION_7_TEXT.equals(version)) {
			return getAppClient70Image();
		} else if (J2EEVersionConstants.VERSION_8_TEXT.equals(version)) {
			return getAppClient80Image();
		}
		return getAppClient50Image();
	}
	
	public IFile getDDFile() {
		if (ddFile != null){
			return ddFile;
		}
		IVirtualFolder virtualFolder = ComponentCore.createComponent(project).getRootFolder();
		ddFile = virtualFolder.getFile(PROJECT_RELATIVE_PATH).getUnderlyingFile();
		return ddFile;
	}

	public IProject getProject() {
		return project;
	}
	
	public Object getAdapter(Class adapter) {
		if (IProject.class == adapter){
			return getProject();
		}
		return null;
	}

	private Image getAppClient50Image() {
		if (appClient50Image == null) {
			appClient50Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.APP_CLIENT50).createImage();
		}
		return appClient50Image;
	}

	private Image getAppClient60Image() {
		if (appClient60Image == null) {
			appClient60Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.APP_CLIENT60).createImage();
		}
		return appClient60Image;
	}

	private Image getAppClient70Image() {
		if (appClient70Image == null) {
			appClient70Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.APP_CLIENT70).createImage();
		}
		return appClient70Image;
	}
	
	private Image getAppClient80Image() {
		if (appClient80Image == null) {
			appClient80Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.APP_CLIENT80).createImage();
		}
		return appClient80Image;
	}
}
