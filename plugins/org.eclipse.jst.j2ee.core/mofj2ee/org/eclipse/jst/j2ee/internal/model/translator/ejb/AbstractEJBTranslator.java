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
 * Created on Mar 31, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public abstract class AbstractEJBTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI, J2EEVersionConstants {
	protected static final EjbPackage EJB_PKG = EjbPackage.eINSTANCE;
	protected static final CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	
	
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public AbstractEJBTranslator(String domNameAndPath) {
		super(domNameAndPath, EJB_PKG.getEJBJar_EnterpriseBeans());
	}

	
	protected Translator[] create13Children() {
		Translator[] start = getCommonBeginMaps13();
		Translator[] middle = getSpecificMaps(J2EE_1_3_ID);
		Translator[] end = getEndMaps13();
		return (Translator[]) concat(start, concat(middle, end));
	}
	
	protected Translator[] create14Children() {
			Translator[] start = getCommonBeginMaps14();
			Translator[] middle = getSpecificMaps(J2EE_1_4_ID);
			Translator[] end = getEndMaps14();
			//Translator[] deploymentExt = getDeploymentExtensionMaps();
			return (Translator[]) concat(start, concat(middle, end));
	}
	
	protected Translator[] getCommonBeginMaps13() {
		
		Translator[] beginMaps =  new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()),
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName()),
			new Translator(SMALL_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon()),
			new Translator(LARGE_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon()),
			new Translator(EJB_NAME, EJB_PKG.getEnterpriseBean_Name())
		};
		
		if (usesHomeRemoteAttributes())
			return (Translator[])concat(concat(beginMaps, getHomeRemoteMaps()), getEJBClassMap());
		return (Translator[])concat(beginMaps, getEJBClassMap());
	}
	

	protected Translator[] getCommonBeginMaps14() {
		
		Translator[] beginMaps =  new Translator[] {
			IDTranslator.INSTANCE,
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			new Translator(EJB_NAME, EJB_PKG.getEnterpriseBean_Name())
		};
		if (usesHomeRemoteAttributes())
			return (Translator[])concat(concat(beginMaps, getHomeRemoteMaps()), getEJBClassMap());
		return (Translator[])concat(beginMaps, getEJBClassMap());
	}
	
	protected Translator[] getHomeRemoteMaps() {
		return new Translator[] {
			new JavaClassTranslator(HOME, EJB_PKG.getEnterpriseBean_HomeInterface()),
			new JavaClassTranslator(REMOTE, EJB_PKG.getEnterpriseBean_RemoteInterface()),
			//Begin EJB 2.0
			new JavaClassTranslator(LOCAL_HOME, EJB_PKG.getEnterpriseBean_LocalHomeInterface()),
			new JavaClassTranslator(LOCAL, EJB_PKG.getEnterpriseBean_LocalInterface())
			//End EJB 2.0
		};
	}

	protected Translator[] getEJBClassMap() {
		return new Translator[] {
			new JavaClassTranslator(EJB_CLASS, EJB_PKG.getEnterpriseBean_EjbClass())
		};
	}

	protected boolean usesHomeRemoteAttributes() {
		return true;
	}


	protected Translator[] getEndMaps13() {
		return new Translator[] {
			CommonTranslators.JNDI_REF_GROUP_ENV_ENTRY_1_3,
			CommonTranslators.JNDI_REF_GROUP_EJB_REF_1_3,
			CommonTranslators.JNDI_REF_GROUP_EJB_LOCAL_REF_1_3,
			CommonTranslators.createSecurityRoleRefTranslator13(EJB_PKG.getEnterpriseBean_SecurityRoleRefs()),
			new SecurityIdentityTranslator(),
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_REF_1_3,
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_ENV_REF_1_3
		};

	}
		
	protected Translator[] getEndMaps14() {
		return new Translator[] {
			CommonTranslators.JNDI_REF_GROUP_ENV_ENTRY_1_4,
			CommonTranslators.JNDI_REF_GROUP_EJB_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_EJB_LOCAL_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_SERVICE_REF_1_4,	
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_ENV_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_MESSAGE_DEST_REF_1_4,
			CommonTranslators.createSecurityRoleRefTranslator14(EJB_PKG.getEnterpriseBean_SecurityRoleRefs()),
			new SecurityIdentityTranslator(),
		};
		
	}
	
	protected abstract Translator[] getSpecificMaps(int versionID);

//	protected Translator[] getDeploymentExtensionMaps(){
//		return new Translator[] {
//			CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getEnterpriseBean_Extensions())
//		};
//	}
}
