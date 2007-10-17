/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
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

import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * @author kraev
 */
public class CreateListenerTemplateModel {

	private IDataModel dataModel = null;

	/**
	 * Constructor
	 */
	public CreateListenerTemplateModel(IDataModel dataModel) {
		super();
		this.dataModel = dataModel;
	}

	public String getListenerClassName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
	}

	public String getJavaPackageName() {
		return getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE).trim();
	}

	public String getQualifiedJavaClassName() {
		return getJavaPackageName() + "." + getListenerClassName(); //$NON-NLS-1$
	}

	public String getSuperclassName() {
		return getProperty(INewJavaClassDataModelProperties.SUPERCLASS).trim();
	}

	public String getListenerName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
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
	
	public boolean implementServletContextListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.SERVLET_CONTEXT_LISTENER);
	}
	
	public boolean implementServletContextAttributeListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.SERVLET_CONTEXT_ATTRIBUTE_LISTENER);
	}
	
	public boolean implementHttpSessionListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_LISTENER);
	}
	
	public boolean implementHttpSessionAttributeListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_ATTRIBUTE_LISTENER);
	}
	
	public boolean implementHttpSessionActivationListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_ACTIVATION_LISTENER);
	}
	
	public boolean implementHttpSessionBindingListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_BINDING_LISTENER);
	}
	
	public boolean implementServletRequestListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.SERVLET_REQUEST_LISTENER);
	}
	
	public boolean implementServletRequestAttributeListener() {
		return dataModel.getBooleanProperty(INewListenerClassDataModelProperties.SERVLET_REQUEST_ATTRIBUTE_LISTENER);
	}

}
