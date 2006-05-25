/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.ArtifactEditRegistryReader;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class EARComponentLoadStrategyImpl extends ComponentLoadStrategyImpl {

	private List artifactEditsToDispose = new ArrayList();
	private Map binaryComponentURIsToDiskFileMap = new HashMap();

	public EARComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public List getFiles() {
		aggregateSourceFiles();
		addModulesAndUtilities();
		return filesHolder.getFiles();
	}

	public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
		if (binaryComponentURIsToDiskFileMap.containsKey(uri)) {
			java.io.File diskFile = (java.io.File) binaryComponentURIsToDiskFileMap.get(uri);
			return new FileInputStream(diskFile);
		}
		return super.getInputStream(uri);
	}

	public void addModulesAndUtilities() {
		EARArtifactEdit earArtifactEdit = null;
		try {
			earArtifactEdit = EARArtifactEdit.getEARArtifactEditForRead(vComponent);
			IVirtualReference[] components = earArtifactEdit.getComponentReferences();
			for (int i = 0; i < components.length; i++) {
				IVirtualReference reference = components[i];
				IVirtualComponent referencedComponent = reference.getReferencedComponent();

				java.io.File diskFile = null;
				if (referencedComponent.isBinary()) {
					diskFile = ((VirtualArchiveComponent) referencedComponent).getUnderlyingDiskFile();
					if (!diskFile.exists()) {
						IFile wbFile = ((VirtualArchiveComponent) referencedComponent).getUnderlyingWorkbenchFile();
						diskFile = new File(wbFile.getLocation().toOSString());
					}
				}

				boolean isModule = false;
				ArtifactEdit componentArtifactEdit = null;
				try {
					if (J2EEProjectUtilities.isApplicationClientComponent(referencedComponent)) {
						componentArtifactEdit = AppClientArtifactEdit.getAppClientArtifactEditForRead(referencedComponent);
					} else if (J2EEProjectUtilities.isEJBComponent(referencedComponent)) {
						componentArtifactEdit = ArtifactEditRegistryReader.instance().getArtifactEdit(J2EEProjectUtilities.EJB).createArtifactEditForRead(referencedComponent);
					} else if (J2EEProjectUtilities.isDynamicWebComponent(referencedComponent)) {
						componentArtifactEdit = ArtifactEditRegistryReader.instance().getArtifactEdit(J2EEProjectUtilities.DYNAMIC_WEB).createArtifactEditForRead(referencedComponent);
					} else if (J2EEProjectUtilities.isJCAComponent(referencedComponent)) {
						componentArtifactEdit = ArtifactEditRegistryReader.instance().getArtifactEdit(J2EEProjectUtilities.JCA).createArtifactEditForRead(referencedComponent);
					}
					if (null != componentArtifactEdit) {
						Archive archive = ((EnterpriseArtifactEdit) componentArtifactEdit).asArchive(exportSource);
						if (referencedComponent.isBinary()) {
							artifactEditsToDispose.add(componentArtifactEdit);
							archive.setLoadingContainer(getContainer());
							binaryComponentURIsToDiskFileMap.put(archive.getOriginalURI(), diskFile);
						}
						archive.setURI(earArtifactEdit.getModuleURI(referencedComponent));
						filesHolder.addFile(archive);
						isModule = true;
					}
				} catch (OpenFailureException oe) {
					Logger.getLogger().logError(oe);
				} finally {
					if (!referencedComponent.isBinary() && componentArtifactEdit != null) {
						componentArtifactEdit.dispose();
					}
				}
				if (!isModule) {
					if (referencedComponent.isBinary()) {
						String uri = diskFile.getName();
						addExternalFile(uri, diskFile);
					} else if (J2EEProjectUtilities.isUtilityProject(referencedComponent.getProject())) {
						try {
							if (!referencedComponent.isBinary()) {
								String uri = earArtifactEdit.getModuleURI(referencedComponent);
								Archive archive = J2EEProjectUtilities.asArchive(uri, referencedComponent.getProject(), exportSource);
								archive.setURI(uri);
								filesHolder.addFile(archive);
							} else {


							}
						} catch (OpenFailureException e) {
							Logger.getLogger().logError(e);
						}
					}
				}
			}

		} finally {
			if (null != earArtifactEdit) {
				earArtifactEdit.dispose();
			}
		}
	}

	public void close() {
		super.close();
		Iterator it = artifactEditsToDispose.iterator();
		while (it.hasNext()) {
			ArtifactEdit edit = (ArtifactEdit) it.next();
			edit.dispose();
		}
		artifactEditsToDispose.clear();
	}

	public ZipFileLoadStrategyImpl createLoadStrategy(String uri) throws FileNotFoundException, IOException {
		String filename = uri.replace('/', java.io.File.separatorChar);
		java.io.File file = new java.io.File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.file_not_found_EXC_, (new Object[]{uri, file.getAbsolutePath()}))); // =
		}
		if (file.isDirectory()) {
			throw new FileNotFoundException(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.file_not_found_EXC_, (new Object[]{uri, file.getAbsolutePath()}))); // =
		}
		return new org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl(file);
	}
}
