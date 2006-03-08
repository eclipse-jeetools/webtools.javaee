package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
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
    
    public static String DEFAULT_PROJECT_NAME = "SimpleProject"; //$NON-NLS-1$
    public static String DEFAULT_EAR_PROJECT_NAME = "SimpleEARProject"; //$NON-NLS-1$
	public static String DEFAULT_COMPONENT_NAME = "SimpleComponent"; //$NON-NLS-1$
	public static String DEFAULT_EAR_COMPONENT_NAME = "SimpleEARComponent"; //$NON-NLS-1$
	public static String DEFAULT_COMPONENT_WITH_EAR = "Component"; //$NON-NLS-1$		
    
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
    	IVirtualComponent component = ComponentCore.createComponent(project);
    	if (!J2EEProjectUtilities.isDynamicWebProject(component.getProject()) || !J2EEProjectUtilities.isApplicationClientProject(component.getProject())) {
    		IContainer[] ouputContainers = J2EEProjectUtilities.getOutputContainers(component.getProject());
    		assertNotNull(ouputContainers[0]);
    	}
    }


//	private void createSimpleEARModule(String componentName) throws Exception {
//		IDataModel dataModel = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
//        dataModel.setProperty(IComponentCreationDataModelProperties.COMPONENT_NAME, componentName);
//        runAndVerify(dataModel);
//	}

	public void createSimpleModule(String componentName) throws Exception {
        IDataModel dataModel = getComponentCreationDataModel();
        dataModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, componentName);
        runAndVerify(dataModel,false,true);
    }

	public abstract IDataModel getComponentCreationDataModel();
	public abstract IDataModel getComponentCreationDataModelWithEar();
	
	public static void verifyDataModel(IDataModel dataModel) throws Exception{
	    DataModelVerifier verifier = DataModelVerifierFactory.getInstance().createVerifier(dataModel);
	    verifier.verify(dataModel);
	}

    public void testAddtoEAR() throws Exception {
        createModuleWithEAR(DEFAULT_COMPONENT_WITH_EAR + componentSeed);
    }	
    
	public void createModuleWithEAR(String componentName) throws Exception {
        IDataModel dataModel = getComponentCreationDataModelWithEar();
        if( dataModel != null ){
	        dataModel.setProperty(IJ2EEFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, componentName);
	        runAndVerify(dataModel,false,true);
        }
    }  	
}
