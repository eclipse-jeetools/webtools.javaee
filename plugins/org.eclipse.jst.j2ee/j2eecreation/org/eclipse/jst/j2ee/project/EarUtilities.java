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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class EarUtilities extends JavaEEProjectUtilities {

	public EarUtilities() {
	}

	/**
	 * This method will return the an IVirtualComponent for the given module name. The method take
	 * either moduleName or moduleName + ".module_extension" (module_extension = ".jar" || ".war" ||
	 * ".rar") which allows users to get a IVirtualComponent for a given entry in an application.xml
	 * 
	 * @return - a IVirtualComponent for module name
	 */
	public static IVirtualComponent getModule(IVirtualComponent earComponent, String moduleName) {
		String innerModuleName = moduleName;
		if (innerModuleName == null)
			return null;
		if (innerModuleName.endsWith(IJ2EEModuleConstants.JAR_EXT) || innerModuleName.endsWith(IJ2EEModuleConstants.WAR_EXT) || innerModuleName.endsWith(IJ2EEModuleConstants.RAR_EXT))
			innerModuleName = innerModuleName.substring(0, (innerModuleName.length() - IJ2EEModuleConstants.JAR_EXT.length()));
		IVirtualReference[] references = getComponentReferences(earComponent);
		for (int i = 0; i < references.length; i++) {
			IVirtualComponent component = references[i].getReferencedComponent();
			if (component.getName().equals(innerModuleName)) {
				return component;
			}
		}
		return null;
	}

	/**
	 * This method will return the list of IVirtualReferences for the J2EE module components
	 * contained in this EAR application.
	 * 
	 * @return - an array of IVirtualReferences for J2EE modules in the EAR
	 */
	public static IVirtualReference[] getJ2EEModuleReferences(IVirtualComponent earComponent) {
		List j2eeTypes = new ArrayList();
		j2eeTypes.add(IJ2EEFacetConstants.APPLICATION_CLIENT);
		j2eeTypes.add(IJ2EEFacetConstants.JCA);
		j2eeTypes.add(IJ2EEFacetConstants.EJB);
		j2eeTypes.add(IJ2EEFacetConstants.DYNAMIC_WEB);
		IVirtualReference[] refs = getComponentReferences(earComponent, j2eeTypes);
		String earLib = getEARLibDir(earComponent);
		if(earLib == null)
			return refs;
		ArrayList <IVirtualReference> moduleRefs = new ArrayList <IVirtualReference>();
		Path earLibPath = new Path(earLib);
		for(int i = 0; i < refs.length; i++) {
			if(!refs[i].getRuntimePath().equals(earLibPath)) {
				moduleRefs.add(refs[i]);
			}
		}
		return moduleRefs.toArray(new IVirtualReference[moduleRefs.size()]);
	}

	/**
	 * This method will return the list of IVirtualReferences for all of the components contained in
	 * an EAR application.
	 * 
	 * @return - an array of IVirtualReferences for components in the EAR
	 */
	public static IVirtualReference[] getComponentReferences(IVirtualComponent earComponent) {
		return getComponentReferences(earComponent, Collections.EMPTY_LIST);
	}

	/**
	 * This method will return the IVirtualReference to the component of the given name
	 * 
	 * @return - IVirtualReference or null if not found
	 */
	public static IVirtualReference getComponentReference(IVirtualComponent earComponent, String componentName) {
		IVirtualReference[] refs = getComponentReferences(earComponent, Collections.EMPTY_LIST);
		for (int i = 0; i < refs.length; i++) {
			IVirtualReference reference = refs[i];
			if (reference.getReferencedComponent().getName().equals(componentName))
				return reference;

		}
		return null;
	}

	private static IVirtualReference[] getComponentReferences(IVirtualComponent earComponent, List componentTypes) {
		List components = getComponentReferencesAsList(earComponent, componentTypes);
		if(components.size() > 0)
			return (IVirtualReference[]) components.toArray(new IVirtualReference[components.size()]);
		return NO_REFERENCES;
	} 
	
	private static List getComponentReferencesAsList(IVirtualComponent earComponent, List componentTypes) {
		return getComponentReferencesAsList(earComponent, componentTypes, false);
	}

	/**
	 * 
	 * @param componentTypes
	 * @return A List of {@link IVirtualReference}s.
	 * 
	 * This method is copied from EARArtifactEdit.  Any bug fixes should occur in both locations.
	 */
	private static List getComponentReferencesAsList(IVirtualComponent earComponent, List componentTypes, boolean expandLibraries) {
		List components = new ArrayList();
		if (earComponent != null && isEARProject(earComponent.getProject())) {
			Map<String, Object> options = new HashMap<String, Object>();
			if (expandLibraries) {
				options.put(J2EEModuleVirtualComponent.GET_EXPANDED_LIB_REFS, Boolean.TRUE);
			}
			IVirtualReference[] refComponents = earComponent.getReferences(options);
	
			for (int i = 0; i < refComponents.length; i++) {
				IVirtualReference reference = refComponents[i];
				IVirtualComponent module = reference.getReferencedComponent();
				if (module == null)
					continue;

				// if component types passed in is null then return all components
				if (componentTypes == null || componentTypes.size() == 0)
					components.add(reference);
				else {
					if (componentTypes.contains(getJ2EEComponentType(module))) {
						components.add(reference);
					}
				}
			}
		}
		return components;
	}
	
	/**
	 * Checks if the uri mapping already exists.
	 * 
	 * @param String
	 *            currentURI - The current uri of the module.
	 * @return boolean
	 */
	public static boolean uriExists(String currentURI, IProject earProject) {
		StructureEdit edit = null;
		try {
			edit = StructureEdit.getStructureEditForRead(earProject);
			if (edit!=null && edit.getComponent()!=null) {
				List referencedComps = edit.getComponent().getReferencedComponents();
				for (int i=0; i<referencedComps.size(); i++) {
					ReferencedComponent ref = (ReferencedComponent) referencedComps.get(i);
					Object module = ref.getDependentObject();
					if (module!=null && module instanceof Module) {
						String existingURI = ((Module)module).getUri();
						if (existingURI!=null && existingURI.equals(currentURI))
							return true;
					}
				}
			}
		} finally {
			if (edit != null)
				edit.dispose();
		}
		return false;
	}

	/**
	 * Returns all referencing EAR projects.
	 * @param project Project to check. If null or an EAR, returns a zero length array.
	 * @return Array of referencing EAR projects.
	 */
	public static IProject[] getReferencingEARProjects(final IProject project) {
		// TODO - should this go here, or in JavaEEProjectUtilities?
		if(project != null && isEARProject(project)){
			return new IProject[] {project};
		}
		
		List result = new ArrayList();
		IVirtualComponent component = ComponentCore.createComponent(project);
		if (component != null) {
			IVirtualComponent[] refComponents = component.getReferencingComponents();
			for (int i = 0; i < refComponents.length; i++) {
				if (isEARProject(refComponents[i].getProject()))
					result.add(refComponents[i].getProject());
			}
		}
		
		IProject [] earProjects = (IProject[]) result.toArray(new IProject[result.size()]);
		//sort the list so it is consistent
		Arrays.sort(earProjects, new Comparator<IProject>() {
			public int compare(IProject p0, IProject p1) {
				return p0.getName().compareTo(p1.getName());
			}
		});

		return earProjects;
	}

	public static boolean isStandaloneProject(IProject project) {
		return getReferencingEARProjects(project).length == 0;
	}

	public static IVirtualReference[] getUtilityModuleReferences(IVirtualComponent earComponent) {
		return getUtilityModuleReferences(earComponent, false);
	}

	/**
	 * This method will return the list of IVirtualReferences for all of the utility modules
	 * contained in the EAR application
	 * 
	 * @return - an array of IVirtualReferences for utility modules in the EAR
	 */
	public static IVirtualReference[] getUtilityModuleReferences(IVirtualComponent earComponent, boolean expandLibraries) {
		if (earComponent != null && isEARProject(earComponent.getProject())) {
			List explicitUtilityReferences = 
				getComponentReferencesAsList(earComponent, Collections.singletonList(IJ2EEFacetConstants.UTILITY), expandLibraries);
			
			// fetch other Utility Jars attached to the EAR project 
			List implicitUtilityReferenceTypes =
				Arrays.asList(new String[] {  IModuleConstants.JST_APPCLIENT_MODULE, 
											   IModuleConstants.JST_WEB_MODULE,  
											   IModuleConstants.JST_EJB_MODULE }); 
	
			List implicitUtilityReferences = 
				getComponentReferencesAsList(earComponent, implicitUtilityReferenceTypes, expandLibraries);
			
			IEARModelProvider earModel = (IEARModelProvider)ModelProviderManager.getModelProvider(earComponent.getProject());
			ICommonApplication application = (ICommonApplication)earModel.getModelObject();
			Object module = null;
			IVirtualReference reference = null;
			for (Iterator referenceItr = implicitUtilityReferences.iterator(); referenceItr.hasNext(); ) {
				module = null;
				reference = (IVirtualReference) referenceItr.next();
				if (application instanceof org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl)
				{
					module = ((org.eclipse.jst.javaee.application.internal.impl.ApplicationImpl)application).getFirstModule(reference.getArchiveName());
				}
				else if (application instanceof org.eclipse.jst.j2ee.application.internal.impl.ApplicationImpl)
				{
					module = ((org.eclipse.jst.j2ee.application.internal.impl.ApplicationImpl)application).getFirstModule(reference.getArchiveName()); 
				}
				if(module != null) 
					referenceItr.remove(); 
			}
			
			List allUtilityModuleReferences = new ArrayList();
			allUtilityModuleReferences.addAll(explicitUtilityReferences);
			allUtilityModuleReferences.addAll(implicitUtilityReferences);
			
			if(allUtilityModuleReferences.size() > 0)
				return (IVirtualReference[]) allUtilityModuleReferences.toArray(new IVirtualReference[allUtilityModuleReferences.size()]);
		}
		return NO_REFERENCES;
		
	}
	
	/**
	 * Returns a List of ProjectFacetVersions for a given module type that are supported by a given ear version
	 * @param earProjectFacetVersion - the ProjectFacetVersion for the ENTERPRISE_APPLICATION that the module will be added to
	 * @param moduleProjectFacet - the module type whose appropriate ProjectFacetVersions are desired
	 * @return List of compatible ProjectFacetVersions of the moduleProjectFacet type
	 */
	public static List<IProjectFacetVersion> getSupportedFacets(IProjectFacetVersion earProjectFacetVersion, IProjectFacet moduleProjectFacet)
	{
		List<IProjectFacetVersion> retVal = new ArrayList<IProjectFacetVersion>();

		if (earProjectFacetVersion != null && ENTERPRISE_APPLICATION.equals(earProjectFacetVersion.getProjectFacet().getId()))
		{
			String moduleProjectFacetId = moduleProjectFacet.getId();
			int earVersion = J2EEVersionUtil.convertVersionStringToInt(earProjectFacetVersion.getVersionString());

			if (DYNAMIC_WEB.equals(moduleProjectFacetId))
			{
				switch (earVersion)
				{
				case J2EEVersionConstants.VERSION_6_0:
					retVal.add(DYNAMIC_WEB_30);
				case J2EEVersionConstants.VERSION_5_0:
					retVal.add(DYNAMIC_WEB_25);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_4:
					retVal.add(DYNAMIC_WEB_24);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_3:
					retVal.add(DYNAMIC_WEB_23);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_2:
					//$FALL-THROUGH$
					retVal.add(DYNAMIC_WEB_22);
				}
			}
			else if (EJB.equals(moduleProjectFacetId))
			{
				switch (earVersion)
				{
				case J2EEVersionConstants.VERSION_6_0:
					retVal.add(EJB_31);
				case J2EEVersionConstants.VERSION_5_0:
					retVal.add(EJB_30);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_4:
					retVal.add(EJB_21);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_3:
					retVal.add(EJB_20);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_2:
					retVal.add(EJB_11);
				}
			}
			else if (JCA.equals(moduleProjectFacetId))
			{
				switch (earVersion)
				{
				case J2EEVersionConstants.VERSION_6_0:
					retVal.add(JCA_16);
				case J2EEVersionConstants.VERSION_5_0:
				case J2EEVersionConstants.VERSION_1_4:
					retVal.add(JCA_15);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_3:
					retVal.add(JCA_10);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_2:
					// there is no JCA in EAR 1.2
				}
			}
			else if (APPLICATION_CLIENT.equals(moduleProjectFacetId))
			{
				switch (earVersion)
				{
				case J2EEVersionConstants.VERSION_6_0:
					retVal.add(APPLICATION_CLIENT_60);
				case J2EEVersionConstants.VERSION_5_0:
					retVal.add(APPLICATION_CLIENT_50);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_4:
					retVal.add(APPLICATION_CLIENT_14);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_3:
					retVal.add(APPLICATION_CLIENT_13);
					//$FALL-THROUGH$
				case J2EEVersionConstants.VERSION_1_2:
					retVal.add(APPLICATION_CLIENT_12);
				}
			}
			else if (UTILITY.equals(moduleProjectFacetId))
			{
				retVal.add(UTILITY_FACET_10);
			}
			else
			{
				// invalid module type
				throw new IllegalArgumentException("The moduleProjectFacet parameter must be a valid Java EE module type."); //$NON-NLS-1$
			}
		}
		else
		{
			// invalid EAR facet
			if (earProjectFacetVersion == null)
				throw new IllegalArgumentException("The earProjectFacetVersion parameter cannot be null"); //$NON-NLS-1$
			throw new IllegalArgumentException("The earProjectFacetVersion parameter must be an ENTERPRISE_APPLICATION facet."); //$NON-NLS-1$
		}

		return retVal;
	}

	/**
	 * Get the library directory from an EAR virtual component
	 * 
	 * @param earComponent
	 *            the EAR virtual component
	 * 
	 * @return a runtime representation of the library directory path or null if
	 *         the EAR's version is lower than 5
	 */
	public static String getEARLibDir(IVirtualComponent earComponent) {
		// check if the EAR component's version is 5 or greater
		IProject earProject = earComponent.getProject();
		if (!JavaEEProjectUtilities.isJEEComponent(earComponent, JavaEEProjectUtilities.DD_VERSION)
				|| !JavaEEProjectUtilities.isJEEComponent(earComponent, JavaEEProjectUtilities.FACET_VERSION)) {
			return null;
		}
		
		// default lib dir if there is no deployment descriptor
		// or if the deployment descriptor does not override
		String libDir = J2EEConstants.EAR_DEFAULT_LIB_DIR;
		
		// retrieve the model provider
		IModelProvider modelProvider = ModelProviderManager.getModelProvider(earProject);
		if (modelProvider == null){
			return libDir;
		}
		
		// retrieve the EAR's deployment descriptor model object 
		Object o = modelProvider.getModelObject(new Path(J2EEConstants.APPLICATION_DD_URI));
		Application app = null;
		// check to see the type - if this is in a project with a j2ee 1.4 or older facet, lib dir is unavailable.
		if (o instanceof Application){
			app = (Application) o;
		}
		if (app == null){
			return libDir;
		}
		
		// retrieve the library directory from the model
		String ddLibDir = app.getLibraryDirectory();
		if (ddLibDir != null) {
			libDir = ddLibDir;
		}
		
		return libDir;
	}
	
}
