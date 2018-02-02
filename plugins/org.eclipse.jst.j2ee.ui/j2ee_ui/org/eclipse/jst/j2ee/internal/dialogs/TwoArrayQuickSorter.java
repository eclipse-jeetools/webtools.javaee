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
package org.eclipse.jst.j2ee.internal.dialogs;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
import java.util.Comparator;

import org.eclipse.core.runtime.Assert;

/**
 * Quick sort to sort key-value pairs. The keys and arrays are specified in
 * separate arrays.
 * 
 * @plannedfor 2.0
 */
public class TwoArrayQuickSorter {

	private Comparator fComparator;

	/**
	 * Default comparator.
	 */
	public static final class StringComparator implements Comparator {
		private boolean fIgnoreCase;

		StringComparator(boolean ignoreCase) {
			fIgnoreCase = ignoreCase;
		}

		public int compare(Object left, Object right) {
			return fIgnoreCase ? ((String) left).compareToIgnoreCase((String) right) : ((String) left).compareTo((String) right);
		}
	}

	/**
	 * Creates a sorter with default string comparator. The keys are assumed to
	 * be strings.
	 * 
	 * @param ignoreCase
	 *            specifies whether sorting is case sensitive or not.
	 */
	public TwoArrayQuickSorter(boolean ignoreCase) {
		fComparator = new StringComparator(ignoreCase);
	}

	/**
	 * Creates a sorter with a comparator.
	 * 
	 * @param comparator
	 *            the comparator to order the elements. The comparator must not
	 *            be <code>null</code>.
	 */
	public TwoArrayQuickSorter(Comparator comparator) {
		fComparator = comparator;
	}

	/**
	 * Sorts keys and values in parallel.
	 * 
	 * @param keys
	 *            the keys to use for sorting.
	 * @param values
	 *            the values associated with the keys.
	 */
	public void sort(Object[] keys, Object[] values) {
		if ((keys == null) || (values == null)) {
			Assert.isTrue(false, "Either keys or values in null"); //$NON-NLS-1$
			return;
		}

		if (keys.length <= 1)
			return;

		internalSort(keys, values, 0, keys.length - 1);
	}

	private void internalSort(Object[] keys, Object[] values, int left, int right) {
		int localLeft = left;
		int localRight = right;
		int original_left = localLeft;
		int original_right = localRight;

		Object mid = keys[(localLeft + localRight) / 2];
		do {
			while (fComparator.compare(keys[localLeft], mid) < 0)
				localLeft++;

			while (fComparator.compare(mid, keys[localRight]) < 0)
				localRight--;

			if (localLeft <= localRight) {
				swap(keys, localLeft, localRight);
				swap(values, localLeft, localRight);
				localLeft++;
				localRight--;
			}
		} while (localLeft <= localRight);

		if (original_left < localRight)
			internalSort(keys, values, original_left, localRight);

		if (localLeft < original_right)
			internalSort(keys, values, localLeft, original_right);
	}

	/*
	 * Swaps x[a] with x[b].
	 */
	private static final void swap(Object x[], int a, int b) {
		Object t = x[a];
		x[a] = x[b];
		x[b] = t;
	}

}
