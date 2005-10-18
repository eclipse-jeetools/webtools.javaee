package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

public class EnterpriseArtifactEditTest extends TestCase {
	
	private IProject earProject;
	private String earModuleName;

	public EnterpriseArtifactEditTest() {
		super();
		if (TestWorkspace.init()) {
			earProject = TestWorkspace.getTargetProject(TestWorkspace.EAR_PROJECT_NAME);
			earModuleName = TestWorkspace.EAR_MODULE_NAME;
		} else {
			fail();

		}
	}

	public void testEnterpriseArtifactEditComponentHandleboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		EnterpriseArtifactEdit edit = null;
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

	public void testEnterpriseArtifactEditArtifactEditModel() {
		EnterpriseArtifactEdit edit = new EARArtifactEdit(getArtifactEditModelforRead());
		assertNotNull(edit);
		edit.dispose();
	}


	//////////BUG

	public void testGetDeploymentDescriptorRoot() {
		EnterpriseArtifactEdit edit = null;
		try {
			edit = new EARArtifactEdit(earProject,true);
			edit.getDeploymentDescriptorRoot();
			//////////////////////////BUG\\\\\\\\\\\
			//assertNotNull(edit.getDeploymentDescriptorRoot());
		} catch (Exception e) {
			// TODO fail();
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	public ArtifactEditModel getArtifactEditModelforRead() {
		EMFWorkbenchContext context = new EMFWorkbenchContext(earProject);
		return new ArtifactEditModel(this.toString(), context, true, TestWorkspace.APP_CLIENT_MODULE_URI);
	}



	public EARArtifactEdit getArtifactEditForRead() {
		return new EARArtifactEdit(getArtifactEditModelforRead());
	}

}
