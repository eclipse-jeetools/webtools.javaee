/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;


import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.server.j2ee.IApplicationClientModule;

public class ApplicationClientDeployable extends J2EEDeployable implements IApplicationClientModule {

	/**
	 * Constructor for ApplicationClientDeployable.
	 * 
	 * @param aNature
	 * @param aFactoryId
	 */
	public ApplicationClientDeployable(J2EENature aNature, String aFactoryId) {
		super(aNature, aFactoryId);
	}

	/**
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
		return "j2ee.appclient"; //$NON-NLS-1$
	}

	public String getVersion() {
		return getNature().getJ2EEVersionText();
	}
}