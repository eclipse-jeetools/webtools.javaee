/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaArrayTypeReflectionAdapter.java,v $
 *  $Revision: 1.5 $  $Date: 2005/05/11 22:41:06 $ 
 */
package org.eclipse.jem.internal.java.adapters;

import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.ArrayType;
import org.eclipse.jem.java.InheritanceCycleException;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.impl.ArrayTypeImpl;

/**
 * Array type reflection adapter. Since arrays are very constant we don't need any fancy reflection to the source type (class object). It really
 * doesn't do anything. It is just here so that it exists. Everything is constant or depends on the final component type.
 * 
 * @since 1.0.0
 */
public class JavaArrayTypeReflectionAdapter extends JavaReflectionAdaptor implements IJavaClassAdaptor {

	public JavaArrayTypeReflectionAdapter(Notifier target) {
		super(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor#getReflectionSource()
	 */
	public Object getReflectionSource() {
		return null; // There isn't any for arrays.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor#hasReflectionSource()
	 */
	public boolean hasReflectionSource() {
		// This method is used to determine if valid, so we pass on to use the final component.
		ArrayType jh = (ArrayType) getTarget();
		JavaHelpers fc = jh.getFinalComponentType();
		return (fc.isPrimitive() || ((JavaClass) fc).isExistingType());
	}
	
	public boolean hasCachedReflectionSource() {
		ArrayType jh = (ArrayType) getTarget();
		JavaHelpers fc = jh.getFinalComponentType();
		if(fc.isPrimitive())
		    return true;
		else {
		    JavaReflectionAdaptor reflectionAdaptor = (JavaReflectionAdaptor) EcoreUtil.getExistingAdapter(fc, ReflectionAdaptor.TYPE_KEY);
		    return (reflectionAdaptor != null) ? reflectionAdaptor.hasCachedReflectionSource() : false;		    
		} 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.java.adapters.ReflectionAdaptor#reflectValues()
	 */
	public boolean reflectValues() {
		ArrayTypeImpl at = (ArrayTypeImpl) getTarget();

		// Arrays are always:
		//		final
		//		Supertype of java.lang.Object
		//		implements java.lang.Cloneable, java.io.Serializable
		at.setFinal(true);
		try {
			at.setSupertype((JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.lang.Object", (EObject) getTarget()));
		} catch (InheritanceCycleException e) {
		}
		List list = at.getImplementsInterfacesGen();
		list.add(JavaRefFactory.eINSTANCE.createClassRef("java.lang.Cloneable"));
		list.add(JavaRefFactory.eINSTANCE.createClassRef("java.io.Serializable"));
		return super.reflectValues();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdaptor#flushReflectedValues(boolean)
	 */
	protected boolean flushReflectedValues(boolean clearCachedModelObject) {
		ArrayTypeImpl at = (ArrayTypeImpl) getTarget();
		at.getImplementsInterfacesGen().clear();
		return true;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.IJavaClassAdaptor#isSourceTypeFromBinary()
	 */
	public boolean isSourceTypeFromBinary() {
		return false;
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.IJavaClassAdaptor#reflectFieldsIfNecessary()
	 */
	public synchronized boolean reflectFieldsIfNecessary() {
		return reflectValuesIfNecessary();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.IJavaClassAdaptor#reflectMethodsIfNecessary()
	 */
	public synchronized boolean reflectMethodsIfNecessary() {
		return reflectValuesIfNecessary();
	}

	/*
	 *  (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.IJavaClassAdaptor#sourceTypeExists()
	 */
	public boolean sourceTypeExists() {
		return hasReflectionSource();
	}
}