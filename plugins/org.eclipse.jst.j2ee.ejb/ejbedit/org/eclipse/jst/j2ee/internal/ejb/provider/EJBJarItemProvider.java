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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.internal.provider.CompatibilityDescriptionGroupItemProvider;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.EJBJar}object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class EJBJarItemProvider extends CompatibilityDescriptionGroupItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public EJBJarItemProvider(AdapterFactory adapterFactory) {
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

			addEjbClientJarPropertyDescriptor(object);
			addVersionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Ejb Client Jar feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	protected void addEjbClientJarPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_EJBJar_ejbClientJar_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_EJBJar_ejbClientJar_feature_desc"), //$NON-NLS-1$
					EjbPackage.eINSTANCE.getEJBJar_EjbClientJar(), false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Version feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 */
	protected void addVersionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_EJBJar_version_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_EJBJar_version_feature_desc"), //$NON-NLS-1$
					EjbPackage.eINSTANCE.getEJBJar_Version(), false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(EjbPackage.eINSTANCE.getEJBJar_AssemblyDescriptor());
			childrenReferences.add(EjbPackage.eINSTANCE.getEJBJar_EnterpriseBeans());
			childrenReferences.add(EjbPackage.eINSTANCE.getEJBJar_RelationshipList());
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
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(EJBJar.class)) {
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR :
			case EjbPackage.EJB_JAR__VERSION :
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR :
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS :
			case EjbPackage.EJB_JAR__RELATIONSHIP_LIST : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBJar_AssemblyDescriptor(), EjbFactory.eINSTANCE.createAssemblyDescriptor()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBJar_EnterpriseBeans(), EjbFactory.eINSTANCE.createEntity()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBJar_EnterpriseBeans(), EjbFactory.eINSTANCE.createContainerManagedEntity()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBJar_EnterpriseBeans(), EjbFactory.eINSTANCE.createSession()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBJar_EnterpriseBeans(), EjbFactory.eINSTANCE.createMessageDriven()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBJar_RelationshipList(), EjbFactory.eINSTANCE.createRelationships()));
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
	 * This returns EJBJar.gif.
	 */
	public Object getImage(Object object) {

		String key = null;
		switch (((EJBJar) object).getVersionID()) {

			case J2EEVersionConstants.EJB_1_0_ID :
				key = "10_ejbjar_obj"; //$NON-NLS-1$
				break;
			case J2EEVersionConstants.EJB_1_1_ID :
				key = "11_ejbjar_obj"; //$NON-NLS-1$
				break;
			case J2EEVersionConstants.EJB_2_0_ID :
				key = "20_ejbjar_obj"; //$NON-NLS-1$
				break;
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				key = "21_ejb_obj"; //$NON-NLS-1$
				break;
		}

		return J2EEPlugin.getPlugin().getImage(key); //$NON-NLS-1$
	}

	public String getText(Object object) {
		EJBJar ejbJar = (EJBJar) object;
		if (ejbJar.getDisplayName() == null) {
			try {
				IProject project = ProjectUtilities.getProject(ejbJar);
				return project.getDescription().getName();
			} catch (Exception e) {
				//Ignore
			}

			Resource resource = ejbJar.eResource();
			if (resource != null) {
				return new Path(resource.getURI().toString()).removeFileExtension().lastSegment();
			}
			return EJBProviderLibrariesResourceHandler.getString("EJBJar_UI_"); //$NON-NLS-1$ = "EJBJar"

		}
		return ejbJar.getDisplayName();
	}

	/**
	 * @see ITreeItemContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object parent) {
		if (parent == null)
			return false;
		return !((EJBJar) parent).getEnterpriseBeans().isEmpty() || ((EJBJar) parent).getAssemblyDescriptor() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		List children = new ArrayList();
		EJBJar aJar = (EJBJar) object;
		if (aJar.getAssemblyDescriptor() != null)
			children.add(aJar.getAssemblyDescriptor());
		if (aJar.getEnterpriseBeans() != null)
			children.addAll(aJar.getEnterpriseBeans());
		if (aJar.getRelationshipList() != null && aJar.getRelationshipList().getEjbRelations() != null)
			children.addAll(aJar.getRelationshipList().getEjbRelations());
		return children;
	}

}