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
 *  $RCSfile: JavaProjectInfo.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:33:53 $ 
 */
/**
 * Insert the type's description here.
 * Creation date: (05/07/01 10:09:26 PM)
 * @author: Administrator
 */
public class JavaProjectInfo implements IJavaProjectInfo {
	
	public final static String DEFAULT_JAVA_OUTPUT_PATH = "bin";//$NON-NLS-1$
	public final static String DEFAULT_SOURCE_PATH = "source";//$NON-NLS-1$
	protected String sourcePath;
/**
 * JavaProjectInfo constructor comment.
 */
public JavaProjectInfo() {
	super();
}
/**
 * Subclasses should override as necessary
 */
protected String getDefaultSourcePath() {
	return DEFAULT_SOURCE_PATH;
}
/**
 * Get the class path for the reciever. By default ask the template
 * @return java.lang.String
 */
public String getSourcePath() {
	if (sourcePath == null)
		sourcePath = getDefaultSourcePath();
	return sourcePath;
}
public void setSourcePath(String path) {
	sourcePath = path;
}
}
