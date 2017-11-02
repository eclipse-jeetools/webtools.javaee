/*******************************************************************************
 * Copyright (c) 2007, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.ClasspathLibraryExpander;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualReference;
import org.eclipse.wst.common.componentcore.internal.util.FacetedProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.util.VirtualReferenceUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class JavaEEProjectUtilities extends ProjectUtilities implements IJ2EEFacetConstants {
	protected static final IVirtualReference[] NO_REFERENCES = new IVirtualReference[0];
	public static final int FACET_VERSION = 0;
	public static final int DD_VERSION = 1;
	
	public JavaEEProjectUtilities() {
	}

	
	public static boolean isProjectOfType(IProject project, String typeID) {
		return getProjectFacetVersion(project, typeID) != null;
	}

	public static IProjectFacetVersion getProjectFacetVersion(IProject project, String typeID){
		return FacetedProjectUtilities.getProjectFacetVersion(project, typeID);
	}
	
	private static boolean isProjectOfType(IFacetedProject facetedProject, String typeID) {
		return FacetedProjectUtilities.isProjectOfType(facetedProject, typeID);
	}

	private static boolean isEARProject(IFacetedProject project) {
		return isProjectOfType(project, ENTERPRISE_APPLICATION);
	}

	private static boolean isDynamicWebProject(IFacetedProject project) {
		return isProjectOfType(project, DYNAMIC_WEB);
	}

	private static boolean isStaticWebProject(IFacetedProject project) {
		return isProjectOfType(project, STATIC_WEB);
	}

	private static boolean isEJBProject(IFacetedProject project) {
		return isProjectOfType(project, EJB);
	}

	private static boolean isJCAProject(IFacetedProject project) {
		return isProjectOfType(project, JCA);
	}

	private static boolean isApplicationClientProject(IFacetedProject project) {
		return isProjectOfType(project, APPLICATION_CLIENT);
	}

	private static boolean isUtilityProject(IFacetedProject project) {
		return isProjectOfType(project, UTILITY);
	}

	public static boolean isEARProject(IProject project) {
		return isProjectOfType(project, ENTERPRISE_APPLICATION);
	}
	
	public static boolean isDynamicWebComponent(IVirtualComponent component) {
		if (component.isBinary()) {
			return isBinaryType(component, JavaEEQuickPeek.WEB_TYPE);
		}
		return isProjectOfType(component.getProject(), DYNAMIC_WEB);
	}

	public static boolean isDynamicWebProject(IProject project) {
		return isProjectOfType(project, DYNAMIC_WEB);
	}
	
	public static boolean isWebFragmentProject(IProject project) {
		return isProjectOfType(project, WEBFRAGMENT);
	}
	
	public static boolean isWebFragmentProject(IFacetedProject project) {
		return isProjectOfType(project, WEBFRAGMENT);
	}
	
	public static boolean isWebFragmentProject(IVirtualComponent component) {
		if (component.isBinary()) {
			return isBinaryType(component, JavaEEQuickPeek.WEBFRAGMENT_TYPE);
		}
		return isProjectOfType(component.getProject(), WEBFRAGMENT);
	}

	public static boolean isStaticWebProject(IProject project) {
		return isProjectOfType(project, STATIC_WEB);
	}

	public static boolean isEJBComponent(IVirtualComponent component) {
		if (component.isBinary()) {
			return isBinaryType(component, JavaEEQuickPeek.EJB_TYPE);
		}
		return isProjectOfType(component.getProject(), EJB);
	}

	public static boolean isEJBProject(IProject project) {
		return isProjectOfType(project, EJB);
	}

	public static boolean isJCAComponent(IVirtualComponent component) {
		if (component.isBinary()) {
			return isBinaryType(component, JavaEEQuickPeek.CONNECTOR_TYPE);
		}
		return isProjectOfType(component.getProject(), JCA);
	}

	public static boolean isJCAProject(IProject project) {
		return isProjectOfType(project, JCA);
	}

	public static boolean isApplicationClientComponent(IVirtualComponent component) {
		if (component.isBinary()) {
			return isBinaryType(component, JavaEEQuickPeek.APPLICATION_CLIENT_TYPE);
		}
		return isProjectOfType(component.getProject(), APPLICATION_CLIENT);
	}

	public static boolean isApplicationClientProject(IProject project) {
		return isProjectOfType(project, APPLICATION_CLIENT);
	}

	public static boolean isUtilityProject(IProject project) {
		return isProjectOfType(project, UTILITY);
	}

	/**
	 * Return all projects in workspace of the specified type
	 * 
	 * @param type -
	 *            use one of the static strings on this class as a type
	 * @return IProject[]
	 */
	public static IProject[] getAllProjectsInWorkspaceOfType(String type) {
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		List result = new ArrayList();
		for (int i = 0; i < projects.length; i++) {
			if (isProjectOfType(projects[i], type))
				result.add(projects[i]);
		}
		return (IProject[]) result.toArray(new IProject[result.size()]);
	}
	
	/**
	 * @param  IVirtualComponent of the enclosing component
	 * @param  IVirtualReference[] of references contained by the enclosing component
	 * @return IVirtualReference[] of original references in addition to the expanded list of references for any
	 *         consumed references found
	 */
	public static IVirtualReference[] getExpandedReferences(IVirtualComponent component, IVirtualReference[] refs) {
		ArrayList<IVirtualReference> allRefs = new ArrayList<IVirtualReference>();
		for (int i = 0; i < refs.length; i++) {
			IVirtualReference reference = refs[i];
			if (reference.getDependencyType()==IVirtualReference.DEPENDENCY_TYPE_CONSUMES) {
				List<IVirtualReference> references = expandConsumedReference(component, reference);
				for (Iterator<IVirtualReference> iterator = references.iterator(); iterator.hasNext();) {
					allRefs.add(iterator.next());
				}
			}
			else {
				allRefs.add(reference);
			}
		}
		return allRefs.toArray(new IVirtualReference[allRefs.size()]);
	}
	
	/**
	 * @param  IVirtualComponent of the enclosing component that is doing the consuming
	 * @param  IVirtualReference of the consumed reference
	 * @return List containing an IVirtualReference for all archives consumed by the enclosing component
	 */
	public static List<IVirtualReference> expandConsumedReference(IVirtualComponent rootComponent, IVirtualReference reference) {
		if (reference.getDependencyType()!=IVirtualReference.DEPENDENCY_TYPE_CONSUMES) {
			return Collections.EMPTY_LIST;
		}
		List <IVirtualReference> libRefs = new ArrayList<IVirtualReference>();
		try {
			ClasspathLibraryExpander classpathLibExpander = new ClasspathLibraryExpander(rootComponent, reference);
			List<IFlatFile> flatLibs = classpathLibExpander.fetchFlatFiles();
			for (IFlatFile flatFile : flatLibs) {
				File file = flatFile.getAdapter(File.class);
				if (file != null) {
					String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
					IVirtualComponent dynamicComponent = ComponentCore.createArchiveComponent(
							rootComponent.getProject(), type + file.getAbsolutePath(), flatFile.getModuleRelativePath().makeAbsolute());
					IVirtualReference dynamicRef = ComponentCore.createReference(rootComponent, dynamicComponent);
					((VirtualReference)dynamicRef).setDerived(true);
					dynamicRef.setArchiveName(file.getName());
					dynamicRef.setRuntimePath(flatFile.getModuleRelativePath().makeAbsolute());
					libRefs.add(dynamicRef);
				}
			}	
		} catch (CoreException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}
		return libRefs;
	}
	
	private static boolean isBinaryType(IVirtualComponent aBinaryComponent, int quickPeekType){
		JavaEEQuickPeek qp = JavaEEBinaryComponentHelper.getJavaEEQuickPeek(aBinaryComponent);
		int type = qp.getType();
		return quickPeekType == type;
	}
	
	private static String convertQuickpeekResult(int type) {
		switch(type){
		case JavaEEQuickPeek.APPLICATION_CLIENT_TYPE:
			return APPLICATION_CLIENT;
		case JavaEEQuickPeek.WEB_TYPE:
			return DYNAMIC_WEB;
		case JavaEEQuickPeek.EJB_TYPE:
			return EJB;
		case JavaEEQuickPeek.CONNECTOR_TYPE:
			return JCA;
		case JavaEEQuickPeek.APPLICATION_TYPE:
			return ENTERPRISE_APPLICATION;
		case JavaEEQuickPeek.WEBFRAGMENT_TYPE:
			return WEBFRAGMENT;
		default:
			return UTILITY;
		}
	}
	public static String getJ2EEComponentType(IVirtualComponent component) {
		if (null != component) {
			if (component.isBinary()) {
				JavaEEQuickPeek qp = JavaEEBinaryComponentHelper.getJavaEEQuickPeek(component);
				return convertQuickpeekResult(qp.getType());
			}
			return getJ2EEProjectType(component.getProject());
		}
		return ""; //$NON-NLS-1$
	}
	public static String getJ2EEFileType(IPath path) {
		if( path != null && path.toFile().exists() && path.toFile().isFile()) {
			JavaEEQuickPeek qp = JavaEEBinaryComponentHelper.getJavaEEQuickPeek(path);
			return convertQuickpeekResult(qp.getType());
		}
		return null;
	}

	public static String getJ2EEProjectType(IProject project) {
		if (null != project && project.isAccessible()) {
			IFacetedProject facetedProject = null;
			try {
				facetedProject = ProjectFacetsManager.create(project);
			} catch (CoreException e) {
				return ""; //$NON-NLS-1$
			}
			if (isApplicationClientProject(facetedProject))
				return APPLICATION_CLIENT;
			else if (isDynamicWebProject(facetedProject))
				return DYNAMIC_WEB;
			else if (isEJBProject(facetedProject))
				return EJB;
			else if (isEARProject(facetedProject))
				return ENTERPRISE_APPLICATION;
			else if (isJCAProject(facetedProject))
				return JCA;
			else if (isStaticWebProject(facetedProject))
				return STATIC_WEB;
			else if (isUtilityProject(facetedProject))
				return UTILITY;
			else if (isWebFragmentProject(facetedProject))
				return WEBFRAGMENT;
		}
		return ""; //$NON-NLS-1$
	}
	
	
	/**
	 * Returns true if the Java EE Deployment Descriptor exists for the
	 * specified project.
	 * 
	 * @param virtualComponent
	 * @return
	 * @see #getDeploymentDescriptorQuickPeek(IProject)
	 */
	public static boolean deploymentDescriptorExists(IProject project) {
		IVirtualFile ddFile = getJavaEEDeploymentDescriptor(project);
		return ddFile != null;
	}

	/**
	 * Returns a JavaEEQuickPeek for the Java EE Deployment Descriptor for the
	 * specified project. If no Java EE Deployment Descriptor exists null will
	 * be returned.
	 * 
	 * @param project
	 * @return
	 * @see #deploymentDescriptorExists(IProject)
	 */
	public static JavaEEQuickPeek getDeploymentDescriptorQuickPeek(IProject project) {
		IVirtualFile ddFile = getJavaEEDeploymentDescriptor(project);
		if (ddFile == null) {
			return null;
		}
		InputStream in = null;
		try {
			in = ddFile.getUnderlyingFile().getContents();
			JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(in);
			return quickPeek;
		} catch (CoreException e) {
			J2EEPlugin.logError(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					J2EEPlugin.logError(e);
				}
			}
		}
		return null;
	}

	/**
	 * Returns an IVirtualFile for the Java EE Deployment Descriptor for the
	 * specified project if it exists. Returns null if there is no Java EE
	 * Deployment Descriptor
	 * 
	 * @param virtualComponent
	 * @return
	 */
	private static IVirtualFile getJavaEEDeploymentDescriptor(IProject project) {
		if (project == null) {
			throw new NullPointerException();
		}
		String ddURI = null;
		if (isEARProject(project)) {
			ddURI = J2EEConstants.APPLICATION_DD_URI;
		} else if (isEJBProject(project)) {
			ddURI = J2EEConstants.EJBJAR_DD_URI;
		} else if (isApplicationClientProject(project)) {
			ddURI = J2EEConstants.APP_CLIENT_DD_URI;
		} else if (isJCAProject(project)) {
			ddURI = J2EEConstants.RAR_DD_URI;
		} else if (isDynamicWebProject(project)) {
			ddURI = J2EEConstants.WEBAPP_DD_URI;
		}else if (isWebFragmentProject(project)) {
			ddURI = J2EEConstants.WEBFRAGMENT_DD_URI;
		} else {
			throw new IllegalArgumentException("Project:" + project.getName() + " is not a Java EE Project"); //$NON-NLS-1$//$NON-NLS-2$
		}

		IVirtualComponent comp = ComponentCore.createComponent(project);
		if (comp != null) {
			IVirtualFile vFile = comp.getRootFolder().getFile(new Path(ddURI));
			if (vFile.exists()) {
				return vFile;
			}
		}
		return null;
	}
	
	
	
	
	/**
	 * Returns the J2EE Module version based on the DD XML file
	 * @param project
	 * @return version String
	 */
	public static String getJ2EEDDProjectVersion(IProject project) {
		int type = J2EEVersionConstants.UNKNOWN;
		String ddURI = null;
		if (isEARProject(project)) {
			type = J2EEVersionConstants.APPLICATION_TYPE;
			ddURI = J2EEConstants.APPLICATION_DD_URI;
		} else if (isEJBProject(project)) {
			type = J2EEVersionConstants.EJB_TYPE;
			ddURI = J2EEConstants.EJBJAR_DD_URI;
		} else if (isApplicationClientProject(project)) {
			type = J2EEVersionConstants.APPLICATION_CLIENT_TYPE;
			ddURI = J2EEConstants.APP_CLIENT_DD_URI;
		} else if (isJCAProject(project)) {
			type = J2EEVersionConstants.CONNECTOR_TYPE;
			ddURI = J2EEConstants.RAR_DD_URI;
		} else if (isDynamicWebProject(project)) {
			type = J2EEVersionConstants.WEB_TYPE;
			ddURI = J2EEConstants.WEBAPP_DD_URI;
		} else if (isWebFragmentProject(project)) {
			type = J2EEVersionConstants.WEBFRAGMENT_TYPE;
			ddURI = J2EEConstants.WEBFRAGMENT_DD_URI;
		} 

		if(type != J2EEVersionConstants.UNKNOWN){
			IVirtualComponent comp = ComponentCore.createComponent(project);
			if (comp != null) {
				IVirtualFile vFile = comp.getRootFolder().getFile(new Path(ddURI));
				if(vFile.exists()){
					InputStream in= null;
					try{
						in = vFile.getUnderlyingFile().getContents();
						JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(in);
						int vers = (quickPeek.getVersion() == J2EEVersionConstants.UNKNOWN) ? getLatestVersion(project) : quickPeek.getVersion();
						return J2EEVersionUtil.convertVersionIntToString(vers);
					} catch (CoreException e) {
						J2EEPlugin.logError(e);
					} finally {
						if(in != null){
							try {
								in.close();
							} catch (IOException e) {
								J2EEPlugin.logError(e);
							}
						}
					}
					
				}
				else
					return J2EEVersionUtil.convertVersionIntToString(getLatestVersion(project));
			}
		}
		
		return null;
	}


	private static int getLatestVersion(IProject project) {
		if (isEARProject(project) || isApplicationClientProject(project))
			return J2EEVersionConstants.VERSION_8_0;
		if (isEJBProject(project))
			return J2EEVersionConstants.VERSION_3_2;
		if (isDynamicWebProject(project))
			return J2EEVersionConstants.VERSION_4_0;
		if (isWebFragmentProject(project))
			return J2EEVersionConstants.VERSION_4_0;
		if (isJCAProject(project))
			return J2EEVersionConstants.VERSION_1_7;
		return J2EEVersionConstants.UNKNOWN;
			
	}

	public static int getJ2EEVersion(IProject javaEEProject)
	{
		int retVal = 0;
		return retVal;
	}
	
	/**
	 * Given a component returns whether the component has
	 * Java EE version greater than or equal to 5
	 * 
	 * @param component
	 * @return true if the component is Java EE version 5 or greater, false otherwise
	 * @throws NullPointerException if the component is null
	 */
	public static boolean isJEEComponent(IVirtualComponent component){
		if(component == null){
			throw new NullPointerException();
		}
		
		if(component.isBinary()){
			JavaEEQuickPeek qp = JavaEEBinaryComponentHelper.getJavaEEQuickPeek(component);
			int javaEEVersion = qp.getJavaEEVersion();
			return javaEEVersion >= J2EEConstants.JEE_5_0_ID;
		}
		IProject project = component.getProject();
		
		IProjectFacetVersion facetVersion = getProjectFacetVersion(project, ENTERPRISE_APPLICATION);
		if(facetVersion != null){
			if(facetVersion == IJ2EEFacetConstants.ENTERPRISE_APPLICATION_12 ||
			   facetVersion == IJ2EEFacetConstants.ENTERPRISE_APPLICATION_13 ||
			   facetVersion == IJ2EEFacetConstants.ENTERPRISE_APPLICATION_14){
				return false;
			}
			return true;
		}
		
		facetVersion = getProjectFacetVersion(project, APPLICATION_CLIENT);
		if(facetVersion != null){
			if(facetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_12 ||
			   facetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_13 ||
			   facetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_14){
				return false;
			}
			return true;
		}
		
		facetVersion = getProjectFacetVersion(project, EJB);
		if(facetVersion != null){
			if(facetVersion == IJ2EEFacetConstants.EJB_11 ||
			   facetVersion == IJ2EEFacetConstants.EJB_20 ||
			   facetVersion == IJ2EEFacetConstants.EJB_21){
				return false;
			}
			return true;
		}
		
		facetVersion = getProjectFacetVersion(project, DYNAMIC_WEB);
		if(facetVersion != null){
			if(facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_22 ||
			   facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_23 ||
			   facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_24){
				return false;
			}
			return true;
		}
		
		facetVersion = getProjectFacetVersion(project, JCA);
		if(facetVersion != null){
			if(facetVersion == IJ2EEFacetConstants.JCA_10 ||
			   facetVersion == IJ2EEFacetConstants.JCA_15 ){
				return false;
			}
			return true;
		}
		
		facetVersion = getProjectFacetVersion(project, WEBFRAGMENT);
		if(facetVersion != null){
			return true;
		}
		
		return false;
	}
	
	/**
	 * Given a component returns whether the component has
	 * Java EE version greater than or equal to 5
	 * 
	 * @param component   - IVirtualComponent
	 * @param versionType - flag to specify whether to use the facet version
	 *                      or the DD XML file version
	 * @return true if the component is Java EE version 5 or greater, false otherwise
	 */
	public static boolean isJEEComponent(IVirtualComponent component, int versionType){
		if (component.isBinary()) {
			JavaEEQuickPeek qp = JavaEEBinaryComponentHelper.getJavaEEQuickPeek(component);
			int javaEEVersion = qp.getJavaEEVersion();
			return javaEEVersion >= J2EEConstants.JEE_5_0_ID;
		} 
		
		IProject project = component.getProject();
		if (versionType == FACET_VERSION) {
			IProjectFacetVersion facetVersion = getProjectFacetVersion(project, ENTERPRISE_APPLICATION);
			if(facetVersion != null){
				if(facetVersion == IJ2EEFacetConstants.ENTERPRISE_APPLICATION_12 ||
				   facetVersion == IJ2EEFacetConstants.ENTERPRISE_APPLICATION_13 ||
				   facetVersion == IJ2EEFacetConstants.ENTERPRISE_APPLICATION_14){
					return false;
				}
				return true;
			}
			
			facetVersion = getProjectFacetVersion(project, APPLICATION_CLIENT);
			if(facetVersion != null){
				if(facetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_12 ||
				   facetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_13 ||
				   facetVersion == IJ2EEFacetConstants.APPLICATION_CLIENT_14){
					return false;
				}
				return true;
			}
			
			facetVersion = getProjectFacetVersion(project, EJB);
			if(facetVersion != null){
				if(facetVersion == IJ2EEFacetConstants.EJB_11 ||
				   facetVersion == IJ2EEFacetConstants.EJB_20 ||
				   facetVersion == IJ2EEFacetConstants.EJB_21){
					return false;
				}
				return true;
			}
			
			facetVersion = getProjectFacetVersion(project, DYNAMIC_WEB);
			if(facetVersion != null){
				if(facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_22 ||
				   facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_23 ||
				   facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_24){
					return false;
				}
				return true;
			}
			
			facetVersion = getProjectFacetVersion(project, JCA);
			if(facetVersion != null){
				if(facetVersion == IJ2EEFacetConstants.JCA_10 ||
				   facetVersion == IJ2EEFacetConstants.JCA_15 ){
					return false;
				}
				return true;
			}
			
			facetVersion = getProjectFacetVersion(project, WEBFRAGMENT);
			if(facetVersion != null){
				return true;
			}
			return false;
		}
		String ddVersion = getJ2EEDDProjectVersion(project);
		int j2eeLevel = 0;
		if (isEARProject(project))
			j2eeLevel = J2EEVersionUtil.convertVersionStringToInt(ddVersion);
		else if (isDynamicWebProject(project))
			j2eeLevel = J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(ddVersion);
		else if (isEJBProject(project))
			j2eeLevel = J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(ddVersion);
		else if (isJCAProject(project))
			j2eeLevel = J2EEVersionUtil.convertConnectorVersionStringToJ2EEVersionID(ddVersion);
		else if (isApplicationClientProject(project))
			j2eeLevel = J2EEVersionUtil.convertAppClientVersionStringToJ2EEVersionID(ddVersion);
		else if (isWebFragmentProject(project))
			j2eeLevel = J2EEVersionUtil.convertWebFragmentVersionStringToJ2EEVersionID(ddVersion);
		return j2eeLevel >= J2EEVersionConstants.JEE_5_0_ID;
	}
	
	/**
	 * Given a component returns whether the component has Java EE version less than 5
	 * 
	 * @param component
	 * @return true if the component is less then Java EE version 5, false otherwise
	 */
	public static boolean isLegacyJ2EEComponent(IVirtualComponent component){
		return !isJEEComponent(component);
	}
	/**
	 * This checks the component passed if it is any of the "blessed" component types
	 * 
	 * @param component
	 * @return true if the component is any of the JavaEE types.
	 */
	public static boolean usesJavaEEComponent(IVirtualComponent component){
		
		if (component != null && (component instanceof J2EEModuleVirtualComponent 
				|| component instanceof EARVirtualComponent
				|| component instanceof J2EEModuleVirtualArchiveComponent))
			return true;
		return false;
	}
	
	public static String getComponentURI(IVirtualComponent comp) {
		String name = null;
		//First find URI contained in EAR
		IProject[] earProjects = EarUtilities.getReferencingEARProjects(comp.getProject());
		if (earProjects.length > 0) {
			IModelProvider provider = ModelProviderManager.getModelProvider(earProjects[0]);
			if (provider instanceof IEARModelProvider)	{
				name = ((IEARModelProvider)provider).getModuleURI(comp);
			}
		}
		//If not found, then return the default name from the ModuleCore API
		if( name == null || name == "" ){ //$NON-NLS-1$ 
			return VirtualReferenceUtilities.INSTANCE.getDefaultProjectArchiveName(comp);
		}
		return name;
	}
	
}
