/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl;
import org.eclipse.jst.j2ee.ejb.EJBMethodCategory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;

/**
 * The valid EJB method category names are: EJBHOMEMETHOD, EJBCREATEMETHOD, EJBFINDERMETHOD, EJBREMOTEMETHOD.
 * @generated
 */
public class EJBMethodCategoryImpl extends J2EEEObjectImpl implements EJBMethodCategory {

	public EJBMethodCategoryImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_METHOD_CATEGORY;
	}

}





