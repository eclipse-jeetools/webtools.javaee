package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import java.io.File;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

public class AppClientArtifactEditFVTest extends TestCase {

	private IProject appClientProject;
	private String appClientModuleName;
	

	public AppClientArtifactEditFVTest() {
		super();
		if (TestWorkspace.init()) {
			appClientProject = TestWorkspace.getTargetProject(TestWorkspace.APP_CLIENT_PROJECT_NAME);
			appClientModuleName = TestWorkspace.APP_CLIENT_MODULE_NAME;
		} else {
			fail();

		}
	}

	public void testCreationDisposeFunction() {
		AppClientArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(appClientProject,appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(handle);
			edit.addListener(new EditModelListener() {

				public void editModelChanged(EditModelEvent anEvent) {
					pass();

				}
			});
			ApplicationClient client = edit.getApplicationClient();
			updateClient(client);
			edit.save(new NullProgressMonitor());

		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	private void updateClient(ApplicationClient client) {
		client.setDescription(TestWorkspace.FVT_DESCRIPTION);
		client.setDisplayName(TestWorkspace.FVT_DISPLAY_NAME);
		client.setLargeIcon(TestWorkspace.FVT_LARGE_ICON);

	}

	private void pass() {
		assertTrue(true);
	}

	public void testPersistenceFunction() {
		AppClientArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(appClientProject,appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(handle);
			ApplicationClient appClient = edit.getApplicationClient();
			pass(appClient);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	private static void pass(ApplicationClient appClient) {
		boolean pass = appClient.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && appClient.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) && appClient.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void validateResource() {
		AppClientArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(appClientProject,appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(handle);
			//EList resourceList = wbComponent.getResources();
			//for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
			//}
		} finally {
			if (edit != null) {
				edit.dispose();
			}

		}
	}

	private ComponentResource createResourceComponent() {
		ComponentResource resourceComponent = ComponentcoreFactory.eINSTANCE.createComponentResource();
		File testFile = TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH.toFile();
		if (testFile.exists()) {
			resourceComponent.setRuntimePath(TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH);
			resourceComponent.setSourcePath(TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH);
		} else {
			fail("Missing: TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH");
		}
		return resourceComponent;
	}
}
