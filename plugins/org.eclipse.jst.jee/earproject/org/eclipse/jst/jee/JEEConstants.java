/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee;
/**
 * This is a catalog of useful constants for the archive support.  Can be used to
 * store relative paths to specific xml and xmi resources.  
 */
public interface JEEConstants extends JEEVersionConstants {
	
	String APPLICATION_SCHEMA_5_0       =   "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application_5.xsd";//$NON-NLS-1$
	

	String APPLICATION_SCHEMA_LOC_5_0 	= "http://java.sun.com/xml/ns/javaee/application_5.xsd"; //$NON-NLS-1$

	String WEB_INF_CLASSES				=	"WEB-INF/classes"; //$NON-NLS-1$

	String MANIFEST_URI	 				=	"META-INF/MANIFEST.MF"; //$NON-NLS-1$

}