/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.internal.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.J2EEEObject;
import org.eclipse.jst.j2ee.jca.ActivationSpec;
import org.eclipse.jst.j2ee.jca.AdminObject;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.InboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.License;
import org.eclipse.jst.j2ee.jca.MessageAdapter;
import org.eclipse.jst.j2ee.jca.MessageListener;
import org.eclipse.jst.j2ee.jca.OutboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.SecurityPermission;



public class JcaAdapterFactory extends AdapterFactoryImpl {
	protected static JcaPackage modelPackage;

	public JcaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = (JcaPackage)EPackage.Registry.INSTANCE.getEPackage(JcaPackage.eNS_URI);
		}
	}
	@Override
	public boolean isFactoryForType(Object type) {
		if (type == modelPackage) {
			return true;
		}
		if (type instanceof EObject) {
			return ((EObject)type).eClass().eContainer() == modelPackage;
		}
		return false;
	}

	protected JcaSwitch sw = new JcaSwitch() {
		@Override
		public Object caseConnector(Connector object) {
			return createConnectorAdapter();
		}
		@Override
		public Object caseLicense(License object) {
			return createLicenseAdapter();
		}
		@Override
		public Object caseResourceAdapter(ResourceAdapter object) {
			return createResourceAdapterAdapter();
		}
		@Override
		public Object caseSecurityPermission(SecurityPermission object) {
			return createSecurityPermissionAdapter();
		}
		@Override
		public Object caseAuthenticationMechanism(AuthenticationMechanism object) {
			return createAuthenticationMechanismAdapter();
		}
		@Override
		public Object caseConfigProperty(ConfigProperty object) {
			return createConfigPropertyAdapter();
		}
	};

	@Override
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target); // sw
	}

	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	public Adapter createConnectorAdapter() {
		return null;
	}

	public Adapter createLicenseAdapter() {
		return null;
	}

	public Adapter createResourceAdapterAdapter() {
		return null;
	}

	public Adapter createSecurityPermissionAdapter() {
		return null;
	}

	public Adapter createAuthenticationMechanismAdapter() {
		return null;
	}

	public Adapter createConfigPropertyAdapter() {
		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean isFactoryForTypeGen(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JcaSwitch modelSwitch =
		new JcaSwitch() {
			@Override
			public Object caseConnector(Connector object) {
				return createConnectorAdapter();
			}
			@Override
			public Object caseResourceAdapter(ResourceAdapter object) {
				return createResourceAdapterAdapter();
			}
			@Override
			public Object caseAuthenticationMechanism(AuthenticationMechanism object) {
				return createAuthenticationMechanismAdapter();
			}
			@Override
			public Object caseConfigProperty(ConfigProperty object) {
				return createConfigPropertyAdapter();
			}
			@Override
			public Object caseSecurityPermission(SecurityPermission object) {
				return createSecurityPermissionAdapter();
			}
			@Override
			public Object caseLicense(License object) {
				return createLicenseAdapter();
			}
			@Override
			public Object caseInboundResourceAdapter(InboundResourceAdapter object) {
				return createInboundResourceAdapterAdapter();
			}
			@Override
			public Object caseOutboundResourceAdapter(OutboundResourceAdapter object) {
				return createOutboundResourceAdapterAdapter();
			}
			@Override
			public Object caseMessageAdapter(MessageAdapter object) {
				return createMessageAdapterAdapter();
			}
			@Override
			public Object caseConnectionDefinition(ConnectionDefinition object) {
				return createConnectionDefinitionAdapter();
			}
			@Override
			public Object caseAdminObject(AdminObject object) {
				return createAdminObjectAdapter();
			}
			@Override
			public Object caseMessageListener(MessageListener object) {
				return createMessageListenerAdapter();
			}
			@Override
			public Object caseActivationSpec(ActivationSpec object) {
				return createActivationSpecAdapter();
			}
			@Override
			public Object caseRequiredConfigPropertyType(RequiredConfigPropertyType object) {
				return createRequiredConfigPropertyTypeAdapter();
			}
			@Override
			public Object caseJ2EEEObject(J2EEEObject object) {
				return createJ2EEEObjectAdapter();
			}
			@Override
			public Object caseDescriptionGroup(DescriptionGroup object) {
				return createDescriptionGroupAdapter();
			}
			@Override
			public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
				return createCompatibilityDescriptionGroupAdapter();
			}
			@Override
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAdapterGen(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * By default create methods return null so that we can easily ignore cases.
	 * It's useful to ignore a case when inheritance will catch all the cases anyway.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createConnectorAdapterGen() {

		return null;
	}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLicenseAdapterGen() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.InboundResourceAdapter <em>Inbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.InboundResourceAdapter
	 * @generated
	 */
	public Adapter createInboundResourceAdapterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.OutboundResourceAdapter <em>Outbound Resource Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.OutboundResourceAdapter
	 * @generated
	 */
	public Adapter createOutboundResourceAdapterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.MessageAdapter <em>Message Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.MessageAdapter
	 * @generated
	 */
	public Adapter createMessageAdapterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.ConnectionDefinition <em>Connection Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.ConnectionDefinition
	 * @generated
	 */
	public Adapter createConnectionDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.AdminObject <em>Admin Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.AdminObject
	 * @generated
	 */
	public Adapter createAdminObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.MessageListener <em>Message Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.MessageListener
	 * @generated
	 */
	public Adapter createMessageListenerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.ActivationSpec <em>Activation Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.ActivationSpec
	 * @generated
	 */
	public Adapter createActivationSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType <em>Required Config Property Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType
	 * @generated
	 */
	public Adapter createRequiredConfigPropertyTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.J2EEEObject <em>J2EEE Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.J2EEEObject
	 * @generated
	 */
	public Adapter createJ2EEEObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.DescriptionGroup <em>Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.DescriptionGroup
	 * @generated
	 */
	public Adapter createDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup <em>Compatibility Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup
	 * @generated
	 */
	public Adapter createCompatibilityDescriptionGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createResourceAdapterAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createSecurityPermissionAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAuthenticationMechanismAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createConfigPropertyAdapterGen() {
		return null;
	}

} //JcaAdapterFactory
