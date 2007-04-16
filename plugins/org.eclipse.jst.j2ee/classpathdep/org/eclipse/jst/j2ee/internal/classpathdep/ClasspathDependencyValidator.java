/*******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * BEA Systems, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainer;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.server.core.internal.RuntimeClasspathContainer;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidatorJob;

/**
 * Validates classpath entries that have been tagged as component dependencies.
 */
public class ClasspathDependencyValidator implements IValidatorJob {
	
	protected IReporter _reporter;
	
	public ClasspathDependencyValidator() {
		super();
	}
	
	public IStatus validateInJob(IValidationContext helper, IReporter reporter)
			throws ValidationException {
		_reporter = reporter;
		//Remove all markers related to this validator
		_reporter.removeAllMessages(this);
		//Using the helper class, load the module model
		final Set archiveNames = new HashSet();
		final IProject proj = ((ClasspathDependencyValidatorHelper) helper).getProject();
		try {
			if (proj.isAccessible() 
			    && proj.hasNature(ModuleCoreNature.MODULE_NATURE_ID)
			    && proj.hasNature(JavaCore.NATURE_ID)) {
			    
				final IJavaProject javaProject = JavaCore.create(proj);
			    final boolean isWebApp = J2EEProjectUtilities.isDynamicWebProject(proj);
				final Map referencedRawEntries = ClasspathDependencyUtil.getRawComponentClasspathDependencies(javaProject); 				
				final List potentialRawEntries = ClasspathDependencyUtil.getPotentialComponentClasspathDependencies(javaProject);
				final IVirtualComponent component = ComponentCore.createComponent(proj);				
				
				// validate the raw referenced container entries
				Iterator i =  referencedRawEntries.keySet().iterator();
				boolean hasRootMapping = false;
				while (i.hasNext()) {
					final IClasspathEntry entry = (IClasspathEntry) i.next();
					final IClasspathAttribute attrib = (IClasspathAttribute) referencedRawEntries.get(entry);
					final IPath runtimePath = ClasspathDependencyUtil.getRuntimePath(attrib, isWebApp);
					if (runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
						hasRootMapping = true;
					}
					IMessage[] msgs = validateVirtualComponentEntry(entry, attrib, isWebApp, proj);
					reportMessages(msgs);
				}
			
				if (!referencedRawEntries.isEmpty()) {
					if (J2EEProjectUtilities.isApplicationClientProject(proj)) { 
						// classpath component dependencies are not supported for application client projects
						_reporter.addMessage(this, new Message("classpathdependencyvalidator", // $NON-NLS-1$
								IMessage.HIGH_SEVERITY, "AppClientProject", null, proj)); // $NON-NLS-1$	
					}

					// are there any root mappings
					if (hasRootMapping && component != null) {
						boolean referencedFromEARorWAR = false;
						final List earWarRefs = new ArrayList();
						final IVirtualComponent[] refComponents = component.getReferencingComponents();
						for (int j = 0; j < refComponents.length; j++) {
							if (J2EEProjectUtilities.isEARProject(refComponents[j].getProject())
									|| J2EEProjectUtilities.isDynamicWebProject(refComponents[j].getProject())) {
								referencedFromEARorWAR = true;
								earWarRefs.add(refComponents[j]);
							}
						}
						if (!referencedFromEARorWAR) {
							// root mappings are only supported if the project is referenced by an EAR or a WAR
							_reporter.addMessage(this, new Message("classpathdependencyvalidator", // $NON-NLS-1$
									IMessage.HIGH_SEVERITY, "RootMappingNonEARWARRef", null, proj)); // $NON-NLS-1$
						}
					}
				}
				
				// generate warning messages for any potential entries; we warn for these since
				// the classes are being exposed but will not be bundled into the exported/published module and
				// therefore will not be available at runtime.
				i = potentialRawEntries.iterator();
				while (i.hasNext()) {
					final IClasspathEntry entry = (IClasspathEntry) i.next();
					_reporter.addMessage(this, new Message("classpathdependencyvalidator", // $NON-NLS-1$
							IMessage.NORMAL_SEVERITY, "NonTaggedExportedClasses", new String[]{entry.getPath().toString()}, proj)); // $NON-NLS-1$	
				}
				
				// validate all resolved entries (only perform this if there are raw referenced entries)
				if (!referencedRawEntries.isEmpty()) {
					final Map referencedResolvedEntries = ClasspathDependencyUtil.getComponentClasspathDependencies(javaProject, isWebApp);  
					i = referencedResolvedEntries.keySet().iterator();
					while (i.hasNext()) {
						final IClasspathEntry entry = (IClasspathEntry) i.next();
						final IClasspathAttribute attrib = (IClasspathAttribute) referencedResolvedEntries.get(entry);
						// compute the archive name
						final String archivePath = ClasspathDependencyUtil.getArchiveName(entry);
						if (archiveNames.contains(archivePath)) {
							// Project cp entry
							_reporter.addMessage(this, new Message("classpathdependencyvalidator", // $NON-NLS-1$
									IMessage.HIGH_SEVERITY, "DuplicateArchiveName", new String[]{entry.getPath().toString()}, proj)); // $NON-NLS-1$				
						} else {
							archiveNames.add(archivePath);
						}
						IMessage[] msgs = validateVirtualComponentEntry(entry, attrib, isWebApp, proj);
						reportMessages(msgs);
					}
				}
			}
		} catch (CoreException e) {
			Logger.getLogger(J2EEPlugin.PLUGIN_ID).logError(e);
		}
		
		return Status.OK_STATUS;
	}
	
	private void reportMessages(final IMessage[] msgs) {
		for (int i = 0; i < msgs.length; i++) {
			_reporter.addMessage(this, msgs[i]); 
		}
	}
	
	/**
	 * Checks if the specified Java classpath entry is a valid WTP virtual component reference.
	 * Does not check the runtime path.
	 * @param entry Raw or resolved classpath entry to validate. 
	 * @param attrib The WTP classpath component dependency attribute. Null if it has not yet been set.
	 * @param isWebApp True if the target project is associated with a web project.
	 * @return IMessages representing validation results.
	 */
	public static IMessage[] validateVirtualComponentEntry(final IClasspathEntry entry, final IClasspathAttribute attrib, final boolean isWebApp, final IProject project) {
		List results = new ArrayList();
		if (entry == null) {
			return (IMessage[]) results.toArray(new IMessage[results.size()]);
		}
		
		final int kind = entry.getEntryKind();
		if (kind == IClasspathEntry.CPE_PROJECT) {
			
			// Project cp entry
			
			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
					IMessage.HIGH_SEVERITY, "ProjectClasspathEntry", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$

			return (IMessage[]) results.toArray(new IMessage[results.size()]);
		} else if (kind == IClasspathEntry.CPE_SOURCE) {
			
			// Source cp entry
			
			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
					IMessage.HIGH_SEVERITY, "SourceEntry", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$
			return (IMessage[]) results.toArray(new IMessage[results.size()]);
		} else if (kind == IClasspathEntry.CPE_CONTAINER) {

			final IPath path = entry.getPath();

    		if (J2EEComponentClasspathContainer.CONTAINER_PATH.isPrefixOf(path)) {
        		// EAR libraries container
    			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
    					IMessage.HIGH_SEVERITY, "EarLibrariesContainer", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$
    			return (IMessage[]) results.toArray(new IMessage[results.size()]);
    		} else if (path.segment(0).equals("org.eclipse.jst.j2ee.internal.web.container")) { //$NON-NLS-1$
        		// web app libraries container
    			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
    					IMessage.HIGH_SEVERITY, "WebAppLibrariesContainer", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$
    			return (IMessage[]) results.toArray(new IMessage[results.size()]);
    		} else if (path.segment(0).equals(RuntimeClasspathContainer.SERVER_CONTAINER)) {
    			// runtime classpath container
    			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
    					IMessage.HIGH_SEVERITY, "RuntimeClasspathContainer", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$
    			return (IMessage[]) results.toArray(new IMessage[results.size()]);
    		} else if (path.segment(0).equals(JavaRuntime.JRE_CONTAINER)) {
    			// JRE classpath container
    			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
    					IMessage.HIGH_SEVERITY, "JREContainer", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$
    			return (IMessage[]) results.toArray(new IMessage[results.size()]);
    		}
		} else if (kind == IClasspathEntry.CPE_LIBRARY) {
			// does the path refer to a file or a folder?
			final IPath entryPath = entry.getPath();
			IPath entryLocation = entryPath;
			final IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(entryPath);
			if (resource != null) {
				entryLocation = resource.getLocation();
			}
			boolean isFile = true; // by default, assume a jar file
			if (entryLocation.toFile().isDirectory()) {
				isFile = false;
			}
				
			if (!isFile) {
				// Class folder reference
				results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
						IMessage.HIGH_SEVERITY, "ClassFolderEntry", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$
			}
		}
		
    	if (!isWebApp && !entry.isExported()) {

    		// if not a web app, associated cp entry must be exported
			
			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
					IMessage.HIGH_SEVERITY, "NonWebNonExported", new String[]{entry.getPath().toString()}, project)); // $NON-NLS-1$
		}
    	
    	final IPath runtimePath = ClasspathDependencyUtil.getRuntimePath(attrib, isWebApp);
    	if (!isWebApp) {
    		// only a ../ mapping is currently legal in a non-web context
    		if (!runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
    			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
    					IMessage.HIGH_SEVERITY, "InvalidNonWebRuntimePath", new String[]{entry.getPath().toString(), runtimePath.toString()}, project)); // $NON-NLS-1$
    		}
    	} else {
    		String pathStr = runtimePath.toString();
    		// can only be ../, /WEB-INF/lib or /WEB-INF/classes
    		if (!runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH) 
    			&& !pathStr.equals("/WEB-INF/lib")
    			&& !pathStr.equals("/WEB-INF/classes")) { 
    			results.add(new Message("classpathdependencyvalidator", // $NON-NLS-1$
    					IMessage.HIGH_SEVERITY, "InvalidWebRuntimePath", new String[]{entry.getPath().toString(), pathStr}, project)); // $NON-NLS-1$
    		}
    	}

		return (IMessage[]) results.toArray(new IMessage[results.size()]);
	}
	
	public ISchedulingRule getSchedulingRule(IValidationContext helper) {
		return null;
	}

	public void cleanup(IReporter reporter) {
		_reporter = null;

	}

	public void validate(IValidationContext helper, IReporter reporter)
			throws ValidationException {
		// Forwarding to job method
		validateInJob(helper, reporter);
	}
}
