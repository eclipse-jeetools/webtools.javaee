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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.AbstractEnumerator;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.jdt.core.IType;
import org.eclipse.jem.internal.adapters.jdom.JDOMSearchHelper;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.DestinationType;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.SubscriptionDurabilityKind;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.ejb.impl.ActivationConfigPropertyImpl;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBCreationResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CreateMessageDrivenBeanDataModel extends CreateEnterpriseBeanDataModel {
	/**
	 * The acknowledge mode can be either AutoAcknowledge or DupsOkAcknowledge. The value may also
	 * be empyt. This property is only valid for a TRANSACTION_TYPE value of Bean. (Optional)
	 * 
	 * @link com.ibm.wtp.ejb.AcknowledgeMode
	 */
	public static final String ACKNOWLEDGE_MODE = "CreateMessageDrivenBeanDataModel.acknowledgeMode"; //$NON-NLS-1$
	/**
	 * This should be used to return a name for the ACKNOWLEDGE_MODE (Optional)
	 * 
	 * @link String
	 */
	public static final String ACKNOWLEDGE_MODE_NAME = "CreateMessageDrivenBeanDataModel.acknowledgeModeName"; //$NON-NLS-1$
	/**
	 * Defines the activation config properties for non JMS type message driven bean.
	 * 
	 * @since EJB 2.1
	 * @link String
	 */
	public static final String ACTIVATION_CONFIG_PROPERTIES = "CreateMessageDrivenBean21DataModel.activationConfig"; //$NON-NLS-1$
	/**
	 * The destination type either Topic or Queue. (Optional - defaults to Queue)
	 * 
	 * @link com.ibm.wtp.ejb.DestinationType
	 */
	public static final String DESTINATION_TYPE = "CreateMessageDrivenBeanDataModel.destinationType"; //$NON-NLS-1$
	/**
	 * Return a display name for the DESTINATION_TYPE (Optional)
	 * 
	 * @link String
	 */
	public static final String DESTINATION_TYPE_NAME = "CreateMessageDrivenBeanDataModel.destinationTypeName"; //$NON-NLS-1$
	/**
	 * The default JMS Listener type for JMS type Message Driven Bean
	 * 
	 * @since EJB 2.1
	 */
	public static final String JMS_INTERFACE_TYPE = "javax.jms.MessageListener"; //$NON-NLS-1$
	/**
	 * Defines the message listener type (JMS or Other) for the message driven bean.
	 * 
	 * @since EJB 2.1
	 * @link String
	 */
	public static final String LISTENER_TYPE = "CreateMessageDrivenBean21DataModel.listenerType"; //$NON-NLS-1$

	private static final String MDB_INTERFACE_TYPE = "javax.ejb.MessageDrivenBean"; //$NON-NLS-1$
	/**
	 * The message selector for the message driven bean. (Optional)
	 * 
	 * @link String
	 */
	public static final String MESSAGE_SELECTOR = "CreateMessageDrivenBeanDataModel.messageSelector"; //$NON-NLS-1$
	/**
	 * Return the Messaging Type class for Other types of MDB's
	 * 
	 * @since EJB 2.1
	 * @link String
	 */
	public static final String OTHER_LISTENER_TYPE = "CreateMessageDrivenBean21DataModel.otherListenerType"; //$NON-NLS-1$
	/**
	 * Returns the display name for other available messaging types for MDB's
	 * 
	 * @since EJB 2.1
	 * @link String
	 */
	public static final String OTHER_LISTENER_TYPE_NAME = "CreateMessageDrivenBean21DataModel.otherListenerTypeName"; //$NON-NLS-1$
	/**
	 * The subscription durability can be either Durable or NonDurable. You may also leave this
	 * value empty (unspecified). This value is only used if the DESTINATION_TYPE is Topic.
	 * (Optional)
	 * 
	 * @link com.ibm.wtp.ejb.SubscriptionDurabilityKind
	 */
	public static final String SUBSCRIPTION_DURABILITY = "CreateMessageDrivenBeanDataModel.subscriptionDurability"; //$NON-NLS-1$
	/**
	 * Return a display name for the SUBSCRIPTION_DURABILITY. (Optional)
	 * 
	 * @link String
	 */
	public static final String SUBSCRIPTION_DURABILITY_NAME = "CreateMessageDrivenBeanDataModel.subscriptionDurabilityName"; //$NON-NLS-1$
	/**
	 * The transaction type either container or bean. (Optional - defaults to container)
	 * 
	 * @link com.ibm.wtp.ejb.TransactionType
	 */
	public static final String TRANSACTION_TYPE = "CreateMessageDrivenBeanDataModel.transactionType"; //$NON-NLS-1$
	/**
	 * Return a display name for the TRANSACTION_TYPE. (Optional)
	 * 
	 * @link String
	 */
	public static final String TRANSACTION_TYPE_NAME = "CreateMessageDrivenBeanDataModel.transactionTypeName"; //$NON-NLS-1$
	/**
	 * @param value
	 */
	protected String[] otherListenerTypes;

	public void addActivationConfigKeyValue(String[] keyValue) {
		List configsProperties = (List) getProperty(ACTIVATION_CONFIG_PROPERTIES);
		ActivationConfigProperty configProperty = null;
		boolean foundKey = false;
		for (int i = 0; i < configsProperties.size(); i++) {
			configProperty = (ActivationConfigPropertyImpl) configsProperties.get(i);
			String name = configProperty.getName();
			if (keyValue[0].equals(name)) {
				foundKey = true;
				break;
			}
		}
		if (configProperty != null && foundKey == true) {
			if (configProperty.getValue() == null || configProperty.getValue().equals("") || configProperty.getValue() != keyValue[1]) //$NON-NLS-1$
				configProperty.setValue(keyValue[1]);
		} else {
			configProperty = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			configProperty.setName(keyValue[0]);
			configProperty.setValue(keyValue[1]);
			configsProperties.add(configProperty);
		}
		setProperty(ACTIVATION_CONFIG_PROPERTIES, configsProperties);
	}

	private Object[] addFirstEmptySlot(List values) {
		Object[] result = new Object[values.size() + 1];
		for (int i = 0; i < values.size(); i++) {
			result[i + 1] = values.get(i);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#basicIsEnabled(java.lang.String)
	 */
	protected Boolean basicIsEnabled(String propertyName) {
		if (propertyName.equals(ACKNOWLEDGE_MODE) || propertyName.equals(ACKNOWLEDGE_MODE_NAME))
			return isAcknowledgeModeEnabled();
		if (propertyName.equals(SUBSCRIPTION_DURABILITY) || propertyName.equals(SUBSCRIPTION_DURABILITY_NAME))
			return isSubscriptionDurabilityEnabled();
		return super.basicIsEnabled(propertyName);
	}

	private ActivationConfigProperty createAcknowledgementModeConfigProperty() {
		AcknowledgeMode mode = (AcknowledgeMode) getProperty(CreateMessageDrivenBeanDataModel.ACKNOWLEDGE_MODE);
		if (mode != null) {
			ActivationConfigProperty property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			property.setName("acknowledgeMode"); //$NON-NLS-1$
			EEnumLiteral literal = getEnumLiteral(mode, EjbPackage.eINSTANCE.getAcknowledgeMode());
			property.setValue(literal.getName());
			return property;
		}
		return null;
	}

	private ActivationConfigProperty createDestintionTypeConfigPropery() {
		DestinationType type = (DestinationType) getProperty(CreateMessageDrivenBeanDataModel.DESTINATION_TYPE);
		if (type != null) {
			ActivationConfigProperty property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			property.setName("destinationType"); //$NON-NLS-1$
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
			property.setValue(typeString);
			return property;
		}
		return null;
	}



	private EEnumLiteral getEnumLiteral(AbstractEnumerator enumValue, EEnum eenum) {
		if (enumValue != null)
			return eenum.getEEnumLiteral(enumValue.getValue());
		return null;
	}

	public ActivationConfig createJMSActivationConfigModel() {
		ActivationConfig activationConfig = EjbFactoryImpl.getActiveFactory().createActivationConfig();
		ActivationConfigProperty property = createDestintionTypeConfigPropery();
		if (property != null)
			((List) activationConfig.getConfigProperties()).add(property);
		property = createDurabilityConfigProperty();
		if (property != null)
			((List) activationConfig.getConfigProperties()).add(property);
		property = createAcknowledgementModeConfigProperty();
		if (property != null)
			((List) activationConfig.getConfigProperties()).add(property);
		property = createMessageSelectorConfigProperty();
		if (property != null)
			((List) activationConfig.getConfigProperties()).add(property);
		return activationConfig;
	}

	/**
	 * @return
	 */
	private ActivationConfigProperty createDurabilityConfigProperty() {
		DestinationType destinationType = (DestinationType) getProperty(CreateMessageDrivenBeanDataModel.DESTINATION_TYPE);
		if (destinationType.equals(DestinationType.TOPIC_LITERAL)) {
			ActivationConfigProperty property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			property.setName("subscriptionDurability"); //$NON-NLS-1$
			SubscriptionDurabilityKind durability = (SubscriptionDurabilityKind) getProperty(SUBSCRIPTION_DURABILITY);
			if (durability == null)
				durability = SubscriptionDurabilityKind.NON_DURABLE_LITERAL;
			EEnumLiteral lit = getEnumLiteral(durability, EjbPackage.eINSTANCE.getSubscriptionDurabilityKind());
			property.setValue(lit.getName());
			return property;
		}
		return null;
	}

	private ActivationConfigProperty createMessageSelectorConfigProperty() {
		String messageSelector = getStringProperty(MESSAGE_SELECTOR);
		if (messageSelector != null && messageSelector.length() > 0) {
			ActivationConfigProperty property = EjbFactoryImpl.getActiveFactory().createActivationConfigProperty();
			property.setName("messageSelector"); //$NON-NLS-1$
			property.setValue(messageSelector);
			return property;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doGetValidPropertyValues(java.lang.String)
	 */
	protected Object[] doGetValidPropertyValues(String propertyName) {
		if (propertyName.equals(TRANSACTION_TYPE))
			return getValidTransactionTypes();
		if (propertyName.equals(ACKNOWLEDGE_MODE))
			return getValidAcknowledgeModes();
		if (propertyName.equals(DESTINATION_TYPE))
			return getValidDestinationTypes();
		if (propertyName.equals(SUBSCRIPTION_DURABILITY))
			return getValidSubscriptionDurabilities();
		if (propertyName.equals(TRANSACTION_TYPE_NAME))
			return getValidEnumNames(TRANSACTION_TYPE);
		if (propertyName.equals(ACKNOWLEDGE_MODE_NAME))
			return getValidEnumNames(ACKNOWLEDGE_MODE);
		if (propertyName.equals(DESTINATION_TYPE_NAME))
			return getValidEnumNames(DESTINATION_TYPE);
		if (propertyName.equals(SUBSCRIPTION_DURABILITY_NAME))
			return getValidEnumNames(SUBSCRIPTION_DURABILITY);
		if (propertyName.equals(OTHER_LISTENER_TYPE_NAME))
			return getValidOtherListenerTypes();
		return super.doGetValidPropertyValues(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(ACKNOWLEDGE_MODE_NAME))
			return setAcknowledgMode((String) propertyValue);
		if (propertyName.equals(DESTINATION_TYPE_NAME))
			return setDestinationType((String) propertyValue);
		if (propertyName.equals(SUBSCRIPTION_DURABILITY_NAME))
			return setSubscriptionDurability((String) propertyValue);
		if (propertyName.equals(TRANSACTION_TYPE_NAME))
			return setTransactionType((String) propertyValue);
		if (propertyName.equals(OTHER_LISTENER_TYPE_NAME))
			return setOtherMessageType((String) propertyValue);
		return super.doSetProperty(propertyName, propertyValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(OTHER_LISTENER_TYPE) && (getBooleanProperty(LISTENER_TYPE) == false)) {
			return validateOtherListenerType();
		}
		return super.doValidateProperty(propertyName);
	}

	/**
	 *  
	 */
	public IStatus validateOtherListenerType() {
		String interfaceName = getStringProperty(OTHER_LISTENER_TYPE);
		if (interfaceName == null || interfaceName.length() == 0) {
			return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("NON_INTERFACE_NAME_CANNOT_BE_NULL_UI_"), null); //$NON-NLS-1$
		} else if (interfaceName != null && interfaceName.length() > 0) {
			IType type = JDOMSearchHelper.findType(interfaceName, false, getJavaProject(), null);
			if (type == null) {
				return EjbPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CANNOT_RESOLVE_INTERFACE_UI_"), null); //$NON-NLS-1$
			}
		}
		notifyValidValuesChange(CreateMessageDrivenBeanDataModel.OTHER_LISTENER_TYPE);
		return OK_STATUS;
	}

	public List getActivationConfigProperty() {
		return (List) getProperty(ACTIVATION_CONFIG_PROPERTIES);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#getBeanClassEJBInterfaceName()
	 */
	protected String getBeanClassEJBInterfaceName() {
		return MDB_INTERFACE_TYPE;
	}

	public ArrayList getDefaultActivationConfig(String property) {
		return new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new CreateMessageDrivenBeanOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(LISTENER_TYPE))
			return Boolean.TRUE;
		if (propertyName.equals(TRANSACTION_TYPE))
			return TransactionType.CONTAINER_LITERAL;
		if (propertyName.equals(DESTINATION_TYPE))
			return DestinationType.QUEUE_LITERAL;
		if (propertyName.equals(TRANSACTION_TYPE_NAME))
			return getEnumeratorName(TRANSACTION_TYPE);
		if (propertyName.equals(ACKNOWLEDGE_MODE_NAME))
			return getEnumeratorName(ACKNOWLEDGE_MODE);
		if (propertyName.equals(DESTINATION_TYPE_NAME))
			return getEnumeratorName(DESTINATION_TYPE);
		if (propertyName.equals(SUBSCRIPTION_DURABILITY_NAME))
			return getEnumeratorName(SUBSCRIPTION_DURABILITY);
		if (propertyName.equals(ACTIVATION_CONFIG_PROPERTIES))
			return getDefaultActivationConfig(ACTIVATION_CONFIG_PROPERTIES);
		if (propertyName.equals(OTHER_LISTENER_TYPE_NAME))
			return getProperty(OTHER_LISTENER_TYPE);
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#getEJBType()
	 */
	public int getEJBType() {
		return EJB_TYPE_MDB;
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
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#getMinimumSupportedProjectVersion()
	 */
	public int getMinimumSupportedProjectVersion() {
		return J2EEVersionConstants.EJB_2_0_ID;
	}

	public ActivationConfig getNonJMSActivationConfigModel() {
		ActivationConfig activationConfig = EjbFactoryImpl.getActiveFactory().createActivationConfig();
		List configs = (List) getProperty(ACTIVATION_CONFIG_PROPERTIES);
		if (configs != null && !configs.isEmpty()) {
			((List) activationConfig.getConfigProperties()).addAll(configs);
		}
		return activationConfig;
	}

	/**
	 * @return
	 */
	private Object[] getValidAcknowledgeModes() {
		if (getProperty(TRANSACTION_TYPE) == TransactionType.CONTAINER_LITERAL)
			return new Object[0];
		return addFirstEmptySlot(AcknowledgeMode.VALUES);
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

	public String[] getValidOtherListenerTypes() {
		if (otherListenerTypes == null) {
			otherListenerTypes = new String[2];
			otherListenerTypes[0] = "javax.xml.messaging.OnewayListener"; //$NON-NLS-1$
			otherListenerTypes[1] = "javax.xml.messaging.ReqRespListener"; //$NON-NLS-1$
		}
		return otherListenerTypes;
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
	private Object[] getValidTransactionTypes() {
		return TransactionType.VALUES.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#init()
	 */
	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ejb.operations.CreateEnterpriseBeanDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(ACKNOWLEDGE_MODE);
		addValidBaseProperty(ACKNOWLEDGE_MODE_NAME);
		addValidBaseProperty(DESTINATION_TYPE);
		addValidBaseProperty(DESTINATION_TYPE_NAME);
		addValidBaseProperty(MESSAGE_SELECTOR);
		addValidBaseProperty(SUBSCRIPTION_DURABILITY);
		addValidBaseProperty(SUBSCRIPTION_DURABILITY_NAME);
		addValidBaseProperty(TRANSACTION_TYPE);
		addValidBaseProperty(TRANSACTION_TYPE_NAME);
		addValidBaseProperty(LISTENER_TYPE);
		addValidBaseProperty(OTHER_LISTENER_TYPE);
		addValidBaseProperty(OTHER_LISTENER_TYPE_NAME);
		addValidBaseProperty(ACTIVATION_CONFIG_PROPERTIES);
	}

	/**
	 * Return true if the transaction type is Bean.
	 * 
	 * @return
	 */
	private Boolean isAcknowledgeModeEnabled() {
		TransactionType type = (TransactionType) getProperty(TRANSACTION_TYPE);
		return getBoolean(type == TransactionType.BEAN_LITERAL);
	}

	public boolean isJ2EE14Project() {
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(getTargetProject());
		return nature.getJ2EEVersion() == J2EEVersionConstants.J2EE_1_4_ID;
	}

	/**
	 * Return true if the destination type is Topic.
	 * 
	 * @return
	 */
	private Boolean isSubscriptionDurabilityEnabled() {
		DestinationType type = (DestinationType) getProperty(DESTINATION_TYPE);
		return getBoolean(type == DestinationType.TOPIC_LITERAL);
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
	 * @param string
	 * @return
	 */
	private boolean setAcknowledgMode(String string) {
		Object value = AcknowledgeMode.get(string);
		setProperty(ACKNOWLEDGE_MODE, value);
		return true;
	}

	/**
	 * @param string
	 * @return
	 */
	private boolean setDestinationType(String string) {
		Object value = DestinationType.get(string);
		setProperty(DESTINATION_TYPE, value);
		notifyValidValuesChange(SUBSCRIPTION_DURABILITY);
		return true;
	}

	/**
	 * @param string
	 * @return
	 */
	private boolean setOtherMessageType(String string) {
		setProperty(OTHER_LISTENER_TYPE, string);
		return true;
	}

	/**
	 * @param string
	 * @return
	 */
	private boolean setSubscriptionDurability(String string) {
		Object value = SubscriptionDurabilityKind.get(string);
		setProperty(SUBSCRIPTION_DURABILITY, value);
		return true;
	}

	/**
	 * @param string
	 * @return
	 */
	private boolean setTransactionType(String string) {
		Object value = TransactionType.get(string);
		setProperty(TRANSACTION_TYPE, value);
		notifyValidValuesChange(ACKNOWLEDGE_MODE);
		return true;
	}

}