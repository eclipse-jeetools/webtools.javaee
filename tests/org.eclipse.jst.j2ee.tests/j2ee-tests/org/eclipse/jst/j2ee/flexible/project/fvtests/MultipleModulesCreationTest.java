/*
 * Created on Jan 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.flexible.project.fvtests;

import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationOperation;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;

/**
 * @author blancett
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MultipleModulesCreationTest extends  TestCase  {
	
    public static Test suite() {
        return new SimpleTestSuite(MultipleModulesCreationTest.class);
    }
    	
	
	public void testWebModuleCreation() throws Exception {
		createWebModule(24, "FirstWeb", "FirstWeb");
	}
	
	private void createWebModule(int j2eeVersion, String aModuleName, String projectName){	
		
		
		IDataModel model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
		model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME, "SingleComp");
		try {
			runWebModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		IProject project = ProjectUtilities.getProject("SingleComp");
		
		model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
		model.setProperty( IWebComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
		model.setProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME, aModuleName);
		model.setBooleanProperty(IWebComponentCreationDataModelProperties.SUPPORT_MULTIPLE_MODULES, true);
		try {
			runWebModuleCreationOperation(model);
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		
		project = ProjectUtilities.getProject(projectName);
		
			model = DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
	
			model.setBooleanProperty(IWebComponentCreationDataModelProperties.SUPPORT_MULTIPLE_MODULES, true);
			model.setProperty( IWebComponentCreationDataModelProperties.PROJECT_NAME, projectName);
			model.setIntProperty(IWebComponentCreationDataModelProperties.COMPONENT_VERSION, j2eeVersion);
			model.setProperty(IWebComponentCreationDataModelProperties.COMPONENT_NAME, "SecondWeb");
	
			
			try {
				runWebModuleCreationOperation(model);
			}
			catch (Exception e) {
				e.printStackTrace();
			}			
		
		
	}
	
	private  void runWebModuleCreationOperation(IDataModel model) throws Exception {		
		WebComponentCreationOperation webOp = new WebComponentCreationOperation(model);
		webOp.execute(new NullProgressMonitor(), null);
	}	

}
