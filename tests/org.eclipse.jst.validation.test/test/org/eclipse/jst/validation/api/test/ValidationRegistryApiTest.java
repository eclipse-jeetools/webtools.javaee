package org.eclipse.jst.validation.api.test;

import org.eclipse.wst.validation.core.IValidationRegistry;
import org.eclipse.wst.validation.core.IValidator;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
		IValidationRegistry valReg = IValidationRegistry.instance;
		IValidator validator = valReg.getValidator("org.eclipse.jst.j2ee.model.internal.validation.EJBValidator");
		Assert.assertNotNull(validator);
	 } catch (Exception e) {
		e.printStackTrace();
	 }
	}

}
