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

import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jst.j2ee.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.OperationTestCase;

/**
 * Test utility class that contains code for manipulating projects.
 * DO NOT verify dependencies between EAR and module projects.
 */
public class ProjectUtil {
	
	public static final IWorkspace ws = ResourcesPlugin.getWorkspace();

	/**
	 * Gets the project for the given name. Does not test for existence.
	 * @param name
	 * @return
	 */
	public static IProject getProject(final String name) {
		return ws.getRoot().getProject(name);
	}

	/**
	 * Deletes the specified project if it exists and waits for all refactoring
	 * jobs.
	 */
	public static void deleteProject(final IProject project)
	throws CoreException, InterruptedException {
		if (!project.exists()) {
			return;
		}

		IWorkspaceRunnable workspaceRunnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor pm) throws CoreException {
				project.delete(true, null);
			}
		};
		
		//waitForValidationJobs();
		ResourcesPlugin.getWorkspace().run(workspaceRunnable, null);
		DependencyUtil.waitForProjectRefactoringJobs();
		ProjectUtil.waitForClasspathUpdate();
	}

	/**
	 * Renames the specified project if it exists and waits for all refactoring
	 * jobs.
	 * @param project
	 * @param newName
	 * @return Renamed project.
	 */
	public static IProject renameProject(final IProject project, final String newName) 
	throws CoreException, InterruptedException {
		final IProject newProject = getProject(newName);
		Assert.assertFalse("Cannot rename project " + project + ", a project already exists with name " + newName, newProject.exists());
		Assert.assertTrue("Project " + project + " does not exist, cannot rename.", project.exists());
		
		IWorkspaceRunnable workspaceRunnable = new IWorkspaceRunnable() {
			public void run(IProgressMonitor pm) throws CoreException {
				project.move(newProject.getFullPath(), true, null);
			}
		};
		
		//waitForValidationJobs();
		ResourcesPlugin.getWorkspace().run(workspaceRunnable, null);
		DependencyUtil.waitForProjectRefactoringJobs();
		DependencyUtil.waitForComponentRefactoringJobs();
        ProjectUtil.waitForClasspathUpdate();
		return newProject;
	}
	
	/**
	 * Creates an EAR project.
	 * @param name EAR name.
	 * @return The EAR project.
	 * @throws Exception
	 */
	public static IProject createEARProject(final String name) throws Exception {
		return createEARProject(name, false);
    }
	
	/**
	 * Creates an EAR project.
	 * @param name EAR name.
	 * @param waitForBuildToComplete True if the call should wait for the subsequent build to complete.
	 * @return The EAR project.
	 * @throws Exception
	 */
	public static IProject createEARProject(final String name, final boolean waitForBuildToComplete) throws Exception {
		return createEARProject(name, J2EEVersionConstants.J2EE_1_4_ID, waitForBuildToComplete);
    }
	
	/**
	 * Creates an EAR project.
	 * @param name EAR name.
	 * @param waitForBuildToComplete True if the call should wait for the subsequent build to complete.
	 * @param 
	 * @return The EAR project.
	 * @throws Exception
	 */
	public static IProject createEARProject(final String name, final int facetVersion, final boolean waitForBuildToComplete) throws Exception {
		final IDataModel dataModel = getEARCreationDataModel(name, facetVersion);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.ENTERPRISE_APPLICATION, null, waitForBuildToComplete);
	}
	
	/**
	 * Creates a web project with optional EAR association.
	 * @param name Web name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The Web project.
	 * @throws Exception
	 */
	public static IProject createWebProject(final String name, final String earName) throws Exception {
		return createWebProject(name, earName, false);
    }
	
	
	/**
	 * Creates a web project with optional EAR association.
	 * @param name Web name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The Web project.
	 * @throws Exception
	 */
	public static IProject createWebProject(final String name, final String earName, final boolean waitForBuildToComplete) throws Exception {
		return createWebProject(name, earName, J2EEVersionConstants.SERVLET_2_4, waitForBuildToComplete);
    }
	
	/**
	 * Creates a web project with optional EAR association.
	 * @param name Web name.
	 * @param earName EAR name; null for no EAR association.
	 * @param facetVersion The facet version.
	 * @return The Web project.
	 * @throws Exception
	 */
	public static IProject createWebProject(final String name, final String earName, final int facetVersion, final boolean waitForBuildToComplete) throws Exception {
		final IDataModel dataModel = getWebCreationDataModel(name, earName, facetVersion);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.DYNAMIC_WEB, earName, waitForBuildToComplete);
    }
	
	/**
	 * Creates a Utility project with optional EAR association.
	 * @param name Util name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The utility project.
	 * @throws Exception
	 */
	public static IProject createUtilityProject(final String name, final String earName) throws Exception {
		return createUtilityProject(name, earName, false);
	}
	
	/**
	 * Creates a Utility project with optional EAR association.
	 * @param name Util name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The utility project.
	 * @throws Exception
	 */
	public static IProject createUtilityProject(final String name, final String earName, final boolean waitForBuildToComplete) throws Exception {
		final IDataModel dataModel = getUtilityCreationDataModel(name, earName);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.UTILITY, earName, waitForBuildToComplete);
	}
	
	/**
	 * Creates an EJB project with optional EAR association.
	 * @param name EJB name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The EJB project.
	 * @throws Exception
	 */
	public static IProject createEJBProject(final String name, final String earName) throws Exception {
		return createEJBProject(name, earName, false);
	}
	
	/**
	 * Creates an EJB project with optional EAR association.
	 * @param name EJB name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The EJB project.
	 * @throws Exception
	 */
	public static IProject createEJBProject(final String name, final String earName, final boolean waitForBuildToComplete) throws Exception {
		return createEJBProject(name, earName, J2EEVersionConstants.EJB_2_1_ID, waitForBuildToComplete);
	}
	
	/**
	 * Creates an EJB project with optional EAR association.
	 * @param name EJB name.
	 * @param earName EAR name; null for no EAR association.
	 * @param facetVersion The facet version.
	 * @return The EJB project.
	 * @throws Exception
	 */
	public static IProject createEJBProject(final String name, final String earName, final int facetVersion, final boolean waitForBuildToComplete) throws Exception {
		final IDataModel dataModel = getEJBCreationDataModel(name, earName, facetVersion);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.EJB, earName, waitForBuildToComplete);
	}
	
	/**
	 * Creates an EJB project with optional EAR association.
	 * 
	 * @param name
	 *            EJB name.
	 * @param earName
	 *            EAR name; null for no EAR association.
	 * @param facetModelProperties
	 *            this properties will be added to the facet model retrieved by
	 *            <code> FacetDataModelMap facetMap = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.EJB);</code>
	 *            This gives you the opportunity to control the creation of the
	 *            project without introducing new create* methods.
	 * @param facetVersion
	 *            The facet version.
	 * @return The EJB project.
	 * @throws Exception
	 */
	public static IProject createEJBProject(final String name, final String earName,
			Map<String, Object> facetModelProperties, final int facetVersion, final boolean waitForBuildToComplete)
			throws Exception {
		final IDataModel dataModel = getEJBCreationDataModel(name, earName, facetVersion);
		FacetDataModelMap facetMap = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.EJB);
		for (Map.Entry<String, Object> entry : facetModelProperties.entrySet()) {
			facetModel.setProperty(entry.getKey(), entry.getValue());
		}
		return createAndVerify(dataModel, name, J2EEProjectUtilities.EJB, earName, waitForBuildToComplete);
	}
	
	/**
	 * Creates an EJB project with optional EAR association.
	 * @param name EJB name.
	 * @param earName EAR name; null for no EAR association.
	 * @param clientName Client name; null for no client. 
	 * @param facetVersion The facet version.
	 * @param waitForBuildToComplete
	 * @return The EJB project.
	 * @throws Exception
	 */
	public static IProject createEJBProject(final String name, final String earName, final String clientName,
			final int facetVersion, final boolean waitForBuildToComplete) throws Exception {
		final IDataModel dataModel = getEJBCreationDataModel(name, earName, facetVersion);
		FacetDataModelMap facetMap = (FacetDataModelMap) dataModel
				.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel facetModel = facetMap.getFacetDataModel(IJ2EEFacetConstants.EJB);
		facetModel.setBooleanProperty(IJ2EEFacetInstallDataModelProperties.GENERATE_DD, true);
		// only create client if given a client name, and is added to EAR
		if (clientName != null && earName != null) {
			facetModel.setBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, true);
			facetModel.setStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME, clientName);
		}
		return createAndVerify(dataModel, name, J2EEProjectUtilities.EJB, earName, waitForBuildToComplete);
	}

	private static IDataModel getEARCreationDataModel(final String name, final int facetVersion) {
		final IDataModel model =  DataModelFactory.createDataModel(new EARFacetProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.ENTERPRISE_APPLICATION, null, facetVersion, false);
		return model;
	}
	
	private static IDataModel getWebCreationDataModel(final String name, final String earName, final int facetVersion) {
		final IDataModel model =  DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.DYNAMIC_WEB, earName, facetVersion, facetVersion == J2EEVersionConstants.SERVLET_2_5);
		return model;
	}
		
	private static IDataModel getUtilityCreationDataModel(final String name, final String earName) {
		final IDataModel model =  DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.UTILITY, earName, 0, false);
		return model;
	}
	
	private static IDataModel getEJBCreationDataModel(final String name, final String earName, final int facetVersion) {
		final IDataModel model =  DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.EJB, earName, facetVersion, facetVersion == J2EEVersionConstants.EJB_3_0_ID);
		return model;
	}
	
	private static void configure(final IDataModel model, final String name, final String facet, final String earName, final int facetVersion, final boolean isJEE5) {
		model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, name);
		final FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		IDataModel facetDM = map.getFacetDataModel(facet);
		if (earName != null) {
			facetDM.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true);
			facetDM.setProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME, earName);
		}
		if (facetVersion != 0) {
			String versionText = J2EEVersionUtil.convertVersionIntToString(facetVersion);
			facetDM.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, versionText);
		}
		if (isJEE5) {
			facetDM = map.getFacetDataModel(J2EEProjectUtilities.JAVA);
			facetDM.setStringProperty(IFacetDataModelProperties.FACET_VERSION_STR, J2EEVersionConstants.VERSION_5_0_TEXT);
		}
	}

	private static IProject createAndVerify(final IDataModel model, final String projectName, final String type, final String earName, final boolean waitForBuildToComplete) throws Exception {
		// run the data model operation to create the projects
		OperationTestCase.runAndVerify(model,false,true, waitForBuildToComplete);
        // wait for any classpath update jobs
        ProjectUtil.waitForClasspathUpdate();
		// verify the EAR (if one was created)
	    verifyProject(earName, J2EEProjectUtilities.ENTERPRISE_APPLICATION);
	    // verify the module project
	    return verifyProject(projectName, type);
	}
	
	private static IProject verifyProject(final String projectName, final String type) {
		if (projectName != null) {
            final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
            Assert.assertTrue("Failed to create project " + projectName, project.exists());
            Assert.assertTrue("Project not is of type " + type, JavaEEProjectUtilities.isProjectOfType(project, type));
            return project;
        }	
		return null;
	}

	public static void waitForClasspathUpdate() {
		DependencyVerificationUtil.waitForClasspathUpdate();
    }

	private static ClasspathUpdateJobListener listener = new ClasspathUpdateJobListener();
    
	private static class ClasspathUpdateJobListener extends JobChangeAdapter {

		public boolean isDone = false;

		public ClasspathUpdateJobListener() {
			super();
			Job.getJobManager().addJobChangeListener(this);
		}

		public void done(IJobChangeEvent event) {
			Job job = event.getJob();
			if (job.getName().equals(J2EEComponentClasspathUpdater.MODULE_UPDATE_JOB_NAME)) {
				isDone = true;
			}
		}
    }
}
