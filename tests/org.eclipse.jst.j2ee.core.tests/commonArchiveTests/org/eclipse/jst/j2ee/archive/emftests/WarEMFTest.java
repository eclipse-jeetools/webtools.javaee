package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.common.ResAuthTypeBase;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.internal.webapplication.impl.FilterMappingImpl;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;


public class WarEMFTest extends GeneralEMFPopulationTest {
    protected EARFile earFile;
    protected WARFile warFile;
    protected int createdWebTypes = 0;
    protected int createdErrorPages = 0;
	protected boolean fmFlag = false;
    public WarEMFTest(String name) {
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
        
        junit.textui.TestRunner.main(new String[]{ WarEMFTest.class.getName() });
    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new WarEMFTest("testWARPopulation"));
		suite.addTest(new WarEMFTest("test14WARPopulation"));
        return suite;
    }

    public void testWARPopulation() throws Exception {
        EMFAttributeFeatureGenerator.reset();
        createEAR();
        createWARClient();

        WebAppResource DD = (WebAppResource) warFile.getDeploymentDescriptorResource();
        DD.setVersionID(J2EEVersionConstants.J2EE_1_3_ID);
		//TODO: individual test for each version
		setVersion(VERSION_1_3);
		setModuleType(WEB);
        populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory +"testOutput/TestWarEAR";
        earFile.extractTo(out, Archive.EXPAND_ALL);
        earFile.close();

        //Compare work in progress
        String curDir = AutomatedBVT.baseDirectory;
        String exampleDeploymentDesURI = curDir + "EMFTestNoID/web.xml";
        String curDeploymentDesURI = curDir + "testOutput/TestWarEAR/fooWAR/WEB-INF/web.xml";
		setIgnoreAtt(ignorableAttributes());
		setEquivalentLines(getEquivalentLinesMap());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
    }
    
	public void test14WARPopulation() throws Exception {
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createWARClient();

		WebAppResource DD = (WebAppResource) warFile.getDeploymentDescriptorResource();
		DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		//TODO: individual test for each version
		setVersion(VERSION_1_4);
		setModuleType(WEB);
		//setGeneralDepth(2);
		populateRoot(DD.getRootObject());

		String out = AutomatedBVT.baseDirectory +"testOutput/TestWarEAR14";
		earFile.extractTo(out, Archive.EXPAND_ALL);
		earFile.close();
		
		getWARClient();
		assertEquals("2.4", warFile.getDeploymentDescriptor().getVersion());
		out = AutomatedBVT.baseDirectory +"testOutput/TestWarEAR14_2";
		warFile.extractTo(out, Archive.EXPAND_ALL);
		warFile.close();

		//Compare work in progress
		String curDir = AutomatedBVT.baseDirectory;
		String exampleDeploymentDesURI = out + "/WEB-INF/web.xml";
		String curDeploymentDesURI = curDir + "testOutput/TestWarEAR14/fooWAR/WEB-INF/web.xml";
		setIgnoreAtt(ignorableAttributes());
		compareContentsIgnoreWhitespace(curDeploymentDesURI, exampleDeploymentDesURI, null);
	}
	
	/**
	 * @return
	 */
	private Map getEquivalentLinesMap() {
		Map lines = new HashMap();
		lines.put("<distributable></distributable>", "<distributable/>"); 
		return lines;
	}

    public void createWARClient() throws DuplicateObjectException {
        warFile = getArchiveFactory().createWARFileInitialized("fooWAR");
        warFile = (WARFile) earFile.addCopy(warFile);
        warFile.getDeploymentDescriptor().setDisplayName("fooWAR");
        assertTrue(warFile.getDeploymentDescriptor() != null);
    }

    public void createEAR() {
        String earName = "Test.ear";
        earFile = getArchiveFactory().createEARFileInitialized(earName);
        assertTrue(earFile.getDeploymentDescriptor() != null);
    }
    
	public void getWARClient() throws DuplicateObjectException, OpenFailureException {
		String in = AutomatedBVT.baseDirectory + "testOutput/TestWarEAR14/fooWAR";
		warFile = getArchiveFactory().openWARFile(in);
		assertTrue(warFile.getDeploymentDescriptor() != null);
	}

    public EObject createInstance(EReference ref,EObject eObject) {

        if (WebapplicationPackage.eINSTANCE.getWebType().equals(ref.getEType()))
            return createWebType();
        else if (WebapplicationPackage.eINSTANCE.getErrorPage().equals(ref.getEType()))
            return createErrorPage();

        return super.createInstance(ref, eObject);
    }
    /* The web type is abstract.  Alternate between servlet-class
     * and jsp-file
     */
    public WebType createWebType() {
        createdWebTypes++;
        if ((createdWebTypes & 1) == 0)
            return WebapplicationFactory.eINSTANCE.createServletType();
        else
            return WebapplicationFactory.eINSTANCE.createJSPType();

    }
    /* The error page is abstract.  Alternate between exceptiontype
     * and error code
     */
    public ErrorPage createErrorPage() {
        createdErrorPages++;
        if ((createdErrorPages & 1) == 0)
            return WebapplicationFactory.eINSTANCE.createErrorCodeErrorPage();
        else
            return WebapplicationFactory.eINSTANCE.createExceptionTypeErrorPage();
    }
	/* (non-Javadoc)
	 * @see com.ibm.etools.archive.emftest.GeneralEMFPopulationTest#populateAttributes(org.eclipse.emf.ecore.EObject)
	 */
	protected void populateAttributes(EObject eObject) {
		if (eObject instanceof FilterMappingImpl){
			List attributes = eObject.eClass().getEAllAttributes();
			for (int i = 0; i < attributes.size(); i++) {
				EAttribute att = (EAttribute) attributes.get(i);
				if(att.getName().equals("urlPattern") && fmFlag){
					continue;					
				}	
				
				Object value = createAttributeValue(att, eObject);
				if (att.isChangeable())
					eObject.eSet(att, value);
			} 
			fmFlag = !fmFlag;
		}
		else 
			super.populateAttributes(eObject);
	}
	

	protected void populateSharedReference(EObject eObject, EReference ref) {
		if(eObject instanceof FilterMappingImpl && ref.getName().equals("servlet")){
			fmFlag = !fmFlag;
			//if method permission unchecked ignore roles
			if(!fmFlag){
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


	protected boolean isValidAuth(ResAuthTypeBase auth) {
		if (version == VERSION_1_2)
			return auth == ResAuthTypeBase.SERVLET_LITERAL || auth == ResAuthTypeBase.CONTAINER_LITERAL;
		else
			return super.isValidAuth(auth);
	}

}
