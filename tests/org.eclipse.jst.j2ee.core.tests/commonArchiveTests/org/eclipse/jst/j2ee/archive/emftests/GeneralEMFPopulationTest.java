package org.eclipse.jst.j2ee.archive.emftests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.archive.testutilities.J2EEVersionCheck;
import org.eclipse.jst.j2ee.archive.testutilities.TestUtilities;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.ResAuthTypeBase;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.wst.common.tests.BaseTestCase;


public class GeneralEMFPopulationTest extends BaseTestCase {
    //inner class to handle deffered shared references
    protected class DeferredSharedReferenceAction {
        EObject owner;
        EReference ref;

        public DeferredSharedReferenceAction(EObject owner, EReference ref) {
            this.owner = owner;
            this.ref = ref;
        }

        public void performAction() {
            Object value = getSharedObjectByType(owner, ref);
            setReferenceValue(owner, ref, value);
        }
    }

    protected Map equivalentLines;
    public List deferedReferences;
    public static int genDepth = 5;
    public static Object sharedValue;
    public static HashSet ignoreAtt;

    public int version;
    public int moduleType;
    //static versions
    public static final int VERSION_1_2 = 0;
    public static final int VERSION_1_3 = 1;
    public static final int VERSION_1_4 = 2;

    public static final int APPICATION = 0;
    public static final int APP_CLIENT = 1;
    public static final int CONNECTOR = 2;
    public static final int EJB = 3;
    public static final int WEB = 4;

    public GeneralEMFPopulationTest(String name) {
        super(name);
    }

    public void populateRoot(EObject eObject) {
        populateFeatures(eObject);
        if (deferedReferences != null) {
            for (int i = 0; i < deferedReferences.size(); i++) {
                ((DeferredSharedReferenceAction) deferedReferences.get(i)).performAction();
            }
        }
    }

    public void populateFeatures(EObject eObject) {
        if (eObject == null)
            return;
        else {
            populateAttributes(eObject);
            populateReferences(eObject);
        }
    }

    protected void populateReferences(EObject eObject) {
        List references = eObject.eClass().getEAllReferences();
        for (int i = 0; i < references.size(); i++) {
            EReference ref = (EReference) references.get(i);
            if (!ref.isMany() && eObject.eGet(ref) != null)
                continue;
			if (eObject instanceof MessageDriven && (ref.equals(EjbPackage.eINSTANCE.getEnterpriseBean_HomeInterface()) || ref.equals(EjbPackage.eINSTANCE.getEnterpriseBean_RemoteInterface()) || ref.equals(EjbPackage.eINSTANCE.getEnterpriseBean_LocalInterface()) || ref.equals(EjbPackage.eINSTANCE.getEnterpriseBean_LocalHomeInterface()) || ref.equals(EjbPackage.eINSTANCE.getEnterpriseBean_SecurityRoleRefs())))
				continue;
            if (ref.isContainment())
                populateContainmentReference(eObject, ref);
            else
                populateSharedReference(eObject, ref);
        }
    }

    protected void populateSharedReference(EObject eObject, EReference ref) {
        if (ref.getEType() == JavaRefPackage.eINSTANCE.getJavaClass())
            setReferenceValue(eObject, ref, EMFAttributeFeatureGenerator.createJavaClassProxy(ref,eObject));
        else {
            EPackage pkg = ref.getEType().getEPackage();
            //if (pkg == eObject.eClass().getEPackage() || pkg == CommonPackage.eINSTANCE) {
                if (eObject.eClass().getName().equals("EAnnotation") || eObject.eClass().getName().equals("EAnnotationImpl") || !J2EEVersionCheck.checkReferenceVersion(ref, version, moduleType))
                    return;
                if (deferedReferences == null)
                    deferedReferences = new ArrayList();
                deferedReferences.add(new DeferredSharedReferenceAction(eObject, ref));
            //}
        }
    }

    protected void populateContainmentReference(EObject eObject, EReference ref) {
        for (int i = 0; i < getDepthForAttribute(ref); i++) {
            EObject instance = createInstance(ref,eObject);
            if (instance == null)
                return;
            if (!J2EEVersionCheck.checkReferenceVersion(ref, version, moduleType))
                continue;
            setReferenceValue(eObject, ref, instance);
            if (((InternalEObject) instance).eIsProxy())
                return;
            if (ref.getEType() == eObject.eClass())
                populateAttributes(instance);
            else
                populateFeatures(instance);
        }
    }

    protected void setReferenceValue(EObject eObject, EStructuralFeature ref, Object value) {
        if (ref.getName().equals("EAnnotation") || ref.getName().equals("EAnnotationImpl") || !J2EEVersionCheck.checkReferenceVersion(ref, version, moduleType))
            return;
        if (ref.isMany()) {
            List list = (List) eObject.eGet(ref);
            if (value instanceof Collection)
                list.addAll((Collection) value);
            else
                list.add(value);
        } else {
            eObject.eSet(ref, value);
        }
    }

    public EObject createInstance(EReference ref, EObject eObject) {
        if (ref.getEType() == JavaRefPackage.eINSTANCE.getJavaClass())
            return EMFAttributeFeatureGenerator.createJavaClassProxy(ref, eObject);
        return ref.getEType().getEPackage().getEFactoryInstance().create((EClass)ref.getEType());
    }

    protected void populateAttributes(EObject eObject) {
    	if (eObject.eClass() == CommonPackage.eINSTANCE.getQName()) {
    		populateAttributesQName(eObject);
    		return;
    	}
        List attributes = eObject.eClass().getEAllAttributes();
        for (int i = 0; i < attributes.size(); i++) {
            EAttribute att = (EAttribute) attributes.get(i);
            if (eObject instanceof EJBLocalRef && (att.equals(CommonPackage.eINSTANCE.getEjbRef_Home()) || att.equals(CommonPackage.eINSTANCE.getEjbRef_Remote())))
            	continue;
            primPopulateAttrbute(eObject, att);
        }
    }

	private void primPopulateAttrbute(EObject eObject, EAttribute att) {
            if (att.isChangeable() && J2EEVersionCheck.checkAttributeVersion(att, version, moduleType)) {
                for (int j = 0 ; j < getDepthForAttribute(att);j++){
	                Object value = createAttributeValue(att, eObject);
	                setReferenceValue(eObject,att,value);
                }
            }
        }
    
    

    /**
	 * @param eObject
	 */
	private void populateAttributesQName(EObject eObject) {
		String prefix = (String)createAttributeValue(CommonPackage.eINSTANCE.getQName_InternalPrefixOrNsURI(), eObject);
		String localPart = (String)createAttributeValue(CommonPackage.eINSTANCE.getQName_LocalPart(), eObject);
		((QName)eObject).setValues(prefix, "http://www.ibm.com", localPart);
    }

    protected Object createAttributeValue(EAttribute att, EObject eObject) {
        if (att.getEType() == JavaRefPackage.eINSTANCE.getJavaClass()) {
            return EMFAttributeFeatureGenerator.createJavaClassProxy(att,eObject).getClass();
        } else if (att == CommonPackage.eINSTANCE.getResourceRef_Auth())
			return createResAuth(att, eObject);
		else if (att == EjbPackage.eINSTANCE.getMethodElement_Type())
			return createMethodElementType(att, eObject);
        else
            return primCreateAttributeValue(att, eObject);
        //return EMFAttributeFeatureGenerator.createAttribute(att);
    }
    
    protected Object primCreateAttributeValue(EAttribute att, EObject eObject) {
		return EMFAttributeFeatureGenerator.createAttribute(att, eObject, true, version, moduleType);
    }

    // Old Compare method....now using DOMComparator...remove once stable
    public void compareContents(String file1, String file2) throws Exception {
        try {
            int lineno = 1;
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(getFileInputStream(file1)));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(getFileInputStream(file2)));
            String line1 = "", line2 = "";
            while (reader1.ready() && reader2.ready()) {
                line1 = reader1.readLine();
                line2 = reader2.readLine();
                if (line1 == null && line2 == null && lineno != 0)
                    return;
                if (!line1.equals(line2)) {
                    String message = "Error at line #: " + lineno + '\n' + line1 + '\n' + line2 + '\n';
                    assertTrue(message, false);
                    break; // added to escape compare 
                }
                lineno++;
            }
            assertTrue("The files have a different number of lines:" + lineno + '\n' + line1 + '\n' + line2 + '\n', (!reader1.ready() && !reader2.ready()));
        } catch (IOException ex) {
            assertTrue("IO failed", false);
        }
    }
    public void compareContentsIgnoreWhitespace(String file1, String file2, String identifier) throws Exception {

        LineNumberReader reader1 = new LineNumberReader(new InputStreamReader(getFileInputStream(file1)));
        LineNumberReader reader2 = new LineNumberReader(new InputStreamReader(getFileInputStream(file2)));
        compareContentsIgnoreWhitespace(reader1, reader2, identifier);

    }

    public void compareContentsIgnoreWhitespace(LineNumberReader reader1, LineNumberReader reader2, String identifier) throws Exception {
		List errors = new ArrayList();
		String msg = null;
        while (reader1.ready() || reader2.ready()) {
            String line1 = "", line2 = "";
            while (reader1.ready() && line1 != null && line1.equals("")) {
                line1 = reader1.readLine();
                if (line1 != null)
                	line1 = line1.trim();
            }
            while (reader2.ready() && line2 != null && line2.equals("")) {
                line2 = reader2.readLine();
                if (line2 != null)
                	line2 = line2.trim();
            }
            if (line1 != null && line2 != null && !isEquivalentLines(line1, line2)) {
                StringBuffer buff = new StringBuffer();
                buff.append("------------------------------ ");
                buff.append("\nSource line #: ");
                buff.append(reader1.getLineNumber());
                buff.append('\n');
                buff.append(line1);
                buff.append("\nDestination line #: ");
                buff.append(reader2.getLineNumber());
                buff.append('\n');
                buff.append(line2);
                System.out.println(buff.toString());
                errors.add(buff);
                break;
            }
        }
        if (!errors.isEmpty())
			assertTrue("Errors Found, Check Console.", false);
    }

    public boolean lineEquals(String line1, String line2) {
        return line1 != null && line1.equals(line2);
    }

    public boolean isEquivalentLines(String line1, String line2) {
        if (lineEquals(line1, line2))
            return true;

        String equiv = (String) getEquivalentLines().get(line1);
        if (equiv == null){
        	equiv = (String)getEquivalentLines().get(line2);
        	if (equiv != null)
        		return equiv.equals(line1);
        }
        return equiv != null && equiv.equals(line2);
    }
    public Map getEquivalentLines() {
        if (equivalentLines == null)
            equivalentLines = new HashMap();
        return equivalentLines;
    }

    public void setEquivalentLines(Map equivalentLines) {
        this.equivalentLines = equivalentLines;
    }
    /*
    	public void compareContents(String file1, String file2) throws Exception {
    		InputStream is1, is2;
    		is1 = getFileInputStream(file1);
    		is2 = getFileInputStream(file2);
    		InputSource input1 = new InputSource(is1);
    		InputSource input2 = new InputSource(is2);
    		try {
    			String results;
    			if(ignoreAtt == null)
    				results = DomComparitor.compareDoms(input1, input2);
    			else
    				results = DomComparitor.compareDoms(input1, input2, ignoreAtt);
    			if (results != null) {
    				assertTrue("Unequal doms compared as equal " + file1 + " " + file2 + "Details: " + results, false);
    			}
    		} finally {
    			try {
    				is1.close();
    			} catch (Exception e) {
    				Assert.fail(e.getMessage());
    			}
    			try {
    				is2.close();
    			} catch (Exception e) {
    				Assert.fail(e.getMessage());
    			}
    		}
    	}
    */
    public InputStream getFileInputStream(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }

    public InputStream getResourceAsStream(String fileName) {
        InputStream in = null;
        ClassLoader loader = getClass().getClassLoader();
        if (loader == null) {
            in = ClassLoader.getSystemResourceAsStream(fileName);
        } else {
            in = loader.getResourceAsStream(fileName);
        }
        assertTrue("Unable to find resource: " + fileName, in != null);
        return in;
    }

    public void setGeneralDepth(int depth) {
        GeneralEMFPopulationTest.genDepth = depth;
    }

    public void setModuleType(int type) {
        moduleType = type;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getDepthForAttribute(EStructuralFeature feature) {
        if (feature.isMany())
            return genDepth;
        else
            return 1;
    }

    public Object getSharedObjectByType(EObject owner, EReference ref) {
        sharedValue = TestUtilities.getObjectByType(owner.eResource(), ref.getEType(), ref.isMany());
        return sharedValue;
    }

    /**
     * @param set
     */
    public static void setIgnoreAtt(HashSet set) {
        ignoreAtt = set;
    }
    
	
	
	protected Object createResAuth(EAttribute att, EObject eObject) {
		Object auth = null;
		do {
			auth = primCreateAttributeValue(att, eObject);
		} while (!isValidAuth((ResAuthTypeBase)auth));
		return auth;
	}
	
	/**
	 * Web will need to override  for J2EE 1.2/1/3
	 * @param auth
	 * @return
	 */
	protected boolean isValidAuth(ResAuthTypeBase auth) {
		return auth == ResAuthTypeBase.APPLICATION_LITERAL || auth == ResAuthTypeBase.CONTAINER_LITERAL;
	}
	
	protected Object createMethodElementType(EAttribute att, EObject eObject) {
		Object type = null;
		do {
			type = primCreateAttributeValue(att, eObject);
		} while (!isValidMethodElementType((MethodElementKind)type));
		return type;
	}

	/**
	 * Web will need to override  for J2EE 1.2/1/3
	 * @param auth
	 * @return
	 */
	protected boolean isValidMethodElementType(MethodElementKind type) {
		if  (version == VERSION_1_4)
			return true;
		return version != VERSION_1_4 && type != MethodElementKind.SERVICE_ENDPOINT_LITERAL;
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		try {
			String out = AutomatedBVT.baseDirectory + "testOutput/";
			File del = new File(out);
			deleteDirectory(del);
			del.delete();
			out = AutomatedBVT.baseDirectory + "../testData/testOutput/";
			del = new File(out);
			deleteDirectory(del);
			del.delete();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		super.tearDown();
	}
	
	protected void deleteDirectory(File directory)
	   throws IOException
	{
		if (directory.exists()){
		   File[] delFiles = directory.listFiles();
	
		   for(int i=0; i<delFiles.length; ++i)
		   {
		      if(delFiles[i].isDirectory())
		         deleteDirectory(delFiles[i]);
		      delFiles[i].delete();
		   }  
		}
	}

}
