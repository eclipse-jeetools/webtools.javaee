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
 * Created on Apr 1, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.model.translator.application;

import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.xml.EarDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.ConstantAttributeTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author dfholttp
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class ApplicationTranslator extends RootTranslator implements EarDeploymentDescriptorXmlMapperI, J2EEConstants {
	public static ApplicationTranslator INSTANCE = new ApplicationTranslator();
	private static Translator[] children13;
	private static Translator[] children14;
	private static ApplicationPackage APPLICATION_PKG = ApplicationPackage.eINSTANCE;
	private static CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	
    public ApplicationTranslator() {
		super(APPLICATION, ApplicationPackage.eINSTANCE.getApplication());
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
		return  new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(ICON+"/"+SMALL_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon()), //$NON-NLS-1$
			new Translator(ICON+"/"+LARGE_ICON, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon()), //$NON-NLS-1$
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName()),
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()),
			new ModuleTranslator(),
			CommonTranslators.createSecurityRoleTranslator13(APPLICATION_PKG.getApplication_SecurityRoles()),
		};
	}

	protected Translator[] create14Children() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(VERSION, APPLICATION_PKG.getApplication_Version(), DOM_ATTRIBUTE),
			new ConstantAttributeTranslator(XML_NS, J2EE_NS_URL),
			new ConstantAttributeTranslator(XML_NS_XSI, XSI_NS_URL),
			new ConstantAttributeTranslator(XSI_SCHEMA_LOCATION, J2EE_NS_URL+' '+APPLICATION_SCHEMA_LOC_1_4),
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			new ModuleTranslator(),
			CommonTranslators.createSecurityRoleTranslator14(APPLICATION_PKG.getApplication_SecurityRoles()),
		};

	}
}
