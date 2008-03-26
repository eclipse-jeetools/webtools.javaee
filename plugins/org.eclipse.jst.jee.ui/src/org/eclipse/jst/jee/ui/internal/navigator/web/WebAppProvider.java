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
package org.eclipse.jst.jee.ui.internal.navigator.web;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Content and Label Provider helper class for WebApp element.
 * 
 * @author Dimitar Giormov
 *
 */
public class WebAppProvider {
  
  
	private static final String PROJECT_RELATIVE_PATH = "WEB-INF/web.xml"; //$NON-NLS-1$
	
	
	private GroupErrorPagesItemProvider errors;
	private GroupServletItemProvider servlets;
	private GroupFiltersItemProvider filters;
	private GroupListenerItemProvider listeners;
	private GroupServletMappingItemProvider servletMapping;
	private GroupFilterMappingItemProvider filterMapping;
	private GroupReferenceItemProvider references;
	private GroupWelcomePagesItemProvider welcome;
	
	private List<Object> children = new ArrayList<Object>();
	
	private String text;
	private Image WEB_APP_IMAGE;
	
	private IProject prjct = null;
	private IFile ddFile = null;


  private GroupContextParamsItemProvider contextParams;


  
	
	
	
	
	public WebAppProvider(WebApp webApp, IProject project) {
		text = Messages.DEPLOYMENT_DESCRIPTOR + project.getName();
		contextParams = new GroupContextParamsItemProvider(webApp);
		errors = new GroupErrorPagesItemProvider(webApp);
		servlets = new GroupServletItemProvider(webApp);
		servletMapping = new GroupServletMappingItemProvider(webApp);
		filters = new GroupFiltersItemProvider(webApp);
		filterMapping = new GroupFilterMappingItemProvider(webApp);
		listeners = new GroupListenerItemProvider(webApp);
		references = new GroupReferenceItemProvider(webApp);
		welcome = new GroupWelcomePagesItemProvider(webApp);
		children.add(contextParams);
		children.add(errors);
		children.add(servlets);
		children.add(filters);
		children.add(listeners);
		children.add(servletMapping);
		children.add(filterMapping);
		children.add(references);
		children.add(welcome);
		prjct = project;
	}
	public List getChildren(){
		return children;
	}

	public String getText(){
		return text;
	}
	public Image getImage() {
		if (WEB_APP_IMAGE == null) {
			ImageDescriptor imageWebDescriptor = JEEUIPlugin.getDefault()
			.getImageDescriptor(JEEUIPluginIcons.IMG_WEBEEMODEL);
			WEB_APP_IMAGE = imageWebDescriptor.createImage();
		}
		return WEB_APP_IMAGE;
	}

	public IProject getProject(){
		return prjct;
	}
	public IFile getDDFile() {
		if (ddFile != null){
			return ddFile;
		}

		IVirtualFolder virtualFolder = ComponentCore.createComponent(getProject()).getRootFolder();
		return virtualFolder.getFile(PROJECT_RELATIVE_PATH).getUnderlyingFile();
	}
}
