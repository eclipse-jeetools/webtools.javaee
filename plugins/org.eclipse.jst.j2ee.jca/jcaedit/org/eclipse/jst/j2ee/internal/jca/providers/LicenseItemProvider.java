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
package org.eclipse.jst.j2ee.internal.jca.providers;



import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.jca.License;
import org.eclipse.jst.j2ee.jca.internal.plugin.JCAResourceHandler;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;

/**
 * This is the item provider adpater for a License object.
 */
public class LicenseItemProvider extends JcaItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * 
	 * @param AdapterFactory
	 *            adapterFactory - The adapt class.
	 */
	public LicenseItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}// LicenseItemProvider

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addDescriptionPropertyDescriptor(object);
			addRequiredPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_License_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_License_description_feature", "_UI_License_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getLicense_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Required feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRequiredPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_License_required_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_License_required_feature", "_UI_License_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getLicense_Required(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	/**
	 * This specifies how to implement {@link #getChildren}and
	 * {@link org.eclipse.emf.edit.command.AddCommand}and
	 * {@link org.eclipse.emf.edit.command.RemoveCommand}support in {@link #createCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			childrenReferences.add(JcaPackage.eINSTANCE.getLicense_Descriptions());
		}
		return childrenReferences;
	}


	/**
	 * This returns the property descriptors for the adapted class.
	 * 
	 * @param Object
	 *            object - The adapt class.
	 * @return List
	 */
	/*
	 * public List getPropertyDescriptors(Object object) { if (itemPropertyDescriptors == null) {
	 * super.getPropertyDescriptors(object);
	 * 
	 * License f_license = ((License)object); JcaPackage pkg = JcaPackage.eINSTANCE;; // This is for
	 * the description feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Description", "The
	 * description property of the license", pkg.getLicense_Description(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the required feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Required", "The
	 * required property of the license", pkg.getLicense_Required(), true,
	 * ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	 * 
	 * }// if
	 * 
	 * return itemPropertyDescriptors; }// getPropertyDescriptors
	 */
	/**
	 * This returns the parent of the License.
	 * 
	 * @param Object
	 *            object - The adapt class.
	 * @return Object
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}// getParent

	/**
	 * This returns License.gif.
	 * 
	 * @param Object
	 *            object - The adapt class
	 * @return Object
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("full/obj16/license_obj"); //$NON-NLS-1$
	}// getImage

	/**
	 * Get the display name.
	 * 
	 * @param Object
	 *            object - The object to get the name for.
	 * @return String
	 */
	public String getText(Object object) {
		License license = ((License) object);
		if (license.getDescription() == null)
			return JCAResourceHandler.getString("LicenseItemProvider_UI_0"); //$NON-NLS-1$
		return JCAResourceHandler.getString("LicenseItemProvider_UI_0") + ":" + license.getDescription(); //$NON-NLS-1$ //$NON-NLS-2$
	}// getText

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(License.class)) {
			case JcaPackage.LICENSE__DESCRIPTION :
			case JcaPackage.LICENSE__REQUIRED :
			case JcaPackage.LICENSE__DESCRIPTIONS : {
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
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getLicense_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getLicense_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return J2EEPlugin.getDefault();
	}

	/**
	 * This handles notification.
	 * 
	 * @param Notifier
	 *            notifier - Notifies of change.
	 * @param int
	 *            eventType - The event type.
	 * @param EObject
	 *            feature - The reference object.
	 * @param Object
	 *            oldValue - The old object reference.
	 * @param Object
	 *            newValue - The new object reference.
	 * @param int
	 *            index - The current of the object.
	 */
	public void notifyChanged(Notifier notifier, int eventType, EObject feature, Object oldValue, Object newValue, int index) {
		JcaPackage pkg = JcaPackage.eINSTANCE;
		Notification msg = new ENotificationImpl((InternalEObject) notifier, eventType, feature.eContainmentFeature(), oldValue, newValue, index);
		if (feature == pkg.getLicense_Description() || feature == pkg.getLicense_Required()) {
			fireNotifyChanged(msg);
			return;
		}// if
		super.notifyChanged(msg);
	}// notifyChanged

}// LicenseItemProvider
