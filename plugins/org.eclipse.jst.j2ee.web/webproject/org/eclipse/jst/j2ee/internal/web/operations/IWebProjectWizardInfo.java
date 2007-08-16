/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jdt.core.IClasspathEntry;

public interface IWebProjectWizardInfo {
	/**
	 * Adds entries to the class path for this project
	 */
	public void addToClasspathEntries(IClasspathEntry[] entries);

	/**
	 * Get the name of the specified project Creation date: (11/09/00 10:05:24 AM)
	 * 
	 * @return java.lang.String
	 */
	public String getProjectName();

	/**
	 * Get the Enterprise Application Project
	 */
	public String getEARProjectName();

	/**
	 * Get the name of the web content folder. If not set then return the current default.
	 */
	public String getWebContentName();

	/**
	 * Set the name of the web content folder
	 */
	public void setWebContentName(String name);

	/**
	 * Get the name of the Java source folder If not set then return the current default.
	 */
	public String getJavaSourceName();

	/**
	 * Set the name of the Java source folder
	 */
	public void setJavaSourceName(String name);

	/**
	 * Return an Object for the assocated properties or null
	 */
	public Object getProperty(String propertyName);

	/**
	 * Set an Object for the assocated properties or null
	 */
	public void setProperty(String propertyName, Object value);


}

