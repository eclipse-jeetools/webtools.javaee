/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestCompatibleMethods.java,v $
 *  $Revision: 1.1 $  $Date: 2006/03/19 18:27:12 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.tree.ForExpression;
import org.eclipse.jem.internal.proxy.initParser.tree.NoExpressionValueException;
import org.eclipse.jem.tests.proxy.initParser.AbstractInitParserTestCase;
 

/**
 * 
 * @since 1.2.0
 */
public class TestCompatibleMethods extends AbstractInitParserTestCase {

	/**
	 * @param name
	 * 
	 * @since 1.2.0
	 */
	public TestCompatibleMethods(String name) {
		super(name);
	}

	protected ASTTreeInitStringParserTestHelper getTreeParser() {
		return (ASTTreeInitStringParserTestHelper) testHelper;
	}
	
	protected IBeanProxy executeQQQ(IBeanProxy a1, IBeanProxy a2) throws ThrowableProxy, IllegalStateException, NoExpressionValueException {
		ProxyFactoryRegistry registry = getTreeParser().getRegistry();
		IExpression exp = registry.getBeanProxyFactory().createExpression();
		exp.createMethodInvocation(ForExpression.ROOTEXPRESSION, "qqq", true, 2);
		exp.createClassInstanceCreation(ForExpression.METHOD_RECEIVER, "org.eclipse.jem.tests.proxy.initParser.tree.TestCompatibleMethodsData1", 0);
		exp.createProxyExpression(ForExpression.METHOD_ARGUMENT, a1);
		exp.createProxyExpression(ForExpression.METHOD_ARGUMENT, a2);
		return exp.getExpressionValue();
	}
	
	public void testAmbiguous() throws Throwable {
		ProxyFactoryRegistry registry = getTreeParser().getRegistry();
		try {
			executeQQQ(registry.getBeanProxyFactory().createBeanProxyWith(new Integer(1)), registry.getBeanProxyFactory().createBeanProxyWith(
					new Integer(1)));
		} catch (ThrowableProxy e) {
			if (e.getTypeProxy().getTypeName().equals("org.eclipse.jem.internal.proxy.common.AmbiguousMethodException"))
				return;	// This is good.
			throw e;
		}		
		fail("Should of thrown ambiquous.");
	}
	
	public void testExactMatch() throws Throwable {
		ProxyFactoryRegistry registry = getTreeParser().getRegistry();
		IIntegerBeanProxy result = (IIntegerBeanProxy) executeQQQ(registry.getBeanProxyFactory().createBeanProxyWith(new Integer(1)), registry.getBeanProxyFactory().createBeanProxyFrom("new Object()"));
		assertEquals(2, result.intValue());
		
		result = (IIntegerBeanProxy) executeQQQ(registry.getBeanProxyFactory().createBeanProxyFrom("new Object()"), registry.getBeanProxyFactory().createBeanProxyFrom("new Object()"));
		assertEquals(0, result.intValue());
	}
	
	public void testCompatibleMatch() throws Throwable {
		ProxyFactoryRegistry registry = getTreeParser().getRegistry();
		IIntegerBeanProxy result = (IIntegerBeanProxy) executeQQQ(registry.getBeanProxyFactory().createBeanProxyWith(new Float(1)), registry.getBeanProxyFactory().createBeanProxyWith(new Integer(1)));
		assertEquals(1, result.intValue());

		result = (IIntegerBeanProxy) executeQQQ(registry.getBeanProxyFactory().createBeanProxyWith(new Float(1)), registry.getBeanProxyFactory().createBeanProxyFrom("new Object()"));
		assertEquals(0, result.intValue());
	}
	
}
