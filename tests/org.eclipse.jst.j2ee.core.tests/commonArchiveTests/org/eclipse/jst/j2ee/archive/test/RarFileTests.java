package org.eclipse.jst.j2ee.archive.test;

/*
* Licensed Material - Property of IBM
* (C) Copyright IBM Corp. 2001 - All Rights Reserved.
* US Government Users Restricted Rights - Use, duplication or disclosure
* restricted by GSA ADP Schedule Contract with IBM Corp.
*/

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.SecurityPermission;

/**
 * Tests for verfication that RAR support works correctly in WCCM.
 * Creation date: (12/11/00 10:26:11 AM)
 * @author: Jared Jurkiewicz, et al.
 */
public class RarFileTests extends TestCase {
    private final static String copyright = "(c) Copyright IBM Corporation 2001."; //$NON-NLS-1$
    /**
     * SimpleTests constructor comment.
     * @param name java.lang.String
     */
    public RarFileTests(String name) {
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
        String[] className = { "com.ibm.etools.archive.test.RarFileTests", "-noloading" };
        TestRunner.main(className);
    }
    public void printRARToConsole(Connector connector) {
        ResourceAdapter ra = connector.getResourceAdapter();

        System.out.println("ResourceAdapter from XML Deployment Descriptor");
        System.out.println("-------------------------------------------------");
        System.out.println(connector.getDisplayName() + ": " + connector.getDescription());
        System.out.println("Vendor : " + connector.getVendorName());
        System.out.println("Version : " + connector.getVersion());
        System.out.println("Spec Version : " + connector.getSpecVersion());
        System.out.println("EIS Type : " + connector.getEisType());
        System.out.println("Reauthentication Support : " + ra.isReauthenticationSupport());
        System.out.println("Transaction Support : " + ra.getTransactionSupport().getName().toLowerCase());

        if (connector.getLicense() != null) {
            System.out.println("License Required : " + connector.getLicense().isRequired());
            System.out.println("License Description : " + connector.getLicense().getDescription());
        }

        System.out.println("Small Icon : " + connector.getSmallIcon());
        System.out.println("Large Icon : " + connector.getLargeIcon());

        List authMechs = ra.getAuthenticationMechanisms();
        for (int i = 0; i < authMechs.size(); i++) {
            AuthenticationMechanism auth = (AuthenticationMechanism) authMechs.get(i);
            System.out.println("\nAuthentication Mechanism:");
            System.out.println("Description : " + auth.getDescription());
            System.out.println("Type : " + auth.getAuthenticationMechanismType().getName().toLowerCase());
            System.out.println("Credential Interface : " + auth.getCredentialInterface());
        }
        System.out.println("\nRegistered classes:");
        System.out.println("\t" + ra.getConnectionFactoryImplClass() + " implements ");
        System.out.println("\t\t" + ra.getConnectionFactoryInterface());
        System.out.println("\t" + ra.getConnectionImplClass() + " implements ");
        System.out.println("\t\t" + ra.getConnectionInterface());
        System.out.println("\tManagedConnectionFactory: " + ra.getManagedConnectionFactoryClass());

        List configs = ra.getConfigProperties();
        System.out.println("\nConfig-properties:");
        for (int i = 0; i < configs.size(); i++) {
            ConfigProperty prop = (ConfigProperty) configs.get(i);
            System.out.println("\tConfig-property : " + prop.getName());
            System.out.println("\tType : " + prop.getType() + " / Value : " + prop.getValue());
            System.out.println("\tDescription : " + prop.getDescription() + "\n");
        }

        List secs = ra.getSecurityPermissions();
        System.out.println("\nSecurity-Permissions:");
        for (int i = 0; i < secs.size(); i++) {
            SecurityPermission prop = (SecurityPermission) secs.get(i);
            System.out.println("\tConfig-Description : " + prop.getDescription());
            System.out.println("\tSpecification : " + prop.getSpecification() + "\n");
        }

    }
    public static junit.framework.Test suite() {
        return new TestSuite(RarFileTests.class);
    }
    public void testaddCopyModule() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.rar";
        RARFile rarFile = (RARFile) getArchiveFactory().openArchive(in);

        rarFile.getDeploymentDescriptor();

        EARFile newEarFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/CopyTests/newEarWithRarCopiedModule.ear");

        newEarFile.addCopy(rarFile);
        RARFile copied = (RARFile) newEarFile.getModuleFiles().get(0);
        assertTrue(copied.isDeploymentDescriptorSet());
        assertTrue(copied.getDeploymentDescriptor() == copied.getDeploymentDescriptorResource().getContents().get(0));
        assertTrue(copied.getDeploymentDescriptor() != rarFile.getDeploymentDescriptor());
        assertTrue(rarFile.getFiles().size() == copied.getFiles().size());

        newEarFile.saveNoReopen();
    }
    public void testexpandRarModule() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.rar";
        RARFile rarFile = (RARFile) getArchiveFactory().openArchive(in);
        rarFile.setURI("sample.rar");

        rarFile.getDeploymentDescriptor();

        EARFile newEarFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/RarTests/newEarWithRarModule.ear");
        newEarFile.addCopy(rarFile);

        newEarFile.saveNoReopen();

        String out = AutomatedBVT.baseDirectory + "testOutput/RarTests/Rar-containing-ear-out.ear";
        newEarFile.extractTo(out, Archive.EXPAND_RAR_FILES);
    }
    
    /**
     * Very basic method to open an archive and save it as a directory to a new destination.  Requires visual inspection
     * for verification.  Nested wars and ejb jars will be exploded but other nested jars will not
     */
    public void testOpenAndExtract() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.rar";
        RARFile rarFile = (RARFile) getArchiveFactory().openArchive(in);
        String out = AutomatedBVT.baseDirectory + "testOutput/Exploded_RAR_Dir/";
        rarFile.extractToConnectorDirectory(out, Archive.EXPAND_ALL);
    }
    /**
     * Opens an ear file, and for each module, writes it's dd components to the console.  Requires visual inspection
     * for verification
     */

    public void testOpenAndRead() throws Exception {

        String in = AutomatedBVT.baseDirectory + "sample.rar";
        RARFile rarFile = (RARFile) getArchiveFactory().openArchive(in);

		Connector dd = rarFile.getDeploymentDescriptor();
		//printRARToConsole(dd);
	}
	
	public void testopenRarEar() throws Exception {
		EARFile earFile = (EARFile) getArchiveFactory().openArchive(AutomatedBVT.baseDirectory + "testrar.ear");
		assertTrue(earFile.getDeploymentDescriptor().getFirstModule("sample.rar") != null);
	}
	
	public void testSaveRarEar() throws Exception {
		String in = AutomatedBVT.baseDirectory + "sample.rar";
		RARFile rarFile = (RARFile) getArchiveFactory().openArchive(in);
		rarFile.setURI("sample.rar");

        rarFile.getDeploymentDescriptor();

        EARFile newEarFile = getArchiveFactory().createEARFileInitialized(AutomatedBVT.baseDirectory + "testOutput/RarTests/newEarWithRarModule_saved.ear");
        newEarFile.addCopy(rarFile);

        assertNotNull("Module wasn't found!", newEarFile.getDeploymentDescriptor().getFirstModule("sample.rar"));

        newEarFile.save();
    }

    public void testJ2Cauth() throws Exception {
        String in = AutomatedBVT.baseDirectory + "sample.rar";
        RARFile rarFile = (RARFile) getArchiveFactory().openArchive(in);

        Connector dd = rarFile.getDeploymentDescriptor();

        ResourceAdapter rd = dd.getResourceAdapter();

        EList myAuthList = rd.getAuthenticationMechanisms();
//        System.out.println("Number of auth mechanisms is: " + myAuthList.size());
//        for (int i = 0; i < myAuthList.size(); i++) {
//            System.out.println("Auth type is: " + ((AuthenticationMechanism) myAuthList.get(i)).getAuthenticationMechanismType());
//            System.out.println("Auth type is: " + ((AuthenticationMechanism) myAuthList.get(i)).getAuthenticationMechanismType().getName());
//        }
    }

}
