/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import junit.framework.Test;

import org.eclipse.wst.common.tests.SimpleTestSuite;

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
    	
	
	public void testWebModuleCreation() throws Exception {
		runAll();

	}

}
