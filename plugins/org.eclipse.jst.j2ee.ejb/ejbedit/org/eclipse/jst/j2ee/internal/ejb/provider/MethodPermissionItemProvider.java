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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.MethodPermission}object.
 */
public class MethodPermissionItemProvider extends EjbItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public MethodPermissionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: check that this is what you want.
		MethodElement child = EjbFactory.eINSTANCE.createMethodElement();

		// TODO: initialize child here...

		return child;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		return Collections.singleton(EjbPackage.eINSTANCE.getMethodPermission_MethodElements());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EReference getChildReference(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildReference(object, child);
	}


	/**
	 * This returns the image for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		return J2EEPlugin.getPlugin().getImage(refObject.eClass().getName() + "CreateMethodElement"); //$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return EJBProviderLibrariesResourceHandler.getString("Create_MethodElement_UI_"); //$NON-NLS-1$ = "Create MethodElement"
	}

	/**
	 * This returns the help text for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return EJBProviderLibrariesResourceHandler.getString("Create_a_child_of_type_Met1_UI_") + refObject.eClass().getName() + "."; //$NON-NLS-2$ //$NON-NLS-1$ = "Create a child of type MethodElement for the selected "
	}

	/**
	 * This returns MethodPermission.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("methPermission_obj"); //$NON-NLS-1$
	}

	/**
	 * This returns the parent of the MethodPermission.
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

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Description_UI_"), //$NON-NLS-1$ = "Description"
						EJBProviderLibrariesResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$ = "The description property"
						pkg.getMethodPermission_Description()));

			// This is for the assemblyDescriptor feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("AssemblyDescriptor_UI_"), //$NON-NLS-1$ = "AssemblyDescriptor"
						EJBProviderLibrariesResourceHandler.getString("The_assemblyDescriptor_pro_UI_"), //$NON-NLS-1$ = "The assemblyDescriptor property"
						pkg.getMethodPermission_AssemblyDescriptor()));

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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodPermission_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodPermission_description_feature", "_UI_MethodPermission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodPermission_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Unchecked feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUncheckedPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodPermission_unchecked_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodPermission_unchecked_feature", "_UI_MethodPermission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodPermission_Unchecked(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Roles feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRolesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodPermission_roles_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodPermission_roles_feature", "_UI_MethodPermission_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodPermission_Roles(), true));
	}

	public String getText(Object object) {
		if (object instanceof MethodPermission)
			return getText((MethodPermission) object);
		return super.getText(object);
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(MethodPermission.class)) {
			case EjbPackage.METHOD_PERMISSION__DESCRIPTION :
			case EjbPackage.METHOD_PERMISSION__UNCHECKED :
			case EjbPackage.METHOD_PERMISSION__METHOD_ELEMENTS :
			case EjbPackage.METHOD_PERMISSION__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMethodPermission_MethodElements(), EjbFactory.eINSTANCE.createMethodElement()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMethodPermission_MethodElements(), EjbFactory.eINSTANCE.createQueryMethod()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMethodPermission_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMethodPermission_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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

	public String getText(MethodPermission mp) {
		if (mp.isUnchecked())
			return EJBProviderLibrariesResourceHandler.getString("Unchecked_UI_"); //$NON-NLS-1$
		List roles = mp.getRoles();
		StringBuffer buf = new StringBuffer();
		//buf.append(ResourceHandler.getString("MethodPermission__UI_")); //$NON-NLS-1$ =
		// "MethodPermission "
		buf.append("("); //$NON-NLS-1$
		SecurityRole role = null;
		for (int i = 0; i < roles.size(); i++) {
			if (i != 0)
				buf.append(", "); //$NON-NLS-1$
			role = (SecurityRole) roles.get(i);
			buf.append(role.getRoleName());
		}
		buf.append(")"); //$NON-NLS-1$
		return buf.toString();
	}
	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
	 */
	/*
	 * public void notifyChanged(Notifier notifier, int eventType, EObject feature, Object oldValue,
	 * Object newValue, int index) { if (feature == DESCRIPTION_SF || feature == ROLES_SF || feature ==
	 * METHOD_ELEMENTS_SF || feature == UNCHECKED_SF) { fireNotifyChanged(notifier, eventType,
	 * feature, oldValue, newValue, index); return; } super.notifyChanged(notifier, eventType,
	 * feature, oldValue, newValue, index); }
	 */
}
