/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.servlet.tomcat.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.headless.tests.savestrategy.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebExportOperationTomcatTest extends ModuleExportOperationTestCase {

	protected boolean excludeCompileJsp = false;

	public WebExportOperationTomcatTest(String name) {
		super(name);
	}

	public static Test suite() {
		return new SimpleTestSuite(WebExportOperationTomcatTest.class);
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
	protected IProject[] getExportableProjects() throws Exception {
		IProject[] projs = super.getExportableProjects();
		List filteredProjs = new ArrayList();
		for (int i = 0; i < projs.length; i++) {
			IProject project = projs[i];
			IVirtualComponent comp = ComponentCore.createComponent(project);
			if ((WebArtifactEdit.TYPE_ID).equals(comp.getComponentTypeId()))
				filteredProjs.add(project);
		}
		return (IProject[]) filteredProjs.toArray(new IProject[filteredProjs.size()]);
		
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTestCase getImportTestCase() {
		return new WebImportOperationTomcatTest(""); //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	public String getModuleExportFileExt() {
		return ".war"; //$NON-NLS-1$
	}

}
