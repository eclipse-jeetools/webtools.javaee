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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class EARComponentLoadStrategyImpl extends ComponentLoadStrategyImpl {

	public EARComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public List getFiles() {
		aggregateSourceFiles();
		addModulesAndUtilities();
		return filesHolder.getFiles();
	}

	public void addModulesAndUtilities() {
		EARArtifactEdit earArtifactEdit = null;
		try {
			earArtifactEdit = EARArtifactEdit.getEARArtifactEditForRead(vComponent);
			IVirtualReference[] components = earArtifactEdit.getComponentReferences();
			for (int i = 0; i < components.length; i++) {
				IVirtualReference reference = components[i];
				IVirtualComponent referencedComponent = reference.getReferencedComponent();
				if( !referencedComponent.isBinary() ){
					if (J2EEProjectUtilities.isApplicationClientProject(referencedComponent.getProject()) || J2EEProjectUtilities.isEJBProject(referencedComponent.getProject()) || J2EEProjectUtilities.isDynamicWebProject(referencedComponent.getProject()) || J2EEProjectUtilities.isJCAProject(referencedComponent.getProject())) {
						ArtifactEdit componentArtifactEdit = null;
						try {
							componentArtifactEdit = ComponentUtilities.getArtifactEditForRead(referencedComponent);
							Archive archive = ((EnterpriseArtifactEdit) componentArtifactEdit).asArchive(exportSource);
							archive.setURI(earArtifactEdit.getModuleURI(referencedComponent));
							filesHolder.addFile(archive);
						} catch (OpenFailureException oe) {
							Logger.getLogger().logError(oe);
						} finally {
							if (componentArtifactEdit != null) {
								componentArtifactEdit.dispose();
							}
						}
					} else if (J2EEProjectUtilities.isUtilityProject(referencedComponent.getProject())) {
						try {
							if (!referencedComponent.isBinary()) {
								String uri = referencedComponent.getName() + ".jar"; //$NON-NLS-1$
								Archive archive = J2EEProjectUtilities.asArchive(uri, referencedComponent.getProject(), exportSource);
								filesHolder.addFile(archive);
							}
						} catch (OpenFailureException e) {
							Logger.getLogger().logError(e);
						}
					}
				}else{
					java.io.File diskFile = ((VirtualArchiveComponent) referencedComponent).getUnderlyingDiskFile();
					String uri = diskFile.getName();
					addExternalFile(uri, diskFile);					
				}
			}

		} finally {
			if (null != earArtifactEdit) {
				earArtifactEdit.dispose();
			}
		}
	}

	public ZipFileLoadStrategyImpl createLoadStrategy(String uri) throws FileNotFoundException, IOException {
		String filename = uri.replace('/', java.io.File.separatorChar);
		java.io.File file = new java.io.File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException(CommonArchiveResourceHandler.getString("file_not_found_EXC_", (new Object[]{uri, file.getAbsolutePath()}))); //$NON-NLS-1$ = "URI Name: {0}; File name: {1}"
		}
		if (file.isDirectory()) {
			throw new FileNotFoundException(CommonArchiveResourceHandler.getString("file_not_found_EXC_", (new Object[]{uri, file.getAbsolutePath()}))); //$NON-NLS-1$ = "URI Name: {0}; File name: {1}"
		}
		return new org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl(file);
	}
}
