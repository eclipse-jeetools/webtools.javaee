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
 * Created on May 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.impl.ActivationConfigPropertyImpl;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.internal.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class MDBNonJMSActivationConfigDataModel extends J2EEModelModifierOperationDataModel {
	/**
	 * Defines the activation config properties for non JMS type message driven bean.
	 * 
	 * @since EJB 2.1
	 * @link String
	 */
	protected ActivationConfig editActivationConfig = null;
	public static final String ACTIVATION_CONFIG_PROPERTIES = "CreateMessageDrivenBean21DataModel.activationConfig"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new MDBNonJMSActivationConfigOperation(this);
	}

	public List getActivationConfigProperty() {
		return (List) getProperty(ACTIVATION_CONFIG_PROPERTIES);
	}

	public ArrayList getDefaultActivationConfig(String property) {
		return new ArrayList();
	}

	public ActivationConfig getNonJMSActivationConfigModel() {
		ActivationConfig activationConfig = EjbFactoryImpl.getActiveFactory().createActivationConfig();
		List configs = (List) getProperty(ACTIVATION_CONFIG_PROPERTIES);
		if (configs != null && !configs.isEmpty()) {
			((List) activationConfig.getConfigProperties()).addAll(configs);
		}
		return activationConfig;
	}

	public void addActivationConfigKeyValue(String property, String[] keyValue) {
		if (editActivationConfig == null)
			editActivationConfig = (ActivationConfig) getProperty(ACTIVATION_CONFIG_PROPERTIES);
		List configProperties = editActivationConfig.getConfigProperties();
		ActivationConfigProperty configProperty = null;
		boolean foundProperty = false;
		if (!configProperties.isEmpty()) {
			for (int i = 0; i < configProperties.size(); i++) {
				configProperty = (ActivationConfigPropertyImpl) configProperties.get(i);
				if (property.equals("names")) { //$NON-NLS-1$
					String value = configProperty.getValue();
					if (value != null && value.equals(keyValue[1])) {
						configProperty.setName(keyValue[0]);
						foundProperty = true;
					}
				} else if (property.equals("values")) { //$NON-NLS-1$
					String name = configProperty.getName();
					if (name != null && name.equals(keyValue[0])) {
						configProperty.setValue(keyValue[1]);
						foundProperty = true;
					}
				}
			}
		}
		if (foundProperty == false) {
			configProperty = EjbFactory.eINSTANCE.createActivationConfigProperty();
			if (property.equals("names")) //$NON-NLS-1$
				configProperty.setName(keyValue[0]);
			else if (property.equals("value")) //$NON-NLS-1$
				configProperty.setName(keyValue[1]);
			configProperties.add(configProperty);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ACTIVATION_CONFIG_PROPERTIES))
			return getDefaultActivationConfig(ACTIVATION_CONFIG_PROPERTIES);
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(ACTIVATION_CONFIG_PROPERTIES);
		super.initValidBaseProperties();
	}


	/**
	 * @param strings
	 */
	public void removeActivationConfigKeyValue(String[] keyValue) {
		List configs = (List) getProperty(ACTIVATION_CONFIG_PROPERTIES);
		ActivationConfigPropertyImpl configProperty = null;
		boolean foundKeyOrValue = false;
		for (int i = 0; i < configs.size(); i++) {
			configProperty = (ActivationConfigPropertyImpl) configs.get(i);
			String name = configProperty.getName();
			String value = configProperty.getValue();
			if (keyValue[0].equals(name) || keyValue[1].equals(value)) {
				foundKeyOrValue = true;
				break;
			}
		}
		if (configProperty != null && foundKeyOrValue == true) {
			configs.remove(configProperty);
			setProperty(ACTIVATION_CONFIG_PROPERTIES, configs);
		}
	}

	/**
	 * @return Returns the activationConfig.
	 */
	public ActivationConfig getActivationConfig() {
		return editActivationConfig;
	}

	/**
	 * @param activationConfig
	 *            The activationConfig to set.
	 */
	public void setActivationConfig(ActivationConfig activationConfig) {
		this.editActivationConfig = activationConfig;
	}
}