package org.eclipse.jem.internal.instantiation.base;
/*******************************************************************************
 * Copyright (c) 2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaDataTypeInstance.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jem.internal.java.JavaHelpers;

/**
 * This is the default instance of a Java Model Datatype (i.e. primitive).
 * It can be created from a string, which becomes the initialization string
 * for the instance. It's toString will be the initialization string.
 *
 * It should not be referenced directly, the IJavaDataTypeInstance interface should be
 * used instead. It is public so that it can be subclassed. 
 */
public class JavaDataTypeInstance extends EObjectImpl implements IJavaDataTypeInstance {
	
	public static final String INIT_STRING_NAME = "initializationString"; //$NON-NLS-1$

	protected JavaDataTypeInstance() {
	}

	/*
	 * This is here only for JavaFactoryHandler to set the init string.
	 * It is not exposed in the interface and should not be called outside.
	 */
	public void setInitializationString(String initString) {
		eSet(eClass().getEStructuralFeature(INIT_STRING_NAME), initString);
	}
			
	public JavaHelpers getJavaType() {
		return (JavaHelpers) eClass();
	}
	
	public String getInitializationString() {
		return (String) eGet(eClass().getEStructuralFeature(INIT_STRING_NAME));
	}
	
	public boolean isSetInitializationString() {
		return eIsSet(eClass().getEStructuralFeature(INIT_STRING_NAME));
	}
	
	public String toString() {
		return isSetInitializationString() ? getInitializationString() : ""; //$NON-NLS-1$
	}
	public boolean isPrimitive(){
		return true;
	}
}
