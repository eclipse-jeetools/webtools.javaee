package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.frameworks.artifactedit.tests.ArtifactEditAPITests;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class J2EEArtifactEditAPIAllTest extends TestSuite {



	public static Test suite() {
		return new ArtifactEditAPITests();
	}

	public J2EEArtifactEditAPIAllTest() {
		super();
		addTest(new SimpleTestSuite(AppClientArtifactEditTest.class));
		//addTest(new SimpleTestSuite(AppClientArtifactEditFVTest.class));
		addTest(new SimpleTestSuite(ConnectorArtifactEditTest.class));
		//addTest(new SimpleTestSuite(ConnectorArtifactEditFVTest.class));
		addTest(new SimpleTestSuite(EARArtifactEditTest.class));
		//addTest(new SimpleTestSuite(EarArtiFactEditFVTest.class));
		addTest(new SimpleTestSuite(WebArtifactEditTest.class));
		//addTest(new SimpleTestSuite(WebArtifactEditFVTest.class));
		addTest(new SimpleTestSuite(EJBArtifactEditTest.class));
		//addTest(new SimpleTestSuite(EJBArtifactEditFVTest.class));
		addTest(new SimpleTestSuite(EnterpriseArtifactEditTest.class));

	}

}
