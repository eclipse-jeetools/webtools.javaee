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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * Abstract implementation of {@link IArchiveSaveAdapter} intended for
 * subclassing by clients. See {@link IArchiveSaveAdapter} for details.
 */
public abstract class AbstractArchiveSaveAdapter extends AbstractArchiveAdapter implements IArchiveSaveAdapter {

	public void close() throws IOException {
	}

	public void finish() throws IOException {
	}

	public void save(IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int GATHER_RESOURCES_TICKS = 1000;
		final int SAVE_RESOURCES_TICKS = 1000;
		final int FINISH_TICKS = 10;
		final int TOTAL_TICKS = GATHER_RESOURCES_TICKS + SAVE_RESOURCES_TICKS + FINISH_TICKS;
		Exception caughtException = null;
		try {
			monitor.beginTask("Saving resources", TOTAL_TICKS);
			List<IArchiveResource> files = getArchive().getArchiveResources();
			monitor.worked(GATHER_RESOURCES_TICKS);
			IProgressMonitor saveSubMonitor = new SubProgressMonitor(monitor, SAVE_RESOURCES_TICKS);
			int SUB_SAVE_TICKS = 10;
			int SUB_TOTAL_TICKS = SUB_SAVE_TICKS * files.size();
			try {
				saveSubMonitor.beginTask("Saving resources", SUB_TOTAL_TICKS);
				for (IArchiveResource file : files) {
					if (shouldSave(file)) {
						save(file);
					}
					saveSubMonitor.worked(SUB_SAVE_TICKS);
				}
			} finally {
				saveSubMonitor.done();
			}
		} catch(Exception e){
			caughtException = e;
		} finally {
			try {
				finish();
			} catch (IOException e) {
				throw new ArchiveSaveFailureException(e);
			} finally {
				monitor.done();
				if(caughtException != null){
					throw new ArchiveSaveFailureException(caughtException);
				}
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
