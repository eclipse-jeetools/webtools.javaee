/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.web.operations.IBaseWebNature;
import org.eclipse.wst.server.core.model.IProjectModule;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

public class StaticWebDeployableFactory extends ProjectModuleFactoryDelegate {
	private static final String ID = "org.eclipse.jst.j2ee.internal.web.deployables.static"; //$NON-NLS-1$

	/*
	 * @see DeployableProjectFactoryDelegate#getFactoryID()
	 */
	public String getFactoryId() {
		return ID;
	}

	/**
	 * Returns true if the project represents a deployable project of this type.
	 * 
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 * @return boolean
	 */
	protected boolean isValidModule(IProject project) {
		try {
			return project.hasNature(IWebNatureConstants.STATIC_NATURE_ID);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Creates the deployable project for the given project.
	 * 
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 * @return com.ibm.etools.server.core.model.IDeployableProject
	 */
	protected IProjectModule createModule(IProject project) {
		try {
			IProjectModule deployable = null;
			IBaseWebNature nature = (IBaseWebNature) project.getNature(IWebNatureConstants.STATIC_NATURE_ID);
			deployable = (IProjectModule) nature.getModule();
			if (deployable == null)
				deployable = new StaticWebDeployable(nature.getProject());
			return deployable;
		} catch (Exception e) {
		}
		return null;
	}
}