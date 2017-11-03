/*******************************************************************************
 * Copyright (c) 2003, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Kiril Mitov, k.mitov@sap.com	- bug 204160
 * Kaloyan Raev, kaloyan.raev@sap.com
 *******************************************************************************/
/*
 * Created on Aug 6, 2004
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.ABSTRACT_METHODS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DESTROY;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DO_DELETE;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DO_GET;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DO_HEAD;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DO_OPTIONS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DO_POST;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DO_PUT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.DO_TRACE;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.GET_SERVLET_CONFIG;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.GET_SERVLET_INFO;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.INIT;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.INIT_PARAM;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.SERVICE;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.URL_MAPPINGS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties.ASYNC_SUPPORT;
import static org.eclipse.jst.j2ee.web.IServletConstants.DESTROY_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.DO_DELETE_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.DO_GET_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.DO_HEAD_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.DO_POST_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.DO_PUT_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.DO_TRACE_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.GET_SERVLET_CONFIG_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.GET_SERVLET_INFO_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.HTTP_SERVICE_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DESTROY;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_DELETE;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_GET;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_HEAD;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_OPTIONS;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_POST;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_PUT;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_DO_TRACE;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_GET_SERVLET_CONFIG;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_GET_SERVLET_INFO;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_INIT;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_SERVICE;
import static org.eclipse.jst.j2ee.web.IServletConstants.METHOD_TO_STRING;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_ANNOTATION_INIT_PARAM;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SERVLET_REQUEST;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SERVLET_RESPONSE;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_IO_EXCEPTION;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONFIG;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_EXCEPTION;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_RESPONSE;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_WEB_SERVLET;
import static org.eclipse.jst.j2ee.web.IServletConstants.SERVICE_SIGNATURE;
import static org.eclipse.jst.j2ee.web.IServletConstants.SERVLET_INIT_SIGNATURE;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.j2ee.internal.common.operations.Method;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author jlanuti
 */
public class CreateServletTemplateModel extends CreateWebClassTemplateModel {

	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;
	
	public CreateServletTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}
	
	@Override
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		
		if (shouldGenInit()) {
			collection.add(QUALIFIED_SERVLET_CONFIG);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
		}
		
		if (shouldGenGetServletConfig()) {
			collection.add(QUALIFIED_SERVLET_CONFIG);
		}
		
		if (shouldGenService()) {
			if (isHttpServletSuperclass()) {
				collection.add(QUALIFIED_HTTP_SERVLET_REQUEST);
				collection.add(QUALIFIED_HTTP_SERVLET_RESPONSE);
			} else {
				collection.add(QUALIFIED_SERVLET_REQUEST);
				collection.add(QUALIFIED_SERVLET_RESPONSE);
			}
			
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
			collection.add(QUALIFIED_IO_EXCEPTION);
		}
		
		if (shouldGenDoGet() || shouldGenDoPost() || shouldGenDoPut() || 
				shouldGenDoDelete() || shouldGenDoHead() || 
				shouldGenDoOptions() || shouldGenDoTrace()) {
			collection.add(QUALIFIED_HTTP_SERVLET_REQUEST);
			collection.add(QUALIFIED_HTTP_SERVLET_RESPONSE);
			collection.add(QUALIFIED_SERVLET_EXCEPTION);
			collection.add(QUALIFIED_IO_EXCEPTION);
		}
		
		if (SERVLET_3.equals(getJavaEEVersion()) || SERVLET_3_1.equals(getJavaEEVersion()) || SERVLET_4_0.equals(getJavaEEVersion())){
			collection.add(QUALIFIED_WEB_SERVLET);
			if (getInitParams()!= null && getInitParams().size()>0){
				collection.add(QUALIFIED_ANNOTATION_INIT_PARAM);
			}
			
			

		}
		
		return collection;
	}

	public String getServletName() {
		return super.getDisplayName();
	}
	
	/**
	 * @deprecated Use {@link #getClassName()} instead. Will be removed post WTP
	 *             3.0.
	 * 
	 * @see CreateWebClassTemplateModel#getClassName()
	 */
	public String getServletClassName() {
		return super.getClassName();
	}
	
	public boolean shouldGenInit() {
		return implementImplementedMethod(METHOD_INIT);
	}

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(METHOD_DESTROY);
	}

	public boolean shouldGenGetServletConfig() {
		return implementImplementedMethod(METHOD_GET_SERVLET_CONFIG);
	}

	public boolean shouldGenGetServletInfo() {
		return implementImplementedMethod(METHOD_GET_SERVLET_INFO);
	}

	public boolean shouldGenService() {
		return implementImplementedMethod(METHOD_SERVICE);
	}

	public boolean shouldGenDoGet() {
		return implementImplementedMethod(METHOD_DO_GET);
	}

	public boolean shouldGenDoPost() {
		return implementImplementedMethod(METHOD_DO_POST);
	}

	public boolean shouldGenDoPut() {
		return implementImplementedMethod(METHOD_DO_PUT);
	}

	public boolean shouldGenDoDelete() {
		return implementImplementedMethod(METHOD_DO_DELETE);
	}

	public boolean shouldGenDoHead() {
		return implementImplementedMethod(METHOD_DO_HEAD);
	}

	public boolean shouldGenDoOptions() {
		return implementImplementedMethod(METHOD_DO_OPTIONS);
	}

	public boolean shouldGenDoTrace() {
		return implementImplementedMethod(METHOD_DO_TRACE);
	}
	
	public boolean shouldGenToString() {
		return implementImplementedMethod(METHOD_TO_STRING);
	}

	public boolean isGenericServletSuperclass() {
		return ServletSupertypesValidator.isGenericServletSuperclass(dataModel);
	}
	
	public boolean isHttpServletSuperclass() {
		return ServletSupertypesValidator.isHttpServletSuperclass(dataModel);
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

	public List<String[]> getServletMappings() {
		return (List) dataModel.getProperty(URL_MAPPINGS);
	}

	public boolean getAsyncSupported(){
		return dataModel.getBooleanProperty(ASYNC_SUPPORT);
	}
	
	public String getServletMapping(int index) {
		List<String[]> mappings = getServletMappings();
		if (index < mappings.size()) {
			String[] map = mappings.get(index);
			return map[0];
		}
		return null;
	}

	/**
	 * @deprecated Use {@link #getDescription()} instead. Will be removed post
	 *             WTP 3.0.
	 * 
	 * @see CreateWebClassTemplateModel#getDescription()
	 */
	public String getServletDescription() {
		return super.getDescription();
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (dataModel.getBooleanProperty(ABSTRACT_METHODS)) {
			if (methodName.equals(METHOD_INIT))
				return dataModel.getBooleanProperty(INIT);
			else if (methodName.equals(METHOD_DESTROY))
				return dataModel.getBooleanProperty(DESTROY);
			else if (methodName.equals(METHOD_GET_SERVLET_CONFIG))
				return dataModel.getBooleanProperty(GET_SERVLET_CONFIG);
			else if (methodName.equals(METHOD_GET_SERVLET_INFO))
				return dataModel.getBooleanProperty(GET_SERVLET_INFO);
			else if (methodName.equals(METHOD_SERVICE))
				return dataModel.getBooleanProperty(SERVICE);
			else if (methodName.equals(METHOD_DO_GET))
				return dataModel.getBooleanProperty(DO_GET);
			else if (methodName.equals(METHOD_DO_POST))
				return dataModel.getBooleanProperty(DO_POST);
			else if (methodName.equals(METHOD_DO_PUT))
				return dataModel.getBooleanProperty(DO_PUT);
			else if (methodName.equals(METHOD_DO_DELETE))
				return dataModel.getBooleanProperty(DO_DELETE);
			else if (methodName.equals(METHOD_DO_HEAD))
				return dataModel.getBooleanProperty(DO_HEAD);
			else if (methodName.equals(METHOD_DO_OPTIONS))
				return dataModel.getBooleanProperty(DO_OPTIONS);
			else if (methodName.equals(METHOD_DO_TRACE))
				return dataModel.getBooleanProperty(DO_TRACE);
		}
		return false;
	}

	@Override
	public Collection<Method> getUnimplementedMethods() {
		Collection<Method> unimplementedMethods = super.getUnimplementedMethods();
		Iterator<Method> iterator = unimplementedMethods.iterator();
		
		while (iterator.hasNext()) {
			Method method = iterator.next();
			if ((METHOD_INIT.equals(method.getName()) && SERVLET_INIT_SIGNATURE.equals(method.getSignature())) || 
					(METHOD_DESTROY.equals(method.getName()) && DESTROY_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_GET_SERVLET_CONFIG.equals(method.getName()) && GET_SERVLET_CONFIG_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_GET_SERVLET_INFO.equals(method.getName()) && GET_SERVLET_INFO_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_SERVICE.equals(method.getName()) && SERVICE_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_SERVICE.equals(method.getName()) && HTTP_SERVICE_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_DO_GET.equals(method.getName()) && DO_GET_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_DO_POST.equals(method.getName()) && DO_POST_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_DO_PUT.equals(method.getName()) && DO_PUT_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_DO_DELETE.equals(method.getName()) && DO_DELETE_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_DO_HEAD.equals(method.getName()) && DO_HEAD_SIGNATURE.equals(method.getSignature())) ||
					(METHOD_DO_OPTIONS.equals(method.getName()) && METHOD_DO_OPTIONS.equals(method.getSignature())) ||
					(METHOD_DO_TRACE.equals(method.getName()) && DO_TRACE_SIGNATURE.equals(method.getSignature()))) {
				iterator.remove();
			}
		}
		
		return unimplementedMethods;
	}
	
	public Map<String, Object> getClassAnnotationParams() {
		Map<String, Object> result = new Hashtable<String, Object>();
		
		String dispName = getServletName().trim();
		if (!dispName.equals(getClassName()) && (dispName.length() > 0))
			result.put(ATT_NAME, dispName);
		
		String description = getDescription().trim();
		if (description.length() > 0)
			result.put(ATT_DESCRIPTION, description);
		
		List<String[]> servletMappings = getServletMappings();
		if (servletMappings != null && servletMappings.size() > 0) {
			result.put(ATT_URL_PATTERNS, servletMappings);
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
