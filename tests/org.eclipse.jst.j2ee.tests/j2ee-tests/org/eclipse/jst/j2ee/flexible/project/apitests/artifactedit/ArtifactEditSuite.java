/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ArtifactEditSuite extends TestSuite {

	public static Test suite() {
		return new ArtifactEditSuite();
	}

	public ArtifactEditSuite() {
		super("All Artifact Edit Tests");
		addTestSuite(AppClientArtifactEditFVTest.class );
		addTestSuite(AppClientArtifactEditTest.class );
		addTestSuite(ConnectorArtifactEditFVTest.class );
		addTestSuite(ConnectorArtifactEditTest.class );
		addTestSuite(EarArtiFactEditFVTest.class );
		addTestSuite(EARArtifactEditTest.class );
		addTestSuite(EJBArtifactEditFVTest.class );
		addTestSuite(EJBArtifactEditTest.class );
		addTestSuite(EnterpriseArtifactEditTest.class );
		addTest(J2EEArtifactEditAPIAllTest.suite() );
		addTestSuite(JaxRPCMapArtifactEditFVTest.class );
		addTestSuite(WebArtifactEditFVTest.class );
		addTestSuite(WebArtifactEditTest.class );
		addTestSuite(WSDDArtifactEditFVTest.class );
	}

}
