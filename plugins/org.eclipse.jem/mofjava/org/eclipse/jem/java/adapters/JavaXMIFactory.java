/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaXMIFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2005/09/14 23:30:32 $ 
 */
package org.eclipse.jem.java.adapters;

import org.eclipse.jem.internal.java.adapters.JavaXMIFactoryImpl;
 

/**
 * Factory for Java XMI Resources.
 * @since 1.2.0
 */
public interface JavaXMIFactory {

	/**
	 * Scheme used in URI's for jem elements.
	 * @since 1.2.0
	 */
	public static final String SCHEME = "java"; //$NON-NLS-1$
	public static final JavaXMIFactory INSTANCE = new JavaXMIFactoryImpl();

	/**
	 * Register the reflection key extension.
	 * @param extension
	 * 
	 * @since 1.2.0
	 */
	public void registerReflectionKeyExtension(IJavaReflectionKeyExtension extension);
	
	/**
	 * Deregister the reflection key extension.
	 * @param extension
	 * 
	 * @since 1.2.0
	 */
	public void deregisterReflectionKeyExtension(IJavaReflectionKeyExtension extension);
}
