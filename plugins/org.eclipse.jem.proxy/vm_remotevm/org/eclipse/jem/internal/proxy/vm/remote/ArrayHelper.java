package org.eclipse.jem.internal.proxy.vm.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ArrayHelper.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:57:54 $ 
 */


/**
 * This class provides similiar function as the java.lang.reflect.Array class but
 * it handles multi-dimensional arrays. Without this helper, the client would have
 * to walk down through each dimension executing a get command. This would mean
 * many transactions instead of one provided by this helper.
 *
 * It also provides some other helper functions for arrays that Array doesn't provide.
 */

public final class ArrayHelper {
	// It can never be instantiated.
	private ArrayHelper() {
	}
	
	public static Object get(Object array, int[] indexes) {
		Object answer = array;
		for (int i=0; i<indexes.length; i++)
			answer = java.lang.reflect.Array.get(answer, indexes[i]);
		return answer;
	}
	
	public static byte getByte(Object array, int[] indexes) {
		return ((Byte) get(array, indexes)).byteValue();
	}
	public static boolean getBoolean(Object array, int[] indexes) {
		return ((Boolean) get(array, indexes)).booleanValue();
	}
	public static char getChar(Object array, int[] indexes) {
		return ((Character) get(array, indexes)).charValue();
	}	
	public static double getDouble(Object array, int[] indexes) {
		return ((Double) get(array, indexes)).doubleValue();
	}
	public static float getFloat(Object array, int[] indexes) {
		return ((Float) get(array, indexes)).floatValue();
	}
	public static int getInt(Object array, int[] indexes) {
		return ((Integer) get(array, indexes)).intValue();
	}
	public static long getLong(Object array, int[] indexes) {
		return ((Long) get(array, indexes)).longValue();
	}
	public static short getShort(Object array, int[] indexes) {
		return ((Short) get(array, indexes)).shortValue();
	}
	
	public static void set(Object array, int[] indexes, Object value) {
		Object subArray = array;
		int upTo = indexes.length-1;
		for (int i=0; i<upTo; i++)
			subArray = java.lang.reflect.Array.get(subArray, indexes[i]);
		java.lang.reflect.Array.set(subArray, indexes[upTo], value);
	}
}