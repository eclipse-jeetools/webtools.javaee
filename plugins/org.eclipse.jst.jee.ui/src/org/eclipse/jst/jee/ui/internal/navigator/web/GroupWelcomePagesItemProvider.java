/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WelcomeFileList;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

/**
 * Welcome pages group in Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupWelcomePagesItemProvider extends AbstractWebGroupProvider{

	private static Image WELCOME_PAGES;

	private static Image WELCOME_PAGE;

	public GroupWelcomePagesItemProvider(WebApp webApp) {
		super(webApp);
		text = Messages.WELCOME_PAGES_ITEM_PROVIDER;
	}

	@Override
	public List getChildren() {
//		if (children != null){
//			return children;
//		}
			children.clear();
		if (javaee != null){
			List welcomeFileLists = ((WebApp) javaee).getWelcomeFileLists();
			if (welcomeFileLists.size()>0){
				children =  new ArrayList();
				for (Object object : welcomeFileLists) {
					List welcomeFiles = ((WelcomeFileList)object).getWelcomeFiles();
					for (Object object2 : welcomeFiles) {
						if(object2 != null && !"".equals( ((String)object2).trim())){ //$NON-NLS-1$
							children.add(new WebArtifactNode((WebApp)javaee, (String) object2, getWelcomePageImage()));
						}
					}
				}
				return children;
			}
			return null;
		}
		return null;
	}

	@Override
	public Image getImage() {
		return getWelcomePagesImage();
	}

	public static Image getWelcomePagesImage() {
		if (WELCOME_PAGES == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.GROUP_WELCOME_PAGES);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
			WELCOME_PAGES = imageDescriptor.createImage();
		}
		return WELCOME_PAGES;
	}

	public static Image getWelcomePageImage() {
		if (WELCOME_PAGE == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.GROUP_WELCOME_PAGE);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
			WELCOME_PAGE = imageDescriptor.createImage();
		}
		return WELCOME_PAGE;
	}
}
