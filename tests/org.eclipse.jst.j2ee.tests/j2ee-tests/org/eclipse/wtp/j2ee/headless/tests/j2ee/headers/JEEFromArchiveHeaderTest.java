package org.eclipse.wtp.j2ee.headless.tests.j2ee.headers;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JEEFromArchiveHeaderTest extends TestCase {
	private static final String DATA_DIR = "TestData" + java.io.File.separatorChar + "headerParserTestData" + java.io.File.separatorChar;
	
	protected String getDataPath(String shortName) throws Exception {
		HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
		String pluginRelativeFileName = DATA_DIR + java.io.File.separatorChar + shortName;
		return ProjectUtility.getFullFileName(plugin, pluginRelativeFileName);
	}

	public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        ArrayList<TestData> data = getTestData();
        Iterator<TestData> it = data.iterator();
        TestData s = null;
        while(it.hasNext()) {
        	s = it.next();
            suite.addTest(new JEEFromArchiveHeaderTest(s));
        }
        return suite;
    }
	
	private TestData td;
	public JEEFromArchiveHeaderTest(TestData td) {
		super("testJavaEEFromArchive");
		this.td = td;
	}

	
	

	private static ArrayList<TestData> getTestData() {
		ArrayList<TestData> data = new ArrayList<TestData>();
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
		return data;
	}
	
	public void testJavaEEFromArchive() throws Exception {
		IArchive archive = null;
		try {
			TestData testData = td;
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
