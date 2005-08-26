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
package org.eclipse.jst.j2ee.internal.webservice.provider;


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
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping;


/**
 * This is the item provider adpater for a
 * {@link org.eclipse.jst.j2ee.internal.internal.webservice.jaxrpcmap.VariableMapping}object. <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class VariableMappingItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public VariableMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addIdPropertyDescriptor(object);
			addJavaVariableNamePropertyDescriptor(object);
			addXmlElementNamePropertyDescriptor(object);
			addDataMemberPropertyDescriptor(object);
			addXmlAttributeNamePropertyDescriptor(object);
			addXmlWildcardPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Id feature. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected void addIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_VariableMapping_id_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_VariableMapping_id_feature", "_UI_VariableMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getVariableMapping_Id(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Java Variable Name feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addJavaVariableNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_VariableMapping_javaVariableName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_VariableMapping_javaVariableName_feature", "_UI_VariableMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getVariableMapping_JavaVariableName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Xml Element Name feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addXmlElementNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_VariableMapping_xmlElementName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_VariableMapping_xmlElementName_feature", "_UI_VariableMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getVariableMapping_XmlElementName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Data Member feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDataMemberPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_VariableMapping_dataMember_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_VariableMapping_dataMember_feature", "_UI_VariableMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getVariableMapping_DataMember(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Xml Attribute Name feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addXmlAttributeNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_VariableMapping_xmlAttributeName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_VariableMapping_xmlAttributeName_feature", "_UI_VariableMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getVariableMapping_XmlAttributeName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Xml Wildcard feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addXmlWildcardPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_VariableMapping_xmlWildcard_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_VariableMapping_xmlWildcard_feature", "_UI_VariableMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					JaxrpcmapPackage.eINSTANCE.getVariableMapping_XmlWildcard(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}


	/**
	 * This returns VariableMapping.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/VariableMapping"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((VariableMapping) object).getJavaVariableName();
		return label == null || label.length() == 0 ? getString("_UI_VariableMapping_type") : //$NON-NLS-1$
					getString("_UI_VariableMapping_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(VariableMapping.class)) {
			case JaxrpcmapPackage.VARIABLE_MAPPING__ID :
			case JaxrpcmapPackage.VARIABLE_MAPPING__JAVA_VARIABLE_NAME :
			case JaxrpcmapPackage.VARIABLE_MAPPING__XML_ELEMENT_NAME :
			case JaxrpcmapPackage.VARIABLE_MAPPING__DATA_MEMBER :
			case JaxrpcmapPackage.VARIABLE_MAPPING__XML_ATTRIBUTE_NAME :
			case JaxrpcmapPackage.VARIABLE_MAPPING__XML_WILDCARD : {
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
	}

	/**
	 * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ResourceLocator getResourceLocator() {
		return Webservicej2eeEditPlugin.INSTANCE;
	}
}