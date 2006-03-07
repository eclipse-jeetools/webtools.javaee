package org.eclipse.jst.j2ee.flexible.project.fvtests;
import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.jdt.internal.integration.IJavaProjectCreationProperties;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.flexible.project.tests.Migrate07EJBTest;
import org.eclipse.jst.j2ee.project.facet.IJavaProjectMigrationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaProjectMigrationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public  class ProjectMigrationTest extends TestCase {
	
	public static String DEFAULT_PROJECT_NAME = "TestJavaProj";	
	
	public ProjectMigrationTest(String name) {
		super(name);
	}
	
	public ProjectMigrationTest() {
		super();
	}	

    public static Test suite() {
        //return new SimpleTestSuite(ProjectMigrationTest.class);
        return new SimpleTestSuite(Migrate07EJBTest.class);
    }
	
    
	public void testProjectMigration() throws Exception {
		runAll();
	}
	
	private void createJavaProject(IProgressMonitor monitor) throws CoreException {
		
		IProject project = ProjectUtilities.getProject(DEFAULT_PROJECT_NAME);
		
		if( project.exists()){
			project.delete( true, true, null );
		}
		
		IDataModel model = DataModelFactory.createDataModel(new JavaProjectCreationDataModelProvider());	
		model.setProperty(IJavaProjectCreationProperties.PROJECT_NAME, DEFAULT_PROJECT_NAME);
		
		String[] srcFolder = new String[2];
		srcFolder[0] = new String("Src1");
		srcFolder[1] = new String("Src2");
		
		model.setProperty(IJavaProjectCreationProperties.SOURCE_FOLDERS, srcFolder);
		try {
			model.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
	
	private IPath getOutputPath(IProject project) {
		String outputLocation = "bin";
		return project.getFullPath().append(outputLocation);
	}	
	
	
	public void runAll(){
		try {
			IProgressMonitor monitor = new NullProgressMonitor();
			createJavaProject(monitor);
			migrateProject(DEFAULT_PROJECT_NAME);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void migrateProject(String projectName){	
		IDataModel model = DataModelFactory.createDataModel(new JavaProjectMigrationDataModelProvider());
		model.setProperty( IJavaProjectMigrationDataModelProperties.PROJECT_NAME, projectName);

		try {
			model.getDefaultOperation().execute( null, null );
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
