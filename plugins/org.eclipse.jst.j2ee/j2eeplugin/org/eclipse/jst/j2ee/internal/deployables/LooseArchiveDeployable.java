/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.server.j2ee.ILooseArchive;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleType;
import org.eclipse.wst.server.core.util.ProjectModule;


public class LooseArchiveDeployable extends ProjectModule implements ILooseArchive, IModule {
	protected String factoryId;

	/**
	 * Constructor for LooseArchiveDeployable.
	 */
	public LooseArchiveDeployable(IProject aProject, String aFactoryId) {
		super(aProject);
		factoryId = aFactoryId;
	}

	/*
	 * @see ILooseArchive#getLocation()
	 */
	public IPath getLocation() {
		return J2EEProjectUtilities.getRuntimeLocation(project);
	}

	/*
	 * @see com.ibm.etools.server.core.util.DeployableProject#isBinary()
	 */
	public boolean isBinary() {
		return J2EEProjectUtilities.isBinaryProject(getProject());
	}

	/*
	 * @see com.ibm.etools.server.core.util.DeployableProject#getRootFolder()
	 */
	public IPath getRootFolder() {
		if (isBinary())
			return null;
		IContainer c = J2EEProjectUtilities.getJavaProjectOutputContainer(getProject());
		if (c != null)
			return c.getProjectRelativePath();
		return null;
	}

	/*
	 * @see getFactoryId()
	 */
	public String getFactoryId() {
		return factoryId;
	}

	public String getType() {
		return "j2ee.loosearchive"; //$NON-NLS-1$
	}

	public String getVersion() {
		return "1.0"; //$NON-NLS-1$
	}

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IModule#validate(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IStatus validate(IProgressMonitor monitor) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IModule#getModuleType()
     */
    public IModuleType getModuleType() {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.IModule#getChildModules(org.eclipse.core.runtime.IProgressMonitor)
     */
    public IModule[] getChildModules(IProgressMonitor monitor) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class adapter) {
        // TODO Auto-generated method stub
        return null;
    }
}