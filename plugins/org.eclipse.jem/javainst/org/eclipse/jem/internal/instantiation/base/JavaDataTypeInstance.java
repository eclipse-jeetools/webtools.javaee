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
 *  $Revision: 1.4 $  $Date: 2004/01/19 22:50:15 $ 
 */

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jem.internal.instantiation.JavaAllocation;
import org.eclipse.jem.java.JavaHelpers;

/**
 * This is the default instance of a Java Model Datatype (i.e. primitive).
 * It can be created from a string, which becomes the initialization string
 * for the instance. It's toString will be the initialization string.
 *
 * It should not be referenced directly, the IJavaDataTypeInstance interface should be
 * used instead. It is public so that it can be subclassed. 
 */
public class JavaDataTypeInstance extends EObjectImpl implements IJavaDataTypeInstance {

	protected JavaDataTypeInstance() {
	}

	/*
	 * This is here only for JavaFactoryHandler to set the allocation.
	 * It is not exposed in the interface and should not be called outside.
	 */
	public JavaAllocation getAllocation() {
		return isSetAllocation() ? (JavaAllocation) eGet(JavaInstantiation.getAllocationFeature(this)) : null;
	}
	
	public boolean isSetAllocation() {
		return eIsSet(JavaInstantiation.getAllocationFeature(this));
	}
	
	public void setAllocation(JavaAllocation allocation) {
		eSet(JavaInstantiation.getAllocationFeature(this), allocation);
	}
			
	public JavaHelpers getJavaType() {
		return (JavaHelpers) eClass();
	}
		
	public String toString() {
		return isSetAllocation() ? getAllocation().toString() : ""; //$NON-NLS-1$
	}
	public boolean isPrimitive(){
		return true;
	}
}
