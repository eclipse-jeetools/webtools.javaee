/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.jem.internal.instantiation.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.jem.internal.instantiation.InstantiationPackage;
import org.eclipse.jem.internal.instantiation.PTInstanceReference;

import org.eclipse.jem.internal.instantiation.base.IJavaInstance;

import org.eclipse.jem.internal.instantiation.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>PT Instance Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.impl.PTInstanceReferenceImpl#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PTInstanceReferenceImpl extends PTExpressionImpl implements PTInstanceReference {
	/**
	 * The cached value of the '{@link #getReference() <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReference()
	 * @generated
	 * @ordered
	 */
	protected IJavaInstance reference = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PTInstanceReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return InstantiationPackage.eINSTANCE.getPTInstanceReference();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IJavaInstance getReference() {
		if (reference != null && reference.eIsProxy()) {
			IJavaInstance oldReference = reference;
			reference = (IJavaInstance)eResolveProxy((InternalEObject)reference);
			if (reference != oldReference) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InstantiationPackage.PT_INSTANCE_REFERENCE__REFERENCE, oldReference, reference));
			}
		}
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IJavaInstance basicGetReference() {
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReference(IJavaInstance newReference) {
		IJavaInstance oldReference = reference;
		reference = newReference;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InstantiationPackage.PT_INSTANCE_REFERENCE__REFERENCE, oldReference, reference));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case InstantiationPackage.PT_INSTANCE_REFERENCE__REFERENCE:
				if (resolve) return getReference();
				return basicGetReference();
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
			case InstantiationPackage.PT_INSTANCE_REFERENCE__REFERENCE:
				setReference((IJavaInstance)newValue);
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
			case InstantiationPackage.PT_INSTANCE_REFERENCE__REFERENCE:
				setReference((IJavaInstance)null);
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
			case InstantiationPackage.PT_INSTANCE_REFERENCE__REFERENCE:
				return reference != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.impl.PTExpressionImpl#accept0(org.eclipse.jem.internal.instantiation.ParseVisitor)
	 */
	protected void accept0(ParseVisitor visitor) {
		visitor.visit(this);
		visitor.endVisit(this);
	}
	
	public String toString() {
		return "InstanceRef{" + reference + "}";
	}

} //PTInstanceReferenceImpl
