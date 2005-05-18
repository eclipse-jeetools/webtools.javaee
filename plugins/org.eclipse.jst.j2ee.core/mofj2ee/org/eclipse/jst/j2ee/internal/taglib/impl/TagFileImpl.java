/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.taglib.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.internal.common.impl.DescriptionGroupImpl;
import org.eclipse.jst.j2ee.internal.taglib.TagFile;
import org.eclipse.jst.j2ee.internal.taglib.TaglibPackage;
import org.eclipse.jst.j2ee.internal.taglib.TldExtension;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tag File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.TagFileImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.TagFileImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.TagFileImpl#getExample <em>Example</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.taglib.impl.TagFileImpl#getTagExtensions <em>Tag Extensions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TagFileImpl extends DescriptionGroupImpl implements TagFile {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExample()
	 * @generated
	 * @ordered
	 */
	protected static final String EXAMPLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExample() <em>Example</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExample()
	 * @generated
	 * @ordered
	 */
	protected String example = EXAMPLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTagExtensions() <em>Tag Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList tagExtensions = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TagFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return TaglibPackage.eINSTANCE.getTagFile();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.TAG_FILE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.TAG_FILE__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExample() {
		return example;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExample(String newExample) {
		String oldExample = example;
		example = newExample;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TaglibPackage.TAG_FILE__EXAMPLE, oldExample, example));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTagExtensions() {
		if (tagExtensions == null) {
			tagExtensions = new EObjectContainmentEList(TldExtension.class, this, TaglibPackage.TAG_FILE__TAG_EXTENSIONS);
		}
		return tagExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case TaglibPackage.TAG_FILE__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case TaglibPackage.TAG_FILE__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case TaglibPackage.TAG_FILE__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case TaglibPackage.TAG_FILE__TAG_EXTENSIONS:
					return ((InternalEList)getTagExtensions()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.TAG_FILE__ICONS:
				return getIcons();
			case TaglibPackage.TAG_FILE__DISPLAY_NAMES:
				return getDisplayNames();
			case TaglibPackage.TAG_FILE__DESCRIPTIONS:
				return getDescriptions();
			case TaglibPackage.TAG_FILE__NAME:
				return getName();
			case TaglibPackage.TAG_FILE__PATH:
				return getPath();
			case TaglibPackage.TAG_FILE__EXAMPLE:
				return getExample();
			case TaglibPackage.TAG_FILE__TAG_EXTENSIONS:
				return getTagExtensions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.TAG_FILE__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case TaglibPackage.TAG_FILE__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case TaglibPackage.TAG_FILE__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case TaglibPackage.TAG_FILE__NAME:
				setName((String)newValue);
				return;
			case TaglibPackage.TAG_FILE__PATH:
				setPath((String)newValue);
				return;
			case TaglibPackage.TAG_FILE__EXAMPLE:
				setExample((String)newValue);
				return;
			case TaglibPackage.TAG_FILE__TAG_EXTENSIONS:
				getTagExtensions().clear();
				getTagExtensions().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.TAG_FILE__ICONS:
				getIcons().clear();
				return;
			case TaglibPackage.TAG_FILE__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case TaglibPackage.TAG_FILE__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case TaglibPackage.TAG_FILE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case TaglibPackage.TAG_FILE__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case TaglibPackage.TAG_FILE__EXAMPLE:
				setExample(EXAMPLE_EDEFAULT);
				return;
			case TaglibPackage.TAG_FILE__TAG_EXTENSIONS:
				getTagExtensions().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case TaglibPackage.TAG_FILE__ICONS:
				return icons != null && !icons.isEmpty();
			case TaglibPackage.TAG_FILE__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case TaglibPackage.TAG_FILE__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case TaglibPackage.TAG_FILE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case TaglibPackage.TAG_FILE__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case TaglibPackage.TAG_FILE__EXAMPLE:
				return EXAMPLE_EDEFAULT == null ? example != null : !EXAMPLE_EDEFAULT.equals(example);
			case TaglibPackage.TAG_FILE__TAG_EXTENSIONS:
				return tagExtensions != null && !tagExtensions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");//$NON-NLS-1$
		result.append(name);
		result.append(", path: ");//$NON-NLS-1$
		result.append(path);
		result.append(", example: ");//$NON-NLS-1$
		result.append(example);
		result.append(')');
		return result.toString();
	}

} //TagFileImpl
