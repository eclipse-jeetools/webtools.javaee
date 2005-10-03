package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.web.deployables.J2EEFlexProjWebDeployable;
import org.eclipse.jst.j2ee.internal.web.deployables.WebDeployableFactory;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;
import org.eclipse.wst.server.core.model.IModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;

public class WebArtifactEditFVTest extends TestCase {

	private IProject webProject;
	private String webModuleName;
//	private String serverContextData = TestWorkspace.WEB_SERVER_CONTEXT_ROOT + "Test"; //$NON-NLS-1$

	public WebArtifactEditFVTest() {
		super();
		if (TestWorkspace.init()) {
			webProject = TestWorkspace.getTargetProject(TestWorkspace.WEB_PROJECT_NAME);
			webModuleName = TestWorkspace.WEB_MODULE_NAME;
		} else {
			fail();

		}
	}
	
	public void testCreationDisposeFunction() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForWrite(webProject);
			edit.addListener(new EditModelListener() {
				public void editModelChanged(EditModelEvent anEvent) {
					pass();
				}
			});
			WebApp client = edit.getWebApp();
			updateClient(client);
			edit.save(new NullProgressMonitor());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	public void testDeployableResourceGather() {
		IVirtualComponent webComp = ComponentCore.createComponent(webProject,webModuleName);
		J2EEFlexProjWebDeployable deployable = new J2EEFlexProjWebDeployable(webProject, WebDeployableFactory.ID, webComp);
		try {
			IModuleResource[] resources = deployable.members();
			assertTrue(resources.length>1);
			int numOfModuleResourceFolders = 0;
			for (int i=0; i<resources.length; i++) {
				if (resources[i] instanceof IModuleFolder)
					numOfModuleResourceFolders++;
			}
			assertTrue(numOfModuleResourceFolders==2);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private void updateClient(WebApp client) {
		client.setDescription(TestWorkspace.FVT_DESCRIPTION);
		client.setDisplayName(TestWorkspace.FVT_DISPLAY_NAME);
		client.setLargeIcon(TestWorkspace.FVT_LARGE_ICON);
	}

	private void pass() {
		assertTrue(true);
	}

	public void testPersistenceFunction() {
		WebArtifactEdit edit = null;
		try {
			edit = WebArtifactEdit.getWebArtifactEditForWrite(webProject);
			WebApp web = edit.getWebApp();
			
			
			Filter filter = WebapplicationFactory.eINSTANCE.createFilter();
	        filter.setName("WoohooFilter"); //$NON-NLS-1$
	        filter.setFilterClassName("wtp.test.WhooHoo"); //$NON-NLS-1$
	        
	        ParamValue value = CommonFactory.eINSTANCE.createParamValue();
	        value.setName("Param1"); //$NON-NLS-1$
	        value.setValue("Value1"); //$NON-NLS-1$
	        List initParams = new ArrayList();
	        initParams.add(value);
	        
	        if (initParams != null) {
	            filter.getInitParamValues().addAll(initParams);
	        } 

	        EList filters = web.getFilters();                                   
	        
	        filters.add(filter);
			edit.saveIfNecessary(null);
			
			pass(web);
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	private static void pass(WebApp web) {
		boolean pass = web.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && web.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) && web.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

//	private void validateResource() {
//		WebArtifactEdit edit = null;
//		try {
//			ComponentHandle handle = ComponentHandle.create(webProject,webModuleName);
//			edit = WebArtifactEdit.getWebArtifactEditForWrite(handle);
////			EList resourceList = wbComponent.getResources();
////			for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
////			}
//		} finally {
//			if (edit != null) {
//				edit.dispose();
//			}
//		}
//	}

//	private ComponentResource createResourceComponent() {
//		ComponentResource resourceComponent = ComponentcoreFactory.eINSTANCE.createComponentResource();
//		File testFile = TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH.toFile();
//		if (testFile.exists()) {
//			resourceComponent.setRuntimePath(TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH);
//			resourceComponent.setSourcePath(TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH);
//		} else {
//			fail("Missing: TestWorkspace.ARTIFACT_EDIT_FVT_RESOURCE_PATH"); //$NON-NLS-1$
//		}
//		return resourceComponent;
//	}

}
