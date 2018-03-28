package org.eclipse.wtp.j2ee.headless.tests.j2ee.headers;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class Ear50ImportHeaderTest extends TestCase {
	private static final String DATA_DIR = "TestData" + java.io.File.separatorChar + "headerParserTestData" + java.io.File.separatorChar;
	
	protected String getDataPath(String shortName) throws Exception {
		HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
		String pluginRelativeFileName = DATA_DIR + java.io.File.separatorChar + shortName;
		return ProjectUtility.getFullFileName(plugin, pluginRelativeFileName);
	}

	public static TestSuite suite() {
        TestSuite suite = new TestSuite();

        TestData earNoDD = new TestData("EAR5_NoDD.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
				org.eclipse.jst.javaee.application.Application.class,false);
        ArrayList<TestData> data = noDD_Data();
        Iterator<TestData> it = data.iterator();
        TestData s = null;
        while(it.hasNext()) {
        	s = it.next();
            suite.addTest(new Ear50ImportHeaderTest(earNoDD, s));
        }
        
        
    	TestData earWithDD = new TestData("EAR5_WithDD.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
				org.eclipse.jst.javaee.application.Application.class,true);
        data = withDD_Data();
        it = data.iterator();
        s = null;
        while(it.hasNext()) {
        	s = it.next();
            suite.addTest(new Ear50ImportHeaderTest(earWithDD, s));
        }
        return suite;
    }
	
	private TestData earData;
	private TestData testData;
	public Ear50ImportHeaderTest(TestData ear, TestData testData) {
		super("testEar50");
		this.earData = ear;
		this.testData = testData;
	}


    public void testEar50() throws Exception {
    	IArchive earArchive = null;
    	
    	try {
			String earLocation = getDataPath(earData.fileName);
			IPath earPath = new Path(earLocation);
    		earArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(earPath);
    		earArchive.getArchiveOptions().setOption(JavaEEArchiveUtilities.DISCRIMINATE_EJB_ANNOTATIONS, Boolean.TRUE);
			JavaEEQuickPeek peek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(earArchive);
			Assert.assertEquals(earData.fileName + " type", earData.type, peek.getType());
			if (earData.deploymentDescriptor){
				Assert.assertEquals(earData.fileName + " mod version", earData.modVersion, peek.getVersion());
				Assert.assertEquals(earData.fileName + " ee version", earData.eeVersion, peek.getJavaEEVersion());
			}
			else{
				Assert.assertTrue(earData.fileName + " mod version", peek.getVersion() >= earData.modVersion);
				Assert.assertTrue(earData.fileName + " ee version", peek.getJavaEEVersion() >= earData.eeVersion);
			}
			IArchiveResource innerArchiveResource;
			IArchive innerArchive = null;
			innerArchiveResource = earArchive.getArchiveResource(new Path(testData.fileName));
			innerArchive = earArchive.getNestedArchive(innerArchiveResource);
			peek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(innerArchive);
			Assert.assertEquals(testData.fileName + " type", testData.type, peek.getType());
			Assert.assertEquals(testData.fileName + " mod version", testData.modVersion, peek.getVersion());
			Assert.assertEquals(testData.fileName + " ee version", testData.eeVersion, peek.getJavaEEVersion());
			
	    	//TODO uncomment this block when this bug is resolved: https://bugs.eclipse.org/bugs/show_bug.cgi?id=199953
//	    	System.err.println("TODO -- can't getModelObject from inner archive of JEE5 EAR");
//	    	System.err.println("     -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=199953");
//			Object modelObject = innerArchive.getModelObject();
//			Class clazz = modelObject.getClass();
//			boolean foundInterface = false;
//			for (Class anInterface : clazz.getInterfaces()) {
//				if (!foundInterface) {
//					foundInterface = anInterface == testData.modelObjectInterface;
//				}
//			}
//			Assert.assertTrue("Returned Model Object: " + modelObject.getClass().getName() + " does not implement " + testData.modelObjectInterface.getName(), foundInterface);
    	} finally {
    		if(earArchive != null) {
    			JavaEEArchiveUtilities.INSTANCE.closeArchive(earArchive);
    		}
    	}
    }
	

	private static ArrayList<TestData> noDD_Data() {
		ArrayList<TestData> nestedArchiveData = new ArrayList<TestData>();  	
    	nestedArchiveData.add(new TestData("application-client12.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client13.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client14.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
//    	nestedArchiveData.add(new TestData("AppClient5_NoDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
//    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("AppClient5_WithDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	
    	nestedArchiveData.add(new TestData("ejb-jar11.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_1_1_ID, J2EEVersionConstants.J2EE_1_2_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar20.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar21.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_1_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
//    	nestedArchiveData.add(new TestData("EJB3_NoDD_MessageDriven.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
//    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateful.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
//    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateless.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_WithDD.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	
    	nestedArchiveData.add(new TestData("ra10.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.jca.Connector.class));
    	nestedArchiveData.add(new TestData("ra15.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_5_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.jca.Connector.class));

    	nestedArchiveData.add(new TestData("web22.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_2_ID, J2EEVersionConstants.J2EE_1_2_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web23.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_3_ID, J2EEVersionConstants.J2EE_1_3_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web24.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_4_ID, J2EEVersionConstants.J2EE_1_4_ID, WebApp.class));
//    	nestedArchiveData.add(new TestData("Web25_NoDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    	nestedArchiveData.add(new TestData("Web25_WithDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    	return nestedArchiveData;
	}
	

	private static ArrayList<TestData> withDD_Data() {
		ArrayList<TestData>  nestedArchiveData = new ArrayList<TestData>();  	
    	nestedArchiveData.add(new TestData("application-client12.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client13.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client14.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
//    	nestedArchiveData.add(new TestData("AppClient5_NoDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
//    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("AppClient5_WithDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	
    	nestedArchiveData.add(new TestData("ejb-jar11.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_1_1_ID, J2EEVersionConstants.J2EE_1_2_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar20.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar21.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_1_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
//    	nestedArchiveData.add(new TestData("EJB3_NoDD_MessageDriven.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
//    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateful.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
//    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateless.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_WithDD.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	
    	nestedArchiveData.add(new TestData("ra10.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.jca.Connector.class));
    	nestedArchiveData.add(new TestData("ra15.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_5_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.jca.Connector.class));

    	nestedArchiveData.add(new TestData("web22.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_2_ID, J2EEVersionConstants.J2EE_1_2_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web23.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_3_ID, J2EEVersionConstants.J2EE_1_3_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web24.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_4_ID, J2EEVersionConstants.J2EE_1_4_ID, WebApp.class));
//    	nestedArchiveData.add(new TestData("Web25_NoDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    	nestedArchiveData.add(new TestData("Web25_WithDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    	return nestedArchiveData;
    }
}
