/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.web;

import java.net.URL;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

/**
 * Servlets group in Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupServletItemProvider extends AbstractWebGroupProvider{

	private static Image SERVLET;

	public GroupServletItemProvider(WebApp webApp) {
		super(webApp);
		text = Messages.SERVLET_ITEM_PROVIDER;
	}


	@Override
	public List getChildren() {
		if (javaee != null){
			return ((WebApp) javaee).getServlets();
		}
		return null;
	}

	@Override
	public Image getImage() {
		return getServletImage();
	}


	public static Image getServletImage() {
		if (SERVLET == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.GROUP_SERVLET);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
			SERVLET = imageDescriptor.createImage();
		}
		return SERVLET;
	}

}
