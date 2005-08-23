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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientFactory;


/**
 * This is the item provider adpater for a
 * {@link org.eclipse.jst.j2ee.internal.internal.common.JNDIEnvRefsGroup}object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class JNDIEnvRefsGroupItemProvider extends CompatibilityDescriptionGroupItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public JNDIEnvRefsGroupItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenReferences.add(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EnvironmentProperties());
			childrenReferences.add(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceRefs());
			childrenReferences.add(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbRefs());
			childrenReferences.add(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceEnvRefs());
			childrenReferences.add(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbLocalRefs());
			childrenReferences.add(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_MessageDestinationRefs());
			childrenReferences.add(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ServiceRefs());
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
	 * This returns JNDIEnvRefsGroup.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Object getImage(Object object) {
		return getResourceLocator().getImage("full/obj16/JNDIEnvRefsGroup"); //$NON-NLS-1$
	}

	/**
	 * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	public String getText(Object object) {
		String label = ((JNDIEnvRefsGroup) object).getDisplayName();
		return label == null || label.length() == 0 ? getString("_UI_JNDIEnvRefsGroup_type") : //$NON-NLS-1$
					getString("_UI_JNDIEnvRefsGroup_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(JNDIEnvRefsGroup.class)) {
			case CommonPackage.JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES :
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_REFS :
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_REFS :
			case CommonPackage.JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS :
			case CommonPackage.JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS :
			case CommonPackage.JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS :
			case CommonPackage.JNDI_ENV_REFS_GROUP__SERVICE_REFS : {
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

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EnvironmentProperties(), CommonFactory.eINSTANCE.createEnvEntry()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceRefs(), CommonFactory.eINSTANCE.createResourceRef()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbRefs(), CommonFactory.eINSTANCE.createEjbRef()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbRefs(), CommonFactory.eINSTANCE.createEJBLocalRef()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceEnvRefs(), CommonFactory.eINSTANCE.createResourceEnvRef()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbLocalRefs(), CommonFactory.eINSTANCE.createEJBLocalRef()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_MessageDestinationRefs(), CommonFactory.eINSTANCE.createMessageDestinationRef()));

		newChildDescriptors.add(createChildParameter(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ServiceRefs(), Webservice_clientFactory.eINSTANCE.createServiceRef()));
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
	 */
	public ResourceLocator getResourceLocator() {
		return J2EEPlugin.getDefault();
	}
}