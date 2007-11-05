package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.web.validation.UrlPattern;
import org.eclipse.wst.common.tests.BaseTestCase;

public class UrlPatternTest extends BaseTestCase {
	
	public static Test suite() {
        return new TestSuite(UrlPatternTest.class);
    }

	public void testIsValid() {
		// test an empty string
		assertFalse(UrlPattern.isValid(""));
		
		// test a string containing Carriage Return char
		assertFalse(UrlPattern.isValid("\r"));
		assertFalse(UrlPattern.isValid("/\r"));
		assertFalse(UrlPattern.isValid("/some\r"));
		assertFalse(UrlPattern.isValid("/some\rthing"));
		assertFalse(UrlPattern.isValid("*.something\r"));
		assertFalse(UrlPattern.isValid("*.some\rthing"));
		
		// test a string containing New Line char
		assertFalse(UrlPattern.isValid("\n"));
		assertFalse(UrlPattern.isValid("/\n"));
		assertFalse(UrlPattern.isValid("/some\n"));
		assertFalse(UrlPattern.isValid("/some\nthing"));
		assertFalse(UrlPattern.isValid("/some\r\nthing"));
		assertFalse(UrlPattern.isValid("/some\rthi\nng"));
		assertFalse(UrlPattern.isValid("*.something\n"));
		assertFalse(UrlPattern.isValid("*.some\nthing"));
		
		// test the path mappings
		assertTrue(UrlPattern.isValid("/"));
		assertTrue(UrlPattern.isValid("/*"));
		assertTrue(UrlPattern.isValid("/something"));
		assertTrue(UrlPattern.isValid("/something/"));
		assertTrue(UrlPattern.isValid("/something/*"));
		assertTrue(UrlPattern.isValid("/some/thing"));
		assertTrue(UrlPattern.isValid("/some/thing/*"));
		assertTrue(UrlPattern.isValid("/some/thing/else"));
		assertFalse(UrlPattern.isValid("/some/thing/*.else"));
		
		// test extension mappings
		assertTrue(UrlPattern.isValid("*."));
		assertTrue(UrlPattern.isValid("*.some"));
		assertTrue(UrlPattern.isValid("*.some*thing"));
		assertFalse(UrlPattern.isValid("*.some/thing"));
		
		// test other situations
		assertFalse(UrlPattern.isValid("something"));
		assertFalse(UrlPattern.isValid("some/thing"));
		assertFalse(UrlPattern.isValid(".something"));
		assertFalse(UrlPattern.isValid(".some/thing"));
	}
	
}
