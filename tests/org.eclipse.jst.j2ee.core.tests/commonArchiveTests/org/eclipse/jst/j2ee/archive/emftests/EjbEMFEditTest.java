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
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.internal.ejb.impl.MethodPermissionImpl;
import org.eclipse.jst.j2ee.internal.ejb.impl.QueryMethodImpl;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emf.resource.EMF2SAXRendererFactory;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;


public class EjbEMFEditTest extends GeneralEMFEditingTest {

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

    public EjbEMFEditTest(String name) {
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
		junit.textui.TestRunner.main(new String[] { EjbEMFEditTest.class.getName() }); 
    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        
        /*System.out.println("Switching to SAX Renderer in " + EjbEMFEditTest.class.getName());
		suite.addTest(new AllSAXTests("testSwitchRenderer"));*/
		
        suite.addTest(new EjbEMFEditTest("testEJBJarEdit"));
        suite.addTest(new EjbEMFEditTest("testWCCMJar"));
        /*suite.addTest(new EjbEMFEditTest("testWCCMProvidedCase"));*/
        
        return suite;
    }

    public void testEJBJarEdit() throws Exception {
        getEJB();

		assertEquals("2.0", ejbFile.getDeploymentDescriptor().getVersion());
        EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		setVersion(VERSION_1_3);
		setModuleType(EJB); 
        editRoot(DD.getRootObject());
        String curDir = AutomatedBVT.baseDirectory;
        String out = curDir + "testOutput/EMFModelCreationTests/EditEjbEAR";
        ejbFile.extractTo(out, Archive.EXPAND_ALL);
        ejbFile.close();
        
        
        //Compare work in progress
        
        String exampleDeploymentDesURI = null;
        //System.out.println(RendererFactory.getDefaultRendererFactory());
        if (RendererFactory.getDefaultRendererFactory() instanceof EMF2SAXRendererFactory){
            exampleDeploymentDesURI = curDir + "EMFTests/ejb-jar2-0sax.xml";
        }
        else
            exampleDeploymentDesURI = curDir + "EMFTests/ejb-jar2-0.xml";
        //System.out.println("File: " + exampleDeploymentDesURI);
        String curDeploymentDesURI = out + "/META-INF/ejb-jar.xml";
        compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, "");
    }

    public void testWCCMJar() throws Exception {
		String in = AutomatedBVT.baseDirectory + "../testData/ejb";
		ArchiveOptions options = new ArchiveOptions();
		options.setIsReadOnly(true);
		ejbFile = getArchiveFactory().openEJBJarFile(options, in); 
		 
        EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		DD.getRootObject();

        String out = AutomatedBVT.baseDirectory + "../testData/testOutput";
        ejbFile.extractTo(out, Archive.EXPAND_ALL);
        ejbFile.close();

        /*DD.save(System.out, null); */
        
        //Compare work in progress
        String curDir = AutomatedBVT.baseDirectory;
        String exampleDeploymentDesURI = in + "/META-INF/ejb-jar.xml";
        String curDeploymentDesURI = out + "/META-INF/ejb-jar.xml";

        compareContents(curDeploymentDesURI, exampleDeploymentDesURI);
    }
    
    /**
     * Requires j2ee.core.ws.ext to be on the classpath
     * @throws DuplicateObjectException
     * @throws OpenFailureException
     */
/*    public void testWCCMProvidedCase() throws Exception {

		String in = AutomatedBVT.baseDirectory + "../wccm/ear";
        EARFile earFile = null;
        try {
 
            CommonarchiveFactory factory =
                    CommonarchiveFactoryImpl.getActiveFactory();
            ArchiveOptions opt = new ArchiveOptions();
            opt.setIsReadOnly (true);
            opt.setUseJavaReflection (false);
 
            earFile = factory.openEARFile (opt, in);
            System.out.println ("uri: " + earFile.getURI());
            System.out.println ("origuri: " + earFile.getOriginalURI());
 
            Application application = earFile.getDeploymentDescriptor();
            ApplicationBinding appBindings = earFile.getBindings();
            ApplicationExtension appExtensions = earFile.getExtensions();
 

        Iterator rs = earFile.getLoadedMofResources().iterator();
        while (rs.hasNext())
        {
            Resource r = (Resource) rs.next();
 
            FileOutputStream fo = new FileOutputStream (r.getURI().toString().replace('/', '-'));
            r.save (fo, new HashMap());
            fo.flush();
            fo.close();
        }
 
            System.out.println("==========<Done App DD>==========");
            System.out.println ("");
 

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(earFile != null)
            earFile.close();
        }

    }*/

    public void getEJB() throws DuplicateObjectException, OpenFailureException {
		String in = AutomatedBVT.baseDirectory + "loose_module_workspace/LooseEAREjb/ejbModule/";
		 ejbFile = getArchiveFactory().openEJBJarFile(in);
        assertTrue(ejbFile.getDeploymentDescriptor() != null);
    }

	public EObject createInstance(EClass eClassifier) {
		if (eClassifier.getName().equals("EnterpriseBean")) {
			createdBeans++;
			switch (createdBeans) {
				case (3) :
					return createContainerManagedEntityInstance(eClassifier);
				case (7) :
					return createMessageBeanInstance(eClassifier);
				case (4) :
				case (8) :
					return createSessionBeanInstance(eClassifier);
				default :
					return createEntityBeanInstance(eClassifier);
			}
		} else if (eClassifier.getName().equals("SecurityIdentity"))
			return createSecurityIdentitiyInstance(eClassifier);
		else if (eClassifier.getName().equals("RoleSource"))
			return createRoleSourceInstance(eClassifier);
		else if (eClassifier == EcorePackage.eINSTANCE.getEAttribute())
			return getEjbFactory().createCMPAttribute();
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
		else if (ref.getName().equals("relationshipRoles"))
			return NUM_RELATION_ROLES;
		return super.getDepthForAttribute(ref);
	}

	/* (non-Javadoc)
	* @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#populateFeatures(org.eclipse.emf.ecore.EObject)
	*/
	public void populateFeatures(EObject eObject) {
		if (eObject.eClass() == EJB_PKG.getCMPAttribute()) {
			((CMPAttribute) eObject).setName((String) EMFAttributeFeatureGenerator.createAttribute(EcorePackage.eINSTANCE.getENamedElement_Name(),eObject));
			((CMPAttribute) eObject).setEType((EClassifier) createJavaClassProxy(null));
		} else
			super.editFeatures(eObject);
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#populateAttributes(org.eclipse.emf.ecore.EObject)
	 */
	protected void populateAttributes(EObject eObject) {
		if (eObject instanceof QueryMethodImpl) {
			List attributes = eObject.eClass().getEAllAttributes();
			for (int i = 0; i < attributes.size(); i++) {
				EAttribute att = (EAttribute) attributes.get(i);
				Object value = createAttributeValue(att, eObject);
				if (att.getName().equals("parms") && value == null){
					value = createAttributeValue(att, eObject);
				}
				if (att.isChangeable())
					eObject.eSet(att, value);
			}
		} else if (eObject instanceof MethodPermissionImpl) {
			List attributes = eObject.eClass().getEAllAttributes();
			for (int i = 0; i < attributes.size(); i++) {
				EAttribute att = (EAttribute) attributes.get(i);
				if(att.getName().equals("unchecked") && mpFlag){
					continue;					
				}	
				
				Object value = createAttributeValue(att, eObject);
				if (att.isChangeable())
					eObject.eSet(att, value);
			} 
			mpFlag = !mpFlag;
		} else
			super.editAttributes(eObject);
	}
	

	protected void populateSharedReference(EObject eObject, EReference ref) {
		if(eObject instanceof MethodPermissionImpl && ref.getName().equals("roles")){
			mpFlag = !mpFlag;
			//if method permission unchecked ignore roles
			if(!mpFlag){
				return;
			}
		}
		super.populateSharedReference(eObject, ref);
	}

	public HashSet ignorableAttributes(){
		HashSet set = new HashSet();
		set.add("id");
		return set;
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		earFile = null;
		ejbFile = null;
		mesBean = null;
		entityBean = null;
		secID  = null;
		roleSource = null;

		super.tearDown();
	}

}
