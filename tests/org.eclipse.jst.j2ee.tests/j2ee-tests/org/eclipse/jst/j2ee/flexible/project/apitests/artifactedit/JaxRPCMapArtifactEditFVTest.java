package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.webservice.componentcore.util.JaxRPCMapArtifactEdit;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JaxRPCMapArtifactEditFVTest extends TestCase {

	private IProject webProject;
	private String webModuleName;
	private String serverContextData = TestWorkspace.WEB_SERVER_CONTEXT_ROOT + "Test";

	public JaxRPCMapArtifactEditFVTest() {
		super();
		if (TestWorkspace.init()) {
			webProject = TestWorkspace.getTargetProject(TestWorkspace.WEB_PROJECT_NAME);
			webModuleName = TestWorkspace.WEB_MODULE_NAME;
		} else {
			fail();

		}
	}
	
	private void updateClient(WebServices client) {
		client.setDescription(TestWorkspace.FVT_DESCRIPTION);
		client.setDisplayName(TestWorkspace.FVT_DISPLAY_NAME);
		client.setLargeIcon(TestWorkspace.FVT_LARGE_ICON);

	}

	private void pass() {
		assertTrue(true);
	}

	public void testPersistenceFunction() {
		JaxRPCMapArtifactEdit jaxedit = null;
		WebArtifactEdit webedit = null;
		
			IVirtualComponent comp = ComponentCore.createComponent(webProject,webModuleName);
			
			jaxedit = JaxRPCMapArtifactEdit.getJaxRPCMapArtifactEditForRead(comp);
			jaxedit.getDeploymentDescriptorRoot("WebArtifactEditTest/WebArtifactEditModule/WebContent/WEB-INF/testmap.xml");
			jaxedit.dispose();
			webedit = WebArtifactEdit.getWebArtifactEditForWrite(comp);
			WebApp webapp = webedit.getWebApp();
			webedit.saveIfNecessary(null);
			webedit.dispose();
	}

	private static void pass(WebServices webservice) {
		boolean pass = webservice.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && 
			webservice.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) &&
			webservice.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void validateResource() {
		WebArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(webProject,webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForWrite(handle);
//			EList resourceList = wbComponent.getResources();
//			for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
//			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}

		}
	}

}
