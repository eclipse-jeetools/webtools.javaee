/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.ejb.tests;

import junit.framework.TestSuite;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.InjectionTarget;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.SessionType;
import org.eclipse.jst.javaee.ejb.TransactionType;
import org.eclipse.jst.jee.model.internal.EjbAnnotationFactory;
import org.eclipse.jst.jee.model.internal.common.Result;
import org.eclipse.jst.jee.model.tests.AbstractAnnotationFactoryTest;
import org.eclipse.jst.jee.model.tests.TestUtils;

/**
 * Contains tests for {@link EjbAnnotationFactory}. This class includes test
 * that do not depend on resolving types. Negative tests are also included here.
 * 
 * @author Kiril Mitov k.mitov@sap.com
 */
public class EjbAnnotationFactoryTest extends AbstractAnnotationFactoryTest {

	public static TestSuite suite() {
		TestSuite suite = new TestSuite(EjbAnnotationFactoryTest.class);
		return suite;
	}

	/**
	 * The content of the annotation is not valid.It should be an array.
	 * 
	 * @throws JavaModelException
	 */
	// @Test
	public void testNotArrayLocal() throws JavaModelException {
		final String beanContent = "package com.sap; "
				+ "@Stateless @Local(value = java.util.List.class) public class testNotArrayLocal {}";
		IType type = createCompilationUnit("testNotArrayLocal", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean bean = (SessionBean) result.getMainObject();
		assertTrue(bean.getBusinessLocals().isEmpty());
	}

	/**
	 * The content of the annotation is not valid.It should be an array.
	 * 
	 * @throws JavaModelException
	 */
	// @Test
	public void testNotArrayRemote() throws JavaModelException {
		final String beanContent = "package com.sap; "
				+ "@Stateless @Remote(value = java.util.List.class) public class testNotArrayRemote {}";
		IType type = createCompilationUnit("testNotArrayRemote", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean bean = (SessionBean) result.getMainObject();
		assertTrue(bean.getBusinessRemotes().isEmpty());
	}

	// @Test
	public void testNotArrayEjbs() throws JavaModelException {
		final String beanContent = "package com.sap; "
				+ "@Stateless @EJBs(value = @EJB()) public class testNotArrayEjbs {}";
		IType type = createCompilationUnit("testNotArrayEjbs", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean bean = (SessionBean) result.getMainObject();
		assertTrue(bean.getEjbLocalRefs().isEmpty());
	}

	// @Test
	public void testNotArrayResources() throws JavaModelException {
		final String beanContent = "package com.sap; "
				+ "@Stateless @Resources(value = @Resource(type = \"java.lang.Comparable\")) public class testNotArrayResources {}";
		IType type = createCompilationUnit("testNotArrayResources", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean bean = (SessionBean) result.getMainObject();
		assertTrue(bean.getResourceRefs().isEmpty());
	}

	// @Test
	public void testNotArrayDeclareRoles() throws JavaModelException {
		final String beanContent = "package com.sap; "
				+ "@Stateless @DeclareRoles(value = \"role1\") public class testNotArrayDeclareRoles {}";
		IType type = createCompilationUnit("testNotArrayDeclareRoles", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean bean = (SessionBean) result.getMainObject();
		assertTrue(bean.getSecurityRoleRefs().isEmpty());
	}

	// @Test
	public void testNotArrayActivationConfig() throws JavaModelException {
		final String beanContent = "package com.sap; "
				+ "@MessageDriven(activationConfig = "
				+ "@ActivationConfigProperty(propertyName = \"name1\", propertyValue = \"value1\"),"
				+ "@ActivationConfigProperty(propertyName = \"name2\", propertyValue = \"value2\")) public class testNotArrayActivationConfig {}";
		IType type = createCompilationUnit("testNotArrayActivationConfig", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		MessageDrivenBean bean = (MessageDrivenBean) result.getMainObject();
		assertNull(bean.getActivationConfig());
	}

	// @Test
	public void testTimeout() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testTimeout implements SessionBeanLocal{"
				+ "@Timeout public void timeoutMethod1(javax.ejb.Timer timer) {}"
				+ "@Timeout public void timeoutMethod2(javax.ejb.Timer timer) {}"
				+ "@Timeout public void timeoutMethod3() {}" + "}";
		IType type = createCompilationUnit("testTimeout", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean.getTimeoutMethod());
		assertEquals("timeoutMethod2", sessionBean.getTimeoutMethod().getMethodName());
		assertEquals("javax.ejb.Timer", sessionBean.getTimeoutMethod().getMethodParams().getMethodParams().get(0));
	}

	// @Test
	public void testTimeoutStateful() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateful public class testTimeoutStateful implements SessionBeanLocal{"
				+ "@Timeout public void timeoutMethod(javax.ejb.Timer timer) {}" + "}";
		IType type = createCompilationUnit("testTimeoutStateful", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertNull(sessionBean.getTimeoutMethod());
	}
	
	// @Test
	public void testSingleton() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Singleton public class testSingleton implements SessionBeanLocal{}";
		IType type = createCompilationUnit("testSingleton", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertTrue(sessionBean.getSessionType() == SessionType.SINGLETON_LITERAL);
	}

	// @Test
	public void testTransactionManagementBean() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateful @TransactionManagement(value = TransactionManagementType.BEAN) "
				+ "public class testTransactionManagementBean implements SessionBeanLocal{" + "}";
		IType type = createCompilationUnit("testTransactionManagementBean", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals(TransactionType.BEAN_LITERAL, sessionBean.getTransactionType());

	}

	// @Test
	public void testTransactionManagementContainer() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateful @TransactionManagement(value = TransactionManagementType.CONTAINER) "
				+ "public class testTransactionManagementContainer implements SessionBeanLocal{" + "}";
		IType type = createCompilationUnit("testTransactionManagementContainer", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals(TransactionType.CONTAINER_LITERAL, sessionBean.getTransactionType());
	}

	// @Test
	public void testTransactionManagementContainerMDB() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@MessageDriven @TransactionManagement(value = TransactionManagementType.CONTAINER) "
				+ "public class testTransactionManagementContainerMDB {" + "}";
		IType type = createCompilationUnit("testTransactionManagementContainerMDB", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		MessageDrivenBean messageBean = (MessageDrivenBean) result.getMainObject();
		assertNotNull(messageBean);
		assertEquals(TransactionType.CONTAINER_LITERAL, messageBean.getTransactionType());
	}

	// @Test
	public void testTransactionManagementStaticImportContainer() throws Exception {
		final String beanContent = "package com.sap; " + "import static TransactionManagementType.CONTAINER;"
				+ "@Stateful @TransactionManagement(value = CONTAINER) "
				+ "public class testTransactionManagementStaticImportContainer implements SessionBeanLocal{" + "}";
		IType type = createCompilationUnit("testTransactionManagementStaticImportContainer", beanContent)
				.findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertEquals(TransactionType.CONTAINER_LITERAL, sessionBean.getTransactionType());
	}

	// @Test
	public void testRemoveMethod() throws Exception {
		final String beanContent = "package com.sap"
				+ "@Stateful public class testRemoveMethod implements SessionBeanLocal {"
				+ "@Remove public void removeMethod1() {}"
				+ "@Remove(retainIfException = true) public void removeMethod2() {}"
				+ "@Remove(retainIfException = true) public void removeMethodParam(java.lang.String str) {}" + "}";
		IType type = createCompilationUnit("testRemoveMethod", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		RemoveMethodType method1 = TestUtils.findRemoveMethodByName(sessionBean, "removeMethod1");
		RemoveMethodType method2 = TestUtils.findRemoveMethodByName(sessionBean, "removeMethod2");
		assertNotNull(method1);
		assertNotNull(method2);
		assertFalse(method1.isRetainIfException());
		assertTrue(method2.isRetainIfException());
		assertEquals(new Integer(2), new Integer(sessionBean.getRemoveMethods().size()));
	}

	// @Test
	public void testRemoveMethodOnStateless() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testRemoveMethodOnStateless implements SessionBeanLocal {"
				+ "@Remove public void removeMethod1() {}" + "}";
		IType type = createCompilationUnit("testRemoveMethodOnStateless", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertTrue(sessionBean.getRemoveMethods().isEmpty());
	}

	// @Test
	public void testDeclareRolesOnMessageDrivenBean() throws Exception {
		final String beanContent = "package com.sap;" + "@DeclareRoles(value = {\"role1\", \"role2\"}) "
				+ "@MessageDriven public class testDeclareRolesOnMessageDrivenBean implements SessionBeanLocal {}";
		IType type = createCompilationUnit("testDeclareRolesOnMessageDrivenBean", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		MessageDrivenBean bean = (MessageDrivenBean) result.getMainObject();
		assertNotNull(bean);
	}

	public void testEjbRefName() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testEjbRefName implements SessionBeanLocal {"
				+ "@EJB(name=\"refName\") private SessionBeanLocal field;" + "}";
		IType type = createCompilationUnit("testEjbRefName", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals(1, sessionBean.getEjbLocalRefs().size());
		EjbLocalRef ref = (EjbLocalRef) sessionBean.getEjbLocalRefs().get(0);
		assertEquals(1, ref.getInjectionTargets().size());
		assertInjectionTarget("refName", "", (InjectionTarget) ref.getInjectionTargets().get(0));
	}

	public void testEjbRefNameWithSlash() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testEjbRefNameWithSlash implements SessionBeanLocal {"
				+ "@EJB(name=\"refClass/refName\") private SessionBeanLocal field;" + "}";
		IType type = createCompilationUnit("testEjbRefNameWithSlash", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals(1, sessionBean.getEjbLocalRefs().size());
		EjbLocalRef ref = (EjbLocalRef) sessionBean.getEjbLocalRefs().get(0);
		assertEquals(1, ref.getInjectionTargets().size());
		assertInjectionTarget("refName", "refClass", (InjectionTarget) ref.getInjectionTargets().get(0));
	}

	public void testResourceRefName() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testResourceRefName implements SessionBeanLocal {"
				+ "@Resource(name=\"refName\") private SessionBeanLocal field;" + "}";
		IType type = createCompilationUnit("testResourceRefName", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals(1, sessionBean.getResourceRefs().size());
		ResourceRef ref = (ResourceRef) sessionBean.getResourceRefs().get(0);
		assertEquals(1, ref.getInjectionTargets().size());
		assertInjectionTarget("refName", "", (InjectionTarget) ref.getInjectionTargets().get(0));
	}

	public void testResourceRefNameWithSlash() throws Exception {
		final String beanContent = "package com.sap;"
				+ "@Stateless public class testResourceRefNameWithSlash implements SessionBeanLocal {"
				+ "@Resource(name=\"refClass/refName\") private SessionBeanLocal field;" + "}";
		IType type = createCompilationUnit("testResourceRefNameWithSlash", beanContent).findPrimaryType();
		Result result = fixture.createJavaeeObject(type);
		SessionBean sessionBean = (SessionBean) result.getMainObject();
		assertNotNull(sessionBean);
		assertEquals(1, sessionBean.getResourceRefs().size());
		ResourceRef ref = (ResourceRef) sessionBean.getResourceRefs().get(0);
		assertEquals(1, ref.getInjectionTargets().size());
		assertInjectionTarget("refName", "refClass", (InjectionTarget) ref.getInjectionTargets().get(0));
	}
	
	private void assertInjectionTarget(String targetName, String targetClass, InjectionTarget target) {
		assertEquals(targetClass, target.getInjectionTargetClass());
		assertEquals(targetName, target.getInjectionTargetName());
	}
}
