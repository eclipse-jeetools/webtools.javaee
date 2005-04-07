package org.eclipse.jst.j2ee.flexible.project.apitests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.web.modulecore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;

/**
 * The WebArtifactEditTest is an API test class for the WebArtifactEdit class.
 * All the exposed API is tested through the scenarios in this junit.
 * 
 * @see org.eclipse.jst.j2ee.web.modulecore.util.WebArtifactEdit
 *
 * NOTE -- The test web module creation static helper needs to be plugged in here when it is
 * ready.
 */
public class WebArtifactEditTest extends TestCase {
	
	//TODO initialize the workbenchmodule using web module creation test case
	WorkbenchComponent aModule = null;//AbstractProjectCreationTest.setUpWebModule(PROJECT_NAME, J2EE_VERSION);
	
	public static final int J2EE_VERSION = J2EEVersionConstants.J2EE_1_4_ID;
	public static final String PROJECT_NAME = "TestWeb"; //$NON-NLS-1$
	
	public void test_getWebArtifactEditForRead() {
		WebArtifactEdit retValue = null;
		try {
			retValue = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			Assert.assertNotNull(retValue);
		} finally {
			if (retValue != null)
				retValue.dispose();
		}
	}

	public void test_getWebArtifactEditForWrite() {
		WebArtifactEdit retValue = null;
		try {
			retValue = WebArtifactEdit.getWebArtifactEditForWrite(aModule);
			Assert.assertNotNull(retValue);
		} finally {
			if (retValue != null)
				retValue.dispose();
		}
	}

	public void test_isValidWebModule() throws UnresolveableURIException {
		boolean retValue = WebArtifactEdit.isValidWebModule(aModule);
		Assert.assertEquals(true,retValue);
	}

	public void test_getJ2EEVersion() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			int retValue = objWebArtifactEdit.getJ2EEVersion();
			Assert.assertEquals(J2EEVersionConstants.SERVLET_2_4,retValue);
		} finally {
			if (objWebArtifactEdit!=null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_getDeploymentDescriptorRoot() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			EObject retValue = objWebArtifactEdit.getDeploymentDescriptorRoot();
			Assert.assertNotNull(retValue);
		} finally {
			if (objWebArtifactEdit != null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_getDeploymentDescriptorResource() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			Resource retValue = objWebArtifactEdit.getDeploymentDescriptorResource();
			Assert.assertNotNull(retValue);
		} finally {
			if (objWebArtifactEdit!= null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_getServletVersion() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			int retValue = objWebArtifactEdit.getServletVersion();
			Assert.assertEquals(J2EEVersionConstants.SERVLET_2_4,retValue);
		} finally {
			if (objWebArtifactEdit!= null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_getJSPVersion() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			int retValue = objWebArtifactEdit.getJSPVersion();
			Assert.assertEquals(J2EEVersionConstants.JSP_2_0_ID,retValue);
		} finally {
			if (objWebArtifactEdit!=null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_getDeploymentDescriptorPath() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			IPath retValue = objWebArtifactEdit.getDeploymentDescriptorPath();
			Assert.assertNotNull(retValue);
		} finally {
			if (objWebArtifactEdit!=null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_createModelRoot() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			EObject retValue = objWebArtifactEdit.createModelRoot();
			Assert.assertNotNull(retValue);
		} finally {
			if (objWebArtifactEdit!=null) {
				objWebArtifactEdit.dispose();
			}
		}
	}

	public void test_getLibModules() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			ReferencedComponent[] retValue = objWebArtifactEdit.getLibModules();
			Assert.assertNotNull(retValue);
		} finally {
			if (objWebArtifactEdit!=null) {
				objWebArtifactEdit.dispose();
			}
		}
	}

	public void test_addLibModules() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForWrite(aModule);
			ReferencedComponent[] libModules = new ReferencedComponent[0];
			objWebArtifactEdit.addLibModules(libModules);
			Assert.assertNotNull(objWebArtifactEdit.getLibModules());
		} finally {
			if (objWebArtifactEdit!=null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_getServerContextRoot() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			String retValue = objWebArtifactEdit.getServerContextRoot();
			Assert.assertNotNull(retValue);
		} finally {
			if (objWebArtifactEdit!=null)
				objWebArtifactEdit.dispose();
		}
	}

	public void test_setServerContextRoot() {
		WebArtifactEdit objWebArtifactEdit = null;
		try {
			objWebArtifactEdit = WebArtifactEdit.getWebArtifactEditForWrite(aModule);
			String contextRoot = PROJECT_NAME;
			objWebArtifactEdit.setServerContextRoot(contextRoot);
			Assert.assertEquals(PROJECT_NAME,objWebArtifactEdit.getServerContextRoot());
		} finally {
			if (objWebArtifactEdit!=null)
				objWebArtifactEdit.dispose();
		}
	}
}
