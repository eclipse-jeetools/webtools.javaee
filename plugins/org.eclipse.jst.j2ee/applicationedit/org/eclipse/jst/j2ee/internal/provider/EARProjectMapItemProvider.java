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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jst.j2ee.internal.application.provider.ApplicationProvidersResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.EARProjectMap;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModulemapPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;




/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.internal.earcreation.modulemap.EARProjectMap}
 * object.
 */
public class EARProjectMapItemProvider extends ModulemapItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/** Added to generated code */
	protected boolean includeModules = false;

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public EARProjectMapItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public EARProjectMapItemProvider(AdapterFactory adapterFactory, boolean includeModules) {
		this(adapterFactory);
		this.includeModules = includeModules;
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			if (includeModules)
				childrenReferences.add(ModulemapPackage.eINSTANCE.getEARProjectMap_Mappings());
			childrenReferences.add(ModulemapPackage.eINSTANCE.getEARProjectMap_UtilityJARMappings());
		}
		return childrenReferences;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EReference getChildReference(Object object, Object child) {
		// TODO: Check the type of the specified child object and return the proper feature to use
		// for
		//       adding (see {@link AddCommand}) it as a child.

		return super.getChildReference(object, child);
	}


	/**
	 * This returns the parent of the EARProjectMap.
	 */
	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	/**
	 * This returns EARProjectMap.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("EARProjectMap"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		return ApplicationProvidersResourceHandler.getString("EARProjectMap_UI_"); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(EARProjectMap.class)) {
			case ModulemapPackage.EAR_PROJECT_MAP__MAPPINGS :
			case ModulemapPackage.EAR_PROJECT_MAP__UTILITY_JAR_MAPPINGS : {
				fireNotifyChanged(notification);
				return;
			}
		}
		super.notifyChanged(notification);
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
	 */
	/*
	 * public void notifyChanged(Notifier notifier, int eventType, EObject feature, Object oldValue,
	 * Object newValue, int index) { ModulemapPackage pkg = ModulemapPackage.eINSTANCE;; if
	 * (!includeModules && feature == pkg.getEARProjectMap_Mappings()) return;
	 * 
	 * if (feature == pkg.getEARProjectMap_Mappings() || feature ==
	 * pkg.getEARProjectMap_UtilityJARMappings()) { fireNotifyChanged(notifier, eventType, feature,
	 * oldValue, newValue, index); return; } super.notifyChanged(notifier, eventType, feature,
	 * oldValue, newValue, index); }
	 */

	/**
	 * This adds to the collection of {@linkcom.ibm.etools.emf.edit.command.CommandParameter}s
	 * describing all of the children that can be created under this object.
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		if (includeModules)
			newChildDescriptors.add(createChildParameter(modulemapPackage.getEARProjectMap_Mappings(), modulemapPackage.getModuleMapping()));
		newChildDescriptors.add(createChildParameter(modulemapPackage.getEARProjectMap_UtilityJARMappings(), modulemapPackage.getUtilityJARMapping()));
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
}