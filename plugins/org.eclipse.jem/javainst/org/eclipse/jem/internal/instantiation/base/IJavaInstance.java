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
 *  $RCSfile: IJavaInstance.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jem.internal.java.instantiation.IInstantiationInstance;
/**
 * A common interface for Java instances. It will be
 * shared by Java Objects and Java DataType instances.
 */
public interface IJavaInstance extends EObject, IInstantiationInstance {
	
	/**
	 * Return the initialization string.
	 */
	public String getInitializationString();
	
	/**
	 * Return whether the initialization string
	 * is set or not.
	 */
	public boolean isSetInitializationString();
	/**
	 * Answer true if we are an instance of one of Java's primitive data types.
	 * e.g. boolean, char - true otherwise, e.g. java.lang.Boolean
	 */
	public boolean isPrimitive();
	
}
