/*
 * Created on Dec 2, 2003
 *
 */
package org.eclipse.jst.j2ee.archive.test;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.internal.IWrappedException;


/**
 * @author schacher
 */
public class TestInvalidXmlMultiplicity extends AbstractArchiveTest {

	/**
	 * @param name
	 */
	public TestInvalidXmlMultiplicity(String name) {
		super(name);
	}

	/**
	 * Starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		String[] className = { "com.ibm.etools.archive.test.TestInvalidXmlMultiplicity", "-noloading" };
		TestRunner.main(className);
	}
	
	public static junit.framework.Test suite() {
		return new TestSuite(TestInvalidXmlMultiplicity.class);
	}
	
	public void test1() throws Exception {
		String uri =  AutomatedBVT.baseDirectory + "BadJspConfig.war";
		runTest(uri);
	}
	
	public void test2() throws Exception {
		String uri =  AutomatedBVT.baseDirectory + "BadLoginConfig.war";
		runTest(uri);
	}
	
	public void test3() throws Exception {
		String uri =  AutomatedBVT.baseDirectory + "BadSessionConfig.war";
		runTest(uri);
	}
	
	public void runTest(String uri) throws Exception {
		IWrappedException ex = null;
		WARFile war = getArchiveFactory().openWARFile(uri);
		try {
			war.getDeploymentDescriptor();
		} catch (DeploymentDescriptorLoadException ddex) {
			ex = (IWrappedException) ddex;
		}
		assertNotNull("Exception should have been caught", ex);
		Exception inner = ex.getNestedException();
		
		while (ex != null) {
			if (inner instanceof IWrappedException) {
				ex = (IWrappedException)inner;
				if (ex.getNestedException() != null)
					inner = ex.getNestedException();
			} else 
				ex = null;
		}
		
		assertTrue("The exception should be an IllegalStateException", inner instanceof IllegalStateException);
		war.close();
	}
}
