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



import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.internal.common.CommonEditResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


/**
 * This is the item provider adpater for a {@link com.ibm.etools.common.ResourceRef}object.
 */
public class ResourceEnvRefItemProvider extends CommonItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public ResourceEnvRefItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns resource_reference.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("res_env_ref_obj"); //$NON-NLS-1$
	}

	/**
	 * This returns the parent of the ResourceRef.
	 */
	public Object getParent(Object object) {
		return object == null ? null : ((ResourceEnvRef) object).eContainer();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			CommonPackage pkg = CommonPackage.eINSTANCE;

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Description_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$
						pkg.getResourceEnvRef_Description(), true));

			// This is for the name feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Name_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_name_property_UI_"), //$NON-NLS-1$
						pkg.getResourceEnvRef_Name(), true));

			// This is for the type feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Type_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_type_property_UI_"), //$NON-NLS-1$
						pkg.getResourceEnvRef_Type(), false));

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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceEnvRef_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceEnvRef_description_feature", "_UI_ResourceEnvRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getResourceEnvRef_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceEnvRef_name_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceEnvRef_name_feature", "_UI_ResourceEnvRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getResourceEnvRef_Name(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ResourceEnvRef_type_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ResourceEnvRef_type_feature", "_UI_ResourceEnvRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getResourceEnvRef_Type(), true));
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
			childrenReferences.add(CommonPackage.eINSTANCE.getResourceEnvRef_Descriptions());
		}
		return childrenReferences;
	}


	public String getText(Object object) {
		return "ResourceEnvRef " + ((ResourceEnvRef) object).getName(); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ResourceEnvRef.class)) {
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTION :
			case CommonPackage.RESOURCE_ENV_REF__NAME :
			case CommonPackage.RESOURCE_ENV_REF__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getResourceEnvRef_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getResourceEnvRef_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	public ResourceLocator getResourceLocator() {
		return J2EEPlugin.getDefault();
	}

	/**
	 * This always returns false. The base class has already implemented
	 * {@link ItemProviderAdapter#getChildren ItemProviderAdapter.getChildren}to return the empty
	 * enumeration, and this to check that enumeration, but we know there can't be any children, so
	 * this is faster.
	 */
	public boolean hasChildren(Object object) {
		return false;
	}
}
