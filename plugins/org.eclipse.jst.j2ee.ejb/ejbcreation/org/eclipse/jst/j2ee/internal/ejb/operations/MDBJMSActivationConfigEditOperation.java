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
 * Created on Apr 28, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.SubscriptionDurabilityKind;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class MDBJMSActivationConfigEditOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public MDBJMSActivationConfigEditOperation(MDBJMSActivationConfigEditDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() throws CoreException {
		MDBJMSActivationConfigEditDataModel dataModel = (MDBJMSActivationConfigEditDataModel) getOperationDataModel();
		if (dataModel != null) {
			ActivationConfig config = (ActivationConfig) dataModel.getProperty(MDBJMSActivationConfigEditDataModel.ACTIVATION_CONFIG_PROPERTIES);
			List configProperties = config.getConfigProperties();
			for (int i = 0; i < configProperties.size(); i++) {
				ActivationConfigProperty property = (ActivationConfigProperty) configProperties.get(i);
				if (property != null) {
					if (property.getName().equals(MDBJMSActivationConfigEditDataModel.DESTINATION_TYPE_CONFIG_PROPERTY_NAME)) {
						setDestinationTypeValue(dataModel, property);
						createMessageDestinationTypeModelHelper(dataModel, property);
					} else if (property.getName().equals(MDBJMSActivationConfigEditDataModel.SUBSCRIPTION_DURABILITY_CONFIG_PROPERTY_NAME))
						setDurabilityValue(dataModel, property);
					else if (property.getName().equals(MDBJMSActivationConfigEditDataModel.ACKNOWLEDGE_MODE_CONFIG_PROPERTY_NAME))
						setAcknoledgeModeValue(dataModel, property);
					else if (property.getName().equals(MDBJMSActivationConfigEditDataModel.MESSAGE_SELECTOR_CONFIG_PROPERTY_NAME))
						setMessageSelectorValue(dataModel, property);
					createModelHelper(property);
				}
			}
		}
	}

	/**
	 * @param property
	 */
	private void createMessageDestinationTypeModelHelper(MDBJMSActivationConfigEditDataModel dataModel, ActivationConfigProperty property) {
		MessageDriven bean = (MessageDriven) property.eContainer().eContainer();
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(bean);
		helper.setFeature(EjbFactoryImpl.getPackage().getMessageDrivenDestination_Type());
		DestinationType type = (DestinationType) dataModel.getProperty(MDBJMSActivationConfigEditDataModel.DESTINATION_TYPE);
		helper.setValue(type.getName());
		modifier.addHelper(helper);

	}

	/**
	 * @param dataModel
	 * @param property
	 */
	private void setMessageSelectorValue(MDBJMSActivationConfigEditDataModel dataModel, ActivationConfigProperty property) {
		String messageSelector = (String) dataModel.getProperty(MDBJMSActivationConfigEditDataModel.MESSAGE_SELECTOR);
		property.setValue(messageSelector);
	}

	/**
	 * @param dataModel
	 * @param property
	 */
	private void setAcknoledgeModeValue(MDBJMSActivationConfigEditDataModel dataModel, ActivationConfigProperty property) {
		AcknowledgeMode ackMode = (AcknowledgeMode) dataModel.getProperty(MDBJMSActivationConfigEditDataModel.ACKNOWLEDGE_MODE);
		property.setValue(ackMode.toString());
	}

	/**
	 * @param dataModel
	 * @param property
	 */
	private void setDurabilityValue(MDBJMSActivationConfigEditDataModel dataModel, ActivationConfigProperty property) {
		SubscriptionDurabilityKind durability = (SubscriptionDurabilityKind) dataModel.getProperty(MDBJMSActivationConfigEditDataModel.SUBSCRIPTION_DURABILITY);
		property.setValue(durability.toString());

	}

	/**
	 * @param dataModel
	 * @param property
	 */
	private void setDestinationTypeValue(MDBJMSActivationConfigEditDataModel dataModel, ActivationConfigProperty property) {
		DestinationType destinationType = (DestinationType) dataModel.getProperty(MDBJMSActivationConfigEditDataModel.DESTINATION_TYPE);
		property.setValue(dataModel.getDestintionTypeQualifiedString(destinationType));
	}

	/**
	 * @param property
	 */
	private void createModelHelper(ActivationConfigProperty property) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(property);
		helper.setFeature(EjbFactoryImpl.getPackage().getActivationConfigProperty_Value());
		helper.setValue(property.getValue());
		modifier.addHelper(helper);
	}
}