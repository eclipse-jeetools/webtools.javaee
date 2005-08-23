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
import org.eclipse.jst.j2ee.common.internal.provider.CompatibilityDescriptionGroupItemProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.JcaPackage;


/**
 * This is the item provider adpater for a Conenctor projects
 */
public class ConnectorItemProvider extends CompatibilityDescriptionGroupItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier.
	 * 
	 * @param AdapterFactory
	 *            adapterFactory - An adapter factory.
	 */
	public ConnectorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}// ConnectorItemProvider

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addVendorNamePropertyDescriptor(object);
			addSpecVersionPropertyDescriptor(object);
			addEisTypePropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Vendor Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addVendorNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Connector_vendorName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Connector_vendorName_feature", "_UI_Connector_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnector_VendorName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Spec Version feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSpecVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Connector_specVersion_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Connector_specVersion_feature", "_UI_Connector_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnector_SpecVersion(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Eis Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addEisTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Connector_eisType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Connector_eisType_feature", "_UI_Connector_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnector_EisType(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Version feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Connector_version_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Connector_version_feature", "_UI_Connector_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JcaPackage.eINSTANCE.getConnector_Version(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * 
	 * @param Object
	 *            object - The current property
	 * @return List
	 */
	/*
	 * public List getPropertyDescriptors(Object object) {
	 * 
	 * if(itemPropertyDescriptors == null) { super.getPropertyDescriptors(object);
	 * 
	 * Connector f_connector = ((Connector)object); JcaPackage pkg = JcaPackage.eINSTANCE;; // This
	 * is for the displayName feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Display Name", "The
	 * display name property of the connector", pkg.getConnector_DisplayName(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the description feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Description", "The
	 * description property of the connector", pkg.getConnector_Description(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the smallIcon feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Small Icon", "The
	 * small icon property of the connector", pkg.getConnector_SmallIcon(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the largeIcon feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Large Icon", "The
	 * large icon property of the connector", pkg.getConnector_LargeIcon(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the vendorName feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Vendor Name", "The
	 * vendor name property of the connector", pkg.getConnector_VendorName(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the specVersion feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Spec Version", "The
	 * spec version property of the connector", pkg.getConnector_SpecVersion(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the eisType feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Eis Type", "The eis
	 * type property of the connector", pkg.getConnector_EisType(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the version feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Version", "The version
	 * property of the connector", pkg.getConnector_Version(), true,
	 * ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); // This is for the license feature. //
	 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "License", "The license
	 * of the connector", pkg.getConnector_License(), false)); // This is for the resourceAdapter
	 * feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
	 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Resource Adapter",
	 * "The resource adapter of the connector", pkg.getConnector_ResourceAdapter(), false)); }
	 * return itemPropertyDescriptors; }// getPropertyDescriptors
	 */
	/**
	 * Returns a collection of children references.
	 * 
	 * @param Object
	 *            object - Object to get children references of.
	 * @return Colelction
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			childrenReferences.add(JcaPackage.eINSTANCE.getConnector_License());
			childrenReferences.add(JcaPackage.eINSTANCE.getConnector_ResourceAdapter());
		}// if
		return childrenReferences;
	}// getChildrenReferences

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
	 * This returns the parent of the Connector.
	 * 
	 * @param Object
	 *            object - Object to return the parent of.
	 * @return Object
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}// getParent

	/**
	 * This returns Connector.gif.
	 * 
	 * @param Object -
	 *            The object to get the image for.
	 * @return Object
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("full/obj16/connection_obj"); //$NON-NLS-1$
	}// getImage

	/**
	 * Get the display name.
	 * 
	 * @param Object
	 *            object - The object to get the name for.
	 * @return String
	 */
	public String getText(Object object) {
		Connector connector = ((Connector) object);
		return "Connector " + connector.getDisplayName(); //$NON-NLS-1$
	}// getText

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Connector.class)) {
			case JcaPackage.CONNECTOR__VENDOR_NAME :
			case JcaPackage.CONNECTOR__SPEC_VERSION :
			case JcaPackage.CONNECTOR__EIS_TYPE :
			case JcaPackage.CONNECTOR__VERSION :
			case JcaPackage.CONNECTOR__LICENSE :
			case JcaPackage.CONNECTOR__RESOURCE_ADAPTER : {
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

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getConnector_License(), JcaFactory.eINSTANCE.createLicense()));

		newChildDescriptors.add(createChildParameter(JcaPackage.eINSTANCE.getConnector_ResourceAdapter(), JcaFactory.eINSTANCE.createResourceAdapter()));
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

}// ConenctorItemProvider
