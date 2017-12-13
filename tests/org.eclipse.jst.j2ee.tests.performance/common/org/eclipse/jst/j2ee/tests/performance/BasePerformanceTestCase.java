/*
 * Created on Dec 6, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.tests.performance;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.wst.common.tests.BaseTestCase;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BasePerformanceTestCase extends BaseTestCase {
	protected static Map performanceClass = null;
	static ClassLoader clazzLoader = null;
	protected static String file= null; 
	public static Test suite() {
		performanceClass = null;
		return createTestSuites() ;
	}
	/**
	 * 
	 */
	private static Test createTestSuites() {
		createPerformanceClass();
		TestSuite suite = new TestSuite();
		for (Iterator iter = performanceClass.keySet().iterator(); iter.hasNext();) {
			Class clazz = (Class) iter.next();
			List methodList = (List)performanceClass.get(clazz);
			for (int x=0; x< methodList.size(); ++x) {
				PerformanceTestObject testObject = (PerformanceTestObject)methodList.get(x);
				Test test = createTest(clazz,testObject.getName());
				if (test instanceof BaseTestCase) {
					suite.addTest(new PerformanceTestCaseWrapper((BaseTestCase)test, testObject.getTagging() , testObject.getShortName()));
				}
			}
		}		
		return suite;
	}
	
	static public Test createTest(Class theClass, String name) {
		Constructor constructor;
		try {
			constructor= getTestConstructor(theClass);
		} catch (NoSuchMethodException e) {
			return warning("Class "+theClass.getName()+" has no public constructor TestCase(String name) or TestCase()");
		}
		Object test;
		try {
			if (constructor.getParameterTypes().length == 0) {
				test= constructor.newInstance(new Object[0]);
				if (test instanceof TestCase)
					((TestCase) test).setName(name);
			} else {
				test= constructor.newInstance(new Object[]{name});
			}
		} catch (InstantiationException e) {
			return(warning("Cannot instantiate test case: "+name+" ("+exceptionToString(e)+")"));
		} catch (InvocationTargetException e) {
			return(warning("Exception in constructor: "+name+" ("+exceptionToString(e.getTargetException())+")"));
		} catch (IllegalAccessException e) {
			return(warning("Cannot access test case: "+name+" ("+exceptionToString(e)+")"));
		}
		return (Test) test;
	}
	
	/**
	 * Returns a test which will fail and log a warning message.
	 */
	private static Test warning(final String message) {
		return new TestCase("warning") {
			protected void runTest() {
				fail(message);
			}
		};
	}
	
	public static Constructor getTestConstructor(Class theClass) throws NoSuchMethodException {
		Class[] args= { String.class };
		try {
			return theClass.getConstructor(args);	
		} catch (NoSuchMethodException e) {
			// fall through
		}
		return theClass.getConstructor(new Class[0]);
	}
	
	private static String exceptionToString(Throwable t) {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		t.printStackTrace(writer);
		return stringWriter.toString();

	}
	/**
	 * @param performanceClass2
	 */
	
	public static Map getPerformanceClass() {
		if (performanceClass == null) {
			createPerformanceClass();
		}
		return performanceClass;
	}
	
		
	protected static void createPerformanceClass() {
		if (performanceClass == null) {
			performanceClass = new TestCaseSAXParser().doLoad(new File(file));
		}
	}
	

	/**
	 * @return Returns the clazzLoader.
	 */
	public static ClassLoader getClassLoader() {
		return clazzLoader;
	}
	/**
	 * @param clazzLoader The clazzLoader to set.
	 */
	public static void setClassLoader(ClassLoader clazzLoader) {
		BasePerformanceTestCase.clazzLoader = clazzLoader;
	}
	/**
	 * @return Returns the file.
	 */
	public static String getFile() {
		return file;
	}
	/**
	 * @param file The file to set.
	 */
	public static void setFile(String file) {
		BasePerformanceTestCase.file = file;
	}
	
	/**
	 * @param suite
	 * @param test
	 * @param global
	 * @param string
	 */
	protected static void addPerformanceTest(TestSuite suite, BaseTestCase test, int tagging, String shortName) {
		PerformanceTestCaseWrapper testCase = new PerformanceTestCaseWrapper(test, tagging , shortName);
		suite.addTest(testCase);
	}
}
