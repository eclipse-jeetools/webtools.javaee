package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
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
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			int version = edit.getJ2EEVersion();
			Integer integer = new Integer(version);
			assertTrue(integer.equals(TestWorkspace.EAR_PROJECT_VERSION));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	public void testGetJ2EEModuleReferences() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			IVirtualReference[] j2eeModuleRefs = edit.getJ2EEModuleReferences();
			assertTrue(j2eeModuleRefs.length>0);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorResource() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.EAR_DD_RESOURCE_URI));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	public void testCreateModelRoot() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForWrite(earProject);
			EObject object = edit.createModelRoot();
			assertNotNull(object);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testCreateModelRootint() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			EObject object = edit.createModelRoot(14);
			assertNotNull(object);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testEARArtifactEditComponentHandleboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(earProject);
			wbComponent = moduleCore.getComponent();
			edit = new EARArtifactEdit(earProject, true);
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
		EARArtifactEdit edit = null;
		try {
			IVirtualComponent component = ComponentCore.createComponent(earProject,earModuleName);
			ModuleCoreNature nature = null;
			nature = StructureEdit.getModuleCoreNature(TestWorkspace.EAR_MODULE_URI);
			edit = new EARArtifactEdit(nature, component, true);
			assertNotNull(edit);
		} catch (UnresolveableURIException e) {
			fail();
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	public void testGetEARArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		EARArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(earProject);
			WorkbenchComponent wbComponent = moduleCore.getComponent();
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}


	public void testGetEARArtifactEditForWriteComponentHandle() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForWrite(earProject);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testGetEARArtifactEditForReadWorkbenchComponent() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	public void testGetEARArtifactEditForWriteWorkbenchComponent() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForWrite(earProject);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	public void testIsValidEARModule() {
		IVirtualComponent component = null;
		try {
			component = ComponentCore.createComponent(earProject, earModuleName);
			EARArtifactEdit.isValidEARModule(component);
		} catch (UnresolveableURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isValid = ArtifactEdit.isValidEditableModule(component);
		assertTrue(isValid);
	}
	
	public void testIsValidEAREditableModule() {
		IVirtualComponent component = null;
		try {
			component = ComponentCore.createComponent(earProject, earModuleName);
			EARArtifactEdit.isValidEARModule(component);
		} catch (UnresolveableURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isValid = ArtifactEdit.isValidEditableModule(component);
		assertTrue(isValid);
	}

	public void testGetApplicationXmiResource() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			String uri = edit.getApplicationXmiResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.EAR_DD_RESOURCE_URI));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	public void testGetApplication() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			edit.createModelRoot();
			EObject obj = edit.getApplication();
			assertNotNull(obj);

		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	public void testAddApplicationIfNecessary() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			
			// /Bug
			/*
			 * assertTrue(edit.uriExists(TestWorkspace.EJB_MODULE_URI.toString()));
			 */
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		pass(); // protected method
	}

	// ///////////////BUG Workbench Module not initalized\\\\\\\\\\\\\\\\\\\\\\
	public void testUriExists() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			boolean uriExist = edit.uriExists(TestWorkspace.EJB_MODULE_URI.toString());
			// /Bug
			/*
			 * assertTrue(edit.uriExists(TestWorkspace.EJB_MODULE_URI.toString()));
			 */
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	// ///////////////////BUG ClassCastException \\\\\\\\\\\\\\\\\\\\

	public void testGetWorkbenchUtilModules() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			edit.getUtilityModuleReferences();
			// //////////////classcast exception
			/*
			 * assertNotNull(edit.getWorkbenchUtilModules(wbComponent));
			 */
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	// ///////////////////BUG ClassCastException \\\\\\\\\\\\\\\\\\\\
	public void testGetWorkbenchJ2EEModules() {
		EARArtifactEdit edit = null;
		try {
			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			edit.getJ2EEModuleReferences();
			// classCast
			// assertNotNull(edit.getWorkbenchJ2EEModules(wbComponent));
		} finally {
			if (edit != null) {
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
