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
package org.eclipse.jem.java;
/*
 *  $RCSfile: JavaPackage.java,v $
 *  $Revision: 1.3 $  $Date: 2005/02/15 22:37:02 $ 
 */

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;

public interface JavaPackage extends EPackage{
	
	public static final String PACKAGE_ID = "*package";	// The id (the part that goes after the '#' for a java package.)
														// There will only be one package per java resource so it
														// can be unique. No java types, methods, fields ids can
														// start with an asterick so will be unique.
														
	public static final String PRIMITIVE_PACKAGE_NAME = "_-javaprim";
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of JavaClasses references
	 */
	EList getJavaClasses();

	public String getPackageName() ;

} //JavaPackage





