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
 * Created on Apr 23, 2004
 *
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

/**
 * @author jialin
 *  
 */
public class AddCMPFieldsDataModel extends J2EEModelModifierOperationDataModel {

	public static final String ENTERPRISE_BEAN = "AddCMPFieldsDataModel.ENTERPRISE_BEAN"; //$NON-NLS-1$
	public static final String CMP_FIELD_LIST = "AddCMPFieldsDataModel.CMP_FIELD_LIST"; //$NON-NLS-1$
	public static final String KEY_PACKAGE_NAME = "AddCMPFieldsDataModel.KEY_PACKAGE_NAME"; //$NON-NLS-1$
	public static final String KEY_CLASS_NAME = "AddCMPFieldsDataModel.KEY_CLASS_NAME"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new AddCMPFieldsOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(ENTERPRISE_BEAN);
		addValidBaseProperty(CMP_FIELD_LIST);
		addValidBaseProperty(KEY_PACKAGE_NAME);
		addValidBaseProperty(KEY_CLASS_NAME);
	}

	protected Object getDefaultProperty(String propertyName) {
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(CMP_FIELD_LIST))
			return validateCMPFieldList((List) getProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	protected IStatus validateCMPFieldList(List prop) {
		if (prop == null || prop.isEmpty()) {
			String msg = EJBCreationResourceHandler.getString("ERR_CMP_FIELD_LIST_EMPTY"); //$NON-NLS-1$
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	public List getCMPFields() {
		return (List) getProperty(CMP_FIELD_LIST);
	}

	public boolean isVersion2xOrGreater() {
		ContainerManagedEntity bean = (ContainerManagedEntity) getProperty(ENTERPRISE_BEAN);
		return bean.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID;
	}

	public boolean hasLocal() {
		ContainerManagedEntity bean = (ContainerManagedEntity) getProperty(ENTERPRISE_BEAN);
		return bean.hasLocalClient();
	}

	public boolean hasRemote() {
		ContainerManagedEntity bean = (ContainerManagedEntity) getProperty(ENTERPRISE_BEAN);
		return bean.hasRemoteClient();
	}

	public boolean hasSuperEJB() {
		ContainerManagedEntity bean = (ContainerManagedEntity) getProperty(ENTERPRISE_BEAN);
		JavaClass superClass = bean.getEjbClass().getSupertype();
		if (superClass == null)
			return false;
		return !"java.lang.Object".equals(superClass.getQualifiedName()); //$NON-NLS-1$
	}

	public String getKeyClassName() {
		ContainerManagedEntity bean = (ContainerManagedEntity) getProperty(ENTERPRISE_BEAN);
		return bean.getPrimaryKeyName();
	}
}