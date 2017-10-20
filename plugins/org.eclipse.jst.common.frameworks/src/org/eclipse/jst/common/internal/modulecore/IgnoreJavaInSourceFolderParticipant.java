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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.common.jdt.internal.javalite.IJavaProjectLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaCoreLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class IgnoreJavaInSourceFolderParticipant extends AbstractFlattenParticipant {
	IJavaProjectLite liteProj = null;
	List<IPath> containers = new ArrayList<IPath>();
	
	@Override
	public void initialize(IVirtualComponent component,
			FlatComponentTaskModel dataModel, List<IFlatResource> resources) {
		if( hasJava(component) ) {
			liteProj = JavaCoreLite.create(component.getProject());
			if( liteProj != null && JavaLiteUtilities.getJavaOutputContainers(liteProj).size() > 0) {
				List<IContainer> temp = JavaLiteUtilities.getJavaSourceContainers(liteProj);
				Iterator<IContainer> i = temp.iterator();
				while(i.hasNext()) {
					containers.add(i.next().getFullPath());
				}
			}
		}
	}

	@Override
	public boolean shouldAddExportableFile(IVirtualComponent rootComponent,
			IVirtualComponent currentComponent,
			FlatComponentTaskModel dataModel, IFlatFile file) {
		if( rootComponent == currentComponent ) {
			if( hasJava(rootComponent) ) {
				/* 
				 * Only ignore when given an ear, war, etc, and not 
				 * one of it's consumed children, which may be a fileset, 
				 * or some other unknown type of contributor
				 */
				IFile t = file.getAdapter(IFile.class);
				if( t != null ) {
					Iterator<IPath> i = containers.iterator();
					while(i.hasNext()) {
						if( i.next().isPrefixOf(t.getFullPath()) && t.getName().endsWith(".java")) { //$NON-NLS-1$
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	protected boolean hasJava(IVirtualComponent component) {
		try {
			return component.getProject().isNatureEnabled("org.eclipse.jdt.core.javanature"); //$NON-NLS-1$			
		} catch( CoreException ce) {
			// ignore
		}
		return false;
	}

}
