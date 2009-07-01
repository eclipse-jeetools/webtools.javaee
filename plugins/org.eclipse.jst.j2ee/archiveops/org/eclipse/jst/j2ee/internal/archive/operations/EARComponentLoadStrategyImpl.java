/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
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
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyVirtualComponent;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
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
	
	public EARComponentLoadStrategyImpl(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		super(vComponent, includeClasspathComponents);
	}

	@Override
	public List getFiles() {
		aggregateSourceFiles();
		addModulesAndUtilities();
		return filesHolder.getFiles();
	}
	@Override
	protected synchronized ArtifactEdit getArtifactEditForRead() {
		if(artifactEdit == null){
			artifactEdit = EARArtifactEdit.getEARArtifactEditForRead(vComponent);
		}
		return artifactEdit;
	}

	@Override
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
				boolean forceUtility = false;
				if (referencedComponent.isBinary()) {
					JavaEEQuickPeek qp = JavaEEBinaryComponentHelper.getJavaEEQuickPeek(referencedComponent);
					int jeeVersion = qp.getJavaEEVersion();
					if(jeeVersion == J2EEVersionConstants.JEE_5_0_ID){
						forceUtility = true;
					}
					diskFile = ((VirtualArchiveComponent) referencedComponent).getUnderlyingDiskFile();
					if (!diskFile.exists()) {
						IFile wbFile = ((VirtualArchiveComponent) referencedComponent).getUnderlyingWorkbenchFile();
						if (wbFile != null && wbFile.exists()) {
							diskFile = new File(wbFile.getLocation().toOSString());
						}
					}
				}
				boolean isModule = false;
				if(!forceUtility){
					boolean addClasspathComponentDependencies = false;
					ArtifactEdit componentArtifactEdit = null;
					try {
						if (J2EEProjectUtilities.isApplicationClientComponent(referencedComponent)) {
							componentArtifactEdit = AppClientArtifactEdit.getAppClientArtifactEditForRead(referencedComponent);
						} else if (J2EEProjectUtilities.isEJBComponent(referencedComponent)) {
							addClasspathComponentDependencies = true;
							componentArtifactEdit = ArtifactEditRegistryReader.instance().getArtifactEdit(J2EEProjectUtilities.EJB).createArtifactEditForRead(referencedComponent);
						} else if (J2EEProjectUtilities.isDynamicWebComponent(referencedComponent)) {
							addClasspathComponentDependencies = true;
							componentArtifactEdit = ArtifactEditRegistryReader.instance().getArtifactEdit(J2EEProjectUtilities.DYNAMIC_WEB).createArtifactEditForRead(referencedComponent);
						} else if (J2EEProjectUtilities.isJCAComponent(referencedComponent)) {
							addClasspathComponentDependencies = true;
							componentArtifactEdit = ArtifactEditRegistryReader.instance().getArtifactEdit(J2EEProjectUtilities.JCA).createArtifactEditForRead(referencedComponent);
						}
						if (null != componentArtifactEdit) {
							Archive archive = ((EnterpriseArtifactEdit) componentArtifactEdit).asArchive(exportSource, includeClasspathComponents);
							if (referencedComponent.isBinary()) {
								artifactEditsToDispose.add(componentArtifactEdit);
								archive.setLoadingContainer(getContainer());
								binaryComponentURIsToDiskFileMap.put(archive.getOriginalURI(), diskFile);
							}
							archive.setURI(earArtifactEdit.getModuleURI(referencedComponent));
							filesHolder.addFile(archive);
							isModule = true;
							if (addClasspathComponentDependencies) {
								addClasspathComponentDependencies(referencedComponent);
							}
						}
					} catch (OpenFailureException oe) {
						J2EEPlugin.logError(oe);
					} finally {
						if (!referencedComponent.isBinary() && componentArtifactEdit != null) {
							componentArtifactEdit.dispose();
						}
					}
				}
				if (!isModule) {
					if (referencedComponent.isBinary()) {
						String uri = reference.getArchiveName();
						addExternalFile(uri, diskFile);
					} else if (J2EEProjectUtilities.isUtilityProject(referencedComponent.getProject())) {
						try {
							if (!referencedComponent.isBinary()) {
								String uri = earArtifactEdit.getModuleURI(referencedComponent);
								Archive archive = J2EEProjectUtilities.asArchive(uri, referencedComponent.getProject(), exportSource, includeClasspathComponents);
								archive.setURI(uri);
								filesHolder.addFile(archive);
								addClasspathComponentDependencies(referencedComponent);
							} else {


							}
						} catch (OpenFailureException e) {
							J2EEPlugin.logError(e);
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

	private void addClasspathComponentDependencies(final IVirtualComponent referencedComponent) {
		// retrieve all Java classpath component dependencies
		if (includeClasspathComponents && referencedComponent instanceof J2EEModuleVirtualComponent) {
			final IVirtualReference[] cpRefs = ((J2EEModuleVirtualComponent) referencedComponent).getJavaClasspathReferences();
			for (int j = 0; j < cpRefs.length; j++) {
				final IVirtualReference ref = cpRefs[j];
				// only ../ runtime paths contribute to the EAR
				if (ref.getRuntimePath().equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
					if (ref.getReferencedComponent() instanceof ClasspathDependencyVirtualComponent) {
						final ClasspathDependencyVirtualComponent comp = (ClasspathDependencyVirtualComponent) ref.getReferencedComponent();
						File cpEntryFile = comp.getUnderlyingDiskFile();
						if (!cpEntryFile.exists()) {
							final IFile wbFile = comp.getUnderlyingWorkbenchFile();
							cpEntryFile = new File(wbFile.getLocation().toOSString());
						}
						addExternalFile(ref.getArchiveName(), cpEntryFile);
					}
				}
			}
		}
	}
	
	@Override
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
