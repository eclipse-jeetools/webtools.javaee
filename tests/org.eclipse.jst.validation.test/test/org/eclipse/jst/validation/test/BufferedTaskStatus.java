package org.eclipse.jst.validation.test;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.validation.test.internal.registry.TestcaseUtility;
import org.eclipse.jst.validation.test.setup.IBuffer;


public class BufferedTaskStatus extends TaskStatus implements IBuffer {
	public static final String NEWLINE = System.getProperty("line.separator"); //$NON-NLS-1$
	private IProgressMonitor _monitor = null;
	private StringBuffer _buffer = null;
	private String _logFileName = null;
	
	public BufferedTaskStatus() {
	}
	
	public BufferedTaskStatus(String logFileName) {
		this(logFileName, null);
	}
	
	public BufferedTaskStatus(String logFileName, IProgressMonitor monitor) {
		_buffer = new StringBuffer();
		setProgressMonitor(monitor);
		setLogFileName(logFileName);
	}
	
	public void clear() {
		super.clear();
		_buffer = null;
		_buffer = new StringBuffer();
		_monitor.done();
		_monitor = null;
	}

	public String getLogFileName() {
		return _logFileName;
	}
	
	public void setLogFileName(String name) {
		_logFileName = name;
	}
	
	public StringBuffer getBuffer() {
		return _buffer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#getProgressMonitor()
	 */
	public IProgressMonitor getProgressMonitor() {
		if(_monitor == null) {
			_monitor = new NullProgressMonitor();
		}
		return _monitor;
	}
	
	public void setProgressMonitor(IProgressMonitor m) {
		_monitor = m;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#report()
	 */
	public void report() {
		_buffer.append(NEWLINE);
		_buffer.append(">>>>>>>>>>>>>>>>TEST SUITE RESULTS>>>>>>>>>>>>>>>>"); //$NON-NLS-1$
		_buffer.append(NEWLINE);
		_buffer.append(NEWLINE);

		if(getElapsedTime().size() > 0) {
			Iterator iterator = getElapsedTime().keySet().iterator();
			long total = 0;
			_buffer.append(">>>>>>>>>>>>>>>>ELAPSED TIME>>>>>>>>>>>>>>>>"); //$NON-NLS-1$
			_buffer.append(NEWLINE);
			while(iterator.hasNext()) {
				String taskName = (String)iterator.next();
				Long elapsedTime = (Long)getElapsedTime().get(taskName);
				total += elapsedTime.longValue();
				_buffer.append("Total elapsed time of task "); //$NON-NLS-1$
				_buffer.append(taskName);
				_buffer.append(" is "); //$NON-NLS-1$
				_buffer.append(elapsedTime);
				_buffer.append(NEWLINE);
			}
			_buffer.append("Total time of all tasks is " + total + ", which is " + (total/1000/60) + " minutes."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			_buffer.append(NEWLINE);
		}

		int totNumPass = 0;
		Map taskStatus = getTaskStatus();
		Iterator iterator = taskStatus.keySet().iterator();
		_buffer.append(NEWLINE);
		_buffer.append(">>>>>>>>>>>>>>>>PASS/FAIL SUMMARY>>>>>>>>>>>>>>>>"); //$NON-NLS-1$
		_buffer.append(NEWLINE);
		while(iterator.hasNext()) {
			String taskName = (String)iterator.next();
			TestStatus ts = (TestStatus)taskStatus.get(taskName);
			_buffer.append(ts);
			_buffer.append(NEWLINE);
			if(ts.isSuccessful()) {
				totNumPass++;
			}
		}
		
		_buffer.append(NEWLINE);
		_buffer.append(NEWLINE);
		_buffer.append("Total Number Of Tests: "); //$NON-NLS-1$
		_buffer.append(numTests());
		_buffer.append(NEWLINE);
		_buffer.append("Number Passed: "); //$NON-NLS-1$
		_buffer.append(totNumPass);
		_buffer.append(NEWLINE);
		
		if(getLogFileName() != null) {
			_buffer.append("This report is stored in the following log file: "); //$NON-NLS-1$
			_buffer.append(getLogFileName());
	
			TestcaseUtility.flush(getLogFileName(), _buffer.toString(), true);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#delineate()
	 */
	public void delineate(String testcaseName) {
		TaskStatus. TestStatus ts = getTestStatus(testcaseName);
		if(ts == null) {
			// nothing to report
			return;
		}
		StringBuffer errorsBuffer = new StringBuffer();
		errorsBuffer.append(ts.numFailure());
		errorsBuffer.append(" errors were reported and "); //$NON-NLS-1$
		errorsBuffer.append(ts.numWarnings());
		errorsBuffer.append(" warnings were reported. Read "); //$NON-NLS-1$
		errorsBuffer.append(getLogFileName());
		errorsBuffer.append(" for details."); //$NON-NLS-1$
		write(errorsBuffer.toString());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.validation.test.setup.IBuffer#write(java.lang.String)
	 */
	public void write(String message) {
		// Can't write to the JUnit log. Can only write to the log by failing the
		// test case with a message; instead, write to the buffer, and send this
		// information to a log in the validation.test plugin's state location.
		_buffer.append(message);
		_buffer.append(NEWLINE);
	}
}
