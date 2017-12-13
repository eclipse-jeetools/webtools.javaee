/**
 * 
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import java.io.StringBufferInputStream;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IProjectCreationPropertiesNew;
import org.eclipse.wst.common.tests.OperationTestCase;

/**
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 */
public class EJBGeneraUseTest extends OperationTestCase {
	private static String BASE_DATA_DIR = System.getProperty("user.dir") + java.io.File.separatorChar + "EJBTests" + java.io.File.separatorChar;
	private final String EJB_NAME = "TestEJB";
	private final String EJB_ARCHIVE_NAME = EJB_NAME + ".jar";
	private final String FILE1 = "Test1.java";
	
	/**
	 * used to get a string representing a path where a file with given suffix can be stored.
	 * 
	 * @param suffix the suffix to append to the BASE_DATA_DIR
	 * @return a string containing the BASE_DATA_DIR appended with the given suffix
	 */
	private static String getDataPath(String suffix) {
		return BASE_DATA_DIR + suffix;
	}
	
	/**
	 * Test create new EJB project, add file, export, delete project,
	 * then import and verify.
	 */
	public void test_GeneralUse() throws Exception{
		//create EJB
		IDataModel model = DataModelFactory.createDataModel(new EjbFacetProjectCreationDataModelProvider());
		model.setProperty(IProjectCreationPropertiesNew.PROJECT_NAME, this.EJB_NAME);
		this.runAndVerify(model);
		
		//verify EJB exists
		IVirtualComponent ejb = ComponentUtilities.getComponent(this.EJB_NAME);
		IProject ejbProject = ejb.getProject();
		IVirtualFolder ejbRootFolder = ejb.getRootFolder();
		Assert.assertNotNull("New EJB project named " + this.EJB_NAME + " should exist.", ejb);
		
		IWorkspace workspace = ejbProject.getWorkspace();
		IWorkspaceRoot workspaceRoot = workspace.getRoot();
		
		//add a file to the EJB
		IVirtualFile virtFile = ejbRootFolder.getFile(this.FILE1);
		IFile file = virtFile.getUnderlyingFile();
		file.create(new StringBufferInputStream(""),true,null);
		Assert.assertTrue("The file " + this.FILE1 + " should exsist in the project.", file.exists());
		
		//export the EJB
		model = DataModelFactory.createDataModel(new EJBComponentExportDataModelProvider());
		model.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, ejbProject.getName());
		model.setProperty(IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, getDataPath(this.EJB_ARCHIVE_NAME));
		model.setProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, Boolean.TRUE);
		this.runAndVerify(model);		
		
		//delete the EJB, and verify
		ejbProject.close(null);
		ejbProject.delete(IResource.ALWAYS_DELETE_PROJECT_CONTENT, null);
		Assert.assertNull("The " + this.EJB_NAME + " project should no longer exsist.", ComponentUtilities.getComponent(this.EJB_NAME));
		ejb = null;
		ejbProject = null;
		ejbRootFolder = null;
		virtFile = null;
		file = null;
		ejbProject = workspaceRoot.getProject(this.EJB_NAME);
		Assert.assertFalse("Project should not exsist.", ejbProject.exists());
		
		//import the EJB
		model = DataModelFactory.createDataModel(new EJBComponentImportDataModelProvider());
		model.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, getDataPath(this.EJB_ARCHIVE_NAME));
		model.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, this.EJB_NAME);
		this.runAndVerify(model);
		
		//verify the import
		ejb = ComponentUtilities.getComponent(this.EJB_NAME);
		Assert.assertNotNull("New EJB project named " + this.EJB_NAME + " should exsist", ejb);
		ejbRootFolder = ejb.getRootFolder();
		virtFile = ejbRootFolder.getFile(this.FILE1);
		Assert.assertTrue("The file " + this.FILE1 + " should exsist in the project.", file.exists());
	}
}
