package org.eclipse.jst.validation.test;

import org.eclipse.core.resources.IncrementalProjectBuilder;

/**
 * Enumeration of the types of validations that can be run.
 */
public final class ValidationTypeEnum {
	private ValidationTypeEnum() {
		// Do not permit instances of this class to be created.
	}
	
	public static final int RUN_VALIDATION = 0; // Manual full invocation of a validation by a user. All validators must support this type of validation.
	public static final int FULL_VALIDATION = IncrementalProjectBuilder.FULL_BUILD; // Manual invocation of a full build by a user that triggers a full validation. Validators can, but are not required to, support this type of validation.
	public static final int INCREMENTAL_VALIDATION = IncrementalProjectBuilder.INCREMENTAL_BUILD; // Manual invocation of an incremental build by a user that triggers an incremental validation. Validators can, but are not required to, support this type of validation.
	public static final int AUTO_VALIDATION = IncrementalProjectBuilder.AUTO_BUILD; // Automatic invocation of an incremental build that triggers an incremental validation. Validators can, but are not required to, support this type of validation.
	
	public static final String RUN_VALIDATION_NAME = "RUN_VALIDATION"; //$NON-NLS-1$
	public static final String ASYNC_NAME = "ASYNC"; //$NON-NLS-1$
	public static final String INCREMENTAL_VALIDATION_NAME = "INCREMENTAL_BUILD"; //$NON-NLS-1$
	public static final String FULL_VALIDATION_NAME = "FULL_BUILD"; //$NON-NLS-1$
	public static final String AUTO_VALIDATION_NAME = "AUTO_BUILD"; //$NON-NLS-1$
}