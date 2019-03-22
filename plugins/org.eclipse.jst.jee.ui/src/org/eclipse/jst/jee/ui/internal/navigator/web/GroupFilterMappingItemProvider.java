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
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

/**
 * Filter mappings group in Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupFilterMappingItemProvider extends AbstractWebGroupProvider {

	private static Image FILTER_MAPPING;

	public GroupFilterMappingItemProvider(WebApp webApp) {
		super(webApp);
		text = Messages.FILTER_MAPPING_ITEM_PROVIDER;
	}

	@Override
	public List<?> getChildren() {
		if (javaee != null){
			return flattenMapping(((WebApp) javaee).getFilterMappings());
		}
		return null;
	}

	private List<?> flattenMapping(List<FilterMapping> filterMappings) {
		List<FilterMappingUIWrapper> result = new ArrayList<FilterMappingUIWrapper>();
		if (filterMappings != null && filterMappings.size() > 0){
			for (FilterMapping filterMapping : filterMappings) {
				result.addAll(getFilterMappingDisplay(filterMapping));
			}
		}
		return result;
	}

	@Override
	public Image getImage() {
		return getFilterMappingImage();
	}

	public static Image getFilterMappingImage() {
		if (FILTER_MAPPING == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.GROUP_FILTER_MAPPING);
			ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
			FILTER_MAPPING = imageDescriptor.createImage();
		}
		return FILTER_MAPPING;
	}
	
	private List<FilterMappingUIWrapper> getFilterMappingDisplay(FilterMapping element) {
		List<FilterMappingUIWrapper> result = new ArrayList<FilterMappingUIWrapper>();
		String value = null;
		if (element.getUrlPatterns().size() > 0){
			for (UrlPatternType pattern : element.getUrlPatterns()) {
				value = pattern.getValue();
				result.add(new FilterMappingUIWrapper(value + " -> " + element.getFilterName())); //$NON-NLS-1$
			}
			
		}
		return result;
	}
	
	public class FilterMappingUIWrapper {
		private String value;

		public String getValue() {
			return value;
		}

		public FilterMappingUIWrapper(String value) {
			super();
			this.value = value;
		}
	}


}
