package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.web.internal.operation.SimpleWebModuleCreationDataModelProvider;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

public class StaticWebProjectCreationOperationTest extends ModuleProjectCreationOperationTest {

	public StaticWebProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public static Test suite() {
        return new TestSuite(StaticWebProjectCreationOperationTest.class);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
     */
    public IDataModel getComponentCreationDataModel() {
        return DataModelFactory.createDataModel(new SimpleWebModuleCreationDataModelProvider());
    }
    
    public void createSimpleModule(String componentName) throws Exception {
        IDataModel dataModel = getComponentCreationDataModel();
        dataModel.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, componentName);
       runAndVerify(dataModel,false,true);
    }

}
