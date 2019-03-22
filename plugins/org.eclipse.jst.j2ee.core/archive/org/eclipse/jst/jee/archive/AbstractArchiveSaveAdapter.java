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

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;

/**
 * Abstract implementation of {@link IArchiveSaveAdapter} intended for
 * subclassing by clients. See {@link IArchiveSaveAdapter} for details.
 */
public abstract class AbstractArchiveSaveAdapter extends AbstractArchiveAdapter implements IArchiveSaveAdapter {

	public void close() throws IOException {
	}

	public void finish() throws IOException {
	}

	protected IArchiveResource manifest = null; 
	protected IArchiveResource metaInf = null; 
	
	public void save(IProgressMonitor monitor) throws ArchiveSaveFailureException {
		final int GATHER_RESOURCES_TICKS = 1000;
		final int SAVE_RESOURCES_TICKS = 1000;
		final int FINISH_TICKS = 10;
		final int TOTAL_TICKS = GATHER_RESOURCES_TICKS + SAVE_RESOURCES_TICKS + FINISH_TICKS;
		Exception caughtException = null;
		try {
			monitor.beginTask(CommonArchiveResourceHandler.AbstractArchiveSaveAdapter_Saving_resource_, TOTAL_TICKS);
			
			List<IArchiveResource> files = getArchiveResourcesForSave();
			IPath manifestPath = new Path("META-INF/MANIFEST.MF"); //$NON-NLS-1$
			if(getArchive().containsArchiveResource(manifestPath)){
				manifest = getArchive().getArchiveResource(manifestPath);
			}
			IPath metaInfPath = manifestPath.removeFirstSegments(1);
			if(getArchive().containsArchiveResource(metaInfPath)){
				metaInf = getArchive().getArchiveResource(metaInfPath);
			}
				
			monitor.worked(GATHER_RESOURCES_TICKS);
			IProgressMonitor saveSubMonitor = new SubProgressMonitor(monitor, SAVE_RESOURCES_TICKS);
			int SUB_SAVE_TICKS = 10;
			int SUB_TOTAL_TICKS = SUB_SAVE_TICKS * (files.size() + 2);
			try {
				saveSubMonitor.beginTask(CommonArchiveResourceHandler.AbstractArchiveSaveAdapter_Saving_resource_, SUB_TOTAL_TICKS);
				if(manifest != null){
					save(manifest);
				}
				saveSubMonitor.worked(SUB_SAVE_TICKS);
				if(metaInf != null){
					save(metaInf);
				}
				saveSubMonitor.worked(SUB_SAVE_TICKS);
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

	protected List<IArchiveResource> getArchiveResourcesForSave() {
		List<IArchiveResource> files = getArchive().getArchiveResources();
		return files;
	}

	/**
	 * Subclasses should implement to perform the actual save on the specified
	 * { @link IArchiveResource}.
	 * 
	 * @param file
	 * @throws ArchiveSaveFailureException
	 */
	protected abstract void save(IArchiveResource file) throws ArchiveSaveFailureException;

	/**
	 * Subclassess should override to avoid saving the specified
	 * { @link IArchiveResource}. e.g. to avoid saving source files.
	 * 
	 * @param file
	 * @return
	 */
	protected boolean shouldSave(IArchiveResource file) {
		if(file == manifest || file == metaInf){
			return false;
		}
		return true;
	}

}
