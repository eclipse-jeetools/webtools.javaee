package org.eclipse.jem.java.impl;

/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaParameterImpl.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/13 16:16:21 $ 
 */
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EParameterImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaParameterKind;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.Method;

/**
 * @generated
 */
public class JavaParameterImpl extends EParameterImpl implements JavaParameter{

	/**
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

	/**
	 * The default value of the '{@link #getParameterKind() <em>Parameter Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterKind()
	 * @generated
	 * @ordered
	 */
	protected static final JavaParameterKind PARAMETER_KIND_EDEFAULT = JavaParameterKind.IN_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaParameterKind parameterKind = PARAMETER_KIND_EDEFAULT;
	protected JavaParameterImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass()
	{
		return JavaRefPackage.eINSTANCE.getJavaParameter();
	}

	public JavaHelpers getJavaType() {
		return (JavaHelpers)getEType();
	}
  public String getQualifiedName() {
    return (eContainer() instanceof Method) ? ((Method)eContainer()).getName() + "." + this.getName() : this.getName();
  }
	/**
	 * Is this parameter type an array type.
	 */
	public boolean isArray() {
		return getJavaType().isArray();
	}
	/**
	 * Is this a return parameter.
	 */
	public boolean isReturn() {
		return JavaParameterKind.RETURN == getParameterKind().getValue();
	}
	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public boolean isFinal()
	{
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal)
	{
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_PARAMETER__FINAL, oldFinal, final_));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaParameterKind getParameterKind()
	{
		return parameterKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterKind(JavaParameterKind newParameterKind)
	{
		JavaParameterKind oldParameterKind = parameterKind;
		parameterKind = newParameterKind == null ? PARAMETER_KIND_EDEFAULT : newParameterKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND, oldParameterKind, parameterKind));
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_PARAMETER__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case JavaRefPackage.JAVA_PARAMETER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.JAVA_PARAMETER__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case JavaRefPackage.JAVA_PARAMETER__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case JavaRefPackage.JAVA_PARAMETER__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case JavaRefPackage.JAVA_PARAMETER__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case JavaRefPackage.JAVA_PARAMETER__MANY:
				return isMany() != false;
			case JavaRefPackage.JAVA_PARAMETER__REQUIRED:
				return isRequired() != false;
			case JavaRefPackage.JAVA_PARAMETER__ETYPE:
				return eType != null;
			case JavaRefPackage.JAVA_PARAMETER__EOPERATION:
				return getEOperation() != null;
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				return final_ != FINAL_EDEFAULT;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				return parameterKind != PARAMETER_KIND_EDEFAULT;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_PARAMETER__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_PARAMETER__NAME:
				setName((String)newValue);
				return;
			case JavaRefPackage.JAVA_PARAMETER__ORDERED:
				setOrdered(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_PARAMETER__UNIQUE:
				setUnique(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_PARAMETER__LOWER_BOUND:
				setLowerBound(((Integer)newValue).intValue());
				return;
			case JavaRefPackage.JAVA_PARAMETER__UPPER_BOUND:
				setUpperBound(((Integer)newValue).intValue());
				return;
			case JavaRefPackage.JAVA_PARAMETER__ETYPE:
				setEType((EClassifier)newValue);
				return;
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				setFinal(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				setParameterKind((JavaParameterKind)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_PARAMETER__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case JavaRefPackage.JAVA_PARAMETER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PARAMETER__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PARAMETER__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PARAMETER__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PARAMETER__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PARAMETER__ETYPE:
				setEType((EClassifier)null);
				return;
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				setParameterKind(PARAMETER_KIND_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (final: ");
		result.append(final_);
		result.append(", parameterKind: ");
		result.append(parameterKind);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
	{
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.JAVA_PARAMETER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_PARAMETER__EOPERATION:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.JAVA_PARAMETER__EOPERATION, msgs);
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
	{
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.JAVA_PARAMETER__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_PARAMETER__EOPERATION:
					return eBasicSetContainer(null, JavaRefPackage.JAVA_PARAMETER__EOPERATION, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
	{
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case JavaRefPackage.JAVA_PARAMETER__EOPERATION:
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EOPERATION__EPARAMETERS, EOperation.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve)
	{
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.JAVA_PARAMETER__EANNOTATIONS:
				return getEAnnotations();
			case JavaRefPackage.JAVA_PARAMETER__NAME:
				return getName();
			case JavaRefPackage.JAVA_PARAMETER__ORDERED:
				return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_PARAMETER__UNIQUE:
				return isUnique() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_PARAMETER__LOWER_BOUND:
				return new Integer(getLowerBound());
			case JavaRefPackage.JAVA_PARAMETER__UPPER_BOUND:
				return new Integer(getUpperBound());
			case JavaRefPackage.JAVA_PARAMETER__MANY:
				return isMany() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_PARAMETER__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_PARAMETER__ETYPE:
				if (resolve) return getEType();
				return basicGetEType();
			case JavaRefPackage.JAVA_PARAMETER__EOPERATION:
				return getEOperation();
			case JavaRefPackage.JAVA_PARAMETER__FINAL:
				return isFinal() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.JAVA_PARAMETER__PARAMETER_KIND:
				return getParameterKind();
		}
		return eDynamicGet(eFeature, resolve);
	}

}





