/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.model.translator.connector;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.model.translator.common.BooleanTranslator;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.xml.RarDeploymentDescriptorXmlMapperI;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.wst.common.internal.emf.resource.ConstantAttributeTranslator;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;


public class ConnectorTranslator extends RootTranslator implements RarDeploymentDescriptorXmlMapperI, J2EEConstants {
	public static ConnectorTranslator INSTANCE = new ConnectorTranslator();
	public static final String CONNECTOR = "connector"; //$NON-NLS-1$
	private static JcaPackage CONNECTOR_PKG = JcaPackage.eINSTANCE;
	private static CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	private static Translator[] children13;
	private static Translator[] children14;
	
	private static final Translator VENDOR_NAME_TRANSLATOR = new Translator(VENDOR_NAME, CONNECTOR_PKG.getConnector_VendorName());
	private static final Translator EIS_TYPE_TRANSLATOR = new Translator(EIS_TYPE, CONNECTOR_PKG.getConnector_EisType());
	
	private static final Translator CONFIG_PROPERTY_NAME_TRANSLATOR= new Translator(CONFIG_PROPERTY_NAME, CONNECTOR_PKG.getConfigProperty_Name());
	private static final Translator CONFIG_PROPERTY_TYPE_TRANSLATOR= new Translator(CONFIG_PROPERTY_TYPE, CONNECTOR_PKG.getConfigProperty_Type());
	private static final Translator CONFIG_PROPERTY_VALUE_TRANSLATOR= new Translator(CONFIG_PROPERTY_VALUE, CONNECTOR_PKG.getConfigProperty_Value());
	
	private static final Translator SECURITY_PERMISSION_SPEC_TRANSLATOR = new Translator(SECURITY_PERMISSION_SPEC, CONNECTOR_PKG.getSecurityPermission_Specification());
	
	private static final Translator AUTH_MECH_TYPE_TRANSLATOR = new Translator(AUTH_MECH_TYPE, CONNECTOR_PKG.getAuthenticationMechanism_AuthenticationMechanismType());
	private static final Translator CREDENTIAL_INTERFACE_TRANSLATOR = new Translator(CREDENTIAL_INTERFACE, CONNECTOR_PKG.getAuthenticationMechanism_CredentialInterface());
	
	private static final BooleanTranslator LICENSE_REQUIRED_TRANSLATOR = new BooleanTranslator(LICENSE_REQUIRED, CONNECTOR_PKG.getLicense_Required());
	
	public ConnectorTranslator() {
		super(CONNECTOR, JcaPackage.eINSTANCE.getConnector());
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		switch (versionID) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :
				if (children13 == null)
					children13 = create13Children();
				return children13;	
			default :
				if (children14 == null)
					children14 = create14Children();
				return children14; 
		}
	}
	
	protected Translator[] create13Children() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName() ),
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()),
			new Translator(ICON+"/"+SMALL_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon() ), //$NON-NLS-1$
			new Translator(ICON+"/"+LARGE_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon() ), //$NON-NLS-1$
			VENDOR_NAME_TRANSLATOR,
			new Translator(SPEC_VERSION, CONNECTOR_PKG.getConnector_SpecVersion() ),
			EIS_TYPE_TRANSLATOR,
			new Translator(VERSION, CONNECTOR_PKG.getConnector_Version() ),
			createLicenseTranslator13(),
			createResourceAdapterTranslator13()
		};
	}
	
	protected Translator[] create14Children() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(VERSION, CONNECTOR_PKG.getConnector_SpecVersion(), DOM_ATTRIBUTE),
			new ConstantAttributeTranslator(XML_NS, J2EE_NS_URL),
			new ConstantAttributeTranslator(XML_NS_XSI, XSI_NS_URL),
			new ConstantAttributeTranslator(XSI_SCHEMA_LOCATION, J2EE_NS_URL+' '+CONNECTOR_SCHEMA_LOC_1_5),
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			VENDOR_NAME_TRANSLATOR,
			EIS_TYPE_TRANSLATOR,
			new Translator(RESOURCEADAPTER_VERSION, CONNECTOR_PKG.getConnector_Version()),
			createLicenseTranslator14(),
			createResourceAdapterTranslator14(),
		};
	}

	private Translator createResourceAdapterTranslator14() {
		GenericTranslator result = new GenericTranslator(RESOURCEADAPTER, CONNECTOR_PKG.getConnector_ResourceAdapter());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(RESOURCEADAPTER_CLASS,CONNECTOR_PKG.getResourceAdapter_ResourceAdapterClass()),
			createConfigurationPropertyTranslator14(CONNECTOR_PKG.getResourceAdapter_ConfigProperties()),
			createOutboundTranslator(),
			createInboundTranslator(),
			createAdminObjectTranslator(),
			createSecurityPermissionsTranslator()
		});
		return result;
	}

	private Translator createOutboundTranslator() {
		GenericTranslator result = new GenericTranslator(OUTBOUND_RESOURCEADAPTER, CONNECTOR_PKG.getResourceAdapter_OutboundResourceAdapter());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createConnectionDefinationTranslator(),
			new Translator(TRANSACTION_SUPPORT, CONNECTOR_PKG.getOutboundResourceAdapter_TransactionSupport()),
			createAuthMechanismTranslator14(CONNECTOR_PKG.getOutboundResourceAdapter_AuthenticationMechanisms()),
			new BooleanTranslator(REAUTHENTICATION_SUPPORT, CONNECTOR_PKG.getOutboundResourceAdapter_ReauthenticationSupport()),
		});
		return result;
	}

	private Translator createInboundTranslator() {
		GenericTranslator result = new GenericTranslator(INBOUND_RESOURCEADAPTER, CONNECTOR_PKG.getResourceAdapter_InboundResourceAdapter());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createMessageAdapterTranslator()
		});

		return result;
	}
	
	private Translator createMessageAdapterTranslator() {
		GenericTranslator result = new GenericTranslator(MESSAGEADAPTER, CONNECTOR_PKG.getInboundResourceAdapter_MessageAdapter());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createMessageListenerTranslator(),
		});

		return result;
	}
		
	private Translator createMessageListenerTranslator() {
		GenericTranslator result = new GenericTranslator(MESSAGELISTENER, CONNECTOR_PKG.getMessageAdapter_MessageListeners());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(MESSAGELISTENER_TYPE, CONNECTOR_PKG.getMessageListener_MessageListenerType()),
			createActivationSpecTranslator()
		});
		return result;
	}

	private Translator createActivationSpecTranslator() {
		GenericTranslator result = new GenericTranslator(ACTIVATIONSPEC, CONNECTOR_PKG.getMessageListener_ActivationSpec());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(ACTIVATIONSPEC_CLASS, CONNECTOR_PKG.getActivationSpec_ActivationSpecClass()),
			createRequiredConfigPropertyTranslator()
		});
		return result;
	}

	private Translator createRequiredConfigPropertyTranslator() {
		GenericTranslator result = new GenericTranslator(REQUIRED_CONFIG_PROPERTY, CONNECTOR_PKG.getActivationSpec_RequiredConfigProperties());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.createDescriptionTranslator(CONNECTOR_PKG.getRequiredConfigPropertyType_Descriptions()),
			new Translator(CONFIG_PROPERTY_NAME, CONNECTOR_PKG.getRequiredConfigPropertyType_Name())
		});
		return result;
	}
	
	private Translator createAdminObjectTranslator() {
		GenericTranslator result = new GenericTranslator(ADMINOBJECT, CONNECTOR_PKG.getResourceAdapter_AdminObjects());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(ADMINOBJECT_INTERFACE, CONNECTOR_PKG.getAdminObject_AdminObjectInterface()),
			new Translator(ADMINOBJECT_CLASS, CONNECTOR_PKG.getAdminObject_AdminObjectClass()),
			createConfigurationPropertyTranslator14(CONNECTOR_PKG.getAdminObject_ConfigProperties())
			
		});
		return result;
	}

	private Translator createConnectionDefinationTranslator() {
		GenericTranslator result = new GenericTranslator(CONNECTION_DEFINITION, CONNECTOR_PKG.getOutboundResourceAdapter_ConnectionDefinitions());
		result.setChildren(new Translator[] {
			new Translator(MANAGEDCONNECTIONFACTORY_CLASS, CONNECTOR_PKG.getConnectionDefinition_ManagedConnectionFactoryClass()),
			createConfigurationPropertyTranslator14(CONNECTOR_PKG.getConnectionDefinition_ConfigProperties()),
			new Translator(CONNECTIONFACTORY_INTERFACE, CONNECTOR_PKG.getConnectionDefinition_ConnectionFactoryInterface()),
			new Translator(CONNECTIONFACTORY_IMPL_CLASS, CONNECTOR_PKG.getConnectionDefinition_ConnectionFactoryImplClass()),
			new Translator(CONNECTION_INTERFACE, CONNECTOR_PKG.getConnectionDefinition_ConnectionInterface()),
			new Translator(CONNECTION_IMPL_CLASS, CONNECTOR_PKG.getConnectionDefinition_ConnectionImplClass()),
		});
		return result;
	}

	public Translator createResourceAdapterTranslator13() {
		GenericTranslator result = new GenericTranslator(RESOURCEADAPTER, CONNECTOR_PKG.getConnector_ResourceAdapter());
		result.setChildren(new Translator[] {
			new Translator(MANAGEDCONNECTIONFACTORY_CLASS, CONNECTOR_PKG.getResourceAdapter_ManagedConnectionFactoryClass() ),
			new Translator(CONNECTIONFACTORY_INTERFACE, CONNECTOR_PKG.getResourceAdapter_ConnectionFactoryInterface() ),
			new Translator(CONNECTIONFACTORY_IMPL_CLASS, CONNECTOR_PKG.getResourceAdapter_ConnectionFactoryImplClass() ),
			new Translator(CONNECTION_INTERFACE, CONNECTOR_PKG.getResourceAdapter_ConnectionInterface()),
			new Translator(CONNECTION_IMPL_CLASS, CONNECTOR_PKG.getResourceAdapter_ConnectionImplClass()),
			new Translator(TRANSACTION_SUPPORT, CONNECTOR_PKG.getResourceAdapter_TransactionSupport() ),
			createConfigurationPropertyTranslator13(CONNECTOR_PKG.getResourceAdapter_ConfigProperties()),
			createAuthMechanismTranslator13(CONNECTOR_PKG.getResourceAdapter_AuthenticationMechanisms()),
			new BooleanTranslator(REAUTHENTICATION_SUPPORT, CONNECTOR_PKG.getResourceAdapter_ReauthenticationSupport()),
			createSecurityPermissionsTranslator()
			
			
		});
		return result;
	}
	
	public Translator createSecurityPermissionsTranslator() {
		GenericTranslator result = new GenericTranslator(SECURITY_PERMISSION, CONNECTOR_PKG.getResourceAdapter_SecurityPermissions());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, CONNECTOR_PKG.getSecurityPermission_Description()),
			SECURITY_PERMISSION_SPEC_TRANSLATOR
		});
		return result;
	}
	
	public Translator createAuthMechanismTranslator13(EStructuralFeature afeature) {
		GenericTranslator result = new GenericTranslator(AUTH_MECHANISM, afeature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, CONNECTOR_PKG.getAuthenticationMechanism_Description()), 
			AUTH_MECH_TYPE_TRANSLATOR,
			CREDENTIAL_INTERFACE_TRANSLATOR
		});
		return result;
	}
	
	public Translator createAuthMechanismTranslator14(EStructuralFeature afeature) {
		GenericTranslator result = new GenericTranslator(AUTH_MECHANISM, afeature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.createDescriptionTranslator(CONNECTOR_PKG.getAuthenticationMechanism_Descriptions()), 
			AUTH_MECH_TYPE_TRANSLATOR,
			CREDENTIAL_INTERFACE_TRANSLATOR
		});
		return result;
	}
	
	
	public Translator createConfigurationPropertyTranslator13(EStructuralFeature afeature) {
		GenericTranslator result = new GenericTranslator(CONFIG_PROPERTY, afeature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, CONNECTOR_PKG.getConfigProperty_Description()),
			CONFIG_PROPERTY_NAME_TRANSLATOR,
			CONFIG_PROPERTY_TYPE_TRANSLATOR,
			CONFIG_PROPERTY_VALUE_TRANSLATOR
		});
		return result;
	}
	
	public Translator createConfigurationPropertyTranslator14(EStructuralFeature afeature) {
		GenericTranslator result = new GenericTranslator(CONFIG_PROPERTY, afeature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.createDescriptionTranslator(CONNECTOR_PKG.getConfigProperty_Descriptions()),
			CONFIG_PROPERTY_NAME_TRANSLATOR,
			CONFIG_PROPERTY_TYPE_TRANSLATOR,
			CONFIG_PROPERTY_VALUE_TRANSLATOR
		});
		return result;
	}
	
	public Translator createLicenseTranslator13() {
		GenericTranslator result = new GenericTranslator(LICENSE, CONNECTOR_PKG.getConnector_License());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, CONNECTOR_PKG.getLicense_Description()), 
			LICENSE_REQUIRED_TRANSLATOR
		});
		return result;
	}
	
	public Translator createLicenseTranslator14() {
		GenericTranslator result = new GenericTranslator(LICENSE, CONNECTOR_PKG.getConnector_License());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.createDescriptionTranslator(CONNECTOR_PKG.getLicense_Descriptions()), 
			LICENSE_REQUIRED_TRANSLATOR
		});
		return result;
	}
}
