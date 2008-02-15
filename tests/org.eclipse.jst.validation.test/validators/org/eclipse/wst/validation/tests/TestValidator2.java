package org.eclipse.wst.validation.tests;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.wst.validation.AbstractValidator;
import org.eclipse.wst.validation.ValidationResult;
import org.eclipse.wst.validation.ValidationState;
import org.eclipse.wst.validation.ValidatorMessage;

/**
 * A simple test validator that always returns an error marker and a warning marker.
 * @author karasiuk
 *
 */
public class TestValidator2 extends AbstractValidator {
	
//	private MessageSeveritySetting[] _messages;
	
	public String getName() {
		return "TestValidator2";
	}
	
	public static String id(){
		return BVTValidationPlugin.PLUGIN_ID +".Test2";
	}
	
	
	@Override
	public ValidationResult validate(IResource resource, int kind, ValidationState state, IProgressMonitor monitor){
		ValidationResult vr = new ValidationResult();
		ValidatorMessage vm = ValidatorMessage.create("A sample message from Test2", resource);
		vm.setAttribute(IMarker.LINE_NUMBER, 1);
		vm.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
		vr.add(vm);

		vm = ValidatorMessage.create("A different message from Test2", resource);
		vm.setAttribute(IMarker.LINE_NUMBER, 2);
		vm.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
		vr.add(vm);
				
		return vr;
	}
	
//	@Override
//	public MessageSeveritySetting[] getMessageSettings() {
//		if (_messages == null){
//			_messages = new MessageSeveritySetting[]{
//				new MessageSeveritySetting("bad","Really bad things", MessageSeveritySetting.Severity.Error),
//				new MessageSeveritySetting("sortof", "Sort of bad things", MessageSeveritySetting.Severity.Warning),
//				new MessageSeveritySetting("nice", "Nice things", MessageSeveritySetting.Severity.Ignore)
//			};
//		}
//		return _messages;
//	}

}
