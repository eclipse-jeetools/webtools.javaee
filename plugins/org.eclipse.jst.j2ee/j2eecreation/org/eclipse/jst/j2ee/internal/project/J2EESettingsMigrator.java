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
package org.eclipse.jst.j2ee.internal.project;

/*
 * Created on Apr 22, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.wst.common.frameworks.internal.WTPProjectUtilities;
import org.eclipse.wst.common.internal.migration.CompatibilityUtils;
import org.eclipse.wst.common.internal.migration.IDeprecatedConstants;
import org.eclipse.wst.common.internal.migration.IMigrator;

import org.eclipse.jem.util.emf.workbench.ProjectUtilities;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EESettingsMigrator implements IMigrator {
	/**
	 *  
	 */
	public J2EESettingsMigrator() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.common.migration.IMigrator#migrate(org.eclipse.core.resources.IProject)
	 */
	public boolean migrate(IProject project) {
		migrateProjectFile(project);
		if (migrateJ2EESettingsFile(project))
			return true;
		return false;
	}

	private void migrateProjectFile(IProject project) {
		try {
			J2EENature nature = J2EENature.getRegisteredRuntime(project);
			if (nature == null)
				return;
			int j2eeVersion = nature.getJ2EEVersion();

			if (j2eeVersion == J2EEVersionConstants.J2EE_1_3_ID) {

				if (project.hasNature(IEARNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.EAR13NATURE);

				} else if (project.hasNature(IEJBNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.EJB20NATURE);
					ProjectUtilities.addToBuildSpec(IDeprecatedConstants.LIBCOPYBUILDER, project);

				} else if (project.hasNature(IConnectorNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.CONNNATURE);
					ProjectUtilities.addToBuildSpec(IDeprecatedConstants.LIBCOPYBUILDER, project);
				} else if (project.hasNature(IApplicationClientNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.APPCLIENT13NATURE);
					ProjectUtilities.addToBuildSpec(IDeprecatedConstants.LIBCOPYBUILDER, project);
				}
			} else if (j2eeVersion == J2EEVersionConstants.J2EE_1_2_ID) {
				if (project.hasNature(IEARNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.EAR12NATURE);

				} else if (project.hasNature(IEJBNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.EJB11NATURE);
					ProjectUtilities.addToBuildSpec(IDeprecatedConstants.LIBCOPYBUILDER, project);

				} else if (project.hasNature(IConnectorNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.CONNNATURE);
					ProjectUtilities.addToBuildSpec(IDeprecatedConstants.LIBCOPYBUILDER, project);
				} else if (project.hasNature(IApplicationClientNatureConstants.NATURE_ID)) {
					WTPProjectUtilities.addOldNatureToProject(project, IDeprecatedConstants.APPCLIENT12NATURE);
					ProjectUtilities.addToBuildSpec(IDeprecatedConstants.LIBCOPYBUILDER, project);
				}
			}
		} catch (CoreException ce) {
			ce.printStackTrace();
		}
	}

	/**
	 * @param project
	 * @return
	 */
	private boolean migrateJ2EESettingsFile(IProject project) {
		try {
			J2EENature nature = J2EENature.getRegisteredRuntime(project);
			if (nature == null) return false;
			int j2eeVersion = nature.getJ2EEVersion();
			J2EESettings j2eeSettings = null;
			IFile j2eeSettingsFile = project.getFile(J2EESettings.J2EE_SETTINGS_FILE_NAME);
			if (!CompatibilityUtils.isPersistedTimestampCurrent(project, j2eeSettingsFile)) {
				if (j2eeSettingsFile == null && j2eeSettingsFile.exists())
					j2eeSettings = new J2EESettings(project);
				else
					j2eeSettings = new J2EESettings(project, nature);
				j2eeSettings.setVersion(J2EESettings.CURRENT_VERSION);
				j2eeSettings.setModuleVersion(getJ2EEVersion(project, j2eeVersion));
				j2eeSettings.write();
				CompatibilityUtils.updateTimestamp(project, j2eeSettingsFile);
				return true;
			}
		} catch (CoreException ce) {
			ce.printStackTrace();
		}
		return false;
	}

	/**
	 * @param version
	 * @return
	 */
	private int getJ2EEVersion(IProject project, int version) throws CoreException {
		if (project.hasNature(IWebNatureConstants.J2EE_NATURE_ID)) {
			return getWebVersion(version);
		} else if (project.hasNature(IEJBNatureConstants.NATURE_ID)) {
			return getEJBVersion(version);
		} else if (project.hasNature(IConnectorNatureConstants.NATURE_ID)) {
			return getJCAVersion(version);
		}
		return version;
	}

	/**
	 * @param version
	 * @return
	 */
	private int getWebVersion(int version) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param version
	 * @return
	 */
	private int getJCAVersion(int version) {
		switch (version) {
			case J2EEVersionConstants.J2EE_1_3_ID :
				return J2EEVersionConstants.JCA_1_0_ID;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				return J2EEVersionConstants.JCA_1_5_ID;
		}
	}

	/**
	 * @param version
	 * @return
	 */
	private int getEJBVersion(int version) {
		switch (version) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return J2EEVersionConstants.EJB_1_1_ID;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return J2EEVersionConstants.EJB_2_0_ID;
			case J2EEVersionConstants.EJB_2_0_ID :
				return J2EEVersionConstants.J2EE_1_4_ID;
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				return J2EEVersionConstants.EJB_2_1_ID;
		}
	}
}