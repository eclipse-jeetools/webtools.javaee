/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddMessageDrivenBeanOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;


public class MessageDrivenBeanDataModelProvider extends EnterpriseBeanClassDataModelProvider implements IMessageDrivenBeanDataModelProperties {
	
	public final static String DEFAULT_EJB_SUPERCLASS = "java.lang.Object"; //$NON-NLS-1$ 
	public final static String[] DEFAULT_EJB_INTERFACES = {"javax.ejb.MessageDrivenBean", "javax.jms.MessageListener"}; //$NON-NLS-1$ //$NON-NLS-2$
	
	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public IDataModelOperation getDefaultOperation() {
		return new AddMessageDrivenBeanOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to add their own data model's properties as valid base properties.
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	public String[] getPropertyNames() {
		String[] props = new String[]{DESTINATIONTYPE, DESTINATIONNAME, EJB_INTERFACES};
        return combineProperties(super.getPropertyNames(), props);
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		else if (propertyName.equals(DESTINATIONNAME))
			return getProperty(JNDI_NAME);
		else if (propertyName.equals(DESTINATIONTYPE))
			return DestinationType.QUEUE_LITERAL.getName();
		else if (propertyName.equals(EJB_TYPE))
			return "MessageDrivenBean";
		else if (propertyName.equals(MODIFIER_ABSTRACT))
			return Boolean.FALSE;
		else if (propertyName.equals(SUPERCLASS))
			return DEFAULT_EJB_SUPERCLASS;
		else if (propertyName.equals(EJB_INTERFACES))
			return DEFAULT_EJB_INTERFACES;
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	public IStatus validate(String propertyName) {
		if (propertyName.equals(DESTINATIONNAME))
			return validateJndiName(getStringProperty(propertyName));
		if (propertyName.equals(DESTINATIONTYPE))
			return validateDestinationType(getStringProperty(propertyName));
		return super.validate(propertyName);
	}

	private IStatus validateDestinationType(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0) {
			String msg = IEJBAnnotationConstants.ERR_DESTINATIONTYPE_EMPTY;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (prop.indexOf("Queue") >= 0 || prop.indexOf("Topic") >= 0) {
			return WTPCommonPlugin.OK_STATUS;
		}
		String msg = IEJBAnnotationConstants.ERR_DESTINATIONTYPE_VALUE;
		return WTPCommonPlugin.createErrorStatus(msg);
	}
	
	protected List getEJBInterfaces() {
		if (this.interfaceList == null) {
			this.interfaceList = new ArrayList();
			for (int i = 0; i < ((String[])getProperty(EJB_INTERFACES)).length; i++) {
				this.interfaceList.add(((String[])getProperty(EJB_INTERFACES))[i]);
			}
		}
		return this.interfaceList;
	}

	protected void initializeDelegate() {
		MessageDrivenBeanDelegate delegate = new MessageDrivenBeanDelegate();
		delegate.setDataModel(getDataModel());
		this.setProperty(MODELDELEGATE,delegate);
		//Set the defaults so that they are propagated via events
		this.setProperty(DESTINATIONTYPE, this.getProperty(DESTINATIONTYPE));
		this.setProperty(TRANSACTIONTYPE, this.getProperty(TRANSACTIONTYPE));
		this.setProperty(DESTINATIONNAME, this.getProperty(DESTINATIONNAME));
	}



}