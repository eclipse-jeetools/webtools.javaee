/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.beaninfo;
/*
 *  $RCSfile: TestReflection.java,v $
 *  $Revision: 1.10 $  $Date: 2007/03/28 02:43:45 $ 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jem.internal.beaninfo.PropertyDecorator;
import org.eclipse.jem.internal.beaninfo.core.Utilities;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaEvent;
import org.eclipse.jem.java.TypeKind;

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
	
	private List getRealProps(List props) {
		int size = props.size();
		List newList = new ArrayList(size);
		for (int i=0; i<size; i++) {
			EStructuralFeature f = (EStructuralFeature) props.get(i);
			if (Utilities.getPropertyDecorator(f) != null)
				newList.add(f);
		}
		return newList;
	}
	
	public void testInterfacePropertyReflection() {
		// Test the reflection of interfaces with multiple extends on them so that properities are correct.
		JavaClass testPropClass =
			(JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo.test#NotTopGuy"), true); //$NON-NLS-1$

		assertTrue(testPropClass.isInterface());
		
		// Test that we don't pick up the extends stuff
		List props = getRealProps(testPropClass.getProperties());
		// Forgot there may be non-properties properties.
		assertEquals(1, props.size());
		assertEquals("number", ((EStructuralFeature) props.get(0)).getName());
		
		// Test that we pick up the extends stuff
		props = getRealProps(testPropClass.getAllProperties());
		assertEquals(3, props.size());
		List validNames = Arrays.asList(new String[] {"number", "object", "integer"});
		for (Iterator itr = props.iterator(); itr.hasNext();) {
			EStructuralFeature feature = (EStructuralFeature) itr.next();
			assertTrue("Extra feature:"+feature.getName(), validNames.contains(feature.getName()));
		}
	}
	
	public void testInterfaceEventReflection() {
		// Test the reflection of interfaces with multiple extends on them so that events are correct.
		JavaClass testEventClass =
			(JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo.test#NotTopGuy"), true); //$NON-NLS-1$

		assertTrue(testEventClass.isInterface());
		
		// Test that we don't pick up the extends stuff
		List events = testEventClass.getEvents();
		assertTrue(events.isEmpty());
		
		// Test that we pick up the extends stuff
		events = testEventClass.getAllEvents();
		assertEquals(1, events.size());
		assertEquals("test1ClassEvent", ((JavaEvent) events.get(0)).getName());
	}
	
	public void testInterfaceOperationsReflection() {
		// Test the reflection of interfaces with multiple extends on them so that properities are correct.
		JavaClass testOpClass =
			(JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo.test#NotTopGuy"), true); //$NON-NLS-1$

		assertTrue(testOpClass.isInterface());
		
		// Test that we don't pick up the extends stuff
		List ops = testOpClass.getEOperations();
		assertEquals(2, ops.size());
		List validNames = Arrays.asList(new String[] {"getNumber", "setNumber"});
		for (Iterator itr = ops.iterator(); itr.hasNext();) {
			EOperation op = (EOperation) itr.next();
			assertTrue("Extra operation:"+op.getName(), validNames.contains(op.getName()));
		}
		
		
		// Test that we pick up the extends stuff
		ops = testOpClass.getEAllOperations();
		assertEquals(8, ops.size());
		validNames = Arrays.asList(new String[] {"getNumber", "setNumber", "getObject", "setObject", "getInteger", "setInteger", "addTest1ClassEventListener", "removeTest1ClassEventListener"});
		for (Iterator itr = ops.iterator(); itr.hasNext();) {
			EOperation op = (EOperation) itr.next();
			assertTrue("Extra operation:"+op.getName(), validNames.contains(op.getName()));
		}
	}
	
	/**
	 * Test for regression for this bug.
	 * @throws CoreException
	 * @see https://bugs.eclipse.org/bugs/show_bug.cgi?id=153150
	 *
	 */
	/* see https://bugs.eclipse.org/bugs/show_bug.cgi?id=179023 - we need to run HEADLESS tests as HEADLESS, and this test requires UI
	public void test153150() throws CoreException {
		// This tests that within a platform runnable, that if a class is reflected as not found, then is created and source type tested that
		// it will now reflect as found. The source type test forces a flush if the type is now found. However, if didn't do source type test
		// but did just reflection we wouldn't see the new class until after notification. This bug doesn't address that problem.
		
		// Need to have a folder (package) already exist for this test to work. If the package was created at the same time
		// as the class, then it won't be found until after the notification is done. But this fix was for a new class in an
		// existing package.
		IPackageFragment pkg1 = (IPackageFragment) JavaCore.create(nature.getProject()).findElement(new Path("org/eclipse/jem/tests/beaninfo"));
		final IFolder folder = ((IFolder) pkg1.getCorrespondingResource()).getFolder("t153150");
		if (!folder.exists())
			folder.create(true, true, new NullProgressMonitor());

		final ICompilationUnit[] testCU = new ICompilationUnit[1];
		try {
			JavaCore.run(new IWorkspaceRunnable() {
				public void run(IProgressMonitor monitor) throws CoreException {
					JavaClass test1Class =
						(JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo.t153150#Test153150Class"), true); //$NON-NLS-1$
					assertEquals("Should not be defined.", TypeKind.UNDEFINED_LITERAL, test1Class.getKind());

					// Now create the class.
					IPackageFragment pkg = (IPackageFragment) JavaCore.create(folder);
					testCU[0] = pkg.createCompilationUnit("Test153150Class.java", "public class Test153150Class {}", true, new NullProgressMonitor());

					assertEquals("It should still reflect as not found.", TypeKind.UNDEFINED_LITERAL, test1Class.getKind());
					
					// Now do the get source type. This should reset our flags.
					assertTrue("It should have a source type.", test1Class.isExistingType());
					
					// Now it should reflect.
					assertEquals("It should now reflect as found.", TypeKind.CLASS_LITERAL, test1Class.getKind());
				}
			}, new NullProgressMonitor());
		} finally {
			if (testCU[0] != null) {
				testCU[0].delete(true, new NullProgressMonitor());
			}
		}
	}
	*/
	
}
