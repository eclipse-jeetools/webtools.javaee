/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import java.util.List;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MessageDrivenDestination;
import org.eclipse.jst.j2ee.ejb.SubscriptionDurabilityKind;
import org.eclipse.jst.j2ee.internal.ejb.commands.CommandExecutionFailure;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


public class CreateMessageDrivenCommand extends MessageDrivenCommand {
	/**
	 * Constructor for CreateMessageDrivenCommand.
	 * 
	 * @param aName
	 * @param anEditModel
	 */
	public CreateMessageDrivenCommand(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
	}

	/*
	 * @see AbstractEJBRootCommand#getTaskName()
	 */
	public String getTaskName() {
		return getCreatingTaskName();
	}

	/*
	 * @see EnterpriseBeanCommand#isCreateCommand()
	 */
	public boolean isCreateCommand() {
		return true;
	}

	public boolean shouldGenerateJava() {
		return shouldGenerateJavaForModify();
	}

	/*
	 * @see EnterpriseBeanCommand#executeForMetadataGeneration()
	 */
	protected void executeForMetadataGeneration() throws CommandExecutionFailure {
		super.executeForMetadataGeneration();
		MessageDriven mdb = getMessageDrivenBean();
		mdb.setTransactionType(getTransactionType());
		if (!isContainerManaged && getAcknowledgeMode() != null)
			mdb.setAcknowledgeMode((AcknowledgeMode) getAcknowledgeMode().getInstance());
		if (getMessageSelector() != null && getMessageSelector().length() > 0)
			mdb.setMessageSelector(getMessageSelector());
		//Setup destination
		if (getDestinationType() != null || getSubscriptionDurability() != null) {
			if (mdb.getVersionID() == J2EEVersionConstants.EJB_2_1_ID)
				create14Destination();
			else
				createDestination();
		}
		if (getListenerPortName() != null && listenerPortName.length() > 0) { //$NON-NLS-1$
			//TODO should create a binding as part of post codegen commands
		}
		if (getMessagingType() != null && getMessagingType().length() > 0) {
			JavaHelpers helpers = JavaRefFactory.eINSTANCE.reflectType(getMessagingType(), mdb);
			if (helpers != null)
				mdb.setMessagingType((JavaClass) helpers);
		}
		if (mdb.getVersionID() == J2EEVersionConstants.EJB_2_1_ID) {
			createActivationConfigElements();

		}
	}

	/**
	 *  
	 */
	private void create14Destination() {
		EEnumLiteral destinationType = getDestinationType();
		DestinationType type = DestinationType.get(destinationType.getValue());
		String typeString = null;
		if (type != null) {
			switch (type.getValue()) {
				case DestinationType.QUEUE :
					typeString = "javax.jms.Queue"; //$NON-NLS-1$
					break;
				case DestinationType.TOPIC :
					typeString = "javax.jms.Topic"; //$NON-NLS-1$
					break;
			}
		}
		JavaHelpers helper = JavaRefFactory.eINSTANCE.reflectType(typeString, getMessageDrivenBean().eContainer());
		if (helper != null)
			getMessageDrivenBean().setMessageDestination(helper.getWrapper());
	}



	/**
	 *  
	 */
	protected void createActivationConfigElements() {
		ActivationConfig config = getEJBFactory().createActivationConfig();
		if (getActivationConfig() != null && !getActivationConfig().getConfigProperties().isEmpty()) {
			getMessageDrivenBean().setActivationConfig(config);
			List configProperties = getActivationConfig().getConfigProperties();
			for (int i = 0; i < configProperties.size(); i++) {
				ActivationConfigProperty modelProperty = (ActivationConfigProperty) configProperties.get(i);
				ActivationConfigProperty property = getEJBFactory().createActivationConfigProperty();
				property.setName(modelProperty.getName());
				property.setValue(modelProperty.getValue());
				getMessageDrivenBean().getActivationConfig().getConfigProperties().add(property);
			}
		}
	}

	protected void createDestination() throws CommandExecutionFailure {
		MessageDrivenDestination dest = getEJBFactory().createMessageDrivenDestination();
		if (getDestinationType() != null)
			dest.setType((DestinationType) getDestinationType().getInstance());
		if (getSubscriptionDurability() != null)
			dest.setSubscriptionDurability((SubscriptionDurabilityKind) getSubscriptionDurability().getInstance());
		getMessageDrivenBean().setDestination(dest);
	}
}