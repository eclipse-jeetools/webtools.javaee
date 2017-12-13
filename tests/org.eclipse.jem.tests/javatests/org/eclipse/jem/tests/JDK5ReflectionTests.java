/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import junit.framework.TestCase;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.core.*;

import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.java.*;

/**
 * Test case for testing the reflection of concepts
 * from JDK 5.
 * @since 1.2.2
 *
 */
public class JDK5ReflectionTests extends TestCase {
	
	private static final String PROJECT_NAME = "JDK5Tests"; //$NON-NLS-1$
	private IJavaProject javaProject;
	private ResourceSet resourceSet;
	
	private boolean oldAutoBuildingState; // autoBuilding state before we started.

	protected boolean isReflectingSource;
	
	/**
	 * 
	 */
	public JDK5ReflectionTests() {
		isReflectingSource = true;
	}

	/**
	 * @param name
	 */
	public JDK5ReflectionTests(String name) {
		super(name);
		isReflectingSource = true;
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		if (isReflectingSource)
			setUpForSource();
		else
			setUpForNoSource();
	}

	protected void setUpForSource() throws CoreException, IOException, MalformedURLException {
		oldAutoBuildingState = JavaProjectUtil.setAutoBuild(true);
		URL installURL = JavaTestsPlugin.getPlugin().getBundle().getEntry("/");
		String[] zipPaths = new String[1];
		zipPaths[0] = FileLocator.toFileURL(new URL(installURL, "testdata/JDK5Tests.zip")).getFile();
		IProject[] projects =
			JavaProjectUtil.importProjects(
				new String[] { PROJECT_NAME },
				zipPaths);
		assertNotNull(projects[0]);
		JavaProjectUtil.waitForAutoBuild();
		javaProject = JavaCore.create(projects[0]);
		javaProject.setOption(JavaCore.COMPILER_COMPLIANCE, "1.5");
		resourceSet = new ResourceSetImpl();
		resourceSet.getAdapterFactories().add(new JavaJDOMAdapterFactory(javaProject));
	}

	protected void setUpForNoSource() throws CoreException, IOException, MalformedURLException {
		oldAutoBuildingState = JavaProjectUtil.setAutoBuild(true);
		URL installURL = JavaTestsPlugin.getPlugin().getBundle().getEntry("/");
		String[] jarPaths = new String[1];
		jarPaths[0] = FileLocator.toFileURL(new URL(installURL, "testdata/JDK5BinTests.jar")).getFile();
		IProject project = JavaProjectUtil.createEmptyJavaProject(ResourcesPlugin.getWorkspace(), new Path(PROJECT_NAME), null);
		assertNotNull(project);
		JavaProjectUtil.waitForAutoBuild();
		javaProject = JavaCore.create(project);
		javaProject.setOption(JavaCore.COMPILER_COMPLIANCE, "1.5");
		ArrayList classpath = new ArrayList(Arrays.asList(javaProject.getRawClasspath()));
		classpath.add(JavaCore.newLibraryEntry(new Path(jarPaths[0]), null, null));
		javaProject.setRawClasspath((IClasspathEntry[]) classpath.toArray(new IClasspathEntry[0]), null);	
		JavaProjectUtil.waitForAutoBuild();
		resourceSet = new ResourceSetImpl();
		resourceSet.getAdapterFactories().add(new JavaJDOMAdapterFactory(javaProject));
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				JavaProjectUtil.deleteProject(javaProject.getProject());
			}
		}, ResourcesPlugin.getWorkspace().getRoot(), 0, null);

		JavaProjectUtil.setAutoBuild(oldAutoBuildingState);
	}
	
	private JavaHelpers reflectClass(String qualifiedName) {
		return JavaRefFactory.eINSTANCE.reflectType(qualifiedName, resourceSet);
	}
	
	/**
	 * Test method test1 from Test class.
	 * 
	 * public Collection<String> test1()
	 *
	 */
	public void test1() {
 		JavaHelpers testHelper = reflectClass("test.me.Test");
		assertFalse(testHelper.isPrimitive());
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = testClass.getMethod("test1", Collections.EMPTY_LIST);
		assertNotNull(method);
		JavaHelpers returnType = method.getReturnType();
		
		
		assertEquals("java.util.Collection", returnType.getQualifiedName());
		assertEquals("Collection", returnType.getSimpleName());
	}
	
	/**
	 * Test method test2 from Test class.
	 * 
	 * public void test2(Collection<? extends IFoo> aCollection) 
	 *
	 */
	public void test2() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test2")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		JavaHelpers returnType = method.getReturnType();
		assertNotNull(returnType); //void
		List params = method.getParameters();
		assertTrue(params.size() == 1);
		JavaParameter param = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param.getName().equals("aCollection"));
		JavaHelpers paramType = param.getJavaType();
		assertNotNull(paramType);
		
		assertEquals("java.util.Collection", paramType.getQualifiedName());
		assertEquals("Collection", paramType.getSimpleName());
	}

	/**
	 * Test method test3 from Test class.
	 * 
	 * public <T extends IFoo> T test3(Class<T> aClass, String s, List<Integer> aList) 
	 *
	 */
	public void test3() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test3")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		//Verify return type.		
		JavaHelpers returnType = method.getReturnType();
		assertNotNull(returnType); 
		
		assertEquals("test.dependents.IFoo", returnType.getQualifiedName());
		assertEquals("IFoo", returnType.getSimpleName());
		
		//Verify parameters
		List params = method.getParameters();
		assertTrue(params.size() == 3);
		
		//Verify Class<T> aClass
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("aClass"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);

		assertEquals("java.lang.Class", param1Type.getQualifiedName());
		assertEquals("Class", param1Type.getSimpleName());
		
		//Verify String s
		JavaParameter param2 = (JavaParameter) params.get(1);
		if (isReflectingSource)
			assertEquals("s", param2.getName());
		JavaHelpers param2Type = param2.getJavaType();
		assertNotNull(param2Type);

		//Verify List<Integer> aList
		JavaHelpers listType = reflectClass("java.util.List");
		JavaParameter param3 = (JavaParameter) params.get(2);
		if (isReflectingSource)
			assertEquals("aList", param3.getName());
		JavaHelpers param3Type = param3.getJavaType();
		assertNotNull(param3Type);
		assertEquals(listType, param3Type);
	}
	
	/**
	 * Test method test4 from Test class.
	 * 
	 * public Map<Integer, String> test4(Integer anInteger) 
	 *
	 */
	public void test4() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test4")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		//Verify return type.		
		JavaHelpers returnType = method.getReturnType();
		assertNotNull(returnType); 
		
		assertEquals("java.util.Map", returnType.getQualifiedName());
		assertEquals("Map", returnType.getSimpleName());
	}
	
	/**
	 * Test method test5 from Test class.
	 * 
	 * public void test5(List<? super IFoo> aList)
	 *
	 */
	public void test5() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test5")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		//Verify parameters
		List params = method.getParameters();
		assertTrue(params.size() == 1);
		
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("aList"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);
		
		assertEquals("java.util.List", param1Type.getQualifiedName());
		assertEquals("List", param1Type.getSimpleName());
	}

	/**
	 * Test method test6 from Test class.
	 * 
	 * public <T> void test6(Class<T>[] aClassArray, List<String>[] anArrayOfListsOfString, Class<?>[] aWildcardClassArray, Class<?>[][][] aMultiDimWildcardArray)
	 *
	 */
	public void test6() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test6")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		//Verify parameters
		List params = method.getParameters();
		assertEquals(4, params.size());
		
		// Verify Class<T>[] aClassArray
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("aClassArray"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);
		assertTrue(((ArrayType)param1Type).getArrayDimensions() == 1);
		assertEquals("java.lang.Class[]", param1Type.getQualifiedName());
		assertEquals("Class[]", param1Type.getSimpleName());
		assertEquals("java.lang.Class", ((ArrayType)param1Type).getComponentTypeAsHelper().getQualifiedName());
		assertEquals("Class", ((ArrayType)param1Type).getComponentTypeAsHelper().getSimpleName());

		// Verify List<String>[] anArrayOfListsOfString
		JavaParameter param2 = (JavaParameter) params.get(1);
		if (isReflectingSource)
			assertTrue(param2.getName().equals("anArrayOfListsOfString"));
		JavaHelpers param2Type = param2.getJavaType();
		assertNotNull(param2Type);
		assertTrue(((ArrayType)param2Type).getArrayDimensions() == 1);
		assertEquals("java.util.List[]", param2Type.getQualifiedName());
		assertEquals("List[]", param2Type.getSimpleName());
		assertEquals("java.util.List", ((ArrayType)param2Type).getComponentTypeAsHelper().getQualifiedName());
		assertEquals("List", ((ArrayType)param2Type).getComponentTypeAsHelper().getSimpleName());
		
		// Verify Class<?>[] aWildcardClassArray
		JavaParameter param3 = (JavaParameter) params.get(2);
		if (isReflectingSource)
			assertTrue(param3.getName().equals("aWildcardClassArray"));
		JavaHelpers param3Type = param3.getJavaType();
		assertNotNull(param3Type);
		assertTrue(((ArrayType)param3Type).getArrayDimensions() == 1);
		assertEquals("java.lang.Class[]", param3Type.getQualifiedName());
		assertEquals("Class[]", param3Type.getSimpleName());
		assertEquals("java.lang.Class", ((ArrayType)param3Type).getComponentTypeAsHelper().getQualifiedName());
		assertEquals("Class", ((ArrayType)param3Type).getComponentTypeAsHelper().getSimpleName());

		// Verify Class<?>[][][] aMultiDimWildcardArray
		JavaParameter param4 = (JavaParameter) params.get(3);
		if (isReflectingSource)
			assertTrue(param4.getName().equals("aMultiDimWildcardArray"));
		JavaHelpers param4Type = param4.getJavaType();
		assertNotNull(param4Type);
		assertTrue(((ArrayType)param4Type).getArrayDimensions() == 3);
		assertEquals("java.lang.Class[][][]", param4Type.getQualifiedName());
		assertEquals("Class[][][]", param4Type.getSimpleName());
		JavaHelpers componentType = ((ArrayType)param4Type).getComponentTypeAsHelper();
		assertEquals("java.lang.Class[][]", componentType.getQualifiedName());
		assertEquals("Class[][]", componentType.getSimpleName());
		componentType = ((ArrayType) componentType).getComponentTypeAsHelper();
		assertEquals("java.lang.Class[]", componentType.getQualifiedName());
		assertEquals("Class[]", componentType.getSimpleName());
		componentType = ((ArrayType) componentType).getComponentTypeAsHelper();
		
	}	

	/**
	 * Test method test7 from Test class.
	 * 
	 * public <T> void test7(List<List<String>> aListOfListOfString, List<List<T>> aListOfListOfT, Map<IFoo, List<Map<IBar, T>>> aMap)
	 *
	 */
	public void test7() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test7")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		//Verify parameters
		List params = method.getParameters();
		assertTrue(params.size() == 3);

		
		// Verify List<List<String>> aListOfListOfString
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("aListOfListOfString"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);
		assertEquals("java.util.List", param1Type.getQualifiedName());
		assertEquals("List", param1Type.getSimpleName());

		// Verify List<List<T>> aListOfListOfT
		JavaParameter param2 = (JavaParameter) params.get(1);
		if (isReflectingSource)
			assertTrue(param2.getName().equals("aListOfListOfT"));
		JavaHelpers param2Type = param2.getJavaType();
		assertNotNull(param2Type);
		assertEquals("java.util.List", param2Type.getQualifiedName());
		assertEquals("List", param2Type.getSimpleName());

		// Verify Map<IFoo, List<Map<IBar, T>>> aMap
		JavaParameter param3 = (JavaParameter) params.get(2);
		if (isReflectingSource)
			assertTrue(param3.getName().equals("aMap"));
		JavaHelpers param3Type = param3.getJavaType();
		assertNotNull(param3Type);
		assertEquals("java.util.Map", param3Type.getQualifiedName());
		assertEquals("Map", param3Type.getSimpleName());
		
	}
	
	/**
	 * Test method test8 from Test class.
	 * 
	 * public void test8(List<? extends List<String>> aList)
	 *
	 */
	public void test8() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test8")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		//Verify parameters
		List params = method.getParameters();
		assertTrue(params.size() == 1);
		
		// Verify List<? extends List<String>> aList
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("aList"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);
		
		assertEquals("java.util.List", param1Type.getQualifiedName());
		assertEquals("List", param1Type.getSimpleName());
		
	}
	
	/**
	 * Test method test9 from Test class.
	 * This is the same test as test7 but with qualified names.
	 * 
	 * public <T> void test9(java.util.List<java.util.List<java.lang.String>> aListOfListOfString, java.util.List<java.util.List<T>> aListOfListOfT, java.util.Map<test.dependents.IFoo, java.util.List<java.util.Map<test.dependents.IBar, T>>> aMap)
	 *
	 */
	public void test9() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test9")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		//Verify parameters
		List params = method.getParameters();
		assertTrue(params.size() == 3);
	
		
		// Verify List<List<String>> aListOfListOfString
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("aListOfListOfString"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);
		assertEquals("java.util.List", param1Type.getQualifiedName());
		assertEquals("List", param1Type.getSimpleName());
	
		// Verify List<List<T>> aListOfListOfT
		JavaParameter param2 = (JavaParameter) params.get(1);
		if (isReflectingSource)
			assertTrue(param2.getName().equals("aListOfListOfT"));
		JavaHelpers param2Type = param2.getJavaType();
		assertNotNull(param2Type);

		assertEquals("java.util.List", param2Type.getQualifiedName());
		assertEquals("List", param2Type.getSimpleName());
	
		// Verify Map<IFoo, List<Map<IBar, T>>> aMap
		JavaParameter param3 = (JavaParameter) params.get(2);
		if (isReflectingSource)
			assertTrue(param3.getName().equals("aMap"));
		JavaHelpers param3Type = param3.getJavaType();
		assertNotNull(param3Type);
		
		assertEquals("java.util.Map", param3Type.getQualifiedName());
		assertEquals("Map", param3Type.getSimpleName());
	}
	
	/**
	 * Test method test10 from Test class.
	 * 
	 * public <T extends Tester> T test10(List<Integer> list)
	 *
	 */
	public void test10() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test10")) {
				break;
			} else {
				method = null;
			}
		}
		
		assertNotNull(method);
		
		JavaHelpers returnType = method.getReturnType();
		
		assertEquals("test.dependents.Tester", returnType.getQualifiedName());
		assertEquals("Tester", returnType.getSimpleName());
		
		//Verify parameters
		List params = method.getParameters();
		assertTrue(params.size() == 1);
	
		
		// Verify List<List<String>> aListOfListOfString
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("list"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);
		assertEquals("java.util.List", param1Type.getQualifiedName());
		assertEquals("List", param1Type.getSimpleName());
	}
	
	/**
	 * Test method test11 from Test class.
	 * 
	 * public <T extends Tester> T[] test11()
	 *
	 */
	public void test11() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test11")) {
				break;
			} else {
				method = null;
			}
		}
		//TODO need to revisit
//		assertNotNull(method);
//		
//		JavaHelpers returnType = method.getReturnType();
//		
//		assertEquals("test.dependents.Tester[]", returnType.getQualifiedName());
//		assertEquals("Tester[]", returnType.getSimpleName());
	}

	/**
	 * Test method test11 from Test class.
	 * 
	 * public <T extends Tester> T[][][] test11()
	 *
	 */
	public void test12() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test12")) {
				break;
			} else {
				method = null;
			}
		}
		//TODO need to revisit
//		assertNotNull(method);
//		
//		JavaHelpers returnType = method.getReturnType();
//		
//		assertEquals("test.dependents.Tester[][][]", returnType.getQualifiedName());
//		assertEquals("Tester[][][]", returnType.getSimpleName());
	}
	/**
	 * Test method test13 from Test class.
	 * 
	 * public void test13(Y param) (where Y is a class-level type parameter
	 *
	 */
	public void test13() {
		JavaHelpers testHelper = reflectClass("test.me.Test");
		JavaClass testClass = (JavaClass) testHelper;
		assertTrue((testClass).isExistingType());
		
		Method method = null;
		List methods = testClass.getMethods();
		for (int i = 0; i < methods.size(); i++) {
			method = (Method) methods.get(i);
			if (method.getName().equals("test13")) {
				break;
			} else {
				method = null;
			}
		}
		assertNotNull(method);
		
		//Verify parameters
		List params = method.getParameters();
		assertTrue(params.size() == 1);
	
		
		// Verify Y param
		JavaParameter param1 = (JavaParameter) params.get(0);
		if (isReflectingSource)
			assertTrue(param1.getName().equals("param"));
		JavaHelpers param1Type = param1.getJavaType();
		assertNotNull(param1Type);
		assertEquals("java.io.Serializable", param1Type.getQualifiedName());
		assertEquals("Serializable", param1Type.getSimpleName());
	}
}
