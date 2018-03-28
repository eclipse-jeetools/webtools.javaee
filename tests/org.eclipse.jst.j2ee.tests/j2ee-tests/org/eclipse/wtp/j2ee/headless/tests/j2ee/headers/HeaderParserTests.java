package org.eclipse.wtp.j2ee.headless.tests.j2ee.headers;

import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.tests.BaseTestCase;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

public class HeaderParserTests extends BaseTestCase {

	public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(HeaderParserTests.class);
        suite.addTest(Ear50ImportHeaderTest.suite());
        suite.addTest(JEEFromArchiveHeaderTest.suite());
        suite.addTest(NormalizeSchemaLocationTest.suite());
        suite.addTest(XMLHeaderTest.suite());
        return suite;
    }
	
	private static final String DATA_DIR = "TestData" + java.io.File.separatorChar + "headerParserTestData" + java.io.File.separatorChar;
	
	protected String getDataPath(String shortName) throws Exception {
		HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
		String pluginRelativeFileName = DATA_DIR + java.io.File.separatorChar + shortName;
		return ProjectUtility.getFullFileName(plugin, pluginRelativeFileName);
	}

	public void testNull() throws Exception {
		JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(null);
		Assert.assertEquals(JavaEEQuickPeek.UNKNOWN, quickPeek.getType());
		Assert.assertEquals(JavaEEQuickPeek.UNKNOWN, quickPeek.getVersion());
		Assert.assertEquals(JavaEEQuickPeek.UNKNOWN, quickPeek.getJavaEEVersion());
	}
}
