/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.plugin;
/*


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
