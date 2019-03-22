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

import org.eclipse.core.runtime.IProgressMonitor;

public interface IArchiveSaveAdapter extends IArchiveAdapter {

	/**
	 * Close and release any resources being held by this object
	 */
	public void close() throws java.io.IOException;

	/**
	 * Notify resources if necessary that save is complete for this archive,
	 * being careful not to close any resources that a parent archive might
	 * still be using
	 */
	public void finish() throws java.io.IOException;

	public void save(IProgressMonitor monitor) throws ArchiveSaveFailureException;

}
