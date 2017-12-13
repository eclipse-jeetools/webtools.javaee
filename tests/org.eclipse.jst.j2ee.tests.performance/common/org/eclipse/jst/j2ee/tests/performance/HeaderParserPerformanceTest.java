package org.eclipse.jst.j2ee.tests.performance;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

public class HeaderParserPerformanceTest {
	private static final String DATA_DIR = "TestData" + java.io.File.separatorChar + "headerParserTestData" + java.io.File.separatorChar;
	
	public void testSpeed() throws Exception {
		List data = getXMLData();
		long[] times = new long[data.size()];
		InputStream in = null;

		for (int i = 0; i < data.size(); i++) {
			try {
				TestData testData = (TestData) data.get(i);
				in = new FileInputStream(new File(getDataPath(testData.fileName)));
				long start = System.currentTimeMillis();
				JavaEEQuickPeek peek = new JavaEEQuickPeek(in);
				long end = System.currentTimeMillis();
				times[i] = end - start;
				if (times[i] == 0) {
					times[i] = 10; // stub in a minimum time
				}
				Assert.assertEquals(testData.type, peek.getType());
				Assert.assertEquals(testData.modVersion, peek.getVersion());
				Assert.assertEquals(testData.eeVersion, peek.getJavaEEVersion());
			} finally {
				if (in != null) {
					in.close();
				}
			}
		}
		// all times should be the same
		Arrays.sort(times);
		if (times[0] * 2 < times[times.length - 1]) {
			Assert.fail();
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
	}
}
