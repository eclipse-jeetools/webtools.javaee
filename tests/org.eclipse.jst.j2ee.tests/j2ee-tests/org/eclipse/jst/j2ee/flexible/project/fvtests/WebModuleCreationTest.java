/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import junit.framework.Test;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
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
public class WebModuleCreationTest extends AbstractModuleCreationTest {
	
    public static Test suite() {
        return new SimpleTestSuite(WebModuleCreationTest.class);
    }
    	
	
	public void testVaild12WebProjectNameCreationWithAlphabetChars() throws Exception {
		int test = 0;
		
		setupWebModule(J2EEVersionConstants.WEB_2_2_ID);
		
//		ProjectUtility.deleteAllProjects();
//		for (int i = 0; i < RandomObjectGenerator.createRandomProjectNumber(); i++)
//			createVaildProjectNameCreationWithAlphabetChars(WEB_PROJECT, J2EEVersionConstants.WEB_2_2_ID, false);
	}

}
