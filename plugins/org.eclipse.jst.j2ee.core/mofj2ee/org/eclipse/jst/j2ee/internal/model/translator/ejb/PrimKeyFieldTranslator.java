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
 * Created on Apr 3, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.SourceLinkTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorPath;

/**
 * @author jlanuti
 */
public class PrimKeyFieldTranslator extends SourceLinkTranslator implements EjbDeploymentDescriptorXmlMapperI {
	
	/**
	 * @param domNameAndPath
	 * @param eClass
	 * @param TranslatorPath
	 */
	public PrimKeyFieldTranslator() {
		super(PRIMKEY_FIELD, EjbPackage.eINSTANCE.getContainerManagedEntity_PrimKeyField(),new TranslatorPath(
			new Translator[] {
				new Translator(CMP_FIELD, EjbPackage.eINSTANCE.getContainerManagedEntity_PersistentAttributes()),
				new Translator(FIELD_NAME, EcorePackage.eINSTANCE.getENamedElement_Name())
			}));
	}
	
	protected String getSpecifiedPrimKeyFieldName(ContainerManagedEntity cmp) {
		return cmp.getPrimKeyField().getName();
	}

	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertStringToValue(java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public Object convertStringToValue(String strValue, EObject owner) {
		// try and convert the string to a cmp attribute, otherwise create a proxy
		Object result = super.convertStringToValue(strValue, owner);
		if (result == null || result == strValue /*reference check is valid here*/) {
			result = EjbFactory.eINSTANCE.createCMPAttribute();
			if (strValue == null) strValue = "";//$NON-NLS-1$
			((InternalEObject)result).eSetProxyURI(URI.createURI(strValue));
		}
		return result;
	}

}
