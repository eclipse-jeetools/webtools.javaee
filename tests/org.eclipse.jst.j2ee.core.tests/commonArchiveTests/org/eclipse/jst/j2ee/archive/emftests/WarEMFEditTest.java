package org.eclipse.jst.j2ee.archive.emftests;

import java.util.HashSet;
import java.util.List;

import junit.framework.TestSuite;
import junit.swingui.TestRunner;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.impl.FilterMappingImpl;


public class WarEMFEditTest extends GeneralEMFEditingTest {
	protected EARFile earFile;	
	protected WARFile warFile;
	protected int createdWebTypes = 0;
	protected int createdErrorPages = 0;
	protected boolean fmFlag = false;

    public WarEMFEditTest(String name) {
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
        String[] className = { "com.ibm.etools.archive.test.WarEMFTest", "-noloading" };
        TestRunner.main(className);
    }
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new WarEMFEditTest("testWAREdit"));
        return suite;
    }

    public void testWAREdit() throws Exception {
        getWARClient();

        WebAppResource DD = (WebAppResource) warFile.getDeploymentDescriptorResource();
		setVersion(VERSION_1_3);
		setModuleType(WEB); 
        editRoot(DD.getRootObject());
        String curDir = AutomatedBVT.baseDirectory;
        String out = curDir + "testOutput/EMFModelCreationTests/EditWarEAR";
        warFile.extractTo(out, Archive.EXPAND_ALL);
        warFile.close();

        //Compare work in progress
       
        String exampleDeploymentDesURI = curDir + "EMFTests/web.xml";
        String curDeploymentDesURI = curDir + out + "/fooWAR/WEB-INF/web.xml";
        //compareContents(curDeploymentDesURI, exampleDeploymentDesURI);
    }

    public void getWARClient() throws DuplicateObjectException, OpenFailureException {
        String in = AutomatedBVT.baseDirectory + "loose_module_workspace/LooseEARWeb/webApplication/";
        warFile = getArchiveFactory().openWARFile(in);
        assertTrue(warFile.getDeploymentDescriptor() != null);
    }
	public EObject createInstance(EClass eClassifier) {

		if (WebapplicationPackage.eINSTANCE.getWebType().equals(eClassifier))
			return createWebType();
		else if (WebapplicationPackage.eINSTANCE.getErrorPage().equals(eClassifier))
			return createErrorPage();

		return super.createInstance(eClassifier);
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
			super.editAttributes(eObject);
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
}
