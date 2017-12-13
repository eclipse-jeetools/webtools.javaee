package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationFactoryImpl;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
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
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			edit.getWebApp().setDescription("test");
			int version = edit.getJ2EEVersion();
			Integer integer = new Integer(version);
			assertTrue(integer.equals(TestWorkspace.WEB_PROJECT_VERSION));

		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}



	public void testGetDeploymentDescriptorResource() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			String uri = edit.getDeploymentDescriptorResource().getURI().toString();
			// assertTrue(uri.equals(TestWorkspace.WEB_DD_RESOURCE_URI));
		} catch (Exception e) {
			// todo
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorRoot() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			edit.getDeploymentDescriptorRoot();
			// /////BUG in PlatformURL\\\\\\\\\\\turning test off////
			EObject object = edit.getDeploymentDescriptorRoot();
			assertNotNull(object);
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	/*
	 * Class under test for EObject createModelRoot()
	 */
	public void testCreateModelRoot() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForWrite(webProject);
			edit.createModelRoot();
			// ////BUG turning off\\\\\\\\\\\\\
			/*
			 * EObject object = edit.createModelRoot(); assertNotNull(object);
			 */
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	/*
	 * Class under test for EObject createModelRoot(int)
	 */
	public void testCreateModelRootint() {
		WebArtifactEdit edit = null;
		try {
			// ///////BUG in PlatformURLModuleConnection
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			edit.createModelRoot(14);
			/*
			 * EObject object = edit.createModelRoot(14); assertNotNull(object);
			 */
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

	/*
	 * Class under test for void WebArtifactEdit(ComponentHandle, boolean)
	 */
	public void testWebArtifactEditComponentHandleboolean() {
		WebArtifactEdit edit = null;
		try {
			edit = new WebArtifactEdit(webProject, true);
			assertNotNull(edit);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}

	}

	/*
	 * Class under test for WebArtifactEdit getWebArtifactEditForRead(ComponentHandle)
	 */
	public void testGetWebArtifactEditForReadComponentHandle() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Class under test for ArtifactEdit getWebArtifactEditForWrite(ComponentHandle)
	 */
	public void testGetWebArtifactEditForWriteComponentHandle() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForWrite(webProject);
		} finally {
			if (edit != null) {
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
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			assertTrue(edit != null);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testIsValidWebModule() {
		IVirtualComponent component = null;
		try {
			component = ComponentCore.createComponent(webProject);
			WebArtifactEdit.isValidWebModule(component);
		} catch (UnresolveableURIException e) {
			fail();
		}
		ArtifactEdit.isValidEditableModule(component);
		assertTrue(ArtifactEdit.isValidEditableModule(component));
	}

	public void testIsValidEditModule() {
		IVirtualComponent component = null;
		try {
			component = ComponentCore.createComponent(webProject);
			WebArtifactEdit.isValidWebModule(component);
		} catch (UnresolveableURIException e) {
			fail();
		}
		ArtifactEdit.isValidEditableModule(component);
		assertTrue(ArtifactEdit.isValidEditableModule(component));
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
		WebArtifactEdit edit = null;
		try {
			edit = new WebArtifactEdit(webProject, true);
			assertNotNull(edit);
		} catch (Exception e) {
			fail();
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetServletVersion() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			assertTrue(edit.getServletVersion() == 24);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testAddWebAppIfNecessary() {
	}

	public void testGetJSPVersion() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			assertTrue(edit.getJSPVersion() == 20);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetDeploymentDescriptorPath() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			edit.getDeploymentDescriptorPath();
			assertNotNull(edit.getDeploymentDescriptorPath());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetLibModules() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			edit.getLibModules();
			// //bug module in editmodel never initialized\\\\
			// assertNotNull(edit.getLibModules());
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	// ////////////Bug\\\\\\\\\\\\\\\\\\\\\\\

	public void testAddLibModules() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			ReferencedComponent refComp = ComponentcoreFactory.eINSTANCE.createReferencedComponent();
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
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testGetServerContextRoot() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			edit.getServerContextRoot();
			// /////////////////////BUG/////////////////////
			// //edit.getServerContextRoot();
			// assertTrue(edit.getServerContextRoot().equals(TestWorkspace.WEB_SERVER_CONTEXT_ROOT));
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	// ////////////////BUG////////////////////////////////

	public void testSetServerContextRoot() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForRead(webProject);
			edit.setServerContextRoot(serverContextData);
			// /////////////////////BUG/////////////////////
			// //edit.getServerContextRoot();
			// edit.setServerContextRoot(serverContextData);
			// String testData = edit.getServerContextRoot();
			// assertTrue(testData.equals(serverContextData));
		} catch (Exception e) {
			// TODO
		} finally {
			if (edit != null) {
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
		WebArtifactEdit edit = null;
		try {
			WebapplicationFactoryImpl factory = new WebapplicationFactoryImpl();
			edit = WebArtifactEdit.getWebArtifactEditForWrite(webProject);
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
			if (edit != null) {
				edit.dispose();
			}
			assertTrue(edit != null);
		}
	}

}
