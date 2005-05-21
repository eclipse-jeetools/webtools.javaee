package org.eclipse.jst.validation.test.internal.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.validation.test.internal.registry.ITestcaseMetaData;
import org.eclipse.jst.validation.test.setup.IBuffer;


/**
 * Run the BVT of the validator: a full test, an incremental test,
 * and an asynchronous test, if applicable.
 * 
 * This class must NOT be wrapped in an IWorkspaceRunnable or 
 * there will be deadlock during the asynchronous validation test!
 * (Read the comment above VThreadManager::isDone() for details.)
 */
public class RunTestsOperation {
	private static RunTestsOperation _inst = null;
	
	private RunTestsOperation() {
	}
	
	public static RunTestsOperation singleton() {
		if(_inst == null) {
			_inst = new RunTestsOperation();
		}
		return _inst;
	}
	
	/**
	 * @see org.eclipse.core.resources.IWorkspaceRunnable#run(IProgressMonitor)
	 */
	public void run(IBuffer buffer, ITestcaseMetaData[] allTmds) {
		IProject[] projects = BVTValidationUtility.getProjects(allTmds);
		for(int i=0; i<projects.length; i++) {
			IProject project = projects[i];
			ITestcaseMetaData[] tmds = BVTValidationUtility.getTests(allTmds, project);
			
			for(int j=0; j<tmds.length; j++) {
				ITestcaseMetaData tmd = tmds[j];
				tmd.run(buffer, project);
			}
		}
	}
	

}
