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



import java.util.ArrayList;
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
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.Entity}object.
 */
public class EntityItemProvider extends EnterpriseBeanItemProvider implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public EntityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns Entity.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("bmpEntity_obj");//$NON-NLS-1$
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
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Entity.class)) {
			case EjbPackage.ENTITY__REENTRANT : {
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
	 * This returns the parent of the Entity.
	 */
	public Object getParent(Object object) {
		return super.getParent(object);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addReentrantPropertyDescriptor(object);
			//addPrimaryKeyPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Reentrant feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	protected void addReentrantPropertyDescriptor(Object object) {
		EjbPackage pkg = EjbPackage.eINSTANCE;
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("IsReentrant_UI_"), //$NON-NLS-1$ = "IsReentrant"
					EJBProviderLibrariesResourceHandler.getString("The_isReentrant_property_UI_"), //$NON-NLS-1$ = "The isReentrant property"
					pkg.getEntity_Reentrant(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));

	}

	/**
	 * This adds a property descriptor for the Primary Key feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	protected void addPrimaryKeyPropertyDescriptor(Object object) {
		EjbPackage pkg = EjbPackage.eINSTANCE;
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("PrimaryKey_UI_"), //$NON-NLS-1$ = "PrimaryKey"
					EJBProviderLibrariesResourceHandler.getString("The_primaryKey_property_UI_"), //$NON-NLS-1$ = "The primaryKey property"
					pkg.getEntity_PrimaryKey(), false));
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenReferences(java.lang.Object)
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			childrenReferences = new ArrayList();
			childrenReferences.addAll(super.getChildrenReferences(object));
//			childrenReferences.add(EjbPackage.eINSTANCE.getEntity_Reentrant());
		}
		return childrenReferences;
	}
}