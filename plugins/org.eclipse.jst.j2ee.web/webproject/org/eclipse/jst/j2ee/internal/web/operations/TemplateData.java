/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;



public class TemplateData {
	protected java.lang.String wtFileName = "";//$NON-NLS-1$
	protected java.lang.String wtCSSName = "";//$NON-NLS-1$
	protected java.lang.String wtContainerLocatoin = "";//$NON-NLS-1$
	protected java.lang.String taglibs;

	/**
	 * TemplateData constructor comment.
	 */
	public TemplateData() {
		super();
	}

	/**
	 */
	public String getContainerLocatoin() {
		return wtContainerLocatoin;
	}

	/**
	 * Insert the method's description here. Creation date: (6/4/2001 5:24:30 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getCSSName() {
		return wtCSSName;
	}

	/**
	 * Insert the method's description here. Creation date: (6/4/2001 5:22:56 PM)
	 * 
	 * @return java.lang.String
	 */
	public String getFileName() {
		return wtFileName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/15/2001 4:16:10 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTaglibs() {
		return taglibs;
	}

	/**
	 */
	public void setContainerLocatoin(String newFileName) {
		wtContainerLocatoin = newFileName;
	}

	/**
	 * Insert the method's description here. Creation date: (6/4/2001 5:24:30 PM)
	 * 
	 * @param newCSSName
	 *            java.lang.String
	 */
	public void setCSSName(java.lang.String newCSSName) {
		wtCSSName = newCSSName;
	}

	/**
	 * Insert the method's description here. Creation date: (6/4/2001 5:22:56 PM)
	 * 
	 * @param newWtFileName
	 *            java.lang.String
	 */
	public void setFileName(String newFileName) {
		wtFileName = newFileName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/15/2001 4:16:10 PM)
	 * 
	 * @param newTaglibs
	 *            java.lang.String
	 */
	public void setTaglibs(java.lang.String newTaglibs) {
		taglibs = newTaglibs;
	}
}