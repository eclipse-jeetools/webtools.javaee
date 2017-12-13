/**
 * 
 */
package org.eclipse.jst.j2ee.tests.performance;

import junit.framework.Test;

import org.eclipse.wtp.j2ee.headless.tests.ear.operations.EARProjectCreationOperationTest;

/**
 * @author itewk
 *
 */
public class WTPHeadlessPerformanceTests extends PerformanceTestSuite {
	public WTPHeadlessPerformanceTests() {
		super("All WTP Headless Performances Tests");
		
		addTest(new EARProjectCreationOperationTest("testEAR14_WithDependencies"), "Test EAR 1.4 Project Creation with Dependincies");
	}
	
	public static Test suite(){
		return new WTPHeadlessPerformanceTests();
	}
}
