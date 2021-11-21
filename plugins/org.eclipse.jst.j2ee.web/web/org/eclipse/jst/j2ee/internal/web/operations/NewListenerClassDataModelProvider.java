/*******************************************************************************
 * Copyright (c) 2007, 2021 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;


import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.USE_EXISTING_CLASS;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_BINDING_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ID_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_ASYNC_EVENT_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_HTTP_SESSION_ACTIVATION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_HTTP_SESSION_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_HTTP_SESSION_BINDING_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_HTTP_SESSION_ID_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_HTTP_SESSION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_SERVLET_CONTEXT_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_SERVLET_CONTEXT_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_SERVLET_REQUEST_ASYNC_EVENT_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_SERVLET_REQUEST_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_JAKARTA_SERVLET_REQUEST_LISTENER;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

/**
 * The NewListenerClassDataModelProvider is a subclass of
 * NewWebClassDataModelProvider and follows the IDataModel Operation and Wizard
 * frameworks.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * 
 * This data model provider is a subclass of the NewWebClassDataModelProvider,
 * which stores base properties necessary in the creation of a default java
 * class.
 * @see org.eclipse.jst.j2ee.internal.common.operations.NewWebClassDataModelProvider
 * 
 * The NewListenerClassDataModelProvider provides more specific properties for
 * java class creation that are required in creating a listener java class. The
 * data model provider is used to store these values for the
 * NewListenerClassOperation.
 * @see org.eclipse.jst.j2ee.internal.web.operations.INewListenerClassDataModelProperties
 *      That operation will create the listener java class based on the settings
 *      defined here in the data model.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassOperation
 * 
 * This data model properties implements the IAnnotationsDataModel to get the
 * USE_ANNOTATIONS property for determining whether or not to generate an
 * annotated java class.
 * @see org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel
 * 
 * Clients can subclass this data model provider to cache and provide their own
 * specific attributes. They should also provide their own validation methods,
 * properties interface, and default values for the properties they add.
 * 
 */
public class NewListenerClassDataModelProvider extends
		NewWebClassDataModelProvider {

	public static final String[] LISTENER_INTERFACES = {
				QUALIFIED_SERVLET_CONTEXT_LISTENER,	
				QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER,
				QUALIFIED_HTTP_SESSION_LISTENER,
				QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER,
				QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER,
				QUALIFIED_HTTP_SESSION_BINDING_LISTENER,
				QUALIFIED_HTTP_SESSION_ID_LISTENER,
				QUALIFIED_SERVLET_REQUEST_LISTENER,
				QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER,
				QUALIFIED_SERVLET_REQUEST_ASYNC_EVENT_LISTENER
			};

	public static final String[] JAKARTA_LISTENER_INTERFACES = {
				QUALIFIED_JAKARTA_SERVLET_CONTEXT_LISTENER,	
				QUALIFIED_JAKARTA_SERVLET_CONTEXT_ATTRIBUTE_LISTENER,
				QUALIFIED_JAKARTA_HTTP_SESSION_LISTENER,
				QUALIFIED_JAKARTA_HTTP_SESSION_ATTRIBUTE_LISTENER,
				QUALIFIED_JAKARTA_HTTP_SESSION_ACTIVATION_LISTENER,
				QUALIFIED_JAKARTA_HTTP_SESSION_BINDING_LISTENER,
				QUALIFIED_JAKARTA_HTTP_SESSION_ID_LISTENER,
				QUALIFIED_JAKARTA_SERVLET_REQUEST_LISTENER,
				QUALIFIED_JAKARTA_SERVLET_REQUEST_ATTRIBUTE_LISTENER,
				QUALIFIED_JAKARTA_SERVLET_REQUEST_ASYNC_EVENT_LISTENER
			};

	/**
	 * Subclasses may extend this method to provide their own default operation
	 * for this data model provider. This implementation uses the
	 * AddListenerOperation to drive the listener creation. It will not return
	 * null.
	 * 
	 * @see IDataModel#getDefaultOperation()
	 * 
	 * @return IDataModelOperation AddListenerOperation
	 */
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new AddListenerOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to provide their own validation on any
	 * of the valid data model properties in the hierarchy. It does not accept a
	 * null parameter. This method will not return null.
	 * 
	 * @see NewJavaClassDataModelProvider#validate(String)
	 * 
	 * @param propertyName
	 * @return IStatus is property value valid?
	 */
	@Override
	public IStatus validate(String propertyName) {
		if (propertyName.equals(INTERFACES)){
			if (!getBooleanProperty(USE_EXISTING_CLASS)) {
				return validateListeners();
			}
		}					
		// Otherwise defer to super to validate the property
		return super.validate(propertyName);
	}
	

	/**
	 * Checks if at least one of the application lifecycle listeners is
	 * selected.
	 */
	private IStatus validateListeners() {
		boolean atLeastOneSelected = false;
		Object value = model.getProperty(INTERFACES);
		if (value != null && (value instanceof List)) {
			List interfaces = (List) value;
			for (String iface : LISTENER_INTERFACES) {
				if (interfaces.contains(iface)) { 
					atLeastOneSelected = true;
					break;
				}
			}
			for (String iface : JAKARTA_LISTENER_INTERFACES) {
				if (interfaces.contains(iface)) { 
					atLeastOneSelected = true;
					break;
				}
			}
		}
		
		if (atLeastOneSelected) {
			return WebPlugin.OK_STATUS;
		}
		String msg = WebMessages.ERR_NO_LISTENER_SELECTED;
		return WebPlugin.createStatus(IStatus.ERROR, msg);
	}

}
