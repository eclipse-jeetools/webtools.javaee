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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

/**
 * Servlet Mappings group in Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupServletMappingItemProvider extends AbstractWebGroupProvider{

	private static Image SERVLETMAPPING;

	public GroupServletMappingItemProvider(WebApp webApp) {
		super(webApp);
		text = Messages.SERVLET_MAPPING_ITEM_PROVIDER;
	}

	@Override
	public List getChildren() {
		if (javaee != null){
			return flattenMapping(((WebApp) javaee).getServletMappings());
		}
		return null;
	}

	@Override
	public Image getImage() {
		return getServletMappingImage();
	}

	public static Image getServletMappingImage() {
		if (SERVLETMAPPING == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.SERVLET_MAPPING);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
			SERVLETMAPPING = imageDescriptor.createImage();
		}
		return SERVLETMAPPING;
	}
	
	private List<?> flattenMapping(List<ServletMapping> servletMappings) {
		List<ServletMappingUIWrapper> result = new ArrayList<ServletMappingUIWrapper>();
		if (servletMappings != null && servletMappings.size() > 0){
			for (ServletMapping filterMapping : servletMappings) {
				result.addAll(getServletMappingDisplay(filterMapping));
			}
		}
		return result;
	}

	
	private List<ServletMappingUIWrapper> getServletMappingDisplay(ServletMapping element) {
		List<ServletMappingUIWrapper> result = new ArrayList<ServletMappingUIWrapper>();
		String value = null;
		if (element.getUrlPatterns().size() > 0){
			for (UrlPatternType pattern : element.getUrlPatterns()) {
				value = pattern.getValue();
				result.add(new ServletMappingUIWrapper(value + " -> " + element.getServletName())); //$NON-NLS-1$
			}
			
		}
		return result;
	}
	
	public class ServletMappingUIWrapper{
		private String value;

		public String getValue() {
			return value;
		}

		public ServletMappingUIWrapper(String value) {
			super();
			this.value = value;
		}
	}
	

}
