/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.archive;

/**
 * Abstract implementation of {@link IArchiveAdapter} intended for subclassing
 * by clients. See {@link IArchiveAdapter} for details. Clients will, however,
 * most likely wish to subclss either {@link AbstractArchiveLoadAdapter} or
 * { @link AbstractArchiveSaveAdapter} instead.
 */
public abstract class AbstractArchiveAdapter implements IArchiveAdapter {

	protected IArchive archive = null;

	public IArchive getArchive() {
		return archive;
	}

	public void setArchive(IArchive archive) {
		this.archive = archive;
	}

}
