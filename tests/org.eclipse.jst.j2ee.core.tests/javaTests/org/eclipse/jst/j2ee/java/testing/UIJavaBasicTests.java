/*
 * Created on Nov 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.java.testing;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.ui.actions.WorkspaceModifyOperation;



/**
 * @author nirav
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UIJavaBasicTests extends TestSuite {
	IProjectDescription description;
	private FileFilter projectFilter = new FileFilter() {
        //Only accept those files that are .project
        public boolean accept(File pathName) {
            return pathName.getName().equals(
                    IProjectDescription.DESCRIPTION_FILE_NAME);
        }
    };
	/**
	 * @param name
	 */
	public UIJavaBasicTests() {
		super();
		createExistingProject();
		TestSuite suite = (TestSuite) suite();
        for (int i = 0; i < suite.testCount(); i++) {
            addTest(suite.testAt(i));
        }
		//addTestSuite(JavaBasicTests.class);
	}
	
	/**
	 * @return
	 */
	protected Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(JavaBasicTests.class);
		return suite;
	}
	
	/**
     * Creates a new project resource with the selected name.
     * <p>
     * In normal usage, this method is invoked after the user has pressed Finish on
     * the wizard; the enablement of the Finish button implies that all controls
     * on the pages currently contain valid values.
     * </p>
     *
     * @return the created project resource, or <code>null</code> if the project
     *    was not created
     */
    protected IProject createExistingProject() {

        String projectName = "StressSampleProject";
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final IProject project = workspace.getRoot().getProject(projectName);
        if (this.description == null) {
            this.description = workspace.newProjectDescription(projectName);
            IPath locationPath = getLocationPath();
            //If it is under the root use the default location
            if (isPrefixOfRoot(locationPath))
                this.description.setLocation(null);
            else
                this.description.setLocation(locationPath);
        } else
            this.description.setName(projectName);

        // create the new project operation
        WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
            protected void execute(IProgressMonitor monitor)
                    throws CoreException {
                monitor.beginTask("", 2000); //$NON-NLS-1$
                project.create(description, new SubProgressMonitor(monitor,
                        1000));
                if (monitor.isCanceled())
                    throw new OperationCanceledException();
                project.open(new SubProgressMonitor(monitor, 1000));

            }
        };

        // run the new project creation operation
        try {
        	op.run(null);
        } catch (InterruptedException e) {
            return null;
        } catch (InvocationTargetException e) {
            // ie.- one of the steps resulted in a core exception	
            Throwable t = e.getTargetException();
            /*
            if (t instanceof CoreException) {
                if (((CoreException) t).getStatus().getCode() == IResourceStatus.CASE_VARIANT_EXISTS) {
                    MessageDialog
                            .openError(
                                    getShell(),
                                    DataTransferMessages
                                            .getString("WizardExternalProjectImportPage.errorMessage"), //$NON-NLS-1$
                                    DataTransferMessages
                                            .getString("WizardExternalProjectImportPage.caseVariantExistsError") //$NON-NLS-1$,
                            );
                } else {
                    ErrorDialog
                            .openError(
                                    getShell(),
                                    DataTransferMessages
                                            .getString("WizardExternalProjectImportPage.errorMessage"), //$NON-NLS-1$
                                    null, ((CoreException) t).getStatus());
                }
            }*/
            return null;
        }

        return project;
    }
    
    /**
	 * @return
	 */
	private IPath getLocationPath() {
		return new Path(getProjectLocation());
	}

	/**
	 * @return
	 */
	private String getProjectLocation() {
		String location = AutomatedBVT.baseDirectory + "StressSampleProject";
		return location;
	}

	/**
     * Set the project name using either the name of the
     * parent of the file or the name entry in the xml for 
     * the file
     */
    private void setProjectName(File projectFile) {

        //If there is no file or the user has already specified forget it
        if (projectFile == null)
            return;

        IPath path = new Path(projectFile.getPath());

        IProjectDescription newDescription = null;

        try {
            newDescription = ResourcesPlugin.getWorkspace().loadProjectDescription(path);
        } catch (CoreException exception) {
            //no good couldn't get the name
        }
    }
    /**
     * Return a.project file from the specified location.
     * If there isn't one return null.
     */
    private File projectFile(String locationFieldContents) {
        File directory = new File(locationFieldContents);
        if (directory.isFile())
            return null;

        File[] files = directory.listFiles(this.projectFilter);
        if (files != null && files.length == 1)
            return files[0];
        else
            return null;
    }
   
    /**
     * Return whether or not the specifed location is a prefix
     * of the root.
     */
    private boolean isPrefixOfRoot(IPath locationPath) {
        return Platform.getLocation().isPrefixOf(locationPath);
    }
    
}
