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

import org.eclipse.emf.common.util.AbstractEnumerator;
import org.eclipse.jst.j2ee.common.operations.J2EEModelModifierOperationDataModel;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.SubscriptionDurabilityKind;
import org.eclipse.wst.common.framework.operation.WTPOperation;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class MDBJMSActivationConfigEditDataModel extends J2EEModelModifierOperationDataModel {
	public static final String ACKNOWLEDGE_MODE_CONFIG_PROPERTY_NAME = "acknowledgeMode"; //$NON-NLS-1$
	public static final String DESTINATION_TYPE_CONFIG_PROPERTY_NAME = "destinationType"; //$NON-NLS-1$
	public static final String SUBSCRIPTION_DURABILITY_CONFIG_PROPERTY_NAME = "subscriptionDurability"; //$NON-NLS-1$
	public static final String MESSAGE_SELECTOR_CONFIG_PROPERTY_NAME = "messageSelector"; //$NON-NLS-1$
	/**
	 * The acknowledge mode can be either AutoAcknowledge or DupsOkAcknowledge. The value may also
	 * be empyt. This property is only valid for a TRANSACTION_TYPE value of Bean. (Optional)
	 * 
	 * @link com.ibm.wtp.ejb.AcknowledgeMode
	 */
	public static final String ACKNOWLEDGE_MODE = "MDBJMSActivationConfigEditDataModel.acknowledgeMode"; //$NON-NLS-1$
	/**
	 * This should be used to return a name for the ACKNOWLEDGE_MODE (Optional)
	 * 
	 * @link String
	 */
	public static final String ACKNOWLEDGE_MODE_NAME = "MDBJMSActivationConfigEditDataModel.acknowledgeModeName"; //$NON-NLS-1$
	/**
	 * The subscription durability can be either Durable or NonDurable. You may also leave this
	 * value empty (unspecified). This value is only used if the DESTINATION_TYPE is Topic.
	 * (Optional)
	 * 
	 * @link com.ibm.wtp.ejb.SubscriptionDurabilityKind
	 */
	public static final String SUBSCRIPTION_DURABILITY = "MDBJMSActivationConfigEditDataModel.subscriptionDurability"; //$NON-NLS-1$
	/**
	 * Return a display name for the SUBSCRIPTION_DURABILITY. (Optional)
	 * 
	 * @link String
	 */
	public static final String SUBSCRIPTION_DURABILITY_NAME = "MDBJMSActivationConfigEditDataModel.subscriptionDurabilityName"; //$NON-NLS-1$
	/**
	 * The destination type either Topic or Queue. (Optional - defaults to Queue)
	 * 
	 * @link com.ibm.wtp.ejb.DestinationType
	 */
	public static final String DESTINATION_TYPE = "MDBJMSActivationConfigEditDataModel.destinationType"; //$NON-NLS-1$
	/**
	 * Return a display name for the DESTINATION_TYPE (Optional)
	 * 
	 * @link String
	 */
	public static final String DESTINATION_TYPE_NAME = "MDBJMSActivationConfigEditDataModel.destinationTypeName"; //$NON-NLS-1$
	/**
	 * The message selector for the message driven bean. (Optional)
	 * 
	 * @link String
	 */
	public static final String MESSAGE_SELECTOR = "MDBJMSActivationConfigEditDataModel.messageSelector"; //$NON-NLS-1$
	/**
	 * Defines the activation config properties for non JMS type message driven bean.
	 * 
	 * @since EJB 2.1
	 * @link String
	 */
	public static final String ACTIVATION_CONFIG_PROPERTIES = "MDBJMSActivationConfigEditDataModel.activationConfig"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new MDBJMSActivationConfigEditOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(DESTINATION_TYPE))
			return DestinationType.QUEUE_LITERAL;
		if (propertyName.equals(ACKNOWLEDGE_MODE_NAME))
			return getEnumeratorName(ACKNOWLEDGE_MODE);
		if (propertyName.equals(DESTINATION_TYPE_NAME))
			return getEnumeratorName(DESTINATION_TYPE);
		if (propertyName.equals(SUBSCRIPTION_DURABILITY_NAME))
			return getEnumeratorName(SUBSCRIPTION_DURABILITY);
		return super.getDefaultProperty(propertyName);
	}

	private String getEnumeratorName(String property) {
		AbstractEnumerator eenum = (AbstractEnumerator) getProperty(property);
		if (eenum != null)
			return eenum.getName();
		return ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(ACKNOWLEDGE_MODE);
		addValidBaseProperty(ACKNOWLEDGE_MODE_NAME);
		addValidBaseProperty(DESTINATION_TYPE);
		addValidBaseProperty(DESTINATION_TYPE_NAME);
		addValidBaseProperty(MESSAGE_SELECTOR);
		addValidBaseProperty(SUBSCRIPTION_DURABILITY);
		addValidBaseProperty(SUBSCRIPTION_DURABILITY_NAME);
		addValidBaseProperty(ACTIVATION_CONFIG_PROPERTIES);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.ejb.operations.CreateEnterpriseBeanDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(ACKNOWLEDGE_MODE_NAME))
			return setAcknowledgMode((String) propertyValue);
		if (propertyName.equals(DESTINATION_TYPE_NAME))
			return setDestinationType((String) propertyValue);
		if (propertyName.equals(SUBSCRIPTION_DURABILITY_NAME))
			return setSubscriptionDurability((String) propertyValue);
		return super.doSetProperty(propertyName, propertyValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(ACKNOWLEDGE_MODE))
			return getValidAcknowledgeModes();
		if (propertyName.equals(DESTINATION_TYPE))
			return getValidDestinationTypes();
		if (propertyName.equals(SUBSCRIPTION_DURABILITY))
			return getValidSubscriptionDurabilities();
		if (propertyName.equals(ACKNOWLEDGE_MODE_NAME))
			return getValidEnumNames(ACKNOWLEDGE_MODE);
		if (propertyName.equals(DESTINATION_TYPE_NAME))
			return getValidEnumNames(DESTINATION_TYPE);
		if (propertyName.equals(SUBSCRIPTION_DURABILITY_NAME))
			return getValidEnumNames(SUBSCRIPTION_DURABILITY);
		return super.doGetValidPropertyValues(propertyName);
	}

	/**
	 * @param string
	 * @return
	 */
	public boolean setSubscriptionDurability(String string) {
		Object value = SubscriptionDurabilityKind.get(string);
		setProperty(SUBSCRIPTION_DURABILITY, value);
		return true;
	}

	/**
	 * @return
	 */
	private Object[] getValidSubscriptionDurabilities() {
		if (getProperty(DESTINATION_TYPE) == DestinationType.QUEUE_LITERAL)
			return new Object[0];
		return addFirstEmptySlot(SubscriptionDurabilityKind.VALUES);
	}

	/**
	 * @return
	 */
	private Object[] getValidAcknowledgeModes() {
		return addFirstEmptySlot(AcknowledgeMode.VALUES);
	}

	private Object[] addFirstEmptySlot(List values) {
		Object[] result = new Object[values.size() + 1];
		for (int i = 0; i < values.size(); i++) {
			result[i + 1] = values.get(i);
		}
		return result;
	}

	/**
	 * @param string
	 * @return
	 */
	public boolean setAcknowledgMode(String string) {
		Object value = AcknowledgeMode.get(string);
		setProperty(ACKNOWLEDGE_MODE, value);
		return true;
	}

	/**
	 * @param string
	 * @return
	 */
	public boolean setDestinationType(String string) {
		String destinationType = getDestintionType(string);
		Object value = DestinationType.get(destinationType);
		setProperty(DESTINATION_TYPE, value);
		notifyValidValuesChange(SUBSCRIPTION_DURABILITY);
		return true;
	}

	protected String getDestintionTypeQualifiedString(DestinationType type) {
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
		return typeString;
	}

	protected String getDestintionType(String value) {
		if (value.equals("javax.jms.Queue") || value.equals("Queue")) //$NON-NLS-1$ //$NON-NLS-2$
			return DestinationType.QUEUE_LITERAL.toString();
		else if (value.equals("javax.jms.Topic") || value.equals("Topic")) //$NON-NLS-1$ //$NON-NLS-2$
			return DestinationType.TOPIC_LITERAL.toString();
		return null;
	}

	/**
	 * @param string
	 * @return
	 */
	public boolean setMessageSelector(String string) {
		setProperty(MESSAGE_SELECTOR, string);
		return true;
	}

	/**
	 * @return
	 */
	private Object[] getValidDestinationTypes() {
		return DestinationType.VALUES.toArray();
	}

	/**
	 * @param enumProperty
	 * @return
	 */
	private Object[] getValidEnumNames(String enumProperty) {
		Object[] enums = doGetValidPropertyValues(enumProperty);
		if (enums != null) {
			if (enums.length == 0)
				return new String[0];
			String[] names = new String[enums.length];
			for (int i = 0; i < enums.length; i++) {
				if (enums[i] == null)
					names[i] = ""; //$NON-NLS-1$
				else
					names[i] = enums[i].toString();
			}
			return names;
		}
		return new String[0];
	}
}