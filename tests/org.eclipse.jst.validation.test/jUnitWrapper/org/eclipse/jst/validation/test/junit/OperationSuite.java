package org.eclipse.jst.validation.test.junit;

import junit.framework.TestResult;
import junit.framework.TestSuite;

/**
 * Instances of this class wrap all of the operations, in plugin.xml,
 * for the validation framework.
 */
public class OperationSuite extends TestSuite {
	private JUnitBuffer _buffer = null;

	public OperationSuite() {
	}
	
	public String toString() {
		return "Validation Framework Tests"; //$NON-NLS-1$
	}

	public JUnitBuffer getBuffer() {
		if(_buffer == null) {
			_buffer = new JUnitBuffer(toString() + System.currentTimeMillis() + ".log"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return _buffer;
	}

	/* (non-Javadoc)
	 * @see junit.framework.Test#run(junit.framework.TestResult)
	 */
	public void run(TestResult result) {
		super.run(result);
		getBuffer().report(); // report only after all of the tests in this suite have finished
		getBuffer().clear();
	}
}
