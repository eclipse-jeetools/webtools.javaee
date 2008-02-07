package org.eclipse.wst.validation.tests.testcase;

import java.io.UnsupportedEncodingException;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.wst.validation.IDependencyIndex;
import org.eclipse.wst.validation.ValidationFramework;
import org.eclipse.wst.validation.ValidationResults;
import org.eclipse.wst.validation.Validator;
import org.eclipse.wst.validation.internal.ValConstants;
import org.eclipse.wst.validation.internal.ValManager;
import org.eclipse.wst.validation.internal.ValPrefManagerGlobal;
import org.eclipse.wst.validation.tests.TestValidator;
import org.eclipse.wst.validation.tests.util.TestEnvironment;

public class Framework extends TestCase {
	
	private static TestEnvironment _env;
	private static IProject		_testProject;
	
	public static Test suite() {
		TestSuite suite = new TestSuite(Framework.class);
		TestSetup wrapper = new TestSetup(suite){
			@Override
			protected void setUp() throws Exception {
				oneTimeSetUp();
			}
			
			@Override
			protected void tearDown() throws Exception {
				oneTimeTearDown();
			}
		};
		return wrapper;
	} 
	
	public Framework(String name){
		super(name);
	}
	
	private static void oneTimeSetUp() throws Exception {
		if (_env != null)return;
		_env = new TestEnvironment();
		turnoffOtherValidators();
		_testProject = _env.createProject("TestProject");
		IPath folder = _env.addFolder(_testProject.getFullPath(), "source");
		_env.addFile(folder, "first.test1", "include map.test1\ninfo - information\nwarning - warning\nerror - error\n\n" +
		"t1error - extra error\nt1warning - extra warning");
		_env.addFile(folder, "second.test1", "info - information\nwarning - warning\nerror - error\n\n" +
			"t1error - extra error\nt1warning - extra warning");
		_env.addFile(folder, "map.test1", "# will hold future mappings");
		_env.addFile(folder, "first.test2", "# sample file");
	}

	/**
	 * Since other plug-ins can add and remove validators, turn off all the ones that are not part of
	 * these tests.
	 */
	private static void turnoffOtherValidators() {
		Validator[] vals = ValManager.getDefault().getValidators();
		for (Validator v : vals){
			if (!v.getValidatorClassname().startsWith("org.eclipse.wst.validation.tests")){
				v.setBuildValidation(false);
				v.setManualValidation(false);
			}
		}
		ValPrefManagerGlobal gp = new ValPrefManagerGlobal();
		gp.saveAsPrefs(vals);		
	}

	private static void oneTimeTearDown() throws Exception {
		_env.dispose();
	}
	
	public void testIndex(){
		ValidationFramework vf = ValidationFramework.getDefault();
		IDependencyIndex index = vf.getDependencyIndex();
		assertNotNull(index);
	}
	
	public void testIndex2() throws CoreException{
		ValidationFramework vf = ValidationFramework.getDefault();
		IDependencyIndex index = vf.getDependencyIndex();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		IResource r = root.findMember("TestProject/source/map.test1");
		IProject p = r.getProject();
		p.build(IncrementalProjectBuilder.FULL_BUILD, new NullProgressMonitor());
		_env.waitForBuild();

		r = root.findMember("TestProject/source/first.test1");
		assertFalse(index.isDependedOn(r));		
	}
	
	public void testGetValidators(){
		ValidationFramework vf = ValidationFramework.getDefault();
		IResource resource = _env.getWorkspace().getRoot().findMember("TestProject/source/first.test1");
		Validator[] validators = vf.getValidatorsFor(resource, false, false);
		assertTrue(validators.length > 0);
		
		String id = TestValidator.id();
		int count = 0;
		for (int i =0; i<validators.length; i++){
			if (validators[i].getId().equals(id))count++;
		}
		assertEquals(1, count);
	}
	
	public void testTest1() throws CoreException, UnsupportedEncodingException {
		ValidationFramework vf = ValidationFramework.getDefault();
		IProject[] projects = {_testProject};
		ValidationResults vr = vf.validate(projects, true, false, new NullProgressMonitor());
		
		IResource resource = _env.getWorkspace().getRoot().findMember("TestProject/source/first.test1");
		checkFirstPass(resource, vr);
		
		// add a first build so that we know that only the map file has changed
		_env.incrementalBuild();
		_env.waitForBuild();
		
		IPath folder = _env.addFolder(_testProject.getFullPath(), "source");
		_env.addFile(folder, "map.test1", "# will hold future mappings");
		
		_env.incrementalBuild();
		_env.waitForBuild();
		
		checkSecondPass(resource);		
	}
	
	public void testTest2() {
		ValidationFramework vf = ValidationFramework.getDefault();
		IResource test2 = _testProject.findMember("source/first.test2");
		assertNotNull(test2);
		Validator[] vals = vf.getValidatorsFor(test2, true, true);
		for (Validator v : vals){
			String id = v.getId();
			if (id.equals(TestValidator.id()))fail("first.test2 should not be validated by the test1 validator");
		}
		
		IResource test1 = _testProject.findMember("source/first.test1");
		assertNotNull(test1);
		vals = vf.getValidatorsFor(test1, true, true);
		boolean found = false;
		for (Validator v : vals){
			String id = v.getId();
			if (id.equals(TestValidator.id()))found = true;
		}
		assertTrue(found);
	}

	private void checkFirstPass(IResource resource, ValidationResults vr) throws CoreException {
		assertTrue("We expect there to be exactly two error messages, but errors=" + vr.getSeverityError(), vr.getSeverityError() == 2);
		assertTrue("We expect there to be exactly two warning messages, but warnings=" + vr.getSeverityWarning(), vr.getSeverityWarning() == 2);
		assertTrue("We expect there to be exactly two info messages, but info=" + vr.getSeverityInfo(), vr.getSeverityInfo() == 2);
		
		assertTrue("We expect six messages, but got back: "+vr.getMessages().length , vr.getMessages().length == 6);
		
		IMarker[] markers = resource.findMarkers(ValConstants.ProblemMarker, false, IResource.DEPTH_ZERO);
		int errors =0, warnings=0, info=0;
		for (IMarker marker : markers){
			int severity = marker.getAttribute(IMarker.SEVERITY, -1);
			switch (severity){
				case IMarker.SEVERITY_ERROR: errors++;
				break;
				case IMarker.SEVERITY_WARNING: warnings++;
				break;
				case IMarker.SEVERITY_INFO: info++;
				break;
			}
		}
		assertTrue("We expect there to be exactly one error message, but errors=" + errors, errors == 1);
		assertTrue("We expect there to be exactly one warning message, but warnings="+warnings, warnings == 1);
		assertTrue("We expect there to be exactly one info message, but info="+info, info == 1);
	}

	private void checkSecondPass(IResource resource) throws CoreException {
		IMarker[] markers = resource.findMarkers(ValConstants.ProblemMarker, false, IResource.DEPTH_ZERO);
		int errors =0, warnings=0, info=0;
		for (int i=0; i<markers.length; i++){
			int severity = markers[i].getAttribute(IMarker.SEVERITY, -1);
			switch (severity){
				case IMarker.SEVERITY_ERROR: errors++;
				break;
				case IMarker.SEVERITY_WARNING: warnings++;
				break;
				case IMarker.SEVERITY_INFO: info++;
				break;
			}
		}
		assertTrue("We expect there to be exactly two error messages, but errors=" + errors, errors == 2);
		assertTrue("We expect there to be exactly two warning messages, but warnings="+warnings, warnings == 2);
		assertTrue("We expect there to be exactly two info messages, but info="+info, info == 2);
	}

}
