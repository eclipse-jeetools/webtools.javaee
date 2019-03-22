/******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    rfrost@bea.com - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.classpathdep.ui;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jst.j2ee.classpathdep.UpdateClasspathAttributeUtil;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyValidator;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.wst.validation.internal.ConfigurationConstants;

/**
 * IMarkerResolutionGenerator for classpath dependency validator problem markers. 
 */
public final class ClasspathDependencyValidatorMarkerResolutions implements IMarkerResolutionGenerator {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IMarkerResolutionGenerator#getResolutions(org.eclipse.core.resources.IMarker)
	 */
	public IMarkerResolution[] getResolutions(final IMarker marker) {
		// generate resolutions based on type of validation problem marker
		String messageId = null;
		String cpEntryPath = null;
		try {
			messageId = (String) marker.getAttribute(ConfigurationConstants.VALIDATION_MARKER_MESSAGEID);
			cpEntryPath = (String) marker.getAttribute(ConfigurationConstants.VALIDATION_MARKER_GROUP);
		} catch (CoreException ce) {
			Logger.getLogger(J2EEUIPlugin.PLUGIN_ID).logError(ce);
			return new IMarkerResolution[0];
		}
		
		if (messageId == null || cpEntryPath == null || cpEntryPath.length() == 0) {
			return new IMarkerResolution[0];
		}
		
		if (ClasspathDependencyValidator.AppClientProject.equals(messageId)) {
			// can apply to multiple cp entries so not currently supporting a quick fix...
		} else if (ClasspathDependencyValidator.DuplicateClassFolderEntry.equals(messageId)) {
			// quick fix removes the dependency
			return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, false) };			
		} else if (ClasspathDependencyValidator.DuplicateArchiveName.equals(messageId)) {
			// quick fix removes the dependency
			return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, false) };			
		} else if (ClasspathDependencyValidator.FilteredContainer.equals(messageId)) {
			// quick fix removes the dependency
			return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, false) };			
		} else if (ClasspathDependencyValidator.InvalidNonWebRuntimePath.equals(messageId)) {
			// quick fix removes the dependency
			return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, false) };			
		} else if (ClasspathDependencyValidator.InvalidWebRuntimePath.equals(messageId)) {
			// quick fix removes the dependency
			return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, false) };			
		} else if (ClasspathDependencyValidator.NonTaggedExportedClasses.equals(messageId)) {
			IResource resource = marker.getResource();
			if(null != resource && resource.getType() == IResource.PROJECT && !JavaEEProjectUtilities.isApplicationClientProject((IProject)resource)){
				// quick fix adds the dependency, and one to remove it
				return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, true),
						new AddClasspathNonDependencyAttributeResolution(cpEntryPath)};	
			}
			// quick fix removes the dependency
			return new IMarkerResolution[] { new AddClasspathNonDependencyAttributeResolution(cpEntryPath) };
		} else if (ClasspathDependencyValidator.ProjectClasspathEntry.equals(messageId)) {
			// quick fix removes the dependency
			return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, false) };	
		} else if (ClasspathDependencyValidator.RootMappingNonEARWARRef.equals(messageId)) {
			// can apply to multiple cp entries so not currently supporting a quick fix...
		} else if (ClasspathDependencyValidator.SourceEntry.equals(messageId)) {
			// quick fix removes the dependency
			return new IMarkerResolution[] { new UpdateClasspathDependencyAttributeResolution(cpEntryPath, false) };	
		}
		
		return new IMarkerResolution[0];
    }
	
    private static IClasspathEntry getClasspathEntryForMarker(final IMarker marker, final String cpEntryPath) throws CoreException {
	    final IProject proj = marker.getResource().getProject();
		if (proj != null && proj.hasNature(JavaCore.NATURE_ID)) {
			final IJavaProject jProject = JavaCore.create(proj);
			if (cpEntryPath != null) {
				IClasspathEntry[] rawCp = jProject.getRawClasspath();
				for (int i = 0; i < rawCp.length; i++) {
					if (rawCp[i].getPath().toString().equals(cpEntryPath)) {
						return rawCp[i];
					}
				}
			}
    	}
    	return null;
    }
	
	/* Resolution that add/removes the classpath dependency attribute */
    private static final class UpdateClasspathDependencyAttributeResolution implements IMarkerResolution {
        private final boolean add;
        private final String cpEntryPath;
        public UpdateClasspathDependencyAttributeResolution(final String cpEntryPath, final boolean add) {
            this.add = add;
            this.cpEntryPath = cpEntryPath;
        }
        
        public String getLabel() {
        	if (add) {
        		return Resources.addClasspathDependencyAttribute;	
        	} 
        	return Resources.removeClasspathDependencyAttribute;
        }

        public void run(final IMarker marker) {
    	    final IProject proj = marker.getResource().getProject();
	    	try {
	    		final IClasspathEntry cpEntry = getClasspathEntryForMarker(marker, cpEntryPath);
	    		if (add) {
	    			UpdateClasspathAttributeUtil.addDependencyAttribute(null, proj.getName(), cpEntry);
	    		} else {
	    			UpdateClasspathAttributeUtil.removeDependencyAttribute(null, proj.getName(), cpEntry);	
	    		}
	    	} catch (CoreException ce){
                ErrorDialog.openError(null, Resources.errorDialogTitle,
                		Resources.errorDialogMessage,
                		ce.getStatus());
	    	} catch (ExecutionException ee){
	    		ErrorDialog.openError(null, Resources.errorDialogTitle,
	    				Resources.errorDialogMessage,
	    				new Status(IStatus.ERROR, J2EEUIPlugin.PLUGIN_ID, 0, ee.getLocalizedMessage(), ee)); 
	    	}
        }        
    }
    
	/* Resolution that adds the classpath nondependency attribute */
    private static final class AddClasspathNonDependencyAttributeResolution implements IMarkerResolution {
        private final String cpEntryPath;
        public AddClasspathNonDependencyAttributeResolution(final String cpEntryPath) {
            this.cpEntryPath = cpEntryPath;
        }
        
        public String getLabel() {
        	return Resources.addClasspathNonDependencyAttribute;	
        }

        public void run(final IMarker marker) {
    	    final IProject proj = marker.getResource().getProject();
	    	try {
	    		final IClasspathEntry cpEntry = getClasspathEntryForMarker(marker, cpEntryPath);
	    		UpdateClasspathAttributeUtil.addNonDependencyAttribute(null, proj.getName(), cpEntry);
	    	} catch (CoreException ce){
                ErrorDialog.openError(null, Resources.errorDialogTitle,
                		Resources.errorDialogMessage,
                		ce.getStatus());
	    	} catch (ExecutionException ee){
	    		ErrorDialog.openError(null, Resources.errorDialogTitle,
	    				Resources.errorDialogMessage,
	    				new Status(IStatus.ERROR, J2EEUIPlugin.PLUGIN_ID, 0, ee.getLocalizedMessage(), ee)); 
	    	}
        }        
    }
    
    private static final class Resources extends NLS {
        public static String removeClasspathDependencyAttribute;
        public static String addClasspathDependencyAttribute;
        public static String addClasspathNonDependencyAttribute;
        public static String errorDialogTitle;
        public static String errorDialogMessage;
        
        static
        {
            initializeMessages( ClasspathDependencyValidatorMarkerResolutions.class.getName(), 
                                Resources.class );
        }
    }
    
    
}
