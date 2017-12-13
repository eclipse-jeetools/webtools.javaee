/**
 * 
 */
package org.eclipse.jst.j2ee.tests.performance;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.tests.BaseTestCase;

/**
 * @author itewk
 *
 */
public class PerformanceTestSuite extends TestSuite {
	private static final String DEFAULT_SIMPLE_NAME = "Performance Test";
	
	/**
	 * 
	 * @param name
	 */
	public PerformanceTestSuite(String name) {
		super(name);
	}
	
	public void addTest(Test test) {
		//must be a BaseTestCase so it can be wrapped in a PerformanceTestCaseWrapper
		if(test instanceof BaseTestCase) {
			PerformanceTestCaseWrapper performanceTest = new PerformanceTestCaseWrapper(
					(BaseTestCase)test,PerformanceTestCaseWrapper.GLOBAL,DEFAULT_SIMPLE_NAME);
			super.addTest(performanceTest);
		}
	}
	
	/**
	 * 
	 */
	public void addTest(Test test, String simpleName) {
		//must be a BaseTestCase so it can be wrapped in a PerformanceTestCaseWrapper
		if(test instanceof BaseTestCase) {
			PerformanceTestCaseWrapper performanceTest = new PerformanceTestCaseWrapper(
					(BaseTestCase)test,PerformanceTestCaseWrapper.GLOBAL,simpleName);
			super.addTest(performanceTest);
		}
	}
}
