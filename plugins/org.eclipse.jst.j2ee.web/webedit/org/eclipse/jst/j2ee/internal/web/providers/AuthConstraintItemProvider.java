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
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.webapplication.AuthConstraint}
 * object.
 */
public class AuthConstraintItemProvider extends WebapplicationItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public AuthConstraintItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns authority_constraint.gif.
	 */
	public Object getImage(Object object) {
		return WebPlugin.getDefault().getImage("authority_constraint");//$NON-NLS-1$
	}

	/**
	 * This returns the secConstraint of the AuthConstraint.
	 */
	public Object getParent(Object object) {
		return ((AuthConstraint) object).getSecConstraint();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
			WebapplicationPackage pkg = WebapplicationPackage.eINSTANCE;
			;
			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("Description_UI__UI_"), //$NON-NLS-1$ = "Description"
						WebAppEditResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$ = "The description property"
						pkg.getAuthConstraint_Description()));
			// This is for the roles feature.
			//
			WebToolingItemPropertyDescriptor propertyDescriptor = new WebToolingItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("Roles_UI_"), //$NON-NLS-1$ = "Roles"
						WebAppEditResourceHandler.getString("The_roles_property_UI_"), //$NON-NLS-1$ = "The roles property"
						pkg.getAuthConstraint_Roles(), WebToolingItemPropertyDescriptor.AUTH_CONSTRAINT_ROLES_EDITOR);
			itemPropertyDescriptors.add(propertyDescriptor);
			// This is for the secConstraint feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), WebAppEditResourceHandler.getString("SecConstraint_UI_"), //$NON-NLS-1$ = "SecConstraint"
						WebAppEditResourceHandler.getString("The_secConstraint_property_UI_"), //$NON-NLS-1$ = "The secConstraint property"
						pkg.getAuthConstraint_SecConstraint()));
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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_AuthConstraint_description_feature"), getString( //$NON-NLS-1$
					"_UI_PropertyDescriptor_description", "_UI_AuthConstraint_description_feature", //$NON-NLS-1$ //$NON-NLS-2$
					"_UI_AuthConstraint_type"), WebapplicationPackage.eINSTANCE.getAuthConstraint_Description(), true, //$NON-NLS-1$
					ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Roles feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRolesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_AuthConstraint_roles_feature"), getString( //$NON-NLS-1$
					"_UI_PropertyDescriptor_description", "_UI_AuthConstraint_roles_feature", "_UI_AuthConstraint_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					WebapplicationPackage.eINSTANCE.getAuthConstraint_Roles(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(WebapplicationPackage.eINSTANCE.getAuthConstraint_Descriptions());
		}
		return childrenReferences;
	}

	public String getText(Object object) {
		String description = ((AuthConstraint) object).getDescription();
		if (description == null) {
			List descList = ((AuthConstraint) object).getDescriptions();
			if (descList != null && descList.size() > 0)
				description = ((Description) descList.get(0)).getValue();
		}
		if (description == null)
			description = ""; //$NON-NLS-1$
			//		return WebAppEditResourceHandler.getString("11concat_UI_", (new
			// Object[]{description})); //$NON-NLS-1$ = "AuthConstraint {0}"
		return description;
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(AuthConstraint.class)) {
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTION :
			case WebapplicationPackage.AUTH_CONSTRAINT__ROLES :
			case WebapplicationPackage.AUTH_CONSTRAINT__DESCRIPTIONS : {
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
		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getAuthConstraint_Descriptions(), CommonFactory.eINSTANCE.createDescription()));
		newChildDescriptors.add(createChildParameter(WebapplicationPackage.eINSTANCE.getAuthConstraint_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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
	 * This always returns false. The base class has already implemented
	 * {@link org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren ItemProviderAdapter.getChildren}
	 * to return the empty enumeration, and this to check that enumeration, but we know there can't
	 * be any children, so this is faster.
	 */
	public boolean hasChildren(Object object) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.IEditingDomainItemProvider#createCommand(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Class,
	 *      org.eclipse.emf.edit.command.CommandParameter)
	 */
	public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter) {
		EStructuralFeature sf = commandParameter.getEReference();
		if (sf == null) {
			sf = commandParameter.getEAttribute();
			if (sf != null && !sf.isMany())
				sf = null;
		}
		if (sf != null) {
			EObject owner = commandParameter.getEOwner();
			Collection collection = commandParameter.getCollection();
			if (commandClass == AddCommand.class) {
				return createAddCommand(editingDomain, owner, sf, collection, commandParameter.getIndex());
			} else if (commandClass == RemoveCommand.class) {
				return createRemoveCommand(editingDomain, owner, sf, collection);
			}
		}
		return super.createCommand(object, editingDomain, commandClass, commandParameter);
	}
}