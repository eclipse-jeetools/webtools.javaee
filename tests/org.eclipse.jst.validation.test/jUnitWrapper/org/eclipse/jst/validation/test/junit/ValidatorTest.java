package org.eclipse.jst.validation.test.junit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.validation.test.internal.registry.TestcaseUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.internal.util.BVTRunner;


/**
 * Instances of this class represent one <test>, contributed in
 * plugin.xml, for one validator.
 */
public class ValidatorTest extends TestCase {
	private ValidatorTestcase _tmd = null;
	private ValidatorSuite _suite = null;

	public ValidatorTest(ValidatorTestcase tmd, ValidatorSuite suite) {
		super(tmd.getName()); // the method named "test" runs the test.
		_tmd = tmd;
		_suite = suite;
	}
	
	public JUnitBuffer getBuffer() {
		return _suite.getBuffer();
	}
	
	public String toString() {
		return _tmd.getProjectName();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#runTest()
	 */
	protected void runTest() throws Throwable {
		try {
			IProject project = TestcaseUtility.findProject(_tmd);
			if((project == null) || !project.exists()) {
				// File needs to be imported (i.e., set up the test).
				if(!BVTRunner.singleton().setupTests(getBuffer(), _tmd, false)) {
					fail("Could not import input from directory " + TestcaseUtility.getInputDir(_tmd)); //$NON-NLS-1$
				}
			}

			if(!project.isAccessible()) {
				fail("Project " + project.getName() + " is not accessible."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			_tmd.run(getBuffer(), project);
			if(!getBuffer().isSuccessful(_tmd.getName())) {
				fail(_tmd.getName() + " failed. Read the log for details. " + getBuffer().getLogFileName()); //$NON-NLS-1$
			}
		}
		finally {
			// Whether this test case fails or not, send its results to the log.
			getBuffer().delineate(_tmd.getName());
		}
	}
}
