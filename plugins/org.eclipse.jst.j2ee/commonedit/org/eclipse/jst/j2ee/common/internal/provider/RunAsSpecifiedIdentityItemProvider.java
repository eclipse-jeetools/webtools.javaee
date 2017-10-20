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
package org.eclipse.jst.j2ee.common.internal.provider;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.internal.common.CommonEditResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

/**
 * This is the item provider adpater for a {@link com.ibm.etools.common.RunAsSpecifiedIdentity}
 * object.
 */
public class RunAsSpecifiedIdentityItemProvider extends SecurityIdentityItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public RunAsSpecifiedIdentityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	@Override
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			CommonPackage pkg = CommonPackage.eINSTANCE;

			// This is for the identity feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), "Identity", //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_identity_of_the_run_as_specified_identity_2"), //$NON-NLS-1$
						pkg.getRunAsSpecifiedIdentity_Identity(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * { @link RemoveCommand}support in {@link #createCommand}.
	 */
	@Override
	public Collection getChildrenReferences(Object object) {
	    return new ArrayList<EReference>();
//		if (childrenReferences == null) {
//			super.getChildrenReferences(object);
//			childrenReferences.add(CommonPackage.eINSTANCE.getRunAsSpecifiedIdentity_Identity());
//		}
//		return childrenReferences;
	}

	/**
	 * This returns the parent of the RunAsSpecifiedIdentity.
	 */
	@Override
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns RunAsSpecifiedIdentity.gif.
	 */
	@Override
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("security_identity_obj");//$NON-NLS-1$
	}

	@Override
	public String getText(Object object) {
		RunAsSpecifiedIdentity runAsSpecifiedIdentity = ((RunAsSpecifiedIdentity) object);
		return runAsSpecifiedIdentity.getIdentity().getRoleName();
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(RunAsSpecifiedIdentity.class)) {
			case CommonPackage.RUN_AS_SPECIFIED_IDENTITY__IDENTITY : {
				fireNotifyChanged(notification);
				return;
			}
		}
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

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getRunAsSpecifiedIdentity_Identity(), CommonFactory.eINSTANCE.createIdentity()));
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
