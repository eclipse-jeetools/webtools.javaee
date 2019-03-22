/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @deprecated this will be deleted post WTP 3.2.  This code is no longer
 * called because the IArchive API is used to handle imports.
 * 
 * @author jsholl
 */
public class EARComponentSaveStrategyImpl extends ComponentSaveStrategyImpl {

	public EARComponentSaveStrategyImpl(IVirtualComponent component) {
		super(component);
	}

	@Override
	public void setDataModel(IDataModel dataModel) {
		super.setDataModel(dataModel);
	}

	protected EARFile getEARFile() {
		return (EARFile) getArchive();
	}


	@Override
	protected java.io.OutputStream getOutputStreamForResource(org.eclipse.emf.ecore.resource.Resource aResource) throws java.io.IOException {
		return null;
	}

	@Override
	public void save() throws SaveFailureException {

		saveFiles();
		saveManifest();
		saveMofResources();
		progressMonitor.subTask(EARArchiveOpsResourceHandler.Updating_project_classpath_UI_);
	}

	@Override
	public void save(Archive anArchive) throws SaveFailureException {
		progressMonitor.subTask(anArchive.getURI());
		saveArchiveAsJARInEAR(anArchive);
	}

	protected void saveArchiveAsJARInEAR(Archive anArchive) throws SaveFailureException {
		try {
			anArchive.save(createNestedSaveStrategy(anArchive));
			progressMonitor.worked(1);
		} catch (IOException e) {
			throw new SaveFailureException(anArchive.getURI(), e);
		}
	}

	protected SubProgressMonitor subMonitor() {
		return new SubProgressMonitor(progressMonitor, 10);
	}

	public void setMonitor(org.eclipse.core.runtime.IProgressMonitor newMonitor) {
		progressMonitor = newMonitor;
	}

	@Override
	protected boolean shouldSave(File aFile) {
		if (aFile.isArchive()) {
			return getFilter().shouldSave(aFile.getURI(), getArchive());
		}
		return super.shouldSave(aFile);
	}

	@Override
	protected boolean shouldLinkAsComponentRef(Archive archive) {
		
		return true;
	}

	@Override
	protected boolean shouldSave(String uri) {
		if (overwriteHandler != null) {
			if (overwriteHandler.isOverwriteNone())
				return false;
			return ((super.shouldSave(uri)) && (overwriteHandler.isOverwriteAll() || overwriteHandler.isOverwriteResources() || overwriteHandler.shouldOverwrite(uri)));
		}
		return true;
	}

	@Override
	public void save(ArchiveManifest aManifest) throws SaveFailureException {
		IVirtualFolder rootFolder = vComponent.getRootFolder();
		IVirtualFile vFile = rootFolder.getFile(new Path(J2EEConstants.MANIFEST_URI));
		IFile iFile = vFile.getUnderlyingFile();
		validateEdit(iFile);
		OutputStream out = new WorkbenchByteArrayOutputStream(iFile);
		try {
			aManifest.write(out);
		} catch (IOException e) {
			J2EEPlugin.logError(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				J2EEPlugin.logError(e);
			}
		}
	}

}
