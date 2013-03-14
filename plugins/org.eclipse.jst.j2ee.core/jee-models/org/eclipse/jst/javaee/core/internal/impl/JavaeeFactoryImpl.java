/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.jst.javaee.core.*;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class JavaeeFactoryImpl extends EFactoryImpl implements JavaeeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static JavaeeFactory init() {
		try {
			JavaeeFactory theJavaeeFactory = (JavaeeFactory)EPackage.Registry.INSTANCE.getEFactory("http://xmlns.jcp.org/xml/ns/javaee"); //$NON-NLS-1$ 
			if (theJavaeeFactory != null) {
				return theJavaeeFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new JavaeeFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaeeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case JavaeePackage.ADDRESSING_TYPE: return (EObject)createAddressingType();
			case JavaeePackage.ADMINISTERED_OBJECT_TYPE: return (EObject)createAdministeredObjectType();
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE: return (EObject)createConnectionFactoryResourceType();
			case JavaeePackage.DATA_SOURCE_TYPE: return (EObject)createDataSourceType();
			case JavaeePackage.DESCRIPTION: return (EObject)createDescription();
			case JavaeePackage.DISPLAY_NAME: return (EObject)createDisplayName();
			case JavaeePackage.EJB_LOCAL_REF: return (EObject)createEjbLocalRef();
			case JavaeePackage.EJB_REF: return (EObject)createEjbRef();
			case JavaeePackage.EMPTY_TYPE: return (EObject)createEmptyType();
			case JavaeePackage.ENV_ENTRY: return (EObject)createEnvEntry();
			case JavaeePackage.ICON: return (EObject)createIcon();
			case JavaeePackage.INJECTION_TARGET: return (EObject)createInjectionTarget();
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE: return (EObject)createJmsConnectionFactoryType();
			case JavaeePackage.JMS_DESTINATION_TYPE: return (EObject)createJmsDestinationType();
			case JavaeePackage.LIFECYCLE_CALLBACK: return (EObject)createLifecycleCallback();
			case JavaeePackage.LISTENER: return (EObject)createListener();
			case JavaeePackage.MAIL_SESSION_TYPE: return (EObject)createMailSessionType();
			case JavaeePackage.MESSAGE_DESTINATION: return (EObject)createMessageDestination();
			case JavaeePackage.MESSAGE_DESTINATION_REF: return (EObject)createMessageDestinationRef();
			case JavaeePackage.PARAM_VALUE: return (EObject)createParamValue();
			case JavaeePackage.PERSISTENCE_CONTEXT_REF: return (EObject)createPersistenceContextRef();
			case JavaeePackage.PERSISTENCE_UNIT_REF: return (EObject)createPersistenceUnitRef();
			case JavaeePackage.PORT_COMPONENT_REF: return (EObject)createPortComponentRef();
			case JavaeePackage.PROPERTY_TYPE: return (EObject)createPropertyType();
			case JavaeePackage.RESOURCE_ENV_REF: return (EObject)createResourceEnvRef();
			case JavaeePackage.RESOURCE_REF: return (EObject)createResourceRef();
			case JavaeePackage.RESPECT_BINDING_TYPE: return (EObject)createRespectBindingType();
			case JavaeePackage.RUN_AS: return (EObject)createRunAs();
			case JavaeePackage.SECURITY_ROLE: return (EObject)createSecurityRole();
			case JavaeePackage.SECURITY_ROLE_REF: return (EObject)createSecurityRoleRef();
			case JavaeePackage.SERVICE_REF: return (EObject)createServiceRef();
			case JavaeePackage.SERVICE_REF_HANDLER: return (EObject)createServiceRefHandler();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN: return (EObject)createServiceRefHandlerChain();
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS: return (EObject)createServiceRefHandlerChains();
			case JavaeePackage.URL_PATTERN_TYPE: return (EObject)createUrlPatternType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case JavaeePackage.ADDRESSING_RESPONSES_TYPE:
				return createAddressingResponsesTypeFromString(eDataType, initialValue);
			case JavaeePackage.EJB_REF_TYPE:
				return createEjbRefTypeFromString(eDataType, initialValue);
			case JavaeePackage.GENERIC_BOOLEAN_TYPE:
				return createGenericBooleanTypeFromString(eDataType, initialValue);
			case JavaeePackage.ISOLATION_LEVEL_TYPE:
				return createIsolationLevelTypeFromString(eDataType, initialValue);
			case JavaeePackage.MESSAGE_DESTINATION_USAGE_TYPE:
				return createMessageDestinationUsageTypeFromString(eDataType, initialValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE:
				return createPersistenceContextSynchronizationTypeFromString(eDataType, initialValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE:
				return createPersistenceContextTypeFromString(eDataType, initialValue);
			case JavaeePackage.RES_AUTH_TYPE:
				return createResAuthTypeFromString(eDataType, initialValue);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE:
				return createResSharingScopeTypeFromString(eDataType, initialValue);
			case JavaeePackage.TRANSACTION_SUPPORT:
				return createTransactionSupportFromString(eDataType, initialValue);
			case JavaeePackage.ADDRESSING_RESPONSES_TYPE_OBJECT:
				return createAddressingResponsesTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.DEWEY_VERSION_TYPE:
				return createDeweyVersionTypeFromString(eDataType, initialValue);
			case JavaeePackage.EJB_LINK:
				return createEJBLinkFromString(eDataType, initialValue);
			case JavaeePackage.EJB_REF_NAME_TYPE:
				return createEjbRefNameTypeFromString(eDataType, initialValue);
			case JavaeePackage.EJB_REF_TYPE_OBJECT:
				return createEjbRefTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.ENV_ENTRY_TYPE:
				return createEnvEntryTypeFromString(eDataType, initialValue);
			case JavaeePackage.FULLY_QUALIFIED_CLASS_TYPE:
				return createFullyQualifiedClassTypeFromString(eDataType, initialValue);
			case JavaeePackage.GENERIC_BOOLEAN_TYPE_OBJECT:
				return createGenericBooleanTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.HOME:
				return createHomeFromString(eDataType, initialValue);
			case JavaeePackage.ISOLATION_LEVEL_TYPE_OBJECT:
				return createIsolationLevelTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.JAVA_IDENTIFIER:
				return createJavaIdentifierFromString(eDataType, initialValue);
			case JavaeePackage.JAVA_TYPE:
				return createJavaTypeFromString(eDataType, initialValue);
			case JavaeePackage.JDBC_URL_TYPE:
				return createJdbcUrlTypeFromString(eDataType, initialValue);
			case JavaeePackage.JNDI_NAME:
				return createJNDINameFromString(eDataType, initialValue);
			case JavaeePackage.LOCAL:
				return createLocalFromString(eDataType, initialValue);
			case JavaeePackage.LOCAL_HOME:
				return createLocalHomeFromString(eDataType, initialValue);
			case JavaeePackage.MESSAGE_DESTINATION_LINK:
				return createMessageDestinationLinkFromString(eDataType, initialValue);
			case JavaeePackage.MESSAGE_DESTINATION_TYPE_TYPE:
				return createMessageDestinationTypeTypeFromString(eDataType, initialValue);
			case JavaeePackage.MESSAGE_DESTINATION_USAGE_TYPE_OBJECT:
				return createMessageDestinationUsageTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.PATH_TYPE:
				return createPathTypeFromString(eDataType, initialValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE_OBJECT:
				return createPersistenceContextSynchronizationTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE_OBJECT:
				return createPersistenceContextTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.PROTOCOL_BINDING_LIST_TYPE:
				return createProtocolBindingListTypeFromString(eDataType, initialValue);
			case JavaeePackage.PROTOCOL_BINDING_TYPE:
				return createProtocolBindingTypeFromString(eDataType, initialValue);
			case JavaeePackage.PROTOCOL_URI_ALIAS_TYPE:
				return createProtocolURIAliasTypeFromString(eDataType, initialValue);
			case JavaeePackage.QNAME_PATTERN:
				return createQnamePatternFromString(eDataType, initialValue);
			case JavaeePackage.REMOTE:
				return createRemoteFromString(eDataType, initialValue);
			case JavaeePackage.RES_AUTH_TYPE_OBJECT:
				return createResAuthTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE_OBJECT:
				return createResSharingScopeTypeObjectFromString(eDataType, initialValue);
			case JavaeePackage.ROLE_NAME:
				return createRoleNameFromString(eDataType, initialValue);
			case JavaeePackage.TRANSACTION_SUPPORT_OBJECT:
				return createTransactionSupportObjectFromString(eDataType, initialValue);
			case JavaeePackage.TRUE_FALSE_TYPE:
				return createTrueFalseTypeFromString(eDataType, initialValue);
			case JavaeePackage.TRUE_FALSE_TYPE_OBJECT:
				return createTrueFalseTypeObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case JavaeePackage.ADDRESSING_RESPONSES_TYPE:
				return convertAddressingResponsesTypeToString(eDataType, instanceValue);
			case JavaeePackage.EJB_REF_TYPE:
				return convertEjbRefTypeToString(eDataType, instanceValue);
			case JavaeePackage.GENERIC_BOOLEAN_TYPE:
				return convertGenericBooleanTypeToString(eDataType, instanceValue);
			case JavaeePackage.ISOLATION_LEVEL_TYPE:
				return convertIsolationLevelTypeToString(eDataType, instanceValue);
			case JavaeePackage.MESSAGE_DESTINATION_USAGE_TYPE:
				return convertMessageDestinationUsageTypeToString(eDataType, instanceValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE:
				return convertPersistenceContextSynchronizationTypeToString(eDataType, instanceValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE:
				return convertPersistenceContextTypeToString(eDataType, instanceValue);
			case JavaeePackage.RES_AUTH_TYPE:
				return convertResAuthTypeToString(eDataType, instanceValue);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE:
				return convertResSharingScopeTypeToString(eDataType, instanceValue);
			case JavaeePackage.TRANSACTION_SUPPORT:
				return convertTransactionSupportToString(eDataType, instanceValue);
			case JavaeePackage.ADDRESSING_RESPONSES_TYPE_OBJECT:
				return convertAddressingResponsesTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.DEWEY_VERSION_TYPE:
				return convertDeweyVersionTypeToString(eDataType, instanceValue);
			case JavaeePackage.EJB_LINK:
				return convertEJBLinkToString(eDataType, instanceValue);
			case JavaeePackage.EJB_REF_NAME_TYPE:
				return convertEjbRefNameTypeToString(eDataType, instanceValue);
			case JavaeePackage.EJB_REF_TYPE_OBJECT:
				return convertEjbRefTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.ENV_ENTRY_TYPE:
				return convertEnvEntryTypeToString(eDataType, instanceValue);
			case JavaeePackage.FULLY_QUALIFIED_CLASS_TYPE:
				return convertFullyQualifiedClassTypeToString(eDataType, instanceValue);
			case JavaeePackage.GENERIC_BOOLEAN_TYPE_OBJECT:
				return convertGenericBooleanTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.HOME:
				return convertHomeToString(eDataType, instanceValue);
			case JavaeePackage.ISOLATION_LEVEL_TYPE_OBJECT:
				return convertIsolationLevelTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.JAVA_IDENTIFIER:
				return convertJavaIdentifierToString(eDataType, instanceValue);
			case JavaeePackage.JAVA_TYPE:
				return convertJavaTypeToString(eDataType, instanceValue);
			case JavaeePackage.JDBC_URL_TYPE:
				return convertJdbcUrlTypeToString(eDataType, instanceValue);
			case JavaeePackage.JNDI_NAME:
				return convertJNDINameToString(eDataType, instanceValue);
			case JavaeePackage.LOCAL:
				return convertLocalToString(eDataType, instanceValue);
			case JavaeePackage.LOCAL_HOME:
				return convertLocalHomeToString(eDataType, instanceValue);
			case JavaeePackage.MESSAGE_DESTINATION_LINK:
				return convertMessageDestinationLinkToString(eDataType, instanceValue);
			case JavaeePackage.MESSAGE_DESTINATION_TYPE_TYPE:
				return convertMessageDestinationTypeTypeToString(eDataType, instanceValue);
			case JavaeePackage.MESSAGE_DESTINATION_USAGE_TYPE_OBJECT:
				return convertMessageDestinationUsageTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.PATH_TYPE:
				return convertPathTypeToString(eDataType, instanceValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE_OBJECT:
				return convertPersistenceContextSynchronizationTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE_OBJECT:
				return convertPersistenceContextTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.PROTOCOL_BINDING_LIST_TYPE:
				return convertProtocolBindingListTypeToString(eDataType, instanceValue);
			case JavaeePackage.PROTOCOL_BINDING_TYPE:
				return convertProtocolBindingTypeToString(eDataType, instanceValue);
			case JavaeePackage.PROTOCOL_URI_ALIAS_TYPE:
				return convertProtocolURIAliasTypeToString(eDataType, instanceValue);
			case JavaeePackage.QNAME_PATTERN:
				return convertQnamePatternToString(eDataType, instanceValue);
			case JavaeePackage.REMOTE:
				return convertRemoteToString(eDataType, instanceValue);
			case JavaeePackage.RES_AUTH_TYPE_OBJECT:
				return convertResAuthTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE_OBJECT:
				return convertResSharingScopeTypeObjectToString(eDataType, instanceValue);
			case JavaeePackage.ROLE_NAME:
				return convertRoleNameToString(eDataType, instanceValue);
			case JavaeePackage.TRANSACTION_SUPPORT_OBJECT:
				return convertTransactionSupportObjectToString(eDataType, instanceValue);
			case JavaeePackage.TRUE_FALSE_TYPE:
				return convertTrueFalseTypeToString(eDataType, instanceValue);
			case JavaeePackage.TRUE_FALSE_TYPE_OBJECT:
				return convertTrueFalseTypeObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressingType createAddressingType() {
		AddressingTypeImpl addressingType = new AddressingTypeImpl();
		return addressingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdministeredObjectType createAdministeredObjectType() {
		AdministeredObjectTypeImpl administeredObjectType = new AdministeredObjectTypeImpl();
		return administeredObjectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionFactoryResourceType createConnectionFactoryResourceType() {
		ConnectionFactoryResourceTypeImpl connectionFactoryResourceType = new ConnectionFactoryResourceTypeImpl();
		return connectionFactoryResourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSourceType createDataSourceType() {
		DataSourceTypeImpl dataSourceType = new DataSourceTypeImpl();
		return dataSourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Description createDescription() {
		DescriptionImpl description = new DescriptionImpl();
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisplayName createDisplayName() {
		DisplayNameImpl displayName = new DisplayNameImpl();
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbLocalRef createEjbLocalRef() {
		EjbLocalRefImpl ejbLocalRef = new EjbLocalRefImpl();
		return ejbLocalRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbRef createEjbRef() {
		EjbRefImpl ejbRef = new EjbRefImpl();
		return ejbRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmptyType createEmptyType() {
		EmptyTypeImpl emptyType = new EmptyTypeImpl();
		return emptyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvEntry createEnvEntry() {
		EnvEntryImpl envEntry = new EnvEntryImpl();
		return envEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Icon createIcon() {
		IconImpl icon = new IconImpl();
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InjectionTarget createInjectionTarget() {
		InjectionTargetImpl injectionTarget = new InjectionTargetImpl();
		return injectionTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JmsConnectionFactoryType createJmsConnectionFactoryType() {
		JmsConnectionFactoryTypeImpl jmsConnectionFactoryType = new JmsConnectionFactoryTypeImpl();
		return jmsConnectionFactoryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JmsDestinationType createJmsDestinationType() {
		JmsDestinationTypeImpl jmsDestinationType = new JmsDestinationTypeImpl();
		return jmsDestinationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LifecycleCallback createLifecycleCallback() {
		LifecycleCallbackImpl lifecycleCallback = new LifecycleCallbackImpl();
		return lifecycleCallback;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Listener createListener() {
		ListenerImpl listener = new ListenerImpl();
		return listener;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MailSessionType createMailSessionType() {
		MailSessionTypeImpl mailSessionType = new MailSessionTypeImpl();
		return mailSessionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestination createMessageDestination() {
		MessageDestinationImpl messageDestination = new MessageDestinationImpl();
		return messageDestination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestinationRef createMessageDestinationRef() {
		MessageDestinationRefImpl messageDestinationRef = new MessageDestinationRefImpl();
		return messageDestinationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParamValue createParamValue() {
		ParamValueImpl paramValue = new ParamValueImpl();
		return paramValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceContextRef createPersistenceContextRef() {
		PersistenceContextRefImpl persistenceContextRef = new PersistenceContextRefImpl();
		return persistenceContextRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceUnitRef createPersistenceUnitRef() {
		PersistenceUnitRefImpl persistenceUnitRef = new PersistenceUnitRefImpl();
		return persistenceUnitRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortComponentRef createPortComponentRef() {
		PortComponentRefImpl portComponentRef = new PortComponentRefImpl();
		return portComponentRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyType createPropertyType() {
		PropertyTypeImpl propertyType = new PropertyTypeImpl();
		return propertyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceEnvRef createResourceEnvRef() {
		ResourceEnvRefImpl resourceEnvRef = new ResourceEnvRefImpl();
		return resourceEnvRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceRef createResourceRef() {
		ResourceRefImpl resourceRef = new ResourceRefImpl();
		return resourceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RespectBindingType createRespectBindingType() {
		RespectBindingTypeImpl respectBindingType = new RespectBindingTypeImpl();
		return respectBindingType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RunAs createRunAs() {
		RunAsImpl runAs = new RunAsImpl();
		return runAs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityRole createSecurityRole() {
		SecurityRoleImpl securityRole = new SecurityRoleImpl();
		return securityRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SecurityRoleRef createSecurityRoleRef() {
		SecurityRoleRefImpl securityRoleRef = new SecurityRoleRefImpl();
		return securityRoleRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceRef createServiceRef() {
		ServiceRefImpl serviceRef = new ServiceRefImpl();
		return serviceRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceRefHandler createServiceRefHandler() {
		ServiceRefHandlerImpl serviceRefHandler = new ServiceRefHandlerImpl();
		return serviceRefHandler;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceRefHandlerChain createServiceRefHandlerChain() {
		ServiceRefHandlerChainImpl serviceRefHandlerChain = new ServiceRefHandlerChainImpl();
		return serviceRefHandlerChain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceRefHandlerChains createServiceRefHandlerChains() {
		ServiceRefHandlerChainsImpl serviceRefHandlerChains = new ServiceRefHandlerChainsImpl();
		return serviceRefHandlerChains;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UrlPatternType createUrlPatternType() {
		UrlPatternTypeImpl urlPatternType = new UrlPatternTypeImpl();
		return urlPatternType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressingResponsesType createAddressingResponsesTypeFromString(EDataType eDataType, String initialValue) {
		AddressingResponsesType result = AddressingResponsesType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAddressingResponsesTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbRefType createEjbRefTypeFromString(EDataType eDataType, String initialValue) {
		EjbRefType result = EjbRefType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEjbRefTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericBooleanType createGenericBooleanTypeFromString(EDataType eDataType, String initialValue) {
		GenericBooleanType result = GenericBooleanType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGenericBooleanTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsolationLevelType createIsolationLevelTypeFromString(EDataType eDataType, String initialValue) {
		IsolationLevelType result = IsolationLevelType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIsolationLevelTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createEnvEntryTypeFromString(EDataType eDataType, String initialValue) {
		return createFullyQualifiedClassTypeFromString(JavaeePackage.Literals.FULLY_QUALIFIED_CLASS_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnvEntryTypeToString(EDataType eDataType, Object instanceValue) {
		return convertFullyQualifiedClassTypeToString(JavaeePackage.Literals.FULLY_QUALIFIED_CLASS_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestinationUsageType createMessageDestinationUsageTypeFromString(EDataType eDataType, String initialValue) {
		MessageDestinationUsageType result = MessageDestinationUsageType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMessageDestinationUsageTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceContextSynchronizationType createPersistenceContextSynchronizationTypeFromString(EDataType eDataType, String initialValue) {
		PersistenceContextSynchronizationType result = PersistenceContextSynchronizationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPersistenceContextSynchronizationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceContextType createPersistenceContextTypeFromString(EDataType eDataType, String initialValue) {
		PersistenceContextType result = PersistenceContextType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPersistenceContextTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResAuthType createResAuthTypeFromString(EDataType eDataType, String initialValue) {
		ResAuthType result = ResAuthType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResAuthTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResSharingScopeType createResSharingScopeTypeFromString(EDataType eDataType, String initialValue) {
		ResSharingScopeType result = ResSharingScopeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResSharingScopeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionSupport createTransactionSupportFromString(EDataType eDataType, String initialValue) {
		TransactionSupport result = TransactionSupport.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransactionSupportToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressingResponsesType createAddressingResponsesTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createAddressingResponsesTypeFromString(JavaeePackage.Literals.ADDRESSING_RESPONSES_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAddressingResponsesTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertAddressingResponsesTypeToString(JavaeePackage.Literals.ADDRESSING_RESPONSES_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createDeweyVersionTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeweyVersionTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createEJBLinkFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEJBLinkToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createEjbRefNameTypeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEjbRefNameTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EjbRefType createEjbRefTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createEjbRefTypeFromString(JavaeePackage.Literals.EJB_REF_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEjbRefTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertEjbRefTypeToString(JavaeePackage.Literals.EJB_REF_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createFullyQualifiedClassTypeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFullyQualifiedClassTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericBooleanType createGenericBooleanTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createGenericBooleanTypeFromString(JavaeePackage.Literals.GENERIC_BOOLEAN_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGenericBooleanTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertGenericBooleanTypeToString(JavaeePackage.Literals.GENERIC_BOOLEAN_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createHomeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertHomeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsolationLevelType createIsolationLevelTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createIsolationLevelTypeFromString(JavaeePackage.Literals.ISOLATION_LEVEL_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIsolationLevelTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertIsolationLevelTypeToString(JavaeePackage.Literals.ISOLATION_LEVEL_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createJavaIdentifierFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJavaIdentifierToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createJavaTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJavaTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createJdbcUrlTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJdbcUrlTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createJNDINameFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertJNDINameToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createLocalFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLocalToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createLocalHomeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLocalHomeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createMessageDestinationLinkFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMessageDestinationLinkToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createMessageDestinationTypeTypeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMessageDestinationTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageDestinationUsageType createMessageDestinationUsageTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createMessageDestinationUsageTypeFromString(JavaeePackage.Literals.MESSAGE_DESTINATION_USAGE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMessageDestinationUsageTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertMessageDestinationUsageTypeToString(JavaeePackage.Literals.MESSAGE_DESTINATION_USAGE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createPathTypeFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPathTypeToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceContextSynchronizationType createPersistenceContextSynchronizationTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createPersistenceContextSynchronizationTypeFromString(JavaeePackage.Literals.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPersistenceContextSynchronizationTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPersistenceContextSynchronizationTypeToString(JavaeePackage.Literals.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PersistenceContextType createPersistenceContextTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createPersistenceContextTypeFromString(JavaeePackage.Literals.PERSISTENCE_CONTEXT_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPersistenceContextTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertPersistenceContextTypeToString(JavaeePackage.Literals.PERSISTENCE_CONTEXT_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> createProtocolBindingListTypeFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) return null;
		List<String> result = new ArrayList<String>();
		for (String item : split(initialValue)) {
			result.add(createProtocolBindingTypeFromString(JavaeePackage.Literals.PROTOCOL_BINDING_TYPE, item));
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertProtocolBindingListTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) return null;
		List<?> list = (List<?>)instanceValue;
		if (list.isEmpty()) return ""; //$NON-NLS-1$
		StringBuffer result = new StringBuffer();
		for (Object item : list) {
			result.append(convertProtocolBindingTypeToString(JavaeePackage.Literals.PROTOCOL_BINDING_TYPE, item));
			result.append(' ');
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createProtocolBindingTypeFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) return null;
		String result = null;
		RuntimeException exception = null;
		try {
			result = (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.ANY_URI, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = createProtocolURIAliasTypeFromString(JavaeePackage.Literals.PROTOCOL_URI_ALIAS_TYPE, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) return result;
    
		throw exception;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertProtocolBindingTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) return null;
		if (XMLTypePackage.Literals.ANY_URI.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.ANY_URI, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (JavaeePackage.Literals.PROTOCOL_URI_ALIAS_TYPE.isInstance(instanceValue)) {
			try {
				String value = convertProtocolURIAliasTypeToString(JavaeePackage.Literals.PROTOCOL_URI_ALIAS_TYPE, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName()); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createProtocolURIAliasTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertProtocolURIAliasTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createQnamePatternFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.TOKEN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQnamePatternToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.TOKEN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createRemoteFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRemoteToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResAuthType createResAuthTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createResAuthTypeFromString(JavaeePackage.Literals.RES_AUTH_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResAuthTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertResAuthTypeToString(JavaeePackage.Literals.RES_AUTH_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResSharingScopeType createResSharingScopeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createResSharingScopeTypeFromString(JavaeePackage.Literals.RES_SHARING_SCOPE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResSharingScopeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertResSharingScopeTypeToString(JavaeePackage.Literals.RES_SHARING_SCOPE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createRoleNameFromString(EDataType eDataType, String initialValue) {
		return (String)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRoleNameToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransactionSupport createTransactionSupportObjectFromString(EDataType eDataType, String initialValue) {
		return createTransactionSupportFromString(JavaeePackage.Literals.TRANSACTION_SUPPORT, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTransactionSupportObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTransactionSupportToString(JavaeePackage.Literals.TRANSACTION_SUPPORT, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean createTrueFalseTypeFromString(EDataType eDataType, String initialValue) {
		return (Boolean)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.BOOLEAN, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTrueFalseTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.BOOLEAN, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean createTrueFalseTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTrueFalseTypeFromString(JavaeePackage.Literals.TRUE_FALSE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTrueFalseTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTrueFalseTypeToString(JavaeePackage.Literals.TRUE_FALSE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaeePackage getJavaeePackage() {
		return (JavaeePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static JavaeePackage getPackage() {
		return JavaeePackage.eINSTANCE;
	}

} //JavaeeFactoryImpl
