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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.AssemblyDescriptor}object.
 */
public class AssemblyDescriptorItemProvider extends EjbItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public AssemblyDescriptorItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: check that this is what you want.
		MethodPermission child = EjbFactory.eINSTANCE.createMethodPermission();

		// TODO: initialize child here...

		return child;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		EjbPackage pkg = EjbPackage.eINSTANCE;
		Collection result = new ArrayList();
		result.add(pkg.getAssemblyDescriptor_SecurityRoles());
		result.add(pkg.getAssemblyDescriptor_MethodPermissions());
		result.add(pkg.getAssemblyDescriptor_MethodTransactions());
		result.add(pkg.getAssemblyDescriptor_ExcludeList());
		return result;
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
		return J2EEPlugin.getPlugin().getImage(refObject.eClass().getName() + "CreateMethodPermission");//$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return EJBProviderLibrariesResourceHandler.getString("Create_MethodPermission_UI_"); //$NON-NLS-1$ = "Create MethodPermission"
	}

	/**
	 * This returns the help text for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return EJBProviderLibrariesResourceHandler.getString("Create_a_child_of_type_Met_UI_") + refObject.eClass().getName() + ".";//$NON-NLS-2$ //$NON-NLS-1$ = "Create a child of type MethodPermission for the selected "
	}

	/**
	 * This returns AssemblyDescriptor.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("assemblyDescriptor_obj");//$NON-NLS-1$
	}

	/**
	 * This returns the parent of the AssemblyDescriptor.
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

			// This is for the ejbJar feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("EjbJar_UI_"), //$NON-NLS-1$ = "EjbJar"
						EJBProviderLibrariesResourceHandler.getString("The_ejbJar_property_UI_"), //$NON-NLS-1$ = "The ejbJar property"
						pkg.getAssemblyDescriptor_EjbJar()));

		}
		return itemPropertyDescriptors;
	}

	public String getText(Object object) {
		return EJBProviderLibrariesResourceHandler.getString("AssemblyDescriptor_UI_"); //$NON-NLS-1$ = "AssemblyDescriptor"
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(AssemblyDescriptor.class)) {
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_PERMISSIONS :
			case EjbPackage.ASSEMBLY_DESCRIPTOR__METHOD_TRANSACTIONS :
			case EjbPackage.ASSEMBLY_DESCRIPTOR__SECURITY_ROLES :
			case EjbPackage.ASSEMBLY_DESCRIPTOR__EXCLUDE_LIST :
			case EjbPackage.ASSEMBLY_DESCRIPTOR__MESSAGE_DESTINATIONS : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getAssemblyDescriptor_MethodPermissions(), EjbFactory.eINSTANCE.createMethodPermission()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getAssemblyDescriptor_MethodTransactions(), EjbFactory.eINSTANCE.createMethodTransaction()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getAssemblyDescriptor_SecurityRoles(), CommonFactory.eINSTANCE.createSecurityRole()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getAssemblyDescriptor_ExcludeList(), EjbFactory.eINSTANCE.createExcludeList()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getAssemblyDescriptor_MessageDestinations(), CommonFactory.eINSTANCE.createMessageDestination()));
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
}