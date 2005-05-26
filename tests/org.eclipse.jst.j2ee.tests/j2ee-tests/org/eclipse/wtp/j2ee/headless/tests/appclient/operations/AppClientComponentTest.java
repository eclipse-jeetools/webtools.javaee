/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.AbstractJ2EEComponentCreationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.RandomObjectGenerator;

/**
 * @author blancett
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class AppClientComponentTest extends AbstractJ2EEComponentCreationTest {

	public void testVaild12ApplicationClientProjectNameCreationWithAlphabetChars() throws Exception {
		org.eclipse.wst.common.tests.ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			createValidComponentNameCreationWithAlphabetChars(APPLICATION_CLIENT_MODULE, J2EEVersionConstants.J2EE_1_2_ID, false);
			addJavaMainClassToApplicationModel(ProjectUtility.getProject(projectName));
		}
	}

	public void testVaild12ApplicationClientCreationWithMixedChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createValidComponentNameCreationWithAlphabetChars(APPLICATION_CLIENT_MODULE, J2EEVersionConstants.J2EE_1_2_ID, true);
	}

	public void testVaild13ApplicationClientNameCreationWithAlphabetChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createValidComponentNameCreationWithAlphabetChars(APPLICATION_CLIENT_MODULE, J2EEVersionConstants.J2EE_1_3_ID, false);
	}

	public void testVaild13ApplicationClientNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		setupEARComponent("test", J2EEVersionConstants.J2EE_1_3_ID);
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			IProject javaProject = ProjectUtility.getProject("testapp");
			IDataModel model = DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());
			model.setProperty(IAppClientComponentCreationDataModelProperties.PROJECT_NAME, javaProject.getName());
			//model.setProperty(AppClientProjectCreationDataModel.PROJECT_LOCATION, javaProject.getLocation().toOSString());
			model.setIntProperty(IAppClientComponentCreationDataModelProperties.COMPONENT_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
			createAppClientComponent(model, ProjectUtility.getProject("test"));
		}
	}

	public void testVaildRandomVersionsApplicationProjectNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			if (RandomObjectGenerator.createRandomProjectNumber() % 2 == 0)
				createValidComponentNameCreationWithAlphabetChars(APPLICATION_CLIENT_MODULE, J2EEVersionConstants.J2EE_1_3_ID, true);
			else
				createValidComponentNameCreationWithAlphabetChars(APPLICATION_CLIENT_MODULE, J2EEVersionConstants.J2EE_1_2_ID, true);
		}
	}

	public void testInVaild12ApplicationClientNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			try {
				IDataModel model = setupApplicationClientComponent(RandomObjectGenerator.createInvalidRandomProjectName(), J2EEVersionConstants.J2EE_1_2_ID);
				ProjectUtility.verifyProject(model.getStringProperty(IJ2EEComponentCreationDataModelProperties.PROJECT_NAME), true);
			} catch (Exception e) {
				if (e instanceof IllegalArgumentException) {
					System.out.println(ILLEGAL_PROJECT_NAME_MESSAGE + projectName);
				} else
					new Exception(UNEXPECTED_ERROR_MESSAGE);
			} finally {
				new Exception(TEST_FAILED_MESSAGE);
			}
		}
	}

    public static Test suite() {
        return new SimpleTestSuite(AppClientComponentTest.class);
    }

}
