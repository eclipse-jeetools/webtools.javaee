/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.util.zip.ZipFile;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.internal.ZipFileArchiveLoadAdapterImpl;

public class JavaEEEMFZipFileLoadAdapterImpl extends ZipFileArchiveLoadAdapterImpl {

	private JavaEEEMFArchiveAdapterHelper emfHelper = new JavaEEEMFArchiveAdapterHelper();

	public JavaEEEMFZipFileLoadAdapterImpl() {
		super();
	}

	public JavaEEEMFZipFileLoadAdapterImpl(ZipFile zipFile) {
		super(zipFile);
	}

	@Override
	public void setArchive(IArchive archive) {
		super.setArchive(archive);
		emfHelper.setArchive(archive);
	}

	public Resource getResource(IPath resourcePath) throws ArchiveModelLoadException {
		return emfHelper.getResource(resourcePath);
	}
	
	@Override
	public boolean containsModelObject(IPath modelObjectPath) {
		return emfHelper.containsModelObject(modelObjectPath);
	}

	@Override
	public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
		return emfHelper.getModelObject(modelObjectPath);
	}
	
	@Override
	public void close() {
		super.close();
		WorkbenchResourceHelperBase.removeAndUnloadAll(emfHelper.getResourceSet().getResources(), emfHelper.getResourceSet());
	}
	
	protected JavaEEEMFArchiveAdapterHelper getEMFHelper() {
		return emfHelper;
	}
}
