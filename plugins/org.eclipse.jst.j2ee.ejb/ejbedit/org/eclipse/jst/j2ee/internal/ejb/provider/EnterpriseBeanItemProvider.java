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
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.internal.provider.JNDIEnvRefsGroupItemProvider;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceExtManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper;

/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.EnterpriseBean}object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class EnterpriseBeanItemProvider extends JNDIEnvRefsGroupItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public EnterpriseBeanItemProvider(AdapterFactory adapterFactory) {
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

			addNamePropertyDescriptor(object);
			//addEjbClassPropertyDescriptor(object);
			//addHomeInterfacePropertyDescriptor(object);
			//addRemoteInterfacePropertyDescriptor(object);
			//addLocalHomeInterfacePropertyDescriptor(object);
			//addLocalInterfacePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 *  
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("_UI_EnterpriseBean_name_feature"), //$NON-NLS-1$
					EJBProviderLibrariesResourceHandler.getString("_UI_EnterpriseBean_name_feature_desc"), //$NON-NLS-1$ 
					EjbPackage.eINSTANCE.getEnterpriseBean_Name(), false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Ejb Class feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addEjbClassPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EnterpriseBean_ejbClass_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EnterpriseBean_ejbClass_feature", "_UI_EnterpriseBean_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEnterpriseBean_EjbClass(), true));
	}

	/**
	 * This adds a property descriptor for the Home Interface feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addHomeInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EnterpriseBean_homeInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EnterpriseBean_homeInterface_feature", "_UI_EnterpriseBean_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEnterpriseBean_HomeInterface(), true));
	}

	/**
	 * This adds a property descriptor for the Remote Interface feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRemoteInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EnterpriseBean_remoteInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EnterpriseBean_remoteInterface_feature", "_UI_EnterpriseBean_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEnterpriseBean_RemoteInterface(), true));
	}

	/**
	 * This adds a property descriptor for the Local Home Interface feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLocalHomeInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EnterpriseBean_localHomeInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EnterpriseBean_localHomeInterface_feature", "_UI_EnterpriseBean_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEnterpriseBean_LocalHomeInterface(), true));
	}

	/**
	 * This adds a property descriptor for the Local Interface feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addLocalInterfacePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EnterpriseBean_localInterface_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EnterpriseBean_localInterface_feature", "_UI_EnterpriseBean_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEnterpriseBean_LocalInterface(), true));
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
            childrenReferences.add(EjbPackage.eINSTANCE.getEnterpriseBean_SecurityRoleRefs());
            childrenReferences.add(EjbPackage.eINSTANCE.getEnterpriseBean_SecurityIdentity());
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
		switch (notification.getFeatureID(EnterpriseBean.class)) {
			case EjbPackage.ENTERPRISE_BEAN__EJB_CLASS :
			case EjbPackage.ENTERPRISE_BEAN__HOME_INTERFACE :
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_HOME_INTERFACE :
			case EjbPackage.ENTERPRISE_BEAN__LOCAL_INTERFACE :
			case EjbPackage.ENTERPRISE_BEAN__REMOTE_INTERFACE :
			case EjbPackage.ENTERPRISE_BEAN__NAME :
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_ROLE_REFS :
			case EjbPackage.ENTERPRISE_BEAN__SECURITY_IDENTITY : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEnterpriseBean_SecurityRoleRefs(), CommonFactory.eINSTANCE.createSecurityRoleRef()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEnterpriseBean_SecurityIdentity(), CommonFactory.eINSTANCE.createSecurityIdentity()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEnterpriseBean_SecurityIdentity(), CommonFactory.eINSTANCE.createRunAsSpecifiedIdentity()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEnterpriseBean_SecurityIdentity(), CommonFactory.eINSTANCE.createUseCallerIdentity()));
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

	public static String getEJBName(Object object) {
		String name = ((EnterpriseBean) object).getDisplayName();
		if (name == null || name.equals("")) //$NON-NLS-1$
			name = ((EnterpriseBean) object).getName();
		return name == null ? "<<??>>" : name; //$NON-NLS-1$
	}

	/**
	 * This returns EnterpriseBean.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("bmpEntity_obj"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		return getEJBName(object);
	}

	public Collection getChildrenSuper(Object object) {
		Collection myChildren = super.getChildren(object);
		EnterpriseBean bean = (EnterpriseBean) object;
		if (bean == null || bean.getEjbJar() == null)
			return myChildren;
		try {
			if (bean.getEjbJar().getJ2EEVersionID() <= J2EEVersionConstants.J2EE_1_3_ID) {
				WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
				myChildren.addAll(serviceHelper.get13ServiceRefs(bean));
			}
		} catch (RuntimeException re) {
			Logger.getLogger().logError(re);
		}
		return myChildren;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection myChildren = super.getChildren(object);
		EnterpriseBean bean = (EnterpriseBean) object;
		try {
			if (bean.getEjbJar().getJ2EEVersionID() <= J2EEVersionConstants.J2EE_1_3_ID) {
				WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
				myChildren.addAll(serviceHelper.get13ServiceRefs(bean));
			}
		} catch (RuntimeException re) {
			Logger.getLogger().logError(re);
		}
		return myChildren;
	}
}