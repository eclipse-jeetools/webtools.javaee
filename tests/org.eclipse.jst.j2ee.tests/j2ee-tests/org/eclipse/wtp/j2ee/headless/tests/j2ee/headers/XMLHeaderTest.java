package org.eclipse.wtp.j2ee.headless.tests.j2ee.headers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class XMLHeaderTest extends TestCase {
	private static final String DATA_DIR = "TestData" + java.io.File.separatorChar + "headerParserTestData" + java.io.File.separatorChar;
	
	protected String getDataPath(String shortName) throws Exception {
		HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
		String pluginRelativeFileName = DATA_DIR + java.io.File.separatorChar + shortName;
		return ProjectUtility.getFullFileName(plugin, pluginRelativeFileName);
	}

	public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        ArrayList<TestData> data = getXMLData();
        Iterator<TestData> it = data.iterator();
        TestData s = null;
        while(it.hasNext()) {
        	s = it.next();
            suite.addTest(new XMLHeaderTest(s));
        }
        return suite;
    }
	
	private TestData td;
	public XMLHeaderTest(TestData td) {
		super("testJavaEE");
		this.td = td;
	}

	public void testJavaEE() throws Exception {
		InputStream in = null;
		try {
			TestData testData = td;
			in = new FileInputStream(new File(getDataPath(testData.fileName)));
			JavaEEQuickPeek peek = new JavaEEQuickPeek(in);
			Assert.assertEquals("type does not match: " + testData.toString(), testData.type, peek.getType());
			Assert.assertEquals("modVersion does not match: " + testData.toString(), testData.modVersion, peek.getVersion());
			Assert.assertEquals("eeVersion does not match: " + testData.toString(), testData.eeVersion, peek.getJavaEEVersion());
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	private static ArrayList<TestData> getXMLData() {
		ArrayList<TestData> data = new ArrayList<TestData>();
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
		data.add(new TestData("web30.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_3_0_ID, J2EEVersionConstants.JEE_6_0_ID));
		data.add(new TestData("web31.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_3_1_ID, J2EEVersionConstants.JEE_7_0_ID));
		data.add(new TestData("web40.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_4_0_ID, J2EEVersionConstants.JEE_8_0_ID));
		
		// Test the Web-App DDs without the schema specified

		// Following tests fail
//		data.add(new TestData("web24min.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_4_ID, J2EEVersionConstants.J2EE_1_4_ID));
//		data.add(new TestData("web25min.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_2_5_ID, J2EEVersionConstants.JEE_5_0_ID));
//		data.add(new TestData("web30min.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_3_0_ID, J2EEVersionConstants.JEE_6_0_ID));
//		data.add(new TestData("web31min.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_3_1_ID, J2EEVersionConstants.JEE_7_0_ID));
//		data.add(new TestData("web40min.xml", J2EEVersionConstants.WEB_TYPE, J2EEVersionConstants.WEB_4_0_ID, J2EEVersionConstants.JEE_8_0_ID));
		
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

}
