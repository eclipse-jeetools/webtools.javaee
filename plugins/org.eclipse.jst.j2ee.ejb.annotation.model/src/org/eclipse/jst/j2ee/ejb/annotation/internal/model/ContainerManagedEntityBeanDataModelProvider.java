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
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.annotation.internal.operations.AddContainerManagedEntityBeanOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class ContainerManagedEntityBeanDataModelProvider extends EnterpriseBeanClassDataModelProvider implements IContainerManagedEntityBeanDataModelProperties {

	public final static String DEFAULT_EJB_SUPERCLASS = "java.lang.Object"; //$NON-NLS-1$ 
	public final static String[] DEFAULT_EJB_INTERFACES = { "javax.ejb.EntityBean" }; //$NON-NLS-1$ //$NON-NLS-2$

	private List interfaceList;
	private HashMap sqlTypes = new HashMap();
	private HashMap attributeTypes = new HashMap();

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
		propertyNames.add(SQLTYPES);
		propertyNames.add(ATTRIBUTETYPES);
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
		else if (propertyName.equals(SQLTYPES))
			return sqlTypes;
		else if (propertyName.equals(ATTRIBUTETYPES))
			return attributeTypes;
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
			return validateJndiName(getStringProperty(propertyName));
		if (propertyName.equals(SCHEMA))
			return validateJndiName(getStringProperty(propertyName));
		return super.validate(propertyName);
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
		this.setProperty(SQLTYPES, this.getProperty(SQLTYPES));
		this.setProperty(ATTRIBUTETYPES, this.getProperty(ATTRIBUTETYPES));
	}

}