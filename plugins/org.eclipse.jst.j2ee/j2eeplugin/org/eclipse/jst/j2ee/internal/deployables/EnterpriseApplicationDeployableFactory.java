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

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.server.core.IModule;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @version 1.0
 * @author
 */
public class EnterpriseApplicationDeployableFactory extends J2EEDeployableFactory {/*
																				    * (non-Javadoc)
																				    * 
																				    * @see org.eclipse.wst.server.core.model.ModuleFactoryDelegate#getModules()
																				    */
	public IModule[] getModules() {
		return super.getModules();
	}

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


	public IModule createModule(J2EENature nature) {
		if (nature == null)
			return null;
		EnterpriseApplicationDeployable moduleDelegate = null;
		IModule module = nature.getModule();
		if (module == null) {
			try {
				moduleDelegate = new EnterpriseApplicationDeployable(nature, ID);
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

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory#createModules(org.eclipse.wst.common.modulecore.ModuleCoreNature)
     */
    protected List createModules(ModuleCoreNature nature) {
        // TODO Auto-generated method stub
        return null;
    }



}