/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.exception.OpenFailureException;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.webapplication.WebApp;

import com.ibm.wtp.common.logger.proxy.Logger;

public class EJBLinkMigrator {
	protected EARNatureRuntime currentEARNature;
	protected Map earNaturesToEARFiles = new HashMap();
	protected static final char QUALIFIER_CHAR = '#';
	private J2EEMigrationConfig currentConfig;

	/**
	 * Constructor for EJBLinkMigrator.
	 */
	public EJBLinkMigrator() {
		super();
	}

	public EJBLinkMigrator(J2EEMigrationConfig config) {
		currentConfig = config;
	}

	public void migrate(EARNatureRuntime earNature) {
		if (isConnector())
			return;
		initEAR(earNature);
		migrate();
	}

	protected boolean isConnector() {
		return J2EEMigrationHelper.getDeploymentDescriptorType(currentConfig) == XMLResource.RAR_TYPE;
	}

	protected void migrate() {
		EARFile ear = getCurrentEARFile();
		if (ear == null)
			return;
		Module m = currentEARNature.getModule(currentConfig.getTargetProject());
		if (m == null)
			return;
		String uri = m.getUri();
		migrate(uri, ear);
	}

	protected void migrate(String uri, EARFile ear) {
		switch (J2EEMigrationHelper.getDeploymentDescriptorType(currentConfig)) {
			case XMLResource.EJB_TYPE :
				migrateEJBModule(uri, ear);
				break;
			case XMLResource.WEB_APP_TYPE :
				migrateWebModule(uri, ear);
				break;
			case XMLResource.APP_CLIENT_TYPE :
				migrateAppClientModule(uri, ear);
				break;
			default :
				return;
		}
	}

	protected void migrateAppClientModule(String uri, EARFile ear) {
		IProject project = currentConfig.getTargetProject();
		ApplicationClientNatureRuntime nature = ApplicationClientNatureRuntime.getRuntime(project);
		ApplicationClient client = nature.getApplicationClient();
		if (client == null)
			return;
		migrateRefs(uri, client.getEjbReferences(), ear);
	}

	protected void migrateWebModule(String uri, EARFile ear) {
		IProject project = currentConfig.getTargetProject();
		J2EEWebNatureRuntime nature = J2EEWebNatureRuntime.getRuntime(project);
		WebApp webapp = nature.getWebApp();
		if (webapp == null)
			return;
		migrateRefs(uri, webapp.getEjbRefs(), ear);
	}

	protected void migrateEJBModule(String uri, EARFile ear) {
		IProject project = currentConfig.getTargetProject();
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(project);
		EJBJar ejbJar = nature.getEJBJar();
		if (ejbJar == null)
			return;
		List beans = ejbJar.getEnterpriseBeans();
		for (int i = 0; i < beans.size(); i++) {
			EnterpriseBean owner = (EnterpriseBean) beans.get(i);
			migrateRefs(uri, owner.getEjbRefs(), ear);
		}
	}

	protected void migrateRefs(String uri, List refs, EARFile ear) {
		for (int i = 0; i < refs.size(); i++) {
			EjbRef ref = (EjbRef) refs.get(i);
			migrateRef(uri, ref, ear);
		}
	}

	protected void migrateRef(String uri, EjbRef ref, EARFile ear) {
		String link = ref.getLink();
		if (link == null || isQualifiedLink(link))
			return;

		EnterpriseBean bean = ear.getEnterpiseBeanFromRef(ref, uri);
		if (bean == null)
			return;
		String referencedURI = getEJBJarURI(bean, ear);
		if (referencedURI != null && !referencedURI.equals(uri))
			ref.setLink(computeRelativeText(uri, referencedURI, bean));
	}

	protected String getEJBJarURI(EnterpriseBean bean, EARFile ear) {
		List jarFiles = ear.getEJBJarFiles();
		for (int i = 0; i < jarFiles.size(); i++) {
			EJBJarFile jarFile = (EJBJarFile) jarFiles.get(i);
			if (jarFile.getDeploymentDescriptor() == bean.getEjbJar())
				return jarFile.getURI();
		}
		return null;
	}


	protected String computeRelativeText(String referencingURI, String referencedURI, EnterpriseBean bean) {
		return J2EEProjectUtilities.computeRelativeText(referencingURI, referencedURI, bean);
	}



	protected boolean isQualifiedLink(String ejbLink) {
		return ejbLink != null && ejbLink.indexOf(QUALIFIER_CHAR) != -1;
	}

	protected void initEAR(EARNatureRuntime anEARNature) {
		if (anEARNature == null) {
			EARNatureRuntime[] earNatures = J2EEProjectUtilities.getReferencingEARProjects(getCurrentJ2EENature().getProject());
			if (earNatures.length > 0)
				anEARNature = earNatures[0];
		}
		currentEARNature = anEARNature;
	}

	protected J2EENature getCurrentJ2EENature() {
		return J2EEMigrationHelper.getCurrentProjectNature(currentConfig);
	}

	protected EARFile getCurrentEARFile() {
		if (currentEARNature == null)
			return null;

		EARFile earFile = (EARFile) earNaturesToEARFiles.get(currentEARNature);
		if (earFile == null)
			earFile = initCurrentEARFile();
		return earFile;
	}

	protected EARFile initCurrentEARFile() {
		EARFile earFile = null;
		try {
			earFile = currentEARNature.asEARFile(false, false);
		} catch (OpenFailureException ex) {
			Logger.getLogger().logError(ex);
			return null;
		}
		earNaturesToEARFiles.put(currentEARNature, earFile);
		return earFile;
	}

	public void release() {
		Iterator iter = earNaturesToEARFiles.values().iterator();
		while (iter.hasNext()) {
			((EARFile) iter.next()).close();
		}
		earNaturesToEARFiles.clear();
	}

	/**
	 * @return Returns the currentConfig.
	 */
	public J2EEMigrationConfig getCurrentConfig() {
		return currentConfig;
	}

	/**
	 * @param currentConfig
	 *            The currentConfig to set.
	 */
	public void setCurrentConfig(J2EEMigrationConfig currentConfig) {
		this.currentConfig = currentConfig;
	}
}