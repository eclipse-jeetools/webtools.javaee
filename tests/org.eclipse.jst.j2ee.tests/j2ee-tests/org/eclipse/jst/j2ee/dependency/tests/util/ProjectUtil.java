package org.eclipse.jst.j2ee.dependency.tests.util;

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
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.EARFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;
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
		final IDataModel dataModel = getEARCreationDataModel(name);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.ENTERPRISE_APPLICATION, null);
    }
	
	/**
	 * Creates a web project with optional EAR association.
	 * @param name Web name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The Web project.
	 * @throws Exception
	 */
	public static IProject createWebProject(final String name, final String earName) throws Exception {
		final IDataModel dataModel = getWebCreationDataModel(name, earName);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.DYNAMIC_WEB, earName);
    }
	
	/**
	 * Creates a Utility project with optional EAR association.
	 * @param name Util name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The utility project.
	 * @throws Exception
	 */
	public static IProject createUtilityProject(final String name, final String earName) throws Exception {
		final IDataModel dataModel = getUtilityCreationDataModel(name, earName);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.UTILITY, earName);
	}
	
	/**
	 * Creates an EJB project with optional EAR association.
	 * @param name EJB name.
	 * @param earName EAR name; null for no EAR association.
	 * @return The EJB project.
	 * @throws Exception
	 */
	public static IProject createEJBProject(final String name, final String earName) throws Exception {
		final IDataModel dataModel = getEJBCreationDataModel(name, earName);
		return createAndVerify(dataModel, name, J2EEProjectUtilities.EJB, earName);
	}

	private static IDataModel getEARCreationDataModel(final String name) {
		final IDataModel model =  DataModelFactory.createDataModel(new EARFacetProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.ENTERPRISE_APPLICATION, null);
		return model;
	}

	
	private static IDataModel getWebCreationDataModel(final String name, final String earName) {
		final IDataModel model =  DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.DYNAMIC_WEB, earName);
		return model;
	}
		
	private static IDataModel getUtilityCreationDataModel(final String name, final String earName) {
		final IDataModel model =  DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.UTILITY, earName);
		return model;
	}
	
	private static IDataModel getEJBCreationDataModel(final String name, final String earName) {
		final IDataModel model =  DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		configure(model, name, J2EEProjectUtilities.EJB, earName);
		return model;
	}
	
	private static void configure(final IDataModel model, final String name, final String facet, final String earName) {
		model.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, name);		
		if (earName != null) {
			final FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			final IDataModel facetDM = map.getFacetDataModel(facet);
			facetDM.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true);
			facetDM.setProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME, earName);
		}
	}

	private static IProject createAndVerify(final IDataModel model, final String projectName, final String type, final String earName) throws Exception {
		// run the data model operation to create the projects
		OperationTestCase.runAndVerify(model,false,true, true);
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
            Assert.assertTrue("Project not is of type " + type, J2EEProjectUtilities.isProjectOfType(project, type));
            return project;
        }	
		return null;
	}
	
	
	
	/**
	 * @deprecated do not use
	 * 
	 * @see DoNotUseMeThisWillBeDeletedPost15
	 */
	public static final int MAX_WAIT_TIME = 30000;

	public static void waitForClasspathUpdate() {
		DependencyVerificationUtil.waitForClasspathUpdate();
    }

	/**
	 * @deprecated do not use
	 * 
	 * @see DoNotUseMeThisWillBeDeletedPost15
	 */
	 public static void waitForClasspathUpdate(int maxWait) {
        long startTime = System.currentTimeMillis();
        listener.isDone = false;
        int waitIncrement = 10;
        final J2EEComponentClasspathUpdater updater = J2EEComponentClasspathUpdater.getInstance();
        boolean updatesQueued = false;
        for(int i=0;i<50 && !updatesQueued ; i++){
            updatesQueued = updater.projectsQueued();
            if(!updatesQueued){
                try{
                    Thread.sleep(waitIncrement);
                } catch (InterruptedException e) {
                    J2EEPlugin.getDefault().getLogger().logError(e);
                }
            }
        }
        if(!updatesQueued){
            return;
        }
        
        long totalTime = 0;
        try {
            while (!listener.isDone && totalTime < maxWait) {
                Thread.sleep(waitIncrement);
                totalTime = System.currentTimeMillis() - startTime;
            }
        } catch (InterruptedException e) {
            //J2EEPlugin.getDefault().getLogger().logError(e);
        }
        waitForClasspathUpdate(maxWait- (int)(System.currentTimeMillis()-startTime));
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

