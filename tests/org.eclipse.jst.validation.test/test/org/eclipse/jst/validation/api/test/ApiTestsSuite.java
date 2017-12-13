package org.eclipse.jst.validation.api.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ApiTestsSuite extends TestSuite {
	
	 public static Test suite(){
	        return new ApiTestsSuite();
	    }

	public ApiTestsSuite() {
		super();
		addTest(ValidationRegistryApiTest.suite());
	}

}
