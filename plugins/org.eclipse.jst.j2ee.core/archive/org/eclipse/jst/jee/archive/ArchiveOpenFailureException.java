package org.eclipse.jst.jee.archive;

public class ArchiveOpenFailureException extends ArchiveException {

	private static final long serialVersionUID = -7366116122777721148L;

	public ArchiveOpenFailureException(String message) {
		super(message);
	}

	public ArchiveOpenFailureException(Exception exception){
		super(exception);
	}
	
}
