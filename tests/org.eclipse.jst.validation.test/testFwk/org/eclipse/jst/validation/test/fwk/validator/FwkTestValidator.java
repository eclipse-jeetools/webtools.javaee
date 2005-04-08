package org.eclipse.jst.validation.test.fwk.validator;

import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.wst.validation.internal.operations.LocalizedMessage;
import org.eclipse.wst.validation.internal.provisional.core.IFileDelta;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.validation.internal.provisional.core.ValidationException;

public class FwkTestValidator implements IValidator {
	public static final String ALL_FILES = "ALL_FILES"; // Load all IFileDeltas for the entire contents of the current project. //$NON-NLS-1$
	public static final String JAVAHELPERS = "JAVAHELPERS"; // The JavaClass that the IFileDelta maps to. //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see com.ibm.etools.validation.IValidator#cleanup(com.ibm.etools.validation.IReporter)
	 */
	public void cleanup(IReporter reporter) {
		// Nothing is cached, so nothing to do.
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.validation.IValidator#validate(com.ibm.etools.validation.IHelper, com.ibm.etools.validation.IReporter, com.ibm.etools.validation.IFileDelta[])
	 */
	public void validate(IValidationContext helper, IReporter reporter, IFileDelta[] delta) throws ValidationException {
		if((delta == null) || (delta.length ==0)) {
			// Full Validate
			delta = (IFileDelta[])helper.loadModel(ALL_FILES);
		}
		
		if(delta == null) {
			// Problem loading the files
			return;
		}
		
		for(int i=0; i<delta.length; i++) {
			JavaHelpers target = (JavaHelpers)helper.loadModel(JAVAHELPERS, new Object[]{delta[i]});
			LocalizedMessage message = new LocalizedMessage(IMessage.LOW_SEVERITY, "This is a test message for the validation framework, reported by VF Test Validator. Please ignore.", target); //$NON-NLS-1$
			reporter.addMessage(this, message);
		}
	}

}
