/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    BEA Systems, Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.classpath.tests.util;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants.DependencyAttributeType;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyUtil;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wtp.j2ee.headless.tests.plugin.HeadlessTestsPlugin;

/**
 * Various utility methods.
 */
public class ClasspathDependencyTestUtil extends DependencyUtil {

	public static final IPath CUSTOM_CLASSPATH_CONTAINER = new Path("TEST_CP_CONTAINER"); 
		
	public static final String TEST_DATA = "TestData" + IPath.SEPARATOR + "ClasspathDependencyTests";
	public static final String TEST1_JAR = "Test1.jar";
	public static final String TEST2_JAR = "Test2.jar";
	public static final String TEST3_JAR = "Test3.jar";
	public static final String TEST3_JAR_OTHER_LOCATION = "other/Test3.jar";
	public static final String TEST3_BIN= "Test3_bin";

	public static final IPath TEST1_JAR_PATH = getFullTestDataPath(TEST1_JAR);
	public static final IPath TEST2_JAR_PATH = getFullTestDataPath(TEST2_JAR);
	public static final IPath TEST3_JAR_PATH = getFullTestDataPath(TEST3_JAR);
	public static final IPath TEST3_JAR_OTHER_LOCATION_PATH = getFullTestDataPath(TEST3_JAR_OTHER_LOCATION);
	public static final IPath TEST3_BIN_PATH = new Path(TEST3_BIN);

	public static final String CLASSPATH_DEPENDENCY_MARKER_TYPE = "org.eclipse.jst.j2ee.ClasspathDependencyValidatorMarker";
	public static final String VALIDATION_MARKER_TYPE = "org.eclipse.wst.validation.problemmarker";
	
	public static IPath getFullTestDataPath(String dataPath) {
		try {
			final String testDataPath = TEST_DATA + IPath.SEPARATOR + dataPath;
			HeadlessTestsPlugin plugin = HeadlessTestsPlugin.getDefault();
			if (plugin != null) {
				URL url = plugin.getBundle().getEntry(testDataPath);
		        if (url != null) {
		            url = Platform.asLocalURL(url);
		            return new Path(url.getPath());
		        }
			}
			return new Path(System.getProperty("user.dir") + IPath.SEPARATOR + testDataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Path("");
	}
	
	
	/**
	 * Adds a library entry that references a jar file and is optionally exported.
	 */
	public static IClasspathEntry addLibraryEntry(final IJavaProject javaProject, final IPath path, final boolean isExported) throws CoreException {
		final IClasspathEntry entry = JavaCore.newLibraryEntry(path, null, null, isExported);
		addEntryToCP(javaProject, entry);
		return entry;
	}
	
	public static void addEntryToCP(final IJavaProject javaProject, final IClasspathEntry entry) throws CoreException {
		final IClasspathEntry[] rawCP = javaProject.getRawClasspath();
		final IClasspathEntry[] newCP = new IClasspathEntry[rawCP.length + 1];
		System.arraycopy(rawCP, 0, newCP, 0, rawCP.length);
		newCP[rawCP.length] = entry;
		javaProject.setRawClasspath(newCP, true, null);
	}
	/**
	 * Adds a custom classpath container with the specified name that references two jar files.
	 */
	public static IClasspathEntry addCustomClasspathContainer(final IJavaProject javaProject) throws CoreException {
		final IClasspathEntry entry = JavaCore.newContainerEntry(CUSTOM_CLASSPATH_CONTAINER, true);
		addEntryToCP(javaProject, entry);
		JavaCore.setClasspathContainer(
				CUSTOM_CLASSPATH_CONTAINER,
				new IJavaProject[]{ javaProject }, 
				new IClasspathContainer[] {
						new IClasspathContainer() {
							public IClasspathEntry[] getClasspathEntries() {
								return new IClasspathEntry[]{ 
										JavaCore.newLibraryEntry(TEST1_JAR_PATH, null, null, false),
										JavaCore.newLibraryEntry(TEST2_JAR_PATH, null, null, false),
								}; 
							}
							public String getDescription() { return "Test classpath container"; }
							public int getKind() { return IClasspathContainer.K_APPLICATION;}
							public IPath getPath() { return CUSTOM_CLASSPATH_CONTAINER; }
						}			
				}, 
				null);
		return entry;
	}
	
	public static void verifyNoClasspathAttributes(final IJavaProject javaProject) throws Exception {
		verifyClasspathAttributes(javaProject, new HashSet());
	}
	
	public static Map verifyClasspathAttributes(final IJavaProject javaProject, final Set rawEntryPaths) throws Exception {
		final Map entriesToAttrib = ClasspathDependencyUtil.getRawComponentClasspathDependencies(javaProject, DependencyAttributeType.CLASSPATH_COMPONENT_DEPENDENCY);
		Assert.assertTrue("Project " + javaProject + " should have " + rawEntryPaths.size() + " raw classpath dependencies, only has: " + 
				entriesToAttrib.size(), entriesToAttrib.size()== rawEntryPaths.size());
		final Iterator i = entriesToAttrib.keySet().iterator();
		while (i.hasNext()) {
			final IClasspathEntry entry = (IClasspathEntry) i.next();
			Assert.assertTrue("Project " + javaProject + " missing expected classpath dependency " + entry.getPath(), rawEntryPaths.contains(entry.getPath()));
		}
		return entriesToAttrib;
	}
	
	public static void verifyNoClasspathDependencies(final IVirtualComponent component) throws Exception {
		verifyClasspathDependencies(component, new HashSet());
	}
	
	public static IVirtualReference[] verifyClasspathDependencies(final IVirtualComponent component, final Set archiveNames) throws Exception {
		Assert.assertTrue("Component " + component.getName() + " not a J2EEModuleVirtualComponent", component instanceof J2EEModuleVirtualComponent);
		J2EEModuleVirtualComponent j2eeComp = (J2EEModuleVirtualComponent) component;
		IVirtualReference[] refs = j2eeComp.getJavaClasspathReferences();
		Assert.assertTrue("Component " + component.getName()+ " should have " + archiveNames.size() + " component classpath dependencies, only has: " + 
				refs.length, refs.length == archiveNames.size());
		for (int i = 0; i < refs.length; i++) {
			Assert.assertTrue("Component " + component.getName()+ " has unexpected component classpath dependency " + refs[i].getArchiveName(), archiveNames.contains(refs[i].getArchiveName()));
		}
		return refs;
	}
	
	public static void verifyNoPotentialClasspathEntries(final IJavaProject javaProject) throws Exception {
		verifyPotentialClasspathEntries(javaProject, new HashSet());
	}
	
	public static List verifyPotentialClasspathEntries(final IJavaProject javaProject, final Set potentialEntryPaths) throws Exception {
		List entries = ClasspathDependencyUtil.getPotentialComponentClasspathDependencies(javaProject);
		Assert.assertTrue("Project " + javaProject + " should have " + potentialEntryPaths.size() + " potential classpath dependencies, only has: " + 
				entries.size(), entries.size() == potentialEntryPaths.size());
		for (int i = 0; i < entries.size(); i++) {
			IClasspathEntry entry = (IClasspathEntry) entries.get(i);
			Assert.assertTrue("Project " + javaProject + " missing expected potential classpath entry " + entry.getPath(), potentialEntryPaths.contains(entry.getPath()));
		}
		return entries;
	}
	
	public static void verifyValidationError(final IProject project) throws CoreException {
		verifyError(project, VALIDATION_MARKER_TYPE, true);
	}
	
	public static void verifyNoValidationError(final IProject project) throws CoreException {
		verifyError(project, VALIDATION_MARKER_TYPE, false);
	}
	
	public static void verifyClasspathDependencyError(final IProject project) throws CoreException {
		verifyError(project, CLASSPATH_DEPENDENCY_MARKER_TYPE, true);
	}
	
	public static void verifyNoClasspathDependencyError(final IProject project) throws CoreException {
		verifyError(project, CLASSPATH_DEPENDENCY_MARKER_TYPE, false);
	}
	
	private static void verifyError(final IProject project, final String markerType, final boolean shouldHaveErrorMarker) throws CoreException {
		final IMarker[] markers = project.findMarkers(markerType, true, IProject.DEPTH_ZERO);
		boolean hasError = false;
		for (int i = 0; i < markers.length; i++) {
			if (markers[i].getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR) {
				hasError = true;
				break;
			}
		}
		if (shouldHaveErrorMarker) {
			Assert.assertTrue("Project " + project + " missing expected validation error problem marker", hasError);
		} else {
			Assert.assertFalse("Project " + project + " has unexpected validation error problem marker", hasError);
		}
	}
	
	public static void verifyClasspathDependencyMarker(final IProject project) throws CoreException {
		verifyMarker(project, CLASSPATH_DEPENDENCY_MARKER_TYPE, true);
	}
	
	public static void verifyNoClasspathDependencyMarker(final IProject project) throws CoreException {
		verifyMarker(project, CLASSPATH_DEPENDENCY_MARKER_TYPE, false);
	}

	private static void verifyMarker(final IProject project, final String markerType, final boolean shouldHaveMarker) throws CoreException {
		final IMarker[] markers = project.findMarkers(markerType, true, IProject.DEPTH_ZERO);
		if (shouldHaveMarker) {
			Assert.assertTrue("Project " + project + " missing expected " + markerType + " problem marker", markers.length > 0);
		} else {
			Assert.assertFalse("Project " + project + " has unexpected " + markerType + " problem marker", markers.length > 0);
		}
	}
}
