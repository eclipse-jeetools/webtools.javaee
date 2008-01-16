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
package org.eclipse.jst.j2ee.componentcore;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.builder.DependencyGraphManager;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFolder;
import org.eclipse.wst.common.componentcore.internal.util.IComponentImplFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class J2EEModuleVirtualComponent extends VirtualComponent implements IComponentImplFactory {
	
	private IVirtualReference[] cachedReferences;
	private long depGraphModStamp;

	public J2EEModuleVirtualComponent() {
		super();
	}

	public J2EEModuleVirtualComponent(IProject aProject, IPath aRuntimePath) {
		super(aProject, aRuntimePath);
	}

	public IVirtualComponent createComponent(IProject aProject) {
		return new J2EEModuleVirtualComponent(aProject, new Path("/")); //$NON-NLS-1$
	}

	public IVirtualComponent createArchiveComponent(IProject aProject, String archiveLocation, IPath aRuntimePath) {
		return new J2EEModuleVirtualArchiveComponent(aProject, archiveLocation, aRuntimePath);
	}
	
	public IVirtualFolder createFolder(IProject aProject, IPath aRuntimePath) {
		return new VirtualFolder(aProject, aRuntimePath);
	}
	
	public IVirtualReference[] getReferences() {
		IVirtualReference[] cached = getCachedReferences();
		if (cached != null)
			return cached;
		
		IVirtualReference[] hardReferences = getNonManifestReferences();
		List dynamicReferences = J2EEModuleVirtualComponent.getManifestReferences(this, hardReferences);

		IVirtualReference[] references = null;
		if (dynamicReferences == null) {
			references = hardReferences;
		} else {
			references = new IVirtualReference[hardReferences.length + dynamicReferences.size()];
			System.arraycopy(hardReferences, 0, references, 0, hardReferences.length);
			for (int i = 0; i < dynamicReferences.size(); i++) {
				references[hardReferences.length + i] = (IVirtualReference) dynamicReferences.get(i);
			}
		}
		cachedReferences = references;
		
		return references;
	}
	
	public IVirtualReference[] getNonManifestReferences() {
		return super.getReferences();
	}

	private boolean checkIfStillValid() {
		return DependencyGraphManager.getInstance().checkIfStillValid(depGraphModStamp);
	}

	// Returns cache if still valid or null
	public IVirtualReference[] getCachedReferences() {
		if (cachedReferences != null && checkIfStillValid())
			return cachedReferences;
		else
			depGraphModStamp = DependencyGraphManager.getInstance().getModStamp();
		return null;
	}

	public static String [] getManifestClasspath(IVirtualComponent moduleComponent) {
		String[] manifestClasspath = null;
		if(!moduleComponent.isBinary()){
			IVirtualFile vManifest = moduleComponent.getRootFolder().getFile(J2EEConstants.MANIFEST_URI);
			if (vManifest.exists()) {
				IFile manifestFile = vManifest.getUnderlyingFile();
				InputStream in = null;
				try {
					in = manifestFile.getContents();
					ArchiveManifest manifest = new ArchiveManifestImpl(in);
					manifestClasspath = manifest.getClassPathTokenized();
				} catch (IOException e) {
					Logger.getLogger().logError(e);
				} catch (CoreException e) {
					Logger.getLogger().logError(e);
				} finally {
					if (in != null) {
						try {
							in.close();
							in = null;
						} catch (IOException e) {
							Logger.getLogger().logError(e);
						}
					}
				}
			}
		} else {
			manifestClasspath = ((J2EEModuleVirtualArchiveComponent)moduleComponent).getManifestClasspath();
		}
		
		return manifestClasspath;
			
	}
	

	public static List getManifestReferences(IVirtualComponent moduleComponent, IVirtualReference[] hardReferences) {
		List dynamicReferences = null;
		String [] manifestClasspath = getManifestClasspath(moduleComponent); 

		IVirtualReference foundRef = null;
		String earArchiveURI = null; //The URI for this archive in the EAR
		boolean simplePath = false;
		
		if (manifestClasspath != null && manifestClasspath.length > 0) {
			IProject[] earProjects = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.ENTERPRISE_APPLICATION);
			IVirtualReference[] earRefs = null;
			for (int i = 0; i < earProjects.length && null == earRefs; i++) {
				IVirtualComponent tempEARComponent = ComponentCore.createComponent(earProjects[i]);
				IVirtualReference[] tempEarRefs = tempEARComponent.getReferences();
				for (int j = 0; j < tempEarRefs.length && earRefs == null; j++) {
					if (tempEarRefs[j].getReferencedComponent().equals(moduleComponent)) {
						earRefs = tempEarRefs;
						foundRef = tempEarRefs[j];
						earArchiveURI = foundRef.getArchiveName(); 
						simplePath = earArchiveURI != null ? earArchiveURI.lastIndexOf("/") == -1 : true; //$NON-NLS-1$
					}
				}
			}

			if (null != earRefs) {
				for (int i = 0; i < manifestClasspath.length; i++) {
					boolean found = false;
					for (int j = 0; j < earRefs.length && !found; j++) {
						if(foundRef != earRefs[j]){
							String archiveName = earRefs[j].getArchiveName();
							if (null != archiveName){
								boolean shouldAdd = false;
								if(simplePath && manifestClasspath[i].lastIndexOf("/") == -1){ //$NON-NLS-1$
									shouldAdd = archiveName.equals(manifestClasspath[i]);	
								} else {
									String earRelativeURI = ArchiveUtil.deriveEARRelativeURI(manifestClasspath[i], earArchiveURI);
									if(null != earRelativeURI){
										shouldAdd = earRelativeURI.equals(archiveName);	
									}
								}
								
								if(shouldAdd){
									found = true;
									boolean shouldInclude = true;
									IVirtualComponent dynamicComponent = earRefs[j].getReferencedComponent();
									if(null != hardReferences){
										for (int k = 0; k < hardReferences.length && shouldInclude; k++) {
											if (hardReferences[k].getReferencedComponent().equals(dynamicComponent)) {
												shouldInclude = false;
											}
										}
									}
									if (shouldInclude) {
										IVirtualReference dynamicReference = ComponentCore.createReference(moduleComponent, dynamicComponent);
										if (null == dynamicReferences) {
											dynamicReferences = new ArrayList();
										}
										dynamicReferences.add(dynamicReference);
									}
								}
							}
						}
					}
				}
			}
		}
		return dynamicReferences;
	}

}
