/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestListeners.java,v $
 *  $Revision: 1.5 $  $Date: 2006/05/17 20:13:56 $ 
 */
package org.eclipse.jem.tests.modelListeners;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.*;

import org.eclipse.jem.tests.JavaProjectUtil;
import org.eclipse.jem.tests.beaninfo.AbstractBeanInfoTestCase;
 

/**
 * 
 * @since 1.0.0
 */
public abstract class TestListeners extends TestCase {
	
	protected IJavaProject jp;

	// The indexes of these lookups are found in the setup and teardown methods. They must be kept in sync.
	protected List setupSpecials = Arrays.asList(new String[] { "testOpen", "testClose", "testAddMethodInWorkcopy", "testSaveFromWorkingCopy",
			"testRevert", "testDeleteMethodNoWorkingCopy-obsolete, removed", "testAddClass", "testDeleteClassWithWorkingCopy",
			"testDeleteClassNoWorkingCopy", "testAddPackage", "testDeletePackage"});
	
	protected IListenerTester tester;
		
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		jp = JavaCore.create(JavaProjectUtil.getProject(AbstractBeanInfoTestCase.TEST_BEANINFO_PROJECT));
		String testname = getName();
		int index = setupSpecials.indexOf(testname);
		switch (index) {
			case 0:
				setUpOpen();
				break;
			case 1:
				setUpClose();
				break;
			case 2:
				setUpAddMethodInWorkingCopy();
				break;
			case 3:
				setUpSaveFromWorkingCopy();
				break;
			case 4:
				setUpRevert();
				break;
//			case 5:
//				setupDeleteMethodNoWorkingCopy();
//				break;
			case 6:
				setupAddClass();
				break;
			case 7:
				setupDeleteClassWithWorkingCopy();
				break;
			case 8:
				setUpDeleteClassNoWorkingCopy();
				break;
			case 9:
				setUpAddPackage();
				break;
			case 10:
				setUpDeletePackage();
				break;
			default:
				break;
		}
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		String testname = getName();
		int index = setupSpecials.indexOf(testname);
		switch (index) {
			case 0:
				tearDownOpen();
				break;
			case 1:
				tearDownClose();
				break;
			case 2:
				tearDownAddMethodInWorkingCopy();
				break;
			case 3:
				tearDownSaveFromWorkingCopy();
				break;
			case 4:
				tearDownRevert();
				break;
//			case 5:
//				tearDownDeleteMethodNoWorkingCopy();
//				break;
			case 6:
				tearDownAddClass();
				break;
			case 7:
				tearDownDeleteClassWithWorkingCopy();
				break;
			case 8:
				tearDownDeleteClassNoWorkingCopy();
				break;
			case 9:
				tearDownAddPackage();
				break;
			case 10:
				tearDownDeletePackage();
				break;
			default:
				break;
		}		
		super.tearDown();
	}	


	protected void setTester(IListenerTester tester) {
		this.tester = tester;
	}
	
	protected void setUpOpen() {	
	}	
	public void testOpen() throws JavaModelException {
		// Test open a working copy.
		testCU = (ICompilationUnit) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo/Test1Class.java"));
		testCU.becomeWorkingCopy(null, null);
		tester.isException();
		tester.isComplete();	// It should of been complete.
	}
	protected void tearDownOpen() throws JavaModelException {
		if (testCU != null)
			testCU.discardWorkingCopy();		
	}
	
	
	protected ICompilationUnit testCU;
	protected void setUpClose() throws JavaModelException {
		testCU = (ICompilationUnit) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo/Test1Class.java"));
		testCU.becomeWorkingCopy(null, null);		
	}	
	public void testClose() throws JavaModelException {
		// Test close a working copy.
		testCU.discardWorkingCopy();
		tester.isException();
		tester.isComplete();	// It should of been complete.
	}
	protected void tearDownClose() throws JavaModelException {
		if (testCU != null)
			testCU.discardWorkingCopy();
	}
	
	
	protected void setUpAddMethodInWorkingCopy() throws JavaModelException {
		testCU = (ICompilationUnit) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo/Test1Class.java"));
		testCU.becomeWorkingCopy(null, null);		
	}	
	public void testAddMethodInWorkcopy() throws JavaModelException {
		testCU.getTypes()[0].createMethod("private void getSomething() {}", null, true, new NullProgressMonitor());
		tester.isException();
		tester.isComplete();	// It should of been complete.
	}
	protected void tearDownAddMethodInWorkingCopy() throws JavaModelException {
		if (testCU != null)
			testCU.discardWorkingCopy();		
	}	
	
	
	protected void setUpSaveFromWorkingCopy() throws JavaModelException {
		testCU = (ICompilationUnit) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo/Test1Class.java"));
		testCU.becomeWorkingCopy(null, null);		
		testCU.getTypes()[0].createMethod("private void getSomething() {}", null, true, new NullProgressMonitor());		
	}
	public void testSaveFromWorkingCopy() throws JavaModelException {
		testCU.commitWorkingCopy(true, new NullProgressMonitor());
		tester.isException();
		tester.isComplete();	// It should of been complete.		
	}
	protected void tearDownSaveFromWorkingCopy() throws JavaModelException {
		if (testCU != null)
			testCU.discardWorkingCopy();				
	}
	
	protected void setUpRevert() throws JavaModelException {
		testCU = (ICompilationUnit) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo/Test1Class.java"));
		testCU.becomeWorkingCopy(null, null);		
		testCU.getTypes()[0].createMethod("private void getSomething() {}", null, true, new NullProgressMonitor());				
	}	
	public void testRevert() throws JavaModelException {
		testCU.restore();
		tester.isException();
		tester.isComplete();	// It should of been complete.		
	}
	protected void tearDownRevert() throws JavaModelException {
		if (testCU != null)
			testCU.discardWorkingCopy();				
	}
	
// With 3.2M6 delete with no working copy got way to complex. Doing this through refactoring and other stuff. Just too complicated
// to even try to figure out. So just pulling it out.
	
//	protected void setupDeleteMethodNoWorkingCopy() throws JavaModelException {
//		testCU = (ICompilationUnit) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo/Test1Class.java"));
//		IMethod m = testCU.getTypes()[0].getMethod("getSomething", new String[0]);
//		if (m.exists())
//			m.delete(true, new NullProgressMonitor());
//		testCU.getTypes()[0].createMethod("private void getSomething() {}", null, true, new NullProgressMonitor());						
//	}
//	public void testDeleteMethodNoWorkingCopy() throws CoreException {
//		JavaCore.run(new IWorkspaceRunnable() {
//			public void run(IProgressMonitor monitor) throws CoreException {
//				// Actually there will be a working copy. This is to simulate what delete method from member list
//				// with no open editor. This is done by batching everything up, but there is a working copy created.
//				// But it changes the file directly then. (Confusing, no? But that is the way it does it in JDT).
//				testCU.becomeWorkingCopy(null, null);
//				IMethod method = testCU.getTypes()[0].getMethod("getSomething", new String[] {});
//				IBuffer cuBuffer = testCU.getBuffer();
//				ISourceRange sr = method.getSourceRange();
//				cuBuffer.replace(sr.getOffset(), sr.getLength(),"");
//				cuBuffer.save(monitor, true);
//				testCU.discardWorkingCopy();
//			}
//		}, new NullProgressMonitor());
//		tester.isException();
//		tester.isComplete();	// It should of been complete.				
//	}
//	protected void tearDownDeleteMethodNoWorkingCopy() throws JavaModelException {
//		if (testCU != null)
//			testCU.discardWorkingCopy();						
//	}
	
	protected void setupAddClass() {
		
	}
	public void testAddClass() throws JavaModelException {
		IPackageFragment pkg = (IPackageFragment) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo"));
		testCU = pkg.createCompilationUnit("NewClass.java", "public class NewClass {}", true, new NullProgressMonitor());
		tester.isException();
		tester.isComplete();	// It should of been complete.				
	}
	protected void tearDownAddClass() throws JavaModelException {
		if (testCU != null) {
			testCU.delete(true, new NullProgressMonitor());
		}
	}
	
	protected void setupDeleteClassWithWorkingCopy() throws JavaModelException {
		IPackageFragment pkg = (IPackageFragment) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo"));
		testCU = pkg.createCompilationUnit("NewClass.java", "public class NewClass {}", true, new NullProgressMonitor());
		testCU.becomeWorkingCopy(null, null);
	}
	public void testDeleteClassWithWorkingCopy() throws CoreException {
		ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {

			public void run(IProgressMonitor monitor) throws CoreException {
				// Need to run this way to simulate what happens for real.
				testCU.delete(true, new NullProgressMonitor());
				ResourcesPlugin.getWorkspace().checkpoint(true);
				testCU.discardWorkingCopy();
			}
		}, new NullProgressMonitor());
		tester.isException();
		tester.isComplete();	// It should of been complete.	
		testCU = null;
	}
	protected void tearDownDeleteClassWithWorkingCopy() throws JavaModelException {
		if (testCU != null && testCU.exists()) {
			testCU.delete(true, new NullProgressMonitor());
		}		
	}
	
	protected void setUpDeleteClassNoWorkingCopy() throws JavaModelException {
		IPackageFragment pkg = (IPackageFragment) jp.findElement(new Path("org/eclipse/jem/tests/beaninfo"));
		testCU = pkg.createCompilationUnit("NewClass.java", "public class NewClass {}", true, new NullProgressMonitor());		
	}
	public void testDeleteClassNoWorkingCopy() throws JavaModelException {
		testCU.delete(true, new NullProgressMonitor());
		tester.isException();
		tester.isComplete();	// It should of been complete.	
		testCU = null;		
	}
	protected void tearDownDeleteClassNoWorkingCopy() throws JavaModelException {
		if (testCU != null && testCU.exists()) {
			testCU.delete(true, new NullProgressMonitor());
		}				
	}
	
	protected void setUpAddPackage() throws JavaModelException {
		IPackageFragment pkg = (IPackageFragment) jp.findElement(new Path("test"));
		if (pkg != null)
			pkg.delete(true, new NullProgressMonitor());
	}
	public void testAddPackage() throws JavaModelException {
		IPackageFragmentRoot[] roots = jp.getPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE) {
				roots[i].createPackageFragment("test", true, new NullProgressMonitor());
				break;
			}
		}
		tester.isException();
		tester.isComplete();	// It should of been complete.			
	}
	protected void tearDownAddPackage() throws JavaModelException {
		IPackageFragment pkg = (IPackageFragment) jp.findElement(new Path("test"));
		if (pkg != null)
			pkg.delete(true, new NullProgressMonitor());		
	}
	
	protected IPackageFragment testPkg;
	protected void setUpDeletePackage() throws JavaModelException {
		IPackageFragmentRoot[] roots = jp.getPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			if (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE) {
				testPkg = roots[i].createPackageFragment("test", true, new NullProgressMonitor());
				break;
			}
		}		
	}
	public void testDeletePackage() throws JavaModelException {
		testPkg.delete(true, new NullProgressMonitor());
		tester.isException();
		tester.isComplete();	// It should of been complete.			
		testPkg = null;
	}
	protected void tearDownDeletePackage() throws JavaModelException {
		if (testPkg != null && testPkg.exists())
			testPkg.delete(true, new NullProgressMonitor());
	}
	
}
