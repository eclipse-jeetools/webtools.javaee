/*******************************************************************************
 * Copyright (c) 2007, 2022 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.ABSTRACT_METHODS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.ASYNC_SUPPORT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DESTROY;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.DO_FILTER;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.FILTER_MAPPINGS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewFilterClassDataModelProperties.INIT_PARAM;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DESTROY;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_FILTER;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_INIT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_ANNOTATION_DISPATCHER_TYPE;
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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.j2ee.internal.common.operations.Method;
import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateFilterTemplateModel extends CreateWebClassTemplateModel {

	public static final String ATT_FILTER_NAME = "filterName"; //$NON-NLS-1$
	public static final String ATT_SERVLET_NAMES = "servletNames"; //$NON-NLS-1$
	public static final String ATT_DISPATCHER_TYPES= "dispatcherTypes"; //$NON-NLS-1$
	
	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;

	public CreateFilterTemplateModel(IDataModel dataModel) {
		super(dataModel);
    }
    
	@Override
	public Collection<String> getImports() {
		Collection<String> imports = super.getImports();
		
		String javaEEVersion = getJavaEEVersion();
		if (shouldGenInit()) {
			if (SERVLET_5_0.equals(javaEEVersion)) {
				imports.add(IServletConstants.QUALIFIED_JAKARTA_FILTER_CONFIG);
				imports.add(IServletConstants.QUALIFIED_JAKARTA_SERVLET_EXCEPTION);
			}
			else {
				imports.add(QUALIFIED_FILTER_CONFIG);
				imports.add(QUALIFIED_SERVLET_EXCEPTION);
			}
		}
		
		if (shouldGenDoFilter()) {
			if (SERVLET_5_0.equals(javaEEVersion)) {
				imports.add(IServletConstants.QUALIFIED_JAKARTA_SERVLET_REQUEST);
				imports.add(IServletConstants.QUALIFIED_JAKARTA_SERVLET_RESPONSE);
				imports.add(IServletConstants.QUALIFIED_JAKARTA_FILTER_CHAIN);
				imports.add(QUALIFIED_IO_EXCEPTION);
				imports.add(IServletConstants.QUALIFIED_JAKARTA_SERVLET_EXCEPTION);
			}
			else {
				imports.add(QUALIFIED_SERVLET_REQUEST);
				imports.add(QUALIFIED_SERVLET_RESPONSE);
				imports.add(QUALIFIED_FILTER_CHAIN);
				imports.add(QUALIFIED_IO_EXCEPTION);
				imports.add(QUALIFIED_SERVLET_EXCEPTION);
			}
		}
		if (SERVLET_5_0.equals(javaEEVersion)) {
			imports.add(IServletConstants.QUALIFIED_JAKARTA_WEB_FILTER);
			if (getInitParams() != null && !getInitParams().isEmpty()) {
				imports.add(IServletConstants.QUALIFIED_JAKARTA_ANNOTATION_INIT_PARAM);
			}
			if (hasDispatchers()) {
				imports.add(IServletConstants.QUALIFIED_JAKARTA_ANNOTATION_DISPATCHER_TYPE);
			}
		}
		else {
			if (SERVLET_3.equals(javaEEVersion) || SERVLET_3_1.equals(javaEEVersion) || SERVLET_4_0.equals(javaEEVersion)) {
				imports.add(QUALIFIED_WEB_FILTER);
				if (getInitParams() != null && !getInitParams().isEmpty()) {
					imports.add(QUALIFIED_ANNOTATION_INIT_PARAM);
				}
				if (hasDispatchers()) {
					imports.add(QUALIFIED_ANNOTATION_DISPATCHER_TYPE);
				}
			}
		}
		
		return imports;
	}

	public String getFilterName() {
		return super.getDisplayName();
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
	
	public boolean getAsyncSupported(){
		return dataModel.getBooleanProperty(ASYNC_SUPPORT);
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
	
	public List<String> getDispatcherListWeb3(IFilterMappingItem mapping) {
		List<String> list = new ArrayList<String>();
		
		int dispatchers = mapping.getDispatchers();
		if ((dispatchers & IFilterMappingItem.REQUEST) > 0) {
            list.add("DispatcherType.REQUEST"); //$NON-NLS-1$
        }
        if ((dispatchers & IFilterMappingItem.FORWARD) > 0) {
            list.add("DispatcherType.FORWARD"); //$NON-NLS-1$
        }
        if ((dispatchers & IFilterMappingItem.INCLUDE) > 0) {
            list.add("DispatcherType.INCLUDE"); //$NON-NLS-1$
        }
        if ((dispatchers & IFilterMappingItem.ERROR) > 0) {
            list.add("DispatcherType.ERROR"); //$NON-NLS-1$
        }
        
		return list;
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
			String methodName = method.getName();
			String methodSignature = method.getSignature();
			if ((METHOD_INIT.equals(methodName) && (IServletConstants.FILTER_INIT_SIGNATURE.equals(methodSignature))) ||
					(METHOD_DESTROY.equals(methodName) && (IServletConstants.DESTROY_SIGNATURE.equals(methodSignature))) ||
					(METHOD_DO_FILTER.equals(methodName) && (IServletConstants.DO_FILTER_SIGNATURE.equals(methodSignature)))) {
				iterator.remove();
			}
			else if ((METHOD_INIT.equals(methodName) && (IServletConstants.JAKARTA_FILTER_INIT_SIGNATURE.equals(methodSignature))) ||
					(METHOD_DESTROY.equals(methodName) && (IServletConstants.JAKARTA_DESTROY_SIGNATURE.equals(methodSignature))) ||
					(METHOD_DO_FILTER.equals(methodName) && (IServletConstants.JAKARTA_DO_FILTER_SIGNATURE.equals(methodSignature)))) {
				iterator.remove();
			}
		}
		
		return unimplementedMethods;
	}
	
	public boolean hasDispatchers(){
		List<IFilterMappingItem> filterMappings = getFilterMappings();
		for (IFilterMappingItem filterMapping : filterMappings) {
			if (filterMapping.getDispatchersAsString().length()>0){
				return true;
			}
		}
		return false;
	}
	
	public Map<String, Object> getClassAnnotationParams() {
		Map<String, Object> result = new Hashtable<String, Object>();
		
		String dispName = getFilterName().trim();
		if (!dispName.equals(getClassName()) && (dispName.length() > 0))
			result.put(ATT_FILTER_NAME, dispName);
		
		String description = getDescription().trim();
		if (description.length() > 0)
			result.put(ATT_DESCRIPTION, description);
		
		List<IFilterMappingItem> filterMappings = getFilterMappings();
		List<String> urlMappings = new ArrayList<String>();
		List<String> servletNames = new ArrayList<String>();
		List<String> dispatcherTypes = new ArrayList<String>();
		for (IFilterMappingItem filterMapping : filterMappings) {
			List<String> dispatcherList = getDispatcherListWeb3(filterMapping);
			if (dispatcherList != null && dispatcherList.size()>0){
				for (String disp : dispatcherList) {
					if (!dispatcherTypes.contains(disp)){
						dispatcherTypes.add(disp);	
					}
				}
				
			}
			if (filterMapping.isUrlPatternType()) {
				urlMappings.add(filterMapping.getName());
			} else if (filterMapping.isServletNameType()) {
				servletNames.add(filterMapping.getName());
			}
		}
		if (urlMappings.size() > 0) {
			result.put(ATT_URL_PATTERNS, urlMappings);
		}
		if (dispatcherTypes.size() > 0) {
			result.put(ATT_DISPATCHER_TYPES, dispatcherTypes);
		}
		if (servletNames.size() > 0) {
			result.put(ATT_SERVLET_NAMES, servletNames);
		}
		
		
		List<String[]> initParams = getInitParams();
		if (initParams != null && initParams.size() > 0) {
			result.put(ATT_INIT_PARAMS, initParams);
		}
		
		boolean isAsyncSupported = getAsyncSupported();
		if (isAsyncSupported)
			result.put(ATT_ASYNC_SUPPORT, isAsyncSupported);


		return result;
	}
	
}

