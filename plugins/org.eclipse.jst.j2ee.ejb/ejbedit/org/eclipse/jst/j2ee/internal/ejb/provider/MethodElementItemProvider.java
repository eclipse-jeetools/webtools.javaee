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
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.MethodElement}object.
 */
public class MethodElementItemProvider extends EjbItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	protected final static EjbPackage PACK_SF = EjbFactoryImpl.getPackage();
	protected final static EStructuralFeature NAME_SF = PACK_SF.getMethodElement_Name();
	protected final static EStructuralFeature DESC_SF = PACK_SF.getMethodElement_Description();
	protected final static EStructuralFeature EJB_SF = PACK_SF.getMethodElement_EnterpriseBean();
	protected final static EStructuralFeature PARMS_SF = PACK_SF.getMethodElement_Parms();
	protected final static EStructuralFeature TYPE_SF = PACK_SF.getMethodElement_Type();

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public MethodElementItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns MethodElement.gif.
	 */
	public Object getImage(Object object) {

		MethodElement gen = (MethodElement) object;
		String overlayString = null;
		Object base, overlayImage;

		if (gen.isValid())
			base = J2EEPlugin.getPlugin().getImage("methElement_obj"); //$NON-NLS-1$
		else
			base = J2EEPlugin.getPlugin().getImage("warn_tsk"); //$NON-NLS-1$

		if (gen.isHome()) { //$NON-NLS-1$ = "Home Methods"
			overlayString = "home_interf_ov"; //$NON-NLS-1$
		}
		if (gen.isRemote()) { //$NON-NLS-1$ = "Remote Methods"
			overlayString = "remote_interf_ov"; //$NON-NLS-1$
		}
		if (gen.isLocal()) {
			overlayString = "local_ovr"; //$NON-NLS-1$
		}
		if (gen.isLocalHome()) {
			overlayString = "local_home_ovr"; //$NON-NLS-1$
		}
		if (gen.isUnspecified()) { //$NON-NLS-1$ = "Common Methods"
			return base;
		}
		overlayImage = J2EEPlugin.getPlugin().getImage(overlayString);
		ArrayList imageObjects = new ArrayList();
		imageObjects.add(base);
		imageObjects.add(overlayImage);
		return new ComposedImage(imageObjects) {
			public List getDrawPoints(Size size) {
				List result = super.getDrawPoints(size);
				if (result.size() > 1) {
					((Point) result.get(1)).x = ((Point) result.get(1)).x + 8;
				}
				return result;
			}
		};
		//return null;
	}

	/**
	 * This returns the parent of the MethodElement.
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

			// This is for the name feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Name_UI_"), //$NON-NLS-1$ = "Name"
						EJBProviderLibrariesResourceHandler.getString("The_name_property_UI_"), //$NON-NLS-1$ = "The name property"
						pkg.getMethodElement_Name()));

			// This is for the parms feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Parms_UI_"), //$NON-NLS-1$ = "Parms"
						EJBProviderLibrariesResourceHandler.getString("The_parms_property_UI_"), //$NON-NLS-1$ = "The parms property"
						pkg.getMethodElement_Parms()));

			// This is for the type feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Type_UI_"), //$NON-NLS-1$ = "Type"
						EJBProviderLibrariesResourceHandler.getString("The_type_property_UI_"), //$NON-NLS-1$ = "The type property"
						pkg.getMethodElement_Type()));

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Description_UI_"), //$NON-NLS-1$ = "Description"
						EJBProviderLibrariesResourceHandler.getString("The_description_property_UI_"), //$NON-NLS-1$ = "The description property"
						pkg.getMethodElement_Description()));

			// This is for the enterpriseBean feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("EnterpriseBean_UI_"), //$NON-NLS-1$ = "EnterpriseBean"
						EJBProviderLibrariesResourceHandler.getString("The_enterpriseBean_propert_UI_"), //$NON-NLS-1$ = "The enterpriseBean property"
						pkg.getMethodElement_EnterpriseBean()));

			// This is for the methodPermission feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
			 * ResourceHandler.getString("MethodPermission_UI_"), //$NON-NLS-1$ = "MethodPermission"
			 * ResourceHandler.getString("The_methodPermission_prope_UI_"), //$NON-NLS-1$ = "The
			 * methodPermission property" pkg.getMethodElement_MethodPermission()));
			 */
			// This is for the methodTransaction feature.
			//
			/*
			 * itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
			 * ResourceHandler.getString("MethodTransaction_UI_"), //$NON-NLS-1$ =
			 * "MethodTransaction" ResourceHandler.getString("The_methodTransaction_prop_UI_"),
			 * //$NON-NLS-1$ = "The methodTransaction property"
			 * pkg.getMethodElement_MethodTransaction()));
			 */
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodElement_name_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodElement_name_feature", "_UI_MethodElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodElement_Name(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Parms feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addParmsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodElement_parms_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodElement_parms_feature", "_UI_MethodElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodElement_Parms(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Type feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodElement_type_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodElement_type_feature", "_UI_MethodElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodElement_Type(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodElement_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodElement_description_feature", "_UI_MethodElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodElement_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Enterprise Bean feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addEnterpriseBeanPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_MethodElement_enterpriseBean_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_MethodElement_enterpriseBean_feature", "_UI_MethodElement_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getMethodElement_EnterpriseBean(), true));
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
			childrenReferences.add(EjbPackage.eINSTANCE.getMethodElement_Descriptions());
		}
		return childrenReferences;
	}


	public String getText(Object object) {
		return ((MethodElement) object).getSignature();
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(MethodElement.class)) {
			case EjbPackage.METHOD_ELEMENT__NAME :
			case EjbPackage.METHOD_ELEMENT__PARMS :
			case EjbPackage.METHOD_ELEMENT__TYPE :
			case EjbPackage.METHOD_ELEMENT__DESCRIPTION :
			case EjbPackage.METHOD_ELEMENT__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMethodElement_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getMethodElement_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
	 */
	/*
	 * public void notifyChanged(Notifier notifier, int eventType, EObject feature, Object oldValue,
	 * Object newValue, int index) { if (feature == NAME_SF || feature == PARMS_SF || feature ==
	 * TYPE_SF || feature == ZERO_PARAMS_SF || feature == PARAMS_SF || feature == DESC_SF || feature ==
	 * EJB_SF || feature == PERMISSION_SF || feature == TRANSACTION_SF || feature == FLUSH_SF) {
	 * fireNotifyChanged(notifier, eventType, feature, oldValue, newValue, index); return; }
	 * super.notifyChanged(notifier, eventType, feature, oldValue, newValue, index); }
	 */
	/**
	 * @see Adapter#setTarget(Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		super.setTarget(newTarget);
		if (newTarget != null && newTarget instanceof MethodElement) {
			MethodElement me = (MethodElement) newTarget;
			EnterpriseBean ejb = me.getEnterpriseBean();
			if (ejb != null) {
				JavaClass javaClass = me.getTypeJavaClass();
				if (javaClass != null)
					adaptJavaClass(javaClass);
				else {
					//unspecified
					if (ejb.hasRemoteClient()) {
						adaptJavaClass(ejb.getRemoteInterface());
						adaptJavaClass(ejb.getHomeInterface());
					}
					if (ejb.hasLocalClient())
						adaptJavaClass(ejb.getLocalInterface());
					adaptJavaClass(ejb.getLocalHomeInterface());
				}
			}
		}
	}

	protected void adaptJavaClass(JavaClass javaClass) {
		if (javaClass != null) {
			javaClass.eAdapters().add(this);
			super.setTarget(javaClass);
		}
	}

	/**
	 * @see IDisposable#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

}