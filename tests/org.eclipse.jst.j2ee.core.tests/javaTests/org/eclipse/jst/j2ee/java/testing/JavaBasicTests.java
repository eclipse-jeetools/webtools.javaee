package org.eclipse.jst.j2ee.java.testing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.internal.java.init.JavaInit;
import org.eclipse.jem.java.ArrayType;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaDataType;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.java.Method;
import org.eclipse.jem.java.TypeKind;
import org.eclipse.jem.java.impl.JavaClassImpl;
import org.eclipse.jem.java.impl.JavaRefFactoryImpl;
/**
 * Insert the type's description here.
 * Creation date: (5/18/2000 1:08:17 PM)
 * @author: 
 */
public class JavaBasicTests extends AbstractTestCase {
/**
 * EjbDocCreationTest constructor comment.
 * @param name java.lang.String
 */
public JavaBasicTests(String name) {
	super(name);
}
public static void main(String args[]) {
	String[] className = {"org.eclipse.jst.j2ee.java.JavaBasicTests", "-noloading"}; //$NON-NLS-1$ //$NON-NLS-2$
	TestRunner.main(className);
}
/**
 * Sets up the fixture, for example, open a network connection.
 * This method is called before a test is executed.
 */
protected void setUp() throws Exception {
	JavaInit.init();
	// Java reflection may need additional set up externally
	super.setUp();
}
public static Test suite() {
	TestSuite suite = new TestSuite();
	suite.addTest(new JavaBasicTests("testReflection")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testInheritance")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testInheritance2")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testInheritance3")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testInterfaceInheritanceCycles")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testSerialization")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testSourceNoInheritance")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testCanReflect")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testPrimitives")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testArrays")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testUserClass")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testInnerClass")); //$NON-NLS-1$
	suite.addTest(new JavaBasicTests("testInnerClass2")); //$NON-NLS-1$
	return suite;
}
/**
 * Tears down the fixture, for example, close a network connection.
 * This method is called after a test is executed.
 */
protected void tearDown() throws Exception {
	super.tearDown();
}
/**
 * Test the array reflection capability
 *		- requires access to com.ibm.etools.java.stressSample classes
 */
public void testArrays() {
	ResourceSet set = getResourceSet();
	String result;
	JavaClass aClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.A", set); //$NON-NLS-1$
	result = aClass.infoString();
	//System.out.println(result);
	assertTrue("Testing A infoString", result != null); //$NON-NLS-1$
	JavaClass bClass = (JavaClass)JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.B", set); //$NON-NLS-1$
	result = bClass.infoString();
	//System.out.println(result);
	assertTrue("Testing B infoString", result != null); //$NON-NLS-1$
	Field tempField2, tempField = aClass.getFieldNamed("anA"); //$NON-NLS-1$

	assertNotNull("A has a field: A anA is not null", tempField); //$NON-NLS-1$
	assertTrue("A has a field: A anA", !tempField.isArray()); //$NON-NLS-1$
	assertTrue("A has a field: A anA", tempField.getEType().equals(aClass)); //$NON-NLS-1$
	tempField = aClass.getFieldNamed("bees"); //$NON-NLS-1$
	assertTrue("A has a field: B[] bees", tempField.isArray()); //$NON-NLS-1$
	assertTrue("A has a field: B[] bees", ((ArrayType) tempField.getEType()).getComponentType().equals(bClass)); //$NON-NLS-1$
	assertTrue("A has a field: B[] bees", ((ArrayType) tempField.getEType()).getFinalComponentType().equals(bClass)); //$NON-NLS-1$
	tempField2 = aClass.getFieldNamed("bees_and_bees"); //$NON-NLS-1$
	assertTrue("A has a field: B[][] bees_and_bees", tempField2.isArray()); //$NON-NLS-1$
	assertTrue("A has a field: B[][] bees_and_bees", ((ArrayType) tempField2.getEType()).getComponentType().equals(tempField.getEType())); //$NON-NLS-1$
	assertTrue("A has a field: B[][] bees_and_bees", ((ArrayType) tempField2.getEType()).getFinalComponentType().equals(bClass)); //$NON-NLS-1$
	JavaDataType intType = (JavaDataType) JavaRefFactory.eINSTANCE.reflectType("int", set); //$NON-NLS-1$
	assertTrue("int is not right", intType != null); //$NON-NLS-1$
	tempField = aClass.getFieldNamed("ints"); //$NON-NLS-1$
	assertTrue("A has a field: int[] ints", tempField.isArray()); //$NON-NLS-1$
	assertTrue("A has a field: int[] ints", ((ArrayType) tempField.getEType()).getComponentType().equals(intType)); //$NON-NLS-1$
	assertTrue("A has a field: int[] ints", ((ArrayType) tempField.getEType()).getFinalComponentType().equals(intType)); //$NON-NLS-1$
	tempField2 = aClass.getFieldNamed("ints2"); //$NON-NLS-1$
	assertTrue("A has a field: int[][] ints2", tempField2.isArray()); //$NON-NLS-1$
	assertTrue("A has a field: int[][] ints2", ((ArrayType) tempField2.getEType()).getComponentType().equals(tempField.getEType())); //$NON-NLS-1$
	assertTrue("A has a field: int[][] ints2", ((ArrayType) tempField2.getEType()).getFinalComponentType().equals(intType)); //$NON-NLS-1$
	Method m = aClass.getMethod("getInts", new ArrayList(0)); //$NON-NLS-1$
	assertTrue("A has a method: int[] getInts()", m != null); //$NON-NLS-1$
	assertTrue("A has a method: int[] getInts()", ((JavaHelpers) m.getReturnType()).isArray()); //$NON-NLS-1$
	assertTrue("A has a method: int[] getInts()", ((ArrayType) m.getReturnType()).getComponentType().equals(intType)); //$NON-NLS-1$
	org.eclipse.emf.common.util.EList methods = aClass.getMethods();
	Iterator i = methods.iterator();
	while (i.hasNext()) {
		Method next = (Method) i.next();
		if (next.getName().equals("doAllPrimitives")) { //$NON-NLS-1$
			m = next;
			break;
		}
	}
	assertTrue("A has a method: void doAllPrimitives(int[] ints, int[][] ints2, int[][][] ints3, char[] chars, float[] floats, boolean[] cools, double[] doubles, long[] longs, short[] shorts, byte[] bytes)", m != null); //$NON-NLS-1$
	Collection parms = m.getParameters();
	i = parms.iterator();
	while (i.hasNext()) {
		JavaParameter next = (JavaParameter) i.next();
		assertTrue("doAllPrimitives has all array parameters", next.isArray()); //$NON-NLS-1$
		assertTrue("doAllPrimitives has all primitive array parameters", ((ArrayType) next.getEType()).getFinalComponentType().isPrimitive()); //$NON-NLS-1$
	}
}
/**
 * Verify the canReflect api.
 */
public void testCanReflect() {
	ResourceSet set = getResourceSet();
	JavaClass aClass =
		(JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.A", set); //$NON-NLS-1$
	assertTrue("org.eclipse.jst.j2ee.java.stressSample.A messed up", aClass != null); //$NON-NLS-1$
	assertTrue("Should be able to reflect org.eclipse.jst.j2ee.java.stressSample.A.", aClass.isExistingType()); //$NON-NLS-1$
	JavaClass bogusClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.lang.Foo", set); //$NON-NLS-1$
	assertTrue("java.lang.Foo messed up", bogusClass != null); //$NON-NLS-1$
	assertTrue("Should not be able to reflect java.lang.Foo.", !bogusClass.isExistingType()); //$NON-NLS-1$
}
/**
 * Get some related classes and verify their inheritance
 */
public void testInheritance() {
	ResourceSet set = getResourceSet();
	JavaClass floatClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.lang.Float", set); //$NON-NLS-1$
	assertTrue("Float messed up", floatClass != null); //$NON-NLS-1$
	JavaClass numberClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.lang.Number", set); //$NON-NLS-1$
	assertTrue("Number messed up", numberClass != null); //$NON-NLS-1$
	JavaClass objectClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.lang.Object", set); //$NON-NLS-1$
	assertTrue("Object messed up", objectClass != null); //$NON-NLS-1$
	assertTrue("Float should inherit from Number", floatClass.inheritsFrom(numberClass)); //$NON-NLS-1$
	assertTrue("Number should inherit from Object", numberClass.inheritsFrom(objectClass)); //$NON-NLS-1$
	assertTrue("Float should inherit from Object", floatClass.inheritsFrom(objectClass)); //$NON-NLS-1$
	Method floatMethod = floatClass.getPublicMethod("floatValue", Collections.EMPTY_LIST); //$NON-NLS-1$
	JavaDataType floatType = (JavaDataType) JavaRefFactory.eINSTANCE.reflectType("float", set); //$NON-NLS-1$
	assertTrue("Float should have a method float floatValue()", floatMethod.getReturnType().equals(floatType)); //$NON-NLS-1$
	JavaClass stringClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.lang.String", set); //$NON-NLS-1$
	Method toStringMethod = floatClass.getMethodExtended("toString", Collections.EMPTY_LIST); //$NON-NLS-1$
	assertTrue("Float should have a method String toString()", toStringMethod != null); //$NON-NLS-1$
	assertTrue("Float should have a method String toString()", toStringMethod.getReturnType().equals(stringClass)); //$NON-NLS-1$

	// Test inherited fields and their initializers
	JavaClass aClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.A", set); //$NON-NLS-1$
	Field aTesting = aClass.getField("testString"); //$NON-NLS-1$
	assertTrue("A should have a field String testString", aTesting != null); //$NON-NLS-1$
	assertTrue("A should have a field String testString = testing", aTesting.getInitializer().getSource().equals("\"testing\"")); //$NON-NLS-1$ //$NON-NLS-2$
	JavaClass bClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.B", set); //$NON-NLS-1$
	Field bTesting = bClass.getField("testString"); //$NON-NLS-1$
	assertTrue("B should NOT have a field String testString", bTesting == null); //$NON-NLS-1$
	bTesting = bClass.getFieldExtended("testString"); //$NON-NLS-1$
	assertTrue("B should inherit have a field String testString", bTesting != null); //$NON-NLS-1$
	assertTrue("B should inherit a field String testString = testing", bTesting.getInitializer().getSource().equals("\"testing\"")); //$NON-NLS-1$ //$NON-NLS-2$
	JavaClass dClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.D", set); //$NON-NLS-1$
	Field dTesting = dClass.getFieldExtended("testString"); //$NON-NLS-1$
	assertTrue("D should inherit have a field String testString", dTesting != null); //$NON-NLS-1$
	assertTrue("D should inherit a field String testString = testing", dTesting.getInitializer().getSource().equals("\"testing\"")); //$NON-NLS-1$ //$NON-NLS-2$
	
}
/**
 * Test for cycles.
 *
 */
public void testInheritance2() {
	ResourceSet set = getResourceSet();
	JavaClass dClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.D", set); //$NON-NLS-1$
	assertTrue("D messed up", dClass.isExistingType()); //$NON-NLS-1$
	JavaClass bClass = dClass.getSupertype();
	JavaClass aClass = bClass.getSupertype();
	assertTrue("Should not be able to have D extend A.  This causes a cycle.", !aClass.isValidSupertype(dClass)); //$NON-NLS-1$
}


/**
 * Test for cycles.
 *
 */
public void testInterfaceInheritanceCycles() {
	ResourceSet set = getResourceSet();
	JavaClass dClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.ID", set); //$NON-NLS-1$
	assertTrue("DInterface messed up", dClass.isExistingType()); //$NON-NLS-1$
	JavaClass bClass = (JavaClass) dClass.getImplementsInterfaces().get(0);
	JavaClass aClass = (JavaClass) bClass.getImplementsInterfaces().get(0);
	assertTrue("Should not be able to have ID extend IA.  This causes a cycle.", !aClass.isValidSupertype(dClass)); //$NON-NLS-1$
}/**
 * Test that getEAllStructuralFeatures does trip reflection.
 *
 */
public void testInheritance3() {
	ResourceSet set = getResourceSet();
	JavaClass dClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.D", set); //$NON-NLS-1$
	assertTrue("D messed up", dClass.isExistingType()); //$NON-NLS-1$
	dClass.getEAllStructuralFeatures(); //Should force reflection through inheritance.
	JavaClassImpl aClass = (JavaClassImpl) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.A", set); //$NON-NLS-1$
	List methods = aClass.getMethodsGen(); //No reflection
	//Look for getChar() method
	Iterator it = methods.iterator();
	Method method = null;
	while (it.hasNext()) {
		method = (Method) it.next();
		if (method.getName().equals("getChar")) //$NON-NLS-1$
			break;
		else
			method = null;
	}
	assertNotNull("Failed to find getChar() method because reflection was not tripped.", method); //$NON-NLS-1$
}/**
 * Resolve "int"
 * - also make sure we have the char wrapper properly setup.
 */
public void testPrimitives() {
	ResourceSet set = getResourceSet();
	JavaDataType intType = (JavaDataType) JavaRefFactory.eINSTANCE.reflectType("int", set); //$NON-NLS-1$
	assertTrue("int is not right", intType != null); //$NON-NLS-1$
	JavaDataType charType = (JavaDataType) JavaRefFactory.eINSTANCE.reflectType("char", set); //$NON-NLS-1$
	assertTrue("char wrapper is broken again", charType.getWrapper() != null); //$NON-NLS-1$
}
/**
 * First test is to make sure we can resolve "java.lang.String"
 */
public void testReflection() {
	ResourceSet set = getResourceSet();
	JavaHelpers string1 = JavaRefFactory.eINSTANCE.reflectType("java.lang.String", set); //$NON-NLS-1$
	assertTrue("String1 messed up", string1 != null); //$NON-NLS-1$
	JavaHelpers string2 = JavaRefFactory.eINSTANCE.reflectType("java.lang.String", set); //$NON-NLS-1$
	assertTrue("String2 messed up", string2 != null); //$NON-NLS-1$
	assertTrue("String1 and String2 not identical", (string1.equals(string2))); //$NON-NLS-1$
}
/**
 * Get serializing and reading a JavaClass instance
 */
public void testSerialization() {
	// Use a new context for this test, we may mess up some of these classes.
	ResourceSet rs = JavaRefFactoryImpl.createJavaContext();
	Resource r;
	String fileNameURI = "out.xmi"; //$NON-NLS-1$
	try {
		r = rs.createResource(URI.createURI(fileNameURI));
		// Reflect a complex class
		JavaClass a = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.A", rs); //$NON-NLS-1$
		// Force reflection of all properties to get a complete document
		a.infoString();
		rs.getResources().add(r);
		r.getContents().add(a);
		r.save(rs.getLoadOptions());
	} catch (Exception e) {
		assertTrue("Error occured serializing A: " + e, false); //$NON-NLS-1$
	}

	// Now try reading the serialized model, again into a clean context
	rs = JavaRefFactoryImpl.createJavaContext();
	r = null;
	try {
		r = rs.createResource(URI.createURI(fileNameURI));
		r.load(null);
	} catch (Exception e) {
		assertTrue("Errors occurred while parsing: " + e, false); //$NON-NLS-1$
	}
	assertTrue("Failed to load serialized class", r != null); //$NON-NLS-1$
	JavaClass loaded = null;
	String errorMsg = ""; //$NON-NLS-1$
	try {
		loaded = (JavaClass) r.getContents().get(0);
	} catch (Exception e) {
	    errorMsg = "  Failed to get class A from the loaded resource. error: " + e; //$NON-NLS-1$
	}
	assertTrue("The serialized document should contain a single JavaClass." + errorMsg, loaded != null); //$NON-NLS-1$

}
/**
 * Verify that a class without an extends key word still inherits
 * from java.lang.Object.
 */
public void testSourceNoInheritance() {
	ResourceSet set = getResourceSet();
	JavaClass aClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.A", set); //$NON-NLS-1$
	assertTrue("org.eclipse.jst.j2ee.java.stressSample.A messed up", aClass != null); //$NON-NLS-1$
	JavaClass objectClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("java.lang.Object", set); //$NON-NLS-1$
	assertTrue("Object messed up", objectClass != null); //$NON-NLS-1$
	assertTrue("org.eclipse.jst.j2ee.java.stressSample.A should inherit from Object", aClass.inheritsFrom(objectClass)); //$NON-NLS-1$
}
/**
 * Get some related classes and verify their inheritance
 */
public void testUserClass() {
	ResourceSet set = getResourceSet();
	JavaClass test1Interface = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.Test1Local", set); //$NON-NLS-1$
	assertTrue("Test1Local messed up", test1Interface != null); //$NON-NLS-1$
	assertTrue("Test1Local should be an interface, it may not be properly loaded.", test1Interface.getKind() == TypeKind.INTERFACE_LITERAL); //$NON-NLS-1$
	assertTrue("Test1Local should be public", test1Interface.isPublic()); //$NON-NLS-1$
	assertTrue("Test1Local should not be final", !test1Interface.isFinal()); //$NON-NLS-1$
	// apparently all interfaces are abstract to the JDK, so skip this one
	//assertTrue("Test1Local should not be abstract", !test1Interface.isAbstract());
	assertTrue("Test1Local should be in package org.eclipse.jst.j2ee.java.stressSample.", test1Interface.eContainer() != null); //$NON-NLS-1$
	assertTrue("Test1Local should be in package org.eclipse.jst.j2ee.java.stressSample.", test1Interface.getJavaPackage().getName().equals("org.eclipse.jst.j2ee.java.stressSample")); //$NON-NLS-1$ //$NON-NLS-2$
	JavaClass ejbObjectInterface = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("javax.ejb.EJBLocalObject", set); //$NON-NLS-1$
	assertTrue("Test1Local should implement EJBLocalObject", test1Interface.implementsInterface(ejbObjectInterface)); //$NON-NLS-1$
	JavaClass testBeanClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.Test1Bean", set); //$NON-NLS-1$
	assertTrue("Test1Bean messed up", testBeanClass != null); //$NON-NLS-1$
	assertTrue("Test1Bean should be a class, it may not be properly loaded.", testBeanClass.getKind() == TypeKind.CLASS_LITERAL); //$NON-NLS-1$
	assertTrue("Test1Bean should be public", testBeanClass.isPublic()); //$NON-NLS-1$
	assertTrue("Test1Bean should not be final", !testBeanClass.isFinal()); //$NON-NLS-1$
	assertTrue("Test1Bean should be abstract", testBeanClass.isAbstract()); //$NON-NLS-1$
	String typeName = ((JavaClass)testBeanClass).getMethod("getName", Collections.EMPTY_LIST).getReturnType().getQualifiedName(); //$NON-NLS-1$
	assertTrue("Test1Bean.getName() should be typed as java.lang.String (" + typeName + ")", typeName.equals("java.lang.String")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	assertTrue("Test1Bean should be in package ", testBeanClass.eContainer() != null); //$NON-NLS-1$
	assertTrue("Test1Bean should be in package org.eclipse.jst.j2ee.java.stressSample", testBeanClass.getJavaPackage().getName().equals("org.eclipse.jst.j2ee.java.stressSample")); //$NON-NLS-1$ //$NON-NLS-2$
	JavaClass entityBeanInterface = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("javax.ejb.EntityBean", set); //$NON-NLS-1$
	assertTrue("Test1Bean should implement EntityBean", testBeanClass.implementsInterface(entityBeanInterface));  //$NON-NLS-1$
}
/**
 * Get some related classes and verify their inheritance
 * This variation is most likely to run in VA/J.
 */
public void testUserClass1() {
	ResourceSet set = getResourceSet();
	JavaClass vapAddressInterface = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("com.ibm.etools.java.Field", set); //$NON-NLS-1$
	assertTrue("Field messed up", vapAddressInterface != null); //$NON-NLS-1$
	assertTrue("Field should be an interface, it may not be properly loaded.", vapAddressInterface.getKind() == TypeKind.INTERFACE_LITERAL); //$NON-NLS-1$
	assertTrue("Field should be public", vapAddressInterface.isPublic()); //$NON-NLS-1$
	assertTrue("Field should not be final", !vapAddressInterface.isFinal()); //$NON-NLS-1$
	// apparently all interfaces are abstract to the JDK, so skip this one
	//assertTrue("Field should not be abstract", !vapAddressInterface.isAbstract());
	assertTrue("Field should be in package com.ibm.etools.java.", vapAddressInterface.eContainer() != null); //$NON-NLS-1$
	assertTrue("Field should be in package com.ibm.etools.java.", vapAddressInterface.getJavaPackage().getName().equals("com.ibm.etools.java")); //$NON-NLS-1$ //$NON-NLS-2$
	JavaClass fieldGen = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("com.ibm.etools.java.gen.FieldGen", set); //$NON-NLS-1$
	assertTrue("Field should inherit from FieldGen", vapAddressInterface.inheritsFrom(fieldGen)); //$NON-NLS-1$
}
public void testInnerClass() {
	ResourceSet set = getResourceSet();
	JavaClass c = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.C", set); //$NON-NLS-1$
	assertNotNull(c);
	List cMethods = c.getMethods();
	assertTrue("There should be one method in C.", cMethods.size() == 2); //counting default constructor //$NON-NLS-1$
	Method cMethod = (Method) cMethods.get(0);
	assertEquals("Wrong method name for C.", "makeRunnable", cMethod.getName()); //$NON-NLS-1$ //$NON-NLS-2$
	List inners = c.getDeclaredClasses();
	assertTrue("There should be one inner class.", inners.size() == 1); //$NON-NLS-1$
	JavaClass inner = (JavaClass) inners.get(0);
	List innerMethods = inner.getMethods();
	assertTrue(innerMethods.size() == 2); //counting default constructor
	Method innerMethod = (Method) innerMethods.get(0);
	assertEquals("run", innerMethod.getName());	 //$NON-NLS-1$
}
public void testInnerClass2() {
	ResourceSet set = getResourceSet();
	JavaClass e = (JavaClass) JavaRefFactory.eINSTANCE.reflectType("org.eclipse.jst.j2ee.java.stressSample.E", set); //$NON-NLS-1$
	assertNotNull(e);
	List eMethods = e.getMethods();
	assertTrue("There should be one method in E.", eMethods.size() == 2); //counting default constructor //$NON-NLS-1$
	Method eMethod = (Method) eMethods.get(0);
	assertEquals("Wrong method name for E.", "getTest", eMethod.getName()); //$NON-NLS-1$ //$NON-NLS-2$
	List inners = e.getDeclaredClasses();
	assertTrue("There should be one inner class.", inners.size() == 1); //$NON-NLS-1$
	JavaClass inner = (JavaClass) inners.get(0);
	List innerMethods = inner.getMethods();
	assertTrue(innerMethods.size() == 2); //counting default constructor
	Method innerMethod = (Method) innerMethods.get(0);
	assertEquals("run", innerMethod.getName());	 //$NON-NLS-1$
}
}
