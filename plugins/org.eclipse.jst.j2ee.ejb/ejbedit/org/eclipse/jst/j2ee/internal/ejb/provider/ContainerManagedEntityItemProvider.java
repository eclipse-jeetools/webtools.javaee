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
package org.eclipse.jst.j2ee.internal.ejb.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.ModelledPersistentAttributeFilter;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.ContainerManagedEntity}object.
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ContainerManagedEntityItemProvider extends EntityItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ContainerManagedEntityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addVersionPropertyDescriptor(object);
			addAbstractSchemaNamePropertyDescriptor(object);
			addKeyAttributesPropertyDescriptor(object);
			//addCMPAttributePropertyDescriptor(object);
			addPrimKeyFieldPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Version feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_EJBJar_version_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_EJBJar_version_feature_desc"), //$NON-NLS-1$
					EjbPackage.eINSTANCE.getContainerManagedEntity_Version(), false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Abstract Schema Name feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected void addAbstractSchemaNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_abstractSchemaName_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_abstractSchemaName_feature_desc"), //$NON-NLS-1$
					EjbPackage.eINSTANCE.getContainerManagedEntity_AbstractSchemaName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Key Attributes feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	protected void addKeyAttributesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_keyAttributes_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_keyAttributes_feature_desc"), //$NON-NLS-1$
					EjbPackage.eINSTANCE.getContainerManagedEntity_KeyAttributes(), false));
	}

	/**
	 * This adds a property descriptor for the CMP Attribute feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	protected void addCMPAttributePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_CMPAttribute_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_CMPAttribute_feature_desc"), //$NON-NLS-1$
					EjbPackage.eINSTANCE.getContainerManagedEntity_CMPAttribute(), true));
	}

	/**
	 * This adds a property descriptor for the Prim Key Field feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	protected void addPrimKeyFieldPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_primKeyField_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_ContainerManagedEntity_primKeyField_feature_desc"), //$NON-NLS-1$
					EjbPackage.eINSTANCE.getContainerManagedEntity_PrimKeyField(), false));
	}

	/**
	 * This specifies how to implement {@link #getChildren}and
	 * {@link org.eclipse.emf.edit.command.AddCommand}and
	 * {@link org.eclipse.emf.edit.command.RemoveCommand}support in {@link #createCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *  
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			//childrenReferences.add(EjbPackage.eINSTANCE.getContainerManagedEntity_PersistentAttributes());
			childrenReferences.add(EjbPackage.eINSTANCE.getContainerManagedEntity_Queries());
		}
		return childrenReferences;
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
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> Don't remove this
	 * method or it will be regenerated. <!-- end-user-doc -->
	 *  
	 */
	public String getText(Object object) {
		return super.getText(object);
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 *  
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ContainerManagedEntity.class)) {
			case EjbPackage.CONTAINER_MANAGED_ENTITY__VERSION :
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ABSTRACT_SCHEMA_NAME :
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES :
			case EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES :
			case EjbPackage.CONTAINER_MANAGED_ENTITY__CMP_ATTRIBUTE :
			case EjbPackage.CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES :
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD :
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIMARY_KEY : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getContainerManagedEntity_PersistentAttributes(), EjbFactory.eINSTANCE.createCMPAttribute()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getContainerManagedEntity_PersistentAttributes(), EjbFactory.eINSTANCE.createCMRField()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getContainerManagedEntity_Queries(), EjbFactory.eINSTANCE.createQuery()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getCreateChildText(Object owner, Object feature, Object child, Collection selection) {
		boolean qualify = feature == CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbRefs() || feature == CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbLocalRefs();
		return getString(qualify ? "_UI_CreateChild_text2" : "_UI_CreateChild_text", //$NON-NLS-1$ //$NON-NLS-2$
					new Object[]{getTypeText(child), getFeatureText(feature), getTypeText(owner)});
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
	 * This returns ContainerManagedEntity.gif.
	 */
	public Object getImage(Object object) {
		//Cmp 20 should have their own Itemprovider
		ContainerManagedEntity cmp = null;
		if (object instanceof ContainerManagedEntity) {
			cmp = (ContainerManagedEntity) object;
		}
		switch (cmp.getVersionID()) {

			case J2EEVersionConstants.EJB_1_0_ID :
			case J2EEVersionConstants.EJB_1_1_ID :
				return J2EEPlugin.getPlugin().getImage("11_cmpbean_obj"); //$NON-NLS-1$
			case J2EEVersionConstants.EJB_2_0_ID :
				return J2EEPlugin.getPlugin().getImage("20_cmpbean_obj"); //$NON-NLS-1$
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				return J2EEPlugin.getPlugin().getImage("21_cmpbean_obj"); //$NON-NLS-1$
		}
	}

	public boolean hasChildren(Object arg0) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection result = super.getChildren(object);
		result.addAll(ModelledPersistentAttributeFilter.singleton().filter((ContainerManagedEntity) object));
		return result;
	}
}