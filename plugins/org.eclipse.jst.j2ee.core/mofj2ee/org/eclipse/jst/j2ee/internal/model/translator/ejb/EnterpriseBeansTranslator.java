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
 * Created on Mar 27, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.MultiObjectTranslator;
import org.eclipse.wst.common.internal.emf.resource.ReadAheadHelper;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class EnterpriseBeansTranslator extends MultiObjectTranslator implements EjbDeploymentDescriptorXmlMapperI {
	public static final String ENTERPRISE_BEANS_PATH = ENTERPRISE_BEANS+'/'+ENTITY+','+SESSION+','+MESSAGE_DRIVEN;
    private static final EjbPackage EJB_PKG = EjbPackage.eINSTANCE;
	private static final SessionTranslator SESSION_TRANSLATOR = new SessionTranslator();
	private static final EntityTranslator BMP_TRANSLATOR = new EntityTranslator();
	private static final ContainerManagedEntityTranslator CMP_TRANSLATOR= new ContainerManagedEntityTranslator();
	private static final MessageDrivenTranslator MESSAGE_DRIVEN_TRANSLATOR = new MessageDrivenTranslator();
	private static Map delegates;
	static {
		delegates = new HashMap(7);
		delegates.put(SESSION, SESSION_TRANSLATOR);
		delegates.put(BEAN, BMP_TRANSLATOR);
		delegates.put(CONTAINER, CMP_TRANSLATOR);
		delegates.put(MESSAGE_DRIVEN, MESSAGE_DRIVEN_TRANSLATOR);
	}
	
	/**
	 * @param domNameAndPath
	 * @param eClass
	 */
	public EnterpriseBeansTranslator() {
		super(ENTERPRISE_BEANS_PATH, EJB_PKG.getEJBJar_EnterpriseBeans());
		addReadAheadHelper(new ReadAheadHelper(ENTITY, new String[] {BEAN, CONTAINER}, PERSISTENCE_TYPE ));
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.MultiObjectTranslator#getDelegateFor(org.eclipse.emf.ecore.EObject)
	 */
	public Translator getDelegateFor(EObject o) {
		switch (o.eClass().getClassifierID()) {
			case EjbPackage.SESSION :
				return SESSION_TRANSLATOR;
			case EjbPackage.ENTITY:
				return BMP_TRANSLATOR;
			case EjbPackage.CONTAINER_MANAGED_ENTITY:
				return CMP_TRANSLATOR;
			case EjbPackage.MESSAGE_DRIVEN:
				return MESSAGE_DRIVEN_TRANSLATOR;
		}
		throw new IllegalStateException("Bean type delegate expected"); //$NON-NLS-1$
	}

	public Translator getDelegateFor(String domName, String readAheadName) {
		Translator result = null;
		if (readAheadName != null)
			result = (Translator) delegates.get(readAheadName);
		else 
			result = (Translator) delegates.get(domName);
		
		if (result == null) 
			throw new IllegalStateException("Bean type delegate expected"); //$NON-NLS-1$
		
		return result;
	}
	

	
}
