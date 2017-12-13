package org.eclipse.jst.j2ee.archive.emftests;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class EarEMFEditTest extends GeneralEMFEditingTest {
    EARFile earFile;
    int createdModules = 0;

    private int NUM_MODULES = 4;

    public EarEMFEditTest(String name) {
        super(name);
    }
    
    public EarEMFEditTest(String name, RendererFactory factory) {
    	super(name, factory);
    }
	
    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.EarEMFEditTest", "-noloading" };
        TestRunner.main(className);
    }

    public static junit.framework.Test suite(RendererFactory factory) {
        TestSuite suite = new TestSuite(EarEMFEditTest.class.getName());
        suite.addTest(new EarEMFEditTest("testEAREdit", factory));
        return suite;
    }

    public void testEAREdit() throws Exception {
        getEAR();

		assertEquals("1.2", earFile.getDeploymentDescriptor().getVersion());

        ApplicationResource DD = (ApplicationResource) earFile.getDeploymentDescriptorResource();
		setVersion(VERSION_1_3);
		setModuleType(APPICATION); 
        editRoot(DD.getRootObject());
        String curDir = AutomatedBVT.baseDirectory;
        
        String out = curDir +"testOutput/EditOutput/EMFModelCreationTests/EditEAR";
        earFile.extractTo(out, Archive.EXPAND_ALL);
        earFile.close();

        //Compare work in progress
       
        String exampleDeploymentDesURI = curDir + "EMFTests/application.xml";
        String curDeploymentDesURI = curDir + out + "/META-INF/application.xml";
        //compareContents(curDeploymentDesURI, exampleDeploymentDesURI);
    }

    public void getEAR() throws OpenFailureException {
        String in = AutomatedBVT.baseDirectory + "loose_module_workspace/LooseEAR/";
        earFile = getArchiveFactory().openEARFile(in);
        assertTrue(earFile.getDeploymentDescriptor() != null);
    }

	public EObject createInstance(EClass eClassifier) {
		if (eClassifier.getName().equals("Module")) {
			createdModules++;
			switch (createdModules) {
				case (1) :
					return createJavaModuleInstance(eClassifier);
				case (2) :
					return createEJBModuleInstance(eClassifier);
				case (3) :
					return createWebModuleInstance(eClassifier);
				case (4) :
					return createConnetorModuleInstance(eClassifier);
				default :
					return createJavaModuleInstance(eClassifier);
			}
		}
		return super.createInstance(eClassifier);
	}

	private EObject createConnetorModuleInstance(EClass eClassifier) {
		return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createConnectorModule();
	}

	private EObject createWebModuleInstance(EClass eClassifier) {
		return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createWebModule();
	}

	private EObject createEJBModuleInstance(EClass eClassifier) {
		return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createEjbModule();
	}

	private EObject createJavaModuleInstance(EClass eClassifier) {
		return ((ApplicationFactory) eClassifier.getEPackage().getEFactoryInstance()).createJavaClientModule();
	}

	public Object getSharedObjectByType(EObject owner, EReference ref) {
		if (ref.getName().equals("module"))
			return createJavaModuleInstance((EClass) ref.getEType());
		return super.getSharedObjectByType(owner, ref);
	}

	public int getDepthForAttribute(EReference ref) {
		if (ref.getName().equals("modules"))
			return NUM_MODULES;
		return super.getDepthForAttribute(ref);
	}
}
