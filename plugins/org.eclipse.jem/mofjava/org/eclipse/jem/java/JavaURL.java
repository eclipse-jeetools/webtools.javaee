/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
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
 *  $RCSfile: JavaURL.java,v $
 *  $Revision: 1.8 $  $Date: 2006/05/17 20:13:08 $ 
 */


/**
 * @deprecated Use the appropriate {@link JavaRefFactory} URI methods instead.
 * 
 * @since 1.2.0
 */
public class JavaURL extends org.eclipse.jem.java.internal.impl.URL {
	public static final String JAVA_PROTOCOL_URI_PREFIX = "java:/"; //$NON-NLS-1$
	/**
	 * JavaURL constructor comment.
	 * @param urlString java.lang.String
	 * 
	 * @deprecated Use {@link JavaRefFactory#createTypeURI(String)} or {@link JavaRefFactory#createPackageURI(String)}
	 */
	public JavaURL(String urlString) {
		super(urlString);
	}
	/**
	 * JavaURL constructor comment.
	 * @param nameSpaceName java.lang.String
	 * @param iD java.lang.String
	 * @deprecated Use {@link JavaRefFactory#createTypeURI(String, String)}
	 * 
	 */
	public JavaURL(String nameSpaceName, String iD) {
		super(null, null);
		initializeNamespaceString(nameSpaceName);
		this.ID = iD;
	}
	
	/**
	 * 
	 * @return
	 * @deprecated Use {@link JavaRefFactory#getTypeName(org.eclipse.emf.common.util.URI)} 
	 */
	public String getClassName() {
		return ID;
	}
	
	/**
	 * This method was created in VisualAge.
	 * @return java.lang.String
	 * 
	 * @deprecated Use {@link JavaRefFactory#getFullTypeName(org.eclipse.emf.common.util.URI)}
	 */
	public String getFullString() {
		StringBuffer buf = new StringBuffer();
		if (namespaceName != null) {
			buf.append(namespaceName);
			if (ID != null)
				buf.append("#"); //$NON-NLS-1$
		}
		if (ID != null)
			buf.append(ID);
		return buf.toString();
	}
	
	/**
	 * 
	 * @return
	 * @deprecated Use {@link JavaRefFactory#getPackageName(org.eclipse.emf.common.util.URI)}
	 * @since 1.2.0
	 */
	public String getPackageName() {
		String internalName = namespaceName.substring(JAVA_PROTOCOL_URI_PREFIX.length(), namespaceName.length());
		return JavaPackage.PRIMITIVE_PACKAGE_NAME.equals(internalName) ? "" : internalName; //$NON-NLS-1$
	}
	/* If we don't have a # sign to delimit the start of the java package us the inherited behavior
	 */
	public void initializeFromString(String url) {
		if (url.indexOf("#") != -1) //$NON-NLS-1$
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
	
	/**
	 * 
	 * @param aUrlString
	 * @return
	 * @deprecated Use {@link JavaRefFactory#isJavaURI(org.eclipse.emf.common.util.URI)} 
	 */
	public static boolean isJavaURL(String aUrlString) {
		if (aUrlString == null)
			return false;
		return aUrlString.startsWith(JAVA_PROTOCOL_URI_PREFIX);
	}

	public String toString() {
		return "URL(" + getFullString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
