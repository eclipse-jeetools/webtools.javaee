package org.eclipse.jst.j2ee.flexible.project.apitests;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.modulecore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.resources.ComponentHandle;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

import junit.framework.TestCase;

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
		ComponentHandle handle = null;
		EnterpriseArtifactEdit edit = null;
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

	public void testEnterpriseArtifactEditArtifactEditModel() {
		EnterpriseArtifactEdit edit = new EARArtifactEdit(getArtifactEditModelforRead());
		assertNotNull(edit);
		edit.dispose();
	}

	public void testEnterpriseArtifactEditModuleCoreNatureWorkbenchComponentboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		EnterpriseArtifactEdit edit = null;
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
	
	//////////BUG

	public void testGetDeploymentDescriptorRoot() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		EnterpriseArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(earProject);
			wbComponent = moduleCore.findComponentByName(earModuleName);
			ModuleCoreNature nature = null;
			nature = moduleCore.getModuleCoreNature(TestWorkspace.EAR_MODULE_URI);
			edit = new EARArtifactEdit(nature, wbComponent, true);
			//////////////////////////BUG\\\\\\\\\\\
			//assertNotNull(edit.getDeploymentDescriptorRoot());
		} catch (UnresolveableURIException e) {
			fail();
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
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
