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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class CMPVersionTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI {

	/**
	 * @param domNameAndPath
	 * @param eClass
	 */
	public CMPVersionTranslator() {
		super(CMP_VERSION, EjbPackage.eINSTANCE.getContainerManagedEntity_Version());
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getMOFValue(org.eclipse.emf.ecore.EObject)
	 */
	public Object getMOFValue(EObject mofObject) {
		EJBJar parent = ((ContainerManagedEntity)mofObject).getEjbJar();
		if (parent.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID)
			return super.getMOFValue(mofObject);
		return null;
	}
	


}
