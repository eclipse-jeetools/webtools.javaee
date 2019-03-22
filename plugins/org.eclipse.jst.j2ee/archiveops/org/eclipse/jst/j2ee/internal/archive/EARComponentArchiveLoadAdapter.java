/*******************************************************************************
 * Copyright (c) 2003, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Stefan Dimov, stefan.dimov@sap.com - bug 207826
 * Kaloyan Raev, kaloyan.raev@sap.com - bug 220912
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyProvider;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class EARComponentArchiveLoadAdapter extends ComponentArchiveLoadAdapter {

	// Optimization to directly copy binary files
	private Map<IArchiveResource, File> binaryResourcesToDiskFiles = new HashMap<IArchiveResource, File>();

	public EARComponentArchiveLoadAdapter(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public EARComponentArchiveLoadAdapter(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		super(vComponent, includeClasspathComponents);
	}

	@Override
	public List<IArchiveResource> getArchiveResources() {
		aggregateSourceFiles();
		addModulesAndUtilities();
		List<IArchiveResource> files = filesHolder.getFiles();
		IPath manifestPath = new Path(J2EEConstants.MANIFEST_URI);
		if (!filesHolder.contains(manifestPath)) {
			IArchiveResource manifest = createManifest(manifestPath);
			files.add(manifest);
		}
		return files;
	}

	@Override
	public InputStream getInputStream(IArchiveResource archiveResource) throws IOException, FileNotFoundException {
		if (binaryResourcesToDiskFiles.containsKey(archiveResource)) {
			java.io.File diskFile = binaryResourcesToDiskFiles.get(archiveResource);
			return new FileInputStream(diskFile);
		}
		return super.getInputStream(archiveResource);
	}

	public void addModulesAndUtilities() {
		IVirtualReference[] components = J2EEProjectUtilities.getComponentReferences(vComponent);
		for (int i = 0; i < components.length; i++) {
			IVirtualReference reference = components[i];
			IVirtualComponent referencedComponent = reference.getReferencedComponent();
			if(vComponent.equals(referencedComponent)){
				continue;
			}
			IArchiveResource nestedModuleResource = null;
			IArchive nestedModuleArchive = null;
			ArchiveOpenFailureException caughtException = null;
			try {
				nestedModuleResource = JavaEEArchiveUtilities.INSTANCE.openArchive(referencedComponent);
			} catch (ArchiveOpenFailureException e) {
				caughtException = e;
			} 
			String sPath = new Path(reference.getArchiveName()).lastSegment();
			String srtp = reference.getRuntimePath().toString();
			if (srtp.startsWith("" + IPath.SEPARATOR)) srtp = srtp.substring(1); //$NON-NLS-1$
			String spt = srtp + IPath.SEPARATOR + sPath;
			if (spt.startsWith("" + IPath.SEPARATOR)) spt = spt.substring(1); //$NON-NLS-1$
			if(nestedModuleResource == null){
				if(referencedComponent.isBinary()){
					nestedModuleResource = createFile(new Path(spt));
				} else {
					ArchiveUtil.warn(caughtException);
					return;
				}
			} else {
				nestedModuleArchive = (IArchive)nestedModuleResource;
				nestedModuleResource.setPath(new Path(spt));
				nestedModuleResource.setArchive(archive);
				filesHolder.addFile(nestedModuleResource);
			}
			
			if (referencedComponent.isBinary()) {
				java.io.File diskFile = referencedComponent.getAdapter(java.io.File.class);
				binaryResourcesToDiskFiles.put(nestedModuleResource, diskFile);
			} else if(null != nestedModuleArchive){
				// Bug 220912 - set "export source" flag before calling JavaEEQuickPeek
				if (nestedModuleResource.getType() == IArchive.ARCHIVE_TYPE) {
					IArchiveLoadAdapter nestedLoadAdapter = nestedModuleArchive.getLoadAdapter();
					if(nestedLoadAdapter instanceof ComponentArchiveLoadAdapter){
						((ComponentArchiveLoadAdapter)nestedLoadAdapter).setExportSource(isExportSource());
					}
				}
				boolean addClasspathComponentDependencies = false;
				JavaEEQuickPeek quickPeek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(nestedModuleArchive);
				switch (quickPeek.getType()) {
					case JavaEEQuickPeek.CONNECTOR_TYPE:
					case JavaEEQuickPeek.EJB_TYPE:
					case JavaEEQuickPeek.WEB_TYPE:
						addClasspathComponentDependencies = true;
					case JavaEEQuickPeek.UNKNOWN:
						if (nestedModuleArchive.getLoadAdapter() instanceof JavaComponentArchiveLoadAdapter) {
							addClasspathComponentDependencies = true;
						}
				}
				if (addClasspathComponentDependencies) {	
					((ComponentArchiveLoadAdapter) nestedModuleArchive.getLoadAdapter()).setIncludeClasspathComponents(includeClasspathComponents);
					addClasspathComponentDependencies(referencedComponent);
				}
			}
		}
	}

	private void addClasspathComponentDependencies(final IVirtualComponent referencedComponent) {
		// retrieve all Java classpath component dependencies
		if (includeClasspathComponents && referencedComponent instanceof IClasspathDependencyProvider) {
			final IVirtualReference[] cpRefs = ((IClasspathDependencyProvider) referencedComponent).getJavaClasspathReferences();
			for (int j = 0; j < cpRefs.length; j++) {
				final IVirtualReference ref = cpRefs[j];
				// only ../ runtime paths contribute to the EAR
				if (ref.getRuntimePath().equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
					File cpEntryFile = ref.getReferencedComponent().getAdapter(File.class);
					addExternalFile(new Path(ref.getArchiveName()), cpEntryFile);
				}
			}
		}
	}
	
	@Override
	protected IPath getDefaultModelObjectPath() {
		return new Path(J2EEConstants.APPLICATION_DD_URI);
	}

}
