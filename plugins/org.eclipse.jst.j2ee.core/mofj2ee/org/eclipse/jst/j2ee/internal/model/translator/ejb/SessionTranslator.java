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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class SessionTranslator extends AbstractEJBTranslator {

	private static Translator[] children13;
	private static Translator[] children14;
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public SessionTranslator() {
		super(ENTERPRISE_BEANS+'/'+SESSION);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.model.translator.ejb.AbstractEJBTranslator#getSpecificMaps()
	 */
	protected Translator[] getSpecificMaps(int versionID) {
		return new Translator[] {
			new Translator(SESSION_TYPE, EJB_PKG.getSession_SessionType(), UNSET_IF_NULL), 		//Enumeration
			new Translator(TRANSACTION_TYPE, EJB_PKG.getSession_TransactionType(), UNSET_IF_NULL) 		//Enumeration
		};
	}
		
		
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.model.translator.ejb.AbstractEJBTranslator#getEJBClassMap()
	 * 
	 * Support 1.4 and 1.3
	 */
	protected Translator[] getEJBClassMap() {
		return new Translator[] {			
			//J2EE 1.4
			new JavaClassTranslator(SERVICE_ENDPOINT,EJB_PKG.getSession_ServiceEndpoint()),
			//J2EE 1.3
			new JavaClassTranslator(EJB_CLASS, EJB_PKG.getEnterpriseBean_EjbClass())
		};
		
	}

	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		return EjbFactory.eINSTANCE.createSession();
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
