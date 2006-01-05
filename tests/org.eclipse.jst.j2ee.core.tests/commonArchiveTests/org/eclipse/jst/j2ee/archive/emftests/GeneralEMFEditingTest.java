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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jem.java.JavaRefPackage;
import org.eclipse.jem.java.internal.impl.JavaClassImpl;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.archive.testutilities.J2EEVersionCheck;
import org.eclipse.jst.j2ee.archive.testutilities.TestUtilities;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;
import org.eclipse.wst.common.tests.BaseTestCase;


public class GeneralEMFEditingTest extends BaseTestCase {
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
	public int classIndex = 0;
	public static String avClass[];
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

	public GeneralEMFEditingTest(String name) {
		super(name);
	}

	public void editRoot(EObject eObject) {
		editFeatures(eObject);
		//if (deferedReferences != null) {
		//	for (int i = 0; i < deferedReferences.size(); i++) {
		//		((DeferredSharedReferenceAction) deferedReferences.get(i)).performAction();
		//	}
		//}
	}

	public void editFeatures(EObject eObject) {
		if (eObject == null)
			return;
		else {
			editAttributes(eObject);
			editReferences(eObject);
		}
	}

	protected void editReferences(EObject eObject) {
		List references = eObject.eClass().getEAllReferences();
		for (int i = 0; i < references.size(); i++) {
			EReference ref = (EReference) references.get(i);
			if (!ref.isMany() && eObject.eGet(ref) != null)
				continue;
			if (ref.isContainment())
				populateContainmentReference(eObject, ref);
			else
				populateSharedReference(eObject, ref);
		}
	}

	protected void populateSharedReference(EObject eObject, EReference ref) {
		if (ref.getEType() == JavaRefPackage.eINSTANCE.getJavaClass())
			setReferenceValue(eObject, ref, createJavaClassProxy((EClass) ref.getEType()));
		else {
			EPackage pkg = ref.getEType().getEPackage();
			if (pkg == eObject.eClass().getEPackage() || pkg == CommonPackage.eINSTANCE) {
				if (eObject.eClass().getName().equals("EAnnotation") || eObject.eClass().getName().equals("EAnnotationImpl") || !J2EEVersionCheck.checkReferenceVersion(ref, version, moduleType))
					return;
				if (deferedReferences == null)
					deferedReferences = new ArrayList();
				deferedReferences.add(new DeferredSharedReferenceAction(eObject, ref));
			}
		}
	}

	protected void populateContainmentReference(EObject eObject, EReference ref) {
		for (int i = 0; i < getDepthForAttribute(ref); i++) {
			EObject instance = createInstance((EClass) ref.getEType());
			if (instance == null)
				return;
			if (!J2EEVersionCheck.checkReferenceVersion(ref, version, moduleType))
				continue;
			setReferenceValue(eObject, ref, instance);
			if (((InternalEObject) instance).eIsProxy())
				return;
			if (ref.getEType() == eObject.eClass())
				editAttributes(instance);
			else
				editFeatures(instance);
		}
	}

	protected void setReferenceValue(EObject eObject, EReference ref, Object value) {
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

	public EObject createInstance(EClass eClassifier) {
		if (eClassifier == JavaRefPackage.eINSTANCE.getJavaClass())
			return createJavaClassProxy(eClassifier);
		return eClassifier.getEPackage().getEFactoryInstance().create(eClassifier);
	}

	protected void editAttributes(EObject eObject) {
		List attributes = eObject.eClass().getEAllAttributes();
		for (int i = 0; i < attributes.size(); i++) {
			EAttribute att = (EAttribute) attributes.get(i);
			if (att.isChangeable() && J2EEVersionCheck.checkAttributeVersion(att, version, moduleType)) {
				Object value = createAttributeValue(att, eObject);
				eObject.eSet(att, value);
			}
		}
	}

	protected Object createAttributeValue(EAttribute att, EObject eObject) {
		if (att.getEType() == JavaRefPackage.eINSTANCE.getJavaClass()) {
			return createJavaClassProxy(att.eClass()).getClass();
		} 
		// if (moduleType == WEB)
			return EMFAttributeFeatureGenerator.createAttribute(att, eObject, true, version, moduleType);
		//return EMFAttributeFeatureGenerator.createAttribute(att);
	}

	protected EObject createJavaClassProxy(EClass metaClass) {
		if (avClass == null) {
			avClass = new String[] { "java.util.HashTable", "java.util.List", "java.sql.Data", "java.lang.Integer", "java.lang.String" };
		}
		String name = avClass[classIndex];
		classIndex++;
		if (classIndex == 5)
			classIndex = 0;
		return JavaClassImpl.createClassRef(name);
	}

	// Old Compare method....now using DOMComparator...remove once stable
	public void compareContents(String file1, String file2) throws Exception {
	    BufferedReader reader1 = null;
	    BufferedReader reader2 = null;
		try {
			int lineno = 1;
			reader1 = new BufferedReader(new InputStreamReader(getFileInputStream(file1)));
			reader2 = new BufferedReader(new InputStreamReader(getFileInputStream(file2)));
			String line1 = "", line2 = "";
			while (reader1.ready() && reader2.ready()) {
				line1 = readLineTrimComments(reader1); 
				line2 = readLineTrimComments(reader2);
				if (line1 == null && line2 == null && lineno != 0)
					return;
							    
				if (!line1.trim().equals(line2.trim())) {
					String message = "Error at line #: " + lineno + '\n' + line1 + '\n' + line2 + '\n';
					System.out.println(message);
					//assertTrue(message, false);
				}
				lineno++;
			}
			assertTrue("The files have a different number of lines:" + lineno + '\n' + line1 + '\n' + line2 + '\n', (!reader1.ready() && !reader2.ready()));
		} catch (IOException ex) {
		    ex.printStackTrace();
			assertTrue("IO failed", false);
		} finally {
		    if(reader1 != null)
		        reader1.close();
		    if(reader2 != null)
		        reader2.close();
		}
	} 

    /**
     * @param string
     * @return
     */
    private String readLineTrimComments(BufferedReader reader) throws IOException {
        String result = reader.readLine(); 
        if(result.indexOf("<!--") < 0)
            return result;
        else {
            int endCommentIndx = 0;
            while(reader.ready()) {
                if( (endCommentIndx = result.indexOf("-->")) >= 0) 
                    result = reader.readLine();
                else 
                    return result;
            }
        }
        return result;
    }

    public void compareContentsIgnoreWhitespace(String file1, String file2, String identifier) throws Exception {

		LineNumberReader reader1 = new LineNumberReader(new InputStreamReader(getFileInputStream(file1)));
		LineNumberReader reader2 = new LineNumberReader(new InputStreamReader(getFileInputStream(file2)));
		compareContentsIgnoreWhitespace(reader1, reader2, identifier);

	}

	public void compareContentsIgnoreWhitespace(LineNumberReader reader1, LineNumberReader reader2, String identifier) throws Exception {

		while (reader1.ready() || reader2.ready()) {
			String line1 = "", line2 = "";
			while (reader1.ready() && line1.equals("")) {
				line1 = reader1.readLine().trim();
			}
			while (reader2.ready() && line2.equals("")) {
				line2 = reader2.readLine().trim();
			}
			if (!isEquivalentLines(line1, line2)) {
				StringBuffer buff = new StringBuffer();
				buff.append("Difference found in test ");

				buff.append(identifier);
				buff.append("\nSource line #: ");
				buff.append(reader1.getLineNumber());
				buff.append('\n');
				buff.append(line1);
				buff.append("\nDestination line #: ");
				buff.append(reader2.getLineNumber());
				buff.append('\n');
				buff.append(line2);
				assertTrue(buff.toString(), false);
			}
		}

	}

	public boolean lineEquals(String line1, String line2) {
		return line1.equals(line2);
	}

	public boolean isEquivalentLines(String line1, String line2) {
		if (lineEquals(line1, line2))
			return true;
		if (equalTags(line1, line2))
			return true;
		String equiv = (String) getEquivalentLines().get(line1);
		return equiv != null && equiv.equals(line2);
	}
	
	public boolean equalTags(String line1, String line2){
		//data check, there should be no data for this test to return true
		int shortEndIndex1 = line1.indexOf("/>");
		int shortEndIndex2 = line2.indexOf("/>");
		if (shortEndIndex1 == -1 && shortEndIndex2 == -1)
			return false;
		else if (shortEndIndex1 != -1){
			String tagName1 = line1.substring(1,shortEndIndex1);
			String tagName2 = extractTagName(line2);
			if (checkNoData(line2) && tagName1.equals(tagName2)){
				return true;
			}
		}
		else if (shortEndIndex2 != -1){
			String tagName1 = extractTagName(line1); 
			String tagName2 = line2.substring(1,shortEndIndex2);
			if (checkNoData(line1) && tagName1.equals(tagName2)){
				return true;
			}
		}
		return false;
	}
	/**
	 * @param line2
	 * @return
	 */
	private String extractTagName(String line) {
		int endOpenTag = line.indexOf(">");
		return line.substring(1,endOpenTag);
	}

	/**
	 * @param line2
	 * @return
	 */
	private boolean checkNoData(String line) {
		int endOpenTag = line.indexOf(">");
		int startEndTag = line.lastIndexOf("<");
		if (endOpenTag == line.length())
			return true;
		else if (endOpenTag+1 == startEndTag)
			return true;
		return false;
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

	public int getDepthForAttribute(EReference ref) {
		if (ref.isMany())
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
