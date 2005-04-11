package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.applicationclient.modulecore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

import junit.framework.TestCase;

public class ConnectorArtifactEditFVTest extends TestCase {
	private IProject jcaProject;
	private String jcaModuleName;


	public ConnectorArtifactEditFVTest() {
		super();

		if (TestWorkspace.init()) {
			jcaProject = TestWorkspace.getTargetProject(TestWorkspace.JCA_PROJECT_NAME);
			jcaModuleName = TestWorkspace.JCA_MODULE_NAME;
		} else {
			fail();

		}

	}

	public void testCreationDisposeFunction() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(wbComponent);
			edit.addListener(new EditModelListener() {

				public void editModelChanged(EditModelEvent anEvent) {
					pass();

				}
			});
			Connector client = edit.getConnector();
			updateClient(client);
			edit.save(new NullProgressMonitor());

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private void updateClient(Connector client) {
		client.setDescription(TestWorkspace.FVT_DESCRIPTION);
		client.setDisplayName(TestWorkspace.FVT_DISPLAY_NAME);
		client.setLargeIcon(TestWorkspace.FVT_LARGE_ICON);

	}
	private static void pass(Connector client) {
		boolean pass = client.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && client.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) && client.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void pass() {
		assertTrue(true);
	}

	public void testPersistenceFunction() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(wbComponent);
			Connector jca = edit.getConnector();
			pass(jca);


		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private static void pass(ApplicationClient jca) {
		boolean pass = jca.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && jca.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) && jca.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void validateResource() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(wbComponent);
			EList resourceList = wbComponent.getResources();
			for (Iterator iter = resourceList.iterator(); iter.hasNext();) {

			}


		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
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
