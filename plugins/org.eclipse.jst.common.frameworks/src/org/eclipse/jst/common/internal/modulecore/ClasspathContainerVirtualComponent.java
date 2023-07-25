/*******************************************************************************
 * Copyright (c) 2009, 2019 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.wst.common.componentcore.internal.resources.AbstractResourceListVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFile;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public class ClasspathContainerVirtualComponent extends
		AbstractResourceListVirtualComponent {
	public static final String CLASSPATH = "classpath"; //$NON-NLS-1$
	public static final String CON = "con"; //$NON-NLS-1$
	public static final String CLASSPATH_CON = CLASSPATH + Path.SEPARATOR + CON;
	private String containerPath;
	private IClasspathEntry[] containerEntries;
	private IClasspathContainer container;
	private boolean initialized = false;
	private Object initLock = new Object();
	public ClasspathContainerVirtualComponent(IProject p,
			IVirtualComponent referencingComponent, String containerPath) {
		super(p, referencingComponent);
		this.containerPath = containerPath;
	}
	
	private void init() {
		synchronized (initLock) {
			if (initialized) {
				return;
			}
		}
		try {
			IClasspathContainer localContainer = JavaCore.getClasspathContainer(new Path(containerPath), JavaCore.create(project));
			IClasspathEntry[] localcontainerEntries = null;
			if (localContainer != null) {
				localcontainerEntries = localContainer.getClasspathEntries();
			} else {
				localcontainerEntries = new IClasspathEntry[] {};
			}
			synchronized (initLock) {
				if(initialized){
					return;
				}
				container = localContainer;
				containerEntries = localcontainerEntries;
			}
		} catch (JavaModelException jme) {
			// eat it
		} finally {
			synchronized (initLock) {
				initialized = true;
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o){
			return true;
		}
		
		if(!super.equals(o)){
			return false;
		}
		ClasspathContainerVirtualComponent other = (ClasspathContainerVirtualComponent) o;
		return containerPath == null ? other.containerPath == null : containerPath.equals(other.containerPath);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + (containerPath == null ? 0 : containerPath.hashCode());
	}

	public String getContainerPath() {
		return containerPath;
	}
	
	@Override
	public String getId() {
		return CLASSPATH_CON + Path.SEPARATOR + containerPath;
	}

	@Override
	protected String getFirstIdSegment() {
		// Do not call
		return null;
	}
	
	public IClasspathContainer getClasspathContainer(){
		init();
		return container;
	}
	
	private IClasspathEntry[] getEntries() {
		init();
		return containerEntries;
	}
	
	public String getClasspathContainerPath() {
		return containerPath;
	}

	@Override
	public IVirtualFolder getRootFolder() {
		IVirtualFolder folder = new VirtualFolder(project, new Path("/")) { //$NON-NLS-1$
			@Override
			public IVirtualResource[] members(int memberFlags) throws CoreException {
				IClasspathEntry[] entries = getEntries();
				ArrayList<IVirtualFile> jars = new ArrayList<IVirtualFile>();
				for(IClasspathEntry entry: entries) {
					if( entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
						File f = entry.getPath().toFile();
						jars.add(new AbsoluteVirtualFile(getProject(), new Path("/"), f)); //$NON-NLS-1$
					}
				}
				return jars.toArray(new IVirtualFile[jars.size()]);
			}
		};
		return folder;
	}
	
	protected class AbsoluteVirtualFile extends VirtualFile {
		private File file;
		public AbsoluteVirtualFile(IProject aComponentProject,
				IPath aRuntimePath, File absoluteFile) {
			super(aComponentProject, aRuntimePath, null);
			this.file = absoluteFile;
		}
		@Override
		public Object getAdapter(Class adapter) { 
			if( File.class.equals(adapter))
				return file;
			return null;
		}
	}
	
	@Override
	protected IContainer[] getUnderlyingContainers() {
		// do not implement, overriding key method members(int)
		return new IContainer[]{};
	}

	@Override
	protected IResource[] getLooseResources() {
		// do not implement, overriding key method members(int)
		return new IResource[]{};
	}
}
