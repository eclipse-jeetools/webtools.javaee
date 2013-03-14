/*******************************************************************************
 * Copyright (c) 2005, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.project.facet;



import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.XMLWriter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainer;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class EjbFacetInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {

		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {
			IDataModel model = (IDataModel) config;

			final IJavaProject jproj = JavaCore.create(project);

			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Create the directory structure.
			final IWorkspace ws = ResourcesPlugin.getWorkspace();
			final IPath pjpath = project.getFullPath();

			// Setup the flexible project structure.
			final IVirtualComponent c = ComponentCore.createComponent(project, false);
			c.create(0, null);
			setOutputFolder(model,c);
			final IVirtualFolder ejbroot = c.getRootFolder();

			IFolder ejbFolder = null;
			String configFolder = null;

			configFolder = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER);
			Path configFolderPath = new Path(configFolder);
			ejbroot.createLink(configFolderPath, 0, null);
			J2EEModuleVirtualComponent.setDefaultDeploymentDescriptorFolder(ejbroot, configFolderPath, null);

			IPath ejbFolderpath = pjpath.append(configFolder);

			ejbFolder = ws.getRoot().getFolder(ejbFolderpath);

			IFile vf = ejbFolder.getFile(new Path(J2EEConstants.MANIFEST_URI));
			if (vf == null || !vf.exists()) {
				try {
					createManifest(project, ejbFolder, monitor);
				} catch (InvocationTargetException e) {
					EjbPlugin.logError(e);
				} catch (InterruptedException e) {
					EjbPlugin.logError(e);
				}
			}
			
			if( fv == IJ2EEFacetConstants.EJB_32 || fv == IJ2EEFacetConstants.EJB_31 || fv == IJ2EEFacetConstants.EJB_30)
            {
                if(model.getBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD)){
                    // Create the deployment descriptor (ejb-jar.xml) if one doesn't exist
                    IFile ejbJarXmlFile = ejbFolder.getFile(J2EEConstants.EJBJAR_DD_URI);
                    if (!ejbJarXmlFile.exists()) {
                        try {
                        	String ejbJarXmlContents = null;
                        	if(fv == IJ2EEFacetConstants.EJB_32) {
	                            ejbJarXmlContents = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ejb-jar version=\"3.2\" xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/ejb-jar_3_2.xsd\">\n  <display-name>" + XMLWriter.getEscaped(project.getName()) +" </display-name> \n </ejb-jar>"; //$NON-NLS-1$ //$NON-NLS-2$
                        	} else if(fv == IJ2EEFacetConstants.EJB_31) {
	                            ejbJarXmlContents = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ejb-jar version=\"3.1\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_1.xsd\">\n  <display-name>" + XMLWriter.getEscaped(project.getName()) +" </display-name> \n </ejb-jar>"; //$NON-NLS-1$ //$NON-NLS-2$
                        	} else {
                        		ejbJarXmlContents = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ejb-jar version=\"3.0\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/ejb-jar_3_0.xsd\">\n  <display-name>" + XMLWriter.getEscaped(project.getName()) +" </display-name> \n </ejb-jar>"; //$NON-NLS-1$ //$NON-NLS-2$
                        	}
                        	ejbJarXmlFile.create(new ByteArrayInputStream(ejbJarXmlContents.getBytes("UTF-8")), true, monitor); //$NON-NLS-1$
                        } catch (UnsupportedEncodingException e) {
                        	EjbPlugin.logError(e);
                        }
                    }
                }
            }
            else
            {
                if (!ejbFolder.getFile(J2EEConstants.EJBJAR_DD_URI).exists()) {
                    String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);
                    int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
                    EJBArtifactEdit.createDeploymentDescriptor(project, nVer);
                }
            }

			// add source folder maps
			final IClasspathEntry[] cp = jproj.getRawClasspath();
			for (int i = 0; i < cp.length; i++) {
				final IClasspathEntry cpe = cp[i];
				if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					ejbroot.createLink(cpe.getPath().removeFirstSegments(1), 0, null);
				}
			}

			// Setup the classpath.
			ClasspathHelper.removeClasspathEntries(project, fv);

			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}
			
			if( model.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.INSTALL_EAR_LIBRARY)){
				final IPath earLibContainer = new Path(J2EEComponentClasspathContainer.CONTAINER_ID);
				addToClasspath(jproj, JavaCore.newContainerEntry(earLibContainer));
			}
			
			try {
				((IDataModelOperation) model.getProperty(FacetDataModelProvider.NOTIFICATION_OPERATION)).execute(monitor, null);
			} catch (ExecutionException e) {
				EjbPlugin.logError(e);
			}
			
			if (monitor != null) {
				monitor.worked(1);
			}
		}

		finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}
	
}
