package org.eclipse.jst.jee.archive;

public class ArchiveModelLoadException extends ArchiveException {

	private static final long serialVersionUID = 4103220169134334112L;

	public ArchiveModelLoadException(String message) {
		super(message);
	}
	
	public ArchiveModelLoadException(Exception exception){
		super(exception);
	}
}
