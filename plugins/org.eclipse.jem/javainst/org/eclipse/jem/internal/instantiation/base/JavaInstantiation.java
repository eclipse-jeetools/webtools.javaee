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
package org.eclipse.jem.internal.instantiation.base;
/*
 *  $RCSfile: JavaInstantiation.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/15 22:36:09 $ 
 */

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.internal.java.instantiation.IInstantiationHandlerFactoryAdapter;

/**
 * This class is used to initialize java model instantiation code and to access dynamic features.
 */
public class JavaInstantiation {
	
	
	public static final String ALLOCATION = "allocation";  //$NON-NLS-1$

	/**
	 * Get a structural feature from an instance of an IJavaObjectInstance, where the feature
	 * is specified by its name.
	 */
	public static EStructuralFeature getSFeature(IJavaObjectInstance jobject, String featureName) {
		return jobject.eClass().getEStructuralFeature(featureName);
	}
	
	/**
	 * Get a reference from an instance of an IJavaObjectInstance, where the feature
	 * is specified by its name.
	 */
	public static EReference getReference(IJavaObjectInstance jobject, String featureName) {
		return (EReference) getSFeature(jobject, featureName);
	}	
	
	/**
	 * Get a structural feature from an instance of an IJavaObjectInstance, where the feature
	 * is specified by its URI (e.g. "java:/java.lang#Object/class").
	 */
	public static EStructuralFeature getSFeature(IJavaObjectInstance jobject, URI sfURI) {
		return getSFeature((JavaClass) jobject.getJavaType(), sfURI);
	}

	/**
	 * Get a structural feature from a JavaClass, where the feature
	 * is specified by its URI (e.g. "java:/java.lang#Object/class").
	 */
	public static EStructuralFeature getSFeature(JavaClass jclass, URI sfURI) {
		return getSFeature(jclass.eResource().getResourceSet(), sfURI);
	}
	
	/**
	 * Get a structural feature from a ResourceSet, where the feature
	 * is specified by its URI (e.g. "java:/java.lang#Object/class").
	 */
	public static EStructuralFeature getSFeature(ResourceSet rset, URI sfURI) {
		return (EStructuralFeature) rset.getEObject(sfURI, true);
	}	
	
	/**
	 * Get a reference from an instance of an IJavaObjectInstance, where the feature
	 * is specified by its URI (e.g. "java:/java.lang#Object/class").
	 */
	public static EReference getReference(IJavaObjectInstance jobject, URI sfURI) {
		return (EReference) getSFeature((JavaClass) jobject.getJavaType(), sfURI);
	}

	/**
	 * Get a reference from a JavaClass, where the feature
	 * is specified by its URI (e.g. "java:/java.lang#Object/class").
	 */
	public static EReference getReference(JavaClass jclass, URI sfURI) {
		return (EReference) getSFeature(jclass.eResource().getResourceSet(), sfURI);
	}
	
	/**
	 * Get a reference from a ResourceSet, where the feature
	 * is specified by its URI (e.g. "java:/java.lang#Object/class").
	 */
	public static EReference getReference(ResourceSet rset, URI sfURI) {
		return (EReference) rset.getEObject(sfURI, true);
	}	
	
	/**
	 * Get the allocation Feature for this object. Since it depends
	 * on what resource that the metaclass is defined in, we must look for it.
	 */
	public static EReference getAllocationFeature(IJavaInstance jinstance) {
		return (EReference) jinstance.eClass().getEStructuralFeature(ALLOCATION);
	}
			
	public static void initialize(ResourceSet rset) {
			if (EcoreUtil.getExistingAdapter(rset, IInstantiationHandlerFactoryAdapter.ADAPTER_KEY) == null)
				rset.eAdapters().add(new JavaInstantiationHandlerFactoryAdapter());
	}
}
