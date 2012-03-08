/*******************************************************************************
 * Copyright (c) 2006 2007 BEA Systems, Inc. and others.
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

import org.eclipse.core.resources.IContainer;
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
import org.eclipse.jst.common.jdt.internal.javalite.IJavaProjectLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaCoreLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants.DependencyAttributeType;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;
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
	
	public static final String AppClientProject = "AppClientProject"; //$NON-NLS-1$
	public static final String RootMappingNonEARWARRef = "RootMappingNonEARWARRef"; //$NON-NLS-1$
	public static final String NonTaggedExportedClasses = "NonTaggedExportedClasses"; //$NON-NLS-1$
	public static final String DuplicateArchiveName = "DuplicateArchiveName"; //$NON-NLS-1$
	public static final String ProjectClasspathEntry = "ProjectClasspathEntry"; //$NON-NLS-1$
	public static final String SourceEntry = "SourceEntry"; //$NON-NLS-1$
	public static final String FilteredContainer = "FilteredContainer"; //$NON-NLS-1$
	public static final String DuplicateClassFolderEntry = "DuplicateClassFolderEntry"; //$NON-NLS-1$
	public static final String NonWebNonExported = "NonWebNonExported"; //$NON-NLS-1$
	public static final String InvalidNonWebRuntimePath = "InvalidNonWebRuntimePath"; //$NON-NLS-1$
	public static final String InvalidWebRuntimePath = "InvalidWebRuntimePath"; //$NON-NLS-1$
	
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
			if (ModuleCoreNature.isFlexibleProject(proj)
					&& proj.hasNature(JavaCoreLite.NATURE_ID)) {
			
				final boolean isWebApp = JavaEEProjectUtilities.isDynamicWebProject(proj);
			    final IVirtualComponent component = ComponentCore.createComponent(proj);
			    final boolean isLegacyJ2EE = JavaEEProjectUtilities.isLegacyJ2EEComponent(component);
			  
			    final IJavaProjectLite javaProjectLite = JavaCoreLite.create(proj);
				final Map referencedRawEntries = ClasspathDependencyUtil.getRawComponentClasspathDependencies(javaProjectLite, DependencyAttributeType.CLASSPATH_COMPONENT_DEPENDENCY, isLegacyJ2EE); 				
				final List potentialRawEntries = ClasspathDependencyUtil.getPotentialComponentClasspathDependencies(javaProjectLite, isLegacyJ2EE);				
				final ClasspathDependencyValidatorData data = new ClasspathDependencyValidatorData(proj);
				
				ClasspathDependencyExtensionManager extensionManger = ClasspathDependencyExtensionManager.instance();
				
				// validate the raw referenced container entries
				Iterator i =  referencedRawEntries.keySet().iterator();
				boolean hasRootMapping = false;
				while (i.hasNext()) {
					final IClasspathEntry entry = (IClasspathEntry) i.next();
					final boolean isClassFolder = ClasspathDependencyUtil.isClassFolderEntry(entry);
					final IClasspathAttribute attrib = (IClasspathAttribute) referencedRawEntries.get(entry);
					final IPath runtimePath = ClasspathDependencyUtil.getRuntimePath(attrib, isWebApp, isClassFolder);
					if (runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
						hasRootMapping = true;
					}
					IMessage[] msgs = validateVirtualComponentEntry(entry, attrib, isWebApp, proj, data);
					final String cpEntryPath = entry.getPath().toString();
					for (int j = 0; j < msgs.length; j++) {
						msgs[j].setGroupName(cpEntryPath);
					}
					reportMessages(msgs);
		    		// if not a web app, warn if associated cp entry is not exported
					if (!isWebApp && !entry.isExported() && !extensionManger.doesProjectHandleExport(proj, entry)) {
						_reporter.addMessage(this, new Message("classpathdependencyvalidator", //$NON-NLS-1$
								IMessage.NORMAL_SEVERITY, NonWebNonExported, new String[]{cpEntryPath}, proj));
					}
				}
			
				if (!referencedRawEntries.isEmpty()) {
//					if (JavaEEProjectUtilities.isApplicationClientProject(proj)) { 
//						// classpath component dependencies are not supported for application client projects
//						final IMessage msg = new Message("classpathdependencyvalidator", //$NON-NLS-1$
//								IMessage.HIGH_SEVERITY, AppClientProject, null, proj);
//						_reporter.addMessage(this, msg);
//					}

					// are there any root mappings
					if (hasRootMapping && component != null) {
						boolean referencedFromEARorWAR = false;
						final List earWarRefs = new ArrayList();
						final IVirtualComponent[] refComponents = component.getReferencingComponents();
						for (int j = 0; j < refComponents.length; j++) {
							if (JavaEEProjectUtilities.isEARProject(refComponents[j].getProject())
									|| JavaEEProjectUtilities.isDynamicWebProject(refComponents[j].getProject())) {
								referencedFromEARorWAR = true;
								earWarRefs.add(refComponents[j]);
							}
						}
						if (!referencedFromEARorWAR) {
							// warn if there are root mappings and the project is not referenced by an EAR or a WAR
							final IMessage msg =new Message("classpathdependencyvalidator", //$NON-NLS-1$
									IMessage.NORMAL_SEVERITY, RootMappingNonEARWARRef, null, proj); 
							_reporter.addMessage(this, msg);
						}
					}
				}
				
				// generate warning messages for any potential entries; we warn for these since
				// the classes are being exposed but will not be bundled into the exported/published module and
				// therefore will not be available at runtime.
				i = potentialRawEntries.iterator();
				while (i.hasNext()) {
					final IClasspathEntry entry = (IClasspathEntry) i.next();
					final IMessage msg =new Message("classpathdependencyvalidator", //$NON-NLS-1$
							IMessage.NORMAL_SEVERITY, NonTaggedExportedClasses, new String[]{entry.getPath().toString()}, proj);
					msg.setGroupName(entry.getPath().toString());
					_reporter.addMessage(this, msg); 
				}
				
				// validate all resolved entries (only perform this if there are raw referenced entries)
				if (!referencedRawEntries.isEmpty()) {
					final Map referencedResolvedEntries = ClasspathDependencyUtil.getComponentClasspathDependencies(javaProjectLite, isWebApp, false);  
					i = referencedResolvedEntries.keySet().iterator();
					while (i.hasNext()) {
						final IClasspathEntry entry = (IClasspathEntry) i.next();
						final IClasspathAttribute attrib = (IClasspathAttribute) referencedResolvedEntries.get(entry);
						// compute the archive name
						final String archivePath = ClasspathDependencyUtil.getArchiveName(entry);
						if (archiveNames.contains(archivePath)) {
							// Project cp entry
							final IMessage msg = new Message("classpathdependencyvalidator", //$NON-NLS-1$
									IMessage.HIGH_SEVERITY, DuplicateArchiveName, new String[]{entry.getPath().toString()}, proj);									
							_reporter.addMessage(this, msg); 
						} else {
							archiveNames.add(archivePath);
						}
						// validate the resolved entry if we didn't already validate as part of the raw entries
						if (!referencedRawEntries.containsKey(entry)) {
							IMessage[] msgs = validateVirtualComponentEntry(entry, attrib, isWebApp, proj, data);
							reportMessages(msgs);
						}
					}
				}
			}
		} catch (CoreException e) {
			J2EEPlugin.logError(e);
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
		return validateVirtualComponentEntry(entry, attrib, isWebApp, project, new ClasspathDependencyValidatorData(project));
	}
	
	/**
	 * Holds data required to validate classpath dependencies for a specific project. Can be computed once for the project and reused.
	 *
	 */
	public static class ClasspathDependencyValidatorData {
		private final IProject _project;
		// Class folders mapped via the component file (either directly or via src folders)
		private List <IContainer> javaOutputFolders;
		
		public ClasspathDependencyValidatorData(final IProject project) {
			this._project = project;
		}
		
		public IProject getProject() {
			return _project;
		}
		
		public List <IContainer> getJavaOutputFolders(){
			if(javaOutputFolders == null){
				javaOutputFolders = JavaLiteUtilities.getJavaOutputContainers(ComponentCore.createComponent(_project));		
			}
			return javaOutputFolders;
		}
		
		/**
		 * @deprecated use {@link #getJavaOutputFolders()}
		 * @return
		 */
		public IContainer[] getMappedClassFolders() {
			return getJavaOutputFolders().toArray(new IContainer[javaOutputFolders.size()]);
		}
	}
	
	/**
	 * Checks if the specified Java classpath entry is a valid WTP virtual component reference.
	 * Does not check the runtime path.
	 * @param entry Raw or resolved classpath entry to validate. 
	 * @param attrib The WTP classpath component dependency attribute. Null if it has not yet been set.
	 * @param isWebApp True if the target project is associated with a web project.
	 * @param project The parent project.
	 * @param data Data required for validation. Can be computed once for the project and reused.
	 * @return IMessages representing validation results.
	 */
	public static IMessage[] validateVirtualComponentEntry(final IClasspathEntry entry, final IClasspathAttribute attrib, final boolean isWebApp, final IProject project, 
			final ClasspathDependencyValidatorData data) {
		List results = new ArrayList();
		if (entry == null) {
			return (IMessage[]) results.toArray(new IMessage[results.size()]);
		}
		
		final int kind = entry.getEntryKind();
		final boolean isFile = !ClasspathDependencyUtil.isClassFolderEntry(entry);
		
		if (kind == IClasspathEntry.CPE_PROJECT) {
			// Project cp entry
			// Allow faceted projects only, and not plain java projects
			boolean isFacetedProject = false;
			IProject referencedProject = ResourcesPlugin.getWorkspace().getRoot().getProject(entry.getPath().toString());
			try {
				isFacetedProject = FacetedProjectFramework.isFacetedProject(referencedProject);
			}
			catch (CoreException ce){
				//Ignore. Thrown when project metadata cannot be read. In that case we will treat the project as non faceted 
			}			
			if (!isFacetedProject){
				results.add(new Message("classpathdependencyvalidator", //$NON-NLS-1$
						IMessage.HIGH_SEVERITY, ProjectClasspathEntry, new String[]{entry.getPath().toString()}, project));
					return (IMessage[]) results.toArray(new IMessage[results.size()]);
			}
		} else if (kind == IClasspathEntry.CPE_SOURCE) {
					
			// Source cp entry
			
			results.add(new Message("classpathdependencyvalidator", //$NON-NLS-1$
					IMessage.HIGH_SEVERITY, SourceEntry, new String[]{entry.getPath().toString()}, project));
			return (IMessage[]) results.toArray(new IMessage[results.size()]);
		} else if (kind == IClasspathEntry.CPE_CONTAINER) {

			// get the set of classpath container IDs that should be filtered
			List filteredIDs = ClasspathDependencyExtensions.get().getFilteredClasspathContainerIDs();
			final IPath path = entry.getPath();
			for (int i = 0; i < filteredIDs.size(); i++) {
				final String id = (String) filteredIDs.get(i);
				if (path.segment(0).equals(id)) {
	        		// filtered classpath container
	    			results.add(new Message("classpathdependencyvalidator", //$NON-NLS-1$
	    					IMessage.HIGH_SEVERITY, FilteredContainer, new String[]{entry.getPath().toString()}, project));
	    			return (IMessage[]) results.toArray(new IMessage[results.size()]);					
				}
			}

		} else if (kind == IClasspathEntry.CPE_LIBRARY) {
			if (!isFile) {
				final IContainer[] mappedClassFolders = data.getMappedClassFolders();
				final IResource resource = ClasspathDependencyUtil.getEntryResource(entry);
				if (resource != null) {
					final IPath fullClassFolderPath = resource.getFullPath();
					boolean alreadyMapped = false;
					for (int j = 0; j < mappedClassFolders.length; j++) {
						if (fullClassFolderPath.equals(mappedClassFolders[j].getFullPath())) {
							// entry resolves to same file as existing class folder mapping, skip
							alreadyMapped = true;
							break;
						}
					} 

					// Class folder reference; ensure this is not already mapped via the component file.
					if (alreadyMapped) {
						results.add(new Message("classpathdependencyvalidator", //$NON-NLS-1$
								IMessage.HIGH_SEVERITY, DuplicateClassFolderEntry, new String[]{entry.getPath().toString()}, project));
					}
				}
			}
		}
    	
//    	final IPath runtimePath = ClasspathDependencyUtil.getRuntimePath(attrib, isWebApp, !isFile);
//    	if (!isWebApp) {
//    		// only a ../ or / mapping is currently legal in a non-web context
//    		if (!(runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH) 
//    				|| runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_COMPONENT_PATH))) { 
//    			results.add(new Message("classpathdependencyvalidator", //$NON-NLS-1$
//    					IMessage.HIGH_SEVERITY, InvalidNonWebRuntimePath, new String[]{entry.getPath().toString(), runtimePath.toString()}, project));
//    		}
//    	} else {
//    		String pathStr = runtimePath.toString();
//    		// can only be ../, /WEB-INF/lib or /WEB-INF/classes
//    		if (!runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH) 
//    			&& !runtimePath.equals(IClasspathDependencyConstants.WEB_INF_LIB_PATH)
//    			&& !runtimePath.equals(IClasspathDependencyConstants.WEB_INF_CLASSES_PATH)) { 
//    			results.add(new Message("classpathdependencyvalidator", //$NON-NLS-1$
//    					IMessage.HIGH_SEVERITY, InvalidWebRuntimePath, new String[]{entry.getPath().toString(), pathStr}, project));
//    		}
//    	}

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
