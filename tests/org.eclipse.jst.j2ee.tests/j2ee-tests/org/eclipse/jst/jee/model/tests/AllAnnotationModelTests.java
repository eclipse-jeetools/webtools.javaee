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
package org.eclipse.jst.jee.model.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jee.model.ejb.tests.DeleteProjectTest;
import org.eclipse.jst.jee.model.ejb.tests.EJB3MergedModelProviderFactoryTest;
import org.eclipse.jst.jee.model.ejb.tests.EJB3MergedModelProviderTest;
import org.eclipse.jst.jee.model.ejb.tests.EJBAnnotationReaderWithClientTest;
import org.eclipse.jst.jee.model.ejb.tests.Ejb3ModelProviderTest;
import org.eclipse.jst.jee.model.ejb.tests.EjbAnnotationFactoryTest;
import org.eclipse.jst.jee.model.ejb.tests.EjbAnnotationReaderTest;
import org.eclipse.jst.jee.model.ejb.tests.EjbReferenceTest;
import org.eclipse.jst.jee.model.ejb.tests.GenerateDDTest;
import org.eclipse.jst.jee.model.ejb.tests.LifecycleAnnotationsTest;
import org.eclipse.jst.jee.model.ejb.tests.NotifyCloseProjectTest;
import org.eclipse.jst.jee.model.ejb.tests.ResourceReferenceTest;
import org.eclipse.jst.jee.model.ejb.tests.SecurityRolesTest;
import org.eclipse.jst.jee.model.web.tests.DeleteWebProjectTest;
import org.eclipse.jst.jee.model.web.tests.TestWebXmlModelAfterUpdate;
import org.eclipse.jst.jee.model.web.tests.Web25MergedModelProviderTest;
import org.eclipse.jst.jee.model.web.tests.Web3AnnotationReaderTest;
import org.eclipse.jst.jee.model.web.tests.WebAnnotationReaderTest;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
// @SuiteClasses(value = { EjbAnnotationReaderTest.class,
// EJBModelWithClientProjectTest.class, EjbReferenceTest.class,
// LifecycleAnnotationsTest.class, ResourceReferenceTest.class,
// SecurityRolesTest.class, DeleteProjectTest.class,
// ManyToOneRelationTest.class, RegisterMergedModelProviderTest.class,
// EjbAnnotationFactoryTest.class,
// WebAnnotationReaderTest.class, DeleteWebProjectTest.class,
// NotifyCloseProjectTest.class,
// Web25MergedModelProviderTest.class })
// @RunWith(Suite.class)
public class AllAnnotationModelTests {

	public static Test suite() {
		try {
			TestSuite suite = new TestSuite(AllAnnotationModelTests.class.getName());
			suite.addTest(EjbAnnotationReaderTest.suite());
			suite.addTest(EJBAnnotationReaderWithClientTest.suite());
			suite.addTest(EjbReferenceTest.suite());
			suite.addTest(LifecycleAnnotationsTest.suite());
			suite.addTest(ResourceReferenceTest.suite());
			suite.addTest(SecurityRolesTest.suite());
			suite.addTest(DeleteProjectTest.suite());
			suite.addTest(ManyToOneRelationTest.suite());
			suite.addTest(RegisterMergedModelProviderTest.suite());
			suite.addTest(EjbAnnotationFactoryTest.suite());
			suite.addTest(WebAnnotationReaderTest.suite());
			suite.addTest(Web3AnnotationReaderTest.suite());
			suite.addTest(DeleteWebProjectTest.suite());
			suite.addTest(NotifyCloseProjectTest.suite());
			suite.addTest(TestWebXmlModelAfterUpdate.suite());
			suite.addTest(Web25MergedModelProviderTest.suite());
			suite.addTest(GenerateDDTest.suite());
			suite.addTest(EJB3MergedModelProviderFactoryTest.suite());
			suite.addTest(EJB3MergedModelProviderTest.suite());
			suite.addTest(Ejb3ModelProviderTest.suite());
			return suite;
		} catch (Exception e) {
			HeadlessTestsPlugin.getDefault().getLog().log(
					new Status(IStatus.ERROR, "org.eclipse.jst.j2ee.tests", "Error while building the test suite", e));
		}
		return null;
		// return new JUnit4TestAdapter(AllAnnotationModelTests.class);
	}
}
