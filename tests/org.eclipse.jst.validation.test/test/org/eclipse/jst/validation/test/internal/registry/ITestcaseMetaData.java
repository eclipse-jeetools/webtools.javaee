package org.eclipse.jst.validation.test.internal.registry;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.validation.test.setup.IBuffer;


/**
 * This interface represents a test case that either should be run
 * as part of the Validation BVT Suite, or that is part of a test
 * case that is run by the Validation BVT Suite.
 */
public interface ITestcaseMetaData {
	/**
	 * Return true if this test case should be shown as a test on 
	 * the test collector menu.
	 */
	public boolean isVisible();
	
	/**
	 * Return the project that this test case is designed to run on.
	 */
	public IProject getProject();
	
	/**
	 * Return the name of the project that this test case is designed 
	 * to run on.
	 */
	public String getProjectName();
	
	/**
	 * Return the id of the plugin that declared this test case.
	 */
	public String getDeclaringPluginId();
	
	/**
	 * Return the name of this test case.
	 */
	public String getName();
	
	/**
	 * Return the name of the file that must be imported for this test case
	 * to run on.
	 */
	public String getInputFileName();
	
	/**
	 * Import the file, if necessary, and run this test. The test case
	 * reports whether or not the test passes to the ITestBuffer.
	 */
	public void run(IBuffer buffer, IProject project);
}
