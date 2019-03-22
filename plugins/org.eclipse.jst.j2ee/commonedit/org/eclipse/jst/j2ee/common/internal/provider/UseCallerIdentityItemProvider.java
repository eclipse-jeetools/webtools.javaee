/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.provider;



import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.UseCallerIdentity;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a {@link com.ibm.etools.common.UseCallerIdentity}object.
 */
public class UseCallerIdentityItemProvider extends SecurityIdentityItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public UseCallerIdentityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
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

	/**
	 * This returns the parent of the UseCallerIdentity.
	 */
	@Override
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns UseCallerIdentity.gif.
	 */
	@Override
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("security_identity_obj");//$NON-NLS-1$
	}

	@Override
	public String getText(Object object) {
		UseCallerIdentity useCallerIdentity = ((UseCallerIdentity) object);
		return "UseCallerIdentity " + useCallerIdentity.getDescription(); //$NON-NLS-1$
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
	 *  
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return J2EEPlugin.getDefault();
	}
}