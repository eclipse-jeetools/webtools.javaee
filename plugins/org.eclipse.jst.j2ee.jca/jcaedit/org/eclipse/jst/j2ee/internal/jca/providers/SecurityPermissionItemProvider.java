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
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.jca.SecurityPermission;
import org.eclipse.jst.j2ee.jca.internal.plugin.JCAResourceHandler;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


/**
 * This is the item provider adpater for a SecurityPermission object.
 */
public class SecurityPermissionItemProvider extends JcaItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * 
	 * @param AdapterFactory
	 *            adapterFactory - The adapt factory.
	 */
	public SecurityPermissionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}// SecurityPermissionItemProvider

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
			addSpecificationPropertyDescriptor(object);
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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_SecurityPermission_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_SecurityPermission_description_feature", "_UI_SecurityPermission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getSecurityPermission_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Specification feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSpecificationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_SecurityPermission_specification_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_SecurityPermission_specification_feature", "_UI_SecurityPermission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getSecurityPermission_Specification(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(JcaPackage.eINSTANCE.getSecurityPermission_Descriptions());
		}
		return childrenReferences;
	}


	/**
	 * This returns the property descriptors for the adapted class.
	 * 
	 * @param Object
	 *            object - The adapted class.
	 * @return List
	 */
	/*
	 * public List getPropertyDescriptors(Object object) { if (itemPropertyDescriptors == null) {
	 * super.getPropertyDescriptors(object);
	 * 
	 * SecurityPermission f_securityPermission = ((SecurityPermission)object); JcaPackage pkg =
	 * JcaPackage.eINSTANCE;; // This is for the description feature. // itemPropertyDescriptors.add
	 * (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Description", "The
	 * description property of the security permission", pkg.getSecurityPermission_Description(),
	 * true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the specification feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Specification", "The
	 * specification property of the security permission",
	 * pkg.getSecurityPermission_Specification(), true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
	 * 
	 * }// if return itemPropertyDescriptors; }// getPropertyDescriptors
	 */
	/**
	 * This returns the parent of the SecurityPermission.
	 * 
	 * @param Object
	 *            object - The adapt class.
	 * @return Object
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}// getParent

	/**
	 * This returns SecurityPermission.gif.
	 * 
	 * @param Object
	 *            object - The adapt class.
	 * @return object
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("full/obj16/security_permission_obj"); //$NON-NLS-1$
	}// getImage


	/**
	 * Get the display name.
	 * 
	 * @param Object
	 *            object - The object to get the name for.
	 * @return String
	 */
	public String getText(Object object) {
		SecurityPermission securityPermission = ((SecurityPermission) object);
		if (securityPermission.getDescription() == null)
			return JCAResourceHandler.SecurityPermissionItemProvider_UI_0; 
		return JCAResourceHandler.SecurityPermissionItemProvider_UI_0 + ": " + securityPermission.getDescription();  //$NON-NLS-1$
	}// getText

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(SecurityPermission.class)) {
			case JcaPackage.SECURITY_PERMISSION__DESCRIPTION :
			case JcaPackage.SECURITY_PERMISSION__SPECIFICATION :
			case JcaPackage.SECURITY_PERMISSION__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getSecurityPermission_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getSecurityPermission_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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
		if (feature == pkg.getSecurityPermission_Description() || feature == pkg.getSecurityPermission_Specification()) {
			fireNotifyChanged(msg);
			return;
		}// if
		super.notifyChanged(msg);
	}// notifyChanged

}// SecurityPermissionItemProvider
