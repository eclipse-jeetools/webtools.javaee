/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jun 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.taglib;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeTargetHandler;
import org.eclipse.wst.server.core.IRuntimeType;

/**
 * @author admin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class WebProjectServerTaglibListener implements IRuntimeTargetHandler {
	private IRuntime runtimeToBeRemoved;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.core.model.IRuntimeTargetHandlerDelegate#setRuntimeTarget(org.eclipse.core.resources.IProject,
	 *      org.eclipse.wst.server.core.IRuntime, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setRuntimeTarget(IProject project, IRuntime runtime, IProgressMonitor monitor) throws CoreException {
		// TODO right now only v6 server contributes taglibs, if more servers are added need to
		// determine that
		if (this.runtimeToBeRemoved != null) {
			if (ServerJarsUtil.isTargetedAtWASV6(this.runtimeToBeRemoved) || ServerJarsUtil.isTargetedAtWASV6(runtime)) {
				if (WebPlugin.getDefault().getTaglibRegistryManager().isTaglibRegistryExists(project))
					WebPlugin.getDefault().getTaglibRegistryManager().getTaglibRegistry(project).refresh();
			}
		}
		this.runtimeToBeRemoved = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.server.core.model.IRuntimeTargetHandlerDelegate#removeRuntimeTarget(org.eclipse.core.resources.IProject,
	 *      org.eclipse.wst.server.core.IRuntime, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void removeRuntimeTarget(IProject project, IRuntime runtime, IProgressMonitor monitor) throws CoreException {
		this.runtimeToBeRemoved = runtime;
	}

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IRuntimeTargetHandler#getId()
     */
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IRuntimeTargetHandler#supportsRuntimeType(org.eclipse.wst.server.core.IRuntimeType)
     */
    public boolean supportsRuntimeType(IRuntimeType runtimeType) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }

}