/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.deployables;


import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployable;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.server.j2ee.IEJBModule;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleType;

public class EJBDeployable extends J2EEDeployable implements IEJBModule {
	/**
	 * Constructor for EJBDeployable.
	 * 
	 * @param aNature
	 * @param aFactoryId
	 */
	public EJBDeployable(J2EENature aNature, String aFactoryId) {
		super(aNature, aFactoryId);
	}

	/*
	 * @see IEJBModule#getEJBSpecificationVersion()
	 */
	public String getEJBSpecificationVersion() {
		return getNature().isJ2EE1_3() ? "2.0" : "1.1"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/*
	 * @see IEJBModule#getJNDIName(String)
	 */
	public String getJNDIName(String ejbName) {
		EjbModuleExtensionHelper modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
		return modHelper == null ? null : modHelper.getJNDIName(getEJBNature().getEJBJar(), getEJBNature().getEJBJar().getEnterpriseBeanNamed(ejbName));

	}

	protected EJBNatureRuntime getEJBNature() {
		return (EJBNatureRuntime) getNature();
	}

	/*
	 * @see com.ibm.etools.server.core.util.DeployableProject#getRootFolder()
	 */
	public IPath getRootFolder() {
		J2EEModuleNature aNature = (J2EEModuleNature) getNature();
		if (aNature == null)
			return super.getRootFolder();
		else if (aNature.isBinaryProject())
			return null;
		else
			return aNature.getModuleServerRoot().getProjectRelativePath();
	}

	public String getType() {
		return "j2ee.ejb"; //$NON-NLS-1$
	}

	public String getVersion() {
		return getEJBNature().getJ2EEVersionText();
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