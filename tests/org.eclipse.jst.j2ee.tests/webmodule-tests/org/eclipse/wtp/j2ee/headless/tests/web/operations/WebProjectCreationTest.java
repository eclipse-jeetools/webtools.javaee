/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleCreationDataModelOld;
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
public class WebProjectCreationTest extends AbstractProjectCreationTest {
	
	
	public void testVaild12WebProjectNameCreationWithAlphabetChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_2_ID, false);
	}
	
	public void testVaild12WebProjectNameCreationWithMixedChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_2_ID, true);
	}
	
	public void testVaild13WebProjectNameCreationWithAlphabetChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_3_ID, false);
	}
	
	public void testVaild13WebProjectNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
			createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_3_ID, true);
	}
	
	public void testVaildRandomVersionsWebProjectNameCreationAllChars() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			if (RandomObjectGenerator.createRandomProjectNumber() % 2 == 0)
				createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_3_ID, true);
			else
				createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_2_ID, true);
		}
	}
	
	public void testVaildRandomVersionsWebProjectNameCreationAlphabet() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			if (RandomObjectGenerator.createRandomProjectNumber() % 2 == 0)
				createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_3_ID, false);
			else
				createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_2_ID, false);
		}
	}
	
	public void testInVaild12WebProjectNameCreation() throws Exception {
		ProjectUtility.deleteAllProjects();
		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++) {
			try {
				 setupWebProject(RandomObjectGenerator.createInvalidRandomProjectName(), J2EEVersionConstants.WEB_2_2_ID);
				 J2EEModuleCreationDataModelOld model = setupWebProject(RandomObjectGenerator.createInvalidRandomProjectName(), J2EEVersionConstants.WEB_2_2_ID);
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
	}

    public static Test suite() {
        return new SimpleTestSuite(WebProjectCreationTest.class);
    }
    
    public void testJavaCreation() throws Exception {
	}
	

}
