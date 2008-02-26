/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 20, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.common;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.xml.DeploymentDescriptorXmlMapperI;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.wst.common.internal.emf.resource.DependencyTranslator;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * A static helper for creating Generic common J2EE translators
 */
public class CommonTranslators implements DeploymentDescriptorXmlMapperI {

	private static CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	private static Webservice_clientPackage WEBSERVICE_PKG = Webservice_clientPackage.eINSTANCE;
	public static final Translator[] EMPTY_CHILDREN = new Translator[] {};
	private static final Translator EJB_REF_NAME_TRANSLATOR = new Translator(EJB_REF_NAME, COMMON_PKG.getEjbRef_Name());
	private static final Translator EJB_REF_TYPE_TRANSLATOR = new Translator(EJB_REF_TYPE, COMMON_PKG.getEjbRef_Type());		//Enumeration
	private static final Translator EJB_LINK_TRANSLATOR = new Translator(EJB_LINK, COMMON_PKG.getEjbRef_Link());
	
	private static final Translator RES_REF_NAME_TRANSLATOR = new Translator(RES_REF_NAME, COMMON_PKG.getResourceRef_Name());
	private static final Translator RES_TYPE_TRANSLATOR = new Translator(RES_TYPE, COMMON_PKG.getResourceRef_Type());
	private static final Translator RES_AUTH_TRANSLATOR = new ResAuthTranslator();
	private static final Translator RES_SHARING_SCOPE_TRANSLATOR = new Translator(RES_SHARING_SCOPE, COMMON_PKG.getResourceRef_ResSharingScope());
	
	private static final Translator RES_ENV_REF_NAME_TRANSLATOR = new Translator(RESOURCE_ENV_REF_NAME, COMMON_PKG.getResourceEnvRef_Name());
	private static final Translator RES_ENV_REF_TYPE_TRANSLATOR = new JavaClassTranslator(RESOURCE_ENV_REF_TYPE, COMMON_PKG.getResourceEnvRef_Type());
	
	private static final Translator SEC_REF_ROLE_NAME_TRANSALTOR = new Translator(ROLE_NAME, COMMON_PKG.getSecurityRoleRef_Name());
	private static final Translator SEC_REF_ROLE_LINK_TRANSLATOR = new Translator(ROLE_LINK, COMMON_PKG.getSecurityRoleRef_Link());
	
	private static final Translator RUNAS_ROLE_NAME_TRANSLATOR = new DependencyTranslator(ROLE_NAME, COMMON_PKG.getIdentity_RoleName(), COMMON_PKG.getRunAsSpecifiedIdentity_Identity());
	
	private static final Translator SECURITY_ROLE_NAME_TRANSLATOR = new Translator(ROLE_NAME, COMMON_PKG.getSecurityRole_RoleName());
	
	public static final Translator DESCRIPTIONS_TRANSLATOR = createDescriptionTranslator(COMMON_PKG.getDescriptionGroup_Descriptions());
	public static final Translator DISPLAYNAMES_TRANSLATOR = createDisplayNameTranslator(COMMON_PKG.getDescriptionGroup_DisplayNames());
	public static final Translator ICONS_TRANSLATOR = createIconTranslator();
	
	//The Official Concept of JNDIEnvRefGroup FOR J2EE 1.4
	public static final Translator JNDI_REF_GROUP_ENV_ENTRY_1_4 = new EnvEntryTranslator(COMMON_PKG.getJNDIEnvRefsGroup_EnvironmentProperties(), true, true);
	public static final Translator JNDI_REF_GROUP_EJB_REF_1_4 = CommonTranslators.createEJBRefTranslator14(COMMON_PKG.getJNDIEnvRefsGroup_EjbRefs());
	public static final Translator JNDI_REF_GROUP_EJB_LOCAL_REF_1_4 = CommonTranslators.createEJBLocalRefTranslator14(COMMON_PKG.getJNDIEnvRefsGroup_EjbLocalRefs());
	public static final Translator JNDI_REF_GROUP_SERVICE_REF_1_4 = CommonTranslators.createServiceRefGroupTranslator(COMMON_PKG.getJNDIEnvRefsGroup_ServiceRefs());	
	public static final Translator JNDI_REF_GROUP_RESOURCE_REF_1_4 = CommonTranslators.createResourceRefTranslator14(COMMON_PKG.getJNDIEnvRefsGroup_ResourceRefs());
	public static final Translator JNDI_REF_GROUP_RESOURCE_ENV_REF_1_4 = CommonTranslators.createResourceEnvRefTranslator14(COMMON_PKG.getJNDIEnvRefsGroup_ResourceEnvRefs());
	public static final Translator JNDI_REF_GROUP_MESSAGE_DEST_REF_1_4 = CommonTranslators.createMessageDestinationRefTranslator(COMMON_PKG.getJNDIEnvRefsGroup_MessageDestinationRefs());
	
	//The unofficial concept of JNDIEnvRefGroup FOR J2EE 1.3
	public static final Translator JNDI_REF_GROUP_ENV_ENTRY_1_3 = new EnvEntryTranslator(COMMON_PKG.getJNDIEnvRefsGroup_EnvironmentProperties(), true, false);
	public static final Translator JNDI_REF_GROUP_EJB_REF_1_3 = CommonTranslators.createEJBRefTranslator13(COMMON_PKG.getJNDIEnvRefsGroup_EjbRefs());
	public static final Translator JNDI_REF_GROUP_EJB_LOCAL_REF_1_3 = CommonTranslators.createEJBLocalRefTranslator13(COMMON_PKG.getJNDIEnvRefsGroup_EjbLocalRefs());
	public static final Translator JNDI_REF_GROUP_RESOURCE_REF_1_3 =  CommonTranslators.createResourceRefTranslator13(COMMON_PKG.getJNDIEnvRefsGroup_ResourceRefs());
	public static final Translator JNDI_REF_GROUP_RESOURCE_ENV_REF_1_3 = CommonTranslators.createResourceEnvRefTranslator13(COMMON_PKG.getJNDIEnvRefsGroup_ResourceEnvRefs());
	
	public static final Translator LISTENER_CLASS_TRANSLATOR = new JavaClassTranslator(LISTENER_CLASS, COMMON_PKG.getListener_ListenerClass());
	
	private CommonTranslators() {
		super();
	}
	
	public static Translator createSecurityRoleRefTranslator13(EStructuralFeature feature) {
		
		GenericTranslator result = new GenericTranslator(SECURITY_ROLE_REF, feature);
		result.setChildren(new Translator[] {
			new Translator(DESCRIPTION, COMMON_PKG.getSecurityRoleRef_Description()), 
			SEC_REF_ROLE_NAME_TRANSALTOR, 
			SEC_REF_ROLE_LINK_TRANSLATOR 
		});
		return result;
	}
	
	public static Translator createSecurityRoleRefTranslator14(EStructuralFeature feature) {
		
		GenericTranslator result = new GenericTranslator(SECURITY_ROLE_REF, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createDescriptionTranslator(COMMON_PKG.getSecurityRoleRef_Descriptions()), 
			SEC_REF_ROLE_NAME_TRANSALTOR,
			SEC_REF_ROLE_LINK_TRANSLATOR
		});
		return result;
	}
	
	public static Translator createResourceEnvRefTranslator13(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(RESOURCE_ENV_REF, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE, 
			new Translator(DESCRIPTION, COMMON_PKG.getResourceEnvRef_Description()), 
			RES_ENV_REF_NAME_TRANSLATOR, 
			RES_ENV_REF_TYPE_TRANSLATOR
		});
		return result;
	}
	
	public static Translator createResourceEnvRefTranslator14(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(RESOURCE_ENV_REF, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE, 
			createDescriptionTranslator(COMMON_PKG.getResourceEnvRef_Descriptions()), 
			RES_ENV_REF_NAME_TRANSLATOR, 
			RES_ENV_REF_TYPE_TRANSLATOR,
			//J2EE1.4 Added
		//	createDeploymentExtensionTranslator(COMMON_PKG.getResourceEnvRef_Extensions())
		});
		return result;
	}
	
	public static Translator createResourceRefTranslator13(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(RESOURCE_REF, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,		
			new Translator(DESCRIPTION, COMMON_PKG.getResourceRef_Description()),
			RES_REF_NAME_TRANSLATOR,
			RES_TYPE_TRANSLATOR,
			RES_AUTH_TRANSLATOR,
			//EJB 2.0 Added
			RES_SHARING_SCOPE_TRANSLATOR,
		});
		return result;
	}
	
	public static Translator createResourceRefTranslator14(EStructuralFeature feature) {
			GenericTranslator result = new GenericTranslator(RESOURCE_REF, feature);
			result.setChildren(new Translator[] {
				IDTranslator.INSTANCE,		
				createDescriptionTranslator(COMMON_PKG.getResourceRef_Descriptions()),
				RES_REF_NAME_TRANSLATOR,
				RES_TYPE_TRANSLATOR,
				RES_AUTH_TRANSLATOR,
				//EJB 2.0 Added
				RES_SHARING_SCOPE_TRANSLATOR,
			
				//J2EE1.4 Added
			//	createDeploymentExtensionTranslator(COMMON_PKG.getResourceRef_Extensions())
			});
			return result;
		}
	
	public static Translator createSecurityRoleTranslator13(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(SECURITY_ROLE, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, COMMON_PKG.getSecurityRole_Description()), 
			SECURITY_ROLE_NAME_TRANSLATOR 
		});
		return result;
	}
	
	public static Translator createSecurityRoleTranslator14(EStructuralFeature feature) {
			GenericTranslator result = new GenericTranslator(SECURITY_ROLE, feature);
			result.setChildren(new Translator[] {
				IDTranslator.INSTANCE,
				createDescriptionTranslator(COMMON_PKG.getSecurityRole_Descriptions()), 
				SECURITY_ROLE_NAME_TRANSLATOR 
			});
			return result;
		}
	
	public static Translator createEJBRefTranslator13(EStructuralFeature feature) {
		return createEJBRefTranslator13(feature, false);
	}
	
	public static Translator createEJBLocalRefTranslator13(EStructuralFeature feature) {
		return createEJBRefTranslator13(feature, true);
	}
	private static Translator createEJBRefTranslator13(EStructuralFeature feature, boolean local) {
		String nodeName = local ? EJB_LOCAL_REF : EJB_REF;
		String homeTag = local ? LOCAL_HOME : HOME;
		String remoteTag = local ? LOCAL : REMOTE;
		EStructuralFeature homeFeature = local ? COMMON_PKG.getEJBLocalRef_LocalHome() : COMMON_PKG.getEjbRef_Home();
		EStructuralFeature remoteFeature = local ? COMMON_PKG.getEJBLocalRef_Local() : COMMON_PKG.getEjbRef_Remote();
		
		GenericTranslator result = new GenericTranslator(nodeName, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,				
			new Translator(DESCRIPTION, COMMON_PKG.getEjbRef_Description()),
			EJB_REF_NAME_TRANSLATOR,
			EJB_REF_TYPE_TRANSLATOR,
			new Translator(homeTag, homeFeature),
			new Translator(remoteTag, remoteFeature),
			EJB_LINK_TRANSLATOR
		});
		return result;
	}
	
	public static Translator createEJBRefTranslator14(EStructuralFeature feature) {
		return createEJBRefTranslator14(feature, false);
	}
	
	public static Translator createEJBLocalRefTranslator14(EStructuralFeature feature) {
		return createEJBRefTranslator14(feature, true);
	}
	private static Translator createEJBRefTranslator14(EStructuralFeature feature, boolean local) {
		String nodeName = local ? EJB_LOCAL_REF : EJB_REF;
		String homeTag = local ? LOCAL_HOME : HOME;
		String remoteTag = local ? LOCAL : REMOTE;
		EStructuralFeature homeFeature = local ? COMMON_PKG.getEJBLocalRef_LocalHome() : COMMON_PKG.getEjbRef_Home();
		EStructuralFeature remoteFeature = local ? COMMON_PKG.getEJBLocalRef_Local() : COMMON_PKG.getEjbRef_Remote();
		
		GenericTranslator result = new GenericTranslator(nodeName, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,				
			createDescriptionTranslator(COMMON_PKG.getEjbRef_Descriptions()),
			EJB_REF_NAME_TRANSLATOR,
			EJB_REF_TYPE_TRANSLATOR,
			new Translator(homeTag, homeFeature),
			new Translator(remoteTag, remoteFeature),
			EJB_LINK_TRANSLATOR,
		//	createDeploymentExtensionTranslator(COMMON_PKG.getEjbRef_Extensions())
		});
		return result;
	}
	
	
	public static Translator createRunAsTranslator13(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(RUN_AS, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, COMMON_PKG.getSecurityIdentity_Description()),
			//new DependencyTranslator(DES,)
			RUNAS_ROLE_NAME_TRANSLATOR
		});
		return result;
	}


	public static Translator createRunAsTranslator14(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(RUN_AS, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createDescriptionTranslator(COMMON_PKG.getSecurityIdentity_Descriptions()),
			RUNAS_ROLE_NAME_TRANSLATOR
		});
		return result;
	}
	
	
	
	public static  Translator createMessageDestinationRefTranslator(EStructuralFeature feature){
		GenericTranslator result = new GenericTranslator(MESSAGE_DEST_REF,feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createDescriptionTranslator(COMMON_PKG.getMessageDestinationRef_Descriptions()),
			new Translator(MESSAGE_DEST_REF_NAME,COMMON_PKG.getMessageDestinationRef_Name()),
			new Translator(MESSAGE_DEST_TYPE,COMMON_PKG.getMessageDestinationRef_Type()),
			new Translator(MESSAGE_DEST_USAGE,COMMON_PKG.getMessageDestinationRef_Usage()),
			new Translator(MESSAGE_DEST_LINK,COMMON_PKG.getMessageDestinationRef_Link()),
			//createDeploymentExtensionTranslator(COMMON_PKG.getMessageDestinationRef_Extensions())	
		});

		return result;
	}
	
	public static  Translator createMessageDestinationTranslator(EStructuralFeature feature){
		GenericTranslator result = new GenericTranslator(MESSAGE_DEST,feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			DESCRIPTIONS_TRANSLATOR,
			DISPLAYNAMES_TRANSLATOR,
			ICONS_TRANSLATOR,
			new Translator(MESSAGE_DEST_NAME,COMMON_PKG.getMessageDestination_Name()),
			//createDeploymentExtensionTranslator(COMMON_PKG.getMessageDestination_Extensions())
		});

		return result;
	}

	/**
	 * This model was model from Websphere Webservices
	 * @param reference
	 * @return
	 */
	public static Translator createServiceRefGroupTranslator(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(SERVICE_REF, feature);
		result.setChildren( new Translator[] {
			IDTranslator.INSTANCE,
			DESCRIPTIONS_TRANSLATOR,
			DISPLAYNAMES_TRANSLATOR,
			ICONS_TRANSLATOR,
			new Translator(SERVICE_REF_NAME, WEBSERVICE_PKG.getServiceRef_ServiceRefName()),
			new JavaClassTranslator(SERVICE_INTERFACE, WEBSERVICE_PKG.getServiceRef_ServiceInterface()),
			new Translator(WSDL_FILE, WEBSERVICE_PKG.getServiceRef_WsdlFile()),
			new Translator(JAXRPC_MAPPING_FILE, WEBSERVICE_PKG.getServiceRef_JaxrpcMappingFile()),
			createQNameTranslator(SERVICE_QNAME, WEBSERVICE_PKG.getServiceRef_ServiceQname()),
			createPortComponentRefTranslator(),
			createServiceRefHandlerTranslator(),
		});
		
		
		return result;
	}
	
	public static Translator createQNameTranslator(String domName, EStructuralFeature feature) {		
		GenericTranslator result = new GenericTranslator(domName, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(Translator.TEXT_ATTRIBUTE_VALUE, CommonPackage.eINSTANCE.getQName_CombinedQName())			
		});
		return result;	
	}

	/**
	 * This model was model from Websphere Webservices
	 * @return
	 */
	public static Translator createServiceRefHandlerTranslator() {
		GenericTranslator result = new GenericTranslator(HANDLER, WEBSERVICE_PKG.getServiceRef_Handlers());
		result.setChildren(new Translator[]{
			IDTranslator.INSTANCE,
			DESCRIPTIONS_TRANSLATOR,
			DISPLAYNAMES_TRANSLATOR,
			ICONS_TRANSLATOR,
			new Translator(HANDLER_NAME, WEBSERVICE_PKG.getHandler_HandlerName()),
			new JavaClassTranslator(HANDLER_CLASS, WEBSERVICE_PKG.getHandler_HandlerClass()),
			createParamValueType(HANDLER_INIT_PARAM, WEBSERVICE_PKG.getHandler_InitParams()),
			createQNameTranslator(SOAP_HEADER, WEBSERVICE_PKG.getHandler_SoapHeaders()),
			new Translator(SOAP_ROLE, WEBSERVICE_PKG.getHandler_SoapRoles()),
			new Translator(PORT_NAME, WEBSERVICE_PKG.getHandler_PortNames())
		});
		
		return result;
	}

	/**
	 * @return
	 */
	public static Translator createPortComponentRefTranslator() {
		GenericTranslator result = new GenericTranslator(PORT_COMPONENT_REF, WEBSERVICE_PKG.getServiceRef_PortComponentRefs());
		result.setChildren( new Translator[]{
			IDTranslator.INSTANCE,
			new JavaClassTranslator(SERVICE_ENPOINT_INTERFACE, WEBSERVICE_PKG.getPortComponentRef_ServiceEndpointInterface()),
			new Translator(PORT_COMPONENT_LINK , WEBSERVICE_PKG.getPortComponentRef_PortComponentLink())
		});
		return result;
	}

	/**
	 * @return
	 */
	public static Translator createDescriptionTranslator(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(DESCRIPTION, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(LANG, COMMON_PKG.getDescription_Lang(), Translator.DOM_ATTRIBUTE),
			new Translator(Translator.TEXT_ATTRIBUTE_VALUE, COMMON_PKG.getDescription_Value()) 
		});
		return result;
	}
	
	/**
	 * @return
	 */
	public static Translator createDisplayNameTranslator(EStructuralFeature feature) {
		GenericTranslator result = new GenericTranslator(DISPLAY_NAME, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(LANG, COMMON_PKG.getDisplayName_Lang(), Translator.DOM_ATTRIBUTE),
			new Translator(Translator.TEXT_ATTRIBUTE_VALUE, COMMON_PKG.getDisplayName_Value()) 
		});
		return result;
	}

	
	/**
	 * @return
	 */
	public static Translator createIconTranslator() {
		GenericTranslator result = new GenericTranslator(ICON, COMMON_PKG.getDescriptionGroup_Icons());
		result.setChildren(new Translator[] {
			new Translator(LANG, COMMON_PKG.getIconType_Lang(), Translator.DOM_ATTRIBUTE),
			IDTranslator.INSTANCE,
			new Translator(SMALL_ICON, COMMON_PKG.getIconType_SmallIcon()), 
			new Translator(LARGE_ICON, COMMON_PKG.getIconType_LargeIcon()) 
		});
		return result;
	}

//	public static Translator createDeploymentExtensionTranslator(EStructuralFeature feature) {
//		return createDeploymentExtensionTranslator(DEPLOY_EXT, feature);
//	}
//
//	public static Translator createDeploymentExtensionTranslator(String domName, EStructuralFeature feature) {
//		GenericTranslator result = new GenericTranslator(domName, feature);
//		result.setChildren(new Translator[] {
//			new Translator(NAMESPACE, COMMON_PKG.getDeploymentExtension_Namespace(), Translator.DOM_ATTRIBUTE),
//			new Translator(MUSTUNDERSTAND, COMMON_PKG.getDeploymentExtension_MustUnderstand(), Translator.DOM_ATTRIBUTE),
//			IDTranslator.INSTANCE,
//			Translator.createParentAndTextAttributeTranslator(EXT_ELEMENT, COMMON_PKG.getDeploymentExtension_ExtensionElements(), COMMON_PKG.getExtensibleType_Value())
//		});
//		return result;
//	}
	
	public static Translator createParamValueType(String domName, EStructuralFeature feature){
		GenericTranslator result = new GenericTranslator(domName, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			createDescriptionTranslator(COMMON_PKG.getParamValue_Descriptions()),
			new Translator(PARAM_NAME, COMMON_PKG.getParamValue_Name()),
			new Translator(PARAM_VALUE, COMMON_PKG.getParamValue_Value()),
		});
		return result;
	}


}
