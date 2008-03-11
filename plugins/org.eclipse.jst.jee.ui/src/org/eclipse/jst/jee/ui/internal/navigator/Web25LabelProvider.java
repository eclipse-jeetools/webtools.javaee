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
package org.eclipse.jst.jee.ui.internal.navigator;

import org.eclipse.jst.j2ee.navigator.internal.J2EELabelProvider;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.FilterMapping;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.ServletMapping;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupFilterMappingItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupFiltersItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupListenerItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupServletItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupServletMappingItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.swt.graphics.Image;


/**
 * Web 2.5 Label provider is Deployment Descriptor label provider, 
 * used for decorating of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class Web25LabelProvider extends J2EELabelProvider {

	
	
	@Override
	public Image getImage(Object element) {
		Image ret = null;
		if (element instanceof WebAppProvider) {
			ret = ((WebAppProvider) element).getImage();
		} else if (element instanceof AbstractGroupProvider){
			ret = ((AbstractGroupProvider) element).getImage();
		} else if (element instanceof Servlet){
			ret = GroupServletItemProvider.getServletImage();
		} else if (element instanceof ServletMapping){
			ret = GroupServletMappingItemProvider.getServletMappingImage();
		} else if (element instanceof Filter){
			ret = GroupFiltersItemProvider.getFiltersImage();
		} else if (element instanceof Listener){
			ret = GroupListenerItemProvider.getListenersImage();
		} else if (element instanceof FilterMapping){
			ret = GroupFilterMappingItemProvider.getFilterMappingImage();
		}
		return ret;
	}

	@Override
	public String getText(Object element) {
		String ret = null;
		if (element instanceof WebAppProvider) {
			ret = ((WebAppProvider) element).getText();
		} else if (element instanceof AbstractGroupProvider){
			ret = ((AbstractGroupProvider) element).getText();
		} else if (element instanceof Servlet){
			ret = ((Servlet) element).getServletName();
		} else if (element instanceof ServletMapping){
			ret = "Servlet mapping (" + getServletMappingDisplay((ServletMapping) element)+")"; //$NON-NLS-1$ //$NON-NLS-2$
		} else if (element instanceof Filter){
			ret = ((Filter) element).getFilterName();
		} else if (element instanceof Listener){
			ret = ((Listener) element).getListenerClass();
		} else if (element instanceof FilterMapping){
			ret = "Filter mapping (" + getFilterMappingDisplay((FilterMapping)element) + ")"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return ret;
	}

	private String getFilterMappingDisplay(FilterMapping element) {
		String returnValue = ""; //$NON-NLS-1$
		UrlPatternType urlPatterns = (UrlPatternType) element.getUrlPatterns().get(0);
		returnValue = element.getFilterName() + "-> " + urlPatterns.getValue(); //$NON-NLS-1$
		return returnValue;
	}

	private String getServletMappingDisplay(ServletMapping element) {
		String retValue = "-> " + element.getServletName(); //$NON-NLS-1$
		UrlPatternType urlPatterns = (UrlPatternType) element.getUrlPatterns().get(0);
		retValue = urlPatterns.getValue() + retValue;
		return retValue;
	}

}
