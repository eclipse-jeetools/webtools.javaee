/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.validation.test.junit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.validation.test.internal.registry.TestcaseUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.jst.validation.test.internal.util.BVTRunner;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;


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
			ValidatorManager vm = ValidatorManager.getManager();
			ValidatorManager.addProjectBuildValidationSupport(project);
			vm.enableValidator("ClasspathDependencyValidator");
			vm.disableValidator("EarValidator");
			vm.enableValidator("WarValidator", project, true, false);
			vm.disableValidator("EJBValidator", project, true, true);
		}
		finally {
			// Whether this test case fails or not, send its results to the log.
			getBuffer().delineate(_tmd.getName());
		}
	}
}
