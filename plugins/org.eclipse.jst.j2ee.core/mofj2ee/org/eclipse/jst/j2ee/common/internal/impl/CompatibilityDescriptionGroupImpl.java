/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Compatibility Description Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl#getSmallIcon <em>Small Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl#getLargeIcon <em>Large Icon</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl#getDisplayName <em>Display Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CompatibilityDescriptionGroupImpl extends DescriptionGroupImpl implements CompatibilityDescriptionGroup {
	/**
	 * The default value of the '{@link #getSmallIcon() <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSmallIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String SMALL_ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSmallIcon() <em>Small Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSmallIcon()
	 * @generated
	 * @ordered
	 */
	protected String smallIcon = SMALL_ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getLargeIcon() <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String LARGE_ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLargeIcon() <em>Large Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLargeIcon()
	 * @generated
	 * @ordered
	 */
	protected String largeIcon = LARGE_ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected static final String DISPLAY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDisplayName() <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayName()
	 * @generated
	 * @ordered
	 */
	protected String displayName = DISPLAY_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 */
	protected CompatibilityDescriptionGroupImpl() {
		super();
		eAdapters().add(new DescriptionGroupAdapter());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.COMPATIBILITY_DESCRIPTION_GROUP;
	}

	@Override
	public String getSmallIcon() {
		if(eResource() == null) 
			return getSmallIconGen();
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			return getSmallIconGen(); 
		case J2EEVersionConstants.J2EE_1_4_ID :
		default :
			if (getIcons().size() > 0)
				return ((IconType)getIcons().get(0)).getSmallIcon();
		}
		return null;
	}
	
	@Override
	public void setSmallIcon(String newSmallIcon) {
		if(eResource() == null) {
			setSmallIconGen(newSmallIcon);
			internalSetSmallIconInList(newSmallIcon); 
			return;
		}
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			setSmallIconGen(newSmallIcon);
			break;
		case J2EEVersionConstants.J2EE_1_4_ID :
		default : 
			internalSetSmallIconInList(newSmallIcon); 
			break;
		}
	}
	/**
	 * @param newSmallIcon
	 */
	protected void internalSetSmallIconInList(String newSmallIcon) {
		IconType newValue = null;
		if(getIcons().size() > 0) {
			newValue = (IconType) getIcons().get(0);
			newValue.setSmallIcon(newSmallIcon);
		}
		else {
			newValue = CommonFactory.eINSTANCE.createIconType();
			newValue.setSmallIcon(newSmallIcon);
			getIcons().add(newValue); 
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSmallIconGen() {
		return smallIcon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSmallIconGen(String newSmallIcon) {
		String oldSmallIcon = smallIcon;
		smallIcon = newSmallIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON, oldSmallIcon, smallIcon));
	}

	@Override
	public String getLargeIcon() {
		if(eResource() == null) 
			return getLargeIconGen();
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			return getLargeIconGen(); 
		case J2EEVersionConstants.J2EE_1_4_ID :
		default :
			if (getIcons().size() > 0)
				return ((IconType)getIcons().get(0)).getLargeIcon();
		}
		return null;
	}
	
	@Override
	public void setLargeIcon(String newLargeIcon) {
		if(eResource() == null) {
			setLargeIconGen(newLargeIcon);
			internalSetLargeIconInList(newLargeIcon);
			return;
		}
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			setLargeIconGen(newLargeIcon);
			break;
		case J2EEVersionConstants.J2EE_1_4_ID :
		default : 
			internalSetLargeIconInList(newLargeIcon); 
			break;
		}
	}

	/**
	 * @param newLargeIcon
	 */
	protected void internalSetLargeIconInList(String newLargeIcon) {
		IconType newValue = null;
		if(getIcons().size() > 0) {
			newValue = (IconType) getIcons().get(0);
			newValue.setLargeIcon(newLargeIcon);
		}
		else {
			newValue = CommonFactory.eINSTANCE.createIconType();
			newValue.setLargeIcon(newLargeIcon);
			getIcons().add(newValue); 
		}
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLargeIconGen() {
		return largeIcon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLargeIconGen(String newLargeIcon) {
		String oldLargeIcon = largeIcon;
		largeIcon = newLargeIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON, oldLargeIcon, largeIcon));
	}

	@Override
	public String getDescription() {
		if(eResource() == null) 
			return getDescriptionGen();
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			return getDescriptionGen(); 
		case J2EEVersionConstants.J2EE_1_4_ID :
		default :
			if (getDescriptions().size() > 0)
				return ((Description)getDescriptions().get(0)).getValue();
		}
		return null;
	}
	
	@Override
	public void setDescription(String newDescription) {
		if(eResource() == null) {
			setDescriptionGen(newDescription);
			internalSetDescriptionInList(newDescription);
			return;
		}
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			setDescriptionGen(newDescription);
			break;
		case J2EEVersionConstants.J2EE_1_4_ID :
		default :
			internalSetDescriptionInList(newDescription); 
			break;
		}
	}
	
	/**
	 * @param newDescription
	 */
	protected void internalSetDescriptionInList(String newDescription) {
		Description newValue = null;
		if(getDescriptions().size() > 0) {
			newValue = (Description) getDescriptions().get(0);
			newValue.setValue(newDescription);
		}
		else {
			newValue = CommonFactory.eINSTANCE.createDescription();
			newValue.setValue(newDescription);
			getDescriptions().add(newValue);
		}
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescriptionGen() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescriptionGen(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION, oldDescription, description));
	}

	@Override
	public String getDisplayName() {
		if(eResource() == null) 
			return getDisplayNameGen();
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			return getDisplayNameGen(); 
		case J2EEVersionConstants.J2EE_1_4_ID :
		default :
			if (getDisplayNames().size() > 0)
				return ((DisplayName)getDisplayNames().get(0)).getValue();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.common.DescriptionGroup#setDisplayName(java.lang.String)
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		if(eResource() == null) {
			setDisplayNameGen(newDisplayName);
			internalSetDisplayNameInList(newDisplayName);
			return;
		}
		
		switch (getJ2EEVersionID()) {
		case J2EEVersionConstants.J2EE_1_2_ID :
		case J2EEVersionConstants.J2EE_1_3_ID :
			setDisplayNameGen(newDisplayName);
			break;
		case J2EEVersionConstants.J2EE_1_4_ID :
		default :
			internalSetDisplayNameInList(newDisplayName);
			break;
		}
	}

	/**
	 * @param newDisplayName
	 */
	protected void internalSetDisplayNameInList(String newDisplayName) {
		DisplayName newValue = null;
		if(getDisplayNames().size() > 0) {
			newValue = (DisplayName) getDisplayNames().get(0);
			newValue.setValue(newDisplayName);
		}
		else  {
			newValue = CommonFactory.eINSTANCE.createDisplayName();
			newValue.setValue(newDisplayName);
			getDisplayNames().add(newValue);
		}			  
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplayNameGen() {
		return displayName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisplayNameGen(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME, oldDisplayName, displayName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON:
				return getSmallIcon();
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON:
				return getLargeIcon();
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION:
				return getDescription();
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME:
				return getDisplayName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (smallIcon: "); //$NON-NLS-1$
		result.append(smallIcon);
		result.append(", largeIcon: "); //$NON-NLS-1$
		result.append(largeIcon);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(", displayName: "); //$NON-NLS-1$
		result.append(displayName);
		result.append(')');
		return result.toString();
	}

} //CompatibilityDescriptionGroupImpl
