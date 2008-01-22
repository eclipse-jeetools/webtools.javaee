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

import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author jlanuti
 */
public class CreateServletTemplateModel extends CreateWebClassTemplateModel
		implements INewServletClassDataModelProperties {

	public static final String QUALIFIED_IO_EXCEPTION = "java.io.IOException"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_EXCEPTION = "javax.servlet.ServletException"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_CONFIG = "javax.servlet.ServletConfig"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_REQUEST = "javax.servlet.ServletRequest"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_RESPONSE = "javax.servlet.ServletResponse"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SERVLET_REQUEST = "javax.servlet.http.HttpServletRequest"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SERVLET_RESPONSE = "javax.servlet.http.HttpServletResponse"; //$NON-NLS-1$
	
	public static final String INIT = "init"; //$NON-NLS-1$
	public static final String DESTROY = "destroy"; //$NON-NLS-1$
	public static final String GET_SERVLET_CONFIG = "getServletConfig"; //$NON-NLS-1$
	public static final String GET_SERVLET_INFO = "getServletInfo"; //$NON-NLS-1$
	public static final String SERVICE = "service"; //$NON-NLS-1$
	public static final String DO_GET = "doGet"; //$NON-NLS-1$
	public static final String DO_POST = "doPost"; //$NON-NLS-1$
	public static final String DO_PUT = "doPut"; //$NON-NLS-1$
	public static final String DO_DELETE = "doDelete"; //$NON-NLS-1$
	public static final String DO_HEAD = "doHead"; //$NON-NLS-1$
	public static final String DO_OPTIONS = "doOptions"; //$NON-NLS-1$
	public static final String DO_TRACE = "doTrace"; //$NON-NLS-1$
	public static final String TO_STRING = "toString"; //$NON-NLS-1$
	
	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;
	
	public CreateServletTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}
	
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
		return implementImplementedMethod(INIT);
	}

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(DESTROY);
	}

	public boolean shouldGenGetServletConfig() {
		return implementImplementedMethod(GET_SERVLET_CONFIG);
	}

	public boolean shouldGenGetServletInfo() {
		return implementImplementedMethod(GET_SERVLET_INFO);
	}

	public boolean shouldGenService() {
		return implementImplementedMethod(SERVICE);
	}

	public boolean shouldGenDoGet() {
		return implementImplementedMethod(DO_GET);
	}

	public boolean shouldGenDoPost() {
		return implementImplementedMethod(DO_POST);
	}

	public boolean shouldGenDoPut() {
		return implementImplementedMethod(DO_PUT);
	}

	public boolean shouldGenDoDelete() {
		return implementImplementedMethod(DO_DELETE);
	}

	public boolean shouldGenDoHead() {
		return implementImplementedMethod(DO_HEAD);
	}

	public boolean shouldGenDoOptions() {
		return implementImplementedMethod(DO_OPTIONS);
	}

	public boolean shouldGenDoTrace() {
		return implementImplementedMethod(DO_TRACE);
	}
	
	public boolean shouldGenToString() {
		return implementImplementedMethod(TO_STRING);
	}

	public boolean isGenericServletSuperclass() {
		ServletSupertypesValidator validator = new ServletSupertypesValidator(dataModel);
		return validator.isGenericServletSuperclass();
	}
	
	public boolean isHttpServletSuperclass() {
		ServletSupertypesValidator validator = new ServletSupertypesValidator(dataModel);
		return validator.isHttpServletSuperclass();
	}
	
	public List getInitParams() {
		return (List) dataModel.getProperty(INIT_PARAM);
	}

	public String getInitParam(int index, int type) {
		List params = getInitParams();
		if (index < params.size()) {
			String[] stringArray = (String[]) params.get(index);
			return stringArray[type];
		}
		return null;
	}

	public List getServletMappings() {
		return (List) dataModel.getProperty(URL_MAPPINGS);
	}

	public String getServletMapping(int index) {
		List mappings = getServletMappings();
		if (index < mappings.size()) {
			String[] map = (String[]) mappings.get(index);
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
			if (methodName.equals(INIT))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.INIT);
			else if (methodName.equals(DESTROY))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DESTROY);
			else if (methodName.equals(GET_SERVLET_CONFIG))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.GET_SERVLET_CONFIG);
			else if (methodName.equals(GET_SERVLET_INFO))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.GET_SERVLET_INFO);
			else if (methodName.equals(SERVICE))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.SERVICE);
			else if (methodName.equals(DO_GET))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DO_GET);
			else if (methodName.equals(DO_POST))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DO_POST);
			else if (methodName.equals(DO_PUT))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DO_PUT);
			else if (methodName.equals(DO_DELETE))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DO_DELETE);
			else if (methodName.equals(DO_HEAD))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DO_HEAD);
			else if (methodName.equals(DO_OPTIONS))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DO_OPTIONS);
			else if (methodName.equals(DO_TRACE))
				return dataModel.getBooleanProperty(INewServletClassDataModelProperties.DO_TRACE);
		}
		return false;
	}

}
