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
package org.eclipse.jem.tests.proxy;
import java.io.IOException;

import org.eclipse.jem.internal.proxy.common.AmbiguousMethodException;
import org.eclipse.jem.internal.proxy.core.*;

/**
 * @author richkulp
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestStandard extends AbstractTestProxy {

	/**
	 * 
	 */
	public TestStandard() {
		super();
	}

	/**
	 * @param name
	 */
	public TestStandard(String name) {
		super(name);
	}

	public void testObject() {
		IBeanTypeProxy objectType = proxyTypeFactory.getBeanTypeProxy("java.lang.Object"); //$NON-NLS-1$
		assertNotNull(objectType);
		assertFalse(objectType.isArray());
		assertEquals("java.lang.Object", objectType.getTypeName());
	}
	
	public void testTypeOf() {
		IBeanTypeProxy objectType = proxyTypeFactory.getBeanTypeProxy("java.lang.Object"); //$NON-NLS-1$		
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$
		assertNotNull(integerType);
		assertEquals("java.lang.Integer", integerType.getTypeName());
		assertTrue(integerType.isKindOf(objectType));		
	}
	
	public void testMethodInvoke() throws ThrowableProxy {		
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$		
		IMethodProxy mthd = integerType.getMethodProxy("valueOf", "java.lang.String"); //$NON-NLS-1$ //$NON-NLS-2$
		assertNotNull(mthd);
		// See if we can invoke methods.
		IIntegerBeanProxy anInt = (IIntegerBeanProxy) mthd.invoke(null, proxyFactory.createBeanProxyWith("5")); //$NON-NLS-1$
		assertNotNull(anInt);
		assertEquals(5, anInt.intValue());
		// See if invoke with bad type throws the ExceptionProxy.
		try {
			mthd.invoke(null, proxyFactory.createBeanProxyWith(5));
			fail("Exception not thrown like it should of been.");
		} catch (ThrowableProxy e) {
			// We should of gotton the exception. See if it is of the correct type.
			assertEquals("java.lang.IllegalArgumentException", e.getTypeProxy().getTypeName()); //$NON-NLS-1$
		}						
	}

	public void testInvoke() throws ThrowableProxy {
		// Technically invokables should be used for one-shot usage, but here to test the invoke correctly
		// it will be used twice. This is not an error, just overhead.
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$		
		IInvokable invokable = integerType.getInvokable("valueOf", "java.lang.String"); //$NON-NLS-1$ //$NON-NLS-2$
		assertNotNull(invokable);
		// See if we can invoke methods.
		IIntegerBeanProxy anInt = (IIntegerBeanProxy) invokable.invoke(null, proxyFactory.createBeanProxyWith("5")); //$NON-NLS-1$
		assertNotNull(anInt);
		assertEquals(5, anInt.intValue());
		// See if invoke with bad type throws the ExceptionProxy.
		try {
			invokable.invoke(null, proxyFactory.createBeanProxyWith(5));
			fail("Exception not thrown like it should of been.");
		} catch (ThrowableProxy e) {
			// We should of gotton the exception. See if it is of the correct type.
			assertEquals("java.lang.IllegalArgumentException", e.getTypeProxy().getTypeName()); //$NON-NLS-1$
		}						
	}

	public void testSimpleInitString() throws ThrowableProxy, InstantiationException {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		// See if we can create it from an initialization string.
		IIntegerBeanProxy anInt = (IIntegerBeanProxy) integerType.newInstance("new java.lang.Integer(6)"); //$NON-NLS-1$
		assertNotNull(anInt);
		assertEquals(6, anInt.intValue());

		// See if string initialization works.
		IStringBeanProxy aString = (IStringBeanProxy) proxyTypeFactory.getBeanTypeProxy("java.lang.String").newInstance("\"abcd\""); //$NON-NLS-1$ //$NON-NLS-2$
		assertNotNull(aString);
		assertEquals("abcd", aString.stringValue()); //$NON-NLS-1$
	}
	
	public void testGetConstructors() {
		IBeanTypeProxy stringType = proxyTypeFactory.getBeanTypeProxy("java.lang.String"); //$NON-NLS-1$				
		
		// Get all constructors.
		IConstructorProxy[] ctors = stringType.getConstructors();
		assertNotNull(ctors);
		int expectedCtors = System.getProperty("java.version","").startsWith("1.5") ? 13 : 11;
		assertEquals(expectedCtors, ctors.length);		
	}

	public void testGetDeclaredConstructors() {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				
		
		// Get all constructors.
		IConstructorProxy[] ctors = testAccessType.getDeclaredConstructors();
		assertNotNull(ctors);
		assertEquals(4, ctors.length);		
	}

	public void testSimpleConstructor() throws ThrowableProxy {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		
		// See if we can create it from a constructor.
		IConstructorProxy ctor = integerType.getConstructorProxy(new String[] {"java.lang.String"}); //$NON-NLS-1$
		assertNotNull(ctor);
		IIntegerBeanProxy anInt = (IIntegerBeanProxy) ctor.newInstance(new IBeanProxy[] {proxyFactory.createBeanProxyWith("0")}); //$NON-NLS-1$
		assertEquals(0, anInt.intValue());
						
		// Zero is cached, let's see if that is what we got above.
		IIntegerBeanProxy aZero = proxyFactory.createBeanProxyWith(new Integer(0));
		assertSame(anInt, aZero);
	}

	public void testSimpleDeclaredConstructor() throws ThrowableProxy {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				
		
		// See if we can get a private constructor.
		IConstructorProxy ctor = testAccessType.getDeclaredConstructorProxy(new String[] {"java.lang.RuntimeException"}); //$NON-NLS-1$
		assertNotNull(ctor);
	}

	public void testPrimitiveReturn() throws ThrowableProxy {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		
		// See that we get a primitive back when the method return type is a primitive.
		IMethodProxy mthdPrim = integerType.getMethodProxy("intValue"); //$NON-NLS-1$
		IIntegerBeanProxy anInt = (IIntegerBeanProxy) mthdPrim.invoke(proxyFactory.createBeanProxyWith(5));
		assertEquals("int", anInt.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(5, anInt.intValue());
		
		// See that we get a primitive back when the field is a primitive.
		IFieldProxy fieldPrim = integerType.getFieldProxy("MIN_VALUE"); //$NON-NLS-1$
		anInt = (IIntegerBeanProxy) fieldPrim.get(null);
		assertEquals("int", anInt.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(Integer.MIN_VALUE, anInt.intValue());	
	}
	
	public void testPrimitiveArrayType() {
		// Test Primitive arrays.
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("[I"); //$NON-NLS-1$
		assertNotNull(arrayType);
		assertTrue(arrayType.isArray());
		assertEquals("[I", arrayType.getTypeName()); //$NON-NLS-1$
		assertEquals(proxyTypeFactory.getBeanTypeProxy("int"), arrayType.getComponentType()); //$NON-NLS-1$
	}
	
	public void testObjectArrayType() {
		// Test simple object type array
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("[Ljava.lang.Integer;"); //$NON-NLS-1$
		assertNotNull(arrayType);
		assertTrue(arrayType.isArray());
		assertEquals("[Ljava.lang.Integer;", arrayType.getTypeName()); //$NON-NLS-1$
		assertEquals(proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"), arrayType.getComponentType()); //$NON-NLS-1$		
	}
	
	public void testPrimitive2DArrayType() {
		// Test two dimension primitive array
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("[[I"); //$NON-NLS-1$
		assertNotNull(arrayType);
		assertTrue(arrayType.isArray());
		assertEquals("[[I", arrayType.getTypeName()); //$NON-NLS-1$
		assertEquals(proxyTypeFactory.getBeanTypeProxy("[I"), arrayType.getComponentType()); //$NON-NLS-1$
		
		// Test construction using accessor.
		arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("int", 2); //$NON-NLS-1$
		assertNotNull(arrayType);
		assertTrue(arrayType.isArray());
		assertEquals("[[I", arrayType.getTypeName()); //$NON-NLS-1$
	}
	
	public void testObject2DArrayType() {
		// Test two dimension object type array
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("[[Ljava.lang.Integer;"); //$NON-NLS-1$
		assertNotNull(arrayType);
		assertTrue(arrayType.isArray());
		assertEquals("[[Ljava.lang.Integer;", arrayType.getTypeName()); //$NON-NLS-1$
		assertEquals(proxyTypeFactory.getBeanTypeProxy("[Ljava.lang.Integer;"), arrayType.getComponentType()); //$NON-NLS-1$
		
		// Test get type using accessor.
		arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 2); //$NON-NLS-1$
		assertNotNull(arrayType);
		assertTrue(arrayType.isArray());
		assertEquals("[[Ljava.lang.Integer;", arrayType.getTypeName()); //$NON-NLS-1$		
	}
	
	public void testArrayTypeFromArrayType() {
		// Test creating an array type from an array type, i.e. adding dimensions.
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("[Ljava.lang.Integer;", 2); //$NON-NLS-1$
		assertNotNull(arrayType);
		assertTrue(arrayType.isArray());
		assertEquals("[[[Ljava.lang.Integer;", arrayType.getTypeName()); //$NON-NLS-1$
	}
	
	public void testArrayFromAccessorSimpleType() throws ThrowableProxy {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		
		// Test creating an array with accessor of more than one dimension with component type not an array.
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(integerType, new int[] {3, 4});
		assertNotNull(arrayProxy);
		assertEquals("[[Ljava.lang.Integer;", arrayProxy.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(3, arrayProxy.getLength());
		
		// get(0) should be an Integer[4].
		arrayProxy = (IArrayBeanProxy) arrayProxy.get(0);
		assertNotNull(arrayProxy);
		assertEquals(4, arrayProxy.getLength());				
	}
	
	public void testArrayFromAccessorArrayType() throws ThrowableProxy {
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 1); //$NON-NLS-1$
		
		// Test creating an array with accessor but component type is an array.		
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(arrayType, new int[] {1});
		assertNotNull(arrayProxy);
		assertEquals("[Ljava.lang.Integer;", arrayProxy.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(1, arrayProxy.getLength());
	}
	
	public void testArrayFromAccessorArrayTypeMultiDim() throws ThrowableProxy {
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 1); //$NON-NLS-1$

		// Test creating an array with accessor but component type is an array, but extend and add a dimension.		
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(arrayType, new int[] {2 ,3});
		assertNotNull(arrayProxy);
		assertEquals("[[Ljava.lang.Integer;", arrayProxy.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(2, arrayProxy.getLength());

		// Test first entry is an Integer[3]		
		IArrayBeanProxy idx1 = (IArrayBeanProxy) arrayProxy.get(0);
		assertNotNull(idx1);
		assertEquals(3, idx1.getLength());
		
		// Test [1][2] is null.
		IBeanProxy idx2 = arrayProxy.get(new int [] {1,2});
		assertNull(idx2);
	}
	
	public void testArraySet() throws ThrowableProxy {
		IBeanProxy anInt = proxyFactory.createBeanProxyWith(new java.lang.Integer(23));
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 1); //$NON-NLS-1$
		
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(arrayType, new int[] {2 ,3});
		assertNotNull(arrayProxy);
		assertEquals("[[Ljava.lang.Integer;", arrayProxy.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(2, arrayProxy.getLength());

		// Test first entry is an Integer[3]		
		IArrayBeanProxy idx1 = (IArrayBeanProxy) arrayProxy.get(0);
		
		// Test set into array idx1, but retrieve from arrayProxy. Tests
		//   1) Set with one index into array from outer array[0]
		//   2) Get with multi index from outer array
		//   3) And that the same array is set that exists within the first array.
		idx1.set(anInt, 2);
		IBeanProxy idx3 = arrayProxy.get(new int[] {0, 2});
		assertNotNull(idx3);
		assertEquals("java.lang.Integer", idx3.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(anInt, idx3);
		
		// Inverse test of above, multi set, single get of same array.
		arrayProxy.set(anInt, new int[] {0,1});
		assertEquals(anInt, idx1.get(1));
	}

	public void testArraySnapshot2DimArray() throws ThrowableProxy {
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 2); //$NON-NLS-1$
		
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(arrayType, new int[] {2, 3});
		assertNotNull(arrayProxy);

		// Get the two entries which are Integer[3]		
		IArrayBeanProxy idx0 = (IArrayBeanProxy) arrayProxy.get(0);
		IArrayBeanProxy idx1 = (IArrayBeanProxy) arrayProxy.get(1);
		
		// Now get the snapshot and see if the entries are idx0 and idx1.
		IBeanProxy[] snapshot = arrayProxy.getSnapshot();
		assertEquals(idx0, snapshot[0]);
		assertEquals(idx1, snapshot[1]);
	}

	public void testArraySnapshotPrimitiveArray() throws ThrowableProxy {
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("int", 1); //$NON-NLS-1$
		
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(arrayType, new int[] {2});
		assertNotNull(arrayProxy);

		// Set the two entries to int values.
		arrayProxy.set(proxyFactory.createBeanProxyWith(2), 0);
		arrayProxy.set(proxyFactory.createBeanProxyWith(3), 1);
		IIntegerBeanProxy idx0 = (IIntegerBeanProxy) arrayProxy.get(0);
		IIntegerBeanProxy idx1 = (IIntegerBeanProxy) arrayProxy.get(1);
		assertEquals(2, idx0.intValue());
		assertEquals(3, idx1.intValue());
		
		// Now get the snapshot and see if the entries are idx0 and idx1.
		IBeanProxy[] snapshot = arrayProxy.getSnapshot();
		assertEquals(idx0, snapshot[0]);
		assertEquals(idx1, snapshot[1]);
	}

	public void testEmptyArray() throws ThrowableProxy {
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 1); //$NON-NLS-1$

		// Create an empty one dimensional array		
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(arrayType, null);
		assertNotNull(arrayProxy);
		assertEquals("[Ljava.lang.Integer;", arrayProxy.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(0, arrayProxy.getLength());
	}
	
	public void testEmpty2DArray() throws ThrowableProxy {
		// Create an emtpy two dimensional array		
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 2), null); //$NON-NLS-1$
		assertNotNull(arrayProxy);
		assertEquals("[[Ljava.lang.Integer;", arrayProxy.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(0, arrayProxy.getLength());		
	}
	
	public void test2DArray() throws ThrowableProxy {
		// Create a 2D array with three empty entries in dim 0. 
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("java.lang.Integer", 2), 3); //$NON-NLS-1$
		assertNotNull(arrayProxy);
		assertEquals("[[Ljava.lang.Integer;", arrayProxy.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(3, arrayProxy.getLength());
		// Array entry should be empty.
		IArrayBeanProxy idx1 = (IArrayBeanProxy) arrayProxy.get(0);
		assertNull(idx1);		
	}
	
	public void testPrimitiveArray() throws ThrowableProxy {
		// Make sure that if the final component type is an
		// int (primitive) that we get a primitive proxy back and not an Integer object.		
		IArrayBeanTypeProxy arrayType = (IArrayBeanTypeProxy) proxyTypeFactory.getBeanTypeProxy("int", 1); //$NON-NLS-1$
		IArrayBeanProxy arrayProxy = proxyFactory.createBeanProxyWith(arrayType, 3);
		arrayProxy.set(proxyFactory.createBeanProxyWith(44), 1);
		IBeanProxy idx1 = arrayProxy.get(1);
		assertNotNull(idx1);
		assertEquals("int", idx1.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals(44, ((IIntegerBeanProxy) idx1).intValue());
	}
	
	public void testCollections() throws ThrowableProxy {
		IBeanTypeProxy vectorType = proxyTypeFactory.getBeanTypeProxy("java.util.Vector"); //$NON-NLS-1$
		IBeanProxy vectorProxy = vectorType.newInstance();
		CollectionBeanProxyWrapper col = new CollectionBeanProxyWrapper(vectorProxy);
		
		// Test adding
		assertTrue(col.add(proxyFactory.createBeanProxyWith(33)));
		assertEquals(1, col.size());
			
		// Test toArray, first entry should be 33 and length should be 1.
		IArrayBeanProxy arrayOut = col.toArray();
		assertEquals(1, arrayOut.getLength());
		assertEquals(33, ((IIntegerBeanProxy) arrayOut.get(0)).intValue());
		
		// Test toArray(array).
		IArrayBeanProxy arrayInto = proxyFactory.createBeanProxyWith(arrayOut.getTypeProxy(), arrayOut.getLength()); //$NON-NLS-1$
		IArrayBeanProxy arrayOut2 = col.toArray(arrayInto);
		// It should of been the exact same array (arrayInto == arrayOut2 under the proxies).
		assertTrue(arrayInto.sameAs(arrayOut2));
		assertEquals(33, ((IIntegerBeanProxy) arrayOut.get(0)).intValue());
		
		// Test setting with wrong type throws exception.
		try {
			// Test the setting with wrong type throws an exception
			col = new CollectionBeanProxyWrapper(arrayOut);
			fail("ClassCastException should of been thrown.");
		} catch (ClassCastException e) {
			// If here, then the test was passed
		}
	}
	
	public void testIterator() throws ThrowableProxy {
		IBeanTypeProxy vectorType = proxyTypeFactory.getBeanTypeProxy("java.util.Vector"); //$NON-NLS-1$
		IBeanProxy vectorProxy = vectorType.newInstance();
		CollectionBeanProxyWrapper col = new CollectionBeanProxyWrapper(vectorProxy);
		col.add(proxyFactory.createBeanProxyWith(33));		
		
		// See if the iterator interface works.
		IteratorBeanProxyWrapper itrProxy = col.iterator();
		assertNotNull(itrProxy);
		int iterations = 0;
		while (itrProxy.hasNext()) {
			iterations++;
			assertTrue(iterations < 2);
			IBeanProxy itrValue = itrProxy.next();
			assertEquals(33, ((IIntegerBeanProxy) itrValue).intValue());
		}
	}
	
	public void testListInterface() throws ThrowableProxy {
		IBeanTypeProxy vectorType = proxyTypeFactory.getBeanTypeProxy("java.util.Vector"); //$NON-NLS-1$
		IBeanProxy vectorProxy = vectorType.newInstance();
		
		// See if the list inteface works.
		ListBeanProxyWrapper list = new ListBeanProxyWrapper(vectorProxy);
		assertTrue(list.add(proxyFactory.createBeanProxyWith(33)));	// First entry.
		list.add(0, proxyFactory.createBeanProxyWith(true));	// Now becomes first entry.
		assertEquals(2, list.size());
		assertEquals(true, ((IBooleanBeanProxy) list.get(0)).booleanValue());
		assertEquals(33, ((IIntegerBeanProxy) list.get(1)).intValue());	// Swapped to second entry due to add(0,...)		
	}
	
	public void testListIterator() throws ThrowableProxy {
		IBeanTypeProxy vectorType = proxyTypeFactory.getBeanTypeProxy("java.util.Vector"); //$NON-NLS-1$
		IBeanProxy vectorProxy = vectorType.newInstance();
		ListBeanProxyWrapper list = new ListBeanProxyWrapper(vectorProxy);
		list.add(proxyFactory.createBeanProxyWith(33));	// First entry.
		list.add(0, proxyFactory.createBeanProxyWith(true));	// Now becomes first entry.

		// See if the ListIterator interface works
		ListIteratorBeanProxyWrapper lItrProxy = list.listIterator();
		assertNotNull(lItrProxy);
		assertTrue(lItrProxy.hasNext());
		IBooleanBeanProxy bool1 = (IBooleanBeanProxy) lItrProxy.next();
		assertEquals(true, bool1.booleanValue());
		IIntegerBeanProxy int1 = (IIntegerBeanProxy) lItrProxy.next();
		assertEquals(33, int1.intValue());	// Old first entry moved to second entry by add(0,...) above.
		assertFalse(lItrProxy.hasNext());
		assertEquals(1, lItrProxy.previousIndex());
	}
	
	public void testMethodAccessors() throws ThrowableProxy {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		
		// Test able to access beantype proxy and invoke methods on it. This isn't
		// the approved way of getting methods, but it tests that method proxies
		// are created correctly if returned from an invoke method.
		IBeanTypeProxy classTypeProxy = proxyTypeFactory.getBeanTypeProxy("java.lang.Class"); //$NON-NLS-1$
		IMethodProxy getMethodMethod = classTypeProxy.getMethodProxy("getMethod", new String[] {"java.lang.String", "[Ljava.lang.Class;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		// Get the Integer.byteValue() method through an invoke instead of a method factory or bean type.
		IBeanProxy method = getMethodMethod.invoke(integerType, new IBeanProxy[] {
			proxyFactory.createBeanProxyWith("byteValue"), null}); //$NON-NLS-1$
		assertNotNull(method);
		assertTrue(method instanceof IMethodProxy);
		
		// Now invoke it to see if correct answer comes back.
		IBeanProxy byteValue = ((IMethodProxy) method).invoke(proxyFactory.createBeanProxyWith(new Integer(254)));
		assertNotNull(byteValue);
		assertEquals("byte", byteValue.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals((byte)254, ((INumberBeanProxy) byteValue).byteValue());
	}
	
	public void testInvokableAccessors() throws ThrowableProxy {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		
		// Test able to access beantype proxy and invoke methods on it. This isn't
		// the approved way of getting methods, but it tests that method proxies
		// are created correctly if returned from an invoke method.
		IBeanTypeProxy classTypeProxy = proxyTypeFactory.getBeanTypeProxy("java.lang.Class"); //$NON-NLS-1$
		IInvokable getMethodInvokable = classTypeProxy.getInvokable("getMethod", new String[] {"java.lang.String", "[Ljava.lang.Class;"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		// Get the Integer.byteValue() method through an invoke instead of a method factory or bean type.
		IBeanProxy method = getMethodInvokable.invoke(integerType, new IBeanProxy[] {
			proxyFactory.createBeanProxyWith("byteValue"), null}); //$NON-NLS-1$
		assertNotNull(method);
		assertTrue(method instanceof IMethodProxy);
		
		// Now invoke it to see if correct answer comes back.
		IBeanProxy byteValue = ((IMethodProxy) method).invoke(proxyFactory.createBeanProxyWith(new Integer(254)));
		assertNotNull(byteValue);
		assertEquals("byte", byteValue.getTypeProxy().getTypeName()); //$NON-NLS-1$
		assertEquals((byte)254, ((INumberBeanProxy) byteValue).byteValue());
	}	
	
	public void testCallback() throws ThrowableProxy {
		System.out.println("--- Starting the callback test ---"); //$NON-NLS-1$
		IBeanTypeProxy callbackType = proxyTypeFactory.getBeanTypeProxy("org.eclipse.jem.tests.proxy.vm.TestCallback"); //$NON-NLS-1$
		assertNotNull(callbackType);
		Object testObject = new Object();
		IBeanProxy callbackProxy = callbackType.newInstance();
		TestCallback cb = new TestCallback(testObject, registry);
		registry.getCallbackRegistry().registerCallback(callbackProxy, cb);
		IInvokable start = callbackType.getInvokable("start");	//$NON-NLS-1$
		IInvokable stop = callbackType.getInvokable("stop");	//$NON-NLS-1$
		synchronized(testObject) {
			start.invokeCatchThrowableExceptions(callbackProxy);
			try {
				testObject.wait(30000);	// Should be done by 30 seconds.
			} catch (InterruptedException e) {
			}
		}				
		stop.invokeCatchThrowableExceptions(callbackProxy);
		registry.getCallbackRegistry().deregisterCallback(callbackProxy);				
		cb.testCompleted();
		System.out.println("If there is anything in the .log file, then the test failed.");			 //$NON-NLS-1$		
	}
	
	public void testCallbackStream() throws ThrowableProxy, IOException {
		System.out.println("--- Starting the callback stream test ---"); //$NON-NLS-1$
		IBeanTypeProxy callbackType = proxyTypeFactory.getBeanTypeProxy("org.eclipse.jem.tests.proxy.vm.TestCallbackStream"); //$NON-NLS-1$
		assertNotNull(callbackType);
		Object testObject = new Object();
		IBeanProxy callbackProxy = callbackType.newInstance();
		TestCallbackStream cb = new TestCallbackStream(testObject);
		registry.getCallbackRegistry().registerCallback(callbackProxy, cb);
		IMethodProxy start = callbackType.getMethodProxy("start");							 //$NON-NLS-1$
		synchronized(testObject) {			
			start.invokeCatchThrowableExceptions(callbackProxy);
		try {
				testObject.wait(30000);	// Should be done by 30 seconds.
			} catch (InterruptedException e) {
			}	
		}		
		registry.getCallbackRegistry().deregisterCallback(callbackProxy);				
		cb.testComplete();
		System.out.println("If there is anything in the .log file, then the test failed.");			 //$NON-NLS-1$		
	}
	
	public void testSimpleGetField() throws ThrowableProxy {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		
		// Get the public field.
		IFieldProxy field = integerType.getFieldProxy("MAX_VALUE"); //$NON-NLS-1$
		assertNotNull(field);
	}

	public void testSimpleGetDeclaredField() throws ThrowableProxy {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"); //$NON-NLS-1$				
		
		// Get the public field.
		IFieldProxy field = integerType.getDeclaredFieldProxy("value"); //$NON-NLS-1$
		assertNotNull(field);
	}

	public void testGetFields() {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				
		
		// Get all fields.
		IFieldProxy[] fields = testAccessType.getFields();
		assertNotNull(fields);
		assertEquals(2, fields.length);		
	}

	public void testGetDeclaredFields() {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				
		
		// Get all fields.
		IFieldProxy[] fields = testAccessType.getDeclaredFields();
		assertNotNull(fields);
		assertEquals(3, fields.length);		
	}
	
	public void testSimpleGetMethod() throws ThrowableProxy {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$	
		
		// Get the public field.
		IMethodProxy method = testAccessType.getMethodProxy("xyz"); //$NON-NLS-1$
		assertNotNull(method);
	}

	public void testSimpleGetDeclaredMethod() throws ThrowableProxy {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$	
		
		// Get the public field.
		IMethodProxy method = testAccessType.getDeclaredMethodProxy("qxr", (String[]) null); //$NON-NLS-1$
		assertNotNull(method);
	}

	public void testGetMethods() {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$
		IBeanTypeProxy testObjectType = proxyTypeFactory.getBeanTypeProxy("java.lang.Object"); //$NON-NLS-1$
		
		// Get all methods. Need to get all of Object too since getMethods() gets them all including inherited. We will
		// then take the diff to show what's only at the local level.
		IMethodProxy[] methods = testAccessType.getMethods();
		IMethodProxy[] objectMethods = testObjectType.getMethods();
		assertNotNull(methods);
		assertNotNull(objectMethods);
		assertEquals(7, methods.length-objectMethods.length);		
	}

	public void testGetDeclaredMethods() {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				
		
		// Get all fields.
		IMethodProxy[] methods = testAccessType.getDeclaredMethods();
		assertNotNull(methods);
		assertEquals(8, methods.length);		
	}

	public void testFindCompatibleConstructor() throws AmbiguousMethodException, NoSuchMethodException, IllegalAccessException {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				

		IConstructorProxy ctor = testAccessType.getCompatibleConstructor(new IBeanTypeProxy[] {proxyTypeFactory.getBeanTypeProxy("java.lang.ArrayStoreException")});
		IConstructorProxy comp = testAccessType.getDeclaredConstructorProxy(new IBeanTypeProxy[] {proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException")});
		
		assertNotNull(ctor);
		assertNotNull(comp);
		assertEquals(ctor, comp);
	}
	
	public void testFindCompatibleMethod() throws AmbiguousMethodException, NoSuchMethodException {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				

		IMethodProxy method = testAccessType.getCompatibleMethod("xyz", new IBeanTypeProxy[] {proxyTypeFactory.getBeanTypeProxy("java.lang.Integer")});
		IMethodProxy comp = testAccessType.getDeclaredMethodProxy("xyz", new IBeanTypeProxy[] {proxyTypeFactory.getBeanTypeProxy("java.lang.Number")});
		
		assertNotNull(method);
		assertNotNull(comp);
		assertEquals(method, comp);
	}
	
	public void testFindAmbiguousMethod() throws NoSuchMethodException {
		IBeanTypeProxy testAccessType = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess"); //$NON-NLS-1$				

		try {
			testAccessType.getCompatibleMethod("ddd", new IBeanTypeProxy[] {proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"), proxyTypeFactory.getBeanTypeProxy("java.lang.Integer")});
			fail("Should of been ambiguous");
		} catch (AmbiguousMethodException e) {
		} 
	}
}
