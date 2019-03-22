/*******************************************************************************
 * Copyright (c) 2018 Red Hat Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 *******************************************************************************/
package org.eclipse.jst.j2ee.tests;

import org.eclipse.jst.common.annotations.tests.AnnotationProviderTest;
import org.eclipse.jst.j2ee.defect.tests.DefectVerificationTestsSuite;
import org.eclipse.jst.jee.model.ejb.tests.EJBModelSuite;
import org.eclipse.jst.jee.model.mergers.tests.ModelMergersSuite;
import org.eclipse.jst.jee.model.web.tests.WebModelSuite;
import org.eclipse.wtp.j2ee.headless.tests.exportmodel.ExportModelTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.headers.HeaderParserTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class QuickSuite extends TestSuite {
	public static Test suite() {
		return new QuickSuite();
	}

	public QuickSuite() {
		super("Quick-Running Unit Tests");
		addTest(DefectVerificationTestsSuite.suite()); 
//		addTest(WebAppLibrariesContainerTests.suite());  // Suite has failures
//		addTest(ThreadingTest.suite());  // Suite has failures
		addTest(ExportModelTest.suite());
		addTest(AnnotationProviderTest.suite());
//		addTestSuite(BindingsHelperTest.class);
		addTest(org.eclipse.jst.j2ee.classpath.tests.AllTests.suite());  
//		addTest(ArtifactEditSuite.suite()); // Suite has lots of failures
//		addTest(FVTestSuite.suite()); // Suite has failures
		addTest(org.eclipse.jst.j2ee.tests.modulecore.AllTests.suite()); 

		addTest(EJBModelSuite.suite());  

		addTest(ModelMergersSuite.suite()); 
		addTest(WebModelSuite.suite()); 
		addTest(HeaderParserTests.suite());
	}

}
