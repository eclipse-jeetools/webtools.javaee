package org.eclipse.jst.validation.test.internal.registry;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.sample.parser.PropertyLine;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.setup.IBuffer;
import org.eclipse.wst.validation.internal.ConfigurationConstants;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.ValidationRegistryReader;
import org.eclipse.wst.validation.internal.ValidatorMetaData;

public class MessageUtility {
	private final static String _TESTCASE_MESSAGE_TEMPLATE_ = "<message prefix=\"{0}\" resource=\"{1}\" location=\"{2}\" text=\"{3}\"/>"; //$NON-NLS-1$
	private final static String _TESTCASE_MARKER_TEMPLATE_ =  "<marker prefix=\"{0}\" resource=\"{1}\" location=\"{2}\" text=\"{3}\"/>"; //$NON-NLS-1$
	private static MessageComparator _messageComparator = null;
	private static MessageIdComparator _messageIdComparator = null;
	private static MessagePrefixComparator _messagePrefixComparator = null;
	
	private MessageUtility() {
	}
	
	public static String toString(MessageMetaData mmd) {
		String location = (mmd.isSetLineNumber()) ? String.valueOf(mmd.getLineNumber()) : mmd.getLocation();
		return toString(mmd.getMessagePrefix(), mmd.getResource(), location, mmd.getText(), _TESTCASE_MESSAGE_TEMPLATE_);
	}
	
	public static String toString(IMarker marker) {
		Integer lineNumber = MessageUtility.getLineNumber(marker);
		String location = (lineNumber == null) ? (location = MessageUtility.getLocation(marker)) : (location = lineNumber.toString());
		return toString(getMessagePrefix(marker), getResource(marker), location, getMessage(marker), _TESTCASE_MARKER_TEMPLATE_);
	}
	
	private static String toString(String fmssgPrefix, String fresource, String flocation, String ftext, String template) {
		String mssgPrefix = (fmssgPrefix == null) ? "" : fmssgPrefix; //$NON-NLS-1$
		String resource = (fresource == null) ? "" : fresource; //$NON-NLS-1$
		String location = (flocation == null) ? "" : flocation; //$NON-NLS-1$
		String text = (ftext == null) ? "" : ftext; //$NON-NLS-1$
		return MessageFormat.format(template, new String[]{mssgPrefix, resource, location, text});
	}
	
	public static String getProject(IMarker marker) {
		return marker.getResource().getProject().getName();
	}
	

	public static String getResource(IMarker marker) {
		return marker.getResource().getProjectRelativePath().toString();
	}
	

	// Based on the last letter of the message prefix, what severity
	// should this marker be?
	public static Integer getSeverity(IMarker marker) throws CoreException {
		String message = (String)marker.getAttribute(IMarker.MESSAGE);
		if(message == null) {
			return null;
		}
		
		String[] tokens = parse(message);
		String severity = tokens[1];
		Integer result;
		if(severity.equals("E")) { //$NON-NLS-1$
			result = new Integer(IMarker.SEVERITY_ERROR);
		}
		else if(severity.equals("W")) { //$NON-NLS-1$
			result = new Integer(IMarker.SEVERITY_WARNING);
		}
		else if(severity.equals("I")) { //$NON-NLS-1$
			result = new Integer(IMarker.SEVERITY_INFO);
		}
		else {
			result = null;
		}
		return result;
	}
	
	public static String getMessage(IMarker marker) {
		try {
			return (String)marker.getAttribute(IMarker.MESSAGE);
		}
		catch(CoreException e) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, e);
			}
			return null;
		}
	}
	
	public static String getMessagePrefix(IMarker marker) {
		String message = getMessage(marker);
		if(message == null) {
			return null;
		}
		
		String[] tokens = parse(message);
		return tokens[0];
	}
	
	// This method is needed for the fuzzy compare. Need to 
	// strip the ".java" or ".class" extension off of the resource
	// name so that the class name can be searched for in the marker's
	// location text.
	public static String getResource(MessageMetaData mmd) {
		String resource = mmd.getResource();
		int index = resource.indexOf(".java"); //$NON-NLS-1$
		if(index > -1) {
			return resource.substring(0, index);
		}
		
		index = resource.indexOf(".class"); //$NON-NLS-1$
		if(index > -1) {
			return resource.substring(0, index);
		}
		
		return resource;
	}
	
	public static ValidatorMetaData getValidator(IMarker marker) {
		String validatorClass = ConfigurationManager.getManager().getValidator(marker);
		ValidatorMetaData vmd = ValidationRegistryReader.getReader().getValidatorMetaData(validatorClass);
		return vmd;
	}
	
	/**
	 * Parse the message value, to extract the unique error id, if any.
	 * String[0] is the message prefix, String [1] is the error char, String[2] is the rest of the string after the ':'
	 */
	public static String[] parse(String message) {
		if (message == null) {
			return new String[]{"", "", ""}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}

		StringTokenizer tokenizer = new StringTokenizer(message);
		int numTokens = tokenizer.countTokens();
		if (numTokens == 0) {
			return new String[]{"", "", message}; //$NON-NLS-1$ //$NON-NLS-2$
		}
		else {
			String errorId = tokenizer.nextToken();
			boolean isErrorIndex = false;

			int errorIdIndex = errorId.indexOf(':');
			if (errorIdIndex > 0) {
				String tempString = errorId.substring(0, errorIdIndex);
				char[] temp = tempString.toCharArray();
				if (temp.length == 9) { // Must be of the form ABCD1234E, which is 9 char long

					isErrorIndex = Character.isLetter(temp[0]);
					isErrorIndex = isErrorIndex && Character.isLetter(temp[1]);
					isErrorIndex = isErrorIndex && Character.isLetter(temp[2]);
					isErrorIndex = isErrorIndex && Character.isLetter(temp[3]);
					isErrorIndex = isErrorIndex && Character.isDigit(temp[4]);
					isErrorIndex = isErrorIndex && Character.isDigit(temp[5]);
					isErrorIndex = isErrorIndex && Character.isDigit(temp[6]);
					isErrorIndex = isErrorIndex && Character.isDigit(temp[7]);
					isErrorIndex = isErrorIndex && Character.isLetter(temp[8]);

					if (isErrorIndex) {
						String shortMessage;
						if (numTokens > 1) {
							shortMessage = message.substring(errorIdIndex + 1, message.length()).intern();
						}
						else {
							shortMessage = ""; //$NON-NLS-1$
						}
						return new String[]{tempString.substring(0, 8), new String(new char[]{temp[8]}), shortMessage};
					}
				}
			}

			if (!isErrorIndex) {
				return new String[]{"", "", message}; //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		
		return new String[]{"", "", message}; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static Integer getLineNumber(IMarker marker) {
		try {
			return (Integer)marker.getAttribute(IMarker.LINE_NUMBER);
		}
		catch(CoreException e) {
			return null;
		}
	}
	
	public static String getTargetObject(IMarker marker) {
		try {
			String targetObject = (String)marker.getAttribute(ConfigurationConstants.VALIDATION_MARKER_TARGETOBJECT);
			if((targetObject != null) && (targetObject.equals(""))) { //$NON-NLS-1$
				// Empty string means that there was no target object.
				return null;
			}
			return targetObject;
		}
		catch(CoreException e) {
			return null;
		}
	}
	
	/**
	 * Sort first on validator, 
	 * then project, 
	 * then message prefix,
	 * then location,
	 * then resource. (The resource may be different in batch and UI, so compare location & resource at the same time.)
	 * 
	 * @param verbose If true, verbose output while comparing lists.
	 */	
	public static Comparator getMessageComparator(final IBuffer buffer, final boolean verbose) {
		if(_messageComparator == null) {
			_messageComparator = new MessageUtility(). new MessageComparator();
		}
		_messageComparator.setVerbose(verbose);
		_messageComparator.setBuffer(buffer);
		return _messageComparator;
	}
	
	/**
	 * Sort first on the version, then on the message prefix.
	 */	
	public static Comparator getMessageIdComparator(final IBuffer buffer, final boolean verbose) {
		if(_messageIdComparator == null) {
			_messageIdComparator = new MessageUtility(). new MessageIdComparator();
		}
		_messageIdComparator.setVerbose(verbose);
		_messageIdComparator.setBuffer(buffer);
		return _messageIdComparator;
	}

	public static String getLocation(IMarker marker) {
		try {
			return (String)marker.getAttribute(IMarker.LOCATION);
		}
		catch(CoreException e) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, e);
			}
			return null;
		}
	}

	private class MessageComparator implements Comparator {
		private boolean _verbose = false;
		private IBuffer _buffer = null;
		
		public boolean isVerbose() {
			return _verbose;
		}
		
		public void setVerbose(boolean v) {
			_verbose = v;
		}
		
		public IBuffer getBuffer() {
			return _buffer;
		}
		
		public void setBuffer(IBuffer b) {
			_buffer = b;
		}
		
		public int compare(Object a, Object b) {
			if((a == null) && (b == null)) {
				return 0;
			}
			else if(a == null) {
				return 1;
			}
			else if(b == null) {
				return -1;
			}
			
			
			// Sort first on the message prefix, then on the location, then project, then resource
			if((a instanceof String) && (b instanceof String)) {
				return compare((String)a, (String)b);
			}
			else if ((a instanceof IMarker) && (b instanceof IMarker)) {
				IMarker aM = (IMarker)a;
				IMarker bM = (IMarker)b;
				int result = compare(aM, bM);
				if(isVerbose()) {
					getBuffer().write("compare(" + MessageUtility.toString(aM) + ", " + MessageUtility.toString(bM) + " = " + result); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				return result;
			}
			else if ((a instanceof IMarker) && (b instanceof MessageMetaData)) {
				IMarker aM = (IMarker)a;
				MessageMetaData mmd = (MessageMetaData)b;
				int result = compare(aM, mmd);
				if(isVerbose()) {
					getBuffer().write("compare(" + MessageUtility.toString(aM) + ", " + MessageUtility.toString(mmd) + " = " + result); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				return result;
			}
			else if((a instanceof MessageMetaData) && (b instanceof IMarker)) {
				MessageMetaData mmd = (MessageMetaData)a;
				IMarker bM = (IMarker)b;
				int result = compare(mmd, bM);
				if(isVerbose()) {
					getBuffer().write("compare(" + MessageUtility.toString(mmd) + ", " + MessageUtility.toString(bM) + " = " + result); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}						
				return result;
			}
			else if((a instanceof MessageMetaData) && (b instanceof MessageMetaData)) {
				MessageMetaData aMMD = (MessageMetaData)a;
				MessageMetaData bMMD = (MessageMetaData)b;
				int result = compare(aMMD, bMMD);
				if(isVerbose()) {
					getBuffer().write("compare(" + MessageUtility.toString(aMMD) + ", " + MessageUtility.toString(bMMD) + " = " + result); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				return result;
			}
			
			// else put it at the end of the list
			return 1;
		}
		
		public int compare(String aStr, String bStr) {
			if((aStr == null) && (bStr == null)) {
				return 0;
			}
			else if(aStr == null) {
				return 1;
			}
			else if(bStr == null) {
				return -1;
			}
			return aStr.compareTo(bStr);
		}
		
		public int compare(int aInt, int bInt) {
			return aInt - bInt;
		}
		
		public int compare(ValidatorMetaData aVMD, ValidatorMetaData bVMD) {
			if((aVMD == null) && (bVMD == null)) {
				return 0;
			}
			else if(aVMD == null) {
				return 1;
			}
			else if(bVMD == null) {
				return -1;
			}
			return compare(aVMD.getValidatorUniqueName(), bVMD.getValidatorUniqueName());
		}

		public int compare(IMarker aMarker, IMarker bMarker) {
			if((aMarker == null) && (bMarker == null)) {
				return 0;
			}
			else if(aMarker == null) {
				return 1;
			}
			else if(bMarker == null) {
				return -1;
			}
			
			int valCmp = compare(getValidator(aMarker), getValidator(bMarker));
			if(valCmp != 0) {
				return valCmp;
			}

			int prjCmp = compare(getProject(aMarker), getProject(bMarker));
			if(prjCmp != 0) {
				return prjCmp;
			}
			
			int idCmp = compare(getMessagePrefix(aMarker), getMessagePrefix(bMarker));
			if(idCmp != 0) {
				return idCmp;
			}

			int cmp = compareResourceAndLocation(aMarker, bMarker);
			return cmp;
		}
		
		public int compare(IMarker marker, MessageMetaData mmd) {
			if((marker == null) && (mmd == null)) {
				return 0;
			}
			else if(marker == null) {
				return 1;
			}
			else if(mmd == null) {
				return -1;
			}

			int valCmp = compare(getValidator(marker), mmd.getValidator());
			if(valCmp != 0) {
				return valCmp;
			}

			int prjCmp = compare(getProject(marker), mmd.getValidatorTestcase().getProjectName());
			if(prjCmp != 0) {
				return prjCmp;
			}
			
			int idCmp = compare(getMessagePrefix(marker), mmd.getMessagePrefix());
			if(idCmp != 0) {
				return idCmp;
			}

			int cmp = compareResourceAndLocation(marker, mmd);
			return cmp;
		}
		
		public int compare(MessageMetaData mmd, IMarker marker) {
			if((mmd == null) && (marker == null)) {
				return 0;
			}
			else if(mmd == null) {
				return 1;
			}
			else if(marker == null) {
				return -1;
			}

			int valCmp = compare(mmd.getValidator(), getValidator(marker));
			if(valCmp != 0) {
				return valCmp;
			}

			int prjCmp = compare(mmd.getValidatorTestcase().getProjectName(), getProject(marker));
			if(prjCmp != 0) {
				return prjCmp;
			}

			int idCmp = compare(mmd.getMessagePrefix(), getMessagePrefix(marker));
			if(idCmp != 0) {
				return idCmp;
			}

			int cmp = compareResourceAndLocation(mmd, marker);
			return cmp;
		}
		
		public int compare(MessageMetaData aMMD, MessageMetaData bMMD) {
			if((aMMD == null) && (bMMD == null)) {
				return 0;
			}
			else if(aMMD == null) {
				return 1;
			}
			else if(bMMD == null) {
				return -1;
			}
			
			int valCmp = compare(aMMD.getValidator(), bMMD.getValidator());
			if(valCmp != 0) {
				return valCmp;
			}

			int prjCmp = compare(aMMD.getValidatorTestcase().getProjectName(), bMMD.getValidatorTestcase().getProjectName());
			if(prjCmp != 0) {
				return prjCmp;
			}
			
			int idCmp = compare(aMMD.getMessagePrefix(), bMMD.getMessagePrefix());
			if(idCmp != 0) {
				return idCmp;
			}

			int cmp = compareResourceAndLocation(aMMD, bMMD);
			return cmp;
		}
		
		public int compareResourceAndLocation(IMarker aMarker, IMarker bMarker) {
			int resCmp = compare(getResource(aMarker), getResource(bMarker));
			if(resCmp != 0) {
				return resCmp;
			}
			
			Integer aLineNo = getLineNumber(aMarker);
			Integer bLineNo = getLineNumber(bMarker);
			if((aLineNo != null) && (bLineNo!= null)) {
				return compare(aLineNo, bLineNo);
			}
			
			String aLocation = getLocation(aMarker);
			String bLocation = getLocation(bMarker);
			if((aLocation != null) && (bLocation != null)) {
				return compare(aLocation, bLocation);
			}
			
			// If one has a line number and the other has a text location, can't compare
			// the location, so just assume that they're equal.
			return resCmp;
		}
		
		public int compareResourceAndLocation(IMarker marker, MessageMetaData mmd) {
			int resCmp = compare(getResource(marker), mmd.getResource());
			if(resCmp != 0) {
				return resCmp;
			}
			
			if(mmd.isSetLineNumber()) {
				Integer aLineNo = getLineNumber(marker);
				int bLineNo = mmd.getLineNumber();
				if(aLineNo != null) {
					return compare(aLineNo.intValue(), bLineNo);
				}
			}

			if(mmd.isSetLocation()) {
				String aLocation = getLocation(marker);
				String bLocation = mmd.getLocation();
				if((aLocation != null) && (bLocation != null)) {
					return compare(aLocation, bLocation);
				}
			}
			
			// If one has a line number and the other has a text location, can't compare
			// the location, so just assume that they're equal.
			return resCmp;
		}
		
		public int compareResourceAndLocation(MessageMetaData mmd, IMarker marker) {
			int resCmp = compare(mmd.getResource(), getResource(marker));
			if(resCmp != 0) {
				return resCmp;
			}

			if(mmd.isSetLineNumber()) {
				int aLineNo = mmd.getLineNumber();
				Integer bLineNo = getLineNumber(marker);
				if(bLineNo!= null) {
					return compare(aLineNo, bLineNo.intValue());
				}
			}

			if(mmd.isSetLocation()) {
				String aLocation = mmd.getLocation();
				String bLocation = getLocation(marker);
				if((aLocation != null) && (bLocation != null)) {
					return compare(aLocation, bLocation);
				}
			}
			
			// If one has a line number and the other has a text location, can't compare
			// the location, so just assume that they're equal.
			return resCmp;
		}
		
		public int compareResourceAndLocation(MessageMetaData aMMD, MessageMetaData bMMD) {
			int resCmp = compare(aMMD.getResource(), bMMD.getResource());
			if(resCmp != 0) {
				return resCmp;
			}

			if(aMMD.isSetLineNumber() && bMMD.isSetLineNumber()) {
				int aLineNo = aMMD.getLineNumber();
				int bLineNo = bMMD.getLineNumber();
				return compare(aLineNo, bLineNo);
			}

			if(aMMD.isSetLocation() && bMMD.isSetLocation()) {
				String aLocation = aMMD.getLocation();
				String bLocation = bMMD.getLocation();
				if((aLocation != null) && (bLocation != null)) {
					return compare(aLocation, bLocation);
				}
			}
			
			// If one has a line number and the other has a text location, can't compare
			// the location, so just assume that they're equal.
			return resCmp;
		}
	}
	
	public static MessagePrefixComparator getMessagePrefixComparator() {
		if(_messagePrefixComparator == null) {
			_messagePrefixComparator = new MessagePrefixComparator();
		}
		return _messagePrefixComparator;
	}
	
	static class MessagePrefixComparator implements Comparator {
		public int compare(Object a, Object b) {
			String aPrefix = null;
			String bPrefix = null;
			if(a instanceof PropertyLine) {
				aPrefix = ((PropertyLine)a).getMessagePrefix();
			}
			else if(a instanceof MessageMetaData) {
				aPrefix = ((MessageMetaData)a).getMessagePrefix();
			}
			
			if(b instanceof PropertyLine) {
				bPrefix = ((PropertyLine)b).getMessagePrefix();
			}
			else if(b instanceof MessageMetaData) {
				bPrefix = ((MessageMetaData)b).getMessagePrefix();
			}

			if(aPrefix.length() == 9) {
				aPrefix = aPrefix.substring(0, 9);
			}
			
			if(bPrefix.length() == 9) {
				bPrefix = bPrefix.substring(0, 9);
			}
			
			return aPrefix.compareTo(bPrefix);
		}
	}
		
	class MessageIdComparator implements Comparator {
		private boolean _verbose = false;
		private IBuffer _buffer = null;
		
		public boolean isVerbose() {
			return _verbose;
		}
		
		public void setVerbose(boolean v) {
			_verbose = v;
		}
		
		public IBuffer getBuffer() {
			return _buffer;
		}
		
		public void setBuffer(IBuffer b) {
			_buffer = b;
		}
		
		public String getMessageId(IMarker marker) {
			try {
				return (String)marker.getAttribute(ConfigurationConstants.VALIDATION_MARKER_MESSAGEID);
			}
			catch(CoreException exc) {
				Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
				if(logger.isLoggingLevel(Level.SEVERE)) {
					logger.write(Level.SEVERE, exc);
				}
				return ""; //$NON-NLS-1$
			}
		}
		
		public int compare(String aStr, String bStr) {
			if((aStr == null) && (bStr == null)) {
				return 0;
			}
			else if(aStr == null) {
				return 1;
			}
			else if(bStr == null) {
				return -1;
			}
			return aStr.compareTo(bStr);
		}
		
		public int compare(Object a, Object b) {
			if((a == null) && (b == null)) {
				return 0;
			}
			else if(a == null) {
				return 1;
			}
			else if(b == null) {
				return -1;
			}
			
			
			// Sort first on the version, then on the message prefix
			if((a instanceof String) && (b instanceof String)) {
				String aStr = (String)a;
				String bStr = (String)b;
				return compare(aStr, bStr);
			}
			else if ((a instanceof PropertyLine) && (b instanceof PropertyLine)) {
				PropertyLine aM = (PropertyLine)a;
				PropertyLine bM = (PropertyLine)b;
				
				return compare(aM.getMessageId(), bM.getMessageId());
			}
			else if ((a instanceof PropertyLine) && (b instanceof IMarker)) {
				PropertyLine aM = (PropertyLine)a;
				IMarker bM = (IMarker)b;
				
				return compare(aM.getMessageId(), getMessageId(bM));
			}
			else if((a instanceof IMarker) && (b instanceof PropertyLine)) {
				IMarker aM = (IMarker)a;
				PropertyLine bM = (PropertyLine)b;
				
				return compare(getMessageId(aM), bM.getMessageId());
			}
			else if((a instanceof IMarker) && (b instanceof IMarker)) {
				IMarker aM = (IMarker)a;
				IMarker bM = (IMarker)b;

				return compare(getMessageId(aM), getMessageId(bM));
			}
			
			// else put it at the end of the list
			return 1;
		}
	};
}
