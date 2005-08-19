package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.OperationTestCase;
import org.eclipse.wtp.j2ee.headless.tests.appclient.operations.AppClientProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.ejb.operations.EJBProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifierFactory;
import org.eclipse.wtp.j2ee.headless.tests.jca.operations.ConnectorProjectCreationOperationTest;
import org.eclipse.wtp.j2ee.headless.tests.web.operations.WebProjectCreationOperationTest;

public abstract class ModuleProjectCreationOperationTest extends OperationTestCase {
	private long componentSeed = System.currentTimeMillis();
    
    public static String DEFAULT_PROJECT_NAME = "SimpleProject";
    public static String DEFAULT_EAR_PROJECT_NAME = "SimpleEARProject";
	public static String DEFAULT_COMPONENT_NAME = "SimpleComponent";
	public static String DEFAULT_EAR_COMPONENT_NAME = "SimpleEARComponent";
    
    /**
	 * @param name
	 */
	public ModuleProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(AppClientProjectCreationOperationTest.class);
        suite.addTestSuite(EJBProjectCreationOperationTest.class);
        suite.addTestSuite(WebProjectCreationOperationTest.class); 
        suite.addTestSuite(ConnectorProjectCreationOperationTest.class); 
        return suite;
    }
    
    public void testDefaults() throws Exception {
        createSimpleModule(DEFAULT_COMPONENT_NAME + componentSeed);
    }
    
    public void testOutputContainer() throws Exception {
    	createSimpleModule(DEFAULT_COMPONENT_NAME + componentSeed);
    	IProject project = ProjectUtilities.getProject(DEFAULT_COMPONENT_NAME + componentSeed);
    	IVirtualComponent component = ComponentCore.createComponent(project,DEFAULT_COMPONENT_NAME + componentSeed);
    	IContainer[] ouputContainers = ComponentUtilities.getOutputContainers(component);
    	assertNotNull(ouputContainers[0]);
    }


	private void createSimpleEARModule(String componentName) throws Exception {
		IDataModel dataModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
        dataModel.setProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_NAME, componentName);
        runAndVerify(dataModel);
	}

	public void createSimpleModule(String componentName) throws Exception {
        IDataModel dataModel = getComponentCreationDataModel();
        dataModel.setProperty(IJ2EEComponentCreationDataModelProperties.COMPONENT_NAME, componentName);
        dataModel.setBooleanProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR, true);
        runAndVerify(dataModel,false,true);
    }

	public abstract IDataModel getComponentCreationDataModel();

	public static void verifyDataModel(IDataModel dataModel) throws Exception{
	    DataModelVerifier verifier = DataModelVerifierFactory.getInstance().createVerifier(dataModel);
	    verifier.verify(dataModel);
	}

}
