package org.eclipse.jst.j2ee.archive.test;

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.impl.JavaClassImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;


public class TestModuleClassLoading extends TestCase {
    static String testClass = "client.Subtract";
    
    
    
    /**
     * 
     */
    public TestModuleClassLoading() {
        super();
    }

    public TestModuleClassLoading(String name) {
        super(name);
    }

	public CommonarchiveFactory getArchiveFactory() {
		return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
	}
	/**
	 * Starts the application.
	 * @param args an array of command-line arguments
	 */
	public static void main(java.lang.String[] args) {
		String[] className = {"com.ibm.etools.archive.test.TestModuleClassLoading", "-noloading"};
		TestRunner.main(className);
	}
	public static junit.framework.Test suite() {
		return new TestSuite(TestModuleClassLoading.class);
	}
    public static void testAddModuleClassLoading() throws Exception {
		String[] files = { "PROTO.ear","SEIinAnotherJar.ear", "SEIinWEB-INF_lib_jar.ear" };

        if (files.length == 0) {
            System.out.println("Usage: args specify full path to ear files.");
        }
        
       // com.ibm.websphere.models.config.init.ConfigInit.init();
        for (int i = 0; i < files.length; i++) {
            String filename = files[i];   
			CommonarchiveFactory factory = CommonarchiveFactoryImpl.getActiveFactory();

			String in = AutomatedBVT.baseDirectory + files[i];
            try {
				EARFile earFile = factory.openEARFile (in);
				System.out.println ("uri: " + earFile.getURI());
				System.out.println("Testing " + earFile.getURI());
                doModules(earFile);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    

	public static void doModules(EARFile earFile) {
		List modules = earFile.getModuleFiles();
		for (int i = 0; i < modules.size(); i++) {
			Archive module = (Archive) modules.get(i);
			System.out.print("\tLooking for class " + testClass + " in module " + module.getURI());
			doLoad(module, testClass);
		}
	} // validateModules

	private static void doLoad(Archive module, String testClass) {
		try {
			JavaClass javaClass =
				(JavaClass) JavaClassImpl.reflect(testClass, module.getResourceSet());
			if (!(javaClass).isExistingType()) {
				// This is what happens if the class can't be loaded.
				System.out.println("- NOT FOUND - isExistingType returns false.");
			} else {
				System.out.println(" - found: isExistingType returns true.");
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
}
