package org.eclipse.jst.j2ee.archive.test;

import java.io.FileNotFoundException;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveInit;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;



public class ClientContainerResourceLoadTest extends TestCase{
	public static EARFile earFile = null;
	
	private final static String copyright = "(c) Copyright IBM Corporation 2001."; //$NON-NLS-1$
	/**
	 * SimpleTests constructor comment.
	 * @param name java.lang.String
	 */
	public ClientContainerResourceLoadTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		ArchiveInit.init();
	}

	
	public static junit.framework.Test suite() {
		return new TestSuite(ClientContainerResourceLoadTest.class);
	}
	/**
	 * Starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		String[] className = { "com.ibm.etools.archive.test.ClientContainerResourceLoadTest", "-noloading" };
		TestRunner.main(className);
	}
	
	public void testClientContainerLoad () throws Exception {

		try {

			CommonarchiveFactory factory = CommonarchiveFactoryImpl.getActiveFactory();
			String in = AutomatedBVT.baseDirectory + "mtapp2.ear";
			String out = AutomatedBVT.baseDirectory + "mtapp2Finished.ear";
			earFile = factory.openEARFile (in);
			System.out.println ("uri: " + earFile.getURI());

			List appClients = earFile.getApplicationClientFiles();
			boolean exFound1 = false;
			boolean exFound2 = false;
			
			for(int i = 0; i<appClients.size(); i++){
				ApplicationClientFile file = (ApplicationClientFile)appClients.get(0);
				Resource res;

				try{
					res = file.getMofResource("META-INF/FOOclient-resource.xmi");
				} catch(Exception e){	
					exFound1 = true;
					if(!(e instanceof FileNotFoundException)){
						assertTrue("Expected FileNotFoundException", false);
					}		
				}
				
				try {
					res = file.getMofResource("META-INF/client-resource.xmi");
				} catch (Exception e) {
					exFound2 = true;
					if(!(e instanceof ResourceLoadException)){
						assertTrue("Expected ResourceLoadException", false);
					}	
				}
			}
			assertTrue("No exception logged for non-existing file", exFound1);
			assertTrue("No exception logged for existing", exFound2);

			
			earFile.saveAs(out);
			earFile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
