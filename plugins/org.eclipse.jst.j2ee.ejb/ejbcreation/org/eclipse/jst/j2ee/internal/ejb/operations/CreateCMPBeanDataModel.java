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
 * Created on Feb 25, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.creation.CMPField;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class CreateCMPBeanDataModel extends CreateEntityBeanDataModel {
	/**
	 * Boolean, default FALSE
	 */
	public static final String USE_PRIMITIVE_KEY = "CreateCMPBeanDataModel.USE_PRIMITIVE_KEY"; //$NON-NLS-1$
	/**
	 * This is a String property of the version which is only valid in a 2.x project. Defaults to
	 * 2.x.
	 * 
	 * @see String
	 * 
	 * @see ContainerManagedEntity#VERSION_1_X
	 * @see ContainerManagedEntity#VERSION_2_X
	 */
	public static final String CMP_VERSION = "CreateCMPBeanDataModel.CMP_VERSION"; //$NON-NLS-1$
	/**
	 * A List of CMPFields to be created with this bean default is a list with a single field with
	 * of name, id, type int, key field.
	 */
	public static final String CMP_FIELD_LIST = "CreateCMPBeanDataModel.CMP_FIELD_LIST"; //$NON-NLS-1$

	public static final String EJBTAGSET = "ejb"; //$NON-NLS-1$

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(CMP_FIELD_LIST);
		addValidBaseProperty(USE_PRIMITIVE_KEY);
		addValidBaseProperty(CMP_VERSION);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (CMP_FIELD_LIST.equals(propertyName)) {
			CMPField field = new CMPField();
			field.setName("id"); //$NON-NLS-1$
			field.setType("java.lang.Integer"); //$NON-NLS-1$
			field.setIsKey(true);
			field.setPromoteGS(false);
			field.setPromoteLocalGS(false);
			field.setExisting(false);
			field.setIsArray(false);
			ArrayList list = new ArrayList(1);
			list.add(field);
			return list;
		} else if (USE_PRIMITIVE_KEY.equals(propertyName)) {
			return new Boolean(canUsePrimaryKey());
		} else if (CMP_VERSION.equals(propertyName)) {
			return ContainerManagedEntity.VERSION_2_X;
		} else if (propertyName.equals(KEY_CLASS_NAME) && getBooleanProperty(USE_PRIMITIVE_KEY)) {
			CMPField primKey = getPrimKeyField();
			if (primKey != null)
				return getPrimKeyField().getType();
		}
		return super.getDefaultProperty(propertyName);
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean returnValue = super.doSetProperty(propertyName, propertyValue);
		if (CMP_VERSION.equals(propertyName)) {
			if (!isVersion2xOrGreater()) {
				setBooleanProperty(ADD_REMOTE, true);
				setBooleanProperty(ADD_LOCAL, false);
				setBooleanProperty(USE_ANNOTATIONS, false);
				notifyEnablementChange(USE_ANNOTATIONS);
			} else {
				notifyEnablementChange(USE_ANNOTATIONS);
			}
		} else if (propertyName.equals(PROJECT_NAME)) {
			if (!isProject2xOrGreater() && isSet(CMP_VERSION)) {
				setProperty(CMP_VERSION, null);
			}
			notifyDefaultChange(CMP_VERSION);
		} else if (propertyName.equals(BEAN_CLASS_SUPERCLASS) || propertyName.equals(CMP_FIELD_LIST)) {
			notifyDefaultChange(USE_PRIMITIVE_KEY);
			if (getBooleanProperty(USE_PRIMITIVE_KEY) || getStringProperty(KEY_CLASS_NAME) == null || getStringProperty(KEY_CLASS_NAME).equals("")) { //$NON-NLS-1$
				setProperty(KEY_CLASS_NAME, getDefaultProperty(KEY_CLASS_NAME));
			}
		} else if (propertyName.equals(USE_PRIMITIVE_KEY)) {
			setProperty(KEY_CLASS_NAME, getDefaultProperty(KEY_CLASS_NAME));
		} else if (propertyName.equals(USE_ANNOTATIONS)) {
			if (((Boolean) propertyValue).booleanValue()) {
				if (isSet(CMP_VERSION))
					setProperty(CMP_VERSION, null);
				else
					notifyEnablementChange(CMP_VERSION);
			} else
				notifyEnablementChange(CMP_VERSION);
		} else if (propertyName.equals(BEAN_SUPEREJB_NAME)) {
			notifyEnablementChange(KEY_CLASS_NAME);
			if (propertyValue != null && !propertyValue.equals("")) //$NON-NLS-1$
				removeKeyFields();
			else
				ensureKeyFieldExists();
			notifyEnablementChange(CMP_FIELD_LIST);
		}
		return returnValue;
	}

	public WTPOperation getDefaultOperation() {
		return new CreateCMPBeanOperation(this);
	}

	public List getCMPFields() {
		return (List) getProperty(CMP_FIELD_LIST);
	}

	protected CMPField getPrimKeyField() {
		List cmpFields = getCMPFields();
		if (cmpFields.isEmpty()) {
			return null;
		}
		java.util.Iterator addedFields = cmpFields.iterator();
		CMPField cmpField, keyField = null;
		while (addedFields.hasNext()) {
			cmpField = (CMPField) addedFields.next();
			if (cmpField.isIsKey()) {
				if (keyField == null)
					keyField = cmpField;
				else
					return null;
			}
		}
		return keyField;
	}

	public boolean canUsePrimaryKey() {
		//TODO handle binary
		String superEJBName = getStringProperty(BEAN_SUPEREJB_NAME);
		if (superEJBName == null)
			superEJBName = ""; //$NON-NLS-1$
		return superEJBName.equals("") && hasValidPrimitiveKeyField(); //$NON-NLS-1$
	}

	private boolean hasValidPrimitiveKeyField() {
		CMPField field = getPrimKeyField();
		if (field == null)
			return false;
		return !field.isIsArray() && !EJBGenHelpers.isPrimitive(field.getType());
	}

	public int getEJBType() {
		return EJB_TYPE_CMP;
	}

	public boolean isProject2xOrGreater() {
		return super.isVersion2xOrGreater();
	}

	public boolean isVersion2xOrGreater() {
		return super.isVersion2xOrGreater() && getStringProperty(CMP_VERSION).equals(ContainerManagedEntity.VERSION_2_X);
	}

	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(CMP_VERSION)) {
			return isProject2xOrGreater() ? new String[]{ContainerManagedEntity.VERSION_1_X, ContainerManagedEntity.VERSION_2_X} : new String[0];
		}
		return super.doGetValidPropertyValues(propertyName);
	}

	protected Boolean basicIsEnabled(String propertyName) {
		if (CMP_VERSION.equals(propertyName))
			return getBoolean(!getBooleanProperty(USE_ANNOTATIONS));
		else if (USE_ANNOTATIONS.equals(propertyName)) {
			if (!isVersion2xOrGreater())
				return Boolean.FALSE;
			return (AnnotationsControllerManager.INSTANCE.isAnyAnnotationsSupported()) ? Boolean.TRUE : Boolean.FALSE;
		} else if (KEY_CLASS_NAME.equals(propertyName)) {
			if (getProperty(BEAN_SUPEREJB_NAME) != null && !getProperty(BEAN_SUPEREJB_NAME).equals("")) //$NON-NLS-1$
				return Boolean.FALSE;
			return new Boolean(!getBooleanProperty(USE_PRIMITIVE_KEY));
		} else
			return super.basicIsEnabled(propertyName);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(CMP_FIELD_LIST)) {
			return validateFieldList();
		}
		return super.doValidateProperty(propertyName);
	}

	protected IStatus validateFieldList() {
		List fieldList = (List) getProperty(CMP_FIELD_LIST);
		String superBeanName = getStringProperty(BEAN_SUPEREJB_NAME);
		if (superBeanName == null)
			superBeanName = ""; //$NON-NLS-1$

		if (superBeanName.equals("") && (fieldList.size() == 0 || !containsKeyField())) { //$NON-NLS-1$
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("ERR_CMP_FIELD_LIST_KEY_FIELD_EMPTY"), null); //$NON-NLS-1$
		}
		return OK_STATUS;
	}

	private boolean containsKeyField() {
		List fieldList = (List) getProperty(CMP_FIELD_LIST);
		for (int x = 0; x < fieldList.size(); ++x) {
			CMPField field = (CMPField) fieldList.get(x);
			if (field.isIsKey())
				return true;
		}
		return false;
	}

	private void removeKeyFields() {
		ArrayList newFieldsList = new ArrayList();
		List fieldList = (List) getProperty(CMP_FIELD_LIST);
		newFieldsList.addAll(fieldList);
		for (int x = 0; x < fieldList.size(); ++x) {
			CMPField field = (CMPField) fieldList.get(x);
			if (field.isIsKey())
				newFieldsList.remove(field);
		}
		setProperty(CMP_FIELD_LIST, newFieldsList);
	}

	private void ensureKeyFieldExists() {
		if (containsKeyField())
			return;

		List newFields = new ArrayList();
		List keyList = (List) getDefaultProperty(CMP_FIELD_LIST);
		newFields.addAll(keyList);
		newFields.addAll(getCMPFields());
		setProperty(CMP_FIELD_LIST, newFields);
	}

}