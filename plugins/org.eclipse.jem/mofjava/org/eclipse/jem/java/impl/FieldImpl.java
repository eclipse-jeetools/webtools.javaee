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
 *  $RCSfile: FieldImpl.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/13 16:25:08 $ 
 */
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EAttributeImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.java.Block;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.JavaURL;
import org.eclipse.jem.java.JavaVisibilityKind;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;

/**
 * @generated
 */
public class FieldImpl extends EAttributeImpl implements Field{

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
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
	 * The default value of the '{@link #getJavaVisibility() <em>Java Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final JavaVisibilityKind JAVA_VISIBILITY_EDEFAULT = JavaVisibilityKind.PUBLIC_LITERAL;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected JavaVisibilityKind javaVisibility = JAVA_VISIBILITY_EDEFAULT;
	/**
	 * The cached value of the '{@link #getInitializer() <em>Initializer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitializer()
	 * @generated
	 * @ordered
	 */
	protected Block initializer = null;

	protected FieldImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.eINSTANCE.getField();
	}

	/**
	 * createFieldRef - return a JavaURL reference to the named field in the named Java class
	 * 		in the form "package.class_field"
	 */
  public static Field createFieldRef(String className, String fieldName) {
    Field ref = JavaRefFactoryImpl.getActiveFactory().createField();
    JavaURL javaurl = new JavaURL(className + "/" + fieldName);
    ((InternalEObject) ref).eSetProxyURI(URI.createURI(javaurl.getFullString()));
    return ref;    
  }
	/**
	 * Get the class that this field is within.
	 */
	public JavaClass getContainingJavaClass() {
		return (JavaClass) this.getJavaClass();
	}
	/**
	 * Overrides to perform lazy initializations/reflection.
	 */
	public EClassifier getEType() {
    if (!hasReflected) reflectValues();
    return super.getEType();
  }
  public Block getInitializer() {
    if (!hasReflected) reflectValues();
    return getInitializerGen();
  }
  public boolean isFinal() {
    if (!hasReflected) reflectValues();
    return isFinalGen();
  }
  public boolean isStatic() {
    if (!hasReflected) reflectValues();
    return isStaticGen();
  }
	public JavaHelpers getJavaType() {
		return (JavaHelpers)getEType();
	}
 public JavaVisibilityKind getJavaVisibility() {
    if (!hasReflected) reflectValues();
    return getJavaVisibilityGen();
  }
protected ReadAdaptor getReadAdaptor() {
    return (ReadAdaptor)EcoreUtil.getRegisteredAdapter(this, ReadAdaptor.TYPE_KEY);
  }

//FB   protected Object getReadAdaptorValue(EObject attribute) {
//FB     if (getReadAdaptor() != null)
//FB       return readAdaptor.getValueIn(this, attribute);
//FB     return null;
//FB   }

//FB BEGIN
  protected boolean hasReflected = false;

  protected void reflectValues()
  {
    ReadAdaptor readAdaptor = getReadAdaptor();
    if (readAdaptor != null) hasReflected = readAdaptor.reflectValuesIfNecessary();
  }

	/**
	 * Is this field an array type.
	 */
	public boolean isArray() {
		return getJavaType().isArray();
	}

	/**
	 * Overridden to prevent the reflection of the class.
	 */
  public EList eContents() {
    EList results = new BasicEList();
//FB  
//FB    EList containments = eClass().getEAllContainments();
//FB    if (containments != null) {
//FB      Iterator i = containments.iterator();
//FB      while (i.hasNext()) {
//FB        EStructuralFeature sf = (EStructuralFeature) i.next();
//FB        //Change from super to primRefValue
//FB        Object value = primRefValue(sf);
//FB        //EndChange
//FB        if (value != null)
//FB          if (sf.isMany())
//FB            results.addAll((Collection) value);
//FB          else
//FB            results.add(value);
//FB      }
//FB    }
    if (getInitializerGen() != null) results.add(getInitializerGen()); //FB
    return results;
  }

	public String toString() {
		return getClass().getName() + " " + "(" + getName() + ")";
	}
	/**
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public JavaVisibilityKind getJavaVisibilityGen() {
		return javaVisibility;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public boolean isFinalGen() {
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.FIELD__FINAL, oldFinal, final_));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public boolean isStaticGen() {
		return static_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {
		boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.FIELD__STATIC, oldStatic, static_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaVisibility(JavaVisibilityKind newJavaVisibility) {
		JavaVisibilityKind oldJavaVisibility = javaVisibility;
		javaVisibility = newJavaVisibility == null ? JAVA_VISIBILITY_EDEFAULT : newJavaVisibility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.FIELD__JAVA_VISIBILITY, oldJavaVisibility, javaVisibility));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public JavaClass getJavaClass() {
		if (eContainerFeatureID != JavaRefPackage.FIELD__JAVA_CLASS) return null;
		return (JavaClass)eContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJavaClass(JavaClass newJavaClass) {
		if (newJavaClass != eContainer || (eContainerFeatureID != JavaRefPackage.FIELD__JAVA_CLASS && newJavaClass != null)) {
			if (EcoreUtil.isAncestor(this, newJavaClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eContainer != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newJavaClass != null)
				msgs = ((InternalEObject)newJavaClass).eInverseAdd(this, JavaRefPackage.JAVA_CLASS__FIELDS, JavaClass.class, msgs);
			msgs = eBasicSetContainer((InternalEObject)newJavaClass, JavaRefPackage.FIELD__JAVA_CLASS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.FIELD__JAVA_CLASS, newJavaClass, newJavaClass));
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.FIELD__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case JavaRefPackage.FIELD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.FIELD__ORDERED:
				return ordered != ORDERED_EDEFAULT;
			case JavaRefPackage.FIELD__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case JavaRefPackage.FIELD__LOWER_BOUND:
				return lowerBound != LOWER_BOUND_EDEFAULT;
			case JavaRefPackage.FIELD__UPPER_BOUND:
				return upperBound != UPPER_BOUND_EDEFAULT;
			case JavaRefPackage.FIELD__MANY:
				return isMany() != false;
			case JavaRefPackage.FIELD__REQUIRED:
				return isRequired() != false;
			case JavaRefPackage.FIELD__ETYPE:
				return eType != null;
			case JavaRefPackage.FIELD__CHANGEABLE:
				return changeable != CHANGEABLE_EDEFAULT;
			case JavaRefPackage.FIELD__VOLATILE:
				return volatile_ != VOLATILE_EDEFAULT;
			case JavaRefPackage.FIELD__TRANSIENT:
				return transient_ != TRANSIENT_EDEFAULT;
			case JavaRefPackage.FIELD__DEFAULT_VALUE_LITERAL:
				return DEFAULT_VALUE_LITERAL_EDEFAULT == null ? defaultValueLiteral != null : !DEFAULT_VALUE_LITERAL_EDEFAULT.equals(defaultValueLiteral);
			case JavaRefPackage.FIELD__DEFAULT_VALUE:
				return getDefaultValue() != null;
			case JavaRefPackage.FIELD__UNSETTABLE:
				return unsettable != UNSETTABLE_EDEFAULT;
			case JavaRefPackage.FIELD__DERIVED:
				return derived != DERIVED_EDEFAULT;
			case JavaRefPackage.FIELD__ECONTAINING_CLASS:
				return getEContainingClass() != null;
			case JavaRefPackage.FIELD__ID:
				return iD != ID_EDEFAULT;
			case JavaRefPackage.FIELD__EATTRIBUTE_TYPE:
				return basicGetEAttributeType() != null;
			case JavaRefPackage.FIELD__FINAL:
				return final_ != FINAL_EDEFAULT;
			case JavaRefPackage.FIELD__STATIC:
				return static_ != STATIC_EDEFAULT;
			case JavaRefPackage.FIELD__JAVA_VISIBILITY:
				return javaVisibility != JAVA_VISIBILITY_EDEFAULT;
			case JavaRefPackage.FIELD__JAVA_CLASS:
				return getJavaClass() != null;
			case JavaRefPackage.FIELD__INITIALIZER:
				return initializer != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.FIELD__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.FIELD__NAME:
				setName((String)newValue);
				return;
			case JavaRefPackage.FIELD__ORDERED:
				setOrdered(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__UNIQUE:
				setUnique(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__LOWER_BOUND:
				setLowerBound(((Integer)newValue).intValue());
				return;
			case JavaRefPackage.FIELD__UPPER_BOUND:
				setUpperBound(((Integer)newValue).intValue());
				return;
			case JavaRefPackage.FIELD__ETYPE:
				setEType((EClassifier)newValue);
				return;
			case JavaRefPackage.FIELD__CHANGEABLE:
				setChangeable(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__VOLATILE:
				setVolatile(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__TRANSIENT:
				setTransient(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__DEFAULT_VALUE_LITERAL:
				setDefaultValueLiteral((String)newValue);
				return;
			case JavaRefPackage.FIELD__UNSETTABLE:
				setUnsettable(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__DERIVED:
				setDerived(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__ID:
				setID(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__FINAL:
				setFinal(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__STATIC:
				setStatic(((Boolean)newValue).booleanValue());
				return;
			case JavaRefPackage.FIELD__JAVA_VISIBILITY:
				setJavaVisibility((JavaVisibilityKind)newValue);
				return;
			case JavaRefPackage.FIELD__JAVA_CLASS:
				setJavaClass((JavaClass)newValue);
				return;
			case JavaRefPackage.FIELD__INITIALIZER:
				setInitializer((Block)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.FIELD__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case JavaRefPackage.FIELD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__ORDERED:
				setOrdered(ORDERED_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__LOWER_BOUND:
				setLowerBound(LOWER_BOUND_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__UPPER_BOUND:
				setUpperBound(UPPER_BOUND_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__ETYPE:
				setEType((EClassifier)null);
				return;
			case JavaRefPackage.FIELD__CHANGEABLE:
				setChangeable(CHANGEABLE_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__VOLATILE:
				setVolatile(VOLATILE_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__TRANSIENT:
				setTransient(TRANSIENT_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__DEFAULT_VALUE_LITERAL:
				setDefaultValueLiteral(DEFAULT_VALUE_LITERAL_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__UNSETTABLE:
				setUnsettable(UNSETTABLE_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__DERIVED:
				setDerived(DERIVED_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__ID:
				setID(ID_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__STATIC:
				setStatic(STATIC_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__JAVA_VISIBILITY:
				setJavaVisibility(JAVA_VISIBILITY_EDEFAULT);
				return;
			case JavaRefPackage.FIELD__JAVA_CLASS:
				setJavaClass((JavaClass)null);
				return;
			case JavaRefPackage.FIELD__INITIALIZER:
				setInitializer((Block)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public Block getInitializerGen() {
		return initializer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitializer(Block newInitializer, NotificationChain msgs) {
		Block oldInitializer = initializer;
		initializer = newInitializer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaRefPackage.FIELD__INITIALIZER, oldInitializer, newInitializer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitializer(Block newInitializer) {
		if (newInitializer != initializer) {
			NotificationChain msgs = null;
			if (initializer != null)
				msgs = ((InternalEObject)initializer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaRefPackage.FIELD__INITIALIZER, null, msgs);
			if (newInitializer != null)
				msgs = ((InternalEObject)newInitializer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JavaRefPackage.FIELD__INITIALIZER, null, msgs);
			msgs = basicSetInitializer(newInitializer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaRefPackage.FIELD__INITIALIZER, newInitializer, newInitializer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.FIELD__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.FIELD__ECONTAINING_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.FIELD__ECONTAINING_CLASS, msgs);
				case JavaRefPackage.FIELD__JAVA_CLASS:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.FIELD__JAVA_CLASS, msgs);
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
				case JavaRefPackage.FIELD__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.FIELD__ECONTAINING_CLASS:
					return eBasicSetContainer(null, JavaRefPackage.FIELD__ECONTAINING_CLASS, msgs);
				case JavaRefPackage.FIELD__JAVA_CLASS:
					return eBasicSetContainer(null, JavaRefPackage.FIELD__JAVA_CLASS, msgs);
				case JavaRefPackage.FIELD__INITIALIZER:
					return basicSetInitializer(null, msgs);
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
				case JavaRefPackage.FIELD__ECONTAINING_CLASS:
					return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.ECLASS__ESTRUCTURAL_FEATURES, EClass.class, msgs);
				case JavaRefPackage.FIELD__JAVA_CLASS:
					return ((InternalEObject)eContainer).eInverseRemove(this, JavaRefPackage.JAVA_CLASS__FIELDS, JavaClass.class, msgs);
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
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JavaRefPackage.FIELD__EANNOTATIONS:
				return getEAnnotations();
			case JavaRefPackage.FIELD__NAME:
				return getName();
			case JavaRefPackage.FIELD__ORDERED:
				return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__UNIQUE:
				return isUnique() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__LOWER_BOUND:
				return new Integer(getLowerBound());
			case JavaRefPackage.FIELD__UPPER_BOUND:
				return new Integer(getUpperBound());
			case JavaRefPackage.FIELD__MANY:
				return isMany() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__REQUIRED:
				return isRequired() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__ETYPE:
				if (resolve) return getEType();
				return basicGetEType();
			case JavaRefPackage.FIELD__CHANGEABLE:
				return isChangeable() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__VOLATILE:
				return isVolatile() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__TRANSIENT:
				return isTransient() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__DEFAULT_VALUE_LITERAL:
				return getDefaultValueLiteral();
			case JavaRefPackage.FIELD__DEFAULT_VALUE:
				return getDefaultValue();
			case JavaRefPackage.FIELD__UNSETTABLE:
				return isUnsettable() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__DERIVED:
				return isDerived() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__ECONTAINING_CLASS:
				return getEContainingClass();
			case JavaRefPackage.FIELD__ID:
				return isID() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__EATTRIBUTE_TYPE:
				if (resolve) return getEAttributeType();
				return basicGetEAttributeType();
			case JavaRefPackage.FIELD__FINAL:
				return isFinal() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__STATIC:
				return isStatic() ? Boolean.TRUE : Boolean.FALSE;
			case JavaRefPackage.FIELD__JAVA_VISIBILITY:
				return getJavaVisibility();
			case JavaRefPackage.FIELD__JAVA_CLASS:
				return getJavaClass();
			case JavaRefPackage.FIELD__INITIALIZER:
				return getInitializer();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toStringGen() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (final: ");
		result.append(final_);
		result.append(", static: ");
		result.append(static_);
		result.append(", javaVisibility: ");
		result.append(javaVisibility);
		result.append(')');
		return result.toString();
	}

}





