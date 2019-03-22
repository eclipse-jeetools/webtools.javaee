/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.java.internal.impl;
/*


 */

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.ECrossReferenceEList;

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
		return JavaRefPackage.Literals.JAVA_PACKAGE;
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JavaRefPackage.JAVA_PACKAGE__JAVA_CLASSES:
				return getJavaClasses();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JavaRefPackage.JAVA_PACKAGE__JAVA_CLASSES:
				return !getJavaClasses().isEmpty();
		}
		return super.eIsSet(featureID);
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
	@Override
	  public EList<EObject> eContents()
	  {
	    return EContentsEList.createEContentsEList(this);
	  }

	  @Override
	  public EList<EObject> eCrossReferences()
	  {
	    return ECrossReferenceEList.createECrossReferenceEList(this);
	  }

} //JavaPackageImpl





