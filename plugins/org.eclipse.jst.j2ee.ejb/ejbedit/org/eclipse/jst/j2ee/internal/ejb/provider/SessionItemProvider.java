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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.Session}object.
 */
public class SessionItemProvider extends EnterpriseBeanItemProvider implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public SessionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns Session.gif.
	 */
	public Object getImage(Object object) {
		//	  Session sess = null;
		// 	  if (object instanceof Session)
		// 	    sess = (Session) object;
		// 	  if (sess != null && sess.isVersion2_X())
		// 	    return J2EEPlugin.getPlugin().getImage("20_ejb_obj"); //$NON-NLS-1$
		return J2EEPlugin.getPlugin().getImage("sessionBean_obj"); //$NON-NLS-1$
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
		switch (notification.getFeatureID(Session.class)) {
			case EjbPackage.SESSION__TRANSACTION_TYPE :
			case EjbPackage.SESSION__SERVICE_ENDPOINT :
			case EjbPackage.SESSION__SESSION_TYPE : {
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
	 * This returns the parent of the Session.
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

			EjbPackage pkg = EjbPackage.eINSTANCE;

			// This is for the transactionType feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("TransactionType_UI_"), //$NON-NLS-1$ = "TransactionType"
						EJBProviderLibrariesResourceHandler.getString("The_transactionType_proper_UI_"), //$NON-NLS-1$ = "The transactionType property"
						pkg.getSession_TransactionType()));

			// This is for the sessionType feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("SessionType_UI_"), //$NON-NLS-1$ = "SessionType"
						EJBProviderLibrariesResourceHandler.getString("The_sessionType_property_UI_"), //$NON-NLS-1$ = "The sessionType property"
						pkg.getSession_SessionType()));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Transaction Type feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTransactionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Session_transactionType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Session_transactionType_feature", "_UI_Session_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getSession_TransactionType(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Session Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addSessionTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Session_sessionType_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Session_sessionType_feature", "_UI_Session_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getSession_SessionType(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Service Endpoint feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addServiceEndpointPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Session_serviceEndpoint_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Session_serviceEndpoint_feature", "_UI_Session_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getSession_ServiceEndpoint(), true));
	}


}