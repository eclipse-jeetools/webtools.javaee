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

import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.server.core.IModule;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @version 1.0
 * @author
 */
public class ApplicationClientDeployableFactory extends J2EEDeployableFactory {
	private static final String ID = "com.ibm.wtp.server.j2ee.appclient"; //$NON-NLS-1$

	/**
	 * Constructor for ApplicationClientDeployableFactory.
	 */
	public ApplicationClientDeployableFactory() {
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
		return IApplicationClientNatureConstants.NATURE_ID;
	}

	/*
	 * @see J2EEDeployableFactory#createDeployable(J2EENature)
	 */

	public IModule createModule(J2EENature nature) {
		if (nature == null)
			return null;
		ApplicationClientDeployable moduleDelegate = null;
		IModule module = nature.getModule();
		if (module == null) {
			try {
				moduleDelegate = new ApplicationClientDeployable(nature, ID);
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

}