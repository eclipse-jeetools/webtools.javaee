/*******************************************************************************
 * Copyright (c) 2003, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.componentcore.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyComponent;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyReceiver;
import org.eclipse.jst.common.jdt.internal.javalite.JavaCoreLite;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants.DependencyAttributeType;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.common.J2EEDependencyListener;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathInitializer;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.builder.IDependencyGraph;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFolder;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualReference;
import org.eclipse.wst.common.componentcore.internal.util.IComponentImplFactory;
import org.eclipse.wst.common.componentcore.internal.util.VirtualReferenceUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public class EARVirtualComponent extends VirtualComponent implements IComponentImplFactory, IClasspathDependencyReceiver {
	public static String [] EXTENSIONS_TO_IGNORE = new String [] {IJ2EEModuleConstants.JAR_EXT, ".zip", IJ2EEModuleConstants.RAR_EXT, IJ2EEModuleConstants.WAR_EXT };  //$NON-NLS-1$

	private IVirtualReference[] cachedReferences;
	private long depGraphModStamp;
	private long jeeModStamp;
	
	public EARVirtualComponent() {
		super();
	}

	public EARVirtualComponent(IProject aProject, IPath aRuntimePath) {
		super(aProject, aRuntimePath);
	}

	@Override
	public IVirtualComponent createArchiveComponent(IProject aProject, String archiveLocation, IPath aRuntimePath) {
		return new J2EEModuleVirtualArchiveComponent(aProject, archiveLocation, aRuntimePath);
	}
	
	@Override
	public IVirtualComponent createComponent(IProject aProject) {
		return new EARVirtualComponent(aProject, new Path("/")); //$NON-NLS-1$
	}

	@Override
	public IVirtualFolder createFolder(IProject aProject, IPath aRuntimePath) {
		return new VirtualFolder(aProject, aRuntimePath);
	}

	@Override
	protected boolean shouldCacheReferences() {
		return true;
	}
	
	private List<IVirtualReference> getHardReferences(IVirtualComponent earComponent) {
		IVirtualReference[] comparison = super.getReferences(new HashMap<String, Object>());
		ArrayList<IVirtualReference> refs2 = new ArrayList<IVirtualReference>();
		refs2.addAll(Arrays.asList(comparison));
		return refs2;
	}

	@Override
	protected void customizeCreatedReference(IVirtualReference reference, Object referenceComponent) {
		if( referenceComponent instanceof ReferencedComponent )
		{
			if (((ReferencedComponent) referenceComponent).getArchiveName() != null)
			{
				reference.setArchiveName(((ReferencedComponent) referenceComponent).getArchiveName());
			}
			else
			{
				reference.setArchiveName(VirtualReferenceUtilities.INSTANCE.getDefaultArchiveName(reference));
			}
		}else { 
				VirtualReferenceUtilities.INSTANCE.ensureReferencesHaveNames(new IVirtualReference[]{reference});
		}
	}

	
	/**
	 * Returns the resulting list of referenced components based off the hard references and archives mapping to the root folder.
	 * 
	 * @param earComponent
	 * @param hardReferences
	 * @param membersToIgnore
	 * @return
	 */
	private static List getLooseArchiveReferences(EARVirtualComponent earComponent, List hardReferences) {
		return  getLooseArchiveReferences(earComponent, hardReferences, null, earComponent.getRootFolder());
	}
	
	private static List getLooseArchiveReferences(EARVirtualComponent earComponent, List hardReferences, List dynamicReferences, IVirtualFolder folder) {
		Map<EARVirtualComponent, List> cache = J2EEComponentClasspathInitializer.getLooseConfigCache();
		if (cache != null) {
			List list = cache.get(earComponent);
			if (list != null) {
				return list;
			}
		}
		List list = getLooseArchiveReferences2(earComponent, hardReferences, null, folder);
		if (cache != null) {
			cache.put(earComponent, list);
		}
		return list;
	}	
	
	private static List getLooseArchiveReferences2(EARVirtualComponent earComponent, List hardReferences, List dynamicReferences, IVirtualFolder folder) {
		List innerDynamicReferences = dynamicReferences;
		try {
			IVirtualResource[] members = folder.members();
			for (int i = 0; i < members.length; i++) {
				if (IVirtualResource.FILE == members[i].getType()) {
					if(isDynamicComponent((IVirtualFile)members[i])){
						IPath archiveFullPath = new Path(members[i].getRuntimePath().toString());
						boolean shouldInclude = true;
						for (int j = 0; j < hardReferences.size() && shouldInclude; j++) {
							IVirtualReference tmpRef = ((IVirtualReference) hardReferences.get(j));
							IPath tmpFullPath = tmpRef.getRuntimePath().append(tmpRef.getArchiveName());
							if( tmpFullPath.equals(archiveFullPath))
								shouldInclude = false;
						} 
						if (shouldInclude) {
							IResource iResource = members[i].getUnderlyingResource();
							IVirtualComponent dynamicComponent = ComponentCore.createArchiveComponent(earComponent.getProject(), VirtualArchiveComponent.LIBARCHIVETYPE + iResource.getFullPath().toString());
							IVirtualReference dynamicRef = ComponentCore.createReference(earComponent, dynamicComponent);
							((VirtualReference)dynamicRef).setDerived(true);
							dynamicRef.setArchiveName(archiveFullPath.lastSegment());
							dynamicRef.setRuntimePath(archiveFullPath.removeLastSegments(1));
							if (null == innerDynamicReferences) {
								innerDynamicReferences = new ArrayList();
							}
							innerDynamicReferences.add(dynamicRef);
						}
					}
				} else if(IVirtualResource.FOLDER == members[i].getType()){
					innerDynamicReferences = getLooseArchiveReferences2(earComponent, hardReferences, innerDynamicReferences, (IVirtualFolder)members[i]);
				}
			}
		} catch (CoreException e) {
			J2EEPlugin.logError(e);
		}
		return innerDynamicReferences;
	}
	public static boolean isDynamicComponent(IVirtualFile vFile){
		String archiveName = vFile.getName();
		for(int j = 0; j<EXTENSIONS_TO_IGNORE.length; j++){
			if(J2EEComponentClasspathUpdater.endsWithIgnoreCase(archiveName, EXTENSIONS_TO_IGNORE[j])){
				return true;
			}
		}
		return false;
	}

	@Override
	public IVirtualReference[] getReferences() {
		IVirtualReference[] cached = getCachedReferences();
		if (cached != null)
			return cached;
		List hardReferences = getHardReferences(this);
		List dynamicReferences = getLooseArchiveReferences(this, hardReferences);

		if (dynamicReferences != null) {
			hardReferences.addAll(dynamicReferences);
		}
		cachedReferences = (IVirtualReference[]) hardReferences.toArray(new IVirtualReference[hardReferences.size()]);
		return cachedReferences;
	}
	
	@Override
	public IVirtualReference[] getReferences(Map<String, Object> options) {
		Object refType = options.get(IVirtualComponent.REQUESTED_REFERENCE_TYPE);
		if( refType != null && (IVirtualComponent.NON_DERIVED_REFERENCES.equals(refType) || 
								IVirtualComponent.HARD_REFERENCES.equals(refType) ||
								IVirtualComponent.DISPLAYABLE_REFERENCES.equals(refType))) {
			List<IVirtualReference> hardReferences = getHardReferences(this);
			return hardReferences.toArray(new IVirtualReference[hardReferences.size()]);
		}
		if( refType != null && IVirtualComponent.DISPLAYABLE_REFERENCES_ALL.equals(refType)){
			checkIfStillValid(); // This will clear the cache of raw references if needed.
			return getAllReferences();
		}
		
		IVirtualReference[] refs = getReferences();
		Boolean objGetExpandRefs = (Boolean)options.get(J2EEModuleVirtualComponent.GET_EXPANDED_LIB_REFS);
		boolean getExpandRefs = objGetExpandRefs != null ? objGetExpandRefs.booleanValue() : false;
		if (getExpandRefs) {
			return JavaEEProjectUtilities.getExpandedReferences(this, refs);
		}
		return refs;
	}
	
	protected boolean shouldAddClasspathDependencyDerivedReference() {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for( int i = 0; i < projects.length; i++ ) {
			try {
				if( projects[i].hasNature(JavaCoreLite.NATURE_ID) && ModuleCoreNature.isFlexibleProject(projects[i])) {
					Map <IClasspathEntry, IClasspathAttribute> results = 
						ClasspathDependencyUtil.getRawComponentClasspathDependencies(
							JavaCoreLite.create(projects[i]), 
									DependencyAttributeType.CLASSPATH_COMPONENT_DEPENDENCY);
					if( !results.isEmpty())
						return true;
				}
			} catch(CoreException ce) {
			}
		}
		return false;
	}
	
	// Returns cache if still valid or null
	public IVirtualReference[] getCachedReferences() {
		if (cachedReferences != null && checkIfStillValid())
			return cachedReferences;
		return null;
	}

	private boolean checkIfStillValid() {
		boolean valid = IDependencyGraph.INSTANCE.getModStamp() == depGraphModStamp;
		valid = valid && J2EEDependencyListener.INSTANCE.getModStamp() == jeeModStamp;
		if (!valid) {
			clearCache();
		}
		return valid;
	}
	
	@Override
	protected void clearCache() {
		super.clearCache();
		depGraphModStamp = IDependencyGraph.INSTANCE.getModStamp();
		jeeModStamp = J2EEDependencyListener.INSTANCE.getModStamp();
		cachedReferences = null;
	}

	@Override
	public boolean canReceiveClasspathDependencies() {
		return true;
	}

	@Override
	public IPath getClasspathFolderPath(IClasspathDependencyComponent component) {
		return Path.EMPTY;
	}
}
