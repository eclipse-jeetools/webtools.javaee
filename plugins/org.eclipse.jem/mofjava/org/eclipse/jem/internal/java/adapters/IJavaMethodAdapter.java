/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IJavaMethodAdapter.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:20:25 $ 
 */
package org.eclipse.jem.internal.java.adapters;
 

/**
 * Used to reflect methods
 * @since 1.0.0
 */
public interface IJavaMethodAdapter {

	/**
	 * Reflect the generated if necessary.
	 * @return <code>true</code> if reflection occurred.
	 * 
	 * @since 1.0.0
	 */
	public boolean reflectGeneratedIfNecessary();
	
	/**
	 * Reflect the names of the method parameters if necessary.
	 * @return <code>true</code> if reflection occurred.
	 * 
	 * @since 1.0.0
	 */
	public boolean reflectParamNamesIfNecessary();
}
