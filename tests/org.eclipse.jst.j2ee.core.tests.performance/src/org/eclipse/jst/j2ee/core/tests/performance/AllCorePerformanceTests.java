/*
 * Created on Nov 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.core.tests.performance;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.archive.emftests.AppClientEMFEditTest;
import org.eclipse.jst.j2ee.archive.emftests.AppClientEMFTest;
import org.eclipse.jst.j2ee.archive.emftests.EarEMFEditTest;
import org.eclipse.jst.j2ee.archive.emftests.EarEMFTest;
import org.eclipse.jst.j2ee.archive.emftests.EjbEMFEditTest;
import org.eclipse.jst.j2ee.archive.emftests.RarEMFEditTest;
import org.eclipse.jst.j2ee.archive.emftests.RarEMFTest;
import org.eclipse.jst.j2ee.archive.emftests.WarEMFEditTest;
import org.eclipse.jst.j2ee.archive.emftests.WarEMFTest;
import org.eclipse.jst.j2ee.archive.emftests.WebServicesEMFTest;
import org.eclipse.jst.j2ee.tests.performance.BasePerformanceTestCase;
import org.eclipse.jst.j2ee.tests.performance.PerformanceTestCaseWrapper;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.ConnectorProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;

/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AllCorePerformanceTests extends BasePerformanceTestCase{
	
	public static Test suite() {
	
		TestSuite suite = new TestSuite("Performance Test for com.ibm.etools.archive.emftest");
		addPerformanceTest(suite, new AppClientEMFEditTest("testApplicationClientEdit"), PerformanceTestCaseWrapper.GLOBAL, "Application Client emf edit test" );
		addPerformanceTest(suite, new AppClientEMFTest("testApplicationClientPopulation"), PerformanceTestCaseWrapper.LOCAL, "Application Client emf test" );
		addPerformanceTest(suite, new AppClientEMFTest("test14ApplicationClientPopulation"), PerformanceTestCaseWrapper.GLOBAL, "Application Client 14 emf test" );
		
		addPerformanceTest(suite, new EarEMFEditTest("testEAREdit"), PerformanceTestCaseWrapper.GLOBAL, "EAR emf edit test" );
		//addPerformanceTest(suite, new EarEMFTest("testEARPopulation"), PerformanceTestCaseWrapper.LOCAL, "EAR  emf test" );
		addPerformanceTest(suite, new EarEMFTest("test14EARPopulation"), PerformanceTestCaseWrapper.GLOBAL, "EAR 14 emf test" );
		
		//addPerformanceTest(suite, new EjbEMFEditTest("testEJBJarEdit"), PerformanceTestCaseWrapper.GLOBAL, "EJB emf edit test -testEJBJarEdit" );
		addPerformanceTest(suite, new EjbEMFEditTest("testWCCMJar"), PerformanceTestCaseWrapper.LOCAL, "EJB emf edit test - testWCCMJar" );
		//addPerformanceTest(suite, new EjbEMFTest("testEJBJarPopulation"), PerformanceTestCaseWrapper.LOCAL, "EJB  emf test" );
		//addPerformanceTest(suite, new EjbEMFTest("test14EJBJarPopulation"), PerformanceTestCaseWrapper.GLOBAL, "EJB 14 emf test" );
	
		addPerformanceTest(suite, new RarEMFEditTest("testRAREdit"), PerformanceTestCaseWrapper.GLOBAL, "RAR emf edit test" );
		addPerformanceTest(suite, new RarEMFTest("testRARPopulation"), PerformanceTestCaseWrapper.LOCAL, "RAR  emf test" );
		addPerformanceTest(suite, new RarEMFTest("test14RARPopulation"), PerformanceTestCaseWrapper.GLOBAL, "RAR 14 emf test" );
	
		addPerformanceTest(suite, new WarEMFEditTest("testWAREdit"), PerformanceTestCaseWrapper.GLOBAL, "WAR emf edit test" );
		//addPerformanceTest(suite, new WarEMFTest("testWARPopulation"), PerformanceTestCaseWrapper.LOCAL, "WAR  emf test" );
		addPerformanceTest(suite, new WarEMFTest("test14WARPopulation"), PerformanceTestCaseWrapper.GLOBAL, "WAR 14 emf test" );
		
		addPerformanceTest(suite, new WebServicesEMFTest("test13WebServicesClientPopulation"), PerformanceTestCaseWrapper.NONE, "Web Service Client 13 emf test" );
		addPerformanceTest(suite, new WebServicesEMFTest("test13WebServicesDDPopulation"), PerformanceTestCaseWrapper.LOCAL, "Web services 13 emf test" );
		addPerformanceTest(suite, new WebServicesEMFTest("test14WebServicesDDPopulation"), PerformanceTestCaseWrapper.GLOBAL, "Web services 14 emf test" );
		addPerformanceTest(suite, new WebServicesEMFTest("testJaxRPCMapPopulation"), PerformanceTestCaseWrapper.GLOBAL, "JaxRPCMapping emf test" );
		
		
		//Creation tests
		addPerformanceTest(suite, new EJBProjectCreationOperationTest("testDefaults"), PerformanceTestCaseWrapper.NONE, "EJB Component creation test" );
		addPerformanceTest(suite, new AppClientProjectCreationOperationTest("testDefaults"), PerformanceTestCaseWrapper.LOCAL, "App Client Component creation test" );
		addPerformanceTest(suite, new ConnectorProjectCreationOperationTest("testDefaults"), PerformanceTestCaseWrapper.GLOBAL, "Connector Component creation test" );
		addPerformanceTest(suite, new WebProjectCreationOperationTest("testDefaults"), PerformanceTestCaseWrapper.GLOBAL, "Web Component creation test" );
		
		return suite;
	}

	
	
}
