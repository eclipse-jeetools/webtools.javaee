/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
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
 *  $RCSfile: TestStandard.java,v $
 *  $Revision: 1.10 $  $Date: 2005/07/14 14:48:47 $ 
 */

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.jem.internal.beaninfo.*;
import org.eclipse.jem.internal.beaninfo.core.Utilities;

import org.eclipse.jem.java.*;

/**
 * @author richkulp
 *
 * Standard BeanInfo tests.
 */
public class TestStandard extends AbstractBeanInfoTestCase {

	public TestStandard() {
		super();
	}

	public TestStandard(String name) {
		super(name);
	}
		
	public void testArrayClassType() {
		// Test one dimension array type for a class as final component
		JavaClass at = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jem.tests.beaninfo.Test1Class[]", rset);
		
		assertTrue(at.isArray());
		assertSame(TypeKind.CLASS_LITERAL, at.getKind());
		assertEquals(1, ((ArrayType) at).getArrayDimensions());
		assertTrue(at.isPublic());
		assertTrue("java.lang.Object".equals(at.getSupertype().getQualifiedName()));
		assertTrue(at.getImplementsInterfaces().size() == 2);
		assertTrue(at.getMethods().isEmpty());
		assertTrue(at.getFields().isEmpty());
		assertTrue(at.getProperties().isEmpty());
		assertTrue(at.getEvents().isEmpty());
		assertTrue(at.getEOperations().isEmpty());
	}

	public void testArrayPrimitiveType() {
		// Test one dimension array type for a class as final component
		JavaClass at = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("int[]", rset);
		
		assertTrue(at.isArray());
		assertSame(TypeKind.CLASS_LITERAL, at.getKind());
		assertEquals(1, ((ArrayType) at).getArrayDimensions());		
		assertTrue(at.isPublic());
		assertTrue("java.lang.Object".equals(at.getSupertype().getQualifiedName()));
		assertTrue(at.getImplementsInterfaces().size() == 2);
		assertTrue(at.getMethods().isEmpty());
		assertTrue(at.getFields().isEmpty());
		assertTrue(at.getProperties().isEmpty());
		assertTrue(at.getEvents().isEmpty());
		assertTrue(at.getEOperations().isEmpty());
	}

	public void testArrayUndefined() {
		// Test one dimension array type for a class as final component that is undefined.
		JavaClass at = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("XYZ[]", rset);
		
		assertTrue(at.isArray());
		assertSame(TypeKind.UNDEFINED_LITERAL, at.getKind());
	}

	public void testArrayClassType2Dim() {
		// Test one dimension array type for a class as final component
		JavaClass at = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jem.tests.beaninfo.Test1Class[][]", rset);
		
		assertTrue(at.isArray());
		assertSame(TypeKind.CLASS_LITERAL, at.getKind());
		assertEquals(2, ((ArrayType) at).getArrayDimensions());
		assertTrue(at.isPublic());
		assertTrue("java.lang.Object".equals(at.getSupertype().getQualifiedName()));
		assertTrue(at.getImplementsInterfaces().size() == 2);
		assertTrue(at.getMethods().isEmpty());
		assertTrue(at.getFields().isEmpty());
		assertTrue(at.getProperties().isEmpty());
		assertTrue(at.getEvents().isEmpty());
		assertTrue(at.getEOperations().isEmpty());
	}

	public void testArrayPrimitiveType2Dim() {
		// Test one dimension array type for a class as final component
		JavaClass at = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("int[][]", rset);
		
		assertTrue(at.isArray());
		assertSame(TypeKind.CLASS_LITERAL, at.getKind());
		assertEquals(2, ((ArrayType) at).getArrayDimensions());		
		assertTrue(at.isPublic());
		assertTrue("java.lang.Object".equals(at.getSupertype().getQualifiedName()));
		assertTrue(at.getImplementsInterfaces().size() == 2);
		assertTrue(at.getMethods().isEmpty());
		assertTrue(at.getFields().isEmpty());
		assertTrue(at.getProperties().isEmpty());
		assertTrue(at.getEvents().isEmpty());
		assertTrue(at.getEOperations().isEmpty());
	}

	public void testArrayUndefined2Dim() {
		// Test one dimension array type for a class as final component that is undefined.
		JavaClass at = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("XYZ[][]", rset);
		
		assertTrue(at.isArray());
		assertSame(TypeKind.UNDEFINED_LITERAL, at.getKind());
	}

	public void testArrayInnerClassType() {
		// Test one dimension array type for a class as final component
		JavaClass at = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jem.tests.beaninfo.Customer$Account[]", rset);
		
		assertTrue(at.isArray());
		assertSame(TypeKind.CLASS_LITERAL, at.getKind());
		assertEquals(1, ((ArrayType) at).getArrayDimensions());
		assertTrue(at.isPublic());
		assertTrue("java.lang.Object".equals(at.getSupertype().getQualifiedName()));
		assertTrue(at.getImplementsInterfaces().size() == 2);
		assertTrue(at.getMethods().isEmpty());
		assertTrue(at.getFields().isEmpty());
		assertTrue(at.getProperties().isEmpty());
		assertTrue(at.getEvents().isEmpty());
		assertTrue(at.getEOperations().isEmpty());
	}
	
	public void testBeanDecoratorReflected() {
		JavaClass test1Class = getTest1Class();

		// Test the bean decorator.
		BeanDecorator bd = Utilities.getBeanDecorator(test1Class);
		assertEquals("Test1Class", bd.getName()); //$NON-NLS-1$
		assertNull(bd.getCustomizerClass());
	}

	protected JavaClass getTest1Class() {
		JavaClass test1Class =
			(JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class"), true); //$NON-NLS-1$
		return test1Class;
	}
	
	public void testLocalReflectedProperties() {
		EList allLocalFeatures = getTest1Class().getProperties();
		assertEquals(2, allLocalFeatures.size());
		
		Iterator itr = Utilities.getPropertiesIterator(allLocalFeatures);
		boolean found = false;
		while (itr.hasNext()) {
			PropertyDecorator pd = (PropertyDecorator) itr.next();
			if (pd.getName().equals("foo")) { //$NON-NLS-1$
				found = true;
				assertTrue(pd instanceof IndexedPropertyDecorator);
				IndexedPropertyDecorator ip = (IndexedPropertyDecorator) pd;
				JavaHelpers type = (JavaHelpers) ip.getPropertyType();
				assertEquals("java.lang.String[]", type.getQualifiedName());
				if (!type.getQualifiedName().equals("java.lang.String[]")); //$NON-NLS-1$
					
				Method mthd = ip.getReadMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class.getFoo("), true), mthd);
				
				mthd = ip.getWriteMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class.setFoo(java.lang.String[]"), true), mthd);
				
				mthd = ip.getIndexedReadMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class.getFoo(int"), true), mthd);
				
				mthd = ip.getIndexedWriteMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1Class.setFoo(int,java.lang.String"), true), mthd);
				
				break;
			}
		}

		assertTrue(found);		
	}
	
	public void testLocalReflectedOperations() {
		JavaClass test1Class = getTest1Class();
		
		// Test local operations.
		EList allLocalOperations = test1Class.getEOperations();
		assertEquals(10, allLocalOperations.size());
		Iterator itr = allLocalOperations.iterator();
		// This is the desired method that the operation of interest points to.
		Method desired =
			(Method) rset.getEObject(URI.createURI(
				"java:/org.eclipse.jem.tests.beaninfo#Test1Class.setFoo(int,java.lang.String"), true); //$NON-NLS-1$
		boolean found = false;
		while (itr.hasNext()) {
			MethodProxy bhav = (MethodProxy) itr.next();
			if (bhav.getMethod() == desired) {
				found = true;
				List parms = Utilities.getMethodDecorator(bhav).getParameterDescriptors();
				assertEquals(2, parms.size());
				ParameterDecorator pmd = (ParameterDecorator) parms.get(0);
				assertEquals("index", pmd.getName());
				
				break;
			}
		}
		assertTrue(found);		
	}
	
	public void testLocalReflectedEvents() {
		JavaClass test1Class = getTest1Class();
		
		// Test event set reflected.
		List events = test1Class.getEvents();
		assertEquals(2, events.size());
		Iterator itr = events.iterator();
		boolean foundevt1 = false, foundevt2 = false;
		while (itr.hasNext()) {
			JavaEvent event = (JavaEvent) itr.next();
			if ("test1ClassEvent".equals(event.getName())) { //$NON-NLS-1$
				foundevt1 = true;
				EventSetDecorator edec = Utilities.getEventSetDecorator(event);
				List mlist = edec.getListenerMethods();
				assertEquals(2, mlist.size());
			} else if ("test1ClassUnicastEvent".equals(event.getName())) { //$NON-NLS-1$
				foundevt2 = true;
				EventSetDecorator edec = Utilities.getEventSetDecorator(event);
				assertTrue(edec.isUnicast());
			}
		}
		assertTrue(foundevt1);
		assertTrue(foundevt2);
	}
	
	protected JavaClass getTest2ClassB() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2ClassB"), true); //$NON-NLS-1$
	}

	public void testBeanDecoratorIntrospected() {
		// Test one with a beaninfo.
		JavaClass test2ClassB = getTest2ClassB();
		BeanDecorator bd = Utilities.getBeanDecorator(test2ClassB);
		assertEquals("Test2ClassB", bd.getName());
		
		// The following will test if search across beaninfo search path worked. It would fail if it didn't work.
		assertEquals("A name for this class.", bd.getDisplayName()); //$NON-NLS-1$
		assertFalse(bd.isExpert());
		// Customizer class set in BeanInfo, but bogus for now to same class as being introspected.
		assertSame(test2ClassB, bd.getCustomizerClass());
	}
	
	public void testLocalIntrospectedProperties() {
		JavaClass test2ClassB = getTest2ClassB();
		EList allLocalFeatures = test2ClassB.getProperties();
		assertEquals(2, allLocalFeatures.size());
		
		Iterator itr = Utilities.getPropertiesIterator(allLocalFeatures);
		boolean gotSet = false, gotFoo = false;
		while (itr.hasNext()) {
			PropertyDecorator pd = (PropertyDecorator) itr.next();
			Method mthd = null;
			if (pd.getName().equals("setA")) { //$NON-NLS-1$
				gotSet = true;
				mthd = pd.getReadMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2ClassB.isSetA("), true), mthd);
			} else if (pd.getName().equals("foo")) { //$NON-NLS-1$
				gotFoo = true;
				IndexedPropertyDecorator ipd = (IndexedPropertyDecorator) pd;
				mthd = ipd.getIndexedReadMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2ClassB.getFoo(int"), true), mthd);
			}
		}
		assertTrue(gotSet);
		assertTrue(gotFoo);
	}
	
	public void testLocalIntrospectedOperations() {
		JavaClass test2ClassB = getTest2ClassB();
		
		EList allLocalOperations = test2ClassB.getEOperations();
		assertEquals(2, allLocalOperations.size()); 

		boolean found = false;
		Iterator itr = test2ClassB.getEOperations().iterator();
		while (itr.hasNext()) {
			EOperation bhav = (EOperation) itr.next();
			if (bhav.getName().equals("setSetA")) { //$NON-NLS-1$
				List parms = Utilities.getMethodDecorator(bhav).getParameterDescriptors();
				assertEquals(1, parms.size());
				ParameterDecorator pmd = (ParameterDecorator) parms.get(0);
				assertEquals("aBooleanSetting", pmd.getName());
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	public void testLocalIntrospectedEvents() {
		JavaClass test2ClassB = getTest2ClassB();
		
		// Test event set introspected.
		EList events = test2ClassB.getEvents();
		assertEquals(1, events.size());
		JavaEvent event = (JavaEvent) events.get(0);
		assertEquals("overrideName", event.getName());  //$NON-NLS-1$
		EventSetDecorator edec = Utilities.getEventSetDecorator(event);
		assertEquals("ITest1ClassEventListener", edec.getListenerType().getName());
		List mlist = edec.getListenerMethods();
		assertEquals(1, mlist.size());
		MethodProxy mproxy = (MethodProxy) mlist.get(0);
		MethodDecorator mdec = Utilities.getMethodDecorator(mproxy);
		assertEquals("event1", mdec.getName()); //$NON-NLS-1$
		assertEquals("Event 1", mdec.getDisplayName()); //$NON-NLS-1$
	}
	
	public void testSuperTypes() {
		JavaClass testClass = getTest1Class();
		objFeaturesSetup();	// Get number of features for Object. This can vary depending on extensions, so that is why we compute it.
		
		// Now do the testing of merging with super types.
		assertEquals(2+objFeatures, testClass.getAllProperties().size());
		JavaClass test1ClassA = getTest1ClassA(); //$NON-NLS-1$
		assertNotNull(test1ClassA);
		assertEquals(3+objFeatures, test1ClassA.getAllProperties().size());
	}

	protected JavaClass getTest1ClassA() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test1ClassA"), true);
	}
	
	public void testSuperTypesEvents() {
		JavaClass test1ClassA = getTest1ClassA(); 
			
		// Test merging supertypes event set.
		List events = test1ClassA.getAllEvents();
		assertEquals(2, events.size());
		Iterator itr = events.iterator();
		boolean foundevt1 = false;
		boolean foundevt2 = false;
		while (itr.hasNext()) {
			JavaEvent event = (JavaEvent) itr.next();
			if ("test1ClassEvent".equals(event.getName())) { //$NON-NLS-1$
				foundevt1 = true;
				EventSetDecorator edec = Utilities.getEventSetDecorator(event);
				List mlist = edec.getListenerMethods();
				assertEquals(2, mlist.size());
			} else if ("test1ClassUnicastEvent".equals(event.getName())) { //$NON-NLS-1$
				foundevt2 = true;
				EventSetDecorator edec = Utilities.getEventSetDecorator(event);
				assertTrue(edec.isUnicast());
			}
		}
		assertTrue(foundevt1);
		assertTrue(foundevt2);
	}
	
	protected JavaClass getTest2Class() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2Class"), true); //$NON-NLS-1$
	}
	
	protected JavaClass getTest2ClassA() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2ClassA"), true); //$NON-NLS-1$
	}	
	
	public void testSuperTypeHideProperty() {
		JavaClass test2Class = getTest2Class();
		objFeaturesSetup();
		
		// Test2ClassBeanInfo explicitly hides Object (super) from its BeanInfo, so only the non-BeanInfo properties will show.		
		assertEquals(2+objNonProperties, test2Class.getAllProperties().size());
		
		// Try with subclasses. Make sure that Test2ClassBeanInfo is used with the subclasses.
		JavaClass test2ClassA = getTest2ClassA();		
		assertEquals(3+objNonProperties, test2ClassA.getAllProperties().size());
		
		JavaClass test2ClassB = getTest2ClassB();		
		assertEquals(4+objNonProperties, test2ClassB.getAllProperties().size());
	}
	
	public void testIndexedBeanInfo() {
		JavaClass test2Class = getTest2Class();
		
		// Test that Indexed thru beaninfo works
		Iterator itr = Utilities.getPropertiesIterator(test2Class.getProperties());
		boolean found = false;
		while (itr.hasNext()) {		
			PropertyDecorator pd = (PropertyDecorator) itr.next();			
			if (pd.getName().equals("fooBar")) { //$NON-NLS-1$
				found = true;
				assertTrue(pd instanceof IndexedPropertyDecorator);
				IndexedPropertyDecorator ip = (IndexedPropertyDecorator) pd;
				JavaHelpers type = (JavaHelpers) ip.getPropertyType();
				assertEquals("Ljava.lang.String[]", type.getQualifiedName()); //$NON-NLS-1$
				Method mthd = ip.getReadMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2Class.getFooBar("), true), mthd); //$NON-NLS-1$
				mthd = ip.getWriteMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2Class.setFooBar(Ljava.lang.String[]"), true), mthd); //$NON-NLS-1$
				mthd = ip.getIndexedReadMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2Class.getFooBar(int"), true), mthd); //$NON-NLS-1$
				mthd = ip.getIndexedWriteMethod();
				assertSame(rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2Class.setFooBar(int,java.lang.String"), true), mthd); //$NON-NLS-1$
				break;
			}
		}

		assertTrue(found);
	}

	protected JavaClass getTest2ClassC() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Test2ClassC"), true); //$NON-NLS-1$
	}
	
	public void testBeanInfoProject() {
		JavaClass test2ClassC = getTest2ClassC();
		
		// This will test that we could find Test2ClassC BeanInfo that is in another BeanInfo project (i.e. a project
		// that is specifically for BeanInfo's for a specific code project).
		BeanDecorator bd = Utilities.getBeanDecorator(test2ClassC);
		assertNotNull(bd);
		assertEquals("Test2ClassC from BeanInfo", bd.getDisplayName());
		
	}
	
	public void testOverride() {
		
		// Test that the local property is expert because it overrode from Test2ClassB and made it expert.
		JavaClass test2ClassB = getTest2ClassB();
		
		// Test that the local property is not expert in Test2ClassB.
		EStructuralFeature p = test2ClassB.getEStructuralFeature("setA");
		assertNotNull(p);
		PropertyDecorator pd = Utilities.getPropertyDecorator(p);
		assertFalse(pd.isExpert());

		// Now get subclass with override.
		JavaClass test2ClassC = getTest2ClassC();
		
		// Test that the local property is now expert because it overrode from Test2ClassB and made it expert.
		p = test2ClassC.getEStructuralFeature("setA");
		assertNotNull(p);
		pd = Utilities.getPropertyDecorator(p);
		assertTrue(pd.isExpert());		
	}
	
	protected JavaClass getTest2ClassPreq() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo.prereq#Test2ClassPreq"), true); //$NON-NLS-1$
	}
		
	public void testPrereqedProjects() {
		JavaClass test2ClassPrereq = getTest2ClassPreq();
		objFeaturesSetup();
		 
		// Test finding beaninfos for classes in another project (pre-reqed project).		
		assertEquals(1+objFeatures, test2ClassPrereq.getAllProperties().size());
		// Verify BeanInfo and not jsut introspection.
		EStructuralFeature f = test2ClassPrereq.getEStructuralFeature("aSet");
		assertNotNull(f);
		PropertyDecorator pd = Utilities.getPropertyDecorator(f);
		assertTrue(pd.isExpert());
	}
	
	public void testRemovePropRebuild() throws CoreException {
		objFeaturesSetup();
		
		// Test removing a property directly from a super class causes the subclasses to re-build their properties.
		JavaClass test1Class = getTest1Class();
		JavaClass test1ClassA = getTest1ClassA();
		// Get and test the properties first to cause introspection for both BEFORE the modification.
		assertEquals(2+objFeatures, test1Class.getAllProperties().size());
		assertEquals(3+objFeatures, test1ClassA.getAllProperties().size());
		try {
			test1Class.getEStructuralFeatures().remove(0); // Remove the first one. They should both now rebuild.
			assertEquals(1+objFeatures, test1Class.getAllProperties().size());
			assertEquals(2+objFeatures, test1ClassA.getAllProperties().size());
		} finally {
			// Need to close and reopen the project so that to restore the correct shape for test1Class for other tests.
			final IProject project = nature.getProject();
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			workspace.run(new IWorkspaceRunnable () {
				public void run(IProgressMonitor monitor) throws CoreException {
					project.close(monitor);
					project.open(monitor);
				}
			}, project, 0, null);
		}
	}
	
	public void testRemoveEventRebuild() throws CoreException {
		// Test removing an event directly from a super class causes the subclasses to re-build their allEvents.
		JavaClass test1Class = getTest1Class();
		JavaClass test1ClassA = getTest1ClassA();
		// Get and test the events first to cause introspection for both BEFORE the modification.
		assertEquals(2, test1Class.getAllEvents().size());
		assertEquals(2, test1ClassA.getAllEvents().size());		
		try {
			test1Class.getEvents().remove(0); // Remove the first one. They should now both rebuild.
			assertEquals(1, test1Class.getAllEvents().size());
			assertEquals(1, test1ClassA.getAllEvents().size());			
		} finally {
			// Need to close and reopen the project so that to restore the correct shape for test1Class for other tests.
			final IProject project = nature.getProject();
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			workspace.run(new IWorkspaceRunnable () {
				public void run(IProgressMonitor monitor) throws CoreException {
					project.close(monitor);
					project.open(monitor);
				}
			}, project, 0, null);
		}
	}
	
	protected JavaClass getCustomer() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#Customer"), true); //$NON-NLS-1$
	}
	
	public void testInnerClass() {
		JavaClass customer = getCustomer();
		
		// Now test customer to make sure that it introspects correctly, plus the inner class of one of its properties does too.
		List allLocalFeatures = customer.getProperties();
		assertEquals(2, allLocalFeatures.size());
		Iterator itr = allLocalFeatures.iterator();
		boolean found = false;
		while (itr.hasNext()) {
			EStructuralFeature ea = (EStructuralFeature) itr.next();
			if ("savings".equals(ea.getName())) { //$NON-NLS-1$
				found = true;
				JavaClass innerClass = (JavaClass) ea.getEType();	// This property type is an innerclass of Customer
				assertEquals("Customer$Account", innerClass.getName());
				allLocalFeatures = innerClass.getProperties();
				assertEquals(2, allLocalFeatures.size());
			}
		}
		
		assertTrue(found);
	}

	protected JavaClass getTestBoundNotBound() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#TestBoundNotBound"), true); //$NON-NLS-1$
	}
	
	public void testNotBoundReflection() {
		JavaClass testBoundNotBound = getTestBoundNotBound();
		
		// Now test that properties are bound correctly on reflection. First test reflect not bound correct.
		// This is determined by no addPropertyChangeListener method on class.		
		List allLocalFeatures = testBoundNotBound.getProperties();
		assertEquals(1, allLocalFeatures.size());
		EStructuralFeature ea = (EStructuralFeature) allLocalFeatures.get(0);
		PropertyDecorator pd = Utilities.getPropertyDecorator(ea);
		assertNotNull(pd);
		assertFalse(pd.isBound());
	}
	
	protected JavaClass getTestBoundSuper() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#TestBoundSuper"), true); //$NON-NLS-1$
	}
	
	public void testBoundReflection() {
		JavaClass testBoundSuper = getTestBoundSuper();
		
		// Test that reflection can find bound property.
		// This is determined by finding add/remove PropertyChangeListener method on class.		
		List allLocalFeatures = testBoundSuper.getProperties();
		assertEquals(1, allLocalFeatures.size());
		EStructuralFeature ea = (EStructuralFeature) allLocalFeatures.get(0);
		PropertyDecorator pd = Utilities.getPropertyDecorator(ea);
		assertNotNull(pd);
		assertTrue(pd.isBound());
	}

	protected JavaClass getTestBoundSub() {
		return (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#TestBoundSub"), true); //$NON-NLS-1$
	}
	
	public void testBoundReflectionSubclass() {
		JavaClass testBoundSub = getTestBoundSub();

		// Test that reflection can find bound property due to inherit from a bound class.
		// This is determined by finding add/remove PropertyChangeListener method on class (but from a super class).						
		List allLocalFeatures = testBoundSub.getProperties();
		assertEquals(1, allLocalFeatures.size());		
		EStructuralFeature ea = (EStructuralFeature) allLocalFeatures.get(0);
		PropertyDecorator pd = Utilities.getPropertyDecorator(ea);
		assertNotNull(pd);
		assertTrue(pd.isBound());
	}
	
	public void testOverridesFile() {
		// Test that override files get applied correctly.
		JavaClass testOverride = (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#TestOverrides"), true); //$NON-NLS-1$
		assertNotNull(testOverride);
		EStructuralFeature xyz = testOverride.getEStructuralFeature("xyz");
		assertNotNull(xyz);
		// Test that it has the correct type so that we know it was reflected correctly.
		assertEquals(rset.getEObject(URI.createURI("java:/#int"), true), xyz.getEType());
		
		// Test that we have the annotation we added.
		assertNotNull(xyz.getEAnnotation("Override Annotation"));
	}
	
	public void test79083() {
		// Test bug 79083: Null ETypes from overrides that didn't have a reflection to give it the type. BeanInfo should make it EObject type.
		JavaClass testOverride = (JavaClass) rset.getEObject(URI.createURI("java:/org.eclipse.jem.tests.beaninfo#TestOverrides"), true); //$NON-NLS-1$
		assertNotNull(testOverride);
		EStructuralFeature test79083 = testOverride.getEStructuralFeature("test79083");
		assertNotNull(test79083);
		assertEquals(EcorePackage.eINSTANCE.getEObject(), test79083.getEType());
	}
}
