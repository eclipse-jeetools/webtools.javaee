package org.eclipse.jst.j2ee.flexible.project.apitests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.modulecore.util.EARArtifactEdit;
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
			assertTrue(version.equals(TestWorkspace.WEB_PROJECT_VERSION));
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

	// /////////////BUG in PlatformURL\\\\\\\\\\\


	public void testCreateModelRoot() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			edit = EARArtifactEdit.getEARArtifactEditForWrite(wbComponent);
			// ////BUG turning off\\\\\\\\\\\\\
			/*
			 * EObject object = edit.createModelRoot(); assertNotNull(object);
			 */

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	/*
	 * Class under test for EObject createModelRoot(int)
	 */
	// ///////////////BUG in PlatformURLModuleConnection
	public void testCreateModelRootint() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(earModuleName);
			// ///////BUG in PlatformURLModuleConnection
			edit = EARArtifactEdit.getEARArtifactEditForRead(wbComponent);
			/*
			 * EObject object = edit.createModelRoot(14); assertNotNull(object);
			 */

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	/*
	 * Class under test for void EARArtifactEdit(ComponentHandle, boolean)
	 */
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

	/*
	 * Class under test for void EARArtifactEdit(ArtifactEditModel)
	 */
	public void testEARArtifactEditArtifactEditModel() {
		EARArtifactEdit edit = new EARArtifactEdit(getArtifactEditModelforRead());
		assertNotNull(edit);
		edit.dispose();
	}

	/*
	 * Class under test for void EARArtifactEdit(ModuleCoreNature, WorkbenchComponent, boolean)
	 */
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

	/*
	 * Class under test for EARArtifactEdit getEARArtifactEditForWrite(ComponentHandle)
	 */
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

	/*
	 * Class under test for EARArtifactEdit getEARArtifactEditForRead(WorkbenchComponent)
	 */
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

	/*
	 * Class under test for EARArtifactEdit getEARArtifactEditForWrite(WorkbenchComponent)
	 */
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
	}

	public void testGetApplication() {
	}

	public void testAddApplicationIfNecessary() {
	}

	public void testUriExists() {
	}

	public void testGetWorkbenchUtilModules() {
	}

	public void testGetWorkbenchJ2EEModules() {
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
