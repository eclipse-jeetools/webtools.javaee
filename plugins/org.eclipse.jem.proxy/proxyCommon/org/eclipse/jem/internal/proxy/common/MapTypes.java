/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: MapTypes.java,v $
 *  $Revision: 1.1 $  $Date: 2004/05/26 22:02:08 $ 
 */
package org.eclipse.jem.internal.proxy.common;

import java.util.HashMap;
 

/**
 * This is used for mapping between JNI format and non-JNI format. It also has the
 * maps for primitives.
 * @since 1.0.0
 */
public class MapTypes {

	public final static HashMap MAP_SHORTSIG_TO_TYPE = new HashMap(8);
	public final static HashMap MAP_TYPENAME_TO_SHORTSIG = new HashMap(8);
	static {
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("B", Byte.TYPE); //$NON-NLS-1$
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("C", Character.TYPE); //$NON-NLS-1$
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("D", Double.TYPE);		 //$NON-NLS-1$
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("F", Float.TYPE); //$NON-NLS-1$
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("I", Integer.TYPE); //$NON-NLS-1$
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("J", Long.TYPE); //$NON-NLS-1$
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("S", Short.TYPE); //$NON-NLS-1$
		MapTypes.MAP_SHORTSIG_TO_TYPE.put("Z", Boolean.TYPE); //$NON-NLS-1$
		
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("byte","B"); //$NON-NLS-1$ //$NON-NLS-2$
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("char","C"); //$NON-NLS-1$ //$NON-NLS-2$
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("double","D"); //$NON-NLS-1$ //$NON-NLS-2$
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("float","F"); //$NON-NLS-1$ //$NON-NLS-2$
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("int","I"); //$NON-NLS-1$ //$NON-NLS-2$
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("long","J"); //$NON-NLS-1$ //$NON-NLS-2$
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("short","S"); //$NON-NLS-1$ //$NON-NLS-2$
		MapTypes.MAP_TYPENAME_TO_SHORTSIG.put("boolean","Z"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	

	/**
	 * Convert formal type name for an array (i.e. java.lang.Object[]
	 * to the jni format (i.e. [Ljava.lang.Object;)
	 * This is used when a name is passed in from the IDE side.
	 * The VM side uses the jni format, and all of proxy uses the jni format.
	 * 
	 * @param classname
	 * @return string jni form of name.
	 * 
	 * @since 1.0.0
	 */
	public static String getJNIFormatName(String classname) {
		if (classname.length() == 0 || !classname.endsWith("]")) //$NON-NLS-1$
			return classname;	// Not an array,or invalid
		
		StringBuffer jni = new StringBuffer(classname.length());
		int firstOpenBracket = classname.indexOf('[');
		int ob = firstOpenBracket;
		while (ob > -1) {
			int cb = classname.indexOf(']', ob);
			if (cb == -1)
				break;
			jni.append('[');
			ob = classname.indexOf('[', cb);
		}
		
		String finalType = classname.substring(0, firstOpenBracket).trim();
		if (finalType != null) {
			String shortSig = (String) MapTypes.MAP_TYPENAME_TO_SHORTSIG.get(finalType);
			if (shortSig == null) {
				jni.append('L');
				jni.append(finalType);
				jni.append(';');
			} else {
				jni.append(shortSig);
			}
		}
		
		return jni.toString();
	}
}
