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
	private IVirtualReference[] cachedFuzzyEARReferences;
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
		return getReferences(false);
	}
	
	public IVirtualReference[] getReferences(boolean findFuzzyEARRefs){
		IVirtualReference[] cached = getCachedReferences(findFuzzyEARRefs);
		if (cached != null)
			return cached;
		
		IVirtualReference[] hardReferences = getNonManifestReferences();
		List dynamicReferences = J2EEModuleVirtualComponent.getManifestReferences(this, hardReferences, findFuzzyEARRefs);

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
		return getCachedReferences(false);
	}
	
	public IVirtualReference[] getCachedReferences(boolean findFuzzyEARRefs) {
		if(findFuzzyEARRefs && cachedFuzzyEARReferences != null && checkIfStillValid()){
			return cachedFuzzyEARReferences;
		} else if (cachedReferences != null && checkIfStillValid()){
			return cachedReferences;
		} 
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
		return getManifestReferences(moduleComponent, hardReferences, false);
	}
	
	public static List getManifestReferences(IVirtualComponent moduleComponent, IVirtualReference[] hardReferences, boolean findFuzzyEARRefs) {
		List dynamicReferences = null;
		String [] manifestClasspath = getManifestClasspath(moduleComponent); 

		IVirtualReference foundRef = null;
		String earArchiveURI = null; //The URI for this archive in the EAR
		boolean simplePath = false;
		
		if (manifestClasspath != null && manifestClasspath.length > 0) {
			boolean [] foundRefAlready = findFuzzyEARRefs ? new boolean[manifestClasspath.length]: null;
			if(null != foundRefAlready){
				for(int i=0; i<foundRefAlready.length; i++){
					foundRefAlready[i] = false;
				}
			}
			IProject[] earProjects = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.ENTERPRISE_APPLICATION);
			for (int earIndex = 0; earIndex < earProjects.length; earIndex++) {
				IVirtualReference[] earRefs = null;
				IVirtualComponent tempEARComponent = ComponentCore.createComponent(earProjects[earIndex]);
				IVirtualReference[] tempEarRefs = tempEARComponent.getReferences();
				for (int j = 0; j < tempEarRefs.length && earRefs == null; j++) {
					if (tempEarRefs[j].getReferencedComponent().equals(moduleComponent)) {
						earRefs = tempEarRefs;
						foundRef = tempEarRefs[j];
						earArchiveURI = foundRef.getArchiveName(); 
						simplePath = earArchiveURI != null ? earArchiveURI.lastIndexOf("/") == -1 : true; //$NON-NLS-1$
					}
				}
				if (null != earRefs) {
					for (int manifestIndex = 0; manifestIndex < manifestClasspath.length; manifestIndex++) {
						boolean found = false;
						if(foundRefAlready != null && foundRefAlready[manifestIndex]){
							continue;
						}
						for (int j = 0; j < earRefs.length && !found; j++) {
							if(foundRef != earRefs[j]){
								String archiveName = earRefs[j].getArchiveName();
								if (null != archiveName){
									boolean shouldAdd = false;
									if(simplePath && manifestClasspath[manifestIndex].lastIndexOf("/") == -1){ //$NON-NLS-1$
										shouldAdd = archiveName.equals(manifestClasspath[manifestIndex]);	
									} else {
										String earRelativeURI = ArchiveUtil.deriveEARRelativeURI(manifestClasspath[manifestIndex], earArchiveURI);
										if(null != earRelativeURI){
											shouldAdd = earRelativeURI.equals(archiveName);	
										}
									}
									
									if(shouldAdd){
										if(findFuzzyEARRefs){
											foundRefAlready[manifestIndex] = true;
										}
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
					if(!findFuzzyEARRefs){
						break;
					} else {
						boolean foundAll = true;
						for(int i = 0; i < foundRefAlready.length && foundAll; i++){
							if(!foundRefAlready[i]){
								foundAll = false;
							}
						}
						if(foundAll){
							break;
						}
					}
				}
			}
		}
		return dynamicReferences;
	}

}
