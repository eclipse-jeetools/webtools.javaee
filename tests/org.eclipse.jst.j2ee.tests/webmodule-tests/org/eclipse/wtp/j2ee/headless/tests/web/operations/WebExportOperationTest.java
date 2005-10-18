/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.WARFileImpl;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.headless.tests.savestrategy.ModuleImportOperationTestCase;
import org.eclipse.wtp.headless.tests.savestrategy.WebImportOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebExportOperationTest extends ModuleExportOperationTestCase {

	protected boolean excludeCompileJsp = false;

	public WebExportOperationTest(String name) {
		super(name);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(WebExportOperationTest.class);
		return suite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.OperationTestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		excludeCompileJsp = false;
	}

	public void testExcludeCompileJspOn() throws Exception {
		excludeCompileJsp = true;
		testAllExportTestCases();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getModelInstance()
	 */
	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new WebComponentExportDataModelProvider());
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTestCase getImportTestCase() {
		return new WebImportOperationTest("");
	}
	protected IProject[] getExportableProjects() throws Exception {
		IProject[] projs = super.getExportableProjects();
		List filteredProjs = new ArrayList();
		for (int i = 0; i < projs.length; i++) {
			IProject project = projs[i];
			IVirtualComponent comp = ComponentCore.createComponent(project);
			if (J2EEProjectUtilities.isDynamicWebProject(comp.getProject()))
				filteredProjs.add(project);
		}
		return (IProject[]) filteredProjs.toArray(new IProject[filteredProjs.size()]);
		
	}

	/**
	 * @return
	 */
	public String getModuleExportFileExt() {
		return ".war";
	}
	
	public void testExport(IVirtualComponent component, String filename) throws Exception {
		super.testExport(component,filename);
		//verify web dd
		testDDExported(component);
	}
	
	protected void testDDExported(IVirtualComponent component) throws Exception {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(component);
			WARFileImpl archive = (WARFileImpl) webEdit.asArchive(true);
			try {
			Resource res = archive.getDeploymentDescriptorResource();
			} catch (Exception e) {
				fail("Web deployment descriptor is null.");
			}
		} finally {
			if (webEdit !=null)
				webEdit.dispose();
		}
	}

}
