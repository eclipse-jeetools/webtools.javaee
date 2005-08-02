/*
 * Created on Feb 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.headless.tests.savestrategy;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AllTests extends TestSuite {

	public static Test suite() {
		return new AllTests();
	}

	public AllTests() {
		super("WEB Tests");
		addTest(new SimpleTestSuite(WebSaveStrategyTests.class));
		addTest(new SimpleTestSuite(AppClientSaveStrategyTests.class));
		addTest(new SimpleTestSuite(AppClientImportOperationTest.class));
		addTest(new SimpleTestSuite(EJBImportOperationTest.class));
		addTest(new SimpleTestSuite(WebImportOperationTest.class));
	}

}
