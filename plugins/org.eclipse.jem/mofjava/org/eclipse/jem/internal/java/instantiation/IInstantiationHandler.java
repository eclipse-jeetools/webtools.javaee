/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.instantiation;
/*
 *  $RCSfile: IInstantiationHandler.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/15 22:37:02 $ 
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


