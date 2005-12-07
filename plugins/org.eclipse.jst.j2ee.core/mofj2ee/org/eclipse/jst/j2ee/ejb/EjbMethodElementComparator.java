/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb;

import java.util.Comparator;

/**
 * @since 1.0
 */

public class EjbMethodElementComparator implements Comparator {
	static final int HOME = MethodElementKind.HOME;
	static final int REMOTE = MethodElementKind.REMOTE;
	static final int LOCAL_HOME = MethodElementKind.LOCAL_HOME;
	static final int LOCAL = MethodElementKind.LOCAL;
	static final int UNSPECIFIED = MethodElementKind.UNSPECIFIED;

	/**
	 * Constructor for EjbMethodElementComparator.
	 */
	public EjbMethodElementComparator() {
		super();
	}

	/**
	 * Compares two arguments for order.
	 * @see Comparator#compare(Object, Object)
	 *@param o1 the first object to be compared
	 *@param 02 the second object to be compared
	 *@return a negative integer, zero, or a positive integer
	 */
	public int compare(Object o1, Object o2) {
		return compare((MethodElement) o1, (MethodElement) o2);
	}
	/**
	 * Sort by type first in the following order:
	 * Home, Remote, LocalHome, Local, Unspecified
	 * Then alphabetically order.
	 */
	protected int compare(MethodElement me1, MethodElement me2) {
		int type1, type2;
		type1 = me1.getType().getValue();
		type2 = me2.getType().getValue();
		if (type1 == type2)
			return compareSignatures(me1, me2);
		return compareTypes(type1, type2);
	}

	protected int compareTypes(int type1, int type2) {
		if (type1 == HOME)
			return -1;
		if (type1 == REMOTE) {
			if (type2 == HOME)
				return 1;
			return -1;
		}
		if (type1 == LOCAL_HOME) {
			if (type2 == HOME || type2 == REMOTE)
				return 1;
			return -1;
		}
		if (type1 == LOCAL) {
			if (type2 == HOME || type2 == REMOTE || type2 == LOCAL_HOME)
				return 1;
			return -1;
		}
		if (type1 == UNSPECIFIED) {
			if (type2 == HOME || type2 == REMOTE || type2 == LOCAL_HOME || type2 == LOCAL)
				return 1;
			return -1;
		}
		return -1;
	}
	protected int compareSignatures(MethodElement me1, MethodElement me2) {
		String sig1, sig2;
		sig1 = me1.getSignature();
		sig2 = me2.getSignature();
		return sig1.compareTo(sig2);
	}
}

