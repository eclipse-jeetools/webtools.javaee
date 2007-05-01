package org.eclipse.jst.jee.archive;

public class ArchiveException extends Exception {

	private static final long serialVersionUID = 3796439415310903317L;

	public ArchiveException() {
		super();
	}

	public ArchiveException(String message) {
		super(message);
	}

	public ArchiveException(Exception exception) {
		super(exception.getMessage());
	}

}
