package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jem.java.impl.JavaClassImpl;
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
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.RoleSource;
import org.eclipse.jst.j2ee.ejb.impl.EJBRelationshipRoleImpl;
import org.eclipse.jst.j2ee.ejb.impl.MethodPermissionImpl;
import org.eclipse.jst.j2ee.ejb.impl.QueryMethodImpl;
import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;


public class EjbEMFTest extends GeneralEMFPopulationTest {
	static {
		//Temporary workaround to keep packages from being registered twice
		J2EEInit.init();
	}
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
	boolean firstReturnTypeMapping = true;
	public EjbEMFTest(String name) {
		super(name);
	}

	public CommonarchiveFactory getArchiveFactory() {
		return CommonarchivePackage.eINSTANCE.getCommonarchiveFactory();
	}

	public EjbFactory getEjbFactory() {
		return EJB_PKG.getEjbFactory();
	}

	public ApplicationFactory getApplicationFactory() {
		return ApplicationPackage.eINSTANCE.getApplicationFactory();
	}

	public WebapplicationFactory getWebAppFactory() {
		return WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
	}

	public static void main(java.lang.String[] args) {
		junit.textui.TestRunner.main(new String[] { EjbEMFTest.class.getName()});
	}
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();

		/*System.out.println("Switching to SAX Renderer in " + EjbEMFTest.class.getName());
		suite.addTest(new AllSAXTests("testSwitchRenderer"));*/
		suite.addTest(new EjbEMFTest("testEJBJarPopulation"));
		suite.addTest(new EjbEMFTest("test14EJBJarPopulation"));
		return suite;
	}

	public void testEJBJarPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createEJB();

		EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		//TODO: individual test for each version
		DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		setVersion(VERSION_1_3);
		setModuleType(EJB);
		populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory +"testOutput/TestEJBEAR";
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();
		setEquivalentLines(getEquivalentLinesMap());
		//Compare work in progress
		String curDir = AutomatedBVT.baseDirectory;
		String exampleDeploymentDesURI = curDir + "/EMFTestNoID/ejb-jar.xml";
		String curDeploymentDesURI = curDir + "testOutput/TestEJBEAR/fooEJB/META-INF/ejb-jar.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
//		DD.unload();
//		DD.load(new HashMap());
	}
	
	public void test14EJBJarPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createEJB();

		EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		//TODO: individual test for each version
		DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(EJB);
		//setGeneralDepth(2);
		populateRoot(DD.getRootObject());
		String out = AutomatedBVT.baseDirectory +"testOutput/TestEJBEAR14";
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();
		
		setEquivalentLines(getEquivalentLinesMap());
		getEJB();
		assertEquals("2.1", ejbFile.getDeploymentDescriptor().getVersion());
		out = AutomatedBVT.baseDirectory +"testOutput/TestEJBEAR14_2";
		ejbFile.extractTo(out, Archive.EXPAND_ALL);
		ejbFile.close();

		//Compare work in progress
		String curDir = AutomatedBVT.baseDirectory;
		String exampleDeploymentDesURI = out + "/META-INF/ejb-jar.xml";
		String curDeploymentDesURI = curDir + "testOutput/TestEJBEAR14/fooEJB/META-INF/ejb-jar.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
	}
	
	/**
	 * @return
	 */
	private Map getEquivalentLinesMap() {
		Map lines = new HashMap();
		lines.put("<cascade-delete></cascade-delete>", "<cascade-delete/>");
		lines.put("<method-params></method-params>","<method-params/>");
		lines.put("<unchecked></unchecked>","<unchecked/>"); 
		lines.put("<use-caller-identity></use-caller-identity>","<use-caller-identity/>");
		return lines;
	}

	public void getEJB() throws DuplicateObjectException, OpenFailureException {
		String in = AutomatedBVT.baseDirectory +"testOutput/TestEJBEAR14/fooEJB";
		ejbFile = getArchiveFactory().openEJBJarFile(in);
		assertTrue(ejbFile.getDeploymentDescriptor() != null);
	}

	public void createEJB() throws DuplicateObjectException {
		ejbFile = getArchiveFactory().createEJBJarFileInitialized("fooEJB");
		ejbFile = (EJBJarFile) earFile.addCopy(ejbFile);
		ejbFile.getDeploymentDescriptor().setDisplayName("fooEJB");
		assertTrue(ejbFile.getDeploymentDescriptor() != null);
	}

	public void createEAR() {
		String earName = "Test.ear";
		earFile = getArchiveFactory().createEARFileInitialized(earName);
		assertTrue(earFile.getDeploymentDescriptor() != null);
	}

	public EObject createInstance(EReference ref, EObject eObject) {
		EClass eClassifier = (EClass)ref.getEType();
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
		return super.createInstance(ref,eObject);
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
	public int getDepthForAttribute(EStructuralFeature ref) {
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
			((CMPAttribute) eObject).setEType((EClassifier) EMFAttributeFeatureGenerator.createJavaClassProxy(EcorePackage.eINSTANCE.getETypedElement_EType(),eObject));
		} else if (eObject.eClass() == EJB_PKG.getCMRField()) {
			((CMRField) eObject).setName((String) EMFAttributeFeatureGenerator.createAttribute(EcorePackage.eINSTANCE.getENamedElement_Name(),eObject));
			populateSharedReference(eObject, EJB_PKG.getCMRField_CollectionType());
			populateSharedReference(eObject, EJB_PKG.getCMRField_Role());		
		} else
			super.populateFeatures(eObject);
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#populateAttributes(org.eclipse.emf.ecore.EObject)
	 */
	protected void populateAttributes(EObject eObject) {
		if (eObject instanceof QueryMethodImpl) {
			List attributes = eObject.eClass().getEAllAttributes();
			for (int i = 0; i < attributes.size(); i++) { 
				EAttribute att = (EAttribute) attributes.get(i);
				if (att.equals(EJB_PKG.getMethodElement_Description()) || att.equals(EJB_PKG.getMethodElement_EnterpriseBean()) || att.equals(EJB_PKG.getMethodElement_Type()) )
					continue;
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
		} else if (eObject instanceof EJBRelationshipRoleImpl) {
			List attributes = eObject.eClass().getEAllAttributes();
			EAttribute lastAttr = null;
			Object value = null;
			for (int i = 0; i < attributes.size(); i++) {
				EAttribute att = (EAttribute) attributes.get(i);
				if (att.getName().equals("cascadeDelete") && (lastAttr.getName().equals("multiplicity") && !value.toString().equals("Many")))
					continue;

				value = createAttributeValue(att, eObject);
				if (att.isChangeable())
					eObject.eSet(att, value);
				lastAttr = att;
		}
			mpFlag = !mpFlag;
		} else
			super.populateAttributes(eObject);
	}
	

	protected void populateSharedReference(EObject eObject, EReference ref) {
		if (ref == EJB_PKG.getCMRField_CollectionType()) {
			setReferenceValue(eObject, ref, JavaClassImpl.createClassRef("java.util.Collection"));
			return;
		} else if(eObject instanceof MethodPermission && ref.getName().equals("roles")){
			//if method permission unchecked ignore roles
			if(mpFlag){
				return;
			}
			mpFlag = !mpFlag;
		}
		super.populateSharedReference(eObject, ref);
	}

	public HashSet ignorableAttributes(){
		HashSet set = new HashSet();
		set.add("id");
		return set;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#createAttributeValue(org.eclipse.emf.ecore.EAttribute, org.eclipse.emf.ecore.EObject)
	 */
	protected Object createAttributeValue(EAttribute att, EObject eObject) {
		//eat the first return type mapping because the order was changed.
		if (firstReturnTypeMapping && att.equals(EJB_PKG.getQuery_ReturnTypeMapping())){
			super.createAttributeValue(att,eObject);
			firstReturnTypeMapping = false;
		} else if (att.equals(EJB_PKG.getEnterpriseBean_Name()))
			return EMFAttributeFeatureGenerator.createAttribute(att,EJB_PKG.getEntity());
		return super.createAttributeValue(att, eObject);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		earFile = null;
		ejbFile = null;
		mesBean = null;
		entityBean = null;
		secID = null;
		roleSource = null;

		
}
}
