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
package org.eclipse.jst.j2ee.common.internal.provider;



import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.internal.common.CommonEditResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;



/**
 * This extended item provider supports two addition features, (@link CreateChildCommand} and
 * {@link CreateChildrenCommand}. These two actions are implemented uniformly on all our item
 * adapters using this common base class.
 */
public class CommonItemProviderAdapter extends ItemProviderAdapter {

	/**
	 * This creates and instance from an adapter factory and a domain notifier.
	 */
	protected CommonItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This implements the default behavior for {@link CreateChildCommand}.
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
	 * This returns the default image for {@link com.ibm.etools.common.command.CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		Collection localChildrenReferences = getChildrenReferences(object);
		if (localChildrenReferences.size() != 1) {
			return J2EEPlugin.getPlugin().getImage("create_child");//$NON-NLS-1$
		}
		return J2EEPlugin.getPlugin().getImage(refObject.eClass().getName() + "Create" + //$NON-NLS-1$
					((EReference) localChildrenReferences.iterator().next()).getEType().getName());
	}

	/**
	 * This returns the default create children label.
	 */
	public String getCreateChildrenText(Object object) {
		//EObject refObject = (EObject)object;
		Collection localChildrenReferences = getChildrenReferences(object);
		if (localChildrenReferences.size() != 1) {
			return CommonEditResourceHandler.getString("Create_Two_Children_UI_"); //$NON-NLS-1$
		}
		return CommonEditResourceHandler.getString("21concat", (new Object[]{((EReference) localChildrenReferences.iterator().next()).getEType().getName()})); //$NON-NLS-1$
	}

	/**
	 * This returns the default create children help text.
	 */
	public String getCreateChildrenToolTipText(Object object) {
		EObject refObject = (EObject) object;
		Collection localChildrenReferences = getChildrenReferences(object);
		if (localChildrenReferences.size() != 1) {
			return CommonEditResourceHandler.getString("19concat_UI_", (new Object[]{refObject.eClass().getName()})); //$NON-NLS-1$
		}
		return CommonEditResourceHandler.getString("20concat_UI_", (new Object[]{((EReference) localChildrenReferences.iterator().next()).getEType().getName(), refObject.eClass().getName()})); //$NON-NLS-1$ = "Create two new children of type {0} for the selected {1}."
	}

	/**
	 * This returns the default label for {@link com.ibm.etools.common.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		//EObject refObject = (EObject)object;
		Collection localChildrenReferences = getChildrenReferences(object);
		if (localChildrenReferences.size() != 1) {
			return CommonEditResourceHandler.getString("Create_Child_UI_"); //$NON-NLS-1$
		}
		return CommonEditResourceHandler.getString("18concat_UI_", (new Object[]{((EReference) localChildrenReferences.iterator().next()).getEType().getName()})); //$NON-NLS-1$ = "Create {0}"
	}

	/**
	 * This returns the default help text for
	 * {@link com.ibm.etools.common.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		Collection localChildrenReferences = getChildrenReferences(object);
		if (localChildrenReferences.size() != 1) {
			return CommonEditResourceHandler.getString("29concat", (new Object[]{refObject.eClass().getName()})); //$NON-NLS-1$ = "Create a new child for the selected {0}."
		}
		return CommonEditResourceHandler.getString("16concat_UI_", (new Object[]{((EReference) localChildrenReferences.iterator().next()).getEType().getName(), refObject.eClass().getName()})); //$NON-NLS-1$ = "Create a child of type {0} for the selected {1}."
	}
}