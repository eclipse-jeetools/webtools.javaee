/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Aug 6, 2004
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;


/**
 * @author jlanuti
 */
public class CreateServletTemplateModel {

	NewServletClassDataModel dataModel = null;
	public static final String INIT = "init"; //$NON-NLS-1$
	public static final String TO_STRING = "toString"; //$NON-NLS-1$
	public static final String GET_SERVLET_INFO = "getServletInfo"; //$NON-NLS-1$
	public static final String DO_POST = "doPost"; //$NON-NLS-1$
	public static final String DO_PUT = "doPut"; //$NON-NLS-1$
	public static final String DO_DELETE = "doDelete"; //$NON-NLS-1$
	public static final String DESTROY = "destroy"; //$NON-NLS-1$
	public static final String DO_GET = "doGet"; //$NON-NLS-1$

	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;

	/**
	 * Constructor
	 */
	public CreateServletTemplateModel(NewServletClassDataModel dataModel) {
		super();
		this.dataModel = dataModel;
	}

	public String getServletClassName() {
		return getProperty(NewJavaClassDataModel.CLASS_NAME);
	}

	public String getJavaPackageName() {
		return getProperty(NewJavaClassDataModel.JAVA_PACKAGE);
	}

	public String getQualifiedJavaClassName() {
		return getJavaPackageName() + "." + getServletClassName(); //$NON-NLS-1$
	}

	public String getSuperclassName() {
		return getProperty(NewJavaClassDataModel.SUPERCLASS);
	}

	public String getServletName() {
		return this.dataModel.getServletName();
	}

	public boolean isPublic() {
		return this.dataModel.getBooleanProperty(NewJavaClassDataModel.MODIFIER_PUBLIC);
	}

	public boolean isFinal() {
		return this.dataModel.getBooleanProperty(NewJavaClassDataModel.MODIFIER_FINAL);
	}

	public boolean isAbstract() {
		return this.dataModel.getBooleanProperty(NewJavaClassDataModel.MODIFIER_ABSTRACT);
	}

	protected String getProperty(String propertyName) {
		return this.dataModel.getStringProperty(propertyName);
	}

	public boolean shouldGenInit() {
		return implementImplementedMethod(INIT);
	}

	public boolean shouldGenToString() {
		return implementImplementedMethod(TO_STRING);
	}

	public boolean shouldGenGetServletInfo() {
		return implementImplementedMethod(GET_SERVLET_INFO);
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

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(DESTROY);
	}

	public boolean shouldGenDoGet() {
		return implementImplementedMethod(DO_GET);
	}

	public List getInitParams() {
		return (List) this.dataModel.getParentEditModel().getProperty(AddServletDataModel.INIT_PARAM);
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
		return (List) this.dataModel.getParentEditModel().getProperty(AddServletDataModel.URL_MAPPINGS);
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
		return this.dataModel.getParentEditModel().getStringProperty(AddServletFilterListenerCommonDataModel.DESCRIPTION);
	}

	public List getInterfaces() {
		return (List) this.dataModel.getProperty(NewJavaClassDataModel.INTERFACES);
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (methodName.equals(INIT))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.INIT);
		else if (methodName.equals(TO_STRING))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.TO_STRING);
		else if (methodName.equals(GET_SERVLET_INFO))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.GET_SERVLET_INFO);
		else if (methodName.equals(DO_POST))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.DO_POST);
		else if (methodName.equals(DO_PUT))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.DO_PUT);
		else if (methodName.equals(DO_DELETE))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.DO_DELETE);
		else if (methodName.equals(DESTROY))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.DESTROY);
		else if (methodName.equals(DO_GET))
			return this.dataModel.getBooleanProperty(NewServletClassDataModel.DO_GET);
		else
			return false;
	}

}