/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;


import java.util.Comparator;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;


// This class sorts the name of Enterprise Beans

public class EJBNameComparator implements Comparator {
	private static EJBNameComparator singleton;

	public int compare(Object o1, Object o2) {
		String name1, name2;
		name1 = ((EnterpriseBean) o1).getName();
		name2 = ((EnterpriseBean) o2).getName();
		if (name1 == null) {
			if (name2 == null)
				return 0;
			return -1;
		} else if (name2 == null)
			return 1;
		else
			return name1.compareTo(name2);
	}

	public static Comparator singleton() {
		if (singleton == null)
			singleton = new EJBNameComparator();
		return singleton;
	}

}