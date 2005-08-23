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
package org.eclipse.jst.j2ee.common.internal.util;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
/**
 * Insert the type's description here.
 * Creation date: (03/22/01 10:54:22 AM)
 * @author: Administrator
 */
public abstract class DefaultedAdapterImpl extends AdapterImpl {
	final static String ADAPTER_KEY = "DEFAULTED_ADAPTER"; //$NON-NLS-1$
	protected boolean fNotify = true;
/**
 * DefaultedAdapter constructor comment.
 */
public DefaultedAdapterImpl() {
	super();
}
public abstract EObject defaultContainer();
public boolean isAdapterForType(Object type) {
	return ADAPTER_KEY.equals(type);
}
public static boolean isDefault(Defaultable object) {
	Defaultor defaultor = (Defaultor) retrieveDefaultContainerFor(object);
	return defaultor != null && defaultor.isDefault(object);
}
		
/**
 * Insert the method's description here.
 * Creation date: (6/13/2001 8:43:41 PM)
 * @return boolean
 */
public boolean isNotify() {
	return fNotify;
}
public static DefaultedAdapterImpl retrieveAdapterFor(Defaultable notifier) {
	return (DefaultedAdapterImpl)EcoreUtil.getExistingAdapter(notifier,ADAPTER_KEY);
}
public static EObject retrieveDefaultContainerFor(Defaultable notifier) {
	DefaultedAdapterImpl adapter = retrieveAdapterFor(notifier);
	return adapter == null ? null : adapter.defaultContainer();
}
/**
 * Insert the method's description here.
 * Creation date: (6/13/2001 8:43:41 PM)
 * @param newNotify boolean
 */
public void setNotify(boolean newNotify) {
	fNotify = newNotify;
}
}


