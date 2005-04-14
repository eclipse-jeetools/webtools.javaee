/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.impl;
/*
 *  $RCSfile: FeatureDecoratorImpl.java,v $
 *  $Revision: 1.6 $  $Date: 2005/04/14 19:05:36 $ 
 */


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EAnnotationImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.internal.beaninfo.BeaninfoPackage;
import org.eclipse.jem.internal.beaninfo.FeatureDecorator;
import org.eclipse.jem.internal.beaninfo.ImplicitItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Decorator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getShortDescription <em>Short Description</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isExpert <em>Expert</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isHidden <em>Hidden</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isPreferred <em>Preferred</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isMergeIntrospection <em>Merge Introspection</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#isAttributesExplicitEmpty <em>Attributes Explicit Empty</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getImplicitlySetBits <em>Implicitly Set Bits</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getImplicitDecoratorFlag <em>Implicit Decorator Flag</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.impl.FeatureDecoratorImpl#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureDecoratorImpl extends EAnnotationImpl implements FeatureDecorator{
	
	/**
	 * Bits for implicitly set features. This is internal, not meant for clients.
	 * Note: To make it easier to make changes Feature goes from high bits down, while the
	 * subclasses go from low-bits up. That way if a change is made to add something of
	 * interest to Feature, all of the subclasses don't need to have their bits re-ordered.
	 * Since these are bits, it doesn't matter which ones to use.
	 */
	public static final long FEATURE_DISPLAYNAME_IMPLICIT = 0x8000000000000000L;
	public static final long FEATURE_SHORTDESC_IMPLICIT = 0x4000000000000000L;
	public static final long FEATURE_CATEGORY_IMPLICIT = 0x2000000000000000L;
	public static final long FEATURE_EXPERT_IMPLICIT = 0x1000000000000000L;
	public static final long FEATURE_HIDDEN_IMPLICIT = 0x800000000000000L;
	public static final long FEATURE_PREFERRED_IMPLICIT = 0x400000000000000L;
	public static final long FEATURE_ATTRIBUTES_IMPLICIT = 0x200000000000000L; 
	
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
	 * This is true if the Display Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean displayNameESet = false;

	/**
	 * The default value of the '{@link #getShortDescription() <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String SHORT_DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getShortDescription() <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getShortDescription()
	 * @generated
	 * @ordered
	 */
	protected String shortDescription = SHORT_DESCRIPTION_EDEFAULT;
	/**
	 * This is true if the Short Description attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean shortDescriptionESet = false;

	/**
	 * The default value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategory() <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCategory()
	 * @generated
	 * @ordered
	 */
	protected String category = CATEGORY_EDEFAULT;
	/**
	 * The default value of the '{@link #isExpert() <em>Expert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExpert()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXPERT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isExpert() <em>Expert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExpert()
	 * @generated
	 * @ordered
	 */
	protected boolean expert = EXPERT_EDEFAULT;

	/**
	 * This is true if the Expert attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean expertESet = false;

	/**
	 * The default value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIDDEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHidden() <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHidden()
	 * @generated
	 * @ordered
	 */
	protected boolean hidden = HIDDEN_EDEFAULT;

	/**
	 * This is true if the Hidden attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hiddenESet = false;

	/**
	 * The default value of the '{@link #isPreferred() <em>Preferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferred()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PREFERRED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isPreferred() <em>Preferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPreferred()
	 * @generated
	 * @ordered
	 */
	protected boolean preferred = PREFERRED_EDEFAULT;

	/**
	 * This is true if the Preferred attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean preferredESet = false;

	/**
	 * The default value of the '{@link #isMergeIntrospection() <em>Merge Introspection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeIntrospection()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MERGE_INTROSPECTION_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMergeIntrospection() <em>Merge Introspection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMergeIntrospection()
	 * @generated
	 * @ordered
	 */
	protected boolean mergeIntrospection = MERGE_INTROSPECTION_EDEFAULT;
	/**
	 * The default value of the '{@link #isAttributesExplicitEmpty() <em>Attributes Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAttributesExplicitEmpty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAttributesExplicitEmpty() <em>Attributes Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAttributesExplicitEmpty()
	 * @generated
	 * @ordered
	 */
	protected boolean attributesExplicitEmpty = ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT;

	/**
	 * The default value of the '{@link #getImplicitlySetBits() <em>Implicitly Set Bits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplicitlySetBits()
	 * @generated
	 * @ordered
	 */
	protected static final long IMPLICITLY_SET_BITS_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getImplicitlySetBits() <em>Implicitly Set Bits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplicitlySetBits()
	 * @generated
	 * @ordered
	 */
	protected long implicitlySetBits = IMPLICITLY_SET_BITS_EDEFAULT;

	/**
	 * The default value of the '{@link #getImplicitDecoratorFlag() <em>Implicit Decorator Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplicitDecoratorFlag()
	 * @generated
	 * @ordered
	 */
	protected static final ImplicitItem IMPLICIT_DECORATOR_FLAG_EDEFAULT = ImplicitItem.NOT_IMPLICIT_LITERAL;

	/**
	 * The cached value of the '{@link #getImplicitDecoratorFlag() <em>Implicit Decorator Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplicitDecoratorFlag()
	 * @generated
	 * @ordered
	 */
	protected ImplicitItem implicitDecoratorFlag = IMPLICIT_DECORATOR_FLAG_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EMap attributes = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected FeatureDecoratorImpl() {
		super();
		setSource(this.getClass().getName());		
	}
	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return BeaninfoPackage.eINSTANCE.getFeatureDecorator();
	}

	public String getDisplayName() {
		return isSetDisplayName() ? getDisplayNameGen() : getName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDisplayNameGen() {
		return displayName;
	}

	public String getName() {
		ENamedElement ne = (ENamedElement) getEModelElement();
		if (ne != null)
			return ne.getName();	// The name from the owner of the feature has to be the name of feature.
		else
			return "?";	// Don't know what it is. //$NON-NLS-1$
	}
	


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayName(String newDisplayName) {
		String oldDisplayName = displayName;
		displayName = newDisplayName;
		boolean oldDisplayNameESet = displayNameESet;
		displayNameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME, oldDisplayName, displayName, !oldDisplayNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDisplayName() {
		String oldDisplayName = displayName;
		boolean oldDisplayNameESet = displayNameESet;
		displayName = DISPLAY_NAME_EDEFAULT;
		displayNameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME, oldDisplayName, DISPLAY_NAME_EDEFAULT, oldDisplayNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDisplayName() {
		return displayNameESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShortDescription(String newShortDescription) {
		String oldShortDescription = shortDescription;
		shortDescription = newShortDescription;
		boolean oldShortDescriptionESet = shortDescriptionESet;
		shortDescriptionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION, oldShortDescription, shortDescription, !oldShortDescriptionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetShortDescription() {
		String oldShortDescription = shortDescription;
		boolean oldShortDescriptionESet = shortDescriptionESet;
		shortDescription = SHORT_DESCRIPTION_EDEFAULT;
		shortDescriptionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION, oldShortDescription, SHORT_DESCRIPTION_EDEFAULT, oldShortDescriptionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetShortDescription() {
		return shortDescriptionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCategory(String newCategory) {
		String oldCategory = category;
		category = newCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__CATEGORY, oldCategory, category));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExpert() {
		return expert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpert(boolean newExpert) {
		boolean oldExpert = expert;
		expert = newExpert;
		boolean oldExpertESet = expertESet;
		expertESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__EXPERT, oldExpert, expert, !oldExpertESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetExpert() {
		boolean oldExpert = expert;
		boolean oldExpertESet = expertESet;
		expert = EXPERT_EDEFAULT;
		expertESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__EXPERT, oldExpert, EXPERT_EDEFAULT, oldExpertESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetExpert() {
		return expertESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHidden(boolean newHidden) {
		boolean oldHidden = hidden;
		hidden = newHidden;
		boolean oldHiddenESet = hiddenESet;
		hiddenESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__HIDDEN, oldHidden, hidden, !oldHiddenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHidden() {
		boolean oldHidden = hidden;
		boolean oldHiddenESet = hiddenESet;
		hidden = HIDDEN_EDEFAULT;
		hiddenESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__HIDDEN, oldHidden, HIDDEN_EDEFAULT, oldHiddenESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHidden() {
		return hiddenESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPreferred() {
		return preferred;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferred(boolean newPreferred) {
		boolean oldPreferred = preferred;
		preferred = newPreferred;
		boolean oldPreferredESet = preferredESet;
		preferredESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__PREFERRED, oldPreferred, preferred, !oldPreferredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPreferred() {
		boolean oldPreferred = preferred;
		boolean oldPreferredESet = preferredESet;
		preferred = PREFERRED_EDEFAULT;
		preferredESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, BeaninfoPackage.FEATURE_DECORATOR__PREFERRED, oldPreferred, PREFERRED_EDEFAULT, oldPreferredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPreferred() {
		return preferredESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMergeIntrospection() {
		return mergeIntrospection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMergeIntrospection(boolean newMergeIntrospection) {
		boolean oldMergeIntrospection = mergeIntrospection;
		mergeIntrospection = newMergeIntrospection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION, oldMergeIntrospection, mergeIntrospection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAttributesExplicitEmpty() {
		return attributesExplicitEmpty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAttributesExplicitEmpty(boolean newAttributesExplicitEmpty) {
		boolean oldAttributesExplicitEmpty = attributesExplicitEmpty;
		attributesExplicitEmpty = newAttributesExplicitEmpty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY, oldAttributesExplicitEmpty, attributesExplicitEmpty));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getImplicitlySetBits() {
		return implicitlySetBits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplicitlySetBits(long newImplicitlySetBits) {
		long oldImplicitlySetBits = implicitlySetBits;
		implicitlySetBits = newImplicitlySetBits;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__IMPLICITLY_SET_BITS, oldImplicitlySetBits, implicitlySetBits));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplicitItem getImplicitDecoratorFlag() {
		return implicitDecoratorFlag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplicitDecoratorFlag(ImplicitItem newImplicitDecoratorFlag) {
		ImplicitItem oldImplicitDecoratorFlag = implicitDecoratorFlag;
		implicitDecoratorFlag = newImplicitDecoratorFlag == null ? IMPLICIT_DECORATOR_FLAG_EDEFAULT : newImplicitDecoratorFlag;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BeaninfoPackage.FEATURE_DECORATOR__IMPLICIT_DECORATOR_FLAG, oldImplicitDecoratorFlag, implicitDecoratorFlag));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap getAttributes() {
		if (attributes == null) {
			attributes = new EcoreEMap(BeaninfoPackage.eINSTANCE.getFeatureAttributeMapEntry(), FeatureAttributeMapEntryImpl.class, this, BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (displayName: ");
		if (displayNameESet) result.append(displayName); else result.append("<unset>");
		result.append(", shortDescription: ");
		if (shortDescriptionESet) result.append(shortDescription); else result.append("<unset>");
		result.append(", category: ");
		result.append(category);
		result.append(", expert: ");
		if (expertESet) result.append(expert); else result.append("<unset>");
		result.append(", hidden: ");
		if (hiddenESet) result.append(hidden); else result.append("<unset>");
		result.append(", preferred: ");
		if (preferredESet) result.append(preferred); else result.append("<unset>");
		result.append(", mergeIntrospection: ");
		result.append(mergeIntrospection);
		result.append(", attributesExplicitEmpty: ");
		result.append(attributesExplicitEmpty);
		result.append(", implicitlySetBits: ");
		result.append(implicitlySetBits);
		result.append(", implicitDecoratorFlag: ");
		result.append(implicitDecoratorFlag);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
					return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
					return eBasicSetContainer(null, BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
					return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
				case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
					return ((InternalEList)getAttributes()).basicRemove(otherEnd, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
					return eContainer.eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				return getEAnnotations();
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				return getSource();
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				return getDetails();
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement();
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				return getContents();
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				return getReferences();
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				return getDisplayName();
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				return getShortDescription();
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				return getCategory();
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				return isExpert() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				return isHidden() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				return isPreferred() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				return isMergeIntrospection() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return isAttributesExplicitEmpty() ? Boolean.TRUE : Boolean.FALSE;
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICITLY_SET_BITS:
				return new Long(getImplicitlySetBits());
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return getImplicitDecoratorFlag();
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				return getAttributes();
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
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				setSource((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				getDetails().clear();
				getDetails().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				getContents().clear();
				getContents().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				getReferences().clear();
				getReferences().addAll((Collection)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				setShortDescription((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				setCategory((String)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				setExpert(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				setHidden(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				setPreferred(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(((Boolean)newValue).booleanValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(((Long)newValue).longValue());
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag((ImplicitItem)newValue);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				getAttributes().addAll((Collection)newValue);
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
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				getDetails().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				setEModelElement((EModelElement)null);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				getContents().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				getReferences().clear();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				unsetDisplayName();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				unsetShortDescription();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				setCategory(CATEGORY_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				unsetExpert();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				unsetHidden();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				unsetPreferred();
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				setMergeIntrospection(MERGE_INTROSPECTION_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				setAttributesExplicitEmpty(ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICITLY_SET_BITS:
				setImplicitlySetBits(IMPLICITLY_SET_BITS_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				setImplicitDecoratorFlag(IMPLICIT_DECORATOR_FLAG_EDEFAULT);
				return;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				getAttributes().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * Called by overrides to eIsSet to test if source is set. This is because for the 
	 * FeatureDecorator and subclasses, setting source to the classname is considered
	 * to be not set since that is the new default for each class level. By doing this
	 * when serializing it won't waste space and time adding a copy of the source string
	 * to the serialized output and then creating a NEW copy on each decorator loaded
	 * from an XMI file. 
	 * 
	 * @return <code>true</code> if source is not null and not equal to class name.
	 * 
	 * @since 1.1.0
	 */
	protected boolean isSourceSet() {
		return source != null && !getClass().getName().equals(source);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.EObject#eIsSet(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				return isSourceSet();	// Override so that if set to the same as classname, then it is considered not set.
			default:
				return eIsSetGen(eFeature);
		}
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSetGen(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case BeaninfoPackage.FEATURE_DECORATOR__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__SOURCE:
				return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
			case BeaninfoPackage.FEATURE_DECORATOR__DETAILS:
				return details != null && !details.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__EMODEL_ELEMENT:
				return getEModelElement() != null;
			case BeaninfoPackage.FEATURE_DECORATOR__CONTENTS:
				return contents != null && !contents.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__REFERENCES:
				return references != null && !references.isEmpty();
			case BeaninfoPackage.FEATURE_DECORATOR__DISPLAY_NAME:
				return isSetDisplayName();
			case BeaninfoPackage.FEATURE_DECORATOR__SHORT_DESCRIPTION:
				return isSetShortDescription();
			case BeaninfoPackage.FEATURE_DECORATOR__CATEGORY:
				return CATEGORY_EDEFAULT == null ? category != null : !CATEGORY_EDEFAULT.equals(category);
			case BeaninfoPackage.FEATURE_DECORATOR__EXPERT:
				return isSetExpert();
			case BeaninfoPackage.FEATURE_DECORATOR__HIDDEN:
				return isSetHidden();
			case BeaninfoPackage.FEATURE_DECORATOR__PREFERRED:
				return isSetPreferred();
			case BeaninfoPackage.FEATURE_DECORATOR__MERGE_INTROSPECTION:
				return mergeIntrospection != MERGE_INTROSPECTION_EDEFAULT;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES_EXPLICIT_EMPTY:
				return attributesExplicitEmpty != ATTRIBUTES_EXPLICIT_EMPTY_EDEFAULT;
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICITLY_SET_BITS:
				return implicitlySetBits != IMPLICITLY_SET_BITS_EDEFAULT;
			case BeaninfoPackage.FEATURE_DECORATOR__IMPLICIT_DECORATOR_FLAG:
				return implicitDecoratorFlag != IMPLICIT_DECORATOR_FLAG_EDEFAULT;
			case BeaninfoPackage.FEATURE_DECORATOR__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

}
