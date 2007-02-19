/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;
/*
 *  $RCSfile: JavaEventImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2007/02/19 05:31:23 $ 
 */
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;

import org.eclipse.jem.java.JavaEvent;
import org.eclipse.jem.java.JavaRefPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Event</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class JavaEventImpl extends EStructuralFeatureImpl implements JavaEvent {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JavaEventImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.Literals.JAVA_EVENT;
	}

}
