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
package org.eclipse.jem.java.impl;
/*
 *  $RCSfile: JavaPackageImpl.java,v $
 *  $Revision: 1.7 $  $Date: 2005/04/14 19:05:33 $ 
 */

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jem.java.JavaPackage;
import org.eclipse.jem.java.JavaRefPackage;

import org.eclipse.jem.java.*;

public class JavaPackageImpl extends EPackageImpl implements JavaPackage, EPackage {
		/**
		 * @generated This field/method will be replaced during code generation.
		 */
	protected JavaPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaRefPackage.eINSTANCE.getJavaPackage();
	}

	/**
	 * Get the list of classes that this package contains.
	 * Since this is a derived relationship, we need to implement
	 * it here. It will get the metaobjects list. These are the
	 * java classes this package contains.
	 *
	 * If this is the primitives package, then it must return
	 * an empty list because it doesn't contain any classes.
	 */
	public EList getJavaClasses() {
		return !PRIMITIVE_PACKAGE_NAME.equals(getName()) ?
			ECollections.unmodifiableEList(getEClassifiers()) :
			ECollections.EMPTY_ELIST;
	}
/**
 * Return the name for this package.
 * We do not want to expose the .javaprim package
 * since this is the name of the default package.
 */
public String getName() {
	if (isDefault())
		return "";
	else
		return super.getName();
}
	public String getPackageName() {

	String internalName = super.getName() ;
	return JavaPackage.PRIMITIVE_PACKAGE_NAME.equals(internalName) ? "" : internalName ;	
}
protected boolean isDefault() {
	return JavaPackage.PRIMITIVE_PACKAGE_NAME.equals(super.getName());	
}
	/**
	 * Since classes are loaded dynamically and not from a schema, a
	 * class could be asked for through this method, and if not yet reflected,
	 * it wouldn't be found. We need to make sure that any class asked for
	 * in this package is found (i.e. poofed up). Otherwise loading an instance
	 * document that refers to java class through namespaces won't be found.
	 */
	public EClassifier getEClassifier(String className) {			
		// Try to get the class from the resource that this package is in.
		// This will create it if not found. This works because the
		// structure is there is one java package per resource, and
		// the id of the class is the class name without the package
		// portion, which is what className above is.
		Object result = eResource().getEObject(className);
		return (result instanceof EClassifier) ? (EClassifier) result : null;
	}
	
	/**
	 * reflect - reflect a Java package for a given package name.
	 * If the package does not exist, one will be created through
	 * the reflection mechanism.
	 * @deprecated
	 * @see org.eclipse.jem.java.JavaRefFactory#reflectPackage(java.lang.String, org.eclipse.emf.ecore.resource.ResourceSet) 
	 */
	public static JavaPackage reflect(String packageName, ResourceSet set) {
		return JavaRefFactory.eINSTANCE.reflectPackage(packageName, set);
    }

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JavaRefPackage.JAVA_PACKAGE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_PACKAGE__EFACTORY_INSTANCE:
					if (eFactoryInstance != null)
						msgs = ((InternalEObject)eFactoryInstance).eInverseRemove(this, EcorePackage.EFACTORY__EPACKAGE, EFactory.class, msgs);
					return basicSetEFactoryInstance((EFactory)otherEnd, msgs);
				case JavaRefPackage.JAVA_PACKAGE__ECLASSIFIERS:
					return ((InternalEList)getEClassifiers()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_PACKAGE__ESUBPACKAGES:
					return ((InternalEList)getESubpackages()).basicAdd(otherEnd, msgs);
				case JavaRefPackage.JAVA_PACKAGE__ESUPER_PACKAGE:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, JavaRefPackage.JAVA_PACKAGE__ESUPER_PACKAGE, msgs);
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
				case JavaRefPackage.JAVA_PACKAGE__EANNOTATIONS:
					return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_PACKAGE__EFACTORY_INSTANCE:
					return basicSetEFactoryInstance(null, msgs);
				case JavaRefPackage.JAVA_PACKAGE__ECLASSIFIERS:
					return ((InternalEList)getEClassifiers()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_PACKAGE__ESUBPACKAGES:
					return ((InternalEList)getESubpackages()).basicRemove(otherEnd, msgs);
				case JavaRefPackage.JAVA_PACKAGE__ESUPER_PACKAGE:
					return eBasicSetContainer(null, JavaRefPackage.JAVA_PACKAGE__ESUPER_PACKAGE, msgs);
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
				case JavaRefPackage.JAVA_PACKAGE__ESUPER_PACKAGE:
					return eContainer.eInverseRemove(this, EcorePackage.EPACKAGE__ESUBPACKAGES, EPackage.class, msgs);
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
			case JavaRefPackage.JAVA_PACKAGE__EANNOTATIONS:
				return getEAnnotations();
			case JavaRefPackage.JAVA_PACKAGE__NAME:
				return getName();
			case JavaRefPackage.JAVA_PACKAGE__NS_URI:
				return getNsURI();
			case JavaRefPackage.JAVA_PACKAGE__NS_PREFIX:
				return getNsPrefix();
			case JavaRefPackage.JAVA_PACKAGE__EFACTORY_INSTANCE:
				return getEFactoryInstance();
			case JavaRefPackage.JAVA_PACKAGE__ECLASSIFIERS:
				return getEClassifiers();
			case JavaRefPackage.JAVA_PACKAGE__ESUBPACKAGES:
				return getESubpackages();
			case JavaRefPackage.JAVA_PACKAGE__ESUPER_PACKAGE:
				return getESuperPackage();
			case JavaRefPackage.JAVA_PACKAGE__JAVA_CLASSES:
				return getJavaClasses();
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
			case JavaRefPackage.JAVA_PACKAGE__EANNOTATIONS:
				getEAnnotations().clear();
				getEAnnotations().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_PACKAGE__NAME:
				setName((String)newValue);
				return;
			case JavaRefPackage.JAVA_PACKAGE__NS_URI:
				setNsURI((String)newValue);
				return;
			case JavaRefPackage.JAVA_PACKAGE__NS_PREFIX:
				setNsPrefix((String)newValue);
				return;
			case JavaRefPackage.JAVA_PACKAGE__EFACTORY_INSTANCE:
				setEFactoryInstance((EFactory)newValue);
				return;
			case JavaRefPackage.JAVA_PACKAGE__ECLASSIFIERS:
				getEClassifiers().clear();
				getEClassifiers().addAll((Collection)newValue);
				return;
			case JavaRefPackage.JAVA_PACKAGE__ESUBPACKAGES:
				getESubpackages().clear();
				getESubpackages().addAll((Collection)newValue);
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
			case JavaRefPackage.JAVA_PACKAGE__EANNOTATIONS:
				getEAnnotations().clear();
				return;
			case JavaRefPackage.JAVA_PACKAGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PACKAGE__NS_URI:
				setNsURI(NS_URI_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PACKAGE__NS_PREFIX:
				setNsPrefix(NS_PREFIX_EDEFAULT);
				return;
			case JavaRefPackage.JAVA_PACKAGE__EFACTORY_INSTANCE:
				setEFactoryInstance((EFactory)null);
				return;
			case JavaRefPackage.JAVA_PACKAGE__ECLASSIFIERS:
				getEClassifiers().clear();
				return;
			case JavaRefPackage.JAVA_PACKAGE__ESUBPACKAGES:
				getESubpackages().clear();
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
			case JavaRefPackage.JAVA_PACKAGE__EANNOTATIONS:
				return eAnnotations != null && !eAnnotations.isEmpty();
			case JavaRefPackage.JAVA_PACKAGE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case JavaRefPackage.JAVA_PACKAGE__NS_URI:
				return NS_URI_EDEFAULT == null ? nsURI != null : !NS_URI_EDEFAULT.equals(nsURI);
			case JavaRefPackage.JAVA_PACKAGE__NS_PREFIX:
				return NS_PREFIX_EDEFAULT == null ? nsPrefix != null : !NS_PREFIX_EDEFAULT.equals(nsPrefix);
			case JavaRefPackage.JAVA_PACKAGE__EFACTORY_INSTANCE:
				return eFactoryInstance != null;
			case JavaRefPackage.JAVA_PACKAGE__ECLASSIFIERS:
				return eClassifiers != null && !eClassifiers.isEmpty();
			case JavaRefPackage.JAVA_PACKAGE__ESUBPACKAGES:
				return eSubpackages != null && !eSubpackages.isEmpty();
			case JavaRefPackage.JAVA_PACKAGE__ESUPER_PACKAGE:
				return getESuperPackage() != null;
			case JavaRefPackage.JAVA_PACKAGE__JAVA_CLASSES:
				return !getJavaClasses().isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //JavaPackageImpl





