package org.eclipse.wst.validation.tests;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.wst.validation.ValidationResult;
import org.eclipse.wst.validation.ValidationState;

/**
 * A slower validator.
 * @author karasiuk
 *
 */
public class TestValidator4 extends TestValidator {
	
	public static String id(){
		return BVTValidationPlugin.PLUGIN_ID +".Test4";
	}
	
	@Override
	public ValidationResult validate(IResource resource, int kind,
			ValidationState state, IProgressMonitor monitor) {
		ValidationResult vr = super.validate(resource, kind, state, monitor);
		long j = 0;
		try {
			for (long i=0; i< 10000000; i++)j = i + 1;
			Thread.sleep(2000);
		}
		catch (InterruptedException e){
			// eat it
		}
		return vr;
	}

}
