/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
	public ClasspathContainerVirtualComponent(IProject p,
			IVirtualComponent referencingComponent, String containerPath) {
		super(p, referencingComponent);
		this.containerPath = containerPath;
		try {
			IClasspathContainer container = JavaCore.getClasspathContainer(new Path(containerPath), 
					JavaCore.create(p));
			containerEntries = container.getClasspathEntries();
		} catch( JavaModelException jme ) {
			
		}
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
	
	public String getClasspathContainerPath() {
		return containerPath;
	}

	@Override
	public IVirtualFolder getRootFolder() {
		IVirtualFolder folder = new VirtualFolder(project, new Path("/")) { //$NON-NLS-1$
			@Override
			public IVirtualResource[] members(int memberFlags) throws CoreException {
				ArrayList<IVirtualFile> jars = new ArrayList<IVirtualFile>();
				for( int i = 0; i < containerEntries.length; i++ ) {
					if( containerEntries[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
						File f = containerEntries[i].getPath().toFile();
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
