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
import org.eclipse.jst.j2ee.internal.web.operations.IStaticWebNature;
import org.eclipse.jst.j2ee.internal.web.operations.WebNatureRuntimeUtilities;
import org.eclipse.jst.server.j2ee.IStaticWeb;
import org.eclipse.wst.server.core.util.ProjectModule;

public class StaticWebDeployable extends ProjectModule implements IStaticWeb {

	public StaticWebDeployable(IProject project) {
		super(project);
		setWebNature(getStaticWebNature());
	}

	/**
	 * @param staticWebNature
	 */
	private void setWebNature(IStaticWebNature nature) {
		nature.setModule(this);
	}

	public String getFactoryId() {
		return "org.eclipse.jst.j2ee.internal.web.deployables.static"; //$NON-NLS-1$
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
			return (this.project.hasNature(IWebNatureConstants.STATIC_NATURE_ID));
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return false;
	}

	protected IStaticWebNature getStaticWebNature() {
		return WebNatureRuntimeUtilities.getStaticRuntime(this.project);
	}

	public String getContextRoot() {
		IStaticWebNature nature = getStaticWebNature();
		if (nature != null)
			return nature.getContextRoot();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.server.core.util.ProjectModule#getRootFolder()
	 */
	public IPath getRootFolder() {
		// TODO Auto-generated method stub
		return getStaticWebNature().getRootPublishableFolder().getProjectRelativePath();
	}

	public String getType() {
		return "web.static"; //$NON-NLS-1$
	}

	public String getVersion() {
		return "1.0"; //$NON-NLS-1$
	}
}