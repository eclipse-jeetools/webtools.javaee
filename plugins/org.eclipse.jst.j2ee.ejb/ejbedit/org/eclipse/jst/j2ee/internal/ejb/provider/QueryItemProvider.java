/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


// Referenced classes of package org.eclipse.jst.j2ee.internal.internal.ejb.provider:
//            EjbItemProviderAdapter

public class QueryItemProvider extends EjbItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	public QueryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Object createChild(Object object) {
		return null;
	}

	public Collection getChildrenReferences(Object object) {
		if (super.childrenReferences == null) {
			super.getChildrenReferences(object);
			super.childrenReferences.add(EjbPackage.eINSTANCE.getQuery_QueryMethod());
		}
		return super.childrenReferences;
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


	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		return J2EEPlugin.getPlugin().getImage(refObject.eClass().getName() + "Create#CHILD_CLASS_NAME#"); //$NON-NLS-1$
	}

	public String getCreateChildText(Object object) {
		return "Create #CHILD_CLASS_NAME#"; //$NON-NLS-1$
	}

	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return "Create a child of type #CHILD_CLASS_NAME# for the selected " + refObject.eClass().getName() + "."; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("db_obj"); //$NON-NLS-1$
	}

	public Object getParent(Object object) {
		return ((EObject) object).eContainer();
	}

	public List getPropertyDescriptors(Object object) {
		if (super.itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);
			EjbPackage pkg = EjbPackage.eINSTANCE;
			super.itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) super.adapterFactory).getRootAdapterFactory(), "Description_7", "The_description_property_of_the_query_8", pkg.getQuery_Description(), true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); //$NON-NLS-1$ //$NON-NLS-2$
			super.itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) super.adapterFactory).getRootAdapterFactory(), "Ejb_QL_9", "The_ejb_ql_property_of_the_query_10", pkg.getQuery_EjbQL(), true, ItemPropertyDescriptor.TEXT_VALUE_IMAGE)); //$NON-NLS-1$ //$NON-NLS-2$
			super.itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) super.adapterFactory).getRootAdapterFactory(), "Return_Type_Mapping_11", "The_return_type_mapping_property_of_the_query_12", pkg.getQuery_ReturnTypeMapping(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)); //$NON-NLS-1$ //$NON-NLS-2$
			super.itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) super.adapterFactory).getRootAdapterFactory(), "Entity_13", "The_entity_of_the_query_14", pkg.getQuery_Entity(), false)); //$NON-NLS-1$ //$NON-NLS-2$
			super.itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) super.adapterFactory).getRootAdapterFactory(), "Query_Method_15", "The_query_method_of_the_query_16", pkg.getQuery_QueryMethod(), false)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return super.itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Query_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Query_description_feature", "_UI_Query_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getQuery_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Ejb QL feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addEjbQLPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Query_ejbQL_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Query_ejbQL_feature", "_UI_Query_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getQuery_EjbQL(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Return Type Mapping feature. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addReturnTypeMappingPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_Query_returnTypeMapping_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_Query_returnTypeMapping_feature", "_UI_Query_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getQuery_ReturnTypeMapping(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	public String getText(Object object) {
		Query query = (Query) object;
		return "Query " + query.getEjbQL(); //$NON-NLS-1$
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(Query.class)) {
			case EjbPackage.QUERY__DESCRIPTION :
			case EjbPackage.QUERY__EJB_QL :
			case EjbPackage.QUERY__RETURN_TYPE_MAPPING :
			case EjbPackage.QUERY__QUERY_METHOD :
			case EjbPackage.QUERY__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getQuery_QueryMethod(), EjbFactory.eINSTANCE.createQueryMethod()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getQuery_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getQuery_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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