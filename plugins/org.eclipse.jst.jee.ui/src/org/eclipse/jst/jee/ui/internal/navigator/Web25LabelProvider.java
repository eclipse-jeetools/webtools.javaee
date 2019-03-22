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
package org.eclipse.jst.jee.ui.internal.navigator;

import org.eclipse.jst.j2ee.navigator.internal.J2EELabelProvider;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.web.ErrorPage;
import org.eclipse.jst.javaee.web.Filter;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupContextParamsItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupErrorPagesItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupFilterMappingItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupFilterMappingItemProvider.FilterMappingUIWrapper;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupFiltersItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupListenerItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupServletItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupServletMappingItemProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.GroupServletMappingItemProvider.ServletMappingUIWrapper;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebArtifactNode;
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
		} else if (element instanceof ServletMappingUIWrapper){
			ret = GroupServletMappingItemProvider.getServletMappingImage();
		} else if (element instanceof Filter){
			ret = GroupFiltersItemProvider.getFiltersImage();
		} else if (element instanceof Listener){
			ret = GroupListenerItemProvider.getListenersImage();
		} else if (element instanceof FilterMappingUIWrapper){
			ret = GroupFilterMappingItemProvider.getFilterMappingImage();
		} else if (element instanceof WebArtifactNode){
		  ret = ((WebArtifactNode)element).getImage();
		} else if (element instanceof ErrorPage) {
		  ret = GroupErrorPagesItemProvider.getErrorPagesImage((ErrorPage)element); 
        } else if (element instanceof ParamValue){
          ret = GroupContextParamsItemProvider.getContextParamsImage(); 
        } else {
          ret = super.getImage(element);
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
		} else if (element instanceof ServletMappingUIWrapper) {
			ret = ((ServletMappingUIWrapper) element).getValue();
		} else if (element instanceof Filter){
			ret = ((Filter) element).getFilterName();
		} else if (element instanceof Listener){
			ret = ((Listener) element).getListenerClass();
		} else if (element instanceof FilterMappingUIWrapper) {
			ret = ((FilterMappingUIWrapper) element).getValue();
		} else if (element instanceof WebArtifactNode) {
            ret = ((WebArtifactNode) element).getText(); 
        } else if (element instanceof ErrorPage ){
        	ErrorPage page = ((ErrorPage) element);
			if (page.getErrorCode() == null) {
				ret = page.getExceptionType() + " -> " + page.getLocation(); //$NON-NLS-1$
			} else {
				ret = page.getErrorCode() + " -> " + page.getLocation(); //$NON-NLS-1$
			}
        } else if (element instanceof ParamValue){
        	ret = ((ParamValue)element).getParamName() + " = " + ((ParamValue)element).getParamValue();//$NON-NLS-1$ 
        } else {
        	ret = super.getText(element);
        }
		return ret;
	}

}
