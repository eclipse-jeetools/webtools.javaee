/*
 * Created on Jan 6, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.appclient.operations;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.application.internal.operations.AppClientComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;
import org.eclipse.wtp.headless.tests.savestrategy.AppClientImportOperationTest;
import org.eclipse.wtp.headless.tests.savestrategy.ModuleImportOperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AppClientExportOperationTest extends ModuleExportOperationTestCase {

	public AppClientExportOperationTest(String name) {
		super(name);
	}
	public static Test suite() {
		return new SimpleTestSuite(AppClientExportOperationTest.class);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getModelInstance()
	 */
	protected IDataModel getModelInstance() {
		return DataModelFactory.createDataModel(new AppClientComponentExportDataModelProvider());
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleExportOperationTestCase#getImportTestCase()
	 */
	protected ModuleImportOperationTestCase getImportTestCase() {
		return new AppClientImportOperationTest("");
	}
	protected IProject[] getExportableProjects() throws Exception {
		IProject[] projs = super.getExportableProjects();
		List filteredProjs = new ArrayList();
		for (int i = 0; i < projs.length; i++) {
			IProject project = projs[i];
			IFlexibleProject flex = ComponentCore.createFlexibleProject(project);
			if (flex.getComponentsOfType(AppClientArtifactEdit.TYPE_ID).length > 0)
				filteredProjs.add(project);
		}
		return (IProject[]) filteredProjs.toArray(new IProject[filteredProjs.size()]);
		
	}

}
