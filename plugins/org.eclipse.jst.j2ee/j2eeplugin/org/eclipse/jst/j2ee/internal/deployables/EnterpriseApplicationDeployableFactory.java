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
package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.util.ProjectModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;

/**
 * @version 1.0
 * @author
 */
public class EnterpriseApplicationDeployableFactory extends J2EEDeployableFactory {
	protected static final String ID = "com.ibm.wtp.server.j2ee.application"; //$NON-NLS-1$

	protected static final IPath[] PATHS = new IPath[]{new Path("META-INF/application.xml"), //$NON-NLS-1$
				new Path("META-INF/.modulemaps") //$NON-NLS-1$
	};

	/**
	 * Constructor for EnterpriseApplicationDeployableFactory.
	 */
	public EnterpriseApplicationDeployableFactory() {
		super();
	}

	/*
	 * @see DeployableProjectFactoryDelegate#getFactoryID()
	 */
	public String getFactoryId() {
		return ID;
	}

	/*
	 * @see J2EEDeployableFactory#getNatureID()
	 */
	public String getNatureID() {
		return IEARNatureConstants.NATURE_ID;
	}

	/*
	 * @see J2EEDeployableFactory#createDeployable(J2EENature)
	 */
	public IModule createModule(J2EENature nature) {
		return new EnterpriseApplicationDeployable(nature, ID);
	}

	/*
	 * @see DeployableProjectFactoryDelegate#getListenerPaths()
	 */
	protected IPath[] getListenerPaths() {
		return PATHS;
	}

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate#createModule(org.eclipse.core.resources.IProject)
     */
    protected IModule createModule(IProject project) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.model.ModuleFactoryDelegate#getModuleDelegate(org.eclipse.wst.server.core.IModule)
     */
    public ModuleDelegate getModuleDelegate(IModule module) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.model.ModuleFactoryDelegate#getModules()
     */
    public IModule[] getModules() {
        // TODO Auto-generated method stub
        return null;
    }
}