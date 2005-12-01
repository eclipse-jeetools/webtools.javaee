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
 * Created on Apr 1, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.ReadAheadHelper;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class SecurityIdentityTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI {
	private static final CommonPackage COMMON_PKG = CommonPackage.eINSTANCE;
	
	private static final Translator[] RUN_AS_TRANSLATORS = createRunAsTranslators();
	
	private static final Translator[] USE_CALLER_TRANSLATORS = createUseCallerTranslators();
	
	
	private static Translator[] createRunAsTranslators() {
		GenericTranslator runAsTranslator = new GenericTranslator(RUN_AS, COMMON_PKG.getRunAsSpecifiedIdentity_Identity());
		runAsTranslator.setChildren(new Translator[] {
			new Translator(DESCRIPTION, COMMON_PKG.getIdentity_Description()),
			new Translator(ROLE_NAME, COMMON_PKG.getIdentity_RoleName())
		});
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, COMMON_PKG.getSecurityIdentity_Description()),
			runAsTranslator
		};
	}
	
	private static Translator[] createUseCallerTranslators() {
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION, COMMON_PKG.getSecurityIdentity_Description()),
			new Translator(USE_CALLER_IDENTITY, null, EMPTY_TAG)
		};
	}

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public SecurityIdentityTranslator() {
		super(SECURITY_IDENTITY, EjbPackage.eINSTANCE.getEnterpriseBean_SecurityIdentity());
		addReadAheadHelper(new ReadAheadHelper(SECURITY_IDENTITY, new String[] {RUN_AS, USE_CALLER_IDENTITY}, null));
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null)
			return CommonTranslators.EMPTY_CHILDREN;
		if (((SecurityIdentity)o).isRunAsSpecifiedIdentity())
			return RUN_AS_TRANSLATORS;
		else if (((SecurityIdentity)o).isUseCallerIdentity())
			return USE_CALLER_TRANSLATORS;
		else
			throw new IllegalStateException("Internal error: Security Identity Type expected"); //$NON-NLS-1$
	}


	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		CommonFactory fact = CommonFactory.eINSTANCE; 
		if (RUN_AS.equals(readAheadName)) {
			RunAsSpecifiedIdentity result = fact.createRunAsSpecifiedIdentity();
			result.setIdentity(fact.createIdentity());
			return result;
		} else if (USE_CALLER_IDENTITY.equals(readAheadName))
			return fact.createUseCallerIdentity();
		else 
			return null;
	}

	public boolean isManagedByParent() {
		return false;
	}

}
