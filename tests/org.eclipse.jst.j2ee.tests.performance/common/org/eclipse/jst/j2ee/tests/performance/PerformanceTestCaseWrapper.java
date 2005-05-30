/*
 * Created on Nov 24, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.tests.performance;

import junit.framework.Test;

import org.eclipse.test.performance.Dimension;
import org.eclipse.test.performance.Performance;
import org.eclipse.test.performance.PerformanceTestCase;
import org.eclipse.wst.common.tests.BaseTestCase;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public  class PerformanceTestCaseWrapper extends PerformanceTestCase {
	protected BaseTestCase fTest;
	public static final int NONE = 0;
	public static final int LOCAL = 1;
	public static final int GLOBAL = 2;	
	
	private boolean tagAsGlobalSummary;
	private boolean tagAsSummary;
	private String shortName;
	
	public static final int ITERATIONS = 10;

	public PerformanceTestCaseWrapper(BaseTestCase test, 
									int tagging,
									String shortName) {
		fTest=test;
		tagAsGlobalSummary = ((tagging & GLOBAL) != 0);
	    tagAsSummary = ((tagging & LOCAL) != 0);
	    this.shortName =  shortName;
	}
	
	public int countTestCases() {
		return fTest.countTestCases();
	}
	
	public String toString() {
		return fTest.toString();
	}

	public Test getTest() {
		return fTest;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.test.performance.PerformanceTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		fTest.setUpTest();
	}
	
	protected void basicSetUp() throws Exception {
		Performance performance= Performance.getDefault();
		fPerformanceMeter= performance.createPerformanceMeter(performance.getDefaultScenarioId(fTest));
	}

	
	/**
	 * Runs the bare test sequence.
	 * @exception Throwable if any exception is thrown
	 */
	public void runBare() throws Throwable {
		basicSetUp();
		tagIfNecessary(shortName, Dimension.CPU_TIME);
		try {
			for (int i= 0; i < ITERATIONS; i++) {
				setUp();
				startMeasuring();
				runTest();
				stopMeasuring();
			}
		}
		finally {
			commitMeasurements();
			assertPerformance();
			tearDown();
		}
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#runTest()
	 */
	protected void runTest() throws Throwable {
		// TODO Auto-generated method stub
		fTest.runCoreTest();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.test.performance.PerformanceTestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		fTest.tearDownTest();
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setName(java.lang.String)
	 */
	public void setName(String name) {
		// TODO Auto-generated method stub
		fTest.setName(name);
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#getName()
	 */
	public String getName() {
		// TODO Auto-generated method stub
		return fTest.getName();
	}
	
    /**
     * Answers whether this test should be tagged globally.
     * 
     * @return whether this test should be tagged globally
     */
    private boolean shouldGloballyTag() {
    	return tagAsGlobalSummary;
    }
    
    /**
     * Answers whether this test should be tagged locally.
     * 
     * @return whether this test should be tagged locally
     */
    private boolean shouldLocallyTag() {
    	return tagAsSummary;
    }
    
    public void tagIfNecessary(String shortName, Dimension dimension) {
		if (shouldGloballyTag()) {
			tagAsGlobalSummary(shortName, dimension);
		}
		if (shouldLocallyTag()) {
			tagAsSummary(shortName, dimension);
		}
	}
	
	public void tagIfNecessary(String shortName, Dimension [] dimensions) {
		if (shouldGloballyTag()) {
			tagAsGlobalSummary(shortName, dimensions);
		}
		if (shouldLocallyTag()) {
			tagAsSummary(shortName, dimensions);
		}
	}
	
	
}
