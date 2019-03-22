/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     rfrost@bea.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyComponent;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * Subclass of J2EEModuleVirtualArchiveComponent that represents the component references computed from Java classpath entries that
 * have been marked as publish/export dependencies. In most cases, the classpath entries will resolve into archive dependencies, 
 * however, class folder dependencies are also possible and those are represented as a reference to an VirtualComponent using this
 * class as well.
 */
public class ClasspathDependencyVirtualComponent extends J2EEModuleVirtualArchiveComponent implements IClasspathDependencyComponent {

	final private boolean isClassFolder;
	
	public ClasspathDependencyVirtualComponent(IProject aComponentProject, String location, boolean isClassFolder) {
		super(aComponentProject, location, null);
		this.isClassFolder = isClassFolder;
	}

	/**
	 * Returns true if this component represents a library class folder entry.
	 * @return True for class folders.
	 */
	@Override
	public boolean isClassFolder() {
		return isClassFolder;
	}
	
	/**
	 * Returns the container for the specified class folder or null if does not represent a class folder.
	 * @return IContainer for the class folder or null if this reference does not match class folder.
	 */
	@Override
	public IContainer getClassFolder() {
		if (!isClassFolder) {
			return null;
		}
		final IPath path = new Path(getName().substring(4));
		final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		final IContainer[] allContainers = root.findContainersForLocation(path);
		if (allContainers == null || allContainers.length == 0 || allContainers[0].getType() == IResource.ROOT) {
			return null;
		} 
		// return first matching container
		return allContainers[0];
	}
	
	@Override
	public IVirtualReference[] getReferences() {
		if (isClassFolder) {
			return NO_REFERENCES;
		}
		return super.getReferences();
	}

	@Override
	public String[] getManifestClasspath() {
		if (isClassFolder) {
			return new String[0];
		}
		return super.getManifestClasspath();
	}
	
	@Override
	public Object getAdapter(Class clazz) {
		if( isClassFolder && clazz.equals(IPath.class)) 
			return getClassFolder().getLocation();
		return super.getAdapter(clazz);
	}
}
