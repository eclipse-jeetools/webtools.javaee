/*******************************************************************************
 * Copyright (c) 2010, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.internal.resources.XMLWriter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ManifestFileCreationAction;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class WebFragmentFacetInstallDelegate implements IDelegate {

	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {
		if (monitor != null)
			monitor.beginTask("", 1); //$NON-NLS-1$
		try {
			IDataModel model = (IDataModel) config;
			WtpUtils.addNatures(project);
			final IVirtualComponent c = ComponentCore.createComponent(project, false);
			c.create(0, null);
			final IVirtualFolder jsrc = c.getRootFolder();
			final IJavaProject jproj = JavaCore.create(project);
			final IClasspathEntry[] cp = jproj.getRawClasspath();
			IPath firstPath = null;
			for (int i = 0; i < cp.length; i++) {
				final IClasspathEntry cpe = cp[i];

				if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					IPath path = cpe.getPath().removeFirstSegments(1);
					if (path.isEmpty()) {
						path = new Path("/"); //$NON-NLS-1$
					}
					jsrc.createLink(path, 0, null);
					if (firstPath == null){
						firstPath = path;
					}
				}
			}
			J2EEModuleVirtualComponent.setDefaultDeploymentDescriptorFolder(jsrc, firstPath, null);
			final IVirtualFolder root = c.getRootFolder();
			IContainer container = null;
			if (root.getProjectRelativePath().segmentCount() == 0) {
				container = project;
			} else {
				container = root.getUnderlyingFolder();
			}
			try {
				if (container != null) {
					createWebFragmentFile(project, fv, container, monitor);
					createManifest (project, container, monitor);
				}
			} catch (InvocationTargetException e) {
				J2EEPlugin.logError(e);
			} catch (InterruptedException e) {
				J2EEPlugin.logError(e);
			}
			ClasspathHelper.removeClasspathEntries(project, fv);
			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}
			try {
				((IDataModelOperation) model.getProperty(FacetDataModelProvider.NOTIFICATION_OPERATION)).execute(monitor, null);
			} catch (ExecutionException e) {
				WebPlugin.logError(e);
			}
		} finally {
			if (monitor != null)
				monitor.done();
		}
	}
	
	protected void createWebFragmentFile(IProject project, IProjectFacetVersion fv, IContainer aFolder, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
    	
        IFile file = aFolder.getFile(new Path(J2EEConstants.WEBFRAGMENT_DD_URI));
        if (file.exists()) return;
        try {
        	createWebFragmentFile(file, project, fv);
        } catch (CoreException e) {
            J2EEPlugin.logError(e);
        } catch (IOException e) {
            J2EEPlugin.logError(e);
        }
    }
	
	public static void createWebFragmentFile(IFile file, IProject project, IProjectFacetVersion fv) throws CoreException, IOException {
		try {
			WorkbenchByteArrayOutputStream out = new WorkbenchByteArrayOutputStream(file);
			String projectNamewithoutSpecialChars = getNameWithoutSpecialCharacters(project.getName());
			
			final String webFragXmlContents;
			
			if( fv == IJ2EEFacetConstants.WEBFRAGMENT_31 )
			{
				webFragXmlContents = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<web-fragment id=\"WebFragment_ID\" version=\"3.1\" xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-fragment_3_1.xsd\">\n<display-name>" + XMLWriter.getEscaped(project.getName())+ "</display-name> \n <name>"+ XMLWriter.getEscaped(projectNamewithoutSpecialChars) +  "</name> \n </web-fragment>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			else
			{
				webFragXmlContents = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<web-fragment id=\"WebFragment_ID\" version=\"3.0\" xmlns=\"http://java.sun.com/xml/ns/javaee\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-fragment_3_0.xsd\">\n<display-name>" + XMLWriter.getEscaped(project.getName())+ "</display-name> \n <name>"+ XMLWriter.getEscaped(projectNamewithoutSpecialChars) +  "</name> \n </web-fragment>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			
			out.write(webFragXmlContents.getBytes("UTF-8")); //$NON-NLS-1$
			out.close();
		} catch (IOException ioe) {
			throw ioe;
		}
	}
	
	
	
    protected void createManifest(IProject project, IContainer aFolder, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
    	
        IFile file = aFolder.getFile(new Path(J2EEConstants.MANIFEST_URI));
        if (file.exists()) return;
        try {
            ManifestFileCreationAction.createManifestFile(file, project);
        } catch (CoreException e) {
            J2EEPlugin.logError(e);
        } catch (IOException e) {
            J2EEPlugin.logError(e);
        }
    }
    
    private static String getNameWithoutSpecialCharacters(String projectName){
    	String result = projectName;
    	Pattern p = Pattern.compile("($|_|\\p{L})(\\p{L}|\\p{Nd}|_|$)*"); //$NON-NLS-1$
    	 for(int curLength = 1; curLength <= result.length(); curLength++){
    		 if(p.matcher(result.substring(0,curLength)).matches()== false){
    			 result = result.replace(result.charAt(curLength-1 < 0 ? curLength : curLength-1), '_');
    		 }
   	 }
    	 return result;    	
    }
}