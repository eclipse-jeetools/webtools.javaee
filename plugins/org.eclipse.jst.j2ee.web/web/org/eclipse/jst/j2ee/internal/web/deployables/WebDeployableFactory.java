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

import com.ibm.wtp.common.logger.proxy.Logger;

public class WebDeployableFactory extends J2EEDeployableFactory {
    private static final String ID = "com.ibm.wtp.web.server"; //$NON-NLS-1$

   

    protected static final IPath[] PATHS = new IPath[] { new Path(".j2ee") //$NON-NLS-1$
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
        if (nature == null)
            return null;
        J2EEWebDeployable moduleDelegate = null;
        IModule module = nature.getModule();
        if (module == null) {
            try {
                moduleDelegate = new J2EEWebDeployable(nature, ID);
                module = createModule(moduleDelegate.getId(), moduleDelegate.getName(), moduleDelegate.getType(), moduleDelegate.getVersion(), moduleDelegate.getProject());
                nature.setModule(module);
                moduleDelegate.initialize(module);
            } catch (Exception e) {
                Logger.getLogger().write(e);
            } finally {
                moduleDelegates.add(moduleDelegate);
            }
        }
        return module;
    }

    /*
     * @see DeployableProjectFactoryDelegate#getListenerPaths()
     */
    protected IPath[] getListenerPaths() {
        return PATHS;
    }




}