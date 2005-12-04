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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddContainerManagedEntityBeanOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class ContainerManagedEntityBeanDataModelProvider extends EnterpriseBeanClassDataModelProvider implements
		IContainerManagedEntityBeanDataModelProperties {

	public final static String DEFAULT_EJB_SUPERCLASS = "java.lang.Object"; //$NON-NLS-1$ 
	public final static String[] DEFAULT_EJB_INTERFACES = { "javax.ejb.EntityBean" }; //$NON-NLS-1$ //$NON-NLS-2$

	private List interfaceList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public IDataModelOperation getDefaultOperation() {
		AddContainerManagedEntityBeanOperation operation = new AddContainerManagedEntityBeanOperation(getDataModel());
		getDataModel().getProperty(IEnterpriseBeanClassDataModelProperties.MODELDELEGATE);
		return operation;
	}

	/**
	 * Subclasses may extend this method to add their own data model's
	 * properties as valid base properties.
	 * 
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(DATASOURCE);
		propertyNames.add(SCHEMA);
		propertyNames.add(TABLE);
		propertyNames.add(ATTRIBUTES);
		propertyNames.add(EJB_INTERFACES);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(USE_ANNOTATIONS))
			return Boolean.FALSE;
		else if (propertyName.equals(DATASOURCE))
			return getProperty(JNDI_NAME);
		else if (propertyName.equals(EJB_TYPE))
			return "EntityBean";
		else if (propertyName.equals(MODIFIER_ABSTRACT))
			return Boolean.FALSE;
		else if (propertyName.equals(SUPERCLASS))
			return DEFAULT_EJB_SUPERCLASS;
		else if (propertyName.equals(EJB_INTERFACES))
			return DEFAULT_EJB_INTERFACES;
		else if (propertyName.equals(SCHEMA))
			return "MYSCHEMA";
		else if (propertyName.equals(TABLE))
			return "MYTABLE";
		else if (propertyName.equals(ATTRIBUTES))
			return new HashMap();
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	public IStatus validate(String propertyName) {
		if (propertyName.equals(DATASOURCE))
			return validateJndiName(getStringProperty(propertyName));
		if (propertyName.equals(TABLE))
			return validateTable(getStringProperty(propertyName));
		if (propertyName.equals(SCHEMA))
			return validateJndiName(getStringProperty(propertyName));
		if (propertyName.equals(ATTRIBUTES))
			return validateAttributes(getProperty(propertyName));
		return super.validate(propertyName);
	}

	protected IStatus validateTable(String prop) {
		// check for empty
		if (prop == null || prop.trim().length() == 0 || prop.indexOf(" ") >= 0 ) {
			String msg = IEJBAnnotationConstants.ERR_CMP_INVALID_TABLE;
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		
		return WTPCommonPlugin.OK_STATUS;
	}
	private IStatus validateAttributes(Object property) {
		HashMap attributes = (HashMap) property;
		if (attributes == null || attributes.size() == 0) {
			String msg = IEJBAnnotationConstants.ERR_NO_ATTRIBUTES;
			return WTPCommonPlugin.createErrorStatus(msg);
		}

		// No two fields should have the same name/column
		Iterator attributeNames = attributes.values().iterator();
		boolean duplicate = false;

		while (attributeNames.hasNext() && duplicate == false) {
			CMPAttributeDelegate aRow = (CMPAttributeDelegate) attributeNames.next();
			Iterator otherNames = attributes.values().iterator();
			while (otherNames.hasNext()) {
				CMPAttributeDelegate otherRow = (CMPAttributeDelegate) otherNames.next();
				if (aRow != otherRow && aRow.getName().equals(otherRow.getName())) {
					duplicate = true;
					break;
				}
				if (aRow != otherRow && aRow.getColumnName().equals(otherRow.getColumnName())) {
					duplicate = true;
					break;
				}
			}
		}
		if (duplicate)
			return WTPCommonPlugin.createErrorStatus(IEJBAnnotationConstants.ERR_DUPLICATE_ATTRIBUTES);

		// There should be at least one primary key field
		attributeNames = attributes.values().iterator();
		int primaryKey = 0;
		while (attributeNames.hasNext() && duplicate == false) {
			CMPAttributeDelegate aRow = (CMPAttributeDelegate) attributeNames.next();
			if (aRow.isKey())
				primaryKey++;
		}

		if (primaryKey == 0)
			return WTPCommonPlugin.createErrorStatus(IEJBAnnotationConstants.ERR_NO_KEY);

		return WTPCommonPlugin.OK_STATUS;

	}

	public HashMap getCMPAttributes() {
		HashMap attributes = (HashMap) getProperty(ATTRIBUTES);
		return attributes;
	}

	protected List getEJBInterfaces() {
		if (this.interfaceList == null) {
			this.interfaceList = new ArrayList();
			for (int i = 0; i < ((String[]) getProperty(EJB_INTERFACES)).length; i++) {
				this.interfaceList.add(((String[]) getProperty(EJB_INTERFACES))[i]);
			}
		}
		return this.interfaceList;
	}

	protected void initializeDelegate() {
		ContainerManagedEntityBeanDelegate delegate = new ContainerManagedEntityBeanDelegate();
		delegate.setDataModel(getDataModel());
		this.setProperty(MODELDELEGATE, delegate);

		// Set the defaults so that they are propagated via events
		this.setProperty(DATASOURCE, this.getProperty(DATASOURCE));
		this.setProperty(SCHEMA, this.getProperty(SCHEMA));
		this.setProperty(TABLE, this.getProperty(TABLE));
		this.setProperty(ATTRIBUTES, this.getProperty(ATTRIBUTES));
	}

}