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
package org.eclipse.jst.j2ee.internal.ejb.provider;



import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;



/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.CMRField}object.
 */
public class CMRFieldItemProvider extends CMPAttributeItemProvider implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public CMRFieldItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns CMRField.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("CMRField"); //$NON-NLS-1$
	}

	/**
	 * This returns the parent of the CMRField.
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			EjbPackage pkg = EjbPackage.eINSTANCE;

			// This is for the role feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Role_2"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_role_of_the_cmr_field_3"), //$NON-NLS-1$
						pkg.getCMRField_Role(), false));

			// This is for the collectionType feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Collection_Type_4"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_collection_type_of_the_cmr_field_5"), //$NON-NLS-1$
						pkg.getCMRField_CollectionType(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Collection Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addCollectionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_CMRField_collectionType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_CMRField_collectionType_feature", "_UI_CMRField_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getCMRField_CollectionType(), true));
	}


	public String getText(Object object) {
		CMRField cmrField = ((CMRField) object);
		return EJBProviderLibrariesResourceHandler.getString("CMRField__6") + cmrField.getName(); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
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
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
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
}
