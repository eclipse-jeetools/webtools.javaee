/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.deployables;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.web.jfaces.extension.FileURL;
import org.eclipse.jst.j2ee.internal.web.jfaces.extension.FileURLExtensionReader;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntimeUtilities;
import org.eclipse.jst.j2ee.internal.web.operations.WebEditModel;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.server.core.WebResource;
import org.eclipse.wst.common.modulecore.ArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.web.internal.operation.IBaseWebNature;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @version 1.0
 * @author
 */
public class WebDeployableArtifactUtil  {
	private final static String[] extensionsToExclude = new String[]{"sql", "xmi"}; //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Constructor for WebDeployableObjectAdapter.
	 */
	public WebDeployableArtifactUtil() {
		super();
	}

	/*
	 * @see IDeployableObjectAdapterDelegate#getDeployableObject(Object)
	 */
	public static IModuleArtifact getModuleObject(Object obj) {
		IResource resource = null;
		if (obj instanceof IResource)
			resource = (IResource) obj;
		else if (obj instanceof IAdaptable)
			resource = (IResource) ((IAdaptable) obj).getAdapter(IResource.class);
		else if (obj instanceof EObject) {
			resource = ProjectUtilities.getProject((EObject) obj);

			/*
			 * ccc - this code is a pseudo-rehash of the code below. The difference is that we have
			 * a Servlet, instead of an IResource that might be a Servlet
			 */
			if (obj instanceof Servlet) {
				String mapping = null;
				java.util.List mappings = ((Servlet) obj).getMappings();
				IBaseWebNature webNature = J2EEWebNatureRuntimeUtilities.getRuntime(resource.getProject());

				if (mappings != null && !mappings.isEmpty()) {
					ServletMapping map = (ServletMapping) mappings.get(0);
					mapping = map.getUrlPattern();
				}
				if (mapping != null) {
					return new WebResource(getModule(webNature), new Path(mapping));
				}
				WebType webType = ((Servlet) obj).getWebType();
				if (webType.isJspType()) {
					resource = ((IProject) resource).getFile(webNature.getModuleServerRootName() + "/" + ((JSPType) webType).getJspFile()); //$NON-NLS-1$
				} else if (webType.isServletType()) {
					return new WebResource(getModule(webNature), new Path("servlet/" + ((ServletType) webType).getClassName())); //$NON-NLS-1$
				}
			}
		}
		if (resource == null)
			return null;

		// find deployable
		IBaseWebNature webNature = J2EEWebNatureRuntimeUtilities.getRuntime(resource.getProject());
		if (webNature == null)
			return null;

		if (resource instanceof IProject)
			return new WebResource(getModule(webNature), new Path("")); //$NON-NLS-1$

		String className = getServletClassName(resource);
		if (className != null) {
			String mapping = getServletMapping(resource.getProject(), true, className);
			if (mapping != null) {
				return new WebResource(getModule(webNature), new Path(mapping));
			}
			// if there is no servlet mapping, provide direct access to the servlet
			// through the fully qualified class name
			return new WebResource(getModule(webNature), new Path("servlet/" + className)); //$NON-NLS-1$

		}

		// determine path
		IPath rootPath = webNature.getRootPublishableFolder().getProjectRelativePath();
		IPath resourcePath = resource.getProjectRelativePath();

		// Check to make sure the resource is under the webApplication directory
		if (resourcePath.matchingFirstSegments(rootPath) != rootPath.segmentCount())
			return null;

		// Do not allow resource under the web-inf directory
		resourcePath = resourcePath.removeFirstSegments(rootPath.segmentCount());
		if (resourcePath.segmentCount() > 1 && resourcePath.segment(0).equals(IWebNatureConstants.INFO_DIRECTORY))
			return null;

		if (shouldExclude(resource))
			return null;

		//Extension read to get the correct URL for Java Server Faces file if
		//the jsp is of type jsfaces.
		FileURL jspURL = FileURLExtensionReader.getInstance().getFilesURL();
		if (jspURL != null) {
			IPath correctJSPPath = jspURL.getFileURL(resource, resourcePath);
			if (correctJSPPath != null && correctJSPPath.toString().length() > 0)
				return new WebResource(getModule(webNature), correctJSPPath);
		}
		// return Web resource type
		return new WebResource(getModule(webNature), resourcePath);
	}

	/**
	 * Method shouldExclude.
	 * 
	 * @param resource
	 * @return boolean
	 */
	private static boolean shouldExclude(IResource resource) {
		String fileExt = resource.getFileExtension();

		// Exclude files of certain extensions
		for (int i = 0; i < extensionsToExclude.length; i++) {
			String extension = extensionsToExclude[i];
			if (extension.equalsIgnoreCase(fileExt))
				return true;
		}
		return false;
	}

	protected static IModule getModule(IBaseWebNature nature) {
		IModule deployable = nature.getModule();
		if (deployable != null)
			return deployable;

		IProject project = nature.getProject();
		Iterator iterator = Arrays.asList(ServerUtil.getModules("j2ee.web")).iterator(); //$NON-NLS-1$
		
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof IModule) {
				deployable = (IModule) next;
				if (deployable.getProject().equals(project))
					return deployable;
			}
		}
		return null;
	}

	/**
	 * If this resource is a servlet, return the class name. If not, return null.
	 * 
	 * @param resource
	 * @return java.lang.String
	 */
	public static String getServletClassName(IResource resource) {
		if (resource == null)
			return null;

		try {
			IProject project = resource.getProject();
			IPath path = resource.getFullPath();
			if (!project.hasNature(JavaCore.NATURE_ID) || path == null)
				return null;

			IJavaProject javaProject = (IJavaProject) project.getNature(JavaCore.NATURE_ID);
			if (!javaProject.isOpen())
				javaProject.open(new NullProgressMonitor());

			// output location may not be on classpath
			IPath outputPath = javaProject.getOutputLocation();
			if (outputPath != null && "class".equals(path.getFileExtension()) && outputPath.isPrefixOf(path)) { //$NON-NLS-1$
				int count = outputPath.segmentCount();
				path = path.removeFirstSegments(count);
			}

			// remove initial part of classpath
			IClasspathEntry[] classPathEntry = javaProject.getResolvedClasspath(true);
			if (classPathEntry != null) {
				int size = classPathEntry.length;
				for (int i = 0; i < size; i++) {
					IPath classPath = classPathEntry[i].getPath();
					if (classPath.isPrefixOf(path)) {
						int count = classPath.segmentCount();
						path = path.removeFirstSegments(count);
						i += size;
					}
				}
			}

			// get java element
			IJavaElement javaElement = javaProject.findElement(path);

			IType[] types = getTypes(javaElement);
			if (types != null) {
				int size2 = types.length;
				for (int i = 0; i < size2; i++) {
					if (isServlet(types[i]))
						return types[i].getFullyQualifiedName();
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns the types contained within this java element.
	 * 
	 * @param element
	 *            com.ibm.jdt.core.api.IJavaElement
	 * @return com.ibm.jdt.core.api.IType[]
	 */
	private static IType[] getTypes(IJavaElement element) {
		try {
			if (element.getElementType() != IJavaElement.COMPILATION_UNIT)
				return null;

			return ((ICompilationUnit) element).getAllTypes();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Returns true if this java type derives from javax.servlet.GenericServlet
	 * 
	 * @param type
	 *            com.ibm.jdt.core.api.IType
	 * @return boolean
	 */
	private static boolean isServlet(IType type) {
		try {
			ITypeHierarchy hierarchy = type.newSupertypeHierarchy(null);
			IType[] superClasses = hierarchy.getAllSuperclasses(type);

			int size = superClasses.length;
			for (int i = 0; i < size; i++) {
				if ("javax.servlet.GenericServlet".equals(superClasses[i].getFullyQualifiedName())) //$NON-NLS-1$
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Return the mapping of a servlet or JSP file, or null if none was available.
	 * 
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 * @param isServlet
	 *            boolean
	 * @param typeName
	 *            java.lang.String
	 * @return java.lang.String
	 */
	public static String getServletMapping(IProject project, boolean isServlet, String typeName) {
		if (typeName == null || typeName.equals("")) //$NON-NLS-1$
			return null;
		
		ArtifactEdit artifact = null;
		WebArtifactEdit webEdit = null;
		WebApp webApp = null;
		try{
			artifact = ModuleCore.getFirstArtifactEditForRead( project );
			webEdit = ( WebArtifactEdit )artifact;
       		if(webEdit != null) {
           		webApp = webEdit.getWebApplication();		               		

       		}			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if( webEdit != null )
				webEdit.dispose();
		}

		try {
	
			// find servlet
			Iterator iterator = webApp.getServlets().iterator();
			while (iterator.hasNext()) {
				Servlet servlet = (Servlet) iterator.next();
				boolean valid = false;

				WebType webType = servlet.getWebType();
				if (webType.isServletType() && isServlet) {
					ServletType type = (ServletType) webType;
					if (typeName.equals(type.getClassName()))
						valid = true;
				} else if (webType.isJspType() && !isServlet) {
					JSPType type = (JSPType) webType;
					if (typeName.equals(type.getJspFile()))
						valid = true;
				}

				if (valid) {
					java.util.List mappings = servlet.getMappings();
					if (mappings != null && !mappings.isEmpty()) {
						ServletMapping map = (ServletMapping) mappings.get(0);
						return map.getUrlPattern();
					}
				}
			}

			return null;
		} catch (Exception e) {
			return null;
		} finally {
			try {

			} catch (Exception ex) {
				// ignore
			}
		}
	}

}