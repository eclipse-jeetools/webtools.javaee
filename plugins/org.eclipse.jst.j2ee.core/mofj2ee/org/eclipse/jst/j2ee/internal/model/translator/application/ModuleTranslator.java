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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.xml.EarDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.ReadAheadHelper;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class ModuleTranslator extends Translator implements EarDeploymentDescriptorXmlMapperI {
	private static final ApplicationPackage APPLICATION_PKG = ApplicationPackage.eINSTANCE;

	private static final Translator ALT_DD_TRANSLATOR = createAltDDTranslator();
	private static final Translator[] WEB_TRANSLATORS = createWebModuleTranslators();
	private static final Translator[] EJB_TRANSLATORS = createEJBModuleTranslators();
	private static final Translator[] JAVA_TRANSLATORS = createJavaModuleTranslators();
	private static final Translator[] CONNECTOR_TRANSLATORS = createConnectorModuleTranslators();
	//private static final  

	/**
	 * @param domNameAndPath
	 * @param eClass
	 */
	public ModuleTranslator() {
		super(MODULE, APPLICATION_PKG.getApplication_Modules());
		addReadAheadHelper(new ReadAheadHelper(MODULE, new String[] { EJB, JAVA, WEB, CONNECTOR }, null));
	}

	/**
	 * @return
	 */
	private static Translator[] createConnectorModuleTranslators() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(CONNECTOR, APPLICATION_PKG.getModule_Uri()),
			ALT_DD_TRANSLATOR,
		//	CommonTranslators.createDeploymentExtensionTranslator(APPLICATION_PKG.getModule_Extensions())
		};
	}

	/**
	 * @return
	 */
	private static Translator[] createJavaModuleTranslators() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(JAVA, APPLICATION_PKG.getModule_Uri()),
			ALT_DD_TRANSLATOR,
		//	CommonTranslators.createDeploymentExtensionTranslator(APPLICATION_PKG.getModule_Extensions())
		};
	}

	/**
	 * @return
	 */
	private static Translator[] createEJBModuleTranslators() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(EJB, APPLICATION_PKG.getModule_Uri()),
			ALT_DD_TRANSLATOR,
		//	CommonTranslators.createDeploymentExtensionTranslator(APPLICATION_PKG.getModule_Extensions())
		};
	}

	/**
	 * @return
	 */
	private static Translator[] createWebModuleTranslators() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(WEB + "/" + WEB_URI, APPLICATION_PKG.getModule_Uri()), //$NON-NLS-1$
			new Translator(WEB + "/" + CONTEXT_ROOT, APPLICATION_PKG.getWebModule_ContextRoot()), //$NON-NLS-1$
			ALT_DD_TRANSLATOR,
		//	CommonTranslators.createDeploymentExtensionTranslator(APPLICATION_PKG.getModule_Extensions())
		};

	}

	private static Translator createAltDDTranslator() {
		return new Translator(ALT_DD, ApplicationPackage.eINSTANCE.getModule_AltDD());
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null) 
			return CommonTranslators.EMPTY_CHILDREN;
		else if (((Module) o).isEjbModule())
			return EJB_TRANSLATORS;
		else if (((Module) o).isConnectorModule())
			return CONNECTOR_TRANSLATORS;
		else if (((Module) o).isWebModule())
			return WEB_TRANSLATORS;
		else if (((Module) o).isJavaModule())
			return JAVA_TRANSLATORS;
		else
			throw new IllegalStateException("Internal error: Module Type excepted"); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		ApplicationFactory fact = ApplicationFactory.eINSTANCE;
		if (EJB.equals(readAheadName))
			return fact.createEjbModule();
		else if (WEB.equals(readAheadName))
			return fact.createWebModule();
		else if (JAVA.equals(readAheadName))
			return fact.createJavaClientModule();
		else if (CONNECTOR.equals(readAheadName))
			return fact.createConnectorModule();
		else
			return fact.createModule();
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#isManagedByParent()
	 */
	public boolean isManagedByParent() {
		return false;
	}

}
