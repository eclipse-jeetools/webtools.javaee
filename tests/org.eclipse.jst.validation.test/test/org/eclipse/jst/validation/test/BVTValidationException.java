package org.eclipse.jst.validation.test;

public class BVTValidationException extends Exception {
	private Throwable _target = null;
	
	public BVTValidationException(Throwable t) {
		setTargetException(t);
	}
	
	public BVTValidationException(String s) {
		super(s);
	}
	
	public BVTValidationException(String s, Throwable target) {
		super(s);
		setTargetException(target);
	}
	
	public Throwable getTargetException() {
		return _target;
	}
	
	public void setTargetException(Throwable target) {
		_target = target;
	}
}
