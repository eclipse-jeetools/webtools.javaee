/*
 * (c) Copyright 2001 MyCorporation.
 * All Rights Reserved.
 */
package org.eclipse.jst.validation.test.internal.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.validation.test.BVTValidationException;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.internal.registry.BVTValidationRegistryReader;
import org.eclipse.jst.validation.test.internal.registry.ITestcaseMetaData;
import org.eclipse.jst.validation.test.internal.registry.OperationTestcase;
import org.eclipse.jst.validation.test.internal.registry.TestcaseUtility;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;
import org.eclipse.wst.validation.internal.ConfigurationConstants;
import org.eclipse.wst.validation.internal.ValidationRegistryReader;
import org.eclipse.wst.validation.internal.ValidatorMetaData;

/**
 * @version 	1.0
 * @author
 */
public final class BVTValidationUtility {
	private BVTValidationUtility() {
	}
	
	public static ValidatorMetaData getValidatorMetaData(IMarker marker) {
		String validatorClassName = null;
		try {
			validatorClassName = marker.getAttribute(ConfigurationConstants.VALIDATION_MARKER_OWNER).toString();
			ValidatorMetaData vmd = ValidationRegistryReader.getReader().getValidatorMetaData(validatorClassName);
			return vmd;
		}
		catch (CoreException exc) {
		}
		return null;		
	}
	
	private static IMarker[] getValidationTasks(IResource resource) {
		try {
			IMarker[] allMarkers = resource.findMarkers(ConfigurationConstants.VALIDATION_MARKER, false, IResource.DEPTH_INFINITE); // false means only consider PROBLEM_MARKER, not variants of PROBLEM_MARKER. Since addTask only adds PROBLEM_MARKER, we don't need to consider its subtypes.
			return allMarkers;
		}
		catch (CoreException exc) {
			return null;
		}
	}
	
	public static void removeAllValidationMarkers() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IMarker[] markers = getValidationTasks(root);
		if(markers != null) {
			try {
				workspace.deleteMarkers(markers);
			}
			catch(CoreException e) {
			}
		}
	}

	public static String getProjectName(File file) {
		String fileName = file.getName();
		int extIndex = fileName.indexOf('.');
		if(extIndex > 0) {
			return fileName.substring(0, extIndex);
		}
		else {
			return fileName;
		}
	}

	public static String getQualifiedLogName(String logFileName) {
		String dir = BVTValidationPlugin.getPlugin().getStateLocation().toOSString();
		File dirWithFile = new File(dir, logFileName);
		return dirWithFile.toString();
	}
	
	public static IProject[] getProjects(ITestcaseMetaData[] tmds) {
		List sortedList = new ArrayList();
		for(int i=0; i<tmds.length; i++) {
			IProject p = TestcaseUtility.findProject(tmds[i]);
			if((p != null) && (p.exists()) && (p.isOpen()) && !sortedList.contains(p)) {
				sortedList.add(p);
			}
		}
		Collections.sort(sortedList, new Comparator() {
			public int compare(Object a, Object b){
				if((a == null) && (b == null)) {
					return 0;
				}
				else if(a == null) {
					return 1;
				}
				else if(b == null) {
					return -1;
				}
			
				if((a instanceof IProject) && (b instanceof IProject)) {
					return ((IProject)a).getName().compareTo(((IProject)b).getName());
				}
				
				return -1; // should never reach here...both Objects should always be IProjects
			}
		});
		IProject[] result = new IProject[sortedList.size()];
		sortedList.toArray(result);
		return result;
	}

	public static ITestcaseMetaData[] getTests(ITestcaseMetaData[] tmds, IProject p) {
		ITestcaseMetaData[] temp = new ITestcaseMetaData[tmds.length];
		int count = 0;
		for(int i=0; i<tmds.length; i++) {
			ITestcaseMetaData tmd = tmds[i];
			if(tmd.getProjectName().equals(p.getName())) {
				temp[count++] = tmd;
			}
		}
		
		ITestcaseMetaData[] result = new ITestcaseMetaData[count];
		System.arraycopy(temp, 0, result, 0, count);
		return result;
	}

	/**
	 * Return the total number of validator tests.
	 */	
	public static int numValidatorTests(IProgressMonitor monitor, IProject[] projects) throws BVTValidationException {	
		BVTValidationRegistryReader reader = BVTValidationRegistryReader.getReader();
		
		int totalTests = 0;
		for(int i=0; i<projects.length; i++) {
			IProject project = projects[i];
			ValidatorTestcase[] tests = reader.getValidatorTests(monitor, project);
			if((tests == null) || (tests.length == 0)) {
				continue;
			}
			
			totalTests += tests.length;
		}
		
		return totalTests;
	}
	
	/**
	 * Return all public (visible) test cases for a particular project.
	 */
	public static ValidatorTestcase[] getValidatorTests(IProgressMonitor monitor, IProject[] projects) {
		Set testSet = new HashSet();		
		for(int i=0; i<projects.length; i++) {
			IProject project = projects[i];
			ValidatorTestcase[] tests = getValidatorTests(monitor, project);
			if(tests.length == 0) {
				continue;
			}
			
			for(int j=0; j<tests.length; j++) {
				ValidatorTestcase vt = tests[j];
				testSet.add(vt);
			}
		}
		
		ValidatorTestcase[] result = new ValidatorTestcase[testSet.size()];
		testSet.toArray(result);
		return result;
	}

	/**
	 * Return all visible validator test cases for a project.
	 */
	public static ValidatorTestcase[] getValidatorTests(IProgressMonitor monitor, IProject project) {
		BVTValidationRegistryReader reader = BVTValidationRegistryReader.getReader();

		ValidatorTestcase[] tests = reader.getValidatorTests(monitor, project);
		if(tests == null) {
			return new ValidatorTestcase[0];
		}
		
		return tests;
	}

	/**
	 * Return all of the tests.
	 */
	public static OperationTestcase[] getOperationTests(IProgressMonitor monitor) {
		return BVTValidationRegistryReader.getReader().getOperationTests(monitor, (String)null);
	}
	
	public static OperationTestcase[] getOperationTests(IProgressMonitor monitor, IProject project) {
		BVTValidationRegistryReader reader = BVTValidationRegistryReader.getReader();

		OperationTestcase[] tests = reader.getOperationTests(monitor, project);
		if(tests == null) {
			return new OperationTestcase[0];
		}
		
		return tests;
	}

	/**
	 * Return all of the tests.
	 */
	public static ValidatorTestcase[] getValidatorTests(IProgressMonitor monitor) {
		return BVTValidationRegistryReader.getReader().getValidatorTests(monitor, (String)null);
	}
	
	/**
	 * Return all of the tests, visible and invisible, owned by the validator metadata.
	 */
	public static ValidatorTestcase[] getValidatorTests(IProgressMonitor monitor, ValidatorMetaData vmd) {
		ValidatorTestcase[] allTests = BVTValidationRegistryReader.getReader().getValidatorTests(monitor, (String)null);
		ValidatorTestcase[] temp = new ValidatorTestcase[allTests.length];
		int count = 0;
		for(int i=0; i<allTests.length; i++) {
			ValidatorTestcase testvmd = allTests[i];
			if(vmd.equals(testvmd.getValidatorMetaData())) {
				temp[count++] = testvmd;
			}
		}
		ValidatorTestcase[] result = new ValidatorTestcase[count];
		System.arraycopy(temp, 0, result, 0, count);
		return result;
	}
	
	public static ValidatorMetaData[] getValidatorsThatHaveTests(IProgressMonitor monitor) {
		ValidatorTestcase[] tests = getValidatorTests(monitor);
		Set temp = new HashSet();
		for(int i=0; i<tests.length; i++) {
			ValidatorTestcase vt = tests[i];
			temp.add(vt.getValidatorMetaData());
		}
		
		ValidatorMetaData[] vmds = new ValidatorMetaData[temp.size()];
		temp.toArray(vmds);
		return vmds;
	}
}
