package org.eclipse.jem.internal.instantiation.base;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaFactoryHandler.java,v $
 *  $Revision: 1.3 $  $Date: 2004/01/13 16:16:21 $ 
 */

import org.eclipse.emf.ecore.*;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaDataType;
import org.eclipse.jem.internal.java.instantiation.IInstantiationHandler;

/**
 * Adapter on JavaFactoryImpl to do instantiation.
 */
public class JavaFactoryHandler implements IInstantiationHandler {

	public final static JavaFactoryHandler INSTANCE = new JavaFactoryHandler();

	/**
	 * Constructor for JavaFactoryAdapter.
	 */
	protected JavaFactoryHandler() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.instantiation.IInstantiationHandler#handlesClass(org.eclipse.emf.ecore.EClass)
	 */
	public boolean handlesClass(EClass type) {
		return type instanceof JavaClass || type instanceof JavaDataType;
		// During XMI loading, it can't tell the JavaDataType is different than JavaClass.
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.instantiation.IInstantiationHandler#handlesDataType(org.eclipse.jem.java.JavaDataType)
	 */
	public boolean handlesDataType(JavaDataType type) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.java.instantiation.IInstantiationHandler#create(org.eclipse.emf.ecore.EClass)
	 */
	public EObject create(EClass javaClass) {
		EObject result = javaClass instanceof JavaClass ? (EObject) new JavaObjectInstance() : new JavaDataTypeInstance();
		((InternalEObject) result).eSetClass(javaClass);
		return result;
	}
}
