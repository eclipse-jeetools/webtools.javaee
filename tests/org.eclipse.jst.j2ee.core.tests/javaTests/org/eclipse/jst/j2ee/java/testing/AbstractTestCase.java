package org.eclipse.jst.j2ee.java.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.impl.JavaRefFactoryImpl;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.core.tests.bvt.AutomatedBVT;



public abstract class AbstractTestCase extends junit.framework.TestCase {
	protected ResourceSet resourceSet;
	protected Map equivalentLines;
	// If we are running in the workbench, we get our context from
	// the project named below.
	protected static Object project = null;  //really an IJavaProject, but we don't want the workbench referenced here
public AbstractTestCase(String name) {
	super(name);
}

public void compareContents(String file1, String file2) {
	compareContents(getResourceAsStream(file1), getResourceAsStream(file2));
}

public void compareContents(String file1, InputStream in2) {
	compareContents(getResourceAsStream(file1), in2);
}

public void compareContents(InputStream in1, InputStream in2) {
	try {
		int lineno = 1;
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1));
		BufferedReader reader2 = new BufferedReader(new InputStreamReader(in2));
		String line1 = "", line2 = "";
		while (reader1.ready() && reader2.ready()) {
			line1 = reader1.readLine();
			line2 = reader2.readLine();
			if (!line1.equals(line2)) {
				String message = "Error at line #: " + lineno + '\n' + line1 + '\n' + line2 + '\n';
				assertTrue(message, false);
			}
			lineno++;
		}
		assertTrue("The files have a different number of lines:" + lineno + '\n' 
			+ line1 + '\n' + line2 + '\n', (!reader1.ready() && !reader2.ready())); 
	} catch (IOException ex) {
		assertTrue("IO failed", false);
	}
}

//public void compareDOMConentsIgnoringIDs(String file1, Resource resource) throws Exception {
//	InputStream in = resource.getResourceSet().getURIConverter().createInputStream(resource.getURI());
//	HashSet ignore = new HashSet();
//	ignore.add("id");
//	try {
//		compareDOMContents(file1, in, ignore);
//	} finally {
//		in.close();
//	}
//}
//public void compareDOMContents(String file1, InputStream in2, HashSet ignorableAttributes) throws Exception {
//	InputStream in1 = getResourceAsStream(file1);
//	try {
//		compareDOMContents(in1, in2, ignorableAttributes);
//	} finally {
//		try {
//			if (in1 != null)
//				in1.close();
//		} catch (Exception e) {
//			Assert.fail(e.getMessage());
//		}
//	}
//}

//public void compareDOMContents(InputStream in1, InputStream in2, HashSet ignorableAttributes) throws Exception {
//		InputSource input1 = new InputSource(in1);
//		InputSource input2 = new InputSource(in2);
//		try {
//			String results = DomComparitor.compareDoms(input1, input2, ignorableAttributes);
//			if (results != null) {
//				assertTrue("Unequal doms compared as equal. Details: " + results, false);
//			}
//		} catch(Exception ex){
//			assertTrue("Compare failed" + ex.getMessage(), false);
//		}
//	}
public void compareContentsIgnoreWhitespace(InputStream source, InputStream dest, String identifier) throws Exception {

	
	LineNumberReader reader1 = new LineNumberReader(new InputStreamReader(source));
	LineNumberReader reader2 = new LineNumberReader(new InputStreamReader(dest));
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
/**
 * Insert the method's description here.
 * Creation date: (10/17/2000 3:45:25 PM)
 * @return com.ibm.etools.emf.resource.Context
 */
protected ResourceSet computeBasicContext() {
	ResourceSet rs = JavaRefFactoryImpl.createJavaContext();
	return rs;
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2000 3:45:25 PM)
 * @return com.ibm.etools.emf.resource.Context
 */
protected ResourceSet computeProjectContext() {
	ResourceSet rs = null;
	try {
		// ejbNatureRuntime = EjbNatureRuntime.getRuntime(getProject())
		Class natureClass = Class.forName("com.ibm.etools.ejb.ejbproject.EJBNatureRuntime");
		Class projectClass = Class.forName("org.eclipse.core.resources.IProject");
		Class[] parmTypes = {projectClass};
		java.lang.reflect.Method getRuntimeMethod = natureClass.getMethod("getRuntime", parmTypes);
		Object[] args = new Object[1];
		args[0] = getProject();
		Object ejbNatureRuntime = getRuntimeMethod.invoke((Object) null, args);
		if (ejbNatureRuntime == null)
			throw new RuntimeException("Tests must be run in an EJB project");
		// newContext = ejbNatureRuntime.createContext()
		
		java.lang.reflect.Method createContextMethod = natureClass.getMethod("getResourceSet", new Class[0]);
		rs = (ResourceSet) createContextMethod.invoke(ejbNatureRuntime, null);

	} catch (Exception e) {
		// nothing to do
		System.out.println("Reflection error computing project context: " + e);
	}
	return rs;
}
/**
 * Insert the method's description here.
 * Creation date: (7/29/99 8:39:08 AM)
 */
protected void deleteFile(String aFileName) {
	String aString;
	aString = (aFileName.charAt(0) == '/') ? aFileName.substring(1) : aFileName;
	File aFile = new File(aString);
	if (aFile != null)
		aFile.delete();
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2000 3:45:25 PM)
 * @return com.ibm.etools.emf.resource.Context
 */
protected ResourceSet getContext() {
	if (resourceSet == null) {
		if (getProject() == null)
			resourceSet = computeBasicContext();
		else
			resourceSet = computeProjectContext();
	}
	return resourceSet;
}

protected void addEquivalentLines(String sourceLine, String destinationLine) {
	getEquivalentLines().put(sourceLine, destinationLine);
}
/*
 * Get the static project for testing
 */
public static Object getProject() {
	return project;
}
public InputStream getResourceAsStream(String fileName) {

	InputStream in = null;
	ClassLoader loader = getClass().getClassLoader();
	if (loader == null) {
		in = ClassLoader.getSystemResourceAsStream(fileName);
	} else {
		in = loader.getResourceAsStream(fileName);
	}
	assertTrue("Unable to find resource: "+fileName, in != null);
	return in;
}

public boolean lineEquals(String line1, String line2) {
	return line1.equals(line2);
}

public boolean isEquivalentLines(String line1, String line2) {
	if (lineEquals(line1, line2))
		return true;
		
	String equiv = (String)getEquivalentLines().get(line1);
	return equiv != null && equiv.equals(line2);
} 
/**
 * Insert the method's description here.
 * Creation date: (10/16/2000 11:42:36 AM)
 * @return com.ibm.etools.emf.resource.ResourceSet
 */
public ResourceSet getResourceSet() {
	return getContext();
}
protected OutputStream makeOutputStream(String uri) throws IOException {
	File aFile = new File(uri);
	aFile.getParentFile().mkdirs();
	return new FileOutputStream(aFile);
}
protected Resource makeResource(String uri) {
	return makeResource(uri, null);
}

protected Resource makeAbsoluteResource(String relativeUri) {
	StringBuffer b = new StringBuffer();
	b.append("file://").append(AutomatedBVT.baseDirectory).append(relativeUri);
	return makeResource(b.toString());
}
protected Resource makeResource(String uriString, Object extent) {
	URI uri = URI.createURI(uriString);

	Resource.Factory fac = J2EEResourceFactoryRegistry.INSTANCE.getFactory(uri);
	Resource resource;

	resource = fac.createResource(uri);
	ResourceSet rs = getResourceSet();
	rs.getResources().add(resource);
	return resource;	
}
/**
 * Insert the method's description here.
 * Creation date: (10/17/2000 3:45:25 PM)
 * @param newContext com.ibm.etools.emf.resource.Context
 */
protected void setResourceSet(ResourceSet newRS) {
	resourceSet = newRS;
}
/*
 * Set the static project name for testing
 */
public static void setProject(Object inProject) {
	project = inProject;
}
	/**
	 * Gets the equivalentLines.
	 * @return Returns a Map
	 */
	public Map getEquivalentLines() {
		if (equivalentLines == null)
			equivalentLines = new HashMap();
		return equivalentLines;
	}

	/**
	 * Sets the equivalentLines.
	 * @param equivalentLines The equivalentLines to set
	 */
	public void setEquivalentLines(Map equivalentLines) {
		this.equivalentLines = equivalentLines;
	}

}
