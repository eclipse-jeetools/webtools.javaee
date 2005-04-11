package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import java.io.File;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.jst.j2ee.modulecore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

import junit.framework.TestCase;

public class EarArtiFactEditFVTest extends TestCase {
	
	

	private IProject earProject;
	private String earModuleName;

	public EarArtiFactEditFVTest() {
		super();

		if (TestWorkspace.init()) {
			earProject = TestWorkspace.getTargetProject(TestWorkspace.EAR_PROJECT_NAME);
			earModuleName = TestWorkspace.EAR_MODULE_NAME;
		} else {
			fail();

		}
	}
	
	public void testCreationDisposeFunction() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForWrite(wbComponent);
			edit.addListener(new EditModelListener() {

				public void editModelChanged(EditModelEvent anEvent) {
					pass();

				}
			});
			Application client = edit.getApplication();
			updateClient(client);
			edit.save(new NullProgressMonitor());

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private void updateClient(Application client) {
		client.setDescription(TestWorkspace.FVT_DESCRIPTION);
		client.setDisplayName(TestWorkspace.FVT_DISPLAY_NAME);
		client.setLargeIcon(TestWorkspace.FVT_LARGE_ICON);

	}
	private static void pass(Application client) {
		boolean pass = client.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && client.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) && client.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void pass() {
		assertTrue(true);
	}

	public void testPersistenceFunction() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForWrite(wbComponent);
			Application ear = edit.getApplication();
			pass(ear);


		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private static void pass(ApplicationClient ear) {
		boolean pass = ear.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && ear.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) && ear.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void validateResource() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForWrite(wbComponent);
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
