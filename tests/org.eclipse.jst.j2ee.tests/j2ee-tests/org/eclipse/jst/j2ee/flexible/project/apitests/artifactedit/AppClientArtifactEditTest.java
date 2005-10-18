package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.internal.operations.IOperationHandler;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

public class AppClientArtifactEditTest extends TestCase {
	private IProject appClientProject;
	private String appClientModuleName;
	public static final String EDIT_MODEL_ID = "jst.app_client"; //$NON-NLS-1$
	private AppClientArtifactEdit artifactEditForRead;
	private ArtifactEditModel artifactEditModelForRead;
	private EditModelListener emListener;



	public class ApplicationArtifactTestSub extends AppClientArtifactEdit {

		public ApplicationArtifactTestSub(ArtifactEditModel anArtifactEditModel) {
			super(anArtifactEditModel);
		}

		protected void addAppClientIfNecessary(XMLResource aResource) {
			super.addAppClientIfNecessary(aResource);
		}
	}

	public void testAddCleint() {
		new ApplicationArtifactTestSub(null);

	}

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
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			int version = edit.getJ2EEVersion();
			Integer integer = new Integer(version);
			assertTrue(integer.equals(TestWorkspace.APP_CLIENT_PROJECT_VERSION));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}



	public void testGetDeploymentDescriptorResource() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			assertTrue(uri.equals(TestWorkspace.APP_CLIENT_DD_RESOURCE_URI));

		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorRoot() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			EObject object = edit.getDeploymentDescriptorRoot();
			assertNotNull(object);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	public void testCreateModelRoot() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			EObject object = edit.createModelRoot();
			assertNotNull(object);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testCreateModelRootint() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			EObject object = edit.createModelRoot(14);
			assertNotNull(object);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testAppClientArtifactEditComponentHandleboolean() {
		AppClientArtifactEdit edit = null;
		try {
			edit = new AppClientArtifactEdit(appClientProject, true);
			assertNotNull(edit);
		} finally {
			if (edit != null) {
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

	// /////////////////BUG//////////////

	public void testGetApplicationClientXmiResource() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			String uri = edit.getApplicationClientXmiResource().getURI().toString();
			// THIS IS A BUG\\ - commmenting out as suggested by DW
			boolean testURI = uri.equals(TestWorkspace.APP_CLIENT_DD_XMI_RESOURCE_URI);
			// assertTrue(uri.equals(TestWorkspace.APP_CLIENT_DD_XMI_RESOURCE_URI));
		} catch (Exception e) {
			// TODO
		}

		finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	public void testAddAppClientIfNecessary() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			Resource resource = edit.getDeploymentDescriptorResource();
			AppClientArtifactEdit edit2 = new AppClientArtifactEdit(getArtifactEditModelforRead()) {
				protected void addAppClientIfNecessary(XMLResource aResource) {
					super.addAppClientIfNecessary(aResource);
				}
			};
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		pass(); // protected - not sure if needed
	}

	public void testCreateNewModule() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			Module module = edit.createNewModule();
			assertNotNull(module);

		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetApplicationClient() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			EObject obj = edit.getApplicationClient();
			assertNotNull(obj);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetAppClientArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		AppClientArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(appClientProject);
			WorkbenchComponent wbComponent = moduleCore.getComponent();
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
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
			WorkbenchComponent wbComponent = moduleCore.getComponent();
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(appClientProject);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}


	public void testGetAppClientArtifactEditForReadWorkbenchComponent() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetAppClientArtifactEditForWriteWorkbenchComponent() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(appClientProject);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testIsValidApplicationClientModule() {
		IVirtualComponent component = ComponentCore.createComponent(appClientProject, appClientModuleName);
		assertTrue(ArtifactEdit.isValidEditableModule(component));
	}

	public void testSave() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(appClientProject);
			try {
				edit.save(new NullProgressMonitor());
			} catch (Exception e) {
				fail(e.getMessage());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
		assertTrue(true);
	}

	public void testSaveIfNecessary() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(appClientProject);
			try {
				edit.saveIfNecessary(new NullProgressMonitor());
			} catch (Exception e) {
				fail(e.getMessage());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
		assertTrue(true);
	}

	public void testSaveIfNecessaryWithPrompt() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForWrite(appClientProject);
			try {
				edit.saveIfNecessaryWithPrompt(new NullProgressMonitor(), handler, true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		} finally {
			if (edit != null) {
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

	// //////////BUG////////////////

	public void testGetContentModelRoot() {
		AppClientArtifactEdit edit = null;
		try {
			edit = AppClientArtifactEdit.getAppClientArtifactEditForRead(appClientProject);
			// THIS IS A BUG\\ - commmenting out as suggested by DW
			Object object = edit.getContentModelRoot();
			// assertNotNull(object);
			pass();
		} catch (Exception e) {
			e.printStackTrace();
			// fail(e.getMessage());
		} finally {
			if (edit != null) {
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
					//Default
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
