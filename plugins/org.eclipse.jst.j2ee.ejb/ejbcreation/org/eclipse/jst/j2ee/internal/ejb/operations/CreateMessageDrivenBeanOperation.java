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
 * Created on Jan 26, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.emf.common.util.AbstractEnumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.SubscriptionDurabilityKind;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateMessageDrivenCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.MessageDrivenCommand;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CreateMessageDrivenBeanOperation extends CreateEnterpriseBeanOperation {
	/**
	 * @param dataModel
	 */
	public CreateMessageDrivenBeanOperation(CreateMessageDrivenBeanDataModel dataModel) {
		super(dataModel);
	}

	/**
	 *  
	 */
	public CreateMessageDrivenBeanOperation() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanOperatoin#createRootCommand(java.lang.String)
	 */
	protected EnterpriseBeanCommand createRootCommand(String beanName) {
		return new CreateMessageDrivenCommand(beanName, (EJBEditModel) editModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanOperation#initializeRootCommand(org.eclipse.jst.j2ee.internal.internal.commands.EnterpriseBeanCommand)
	 */
	protected void initializeRootCommand(EnterpriseBeanCommand ejbCommand) {
		super.initializeRootCommand(ejbCommand);
		CreateMessageDrivenBeanDataModel mdbModel = (CreateMessageDrivenBeanDataModel) operationDataModel;
		MessageDrivenCommand mdbCommand = (MessageDrivenCommand) ejbCommand;
		initializeTransactionTypeAndAcknowledgeMode(mdbCommand, mdbModel);
		if (!mdbModel.isJ2EE14Project()) {
			initializeDestination(mdbCommand, mdbModel);
			initializeMessageSelector(mdbCommand, mdbModel);
		} else {
			boolean isJMS = mdbModel.getBooleanProperty(CreateMessageDrivenBeanDataModel.LISTENER_TYPE);
			initialize14Destination(mdbCommand, mdbModel);
			if (isJMS) {
				initializeJMSMessagingTypeName(mdbCommand, mdbModel);
				mdbCommand.setActivationConfig(mdbModel.createJMSActivationConfigModel());
			} else {
				initializeNonJMSListenerTypeName(mdbCommand, mdbModel);
				mdbCommand.setActivationConfig(mdbModel.getNonJMSActivationConfigModel());
			}
		}
	}

	/**
	 * @param mdbCommand
	 * @param mdbModel
	 */
	private void initialize14Destination(MessageDrivenCommand mdbCommand, CreateMessageDrivenBeanDataModel mdbModel) {
		DestinationType type = (DestinationType) mdbModel.getProperty(CreateMessageDrivenBeanDataModel.DESTINATION_TYPE);
		EEnumLiteral lit = getEnumLiteral(type, EjbPackage.eINSTANCE.getDestinationType());
		mdbCommand.setDestinationType(lit);
	}

	protected void initializeJMSMessagingTypeName(MessageDrivenCommand mdbCommand, CreateMessageDrivenBeanDataModel mdbModel) {
		mdbCommand.setMessagingType(CreateMessageDrivenBeanDataModel.JMS_INTERFACE_TYPE);
	}

	protected void initializeNonJMSListenerTypeName(MessageDrivenCommand mdbCommand, CreateMessageDrivenBeanDataModel mdbModel) {
		String messageType = mdbModel.getStringProperty(CreateMessageDrivenBeanDataModel.OTHER_LISTENER_TYPE);
		mdbCommand.setMessagingType(messageType);
	}

	/**
	 * @param mdbCommand
	 * @param mdbModel
	 */
	private void initializeMessageSelector(MessageDrivenCommand mdbCommand, CreateMessageDrivenBeanDataModel mdbModel) {
		String selector = (String) mdbModel.getProperty(CreateMessageDrivenBeanDataModel.MESSAGE_SELECTOR);
		if (selector != null)
			mdbCommand.setMessageSelector(selector);

	}

	/**
	 * @param mdbCommand
	 * @param mdbModel
	 */
	private void initializeTransactionTypeAndAcknowledgeMode(MessageDrivenCommand mdbCommand, CreateMessageDrivenBeanDataModel mdbModel) {
		TransactionType type = (TransactionType) mdbModel.getProperty(CreateMessageDrivenBeanDataModel.TRANSACTION_TYPE);
		if (type != null) {
			boolean isContainer = type == TransactionType.CONTAINER_LITERAL;
			mdbCommand.setIsContainerManaged(isContainer);
			if (!mdbModel.isJ2EE14Project() && !isContainer) {
				AcknowledgeMode mode = (AcknowledgeMode) mdbModel.getProperty(CreateMessageDrivenBeanDataModel.ACKNOWLEDGE_MODE);
				EEnumLiteral literal = getEnumLiteral(mode, EjbPackage.eINSTANCE.getAcknowledgeMode());
				mdbCommand.setAcknowledgeMode(literal);
			}
		}
	}

	/**
	 * @param mdbCommand
	 * @param mdbModel
	 */
	private void initializeDestination(MessageDrivenCommand mdbCommand, CreateMessageDrivenBeanDataModel mdbModel) {
		DestinationType type = (DestinationType) mdbModel.getProperty(CreateMessageDrivenBeanDataModel.DESTINATION_TYPE);
		EEnumLiteral lit = getEnumLiteral(type, EjbPackage.eINSTANCE.getDestinationType());
		mdbCommand.setDestinationType(lit);
		if (type == DestinationType.TOPIC_LITERAL) {
			SubscriptionDurabilityKind dur = (SubscriptionDurabilityKind) mdbModel.getProperty(CreateMessageDrivenBeanDataModel.SUBSCRIPTION_DURABILITY);
			lit = getEnumLiteral(dur, EjbPackage.eINSTANCE.getSubscriptionDurabilityKind());
			mdbCommand.setSubscriptionDurability(lit);
		}
	}



	private EEnumLiteral getEnumLiteral(AbstractEnumerator enumValue, EEnum eenum) {
		if (enumValue != null)
			return eenum.getEEnumLiteral(enumValue.getValue());
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanOperation#createTemplateModel()
	 */
	protected CreateEnterpriseBeanTemplateModel createTemplateModel() {
		return new CreateMessageDrivenBeanTemplateModel((CreateMessageDrivenBeanDataModel) operationDataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanOperation#getTemplateFileName()
	 */
	protected String getTemplateFileName() {
		return "mdbXDoclet.javajet"; //$NON-NLS-1$
	}
}