/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Kiril Mitov, k.mitov@sap.com	- bug 204160
 *******************************************************************************/
/*
 * Created on Aug 6, 2004
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author jlanuti
 */
public class CreateServletTemplateModel {

	IDataModel dataModel = null;
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
	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;

	/**
	 * Constructor
	 */
	public CreateServletTemplateModel(IDataModel dataModel) {
		super();
		this.dataModel = dataModel;
	}

	public String getServletClassName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
	}

	public String getJavaPackageName() {
		return getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE).trim();
	}

	public String getQualifiedJavaClassName() {
		return getJavaPackageName() + "." + getServletClassName(); //$NON-NLS-1$
	}

	public String getSuperclassName() {
		return getProperty(INewJavaClassDataModelProperties.SUPERCLASS).trim();
	}

	public String getServletName() {
		return getProperty(INewServletClassDataModelProperties.DISPLAY_NAME).trim();
	}

	public boolean isPublic() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_PUBLIC);
	}

	public boolean isFinal() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_FINAL);
	}

	public boolean isAbstract() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_ABSTRACT);
	}

	protected String getProperty(String propertyName) {
		return dataModel.getStringProperty(propertyName);
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

	public boolean isGenericServletSuperclass() {
		ServletSupertypesValidator validator = new ServletSupertypesValidator(dataModel);
		return validator.isGenericServletSuperclass();
	}
	
	public boolean isHttpServletSuperclass() {
		ServletSupertypesValidator validator = new ServletSupertypesValidator(dataModel);
		return validator.isHttpServletSuperclass();
	}
	
	public List getInitParams() {
		return (List) dataModel.getProperty(INewServletClassDataModelProperties.INIT_PARAM);
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
		return (List) dataModel.getProperty(INewServletClassDataModelProperties.URL_MAPPINGS);
	}

	public String getServletMapping(int index) {
		List mappings = getServletMappings();
		if (index < mappings.size()) {
			String[] map = (String[]) mappings.get(index);
			return map[0];
		}
		return null;
	}

	public String getServletDescription() {
		return dataModel.getStringProperty(INewServletClassDataModelProperties.DESCRIPTION);
	}

	public List getInterfaces() {
		return (List) this.dataModel.getProperty(INewJavaClassDataModelProperties.INTERFACES);
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (dataModel.getBooleanProperty(INewJavaClassDataModelProperties.ABSTRACT_METHODS)) {
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
