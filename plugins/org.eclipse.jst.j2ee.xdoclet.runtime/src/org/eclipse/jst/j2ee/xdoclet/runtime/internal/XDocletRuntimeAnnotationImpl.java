/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.xdoclet.runtime.internal;

import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.IXDocletRuntimeAnnotation;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;

public abstract class XDocletRuntimeAnnotationImpl implements
		IXDocletRuntimeAnnotation {

	XDocletPreferenceStore preferenceStore;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#setPreferenceStore(org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore)
	 */
	public void setPreferenceStore(XDocletPreferenceStore preferenceStore) {
		this.preferenceStore = preferenceStore;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.xdoclet.runtime.internal.Xdoc#getPreferenceStore()
	 */
	public XDocletPreferenceStore getPreferenceStore() {
		return preferenceStore;
	}

}
