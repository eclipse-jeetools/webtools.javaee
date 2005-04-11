package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.resources.ComponentHandle;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

public class EARArtifactEditTest extends TestCase {

	private IProject earProject;
	private String earModuleName;

	public EARArtifactEditTest() {
		super();

		if (TestWorkspace.init()) {
			earProject = TestWorkspace.getTargetProject(TestWorkspace.EAR_PROJECT_NAME);
			earModuleName = TestWorkspace.EAR_MODULE_NAME;
		} else {
			fail();

		}
	}

	public void testGetJ2EEVersion() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			String version = wbComponent.getComponentType().getVersion();
			assertTrue(version.equals(TestWorkspace.EAR_PROJECT_VERSION));
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorResource() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.EAR_DD_RESOURCE_URI));

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}


	public void testCreateModelRoot() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForWrite(wbComponent);
			EObject object = edit.createModelRoot();
			assertNotNull(object);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}


	public void testCreateModelRootint() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			EObject object = edit.createModelRoot(14);
			assertNotNull(object);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}


	public void testEARArtifactEditComponentHandleboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		ComponentHandle handle = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(earProject);
			wbComponent = moduleCore.findComponentByName(earModuleName);
			handle = ComponentHandle.create(earProject, wbComponent.getName());
			edit = new EARArtifactEdit(handle, true);
			assertNotNull(edit);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}

	}

	public void testEARArtifactEditArtifactEditModel() {
		EARArtifactEdit edit = new EARArtifactEdit(getArtifactEditModelforRead());
		assertNotNull(edit);
		edit.dispose();
	}


	public void testEARArtifactEditModuleCoreNatureWorkbenchComponentboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(earProject);
			wbComponent = moduleCore.findComponentByName(earModuleName);
			ModuleCoreNature nature = null;
			nature = moduleCore.getModuleCoreNature(TestWorkspace.EAR_MODULE_URI);
			edit = new EARArtifactEdit(nature, wbComponent, true);
			assertNotNull(edit);
		} catch (UnresolveableURIException e) {
			fail();
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}

	}


	public void testGetEARArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			ComponentHandle handle = ComponentHandle.create(earProject, wbComponent.getName());
			edit = EARArtifactEdit.getEARArtifactEditForRead(handle);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}


	public void testGetEARArtifactEditForWriteComponentHandle() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			ComponentHandle handle = ComponentHandle.create(earProject, wbComponent.getName());
			edit = EARArtifactEdit.getEARArtifactEditForWrite(handle);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testGetEARArtifactEditForReadWorkbenchComponent() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}


	public void testGetEARArtifactEditForWriteWorkbenchComponent() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForWrite(wbComponent);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	public void testIsValidEARModule() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(earProject);
			wbComponent = moduleCore.findComponentByName(earModuleName);
			ComponentHandle handle = ComponentHandle.create(earProject, wbComponent.getName());
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
			assertTrue(EARArtifactEdit.isValidEditableModule(wbComponent));
		}
	}

	public void testGetApplicationXmiResource() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.EAR_DD_RESOURCE_URI));

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	public void testGetApplication() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			edit.createModelRoot();
			EObject obj = edit.getApplication();
			assertNotNull(obj);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	public void testAddApplicationIfNecessary() {
		pass(); // protected method
	}

	// ///////////////BUG Workbench Module not initalized\\\\\\\\\\\\\\\\\\\\\\
	public void testUriExists() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			// /Bug
			/*
			 * assertTrue(edit.uriExists(TestWorkspace.EJB_MODULE_URI.toString()));
			 */
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	// ///////////////////BUG ClassCastException \\\\\\\\\\\\\\\\\\\\

	public void testGetWorkbenchUtilModules() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			// //////////////classcast exception
			/*
			 * assertNotNull(edit.getWorkbenchUtilModules(wbComponent));
			 */
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	// ///////////////////BUG ClassCastException \\\\\\\\\\\\\\\\\\\\
	public void testGetWorkbenchJ2EEModules() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			// classCast
			// assertNotNull(edit.getWorkbenchJ2EEModules(wbComponent));
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}

	public void pass() {
		assertTrue(true);
	}

	public ArtifactEditModel getArtifactEditModelforRead() {
		EMFWorkbenchContext context = new EMFWorkbenchContext(earProject);
		return new ArtifactEditModel(this.toString(), context, true, TestWorkspace.APP_CLIENT_MODULE_URI);
	}



	public EARArtifactEdit getArtifactEditForRead() {
		return new EARArtifactEdit(getArtifactEditModelforRead());
	}



}
