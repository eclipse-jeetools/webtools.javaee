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
package org.eclipse.jem.internal.proxy.vm.remote;

import java.util.Arrays;
/*


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
	
	/**
	 * See if the two arrays are "equal" (not identidy, but that the contents are equal).
	 * @param array1 must be an array
	 * @param array2 must be an array
	 * @return true if semantically equal using {@link Arrays#equals(Object[], Object[])};
	 * 
	 * @see Arrays#equals(Object[], Object[]);
	 * @since 1.2.1
	 */
	public static boolean equals(Object array1, Object array2) {
		if (array1 == array2)
			return true;
		if (array1 == null || array2 == null)
			return false;
		Class aclass = array1.getClass();
		Class bclass = array2.getClass();
		if (!aclass.isArray() || !bclass.isArray())
			return false;
		Class acomp = aclass.getComponentType();
		Class bcomp = bclass.getComponentType();
		if (acomp.isPrimitive() || bcomp.isPrimitive()) {
			if (acomp != bcomp)
				return false;
			if (acomp == Integer.TYPE)
				return Arrays.equals((int[]) array1, (int[]) array2);
			else if (acomp == Boolean.TYPE)
				return Arrays.equals((boolean[]) array1, (boolean[]) array2);
			else if (acomp == Long.TYPE)
				return Arrays.equals((long[]) array1, (long[]) array2);
			else if (acomp == Short.TYPE)
				return Arrays.equals((short[]) array1, (short[]) array2);
			else if (acomp == Double.TYPE)
				return Arrays.equals((double[]) array1, (double[]) array2);
			else if (acomp == Float.TYPE)
				return Arrays.equals((float[]) array1, (float[]) array2);
			else if (acomp == Character.TYPE)
				return Arrays.equals((char[]) array1, (char[]) array2);
			else if (acomp == Byte.TYPE)
				return Arrays.equals((byte[]) array1, (byte[]) array2);
			else
				return false;
		} else
			return Arrays.equals((Object[]) array1, (Object[]) array2);
	}
}
