/*******************************************************************************
 * Copyright (c) 2003, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.common.internal.modulecore.AddClasspathFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibReferencesParticipant;
import org.eclipse.jst.common.internal.modulecore.AddMappedOutputFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.IgnoreJavaInSourceFolderParticipant;
import org.eclipse.jst.common.internal.modulecore.ReplaceManifestExportParticipant;
import org.eclipse.jst.common.internal.modulecore.SingleRootExportParticipant;
import org.eclipse.jst.common.internal.modulecore.SingleRootUtil;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyEnablement;
import org.eclipse.jst.j2ee.internal.common.exportmodel.JEEHeirarchyExportParticipant;
import org.eclipse.jst.j2ee.internal.common.exportmodel.JavaEESingleRootCallback;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.server.core.IApplicationClientModule;
import org.eclipse.jst.server.core.IConnectorModule;
import org.eclipse.jst.server.core.IEJBModule;
import org.eclipse.jst.server.core.IEnterpriseApplication;
import org.eclipse.jst.server.core.IUtilityModule;
import org.eclipse.jst.server.core.IWebFragmentModule;
import org.eclipse.jst.server.core.IWebModule;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.flat.IChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.web.internal.deployables.FlatComponentDeployable;

/**
 * J2EE module superclass.
 */
public class J2EEFlexProjDeployable extends FlatComponentDeployable implements 
				IEnterpriseApplication, IApplicationClientModule, 
				IConnectorModule, IEJBModule, IWebModule, IUtilityModule, IWebFragmentModule {
	 
	private Set<String> metaResources = null;
	
	/**
	 * Constructor for J2EEFlexProjDeployable.
	 * 
	 * @param project
	 * @param aComponent
	 */
	public J2EEFlexProjDeployable(IProject project, IVirtualComponent aComponent) {
		super(project, aComponent);
		declareMetadataResources();
	}
	
	/**
	 * Constructor for J2EEFlexProjDeployable.
	 * 
	 * @param project
	 */
	public J2EEFlexProjDeployable(IProject project) {
		super(project);
	}
	
	@Override
	public boolean shouldCache() {
		return true;
	}

	/**
	 * @see SingleRootUtil.isSingleRoot(IVirtualComponent component)
	 * @return <code>true</code> if this module has a single root structure, and
	 *    <code>false</code> otherwise
	 */
	@Override
	public boolean isSingleRootStructure() {
		return new SingleRootUtil(getComponent(), new JavaEESingleRootCallback()).isSingleRoot();
	}
	
	@Override
	protected IFlattenParticipant[] getParticipants() {
		List<IFlattenParticipant> participants = new ArrayList<IFlattenParticipant>();
		
		participants.add(new SingleRootExportParticipant(new JavaEESingleRootCallback()));
		participants.add(new JEEHeirarchyExportParticipant());
		participants.add(new AddClasspathLibReferencesParticipant());
		participants.add(new AddClasspathFoldersParticipant());
		participants.add(new AddMappedOutputFoldersParticipant());
		participants.add(new IgnoreJavaInSourceFolderParticipant());
		if (ClasspathDependencyEnablement.isAllowClasspathComponentDependency()) {
			participants.add(new ReplaceManifestExportParticipant(new Path(J2EEConstants.MANIFEST_URI)));
		}
		
		return participants.toArray(new IFlattenParticipant[participants.size()]);
	}
    
    @Override
	protected IModule gatherModuleReference(IVirtualComponent component, IChildModuleReference child ) {
    	if (!child.isBinary()) 
    		return super.gatherModuleReference(component, child);
    	return J2EEDeployableFactory.j2eeInstance().createChildModule(this, child);
    }
    
    @Override
	protected IModule filterModuleDelegates(IModule[] modules) {
    	for (int i = 0; i < modules.length; i++) {
			ModuleDelegate md = (ModuleDelegate)modules[i].loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
			if (md instanceof J2EEFlexProjDeployable) {
				return modules[i];
			}
		}
		return super.filterModuleDelegates(modules);
	}
    
	/*_________________________________
     * 
	 * Methods for specific J2EE / JEE Interfaces are below
	 *_________________________________
	 */
    public String getJNDIName(String ejbName) {
    	if (!JavaEEProjectUtilities.isEJBProject(component.getProject()))
    		return null;
		EjbModuleExtensionHelper modHelper = null;
		EJBJar jar = null;
		ArtifactEdit ejbEdit = null;
		try {
			ejbEdit = ComponentUtilities.getArtifactEditForRead(component);
			if (ejbEdit != null) {
				jar = (EJBJar) ejbEdit.getContentModelRoot();
				modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
				return modHelper == null ? null : modHelper.getJNDIName(jar, jar.getEnterpriseBeanNamed(ejbName));
			}
		} catch (Exception e) {
			J2EEPlugin.logError(e);
		} finally {
			if (ejbEdit != null)
				ejbEdit.dispose();
		}
		return null;
	}
    
    /**
     * This method returns the context root property from the deployable project's .component file
     */
    public String getContextRoot() {
		Properties props = component.getMetaProperties();
		if(props.containsKey(J2EEConstants.CONTEXTROOT))
			return props.getProperty(J2EEConstants.CONTEXTROOT);
	    return component.getName();
    }
    
    /**
     * This method is applicable for a web deployable.  The module passed in should either be null or
     * the EAR module the web deployable is contained in.  It will return the context root from the EAR
     * if it has one or return the .component value in the web project if it is standalone.
     * 
     * @param module
     * @return contextRoot String
     */
    public String getContextRoot(IModule earModule) {
    	IProject deployProject = component.getProject();
    	String contextRoot = null;
    	if (earModule == null)
    		return getContextRoot();
    	else if (JavaEEProjectUtilities.isEARProject(earModule.getProject()) && JavaEEProjectUtilities.isDynamicWebProject(deployProject)) {
    		EARArtifactEdit edit = null;
    		try {
    			edit = EARArtifactEdit.getEARArtifactEditForRead(earModule.getProject());
    			contextRoot = edit.getWebContextRoot(deployProject);
    		} finally {
    			if (edit!=null)
    				edit.dispose();
    		}
    	}
    	return contextRoot;
    }
    
    
	/**
	 * Returns the root folders for the resources in this module.
	 * 
	 * @return a possibly-empty array of resource folders
	 */
	public IContainer[] getResourceFolders() {
		IVirtualComponent vc = ComponentCore.createComponent(getProject());
		if (vc != null) {
			IVirtualFolder vFolder = vc.getRootFolder();
			if (vFolder != null)
				return vFolder.getUnderlyingFolders();
		}
		return new IContainer[]{};
	}
	
	/**
	 * Returns the root folders containing Java output in this module.
	 * 
	 * @return a possibly-empty array of Java output folders
	 * @deprecated
	 */
	public IContainer[] getJavaOutputFolders() {
		return getJavaOutputFolders(component);
	}
	
	/**
	 * @param component
	 * @deprecated
	 * @return
	 */
	public IContainer[] getJavaOutputFolders(IVirtualComponent component) {
		if (component == null)
			return new IContainer[0];
		List<IContainer> l = JavaLiteUtilities.getJavaOutputContainers(component);
		return l.toArray(new IContainer[l.size()]);
	}	
	
    /**
     * Returns the classpath as a list of absolute IPaths.
     * 
     * @deprecated
     * @return an array of paths
     */
    public IPath[] getClasspath() {
		List<IPath> paths = new ArrayList<IPath>();
        IJavaProject proj = JemProjectUtilities.getJavaProject(getProject());
        URL[] urls = JemProjectUtilities.getClasspathAsURLArray(proj);
		for (int i = 0; i < urls.length; i++) {
			URL url = urls[i];
			paths.add(Path.fromOSString(url.getPath()));
		}
        return paths.toArray(new IPath[paths.size()]);
    }
    
    /**
     * Retrieves the module members. The meta resources of project
     * are filtered.
    */

    @Override
    public IModuleResource[] members() throws CoreException {
    	List<IModuleResource> moduleResources = new ArrayList<IModuleResource>();
    	IModuleResource[] resources = super.members();
    	
    	for(IModuleResource res : resources)
    		if(!metaResources.contains(res.getName()))
    			moduleResources.add(res);
    	
    	return moduleResources.toArray(new IModuleResource[moduleResources.size()]);
    }
    
    /**
     * This method declares the .project and .settings paths as 
     * meta resources, so they can be filtered from the members.
     * 
    */
    
    private void declareMetadataResources() {
    	metaResources = new HashSet<String>();
    	metaResources.add(IJ2EEModuleConstants.META_PROJECT_PATH);
    }
}
