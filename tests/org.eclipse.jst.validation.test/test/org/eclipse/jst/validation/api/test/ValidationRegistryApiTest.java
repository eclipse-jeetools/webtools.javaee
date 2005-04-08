package org.eclipse.jst.validation.api.test;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.wst.validation.internal.provisional.ValidationFactory;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

public class ValidationRegistryApiTest extends TestCase {
	
	public static Test suite() {
		return new TestSuite(ValidationRegistryApiTest.class);
	} 

	public ValidationRegistryApiTest() {
		super();
	}

	public ValidationRegistryApiTest(String name) {
		super(name);
	}
	
	public void test_getValidator() {
	try {
		ValidationFactory valFactory = ValidationFactory.instance;
		IValidator validator = valFactory.getValidator("org.eclipse.jst.j2ee.model.internal.validation.EJBValidator");
		Assert.assertNotNull(validator);
	 } catch (Exception e) {
		e.printStackTrace();
	  }
	}

}
