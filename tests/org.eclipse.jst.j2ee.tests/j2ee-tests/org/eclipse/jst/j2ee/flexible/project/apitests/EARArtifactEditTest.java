package org.eclipse.jst.j2ee.flexible.project.apitests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEdit;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.UnresolveableURIException;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;

public class EARArtifactEditTest extends TestCase {
	public static final String MODULE__RESOURCE_URI_PROTOCOL = "module:/resource/";
	private IProject project;
	private WorkbenchComponent earWorkbenchModule;
	private ModuleCoreNature moduleCoreNature;
	private ArtifactEditModel artifactEditModel;
	private EARArtifactEdit earArtifactEdit;

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ModuleCore moduleCore = null;
		String projectName = "FlexibleEarModuleProject";
		try {
			// setup instance variables: project
			createProjectWithEARModule(projectName);
			// ear workbenchModule
			moduleCore = ModuleCore.getModuleCoreForWrite(project); 
			earWorkbenchModule = moduleCore.getWorkbenchModules()[0];
			// nature
			moduleCoreNature = (ModuleCoreNature) project.getNature(ModuleCoreNature.MODULE_NATURE_ID);
			
			URI earModuleURI = URI.createURI(MODULE__RESOURCE_URI_PROTOCOL + projectName + IPath.SEPARATOR
					+ projectName + ".ear" + IPath.SEPARATOR + "META-INF/application.xml");
			// artifactEditModel
			artifactEditModel = moduleCoreNature.getArtifactEditModelForRead(earModuleURI, this);
			// earArtifactEdit
			earArtifactEdit = (EARArtifactEdit) artifactEditModel.getAdapter(EARArtifactEdit.ADAPTER_TYPE);

		} catch (CoreException e) {
			e.printStackTrace();
		} finally {
			if (moduleCore != null)
				moduleCore.dispose();
		}

	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		deleteProject();
		project = null;
		earWorkbenchModule = null;
		moduleCoreNature = null;
		artifactEditModel = null;
		earArtifactEdit = null;
		
		super.tearDown();
	}

	/**
	 * Constructor for EARArtifactEditTest.
	 * @param name
	 */
	public EARArtifactEditTest(String name) {
		super(name);
	}

	public void test_getJ2EEVersion() {
		int j2eeVersion = earArtifactEdit.getJ2EEVersion();
		String message = "J2EE version should be larger than zero";
		assertTrue(message, j2eeVersion > 0);
	}

	public void test_getDeploymentDescriptorResource() {
		Resource resource = earArtifactEdit.getDeploymentDescriptorResource();
		assertNotNull(resource);
		
	}

	public void test_getEARArtifactEditForRead() {
		EARArtifactEdit earArtifactEdit = EARArtifactEdit.getEARArtifactEditForRead(earWorkbenchModule);
		assertNotNull(earArtifactEdit);
	}

	public void test_getEARArtifactEditForWrite() {
		EARArtifactEdit earArtifactEdit = EARArtifactEdit.getEARArtifactEditForRead(earWorkbenchModule);
		assertNotNull(earArtifactEdit);
	}

	public void test_isValidEARModule() throws UnresolveableURIException {
		boolean valid = EARArtifactEdit.isValidEditableModule(earWorkbenchModule);
		assertTrue(valid);
	}

	/*
	 * Class under test for void EARArtifactEdit(ArtifactEditModel)
	 */
	public void test_EARArtifactEdit1() {
		EARArtifactEdit edit = new EARArtifactEdit(artifactEditModel);
		assertNotNull(edit);
	}

	/*
	 * Class under test for void EARArtifactEdit(ModuleCoreNature, WorkbenchComponent, boolean)
	 */
	public void test_EARArtifactEdit2() {
		EARArtifactEdit edit = new EARArtifactEdit(moduleCoreNature, earWorkbenchModule, true);
		assertNotNull(edit);
	}

	public void test_getApplicationXmiResource() {
		ApplicationResource appResource = earArtifactEdit.getApplicationXmiResource();
		assertNotNull(appResource);
	}

	public void test_getApplication() {
		Application application = earArtifactEdit.getApplication();
		assertNotNull(application);
	}
	
	private IProject createProjectWithEARModule(String projectName) throws Exception {
		// TODO: need a data model and operation to do that
		project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		if (!project.exists()) {
			// create project with one ear module
		}
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
	
	private void deleteProject() {
		// TODO
	}
}
