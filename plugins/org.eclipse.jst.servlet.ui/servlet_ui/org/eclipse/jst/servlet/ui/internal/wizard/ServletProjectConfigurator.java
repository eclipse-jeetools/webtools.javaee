/*******************************************************************************
 * Copyright (c) 2016 Red Hat Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * - Mickael Istria (Red Hat Inc.) - initial implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetInstallDataModelProvider;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.ui.wizards.datatransfer.ProjectConfigurator;
import org.eclipse.ui.wizards.datatransfer.RecursiveFileFinder;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ServletProjectConfigurator implements ProjectConfigurator {

	public boolean canConfigure(IProject project, Set<IPath> ignoredDirectories, IProgressMonitor monitor) {
		try {
			RecursiveFileFinder finder = new RecursiveFileFinder(J2EEConstants.WEBAPP_DD_SHORT_NAME, ignoredDirectories);
			project.accept(finder);
			return finder.getFile() != null;
		} catch (CoreException ex) {
			ServletUIPlugin.log(ex);
			return false;
		}
	}

	@Deprecated
	public IWizard getConfigurationWizard() {
		return null;
	}

	public void configure(IProject project, Set<IPath> ignoredDirectories, IProgressMonitor monitor) {
		try {
			if (!ProjectFacetsManager.isProjectFacetDefined(project.getName())) {
				IFacetedProject facetedProject = ProjectFacetsManager.create(project, true, monitor);
				IProjectFacet JAVA_FACET = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_JAVA);
		
				ProjectScope ps = new ProjectScope(project);
				IEclipsePreferences JDPprojectNode = ps.getNode(JavaCore.PLUGIN_ID);
				String compilerCompliance = JDPprojectNode.get(JavaCore.COMPILER_COMPLIANCE, "1.7"); //$NON-NLS-1$
				if (!facetedProject.hasProjectFacet(JAVA_FACET)) {
					facetedProject.installProjectFacet(JAVA_FACET.getVersion(compilerCompliance), null, monitor);
				}
			}
			
			IFacetedProject facetedProject = ProjectFacetsManager.create(project, true, monitor);

			IProjectFacet WEB_FACET = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE);
			if (!facetedProject.hasProjectFacet(WEB_FACET)) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				RecursiveFileFinder finder = new RecursiveFileFinder(J2EEConstants.WEBAPP_DD_SHORT_NAME, ignoredDirectories);
				project.accept(finder);
				InputStream webXmlStream = finder.getFile().getContents();
				Document doc = dBuilder.parse(webXmlStream);
				webXmlStream.close();
	
				IDataModel aFacetInstallDataModel = DataModelFactory.createDataModel(new WebFacetInstallDataModelProvider());
				aFacetInstallDataModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, false);
				String version = ((Element)doc.getElementsByTagName(J2EEConstants.WEBAPP_DOCTYPE).item(0)).getAttribute("version"); //$NON-NLS-1$
				if (version.length() < 1) {
					// TODO decide this according to JRE version : Java6 => servlet 2.5; Java 7 => servlet 3.1
					version = J2EEVersionConstants.VERSION_3_1_TEXT;
				}
				facetedProject.installProjectFacet(WEB_FACET.getVersion(version), aFacetInstallDataModel, monitor);
			}
		} catch (Exception ex) {
			ServletUIPlugin.log(ex);
		}
	}

	public boolean shouldBeAnEclipseProject(IContainer container, IProgressMonitor monitor) {
		return false; // TODO: can we make sure a dir is a JEE project?
	}

	public Set<IFolder> getDirectoriesToIgnore(IProject project, IProgressMonitor monitor) {
		return null;
	}

	public Set<File> findConfigurableLocations(File root, IProgressMonitor monitor) {
		return Collections.emptySet();
	}
}
