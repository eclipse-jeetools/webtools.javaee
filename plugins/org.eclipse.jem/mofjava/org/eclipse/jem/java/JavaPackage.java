package org.eclipse.jem.java;
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
 *  $RCSfile: JavaPackage.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/13 16:16:21 $ 
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





