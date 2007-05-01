package org.eclipse.jst.jee.archive;

public class ArchiveSaveFailureException extends ArchiveException {

	private static final long serialVersionUID = 9102704379415366068L;

	public ArchiveSaveFailureException(String message) {
		super(message);
	}

	public ArchiveSaveFailureException(Exception exception) {
		super(exception);
	}

}
