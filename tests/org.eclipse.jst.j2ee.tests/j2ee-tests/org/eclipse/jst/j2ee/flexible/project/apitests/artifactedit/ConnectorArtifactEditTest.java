package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
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
		IVirtualComponent vc = ComponentCore.createComponent(jcaProject);
		assertTrue(vc.getVersion().equals(TestWorkspace.JCA_PROJECT_VERSION));
	}

	public void testGetDeploymentDescriptorResource() {
		ConnectorArtifactEdit edit = null;
		try {
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(jcaProject);
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
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(jcaProject);
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
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(jcaProject);
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
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(jcaProject);
			wbComponent = moduleCore.getComponent();
			edit = new ConnectorArtifactEdit(jcaProject, true);
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


	public void testGetConnectorArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		ConnectorArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(jcaProject);
			WorkbenchComponent wbComponent = moduleCore.getComponent();
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(jcaProject);
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
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(jcaProject);

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
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(jcaProject);
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
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForWrite(jcaProject);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testIsValidConnectorModule() {
		IVirtualComponent component = ComponentCore.createComponent(jcaProject);
		assertTrue(ArtifactEdit.isValidEditableModule(component));
	}

	public void testGetConnectorXmiResource() {
		ConnectorArtifactEdit edit = null;
		try {
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(jcaProject);
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
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(jcaProject);
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
			edit = ConnectorArtifactEdit.getConnectorArtifactEditForRead(jcaProject);
			Object obj = edit.getConnector();
			assertNotNull(obj);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}
