/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ear.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
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
public class EARProjectCreationTest extends AbstractProjectCreationTest{
	
	public void testVaild12EARProjectNameCreationWithAlphabetChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.J2EE_1_2_ID, false);
	}
	
	public void testVaild12EARProjectNameCreationWithMixedChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.J2EE_1_3_ID, true);
	}
	
	public void testVaild13EARProjectNameCreationWithAlphabetChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.J2EE_1_3_ID, false);
	}
	
	public void testVaild13EARProjectNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.J2EE_1_3_ID, true);
	}
	
	public void testVaildRandomVersionsEARProjectNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			if (RandomObjectGenerator.createRandomProjectNumber() % 2 == 0)
				createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.EJB_2_0_ID, true);
			else
				createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.EJB_1_1_ID, true);
		}
	}
	
	public void testVaildRandomVersionsEARProjectNameCreationAlphabet() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			if (RandomObjectGenerator.createRandomProjectNumber() % 2 == 0)
				createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.J2EE_1_3_ID, false);
			else
				createVaildProjectNameCreationWithAlphabetChars(EAR_PROJECT, J2EEVersionConstants.J2EE_1_2_ID, false);
		}
	}
	
	public void testInVaild12EARProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			try {
//				projectName = setupWebProject(RandomObjectGenerator.createInvalidRandomProjectName(), J2EEVersionConstants.EJB_1_1_ID);
//				checkVaildProjectName(projectName);
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
        return new SimpleTestSuite(EARProjectCreationTest.class);
    }
	
	

}
