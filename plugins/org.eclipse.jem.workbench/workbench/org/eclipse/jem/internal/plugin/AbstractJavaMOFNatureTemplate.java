package org.eclipse.jem.internal.plugin;
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
 *  $RCSfile: AbstractJavaMOFNatureTemplate.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:33:53 $ 
 */

/**
 * Insert the type's description here.
 * Creation date: (3/11/2001 8:42:26 PM)
 * @author: Administrator
 */
public abstract class AbstractJavaMOFNatureTemplate implements IJavaMOFNature {
	
	private String classPath;
	private String sourcePath;
	/**
	 * JavaMOFNatureTemplate constructor comment.
	 */
	public AbstractJavaMOFNatureTemplate() {
		super();
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (2/16/2001 7:49:39 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getClassPath() {
		return classPath;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (11/02/00 9:33:59 AM)
	 * @return org.eclipse.emf.ecore.resource.ResourceSet
	 */
	public org.eclipse.emf.ecore.resource.ResourceSet getContext() {
		//This should be removed and the interfaces fixed up.
		return null;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (2/16/2001 7:49:39 AM)
	 * @return java.lang.String
	 */
	public java.lang.String getSourcePath() {
		return sourcePath;
	}
	/**
	 * The default is to do nothing.  Override to set default values.
	 * @param aProjectName java.lang.String
	 */
	protected void initialize(String aProjectName) {}
	/**
	 * Insert the method's description here.
	 * Creation date: (2/16/2001 7:49:39 AM)
	 * @param newClassPath java.lang.String
	 */
	public void setClassPath(java.lang.String newClassPath) {
		classPath = newClassPath;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (2/16/2001 7:49:39 AM)
	 * @param newSourcePath java.lang.String
	 */
	public void setSourcePath(java.lang.String newSourcePath) {
		sourcePath = newSourcePath;
	}
	/**
	*Shuts down the MOF Nature
	*/
	public void shutdown() {}
}