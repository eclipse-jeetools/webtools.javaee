/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.exception;


public class ManifestException extends ArchiveRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1045140899078192019L;

	/**
	 * Constructor for ManifestException.
	 */
	public ManifestException() {
		super();
	}

	/**
	 * Constructor for ManifestException.
	 * 
	 * @param e
	 */
	public ManifestException(Exception e) {
		super(e);
	}

	/**
	 * Constructor for ManifestException.
	 * 
	 * @param s
	 */
	public ManifestException(String s) {
		super(s);
	}

	/**
	 * Constructor for ManifestException.
	 * 
	 * @param s
	 * @param e
	 */
	public ManifestException(String s, Exception e) {
		super(s, e);
	}

}
