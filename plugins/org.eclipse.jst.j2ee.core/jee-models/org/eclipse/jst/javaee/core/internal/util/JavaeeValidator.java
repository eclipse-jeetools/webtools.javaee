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
package org.eclipse.jst.javaee.core.internal.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

import org.eclipse.jst.javaee.core.*;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage
 * @generated
 */
public class JavaeeValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final JavaeeValidator INSTANCE = new JavaeeValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.jst.javaee.core"; //$NON-NLS-1$

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaeeValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return JavaeePackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case JavaeePackage.ADDRESSING_TYPE:
				return validateAddressingType((AddressingType)value, diagnostics, context);
			case JavaeePackage.ADMINISTERED_OBJECT_TYPE:
				return validateAdministeredObjectType((AdministeredObjectType)value, diagnostics, context);
			case JavaeePackage.CONNECTION_FACTORY_RESOURCE_TYPE:
				return validateConnectionFactoryResourceType((ConnectionFactoryResourceType)value, diagnostics, context);
			case JavaeePackage.DATA_SOURCE_TYPE:
				return validateDataSourceType((DataSourceType)value, diagnostics, context);
			case JavaeePackage.DESCRIPTION:
				return validateDescription((Description)value, diagnostics, context);
			case JavaeePackage.DISPLAY_NAME:
				return validateDisplayName((DisplayName)value, diagnostics, context);
			case JavaeePackage.EJB_LOCAL_REF:
				return validateEjbLocalRef((EjbLocalRef)value, diagnostics, context);
			case JavaeePackage.EJB_REF:
				return validateEjbRef((EjbRef)value, diagnostics, context);
			case JavaeePackage.EMPTY_TYPE:
				return validateEmptyType((EmptyType)value, diagnostics, context);
			case JavaeePackage.ENV_ENTRY:
				return validateEnvEntry((EnvEntry)value, diagnostics, context);
			case JavaeePackage.ICON:
				return validateIcon((Icon)value, diagnostics, context);
			case JavaeePackage.INJECTION_TARGET:
				return validateInjectionTarget((InjectionTarget)value, diagnostics, context);
			case JavaeePackage.JMS_CONNECTION_FACTORY_TYPE:
				return validateJmsConnectionFactoryType((JmsConnectionFactoryType)value, diagnostics, context);
			case JavaeePackage.JMS_DESTINATION_TYPE:
				return validateJmsDestinationType((JmsDestinationType)value, diagnostics, context);
			case JavaeePackage.LIFECYCLE_CALLBACK:
				return validateLifecycleCallback((LifecycleCallback)value, diagnostics, context);
			case JavaeePackage.LISTENER:
				return validateListener((Listener)value, diagnostics, context);
			case JavaeePackage.MAIL_SESSION_TYPE:
				return validateMailSessionType((MailSessionType)value, diagnostics, context);
			case JavaeePackage.MESSAGE_DESTINATION:
				return validateMessageDestination((MessageDestination)value, diagnostics, context);
			case JavaeePackage.MESSAGE_DESTINATION_REF:
				return validateMessageDestinationRef((MessageDestinationRef)value, diagnostics, context);
			case JavaeePackage.PARAM_VALUE:
				return validateParamValue((ParamValue)value, diagnostics, context);
			case JavaeePackage.PERSISTENCE_CONTEXT_REF:
				return validatePersistenceContextRef((PersistenceContextRef)value, diagnostics, context);
			case JavaeePackage.PERSISTENCE_UNIT_REF:
				return validatePersistenceUnitRef((PersistenceUnitRef)value, diagnostics, context);
			case JavaeePackage.PORT_COMPONENT_REF:
				return validatePortComponentRef((PortComponentRef)value, diagnostics, context);
			case JavaeePackage.PROPERTY_TYPE:
				return validatePropertyType((PropertyType)value, diagnostics, context);
			case JavaeePackage.RESOURCE_ENV_REF:
				return validateResourceEnvRef((ResourceEnvRef)value, diagnostics, context);
			case JavaeePackage.RESOURCE_REF:
				return validateResourceRef((ResourceRef)value, diagnostics, context);
			case JavaeePackage.RESPECT_BINDING_TYPE:
				return validateRespectBindingType((RespectBindingType)value, diagnostics, context);
			case JavaeePackage.RUN_AS:
				return validateRunAs((RunAs)value, diagnostics, context);
			case JavaeePackage.SECURITY_ROLE:
				return validateSecurityRole((SecurityRole)value, diagnostics, context);
			case JavaeePackage.SECURITY_ROLE_REF:
				return validateSecurityRoleRef((SecurityRoleRef)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF:
				return validateServiceRef((ServiceRef)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF_HANDLER:
				return validateServiceRefHandler((ServiceRefHandler)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF_HANDLER_CHAIN:
				return validateServiceRefHandlerChain((ServiceRefHandlerChain)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF_HANDLER_CHAINS:
				return validateServiceRefHandlerChains((ServiceRefHandlerChains)value, diagnostics, context);
			case JavaeePackage.URL_PATTERN_TYPE:
				return validateUrlPatternType((UrlPatternType)value, diagnostics, context);
			case JavaeePackage.ADDRESSING_RESPONSES_TYPE:
				return validateAddressingResponsesType((AddressingResponsesType)value, diagnostics, context);
			case JavaeePackage.EJB_REF_TYPE:
				return validateEjbRefType((EjbRefType)value, diagnostics, context);
			case JavaeePackage.GENERIC_BOOLEAN_TYPE:
				return validateGenericBooleanType((GenericBooleanType)value, diagnostics, context);
			case JavaeePackage.ISOLATION_LEVEL_TYPE:
				return validateIsolationLevelType((IsolationLevelType)value, diagnostics, context);
			case JavaeePackage.MESSAGE_DESTINATION_USAGE_TYPE:
				return validateMessageDestinationUsageType((MessageDestinationUsageType)value, diagnostics, context);
			case JavaeePackage.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE:
				return validatePersistenceContextSynchronizationType((PersistenceContextSynchronizationType)value, diagnostics, context);
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE:
				return validatePersistenceContextType((PersistenceContextType)value, diagnostics, context);
			case JavaeePackage.RES_AUTH_TYPE:
				return validateResAuthType((ResAuthType)value, diagnostics, context);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE:
				return validateResSharingScopeType((ResSharingScopeType)value, diagnostics, context);
			case JavaeePackage.TRANSACTION_SUPPORT:
				return validateTransactionSupport((TransactionSupport)value, diagnostics, context);
			case JavaeePackage.ADDRESSING_RESPONSES_TYPE_OBJECT:
				return validateAddressingResponsesTypeObject((AddressingResponsesType)value, diagnostics, context);
			case JavaeePackage.DEWEY_VERSION_TYPE:
				return validateDeweyVersionType((String)value, diagnostics, context);
			case JavaeePackage.EJB_LINK:
				return validateEJBLink((String)value, diagnostics, context);
			case JavaeePackage.EJB_REF_NAME_TYPE:
				return validateEjbRefNameType((String)value, diagnostics, context);
			case JavaeePackage.EJB_REF_TYPE_OBJECT:
				return validateEjbRefTypeObject((EjbRefType)value, diagnostics, context);
			case JavaeePackage.ENV_ENTRY_TYPE:
				return validateEnvEntryType((String)value, diagnostics, context);
			case JavaeePackage.FULLY_QUALIFIED_CLASS_TYPE:
				return validateFullyQualifiedClassType((String)value, diagnostics, context);
			case JavaeePackage.GENERIC_BOOLEAN_TYPE_OBJECT:
				return validateGenericBooleanTypeObject((GenericBooleanType)value, diagnostics, context);
			case JavaeePackage.HOME:
				return validateHome((String)value, diagnostics, context);
			case JavaeePackage.ISOLATION_LEVEL_TYPE_OBJECT:
				return validateIsolationLevelTypeObject((IsolationLevelType)value, diagnostics, context);
			case JavaeePackage.JAVA_IDENTIFIER:
				return validateJavaIdentifier((String)value, diagnostics, context);
			case JavaeePackage.JAVA_TYPE:
				return validateJavaType((String)value, diagnostics, context);
			case JavaeePackage.JDBC_URL_TYPE:
				return validateJdbcUrlType((String)value, diagnostics, context);
			case JavaeePackage.JNDI_NAME:
				return validateJNDIName((String)value, diagnostics, context);
			case JavaeePackage.LOCAL:
				return validateLocal((String)value, diagnostics, context);
			case JavaeePackage.LOCAL_HOME:
				return validateLocalHome((String)value, diagnostics, context);
			case JavaeePackage.MESSAGE_DESTINATION_LINK:
				return validateMessageDestinationLink((String)value, diagnostics, context);
			case JavaeePackage.MESSAGE_DESTINATION_TYPE_TYPE:
				return validateMessageDestinationTypeType((String)value, diagnostics, context);
			case JavaeePackage.MESSAGE_DESTINATION_USAGE_TYPE_OBJECT:
				return validateMessageDestinationUsageTypeObject((MessageDestinationUsageType)value, diagnostics, context);
			case JavaeePackage.PATH_TYPE:
				return validatePathType((String)value, diagnostics, context);
			case JavaeePackage.PERSISTENCE_CONTEXT_SYNCHRONIZATION_TYPE_OBJECT:
				return validatePersistenceContextSynchronizationTypeObject((PersistenceContextSynchronizationType)value, diagnostics, context);
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE_OBJECT:
				return validatePersistenceContextTypeObject((PersistenceContextType)value, diagnostics, context);
			case JavaeePackage.PROTOCOL_BINDING_LIST_TYPE:
				return validateProtocolBindingListType((List<?>)value, diagnostics, context);
			case JavaeePackage.PROTOCOL_BINDING_TYPE:
				return validateProtocolBindingType((String)value, diagnostics, context);
			case JavaeePackage.PROTOCOL_URI_ALIAS_TYPE:
				return validateProtocolURIAliasType((String)value, diagnostics, context);
			case JavaeePackage.QNAME_PATTERN:
				return validateQnamePattern((String)value, diagnostics, context);
			case JavaeePackage.REMOTE:
				return validateRemote((String)value, diagnostics, context);
			case JavaeePackage.RES_AUTH_TYPE_OBJECT:
				return validateResAuthTypeObject((ResAuthType)value, diagnostics, context);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE_OBJECT:
				return validateResSharingScopeTypeObject((ResSharingScopeType)value, diagnostics, context);
			case JavaeePackage.ROLE_NAME:
				return validateRoleName((String)value, diagnostics, context);
			case JavaeePackage.TRANSACTION_SUPPORT_OBJECT:
				return validateTransactionSupportObject((TransactionSupport)value, diagnostics, context);
			case JavaeePackage.TRUE_FALSE_TYPE:
				return validateTrueFalseType((Boolean)value, diagnostics, context);
			case JavaeePackage.TRUE_FALSE_TYPE_OBJECT:
				return validateTrueFalseTypeObject((Boolean)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddressingType(AddressingType addressingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)addressingType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAdministeredObjectType(AdministeredObjectType administeredObjectType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)administeredObjectType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConnectionFactoryResourceType(ConnectionFactoryResourceType connectionFactoryResourceType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)connectionFactoryResourceType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataSourceType(DataSourceType dataSourceType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)dataSourceType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDescription(Description description, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)description, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDisplayName(DisplayName displayName, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)displayName, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbLocalRef(EjbLocalRef ejbLocalRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)ejbLocalRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRef(EjbRef ejbRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)ejbRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmptyType(EmptyType emptyType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)emptyType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnvEntry(EnvEntry envEntry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)envEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIcon(Icon icon, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)icon, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInjectionTarget(InjectionTarget injectionTarget, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)injectionTarget, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJmsConnectionFactoryType(JmsConnectionFactoryType jmsConnectionFactoryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)jmsConnectionFactoryType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJmsDestinationType(JmsDestinationType jmsDestinationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)jmsDestinationType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLifecycleCallback(LifecycleCallback lifecycleCallback, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)lifecycleCallback, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateListener(Listener listener, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)listener, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMailSessionType(MailSessionType mailSessionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)mailSessionType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestination(MessageDestination messageDestination, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)messageDestination, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationRef(MessageDestinationRef messageDestinationRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)messageDestinationRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParamValue(ParamValue paramValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)paramValue, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextRef(PersistenceContextRef persistenceContextRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)persistenceContextRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceUnitRef(PersistenceUnitRef persistenceUnitRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)persistenceUnitRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortComponentRef(PortComponentRef portComponentRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)portComponentRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePropertyType(PropertyType propertyType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)propertyType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceEnvRef(ResourceEnvRef resourceEnvRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)resourceEnvRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceRef(ResourceRef resourceRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)resourceRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRespectBindingType(RespectBindingType respectBindingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)respectBindingType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRunAs(RunAs runAs, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)runAs, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSecurityRole(SecurityRole securityRole, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)securityRole, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSecurityRoleRef(SecurityRoleRef securityRoleRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)securityRoleRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRef(ServiceRef serviceRef, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)serviceRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefHandler(ServiceRefHandler serviceRefHandler, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)serviceRefHandler, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefHandlerChain(ServiceRefHandlerChain serviceRefHandlerChain, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)serviceRefHandlerChain, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefHandlerChains(ServiceRefHandlerChains serviceRefHandlerChains, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)serviceRefHandlerChains, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUrlPatternType(UrlPatternType urlPatternType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)urlPatternType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddressingResponsesType(AddressingResponsesType addressingResponsesType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRefType(EjbRefType ejbRefType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGenericBooleanType(GenericBooleanType genericBooleanType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIsolationLevelType(IsolationLevelType isolationLevelType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationUsageType(MessageDestinationUsageType messageDestinationUsageType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextSynchronizationType(PersistenceContextSynchronizationType persistenceContextSynchronizationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextType(PersistenceContextType persistenceContextType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResAuthType(ResAuthType resAuthType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResSharingScopeType(ResSharingScopeType resSharingScopeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransactionSupport(TransactionSupport transactionSupport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAddressingResponsesTypeObject(AddressingResponsesType addressingResponsesTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDeweyVersionType(String deweyVersionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateDeweyVersionType_Pattern(deweyVersionType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateDeweyVersionType_Pattern
	 */
	public static final  PatternMatcher [][] DEWEY_VERSION_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("\\.?[0-9]+(\\.[0-9]+)*") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Dewey Version Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDeweyVersionType_Pattern(String deweyVersionType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(JavaeePackage.Literals.DEWEY_VERSION_TYPE, deweyVersionType, DEWEY_VERSION_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEJBLink(String ejbLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRefNameType(String ejbRefNameType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRefTypeObject(EjbRefType ejbRefTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnvEntryType(String envEntryType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFullyQualifiedClassType(String fullyQualifiedClassType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGenericBooleanTypeObject(GenericBooleanType genericBooleanTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHome(String home, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIsolationLevelTypeObject(IsolationLevelType isolationLevelTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJavaIdentifier(String javaIdentifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateJavaIdentifier_Pattern(javaIdentifier, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateJavaIdentifier_Pattern
	 */
	public static final  PatternMatcher [][] JAVA_IDENTIFIER__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("($|_|\\p{L})(\\p{L}|\\p{Nd}|_|$)*") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Java Identifier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJavaIdentifier_Pattern(String javaIdentifier, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(JavaeePackage.Literals.JAVA_IDENTIFIER, javaIdentifier, JAVA_IDENTIFIER__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJavaType(String javaType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateJavaType_Pattern(javaType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateJavaType_Pattern
	 */
	public static final  PatternMatcher [][] JAVA_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("[^\\p{Z}]*") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Java Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJavaType_Pattern(String javaType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(JavaeePackage.Literals.JAVA_TYPE, javaType, JAVA_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJdbcUrlType(String jdbcUrlType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateJdbcUrlType_Pattern(jdbcUrlType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateJdbcUrlType_Pattern
	 */
	public static final  PatternMatcher [][] JDBC_URL_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("jdbc:(.*):(.*)") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Jdbc Url Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJdbcUrlType_Pattern(String jdbcUrlType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(JavaeePackage.Literals.JDBC_URL_TYPE, jdbcUrlType, JDBC_URL_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJNDIName(String jndiName, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocal(String local, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocalHome(String localHome, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationLink(String messageDestinationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationTypeType(String messageDestinationTypeType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationUsageTypeObject(MessageDestinationUsageType messageDestinationUsageTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePathType(String pathType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextSynchronizationTypeObject(PersistenceContextSynchronizationType persistenceContextSynchronizationTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextTypeObject(PersistenceContextType persistenceContextTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProtocolBindingListType(List<?> protocolBindingListType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateProtocolBindingListType_ItemType(protocolBindingListType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ItemType constraint of '<em>Protocol Binding List Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProtocolBindingListType_ItemType(List<?> protocolBindingListType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = true;
		for (Iterator<?> i = protocolBindingListType.iterator(); i.hasNext() && (result || diagnostics != null); ) {
			Object item = i.next();
			if (JavaeePackage.Literals.PROTOCOL_BINDING_TYPE.isInstance(item)) {
				result &= validateProtocolBindingType((String)item, diagnostics, context);
			}
			else {
				result = false;
				reportDataValueTypeViolation(JavaeePackage.Literals.PROTOCOL_BINDING_TYPE, item, diagnostics, context);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProtocolBindingType(String protocolBindingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateProtocolBindingType_MemberTypes(protocolBindingType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>Protocol Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProtocolBindingType_MemberTypes(String protocolBindingType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (XMLTypePackage.Literals.ANY_URI.isInstance(protocolBindingType)) {
				if (xmlTypeValidator.validateAnyURI(protocolBindingType, tempDiagnostics, context)) return true;
			}
			if (JavaeePackage.Literals.PROTOCOL_URI_ALIAS_TYPE.isInstance(protocolBindingType)) {
				if (validateProtocolURIAliasType(protocolBindingType, tempDiagnostics, context)) return true;
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (XMLTypePackage.Literals.ANY_URI.isInstance(protocolBindingType)) {
				if (xmlTypeValidator.validateAnyURI(protocolBindingType, null, context)) return true;
			}
			if (JavaeePackage.Literals.PROTOCOL_URI_ALIAS_TYPE.isInstance(protocolBindingType)) {
				if (validateProtocolURIAliasType(protocolBindingType, null, context)) return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProtocolURIAliasType(String protocolURIAliasType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateProtocolURIAliasType_Pattern(protocolURIAliasType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateProtocolURIAliasType_Pattern
	 */
	public static final  PatternMatcher [][] PROTOCOL_URI_ALIAS_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("##.+") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Protocol URI Alias Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProtocolURIAliasType_Pattern(String protocolURIAliasType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(JavaeePackage.Literals.PROTOCOL_URI_ALIAS_TYPE, protocolURIAliasType, PROTOCOL_URI_ALIAS_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQnamePattern(String qnamePattern, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateQnamePattern_Pattern(qnamePattern, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateQnamePattern_Pattern
	 */
	public static final  PatternMatcher [][] QNAME_PATTERN__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("\\*|([\\i-[:]][\\c-[:]]*:)?[\\i-[:]][\\c-[:]]*\\*?") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Qname Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQnamePattern_Pattern(String qnamePattern, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(JavaeePackage.Literals.QNAME_PATTERN, qnamePattern, QNAME_PATTERN__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemote(String remote, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResAuthTypeObject(ResAuthType resAuthTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResSharingScopeTypeObject(ResSharingScopeType resSharingScopeTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRoleName(String roleName, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTransactionSupportObject(TransactionSupport transactionSupportObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrueFalseType(boolean trueFalseType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTrueFalseType_Pattern(trueFalseType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateTrueFalseType_Pattern
	 */
	public static final  PatternMatcher [][] TRUE_FALSE_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("(true|false)") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>True False Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrueFalseType_Pattern(boolean trueFalseType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validatePattern(JavaeePackage.Literals.TRUE_FALSE_TYPE, trueFalseType, TRUE_FALSE_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrueFalseTypeObject(Boolean trueFalseTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTrueFalseType_Pattern(trueFalseTypeObject, diagnostics, context);
		return result;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //JavaeeValidator
