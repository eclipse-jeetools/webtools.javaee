package org.eclipse.jst.validation.test.setup;

/**
 * Instances of this interface track the execution of a test case,
 * and are used to report a summary of the execution of all of the
 * test cases at the end of a set of test runs.
 */
public interface ITestStatus {
	public final int PASS = 0;
	public final int WARN = 1;
	public final int FAIL = 2;
	
	/**
	 * To track how long a task takes, call this method with a unique name
	 * of the task. If the task already exists, the elapsedTime is added
	 * to the existing time.
	 */
	public void addElapsedTime(String taskName, long elapsedTime);
	
	/**
	 * If pass is true, PASS is used; if pass is false, FAIL is used.
	 */
	public void addExecutionStatus(String testPassName, boolean pass);
	
	/**
	 * If pass is true, PASS is used; if pass is false, FAIL is used.
	 * While still considered part of test case "testPassName", this 
	 * splits this part of the test case into a section named "subTaskName".
	 */
	public void addExecutionStatus(String testPassName, String subTaskName, boolean pass);
	
	/**
	 * As each test is executed, this method is used to track how many of that
	 * type of test pass or fail. passOrFail can be one of three values: PASS, WARN, or FAIL.
	 * If value is PASS, then the test case passes, and no message needs to be displayed to the user.
	 * If value is WARN, then the test case passes, and a warning message must be displayed to the user.
	 * If value is FAIL, then the test case fails, and a failure message must be displayed to the user.
	 */
	public void addExecutionStatus(String testcaseName, String subTaskName, int passOrFail, IBuffer buffer, String message);
	
	/**
	 * Return true if all of the test cases run so far have passed; otherwise
	 * return false. If no test cases have run, return true.
	 */
	public boolean isSuccessful();
	
	/**
	 * Return true if a particular test case passed.
	 */
	public boolean isSuccessful(String testName);
	
	/**
	 * Reset all variables to the default value to prepare this test status for reuse.
	 */
	public void clear();
}
