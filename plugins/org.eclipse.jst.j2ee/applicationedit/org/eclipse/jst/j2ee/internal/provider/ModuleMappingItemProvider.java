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
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;




/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.internal.earcreation.modulemap.ModuleMapping}
 * object.
 */
public class ModuleMappingItemProvider extends ModulemapItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public ModuleMappingItemProvider(AdapterFactory adapterFactory) {
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
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("Project_Name_1"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_project_name_property_of_the_module_mapping_UI_"), //$NON-NLS-1$
						pkg.getModuleMapping_ProjectName(), true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

			// This is for the module feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), ApplicationProvidersResourceHandler.getString("Module_UI_"), //$NON-NLS-1$
						ApplicationProvidersResourceHandler.getString("The_module_of_the_module_mapping_UI_"), //$NON-NLS-1$
						pkg.getModuleMapping_Module(), false));

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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ModuleMapping_projectName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ModuleMapping_projectName_feature", "_UI_ModuleMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					ModulemapPackage.eINSTANCE.getModuleMapping_ProjectName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Module feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addModulePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_ModuleMapping_module_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_ModuleMapping_module_feature", "_UI_ModuleMapping_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					ModulemapPackage.eINSTANCE.getModuleMapping_Module(), true));
	}


	/**
	 * This returns the parent of the ModuleMapping.
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns ModuleMapping.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("ModuleMapping"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		ModuleMapping moduleMapping = ((ModuleMapping) object);
		return ApplicationProvidersResourceHandler.getString("ModuleMapping_UI_") + moduleMapping.getProjectName(); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ModuleMapping.class)) {
			case ModulemapPackage.MODULE_MAPPING__PROJECT_NAME :
			case ModulemapPackage.MODULE_MAPPING__MODULE : {
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
	 * feature == pkg.getModuleMapping_ProjectName() || feature == pkg.getModuleMapping_Module() ) {
	 * fireNotifyChanged(notifier, eventType, feature, oldValue, newValue, index); return; }
	 * super.notifyChanged(notifier, eventType, feature, oldValue, newValue, index); }
	 */
}