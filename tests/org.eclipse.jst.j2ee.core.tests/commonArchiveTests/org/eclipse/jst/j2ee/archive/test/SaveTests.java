package org.eclipse.jst.j2ee.archive.test;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;

/**
 * Insert the type's description here.
 * Creation date: (12/21/00 3:04:36 PM)
 * @author: Administrator
 */
public class SaveTests extends TestCase {
    private final static String copyright = "(c) Copyright IBM Corporation 2001."; //$NON-NLS-1$
    /**
     * SaveTests constructor comment.
     * @param name java.lang.String
     */
    public SaveTests(String name) {
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
        String[] className = { "com.ibm.etools.archive.test.SaveTests", "-noloading" };
        TestRunner.main(className);
    }
    public static junit.framework.Test suite() {
        return new TestSuite(SaveTests.class);
    }
    /**
     * This scenario tests the case of opening an archive, modifying it's contents, and saving to the same uri from which it was
     * loaded.  The problem is that the reading of input streams of individual entries in an archive is deferred until requested through
     * the api or save time; therefore, an open file exists on the source file.  To get around this, the implementation saves the archive
     * to a temp file, then deletes (or renames, based on setting, to be implemented) the old file, renames the temp file, and does some
     * housekeeping on the saved archive, with it's load strategy and contained files
     */
    public void testSave() throws Exception {

        //First make a copy of our reference file
        Archive anArchive = getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "example2.war");
        anArchive.saveAsNoReopen(AutomatedBVT.baseDirectory + "testOutput/SaveTests/copy.war");
        anArchive = null;

        WARFile aWarFile = (WARFile) getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "testOutput/SaveTests/copy.war");
        ReadOnlyDirectory sampleDirectory = getArchiveFactory().openReadOnlyDirectory(AutomatedBVT.baseDirectory + "WarTestClasses");
        int initialSize = aWarFile.getClasses().size();
        File aFile = sampleDirectory.getFileInSelfOrSubdirectory("com/ibm/etools/archive/test/WARFileTests.class");
        aWarFile.addCopyClass(aFile);
        assertTrue(aWarFile.getClasses().size() == initialSize + 1);

        String classURI = ArchiveUtil.concatUri(ArchiveConstants.WEBAPP_CLASSES_URI, aFile.getURI(), '/');
        assertTrue(aWarFile.containsFile(classURI));
        aWarFile.save();

        File classFile = aWarFile.getFile(classURI);
        assertTrue("File should not be null", classFile != null);
        assertTrue("Wrong loading archive", classFile.getLoadingContainer() == aWarFile);
        assertTrue("Wrong original URI", classFile.getOriginalURI().equals(classURI));

        aWarFile.saveAs("testOutput/SaveTests/copy2.war");
        aWarFile.save();
    }

    /**
     * This scenario is similar to @link #testSave, except that it tests with a directory instead of a jar file
     */
    public void testSaveDirectory() throws Exception {

        //First make a copy of our reference file
        Archive anArchive = getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "sample.ear");
        anArchive.saveAsNoReopen(AutomatedBVT.baseDirectory + "testOutput/SaveTests/sample-copied.ear");
        anArchive = null;

        EARFile anEARFile = (EARFile) getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "testOutput/SaveTests/sample-copied.ear");

        anEARFile.getDeploymentDescriptor();
        

        anEARFile.extractNoReopen(Archive.EXPAND_WAR_FILES);

    }

}
