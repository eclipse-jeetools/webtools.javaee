/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCodegenCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


public class MessageDrivenCommand extends EnterpriseBeanCommand {
	protected boolean isContainerManaged;
	protected EEnumLiteral acknowledgeModeLiteral;
	protected EEnumLiteral destinationTypeLiteral;
	protected EEnumLiteral subscriptionDurabilityLiteral;
	protected String messageSelector;
	protected String listenerPortName;
	protected ActivationConfig activationConfig;
	protected String messagingType;


	/**
	 * Constructor for MessageDrivenBeanCommand.
	 * 
	 * @param anEjb
	 * @param anEditModel
	 */
	public MessageDrivenCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/**
	 * Constructor for MessageDrivenBeanCommand.
	 * 
	 * @param aName
	 * @param anEditModel
	 */
	public MessageDrivenCommand(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
	}

	/*
	 * @see EnterpriseBeanCommand#createCodegenCommand()
	 */
	protected EnterpriseBeanCodegenCommand createCodegenCommand() {
		return new MessageDrivenCodegenCommand(getMessageDrivenBean());
	}

	/*
	 * @see EnterpriseBeanCommand#createEJB()
	 */
	protected EnterpriseBean createEJB() {
		EnterpriseBean bean = getEJBFactory().createMessageDriven();
		bean.setName(getName());
		getEditModel().getEjbXmiResource().setID(bean, getName());
		return bean;
	}

	/*
	 * @see AbstractEJBRootCommand#getTaskName()
	 */
	public String getTaskName() {
		return null;
	}

	protected MessageDriven getMessageDrivenBean() {
		return (MessageDriven) getEjb();
	}

	public void setIsContainerManaged(boolean aBool) {
		isContainerManaged = aBool;
	}

	protected TransactionType getTransactionType() {
		if (isContainerManaged)
			return TransactionType.CONTAINER_LITERAL;
		return TransactionType.BEAN_LITERAL;
	}

	/**
	 * Gets the acknowledgeModeLiteral.
	 * 
	 * @return Returns a EEnumLiteral
	 */
	public EEnumLiteral getAcknowledgeMode() {
		return acknowledgeModeLiteral;
	}

	/**
	 * Sets the acknowledgeModeLiteral.
	 * 
	 * @param acknowledgeModeLiteral
	 *            The acknowledgeModeLiteral to set
	 */
	public void setAcknowledgeMode(EEnumLiteral acknowledgeModeLiteral) {
		this.acknowledgeModeLiteral = acknowledgeModeLiteral;
	}

	/**
	 * Gets the destinationTypeLiteral.
	 * 
	 * @return Returns a EEnumLiteral
	 */
	public EEnumLiteral getDestinationType() {
		return destinationTypeLiteral;
	}

	/**
	 * Sets the destinationTypeLiteral.
	 * 
	 * @param destinationTypeLiteral
	 *            The destinationTypeLiteral to set
	 */
	public void setDestinationType(EEnumLiteral destinationTypeLiteral) {
		this.destinationTypeLiteral = destinationTypeLiteral;
	}

	/**
	 * Gets the messageSelector.
	 * 
	 * @return Returns a String
	 */
	public String getMessageSelector() {
		return messageSelector;
	}

	/**
	 * Sets the messageSelector.
	 * 
	 * @param messageSelector
	 *            The messageSelector to set
	 */
	public void setMessageSelector(String messageSelector) {
		this.messageSelector = messageSelector;
	}

	/**
	 * Gets the subscriptionDurabilityLiteral.
	 * 
	 * @return Returns a EEnumLiteral
	 */
	public EEnumLiteral getSubscriptionDurability() {
		return subscriptionDurabilityLiteral;
	}

	/**
	 * Sets the subscriptionDurabilityLiteral.
	 * 
	 * @param subscriptionDurabilityLiteral
	 *            The subscriptionDurabilityLiteral to set
	 */
	public void setSubscriptionDurability(EEnumLiteral subscriptionDurabilityLiteral) {
		this.subscriptionDurabilityLiteral = subscriptionDurabilityLiteral;
	}

	/**
	 * Gets the listenerPortName.
	 * 
	 * @return Returns a String
	 */
	public String getListenerPortName() {
		return listenerPortName;
	}

	/**
	 * Sets the listenerPortName.
	 * 
	 * @param listenerPortName
	 *            The listenerPortName to set
	 */
	public void setListenerPortName(String listenerPortName) {
		this.listenerPortName = listenerPortName;
	}

	/**
	 * @return Returns the activationConfig.
	 */
	public ActivationConfig getActivationConfig() {
		return activationConfig;
	}

	/**
	 * @param activationConfig
	 *            The activationConfig to set.
	 */
	public void setActivationConfig(ActivationConfig activationConfig) {
		this.activationConfig = activationConfig;
	}

	/**
	 * @return Returns the messagingType.
	 */
	public String getMessagingType() {
		return messagingType;
	}

	/**
	 * @param messagingType
	 *            The messagingType to set.
	 */
	public void setMessagingType(String messagingType) {
		this.messagingType = messagingType;
	}
}