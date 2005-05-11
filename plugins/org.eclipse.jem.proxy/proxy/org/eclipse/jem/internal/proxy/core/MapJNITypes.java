/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: MapJNITypes.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import org.eclipse.jem.internal.proxy.common.MapTypes;
 

/**
 * This is a public class to map between JNI and normal format (e.g. "[Ljava.lang.String;" to "java.lang.String[]") and back again.
 * This is for arrays, non-arrays will be left alone since they are already correct.
 * @since 1.1.0
 */
public class MapJNITypes {

	/**
	 * Convert the formal type name to the JNI format.
	 * @param formalTypeName
	 * @return JNI format
	 * 
	 * @since 1.1.0
	 */
	public static String getJNITypeName(String formalTypeName) {
		return MapTypes.getJNIFormatName(formalTypeName);
	}
	
	/**
	 * Convert the JNI format to the formal type name.
	 * @param jniTypeName
	 * @return formal type name or "" if not valid.
	 * 
	 * @since 1.1.0
	 */
	public static String getFormalTypeName(String jniTypeName) {
		return MapTypes.getFormalTypeName(jniTypeName);
	}
	
	/**
	 * Return whether the formal type name is for a primitive or not.
	 * @param formalType
	 * @return <code>true</code> if for a primitive.
	 * 
	 * @since 1.1.0
	 */
	public static boolean isFormalTypePrimitive(String formalType) {
		return MapTypes.MAP_TYPENAME_TO_SHORTSIG.get(formalType) != null;
	}
	
	/**
	 * Convert the formal type name, with the given number of dimensions,
	 * to an array JNI type.
	 * @param finalComponent final component name, should not be an array type.
	 * @param dimensions number of dimensions for the array.
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public static String getJNITypeName(String finalComponent, int dimensions) {
		return MapTypes.getJNITypeName(finalComponent, dimensions);
	}
}
