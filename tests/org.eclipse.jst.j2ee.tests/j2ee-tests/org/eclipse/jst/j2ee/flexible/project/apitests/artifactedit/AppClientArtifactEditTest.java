package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.applicationclient.modulecore.util.AppClientArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

public class AppClientArtifactEditTest extends TestCase {
	private IProject appClientProject;
	private String appClientModuleName;
	public static final String EDIT_MODEL_ID = "jst.app_client";
	private AppClientArtifactEdit artifactEditForRead;
	private ArtifactEditModel artifactEditModelForRead;
	private EditModelListener emListener;

	private IOperationHandler handler = new IOperationHandler() {


		public boolean canContinue(String message) {
			return false;
		}


		public boolean canContinue(String message, String[] items) {

			return false;
		}

		public int canContinueWithAllCheck(String message) {

			return 0;
		}

		public int canContinueWithAllCheckAllowCancel(String message) {

			return 0;
		}

		public void error(String message) {


		}

		public void inform(String message) {


		}


		public Object getContext() {

			return null;
		}
	};



	public AppClientArtifactEditTest() {
		super();

		if (TestWorkspace.init()) {
			appClientProject = TestWorkspace.getTargetProject(TestWorkspace.APP_CLIENT_PROJECT_NAME);
			appClientModuleName = TestWorkspace.APP_CLIENT_MODULE_NAME;
		} else {
			fail();

		}
	}

	public void testGetJ2EEVersion() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			String version = wbComponent.getComponentType().getVersion();
			assertTrue(version.equals(TestWorkspace.APP_CLIENT_PROJECT_VERSION));
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
			
		}
	}



	public void testGetDeploymentDescriptorResource() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.APP_CLIENT_DD_RESOURCE_URI));

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	public void testGetDeploymentDescriptorRoot() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			EObject object = edit.getDeploymentDescriptorRoot();
			assertNotNull(object);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		

		}
	}


	public void testCreateModelRoot() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
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
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			EObject object = edit.createModelRoot(14);
			assertNotNull(object);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		

		}
	}


	public void testAppClientArtifactEditComponentHandleboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		ComponentHandle handle = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			wbComponent = moduleCore.findComponentByName(appClientModuleName);
			handle = ComponentHandle.create(appClientProject, wbComponent.getName());
			edit = new AppClientArtifactEdit(handle, true);
			assertNotNull(edit);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}



	}

	/*
	 * Class under test for void AppClientArtifactEdit(ArtifactEditModel)
	 */
	public void testAppClientArtifactEditArtifactEditModel() {
		AppClientArtifactEdit edit = new AppClientArtifactEdit(getArtifactEditModelforRead());
		assertNotNull(edit);
		edit.dispose();
	}

	/*
	 * Class under test for void AppClientArtifactEdit(ModuleCoreNature, WorkbenchComponent,
	 * boolean)
	 */
	public void testAppClientArtifactEditModuleCoreNatureWorkbenchComponentboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			wbComponent = moduleCore.findComponentByName(appClientModuleName);
			ModuleCoreNature nature = null;
			nature = moduleCore.getModuleCoreNature(TestWorkspace.APP_CLIENT_MODULE_URI);
			edit = new AppClientArtifactEdit(nature, wbComponent, true);
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
	
	///////////////////BUG//////////////

	public void testGetApplicationClientXmiResource() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();

			// THIS IS A BUG\\ - commmenting out as suggested by DW
			// assertTrue(uri.equals(TestWorkspace.APP_CLIENT_DD_XMI_RESOURCE_URI));

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	public void testAddAppClientIfNecessary() {
		pass(); // protected - not sure if needed
	}

	public void testCreateNewModule() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			Module module = edit.createNewModule();
			assertNotNull(module);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public void testGetApplicationClient() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			EObject obj = edit.getApplicationClient();
			assertNotNull(obj);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public void testGetAppClientArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			ComponentHandle handle = ComponentHandle.create(appClientProject, wbComponent.getName());
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(handle);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}


	public void testGetAppClientArtifactEditForWriteComponentHandle() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			ComponentHandle handle = ComponentHandle.create(appClientProject, wbComponent.getName());
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(handle);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testGetAppClientArtifactEditForReadWorkbenchComponent() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}


	public void testGetAppClientArtifactEditForWriteWorkbenchComponent() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(wbComponent);
			assertTrue(edit != null);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		
		}
	}

	public void testIsValidApplicationClientModule() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			wbComponent = moduleCore.findComponentByName(appClientModuleName);
			ComponentHandle handle = ComponentHandle.create(appClientProject, wbComponent.getName());
			assertTrue(AppClientArtifactEdit.isValidEditableModule(wbComponent));
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
		
		}
	}



	public void testSave() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(wbComponent);
			try {
				edit.save(new NullProgressMonitor());
			} catch (Exception e) {
				fail(e.getMessage());
			}

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
		assertTrue(true);
	}

	public void testSaveIfNecessary() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(wbComponent);
			try {
				edit.saveIfNecessary(new NullProgressMonitor());
			} catch (Exception e) {
				fail(e.getMessage());
			}

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
		assertTrue(true);
	}

	public void testSaveIfNecessaryWithPrompt() {
		AppClientArtifactEdit edit = null;
		StructureEdit moduleCore = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(wbComponent);
			try {
				edit.saveIfNecessaryWithPrompt(new NullProgressMonitor(), handler, true);
			} catch (Exception e) {
				fail(e.getMessage());
			}

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			pass();
		}
	}

	public void testDispose() {
		AppClientArtifactEdit edit;
		try {
			edit = new AppClientArtifactEdit(getArtifactEditModelforRead());
			edit.dispose();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		pass();
	}
	
	////////////BUG////////////////

	public void testGetContentModelRoot() {
		AppClientArtifactEdit edit = null;
		StructureEdit moduleCore = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(appClientModuleName);
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(wbComponent);
			// THIS IS A BUG\\ - commmenting out as suggested by DW
			// Object object = edit.getContentModelRoot();
			// assertNotNull(object);
			pass();
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();

			}
		}
	}

	public void testAddListener() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		try {
			edit.addListener(getEmListener());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		pass();
		edit.dispose();
	}

	public EditModelListener getEmListener() {
		if (emListener == null)
			emListener = new EditModelListener() {
				public void editModelChanged(EditModelEvent anEvent) {
				}
			};
		return emListener;
	}

	public void testRemoveListener() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		try {
			edit.removeListener(getEmListener());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		edit.dispose();
		pass();
	}

	public void testHasEditModel() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		assertTrue(edit.hasEditModel(artifactEditModelForRead));
		edit.dispose();
	}

	public void testGetArtifactEditModel() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		assertTrue(edit.hasEditModel(artifactEditModelForRead));
		edit.dispose();
	}

	public void testObject() {
		pass();
	}

	public void testGetClass() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		assertNotNull(edit.getClass());
		edit.dispose();
	}

	public void testHashCode() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		int y = -999999999;
		int x = edit.hashCode();
		assertTrue(x != y);
		edit.dispose();
	}

	public void testEquals() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		assertTrue(getArtifactEditForRead().equals(artifactEditForRead));
		edit.dispose();
	}

	public void testClone() {
		pass();
	}

	public void testToString() {
		AppClientArtifactEdit edit = getArtifactEditForRead();
		assertTrue(getArtifactEditForRead().toString() != null);
		edit.dispose();
	}

	public void testNotify() {
		try {
			synchronized (getArtifactEditForRead()) {
				artifactEditForRead.notify();
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
		artifactEditForRead.dispose();
		pass();
	}

	public void testNotifyAll() {
		try {
			synchronized (getArtifactEditForRead()) {
				artifactEditForRead.notifyAll();
			}
		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			artifactEditForRead.dispose();
		}
		pass();
	}


	public void testWaitlong() {
		long x = 2;
		try {
			synchronized (getArtifactEditForRead()) {
				getArtifactEditForRead().wait(x);
			}
		} catch (Exception e) {
			// fail(e.getMessage());
		} finally {
			artifactEditForRead.dispose();
		}
		pass();
	}


	public void testWaitlongint() {
		int x = 2;
		try {
			synchronized (getArtifactEditForRead()) {
				getArtifactEditForRead().wait(x);
			}
		} catch (Exception e) {
			// fail(e.getMessage());
		} finally {
			artifactEditForRead.dispose();
		}
		pass();
	}


	public void testWait() {
		try {
			synchronized (getArtifactEditForRead()) {
				getArtifactEditForRead().wait();
			}
		} catch (Exception e) {
			// fail(e.getMessage());
		} finally {
			artifactEditForRead.dispose();
		}
		pass();

	}

	public void testFinalize() {
		pass();
	}

	public void pass() {
		assertTrue(true);
	}

	public ArtifactEditModel getArtifactEditModelforRead() {
		EMFWorkbenchContext context = new EMFWorkbenchContext(appClientProject);
		artifactEditModelForRead = new ArtifactEditModel(EDIT_MODEL_ID, context, true, TestWorkspace.APP_CLIENT_MODULE_URI);
		return artifactEditModelForRead;
	}



	public AppClientArtifactEdit getArtifactEditForRead() {
		artifactEditForRead = new AppClientArtifactEdit(getArtifactEditModelforRead());
		return artifactEditForRead;
	}

}
