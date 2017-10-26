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
package org.eclipse.jst.j2ee.internal.application.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;



/**
 * This is the item provider adpater for a
 * { @link org.eclipse.jst.j2ee.internal.internal.application.JavaClientModule}object.
 */
public class JavaClientModuleItemProvider extends ModuleItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public JavaClientModuleItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns JavaClientModule.gif.
	 */
	@Override
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("module_clientapp_obj"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((JavaClientModule) object).getUri();
		return label == null || label.length() == 0 ? getString("_UI_JavaClientModule_type") : //$NON-NLS-1$
					getString("_UI_JavaClientModule_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
	}

	/**
	 * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ApplicationProvidersResourceHandler.RESOURCE_LOCATOR;
	}

	/**
	 * This returns the parent of the JavaClientModule.
	 */
	@Override
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	@Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
		}
		return itemPropertyDescriptors;
	}

}
