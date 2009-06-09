/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.componentcore.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

//TODO this should be renamed to EARVirtualFolder because it is not used only for the root.
public class EARVirtualRootFolder extends VirtualFolder {

	public EARVirtualRootFolder(IProject aComponentProject, IPath aRuntimePath) {
		super(aComponentProject, aRuntimePath);
	}

	public static String [] EXTENSIONS_TO_IGNORE = new String [] {".jar", ".zip", ".rar", ".war" };
	
	public IVirtualResource[] superMembers() throws CoreException {
		return superMembers(IResource.NONE);
	}
	
	public IVirtualResource[] superMembers(int memberFlags) throws CoreException {
		return super.members(memberFlags);
	}
	
	public boolean isDynamicComponent(IVirtualFile vFile){
		String archiveName = vFile.getName();
		for(int j = 0; j<EXTENSIONS_TO_IGNORE.length; j++){
			if(J2EEComponentClasspathUpdater.endsWithIgnoreCase(archiveName, EXTENSIONS_TO_IGNORE[j])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * For now, just rip out files with .jar, .rar, or .war file extensions, because these are
	 * the only files automatically added dyamically
	 */
	@Override
	public IVirtualResource[] members(int memberFlags) throws CoreException {
		IVirtualResource[] members = superMembers(memberFlags);
		List virtualResources = new ArrayList();
		boolean shouldAdd = true;
		for (int i = 0; i < members.length; i++) {
			shouldAdd = true;
			if (IVirtualResource.FILE == members[i].getType()) {
				if(isDynamicComponent((IVirtualFile)members[i])){
					shouldAdd = false;
				}
			}
			if (shouldAdd) {
				virtualResources.add(members[i]);
			}
		}
		return (IVirtualResource[]) virtualResources
				.toArray(new IVirtualResource[virtualResources.size()]);
	}
}
