package org.eclipse.jst.j2ee.archive.test;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ValidateXmlCommand;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.XmlValidationResult;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveInit;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * Insert the type's description here.
 * Creation date: (12/11/00 10:26:11 AM)
 * @author: Administrator
 */
public class SimpleTests extends TestCase {
    private final static String copyright = "(c) Copyright IBM Corporation 2001."; //$NON-NLS-1$
    /**
     * SimpleTests constructor comment.
     * @param name java.lang.String
     */
    public SimpleTests(String name) {
        super(name);
    }
    public boolean isEmpty(Collection adapters) {
        if (adapters.isEmpty())
            return true;
        for (Iterator iter = adapters.iterator(); iter.hasNext();) {
            if (iter.next() != null)
                return false;
        }
        return true;
    }
    public CommonarchiveFactory getArchiveFactory() {
        return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
    }
    /**
     * Starts the application.
     * @param args an array of command-line arguments
     */
    public static void main(java.lang.String[] args) {
        String[] className = { "com.ibm.etools.archive.test.SimpleTests", "-noloading" };
        TestRunner.main(className);
    }
    public void printApplToConsole(Application appl) {

        List modules = appl.getModules();
        for (int i = 0; i < modules.size(); i++) {
            System.out.println(modules.get(i));
        }
    }
    public void printEJBJarToConsole(EJBJar ejbJar) {

        List ejbs = ejbJar.getEnterpriseBeans();
        for (int i = 0; i < ejbs.size(); i++) {
            System.out.println(ejbs.get(i));
        }

    }
    public void printWebAppToConsole(WebApp webApp) {
        List servlets = webApp.getServlets();

        for (int i = 0; i < servlets.size(); i++) {
            System.out.println(servlets.get(i));
        }

    }
    public static junit.framework.Test suite() {
        return new TestSuite(SimpleTests.class);
    }
    public void testContainerManagedEntityExtensionRead() throws Exception {
        CommonarchiveFactory factory = CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
        EJBJarFile jarFile = (EJBJarFile) factory.openArchive(AutomatedBVT.baseDirectory + "cmpsample.jar");
        EJBJar jar = jarFile.getDeploymentDescriptor();
     
    }
    public void testEJB11JarAdd() throws Exception {
        EARFile _earFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/TestEAREJBAdd.ear");
        CommonarchiveFactory factory = CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
        EJBJarFile jar = (EJBJarFile) factory.openArchive(AutomatedBVT.baseDirectory + "ejb_compat_entitycmp.jar");
        jar.setURI(AutomatedBVT.baseDirectory + "ejb_compat_entitycmp.jar");
        _earFile.addCopy(jar);
        _earFile.save();
    }
    public void testEJBSetAbstractSchemaName() throws Exception {
        String testSm = AutomatedBVT.baseDirectory + "TestSchema";
        CommonarchiveFactory factory = CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
        EJBJarFile jarFile = (EJBJarFile) factory.openArchive(AutomatedBVT.baseDirectory + "cmpsample.jar");
        EJBJar jar = jarFile.getDeploymentDescriptor();
        List lst = jar.getContainerManagedBeans();

        //I know this will be a CMP, so, lets try set/get on it..
        ContainerManagedEntity cmp = (ContainerManagedEntity) lst.get(0);

        //System.out.println("Setting Schema name to: " + testSm);
        cmp.setAbstractSchemaName(testSm);
        //Retrieve it and see if it matches
        //System.out.println("Getting schema name: " + cmp.getAbstractSchemaName());
        assertTrue(cmp.getAbstractSchemaName().equals(testSm));
    }
    /**
     * This excercises the index adapter on Container
     */
    public void testIndexing() throws Exception {
        Archive anArchive = getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "foo.jar");
        String uri = "com/ibm/etools/archive/test/SimpleTests.java";

        List files = anArchive.getFiles();

        File aFile = anArchive.getFile(uri);
        assertTrue("Adapter should be attached to aFile", !isEmpty(aFile.eAdapters()));

        files.remove(aFile);

        assertTrue("Adapter should not be attached to aFile", isEmpty(aFile.eAdapters()));

        assertTrue("Contains should return false", !anArchive.containsFile(uri));

        File copy = anArchive.addCopy(aFile);

        assertTrue("Contains should return true", anArchive.containsFile(uri));

        String renamedURI = "com/ibm/goobledygook";
        copy.setURI(renamedURI);

        assertTrue("Contains should return false for old uri", !anArchive.containsFile(uri));

        assertTrue("Contains should return true for new uri", anArchive.containsFile(renamedURI));

    }
    /**
     * This excercises the index adapter on Container
     * Tests notifications with add/remove of lists, and that adapters are set post copy
     */
    public void testIndexingWithCollections() throws Exception {
        Archive anArchive = getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "bankejbs.jar");
        EARFile earFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "bar.ear");
        earFile.addCopy(anArchive);
        anArchive = (Archive) earFile.getFile(AutomatedBVT.baseDirectory + "bankejbs.jar");

        assertTrue("Index out of sync", !anArchive.getFiles().isEmpty() && anArchive.isIndexed());
        List files = anArchive.getFiles();
        for (int i = 0; i < files.size(); i++) {
            File aFile = (File) files.get(i);
            assertTrue("Index out of whack; containment should be true", anArchive.containsFile(aFile.getURI()));
            assertTrue("Adapter should be attached to aFile", !isEmpty(aFile.eAdapters()));
        }

        List filesToAdd = getArchiveFactory().openReadOnlyDirectory(AutomatedBVT.baseDirectory + "WarTestClasses").getFilesRecursive();
        List addedFiles = anArchive.addCopyFiles(filesToAdd);

        assertTrue("Files added incorrectly", !filesToAdd.isEmpty() && filesToAdd.size() == addedFiles.size());

        for (int i = 0; i < addedFiles.size(); i++) {
            File aFile = (File) addedFiles.get(i);
            assertTrue("Contains should be true", anArchive.containsFile(aFile.getURI()));
            assertTrue("Should be same instance", anArchive.getFile(aFile.getURI()) == aFile);
            assertTrue("Adapter should be attached to aFile", !isEmpty(aFile.eAdapters()));
        }
        anArchive.getFiles().removeAll(addedFiles);

        for (int i = 0; i < addedFiles.size(); i++) {
            File aFile = (File) addedFiles.get(i);
            assertTrue("Adapter should not be attached to aFile", isEmpty(aFile.eAdapters()));
            assertTrue("Contains should return false", !anArchive.containsFile(aFile.getURI()));
        }
        getArchiveFactory().closeOpenArchives();
    }
    /**
     * Very basic method to open an archive and save it as a directory to a new destination.  Requires visual inspection
     * for verification.  Nested jars will not be exploded
     */
    public void testOpenAndExtract1() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        EARFile earFile = (EARFile) getArchiveFactory().openArchive(in);
        //System.out.println("EAR spec version " + earFile.getSpecVersion() + " detected.");

        String out = AutomatedBVT.baseDirectory + "testOutput/SimpleTests/sample-expanded-out1.ear";
        earFile.extractTo(out, Archive.EXPAND_NONE);
        earFile.close();
    }
    /**
     * Very basic method to open an archive and save it as a directory to a new destination.  Requires visual inspection
     * for verification.  Nested wars will be exploded but other nested jars will not.  This method emulates the websphere install.
     * the archive is opened as read only; test that no temp files are created by putting a breakpoint in ArchiveUtil#createTempFile(String, File)
     * Also the archive is opened with java reflection disabled.  Verify no reflection occurs by putting a breakpoints in the constructors
     * of JavaJDKAdapterFactory, and the method reflectValues() in each of the subclasses of JdkAdaptor.
     */
    public void testOpenAndExtract2() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        ArchiveOptions options = new ArchiveOptions();
        options.setIsReadOnly(true);
        options.setUseJavaReflection(false);
        EARFile earFile = (EARFile) getArchiveFactory().openArchive(options, in);
        //The following code forces the load of all the deployment descriptors, just to make sure java reflection doesn't happen
        List moduleRefs = earFile.getModuleRefs();
        for (int i = 0; i < moduleRefs.size(); i++) {
            ModuleRef m = (ModuleRef) moduleRefs.get(i);
            m.getDeploymentDescriptor();
            
        }

        String out = "testOutput/SimpleTests/sample-expanded-out2.ear";
        earFile.extractTo(out, Archive.EXPAND_WAR_FILES);
        earFile.close();
    }
    /**
     * Very basic method to open an archive and save it as a directory to a new destination.  Requires visual inspection
     * for verification.  Nested wars and ejb jars will be exploded but other nested jars will not
     */
    public void testOpenAndExtract3() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        EARFile earFile = (EARFile) getArchiveFactory().openArchive(in);
        String out = AutomatedBVT.baseDirectory + "testOutput/SimpleTests/sample-expanded-out3.ear";
        earFile.extractTo(out, Archive.EXPAND_WAR_FILES | Archive.EXPAND_EJBJAR_FILES);
        earFile.close();
    }
    /**
     * Opens an ear file, and for each module, writes it's dd components to the console.  Requires visual inspection
     * for verification
     */
    public void testOpenAndRead() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        EARFile earFile = (EARFile) getArchiveFactory().openArchive(in);

        Application appl = earFile.getDeploymentDescriptor();
        printApplToConsole(appl);

        List moduleRefs = earFile.getModuleRefs();

        for (int i = 0; i < moduleRefs.size(); i++) {
            ModuleRef aModuleRef = (ModuleRef) moduleRefs.get(i);
            if (aModuleRef.isWeb()) {
                WebModuleRef wRef = (WebModuleRef) aModuleRef;
                printWebAppToConsole(wRef.getWebApp());
              
            } else if (aModuleRef.isEJB()) {
                EJBModuleRef eRef = (EJBModuleRef) aModuleRef;
                printEJBJarToConsole(eRef.getEJBJar());
                
            }
        }
    }
    /**
     * Very basic method to open an archive and save it as a jar file to a new destination.  Requires visual inspection
     * for verification
     */
    public void testOpenAndSaveAsJarFile() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        EARFile earFile = (EARFile) getArchiveFactory().openArchive(in);
        String out = AutomatedBVT.baseDirectory + "testOutput/SimpleTests/sample-copy.ear";
        earFile.saveAsNoReopen(out);
    }
    /**
     * Test case created as a result of a defect report that currently cannot be reproduced; open an expanded ear file and save it out
     * as a jar file
     */
    public void testOpenDirectoryAndSaveAsJarFile() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample_expanded.ear";
        in = new java.io.File(in).getAbsolutePath();
        //System.out.println(in);
        EARFile earFile = (EARFile) getArchiveFactory().openArchive(in);
        //Added this line to see if loading the dd would make a difference
        earFile.getDeploymentDescriptor();
        String out = "testOutput/SimpleTests/sample-jarred.ear";
        out = new java.io.File(out).getAbsolutePath();
        //System.out.println(out);
        earFile.saveAsNoReopen(out);
    }
    /**
     * @see Archive#canClose()
     */
    public void testSafeClose() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        EARFile earFile = getArchiveFactory().openEARFile(in);
        List modules = earFile.getDeploymentDescriptor().getModules();
        EJBJarFile ejbJarFile = null;

        for (int i = 0; i < modules.size(); i++) {
            Module aModule = (Module) modules.get(i);
            if (aModule.isEjbModule()) {
                ejbJarFile = (EJBJarFile) earFile.getFile(aModule.getUri());
                //System.out.println("EJBJar spec version " + ejbJarFile.getSpecVersion() + " detected.");

                break;
            }
        }

        assertTrue("EJBJarFile should not be null", ejbJarFile != null);

        EARFile newEarFile = getArchiveFactory().createEARFileInitialized("testOutput/SimpleTests/safeClose1.ear");
        newEarFile.addCopy(ejbJarFile);
        assertTrue(!earFile.getDependentOpenArchives().isEmpty() && !ejbJarFile.getDependentOpenArchives().isEmpty());

        newEarFile.save();
        assertTrue(earFile.getDependentOpenArchives().isEmpty() && ejbJarFile.getDependentOpenArchives().isEmpty());
        earFile.close();
        newEarFile.close();
    }
    /**
     * CMVC defect 99544 - If a save fails and the file didn't exist, one should not
     * be created
     */
    public void testSaveFailure() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        String out = AutomatedBVT.baseDirectory + "testOutput/SimpleTests/saveFailure.ear";
        EARFile earFile = getArchiveFactory().openEARFile(in);
        List modules = earFile.getDeploymentDescriptor().getModules();
        EJBJarFile ejbJarFile = null;

        for (int i = 0; i < modules.size(); i++) {
            Module aModule = (Module) modules.get(i);
            if (aModule.isEjbModule()) {
                ejbJarFile = (EJBJarFile) earFile.getFile(aModule.getUri());
                break;
            }
        }

        assertTrue("EJBJarFile should not be null", ejbJarFile != null);

        EARFile newEarFile = getArchiveFactory().createEARFileInitialized(out);
        newEarFile.addCopy(ejbJarFile);
        ejbJarFile.close();
        try {
            newEarFile.save();
        } catch (SaveFailureException expected) {
            System.out.println("Expected save failure occurred");
            return;
        } finally {
            assertTrue("File should not exist", !(new java.io.File(out).exists()));
            getArchiveFactory().closeOpenArchives();
        }
        assertTrue("Save should have failed", false);
    }
    /**
     * Opens an ear file, makes a few changes to ensure the xml is not valid, and validates the xml in all the dds; verifies that
     * sax exceptions are collected
     */
    public void testXmlValidation() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.ear";
        EARFile earFile = getArchiveFactory().openEARFile(in);

        Application appl = earFile.getDeploymentDescriptor();
        appl.getSecurityRoles().add(CommonPackage.eINSTANCE.getCommonFactory().createSecurityRole());

        List moduleFiles = earFile.getModuleFiles();

        for (int i = 0; i < moduleFiles.size(); i++) {
            ModuleFile aModuleFile = (ModuleFile) moduleFiles.get(i);
            if (aModuleFile.isWARFile()) {
                WARFile aWarFile = (WARFile) aModuleFile;
                aWarFile.getDeploymentDescriptor().getServlets().add(WebapplicationPackage.eINSTANCE.getWebapplicationFactory().createServlet());
            } else if (aModuleFile.isEJBJarFile()) {
                EJBJarFile anEjbJarFile = (EJBJarFile) aModuleFile;
                anEjbJarFile.getDeploymentDescriptor().getEnterpriseBeans().add(EjbPackage.eINSTANCE.getEjbFactory().createEntity());
                anEjbJarFile.getDeploymentDescriptor().getAssemblyDescriptor().getSecurityRoles().add(CommonPackage.eINSTANCE.getCommonFactory().createSecurityRole());
            }
        }
        Command cmd = new ValidateXmlCommand(earFile);
        cmd.execute();
        List errors = (List) cmd.getResult();
        assertTrue("Total validation errors should be 3, instead of "+errors.size(), errors.size() == 3);
        for (int i = 0; i < 3; i++) {
            XmlValidationResult result = (XmlValidationResult) errors.get(i);
            if (i == 1)
                assertTrue("The ejb module should have 2 errors instead of "+ result.getCaughtExceptions().size(), result.getCaughtExceptions().size() == 2);
            else
                assertTrue("The web module should have 1 error instead of "+ result.getCaughtExceptions().size(), result.getCaughtExceptions().size() == 1);
        }
        earFile.close();
    }
    protected void setUp() throws Exception {
        super.setUp();
        ArchiveInit.init();
    }
    
   	public void testReadEmptyTags() throws Exception {
   		String uri = AutomatedBVT.baseDirectory + "CHKJ280X.jar";
   		EJBJarFile jarfile = getArchiveFactory().openEJBJarFile(uri);
   		EJBJar jar = jarfile.getDeploymentDescriptor();
   		Session sess = (Session) jar.getEnterpriseBeans().get(0);
   		Entity entity = (Entity) jar.getEnterpriseBeans().get(1);
   		assertFalse("Value should be unset", entity.isSetReentrant());
		assertFalse("Value should be unset", sess.isSetSessionType());
		assertFalse("Value should be unset", sess.isSetTransactionType()); 
}
}
