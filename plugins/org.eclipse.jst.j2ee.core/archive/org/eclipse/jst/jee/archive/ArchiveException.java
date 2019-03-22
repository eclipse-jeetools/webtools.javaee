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

public class ArchiveException extends Exception {

	private static final long serialVersionUID = 3796439415310903317L;

	public ArchiveException() {
		super();
	}

	public ArchiveException(String message) {
		super(message);
	}

	public ArchiveException(Throwable cause) {
		super(cause);
	}
	
	public ArchiveException(String message, Throwable cause){
		super(message, cause);
	}

}
