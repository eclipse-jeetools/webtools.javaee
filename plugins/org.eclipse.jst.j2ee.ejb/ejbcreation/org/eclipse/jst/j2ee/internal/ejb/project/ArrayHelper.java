/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project;


/**
 * Insert the type's description here. Creation date: (09/07/00 9:06:48 AM)
 * 
 * @author: Administrator
 */
public class ArrayHelper {
	/**
	 * ArrayHelper constructor comment.
	 */
	public ArrayHelper() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (09/07/00 9:06:53 AM)
	 */
	void newMethod() {
		//do nothing
	}

	/**
	 * Sort the strings in the given collection.
	 */
	private static void quickSort(String[] sortedCollection, int left, int right) {
		int original_left = left;
		int original_right = right;
		String mid = sortedCollection[(left + right) / 2];
		do {
			while (sortedCollection[left].compareTo(mid) < 0) {
				left++;
			}
			while (mid.compareTo(sortedCollection[right]) < 0) {
				right--;
			}
			if (left <= right) {
				String tmp = sortedCollection[left];
				sortedCollection[left] = sortedCollection[right];
				sortedCollection[right] = tmp;
				left++;
				right--;
			}
		} while (left <= right);
		if (original_left < right) {
			quickSort(sortedCollection, original_left, right);
		}
		if (left < original_right) {
			quickSort(sortedCollection, left, original_right);
		}
	}

	/**
	 * Sorts an array of strings in place using quicksort.
	 */
	public static int[] searchWhenTyping(String[] array, String typedText) {
		int[] indices = new int[array.length];
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i].startsWith(typedText)) {
				indices[count] = i;
				count++;
			}
		}
		int[] result = new int[count];
		for (int i = 0; i < count; i++) {
			result[i] = indices[i];
		}
		return result;
	}

	/**
	 * Sort an int array Shell Sort from K&R, pg 108
	 */
	public static void sort(int[] items) {
		int length = items.length;
		for (int gap = length / 2; gap > 0; gap /= 2) {
			for (int i = gap; i < length; i++) {
				for (int j = i - gap; j >= 0; j -= gap) {
					if (items[j] <= items[j + gap]) {
						int swap = items[j];
						items[j] = items[j + gap];
						items[j + gap] = swap;
					}
				}
			}
		}
	}

	/**
	 * Sorts an array of strings in place using quicksort.
	 */
	public static void sort(String[] strings) {
		if (strings.length > 1)
			quickSort(strings, 0, strings.length - 1);
	}
}