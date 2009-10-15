/**
 * <copyright>
 * </copyright>
 *
 * $Id: JcaFactory.java,v 1.1 2009/10/15 18:52:16 canderson Exp $
 */
package org.eclipse.jst.javaee.jca.internal.metadata;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.jca.ActivationSpec;
import org.eclipse.jst.javaee.jca.AdminObject;
import org.eclipse.jst.javaee.jca.AuthenticationMechanism;
import org.eclipse.jst.javaee.jca.ConfigProperty;
import org.eclipse.jst.javaee.jca.ConnectionDefinition;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor;
import org.eclipse.jst.javaee.jca.InboundResourceAdapter;
import org.eclipse.jst.javaee.jca.License;
import org.eclipse.jst.javaee.jca.MessageAdapter;
import org.eclipse.jst.javaee.jca.MessageListener;
import org.eclipse.jst.javaee.jca.OutboundResourceAdapter;
import org.eclipse.jst.javaee.jca.RequiredConfigProperty;
import org.eclipse.jst.javaee.jca.ResourceAdapter;
import org.eclipse.jst.javaee.jca.SecurityPermission;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage
 * @generated
 */
public interface JcaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JcaFactory eINSTANCE = org.eclipse.jst.javaee.jca.internal.impl.JcaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Activation Spec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activation Spec</em>'.
	 * @generated
	 */
	ActivationSpec createActivationSpec();

	/**
	 * Returns a new object of class '<em>Admin Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Admin Object</em>'.
	 * @generated
	 */
	AdminObject createAdminObject();

	/**
	 * Returns a new object of class '<em>Authentication Mechanism</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Authentication Mechanism</em>'.
	 * @generated
	 */
	AuthenticationMechanism createAuthenticationMechanism();

	/**
	 * Returns a new object of class '<em>Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Config Property</em>'.
	 * @generated
	 */
	ConfigProperty createConfigProperty();

	/**
	 * Returns a new object of class '<em>Connection Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Definition</em>'.
	 * @generated
	 */
	ConnectionDefinition createConnectionDefinition();

	/**
	 * Returns a new object of class '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector</em>'.
	 * @generated
	 */
	Connector createConnector();

	/**
	 * Returns a new object of class '<em>Connector Deployment Descriptor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Deployment Descriptor</em>'.
	 * @generated
	 */
	ConnectorDeploymentDescriptor createConnectorDeploymentDescriptor();

	/**
	 * Returns a new object of class '<em>Inbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Inbound Resource Adapter</em>'.
	 * @generated
	 */
	InboundResourceAdapter createInboundResourceAdapter();

	/**
	 * Returns a new object of class '<em>License</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>License</em>'.
	 * @generated
	 */
	License createLicense();

	/**
	 * Returns a new object of class '<em>Message Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Adapter</em>'.
	 * @generated
	 */
	MessageAdapter createMessageAdapter();

	/**
	 * Returns a new object of class '<em>Message Listener</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Message Listener</em>'.
	 * @generated
	 */
	MessageListener createMessageListener();

	/**
	 * Returns a new object of class '<em>Outbound Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outbound Resource Adapter</em>'.
	 * @generated
	 */
	OutboundResourceAdapter createOutboundResourceAdapter();

	/**
	 * Returns a new object of class '<em>Required Config Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Required Config Property</em>'.
	 * @generated
	 */
	RequiredConfigProperty createRequiredConfigProperty();

	/**
	 * Returns a new object of class '<em>Resource Adapter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Adapter</em>'.
	 * @generated
	 */
	ResourceAdapter createResourceAdapter();

	/**
	 * Returns a new object of class '<em>Security Permission</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Security Permission</em>'.
	 * @generated
	 */
	SecurityPermission createSecurityPermission();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JcaPackage getJcaPackage();

} //JcaFactory
