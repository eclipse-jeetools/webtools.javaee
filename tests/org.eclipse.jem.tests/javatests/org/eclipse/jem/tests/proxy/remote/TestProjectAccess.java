package org.eclipse.jem.tests.proxy.remote;
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
/*
 *  $RCSfile: TestProjectAccess.java,v $
 *  $Revision: 1.2 $  $Date: 2003/10/27 17:32:36 $ 
 */
import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.core.IBeanProxy;
import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
import org.eclipse.jem.tests.proxy.AbstractTestProxy;

/**
 * @author richkulp
 *
 * Test Project Access.
 */
public class TestProjectAccess extends AbstractTestProxy {

	public TestProjectAccess() {
		super();
	}

	public TestProjectAccess(String name) {
		super(name);
	}
	
	public void testClassFromProject() {
		IBeanTypeProxy testClassType = proxyTypeFactory.getBeanTypeProxy("org.eclipse.jem.testing.proxy.remote.TestClass"); //$NON-NLS-1$
		assertNotNull(testClassType);		
	}
	
	public void testProjectClassInstantiation() throws ThrowableProxy {
		IBeanTypeProxy testClassType = proxyTypeFactory.getBeanTypeProxy("org.eclipse.jem.testing.proxy.remote.TestClass"); //$NON-NLS-1$
		IBeanProxy testClass = testClassType.newInstance();
		assertNotNull(testClass);		
	}

	public void testProjectClassMethod() throws ThrowableProxy {
		IBeanTypeProxy testClassType = proxyTypeFactory.getBeanTypeProxy("org.eclipse.jem.testing.proxy.remote.TestClass"); //$NON-NLS-1$
		IMethodProxy testMethod = testClassType.getMethodProxy("getTestString"); //$NON-NLS-1$
		assertNotNull(testMethod);		
	}
	
	public void testProjectClassMethodInvoke() throws ThrowableProxy {
		IBeanTypeProxy testClassType = proxyTypeFactory.getBeanTypeProxy("org.eclipse.jem.testing.proxy.remote.TestClass"); //$NON-NLS-1$
		IMethodProxy testMethod = testClassType.getMethodProxy("getTestString"); //$NON-NLS-1$
		IBeanProxy testClass = testClassType.newInstance();
		IStringBeanProxy aString = (IStringBeanProxy) testMethod.invoke(testClass);
		assertNotNull(aString);
		assertEquals("TESTSTRING", aString.stringValue());		
	}	

}
