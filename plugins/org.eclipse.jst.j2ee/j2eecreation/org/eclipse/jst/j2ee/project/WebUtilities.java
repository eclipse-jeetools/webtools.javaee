/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public class WebUtilities extends JavaEEProjectUtilities {
	public static IPath WEBLIB = new Path("/WEB-INF/lib"); //$NON-NLS-1$

	public WebUtilities() {
	}

	/**
	 * <p>
	 * Retrieves Servlet version information derived from the {@see WebAppResource}.
	 * </p>
	 * 
	 * @return an integer representation of a module version
	 * 
	 */
	public static int getServletVersion(IProject webProject) {
		int retVal = 0;
		Object webAppResource = ModelProviderManager.getModelProvider(webProject).getModelObject();
		if (webAppResource instanceof XMLResource)
		{
			retVal = ((XMLResource)webAppResource).getModuleVersionID();
		}
		else if (webAppResource instanceof WebApp)
		{
			switch (((WebApp)webAppResource).getVersion().getValue()) {
				//EE6TODO
				case WebAppVersionType._25: 
					retVal = J2EEVersionConstants.WEB_2_5_ID;
					break;
			}
		}

		return retVal;
	}

	/**
	 * This method returns the integer representation for the JSP specification level associated
	 * with the J2EE version for this workbench module. This method will not return null and returns
	 * 20 as default.
	 * 
	 * @return an integer representation of the JSP level
	 */
	public static int getJSPVersion(IProject webProject) {
		int servletVersion = getServletVersion(webProject);
		if (servletVersion == J2EEVersionConstants.WEB_2_2_ID)
			return J2EEVersionConstants.JSP_1_1_ID;
		else if (servletVersion == J2EEVersionConstants.WEB_2_3_ID)
			return J2EEVersionConstants.JSP_1_2_ID;
		else
			return J2EEVersionConstants.JSP_2_0_ID;
	}
	
	public static List <IVirtualComponent> getWebFragments(IVirtualComponent webComponent){
		return getWebFragments(webComponent, false);
	}

	/**
	 * Returns a list of WebFragment components for the specified dynamic web component.
	 * @param webComponent
	 * @return
	 */
	public static List <IVirtualComponent> getWebFragments(IVirtualComponent webComponent, boolean expandLibraries){
		List <IVirtualComponent>result = new ArrayList<IVirtualComponent>();
		Map<String, Object> options = new HashMap<String, Object>();
		if (expandLibraries) {
			options.put(J2EEModuleVirtualComponent.GET_EXPANDED_LIB_REFS, Boolean.TRUE);
		}
		IVirtualReference[] refComponents = webComponent.getReferences(options);
		
		for(IVirtualReference virtualReference : refComponents){
			if(virtualReference.getRuntimePath().equals(WEBLIB) && !ClasspathDependencyUtil.isClassFolderReference(virtualReference)){
				IVirtualComponent virtualComponent = virtualReference.getReferencedComponent();
				if(JavaEEProjectUtilities.isWebFragmentProject(virtualComponent)){
					result.add(virtualComponent);
				}
			}
		}
		IVirtualFolder rootFolder = webComponent.getRootFolder();
		IVirtualFolder webLibFolder = rootFolder.getFolder(WEBLIB);
		if(webLibFolder.exists()){
			try {
				IVirtualResource [] webLibs = webLibFolder.members();
				for(IVirtualResource webLib : webLibs){
					if(webLib.getType() == IVirtualResource.FILE && J2EEComponentClasspathUpdater.endsWithIgnoreCase(webLib.getName(), IJ2EEModuleConstants.JAR_EXT)){
						IResource iResource = webLib.getUnderlyingResource();
						IVirtualComponent virtualComponent = ComponentCore.createArchiveComponent(webComponent.getProject(), VirtualArchiveComponent.LIBARCHIVETYPE + iResource.getFullPath().toString());
						if(JavaEEProjectUtilities.isWebFragmentProject(virtualComponent)){
							result.add(virtualComponent);
						}
					}
				}
			} catch (CoreException e) {
				org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
			}
		}
		return result;
	}
	
	public static IVirtualReference[] getLibModules(IVirtualComponent webComponent) {
		return getLibModules(webComponent, false);
	}
	
	
	/**
	 * This method will return the list of dependent modules which are utility jars in the web lib
	 * folder of the deployed path of the module. It will not return null.
	 * 
	 * @return array of the web library dependent modules
	 */
	public static IVirtualReference[] getLibModules(IVirtualComponent webComponent, boolean expandLibraries) {
		List result = new ArrayList();
		Map<String, Object> options = new HashMap<String, Object>();
		options.put(IVirtualComponent.REQUESTED_REFERENCE_TYPE, IVirtualComponent.FLATTENABLE_REFERENCES);
		if (expandLibraries) {
			options.put(J2EEModuleVirtualComponent.GET_EXPANDED_LIB_REFS, Boolean.TRUE);
		}
		IVirtualReference[] refComponents = webComponent.getReferences(options);
		// Check the deployed path to make sure it has a lib parent folder and matchs the web.xml
		// base path
		for (int i = 0; i < refComponents.length; i++) {
			IVirtualReference reference = refComponents[i];
			if (reference.getRuntimePath().equals(WEBLIB) && !ClasspathDependencyUtil.isClassFolderReference(reference))			
				result.add(reference);
		}

		return (IVirtualReference[]) result.toArray(new IVirtualReference[result.size()]);
	}
	
	/**
	 * This method will return the list of dependent modules which are utility jars in the web lib
	 * folder of the deployed path of the module. It will not return null.
	 * 
	 * @return array of the web library dependent modules
	 */
	public static IVirtualReference[] getLibModules(IProject webProject) {
		IVirtualComponent webComponent = ComponentCore.createComponent(webProject);
		return getLibModules(webComponent);
	}

	/**
	 * Determines whether the specified object is an accessible web resource.
	 * 
	 * <p>
	 * An accessible web object is a file or directory that can be accessed
	 * through an URI after deploying on an application server.
	 * </p>
	 * 
	 * <p>
	 * This includes all files and directories that are under the WebContent
	 * root directory of a Dynamic Web Project and are not under the WEB-INF and
	 * META-INF folders.
	 * </p>
	 * 
	 * @param object
	 *            the object to test
	 * @return <code>true</code> if accessible web object, <code>false</code> -
	 *         otherwise.
	 */
	public static boolean isWebResource(Object object) {
		if (object instanceof IResource) {
			IResource resource = (IResource) object;
			IVirtualComponent component = ComponentCore.createComponent(resource.getProject());
			if (component != null && JavaEEProjectUtilities.isDynamicWebComponent(component)) {
				IPath rootPath = component.getRootFolder().getWorkspaceRelativePath();
				IPath webInfPath = rootPath.append(J2EEConstants.WEB_INF);
				IPath metaInfPath = rootPath.append(J2EEConstants.META_INF);
				IPath resourcePath = resource.getFullPath();
				return rootPath.isPrefixOf(resourcePath) && 
						!rootPath.equals(resourcePath) &&
						!webInfPath.isPrefixOf(resourcePath) && 
						!metaInfPath.isPrefixOf(resourcePath);
			}
		}
		return false;
	}

	/**
	 * This method will set the context root on the associated workbench module with the given string
	 * value passed in.  This context root is used by the server at runtime.
	 * 
	 * @param contextRoot string
	 */
	public static void setServerContextRoot(IProject webProject, String contextRoot)
	{
		ComponentUtilities.setServerContextRoot(webProject, contextRoot);
	}
	/**
	 * This method will get the context root on the associated workbench module if null is passed for the earProject, or will use the ear to determine the context root.
	 * This context root is used by the server at runtime.
	 * 
	 * @param webProject IProject
	 * @param earProject IProject - can be null
	 */
	public static String getServerContextRoot(IProject webProject,IProject earProject) {
    	String contextRoot = null;
    	if (earProject == null || !JavaEEProjectUtilities.deploymentDescriptorExists(earProject))
    		return ComponentUtilities.getServerContextRoot(webProject);
    	else if (JavaEEProjectUtilities.isEARProject(earProject) && JavaEEProjectUtilities.isDynamicWebProject(webProject)) {
    		EARArtifactEdit edit = null;
    		try {
    			edit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
    			contextRoot = edit.getWebContextRoot(webProject);
    		} finally {
    			if (edit!=null)
    				edit.dispose();
    		}
    	}
    	return contextRoot;
    }

	/**
	 * Returns all referencing Web projects.
	 * 
	 * @param project
	 *            Project to check. If <code>null</code> then a zero length
	 *            array is returned. If a WAR, then a one element array will be
	 *            returned.
	 * @return Array of referencing Web projects.
	 */
	public static IProject[] getReferencingWebProjects(final IProject project) {
		if (project == null) {
			return new IProject[0];
		} else if (isDynamicWebProject(project)) {
			return new IProject[] { project };
		}

		List result = new ArrayList();
		IVirtualComponent component = ComponentCore.createComponent(project);
		if (component != null) {
			IVirtualComponent[] refComponents = component.getReferencingComponents();
			for (int i = 0; i < refComponents.length; i++) {
				if (isDynamicWebProject(refComponents[i].getProject()))
					result.add(refComponents[i].getProject());
			}
		}

		IProject[] webProjects = (IProject[]) result.toArray(new IProject[result.size()]);
		// sort the list so it is consistent
		Arrays.sort(webProjects, new Comparator<IProject>() {
			public int compare(IProject p0, IProject p1) {
				return p0.getName().compareTo(p1.getName());
			}
		});

		return webProjects;
	}
}
