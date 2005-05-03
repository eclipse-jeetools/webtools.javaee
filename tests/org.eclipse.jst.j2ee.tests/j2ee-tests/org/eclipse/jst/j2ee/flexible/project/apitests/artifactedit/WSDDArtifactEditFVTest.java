package org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit;

import java.io.File;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.internal.webservice.componentcore.util.WSDDArtifactEdit;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelListener;

public class WSDDArtifactEditFVTest extends TestCase {

	private IProject webProject;
	private String webModuleName;
	private String serverContextData = TestWorkspace.WEB_SERVER_CONTEXT_ROOT + "Test";

	public WSDDArtifactEditFVTest() {
		super();
		if (TestWorkspace.init()) {
			webProject = TestWorkspace.getTargetProject(TestWorkspace.WEB_PROJECT_NAME);
			webModuleName = TestWorkspace.WEB_MODULE_NAME;
		} else {
			fail();

		}
	}
	
	public void testCreationDisposeFunction() {
		StructureEdit moduleCore = null;
		WSDDArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WSDDArtifactEdit.getWSDDArtifactEditForWrite(wbComponent);
			edit.addListener(new EditModelListener() {

				public void editModelChanged(EditModelEvent anEvent) {
					pass();

				}
			});
			WebServices client = edit.getWebServices();
			updateClient(client);
			edit.save(new NullProgressMonitor());

		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private void updateClient(WebServices client) {
		client.setDescription(TestWorkspace.FVT_DESCRIPTION);
		client.setDisplayName(TestWorkspace.FVT_DISPLAY_NAME);
		client.setLargeIcon(TestWorkspace.FVT_LARGE_ICON);

	}

	private void pass() {
		assertTrue(true);
	}

	public void testPersistenceFunction() {
		StructureEdit moduleCore = null;
		WSDDArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WSDDArtifactEdit.getWSDDArtifactEditForWrite(wbComponent);
			WebServices web = edit.getWebServices();
			pass(web);
		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

	private static void pass(WebServices webservice) {
		boolean pass = webservice.getDescription().equals(TestWorkspace.FVT_DESCRIPTION) && 
			webservice.getDisplayName().equals(TestWorkspace.FVT_DISPLAY_NAME) &&
			webservice.getLargeIcon().equals(TestWorkspace.FVT_LARGE_ICON);
		assertTrue(pass);
	}

	private void validateResource() {
		StructureEdit moduleCore = null;
		WebArtifactEdit edit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(webProject);
			WorkbenchComponent wbComponent = moduleCore.findComponentByName(webModuleName);
			edit = WebArtifactEdit.getWebArtifactEditForWrite(wbComponent);
			EList resourceList = wbComponent.getResources();
			for (Iterator iter = resourceList.iterator(); iter.hasNext();) {

			}


		} finally {
			if (moduleCore != null) {
				moduleCore.dispose();
				edit.dispose();
			}

		}
	}

}
