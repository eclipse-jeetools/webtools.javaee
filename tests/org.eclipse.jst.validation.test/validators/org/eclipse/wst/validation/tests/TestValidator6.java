package org.eclipse.wst.validation.tests;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.wst.validation.AbstractValidator;
import org.eclipse.wst.validation.ValidationResult;
import org.eclipse.wst.validation.ValidationState;

/**
 * A validator that has similar rules as the XML validator.
 * @author karasiuk
 *
 */
public class TestValidator6 extends AbstractValidator {
	
	public static String id(){
		return BVTValidationPlugin.PLUGIN_ID +".Test6";
	}

	
	private Set<IResource> _set = new HashSet<IResource>(100);
	
	@Override
	public ValidationResult validate(IResource resource, int kind, ValidationState state, IProgressMonitor monitor) {
		ValidationResult result = new ValidationResult();
		_set.add(resource);
		return result;
	}

	public Set<IResource> getSet() {
		return _set;
	}

}
