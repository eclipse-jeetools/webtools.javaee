package org.eclipse.jst.j2ee.tests.performance;

import org.eclipse.test.performance.Dimension;
import org.eclipse.test.performance.PerformanceTestCase;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.JavaEEFacetConstants;

public class EJBProjectCreationTestCase extends PerformanceTestCase {

	public void testEJB30_Defaults() throws Exception {
		Dimension[] dims = new Dimension[] { Dimension.ELAPSED_PROCESS, Dimension.USED_JAVA_HEAP };
		tagAsGlobalSummary("EJB project creation", dims);
		startMeasuring();
		
		// Create an EJB 3.0 project with default settings
		EJBProjectCreationOperationTest.
			getEJBDataModel("ejb30_defaults",            /* projName */
							null,                        /* clientName */
							null,                        /* clientSourceFolder */
							null,                        /* earName */
							JavaEEFacetConstants.EJB_3,  /* version */
							false                        /* createDD */
			);
		
		stopMeasuring();
		commitMeasurements();
		assertPerformance();
	}
	
}
