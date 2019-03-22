/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.ServerUtil;

/**
 * IDataModel object property stored under the property name 
 * { @link ProjectRefactoringDataModelProvider#PROJECT_METADATA}.
 * 
 *  Note: this class is not multi-thread safe.
 */
public class ProjectRefactorMetadata {
	
	public static int NON_CACHING = 0;
	public static int REF_CACHING = 1;
	public static int REFERER_CACHING = 2;
	
	private int _virtualCompCaching = NON_CACHING;
	private final IProject _project;
	private Map<IModule, IServer[]> serverMap;
	private IVirtualComponent virtualComp = null;
	private final List dependentMetadata = new ArrayList();
	private boolean javaNature;
	private boolean moduleCoreNature;
	private IModule[] modules;
	private Set facets = new HashSet();
	private final Map earToModuleURI = new HashMap();
	
	public ProjectRefactorMetadata(final IProject project) {
		_project = project;
	}
	
	public ProjectRefactorMetadata(final IProject project, final int virtualCompCaching) {
		this(project);
		_virtualCompCaching = virtualCompCaching;
	}
	
	public void computeMetadata() {
        computeMetadata(_project);
    }
    public void computeMetadata(final IProject oldProject) {
		try {
			javaNature = _project.hasNature("org.eclipse.jdt.core.javanature"); //$NON-NLS-1$
			moduleCoreNature = ModuleCoreNature.isFlexibleProject(_project);
			if (moduleCoreNature) {
				if (_virtualCompCaching == REF_CACHING) {
					virtualComp = new RefCachingVirtualComponent(ComponentCore.createComponent(_project));
				} else if (_virtualCompCaching == REFERER_CACHING) {
					virtualComp = new RefererCachingVirtualComponent(ComponentCore.createComponent(_project));
				} else {
					virtualComp = ComponentCore.createComponent(_project);			
				}
				final IFacetedProject facetedProject = ProjectFacetsManager.create(_project);
				modules = ServerUtil.getModules(_project);
                // XXX Due to resource change listener order uncertainty, the project associated with the
                // module may be either the new (correct) project or the old project so need to try both
                if ((modules == null || modules.length == 0 )&& !_project.equals(oldProject)) {
                    modules = ServerUtil.getModules(oldProject);
                }
                if( modules == null )
                	modules = new IModule[]{};
                
                if (facetedProject != null) {
                    facets = facetedProject.getProjectFacets();
                }
			}
		} catch (CoreException ce) {
			J2EEPlugin.logError(ce);
		} 
	}

	public void computeServers() {
		serverMap = new HashMap<IModule, IServer[]>();
		for( int i = 0; i < modules.length; i++ ) {
			serverMap.put(modules[i],ServerUtil.getServersByModule(modules[i], null));
		}
	}
	
	/**
	 * This will get the servers for the first module of the project
	 */
	@Deprecated
	public IServer[] getServers() {
		if( modules == null || modules.length == 0 )
			return new IServer[]{};
		return serverMap.get(modules[0]) == null ? new IServer[]{} : serverMap.get(modules[0]);
	}
	
	/**
	 * Get the list of servers this module is on
	 * @param module
	 * @return
	 */
	public IServer[] getServers(IModule module) {
		return serverMap.get(module) == null ? new IServer[]{} : serverMap.get(module);
	}
	
	public void computeDependentMetadata(final int virtualComponentCaching, final IProject[] dependentProjects) {
		// get all referencing projects and execute the appropriate update operation for each one
		ProjectRefactorMetadata metadata;
		for (int i = 0; i < dependentProjects.length; i++) {
			final IProject dependentProject = dependentProjects[i];
			if (dependentProject.exists() && dependentProject.isOpen()) {
				metadata = new ProjectRefactorMetadata(dependentProjects[i], virtualComponentCaching);
				metadata.computeMetadata();
				dependentMetadata.add(metadata);
				if (metadata.isEAR() && virtualComp.getComponent() != null) {
					final String uri = getModuleURI(dependentProject, virtualComp);
					if (uri != null) {
						earToModuleURI.put(dependentProject.getName(), uri);
					}
				}
			}
		}
	}
	
	/**
	 * If this project is associated with the specified EAR project, retrieves the module URI for the dependency or null if
	 * it could not be retrieved.
	 * @param earName Name of the EAR project.
	 * @return Module URI for the project in the context of that EAR.
	 */
	public String getModuleURI(final String earName) {
		return (String) earToModuleURI.get(earName);
	}
	
	private String getModuleURI(final IProject earProject, final IVirtualComponent comp) {
		IEARModelProvider earModelProvider = null;
		earModelProvider = (IEARModelProvider) ModelProviderManager.getModelProvider(earProject);
		if (earModelProvider != null) {
			return earModelProvider.getModuleURI(comp); 
		}		
		return null;
	}
	
	/**
	 * Retrieves the IProject that represents the referenced project.
	 */
	public IProject getProject() {
		return _project;
	}
	
	/**
	 * Retrieves the IModule for the project, or null if no IModule representation
	 * exists.
	 */
	@Deprecated
	public IModule getModule() {
		return modules == null || modules.length == 0 ? null : modules[0];
	}
	
	/**
	 * Get all modules in this project
	 * @return
	 */
	public IModule[] getModules() {
		return modules;
	}
	
	/**
	 * Retrieves the project name.
	 */
	public String getProjectName() {
		return _project.getName();
	}
	
	/**
	 * Retrieves all ProjectRefactorMetadata for dependent projects.
	 * Will be empty if not computed.
	 */
	public ProjectRefactorMetadata[] getDependentMetadata() {
		return (ProjectRefactorMetadata[]) dependentMetadata.toArray(new ProjectRefactorMetadata[dependentMetadata.size()]);
	}
	
	/**
	 * Returns the IVirtualComponent for the project.
	 */
	public IVirtualComponent getVirtualComponent() {
		return virtualComp;
	}
	
	public boolean hasJavaNature() { 
		return javaNature;
	}
	
	public boolean hasModuleCoreNature() {
		return moduleCoreNature;
	}
	
	public Set getProjectFacets() {
		return facets;
	}
	
	public boolean hasFacet(final IProjectFacet facet) {
		 for(Iterator itr = facets.iterator(); itr.hasNext();) {
             final IProjectFacetVersion fv  = (IProjectFacetVersion) itr.next();
             
             if(fv.getProjectFacet() == facet) {
                 return true;
             }
         }
         
         return false;
	}
	
	public boolean isEAR() {
		return hasFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE)); 
	}

	public boolean isEJB() {
		return hasFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE)); 
	}
	
	public boolean isWeb() {
		return hasFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE));
	}
	
	public boolean isAppClient() {
		return hasFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_APPCLIENT_MODULE)); 
	}
	
	public boolean isConnector() {
		return hasFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_CONNECTOR_MODULE));
	}
	
	public boolean isUtility() {
		return hasFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_UTILITY_MODULE));
	}
	
	public class CachingVirtualComponent implements IVirtualComponent {
		protected final IVirtualComponent _comp;
		protected boolean _caching = true;
		public CachingVirtualComponent(final IVirtualComponent comp) {
			_comp = comp;
		}
		@Override
		public void addReferences(IVirtualReference[] references) {
			_comp.addReferences(references);
		}
		@Override
		public void create(int updateFlags, IProgressMonitor aMonitor) throws CoreException {
			_comp.create(updateFlags, aMonitor);
		}
		@Override
		public boolean exists() {
			return _comp.exists();
		}
		@Override
		public IVirtualComponent getComponent() {
			if(_comp == null) return null;
			return _comp.getComponent();
		}
		@Override
		public Properties getMetaProperties() {
			return _comp.getMetaProperties();
		}
		@Override
		public IPath[] getMetaResources() {
			return _comp.getMetaResources();
		}
		@Override
		public String getName() {
			return _comp.getName();
		}
		@Override
		public String getDeployedName() {
			return _comp.getDeployedName();
		}
		@Override
		public IProject getProject() {
			return _comp.getProject();
		}
		@Override
		public IVirtualReference getReference(String aComponentName) {
			return _comp.getReference(aComponentName);
		}
		@Override
		public IVirtualReference[] getReferences() {
			return _comp.getReferences();
		}
		@Override
		public IVirtualReference[] getReferences(Map<String, Object> options) {
			return _comp.getReferences(options);
		}
		public void setCaching(boolean caching) {
			_caching = caching;
		}
		@Override
		public IVirtualComponent[] getReferencingComponents() {
			return _comp.getReferencingComponents();
		}
		@Override
		public IVirtualFolder getRootFolder() {
			return _comp.getRootFolder();
		}
		@Override
		public boolean isBinary() {
			return _comp.isBinary();
		}
		@Override
		public void setMetaProperties(Properties properties) {
			_comp.setMetaProperties(properties);
		}
		@Override
		public void setMetaProperty(String name, String value) {
			_comp.setMetaProperty(name, value);
		}
		@Override
		public void setMetaResources(IPath[] theMetaResourcePaths) {
			_comp.setMetaResources(theMetaResourcePaths);
		}
		@Override
		public void setReferences(IVirtualReference[] theReferences) {
			_comp.setReferences(theReferences);
		}
		@Override
		public Object getAdapter(Class adapter) {
			return _comp.getAdapter(adapter);
		}
		
		@Override
		public boolean equals(Object o) {
			return _comp.equals(o);
		}
		
		@Override
		public int hashCode() {
			return _comp.hashCode();
		}
		
		@Override
		public String toString() {
			return _comp.toString();
		}
		
		public void removeReference(IVirtualReference aReference) {
			((VirtualComponent)_comp).removeReference(aReference);
		}
	}
	
	public class RefCachingVirtualComponent extends CachingVirtualComponent {
		private IVirtualReference[] cachedRefs;
		public RefCachingVirtualComponent(final IVirtualComponent comp) {
			super(comp);
			if(comp != null){
				cachedRefs = ((VirtualComponent)comp).getAllReferences();
			}
		}

		@Override
		public IVirtualReference getReference(String aComponentName) {
			IVirtualReference[] refs = getReferences();
			for (int i = 0; i < refs.length; i++) {
				IVirtualReference reference = refs[i];
				if (reference == null || reference.getReferencedComponent() == null) {
					return null;
				}
				if (reference.getReferencedComponent().getName().equals(aComponentName))
					return reference;
			}
			return null;
		}
		@Override
		public IVirtualReference[] getReferences() {
			if (_caching) {
				return cachedRefs;
			}
			return super.getReferences();
		}
	}

	public class RefererCachingVirtualComponent extends CachingVirtualComponent {
		private IVirtualComponent[] cachedReferers;
		public RefererCachingVirtualComponent(final IVirtualComponent comp) {
			super(comp);
			if(comp!= null){
				cachedReferers = comp.getReferencingComponents();
			}
		}

		@Override
		public IVirtualComponent[] getReferencingComponents() {
			if (_caching) {
				return cachedReferers;
			}
			return _comp.getReferencingComponents();
		}
	}
}
