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
package org.eclipse.jst.j2ee.internal.ejb.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.ejb.EJBMethodCategory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;

/**
 * The valid EJB method category names are: EJBHOMEMETHOD, EJBCREATEMETHOD, EJBFINDERMETHOD, EJBREMOTEMETHOD.
 */
public class EJBMethodCategoryImpl extends EObjectImpl implements EJBMethodCategory, EObject {

	public EJBMethodCategoryImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getEJBMethodCategory();
	}

}





