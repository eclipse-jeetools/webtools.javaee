package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;
import java.util.List;

import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.ejb.impl.MethodPermissionImpl;
import org.eclipse.jst.j2ee.ejb.impl.QueryMethodImpl;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class ResolverTest extends GeneralEMFEditingTest {

    protected static final String _META_INF_EJB_JAR_XML = "/META-INF/ejb-jar.xml";

    protected static final EjbPackage EJB_PKG = EjbPackage.eINSTANCE;

    EARFile earFile;

    EJBJarFile ejbFile;

    EObject mesBean, entityBean;

    SecurityIdentity secID;

    RoleSource roleSource;

    int NUM_BEANS = 10;

    final int NUM_RELATION_ROLES = 2;

    int createdBeans = 0;

    int createdSecRoles = 0;

    protected int createdSecurityIdentities = 0;

    boolean mpFlag = false;

    public ResolverTest(String name) {
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
        junit.textui.TestRunner.main(new String[] { ResolverTest.class.getName()});
    }

    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        /*suite.addTest(new AllSAXTests("testSwitchRenderer"));*/
        suite.addTest(new ResolverTest("testResolver"));
        suite.addTest(new ResolverTest("testResolverNoSchemaLocation"));
        return suite;
    }

    public void testResolver() throws Exception {
        String in = AutomatedBVT.baseDirectory + "../testData/sl";
        getEJB(in);

        assertEquals("2.1", ejbFile.getDeploymentDescriptor().getVersion());
        EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
        setVersion(VERSION_1_4);
        setModuleType(EJB);

        String out = AutomatedBVT.baseDirectory + "../testData/testOutput/sl";
        ejbFile.extractTo(out, Archive.EXPAND_ALL);
        ejbFile.close();

        String exampleDeploymentDesURI = in + _META_INF_EJB_JAR_XML;
        String curDeploymentDesURI = out + _META_INF_EJB_JAR_XML;
        compareContents(curDeploymentDesURI, exampleDeploymentDesURI);
    }

    public void testResolverNoSchemaLocation() throws Exception {

        boolean validating = RendererFactory.getDefaultRendererFactory().isValidating();
        try { 
            RendererFactory.getDefaultRendererFactory().setValidating(false);
            
            String in = AutomatedBVT.baseDirectory + "../testData/no-sl";
            getEJB(in);

            assertEquals("2.1", ejbFile.getDeploymentDescriptor().getVersion());
            EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
            setVersion(VERSION_1_4);
            setModuleType(EJB);

            String out = AutomatedBVT.baseDirectory + "../testData/testOutput/no-sl";
            ejbFile.extractTo(out, Archive.EXPAND_ALL);
            ejbFile.close();

            String exampleDeploymentDesURI = in + _META_INF_EJB_JAR_XML;
            String curDeploymentDesURI = out + _META_INF_EJB_JAR_XML;
            compareContents(curDeploymentDesURI, exampleDeploymentDesURI);
        } catch (RuntimeException re) {
            re.printStackTrace();
        } finally {
            RendererFactory.getDefaultRendererFactory().setValidating(validating);
        }
    }

    public void getEJB(String in) throws DuplicateObjectException, OpenFailureException {
        ejbFile = getArchiveFactory().openEJBJarFile(in);
        assertTrue(ejbFile.getDeploymentDescriptor() != null);
    }

    public EObject createInstance(EClass eClassifier) {
        if (eClassifier.getName().equals("EnterpriseBean")) {
            createdBeans++;
            switch (createdBeans) {
            case (3):
                return createContainerManagedEntityInstance(eClassifier);
            case (7):
                return createMessageBeanInstance(eClassifier);
            case (4):
            case (8):
                return createSessionBeanInstance(eClassifier);
            default:
                return createEntityBeanInstance(eClassifier);
            }
        } else if (eClassifier.getName().equals("SecurityIdentity"))
            return createSecurityIdentitiyInstance(eClassifier);
        else if (eClassifier.getName().equals("RoleSource"))
            return createRoleSourceInstance(eClassifier);
        else if (eClassifier == EcorePackage.eINSTANCE.getEAttribute()) return getEjbFactory().createCMPAttribute();
        return super.createInstance(eClassifier);
    }

    private EObject createSecurityIdentitiyInstance(EClass eClassifier) {
        /* Alternate types */
        createdSecRoles++;
        if ((createdSecRoles & 1) == 0)
            return CommonFactory.eINSTANCE.createUseCallerIdentity();
        else
            return CommonFactory.eINSTANCE.createRunAsSpecifiedIdentity();
    }

    private EObject createRoleSourceInstance(EClass eClassifier) {
        return ((EjbFactory) eClassifier.getEPackage().getEFactoryInstance()).createRoleSource();
    }

    private EObject createMessageBeanInstance(EClass eClassifier) {
        return ((EjbFactory) eClassifier.getEPackage().getEFactoryInstance()).createMessageDriven();
    }

    private EObject createSessionBeanInstance(EClass eClassifier) {
        return ((EjbFactory) eClassifier.getEPackage().getEFactoryInstance()).createSession();
    }

    private EObject createEntityBeanInstance(EClass eClassifier) {
        return ((EjbFactory) eClassifier.getEPackage().getEFactoryInstance()).createEntity();
    }

    private EObject createContainerManagedEntityInstance(EClass eClassifier) {
        return EjbFactory.eINSTANCE.createContainerManagedEntity();
    }

    /**
     * @see org.eclipse.jst.j2ee.archive.test.GeneralEMFTest#getDepthForAttribute(EReference)
     */
    public int getDepthForAttribute(EReference ref) {
        if (ref.getName().equals("enterpriseBeans"))
            return NUM_BEANS;
        else if (ref.getName().equals("entityBeans"))
            return NUM_BEANS;
        else if (ref.getName().equals("relationshipRoles")) return NUM_RELATION_ROLES;
        return super.getDepthForAttribute(ref);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#populateFeatures(org.eclipse.emf.ecore.EObject)
     */
    public void populateFeatures(EObject eObject) {
        if (eObject.eClass() == EJB_PKG.getCMPAttribute()) {
            ((CMPAttribute) eObject).setName((String) EMFAttributeFeatureGenerator.createAttribute(
                    EcorePackage.eINSTANCE.getENamedElement_Name(), eObject));
            ((CMPAttribute) eObject).setEType((EClassifier) createJavaClassProxy(null));
        } else
            super.editFeatures(eObject);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#populateAttributes(org.eclipse.emf.ecore.EObject)
     */
    protected void populateAttributes(EObject eObject) {
        if (eObject instanceof QueryMethodImpl) {
            List attributes = eObject.eClass().getEAllAttributes();
            for (int i = 0; i < attributes.size(); i++) {
                EAttribute att = (EAttribute) attributes.get(i);
                Object value = createAttributeValue(att, eObject);
                if (att.getName().equals("parms") && value == null) {
                    value = createAttributeValue(att, eObject);
                }
                if (att.isChangeable()) eObject.eSet(att, value);
            }
        } else if (eObject instanceof MethodPermissionImpl) {
            List attributes = eObject.eClass().getEAllAttributes();
            for (int i = 0; i < attributes.size(); i++) {
                EAttribute att = (EAttribute) attributes.get(i);
                if (att.getName().equals("unchecked") && mpFlag) {
                    continue;
                }

                Object value = createAttributeValue(att, eObject);
                if (att.isChangeable()) eObject.eSet(att, value);
            }
            mpFlag = !mpFlag;
        } else
            super.editAttributes(eObject);
    }

    protected void populateSharedReference(EObject eObject, EReference ref) {
        if (eObject instanceof MethodPermissionImpl && ref.getName().equals("roles")) {
            mpFlag = !mpFlag;
            //if method permission unchecked ignore roles
            if (!mpFlag) { return; }
        }
        super.populateSharedReference(eObject, ref);
    }

    public HashSet ignorableAttributes() {
        HashSet set = new HashSet();
        set.add("id");
        return set;
    }

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        earFile = null;
        ejbFile = null;
        mesBean = null;
        entityBean = null;
        secID = null;
        roleSource = null;

        super.tearDown();
    }

}