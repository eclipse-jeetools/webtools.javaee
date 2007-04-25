/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * BEA Systems, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.classpathdep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.JavaProject;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyValidator;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Contains utility code for working manipulating the WTP classpath component
 * dependency attribute.
 */
public class ClasspathDependencyUtil implements IClasspathDependencyConstants {
	
	private static final IPath WEB_INF_LIB = new Path("/WEB-INF/lib");
	private static final IPath PARENT_MAPPING = new Path ("../");
	
	
	/**
	 * Returns all unresolved classpath entries for the specified Java project that
	 * have the special WTP classpath component dependency attribute.
	 *  
	 * @param javaProject Java project whose component classpath dependencies are being retrieved.
	 * @return Map from IClasspathEntry to IClasspathAttribute for classpath component dependency.
	 * @return IClasspathEntries with the special component dependency attribute.
	 * @throws CoreException Thrown if an error is encountered accessing the unresolved classpath.
	 */
	public static Map getRawComponentClasspathDependencies(final IJavaProject javaProject) throws CoreException {
		if (javaProject == null) {
			return Collections.EMPTY_MAP;
		}
		final Map referencedRawEntries = new HashMap();
		final IClasspathEntry[] entries = javaProject.getRawClasspath();
        for (int i = 0; i < entries.length; i++) {
            final IClasspathEntry entry = entries[i];
            final IClasspathAttribute attrib = checkForComponentDependencyAttribute(entry);
            if (attrib != null) {
            	referencedRawEntries.put(entry, attrib);
            }
        }
        return referencedRawEntries;
	}
	
	/**
	 * Retrieves the unresolved classpath entries for the specified Java project that
	 * could potentially be mapped into the virtual component tree for the project via
	 * the special WTP classpath component dependence attribute. Classpath entries that
	 * currently have the attribute are not returned by this method (@see {@link #getRawComponentClasspathDependencies(IJavaProject, boolean)})
	 * 
	 * @param javaProject Java project whose potential component classpath dependencies will be retrieved.
	 * @return List of raw IClasspathEntries for potential classpath component dependencies.
	 * @throws CoreException Thrown if an error is encountered. 
	 */
	public static List getPotentialComponentClasspathDependencies(final IJavaProject javaProject) throws CoreException {
		final List potentialRawEntries = new ArrayList();

		if (javaProject == null || !javaProject.getProject().isAccessible()) {
			return Collections.EMPTY_LIST;
		}
		final IProject project = javaProject.getProject();
		final IClasspathEntry[] entries = javaProject.getRawClasspath();
        for (int i = 0; i < entries.length; i++) {
            final IClasspathEntry entry = entries[i];
            final int kind = entry.getEntryKind();
            final IClasspathAttribute attrib = checkForComponentDependencyAttribute(entry);
            if (attrib != null) {
            	continue; // already has the attribute
            } else {
            	// check validation logic for entry
            	// always mark the "isWebApp" param as true so that we get both exported and non-exported entries; for non-web projects,
            	// want to let a user have the option to see and select the non-exported entries and then just generate a validation
            	// error if they happen to select one.
            	final IMessage[] msgs = ClasspathDependencyValidator.validateVirtualComponentEntry(entry, null, true, project);
            	boolean error = false;
            	for (int j = 0; j < msgs.length; j++) {
            		if (msgs[j].getSeverity() == IMessage.HIGH_SEVERITY) {
            			error = true;
            			break;
            		}
            	}
            	if (error) {
            		continue;
            	}
            	
            	// entry can potentially be tagged as a component dependency
            	potentialRawEntries.add(entry);
            }
        }
        return potentialRawEntries;
	}
	
	private static boolean isValid(final IClasspathEntry entry, final IClasspathAttribute attrib, final boolean isWebApp, final IProject project) {
		final IMessage[] msgs = ClasspathDependencyValidator.validateVirtualComponentEntry(entry, attrib, isWebApp, project);
		boolean valid = true;
		for (int j = 0; j < msgs.length; j++) {
			if (msgs[j].getSeverity() == IMessage.HIGH_SEVERITY) {
				valid = false;
				break;
			}
		}
		return valid;
	}
	
	/**
	 * Returns all resolved classpath entries for the specified Java project that
	 * have one of the special WTP classpath component dependency attributes and pass the set of rules
	 * that govern valid classpath dependencies.
	 *  
	 * @param javaProject Java project whose component classpath dependencies are being retrieved.
	 * @param isWebApp True if the target project is associated with a web project.
	 * @return Map from IClasspathEntry to IClasspathAttribute for classpath component dependencies.
	 * @throws CoreException Thrown if an error is encountered accessing the unresolved classpath.
	 */
	public static Map getComponentClasspathDependencies(final IJavaProject javaProject, final boolean isWebApp) throws CoreException {
		return getComponentClasspathDependencies(javaProject, isWebApp, true);
	}

	/**
	 * Returns all resolved classpath entries for the specified Java project that
	 * have one of the special WTP classpath component dependency attributes.
	 *  
	 * @param javaProject Java project whose component classpath dependencies are being retrieved.
	 * @param isWebApp True if the target project is associated with a web project.
	 * @param onlyValid If true, only valid dependencies will be returned.
	 * @return Map from IClasspathEntry to IClasspathAttribute for classpath component dependencies.
	 * @throws CoreException Thrown if an error is encountered accessing the unresolved classpath.
	 */
	public static Map getComponentClasspathDependencies(final IJavaProject javaProject, final boolean isWebApp, final boolean onlyValid) throws CoreException {

		// get the raw entries
		final Map referencedRawEntries = getRawComponentClasspathDependencies(javaProject);
		final Map validRawEntries = new HashMap();

		// filter out non-valid referenced raw entries
		final Iterator i = referencedRawEntries.keySet().iterator();
		while (i.hasNext()) {
			final IClasspathEntry entry = (IClasspathEntry) i.next();
			final IClasspathAttribute attrib = (IClasspathAttribute) referencedRawEntries.get(entry);
			if (!onlyValid || isValid(entry, attrib, isWebApp, javaProject.getProject())) {
				validRawEntries.put(entry, referencedRawEntries.get(entry));
			}
		}

		// if we have no valid raw entries, return empty map
		if (validRawEntries.isEmpty()) {
        	return Collections.EMPTY_MAP;
		}
		
        // NOTE: if, despite the prohibition listed in the API, it is in fact OK to make a call directly into 
        // IClasspathContainer.getClasspathEntries() in this context (and we can be certain that the container
        // implementations will support necessary caching), we can remove the internal API usage 
        
		final IClasspathEntry[] entries = javaProject.getResolvedClasspath(true);
        final JavaProject jProject = (JavaProject) javaProject;
        final Map resolvedPathToRawEntries = jProject.getPerProjectInfo().rootPathToRawEntries;
        // if the resolved-to-raw map is null or empty for some reason, return
        if (resolvedPathToRawEntries == null || resolvedPathToRawEntries.isEmpty()) {
        	return Collections.EMPTY_MAP;
        }
        
		final Map referencedEntries = new HashMap();        
        
        // collect the paths for all resolved entries that are associated with a raw dependent
        // entry
        for (int j = 0; j < entries.length; j++) {
            final IClasspathEntry entry = entries[j];
            final IClasspathEntry rawEntry = (IClasspathEntry) resolvedPathToRawEntries.get(entry.getPath());            
            if (rawEntry == null) {
            	continue;
            }
            if (!validRawEntries.containsKey(rawEntry)) {
            	// missing entry from validRawEntries, skip
            	continue;
            }
            IClasspathAttribute attrib = (IClasspathAttribute) validRawEntries.get(rawEntry);
            final IClasspathAttribute resolvedAttrib = checkForComponentDependencyAttribute(entry);
        	// attribute for the resolved entry must either be unspecified or it must be the
        	// dependency attribute for it to be included
            if (resolvedAttrib == null || resolvedAttrib.getName().equals(CLASSPATH_COMPONENT_DEPENDENCY)) {
            	// filter out resolved entry if it doesn't pass the validation rules
    			if (!onlyValid || isValid(entry, resolvedAttrib != null?resolvedAttrib:attrib,isWebApp, javaProject.getProject())) {
    				if (resolvedAttrib != null) {
    					// if there is an attribute on the sub-entry, use that
    					attrib = resolvedAttrib;
    				}
    				referencedEntries.put(entry, attrib);
    			}
            } 
        }
        
        return referencedEntries;
	}
	
	/**
	 * Retrieves the location (as a absolute file system path) for the specified classpath entry.
	 * @param entry Classpath entry. If null, returns null.
	 * @return Absolute file system path.
	 */
	public static IPath getEntryLocation(final IClasspathEntry entry) {
		if (entry == null) {
			return null;
		}
		final IPath entryPath = entry.getPath();
		IPath entryLocation = entryPath;
		final IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(entryPath);
		if (resource != null) {
			entryLocation = resource.getLocation();
		}
		return entryLocation;
	}
	
	/**
	 * Retrieves the location (as an absolute local file system path) for the classpath dependency represented
	 * by the specified IVirtualReference.
	 * @param ref The IVirtualReference
	 * @return Absolute path in the local file system or null if the specified reference is null or does not refer to 
	 * a VirtualArchiveComponent with type VirtualArchiveComponent.CLASSPATHARCHIVETYPE.
	 */
	public static IPath getClasspathVirtualReferenceLocation(final IVirtualReference ref) {
		if (ref == null) {
			return null;
		}
		if (ref.getReferencedComponent() instanceof VirtualArchiveComponent) {
			final VirtualArchiveComponent comp = (VirtualArchiveComponent) ref.getReferencedComponent();
			if (comp.getArchiveType().equals(VirtualArchiveComponent.CLASSPATHARCHIVETYPE)) {
				java.io.File cpEntryFile = comp.getUnderlyingDiskFile();
				IPath cpEntryPath;
				if (cpEntryFile.exists()) {
					cpEntryPath = new Path(cpEntryFile.getAbsolutePath());
                } else {
                    final IFile iFile = comp.getUnderlyingWorkbenchFile();
                    cpEntryPath = iFile.getLocation();
                }
				return cpEntryPath;
			}
		}
		return null;
	}
	
	/**
	 * Retrieves the runtime path to which the resolved classpath entry components will be
	 * added within the deployed application.
	 * @param attrib The IClasspathAttribute with the WTP classpath component dependency value. If null,
	 * will return the default path.
	 * @param isWebApp True for web projects, false otherwise.
	 * @return Runtime path. Will be null if the attribute is not a WTP classpath component dependency 
	 * attribute.
	 */
	public static IPath getRuntimePath(final IClasspathAttribute attrib, final boolean isWebApp) {
    	if (attrib != null && !attrib.getName().equals(CLASSPATH_COMPONENT_DEPENDENCY)) {
    		return null;
    	}
    	if (attrib == null || attrib.getValue()== null || attrib.getValue().length() == 0) {
    		return getDefaultRuntimePath(isWebApp);
    	}
    	return new Path(attrib.getValue());
	}
	
	/**
	 * Retrieves the default runtime path to which the resolved classpath entry components will be
	 * added within the deployed application.
	 * @param isWebApp True if the default runtime path for web apps should be returned, false otherwise.
	 * @return The default runtime path. 
	 */
	public static IPath getDefaultRuntimePath(final boolean isWebApp) {
		return isWebApp ? WEB_INF_LIB : PARENT_MAPPING; // $NON-NLS-1$
	}
	
	/**
	 * Retrieves the archive name for the specified classpath entry
	 * @param entry The entry.
	 * @return The archive name.
	 */
	public static String getArchiveName(final IClasspathEntry entry) {
		final IPath entryLocation = getEntryLocation(entry);
		return entryLocation.lastSegment();
	}
	
	/**
	 * Checks if the specified IClasspathEntry has one of the special WTP component dependency
	 * attributes that indicate it should be mapped into the virtual component for the associated project.
	 * 
	 * @param entry The IClasspathEntry.
	 * @return The IClasspathAttribute that holds the special WTP attribute or null if one was not found.
	 */
	public static IClasspathAttribute checkForComponentDependencyAttribute(final IClasspathEntry entry) {
		if (entry == null) {
			return null;
		}
	    final IClasspathAttribute[] attributes = entry.getExtraAttributes();
	    for (int i = 0; i < attributes.length; i++) {
	    	final IClasspathAttribute attribute = attributes[i];
	    	final String name = attribute.getName();
	    	if (name.equals(CLASSPATH_COMPONENT_DEPENDENCY) 
	    			|| name.equals(CLASSPATH_COMPONENT_NON_DEPENDENCY)) {
	    		return attribute;
	    	}
	    }
	    return null;
	}
	
	/**
	 * Determines if the specified virtual component represents a classpath component dependency.
	 * @param component Virtual component to test
	 * @return True if a classpath component dependency, false otherwise.
	 */
	public static boolean isClasspathComponentDependency(final IVirtualComponent component) {
		if (component == null) {
			return false;
		}
		if (component instanceof VirtualArchiveComponent) {
			final VirtualArchiveComponent archiveComp = (VirtualArchiveComponent) component;
			return archiveComp.getArchiveType().equals(VirtualArchiveComponent.CLASSPATHARCHIVETYPE);
		}
		return false;
	}
	
	/**
	 * Retrieves the classpath component display string for the specified component.
	 * @param component Component that represents a classpath component.
	 * @return Display string.
	 */
	public static String getClasspathComponentDependencyDisplayString(final IVirtualComponent component) {
		final URI archiveURI = URI.createURI(ModuleURIUtil.getHandleString(component));
		return archiveURI.lastSegment();
	}
	
}
