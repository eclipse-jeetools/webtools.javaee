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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.ejb.EJBRelationshipRole}object.
 */
public class EJBRelationshipRoleItemProvider extends EjbItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {
	protected static final EStructuralFeature ROLES_SF = EjbFactoryImpl.getPackage().getEJBRelation_RelationshipRoles();
	protected EJBRelationshipRole targetRole;
	private static final Class ITreeItemContentProviderClass = ITreeItemContentProvider.class;

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public EJBRelationshipRoleItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: create some child object.
		return null;
	}

	/**
	 * This specifies how to implement {@link #getChildren}and {@link AddCommand}and
	 * {@link RemoveCommand}support in {@link #createCommand}.
	 */
	public Collection getChildrenReferences(Object object) {
		if (childrenReferences == null) {
			super.getChildrenReferences(object);
			// Exclude these children for mapping purposes
			//	  childrenReferences.add(EjbPackage.eINSTANCE.getEJBRelationshipRole_CmrField());
			//	  childrenReferences.add(EjbPackage.eINSTANCE.getEJBRelationshipRole_Source());
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
	 * This returns the image for {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		return J2EEPlugin.getPlugin().getImage(refObject.eClass().getName() + "Create#CHILD_CLASS_NAME#"); //$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return "Create #CHILD_CLASS_NAME#"; //$NON-NLS-1$
	}

	/**
	 * This returns the help text for {@link org.eclipse.jst.j2ee.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return "Create a child of type #CHILD_CLASS_NAME# for the selected " + refObject.eClass().getName() + EJBProviderLibrariesResourceHandler.getString("_4"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * This returns EJBRelationshipRole.gif.
	 */
	public Object getImage(Object object) {
		CommonRelationshipRole role = (CommonRelationshipRole) object;
		String imageName;
		if (role.isKey()) {
			imageName = "roleKey_obj"; //$NON-NLS-1$
		} else if (role.isForward()) {
			Object base = J2EEPlugin.getPlugin().getImage("role_obj"); //$NON-NLS-1$
			Object overlayImage = J2EEPlugin.getPlugin().getImage("frnkeyrelnshp_ovr"); //$NON-NLS-1$
			ArrayList imageObjects = new ArrayList();
			imageObjects.add(base);
			imageObjects.add(overlayImage);
			return new ComposedImage(imageObjects) {
				public List getDrawPoints(Size size) {
					List result = super.getDrawPoints(size);
					if (result.size() > 1) {
						((Point) result.get(1)).x = ((Point) result.get(1)).x + 8;
						((Point) result.get(1)).y = ((Point) result.get(1)).y + 2;
					}
					return result;
				}
			};
		} else {
			imageName = "role_obj"; //$NON-NLS-1$
		}
		return J2EEPlugin.getPlugin().getImage(imageName);
	}

	/**
	 * This returns the parent of the EJBRelationshipRole.
	 */
	public Object getParent(Object object) {
		// override to convince the mapping domain that we are part of the input object graph
		//	return ((EObject)object).eContainer();

		return (((EJBRelationshipRole) object).getSource() != null) ? ((EJBRelationshipRole) object).getSourceEntity() : null;
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			EjbPackage pkg = EjbPackage.eINSTANCE;
			;

			// This is for the description feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Description_5"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_description_property_of_the_ejb_relationship_role_6"), //$NON-NLS-1$
						pkg.getEJBRelationshipRole_Description(), false, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

			// This is for the name feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Name_7"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_roleName_property_of_the_ejb_relationship_role_8"), //$NON-NLS-1$
						pkg.getEJBRelationshipRole_RoleName(), false, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

			// This is for the multiplicity feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Multiplicity_9"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_multiplicity_property_of_the_ejb_relationship_role_10"), //$NON-NLS-1$
						pkg.getEJBRelationshipRole_Multiplicity(), false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));

			// This is for the cascadeDelete feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Cascade_Delete_11"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_cascade_delete_property_of_the_ejb_relationship_role_12"), //$NON-NLS-1$
						pkg.getEJBRelationshipRole_CascadeDelete(), false, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));

			// This is for the cmrField feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Cmr_Field_13"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_cmr_field_of_the_ejb_relationship_role_14"), //$NON-NLS-1$
						pkg.getEJBRelationshipRole_CmrField(), false));

			// This is for the relationship feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Relationship_15"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_relationship_of_the_ejb_relationship_role_16"), //$NON-NLS-1$
						pkg.getEJBRelationshipRole_Relationship(), false));

			// This is for the source feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Source_17"), //$NON-NLS-1$
						EJBProviderLibrariesResourceHandler.getString("The_source_of_the_ejb_relationship_role_18"), //$NON-NLS-1$
						pkg.getEJBRelationshipRole_Source(), false));

		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Description feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addDescriptionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EJBRelationshipRole_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EJBRelationshipRole_description_feature", "_UI_EJBRelationshipRole_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEJBRelationshipRole_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Role Name feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addRoleNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EJBRelationshipRole_roleName_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EJBRelationshipRole_roleName_feature", "_UI_EJBRelationshipRole_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEJBRelationshipRole_RoleName(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Multiplicity feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addMultiplicityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EJBRelationshipRole_multiplicity_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EJBRelationshipRole_multiplicity_feature", "_UI_EJBRelationshipRole_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEJBRelationshipRole_Multiplicity(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
	}

	/**
	 * This adds a property descriptor for the Cascade Delete feature. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addCascadeDeletePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_EJBRelationshipRole_cascadeDelete_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_EJBRelationshipRole_cascadeDelete_feature", "_UI_EJBRelationshipRole_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getEJBRelationshipRole_CascadeDelete(), true, ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));
	}

	public String getText(Object object) {
		try {
			CommonRelationshipRole aRole = (CommonRelationshipRole) object;
			if (aRole == null)
				return ""; //$NON-NLS-1$
			StringBuffer b = new StringBuffer();
			if (aRole.isMany())
				b.append("[0..*]"); //$NON-NLS-1$
			else {
				if (aRole.isKey())
					b.append("[1..1]"); //$NON-NLS-1$
				else
					b.append("[0..1]"); //$NON-NLS-1$
			}
			b.append(" "); //$NON-NLS-1$
			b.append(aRole.getRoleName()).append(" : "); //$NON-NLS-1$
			ContainerManagedEntity type = aRole.getTypeEntity();
			if (type == null)
				b.append(EJBProviderLibrariesResourceHandler.getString("Unknown_Role_Type_UI_")); //$NON-NLS-1$
			else
				b.append(type.getName());
			return b.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(EJBRelationshipRole.class)) {
			case EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTION :
			case EjbPackage.EJB_RELATIONSHIP_ROLE__ROLE_NAME :
			case EjbPackage.EJB_RELATIONSHIP_ROLE__MULTIPLICITY :
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CASCADE_DELETE :
			case EjbPackage.EJB_RELATIONSHIP_ROLE__SOURCE :
			case EjbPackage.EJB_RELATIONSHIP_ROLE__CMR_FIELD :
			case EjbPackage.EJB_RELATIONSHIP_ROLE__DESCRIPTIONS : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBRelationshipRole_Source(), EjbFactory.eINSTANCE.createRoleSource()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBRelationshipRole_CmrField(), EjbFactory.eINSTANCE.createCMRField()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBRelationshipRole_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getEJBRelationshipRole_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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
	 * Object newValue, int index) { EjbPackage pkg = EjbPackage.eINSTANCE;; if (feature ==
	 * pkg.getEJBRelationshipRole_Description() || feature == pkg.getEJBRelationshipRole_RoleName() ||
	 * feature == pkg.getEJBRelationshipRole_Multiplicity() || feature ==
	 * pkg.getEJBRelationshipRole_CascadeDelete() || // feature ==
	 * pkg.getEJBRelationshipRole_CmrField() || feature == pkg.getEJBRelationshipRole_Relationship() ||
	 * feature == pkg.getEJBRelationshipRole_Source()) { fireNotifyChanged(notifier, eventType,
	 * feature, oldValue, newValue, index); return; } super.notifyChanged(notifier, eventType,
	 * feature, oldValue, newValue, index); }
	 */
	/**
	 * @see ItemProviderAdapter#setTarget(Notifier)
	 */
	public void setTarget(Notifier arg0) {
		EJBRelationshipRole role = arg0 instanceof EJBRelationshipRole ? (EJBRelationshipRole) arg0 : null;
		super.setTarget(arg0);
		if (role != null) {
			if (getAdapterFactory() == null) {
				return;
			}
			if (role.getSourceEntity() != null && !role.getSourceEntity().eAdapters().contains(this)) {
				getAdapterFactory().adapt(role.getSourceEntity(), ITreeItemContentProviderClass);
				role.getSourceEntity().eAdapters().add(this);
			}
			RoleSource rs = role.getSource();
			if (rs != null) {
				getAdapterFactory().adapt(rs, getAdapterFactory());
			}
		}
	}

}