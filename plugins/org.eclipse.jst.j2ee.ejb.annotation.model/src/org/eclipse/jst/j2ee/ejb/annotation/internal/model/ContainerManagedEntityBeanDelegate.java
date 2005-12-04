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

import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ContainerManagedEntityBeanDelegate extends EnterpriseBeanDelegate implements IContainerManagedEntityBean {

	public static final String BMP = "BMP";
	public static final String CMP = "CMP";

	public ContainerManagedEntityBeanDelegate() {
		super();
		ContainerManagedEntity entity = EjbFactory.eINSTANCE.createContainerManagedEntity();
		this.setEnterpriseBean(entity);
	}

	public void setDataModel(IDataModel dataModel) {
		super.setDataModel(dataModel);
	}

	public String getType() {
		return CMP;
	}

	public String getTransactionType() {
		return TransactionType.CONTAINER_LITERAL.getName();
	}

	public void propertyChanged(DataModelEvent event) {
		super.propertyChanged(event);
		String property = event.getPropertyName();
		Object propertyValue = event.getProperty();
		ContainerManagedEntity entity = (ContainerManagedEntity) this.getEnterpriseBean();
		if (entity == null)
			return;

		if (IContainerManagedEntityBeanDataModelProperties.SCHEMA.equals(property)) {
			entity.setAbstractSchemaName((String) propertyValue);
		}
	}

	public String getDatasource() {
		return this.getDataModel().getStringProperty(IContainerManagedEntityBeanDataModelProperties.DATASOURCE);
	}

	public String getSchema() {
		ContainerManagedEntity entity = (ContainerManagedEntity) this.getEnterpriseBean();
		return entity.getAbstractSchemaName();
	}

	public String getTable() {
		return this.getDataModel().getStringProperty(IContainerManagedEntityBeanDataModelProperties.TABLE);
	}


	public HashMap getAttributes() {
		return (HashMap) this.getDataModel().getProperty(IContainerManagedEntityBeanDataModelProperties.ATTRIBUTES);
	}

	public void setAttributes(HashMap attr) {
		this.getDataModel().setProperty(IContainerManagedEntityBeanDataModelProperties.ATTRIBUTES,attr);
	}

	public void setAtribute(String name, String jtype, String stype, String description, boolean isPrimary, boolean isTransient) {
		ContainerManagedEntity entity = (ContainerManagedEntity) this.getEnterpriseBean();
		CMPAttribute attribute = null;
		if (isPrimary)
			attribute = entity.addKeyAttributeName(name);
		else
			attribute = entity.addPersistentAttributeName(name);
		// If the class doesn't exist, it will be reflected

		// JavaHelpers helper = JavaRefFactory.eINSTANCE.reflectType(type,
		// (ResourceSet) nature.getResourceSet());
		// attribute.setOriginatingType(helper);
		attribute.setDescription(description);
		attribute.setTransient(isTransient);

	}
	public void removeAttribute(String name) {
		
		ContainerManagedEntity entity = (ContainerManagedEntity) this.getEnterpriseBean();
		entity.removePersistentAttribute(name);
	}

	public String getPrimaryKeyClass() {
		java.util.List keys = getKeyAttributes();
		
		if( keys.size() > 1)
			return this.getSimpleClassName() + ".PrimaryKey";
		else if( keys.size() == 1)
			return ((CMPAttributeDelegate)keys.get(0)).getAttributeType();
		return "java.lang.String";
	}

	public List getKeyAttributes() {
		Iterator attributes = getAttributes().values().iterator();
		ArrayList keys = new ArrayList();
		while (attributes.hasNext()) {
			CMPAttributeDelegate attribute = (CMPAttributeDelegate) attributes.next();
			if(attribute.isKey())
				keys.add(attribute);
			
		}
		return keys;
	}
}
