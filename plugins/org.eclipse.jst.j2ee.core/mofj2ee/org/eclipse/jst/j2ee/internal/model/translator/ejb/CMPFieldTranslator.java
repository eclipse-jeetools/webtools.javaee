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
 * Created on Apr 2, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author administrator
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class CMPFieldTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI {
	public static EjbPackage EJB_PKG = EjbPackage.eINSTANCE;
	public static Translator[] children;
	public CMPFieldTranslator(){
		super(CMP_FIELD, EJB_PKG.getContainerManagedEntity_PersistentAttributes());
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren()
	 */
	protected Translator[] getChildren() {
		if (children == null)
			children = createChildren();
		return children;
	}

	protected Translator[] createChildren(){
		return new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(DESCRIPTION,EJB_PKG.getCMPAttribute_Description()),
			new Translator(FIELD_NAME, EcorePackage.eINSTANCE.getENamedElement_Name())	
		};
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getMOFChildren(org.eclipse.emf.ecore.EObject)
	 */
	public List getMOFChildren(EObject mofObject) {
		switch(((ContainerManagedEntity)mofObject).getVersionID()) {  
			
			case J2EEVersionConstants.EJB_1_0_ID:
			case J2EEVersionConstants.EJB_1_1_ID:
	
				return super.getMOFChildren(mofObject);
				
			case J2EEVersionConstants.EJB_2_0_ID:
			case J2EEVersionConstants.EJB_2_1_ID: default:
	
				return get20CMPAttributes((ContainerManagedEntity)mofObject);
		}  
	}
	
	public List get20CMPAttributes(ContainerManagedEntity cmp) {
		List allAttributes, removed, result;
		removed = null;
		allAttributes = cmp.getPersistentAttributes();
		int size = allAttributes.size();
		CMPAttribute att = null;
		for (int i = 0; i < size; i++) {
			att = (CMPAttribute) allAttributes.get(i);
			if (att.isDerived()) {
				if (removed == null)
					removed = new ArrayList();
				removed.add(att);
			}
		}
		if (removed != null) {
			result = new ArrayList(size);
			result.addAll(allAttributes);
			result.removeAll(removed);
		} else
			result = allAttributes;
		return result;
	}

}
