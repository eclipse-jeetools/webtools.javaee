/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.archive.ConnectorComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorComponentImportOperation extends J2EEArtifactImportOperation {

	public ConnectorComponentImportOperation(IDataModel model) {
		super(model);
	}

	@Override
	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		addAssociateArchivesToClassPath();
	}

	/**
	 * Adds all jar within the file to the classpath.
	 */
	protected void addAssociateArchivesToClassPath() {
		List <IClasspathEntry> extraEntries = new ArrayList <IClasspathEntry> ();
		
		List <IArchiveResource> archiveResources = ((IArchive)archiveWrapper.getUnderLyingArchive()).getArchiveResources();
		IVirtualFile vFile = null;
		IFile file = null;
		for(IArchiveResource archiveResource : archiveResources){
			if(archiveResource.getPath().lastSegment().endsWith(IJ2EEModuleConstants.JAR_EXT)){
				vFile = virtualComponent.getRootFolder().getFile(archiveResource.getPath());
				if (vFile.exists()) {
					file = vFile.getUnderlyingFile();
					extraEntries.add(JavaCore.newLibraryEntry(file.getFullPath(), file.getFullPath(), null, true));
				}
			}
		}
		try {
			addToClasspath(getDataModel(), extraEntries);
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}
	
	protected static void addToClasspath(IDataModel importModel, List extraEntries) throws JavaModelException {
		if (extraEntries.size() > 0) {
			IJavaProject javaProject = JavaCore.create(((IVirtualComponent) importModel.getProperty(IJ2EEComponentImportDataModelProperties.COMPONENT)).getProject());
			addToClasspath(extraEntries, javaProject);
		}
	}
	
	protected static void addToClasspath(List extraEntries, IJavaProject javaProject) throws JavaModelException {
		if (extraEntries.size() > 0) {
			IClasspathEntry[] javaClasspath = javaProject.getRawClasspath();
			List nonDuplicateList = new ArrayList();
			for (int i = 0; i < extraEntries.size(); i++) {
				IClasspathEntry extraEntry = (IClasspathEntry) extraEntries.get(i);
				boolean include = true;
				for (int j = 0; include && j < javaClasspath.length; j++) {
					if (extraEntry.equals(javaClasspath[j])) {
						include = false;
					}
				}
				if (include) {
					nonDuplicateList.add(extraEntry);
				}
			}
			if (nonDuplicateList.size() > 0) {
				IClasspathEntry[] newJavaClasspath = new IClasspathEntry[javaClasspath.length + nonDuplicateList.size()];
				System.arraycopy(javaClasspath, 0, newJavaClasspath, 0, javaClasspath.length);
				for (int j = 0; j < nonDuplicateList.size(); j++) {
					newJavaClasspath[javaClasspath.length + j] = (IClasspathEntry) nonDuplicateList.get(j);
				}
				javaProject.setRawClasspath(newJavaClasspath, new NullProgressMonitor());
			}
		}
	}
	
	@Override
	protected ComponentArchiveSaveAdapter getArchiveSaveAdapter(IVirtualComponent virtualComponent){
		return new ConnectorComponentArchiveSaveAdapter(virtualComponent);
	}
}
