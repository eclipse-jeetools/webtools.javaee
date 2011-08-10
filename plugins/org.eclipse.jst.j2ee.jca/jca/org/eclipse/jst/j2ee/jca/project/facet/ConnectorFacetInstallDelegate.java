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
package org.eclipse.jst.j2ee.jca.project.facet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainer;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.jca.Connector;
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

public class ConnectorFacetInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public static final String CONNECTOR_XML_TEMPLATE_10		= "rartp10.xml";								//$NON-NLS-1$
	public static final String CONNECTOR_XML_TEMPLATE_15		= "rartp15.xml"; //$NON-NLS-1$
	public static final String CONNECTOR_XML_TEMPLATE_16		= "rartp16.xml"; //$NON-NLS-1$
	
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
			setOutputFolder(model, c);

			final IVirtualFolder root = c.getRootFolder();
			IFolder sourceFolder = null;
			String configFolder = null;
			configFolder = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER);
			Path configFolderPath = new Path("/" + configFolder); //$NON-NLS-1$
			root.createLink(configFolderPath, 0, null);
			J2EEModuleVirtualComponent.setDefaultDeploymentDescriptorFolder(root, configFolderPath, null);
			String configFolderName = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER);
			IPath configFolderpath = pjpath.append(configFolderName);
			sourceFolder = ws.getRoot().getFolder(configFolderpath);

			if( fv == IJ2EEFacetConstants.JCA_16)
			{
		        if(model.getBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD)){
		            // Create the deployment descriptor (ra.xml) if one doesn't exist
		            IFile rarFile = sourceFolder.getFile(new Path(J2EEConstants.RAR_DD_URI));
		            if (!rarFile.exists()) {
		                try {
		                    if(!rarFile.getParent().exists()
		                            && (rarFile.getParent().getType() ==  IResource.FOLDER)){
		                        ((IFolder)rarFile.getParent()).create(true, true, monitor);
		                    }
		                    InputStream in = getClass().getResourceAsStream(CONNECTOR_XML_TEMPLATE_16);
		                    rarFile.create(in, true, monitor);
		                    populateDefaultContent(project, fv);
		                } catch (CoreException e) {
		                	J2EEPlugin.logError(e);
		                }
		            }
		        }
			}
			else if (!sourceFolder.getFile(J2EEConstants.RAR_DD_URI).exists()) {
				String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);
				int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
				
				IFile aFile = sourceFolder.getFile(new Path(J2EEConstants.RAR_DD_URI));
				OutputStream out = new WorkbenchByteArrayOutputStream(aFile);
				
				String template = null;
				if(nVer == J2EEVersionConstants.JCA_1_0_ID) {
					template = CONNECTOR_XML_TEMPLATE_10;
				}
				else if (nVer == J2EEVersionConstants.JCA_1_5_ID) {
					template = CONNECTOR_XML_TEMPLATE_15;
				}
//				JCA 1.6 handled separately
//				else {
//					template = CONNECTOR_XML_TEMPLATE_16;
//				}

				InputStream in = getClass().getResourceAsStream(template);
				if (in != null) {
					try {
						ArchiveUtil.copy(in, out);
					} catch (IOException ioe) {
						Logger.getLogger().logError(ioe);
					} finally{
						try{
							out.close();
						}catch (IOException ioe) {
							Logger.getLogger().logError(ioe);
						} finally{
							try{
								in.close();
							}catch (IOException ioe) {
								Logger.getLogger().logError(ioe);
							}
						}
					}
					
					ConnectorArtifactEdit edit = null;
					try{
						edit = new ConnectorArtifactEdit(project, false, true);
						org.eclipse.jst.j2ee.jca.Connector connector = edit.getConnector();
						connector.setDisplayName(project.getName());
						// TODO: investigate setting the version and the impact on adopters
						//connector.setVersion(ver);
						edit.saveIfNecessary(new SubProgressMonitor(monitor,1));
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

			if( model.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.INSTALL_EAR_LIBRARY)){
				final IPath earLibContainer = new Path(J2EEComponentClasspathContainer.CONTAINER_ID);
				addToClasspath(jproj, JavaCore.newContainerEntry(earLibContainer));
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

	private void populateDefaultContent(final IProject project,
			final IProjectFacetVersion fv) {
		final IModelProvider provider = ModelProviderManager.getModelProvider(project, fv);
		   Runnable runnable = new Runnable(){
   
		       public void run() {
		           Connector connector = (Connector) provider.getModelObject();
		           
		           // Add the display-name tag
		           DisplayName displayName = JavaeeFactory.eINSTANCE.createDisplayName();
		           displayName.setValue(project.getName());
		           connector.getDisplayNames().add(displayName);
		       }
		   };
		   provider.modify(runnable, null);
	}
}
