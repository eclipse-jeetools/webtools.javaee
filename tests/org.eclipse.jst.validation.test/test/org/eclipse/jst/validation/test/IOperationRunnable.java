package org.eclipse.jst.validation.test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.jst.validation.test.setup.IBuffer;


/**
 * Instances of this interface are run as part of the Test Collector BVT.
 */
public interface IOperationRunnable extends IWorkspaceRunnable {
	public void setBuffer(IBuffer b);
	public void setProject(IProject p);
	public void setName(String testName);
}
