/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

import org.eclipse.core.runtime.IPath;

public class ArchiveOpenFailureException extends ArchiveException {

	private static final long serialVersionUID = -7366116122777721148L;

	private IPath _archivePath = null;
	
	public ArchiveOpenFailureException(String message) {
		super(message);
	}

	public ArchiveOpenFailureException(Throwable cause){
		super(cause);
	}
	
	public ArchiveOpenFailureException(String message, Throwable cause){
		super(message, cause);
	}
	
	public ArchiveOpenFailureException(IPath archivePath, String message, Throwable cause){
		super(message, cause);
		this._archivePath = archivePath;
	}
	
	public IPath getArchivePath() {
		return _archivePath;
	}
	
}
