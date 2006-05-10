/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.project.facet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;

public class ConnectorFacetInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public static final String CONNECTOR_XML_TEMPLATE_10		= "rartp10.xml";								//$NON-NLS-1$
	public static final String CONNECTOR_XML_TEMPLATE_15		= "rartp15.xml"; //$NON-NLS-1$
	
	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {
			IDataModel model = (IDataModel) config;

			final IJavaProject jproj = JavaCore.create(project);

			addJ2EEModuleClasspathContainer(jproj);
			
			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Create the directory structure.

			final IWorkspace ws = ResourcesPlugin.getWorkspace();
			final IPath pjpath = project.getFullPath();

			// Setup the flexible project structure.

			final IVirtualComponent c = ComponentCore.createComponent(project);

			c.create(0, null);
			c.setMetaProperty("java-output-path", ProductManager.getProperty(IProductConstants.OUTPUT_FOLDER)); //$NON-NLS-1$

			final IVirtualFolder root = c.getRootFolder();
			IFolder sourceFolder = null;
			String configFolder = null;
			configFolder = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER);
			root.createLink(new Path("/" + configFolder), 0, null); //$NON-NLS-1$
			String configFolderName = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER);
			IPath configFolderpath = pjpath.append(configFolderName);
			sourceFolder = ws.getRoot().getFolder(configFolderpath);

			if (!sourceFolder.getFile(J2EEConstants.RAR_DD_URI).exists()) {
				String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);
				int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
				
				IFile aFile = sourceFolder.getFile(new Path(J2EEConstants.RAR_DD_URI));
				OutputStream out = new WorkbenchByteArrayOutputStream(aFile);
				String template = nVer == J2EEVersionConstants.JCA_1_0_ID
						? CONNECTOR_XML_TEMPLATE_10
						: CONNECTOR_XML_TEMPLATE_15;
				InputStream in = getClass().getResourceAsStream(template);
				if (in != null & out != null) {
					try {
						ArchiveUtil.copy(in, out);
					} catch (IOException ioe) {
						Logger.getLogger().logError(ioe);
					} finally{
						try{
							if(null != out){
								out.close();
							} if(null != in){
								in.close();
							}
						}catch (IOException ioe) {
							Logger.getLogger().logError(ioe);
						} 
					}
					
					ConnectorArtifactEdit edit = new ConnectorArtifactEdit(project, false, true);
					try{
						if( edit != null ){
							Connector connector = edit.getConnector();
							connector.setDisplayName(project.getName());
							edit.saveIfNecessary(new NullProgressMonitor());
						}
					}finally{
						if( edit != null ){
							edit.dispose();
						}
					}
				} else {
					//without a template
					ConnectorArtifactEdit.createDeploymentDescriptor(project, nVer);
				} // if				
			}
			
			// add source folder maps
			final IClasspathEntry[] cp = jproj.getRawClasspath();
			for (int i = 0; i < cp.length; i++) {
				final IClasspathEntry cpe = cp[i];
				if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					root.createLink(cpe.getPath().removeFirstSegments(1), 0, null);
				}
			}
			
			IVirtualFile vf = c.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));
			IFile manifestmf = vf.getUnderlyingFile();
			if (manifestmf == null || !manifestmf.exists()) {
				try {
					createManifest(project, c.getRootFolder().getUnderlyingFolder(), monitor);
				} catch (InvocationTargetException e) {
					Logger.getLogger().logError(e);
				} catch (InterruptedException e) {
					Logger.getLogger().logError(e);
				}
			}

			// Setup the classpath.
			ClasspathHelper.removeClasspathEntries(project, fv);

			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}



			try {
				((IDataModelOperation) model.getProperty(FacetDataModelProvider.NOTIFICATION_OPERATION)).execute(monitor, null);
			} catch (ExecutionException e) {
				Logger.getLogger().logError(e);
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
