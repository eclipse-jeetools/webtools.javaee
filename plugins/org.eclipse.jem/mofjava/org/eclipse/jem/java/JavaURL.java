/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java;
/*
 *  $RCSfile: JavaURL.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2004/06/24 18:17:06 $ 
 */


public class JavaURL extends org.eclipse.jem.java.impl.URL {
	public static final String JAVA_PROTOCOL_URI_PREFIX = "java:/";
	/**
	 * JavaURL constructor comment.
	 * @param urlString java.lang.String
	 */
	public JavaURL(String urlString) {
		super(urlString);
	}
	/**
	 * JavaURL constructor comment.
	 * @param nameSpaceName java.lang.String
	 * @param iD java.lang.String
	 */
	public JavaURL(String nameSpaceName, String iD) {
		super(null, null);
		initializeNamespaceString(nameSpaceName);
		this.ID = iD;
	}
	public String getClassName() {
		return ID;
	}
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 */
	public String getFullString() {
		StringBuffer buf = new StringBuffer();
		if (namespaceName != null) {
			buf.append(namespaceName);
			if (ID != null)
				buf.append("#");
		}
		if (ID != null)
			buf.append(ID);
		return buf.toString();
	}
	public String getPackageName() {
		String internalName = namespaceName.substring(JAVA_PROTOCOL_URI_PREFIX.length(), namespaceName.length());
		return JavaPackage.PRIMITIVE_PACKAGE_NAME.equals(internalName) ? "" : internalName;
	}
	/* If we don't have a # sign to delimit the start of the java package us the inherited behavior
	 */
	public void initializeFromString(String url) {
		if (url.indexOf("#") != -1)
			super.initializeFromString(url);
		else {
			int endOfPackageName = url.lastIndexOf('.');
			if (endOfPackageName == -1)
				initializeNamespaceString(null);
			else
				initializeNamespaceString(url.substring(0, endOfPackageName));
			ID = url.substring(endOfPackageName + 1);
		}

	}
	/* 
	 */
	public void initializeNamespaceString(String aNamespaceName) {
		if (aNamespaceName == null)
			namespaceName = JAVA_PROTOCOL_URI_PREFIX;
		else
			namespaceName = JAVA_PROTOCOL_URI_PREFIX + aNamespaceName;
	}
	public static boolean isJavaURL(String aUrlString) {
		if (aUrlString == null)
			return false;
		return aUrlString.startsWith(JAVA_PROTOCOL_URI_PREFIX);
	}

	public String toString() {
		return "URL(" + getFullString() + ")";
	}
}
