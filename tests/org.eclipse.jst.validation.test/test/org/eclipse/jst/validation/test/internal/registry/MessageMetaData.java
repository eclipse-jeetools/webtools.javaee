package org.eclipse.jst.validation.test.internal.registry;

import org.eclipse.wst.validation.internal.ValidatorMetaData;



public class MessageMetaData {
	public static final int LINENO_UNSET = -1;
	private String _messagePrefix = null;
	private String _resource = null;
	private int _lineNumber = LINENO_UNSET;
	private String _location = null;
	private String _text = null; // When this test case was generated, what was the text of the marker? This information is needed when message prefixes change; the test case will fail because the prefix doesn't match, but it's easy to see that the old prefix tested the same thing as the new prefix.
	private ValidatorTestcase _tmd = null; // pointer back to the testcase parent
	
	public MessageMetaData(ValidatorTestcase tmd, String prefix, String resource, int lineNumber, String text) {
		this(tmd, prefix, resource, text);
		_lineNumber = lineNumber;
	}
	
	public MessageMetaData(ValidatorTestcase tmd, String prefix, String resource, String location, String text) {
		this(tmd, prefix, resource, text);
		_location = location;
	}
	
	public MessageMetaData(ValidatorTestcase tmd, String prefix, String resource, String text) {
		_tmd = tmd;
		_messagePrefix = prefix;
		_resource = resource;
		_text = (text == null) ? "" : text; //$NON-NLS-1$
	}
	
	public ValidatorTestcase getValidatorTestcase() {
		return _tmd;
	}
	
	public String getMessagePrefix() {
		return _messagePrefix;
	}
	
	public String getResource() {
		return _resource;
	}
	
	public int getLineNumber() {
		return _lineNumber;
	}
	
	public String getLocation() {
		return _location;
	}
	
	public ValidatorMetaData getValidator() {
		return getValidatorTestcase().getValidatorMetaData();
	}
	
	public String getText() {
		return _text;
	}
	
	public boolean isSetLineNumber() {
		return (_lineNumber != LINENO_UNSET);
	}
	
	public boolean isSetLocation() {
		return isSet(_location);
	}
	
	public boolean isSetResource() {
		return isSet(_resource);
	}
	
	private boolean isSet(String value) {
		if(value == null) {
			return false;
		}
		
		if(value.trim().equals("")) { //$NON-NLS-1$
			return false;
		}
		
		return true;
	}

	public String toString() {
		return MessageUtility.toString(this);
	}
}
