/*
 * Created on Aug 27, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jem.tests.beaninfo;
/*******************************************************************************
 * Copyright (c)  2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.jem.internal.beaninfo.PropertyDecorator;
import org.eclipse.jem.internal.beaninfo.adapters.Utilities;
import org.eclipse.jem.internal.java.JavaClass;
import org.eclipse.jem.internal.java.TypeKind;

/**
 * @author richkulp
 * This one is for testing reflection. It is separate so that
 * test suite can guarentee that it is first.
 */
public class TestReflection extends AbstractBeanInfoTestCase {

	public TestReflection() {
		super();
	}

	public TestReflection(String name) {
		super(name);
	}
	
	public void testReflection() {
		// Test one without a beaninfo. This will do reflection.
		JavaClass test1Class =
			(JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class"), true); //$NON-NLS-1$
		
		assertSame(TypeKind.CLASS_LITERAL, test1Class.getKind());
		
		// Test that the reflection key can find features that aren't yet loaded.
		Object foo = rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class/foo"), true); //$NON-NLS-1$
		assertNotNull(foo);
		
		// Test that the reflection key can find operations that aren't yet loaded.
		Object getFoo =
			rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class/operation/getFoo"), true); //$NON-NLS-1$
		assertNotNull(getFoo);
	}

	public void testInnerReflectionInDefault() {
		// Test that a property of class (through reflection) is an inner class, and the class is resolved correctly.
		JavaClass testInnerClass =
			(JavaClass) rset.getEObject(URI.createURI("java:/#TestDefaultInner"), true); //$NON-NLS-1$
		
		assertSame(TypeKind.CLASS_LITERAL, testInnerClass.getKind());
		
		EList properties = testInnerClass.getProperties();
		assertEquals(1, properties.size());
		
		EStructuralFeature pf = (EStructuralFeature) properties.get(0);
		assertEquals("propertyInner", pf.getName());
		
		PropertyDecorator pd = Utilities.getPropertyDecorator(pf);
		assertNotNull(pd);
		
		JavaClass pdType = (JavaClass) pd.getPropertyType();
		assertEquals("TestDefaultInner$Inner", pdType.getName()); 
		assertSame(TypeKind.CLASS_LITERAL, pdType.getKind());
	}
	
	public void testInnerReflectionInPackage() {
		// Test that a property of class (through reflection) is an inner class, and the class is resolved correctly.
		JavaClass testInnerClass =
			(JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#TestInner"), true); //$NON-NLS-1$
		
		assertSame(TypeKind.CLASS_LITERAL, testInnerClass.getKind());
		
		EList properties = testInnerClass.getProperties();
		assertEquals(1, properties.size());
		
		EStructuralFeature pf = (EStructuralFeature) properties.get(0);
		assertEquals("propertyInner", pf.getName());
		
		PropertyDecorator pd = Utilities.getPropertyDecorator(pf);
		assertNotNull(pd);
		
		JavaClass pdType = (JavaClass) pd.getPropertyType();
		assertEquals("TestInner$Inner", pdType.getName()); 
		assertSame(TypeKind.CLASS_LITERAL, pdType.getKind());
	}
}
