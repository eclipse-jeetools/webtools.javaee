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
 * Created on Feb 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class CreateEntityBeanDataModel extends CreateEnterpriseBeanWithClientViewDataModel {
	private static final String ENTITY_INTERFACE_TYPE = "javax.ejb.EntityBean"; //$NON-NLS-1$
	/**
	 * Type String, required, the name of the key class to use.
	 */
	public static final String KEY_CLASS_NAME = "CreateEntityBeanDataModel.KEY_CLASS_NAME"; //$NON-NLS-1$

	public CreateEntityBeanDataModel() {
		super();
	}

	public WTPOperation getDefaultOperation() {
		return new CreateEntityBeanOperation(this);
	}

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(KEY_CLASS_NAME);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(KEY_CLASS_NAME)) {
			return getDefaultClassName("Key"); //$NON-NLS-1$
		}
		return super.getDefaultProperty(propertyName);
	}

	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(KEY_CLASS_NAME)) {
			return validateKeyClass();
		}
		return super.doValidateProperty(propertyName);
	}

	protected IStatus validateKeyClass() {
		String keyClassName = (String) getProperty(KEY_CLASS_NAME);
		if (keyClassName == null || keyClassName.length() < 1)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Entity_enter_primary_key"), null); //$NON-NLS-1$
		// verify if the package is not a default package
		String pkg = getPackageName(keyClassName);
		if (pkg == null || pkg.trim().length() == 0)
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Entyty_primary_key_not_default"), null); //$NON-NLS-1$
		// verify if it is a valid Java package path
		IStatus javaStatus = validateJavaTypeName(keyClassName, "Key_class_UI_"); //$NON-NLS-1$
		if (!javaStatus.isOK())
			return javaStatus;
		return OK_STATUS;
	}

	protected Boolean getDefaultAddLocalFor2x() {
		return Boolean.TRUE;
	}

	protected Boolean getDefaultAddRemoteFor2x() {
		return Boolean.FALSE;
	}

	protected String getBeanClassEJBInterfaceName() {
		return ENTITY_INTERFACE_TYPE;
	}

	public int getEJBType() {
		return EJB_TYPE_BMP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean result = super.doSetProperty(propertyName, propertyValue);

		if (propertyName.equals(BEAN_SUPEREJB_NAME)) {
			Entity superEJB = null;
			String keyClassName = null;
			if (propertyValue != null && !propertyValue.equals("")) { //$NON-NLS-1$
				superEJB = (Entity) getEJBJar().getEnterpriseBeanNamed((String) propertyValue);
				keyClassName = superEJB.getPrimaryKeyName();
			}
			setProperty(KEY_CLASS_NAME, keyClassName);
			notifyDefaultChange(BEAN_CLASS_SUPERCLASS);
		}
		return result;
	}
}