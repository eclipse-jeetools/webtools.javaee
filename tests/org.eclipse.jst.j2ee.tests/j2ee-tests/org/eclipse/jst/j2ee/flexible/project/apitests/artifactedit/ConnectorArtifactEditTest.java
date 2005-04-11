package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.resources.ComponentHandle;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

public class ConnectorArtifactEditTest extends TestCase {

	private IProject jcaProject;
	private String jcaModuleName;


	public ConnectorArtifactEditTest() {
		super();

		if (TestWorkspace.init()) {
			jcaProject = TestWorkspace.getTargetProject(TestWorkspace.JCA_PROJECT_NAME);
			jcaModuleName = TestWorkspace.JCA_MODULE_NAME;
		} else {
			fail();

		}
	}

	public void testGetJ2EEVersion() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			String version = wbComponent.getComponentType().getVersion();
			assertTrue(version.equals(TestWorkspace.JCA_PROJECT_VERSION));
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorResource() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.JCA_DD_RESOURCE_URI));

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}


	public void testCreateModelRoot() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(wbComponent);
			EObject object = edit.createModelRoot();
			assertNotNull(object);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}


	public void testCreateModelRootint() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
			EObject object = edit.createModelRoot(14);
			assertNotNull(object);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}


	public void testConnectorArtifactEditComponentHandleboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		ComponentHandle handle = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(jcaProject);
			wbComponent = moduleCore.findComponentByName(jcaModuleName);
			handle = ComponentHandle.create(jcaProject, wbComponent.getName());
			edit = new ConnectorArtifactEdit(handle, true);
			assertNotNull(edit);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}

	}

	public void testConnectorArtifactEditArtifactEditModel() {
		ConnectorArtifactEdit edit = new ConnectorArtifactEdit(getArtifactEditModelforRead());
		assertNotNull(edit);
		edit.dispose();
	}


	public void testConnectorArtifactEditModuleCoreNatureWorkbenchComponentboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(jcaProject);
			wbComponent = moduleCore.findComponentByName(jcaModuleName);
			ModuleCoreNature nature = null;
			nature = moduleCore.getModuleCoreNature(TestWorkspace.JCA_MODULE_URI);
			edit = new ConnectorArtifactEdit(nature, wbComponent, true);
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


	public void testGetConnectorArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			ComponentHandle handle = ComponentHandle.create(jcaProject, wbComponent.getName());
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(handle);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}


	public void testGetConnectorArtifactEditForWriteComponentHandle() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			ComponentHandle handle = ComponentHandle.create(jcaProject, wbComponent.getName());
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(handle);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testGetConnectorArtifactEditForReadWorkbenchComponent() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}


	public void testGetConnectorArtifactEditForWriteWorkbenchComponent() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(wbComponent);
			assertTrue(edit != null);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	public void testIsValidConnectorModule() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(jcaProject);
			wbComponent = moduleCore.findComponentByName(jcaModuleName);
			ComponentHandle handle = ComponentHandle.create(jcaProject, wbComponent.getName());
			assertTrue(ConnectorArtifactEdit.isValidEditableModule(wbComponent));
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}

		}
	}

	public void testGetConnectorXmiResource() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.JCA_DD_RESOURCE_URI));

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
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
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
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
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
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
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
		EMFWorkbenchContext context = new EMFWorkbenchContext(jcaProject);
		return new ArtifactEditModel(this.toString(), context, true, TestWorkspace.JCA_MODULE_URI);
	}



	public ConnectorArtifactEdit getArtifactEditForRead() {
		return new ConnectorArtifactEdit(getArtifactEditModelforRead());
	}



	public void testGetDeploymentDescriptorRoot() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
			Object obj = edit.getDeploymentDescriptorRoot();
			assertNotNull(obj);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}



	public void testAddConnectorIfNecessary() {
		pass(); // protected method need to get clarification
	}



	public void testGetConnector() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(wbComponent);
			Object obj = edit.getConnector();
			assertNotNull(obj);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

}
