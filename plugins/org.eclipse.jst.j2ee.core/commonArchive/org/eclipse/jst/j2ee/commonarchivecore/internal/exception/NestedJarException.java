/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.exception;



/**
 * Runtime exception thrown when an error occurs reading a jar within a jar
 */
public class NestedJarException extends ArchiveRuntimeException {

	public NestedJarException() {
		super();
	}

	public NestedJarException(Exception e) {
		super(e);
	}

	public NestedJarException(String s, Exception e) {
		super(s, e);
	}

	public NestedJarException(String s) {
		super(s);
	}
}
