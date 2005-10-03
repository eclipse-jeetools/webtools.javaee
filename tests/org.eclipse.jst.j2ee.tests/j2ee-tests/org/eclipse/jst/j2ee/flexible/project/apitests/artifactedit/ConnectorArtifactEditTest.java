package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
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
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.getComponent();
			String version = wbComponent.getComponentType().getVersion();
			assertTrue(version.equals(TestWorkspace.JCA_PROJECT_VERSION));
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorResource() {
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(handle);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.JCA_DD_RESOURCE_URI));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	public void testCreateModelRoot() {
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(handle);
			EObject object = edit.createModelRoot();
			assertNotNull(object);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	public void testCreateModelRootint() {
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(handle);
			EObject object = edit.createModelRoot(14);
			assertNotNull(object);
		} finally {
			if (edit != null) {
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
			wbComponent = moduleCore.getComponent();
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
		ConnectorArtifactEdit edit = null;
		try {
			ModuleCoreNature nature = null;
			nature = StructureEdit.getModuleCoreNature(TestWorkspace.JCA_MODULE_URI);
			IVirtualComponent component = ComponentCore.createComponent(jcaProject,jcaModuleName);
			edit = new ConnectorArtifactEdit(nature, component, true);
			assertNotNull(edit);
		} catch (UnresolveableURIException e) {
			fail();
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

	}


	public void testGetConnectorArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.getComponent();
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
			WorkbenchComponent wbComponent = moduleCore.getComponent();
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
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(handle);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetConnectorArtifactEditForWriteWorkbenchComponent() {
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(handle);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testIsValidConnectorModule() {
		IVirtualComponent component = ComponentCore.createComponent(jcaProject, jcaModuleName);
		assertTrue(ArtifactEdit.isValidEditableModule(component));
	}

	public void testGetConnectorXmiResource() {
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(handle);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.JCA_DD_RESOURCE_URI));

		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testAddApplicationIfNecessary() {
		pass(); // protected method
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
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(handle);
			Object obj = edit.getDeploymentDescriptorRoot();
			assertNotNull(obj);

		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testAddConnectorIfNecessary() {
		pass(); // protected method need to get clarification
	}



	public void testGetConnector() {
		ConnectorArtifactEdit edit = null;
		try {
			ComponentHandle handle = ComponentHandle.create(jcaProject,jcaModuleName);
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(handle);
			Object obj = edit.getConnector();
			assertNotNull(obj);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}
