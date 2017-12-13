/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    BEA Systems, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.dependency.tests.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.refactor.listeners.J2EEElementChangedListener;
import org.eclipse.jst.j2ee.refactor.listeners.ProjectRefactoringListener;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.validation.internal.ConfigurationManager;
import org.eclipse.wst.validation.internal.GlobalConfiguration;
import org.eclipse.wst.validation.internal.ValidatorMetaData;

/**
 * Various utility methods.
 */
public class DependencyUtil {
	
	public static final IWorkspace ws = ResourcesPlugin.getWorkspace();
	
	/**
	 * Retrieves a unique name.
	 * @param prefix
	 * @return
	 */
	public static String getUniqueName(final String prefix) {
		return prefix + System.currentTimeMillis();
	}
	
	public static void waitForValidationJobs() {
		// Wait for all validation jobs to end
		final IJobManager jobMgr = Platform.getJobManager();
		IProject[] projects = ProjectUtility.getAllProjects();
		for (int i = 0; i < projects.length; i++) {
			final IProject p = projects[i];
			waitForValidationJobs(p);
		}
	}
	
	public static void waitForValidationJobs(final IProject project) {
		// Wait for all validation jobs to end
		final IJobManager jobMgr = Platform.getJobManager();
		final String family = project.getName() + OperationTestCase.VALIDATOR_JOB_FAMILY;
		waitForJobs(family);
	}
	
	public static void waitForProjectRefactoringJobs() {
		waitForJobs(ProjectRefactoringListener.PROJECT_REFACTORING_JOB_FAMILY);
	}
		
	public static void waitForComponentRefactoringJobs() {
		waitForJobs(J2EEElementChangedListener.PROJECT_COMPONENT_UPDATE_JOB_FAMILY);
	}
	
	/**
	 * Waits for jobs in the specified family
	 * @param family
	 */
	
	public static void waitForJobs(final String family) {
		waitForJobs((Object)family);
	}
	
	/**
	 * Waits for jobs in the specified family
	 * @param family
	 */
	public static void waitForJobs(final Object family) {
		final IJobManager jobMgr = Platform.getJobManager();
        for (int i = 0; i < 1000; i++) {
			final Job[] jobs = jobMgr.find(family);
			if (jobs.length > 0) {
				try {
					jobMgr.join(family, null);
				} catch (InterruptedException ie) {
					// make one last check for jobs before exiting
					i = 999;
					continue;
				}
				break;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException ie) {
			}
		}
	}

	 /**
     * Disables all validators (if this is not done, getting error deleting/renaming projects
     * because one of the validators is holding onto web.xml)
     */
    public static void disableValidation() throws InvocationTargetException {
    	disableValidation(true);
    }
    
    public static void enableValidation() throws InvocationTargetException {
    	disableValidation(false);
    }
    
    private static void disableValidation(final boolean disabled) throws InvocationTargetException {
    	final GlobalConfiguration config = new GlobalConfiguration(ConfigurationManager.getManager().getGlobalConfiguration());
    	config.setDisableAllValidation(disabled);
    	config.passivate();
    	config.store();
	}
    
    /**
     * Disables all validators except the specified validator
     * @param project Project on which to disable the validator. Cannot be null.
     * @param validatorToLeaveEnabled Name of the validator to leave enabled
     * @throws CoreException Thrown if an error is encountered disabling the validator.
     */
    public static void disableValidation(final String validatorToLeaveEnabled) throws InvocationTargetException {
        final GlobalConfiguration config = new GlobalConfiguration(ConfigurationManager.getManager().getGlobalConfiguration());
        final List listVmd = new ArrayList();            
        ValidatorMetaData[] enabledValidators = config.getEnabledValidators();
            
        // filter out validators to disable
        boolean disabledSomeValidators = false;
        for (int i = 0; i < enabledValidators.length; i++) {
            final String uniqueName = enabledValidators[i].getValidatorUniqueName();
            if (uniqueName.equals(validatorToLeaveEnabled)) {
                listVmd.add(enabledValidators[i]);
            } else {
                disabledSomeValidators = true;
            }
        }
            
        if (disabledSomeValidators) {
            config.setEnabledValidators((ValidatorMetaData[]) listVmd.toArray(new ValidatorMetaData[0]));                       
            config.passivate();
            config.store();   
        }
    }
    
    /**
     * Adds a Java source path
     * @param path project relative path.
     * @throws CoreException
     */
    public static boolean addJavaSrcPath(final IProject project, final IPath path) throws CoreException {
    	final IFolder folder = project.getFolder(path);
    	if (!folder.exists()) {
    		folder.create(true, true, null);
    	}
    	final IJavaProject jProject = JavaCore.create(project);
    	final IPath absolutePath = project.getFullPath().append(path);
    	final IClasspathEntry entry = JavaCore.newSourceEntry(absolutePath);
    	IClasspathEntry[] cp = jProject.getRawClasspath();
    	final List cpList = new ArrayList();
    	boolean hasEntry = false;
    	for (int i = 0; i < cp.length; i++) {
    		final IClasspathEntry cpe = cp[i];
    		if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
    			if (cpe.getPath().equals(absolutePath)) {
    				hasEntry = true;
    				break;
    			}
    		}
    		cpList.add(cp[i]);
    	}
    	if (!hasEntry) {
    		cpList.add(entry);
        	cp = (IClasspathEntry[]) cpList.toArray(new IClasspathEntry[cpList.size()]);
        	jProject.setRawClasspath(cp, null);
        	waitForComponentRefactoringJobs();
        	return true;
    	}
    	return false;
    }
    
    /**
     * Adds a Java source path
     * @param path project relative path.
     * @throws CoreException
     */
    public static boolean removeJavaSrcPath(final IProject project, final IPath path) throws CoreException {
    	final IJavaProject jProject = JavaCore.create(project);
    	final IPath absolutePath = project.getFullPath().append(path);
    	IClasspathEntry[] cp = jProject.getRawClasspath();
    	final List cpList = new ArrayList();
    	boolean removedEntry = false;
    	for (int i = 0; i < cp.length; i++) {
    		final IClasspathEntry cpe = cp[i];
    		if (cpe.getEntryKind() != IClasspathEntry.CPE_SOURCE 
    				|| !cpe.getPath().equals(absolutePath)) {
    			cpList.add(cp[i]);
    		} else {
    			removedEntry = true;
    		}
    	}
    	if (removedEntry) {
    		cp = (IClasspathEntry[]) cpList.toArray(new IClasspathEntry[cpList.size()]);
    		jProject.setRawClasspath(cp, null);
        	waitForComponentRefactoringJobs();
        	return true;
    	}
    	return false;
    }
    
    /**
     * Verifies the existence (or absence) of the specified component path mapping.
     * @param project
     * @param projectPath
     * @param runtimePath
     * @param exists
     * @throws CoreException
     */
    public static void verifyComponentMapping(final IProject project, final IPath projectPath, final boolean exists) throws CoreException {
    	IPath runtimePath = Path.ROOT;
    	if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
    		// web projects map to WEB-INF/classes
    		runtimePath = new Path(J2EEConstants.WEB_INF_CLASSES);
    	}
    	verifyComponentMapping(project, projectPath, runtimePath, exists);
    	
    }
    
    /**
     * Verifies the existence (or absence) of the specified component path mapping.
     * @param project
     * @param projectPath
     * @param runtimePath
     * @param exists
     * @throws CoreException
     */
    public static void verifyComponentMapping(final IProject project, final IPath projectPath, final IPath runtimePath, final boolean exists) throws CoreException {
    	final IVirtualComponent c = ComponentCore.createComponent(project);
    	c.create(0, null);
    	IVirtualFolder dest = c.getRootFolder();
    	if (!runtimePath.equals(Path.ROOT)) {
    		dest = dest.getFolder(runtimePath);
    	}
    	IContainer[] mappedFolders = dest.getUnderlyingFolders();
    	boolean hasMapping = false;
    	for (int i = 0; i < mappedFolders.length; i++) {
    		if (mappedFolders[i].getProjectRelativePath().equals(projectPath)) {
    			hasMapping = true;
    			break;
    		}
    	}
    	if (exists) {
    		Assert.assertTrue("Component mapping from " + projectPath + " to " + dest.toString() + " missing", hasMapping);
    	} else { 
    		Assert.assertFalse("Component mapping from " + projectPath + " to " + dest.toString() + " should not exist", hasMapping);
    	}
    }
}
