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

import java.io.IOException;
import java.util.List;

/**
 * Abstract implementation of {@link IArchiveSaveAdapter} intended for
 * subclassing by clients. See {@link IArchiveSaveAdapter} for details.
 */
public abstract class AbstractArchiveSaveAdapter extends AbstractArchiveAdapter implements IArchiveSaveAdapter {

	public void close() throws IOException {
	}

	public void finish() throws IOException {
	}

	public void save() throws ArchiveSaveFailureException {
		try {
			List files = getArchive().getArchiveResources();
			IArchiveResource file = null;
			for (int i = 0; i < files.size(); i++) {
				file = (IArchiveResource) files.get(i);
				if (shouldSave(file)) {
					save(file);
				}
			}
		} finally {
			try {
				finish();
			} catch (IOException e) {
				throw new ArchiveSaveFailureException(e);
			}
		}
	}

	/**
	 * Subclasses should implement to perform the actual save on the specified
	 * {@link IArchiveResource}.
	 * 
	 * @param file
	 * @throws ArchiveSaveFailureException
	 */
	protected abstract void save(IArchiveResource file) throws ArchiveSaveFailureException;

	/**
	 * Subclassess should override to avoid saving the specified
	 * {@link IArchiveResource}. e.g. to avoid saving source files.
	 * 
	 * @param file
	 * @return
	 */
	protected boolean shouldSave(IArchiveResource file) {
		return true;
	}

}
