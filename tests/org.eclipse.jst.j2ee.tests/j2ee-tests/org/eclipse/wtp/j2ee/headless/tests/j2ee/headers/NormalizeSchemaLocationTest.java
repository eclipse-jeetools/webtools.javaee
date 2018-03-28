package org.eclipse.wtp.j2ee.headless.tests.j2ee.headers;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NormalizeSchemaLocationTest extends TestCase {

	public static TestSuite suite() {
        TestSuite suite = new TestSuite();
        ArrayList<String[]> data = createData();
        Iterator<String[]> it = data.iterator();
        String[] s = null;
        while(it.hasNext()) {
        	s = it.next();
            suite.addTest(new NormalizeSchemaLocationTest(s[0], s[1]));
        }
        return suite;
    }
	
	private static ArrayList<String[]> createData() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		list.add(new String[] {"", ""});
		list.add(new String[] {"", " "});
		list.add(new String[] {"", "  "});
		list.add(new String[] {"", "   "});
		list.add(new String[] {"", "\n"});
		list.add(new String[] {"", "\n\n"});
		list.add(new String[] {"", "\n\n\n"});
		list.add(new String[] {"", "\n "});
		list.add(new String[] {"simple", "simple"});
		list.add(new String[] {"simple", "simple "});
		list.add(new String[] {"simple", "simple  "});
		list.add(new String[] {"simple", "simple   "});
		list.add(new String[] {"simple", " simple"});
		list.add(new String[] {"simple", "   simple   "});
		list.add(new String[] {"simple", "simple\n"});
		list.add(new String[] {"simple", "\nsimple\n"});
		list.add(new String[] {"simple", "simple\r"});
		list.add(new String[] {"simple", "simple\t"});
		list.add(new String[] {"simple", "simple\n\n"});
		list.add(new String[] {"simple", "simple\n\r"});
		list.add(new String[] {"simple", "simple\n\t"});
		list.add(new String[] {"simple", "simple \n"});
		list.add(new String[] {"simple", "simple\r "});
		list.add(new String[] {"simple", " \t\tsimple\t"});
		list.add(new String[] {"simple", "simple\n \n"});
		list.add(new String[] {"simple", "\r \rsimple \n\r"});
		list.add(new String[] {"simple", " simple\n\t"});
		
		list.add(new String[] {"simple simple", "simple simple"});
		list.add(new String[] {"simple simple", "simple  simple"});
		list.add(new String[] {"simple simple", "simple\nsimple"});
		list.add(new String[] {"simple simple", "simple\rsimple"});
		list.add(new String[] {"simple simple", "simple\tsimple"});
		list.add(new String[] {"simple simple", "simple  \nsimple"});
		list.add(new String[] {"simple simple", "simple\r  simple"});
		list.add(new String[] {"simple simple", "simple          \t        simple"});
		list.add(new String[] {"simple simple", "simple simple "});
		list.add(new String[] {"simple simple", " simple  simple"});
		list.add(new String[] {"simple simple", "   simple      simple   "});
		list.add(new String[] {"simple simple", "simple\n simple\n"});
		list.add(new String[] {"simple simple", "simple\r simple\r"});
		list.add(new String[] {"simple simple", "simple\t simple\t"});
		list.add(new String[] {"simple simple", "simple\n\nsimple"});
		list.add(new String[] {"simple simple", "simple\n\rsimple\n\n"});
		list.add(new String[] {"simple simple", "simple\n\tsimple\n\t"});
		list.add(new String[] {"simple simple", "simple \nsimple \n"});
		list.add(new String[] {"simple simple", "simple\r simple\r "});
		list.add(new String[] {"simple simple", " \t\tsimple\t \t\tsimple\t"});
		list.add(new String[] {"simple simple", "simple\n \nsimple\n \n"});
		list.add(new String[] {"simple simple", "\r \rsimple \n\r\r \rsimple \n\r"});
		list.add(new String[] {"simple simple", " simple\n\t simple\n\t"});
		return list;
	}
	
	
	private String normalizedSchemaLocation;
	private String someSchemaLocation;
	public NormalizeSchemaLocationTest(String normalizedSchemaLocation, String someSchemaLocation) {
		super("testNormalizeSchemaLocation");
		this.normalizedSchemaLocation = normalizedSchemaLocation;
		this.someSchemaLocation = someSchemaLocation;
	}
	
	@Test 
	public void testNormalizeSchemaLocation() throws Exception {
		verifyNormalizeSchemaLocation(normalizedSchemaLocation, someSchemaLocation);
	}

	private String verifyNormalizeSchemaLocation(String normalizedSchemaLocation, String someSchemaLocation) {
		String normalizedString = JavaEEQuickPeek.normalizeSchemaLocation(someSchemaLocation);
		if(!normalizedSchemaLocation.equals(normalizedString)){
			Assert.assertEquals(normalizedSchemaLocation, normalizedString);	
		}
		return normalizedString;
	} 
	
	
}
