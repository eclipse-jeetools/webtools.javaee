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
 *  $Revision: 1.2 $  $Date: 2004/01/12 21:44:21 $ 
 */

import org.eclipse.emf.ecore.EObject;

import org.eclipse.jem.internal.instantiation.JavaAllocation;
import org.eclipse.jem.internal.java.instantiation.IInstantiationInstance;
/**
 * A common interface for Java instances. It will be
 * shared by Java Objects and Java DataType instances.
 */
public interface IJavaInstance extends EObject, IInstantiationInstance {
	
	/**
	 * Get the allocation object.
	 * @return The allocation object.
	 */
	public JavaAllocation getAllocation();
	
	/**
	 * Set the allocation for this object instance.
	 * @param allocation
	 */
	public void setAllocation(JavaAllocation allocation);	
	
	/**
	 * Return whether the allocation has been set or not.
	 * @return <code>true</code> if set. 
	 */
	public boolean isSetAllocation();
	
	/**
	 * Answer true if we are an instance of one of Java's primitive data types.
	 * e.g. boolean, char - true otherwise, e.g. java.lang.Boolean
	 */
	public boolean isPrimitive();
	
}
