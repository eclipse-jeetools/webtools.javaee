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
package org.eclipse.jst.j2ee.internal.model.translator.client;

import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.model.translator.common.EnvEntryTranslator;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.jst.j2ee.internal.xml.DeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.ConstantAttributeTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class ApplicationClientTranslator extends RootTranslator implements DeploymentDescriptorXmlMapperI, J2EEConstants {
	public static ApplicationClientTranslator INSTANCE = new ApplicationClientTranslator();
	public static final String CALLBACK_HANDLER = "callback-handler"; //$NON-NLS-1$
	public static final String APPLICATION_CLIENT = "application-client"; //$NON-NLS-1$
	private static CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	private static Translator[] children13;
	private static Translator[] children14;
	
	protected ApplicationClientTranslator() {
		super(APPLICATION_CLIENT, ClientPackage.eINSTANCE.getApplicationClient());
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
		ClientPackage ePackageClient=ClientPackage.eINSTANCE;
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(SMALL_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_SmallIcon()), 
			new Translator(LARGE_ICON_PATH, COMMON_PKG.getCompatibilityDescriptionGroup_LargeIcon()), 
			new Translator(DISPLAY_NAME, COMMON_PKG.getCompatibilityDescriptionGroup_DisplayName()),
			new Translator(DESCRIPTION, COMMON_PKG.getCompatibilityDescriptionGroup_Description()),
			new EnvEntryTranslator(ePackageClient.getApplicationClient_EnvironmentProps(), true, false),
			CommonTranslators.createEJBRefTranslator13(ePackageClient.getApplicationClient_EjbReferences()),
			CommonTranslators.createResourceRefTranslator13(ePackageClient.getApplicationClient_ResourceRefs()),
			//J2EE 1.3
			CommonTranslators.createResourceEnvRefTranslator13(ePackageClient.getApplicationClient_ResourceEnvRefs()),
			new JavaClassTranslator(CALLBACK_HANDLER, ePackageClient.getApplicationClient_CallbackHandler()),
		};
	}

	protected Translator[] create14Children() {
		ClientPackage ePackageClient=ClientPackage.eINSTANCE;
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(VERSION, ePackageClient.getApplicationClient_Version(), DOM_ATTRIBUTE),
			new ConstantAttributeTranslator(XML_NS, J2EE_NS_URL),
			new ConstantAttributeTranslator(XML_NS_XSI, XSI_NS_URL),
			new ConstantAttributeTranslator(XSI_SCHEMA_LOCATION, J2EE_NS_URL+' '+APP_CLIENT_SCHEMA_LOC_1_4),
			CommonTranslators.DESCRIPTIONS_TRANSLATOR,
			CommonTranslators.DISPLAYNAMES_TRANSLATOR,
			CommonTranslators.ICONS_TRANSLATOR,
			new EnvEntryTranslator(ePackageClient.getApplicationClient_EnvironmentProps(), true, true),
			CommonTranslators.createEJBRefTranslator14(ePackageClient.getApplicationClient_EjbReferences()),
			CommonTranslators.createServiceRefGroupTranslator(ePackageClient.getApplicationClient_ServiceRefs()),
			CommonTranslators.createResourceRefTranslator14(ePackageClient.getApplicationClient_ResourceRefs()),
			CommonTranslators.createResourceEnvRefTranslator14(ePackageClient.getApplicationClient_ResourceEnvRefs()),
			CommonTranslators.createMessageDestinationRefTranslator(ePackageClient.getApplicationClient_MessageDestinationRefs()),
			new JavaClassTranslator(CALLBACK_HANDLER, ePackageClient.getApplicationClient_CallbackHandler()),
			CommonTranslators.createMessageDestinationTranslator(ePackageClient.getApplicationClient_MessageDestinations()),
		//	CommonTranslators.createDeploymentExtensionTranslator(ePackageClient.getApplicationClient_DeploymentExtensions())
		};
	}
	
}
