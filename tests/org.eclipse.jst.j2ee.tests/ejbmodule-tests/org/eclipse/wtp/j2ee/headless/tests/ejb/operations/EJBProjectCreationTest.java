/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.tests.LogUtility;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.AbstractProjectCreationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.RandomObjectGenerator;

/**
 * @author blancett
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EJBProjectCreationTest extends AbstractProjectCreationTest {
	
	
	public void testVaild11EJBProjectNameCreationWithAlphabeticChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_1_1_ID, false);
	}
	
	public void testVaildEJB11ProjectNameCreationWithMixedChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_1_1_ID, true);
	}
	
	public void testVaild20EJBProjectNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_2_0_ID, true);
	}
	
	public void testVaild20EJBProjectNameCreationWithAlphabetic() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_2_0_ID, false);
	}
	
	public void testVaildRandomVersionsEJBProjectNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			if (RandomObjectGenerator.createRandomProjectNumber() % 2 == 0)
				createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_1_1_ID, true);
			else
				createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_2_0_ID, true);
		}
	}
	
	public void testVaildRandomVersionsEJBProjectNameCreationAlphabetChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			if (RandomObjectGenerator.createRandomProjectNumber() % 2 == 0)
				createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_1_1_ID, false);
			else
				createVaildProjectNameCreationWithAlphabetChars(EJB_PROJECT, J2EEVersionConstants.EJB_2_0_ID, false);
		}
	}
	
	public void testInvaild11EJBProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		LogUtility.getInstance().resetLogging();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			try {
				J2EEModuleCreationDataModel model = setupEJBProject(RandomObjectGenerator.createInvalidRandomProjectName(), J2EEVersionConstants.EJB_1_1_ID);
				checkValidDataModel(model);
			} catch (Exception e) {
				if (e instanceof IllegalArgumentException) {
					System.out.println(ILLEGAL_PROJECT_NAME_MESSAGE + projectName);
				} else
					new Exception(UNEXPECTED_ERROR_MESSAGE);
			} finally {
				new Exception(TEST_FAILED_MESSAGE);
			}
		}
		LogUtility.getInstance().verifyNoWarnings();
	}

    public static Test suite() {
        return new SimpleTestSuite(EJBProjectCreationTest.class);
    }

}
