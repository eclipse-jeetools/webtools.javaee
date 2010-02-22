/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.ABSTRACT_METHODS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DESTROY;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DO_FILTER;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.FILTER_MAPPINGS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT_PARAM;
import static org.eclipse.jst.j2ee.web.IServletConstants.DESTROY_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.DO_FILTER_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.FILTER_INIT_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DESTROY;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_INIT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_ANNOTATION_INIT_PARAM;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_FILTER_CHAIN;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_FILTER_CONFIG;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_IO_EXCEPTION;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_EXCEPTION;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_RESPONSE;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_WEB_FILTER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jst.j2ee.internal.common.operations.Method;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateFilterTemplateModel extends CreateWebClassTemplateModel {
	
	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;

	public CreateFilterTemplateModel(IDataModel dataModel) {
		super(dataModel);
    }
    
	@Override
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		
		if (shouldGenInit()) {
			collection.add(QUALIFIED_FILTER_CONFIG);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
		}
		
		if (shouldGenDoFilter()) {
			collection.add(QUALIFIED_SERVLET_REQUEST);
			collection.add(QUALIFIED_SERVLET_RESPONSE);
			collection.add(QUALIFIED_FILTER_CHAIN);
			collection.add(QUALIFIED_IO_EXCEPTION);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
		}
		if (SERVLET_3.equals(getJavaEEVersion())){
			collection.add(QUALIFIED_WEB_FILTER);
			if (getInitParams()!= null && getInitParams().size()>0){
				collection.add(QUALIFIED_ANNOTATION_INIT_PARAM);
			}
		}
		
		return collection;
	}

	public String getFilterName() {
		return getProperty(CLASS_NAME).trim();
	}

	public boolean shouldGenInit() {
		return implementImplementedMethod(METHOD_INIT);
	}

	public boolean shouldGenDoFilter() {
		return implementImplementedMethod(METHOD_DO_FILTER);
	}

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(METHOD_DESTROY);
	}

	public List<String[]> getInitParams() {
		return (List) dataModel.getProperty(INIT_PARAM);
	}

	public String getInitParam(int index, int type) {
		List<String[]> params = getInitParams();
		if (index < params.size()) {
			String[] stringArray = params.get(index);
			return stringArray[type];
		}
		return null;
	}

	public List<IFilterMappingItem> getFilterMappings() {
		return (List<IFilterMappingItem>) dataModel.getProperty(FILTER_MAPPINGS);
	}

	public IFilterMappingItem getFilterMapping(int index) {
		List<IFilterMappingItem> mappings = getFilterMappings();
		if (index < mappings.size()) {
		    return mappings.get(index);
		}
		return null;
	}
	
	public String getDispatcherList(IFilterMappingItem mapping) {
		List<String> list = new ArrayList<String>();
		
		int dispatchers = mapping.getDispatchers();
		if ((dispatchers & IFilterMappingItem.REQUEST) > 0) {
            list.add(DispatcherType.REQUEST_LITERAL.getLiteral());
        }
        if ((dispatchers & IFilterMappingItem.FORWARD) > 0) {
            list.add(DispatcherType.FORWARD_LITERAL.getLiteral());
        }
        if ((dispatchers & IFilterMappingItem.INCLUDE) > 0) {
            list.add(DispatcherType.INCLUDE_LITERAL.getLiteral());
        }
        if ((dispatchers & IFilterMappingItem.ERROR) > 0) {
            list.add(DispatcherType.ERROR_LITERAL.getLiteral());
        }
        
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
        	builder.append(iterator.next());
        	if (iterator.hasNext()) {
        		builder.append(","); //$NON-NLS-1$
        	}
        }
		
		return builder.toString();
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (dataModel.getBooleanProperty(ABSTRACT_METHODS)) {
			if (methodName.equals(METHOD_INIT))
				return dataModel.getBooleanProperty(INIT);
			else if (methodName.equals(METHOD_DO_FILTER))
				return dataModel.getBooleanProperty(DO_FILTER);
			else if (methodName.equals(METHOD_DESTROY))
				return dataModel.getBooleanProperty(DESTROY);
		}		
		return false;
	}
	
	@Override
	public Collection<Method> getUnimplementedMethods() {
		Collection<Method> unimplementedMethods = super.getUnimplementedMethods();
		Iterator<Method> iterator = unimplementedMethods.iterator();
		
		while (iterator.hasNext()) {
			Method method = iterator.next();
			if ((METHOD_INIT.equals(method.getName()) && FILTER_INIT_SIGNATURE.equals(method.getSignature())) || 
					(METHOD_DESTROY.equals(method.getName()) && DESTROY_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_DO_FILTER.equals(method.getName()) && DO_FILTER_SIGNATURE.equals(method.getSignature()))) {
				iterator.remove();
			}
		}
		
		return unimplementedMethods;
	}
	
	public String getJavaEE6AnnotationParameters(){
		String result = "("; //$NON-NLS-1$
		List<IFilterMappingItem> filterMappings = getFilterMappings();
		if (filterMappings != null && filterMappings.size()>0 && hasUrlMapping(filterMappings)){
			result+="urlPatterns={"; //$NON-NLS-1$
			for (IFilterMappingItem filterMapItem : filterMappings) {
				result+="\""+filterMapItem.getName()+"\",";  //$NON-NLS-1$//$NON-NLS-2$
			}
			result = result.substring(0, result.length()-1);
			result+='}';
			
		}
		List<String[]> initParams = getInitParams();
		if (initParams != null && initParams.size()>0){
			if (result.length() > 1){
				result+=", "; //$NON-NLS-1$
			}
			result+="initParams={"; //$NON-NLS-1$
			for (String[] iParams : initParams) {
				result+=generateInitParamAnnotation(iParams[0], iParams[1]) + ","; //$NON-NLS-1$
			}
			result = result.substring(0, result.length()-1);
			result+="}"; //$NON-NLS-1$
		}
		result+=")"; //$NON-NLS-1$
		return result.length() > 2 ? result : ""; //$NON-NLS-1$
	}
	
	private boolean hasUrlMapping(List<IFilterMappingItem> filterMappings) {
		for (IFilterMappingItem filterMapItem : filterMappings) {
			if (filterMapItem.isUrlPatternType()){
				return true;
			}
		}
		return false;
	}

	private String generateInitParamAnnotation(String name, String value){
		return "@WebInitParam(name=\""+name+"\", value=\""+value+"\")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
