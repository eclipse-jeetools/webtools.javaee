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
package org.eclipse.jst.j2ee.internal.ejb.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.ExcludeList;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;


/**
 * @author cbridgha
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MethodElementHelper {
	public static final int NONE = 0;
	public static final int METHOD_PERMISSION = 1;
	public static final int METHOD_TRANSACTION = 2;
	public static final int EXCLUDE_LIST = 3;
	public static int getContainedType(MethodElement me) {
		EObject container = me.eContainer();
		if (container == null)
			return NONE;
		if (container instanceof MethodPermission)
			return METHOD_PERMISSION;
		if (container instanceof MethodTransaction)
			return METHOD_TRANSACTION;
		if (container instanceof ExcludeList)
			return EXCLUDE_LIST;
		return NONE;
	}
	public static MethodPermission getMethodPermission(MethodElement me) {
		if (METHOD_PERMISSION == getContainedType(me))
			return (MethodPermission) me.eContainer();
		return null;
	}
	public static MethodTransaction getMethodTransaction(MethodElement me) {
		if (METHOD_TRANSACTION == getContainedType(me))
			return (MethodTransaction) me.eContainer();
		return null;
	}
	public static ExcludeList getExcludeList(MethodElement me) {
		if (EXCLUDE_LIST == getContainedType(me))
			return (ExcludeList) me.eContainer();
		return null;
	}

}
