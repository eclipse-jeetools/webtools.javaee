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
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.internal.common.CommonEditResourceHandler;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;



/**
 * This is the item provider adpater for a {@link com.ibm.etools.common.EjbRef}object.
 */
public class EjbRefItemProvider extends CommonItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public EjbRefItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns ejb_reference.gif.
	 */
	public Object getImage(Object object) {
		String key = ((EjbRef) object).isLocal() ? "ejb_local_ref_obj" : "ejb_reference"; //$NON-NLS-1$ //$NON-NLS-2$
		return J2EEPlugin.getPlugin().getImage(key); //$NON-NLS-1$
	}

	/**
	 * This returns the parent of the EjbRef.
	 */
	public Object getParent(Object object) {
		return object == null ? null : ((EjbRef) object).eContainer();
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			CommonPackage pkg = CommonPackage.eINSTANCE;

			// This is for the name feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Name_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_name_property_UI_"), //$NON-NLS-1$
						pkg.getEjbRef_Name(), true));

			// This is for the type feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Type_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_type_property_UI_"), //$NON-NLS-1$
						pkg.getEjbRef_Type(), false));

			// This is for the home feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Home_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_home_property_UI_"), //$NON-NLS-1$
						pkg.getEjbRef_Home(), false));

			// This is for the remote feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Remote_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_remote_property_UI_"), //$NON-NLS-1$
						pkg.getEjbRef_Remote(), false));

			// This is for the link feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Link_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_link_property_UI_"), //$NON-NLS-1$
						pkg.getEjbRef_Link(), false));

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), CommonEditResourceHandler.getString("Description_UI_"), //$NON-NLS-1$
						CommonEditResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$
						pkg.getEjbRef_Description(), true));

			// This is for the client feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
			 * ResourceHandler.getString("Client_UI_"), //$NON-NLS-1$
			 * ResourceHandler.getString("The_client_property_UI_"), //$NON-NLS-1$
			 * pkg.getEjbRef_Client()));
			 */

			// This is for the webApp feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
			 * ResourceHandler.getString("WebApp_UI_"), //$NON-NLS-1$
			 * ResourceHandler.getString("The_webApp_property_UI_"), //$NON-NLS-1$
			 * pkg.getEjbRef_WebApp()));
			 */

			// This is for the ejb feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), // next four
			 * lines for 175333 //ResourceHandler.getString("Ejb_UI_"), //$NON-NLS-1$
			 * "EJB",//$NON-NLS-1$ //ResourceHandler.getString("The_ejb_property_UI_"),
			 * //$NON-NLS-1$ ResourceHandler.getString("The_EJB_property_UI_"), //$NON-NLS-1$ = "The
			 * EJB property" pkg.getEjbRef_Ejb()));
			 */

		}
		return itemPropertyDescriptors;
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

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getEjbRef_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getEjbRef_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EjbRef_name_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EjbRef_name_feature", "_UI_EjbRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getEjbRef_Name(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EjbRef_type_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EjbRef_type_feature", "_UI_EjbRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getEjbRef_Type(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Home feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addHomePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EjbRef_home_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EjbRef_home_feature", "_UI_EjbRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getEjbRef_Home(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Remote feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRemotePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EjbRef_remote_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EjbRef_remote_feature", "_UI_EjbRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getEjbRef_Remote(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Link feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLinkPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EjbRef_link_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EjbRef_link_feature", "_UI_EjbRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getEjbRef_Link(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EjbRef_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EjbRef_description_feature", "_UI_EjbRef_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					CommonPackage.eINSTANCE.getEjbRef_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(CommonPackage.eINSTANCE.getEjbRef_Descriptions());
		}
		return childrenReferences;
	}


	public String getText(Object object) {
		if (object instanceof EJBLocalRef)
			return "EjbLocalRef " + ((EJBLocalRef) object).getName(); //$NON-NLS-1$
		return "EjbRef " + ((EjbRef) object).getName(); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(EjbRef.class)) {
			case CommonPackage.EJB_REF__NAME :
			case CommonPackage.EJB_REF__TYPE :
			case CommonPackage.EJB_REF__HOME :
			case CommonPackage.EJB_REF__REMOTE :
			case CommonPackage.EJB_REF__LINK :
			case CommonPackage.EJB_REF__DESCRIPTION :
			case CommonPackage.EJB_REF__DESCRIPTIONS : {
				fireNotifyChanged(notification);
				return;
			}
		}
		super.notifyChanged(notification);
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