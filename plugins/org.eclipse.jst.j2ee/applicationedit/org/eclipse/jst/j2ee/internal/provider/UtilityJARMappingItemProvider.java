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
package org.eclipse.jst.j2ee.internal.provider;



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
import org.eclipse.jst.j2ee.internal.application.provider.ApplicationProvidersResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapPackage;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;



/**
 * This is the item provider adpater for a
 * {@link org.eclipse.jst.j2ee.internal.internal.internal.earcreation.modulemap.UtilityJARMapping}
 * object.
 */
public class UtilityJARMappingItemProvider extends ModulemapItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public UtilityJARMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			ModulemapPackage pkg = ModulemapPackage.eINSTANCE;
			;

			// This is for the projectName feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("Project_Name_UI_"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_project_name_property_of_the_utility_jar_mapping_UI_"), //$NON-NLS-1$
						pkg.getUtilityJARMapping_ProjectName(), false, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

			// This is for the uri feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("Uri_UI_"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_uri_property_of_the_utility_jar_mapping_UI_"), //$NON-NLS-1$
						pkg.getUtilityJARMapping_Uri(), true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Project Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addProjectNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_UtilityJARMapping_projectName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_UtilityJARMapping_projectName_feature", "_UI_UtilityJARMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					ModulemapPackage.eINSTANCE.getUtilityJARMapping_ProjectName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Uri feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addUriPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_UtilityJARMapping_uri_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_UtilityJARMapping_uri_feature", "_UI_UtilityJARMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					ModulemapPackage.eINSTANCE.getUtilityJARMapping_Uri(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}


	/**
	 * This returns the parent of the UtilityJARMapping.
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns UtilityJARMapping.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("icons/full/obj16/prjutiljar_obj"); //$NON-NLS-1$
	}

	//	/**
	//	 * @param object
	//	 * @return
	//	 */
	//	private boolean isEJBClientJAR(Object object) {
	//		UtilityJARMapping map = (UtilityJARMapping) object;
	//		IProject project = ProjectUtilities.getProject(map);
	//		if (project == null)
	//			return false;
	//
	//		EARNatureRuntime runtime = EARNatureRuntime.getRuntime(project);
	//		if (runtime == null)
	//			return false;
	//
	//		EAREditModel model = null;
	//		try {
	//			model = runtime.getEarEditModelForRead(this);
	//			return model.isEjbClientJarMapping(map);
	//		} finally {
	//			if (model != null)
	//				model.releaseAccess(this);
	//		}
	//	}

	public String getText(Object object) {
		UtilityJARMapping map = ((UtilityJARMapping) object);
		return map == null ? "<??>" : map.getUri(); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(UtilityJARMapping.class)) {
			case ModulemapPackage.UTILITY_JAR_MAPPING__PROJECT_NAME :
			case ModulemapPackage.UTILITY_JAR_MAPPING__URI : {
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
		return ApplicationProvidersResourceHandler.RESOURCE_LOCATOR;
	}
	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
	 */
	/*
	 * public void notifyChanged(Notifier notifier, int eventType, EObject feature, Object oldValue,
	 * Object newValue, int index) { ModulemapPackage pkg = ModulemapPackage.eINSTANCE;; if (
	 * feature == pkg.getUtilityJARMapping_ProjectName() || feature ==
	 * pkg.getUtilityJARMapping_Uri() ) { fireNotifyChanged(notifier, eventType, feature, oldValue,
	 * newValue, index); return; } super.notifyChanged(notifier, eventType, feature, oldValue,
	 * newValue, index); }
	 */
}