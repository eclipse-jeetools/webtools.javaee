package org.eclipse.jst.validation.test.setup;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * If information needs to be reported to the user, but that information is long,
 * and the user needs to see it all at the same time (e.g., test case results),
 * then the information is reported to implementations of this class. 
 * Implementations can choose to report the information immediately 
 * (e.g., to System.out), or can append each message, and report the message
 * only once everything is complete. 
 */
public interface IBuffer extends ITestStatus {
	/**
	 * Return the progress monitor that is used for reporting progress
	 * status as the long-running process executes.
	 */
	public IProgressMonitor getProgressMonitor();
	
	/**
	 * Display the message to the user; the buffer implementation may 
	 * display the message immediately, or may display the message only
	 * when report() is called.
	 */
	public void write(String message);
	
	/**
	 * Between one task and the next, usually between test cases, add a delineating
	 * mark to show the beginning and end of each task clearly. The parameter 
	 * passed in, taskName, is the name of the task that is ending.
	 */
	public void delineate(String taskName);
		
	/**
	 * If information was not reported immediately, calling this method
	 * forces the buffer to be reported to the user. The contents of the
	 * buffer are not cleared; a subseqent call to this method will 
	 * repeat what has been reported before. Any calls to elapsedTime
	 * will be reported.
	 */
	public void report();
	
	/**
	 * Clear any stored messages from the buffer, but not any elapsedTime 
	 * numbers from the store.
	 */
	public void clear();
	
	/**
	 * Return the fully-qualified name of the log file, or null if none.
	 */
	public String getLogFileName();
}
