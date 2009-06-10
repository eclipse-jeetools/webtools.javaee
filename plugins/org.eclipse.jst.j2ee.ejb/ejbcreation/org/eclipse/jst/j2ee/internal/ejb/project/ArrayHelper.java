/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
		int localLeft = left;
		int localRight = right;
		int original_left = localLeft;
		int original_right = localRight;
		String mid = sortedCollection[(localLeft + localRight) / 2];
		do {
			while (sortedCollection[localLeft].compareTo(mid) < 0) {
				localLeft++;
			}
			while (mid.compareTo(sortedCollection[localRight]) < 0) {
				localRight--;
			}
			if (localLeft <= localRight) {
				String tmp = sortedCollection[localLeft];
				sortedCollection[localLeft] = sortedCollection[localRight];
				sortedCollection[localRight] = tmp;
				localLeft++;
				localRight--;
			}
		} while (localLeft <= localRight);
		if (original_left < localRight) {
			quickSort(sortedCollection, original_left, localRight);
		}
		if (localLeft < original_right) {
			quickSort(sortedCollection, localLeft, original_right);
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
