package org.eclipse.jst.validation.test;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.validation.test.setup.IBuffer;


/**
 * Sends the TestCaseGeneratorOperation output to System.out.
 */
public class ConsoleBuffer implements IBuffer {
	private BufferedTaskStatus _status = null;
	
	public ConsoleBuffer() {
		_status = new BufferedTaskStatus(); // no log file
	}

	/**
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#report()
	 */
	public void report() {
		_status.report();
		System.out.println(_status.getBuffer().toString());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#clear()
	 */
	public void clear() {
		_status.clear();
	}
	
	public String getLogFileName() {
		return _status.getLogFileName();
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

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {
		_status.setProgressMonitor(monitor);
	}

}