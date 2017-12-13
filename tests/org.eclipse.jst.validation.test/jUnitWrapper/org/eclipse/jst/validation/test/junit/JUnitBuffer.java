/*
 * Created on Apr 16, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.validation.test.junit;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.BufferedTaskStatus;
import org.eclipse.jst.validation.test.setup.IBuffer;


/**
 * When the tests are run as part of JUnit, redirect the output
 * to the JUnit test case.
 */
public class JUnitBuffer implements IBuffer {
	private String _logFileName = null;
	private BufferedTaskStatus _status = null;
	
	JUnitBuffer(String logFileName) {
		_status = new BufferedTaskStatus(getLogFileName(logFileName), new NullProgressMonitor());
	}
	
	public void setProgressMonitor(IProgressMonitor monitor) {
		_status.setProgressMonitor(monitor);
	}
	
	private String getLogFileName(String logFileName) {
		if(_logFileName == null) {
			logFileName = logFileName.replace(' ', '_');
			IPath stateLocation = BVTValidationPlugin.getPlugin().getStateLocation();
			File log = new File(stateLocation.toOSString(), logFileName); //$NON-NLS-1$
			_logFileName = log.getAbsolutePath();
		}
		return _logFileName;
	}
	
	public String getLogFileName() {
		return _status.getLogFileName();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#clear()
	 */
	public void clear() {
		_status.clear();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#delineate(java.lang.String)
	 */
	public void delineate(String taskName) {
		_status.delineate(taskName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#getProgressMonitor()
	 */
	public IProgressMonitor getProgressMonitor() {
		return _status.getProgressMonitor();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#report()
	 */
	public void report() {
		_status.report();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#write(java.lang.String)
	 */
	public void write(String message) {
		_status.write(message);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#addElapsedTime(java.lang.String, long)
	 */
	public void addElapsedTime(String taskName, long elapsedTime) {
		_status.addElapsedTime(taskName, elapsedTime);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#addExecutionStatus(java.lang.String, int, org.eclipse.jst.validation.test.setup.IBuffer, java.lang.String)
	 */
	public void addExecutionStatus(String testcaseName, String subTaskName, int passOrFail, IBuffer buffer, String message) {
		_status.addExecutionStatus(testcaseName, subTaskName, passOrFail, buffer, message);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#addGlobalExecutionStatus(java.lang.String, int)
	 */
	public void addExecutionStatus(String testPassName, boolean pass) {
		_status.addExecutionStatus(testPassName, pass);
	}

	public void addExecutionStatus(String testPassName, String subTaskName, boolean pass) {
		_status.addExecutionStatus(testPassName, subTaskName, pass);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#isSuccessful()
	 */
	public boolean isSuccessful() {
		return _status.isSuccessful();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#isSuccessful()
	 */
	public boolean isSuccessful(String testName) {
		return _status.isSuccessful(testName);
	}

}
