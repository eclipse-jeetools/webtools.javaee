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
package org.eclipse.jst.j2ee.dependency.tests.util;

import java.io.IOException;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.builder.IDependencyGraph;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * Test utility class that programmatically verifies J2EE dependencies. 
 */
public class DependencyVerificationUtil {
	
	public static final IPath WEB_INF_LIB = new Path("/WEB-INF/lib"); //$NON-NLS-1$
	public static final IPath ROOT = new Path("/"); //$NON-NLS-1$
	
	/**
	 * Verifies the presence (or lack) of dependency between two projects as defined by the
	 * DependencyManager.
	 * @param source Source project
	 * @param target Target project
	 * @param hasDep True if a dependency should exist; false if not.
	 * @throws Exception
	 */
	public static void verifyDependency(final IProject source, final IProject target, final boolean hasDep) {
		Set<IProject> referencingComponents = IDependencyGraph.INSTANCE.getReferencingComponents(target);
		boolean dep = false;
		for (IProject project: referencingComponents) {
			if (project.equals(source)) {
				dep= true;
				break;
			}
		}
		if (hasDep) {
			Assert.assertTrue("DependencyGraphManager does not report dependency between " + source + " and " + target, dep);
		} else {
			Assert.assertFalse("DependencyGraphManager incorrectly reporting dependency between " + source + " and " + target, dep);
		}		
	}
	
	/**
	 * Verifies the presence (or lack) of a component reference between two projects.
	 * @param source Source project
	 * @param target Target project
	 * @param runtimePath Runtime path of target project; null to not check path.
	 * @param hasRef True if a component reference should exist; false if not.
	 * @throws Exception
	 */
	public static void verifyComponentReference(final IProject source, final IProject target, final IPath runtimePath, final boolean hasRef) {
		IVirtualComponent sourceComp = ComponentCore.createComponent(source);
		IVirtualReference[] refs = sourceComp.getReferences();
		boolean ref = false;
		for (int i = 0; i < refs.length; i++) {
			if (refs[i].getReferencedComponent().getProject().equals(target)) {
				if (runtimePath == null || refs[i].getRuntimePath().equals(runtimePath)) {
					ref = true;
					break;
				}
			}
		}
		if (hasRef) {
			Assert.assertTrue("Source project " + source + " missing component reference to " + target, ref);
		} else {
			Assert.assertFalse("Source project " + source + " still has component reference to " + target, ref);			
		}
	}
	
	/**
	 * Verifies the presence (or lack) or a static project reference between two projects.
	 * @param source Source project.
	 * @param target Target project.
	 * @param hasRef True if a project reference should exist; false if not.
	 * @throws Exception
	 */
	public static void verifyProjectReference(final IProject source, final IProject target, final boolean hasRef) throws CoreException {
		final IProjectDescription desc = source.getDescription();
		IProject[] refs = desc.getReferencedProjects();
		boolean ref = false;
		for (int i = 0; i < refs.length; i++) {
			if (refs[i].equals(target)) {
				ref = true;
				break;
			}
		}
		if (hasRef) {
			Assert.assertTrue("Source project " + source + " missing project reference to " + target, ref);
		} else {
			Assert.assertFalse("Source project " + source + " still has project reference to " + target, ref);			
		}
	}
	
	/**
	 * Verifies the presence (or lack) of the specified MANIFEST.MF entry.
	 * @param source Source project
	 * @param entry Manifest entry.
	 * @param hasEntry True if the entry should exist, false if not.
	 * @throws Exception
	 */
	public static void verifyManifestReference(final IProject source, final String entry, final boolean hasEntry) throws CoreException, IOException {
		final IVirtualComponent sourceComp = ComponentCore.createComponent(source);
		final IVirtualFile vf = sourceComp.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI) );
		final IFile manifestmf = vf.getUnderlyingFile();
		final ArchiveManifest manifest = DependencyCreationUtil.getArchiveManifest(manifestmf);
		String[] cp = manifest.getClassPathTokenized();
		boolean hasManifestRef = false;
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].equals(entry)) {
				hasManifestRef = true;
			}
		}
		if (hasEntry) {
			Assert.assertTrue("Source project " + source + " missing MANIFEST.MF entry " + entry, hasManifestRef);
		} else {
			Assert.assertFalse("Source project " + source + " still has MANIFEST.MF entry " + entry, hasManifestRef);			
		}
	}
	
	/**
	 * Verifies the presence of an application.xml reference in the specified EAR project for the
	 * specified module project.
	 * @param earProject The EAR project.
	 * @param moduleProject The module project.
	 * @param moduleURI Ignored unless hasRef is false.
	 * @param hasRef True if there should be a ref, false if not
	 * @return Computed module URI or null if one was passed in.
	 * @throws Exception
	 */
	public static String verifyApplicationXMLReference(final IProject earProject, final IProject moduleProject, final String moduleURI, final boolean hasRef) throws CoreException {
		EARArtifactEdit earEdit = null;
		try {
			earEdit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			Assert.assertTrue(earEdit != null);
			Application application = earEdit.getApplication();
			if (hasRef) {
				IVirtualComponent moduleComp = ComponentCore.createComponent(moduleProject);
				String computedURI = earEdit.getModuleURI(moduleComp);
				Assert.assertTrue("EAR's application.xml missing module element for " + moduleURI, 
						application.getFirstModule(computedURI) != null);
				return computedURI;
			} else {
				Module module = application.getFirstModule(moduleURI);
				Assert.assertTrue("EAR's application.xml should not have a module element for " + moduleURI, 
						module == null);
			}
		} finally {
			if (earEdit != null)
				earEdit.dispose();
		}
		return null;
	}
	
	public static void waitForClasspathUpdate() {
		Job [] jobs = Job.getJobManager().find(J2EEComponentClasspathUpdater.MODULE_UPDATE_JOB_NAME);
		if(jobs.length > 0){
			try {
				for (int i = 0; i < jobs.length; i++){
					if(jobs[i].getName().equals(J2EEComponentClasspathUpdater.MODULE_UPDATE_JOB_NAME))
						jobs[i].join();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Verifies that the specified target project is visible (or not visible) to the source project via the Java build path 
	 * @param source Source project.
	 * @param target Target project.
	 * @param hasRef True if the target project should be on the resolved classpath, false if not.
	 * @throws Exception
	 */
	public static void verifyClasspathReference(final IProject source, final IProject target, final boolean hasRef) throws CoreException {
		waitForClasspathUpdate();
		boolean onClasspath = hasClasspathReference(source, target);
		if (hasRef) {
			Assert.assertTrue("Project " + target + " missing from resolved classpath of project " + source, onClasspath);
		} else {
			Assert.assertFalse("Project " + target + " should not be on resolved classpath of project " + source, onClasspath);
		}	
	}
    
    public static boolean hasClasspathReference(final IProject source, final IProject target) throws CoreException {
        IJavaProject javaProject = JavaCore.create(source);
        IClasspathEntry[] cp = javaProject.getResolvedClasspath(true); // ignore unresolvable entries
        boolean onClasspath = false;
        for (int i = 0; i < cp.length; i++) {
            if (cp[i].getEntryKind() == IClasspathEntry.CPE_PROJECT 
                    && cp[i].getPath().equals(target.getFullPath())) {
                onClasspath= true;
                break;
            }
        }
        return onClasspath;
    }
    
	
	public static String verifyEARDependency(final IProject earProject, final IProject childProject, final boolean moduleRef) throws CoreException {
		// .project dep
		verifyProjectReference(earProject, childProject, true);
		// .component dep
		
		IPath runtimePath = ROOT;
		if(EarUtilities.isJEEComponent(ComponentCore.createComponent(earProject)) &&  JavaEEProjectUtilities.isUtilityProject(childProject)){
			runtimePath = new Path("/lib");
		}
		
		verifyComponentReference(earProject, childProject, runtimePath, true);
		// application.xml ref
		String moduleURI = null;
		if (moduleRef) {
			moduleURI = verifyApplicationXMLReference(earProject, childProject, null, true);
		}
		// DependencyGraphManager
		verifyDependency(earProject, childProject, true);
		
		return moduleURI;
	}

	public static void verifyEARDependencyChanged(final IProject earProject, final IProject oldChildProject, final IProject newChildProject) throws CoreException {
		verifyEARDependencyChanged(earProject, oldChildProject, null, newChildProject);
	}
	
	public static void verifyEARDependencyChanged(final IProject earProject, final IProject oldChildProject, final String oldModuleURI, final IProject newChildProject) throws CoreException {
		verifyEARDependency(earProject, newChildProject, oldModuleURI != null);
		verifyEARDependencyRemoved(earProject, oldChildProject, oldModuleURI);
	}
	
	public static void verifyEARDependencyRemoved(final IProject earProject, final IProject childProject) throws CoreException {
		verifyEARDependencyRemoved(earProject, childProject, null);
	}
	
	public static void verifyEARDependencyRemoved(final IProject earProject, final IProject childProject, final String oldModuleURI) throws CoreException {
		// .component dep
		verifyComponentReference(earProject, childProject, ROOT, false);
		// application.xml ref
		if (oldModuleURI != null) {
			verifyApplicationXMLReference(earProject, childProject, oldModuleURI, false);			
		}
		// DependencyGraphManager
		verifyDependency(earProject, childProject, false);
		// .project dep
		verifyProjectReference(earProject, childProject, false);
	}
	
	public static void verifyModuleDependency(final IProject source, final IProject target) throws CoreException, IOException {
		// verify MANIFEST.MF dep added
		verifyManifestReference(source, target.getName() + ".jar", true); //$NON-NLS-1$
		// verify classpath ref (will be via "EAR Libraries")
		verifyClasspathReference(source, target, true);
		// DependencyGraphManager only tracks references defined by the .settings/org.eclipse.wst.common.component file
		// References defined via manifests will not be mapped in the IDependencyGraph
		//verifyDependency(source, target, true);
	}
	
	public static void verifyModuleDependencyChanged(final IProject source, final IProject oldTarget, final IProject newTarget) throws CoreException, IOException {
		verifyModuleDependency(source, newTarget);
		verifyModuleDependencyRemoved(source, oldTarget);
	}
	
	public static void verifyModuleDependencyRemoved(final IProject source, final IProject target) throws CoreException, IOException {
		// verify MANIFEST.MF dep removed
		verifyManifestReference(source, target.getName()+ ".jar", false);
		// verify classpath ref removed 
		verifyClasspathReference(source, target, false);
		// DependencyGraphManager
		verifyDependency(source, target, false);
	}
	
	public static void verifyWebLibDependency(final IProject source, final IProject target) throws CoreException {
		// .project dep
		verifyProjectReference(source, target, true);
		// .component dep
		verifyComponentReference(source, target, WEB_INF_LIB, true);
		// verify classpath ref (will be via "EAR Libraries")
		verifyClasspathReference(source, target, true);
		// DependencyGraphManager
		verifyDependency(source, target, true);
	}
	
	public static void verifyWebLibDependencyChanged(final IProject source, final IProject oldTarget, final IProject newTarget) throws CoreException {
		verifyWebLibDependency(source, newTarget);
		verifyWebLibDependencyRemoved(source, oldTarget);
	}
	
	public static void verifyWebLibDependencyRemoved(final IProject source, final IProject target) throws CoreException {
		// verify changed .project dep
		verifyProjectReference(source, target, false);
		// .component dep
		verifyComponentReference(source, target, WEB_INF_LIB, false);
		// verify classpath ref removed 
		verifyClasspathReference(source, target, false);
		// DependencyGraphManager
		verifyDependency(source, target, false);
	}
	
}
