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
/*
 * Created on Mar 27, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.ConstantAttributeTranslator;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.SourceLinkTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorPath;

/**
 * @author schacher
 */
public class EJBJarTranslator extends RootTranslator implements EjbDeploymentDescriptorXmlMapperI, J2EEConstants {
	private static final EjbPackage EJB_PKG = EjbPackage.eINSTANCE;
	private static CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	public static EJBJarTranslator INSTANCE = new EJBJarTranslator();
	private static Translator[] children13;
	private static Translator[] children14;

	public static TranslatorPath ENTERPRISE_BEAN_NAME_TRANSLATOR_PATH =
			new TranslatorPath(
				new Translator[] {
					new Translator(EJB_JAR, ROOT_FEATURE),
					new Translator(EnterpriseBeansTranslator.ENTERPRISE_BEANS_PATH, EJB_PKG.getEJBJar_EnterpriseBeans()),
					new Translator(EJB_NAME, EJB_PKG.getEnterpriseBean_Name())});


	private static final Translator CMR_FIELD_NAME_TRANSLATOR = new Translator(CMR_FIELD_NAME, EcorePackage.eINSTANCE.getENamedElement_Name());
	private static final Translator CMR_FIELD_TYPE_TRANSLATOR = new JavaClassTranslator(CMR_FIELD_TYPE, EJB_PKG.getCMRField_CollectionType());
	private static final Translator ROLE_SOURCE_EJB_NAME_SOURCELINK_TRANSLATOR = new SourceLinkTranslator(EJB_NAME, EJB_PKG.getRoleSource_EntityBean(), ENTERPRISE_BEAN_NAME_TRANSLATOR_PATH);
	private static final Translator EJB_RELATIONSHIP_ROLE_NAME_TRANSLATOR = new Translator(EJB_RELATIONSHIP_ROLE_NAME, EJB_PKG.getEJBRelationshipRole_RoleName());
	private static final Translator MULTIPLICITY_TRANSLATOR = new MultiplicityTranslator();
	private static final Translator CASCADE_DELETE_TRANSLATOR = new Translator(CASCADE_DELETE, EJB_PKG.getEJBRelationshipRole_CascadeDelete(), Translator.EMPTY_TAG);
	private static final Translator EJB_RELATION_NAME_TRANSLATOR = new Translator(EJB_RELATION_NAME, EJB_PKG.getEJBRelation_Name());
	private static final Translator UNCHECKED_TRANSLATOR = new Translator(UNCHECKED, EJB_PKG.getMethodPermission_Unchecked(), Translator.EMPTY_TAG);
	private static final Translator METHOD_PERMISSION_ROLE_NAME_SOURCELINK_TRANSLATOR = new SourceLinkTranslator(ROLE_NAME,EJB_PKG.getMethodPermission_Roles(),new TranslatorPath(
		new Translator[] {
			new Translator(ASSEMBLY_DESCRIPTOR, CONTAINER_FEATURE),
			new Translator(SECURITY_ROLE, EJB_PKG.getAssemblyDescriptor_SecurityRoles()),
			new Translator(ROLE_NAME, CommonPackage.eINSTANCE.getSecurityRole_RoleName())
		}));
	private static final Translator SEC_ROLE_NAME_TRANSLATOR = new Translator(ROLE_NAME, CommonPackage.eINSTANCE.getSecurityRole_RoleName(),	new TranslatorPath(
		new Translator[] {
			new Translator(ASSEMBLY_DESCRIPTOR, CONTAINER_FEATURE),
			new Translator(METHOD_PERMISSION, EJB_PKG.getAssemblyDescriptor_MethodPermissions()),
			new Translator(SECURITY_ROLE, EJB_PKG.getMethodPermission_Roles()),
			new Translator(ROLE_NAME, CommonPackage.eINSTANCE.getSecurityRole_RoleName())
		}));
	
	private static final Translator TRANS_ATTRIBUTE_TRANSLATOR = new Translator(TRANS_ATTRIBUTE, EJB_PKG.getMethodTransaction_TransactionAttribute());
	private static final Translator METHOD_ELEMENT_EJB_NAME_SOURCELINK_TRANSLATOR = new SourceLinkTranslator(EJB_NAME, EJB_PKG.getMethodElement_EnterpriseBean(), EJBJarTranslator.ENTERPRISE_BEAN_NAME_TRANSLATOR_PATH);
	private static final Translator METHOD_NAME_TRANSLATOR = new Translator(METHOD_NAME, EJB_PKG.getMethodElement_Name());
	private static final Translator METHOD_PARAM_TRANSLATOR = new MethodParamsTranslator(EJB_PKG.getMethodElement_Parms());
	private static final Translator METHOD_ELEMENT_KIND_TRANSLATOR = new MethodElementKindTranslator();
	
	public EJBJarTranslator() {
		super(EJB_JAR, EJB_PKG.getEJBJar());
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
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()),
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName()),
			new Translator(SMALL_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon()),
			new Translator(LARGE_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon()),
			new EnterpriseBeansTranslator(),
			createRelationshipsTranslator13(),
			createAssemblyDescriptorTranslator13(),
			new Translator(EJB_CLIENT_JAR, EJB_PKG.getEJBJar_EjbClientJar()),
		};
	}

	protected Translator[] create14Children() {
		return	new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(VERSION, EJB_PKG.getEJBJar_Version(), DOM_ATTRIBUTE),
			new ConstantAttributeTranslator(XML_NS, J2EE_NS_URL),
			new ConstantAttributeTranslator(XML_NS_XSI, XSI_NS_URL),
			new ConstantAttributeTranslator(XSI_SCHEMA_LOCATION, J2EE_NS_URL+' '+EJB_JAR_SCHEMA_LOC_2_1),
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			new EnterpriseBeansTranslator(),
			createRelationshipsTranslator14(),
			createAssemblyDescriptorTranslator14(),
			new Translator(EJB_CLIENT_JAR, EJB_PKG.getEJBJar_EjbClientJar()),
		//	CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getEJBJar_Extensions())
		};
	}

	public static Translator createRelationshipsTranslator13() {
		GenericTranslator result = new GenericTranslator(RELATIONSHIPS, EJB_PKG.getEJBJar_RelationshipList());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getRelationships_Description()),
				createEJBRelationsTranslator13(),
		});
		return result;
	}

	private static Translator createEJBRelationsTranslator13() {
		GenericTranslator result = new GenericTranslator(EJB_RELATION, EJB_PKG.getRelationships_EjbRelations());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getEJBRelation_Description()),
				EJB_RELATION_NAME_TRANSLATOR,
				createEJBRelationshipRoleTranslator13(),
		});
		return result;
	}

	private static Translator createEJBRelationshipRoleTranslator13() {
		GenericTranslator result = new GenericTranslator(EJB_RELATIONSHIP_ROLE, EJB_PKG.getEJBRelation_RelationshipRoles());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getEJBRelationshipRole_Description()),
				EJB_RELATIONSHIP_ROLE_NAME_TRANSLATOR,
				MULTIPLICITY_TRANSLATOR,
				CASCADE_DELETE_TRANSLATOR,
				createRelationshipRoleSourceTranslator13(),
				createCMRFieldTranslator13(),
		});
		return result;
	}

	private static Translator createRelationshipRoleSourceTranslator13() {
		GenericTranslator result = new GenericTranslator(RELATIONSHIP_ROLE_SOURCE, EJB_PKG.getEJBRelationshipRole_Source());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getRoleSource_Description()),
				ROLE_SOURCE_EJB_NAME_SOURCELINK_TRANSLATOR,
		});
		return result;
	}

	private static Translator createCMRFieldTranslator13() {
		GenericTranslator result = new GenericTranslator(CMR_FIELD, EJB_PKG.getEJBRelationshipRole_CmrField());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getCMPAttribute_Description()),
				CMR_FIELD_NAME_TRANSLATOR,
				CMR_FIELD_TYPE_TRANSLATOR
		});
		return result;
	}


	/**
	 * J2EE 1.4 Relationship
	 * 
	 */
	public static Translator createRelationshipsTranslator14() {
		GenericTranslator result = new GenericTranslator(RELATIONSHIPS, EJB_PKG.getEJBJar_RelationshipList());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getRelationships_Descriptions()),
				createEJBRelationsTranslator14(),
		//		CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getRelationships_Extensions())
		});
		return result;
	}

	private static Translator createEJBRelationsTranslator14() {
		GenericTranslator result = new GenericTranslator(EJB_RELATION, EJB_PKG.getRelationships_EjbRelations());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getEJBRelation_Descriptions()),
				EJB_RELATION_NAME_TRANSLATOR,
				createEJBRelationshipRoleTranslator14(),
		//		CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getEJBRelation_Extensions())
		});
		return result;
	}

	private static Translator createEJBRelationshipRoleTranslator14() {
		GenericTranslator result = new GenericTranslator(EJB_RELATIONSHIP_ROLE, EJB_PKG.getEJBRelation_RelationshipRoles());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getEJBRelationshipRole_Descriptions()),
				EJB_RELATIONSHIP_ROLE_NAME_TRANSLATOR,
				MULTIPLICITY_TRANSLATOR,
				CASCADE_DELETE_TRANSLATOR,
				createRelationshipRoleSourceTranslator14(),
				createCMRFieldTranslator14(),
		//		CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getEJBRelationshipRole_Extensions())
		});
		return result;
	}

	private static Translator createRelationshipRoleSourceTranslator14() {
		GenericTranslator result = new GenericTranslator(RELATIONSHIP_ROLE_SOURCE, EJB_PKG.getEJBRelationshipRole_Source());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getRoleSource_Descriptions()),
				ROLE_SOURCE_EJB_NAME_SOURCELINK_TRANSLATOR
		});
		return result;
	}

	private static Translator createCMRFieldTranslator14() {
		GenericTranslator result = new GenericTranslator(CMR_FIELD, EJB_PKG.getEJBRelationshipRole_CmrField());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getCMPAttribute_Descriptions()),
				CMR_FIELD_NAME_TRANSLATOR,
				CMR_FIELD_TYPE_TRANSLATOR,
		//		CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getCMPAttribute_Extensions())
		});
		return result;
	}

	/*
	 * <= J2EE 1.3 Assembly Descriptor 
	 */
	public static Translator createAssemblyDescriptorTranslator13() {
		GenericTranslator result = new GenericTranslator(ASSEMBLY_DESCRIPTOR, EJB_PKG.getEJBJar_AssemblyDescriptor());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				createSecurityRoleTranslator13(),
				createMethodPermissionTranslator13(),
				createMethodTransactionTranslator13(),
				createExcludesListTranslator13(),
		});
		return result;
	}

	public static Translator createMethodPermissionTranslator13() {
		GenericTranslator result = new GenericTranslator(METHOD_PERMISSION, EJB_PKG.getAssemblyDescriptor_MethodPermissions());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getMethodPermission_Description()),
				UNCHECKED_TRANSLATOR,
				METHOD_PERMISSION_ROLE_NAME_SOURCELINK_TRANSLATOR,
				createMethodElementTranslators13(EJB_PKG.getMethodPermission_MethodElements())
		});

		return result;
	}

	//TODO: NEED TO FIGURE OUT SECURITY PATH		
	public static Translator createSecurityRoleTranslator13() {
		GenericTranslator result = new GenericTranslator(SECURITY_ROLE, EJB_PKG.getAssemblyDescriptor_SecurityRoles());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, CommonPackage.eINSTANCE.getSecurityRole_Description()),
				SEC_ROLE_NAME_TRANSLATOR
			});

		return result;
	}

	public static Translator createMethodTransactionTranslator13() {
		GenericTranslator result = new GenericTranslator(CONTAINER_TRANSACTION, EJB_PKG.getAssemblyDescriptor_MethodTransactions());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getMethodTransaction_Description()),
				createMethodElementTranslators13(EJB_PKG.getMethodTransaction_MethodElements()),
				TRANS_ATTRIBUTE_TRANSLATOR
		});

		return result;
	}

	public static Translator createExcludesListTranslator13() {
		GenericTranslator result = new GenericTranslator(EXCLUDE_LIST, EJB_PKG.getAssemblyDescriptor_ExcludeList());
		result.setChildren(	new Translator[] { 
			IDTranslator.INSTANCE, 
			new Translator(DESCRIPTION, EJB_PKG.getExcludeList_Description()), 
			createMethodElementTranslators13(EJB_PKG.getExcludeList_MethodElements())
		});
		return result;
	}

	public static Translator createMethodElementTranslators13(EStructuralFeature eFeature) {
		GenericTranslator result = new GenericTranslator(METHOD, eFeature);
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				new Translator(DESCRIPTION, EJB_PKG.getMethodElement_Description()),
				METHOD_ELEMENT_EJB_NAME_SOURCELINK_TRANSLATOR,
				METHOD_ELEMENT_KIND_TRANSLATOR,
				METHOD_NAME_TRANSLATOR,
				METHOD_PARAM_TRANSLATOR
			});
		return result;
	}
	
	
	/*
	 * J2EE 1.4 Assembly Descriptor 
	 */
	public static Translator createAssemblyDescriptorTranslator14() {
		GenericTranslator result = new GenericTranslator(ASSEMBLY_DESCRIPTOR, EJB_PKG.getEJBJar_AssemblyDescriptor());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				createSecurityRoleTranslator14(),
				createMethodPermissionTranslator14(),
				createMethodTransactionTranslator14(),
				CommonTranslators.createMessageDestinationTranslator(EJB_PKG.getAssemblyDescriptor_MessageDestinations()),
				createExcludesListTranslator14(),
		//		CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getAssemblyDescriptor_Extensions())
		});
		return result;
	}

	public static Translator createMethodPermissionTranslator14() {
		GenericTranslator result = new GenericTranslator(METHOD_PERMISSION, EJB_PKG.getAssemblyDescriptor_MethodPermissions());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getMethodPermission_Descriptions()),
				UNCHECKED_TRANSLATOR,
				METHOD_PERMISSION_ROLE_NAME_SOURCELINK_TRANSLATOR,
				createMethodElementTranslators14(EJB_PKG.getMethodPermission_MethodElements())
		});

		return result;
	}

	//TODO: NEED TO FIGURE OUT SECURITY PATH		
	public static Translator createSecurityRoleTranslator14() {
		GenericTranslator result = new GenericTranslator(SECURITY_ROLE, EJB_PKG.getAssemblyDescriptor_SecurityRoles());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(CommonPackage.eINSTANCE.getSecurityRole_Descriptions()),
				SEC_ROLE_NAME_TRANSLATOR
		});

		return result;
	}

	public static Translator createMethodTransactionTranslator14() {
		GenericTranslator result = new GenericTranslator(CONTAINER_TRANSACTION, EJB_PKG.getAssemblyDescriptor_MethodTransactions());
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getMethodTransaction_Descriptions()),
				createMethodElementTranslators14(EJB_PKG.getMethodTransaction_MethodElements()),
				TRANS_ATTRIBUTE_TRANSLATOR
		});

		return result;
	}

	public static Translator createExcludesListTranslator14() {
		GenericTranslator result = new GenericTranslator(EXCLUDE_LIST, EJB_PKG.getAssemblyDescriptor_ExcludeList());
		result.setChildren(	new Translator[] { 
			IDTranslator.INSTANCE, 
			CommonTranslators.createDescriptionTranslator(EJB_PKG.getExcludeList_Descriptions()), 
			createMethodElementTranslators14(EJB_PKG.getExcludeList_MethodElements())
		});
		return result;
	}

	public static Translator createMethodElementTranslators14(EStructuralFeature eFeature) {
		GenericTranslator result = new GenericTranslator(METHOD, eFeature);
		result.setChildren(
			new Translator[] {
				IDTranslator.INSTANCE,
				CommonTranslators.createDescriptionTranslator(EJB_PKG.getMethodElement_Descriptions()),
				METHOD_ELEMENT_EJB_NAME_SOURCELINK_TRANSLATOR,
				METHOD_ELEMENT_KIND_TRANSLATOR,
				METHOD_NAME_TRANSLATOR,
				METHOD_PARAM_TRANSLATOR
			});
		return result;
	}

}
