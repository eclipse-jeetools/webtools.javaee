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

import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;



/**
 * @deprecated Exception no longer thrown; check for null instead. Exception which may be thrown
 *             whenever a {@link ModuleFile}is expected to be contained within an ear file but it
 *             is not
 */
public class UncontainedModuleFileException extends ArchiveRuntimeException {
	/**
	 * UncontainedModuleException constructor comment.
	 */
	public UncontainedModuleFileException() {
		super();
	}

	/**
	 * UncontainedModuleException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public UncontainedModuleFileException(String s) {
		super(s);
	}
}
