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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.model.translator.common.BooleanTranslator;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class EntityTranslator extends AbstractEJBTranslator {

	private static Translator[] children13;
	private static Translator[] children14;

	private class PersistenceTypeTranslator extends Translator {

		public PersistenceTypeTranslator() {
			super(PERSISTENCE_TYPE, (EStructuralFeature) null);
		}

		/* (non-Javadoc)
		 * @see com.ibm.etools.emf2xml.impl.Translator#extractStringValue(org.eclipse.emf.ecore.EObject)
		 */
		public String extractStringValue(EObject emfObject) {
			return getPersistenceType();
		}

		/* (non-Javadoc)
			   * @see com.ibm.etools.emf2xml.impl.Translator#convertValueToString(java.lang.Object, org.eclipse.emf.ecore.EObject)
			   */
		public String convertValueToString(Object value, EObject owner) {
			return getPersistenceType();
		}
		
		/* (non-Javadoc)
		 * @see com.ibm.etools.emf2xml.impl.Translator#getMOFChildren(org.eclipse.emf.ecore.EObject)
		 */
		public List getMOFChildren(EObject mofObject) {
			return Collections.singletonList(getPersistenceType());
		}


	}

	protected String getPersistenceType() {
		return BEAN;
	}
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public EntityTranslator() {
		super(ENTERPRISE_BEANS + '/' + ENTITY);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.model.translator.ejb.AbstractEJBTranslator#getSpecificMaps()
	 */
	protected Translator[] getSpecificMaps(int versionID) {
		Translator reentrantTranslator = null;
		switch (versionID) {
			case J2EE_1_2_ID :
			case J2EE_1_3_ID :
				reentrantTranslator = new Translator(REENTRANT, EJB_PKG.getEntity_Reentrant(), UNSET_IF_NULL);
				break;
			case J2EE_1_4_ID :
				reentrantTranslator = new BooleanTranslator(REENTRANT, EJB_PKG.getEntity_Reentrant());
			default :
				break;
		}
		return new Translator[] { new PersistenceTypeTranslator(), new JavaClassTranslator(PRIM_KEY_CLASS, EJB_PKG.getEntity_PrimaryKey()), reentrantTranslator };
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		return EjbFactory.eINSTANCE.createEntity();
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

}
