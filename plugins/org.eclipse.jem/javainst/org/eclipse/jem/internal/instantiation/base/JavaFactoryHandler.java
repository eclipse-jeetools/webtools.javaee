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
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */

import org.eclipse.emf.ecore.*;

import org.eclipse.jem.internal.java.JavaClass;
import org.eclipse.jem.internal.java.JavaDataType;
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


	/**
	 * @see org.eclipse.jem.internal.instantiation.IInstantiationHandler#handlesClass(EClass)
	 */
	public boolean handlesClass(EClass type) {
		return type instanceof JavaClass || type instanceof JavaDataType;	// During XMI loading, it can't tell the JavaDataType is different than JavaClass.
	}

	/**
	 * @see org.eclipse.jem.internal.instantiation.IInstantiationHandler#handlesDataType(EDataType)
	 */
	public boolean handlesDataType(JavaDataType type) {
		return true;
	}

	/**
	 * @see org.eclipse.jem.internal.instantiation.IInstantiationHandler#create(EClass)
	 */
	public EObject create(EClass javaClass) {
		EObject result = javaClass instanceof JavaClass ? (EObject) new JavaObjectInstance() : new JavaDataTypeInstance();
		((InternalEObject)result).eSetClass(javaClass);
		return result;
	}

	/**
	 * @see org.eclipse.jem.internal.instantiation.IInstantiationHandler#createFromString(EDataType, String)
	 */
	public Object createFromString(JavaDataType dataType, String s) {
		JavaDataTypeInstance jdt = new JavaDataTypeInstance();
		((InternalEObject)jdt).eSetClass(dataType);
		if (s != null)		
			jdt.setInitializationString(s);
		return jdt;
	}

}
