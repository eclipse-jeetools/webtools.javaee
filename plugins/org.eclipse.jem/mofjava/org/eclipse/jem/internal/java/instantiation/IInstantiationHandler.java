package org.eclipse.jem.internal.java.instantiation;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IInstantiationHandler.java,v $
 *  $Revision: 1.3 $  $Date: 2004/01/13 16:16:21 $ 
 */
import org.eclipse.emf.ecore.*;

import org.eclipse.jem.java.JavaDataType;

/**
 * The interface for the Instantiation handler. The actual implementation
 * is in a separate project so that instantiation code will not be loaded
 * unless it needs to be.
 */

public interface IInstantiationHandler {
	/**
	 * Answers whether the adapter handles the datatype
	 * or whether the standard EFactory does.
	 */
	public boolean handlesDataType(JavaDataType type);
	
	/**
	 * Answers whether the adapter handles the EClass or the
	 * standard EFactory does.
	 */
	public boolean handlesClass(EClass type);
	
	/**
	 * If adapterHandlesInstance returns true, then
	 * this method will be called to create the instance.
	 */
	public EObject create(EClass javaClass);

}


