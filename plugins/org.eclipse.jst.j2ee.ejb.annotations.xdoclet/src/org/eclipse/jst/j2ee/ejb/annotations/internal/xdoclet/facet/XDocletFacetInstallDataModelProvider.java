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
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.facet;

import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;

public abstract class XDocletFacetInstallDataModelProvider extends FacetInstallDataModelProvider implements
		IXDocletFacetInstallDataModelProperties {

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(XDOCLETHOME);
		names.add(XDOCLETVERSION);
		return names;
	}

	public Object getDefaultProperty(String propertyName) {
		XDocletPreferenceStore store = new XDocletPreferenceStore(null);
		if (propertyName.equals(XDOCLETHOME)) {
			return store.getProperty(XDOCLETHOME);
		} else if (propertyName.equals(XDOCLETVERSION)) {
			return store.getProperty(XDOCLETVERSION);
		} else if (propertyName.equals(FACET_ID)) {
			return getFacetID();
		}
		return super.getDefaultProperty(propertyName);
	}

	protected abstract String getFacetID();

	public boolean propertySet(String propertyName, Object propertyValue) {
		return super.propertySet(propertyName, propertyValue);
	}

	public boolean isPropertyEnabled(String propertyName) {
		return super.isPropertyEnabled(propertyName);
	}

	public IStatus validate(String name) {
		return super.validate(name);
	}

}
