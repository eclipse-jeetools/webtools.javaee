package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.internal.webservice.componentcore.util.WSDDArtifactEdit;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

public class WSDDArtifactEditFVTest extends TestCase {

	private IProject webProject;
	private String webModuleName;
	private String serverContextData = TestWorkspace.WEB_SERVER_CONTEXT_ROOT + "Test";

	public WSDDArtifactEditFVTest() {
		super();
		if (TestWorkspace.init()) {
			webProject = TestWorkspace.getTargetProject(TestWorkspace.WEB_PROJECT_NAME);
			webModuleName = TestWorkspace.WEB_MODULE_NAME;
		} else {
			fail();

		}
	}
	
	public void testCreationDisposeFunction() {
		WSDDArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(webProject,webModuleName);
			edit = WSDDArtifactEdit.getWSDDArtifactEditForWrite(handle);
			edit.addListener(new EditModelListener() {
				public void editModelChanged(EditModelEvent anEvent) {
					pass();
				}
			});
			WebServices client = edit.getWebServices();
			updateClient(client);
			edit.save(new NullProgressMonitor());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
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
		WSDDArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(webProject,webModuleName);
			edit = WSDDArtifactEdit.getWSDDArtifactEditForWrite(handle);
			WebServices web = edit.getWebServices();
			pass(web);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
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
