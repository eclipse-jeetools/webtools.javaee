/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.deployables;


import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;

public class WebDeployableFactory extends J2EEDeployableFactory {
	private static final String ID = "com.ibm.wtp.web.server"; //$NON-NLS-1$

	protected static final IPath[] PATHS = new IPath[]{new Path(".websettings") //$NON-NLS-1$
	};

	/*
	 * @see DeployableProjectFactoryDelegate#getFactoryID()
	 */
	public String getFactoryId() {
		return ID;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclise.wtp.j2ee.servers.J2EEDeployableFactory#getNatureID()
	 */
	public String getNatureID() {
		return IWebNatureConstants.J2EE_NATURE_ID;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclise.wtp.j2ee.servers.J2EEDeployableFactory#createDeployable(org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature)
	 */
	public IModule createModule(J2EENature nature) {
		IModule deployable = (IModule) nature.getModule();
		if (deployable == null)
			deployable = new J2EEWebDeployable(nature, ID);
		return deployable;
	}

	/*
	 * @see DeployableProjectFactoryDelegate#getListenerPaths()
	 */
	protected IPath[] getListenerPaths() {
		return PATHS;
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