package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.tests.AssertWarn;
import org.eclipse.wst.common.tests.BaseTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class HeaderParserTests extends BaseTestCase {

	public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(HeaderParserTests.class);
        return suite;
    }
	
	private static final String DATA_DIR = "TestData" + java.io.File.separatorChar + "headerParserTestData" + java.io.File.separatorChar;
	
	protected String getDataPath(String shortName) throws Exception {
		HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
		String pluginRelativeFileName = DATA_DIR + java.io.File.separatorChar + shortName;
		return ProjectUtility.getFullFileName(plugin, pluginRelativeFileName);
	}

	private class TestData {
		String fileName;

		int type;

		int modVersion;

		int eeVersion;
		
		boolean deploymentDescriptor;

		Class modelObjectInterface;

		public TestData(String fileName, int type, int modVersion, int eeVersion) {
			this.fileName = fileName;
			this.type = type;
			this.modVersion = modVersion;
			this.eeVersion = eeVersion;
		}

		public TestData(String fileName, int type, int modVersion, int eeVersion, Class modelTypeClass) {
			this(fileName, type, modVersion, eeVersion);
			this.modelObjectInterface = modelTypeClass;
		}
		
		public TestData(String fileName, int type, int modVersion, int eeVersion, Class modelTypeClass,boolean deploymentDescriptor) {
			this(fileName, type, modVersion, eeVersion,modelTypeClass);
			this.deploymentDescriptor = deploymentDescriptor;
		}
	}
	
	public void testNull() throws Exception {
		JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(null);
		Assert.assertEquals(JavaEEQuickPeek.UNKNOWN, quickPeek.getType());
		Assert.assertEquals(JavaEEQuickPeek.UNKNOWN, quickPeek.getVersion());
		Assert.assertEquals(JavaEEQuickPeek.UNKNOWN, quickPeek.getJavaEEVersion());
	}

	public void testJavaEEFromArchive() throws Exception {
		List data = new ArrayList();
		data.add(new TestData("application-client12.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
		data.add(new TestData("application-client13.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
		data.add(new TestData("application-client14.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
		data.add(new TestData("application-client5.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
				org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));

		data.add(new TestData("application12.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID,
				org.eclipse.jst.j2ee.application.Application.class));
		data.add(new TestData("application13.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID,
				org.eclipse.jst.j2ee.application.Application.class));
		data.add(new TestData("application14.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID,
				org.eclipse.jst.j2ee.application.Application.class));
		data.add(new TestData("application5.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
				org.eclipse.jst.javaee.application.Application.class));

		data.add(new TestData("ejb-jar11.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_1_1_ID, J2EEVersionConstants.J2EE_1_2_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
		data.add(new TestData("ejb-jar20.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
		data.add(new TestData("ejb-jar21.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_1_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
		data.add(new TestData("ejb-jar30.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));

		data.add(new TestData("ra10.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.jca.Connector.class));
		data.add(new TestData("ra15.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_5_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.jca.Connector.class));

		data.add(new TestData("web22.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_2_ID, J2EEVersionConstants.J2EE_1_2_ID, WebApp.class));
		data.add(new TestData("web23.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_3_ID, J2EEVersionConstants.J2EE_1_3_ID, WebApp.class));
		data.add(new TestData("web24.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_4_ID, J2EEVersionConstants.J2EE_1_4_ID, WebApp.class));
		data.add(new TestData("web25.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));

		IArchive archive = null;
		for (int i = 0; i < data.size(); i++) {
			try {
				TestData testData = (TestData) data.get(i);
				String fileLocation = getDataPath(testData.fileName);
				IPath filePath = new Path(fileLocation);
				archive = JavaEEArchiveUtilities.INSTANCE.openArchive(filePath);
				JavaEEQuickPeek peek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive);
				Assert.assertEquals(testData.type, peek.getType());
				Assert.assertEquals(testData.modVersion, peek.getVersion());
				Assert.assertEquals(testData.eeVersion, peek.getJavaEEVersion());

				Object modelObject = archive.getModelObject();
				Class clazz = modelObject.getClass();
				boolean foundInterface = false;
				for (Class anInterface : clazz.getInterfaces()) {
					if (!foundInterface) {
						foundInterface = anInterface == testData.modelObjectInterface;
					}
				}
				Assert.assertTrue("Returned Model Object: " + modelObject.getClass().getName() + " does not implement " + testData.modelObjectInterface.getName(), foundInterface);
			} finally {
				if (archive != null) {
					JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
				}
			}
		}
	}

	public void testJavaEE() throws Exception {

		List data = getXMLData();

		InputStream in = null;

		for (int i = 0; i < data.size(); i++) {
			try {
				TestData testData = (TestData) data.get(i);
				in = new FileInputStream(new File(getDataPath(testData.fileName)));
				JavaEEQuickPeek peek = new JavaEEQuickPeek(in);
				Assert.assertEquals(testData.type, peek.getType());
				Assert.assertEquals(testData.modVersion, peek.getVersion());
				Assert.assertEquals(testData.eeVersion, peek.getJavaEEVersion());
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}

	}

	private List getXMLData() {
		List data = new ArrayList();
		data.add(new TestData("application-client12.xml", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID));
		data.add(new TestData("application-client13.xml", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID));
		data.add(new TestData("application-client14.xml", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID));
		data.add(new TestData("application-client5.xml", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID));

		data.add(new TestData("application12.xml", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID));
		data.add(new TestData("application13.xml", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID));
		data.add(new TestData("application14.xml", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID));
		data.add(new TestData("application5.xml", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID));

		data.add(new TestData("ejb-jar11.xml", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_1_1_ID, J2EEVersionConstants.J2EE_1_2_ID));
		data.add(new TestData("ejb-jar20.xml", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_0_ID, J2EEVersionConstants.J2EE_1_3_ID));
		data.add(new TestData("ejb-jar21.xml", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_1_ID, J2EEVersionConstants.J2EE_1_4_ID));
		data.add(new TestData("ejb-jar30.xml", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID));

		data.add(new TestData("ra10.xml", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_0_ID, J2EEVersionConstants.J2EE_1_3_ID));
		data.add(new TestData("ra15.xml", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_5_ID, J2EEVersionConstants.J2EE_1_4_ID));

		data.add(new TestData("web22.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_2_ID, J2EEVersionConstants.J2EE_1_2_ID));
		data.add(new TestData("web23.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_3_ID, J2EEVersionConstants.J2EE_1_3_ID));
		data.add(new TestData("web24.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_4_ID, J2EEVersionConstants.J2EE_1_4_ID));
		data.add(new TestData("web25.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID));

		// test some bogus dds as well
		data.add(new TestData("notxml.xml", J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("notjavaee.xml", J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));

		data.add(new TestData("application-client0.xml", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("application-client00.xml", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("application-client000.xml", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));

		data.add(new TestData("application0.xml", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("application00.xml", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("application000.xml", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));

		data.add(new TestData("ejb-jar0.xml", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("ejb-jar00.xml", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("ejb-jar000.xml", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));

		data.add(new TestData("ra0.xml", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("ra00.xml", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("ra000.xml", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));

		data.add(new TestData("web0.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("web00.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		data.add(new TestData("web000.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.UNKNOWN, J2EEVersionConstants.UNKNOWN));
		return data;
	}

	public void testNormalizeSchemaLocation() throws Exception {
		verifyNormalizeSchemaLocation("", "");
		verifyNormalizeSchemaLocation("", " ");
		verifyNormalizeSchemaLocation("", "  ");
		verifyNormalizeSchemaLocation("", "   ");
		verifyNormalizeSchemaLocation("", "\n");
		verifyNormalizeSchemaLocation("", "\n\n");
		verifyNormalizeSchemaLocation("", "\n\n\n");
		verifyNormalizeSchemaLocation("", "\n ");
		verifyNormalizeSchemaLocation("simple", "simple");
		verifyNormalizeSchemaLocation("simple", "simple ");
		verifyNormalizeSchemaLocation("simple", "simple  ");
		verifyNormalizeSchemaLocation("simple", "simple   ");
		verifyNormalizeSchemaLocation("simple", " simple");
		verifyNormalizeSchemaLocation("simple", "   simple   ");
		verifyNormalizeSchemaLocation("simple", "simple\n");
		verifyNormalizeSchemaLocation("simple", "\nsimple\n");
		verifyNormalizeSchemaLocation("simple", "simple\r");
		verifyNormalizeSchemaLocation("simple", "simple\t");
		verifyNormalizeSchemaLocation("simple", "simple\n\n");
		verifyNormalizeSchemaLocation("simple", "simple\n\r");
		verifyNormalizeSchemaLocation("simple", "simple\n\t");
		verifyNormalizeSchemaLocation("simple", "simple \n");
		verifyNormalizeSchemaLocation("simple", "simple\r ");
		verifyNormalizeSchemaLocation("simple", " \t\tsimple\t");
		verifyNormalizeSchemaLocation("simple", "simple\n \n");
		verifyNormalizeSchemaLocation("simple", "\r \rsimple \n\r");
		verifyNormalizeSchemaLocation("simple", " simple\n\t");
		
		verifyNormalizeSchemaLocation("simple simple", "simple simple");
		verifyNormalizeSchemaLocation("simple simple", "simple  simple");
		verifyNormalizeSchemaLocation("simple simple", "simple\nsimple");
		verifyNormalizeSchemaLocation("simple simple", "simple\rsimple");
		verifyNormalizeSchemaLocation("simple simple", "simple\tsimple");
		verifyNormalizeSchemaLocation("simple simple", "simple  \nsimple");
		verifyNormalizeSchemaLocation("simple simple", "simple\r  simple");
		verifyNormalizeSchemaLocation("simple simple", "simple          \t        simple");
		verifyNormalizeSchemaLocation("simple simple", "simple simple ");
		verifyNormalizeSchemaLocation("simple simple", " simple  simple");
		verifyNormalizeSchemaLocation("simple simple", "   simple      simple   ");
		verifyNormalizeSchemaLocation("simple simple", "simple\n simple\n");
		verifyNormalizeSchemaLocation("simple simple", "simple\r simple\r");
		verifyNormalizeSchemaLocation("simple simple", "simple\t simple\t");
		verifyNormalizeSchemaLocation("simple simple", "simple\n\nsimple");
		verifyNormalizeSchemaLocation("simple simple", "simple\n\rsimple\n\n");
		verifyNormalizeSchemaLocation("simple simple", "simple\n\tsimple\n\t");
		verifyNormalizeSchemaLocation("simple simple", "simple \nsimple \n");
		verifyNormalizeSchemaLocation("simple simple", "simple\r simple\r ");
		verifyNormalizeSchemaLocation("simple simple", " \t\tsimple\t \t\tsimple\t");
		verifyNormalizeSchemaLocation("simple simple", "simple\n \nsimple\n \n");
		verifyNormalizeSchemaLocation("simple simple", "\r \rsimple \n\r\r \rsimple \n\r");
		verifyNormalizeSchemaLocation("simple simple", " simple\n\t simple\n\t");
	}

	private String verifyNormalizeSchemaLocation(String normalizedSchemaLocation, String someSchemaLocation) {
		String normalizedString = JavaEEQuickPeek.normalizeSchemaLocation(someSchemaLocation);
		if(!normalizedSchemaLocation.equals(normalizedString)){
			Assert.assertEquals(normalizedSchemaLocation, normalizedString);	
		}
		return normalizedString;
	} 
	
    public void testEAR50Import_NoDD() throws Exception {
    	List nestedArchiveData = new ArrayList();  	
    	nestedArchiveData.add(new TestData("application-client12.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client13.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client14.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("AppClient5_NoDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("AppClient5_WithDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	
    	nestedArchiveData.add(new TestData("ejb-jar11.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_1_1_ID, J2EEVersionConstants.J2EE_1_2_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar20.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar21.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_1_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_NoDD_MessageDriven.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateful.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateless.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_WithDD.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	
    	nestedArchiveData.add(new TestData("ra10.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.jca.Connector.class));
    	nestedArchiveData.add(new TestData("ra15.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_5_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.jca.Connector.class));

    	nestedArchiveData.add(new TestData("web22.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_2_ID, J2EEVersionConstants.J2EE_1_2_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web23.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_3_ID, J2EEVersionConstants.J2EE_1_3_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web24.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_4_ID, J2EEVersionConstants.J2EE_1_4_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("Web25_NoDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    	nestedArchiveData.add(new TestData("Web25_WithDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    	
    	TestData earData = new TestData("EAR5_NoDD.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
				org.eclipse.jst.javaee.application.Application.class,false);
    	runEAR50Tests(earData, nestedArchiveData);
    }
    
    public void testEAR50Import_WithDD() throws Exception {
    	List nestedArchiveData = new ArrayList();  	
    	nestedArchiveData.add(new TestData("application-client12.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_2_ID, J2EEVersionConstants.J2EE_1_2_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client13.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_3_ID, J2EEVersionConstants.J2EE_1_3_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("application-client14.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.J2EE_1_4_ID, J2EEVersionConstants.J2EE_1_4_ID,
				org.eclipse.jst.j2ee.client.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("AppClient5_NoDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	nestedArchiveData.add(new TestData("AppClient5_WithDD.jar", J2EEVersionConstants.APPLICATION_CLIENT_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
    			org.eclipse.jst.javaee.applicationclient.ApplicationClient.class));
    	
    	nestedArchiveData.add(new TestData("ejb-jar11.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_1_1_ID, J2EEVersionConstants.J2EE_1_2_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar20.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("ejb-jar21.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_2_1_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_NoDD_MessageDriven.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateful.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_NoDD_Stateless.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	nestedArchiveData.add(new TestData("EJB3_WithDD.jar", J2EEVersionConstants.EJB_TYPE, J2EEVersionConstants.EJB_3_0_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.ejb.EJBJar.class));
    	
    	nestedArchiveData.add(new TestData("ra10.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_0_ID, J2EEVersionConstants.J2EE_1_3_ID, org.eclipse.jst.j2ee.jca.Connector.class));
    	nestedArchiveData.add(new TestData("ra15.rar", J2EEVersionConstants.CONNECTOR_TYPE, J2EEVersionConstants.JCA_1_5_ID, J2EEVersionConstants.J2EE_1_4_ID, org.eclipse.jst.j2ee.jca.Connector.class));

    	nestedArchiveData.add(new TestData("web22.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_2_ID, J2EEVersionConstants.J2EE_1_2_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web23.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_3_ID, J2EEVersionConstants.J2EE_1_3_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("web24.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_4_ID, J2EEVersionConstants.J2EE_1_4_ID, WebApp.class));
    	nestedArchiveData.add(new TestData("Web25_NoDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    	nestedArchiveData.add(new TestData("Web25_WithDD.war", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID, org.eclipse.jst.javaee.web.WebApp.class));
    
    	TestData earData = new TestData("EAR5_WithDD.ear", J2EEVersionConstants.APPLICATION_TYPE, J2EEVersionConstants.JEE_5_0_ID, J2EEVersionConstants.JEE_5_0_ID,
				org.eclipse.jst.javaee.application.Application.class,true);
    	runEAR50Tests(earData, nestedArchiveData);
    }
    
    private void runEAR50Tests(TestData earData, List<TestData> nestedArchiveData) throws Exception {
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
			for(TestData testData : nestedArchiveData) {
				innerArchiveResource = earArchive.getArchiveResource(new Path(testData.fileName));
				innerArchive = earArchive.getNestedArchive(innerArchiveResource);
				peek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(innerArchive);
				AssertWarn.warnEquals(testData.fileName + " type", testData.type, peek.getType());
				AssertWarn.warnEquals(testData.fileName + " mod version", testData.modVersion, peek.getVersion());
				AssertWarn.warnEquals(testData.fileName + " ee version", testData.eeVersion, peek.getJavaEEVersion());
				
		    	//TODO uncomment this block when this bug is resolved: https://bugs.eclipse.org/bugs/show_bug.cgi?id=199953
		    	System.err.println("TODO -- can't getModelObject from inner archive of JEE5 EAR");
		    	System.err.println("     -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=199953");
//				Object modelObject = innerArchive.getModelObject();
//				Class clazz = modelObject.getClass();
//				boolean foundInterface = false;
//				for (Class anInterface : clazz.getInterfaces()) {
//					if (!foundInterface) {
//						foundInterface = anInterface == testData.modelObjectInterface;
//					}
//				}
//				Assert.assertTrue("Returned Model Object: " + modelObject.getClass().getName() + " does not implement " + testData.modelObjectInterface.getName(), foundInterface);
			}
			
    	} finally {
    		if(earArchive != null) {
    			JavaEEArchiveUtilities.INSTANCE.closeArchive(earArchive);
    		}
    	}
    }
}
