/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ExpressionTest.java,v $
 *  $Revision: 1.3 $  $Date: 2004/11/10 20:26:10 $ 
 */
package org.eclipse.jem.tests.proxy;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.tree.IExpressionConstants;
import org.eclipse.jem.internal.proxy.initParser.tree.IExpressionConstants.NoExpressionValueException;
 
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
	
	public void testCastStringType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(IExpressionConstants.ROOTEXPRESSION, "short");
		exp.createPrimitiveLiteral(IExpressionConstants.CAST_EXPRESSION, 10l);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getTypeName());
		assertEquals((short) 10, ((INumberBeanProxy) result).shortValue());
	}
	
	public void testCastProxyType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(IExpressionConstants.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("short"));
		exp.createPrimitiveLiteral(IExpressionConstants.CAST_EXPRESSION, 'a');
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getTypeName());
		assertEquals((short) 'a', ((INumberBeanProxy) result).shortValue());	
	}
	
	public void testCastError() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(IExpressionConstants.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("short"));
		try {
			exp.createProxyExpression(IExpressionConstants.CAST_EXPRESSION, proxyFactory.createBeanProxyWith(Boolean.TRUE));
			exp.getExpressionValue();
			fail("Should of thrown ClassCastException");
		} catch (ThrowableProxy e) {
			if (!e.getTypeProxy().getFormalTypeName().equals("java.lang.ClassCastException"))
				throw e;	// Some other exception, rethrow it.
		}
	}	
	
	public void testCastFailed() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createCastExpression(IExpressionConstants.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("short"));
		try {
			exp.getExpressionValue();
			fail("Should of gotton IllegalStateException");
		} catch (IllegalStateException e) {
			System.out.println(e.getLocalizedMessage());
		}		
	}

	public void testInstanceofStringType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(IExpressionConstants.ROOTEXPRESSION, "java.lang.String");
		exp.createStringLiteral(IExpressionConstants.INSTANCEOF_VALUE, "asdf");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testInstanceofProxyType() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(IExpressionConstants.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("java.lang.String"));
		exp.createStringLiteral(IExpressionConstants.INSTANCEOF_VALUE, "asdf");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true, ((IBooleanBeanProxy) result).booleanValue());
		
	}
	
	public void testInstanceofFailed() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(IExpressionConstants.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("java.lang.String"));
		try {
			exp.getExpressionValue();
			fail("Should of gotton IllegalStateException");
		} catch (IllegalStateException e) {
			System.out.println(e.getLocalizedMessage());
		}		
	}
	
	public void testTypeLiteral() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createTypeLiteral(IExpressionConstants.ROOTEXPRESSION, "java.lang.String");
		IBeanProxy result = exp.getExpressionValue();
		assertTrue("Not a bean type proxy.", result instanceof IBeanTypeProxy);
		IBeanTypeProxy type = (IBeanTypeProxy) result;
		assertTrue("Not valid.", type.isValid());
		assertEquals("java.lang.String", type.getFormalTypeName());
	}

	public void testTypeFails() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createTypeLiteral(IExpressionConstants.ROOTEXPRESSION, "java.lang.Stng");
			exp.getExpressionValue();
			fail("Should not of gotton a result.");
		} catch (ThrowableProxy e) {
			System.out.println(e.getProxyLocalizedMessage());
		}
	}
	
	public void testNesting() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInstanceofExpression(IExpressionConstants.ROOTEXPRESSION, "short");
		exp.createCastExpression(IExpressionConstants.INSTANCEOF_VALUE, "short");
		exp.createPrimitiveLiteral(IExpressionConstants.CAST_EXPRESSION, (short) 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testPrefixPlus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.PRE_PLUS);
		exp.createPrimitiveLiteral(IExpressionConstants.PREFIX_OPERAND, 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(10, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testPrefixMinus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.PRE_MINUS);
		exp.createPrimitiveLiteral(IExpressionConstants.PREFIX_OPERAND, 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-10, ((INumberBeanProxy) result).intValue());
		
	}

	public void testPrefixMinusChar() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.PRE_MINUS);
		exp.createPrimitiveLiteral(IExpressionConstants.PREFIX_OPERAND, 'a');
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-'a', ((INumberBeanProxy) result).intValue());
		
	}

	public void testPrefixComplement() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.PRE_COMPLEMENT);
		exp.createPrimitiveLiteral(IExpressionConstants.PREFIX_OPERAND, (short) 10);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(~10, ((INumberBeanProxy) result).intValue());
		
	}

	public void testPrefixNot() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.PRE_NOT);
		exp.createPrimitiveLiteral(IExpressionConstants.PREFIX_OPERAND, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false, ((IBooleanBeanProxy) result).booleanValue());
		
	}

	public void testPrefixFail() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createPrefixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.PRE_NOT);
		try {
			exp.createPrimitiveLiteral(IExpressionConstants.PREFIX_OPERAND, 10);
			exp.getExpressionValue();
			fail("Should of failed.");
		} catch (ThrowableProxy e) {
			System.out.println(e.getProxyLocalizedMessage());
		}
		
	}
	
	public void testTimes() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_TIMES, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 4);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(3*4, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testTimesExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_TIMES, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 5d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((3*4*5d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testTimesExtendedNested() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_TIMES, 1);
		exp.createInfixExpression(IExpressionConstants.INFIX_LEFT, IExpressionConstants.IN_MINUS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 10);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 5);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 5d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals(((10-5)*4*5d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testDivide() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_DIVIDE, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4/2, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testDivideExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_DIVIDE, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((12/3/2d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testRemainder() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_REMAINDER, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4%3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testRemainderExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_REMAINDER, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((12%9%2d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testMinus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_MINUS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4-3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testMinusExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_MINUS, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2d);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("double", result.getTypeProxy().getTypeName());
		assertEquals((12-9-2d), ((INumberBeanProxy) result).doubleValue(), 0);
		
	}

	public void testLeftShift() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_LEFT_SHIFT, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(4<<3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testLeftShiftExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_LEFT_SHIFT, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 12);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((12<<9<<2), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testRightShiftSigned() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_RIGHT_SHIFT_SIGNED, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-10000000>>3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testRightShiftSignedExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_RIGHT_SHIFT_SIGNED, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((-10000000>>4>>2), ((INumberBeanProxy) result).intValue(), 0);
	}
	public void testRightShiftUnSigned() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(-10000000>>>3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testRightShiftUnSignedExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_RIGHT_SHIFT_UNSIGNED, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 4);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((-10000000>>>4>>>2), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testLess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_LESS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(-10000000<3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testLessEquals() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_LESS_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(3<=3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testGreater() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_GREATER, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, -10000000);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(-10000000>3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testGreaterEquals() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_GREATER_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 2);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(2>=3, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testEqualsPrimitives() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3d);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(3d==3, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testEqualsObjects() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_EQUALS, 0);
		exp.createTypeLiteral(IExpressionConstants.INFIX_LEFT, "java.lang.String");
		exp.createTypeLiteral(IExpressionConstants.INFIX_RIGHT, "java.lang.String");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(String.class == String.class, ((IBooleanBeanProxy) result).booleanValue());
	}	

	public void testNotEqualsPrimitives() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_NOT_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3d);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(3d!=3, ((IBooleanBeanProxy) result).booleanValue());
	}
	
	public void testNotEqualsObjects() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_NOT_EQUALS, 0);
		exp.createTypeLiteral(IExpressionConstants.INFIX_LEFT, "java.lang.String");
		exp.createTypeLiteral(IExpressionConstants.INFIX_RIGHT, "java.lang.String");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(String.class != String.class, ((IBooleanBeanProxy) result).booleanValue());
	}	

	public void testXOR() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_XOR, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5^3, ((INumberBeanProxy) result).intValue());
		
	}
	
	public void testXORExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_XOR, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 23);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 9);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((23^9^2), ((INumberBeanProxy) result).intValue(), 0);
		
	}

	public void testAnd() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_AND, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5&3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testAndExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_AND, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 13);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 15);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 1);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((13&5&1), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testOr() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_OR, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5|2, ((INumberBeanProxy) result).intValue());
	}
	
	public void testOrExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_OR, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 13);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 6);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals((13|6|2), ((INumberBeanProxy) result).intValue(), 0);
	}

	public void testPlus() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getTypeName());
		assertEquals(5+2, ((INumberBeanProxy) result).intValue());
	}

	public void testPlusExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 5);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 2);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_EXTENDED, 2l);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("long", result.getTypeProxy().getTypeName());
		assertEquals(5+2+2l, ((INumberBeanProxy) result).intValue());
	}

	public void testPlusStringLeft() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
		exp.createProxyExpression(IExpressionConstants.INFIX_LEFT, proxyFactory.createBeanProxyWith("abc"));
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals("abc"+2, ((IStringBeanProxy) result).stringValue());
	}

	public void testPlusStringRight() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 2);
		exp.createProxyExpression(IExpressionConstants.INFIX_RIGHT, proxyFactory.createBeanProxyWith("abc"));		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals(2+"abc", ((IStringBeanProxy) result).stringValue());
	}
	
	public void testPlusStringLeftNull() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
		exp.createProxyExpression(IExpressionConstants.INFIX_LEFT, proxyFactory.createBeanProxyWith("abc"));
		exp.createNull(IExpressionConstants.INFIX_RIGHT);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals("abc"+null, ((IStringBeanProxy) result).stringValue());
	}
	
	public void testPlusStringRightNull() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
		exp.createNull(IExpressionConstants.INFIX_LEFT);
		exp.createProxyExpression(IExpressionConstants.INFIX_RIGHT, proxyFactory.createBeanProxyWith("abc"));
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals(null+"abc", ((IStringBeanProxy) result).stringValue());
	}

	public void testPlusStringLeftChar() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
		exp.createProxyExpression(IExpressionConstants.INFIX_LEFT, proxyFactory.createBeanProxyWith("abc"));
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 'a');
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals("abc"+'a', ((IStringBeanProxy) result).stringValue());
	}

	public void testPlusStringRightChar() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 'a');
		exp.createProxyExpression(IExpressionConstants.INFIX_RIGHT, proxyFactory.createBeanProxyWith("abc"));
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getTypeName());
		assertEquals('a'+"abc", ((IStringBeanProxy) result).stringValue());
	}

	public void testConditionalAnd() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_AND, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true && true, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_AND, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, true);
		exp.createInfixExpression(IExpressionConstants.INFIX_EXTENDED, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true && true && (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndFalseLast() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_AND, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, false);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true && false, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndFalseFirst() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_AND, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false && true, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalAndExtendedAndFalseFirst() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_AND, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, true);
		exp.createInfixExpression(IExpressionConstants.INFIX_EXTENDED, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false && true && (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOr() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_OR, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false || true, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOrExtended() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_OR, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, false);
		exp.createInfixExpression(IExpressionConstants.INFIX_EXTENDED, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false || false || (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOrTrueFirst() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_OR, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, true);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, false);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(true || false, ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testConditionalOrExtendedAndTrueMiddle() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createInfixExpression(IExpressionConstants.ROOTEXPRESSION, IExpressionConstants.IN_CONDITIONAL_OR, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, false);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, true);
		exp.createInfixExpression(IExpressionConstants.INFIX_EXTENDED, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_LEFT, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.INFIX_RIGHT, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("boolean", result.getTypeProxy().getTypeName());
		assertEquals(false || true || (3 == 3), ((IBooleanBeanProxy) result).booleanValue());
	}

	public void testArrayAccess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = (IArrayBeanProxy) proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), 1);
		array.set(proxyFactory.createBeanProxyWith((short) 3), 0);
		
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayAccess(IExpressionConstants.ROOTEXPRESSION, 1);
		exp.createProxyExpression(IExpressionConstants.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYACCESS_INDEX, 0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getTypeName());
		assertEquals(((INumberBeanProxy) result).shortValue(), (short) 3);
	}
	
	public void testMultiArrayAccess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = (IArrayBeanProxy) proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), new int[] {2,1});
		array.set(proxyFactory.createBeanProxyWith((short) 3), new int[]{1,0});
		
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayAccess(IExpressionConstants.ROOTEXPRESSION, 1);
		exp.createProxyExpression(IExpressionConstants.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYACCESS_INDEX, 1);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short[]", result.getTypeProxy().getFormalTypeName());
	}
	
	public void testMultiArrayAccess1() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IArrayBeanProxy array = (IArrayBeanProxy) proxyFactory.createBeanProxyWith(proxyTypeFactory.getBeanTypeProxy("short"), new int[] {2,1});
		array.set(proxyFactory.createBeanProxyWith((short) 3), new int[]{1,0});
		
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayAccess(IExpressionConstants.ROOTEXPRESSION, 2);
		exp.createProxyExpression(IExpressionConstants.ARRAYACCESS_ARRAY, array);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYACCESS_INDEX, 1);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYACCESS_INDEX, 0);		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short", result.getTypeProxy().getFormalTypeName());
		assertEquals((short) 3, ((INumberBeanProxy) result).shortValue());
	}
	
	public void testArrayCreation() throws IllegalStateException, IllegalArgumentException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[]", 1);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYCREATION_DIMENSION, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
	}
	
	public void testMultiArrayCreation() throws IllegalStateException, IllegalArgumentException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[][]", 2);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYCREATION_DIMENSION, 2);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYCREATION_DIMENSION, 4);		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
		assertEquals(4, ((IArrayBeanProxy)((IArrayBeanProxy) result).get(0)).getLength());
	}	

	public void testMultiPartialArrayCreation() throws IllegalStateException, IllegalArgumentException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[][][]", 2);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYCREATION_DIMENSION, 2);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYCREATION_DIMENSION, 4);		
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
		assertEquals(4, ((IArrayBeanProxy)((IArrayBeanProxy) result).get(0)).getLength());
		assertNull(((IArrayBeanProxy)((IArrayBeanProxy) result).get(0)).get(0));
	}
	
	public void testArrayInitializerEmpty() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[]", 0);
		exp.createArrayInitializer(0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(0, ((IArrayBeanProxy) result).getLength());
	}
	
	public void testArrayInitializerOneDim() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[]", 0);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYINITIALIZER_EXPRESSION, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((IArrayBeanProxy) result).getLength());
		assertEquals(3, ((INumberBeanProxy) ((IArrayBeanProxy) result).get(0)).intValue());
	}
	
	public void testArrayInitializerTwoDimEmpty() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[][]", 0);
		exp.createArrayInitializer(0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(0, ((IArrayBeanProxy) result).getLength());
	}
	
	public void testArrayInitializerTwoDim() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[][]", 0);
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
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "int[][]", 0);
		exp.createArrayInitializer(2);
		exp.createNull(IExpressionConstants.ARRAYINITIALIZER_EXPRESSION);
		exp.createArrayInitializer(2);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYINITIALIZER_EXPRESSION, 3);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYINITIALIZER_EXPRESSION, 4);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int[][]", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((IArrayBeanProxy) result).getLength());
		assertNull((IArrayBeanProxy) ((IArrayBeanProxy) result).get(0));
		assertEquals(2, ((IArrayBeanProxy) ((IArrayBeanProxy) result).get(1)).getLength());
		assertEquals(4, ((INumberBeanProxy)((IArrayBeanProxy) ((IArrayBeanProxy) result).get(1)).get(1)).intValue());		
	}
	
	public void testArrayInitializerShortInt() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createArrayCreation(IExpressionConstants.ROOTEXPRESSION, "short[]", 0);
		exp.createArrayInitializer(1);
		exp.createPrimitiveLiteral(IExpressionConstants.ARRAYINITIALIZER_EXPRESSION, 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("short[]", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((IArrayBeanProxy) result).getLength());
		assertEquals(3, ((INumberBeanProxy) ((IArrayBeanProxy) result).get(0)).intValue());
	}
	
	public void testClassInstanceCreationDefault() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(IExpressionConstants.ROOTEXPRESSION, "java.lang.Object", 0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Object", result.getTypeProxy().getFormalTypeName());
	}
	
	public void testClassInstanceCreationDefaultWithBeanTypeProxy() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(IExpressionConstants.ROOTEXPRESSION, proxyTypeFactory.getBeanTypeProxy("java.lang.Object"), 0);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Object", result.getTypeProxy().getFormalTypeName());
	}	
	
	public void testClassInstanceCreationOneArg() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(IExpressionConstants.ROOTEXPRESSION, "java.lang.Integer", 1);
		exp.createStringLiteral(IExpressionConstants.CLASSINSTANCECREATION_ARGUMENT, "3");
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Integer", result.getTypeProxy().getFormalTypeName());
		assertEquals(3, ((INumberBeanProxy) result).intValue());
	}
	
	public void testClassInstanceCreationOneArgWithPrimWidening() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(IExpressionConstants.ROOTEXPRESSION, "java.lang.Short", 1);
		exp.createPrimitiveLiteral(IExpressionConstants.CLASSINSTANCECREATION_ARGUMENT, (byte)3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.Short", result.getTypeProxy().getFormalTypeName());
		assertEquals((short)3, ((INumberBeanProxy) result).shortValue());
	}	
	
	public void testClassInstanceCreationNullArg() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(IExpressionConstants.ROOTEXPRESSION, "testPackage.TestCtorWithNull", 1);
		exp.createNull(IExpressionConstants.CLASSINSTANCECREATION_ARGUMENT);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("testPackage.TestCtorWithNull", result.getTypeProxy().getFormalTypeName());
	}	
	
	public void testClassInstanceCreationMismatchArg() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createClassInstanceCreation(IExpressionConstants.ROOTEXPRESSION, "testPackage.TestCtorWithNull", 1);
		try {
			exp.createPrimitiveLiteral(IExpressionConstants.CLASSINSTANCECREATION_ARGUMENT, 1);
			exp.getExpressionValue();
			fail("Exception should of been thrown.");
		} catch (ThrowableProxy e) {
			assertEquals("java.lang.NoSuchMethodException", e.getTypeProxy().getFormalTypeName());
		}
	}	
	
	public void testStaticFieldAccess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createFieldAccess(IExpressionConstants.ROOTEXPRESSION, "RED", true);
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
	
	public void testNonStaticFieldAccess() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createFieldAccess(IExpressionConstants.ROOTEXPRESSION, "x", true);
		exp.createClassInstanceCreation(IExpression.FIELD_RECEIVER, "java.awt.Point", 2);
		exp.createPrimitiveLiteral(IExpression.CLASSINSTANCECREATION_ARGUMENT, 1);
		exp.createPrimitiveLiteral(IExpression.CLASSINSTANCECREATION_ARGUMENT, 2);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(1, ((INumberBeanProxy) result).intValue());
	}	

	public void testStaticMethodInvoke() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createMethodInvocation(IExpressionConstants.ROOTEXPRESSION, "valueOf", true, 1);
		exp.createTypeReceiver("java.lang.String");
		exp.createPrimitiveLiteral(IExpression.METHOD_ARGUMENT, true);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("java.lang.String", result.getTypeProxy().getFormalTypeName());
		assertEquals("true", ((IStringBeanProxy) result).stringValue());
	}

	public void testNonStaticMethodInvoke() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createMethodInvocation(IExpressionConstants.ROOTEXPRESSION, "compareTo", true, 1);
		exp.createClassInstanceCreation(IExpression.METHOD_RECEIVER, "java.lang.Integer", 1);
		exp.createPrimitiveLiteral(IExpression.CLASSINSTANCECREATION_ARGUMENT, 3);
		exp.createClassInstanceCreation(IExpression.METHOD_ARGUMENT, "java.lang.Integer", 1);
		exp.createPrimitiveLiteral(IExpression.CLASSINSTANCECREATION_ARGUMENT, 4);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertTrue("CompareTo wasn't less than 0.", ((INumberBeanProxy) result).intValue() < 0);
	}	
	
	public void testConditionalTrue() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(IExpression.ROOTEXPRESSION);
		exp.createInfixExpression(IExpression.CONDITIONAL_CONDITION, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_TRUE, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_FALSE, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(6, ((INumberBeanProxy) result).intValue());
	}
	
	public void testConditionalFalse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(IExpression.ROOTEXPRESSION);
		exp.createInfixExpression(IExpression.CONDITIONAL_CONDITION, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_TRUE, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_FALSE, IExpressionConstants.IN_MINUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(0, ((INumberBeanProxy) result).intValue());
	}
	
	public void testNestedConditionalTrueTrue() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(IExpression.ROOTEXPRESSION);
		exp.createInfixExpression(IExpression.CONDITIONAL_CONDITION, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createConditionalExpression(IExpression.CONDITIONAL_TRUE);
		exp.createInfixExpression(IExpression.CONDITIONAL_CONDITION, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_TRUE, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 4);
		exp.createInfixExpression(IExpression.CONDITIONAL_FALSE, IExpressionConstants.IN_MINUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_FALSE, IExpressionConstants.IN_MINUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 5);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(8, ((INumberBeanProxy) result).intValue());
	}
	
	public void testNestedConditionalFalseFalse() throws IllegalStateException, ThrowableProxy, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		exp.createConditionalExpression(IExpression.ROOTEXPRESSION);
		exp.createInfixExpression(IExpression.CONDITIONAL_CONDITION, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 3);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 2);
		exp.createConditionalExpression(IExpression.CONDITIONAL_TRUE);
		exp.createInfixExpression(IExpression.CONDITIONAL_CONDITION, IExpressionConstants.IN_EQUALS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 2);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_TRUE, IExpressionConstants.IN_PLUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 4);
		exp.createInfixExpression(IExpression.CONDITIONAL_FALSE, IExpressionConstants.IN_MINUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 4);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		exp.createInfixExpression(IExpression.CONDITIONAL_FALSE, IExpressionConstants.IN_MINUS, 0);
		exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, (byte) 5);
		exp.createPrimitiveLiteral(IExpression.INFIX_RIGHT, (short) 3);
		IBeanProxy result = exp.getExpressionValue();
		assertNotNull(result);
		assertEquals("int", result.getTypeProxy().getFormalTypeName());
		assertEquals(2, ((INumberBeanProxy) result).intValue());
	}	
	
	public void testVoidReturnType() throws IllegalStateException, ThrowableProxy {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createInfixExpression(IExpression.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
			exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, 3);
			exp.createMethodInvocation(IExpression.INFIX_RIGHT, "gc", true, 0);
			exp.createTypeReceiver("java.lang.System");
			exp.getExpressionValue();
			fail("Should of received no expression value exception.");
		} catch (NoExpressionValueException e) {
			if (e.getLocalizedMessage() != null)
				System.out.println(e.getLocalizedMessage());
		}
	}
	
	public void testWrongReturnType() throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createInfixExpression(IExpression.ROOTEXPRESSION, IExpressionConstants.IN_PLUS, 0);
			exp.createPrimitiveLiteral(IExpression.INFIX_LEFT, 3);
			exp.createMethodInvocation(IExpression.INFIX_RIGHT, "getSecurityManager", true, 0);
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
		exp.createMethodInvocation(IExpression.ROOTEXPRESSION, "getSecurityManager", true, 0);
		exp.createTypeReceiver("java.lang.System");
		exp.invokeExpression();
	}
	
	public void testInvokeFail() throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		IExpression exp = proxyFactory.createExpression();
		try {
			exp.createMethodInvocation(IExpression.ROOTEXPRESSION, "getenv", true, 1);
			exp.createTypeReceiver("java.lang.System");
			exp.createStringLiteral(IExpression.METHOD_ARGUMENT, "xyz");
			exp.invokeExpression();
		} catch (ThrowableProxy e) {
			if (!e.getTypeProxy().getFormalTypeName().equals("java.lang.Error"))
				throw e;	// Some other exception, rethrow it.
		}
	}
}
