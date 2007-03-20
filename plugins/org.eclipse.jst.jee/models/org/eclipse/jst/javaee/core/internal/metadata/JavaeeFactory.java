/**
 * <copyright>
 * </copyright>
 *
 * $Id: JavaeeFactory.java,v 1.1 2007/03/20 18:04:38 jsholl Exp $
 */
package org.eclipse.jst.javaee.core.internal.metadata;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EmptyType;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.core.LifecycleCallback;
import org.eclipse.jst.javaee.core.Listener;
import org.eclipse.jst.javaee.core.MessageDestination;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.PortComponentRef;
import org.eclipse.jst.javaee.core.PropertyType;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.core.ServiceRef;
import org.eclipse.jst.javaee.core.ServiceRefHandler;
import org.eclipse.jst.javaee.core.ServiceRefHandlerChain;
import org.eclipse.jst.javaee.core.ServiceRefHandlerChains;
import org.eclipse.jst.javaee.core.UrlPatternType;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage
 * @generated
 */
public interface JavaeeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JavaeeFactory eINSTANCE = org.eclipse.jst.javaee.core.internal.impl.JavaeeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Description</em>'.
	 * @generated
	 */
	Description createDescription();

	/**
	 * Returns a new object of class '<em>Display Name</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Display Name</em>'.
	 * @generated
	 */
	DisplayName createDisplayName();

	/**
	 * Returns a new object of class '<em>Ejb Local Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ejb Local Ref</em>'.
	 * @generated
	 */
	EjbLocalRef createEjbLocalRef();

	/**
	 * Returns a new object of class '<em>Ejb Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ejb Ref</em>'.
	 * @generated
	 */
	EjbRef createEjbRef();

	/**
	 * Returns a new object of class '<em>Empty Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Empty Type</em>'.
	 * @generated
	 */
	EmptyType createEmptyType();

	/**
	 * Returns a new object of class '<em>Env Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Env Entry</em>'.
	 * @generated
	 */
	EnvEntry createEnvEntry();

	/**
	 * Returns a new object of class '<em>Icon</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Icon</em>'.
	 * @generated
	 */
	Icon createIcon();

	/**
	 * Returns a new object of class '<em>Injection Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Injection Target</em>'.
	 * @generated
	 */
	InjectionTarget createInjectionTarget();

	/**
	 * Returns a new object of class '<em>Lifecycle Callback</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Lifecycle Callback</em>'.
	 * @generated
	 */
	LifecycleCallback createLifecycleCallback();

	/**
	 * Returns a new object of class '<em>Listener</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Listener</em>'.
	 * @generated
	 */
	Listener createListener();

	/**
	 * Returns a new object of class '<em>Message Destination</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Destination</em>'.
	 * @generated
	 */
	MessageDestination createMessageDestination();

	/**
	 * Returns a new object of class '<em>Message Destination Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Destination Ref</em>'.
	 * @generated
	 */
	MessageDestinationRef createMessageDestinationRef();

	/**
	 * Returns a new object of class '<em>Param Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Param Value</em>'.
	 * @generated
	 */
	ParamValue createParamValue();

	/**
	 * Returns a new object of class '<em>Persistence Context Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Persistence Context Ref</em>'.
	 * @generated
	 */
	PersistenceContextRef createPersistenceContextRef();

	/**
	 * Returns a new object of class '<em>Persistence Unit Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Persistence Unit Ref</em>'.
	 * @generated
	 */
	PersistenceUnitRef createPersistenceUnitRef();

	/**
	 * Returns a new object of class '<em>Port Component Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port Component Ref</em>'.
	 * @generated
	 */
	PortComponentRef createPortComponentRef();

	/**
	 * Returns a new object of class '<em>Property Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Type</em>'.
	 * @generated
	 */
	PropertyType createPropertyType();

	/**
	 * Returns a new object of class '<em>Resource Env Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Env Ref</em>'.
	 * @generated
	 */
	ResourceEnvRef createResourceEnvRef();

	/**
	 * Returns a new object of class '<em>Resource Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Ref</em>'.
	 * @generated
	 */
	ResourceRef createResourceRef();

	/**
	 * Returns a new object of class '<em>Run As</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Run As</em>'.
	 * @generated
	 */
	RunAs createRunAs();

	/**
	 * Returns a new object of class '<em>Security Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Role</em>'.
	 * @generated
	 */
	SecurityRole createSecurityRole();

	/**
	 * Returns a new object of class '<em>Security Role Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Role Ref</em>'.
	 * @generated
	 */
	SecurityRoleRef createSecurityRoleRef();

	/**
	 * Returns a new object of class '<em>Service Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref</em>'.
	 * @generated
	 */
	ServiceRef createServiceRef();

	/**
	 * Returns a new object of class '<em>Service Ref Handler</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref Handler</em>'.
	 * @generated
	 */
	ServiceRefHandler createServiceRefHandler();

	/**
	 * Returns a new object of class '<em>Service Ref Handler Chain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref Handler Chain</em>'.
	 * @generated
	 */
	ServiceRefHandlerChain createServiceRefHandlerChain();

	/**
	 * Returns a new object of class '<em>Service Ref Handler Chains</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service Ref Handler Chains</em>'.
	 * @generated
	 */
	ServiceRefHandlerChains createServiceRefHandlerChains();

	/**
	 * Returns a new object of class '<em>Url Pattern Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Url Pattern Type</em>'.
	 * @generated
	 */
	UrlPatternType createUrlPatternType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JavaeePackage getJavaeePackage();

} //JavaeeFactory
