package org.eclipse.jst.validation.test.junit;

import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.eclipse.wst.validation.internal.ValidatorMetaData;

/**
 * Instances of this class wrap all of the <test> instances,
 * contributed in plugin.xml, for one validator.
 */
public class ValidatorSuite extends TestSuite {
	private ValidatorMetaData _vmd = null;
	private JUnitBuffer _buffer = null;

	public ValidatorSuite(ValidatorMetaData vmd) {
		_vmd = vmd;
	}
	
	public String toString() {
		return _vmd.getValidatorDisplayName();
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

