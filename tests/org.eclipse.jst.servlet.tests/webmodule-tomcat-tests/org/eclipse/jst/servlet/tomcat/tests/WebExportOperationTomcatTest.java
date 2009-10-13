/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.tomcat.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTest;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebExportOperationTomcatTest extends ModuleExportOperationTest {

	protected boolean excludeCompileJsp = false;
	protected boolean exportSourceFiles = false;
	protected boolean overwriteExisting = false;
	protected boolean dataModelShouldBeValid = true;

	public WebExportOperationTomcatTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new SimpleTestSuite(WebExportOperationTomcatTest.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		excludeCompileJsp = false;
	}

	public void testExcludeCompileJspOn() throws Exception {
		excludeCompileJsp = true;
		testAllExportTestCases();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getModelInstance()
	 */
	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new WebComponentExportDataModelProvider());
	}
	protected IProject[] getExportableProjects() throws Exception {
		
		deleteAllProjects();
		WebImportOperationTomcatTest importTestCase = (WebImportOperationTomcatTest)getImportTestCase(); 
		importTestCase.testAllImportTestCases();

		// if the projects aren't created successfully, the previous
		// line will fail so there's no need to verify
		IProject[] projs = ProjectUtility.getAllProjects();
		
		List filteredProjs = new ArrayList();
		for (int i = 0; i < projs.length; i++) {
			IProject project = projs[i];
			if (JavaEEProjectUtilities.isDynamicWebProject(project))
				filteredProjs.add(project);
		}
		return (IProject[]) filteredProjs.toArray(new IProject[filteredProjs.size()]);
		
	}
	
	public void testAllExportTestCases() throws Exception {

		File exportDirectory = new File(BASE_DATA_DIR);
		if (exportDirectory.isDirectory()) {
			File[] contents = exportDirectory.listFiles();
			for (int i = 0; i < contents.length; i++) {
				if (!contents[i].isDirectory())
					contents[i].delete();
			}
		}
		IProject[] projects = getExportableProjects();
		for (int i = 0; i < projects.length; i++) {
			testExport(ComponentCore.createComponent(projects[i]), getFileName(projects[i].getName()));
		}
	}
	
	public String getFileName(String baseName) {
		StringBuffer result = new StringBuffer(baseName);
		result.append((exportSourceFiles) ? "_withSource" : "_withoutSource").append(getModuleExtension());
		return result.toString();
	}
	
	public void testExport(IVirtualComponent component, String filename) throws Exception {
		IDataModel dataModel = getModelInstance();
		dataModel.setProperty(J2EEComponentExportDataModelProvider.ARCHIVE_DESTINATION, BASE_DATA_DIR + filename);
		dataModel.setProperty(J2EEComponentExportDataModelProvider.COMPONENT, component);
		dataModel.setBooleanProperty(J2EEComponentExportDataModelProvider.EXPORT_SOURCE_FILES, exportSourceFiles);
		dataModel.setBooleanProperty(J2EEComponentExportDataModelProvider.OVERWRITE_EXISTING, overwriteExisting);

		if (dataModelShouldBeValid)
			runAndVerify(dataModel);
		else
			verifyInvalidDataModel(dataModel);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTest getImportTestCase() {
		return new WebImportOperationTomcatTest(""); //$NON-NLS-1$
	}
	

	protected void addJavaFilesToProject(String projectName,
			String[] classNames, String prackageName) throws Exception {
	}

	protected void verifyJavaFilesExported(String archivePath,
			String[] classNames, String packageName, boolean withClassFiles,
			boolean withSource) throws Exception {
		
	}

	protected IDataModel getExportDataModel(String projectName,
			String destination, boolean exportSource, boolean runBuild,
			boolean overwriteExisting) {
		return null;
	}

	protected String getModuleExtension() {
		return ".war";//$NON-NLS-1$
	}
}
