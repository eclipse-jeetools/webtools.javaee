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
 * Created on Apr 1, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class NewServletClassDataModel extends NewWebJavaClassDataModel {

	public static final String INIT = "NewServletClassDataModel.INIT"; //$NON-NLS-1$
	public static final String DO_POST = "NewServletClassDataModel.DO_POST"; //$NON-NLS-1$
	public static final String DESTROY = "NewServletClassDataModel.DESTROY"; //$NON-NLS-1$
	public static final String TO_STRING = "NewServletClassDataModel.TO_STRING"; //$NON-NLS-1$
	public static final String DO_PUT = "NewServletClassDataModel.DO_PUT"; //$NON-NLS-1$
	public static final String DO_GET = "NewServletClassDataModel.DO_GET"; //$NON-NLS-1$
	public static final String GET_SERVLET_INFO = "NewServletClassDataModel.GET_SERVLET_INFO"; //$NON-NLS-1$
	public static final String DO_DELETE = "NewServletClassDataModel.DO_DELETE"; //$NON-NLS-1$

	protected boolean USE_ANNOTATIONS = false;
	protected String SERVLET_NAME = null;
	protected AddServletDataModel parentEditModel = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(INIT);
		addValidBaseProperty(DO_POST);
		addValidBaseProperty(DESTROY);
		addValidBaseProperty(TO_STRING);
		addValidBaseProperty(DO_PUT);
		addValidBaseProperty(DO_GET);
		addValidBaseProperty(GET_SERVLET_INFO);
		addValidBaseProperty(DO_DELETE);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(DO_POST)) {
			return new Boolean(true);
		}
		if (propertyName.equals(DO_GET)) {
			return new Boolean(true);
		}
		return super.getDefaultProperty(propertyName);
	}

	protected IStatus validateJavaClassName(String className) {
		IStatus status = super.validateJavaClassName(className);
		if (status.isOK()) {
			// do not allow the name "Servlet"
			if (className.equals("Servlet")) { //$NON-NLS-1$
				String msg = WebMessages.getResourceString(WebMessages.ERR_SERVLET_JAVA_CLASS_NAME_INVALID);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
			return WTPCommonPlugin.OK_STATUS;
		}
		return status;
	}

	/**
	 * @return Returns the USE_ANNOTATIONS.
	 */
	public boolean isAnnotated() {
		return this.USE_ANNOTATIONS;
	}

	/**
	 * @param use_annotations
	 *            The USE_ANNOTATIONS to set.
	 */
	public void setAnnotations(boolean use_annotations) {
		this.USE_ANNOTATIONS = use_annotations;
	}

	/**
	 * @return Returns the SERVLET_NAME.
	 */
	public String getServletName() {
		return this.SERVLET_NAME;
	}

	/**
	 * @param servlet_name
	 *            The SERVLET_NAME to set.
	 */
	public void setServletName(String servlet_name) {
		this.SERVLET_NAME = servlet_name;
	}

	/**
	 * @return Returns the parentEditModel.
	 */
	public AddServletDataModel getParentEditModel() {
		return this.parentEditModel;
	}

	/**
	 * @param parentEditModel
	 *            The parentEditModel to set.
	 */
	public void setParentEditModel(AddServletDataModel parentEditModel) {
		this.parentEditModel = parentEditModel;
	}
}