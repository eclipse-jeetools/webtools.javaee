/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.internal.webservice.adapter.AdapterViewerItem;
import org.eclipse.jst.j2ee.webservice.plugin.WebServicePlugin;


// Non-generated provider code

public abstract class AbstractATKUIItemProvider extends ItemProviderAdapter {
	public AbstractATKUIItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Get the resource locator for this adapter's resources.
	 */
	protected ResourceLocator getResourceLocator() {
		return WebServicePlugin.getInstance();
	}

	/**
	 * This implements
	 * {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren}by calling
	 * {@link #getChildrenReferences getChildrenReferences}and using those to collect the children.
	 */
	public Collection getChildren(Object object) {
		if (object instanceof EList)
			return getChildrenFromEList((EList) object);
		else if (object instanceof AdapterViewerItem)
			return getChildrenFromAdapterViewerItem((AdapterViewerItem) object);
		else
			return super.getChildren(object);
	}

	protected Collection getChildrenFromEList(EList eList) {
		Collection result = new ArrayList();
		for (Iterator it = eList.iterator(); it.hasNext();)
			result.addAll(super.getChildren(it.next()));
		return result;
	}

	protected Collection getChildrenFromAdapterViewerItem(AdapterViewerItem adapterViewerItem) {
		Collection result = new ArrayList();
		EList eObjectList = adapterViewerItem.getEObjectList();
		EStructuralFeature feature = adapterViewerItem.getEStructuralFeature();
		for (Iterator it = eObjectList.iterator(); it.hasNext();) {
			EObject eObject = (EObject) it.next();
			Object object = eObject.eGet(feature);
			if (object != null) {
				if (object instanceof List)
					result.addAll((List) object);
				else
					result.add(object);
			}
		}
		return result;
	}

	protected String toDisplayString(String s) {
		return (s != null) ? s : ""; //$NON-NLS-1$
	}
}