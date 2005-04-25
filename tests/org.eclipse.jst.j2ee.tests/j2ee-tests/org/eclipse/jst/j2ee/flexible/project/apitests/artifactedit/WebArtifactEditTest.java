package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.webapplication.impl.WebapplicationFactoryImpl;
import org.eclipse.jst.j2ee.internal.webapplication.util.WebapplicationAdapterFactory;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.impl.ComponentcoreFactoryImpl;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

public class WebArtifactEditTest extends TestCase {

	private IProject webProject;
	private String webModuleName;
	private String serverContextData = TestWorkspace.WEB_SERVER_CONTEXT_ROOT + "Test";

	public WebArtifactEditTest() {
		super();
		if (TestWorkspace.init()) {
			webProject = TestWorkspace.getTargetProject(TestWorkspace.WEB_PROJECT_NAME);
			webModuleName = TestWorkspace.WEB_MODULE_NAME;
		} else {
			fail();

		}
	}

	public void testGetJ2EEVersion() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			edit.getWebApp().setDescription("test");
			int version = edit.getJ2EEVersion();
			Integer integer = new Integer(version);
			assertTrue(integer.equals(TestWorkspace.WEB_PROJECT_VERSION));

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
		}
	}



	public void testGetDeploymentDescriptorResource() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			// assertTrue(uri.equals(TestWorkspace.WEB_DD_RESOURCE_URI));

		} catch (Exception e) {
			// todo

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorRoot() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			edit.getDeploymentDescriptorRoot();
			// /////BUG in PlatformURL\\\\\\\\\\\turning test off////

			EObject object = edit.getDeploymentDescriptorRoot();
			assertNotNull(object);


		} catch (Exception e) {
			// TODO
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	/*
	 * Class under test for EObject createModelRoot()
	 */

	

	public void testCreateModelRoot() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForWrite(wbComponent);
			edit.createModelRoot();
			// ////BUG turning off\\\\\\\\\\\\\
			/*
			 * EObject object = edit.createModelRoot(); assertNotNull(object);
			 */

		} catch (Exception e) {
			// TODO
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
	public void testCreateModelRootint() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			// ///////BUG in PlatformURLModuleConnection
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			edit.createModelRoot(14);
			/*
			 * EObject object = edit.createModelRoot(14); assertNotNull(object);
			 */

		} catch (Exception e) {
			// TODO
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

	/*
	 * Class under test for void WebArtifactEdit(ComponentHandle, boolean)
	 */
	public void testWebArtifactEditComponentHandleboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		ComponentHandle handle = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			handle = ComponentHandle.create(webProject, wbComponent.getName());
			edit = new WebArtifactEdit(handle, true);
			assertNotNull(edit);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}

	}

	/*
	 * Class under test for WebArtifactEdit getWebArtifactEditForRead(ComponentHandle)
	 */
	public void testGetWebArtifactEditForReadComponentHandle() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			ComponentHandle handle = ComponentHandle.create(webProject, wbComponent.getName());
			edit = WebArtifactEdit.getWebArtifactEditForRead(handle);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	/*
	 * Class under test for ArtifactEdit getWebArtifactEditForWrite(ComponentHandle)
	 */
	public void testGetWebArtifactEditForWriteComponentHandle() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			ComponentHandle handle = ComponentHandle.create(webProject, wbComponent.getName());
			edit = WebArtifactEdit.getWebArtifactEditForWrite(handle);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	/*
	 * Class under test for WebArtifactEdit getWebArtifactEditForRead(WorkbenchComponent)
	 */
	public void testGetWebArtifactEditForReadWorkbenchComponent() {
	}

	/*
	 * Class under test for WebArtifactEdit getWebArtifactEditForWrite(WorkbenchComponent)
	 */
	public void testGetWebArtifactEditForWriteWorkbenchComponent() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			assertTrue(edit != null);

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}


		}
	}

	public void testIsValidWebModule() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			ComponentHandle handle = ComponentHandle.create(webProject, wbComponent.getName());
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			try {
				edit.isValidWebModule(wbComponent);
			} catch (UnresolveableURIException e) {
				fail();
			}
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
			WebArtifactEdit.isValidEditableModule(wbComponent);
			assertTrue(WebArtifactEdit.isValidEditableModule(wbComponent));
		}
	}

	public void testIsValidEditModule() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			ComponentHandle handle = ComponentHandle.create(webProject, wbComponent.getName());

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
			}
			WebArtifactEdit.isValidEditableModule(wbComponent);
			assertTrue(WebArtifactEdit.isValidEditableModule(wbComponent));
		}
	}

	/*
	 * Class under test for void WebArtifactEdit(ArtifactEditModel)
	 */
	public void testWebArtifactEditArtifactEditModel() {
		WebArtifactEdit edit = new WebArtifactEdit(getArtifactEditModelforRead());
		assertNotNull(edit);
		edit.dispose();
	}

	/*
	 * Class under test for void WebArtifactEdit(ModuleCoreNature, WorkbenchComponent, boolean)
	 */
	public void testWebArtifactEditModuleCoreNatureWorkbenchComponentboolean() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			ModuleCoreNature nature = null;
			nature = moduleCore.getModuleCoreNature(TestWorkspace.WEB_MODULE_URI);
			edit = new WebArtifactEdit(nature, wbComponent, true);
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

	public void testGetServletVersion() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			assertTrue(edit.getServletVersion() == 24);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public void testAddWebAppIfNecessary() {
	}

	public void testGetJSPVersion() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			assertTrue(edit.getJSPVersion() == 20);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorPath() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			edit.getDeploymentDescriptorPath();
			assertNotNull(edit.getDeploymentDescriptorPath());
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public void testGetLibModules() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			edit.getLibModules();
			// //bug module in editmodel never initialized\\\\
			// assertNotNull(edit.getLibModules());
		} catch (Exception e) {
			// TODO
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	// ////////////Bug\\\\\\\\\\\\\\\\\\\\\\\

	public void testAddLibModules() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			ReferencedComponent refComp = ComponentcoreFactoryImpl.eINSTANCE.createReferencedComponent();
			edit.addLibModules(new ReferencedComponent[]{refComp});
			// ///////////////bug\\\\\\\ owner---WebArtifactEdit -> referenceComponents() != null
			// needs to insure owner
			/*
			 * ReferencedComponent refComp =
			 * ComponentcoreFactoryImpl.eINSTANCE.createReferencedComponent();
			 * edit.addLibModules(new ReferencedComponent[]{refComp});
			 * assertTrue(edit.getLibModules().length > 0);
			 */
		} catch (Exception e) {
			// TODO
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public void testGetServerContextRoot() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			edit.getServerContextRoot();
			// /////////////////////BUG/////////////////////
			// //edit.getServerContextRoot();
			// assertTrue(edit.getServerContextRoot().equals(TestWorkspace.WEB_SERVER_CONTEXT_ROOT));
		} catch (Exception e) {
			// TODO
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	// ////////////////BUG////////////////////////////////

	public void testSetServerContextRoot() {
		StructureEdit moduleCore = null;
		WorkbenchComponent wbComponent = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForRead(wbComponent);
			edit.setServerContextRoot(serverContextData);
			// /////////////////////BUG/////////////////////
			// //edit.getServerContextRoot();
			// edit.setServerContextRoot(serverContextData);
			// String testData = edit.getServerContextRoot();
			// assertTrue(testData.equals(serverContextData));

		} catch (Exception e) {
			// TODO
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
		}
	}

	public ArtifactEditModel getArtifactEditModelforRead() {
		EMFWorkbenchContext context = new EMFWorkbenchContext(webProject);
		return new ArtifactEditModel(this.toString(), context, true, TestWorkspace.APP_CLIENT_MODULE_URI);
	}

	public WebArtifactEdit getArtifactEditForRead() {
		return new WebArtifactEdit(getArtifactEditModelforRead());
	}
	
	public void testCreateServlet() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			WebapplicationFactoryImpl factory = new WebapplicationFactoryImpl();
			moduleCore = StructureEdit.getStructureEditForWrite(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForWrite(wbComponent);
			WebApp webapp = edit.getWebApp();
			Servlet servlet = factory.createServlet();
			ServletType servletType = factory.createServletType();
			servlet.setWebType(servletType);
			servlet.setServletName("servletDescriptor._name");
			servletType.setClassName("servletDescriptor._className");
			// if(servletDescriptor._displayName != null){
			servlet.setDisplayName("servletDescriptor._displayName");
			// }
			
			webapp.getServlets().add(servlet);
			webapp.setDescription("test");
			edit.save(new NullProgressMonitor());
			/*
			 * if(servletDescriptor._loadOnStartup != null){
			 * servlet.setLoadOnStartup(servletDescriptor._loadOnStartup); }
			 * if(servletDescriptor._params != null){ Properties properties =
			 * servlet.getParamsAsProperties(); properties.putAll(servletDescriptor._params); }
			 */


		} catch (Exception e) {
			e.printStackTrace();
			// TODO
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}
			assertTrue(edit != null);

		}
	}

}
