/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IJavaMethodAdapter.java,v $
 *  $Revision: 1.1 $  $Date: 2005/02/03 21:19:40 $ 
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
}
