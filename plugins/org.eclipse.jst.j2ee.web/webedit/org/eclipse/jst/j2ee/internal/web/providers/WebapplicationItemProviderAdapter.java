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
package org.eclipse.jst.j2ee.internal.web.providers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.web.plugin.WebPlugin;


/**
 * This extended item provider supports two addition features, (@link CreateChildCommand} and
 * {@link CreateChildrenCommand}. These two actions are implemented uniformly on all our item
 * adapters using this common base class.
 */
public class WebapplicationItemProviderAdapter extends org.eclipse.emf.edit.provider.ItemProviderAdapter {

	/**
	 * This creates and instance from an adapter factory and a domain notifier.
	 */
	protected WebapplicationItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This implements the default behavior for
	 * {@link org.eclipse.jst.j2ee.webapplication.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		return null;
	}

	/**
	 * This creates the default new children for {@link CreateChildrenCommand}.
	 */
	public Collection createChildren(Object object) {
		return Collections.EMPTY_LIST;
	}

	/**
	 * This returns the default create child image.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		Collection achildrenReferences = getChildrenReferences(object);
		if (achildrenReferences.size() != 1) {
			return WebPlugin.getDefault().getImage("create_child"); //$NON-NLS-1$
		}
		return WebPlugin.getDefault().getImage(refObject.eClass().getName() + "Create" + //$NON-NLS-1$
					((EReference) achildrenReferences.iterator().next()).getEType().getName());

	}

	/**
	 * This returns the default create children label.
	 */
	public String getCreateChildrenText(Object object) {
		//EObject refObject = (EObject) object;
		Collection achildrenReferences = getChildrenReferences(object);
		if (achildrenReferences.size() != 1) {
			return WebAppEditResourceHandler.getString("Create_Two_Children_UI_"); //$NON-NLS-1$ = "Create Two Children"
		}
		return WebAppEditResourceHandler.getString("9concat_UI_", (new Object[]{((EReference) achildrenReferences.iterator().next()).getEType().getName()})); //$NON-NLS-1$ = "Create Two {0}s"

	}

	/**
	 * This returns the default create children help text.
	 */
	public String getCreateChildrenToolTipText(Object object) {
		EObject refObject = (EObject) object;
		Collection achildrenReferences = getChildrenReferences(object);
		if (achildrenReferences.size() != 1) {
			return WebAppEditResourceHandler.getString("7concat_UI_", (new Object[]{refObject.eClass().getName()})); //$NON-NLS-1$ = "Create two new children for the selected {0}."
		}
		return WebAppEditResourceHandler.getString("8concat_UI_", (new Object[]{((EReference) achildrenReferences.iterator().next()).getEType().getName(), refObject.eClass().getName()})); //$NON-NLS-1$
		//$NON-NLS-1$ = "Create two new children of type {0} for the selected {1}."

	}

	/**
	 * This returns the default create child label.
	 */
	public String getCreateChildText(Object object) {
		//EObject refObject = (EObject) object;
		Collection achildrenReferences = getChildrenReferences(object);
		if (achildrenReferences.size() != 1) {
			return WebAppEditResourceHandler.getString("Create_Child_UI_"); //$NON-NLS-1$ = "Create Child"
		}
		return WebAppEditResourceHandler.getString("6concat_UI_", (new Object[]{((EReference) achildrenReferences.iterator().next()).getEType().getName()})); //$NON-NLS-1$ = "Create {0}"

	}

	/**
	 * This returns the default create child help text.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		Collection achildrenReferences = getChildrenReferences(object);
		if (achildrenReferences.size() != 1) {
			return WebAppEditResourceHandler.getString("2concat_UI_", (new Object[]{refObject.eClass().getName()})); //$NON-NLS-1$ = "Create a new child for the selected {0}."
		}
		return WebAppEditResourceHandler.getString("4concat_UI_", (new Object[]{((EReference) achildrenReferences.iterator().next()).getEType().getName(), refObject.eClass().getName()})); //$NON-NLS-1$
		//$NON-NLS-1$ = "Create a child of type {0} for the selected {1}."

	}
}