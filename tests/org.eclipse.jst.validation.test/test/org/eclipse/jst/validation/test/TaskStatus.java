package org.eclipse.jst.validation.test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jst.validation.test.setup.IBuffer;
import org.eclipse.jst.validation.test.setup.ITestStatus;


/**
 * All of the IBuffer instances need to be able to track elapsed time
 * and execution status. Each buffer can create an internal instance of
 * this class to perform those tasks.
 */
public class TaskStatus implements ITestStatus {
	private Map _elapsedTime = null;
	private Map _taskStatus = null;

	public TaskStatus() {
		_elapsedTime = new HashMap();
		_taskStatus = new HashMap();
	}
	
	/**
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#addElapsedTime(String, long)
	 */
	public void addElapsedTime(String taskName, long elapsedTime) {
		Long time = (Long)_elapsedTime.get(taskName);
		if(time == null) {
			time = new Long(elapsedTime);
		}
		else {
			time = new Long(time.longValue() + elapsedTime);
		}
		_elapsedTime.put(taskName, time);
	}
	
	public void addExecutionStatus(String taskName, boolean pass) {
		addExecutionStatus(taskName, null, pass);
	}
	
	public void addExecutionStatus(String taskName, String subTaskName, boolean pass) {
		int success = (pass) ? ITestStatus.PASS : ITestStatus.FAIL;
		addExecutionStatus(taskName, subTaskName, success, null, null);
	}
	
	public int numTests() {
		return _taskStatus.size();
	}
	
	protected Map getElapsedTime() {
		return _elapsedTime;
	}
	
	protected Map getTaskStatus() {
		return _taskStatus;
	}
	
	public void addExecutionStatus(String testcaseName, String subTaskName, int severity, IBuffer buffer, String message) {
		TestStatus ts = getTestStatus(testcaseName);
		if(ts == null) {
			ts = new TestStatus(testcaseName);
			_taskStatus.put(testcaseName, ts);
		}
		if(subTaskName == null) {
			ts.addExecutionStatus(severity);
		}
		else {
			ts.addExecutionStatus(subTaskName, severity);
		}

		if(message != null) {
			buffer.write(message);
		}
	}
	
	protected TestStatus getTestStatus(String testcaseName) {
		return (TestStatus)_taskStatus.get(testcaseName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.ITestStatus#isSuccessful()
	 */
	public boolean isSuccessful() {
		if(_taskStatus.size() == 0) {
			return true;
		}
		
		Iterator iterator = _taskStatus.keySet().iterator();
		while(iterator.hasNext()) {
			String tsName = (String)iterator.next();
			TestStatus ts = (TestStatus)_taskStatus.get(tsName);
			if(ts == null || !ts.isSuccessful()) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isSuccessful(String testName) {
		if(_taskStatus.size() == 0) {
			return true;
		}
		
		TestStatus ts = (TestStatus)_taskStatus.get(testName);
		if(ts == null || !ts.isSuccessful()) {
			return false;
		}
		
		return true;
	}

	public void clear() {
		_taskStatus.clear();
		_elapsedTime.clear();
	}
	
	protected class TestCount {
		public int numRan = 0;
		public int numWarn = 0; // Number of warning messages reported for a test case.
		public int numFail = 0; // Number of failure messages reported for a test case.
		public int numPass = 0; // Number of test cases that passed.
	}

	/**
	 * Instances of this class track the number of test runs and the 
	 * number of test cases that pass.
	 */
	protected class TestStatus implements Comparator {
		private String _testcaseName = null;
		private Map _testStatus = null;
		
		/**
		 * Name must not be null.
		 */
		public TestStatus(String name) {
			_testcaseName = name;
			_testStatus = new HashMap();
		}
		
		public void addExecutionStatus(String subTaskName, int severity) {
			TestCount soFar = (TestCount)_testStatus.get(subTaskName);
			if(soFar == null) {
				soFar = new TestCount();
			}
			soFar.numRan++;
			switch(severity) {
				case(ITestStatus.WARN): {
					soFar.numWarn++;
					soFar.numPass++;
					break;
				}
				
				case(ITestStatus.FAIL): {
					soFar.numFail++;
					break;
				}
				
				case(ITestStatus.PASS): {
					soFar.numPass++;
				}
				
				default: {
					// change nothing
					break;
				}
			}
			
			_testStatus.put(subTaskName, soFar);
		}
		
		public void addExecutionStatus(int severity) {
			addExecutionStatus(null, severity);
		}
		
		public String getTestcaseName() {
			return _testcaseName;
		}
		
		public int numWarnings() {
			int numWarnings = 0;
			Iterator iterator = _testStatus.values().iterator();
			while(iterator.hasNext()) {
				TestCount tc = (TestCount)iterator.next();
				numWarnings += tc.numWarn;
			}
			return numWarnings;
		}
		
		public int numFailure() {
			int numFail = 0;
			Iterator iterator = _testStatus.values().iterator();
			while(iterator.hasNext()) {
				TestCount tc = (TestCount)iterator.next();
				numFail += tc.numFail;
			}
			return numFail;
		}
		
		public boolean isSuccessful() {
			Iterator iterator = _testStatus.values().iterator();
			while(iterator.hasNext()) {
				TestCount tc = (TestCount)iterator.next();
				if(tc.numFail != 0) {
					return false;
				}
			}
			return true;
		}
		
		public String toString() {
			int total = 0;
			int pass = 0;
			int fail = 0;
			int warn = 0;
			
			Iterator iterator = _testStatus.values().iterator();
			while(iterator.hasNext()) {
				TestCount tc = (TestCount)iterator.next();
				total++;
				fail += tc.numFail;
				warn += tc.numWarn;
				pass += tc.numPass;
			}
			
			StringBuffer buffer = new StringBuffer(getTestcaseName());
			buffer.append(" Total:"); //$NON-NLS-1$
			buffer.append(total);
			buffer.append(" [Pass: "); //$NON-NLS-1$
			buffer.append(pass);
			buffer.append(" Fail: "); //$NON-NLS-1$
			buffer.append(fail);
			buffer.append(" Warn: "); //$NON-NLS-1$
			buffer.append(warn);
			buffer.append("]"); //$NON-NLS-1$
			return buffer.toString();
		}
		
		public boolean equals(Object o) {
			if(o instanceof TestStatus) {
				TestStatus s = (TestStatus)o;
				return(_testcaseName.equals(s.getTestcaseName()));
			}
			
			return false;
		}
		
		public int hashCode() {
			return getTestcaseName().hashCode();
		}
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(Object o1, Object o2) {
			if(!(o1 instanceof TestStatus) && (o2 instanceof TestStatus)) {
				// Should never happen, but if it does, say that these non-TestStatus objects are equal.
				return 0;
			}

			TestStatus a = (TestStatus)o1;
			TestStatus b = (TestStatus)o2;
			return a.getTestcaseName().compareTo(b.getTestcaseName());			
		}
	}
}
