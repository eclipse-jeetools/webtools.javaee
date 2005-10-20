/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.classpath.ClasspathHelper;

public class JavaFacetInstalOperation extends AbstractDataModelOperation
implements IJavaFacetInstallDataModelProperties{

	public JavaFacetInstalOperation() {
		super();
	}

	public JavaFacetInstalOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
        if( monitor != null )
        {
            monitor.beginTask( "", 1 ); //$NON-NLS-1$
        }
        
        try
        {
        	IProject project = ProjectUtilities.getProject(model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME));
			IProjectFacetVersion fv = (IProjectFacetVersion) model.getProperty(IFacetDataModelProperties.FACET_VERSION);

            // Create the source and the output directories.
            
            final IWorkspace ws = ResourcesPlugin.getWorkspace();
            
            final IPath pjpath = project.getFullPath();
            String srcFolderName = model.getStringProperty(IJavaFacetInstallDataModelProperties.SOURC_FOLDER_NAME);
            //final IPath srcdir = pjpath.append( "src" );
            final IPath srcdir = pjpath.append( srcFolderName );
            
            final IPath outdir = pjpath.append( "build/classes" ); //$NON-NLS-1$
            
            ws.getRoot().getFolder( srcdir ).getLocation().toFile().mkdirs();
            ws.getRoot().getFolder( outdir ).getLocation().toFile().mkdirs();
            project.refreshLocal( IResource.DEPTH_INFINITE, null );

            // Add the java nature. This will automatically add the builder.
            
            final IProjectDescription desc = project.getDescription();
            final String[] current = desc.getNatureIds();
            final String[] replacement = new String[ current.length + 1 ];
            System.arraycopy( current, 0, replacement, 0, current.length );
            replacement[ current.length ] = JavaCore.NATURE_ID;
            desc.setNatureIds( replacement );
            project.setDescription( desc, null );

            // Set up the sourcepath and the output directory.
            
            final IJavaProject jproj = JavaCore.create( project );
            final IClasspathEntry[] cp = { JavaCore.newSourceEntry( srcdir ) };
            
            jproj.setRawClasspath( cp, outdir, null );
            jproj.save( null, true );
            
            // Setup the classpath. 
            
            ClasspathHelper.removeClasspathEntries( project, fv );
            
            if( ! ClasspathHelper.addClasspathEntries( project, fv ) )
            {
                // TODO: Support the no runtime case.
                // ClasspathHelper.addClasspathEntries( project, fv, <something> );
            }
            
            // Set the compiler comliance level for the project. Ignore whether
            // this might already be set so at the workspace level in case
            // workspace settings change later or the project is included in a
            // different workspace.
            
            JavaFacetUtils.setCompilerLevel( project, fv );
            
            if( monitor != null )
            {
                monitor.worked( 1 );
            }
        }catch (CoreException e) {
			throw new ExecutionException(e.getMessage(), e);
		}
        finally
        {
            if( monitor != null )
            {
                monitor.done();
            }
        }

		return OK_STATUS;
	}

}
