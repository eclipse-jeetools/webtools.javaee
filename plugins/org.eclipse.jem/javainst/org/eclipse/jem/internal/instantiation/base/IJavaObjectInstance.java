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
 *  $RCSfile: IJavaObjectInstance.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */

/**
 * Interface for java object instances.
 */
public interface IJavaObjectInstance extends IJavaInstance {
	
	/**
	 * Is this an implicit bean, i.e. It is queried from the object
	 * and then any settings are placed upon that object, a new
	 * instance is not created when an implicit bean is accessed.
	 */
	public boolean isImplicit();
	
	public boolean isSetImplicit();
	
	public void setImplicit(boolean implicit);
	
	/**
	 * Objects can have their inititialization strings changed.
	 */
	public void setInitializationString(String initString);

	/**
	 * Objects can change the name of the class used to instantiate it.
	 */
	public void setInstantiateUsing(String classname);
	
	/**
	 * Objects can change the serialized string used to represent them.
	 */
	 public void setSerializeData(String serializeData);

	/**
	 * Return the class name used to instantiate this class.
	 */
	public String getInstantiateUsing();
	
	/** 
	 * Returns the serialized Data used to instantiate this class.
	 */
	public String getSerializeData();
	/**
	 * Return whether the instantiate using classname
	 * is set or not.
	 */
	public boolean isSetInstantiateUsing();
	
	/**
	 * Returns whether the serialized Data is set or not.
	 */
	 public boolean isSetSerializeData();

}
