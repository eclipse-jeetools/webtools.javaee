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
	 * Calls <code>validateXXX</code> for the corresonding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map context) {
		switch (classifierID) {
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
			case JavaeePackage.LIFECYCLE_CALLBACK:
				return validateLifecycleCallback((LifecycleCallback)value, diagnostics, context);
			case JavaeePackage.LISTENER:
				return validateListener((Listener)value, diagnostics, context);
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
			case JavaeePackage.EJB_REF_TYPE:
				return validateEjbRefType((EjbRefType)value, diagnostics, context);
			case JavaeePackage.ENV_ENTRY_TYPE:
				return validateEnvEntryType((EnvEntryType)value, diagnostics, context);
			case JavaeePackage.MESSAGE_DESTINATION_USAGE_TYPE:
				return validateMessageDestinationUsageType((MessageDestinationUsageType)value, diagnostics, context);
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE:
				return validatePersistenceContextType((PersistenceContextType)value, diagnostics, context);
			case JavaeePackage.RES_AUTH_TYPE:
				return validateResAuthType((ResAuthType)value, diagnostics, context);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE:
				return validateResSharingScopeType((ResSharingScopeType)value, diagnostics, context);
			case JavaeePackage.DEWEY_VERSION_TYPE:
				return validateDeweyVersionType((String)value, diagnostics, context);
			case JavaeePackage.EJB_LINK:
				return validateEJBLink((String)value, diagnostics, context);
			case JavaeePackage.EJB_REF_NAME_TYPE:
				return validateEjbRefNameType((String)value, diagnostics, context);
			case JavaeePackage.EJB_REF_TYPE_OBJECT:
				return validateEjbRefTypeObject((EjbRefType)value, diagnostics, context);
			case JavaeePackage.ENV_ENTRY_TYPE_OBJECT:
				return validateEnvEntryTypeObject((EnvEntryType)value, diagnostics, context);
			case JavaeePackage.FULLY_QUALIFIED_CLASS_TYPE:
				return validateFullyQualifiedClassType((String)value, diagnostics, context);
			case JavaeePackage.HOME:
				return validateHome((String)value, diagnostics, context);
			case JavaeePackage.JAVA_IDENTIFIER:
				return validateJavaIdentifier((String)value, diagnostics, context);
			case JavaeePackage.JAVA_TYPE:
				return validateJavaType((String)value, diagnostics, context);
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
			case JavaeePackage.PERSISTENCE_CONTEXT_TYPE_OBJECT:
				return validatePersistenceContextTypeObject((PersistenceContextType)value, diagnostics, context);
			case JavaeePackage.REMOTE:
				return validateRemote((String)value, diagnostics, context);
			case JavaeePackage.RES_AUTH_TYPE_OBJECT:
				return validateResAuthTypeObject((ResAuthType)value, diagnostics, context);
			case JavaeePackage.RES_SHARING_SCOPE_TYPE_OBJECT:
				return validateResSharingScopeTypeObject((ResSharingScopeType)value, diagnostics, context);
			case JavaeePackage.ROLE_NAME:
				return validateRoleName((String)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF_PROTOCOL_BINDING_LIST_TYPE:
				return validateServiceRefProtocolBindingListType((List)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF_PROTOCOL_BINDING_TYPE:
				return validateServiceRefProtocolBindingType((String)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF_PROTOCOL_URI_ALIAS_TYPE:
				return validateServiceRefProtocolURIAliasType((String)value, diagnostics, context);
			case JavaeePackage.SERVICE_REF_QNAME_PATTERN:
				return validateServiceRefQnamePattern((String)value, diagnostics, context);
			case JavaeePackage.TRUE_FALSE_TYPE:
				return validateTrueFalseType(((Boolean)value).booleanValue(), diagnostics, context);
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
	public boolean validateDescription(Description description, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)description, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDisplayName(DisplayName displayName, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)displayName, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbLocalRef(EjbLocalRef ejbLocalRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)ejbLocalRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRef(EjbRef ejbRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)ejbRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmptyType(EmptyType emptyType, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)emptyType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnvEntry(EnvEntry envEntry, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)envEntry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIcon(Icon icon, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)icon, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInjectionTarget(InjectionTarget injectionTarget, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)injectionTarget, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLifecycleCallback(LifecycleCallback lifecycleCallback, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)lifecycleCallback, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateListener(Listener listener, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)listener, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestination(MessageDestination messageDestination, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)messageDestination, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationRef(MessageDestinationRef messageDestinationRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)messageDestinationRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParamValue(ParamValue paramValue, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)paramValue, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextRef(PersistenceContextRef persistenceContextRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)persistenceContextRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceUnitRef(PersistenceUnitRef persistenceUnitRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)persistenceUnitRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePortComponentRef(PortComponentRef portComponentRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)portComponentRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePropertyType(PropertyType propertyType, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)propertyType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceEnvRef(ResourceEnvRef resourceEnvRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)resourceEnvRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResourceRef(ResourceRef resourceRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)resourceRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRunAs(RunAs runAs, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)runAs, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSecurityRole(SecurityRole securityRole, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)securityRole, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSecurityRoleRef(SecurityRoleRef securityRoleRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)securityRoleRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRef(ServiceRef serviceRef, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)serviceRef, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefHandler(ServiceRefHandler serviceRefHandler, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)serviceRefHandler, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefHandlerChain(ServiceRefHandlerChain serviceRefHandlerChain, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)serviceRefHandlerChain, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefHandlerChains(ServiceRefHandlerChains serviceRefHandlerChains, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)serviceRefHandlerChains, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUrlPatternType(UrlPatternType urlPatternType, DiagnosticChain diagnostics, Map context) {
		return validate_EveryDefaultConstraint((EObject)urlPatternType, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRefType(EjbRefType ejbRefType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnvEntryType(EnvEntryType envEntryType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationUsageType(MessageDestinationUsageType messageDestinationUsageType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextType(PersistenceContextType persistenceContextType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResAuthType(ResAuthType resAuthType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResSharingScopeType(ResSharingScopeType resSharingScopeType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDeweyVersionType(String deweyVersionType, DiagnosticChain diagnostics, Map context) {
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
	public boolean validateDeweyVersionType_Pattern(String deweyVersionType, DiagnosticChain diagnostics, Map context) {
		return validatePattern(JavaeePackage.Literals.DEWEY_VERSION_TYPE, deweyVersionType, DEWEY_VERSION_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEJBLink(String ejbLink, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRefNameType(String ejbRefNameType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEjbRefTypeObject(EjbRefType ejbRefTypeObject, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEnvEntryTypeObject(EnvEntryType envEntryTypeObject, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFullyQualifiedClassType(String fullyQualifiedClassType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHome(String home, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJavaIdentifier(String javaIdentifier, DiagnosticChain diagnostics, Map context) {
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
	public boolean validateJavaIdentifier_Pattern(String javaIdentifier, DiagnosticChain diagnostics, Map context) {
		return validatePattern(JavaeePackage.Literals.JAVA_IDENTIFIER, javaIdentifier, JAVA_IDENTIFIER__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJavaType(String javaType, DiagnosticChain diagnostics, Map context) {
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
	public boolean validateJavaType_Pattern(String javaType, DiagnosticChain diagnostics, Map context) {
		return validatePattern(JavaeePackage.Literals.JAVA_TYPE, javaType, JAVA_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateJNDIName(String jndiName, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocal(String local, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLocalHome(String localHome, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationLink(String messageDestinationLink, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationTypeType(String messageDestinationTypeType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMessageDestinationUsageTypeObject(MessageDestinationUsageType messageDestinationUsageTypeObject, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePathType(String pathType, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePersistenceContextTypeObject(PersistenceContextType persistenceContextTypeObject, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRemote(String remote, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResAuthTypeObject(ResAuthType resAuthTypeObject, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateResSharingScopeTypeObject(ResSharingScopeType resSharingScopeTypeObject, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRoleName(String roleName, DiagnosticChain diagnostics, Map context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefProtocolBindingListType(List serviceRefProtocolBindingListType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateServiceRefProtocolBindingListType_ItemType(serviceRefProtocolBindingListType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the ItemType constraint of '<em>Service Ref Protocol Binding List Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefProtocolBindingListType_ItemType(List serviceRefProtocolBindingListType, DiagnosticChain diagnostics, Map context) {
		boolean result = true;
		for (Iterator i = serviceRefProtocolBindingListType.iterator(); i.hasNext() && (result || diagnostics != null); ) {
			Object item = i.next();
			if (JavaeePackage.Literals.SERVICE_REF_PROTOCOL_BINDING_TYPE.isInstance(item)) {
				result &= validateServiceRefProtocolBindingType((String)item, diagnostics, context);
			}
			else {
				result = false;
				reportDataValueTypeViolation(JavaeePackage.Literals.SERVICE_REF_PROTOCOL_BINDING_TYPE, item, diagnostics, context);
			}
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefProtocolBindingType(String serviceRefProtocolBindingType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateServiceRefProtocolBindingType_MemberTypes(serviceRefProtocolBindingType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>Service Ref Protocol Binding Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefProtocolBindingType_MemberTypes(String serviceRefProtocolBindingType, DiagnosticChain diagnostics, Map context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (XMLTypePackage.Literals.ANY_URI.isInstance(serviceRefProtocolBindingType)) {
				if (xmlTypeValidator.validateAnyURI(serviceRefProtocolBindingType, tempDiagnostics, context)) return true;
			}
			if (JavaeePackage.Literals.SERVICE_REF_PROTOCOL_URI_ALIAS_TYPE.isInstance(serviceRefProtocolBindingType)) {
				if (validateServiceRefProtocolURIAliasType(serviceRefProtocolBindingType, tempDiagnostics, context)) return true;
			}
			List children = tempDiagnostics.getChildren();
			for (int i = 0; i < children.size(); i++) {
				diagnostics.add((Diagnostic)children.get(i));
			}
		}
		else {
			if (XMLTypePackage.Literals.ANY_URI.isInstance(serviceRefProtocolBindingType)) {
				if (xmlTypeValidator.validateAnyURI(serviceRefProtocolBindingType, null, context)) return true;
			}
			if (JavaeePackage.Literals.SERVICE_REF_PROTOCOL_URI_ALIAS_TYPE.isInstance(serviceRefProtocolBindingType)) {
				if (validateServiceRefProtocolURIAliasType(serviceRefProtocolBindingType, null, context)) return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefProtocolURIAliasType(String serviceRefProtocolURIAliasType, DiagnosticChain diagnostics, Map context) {
		boolean result = validateServiceRefProtocolURIAliasType_Pattern(serviceRefProtocolURIAliasType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateServiceRefProtocolURIAliasType_Pattern
	 */
	public static final  PatternMatcher [][] SERVICE_REF_PROTOCOL_URI_ALIAS_TYPE__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("##.+") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Service Ref Protocol URI Alias Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefProtocolURIAliasType_Pattern(String serviceRefProtocolURIAliasType, DiagnosticChain diagnostics, Map context) {
		return validatePattern(JavaeePackage.Literals.SERVICE_REF_PROTOCOL_URI_ALIAS_TYPE, serviceRefProtocolURIAliasType, SERVICE_REF_PROTOCOL_URI_ALIAS_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefQnamePattern(String serviceRefQnamePattern, DiagnosticChain diagnostics, Map context) {
		boolean result = validateServiceRefQnamePattern_Pattern(serviceRefQnamePattern, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateServiceRefQnamePattern_Pattern
	 */
	public static final  PatternMatcher [][] SERVICE_REF_QNAME_PATTERN__PATTERN__VALUES =
		new PatternMatcher [][] {
			new PatternMatcher [] {
				XMLTypeUtil.createPatternMatcher("\\*|([\\i-[:]][\\c-[:]]*:)?[\\i-[:]][\\c-[:]]*\\*?") //$NON-NLS-1$
			}
		};

	/**
	 * Validates the Pattern constraint of '<em>Service Ref Qname Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateServiceRefQnamePattern_Pattern(String serviceRefQnamePattern, DiagnosticChain diagnostics, Map context) {
		return validatePattern(JavaeePackage.Literals.SERVICE_REF_QNAME_PATTERN, serviceRefQnamePattern, SERVICE_REF_QNAME_PATTERN__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrueFalseType(boolean trueFalseType, DiagnosticChain diagnostics, Map context) {
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
	public boolean validateTrueFalseType_Pattern(boolean trueFalseType, DiagnosticChain diagnostics, Map context) {
		return validatePattern(JavaeePackage.Literals.TRUE_FALSE_TYPE, new Boolean(trueFalseType), TRUE_FALSE_TYPE__PATTERN__VALUES, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTrueFalseTypeObject(Boolean trueFalseTypeObject, DiagnosticChain diagnostics, Map context) {
		boolean result = validateTrueFalseType_Pattern(trueFalseTypeObject.booleanValue(), diagnostics, context);
		return result;
	}

} //JavaeeValidator
