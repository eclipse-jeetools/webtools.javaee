/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001, 2005 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */
package org.eclipse.jst.j2ee.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class ModelProviderManager {
	
public static class ModelProviderKey { 
 		
		protected IProjectFacetVersion version;
		protected int priority;
		public ModelProviderKey() {
			super();
		}
		public boolean equals(Object aOther){
			if( this == aOther )
				return true;
			
			ModelProviderKey otherKey = (ModelProviderKey)aOther;
			if( version.equals( otherKey.version) )
				return true;
			return false;
		}
		
		public int hashCode() {
			return version.hashCode();
		}		
	}
private static final int DEFAULT_PRIORITY = 100;
	private static HashMap<ModelProviderKey, IModelProviderFactory> providers;

	/**
	 * 
	 * @param 
	 * @param 
	 * @return IModelProvider for the given project of the given version, NULL if no IModelProvider exists for project, version pair
	 */
	public static IModelProvider getModelProvider(IProject project, IProjectFacetVersion vers) {
		
		IModelProviderFactory factory = getProvider(vers);
		if(factory != null){
			return factory.create(project);
		}
		
		String errorMessage = J2EECommonMessages.getResourceString(
				J2EECommonMessages.ERR_NO_MODEL_PROVIDER_FOR_PROJECT, new Object[] {project, vers});
		Throwable error = new NullPointerException(errorMessage);
		
		J2EEPlugin.INSTANCE.getLogger().logError(error);
				
		return null;
	}

	/**
	 * 
	 * @param 
	 * @param 
	 * @return IModelProvider for the given component of the given version, NULL if no IModelProvider exists for virtual component/version pair
	 */
	public static IModelProvider getModelProvider(IVirtualComponent aModule, IProjectFacetVersion vers) {

		IModelProviderFactory factory = getProvider(vers);
		if(factory != null){
			return factory.create(aModule);
		}
		
		String errorMessage = J2EECommonMessages.getResourceString(
				J2EECommonMessages.ERR_NO_MODEL_PROVIDER_FOR_PROJECT, new Object[] {aModule, vers});
		Throwable error = new NullPointerException(errorMessage);
		
		J2EEPlugin.INSTANCE.getLogger().logError(error);
		
		return null;
	}

	/**
	 * Used to register an IModelProviderFactory against a facet version
	 * @param providerFactory - {@link IModelProviderFactory}
	 * @param v - {@link IProjectFacetVersion}
	 * @param priority - {@link String}- Used to allow multiple instances, the highest priority is chosen
	 */
	public static void registerProvider(IModelProviderFactory providerFactory,
			IProjectFacetVersion v, String priority) {

		int newPriority = (priority != null) ? Integer.parseInt(priority) : DEFAULT_PRIORITY;
		int currentPriority = getProviderPriority(v);
		if (newPriority <= currentPriority) {
			ModelProviderKey key = createProviderKey(v, newPriority);
			providers.put(key, providerFactory);
		}
	}

	private static IModelProviderFactory getProvider(IProjectFacetVersion v) {
		Set<ModelProviderKey> keys = getProviders().keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			ModelProviderKey key = (ModelProviderKey) iterator.next();
			if (key.version.equals(v))
				return providers.get(key);
		}
		return null;
	}
	private static int getProviderPriority(IProjectFacetVersion v) {
		Set<ModelProviderKey> keys = providers.keySet();
		for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
			ModelProviderKey key = (ModelProviderKey) iterator.next();
			if (key.version.equals(v))
				return key.priority;
		}
		return DEFAULT_PRIORITY;
	}

	private static J2EEModelProviderRegistry registry;


	private static void initProviders() {
		providers = new HashMap();
		registry = J2EEModelProviderRegistry.getInstance();
		
	}
	private static ModelProviderKey createProviderKey(IProjectFacetVersion fv, int priority) {
		ModelProviderKey key =  new ModelProviderKey();
		key.priority = priority;
		key.version = fv;
		return key;
	}

	public static IModelProvider getModelProvider(IProject proj) {
		IProjectFacetVersion facetVersion = getDefaultFacet(proj);
		return getModelProvider(proj, facetVersion);
		
	}

	public static IModelProvider getModelProvider(IVirtualComponent aModule) {
		IProjectFacetVersion facetVersion = getDefaultFacet(aModule);
		return getModelProvider(aModule, facetVersion);		
	}

	private static IProjectFacetVersion getDefaultFacet(IProject proj) {
		String type = J2EEProjectUtilities.getJ2EEProjectType(proj);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(type);
		IFacetedProject fp = null;
		try {
			fp = ProjectFacetsManager.create(proj);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fp != null && facet != null) {
			return fp.getInstalledVersion(facet);
		}
		return null;
			
	}

	private static IProjectFacetVersion getDefaultFacet(IVirtualComponent aModule) {
		String type = J2EEProjectUtilities.getJ2EEComponentType(aModule);
		IProjectFacet facet = ProjectFacetsManager.getProjectFacet(type);
		IFacetedProject fp = null;
		try {
			if (aModule.isBinary())
			{
				
			}
			else
			{
				fp = ProjectFacetsManager.create(aModule.getProject());
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fp != null && facet != null) {
			return fp.getInstalledVersion(facet);
		}
		return null;			
	}

	private static HashMap<ModelProviderKey, IModelProviderFactory> getProviders() {
		if (registry == null)
			initProviders();
		return providers;
	}

}
