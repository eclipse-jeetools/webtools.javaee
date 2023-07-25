/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

public class ArchiveSaveFailureException extends ArchiveException {

	private static final long serialVersionUID = 9102704379415366068L;

	public ArchiveSaveFailureException(String message) {
		super(message);
	}

	public ArchiveSaveFailureException(Throwable cause) {
		super(cause);
	}
	
	public ArchiveSaveFailureException(String message, Throwable cause) {
		super(message, cause);
	}

}
