package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


public class EarEMFEditTest extends GeneralEMFEditingTest {
    EARFile earFile;
    int createdModules = 0;

    private int NUM_MODULES = 4;

    public EarEMFEditTest(String name) {
        super(name);
    }

    public CommonarchiveFactory getArchiveFactory() {
        return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
    }

    public EjbFactory getEjbFactory() {
        return EjbPackage.eINSTANCE.getEjbFactory();
    }

    public ApplicationFactory getApplicationFactory() {
        return ApplicationPackage.eINSTANCE.getApplicationFactory();
    }

    public WebapplicationFactory getWebAppFactory() {
        return WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
    }

    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.EarEMFEditTest", "-noloading" };
        TestRunner.main(className);
    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new EarEMFEditTest("testEAREdit"));
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
    
	public HashSet ignorableAttributes(){
		HashSet set = new HashSet();
		set.add("id");
		return set;
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
