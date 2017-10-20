/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.componentcore;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.IModuleHandler;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JavaEEModuleHandler implements IModuleHandler {

	@Override
	public String getArchiveName(IProject proj,IVirtualComponent comp) {
		if (comp != null)
			return JavaEEProjectUtilities.getComponentURI(comp);
		return proj.getName() + ".jar"; //$NON-NLS-1$
		
	}
	
	@Override
	public boolean setComponentAttributes(IProject proj) {
		return true;
	}

	@Override
	public List<IProject> getFilteredProjectListForAdd(IVirtualComponent sourceComponent, List<IProject> availableProjects) {
		Iterator<IProject> i = availableProjects.iterator();
		IProject p;
		
		
		while(i.hasNext()) {
			p = i.next();
			if( !p.isOpen())
				i.remove();
			else {
				IProject compProject = sourceComponent.getProject();
				if( p.equals(compProject))
					i.remove();
				else if (!isJavaProject(p))
					i.remove();
			}
		}
		return availableProjects;
	}

	private boolean isJavaProject(IProject project) {
		
		if (project != null)
			try {
				return project.hasNature(JavaCore.NATURE_ID);
			} catch (CoreException e) {
				org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
			}
		return false;
				
	}

}
