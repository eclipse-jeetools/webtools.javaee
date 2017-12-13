package org.eclipse.jst.j2ee.archive.test;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ArchiveTypeDiscriminatorRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.GenericArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;


/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2001, 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

/**
 * @author mdelder
 *  
 */
public class DiscriminatorTest
        extends TestCase {

    public boolean assertBarFile = false;

    public DiscriminatorTest(String name) {
        super(name);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("DiscriminatorTest");
        suite.addTest(new DiscriminatorTest("testDiscriminator"));
        return suite;
    }

    // TODO Implement unit test for discriminator framework
    public void testDiscriminator() throws Exception {
        assertBarFile = true;
        try {
            ArchiveTypeDiscriminatorRegistry.registorDiscriminator(new BeverageArchiveDiscriminator());
            Archive archive = CommonarchiveFactory.eINSTANCE.openArchive( AutomatedBVT.baseDirectory + File.separator + "my.bar");
            assertNotNull("The archive was not found!", archive);

            assertTrue("The archive extension (.bar) was not recognized as a custom extension!", archive.isNestedArchive("my.bar"));

            boolean typeContributedSuccessfully = false;
            EList types = archive.getTypes();
            for (int i = 0; i < types.size(); i++) {
                if (types.get(i).equals(BeverageArchiveDiscriminator.BEVERAGE_ARCHIVE_TYPE_KEY)) {
                    typeContributedSuccessfully = true;
                    break;
                }
            }
            assertTrue("The BEVERAGE_ARCHIVE_TYPE_KEY was not found in the list of types of the archive!", typeContributedSuccessfully);
        } finally {
            assertBarFile = false;
        }
    }

    public class BeverageArchiveDiscriminator
            extends TestCase implements GenericArchiveTypeDiscriminator {

        public static final String BEVERAGE_ARCHIVE_TYPE_KEY = "com.ibm.beverage.archive";

        /*
         * (non-Javadoc)
         * 
         * @see com.ibm.etools.j2ee.commonarchivecore.GenericArchiveTypeDiscriminator#discriminate(com.ibm.etools.j2ee.commonarchivecore.Archive)
         */
        public boolean discriminate(Archive anArchive) {
            boolean result = anArchive.containsFile("META-INF/adult-beverage-imbibement-options.xml");
            if (assertBarFile)
                    assertTrue(
                            "The BeverageArchiveDiscriminator did not recogniZe the file! (Ensure it contains a file named META-INF/adult-beverage-imbibement-venues.xml)",
                            result);
            return result;
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.ibm.etools.j2ee.commonarchivecore.GenericArchiveTypeDiscriminator#getCustomFileExtensions()
         */
        public String[] getCustomFileExtensions() {

            return new String[] { "bar"};

        }

        /*
         * (non-Javadoc)
         * 
         * @see com.ibm.etools.j2ee.commonarchivecore.GenericArchiveTypeDiscriminator#getTypeKey()
         */
        public String getTypeKey() {

            return BEVERAGE_ARCHIVE_TYPE_KEY;
        }
    }
}