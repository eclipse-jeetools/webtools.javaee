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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;


/**
 * This is the item provider adpater for a {@link org.eclipse.jst.j2ee.internal.internal.ejb.CMPAttribute}object.
 */
public class CMPAttributeItemProvider extends EjbItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider, IItemPropertySource, IStructuredItemContentProvider, ITreeItemContentProvider {

	protected static final String[] choiceOfTypes = {"boolean", "byte", "byte[]", "char", "double", "float", "int", "long", "short", "java.lang.Boolean", "java.lang.Byte", "java.lang.Character", "java.lang.Double", "java.lang.Float", "java.lang.Integer", "java.lang.Long", "java.lang.Short", "java.lang.String", "java.math.BigDecimal", "java.sql.Date", "java.sql.Time", "java.sql.Timestamp", "java.util.Calendar", "java.util.Date"}; //$NON-NLS-24$//$NON-NLS-23$//$NON-NLS-22$//$NON-NLS-21$//$NON-NLS-20$//$NON-NLS-19$//$NON-NLS-18$//$NON-NLS-17$//$NON-NLS-16$//$NON-NLS-15$//$NON-NLS-14$//$NON-NLS-13$//$NON-NLS-12$//$NON-NLS-11$//$NON-NLS-10$//$NON-NLS-9$//$NON-NLS-8$//$NON-NLS-7$//$NON-NLS-6$//$NON-NLS-5$//$NON-NLS-4$//$NON-NLS-3$//$NON-NLS-2$//$NON-NLS-1$

	/**
	 * This constructs an instance from a factory and a notifier.
	 */
	public CMPAttributeItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This creates a new child for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public Object createChild(Object object) {
		// TODO: check that this is what you want.
		ContainerManagedEntity child = EjbFactory.eINSTANCE.createContainerManagedEntity();

		// TODO: initialize child here...

		return child;
	}

	/**
	 * This creates a primitive {@link com.ibm.etools.emf.edit.command.SetCommand}.
	 */
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) {

		if (feature == EcorePackage.eINSTANCE.getENamedElement_Name()) {
			return new SetCommand(domain, owner, feature, value) {
				protected Command setTypeCommand;

				public void doExecute() {
					CMPAttribute cmpAttribute = (CMPAttribute) this.owner;
					JavaHelpers type = cmpAttribute.getType();
					super.doExecute();
					if (cmpAttribute.getType() != type) {
						setTypeCommand = SetCommand.create(this.domain, this.owner, EcorePackage.eINSTANCE.getETypedElement_EType(), type);
						if (setTypeCommand.canExecute()) {
							setTypeCommand.execute();
						} else {
							setTypeCommand.dispose();
							setTypeCommand = null;
						}
					}
				}

				public void doUndo() {
					if (setTypeCommand != null) {
						setTypeCommand.undo();
					}
					super.doUndo();
				}

				public void doRedo() {
					super.doRedo();
					if (setTypeCommand != null) {
						setTypeCommand.redo();
					}
				}

				public void doDispose() {
					super.doDispose();
					if (setTypeCommand != null) {
						setTypeCommand.dispose();
					}
				}
			};
		}
		return super.createSetCommand(domain, owner, feature, value);
	}

	/**
	 * This returns the image for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public Object getCreateChildImage(Object object) {
		EObject refObject = (EObject) object;
		return J2EEPlugin.getPlugin().getImage(refObject.eClass().getName() + "CreateContainerManagedEntity"); //$NON-NLS-1$
	}

	/**
	 * This returns the label for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildText(Object object) {
		return EJBProviderLibrariesResourceHandler.getString("Create_ContainerManagedEnt_UI_"); //$NON-NLS-1$ = "Create ContainerManagedEntity"
	}

	/**
	 * This returns the help text for {@link org.eclipse.jst.j2ee.internal.internal.ejb.command.CreateChildCommand}.
	 */
	public String getCreateChildToolTipText(Object object) {
		EObject refObject = (EObject) object;
		return EJBProviderLibrariesResourceHandler.getString("Create_a_child_of_type_Con_UI_") + refObject.eClass().getName() + "."; //$NON-NLS-2$ //$NON-NLS-1$ = "Create a child of type ContainerManagedEntity for the selected "
	}

	/**
	 * This returns CMPAttribute.gif.
	 */
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage((((CMPAttribute) object).isKey()) ? "attributeKey_obj" : "attribute_obj"); //$NON-NLS-2$//$NON-NLS-1$
	}

	/**
	 * This returns the parent of the CMPAttribute.
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

			//			EjbPackage pkg = EjbPackage.eINSTANCE;
			EcorePackage ePackageEcore = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

			/*
			 * // This is for the value feature. // itemPropertyDescriptors.add (new
			 * ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "Value", "The
			 * value property", pkg.getCMPAttribute_Value())); // This is for the isTransient
			 * feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "IsTransient",
			 * "The isTransient property", pkg.getCMPAttribute_IsTransient())); // This is for the
			 * isVolatile feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "IsVolatile",
			 * "The isVolatile property", pkg.getCMPAttribute_IsVolatile())); // This is for the
			 * isChangeable feature. // itemPropertyDescriptors.add (new ItemPropertyDescriptor
			 * (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), "IsChangeable",
			 * "The isChangeable property", pkg.getCMPAttribute_IsChangeable()));
			 */

			// This is for the name feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("Name_UI_"), //$NON-NLS-1$ = "Name"
						EJBProviderLibrariesResourceHandler.getString("The_name_property_UI_"), //$NON-NLS-1$ = "The name property"
						ePackageEcore.getENamedElement_Name(), false, ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

			// This is for the eTypeClassifier feature.
			//
			itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), EJBProviderLibrariesResourceHandler.getString("ETypeClassifier_UI_"), //$NON-NLS-1$ = "ETypeClassifier"
						EJBProviderLibrariesResourceHandler.getString("The_eTypeClassifier_proper_UI_"), //$NON-NLS-1$ = "The eTypeClassifier property"
						ePackageEcore.getETypedElement_EType(), false) {
				public Object getPropertyValue(Object o) {
					CMPAttribute thisCMPAttribute = (CMPAttribute) o;
					return createPropertyValueWrapper(o, thisCMPAttribute.getType());
				}

				public Collection getChoiceOfValues(Object o) {
					CMPAttribute thisCMPAttribute = (CMPAttribute) o;
					Collection result = new ArrayList();
					for (int i = 0; i < choiceOfTypes.length; ++i) {
						result.add(JavaRefFactory.eINSTANCE.reflectType(choiceOfTypes[i], thisCMPAttribute));
					}
					return result;
				}
			});

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
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getString("_UI_CMPAttribute_description_feature"), //$NON-NLS-1$
					getString("_UI_PropertyDescriptor_description", "_UI_CMPAttribute_description_feature", "_UI_CMPAttribute_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					EjbPackage.eINSTANCE.getCMPAttribute_Description(), true, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
			childrenReferences.add(EjbPackage.eINSTANCE.getCMPAttribute_Descriptions());
		}
		return childrenReferences;
	}

	public String getText(Object object) {

		CMPAttribute cmpAttribute = (CMPAttribute) object;
		JavaHelpers type = cmpAttribute.getType();
		String typeName = (type != null) ? (" : " + type.getQualifiedName()) : ""; //$NON-NLS-2$//$NON-NLS-1$
		return cmpAttribute.getName() + typeName;
	}

	/**
	 * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(CMPAttribute.class)) {
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTION :
			case EjbPackage.CMP_ATTRIBUTE__DESCRIPTIONS :
			case EjbPackage.CMP_ATTRIBUTE__NAME : {
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

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getCMPAttribute_Descriptions(), CommonFactory.eINSTANCE.createDescription()));

		newChildDescriptors.add(createChildParameter(EjbPackage.eINSTANCE.getCMPAttribute_Descriptions(), WscommonFactory.eINSTANCE.createDescriptionType()));
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

	public void setTarget(Notifier target) {
		if (targets == null || !targets.contains(target))
			super.setTarget(target);
		if (target != null && target instanceof CMPAttribute) {
			if (targets != null && targets.contains(target) || ((CMPAttribute) target).eContainer() == null)
				return;
			Notifier container = ((CMPAttribute) target).eContainer();
			if (container != null && !container.eAdapters().contains(this)) {
				container.eAdapters().add(this);
				super.setTarget(container);
			}
		}
	}


	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getElements(Object)
	 */
	public Collection getElements(Object object) {
		return super.getElements(object);
	}

	/**
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(Object)
	 */
	public Collection getChildren(Object object) {
		try {
			return super.getChildren(object);
		} catch (Exception e) {
			//Ignore
		}
		return Collections.EMPTY_LIST;
	}

	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
}