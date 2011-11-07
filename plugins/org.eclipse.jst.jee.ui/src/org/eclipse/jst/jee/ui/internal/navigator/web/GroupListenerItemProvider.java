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

import java.net.URL;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

/**
 * Listener group in Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupListenerItemProvider extends AbstractWebGroupProvider{

	private static Image LISTENER;

	public GroupListenerItemProvider(WebApp webApp) {
		super(webApp);
		text = Messages.LISTENER_ITEM_PROVIDER;
	}

	@Override
	public List getChildren() {
		if (javaee != null){
		return ((WebApp) javaee).getListeners();
		}
		return null;
	}

	@Override
	public Image getImage() {
		return getListenersImage();
	}

	public static Image getListenersImage() {
		if (LISTENER == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.GROUP_LISTENER);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
			LISTENER = imageDescriptor.createImage();
	      }
	      return LISTENER;
	}

	
}
