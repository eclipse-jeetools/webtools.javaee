package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import java.io.File;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

public class EJBArtifactEditFVTest extends TestCase {
	
	private IProject ejbProject;
	private String ejbModuleName;

	public EJBArtifactEditFVTest() {
		super();

		if (TestWorkspace.init()) {
			ejbProject = TestWorkspace.getTargetProject(TestWorkspace.EJB_PROJECT_NAME);
			ejbModuleName = TestWorkspace.EJB_MODULE_NAME;
		} else {
			fail();

		}
	}
	
	public void testCreationDisposeFunction() {
		StructureEdit moduleCore = null;
		EJBArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(ejbProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(ejbModuleName);
			edit = EJBArtifactEdit.getEJBArtifactEditForWrite(wbComponent);
			edit.addListener(new EditModelListener() {

				public void editModelChanged(EditModelEvent anEvent) {
					pass();

				}
			});
			EJBJar client = edit.getEJBJar();
			updateClient(client);
			edit.save(new NullProgressMonitor());

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private void updateClient(EJBJar client) {
		client.setDescription(TestWorkspace.FVT_DESCRIPTION);
		client.setDisplayName(TestWorkspace.FVT_DISPLAY_NAME);
		client.setLargeIcon(TestWorkspace.FVT_LARGE_ICON);

	}

	private void pass() {
		assertTrue(true);
	}

	public void testPersistenceFunction() {
		StructureEdit moduleCore = null;
		EJBArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(ejbProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(ejbModuleName);
			edit = EJBArtifactEdit.getEJBArtifactEditForWrite(wbComponent);
			EJBJar ejb = edit.getEJBJar();
			pass(ejb);


		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private static void pass(EJBJar ejb) {
		boolean pass = ejb.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && ejb.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) && ejb.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void validateResource() {
		StructureEdit moduleCore = null;
		EJBArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(ejbProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(ejbModuleName);
			edit = EJBArtifactEdit.getEJBArtifactEditForWrite(wbComponent);
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
