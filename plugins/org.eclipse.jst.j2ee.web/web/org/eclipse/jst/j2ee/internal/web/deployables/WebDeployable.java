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
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.web.operations.IBaseWebNature;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntimeUtilities;
import org.eclipse.wst.server.core.model.IProjectModule;
import org.eclipse.wst.server.core.util.ProjectModule;

public abstract class WebDeployable extends ProjectModule implements IProjectModule, IWebNatureConstants {

	public WebDeployable(IProject project) {
		super(project);
		setWebNature(getWebNature());
	}

	protected IBaseWebNature getWebNature() {
		return J2EEWebNatureRuntimeUtilities.getRuntime(this.project);
	}

	public String getFactoryId() {
		return "com.ibm.wtp.web.server"; //$NON-NLS-1$
	}

	/**
	 * Sets the nature.
	 * 
	 * @param nature
	 *            The nature to set
	 */
	protected void setWebNature(IBaseWebNature nature) {
		nature.setModule(this);
	}

	/**
	 * Returns true if this deployable currently exists, and false if it has been deleted or moved
	 * and is no longer represented by this deployable.
	 * 
	 * @return boolean
	 */
	public boolean exists() {
		if (getProject() == null || !getProject().exists())
			return false;
		try {
			return (this.project.hasNature(IWebNatureConstants.J2EE_NATURE_ID));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @see com.ibm.etools.server.core.util.DeployableProject#getRootFolder()
	 */
	public IPath getRootFolder() {
		return getWebNature().getRootPublishableFolder().getProjectRelativePath();
	}
}