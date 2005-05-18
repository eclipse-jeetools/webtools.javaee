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
 *  $RCSfile: ExpressionTest.java,v $
 *  $Revision: 1.8 $  $Date: 2005/05/18 18:41:17 $ 
 */
package org.eclipse.jem.tests.proxy;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.jem.internal.proxy.common.AmbiguousMethodException;
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent;
import org.eclipse.jem.internal.proxy.ide.IDEStandardBeanProxyFactory;
import org.eclipse.jem.internal.proxy.initParser.tree.*;
 
/**
 * 
 * @since 1.0.0
 */
public class ExpressionTest extends AbstractTestProxy {

	/**
	 * 
	 * 
	 * @since 1.0.0
	 */
	public ExpressionTest() {
		super();
	}

	/**
	 * @param name
	 * 
	 * @since 1.0.0
	 */
	public ExpressionTest(String name) {
		super(name);
	}
	
	public void testIProxyBeanTypeProxy() throws CoreException, IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// Test that proxy bean type is first an expression proxy and after expression evaluation does it become a real proxy. 
		// Can't test that it doesn't become a real proxy before evaluation because it may become resolved under the covers. For
		// example IDE expression immediately resolves it.
		//
		// First we must make sure we have a clean registry because it could of been resolved from a previous test.
		recreateRegistry();
		
		// Now do an expression and get a proxy.
		IExpression exp = proxyFactory.createExpression();
		IProxyBeanType beanTypeProxy = proxyTypeFactory.getBeanTypeProxy(exp, "testPackage.TestAccess");
		assertNotNull(beanTypeProxy);
		assertTrue(beanTypeProxy.isExpressionProxy());
		final IBeanTypeProxy[] resolution = new IBeanTypeProxy[1];
		((ExpressionProxy) beanTypeProxy).addProxyListener(new ExpressionProxy.ProxyListener() {
			public void proxyResolved(ProxyEvent event) {
				resolution[0] = (IBeanTypeProxy) event.getProxy();
			}
			public void proxyVoid(ProxyEvent event) {
				fail("Proxy should not be void.");
			}
			public void proxyNotResolved(ProxyEvent event) {
				fail("Proxy should of resolved.");
			}
		});
		
		exp.invokeExpression();
		assertNotNull(resolution[0]);
		IBeanTypeProxy beanTypeProxy2 = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess");
		assertNotNull(beanTypeProxy2);
		assertTrue(beanTypeProxy2.isBeanProxy());
		assertTrue(beanTypeProxy2.isValid());
		assertSame(beanTypeProxy2, resolution[0]);	// In case of beantypes we will get identical ones back (identity not just equals).
	}

	public void testIProxyMethodProxy() throws CoreException, IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// Test that proxy methode is first an expression proxy and after expression evaluation does it become a real proxy. 
		// Can't test that it doesn't become a real proxy before evaluation because it may become resolved under the covers. For
		// example IDE expression immediately resolves it.
		//
		// First we must make sure we have a clean registry because it could of been resolved from a previous test.
		recreateRegistry();
		
		// Now do an expression and get a proxy.
		IExpression exp = proxyFactory.createExpression();
		IProxyBeanType beanTypeProxy = proxyTypeFactory.getBeanTypeProxy(exp, "testPackage.TestAccess");
		IProxyMethod methodProxy = beanTypeProxy.getMethodProxy(exp, "xyz");
		assertNotNull(methodProxy);
		assertTrue(methodProxy.isExpressionProxy());
		final IMethodProxy[] resolution = new IMethodProxy[1];
		final boolean[] wasVoid = new boolean[1];
		((ExpressionProxy) methodProxy).addProxyListener(new ExpressionProxy.ProxyListener() {
			public void proxyResolved(ProxyEvent event) {
				resolution[0] = (IMethodProxy) event.getProxy();
			}
			public void proxyVoid(ProxyEvent event) {
				wasVoid[0] = true;
			}
			public void proxyNotResolved(ProxyEvent event) {
			}
		});
		IProxyMethod methodProxy2 = beanTypeProxy.getMethodProxy(exp, "xyz");
		assertSame(methodProxy, methodProxy2);	// It should return same expression proxy each time.
		
		exp.createMethodInvocation(ForExpression.ROOTEXPRESSION, methodProxy, true, 0);
		exp.createClassInstanceCreation(ForExpression.METHOD_RECEIVER, beanTypeProxy, 0);
		exp.invokeExpression();
		
		assertFalse(wasVoid[0]);
		assertNotNull(resolution[0]);
		IMethodProxy methodProxy3 = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess").getMethodProxy("xyz");
		assertNotNull(methodProxy3);
		assertTrue(methodProxy3.isBeanProxy());
		assertTrue(methodProxy3.isValid());
		// Can't test identity of methodproxies because some registries (IDE for example) create a new method proxy each time.
	}

	public void testIProxyFieldProxy() throws CoreException, IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// Test that proxy field is first an expression proxy and after expression evaluation does it become a real proxy. 
		// Can't test that it doesn't become a real proxy before evaluation because it may become resolved under the covers. For
		// example IDE expression immediately resolves it.
		//
		// First we must make sure we have a clean registry because it could of been resolved from a previous test.
		recreateRegistry();
		
		// Now do an expression and get a proxy.
		IExpression exp = proxyFactory.createExpression();
		IProxyBeanType beanTypeProxy = proxyTypeFactory.getBeanTypeProxy(exp, "testPackage.TestAccess");
		IProxyField fieldProxy = beanTypeProxy.getFieldProxy(exp, "STATIC_FIELD");
		assertNotNull(fieldProxy);
		assertTrue(fieldProxy.isExpressionProxy());
		final IFieldProxy[] resolution = new IFieldProxy[1];
		final boolean[] wasVoid = new boolean[1];
		((ExpressionProxy) fieldProxy).addProxyListener(new ExpressionProxy.ProxyListener() {
			public void proxyResolved(ProxyEvent event) {
				resolution[0] = (IFieldProxy) event.getProxy();
			}
			public void proxyVoid(ProxyEvent event) {
				wasVoid[0] = true;
			}
			public void proxyNotResolved(ProxyEvent event) {
			}
		});
		
		IProxyField fieldProxy2 = beanTypeProxy.getFieldProxy(exp, "STATIC_FIELD");
		assertSame(fieldProxy, fieldProxy2);	// It should return same expression proxy each time.
		
		exp.createFieldAccess(ForExpression.ROOTEXPRESSION, fieldProxy, true);
		exp.createClassInstanceCreation(ForExpression.FIELD_RECEIVER, beanTypeProxy, 0);
		IBeanProxy v = exp.getExpressionValue();
		assertNotNull(v);
		assertEquals(3, ((IIntegerBeanProxy) v).intValue());
		
		assertFalse(wasVoid[0]);
		assertNotNull(resolution[0]);
		IFieldProxy fieldProxy3 = proxyTypeFactory.getBeanTypeProxy("testPackage.TestAccess").getFieldProxy("STATIC_FIELD");
		assertNotNull(fieldProxy3);
		assertTrue(fieldProxy3.isBeanProxy());
		assertTrue(fieldProxy3.isValid());
		// Can't test identity of methodproxies because some registries (IDE for example) create a new method proxy each time.
	}

	public void testCastStringType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(ForExpression.ROOTEXPRESSION, "short");
		exp.createPrimitiveLiteral(ForExpression.CAST_EXPRESSION, 10l);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getTypeName());
		assertEquals((short) 10, ((INumberBeanProxy) result).shortValue());
	}
	
	public void testCastProxyType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(ForExpression.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("short"));
		exp.createPrimitiveLiteral(ForExpression.CAST_EXPRESSION, 'a');
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getTypeName());
		assertEquals((short) 'a', ((INumberBeanProxy) result).shortValue());	
	}
	
	public void testCastError() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(ForExpression.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("short"));
		try {
			exp.createProxyExpression(ForExpression.CAST_EXPRESSION, proxyFactory.createBeanProxyWith(Boolean.TRUE));
			exp.getExpressionValue();
			fail("Should of thrown ClassCastException");
		} catch (ThrowableProxy e) {
			if (!e.getTypeProxy().getFormalTypeName().equals("java.lang.ClassCastException"))
				throw e;	// Some other exception, rethrow it.
		}
	}	
	
	public void testCastFailed() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(ForExpression.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("short"));
		try {
			exp.getExpressionValue();
			fail("Should of gotton IllegalStateException");
		} catch (IllegalStateException e) {
			System.out.println("From testCastFailed (This is successful): "+e.getLocalizedMessage());
		}		
	}

	public void testInstanceofStringType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(ForExpression.ROOTEXPRESSION, "java.lang.String");
		exp.createStringLiteral(ForExpression.INSTANCEOF_VALUE, "asdf");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testInstanceofProxyType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(ForExpression.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("java.lang.String"));
		exp.createStringLiteral(ForExpression.INSTANCEOF_VALUE, "asdf");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true, ((IBooleanBeanProxy) result).booleanValue());
		
	}
	
	public void testInstanceofFailed() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(ForExpression.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("java.lang.String"));
		try {
			exp.getExpressionValue();
			fail("Should of gotton IllegalStateException");
		} catch (IllegalStateException e) {
			System.out.println("From testInstanceofFailed (This is successful): "+e.getLocalizedMessage());
		}		
	}
	
	public void testTypeLiteral() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createTypeLiteral(ForExpression.ROOTEXPRESSION, "java.lang.String");
		IBeanProxy result = exp.getExpressionValue();
		assertTrue("Not a bean type proxy.", result instanceof IBeanTypeProxy);
		IBeanTypeProxy type = (IBeanTypeProxy) result;
		assertTrue("Not valid.", type.isValid());
		assertEquals("java.lang.String", type.getFormalTypeName());
	}

	public void testTypeFails() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createTypeLiteral(ForExpression.ROOTEXPRESSION, "java.lang.Stng");
			exp.getExpressionValue();
			fail("Should not of gotton a result.");
		} catch (ThrowableProxy e) {
			System.out.println("Test successful: "+e.getProxyLocalizedMessage());
		}
	}
	
	public void testNesting() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(ForExpression.ROOTEXPRESSION, "short");
		exp.createCastExpression(ForExpression.INSTANCEOF_VALUE, "short");
		exp.createPrimitiveLiteral(ForExpression.CAST_EXPRESSION, (short) 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testPrefixPlus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(ForExpression.ROOTEXPRESSION, PrefixOperator.PRE_PLUS);
		exp.createPrimitiveLiteral(ForExpression.PREFIX_OPERAND, 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(10, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testPrefixMinus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(ForExpression.ROOTEXPRESSION, PrefixOperator.PRE_MINUS);
		exp.createPrimitiveLiteral(ForExpression.PREFIX_OPERAND, 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-10, ((INumberBeanProxy) result).intValue());
		
	}

	public void testPrefixMinusChar() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(ForExpression.ROOTEXPRESSION, PrefixOperator.PRE_MINUS);
		exp.createPrimitiveLiteral(ForExpression.PREFIX_OPERAND, 'a');
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-'a', ((INumberBeanProxy) result).intValue());
		
	}

	public void testPrefixComplement() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(ForExpression.ROOTEXPRESSION, PrefixOperator.PRE_COMPLEMENT);
		exp.createPrimitiveLiteral(ForExpression.PREFIX_OPERAND, (short) 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(~10, ((INumberBeanProxy) result).intValue());
		
	}

	public void testPrefixNot() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(ForExpression.ROOTEXPRESSION, PrefixOperator.PRE_NOT);
		exp.createPrimitiveLiteral(ForExpression.PREFIX_OPERAND, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false, ((IBooleanBeanProxy) result).booleanValue());
		
	}

	public void testPrefixFail() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(ForExpression.ROOTEXPRESSION, PrefixOperator.PRE_NOT);
		try {
			exp.createPrimitiveLiteral(ForExpression.PREFIX_OPERAND, 10);
			exp.getExpressionValue();
			fail("Should of failed.");
		} catch (ThrowableProxy e) {
			System.out.println("Test was successful: "+e.getProxyLocalizedMessage());
		}
		
	}
	
	public void testTimes() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_TIMES, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 4);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(3*4, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testTimesExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_TIMES, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 5d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((3*4*5d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testTimesExtendedNested() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_TIMES, 1);
		exp.createInfixExpression(ForExpression.INFIX_LEFT, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 10);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 5d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals(((10-5)*4*5d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testDivide() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_DIVIDE, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4/2, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testDivideExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_DIVIDE, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((12/3/2d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testRemainder() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_REMAINDER, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4%3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testRemainderExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_REMAINDER, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((12%9%2d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testMinus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4-3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testMinusExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_MINUS, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((12-9-2d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testLeftShift() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_LEFT_SHIFT, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4<<3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testLeftShiftExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_LEFT_SHIFT, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((12<<9<<2), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testRightShiftSigned() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_RIGHT_SHIFT_SIGNED, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-10000000>>3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testRightShiftSignedExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_RIGHT_SHIFT_SIGNED, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((-10000000>>4>>2), ((INumberBeanProxy) result).intValue(), 0);
	}
	public void testRightShiftUnSigned() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_RIGHT_SHIFT_UNSIGNED, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-10000000>>>3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testRightShiftUnSignedExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_RIGHT_SHIFT_UNSIGNED, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((-10000000>>>4>>>2), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testLess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_LESS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(-10000000<3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testLessEquals() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_LESS_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(3<=3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testGreater() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_GREATER, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(-10000000>3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testGreaterEquals() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_GREATER_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 2);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(2>=3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testEqualsPrimitives() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3d);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(3d==3, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testEqualsObjects() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_EQUALS, 0);
		exp.createTypeLiteral(ForExpression.INFIX_LEFT, "java.lang.String");
		exp.createTypeLiteral(ForExpression.INFIX_RIGHT, "java.lang.String");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(String.class == String.class, ((IBooleanBeanProxy) result).booleanValue());
	}	

	public void testNotEqualsPrimitives() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_NOT_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3d);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(3d!=3, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testNotEqualsObjects() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_NOT_EQUALS, 0);
		exp.createTypeLiteral(ForExpression.INFIX_LEFT, "java.lang.String");
		exp.createTypeLiteral(ForExpression.INFIX_RIGHT, "java.lang.String");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(String.class != String.class, ((IBooleanBeanProxy) result).booleanValue());
	}	

	public void testXOR() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_XOR, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5^3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testXORExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_XOR, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 23);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((23^9^2), ((INumberBeanProxy) result).intValue(), 0);
		
	}

	public void testAnd() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_AND, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5&3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testAndExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_AND, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 13);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 15);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 1);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((13&5&1), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testOr() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_OR, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5|2, ((INumberBeanProxy) result).intValue());
	}
	
	public void testOrExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_OR, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 13);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 6);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((13|6|2), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testPlus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5+2, ((INumberBeanProxy) result).intValue());
	}

	public void testPlusExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 2);
		exp.createPrimitiveLiteral(ForExpression.INFIX_EXTENDED, 2l);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("long", result.getTypeProxy().getTypeName());
		assertEquals(5+2+2l, ((INumberBeanProxy) result).intValue());
	}

	public void testPlusStringLeft() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
		exp.createProxyExpression(ForExpression.INFIX_LEFT, proxyFactory.createBeanProxyWith("abc"));
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals("abc"+2, ((IStringBeanProxy) result).stringValue());
	}

	public void testPlusStringRight() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 2);
		exp.createProxyExpression(ForExpression.INFIX_RIGHT, proxyFactory.createBeanProxyWith("abc"));		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals(2+"abc", ((IStringBeanProxy) result).stringValue());
	}
	
	public void testPlusStringLeftNull() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
		exp.createProxyExpression(ForExpression.INFIX_LEFT, proxyFactory.createBeanProxyWith("abc"));
		exp.createNull(ForExpression.INFIX_RIGHT);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals("abc"+null, ((IStringBeanProxy) result).stringValue());
	}
	
	public void testPlusStringRightNull() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
		exp.createNull(ForExpression.INFIX_LEFT);
		exp.createProxyExpression(ForExpression.INFIX_RIGHT, proxyFactory.createBeanProxyWith("abc"));
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals(null+"abc", ((IStringBeanProxy) result).stringValue());
	}

	public void testPlusStringLeftChar() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
		exp.createProxyExpression(ForExpression.INFIX_LEFT, proxyFactory.createBeanProxyWith("abc"));
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 'a');
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals("abc"+'a', ((IStringBeanProxy) result).stringValue());
	}

	public void testPlusStringRightChar() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 'a');
		exp.createProxyExpression(ForExpression.INFIX_RIGHT, proxyFactory.createBeanProxyWith("abc"));
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals('a'+"abc", ((IStringBeanProxy) result).stringValue());
	}

	public void testConditionalAnd() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_AND, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true && true, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_AND, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, true);
		exp.createInfixExpression(ForExpression.INFIX_EXTENDED, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true && true && (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndFalseLast() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_AND, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, false);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true && false, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndFalseFirst() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_AND, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false && true, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndExtendedAndFalseFirst() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_AND, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, true);
		exp.createInfixExpression(ForExpression.INFIX_EXTENDED, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false && true && (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOr() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_OR, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false || true, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOrExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_OR, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, false);
		exp.createInfixExpression(ForExpression.INFIX_EXTENDED, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false || false || (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOrTrueFirst() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_OR, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, false);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true || false, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOrExtendedAndTrueMiddle() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_CONDITIONAL_OR, 1);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, true);
		exp.createInfixExpression(ForExpression.INFIX_EXTENDED, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false || true || (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testArrayAccess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), 1);
		array.set(proxyFactory.createBeanProxyWith((short) 3), 0);
		
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayAccess(ForExpression.ROOTEXPRESSION, 1);
		exp.createProxyExpression(ForExpression.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getTypeName());
		assertEquals(((INumberBeanProxy) result).shortValue(), (short) 3);
	}
	
	public void testArrayAccessSet() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), 1);
		array.set(proxyFactory.createBeanProxyWith((short) 3), 0);
		
		IExpression exp = proxyFactory.createExpression();
		exp.createAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createArrayAccess(ForExpression.ASSIGNMENT_LEFT, 1);
		exp.createProxyExpression(ForExpression.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 0);
		exp.createPrimitiveLiteral(ForExpression.ASSIGNMENT_RIGHT, (short) 33);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getTypeName());
		assertEquals(((INumberBeanProxy) result).shortValue(), (short) 33);
	}
	
	
	public void testMultiArrayAccess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), new int[] {2,1});
		array.set(proxyFactory.createBeanProxyWith((short) 3), new int[]{1,0});
		
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayAccess(ForExpression.ROOTEXPRESSION, 1);
		exp.createProxyExpression(ForExpression.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 1);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short[]", result.getTypeProxy().getFormalTypeName());
	}

	public void testMultiArrayAccessSet() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), new int[] {2,1});
		array.set(proxyFactory.createBeanProxyWith((short) 3), new int[]{1,0});
		
		IExpression exp = proxyFactory.createExpression();
		exp.createAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createArrayAccess(ForExpression.ASSIGNMENT_LEFT, 1);
		exp.createProxyExpression(ForExpression.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 1);
		IArrayBeanProxy newArray = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), new int[] {3});
		exp.createProxyExpression(ForExpression.ASSIGNMENT_RIGHT, newArray);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(3, ((IArrayBeanProxy) result).getLength());
	}

	public void testMultiArrayAccess1() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), new int[] {2,1});
		array.set(proxyFactory.createBeanProxyWith((short) 3), new int[]{1,0});
		
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayAccess(ForExpression.ROOTEXPRESSION, 2);
		exp.createProxyExpression(ForExpression.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYACCESS_INDEX, 0);		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getFormalTypeName());
		assertEquals((short) 3, ((INumberBeanProxy) result).shortValue());
	}
	
	public void testArrayCreation() throws IllegalStateException, IllegalArgumentException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[]", 1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYCREATION_DIMENSION, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
	}
	
	public void testMultiArrayCreation() throws IllegalStateException, IllegalArgumentException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[][]", 2);
		exp.createPrimitiveLiteral(ForExpression.ARRAYCREATION_DIMENSION, 2);
		exp.createPrimitiveLiteral(ForExpression.ARRAYCREATION_DIMENSION, 4);		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
		assertEquals(4, ((IArrayBeanProxy)((IArrayBeanProxy) result).get(0)).getLength());
	}	

	public void testMultiPartialArrayCreation() throws IllegalStateException, IllegalArgumentException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[][][]", 2);
		exp.createPrimitiveLiteral(ForExpression.ARRAYCREATION_DIMENSION, 2);
		exp.createPrimitiveLiteral(ForExpression.ARRAYCREATION_DIMENSION, 4);		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
		assertEquals(4, ((IArrayBeanProxy)((IArrayBeanProxy) result).get(0)).getLength());
		assertNull(((IArrayBeanProxy)((IArrayBeanProxy) result).get(0)).get(0));
	}
	
	public void testArrayInitializerEmpty() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[]", 0);
		exp.createArrayInitializer(0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(0, ((IArrayBeanProxy) result).getLength());
	}
	
	public void testArrayInitializerOneDim() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[]", 0);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYINITIALIZER_EXPRESSION, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((IArrayBeanProxy) result).getLength());
		assertEquals(3, ((INumberBeanProxy) ((IArrayBeanProxy) result).get(0)).intValue());
	}
	
	public void testArrayInitializerTwoDimEmpty() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[][]", 0);
		exp.createArrayInitializer(0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(0, ((IArrayBeanProxy) result).getLength());
	}
	
	public void testArrayInitializerTwoDim() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[][]", 0);
		exp.createArrayInitializer(1);
		exp.createArrayInitializer(0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((IArrayBeanProxy) result).getLength());
		assertEquals(0, ((IArrayBeanProxy) ((IArrayBeanProxy) result).get(0)).getLength());
	}	

	public void testArrayInitializerTwoDimNotEmpty() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "int[][]", 0);
		exp.createArrayInitializer(2);
		exp.createNull(ForExpression.ARRAYINITIALIZER_EXPRESSION);
		exp.createArrayInitializer(2);
		exp.createPrimitiveLiteral(ForExpression.ARRAYINITIALIZER_EXPRESSION, 3);
		exp.createPrimitiveLiteral(ForExpression.ARRAYINITIALIZER_EXPRESSION, 4);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
		assertNull(((IArrayBeanProxy) result).get(0));
		assertEquals(2, ((IArrayBeanProxy) ((IArrayBeanProxy) result).get(1)).getLength());
		assertEquals(4, ((INumberBeanProxy)((IArrayBeanProxy) ((IArrayBeanProxy) result).get(1)).get(1)).intValue());		
	}
	
	public void testArrayInitializerShortInt() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(ForExpression.ROOTEXPRESSION, "short[]", 0);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(ForExpression.ARRAYINITIALIZER_EXPRESSION, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((IArrayBeanProxy) result).getLength());
		assertEquals(3, ((INumberBeanProxy) ((IArrayBeanProxy) result).get(0)).intValue());
	}
	
	public void testClassInstanceCreationDefault() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(ForExpression.ROOTEXPRESSION, "java.lang.Object", 0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Object", result.getTypeProxy().getFormalTypeName());
	}
	
	public void testClassInstanceCreationDefaultWithBeanTypeProxy() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(ForExpression.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("java.lang.Object"), 0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Object", result.getTypeProxy().getFormalTypeName());
	}	
	
	public void testClassInstanceCreationOneArg() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(ForExpression.ROOTEXPRESSION, "java.lang.Integer", 1);
		exp.createStringLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, "3");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Integer", result.getTypeProxy().getFormalTypeName());
		assertEquals(3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testClassInstanceCreationOneArgWithPrimWidening() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(ForExpression.ROOTEXPRESSION, "java.lang.Short", 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, (byte)3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Short", result.getTypeProxy().getFormalTypeName());
		assertEquals((short)3, ((INumberBeanProxy) result).shortValue());
	}	
	
	public void testClassInstanceCreationNullArg() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(ForExpression.ROOTEXPRESSION, "testPackage.TestCtorWithNull", 1);
		exp.createNull(ForExpression.CLASSINSTANCECREATION_ARGUMENT);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("testPackage.TestCtorWithNull", result.getTypeProxy().getFormalTypeName());
	}	
	
	public void testClassInstanceCreationMismatchArg() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(ForExpression.ROOTEXPRESSION, "testPackage.TestCtorWithNull", 1);
		try {
			exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 1);
			exp.getExpressionValue();
			fail("Exception should of been thrown.");
		} catch (ThrowableProxy e) {
			assertEquals("java.lang.NoSuchMethodException", e.getTypeProxy().getFormalTypeName());
		}
	}	
	
	public void testFieldAccessStatic() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createFieldAccess(ForExpression.ROOTEXPRESSION, "RED", true);
		exp.createTypeReceiver("java.awt.Color");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.awt.Color", result.getTypeProxy().getFormalTypeName());
		IBeanTypeProxy colorType = result.getTypeProxy();
		IMethodProxy red = colorType.getMethodProxy("getRed");
		IMethodProxy green = colorType.getMethodProxy("getGreen");
		IMethodProxy blue = colorType.getMethodProxy("getBlue");
		assertEquals(255, ((INumberBeanProxy) red.invoke(result)).intValue());
		assertEquals(0, ((INumberBeanProxy) green.invoke(result)).intValue());
		assertEquals(0, ((INumberBeanProxy) blue.invoke(result)).intValue());
	}
	
	public void testFieldAccessSetStatic() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createFieldAccess(ForExpression.ASSIGNMENT_LEFT, "STATIC_FIELD", true);
		exp.createTypeReceiver("testPackage.TestAccess");
		exp.createPrimitiveLiteral(ForExpression.ASSIGNMENT_RIGHT, 23);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(23, ((INumberBeanProxy) result).intValue());
	}
	
	
	public void testFieldAccessNonStatic() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createFieldAccess(ForExpression.ROOTEXPRESSION, "x", true);
		exp.createClassInstanceCreation(ForExpression.FIELD_RECEIVER, "java.awt.Point", 2);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((INumberBeanProxy) result).intValue());
	}
	
	public void testFieldAccessSetNonStatic() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createFieldAccess(ForExpression.ASSIGNMENT_LEFT, "x", true);
		exp.createClassInstanceCreation(ForExpression.FIELD_RECEIVER, "java.awt.Point", 2);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 2);
		exp.createPrimitiveLiteral(ForExpression.ASSIGNMENT_RIGHT, 23);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(23, ((INumberBeanProxy) result).intValue());
	}	

	public void testFieldAccessSetNonStaticSimple() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// First create using normal proxy stuff, then test the expression processor.
		IBeanTypeProxy pointType = proxyTypeFactory.getBeanTypeProxy("java.awt.Point");
		IFieldProxy xFieldProxy = pointType.getFieldProxy("x");
		IBeanProxy pointProxy = pointType.getConstructorProxy(new String[] {"int", "int"}).newInstance(new IBeanProxy[] {proxyFactory.createBeanProxyWith(1), proxyFactory.createBeanProxyWith(2)});
		
		IExpression exp = proxyFactory.createExpression();
		ExpressionProxy ep = exp.createSimpleFieldSet(xFieldProxy, pointProxy, proxyFactory.createBeanProxyWith(23), true);
		ep.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy result = event.getProxy();
				assertNotNull(result);
				assertEquals("int", result.getTypeProxy().getFormalTypeName());
				assertEquals(23, ((INumberBeanProxy) result).intValue());
			}
		});
		exp.invokeExpression();
	}

	public void testFieldAccessNonStaticSimple() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// First create using normal proxy stuff, then test the expression processor.
		IBeanTypeProxy pointType = proxyTypeFactory.getBeanTypeProxy("java.awt.Point");
		IFieldProxy xFieldProxy = pointType.getFieldProxy("x");
		IBeanProxy pointProxy = pointType.getConstructorProxy(new String[] {"int", "int"}).newInstance(new IBeanProxy[] {proxyFactory.createBeanProxyWith(1), proxyFactory.createBeanProxyWith(2)});
		
		IExpression exp = proxyFactory.createExpression();
		ExpressionProxy ep = exp.createSimpleFieldAccess(xFieldProxy, pointProxy);
		ep.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy result = event.getProxy();
				assertNotNull(result);
				assertEquals("int", result.getTypeProxy().getFormalTypeName());
				assertEquals(1, ((INumberBeanProxy) result).intValue());
			}
		});
		exp.invokeExpression();
	}

	public void testFieldAccessNonStaticWithProxy() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IBeanTypeProxy pointType = proxyTypeFactory.getBeanTypeProxy("java.awt.Point");
		IExpression exp = proxyFactory.createExpression();
		exp.createFieldAccess(ForExpression.ROOTEXPRESSION, pointType.getFieldProxy("x"), true);
		exp.createClassInstanceCreation(ForExpression.FIELD_RECEIVER, "java.awt.Point", 2);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((INumberBeanProxy) result).intValue());
	}	

	public void testMethodInvokeStatic() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createMethodInvocation(ForExpression.ROOTEXPRESSION, "valueOf", true, 1);
		exp.createTypeReceiver("java.lang.String");
		exp.createPrimitiveLiteral(ForExpression.METHOD_ARGUMENT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getFormalTypeName());
		assertEquals("true", ((IStringBeanProxy) result).stringValue());
	}

	public void testMethodInvokeNonStatic() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createMethodInvocation(ForExpression.ROOTEXPRESSION, "compareTo", true, 1);
		exp.createClassInstanceCreation(ForExpression.METHOD_RECEIVER, "java.lang.Integer", 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 3);
		exp.createClassInstanceCreation(ForExpression.METHOD_ARGUMENT, "java.lang.Integer", 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 4);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertTrue("CompareTo wasn't less than 0.", ((INumberBeanProxy) result).intValue() < 0);
	}
	
	public void testMethodInvokeNonStaticWithProxy() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IBeanTypeProxy integerType = proxyTypeFactory.getBeanTypeProxy("java.lang.Integer");
		IExpression exp = proxyFactory.createExpression();
		exp.createMethodInvocation(ForExpression.ROOTEXPRESSION, integerType.getMethodProxy("compareTo", new IBeanTypeProxy[] {integerType}), true, 1);
		exp.createClassInstanceCreation(ForExpression.METHOD_RECEIVER, "java.lang.Integer", 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 3);
		exp.createClassInstanceCreation(ForExpression.METHOD_ARGUMENT, "java.lang.Integer", 1);
		exp.createPrimitiveLiteral(ForExpression.CLASSINSTANCECREATION_ARGUMENT, 4);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertTrue("CompareTo wasn't less than 0.", ((INumberBeanProxy) result).intValue() < 0);
	}
	
	public void testConditionalTrue() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(ForExpression.ROOTEXPRESSION);
		exp.createInfixExpression(ForExpression.CONDITIONAL_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_TRUE, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_FALSE, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(6, ((INumberBeanProxy) result).intValue());
	}
	
	public void testConditionalFalse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(ForExpression.ROOTEXPRESSION);
		exp.createInfixExpression(ForExpression.CONDITIONAL_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_TRUE, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_FALSE, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(0, ((INumberBeanProxy) result).intValue());
	}
	
	public void testNestedConditionalTrueTrue() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(ForExpression.ROOTEXPRESSION);
		exp.createInfixExpression(ForExpression.CONDITIONAL_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createConditionalExpression(ForExpression.CONDITIONAL_TRUE);
		exp.createInfixExpression(ForExpression.CONDITIONAL_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_TRUE, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 4);
		exp.createInfixExpression(ForExpression.CONDITIONAL_FALSE, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_FALSE, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(8, ((INumberBeanProxy) result).intValue());
	}
	
	public void testNestedConditionalFalseFalse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(ForExpression.ROOTEXPRESSION);
		exp.createInfixExpression(ForExpression.CONDITIONAL_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 2);
		exp.createConditionalExpression(ForExpression.CONDITIONAL_TRUE);
		exp.createInfixExpression(ForExpression.CONDITIONAL_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 2);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_TRUE, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 4);
		exp.createInfixExpression(ForExpression.CONDITIONAL_FALSE, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(ForExpression.CONDITIONAL_FALSE, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 5);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((INumberBeanProxy) result).intValue());
	}	
	
	public void testVoidReturnType() throws IllegalStateException, ThrowableProxy {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
			exp.createMethodInvocation(ForExpression.INFIX_RIGHT, "gc", true, 0);
			exp.createTypeReceiver("java.lang.System");
			exp.getExpressionValue();
			fail("Should of received no expression value exception.");
		} catch (NoExpressionValueException e) {
			if (e.getLocalizedMessage() != null)
				System.out.println("Test was successful: "+e.getLocalizedMessage());
		}
	}
	
	public void testWrongReturnType() throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createInfixExpression(ForExpression.ROOTEXPRESSION, InfixOperator.IN_PLUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, 3);
			exp.createMethodInvocation(ForExpression.INFIX_RIGHT, "getSecurityManager", true, 0);
			exp.createTypeReceiver("java.lang.System");
			exp.getExpressionValue();
			fail("Should of received class cast proxy exception.");
		} catch (ThrowableProxy e) {
			if (!e.getTypeProxy().getFormalTypeName().equals("java.lang.IllegalArgumentException"))
				throw e;	// Some other exception, rethrow it.
		}
	}
	
	public void testInvoke() throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createMethodInvocation(ForExpression.ROOTEXPRESSION, "getSecurityManager", true, 0);
		exp.createTypeReceiver("java.lang.System");
		exp.invokeExpression();
	}
	
	public void testInvokeFail() throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createMethodInvocation(ForExpression.ROOTEXPRESSION, "getenv", true, 1);
			exp.createTypeReceiver("java.lang.System");
			exp.createStringLiteral(ForExpression.METHOD_ARGUMENT, "xyz");
			exp.invokeExpression();
		} catch (ThrowableProxy e) {
			if (!e.getTypeProxy().getFormalTypeName().equals("java.lang.Error"))
				throw e;	// Some other exception, rethrow it.
		}
	}
	
	public void testAssignment() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		ExpressionProxy ep = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		final long[] time = new long[1];
		ep.addProxyListener(new ExpressionResolved() {
			
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyAdapter#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				time[0] = ((INumberBeanProxy) event.getProxy()).longValue();
			}
		});
		exp.createMethodInvocation(ForExpression.ASSIGNMENT_RIGHT, "currentTimeMillis", true, 0);
		exp.createTypeReceiver("java.lang.System");
		exp.invokeExpression();
		assertTrue(0L != time[0]);
	}
	
	public void testReassignment() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		ExpressionProxy ep = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		final long[] time = new long[1];
		ep.addProxyListener(new ExpressionResolved() {
			
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyAdapter#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				time[0] = ((INumberBeanProxy) event.getProxy()).longValue();
			}
		});
		exp.createMethodInvocation(ForExpression.ASSIGNMENT_RIGHT, "currentTimeMillis", true, 0);
		exp.createTypeReceiver("java.lang.System");
		exp.createProxyReassignmentExpression(ForExpression.ROOTEXPRESSION, ep);
		exp.createPrimitiveLiteral(ForExpression.ASSIGNMENT_RIGHT, -23L);
		exp.invokeExpression();
		assertEquals(-23L, time[0]);
	}
	
	public void testAssignmentVoid() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		ExpressionProxy ep = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		ExpressionVoid epl = new ExpressionVoid();
		ep.addProxyListener(epl);
		exp.createMethodInvocation(ForExpression.ASSIGNMENT_RIGHT, "testVoid", true, 0);
		exp.createTypeReceiver("testPackage.TestAccess");
		exp.invokeExpression();
		assertTrue(epl.voidCalled);
	}
	
	public void testAssignmentNot() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(ForExpression.ROOTEXPRESSION);
		exp.createPrimitiveLiteral(ForExpression.CONDITIONAL_CONDITION, true);
		exp.createPrimitiveLiteral(ForExpression.CONDITIONAL_TRUE, true);
		ExpressionProxy ep = exp.createProxyAssignmentExpression(ForExpression.CONDITIONAL_FALSE);
		exp.createPrimitiveLiteral(ForExpression.ASSIGNMENT_RIGHT, false);
		ExpressionNotResolved epl = new ExpressionNotResolved();
		ep.addProxyListener(epl);
		exp.invokeExpression();
		assertTrue(epl.notResolvedCalled);
	}

	public void testAssignmentLaterUsage() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// eparg = 3;
		// epInteger = new Integer(eparg+1);
		IExpression exp = proxyFactory.createExpression();
		ExpressionProxy epArg = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION); 
		exp.createPrimitiveLiteral(ForExpression.ASSIGNMENT_RIGHT, 3);
		ExpressionProxy epInteger = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createClassInstanceCreation(ForExpression.ASSIGNMENT_RIGHT, proxyTypeFactory.getBeanTypeProxy("java.lang.Integer"), 1);
		exp.createInfixExpression(ForExpression.CLASSINSTANCECREATION_ARGUMENT, InfixOperator.IN_PLUS, 0);
		exp.createProxyExpression(ForExpression.INFIX_LEFT, epArg);	// Use literal 3+1 as the argument to this.
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 1); 
		epInteger.addProxyListener(new ExpressionResolved() {
			
			
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyAdapter#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals(((INumberBeanProxy) event.getProxy()).intValue(), 4);
			}			
		});
		exp.invokeExpression();
	}
	
	public void testExpressionPerformanceSmall() throws IllegalStateException, NoExpressionValueException, ThrowableProxy, AmbiguousMethodException, NoSuchMethodException {
		// Small performance test. We are testing a small batch of expressions on each run. We then avg. the runs. This tests many small
		// expression processing performance.
		 
		// Prime the proxies so they don't become part of the timings.
		IBeanTypeProxy pointType = proxyTypeFactory.getBeanTypeProxy("java.awt.Point");
		IMethodProxy setLocation = pointType.getMethodProxy("setLocation", new String[] {"int", "int"});
		IMethodProxy getLocation = pointType.getMethodProxy("getLocation");
		//
		// Now for just a simple one method invocation using method proxies (and not method lookup each time) the standard proxy calls
		// will beat out expression processing for Remote. Note that is not true of IDE vm. In that case the standard proxy calls
		// will ALWAYS out perform greatly the expression processer because there is no latency in the communications. It is basically
		// straight direct calls. While expression processing is also direct calls, the call path is much larger because it needs to 
		// build/maintain/execute the processing stack, rather than just execute the command. 
		//
		
		// Try to prime the communication pump. This sends about 36,000 bytes of data.
		// Doing it here means later when needed the communications costs are drastically reduced.
		IExpression exp = proxyFactory.createExpression();
		int i =1000;
		while (i-->0) {
			exp.createArrayCreation(ForExpression.ROOTEXPRESSION, registry.getBeanTypeProxyFactory()
					.getBeanTypeProxy(exp, "java.lang.Object", 1), 0);
			exp.createArrayInitializer(0);
		}
		exp.invokeExpression();
		
		int times = 100;	// Number of times to run the test.
		int batchSize = 10;	// Size of batch for each test.		
		// Now do the actual test.
		long start = System.currentTimeMillis();
		i = times;
		while(i-->0) {
			expressionSetting(pointType, setLocation, getLocation, batchSize);
		}
		
		long expressionTime = System.currentTimeMillis()-start;
		long startNormal = System.currentTimeMillis();
		i = times;
		while(i-->0) {
			normalSetting(pointType, setLocation, getLocation, batchSize);
		}
		long normalTime = System.currentTimeMillis()-startNormal;
		System.out.println("Small Batch results: Batch size="+batchSize+" Number of runs="+times);
		System.out.println("Expression Time: " + expressionTime + " Avg: " + (expressionTime/((double) times)));
		System.out.println("Normal Time: " + normalTime+ " Avg: " + (normalTime/((double) times)));
		if (normalTime == 0)
			normalTime = 1;	// So no divide by zero.
		int improvement = (int) ((1-((double) expressionTime)/normalTime)*100);
		System.out.println("Improvement of expression over normal time: "+improvement+'%');
		// Note that this test is not valid for IDE because the IDE will always be other way around because
		// there is no latency delay there.
		if (!(proxyFactory instanceof IDEStandardBeanProxyFactory))
			assertTrue("Less than 30% improvement: "+improvement+'%', improvement>=30);	// We like this %.
	}

	public void testExpressionPerformanceLarge() throws IllegalStateException, NoExpressionValueException, ThrowableProxy, AmbiguousMethodException, NoSuchMethodException {
		// Small performance test. We are testing a large batch of expressions on each run. We then avg. the runs. This tests many large
		// expression processing performance.
		 
		// Prime the proxies so they don't become part of the timings.
		IBeanTypeProxy pointType = proxyTypeFactory.getBeanTypeProxy("java.awt.Point");
		IMethodProxy setLocation = pointType.getMethodProxy("setLocation", new String[] {"int", "int"});
		IMethodProxy getLocation = pointType.getMethodProxy("getLocation");
		//
		// Now for just a simple one method invocation using method proxies (and not method lookup each time) the standard proxy calls
		// will beat out expression processing for Remote. Note that is not true of IDE vm. In that case the standard proxy calls
		// will ALWAYS out perform greatly the expression processer because there is no latency in the communications. It is basically
		// straight direct calls. While expression processing is also direct calls, the call path is much larger because it needs to 
		// build/maintain/execute the processing stack, rather than just execute the command. 
		//
		
		// Try to prime the communication pump. This sends about 36,000 bytes of data.
		// Doing it here means later when needed the communications costs are drastically reduced.
		IExpression exp = proxyFactory.createExpression();
		int i =1000;
		while (i-->0) {
			exp.createArrayCreation(ForExpression.ROOTEXPRESSION, registry.getBeanTypeProxyFactory()
					.getBeanTypeProxy(exp, "java.lang.Object", 1), 0);
			exp.createArrayInitializer(0);
		}
		exp.invokeExpression();
		
		int times = 25;	// Number of times to run the test.
		int batchSize = 100;	// Size of batch for each test.
		
		// Now do the actual test.
		long start = System.currentTimeMillis();
		i = times;
		while(i-->0) {
			expressionSetting(pointType, setLocation, getLocation, batchSize);
		}
		long expressionTime = System.currentTimeMillis()-start;
		long startNormal = System.currentTimeMillis();
		i = times;
		while(i-->0) {
			normalSetting(pointType, setLocation, getLocation, batchSize);
		}
		long normalTime = System.currentTimeMillis()-startNormal;
		System.out.println("Large Batch results: Batch size="+batchSize+" Number of runs="+times);
		System.out.println("Expression Time: " + expressionTime + " Avg: " + (expressionTime/((double) times)));
		System.out.println("Normal Time: " + normalTime+ " Avg: " + (normalTime/((double) times)));
		if (normalTime == 0)
			normalTime = 1;	// So no divide by zero.
		int improvement = (int) ((1-((double) expressionTime)/normalTime)*100);
		System.out.println("Improvement of expression over normal time: "+improvement+'%');
		// Note that this test is not valid for IDE because the IDE will always be other way around because
		// there is no latency delay there.
		if (!(proxyFactory instanceof IDEStandardBeanProxyFactory))
			assertTrue("Less than 75% improvement: "+improvement+'%', improvement>=75);	// We like this %.
	}
	
	private void expressionSetting(IBeanTypeProxy pointType, IMethodProxy setLocation, IMethodProxy getLocation, int times) throws ThrowableProxy, NoExpressionValueException {
		// This is the same as expressionSetting except it uses regular proxy access instead of expressions. It should be
		// slower because it requires a round trip for each access. For remote only.
		// To factor out the overhead of the initialization of the expression processing, we will do the create/set/get 10 times.
		// Point p = new Point();
		// p.setLocation(3,4);
		// p.getLocation();
		IExpression exp = proxyFactory.createExpression();
		for (int i = 0; i < times; i++) {
			ExpressionProxy epPoint = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			exp.createClassInstanceCreation(ForExpression.ASSIGNMENT_RIGHT, pointType, 0);
			exp.createSimpleMethodInvoke(setLocation, epPoint, new IProxy[] {proxyFactory.createBeanProxyWith(3), proxyFactory.createBeanProxyWith(4)}, false);
			ExpressionProxy epLoc = exp.createSimpleMethodInvoke(getLocation, epPoint, null, true);
			epLoc.addProxyListener(new ExpressionProxy.ProxyAdapter()); // Just have a listener, we want the callback overhead added in.
		}
		exp.invokeExpression();
	}

	private void normalSetting(IBeanTypeProxy pointType, IMethodProxy setLocation, IMethodProxy getLocation, int times) throws ThrowableProxy, AmbiguousMethodException, NoSuchMethodException {
		// This is the same as expressionSetting except it uses regular proxy access instead of expressions. It should be
		// slower because it requires a round trip for each access.
		// To factor out the overhead of the initialization of the expression processing, we will do the create/set/get 10 times. This here matches that.
		// Point p = new Point();
		// p.setLocation(3,4);
		for (int i = 0; i < times; i++) {
			IBeanProxy epPoint = pointType.newInstance();
			setLocation.invokeCatchThrowableExceptions(epPoint,	new IBeanProxy[] {proxyFactory.createBeanProxyWith(3), proxyFactory.createBeanProxyWith(4)});
			getLocation.invokeCatchThrowableExceptions(epPoint);
		}
	}
	
	public void testBlock() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		int blocknum = exp.createBlockBegin();
		ExpressionProxy epArg = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION); 
		exp.createPrimitiveLiteral(ForExpression.ASSIGNMENT_RIGHT, true);
		exp.createBlockBreak(blocknum);
		ExpressionProxy epBoolean = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createClassInstanceCreation(ForExpression.ASSIGNMENT_RIGHT, proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), 1);
		exp.createInfixExpression(ForExpression.CLASSINSTANCECREATION_ARGUMENT, InfixOperator.IN_CONDITIONAL_AND, 0);
		exp.createProxyExpression(ForExpression.INFIX_LEFT, epArg);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, true);
		exp.createBlockEnd();
		ExpressionNotResolved epBooleanl = new ExpressionNotResolved();
		epBoolean.addProxyListener(epBooleanl);
		exp.invokeExpression();
		assertTrue(epBooleanl.notResolvedCalled);
	}

	/*
	 * A proxy listener class for test purposes that requires resolved but
	 * won't allow not resolved or void resolution. Impliment the resolved
	 * method to test for valid values.
	 * 
	 * @since 1.1.0
	 */
	protected abstract class ExpressionResolved implements ExpressionProxy.ProxyListener {
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyNotResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyNotResolved(ProxyEvent event) {
			fail("Proxy must not be unresolved.");
		}
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyVoid(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyVoid(ProxyEvent event) {
			fail("Proxy must not be void assignment.");
		}
	}
	
	protected class ExpressionNotResolved implements ExpressionProxy.ProxyListener {
		public boolean notResolvedCalled = false;
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyNotResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyNotResolved(ProxyEvent event) {
			notResolvedCalled = true;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyResolved(ProxyEvent event) {
			fail("Proxy should not of been resolved. It should be unresolved.");
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyVoid(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyVoid(ProxyEvent event) {
			fail("Proxy must not be void assignment. It should be unresolved.");
		}
	};

	protected class ExpressionVoid implements ExpressionProxy.ProxyListener {
		public boolean voidCalled = false;
		
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyNotResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyNotResolved(ProxyEvent event) {
			fail("Proxy should of been void and not unresolved.");
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyResolved(ProxyEvent event) {
			fail("Proxy should not of been resolved, it should of been void..");
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyVoid(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
		 */
		public void proxyVoid(ProxyEvent event) {
			voidCalled = true;
		}
	};

	public void testTryCatchNoThrow() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = Boolean.valueOf(true);
		 * } catch (RuntimeException e) {
		 *   y = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		xProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertTrue(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		exp.invokeExpression();
		
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		
	}

	public void testTry2CatchNoThrow() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   try {
		 *     x = Boolean.valueOf(true);
		 *   } catch (RuntimeException e) {
		 *     y = Boolean.valueOf(false);
		 *   }
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		ExpressionProxy runtimeCatchExceptionProxy2 = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();

		xProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertTrue(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		ExpressionNotResolved zProxyResolver = new ExpressionNotResolved();
		zProxy.addProxyListener(zProxyResolver);
		ExpressionNotResolved runtimeCatchExceptionProxyResolver2 = new ExpressionNotResolved();
		runtimeCatchExceptionProxy2.addProxyListener(runtimeCatchExceptionProxyResolver2);
		
		exp.invokeExpression();
		
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		assertTrue(zProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver2.notResolvedCalled);
		
	}

	public void testTry2CatchThrowTry1() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   w = (null).booleanValue();
		 *   try {
		 *     x = Boolean.valueOf(true);
		 *   } catch (RuntimeException e) {
		 *     y = Boolean.valueOf(false);
		 *   }
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy wProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);		
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		ExpressionProxy runtimeCatchExceptionProxy2 = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();

		ExpressionNotResolved wProxyResolver = new ExpressionNotResolved();
		wProxy.addProxyListener(wProxyResolver);

		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		zProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		runtimeCatchExceptionProxy2.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		
		exp.invokeExpression();
		
		assertTrue(wProxyResolver.notResolvedCalled);
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		
	}

	public void testTry2CatchThrowTry2Catch() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   try {
		 *     x = (null).booleanValue();
		 *   } catch (RuntimeException e) {
		 *     y = Boolean.valueOf(false);
		 *   }
		 *   w = Boolean.valueOf(true);
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		ExpressionProxy wProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);		
		ExpressionProxy runtimeCatchExceptionProxy2 = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();

		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);
		runtimeCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});

		yProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});		
		wProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertTrue(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		
		ExpressionNotResolved runtimeCatchExceptionProxyResolver2 = new ExpressionNotResolved();
		runtimeCatchExceptionProxy2.addProxyListener(runtimeCatchExceptionProxyResolver2);
		ExpressionNotResolved zProxyResolver = new ExpressionNotResolved();
		zProxy.addProxyListener(zProxyResolver);
		
		exp.invokeExpression();
		
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver2.notResolvedCalled);
		assertTrue(zProxyResolver.notResolvedCalled);
		
	}

	public void testTry2CatchThrowTry2Catch1() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   try {
		 *     x = (null).booleanValue();
		 *   } catch (IllegalArgumentException e) {
		 *     y = Boolean.valueOf(false);
		 *   }
		 *   w = Boolean.valueOf(true);
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy illegalArgumentCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		ExpressionProxy wProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);		
		ExpressionProxy runtimeCatchExceptionProxy2 = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();

		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);
		ExpressionNotResolved illegalArgumentCatchProxyResolver = new ExpressionNotResolved();
		illegalArgumentCatchExceptionProxy.addProxyListener(illegalArgumentCatchProxyResolver);

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		ExpressionNotResolved wProxyResolver = new ExpressionNotResolved();
		wProxy.addProxyListener(wProxyResolver);
		
		runtimeCatchExceptionProxy2.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		zProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		
		exp.invokeExpression();
		
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(illegalArgumentCatchProxyResolver.notResolvedCalled);
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(wProxyResolver.notResolvedCalled);
		
	}

	public void testTry2CatchThrowTry2Finally2Catch1() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   try {
		 *     x = (null).booleanValue();
		 *   } catch (IllegalArgumentException e) {
		 *     y = Boolean.valueOf(false);
		 *   } finally {
		 *     v = Boolean.valueOf(false);
		 *   }
		 *   w = Boolean.valueOf(true);
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy illegalArgumentCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryFinallyClause();
		ExpressionProxy vProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		ExpressionProxy wProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);		
		ExpressionProxy runtimeCatchExceptionProxy2 = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();

		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);
		ExpressionNotResolved illegalArgumentCatchProxyResolver = new ExpressionNotResolved();
		illegalArgumentCatchExceptionProxy.addProxyListener(illegalArgumentCatchProxyResolver);

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		vProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		ExpressionNotResolved wProxyResolver = new ExpressionNotResolved();
		wProxy.addProxyListener(wProxyResolver);
		
		runtimeCatchExceptionProxy2.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		zProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		
		exp.invokeExpression();
		
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(illegalArgumentCatchProxyResolver.notResolvedCalled);
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(wProxyResolver.notResolvedCalled);
		
	}

	public void testTry2CatchThrowTry2Finally2NoCatch() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   try {
		 *     x = (null).booleanValue();
		 *   } catch (IllegalArgumentException e) {
		 *     y = Boolean.valueOf(false);
		 *   } finally {
		 *     v = Boolean.valueOf(false);
		 *   }
		 *   w = Boolean.valueOf(true);
		 * } catch (IllegalArgumentException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy illegalArgumentCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryFinallyClause();
		ExpressionProxy vProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		ExpressionProxy wProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);		
		ExpressionProxy illegalArgumentCatchExceptionProxy2 = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();

		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);
		ExpressionNotResolved illegalArgumentCatchProxyResolver = new ExpressionNotResolved();
		illegalArgumentCatchExceptionProxy.addProxyListener(illegalArgumentCatchProxyResolver);

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		vProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		ExpressionNotResolved wProxyResolver = new ExpressionNotResolved();
		wProxy.addProxyListener(wProxyResolver);
		
		ExpressionNotResolved illegalArgumentCatchExceptionProxyResolver2 = new ExpressionNotResolved();
		illegalArgumentCatchExceptionProxy2.addProxyListener(illegalArgumentCatchExceptionProxyResolver2);
		ExpressionNotResolved zProxyResolver = new ExpressionNotResolved();
		zProxy.addProxyListener(zProxyResolver);
		
		boolean didit = false;
		try {
			exp.invokeExpression();
		} catch (ThrowableProxy e) {
			assertEquals(proxyTypeFactory.getBeanTypeProxy("java.lang.NullPointerException"), e.getTypeProxy());
			didit = true;
		}
		
		assertTrue(didit);
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(illegalArgumentCatchProxyResolver.notResolvedCalled);
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(wProxyResolver.notResolvedCalled);
		assertTrue(illegalArgumentCatchExceptionProxyResolver2.notResolvedCalled);
		assertTrue(zProxyResolver.notResolvedCalled);
		
	}
	
	public void testTryCatchThrow() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = (null).booleanValue();
		 * } catch (NullPointerException e) {
		 *   y = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);		
		yProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		exp.invokeExpression();
		assertTrue(xProxyResolver.notResolvedCalled);
	}

	public void testTryCatchRethrow() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = (null).booleanValue();
		 * } catch (NullPointerException e) {
		 *   y = Boolean.valueOf(false);
		 *   throw e; // As a rethrow.
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createRethrow();
		exp.createTryEnd();
		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);		
		yProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		try {
			exp.invokeExpression();
			fail("Should of thrown NPE.");
		} catch (ThrowableProxy e) {
			assertEquals("java.lang.NullPointerException", e.getTypeProxy().getTypeName());
		}
		assertTrue(xProxyResolver.notResolvedCalled);
	}

	
	public void testTryCatchExplicitThrow() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = Boolean.valueOf(true);
		 *   throw new NullPointerException();
		 *   y = Boolean.valueOf(false);
		 * } catch (NullPointerException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);
		exp.createThrow();
		exp.createClassInstanceCreation(ForExpression.THROW_OPERAND, proxyTypeFactory.getBeanTypeProxy("java.lang.NullPointerException"), 0);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.NullPointerException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		xProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertTrue(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);

		zProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		exp.invokeExpression();
		assertTrue(yProxyResolver.notResolvedCalled);
	}

	public void testTryCatch2NoThrow() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = Boolean.valueOf(true);
		 * } catch (IllegalArgumentException e) {
		 *   y = Boolean.valueOf(false);
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);
		ExpressionProxy illegalCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryEnd();
		xProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertTrue(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		ExpressionNotResolved illegalCatchExceptionProxyResolver = new ExpressionNotResolved();
		illegalCatchExceptionProxy.addProxyListener(illegalCatchExceptionProxyResolver);
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		ExpressionNotResolved zProxyResolver = new ExpressionNotResolved();
		zProxy.addProxyListener(zProxyResolver);
		
		exp.invokeExpression();
		
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(illegalCatchExceptionProxyResolver.notResolvedCalled);
		assertTrue(zProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		
	}

	public void testTryCatch2Throw1() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = (null).booleanValue();
		 * } catch (NullPointerException e) {
		 *   y = Boolean.valueOf(false);
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.NullPointerException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryEnd();
		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);		

		yProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		ExpressionNotResolved zProxyResolver = new ExpressionNotResolved();
		zProxy.addProxyListener(zProxyResolver);
		
		exp.invokeExpression();
		
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(zProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		
	}

	public void testTryCatch2Throw2() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = (null).booleanValue();
		 * } catch (IllegalArgumentException e) {
		 *   y = Boolean.valueOf(false);
		 * } catch (NullPointerException e) {
		 *   z = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy illegalCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.NullPointerException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryEnd();
		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);		
		ExpressionNotResolved illegalCatchExceptionProxyResolver = new ExpressionNotResolved();
		illegalCatchExceptionProxy.addProxyListener(illegalCatchExceptionProxyResolver);
		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);

		zProxy.addProxyListener(new ExpressionResolved() { 

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});		
		exp.invokeExpression();
		
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(illegalCatchExceptionProxyResolver.notResolvedCalled);
		
	}

	public void testTryCatchThrowFinally() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = (null).booleanValue();
		 * } catch (NullPointerException e) {
		 *   y = Boolean.valueOf(false);
		 * } finally {
		 *   f = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryFinallyClause();
		ExpressionProxy fProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		
		exp.createTryEnd();
		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);		
		yProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		
		fProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		
		exp.invokeExpression();
		assertTrue(xProxyResolver.notResolvedCalled);
	}
	
	public void testTryCatch2Throw1Finally() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = (null).booleanValue();
		 * } catch (NullPointerException e) {
		 *   y = Boolean.valueOf(false);
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * } finally {
		 *   f = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.NullPointerException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryFinallyClause();
		ExpressionProxy fProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryEnd();
		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);		

		yProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		ExpressionNotResolved zProxyResolver = new ExpressionNotResolved();
		zProxy.addProxyListener(zProxyResolver);
		fProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		exp.invokeExpression();
		
		
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(zProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		
	}
	
	public void testTryCatch2Throw2Finally() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = (null).booleanValue();
		 * } catch (IllegalArgumentException e) {
		 *   y = Boolean.valueOf(false);
		 * } catch (NullPointerException e) {
		 *   z = Boolean.valueOf(false);
		 * }  finally {
		 *   f = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("booleanValue"), null, null, true);
		ExpressionProxy illegalCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		ExpressionProxy nullCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.NullPointerException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryFinallyClause();
		ExpressionProxy fProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryEnd();
		ExpressionNotResolved xProxyResolver = new ExpressionNotResolved();
		xProxy.addProxyListener(xProxyResolver);		
		ExpressionNotResolved illegalCatchExceptionProxyResolver = new ExpressionNotResolved();
		illegalCatchExceptionProxy.addProxyListener(illegalCatchExceptionProxyResolver);
		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);

		zProxy.addProxyListener(new ExpressionResolved() { 

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		nullCatchExceptionProxy.addProxyListener(new ExpressionResolved() {
			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				assertEquals("java.lang.NullPointerException", event.getProxy().getTypeProxy().getTypeName());
			}
		});	
		
		fProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});		
		exp.invokeExpression();
		
		assertTrue(xProxyResolver.notResolvedCalled);
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(illegalCatchExceptionProxyResolver.notResolvedCalled);
		
	}
	

	public void testTryCatchNoThrowFinally() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = Boolean.valueOf(true);
		 * } catch (RuntimeException e) {
		 *   y = Boolean.valueOf(false);
		 * } finally {
		 *   f = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryFinallyClause();
		ExpressionProxy fProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		exp.createTryEnd();
		xProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertTrue(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		fProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});
		
		exp.invokeExpression();
		
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		
	}

	public void testTryCatch2NoThrowFinally() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		/*
		 * try {
		 *   x = Boolean.valueOf(true);
		 * } catch (IllegalArgumentException e) {
		 *   y = Boolean.valueOf(false);
		 * } catch (RuntimeException e) {
		 *   z = Boolean.valueOf(false);
		 * } finally {
		 *   f = Boolean.valueOf(false);
		 * }
		 */
		IExpression exp = proxyFactory.createExpression();
		exp.createTry();
		ExpressionProxy xProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(true)}, true);
		ExpressionProxy illegalCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.IllegalArgumentException"), true);
		ExpressionProxy yProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);
		ExpressionProxy runtimeCatchExceptionProxy = exp.createTryCatchClause(proxyTypeFactory.getBeanTypeProxy("java.lang.RuntimeException"), true);
		ExpressionProxy zProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryFinallyClause();
		ExpressionProxy fProxy = exp.createSimpleMethodInvoke(proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean").getMethodProxy("valueOf", new String[] {"boolean"}), null, new IProxy[] {proxyFactory.createBeanProxyWith(false)}, true);		
		exp.createTryEnd();
		xProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertTrue(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		ExpressionNotResolved yProxyResolver = new ExpressionNotResolved();
		yProxy.addProxyListener(yProxyResolver);
		ExpressionNotResolved illegalCatchExceptionProxyResolver = new ExpressionNotResolved();
		illegalCatchExceptionProxy.addProxyListener(illegalCatchExceptionProxyResolver);
		ExpressionNotResolved runtimeCatchExceptionProxyResolver = new ExpressionNotResolved();
		runtimeCatchExceptionProxy.addProxyListener(runtimeCatchExceptionProxyResolver);
		ExpressionNotResolved zProxyResolver = new ExpressionNotResolved();
		zProxy.addProxyListener(zProxyResolver);
		fProxy.addProxyListener(new ExpressionResolved() {

			/* (non-Javadoc)
			 * @see org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyListener#proxyResolved(org.eclipse.jem.internal.proxy.core.ExpressionProxy.ProxyEvent)
			 */
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy proxy = event.getProxy();
				assertEquals("Not a Boolean proxy:"+proxy.getTypeProxy().getTypeName(), proxyTypeFactory.getBeanTypeProxy("java.lang.Boolean"), proxy.getTypeProxy());
				assertFalse(((IBooleanBeanProxy) event.getProxy()).booleanValue());
			}
		});

		exp.invokeExpression();
		
		assertTrue(yProxyResolver.notResolvedCalled);
		assertTrue(illegalCatchExceptionProxyResolver.notResolvedCalled);
		assertTrue(zProxyResolver.notResolvedCalled);
		assertTrue(runtimeCatchExceptionProxyResolver.notResolvedCalled);
		
	}
	

	public void testIfElseTrueWithElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(true);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.IF_TRUE);
		exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		ExpressionProxy falseProxy = exp.createProxyAssignmentExpression(ForExpression.IF_ELSE);		
		exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		
		trueProxy.addProxyListener(new ExpressionResolved() {
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("int", value.getTypeProxy().getFormalTypeName());
				assertEquals(6, ((INumberBeanProxy) value).intValue());
			}
		});

		falseProxy.addProxyListener(new ExpressionNotResolved());
		exp.invokeExpression();
	}

	public void testIfElseFalseWithElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(true);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.IF_TRUE);
		exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		ExpressionProxy falseProxy = exp.createProxyAssignmentExpression(ForExpression.IF_ELSE);		
		exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_MINUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);

		trueProxy.addProxyListener(new ExpressionNotResolved());
		
		falseProxy.addProxyListener(new ExpressionResolved() {
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("int", value.getTypeProxy().getFormalTypeName());
				assertEquals(0, ((INumberBeanProxy) value).intValue());
			}
		});

		exp.invokeExpression();
	}

	public void testIfElseTrueWithoutElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(false);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.IF_TRUE);
		exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		
		trueProxy.addProxyListener(new ExpressionResolved() {
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("int", value.getTypeProxy().getFormalTypeName());
				assertEquals(6, ((INumberBeanProxy) value).intValue());
			}
		});

		exp.invokeExpression();
	}
	
	public void testIfElseFalseWithoutElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(false);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.IF_TRUE);
		exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		
		trueProxy.addProxyListener(new ExpressionNotResolved());

		exp.invokeExpression();
	}

	public void testIfElseBlocksTrueWithElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(true);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockBegin();
			ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
			exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockEnd();
		exp.createBlockBegin();
			ExpressionProxy falseProxy = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);		
			exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_MINUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
			exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockEnd();
		
		trueProxy.addProxyListener(new ExpressionResolved() {
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("int", value.getTypeProxy().getFormalTypeName());
				assertEquals(6, ((INumberBeanProxy) value).intValue());
			}
		});

		falseProxy.addProxyListener(new ExpressionNotResolved());
		exp.invokeExpression();
	}

	public void testIfElseBlocksFalseWithElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(true);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockBegin();
			ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
			exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockEnd();
		exp.createBlockBegin();
			ExpressionProxy falseProxy = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);		
			exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_MINUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
			exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockEnd();

		trueProxy.addProxyListener(new ExpressionNotResolved());
		
		falseProxy.addProxyListener(new ExpressionResolved() {
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("int", value.getTypeProxy().getFormalTypeName());
				assertEquals(0, ((INumberBeanProxy) value).intValue());
			}
		});

		exp.invokeExpression();
	}

	public void testIfElseBlockTrueWithoutElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(false);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockBegin();
			ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
			exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockEnd();
		
		trueProxy.addProxyListener(new ExpressionResolved() {
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("int", value.getTypeProxy().getFormalTypeName());
				assertEquals(6, ((INumberBeanProxy) value).intValue());
			}
		});

		exp.invokeExpression();
	}
	
	public void testIfElseBlockFalseWithoutElse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createIfElse(false);
		exp.createInfixExpression(ForExpression.IF_CONDITION, InfixOperator.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockBegin();
			ExpressionProxy trueProxy = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_PLUS, 0);
			exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, (byte) 3);
			exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, (short) 3);
		exp.createBlockEnd();
		
		trueProxy.addProxyListener(new ExpressionNotResolved());

		exp.invokeExpression();
	}	

	public void testNewInitStringPrimitive() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// Use internal expression class so we can test the special method.
		Expression exp = (Expression) proxyFactory.createExpression();
		ExpressionProxy result = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createNewInstance(ForExpression.ASSIGNMENT_RIGHT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
		result.addProxyListener(new ExpressionResolved(){
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("boolean", value.getTypeProxy().getFormalTypeName());
				assertEquals(false, ((IBooleanBeanProxy) value).booleanValue());
			}
			
		});
		
		exp.invokeExpression();
	}
	
	public void testMark() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// Just test having a mark and no errors.
		Expression exp = (Expression) proxyFactory.createExpression();
		int mark = exp.mark();
		try {
			ExpressionProxy result = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			exp.createNewInstance(ForExpression.ASSIGNMENT_RIGHT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
			result.addProxyListener(new ExpressionResolved(){
				public void proxyResolved(ProxyEvent event) {
					IBeanProxy value = event.getProxy();
					assertNotNull(value);
					assertEquals("boolean", value.getTypeProxy().getFormalTypeName());
					assertEquals(false, ((IBooleanBeanProxy) value).booleanValue());
				}
				
			});			
		} finally {
			exp.endMark(mark);
		}
		exp.invokeExpression();
		
	}
	
	public void testMark2() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// Just test having a mark errors, but expressions before the mark and after the mark are successful.
		Expression exp = (Expression) proxyFactory.createExpression();
		int mark0 = exp.mark();
		final boolean[] resultResolved = new boolean[1];
		ExpressionNotResolved ep1 = null;
		final boolean[] resultResolved2 = new boolean[1];
		try {
			ExpressionProxy result = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			result.addProxyListener(new ExpressionResolved() {

				public void proxyResolved(ProxyEvent event) {
					IBeanProxy value = event.getProxy();
					assertNotNull(value);
					assertEquals("boolean", value.getTypeProxy().getFormalTypeName());
					assertEquals(false, ((IBooleanBeanProxy) value).booleanValue());
					resultResolved[0] = true;
				}

			});
			exp.createNewInstance(ForExpression.ASSIGNMENT_RIGHT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
			exp.createTry();
			int mark = exp.mark();
			try {
				ExpressionProxy result1 = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
				result1.addProxyListener(ep1 = new ExpressionNotResolved());
				exp.createNewInstance(ForExpression.ASSIGNMENT_LEFT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
			} catch (IllegalStateException e) {
				// This is ok, should of got this.
			} finally {
				exp.endMark(mark);
			}
			exp.createTryEnd();
			ExpressionProxy result2 = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			result2.addProxyListener(new ExpressionResolved() {

				public void proxyResolved(ProxyEvent event) {
					IBeanProxy value = event.getProxy();
					assertNotNull(value);
					assertEquals("boolean", value.getTypeProxy().getFormalTypeName());
					assertEquals(false, ((IBooleanBeanProxy) value).booleanValue());
					resultResolved2[0] = true;
				}

			});
			exp.createNewInstance(ForExpression.ASSIGNMENT_RIGHT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
		} finally {
			exp.endMark(mark0);
		}
		
		exp.invokeExpression();
		
		assertNotNull(ep1);
		assertTrue(ep1.notResolvedCalled);
		assertTrue(resultResolved[0]);
		assertTrue(resultResolved2[0]);
		
	}
	
	
	public void testMarkError() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		// Test with one error and see if it restores correctly.
		Expression exp = (Expression) proxyFactory.createExpression();
		int mark = exp.mark();
		ExpressionNotResolved epl = null;
		try {
			ExpressionProxy result = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			result.addProxyListener(epl = new ExpressionNotResolved());
			exp.createNewInstance(ForExpression.ASSIGNMENT_LEFT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
		} catch (IllegalStateException e) {
			// This is good. We should get here.
		} finally {
			exp.endMark(mark);
		}
		
		// Now we should be able to continue and get good answer.
		ExpressionProxy result = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createNewInstance(ForExpression.ASSIGNMENT_RIGHT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
		result.addProxyListener(new ExpressionResolved(){
			public void proxyResolved(ProxyEvent event) {
				IBeanProxy value = event.getProxy();
				assertNotNull(value);
				assertEquals("boolean", value.getTypeProxy().getFormalTypeName());
				assertEquals(false, ((IBooleanBeanProxy) value).booleanValue());
			}
			
		});
		
		exp.invokeExpression();
		assertNotNull(epl);
		assertTrue(epl.notResolvedCalled);
	}
	
	public void testMarkNestError() {
		// Test mark nest error.
		// mark
		// try {
		//   endmark
		// } finally <-- this should cause an exception.
		Expression exp = (Expression) proxyFactory.createExpression();
		int mark = exp.mark();
		ExpressionNotResolved epl = null;
		try {
			exp.createTry();
			ExpressionProxy result = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			result.addProxyListener(epl = new ExpressionNotResolved());
			exp.createNewInstance(ForExpression.ASSIGNMENT_RIGHT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
			exp.endMark(mark);
			exp.createTryFinallyClause();
			exp.createTryEnd();
		} catch (IllegalStateException e) {
			// This is good. We should get here.
			exp.close();
			assertNotNull(epl);
			assertTrue(epl.notResolvedCalled);			
			return;
		}
		
		exp.close();
		fail("Should not of gotten here.");
	}
	
	public void testMarkNestError2() {
		// Test mark nest error.
		// try {
		//   mark
		// } finally <-- this should cause an exception.
		// endmark
		Expression exp = (Expression) proxyFactory.createExpression();
		ExpressionNotResolved epl = null;
		try {
			exp.createTry();
			int mark = exp.mark();
			ExpressionProxy result = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			result.addProxyListener(epl = new ExpressionNotResolved());
			exp.createNewInstance(ForExpression.ASSIGNMENT_RIGHT, "false", proxyTypeFactory.getBeanTypeProxy("boolean"));
			exp.createTryFinallyClause();
			exp.createTryEnd();
			exp.endMark(mark);
		} catch (IllegalStateException e) {
			// This is good. We should get here.
			exp.close();
			assertNotNull(epl);
			assertTrue(epl.notResolvedCalled);			
			return;
		}
		
		exp.close();
		fail("Should not of gotten here.");
	}

	public void testExpressionTransfer() throws Throwable {
		// Test that thread transfer works. We will do part of the expression in one thread, more in the next, and
		// then come back and complete it.
		IExpression exp = proxyFactory.createExpression();
		ExpressionProxy epInfix = exp.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
		exp.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_LESS, 0);
		exp.createPrimitiveLiteral(ForExpression.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(ForExpression.INFIX_RIGHT, 3);
		
		// Now transfer to another thread.
		Expression expression = (Expression) exp;
		expression.beginTransferThread();	// Begin the transfer.
		
		IBeanTypeProxy callbackType = proxyTypeFactory.getBeanTypeProxy("org.eclipse.jem.tests.proxy.vm.TestExpressionThreadTransfer"); //$NON-NLS-1$
		assertNotNull(callbackType);
		IBeanProxy callbackProxy = callbackType.newInstance();
		
		ExpressionThreadTransferCallBack cb = new ExpressionThreadTransferCallBack(expression);
		registry.getCallbackRegistry().registerCallback(callbackProxy, cb);
		IInvokable start = callbackType.getInvokable("start");	//$NON-NLS-1$
		start.invokeCatchThrowableExceptions(callbackProxy);	// Start the thread on the remote vm and wait for it to finish.
		
		epInfix.addProxyListener(new ExpressionResolved() {
			public void proxyResolved(ExpressionProxy.ProxyEvent event) {
				IBeanProxy result = event.getProxy();
				assertNotNull(result);
				assertEquals("boolean", result.getTypeProxy().getTypeName());
				assertEquals(-10000000<3, ((IBooleanBeanProxy) result).booleanValue());		
			}
		});
		
		if (cb.error != null) {
			throw cb.error;
		}
		
		cb.ep.addProxyListener(new ExpressionResolved(){
			public void proxyResolved(ExpressionProxy.ProxyEvent event) {
				IBeanProxy result = event.getProxy();
				assertNotNull(result);
				assertEquals("boolean", result.getTypeProxy().getTypeName());
				assertEquals(String.class == String.class, ((IBooleanBeanProxy) result).booleanValue());
			}
		});

		expression.transferThread();
		exp.invokeExpression();
	}
}
