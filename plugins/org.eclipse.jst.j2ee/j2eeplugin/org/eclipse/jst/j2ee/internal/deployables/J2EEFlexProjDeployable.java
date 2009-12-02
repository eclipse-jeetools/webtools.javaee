/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveLoadAdapter;
import org.eclipse.jst.j2ee.internal.archive.EARComponentArchiveLoadAdapter;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.archive.WebComponentArchiveLoadAdapter;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities.JavaEEWrappingLoadAdapter;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentLoadAdapter;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.SingleRootUtil;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.jst.server.core.IApplicationClientModule;
import org.eclipse.jst.server.core.IConnectorModule;
import org.eclipse.jst.server.core.IEJBModule;
import org.eclipse.jst.server.core.IEnterpriseApplication;
import org.eclipse.jst.server.core.IWebModule;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.model.IModuleFile;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wst.server.core.util.ModuleFile;
import org.eclipse.wst.server.core.util.ModuleFolder;
import org.eclipse.wst.web.internal.deployables.ComponentDeployable;
/**
 * J2EE module superclass.
 */
public class J2EEFlexProjDeployable extends ComponentDeployable implements 
				IEnterpriseApplication, IApplicationClientModule, 
				IConnectorModule, IEJBModule, IWebModule {
	
	protected static final IPath MANIFEST_PATH = new Path(J2EEConstants.MANIFEST_URI);
	protected static IPath WEBLIB = new Path(J2EEConstants.WEB_INF_LIB);
	
	protected IContainer[] cachedOutputContainers;
	protected ComponentArchiveLoadAdapter cLoadAdapter;
	protected Map<IPath, ModuleFolder> moduleFolderCache = new HashMap<IPath, ModuleFolder>();
	protected SingleRootUtil singleUtil;

	/**
	 * Constructor for J2EEFlexProjDeployable.
	 * 
	 * @param project
	 * @param aComponent
	 */
	public J2EEFlexProjDeployable(IProject project, IVirtualComponent aComponent) {
		super(project, aComponent);
	}
	

	/**
	 * Constructor for J2EEFlexProjDeployable.
	 * 
	 * @param project
	 */
	public J2EEFlexProjDeployable(IProject project) {
		super(project);
	}

	/**
	 * Returns the root folders containing Java output in this module.
	 * 
	 * @return a possibly-empty array of Java output folders
	 */
	public IContainer[] getJavaOutputFolders() {
		if (cachedOutputContainers == null)
			cachedOutputContainers = getJavaOutputFolders(component);
		return cachedOutputContainers;
	}
	
	public IContainer[] getJavaOutputFolders(IVirtualComponent component) {
		if (component == null)
			return new IContainer[0];
		List<IContainer> l = JavaLiteUtilities.getJavaOutputContainers(component);
		return l.toArray(new IContainer[l.size()]);
	}	
	
	@Override
	public IModuleResource[] members() throws CoreException {
		members.clear();
		moduleFolderCache.clear();
		IArchive archive = null;
		try {
			archive = JavaEEArchiveUtilities.INSTANCE.openArchive(component);
			IArchiveLoadAdapter loadAdapter = archive.getLoadAdapter();
			if (loadAdapter instanceof ComponentArchiveLoadAdapter) {
				cLoadAdapter = (ComponentArchiveLoadAdapter)loadAdapter;
				cLoadAdapter.setExportSource(false);
				//if a single-root structure is found the load adapter will optimize members
				cLoadAdapter.setOptimize(true);
				
				List<IArchiveResource> archiveResources = archive.getArchiveResources();
				for (Iterator<IArchiveResource> iterator = archiveResources.iterator(); iterator.hasNext();) {
					IArchiveResource archiveResource = iterator.next();
					IPath deployPath = archiveResource.getPath();
					if (archiveResource.getType() == IArchiveResource.FILE_TYPE) {
						IResource resource = cLoadAdapter.getWorkbenchResources(deployPath);
						//check if resource is a workbench resource
						if (resource != null) {
							IFile file = (IFile) resource;
							IModuleFile moduleFile = new ModuleFile(file, file.getName(), deployPath.removeLastSegments(1));
							if (deployPath.equals(MANIFEST_PATH) && cLoadAdapter.shouldUpdateManifest(archiveResource)) {
								moduleFile = replaceManifestFile(archiveResource, moduleFile);
							}
							gatherFileMembers(deployPath, moduleFile);
						}
						//handle external WEB-INF/lib jars as well as classpath component dependencies
						else {
							gatherExternalFiles(cLoadAdapter.getExternalFiles(deployPath), deployPath);
						}
					}
					else if (archiveResource.getType() == IArchiveResource.ARCHIVE_TYPE) {
						IArchiveLoadAdapter aLoadAdapter = ((IArchive)archiveResource).getLoadAdapter();
						// If the component module is an EAR we will return only those archives which are not 
						// binary J2EE modules in the EAR DD.  These J2EE modules will be returned by getChildModules()
						if (cLoadAdapter instanceof EARComponentArchiveLoadAdapter) {
							if (isModule((IArchive)archiveResource)) {
								continue;
							}
						}
						else if (cLoadAdapter instanceof WebComponentArchiveLoadAdapter) {
							//check if jar in the WEB-INF/lib folder is also mapped as project
							if (WEBLIB.equals(deployPath.removeLastSegments(1))) {
								addLibModule(deployPath);
								continue;
							}
						}
						//handle binary utils
						addBinaryMembers(aLoadAdapter, deployPath);
					}
					else if (archiveResource.getType() == IArchiveResource.DIRECTORY_TYPE) {
						gatherFolderMembers(deployPath);
					}
				}
			}
			else if (loadAdapter instanceof JavaEEWrappingLoadAdapter) {
				//handle binary modules
				addBinaryMembers(((JavaEEWrappingLoadAdapter) loadAdapter).getWrappedLoadAdatper(), null);
			}
		} catch (ArchiveOpenFailureException e) {
			J2EEPlugin.logError(e);	
		} finally {
			if (null != archive) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
		IModuleResource[] mr = new IModuleResource[members.size()];
		members.toArray(mr);
		return mr;
	}

	private void addLibModule(IPath deployPath) {
		WebComponentArchiveLoadAdapter wLoadAdapter = (WebComponentArchiveLoadAdapter) cLoadAdapter;
		IResource resource = wLoadAdapter.getWorkbenchLibJars(deployPath);
		if (resource != null) {
			IFile file = (IFile) resource;
			IModuleFile moduleFile = new ModuleFile(file, file.getName(), deployPath.removeLastSegments(1));
			gatherFileMembers(deployPath, moduleFile);
		}
	}

	private void addBinaryMembers(IArchiveLoadAdapter lAdapter, IPath path) {
		IPath deployPath = null;
		
		if (lAdapter instanceof JavaEEBinaryComponentLoadAdapter) {
			VirtualArchiveComponent vComponent = ((JavaEEBinaryComponentLoadAdapter) lAdapter).getArchiveComponent();
			if (path == null) {
				deployPath = ((J2EEModuleVirtualArchiveComponent)vComponent).getDeploymentPath().makeRelative();
			}
			else {
				deployPath = path;
			}
			gatherBinaryMembers(vComponent, deployPath);
		}
	}
	
	private void gatherFileMembers(IPath deployPath, IModuleFile moduleFile) {
		if (deployPath.segmentCount() > 1) {
			ModuleFolder moduleFolder = gatherFolderMembers(deployPath.removeLastSegments(1));
			addMembersToModuleFolder(moduleFolder, new IModuleResource[] {moduleFile});
		}
		else {
			members.add(moduleFile);
		}
	}
	
	private ModuleFolder gatherFolderMembers(IPath path) {
		ModuleFolder existingEntry = moduleFolderCache.get(path);
		if (existingEntry != null) {
			return existingEntry;
		}
		IResource resource = cLoadAdapter.getWorkbenchResources(path);
		if (resource == null || !(resource instanceof IContainer)) 
			return null;
		
		IContainer modContainer = (IContainer) resource;
		ModuleFolder newEntry = new ModuleFolder(modContainer, path.lastSegment(), path.removeLastSegments(1));
		moduleFolderCache.put(path, newEntry);
		
	    if (path.segmentCount() > 1) {
	    	ModuleFolder parent = gatherFolderMembers(path.removeLastSegments(1));
	    	addMembersToModuleFolder(parent, new IModuleResource[] {newEntry});
	    }
	    else {
	    	members.add(newEntry);
	    }
		return newEntry;
	}
	
	private void gatherBinaryMembers(VirtualArchiveComponent virtualComp, IPath deployPath) {
		IModuleFile moduleFile = null;
		final IPath archivePath = virtualComp.getWorkspaceRelativePath();
		if (archivePath != null) { //in Workspace
			IFile utilFile = ResourcesPlugin.getWorkspace().getRoot().getFile(archivePath);
			moduleFile = new ModuleFile(utilFile, utilFile.getName(), deployPath.removeLastSegments(1));
		} 
		else { //outside of workspace
			File extFile = virtualComp.getUnderlyingDiskFile();
			moduleFile = new ModuleFile(extFile, extFile.getName(), deployPath.removeLastSegments(1));
		}
		gatherFileMembers(deployPath, moduleFile);
	}

	private void gatherExternalFiles(File extFile, IPath deployPath) {
		if (extFile == null) 
			return;
			
		IModuleFile moduleFile = new ModuleFile(extFile, extFile.getName(), deployPath.removeLastSegments(1));
		gatherFileMembers(deployPath, moduleFile);
	}

	protected IModuleFile replaceManifestFile(IArchiveResource archiveResource, IModuleFile moduleFile) {
		final IFile file = (IFile) moduleFile.getAdapter(IFile.class);
		// if the MANIFEST.MF is being requested and we have classpath component dependencies, 
		// dynamically generate a customized MANIFEST.MF and return that 
		final IProject project = file.getProject();
		final IPath workingLocation = project.getWorkingLocation(J2EEPlugin.PLUGIN_ID);
		// create path to temp MANIFEST.MF
		final IPath tempManifestPath = workingLocation.append(MANIFEST_PATH);
		final File tempFile = tempManifestPath.toFile();
		if (!tempFile.exists()) {
			// create parent dirs for temp MANIFEST.MF
			final File parent = tempFile.getParentFile();
			if (!parent.exists()) {
				if (!parent.mkdirs()) {
					return moduleFile;
				}
			}
		}
		try {	
			InputStream in = cLoadAdapter.getInputStream(archiveResource);
			OutputStream out = new FileOutputStream(tempFile);
			ArchiveUtil.copy(in, out);
		} catch (FileNotFoundException e) {
			return moduleFile;
		} catch (IOException e) {
			return moduleFile;
		}
		return new ModuleFile(tempFile, tempFile.getName(), moduleFile.getModuleRelativePath());
	}
	
	public boolean isModule(IArchive archiveResource) {
		JavaEEQuickPeek qp = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archiveResource);
		switch (qp.getType()) {
			case JavaEEQuickPeek.APPLICATION_CLIENT_TYPE:
			case JavaEEQuickPeek.EJB_TYPE:
			case JavaEEQuickPeek.WEB_TYPE:
			case JavaEEQuickPeek.CONNECTOR_TYPE:
				return true;
			default:
				break;
		}
		return false;
	}
	
	/**
	 * Returns <code>true</code> if this module has a simple structure based on a
	 * single root folder, and <code>false</code> otherwise.
	 * <p>
	 * In a single root structure, all files that are contained within the root folder
	 * are part of the module, and are already in the correct module structure. No
	 * module resources exist outside of this single folder.
	 * 
	 * For J2EE, this method will check if the project is already in J2EE spec standard output form.  
	 * The project must follow certain rules, but in general, the project's content roots must be source folders
	 * and the output folder must also be the the content root folder.
	 * </p>
	 * 
	 * @return <code>true</code> if this module has a single root structure, and
	 *    <code>false</code> otherwise
	 */
	@Override
	public boolean isSingleRootStructure() {
		return getSingleUtil().isSingleRoot();
	}
	
	private SingleRootUtil getSingleUtil() {
		if (singleUtil == null) {
			singleUtil = new SingleRootUtil(component);
		}
		return singleUtil;
	}
	
    /**
     * Returns the classpath as a list of absolute IPaths.
     * 
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
     * This method will handle a number of J2EE related scenarios.  If this is an ear and a child module is passed in,
     * the URI for that child module will be returned.  If no child module was passed, the URI of the EAR is returned.
     * If this is a child component and the module passed in is the EAR, we grab the module uri for this compared to that
     * EAR.  If no ear module is passed in we look for one and use it and return URI relative to found EAR.  If no EAR's 
     * are found the URI is returned in a default manner.
     * 
     * @return URI string
     */
    public String getURI(IModule module) {
    	// If the component is an ear and the module passed in is a child module
    	if (component!=null && module!=null && JavaEEProjectUtilities.isEARProject(component.getProject()))
 			return getContainedURI(module);

    	IVirtualComponent ear = null;
    	String aURI = null;
    	// If the component is a child module and the module passed in is the ear
    	if (module != null && JavaEEProjectUtilities.isEARProject(module.getProject()))
    		ear = ComponentCore.createComponent(module.getProject());
    	// else if the component is a child module and the module passed in is null, search for first ear
    	else if (module==null && component != null) {
    		IProject[] earProjects = EarUtilities.getReferencingEARProjects(component.getProject());
	        if (earProjects.length>0)
	        	ear = ComponentCore.createComponent(earProjects[0]);
    	}
    	// We have a valid ear and the component is a valid child
    	if (ear != null && component != null) {
    		EARArtifactEdit earEdit = null;
			try {
				earEdit = EARArtifactEdit.getEARArtifactEditForRead(ear);
				if (earEdit != null)
					aURI = earEdit.getModuleURI(component);
			} catch (Exception e) {
				J2EEPlugin.logError(e);
			} finally {
				if (earEdit != null)
					earEdit.dispose();
			}
    	} 
    	// We have an ear component and no child module
    	else if (component!=null && JavaEEProjectUtilities.isEARProject(component.getProject())) {
			aURI = component.getDeployedName()+IJ2EEModuleConstants.EAR_EXT;
    	} 
    	// We have child components but could not find valid ears
    	else if (component != null && JavaEEProjectUtilities.isDynamicWebProject(component.getProject())) {
			if (module != null) {
				IVirtualComponent webComp = ComponentCore.createComponent(component.getProject());
				String extension = IJ2EEModuleConstants.JAR_EXT;
				if (JavaEEProjectUtilities.isDynamicWebProject(module.getProject())) {
					extension = IJ2EEModuleConstants.WAR_EXT;
				} else if (JavaEEProjectUtilities.isJCAProject(module.getProject())) {
					extension = IJ2EEModuleConstants.RAR_EXT;
				}
				IVirtualReference reference = webComp.getReference(module.getProject().getName());
				if(reference != null){
					aURI = ComponentUtilities.getDeployUriOfComponent(reference, extension);
				} else {
					aURI = webComp.getDeployedName() + extension;
				}
			}
			else {
				aURI = component.getDeployedName() + IJ2EEModuleConstants.WAR_EXT;
			}
		} 
    	else if (component!=null && (JavaEEProjectUtilities.isEJBProject(component.getProject()) || JavaEEProjectUtilities.isApplicationClientProject(component.getProject()))) {
    		aURI = component.getDeployedName()+IJ2EEModuleConstants.JAR_EXT;
    	} 
    	else if (component!=null && JavaEEProjectUtilities.isJCAProject(component.getProject())) {
    		aURI = component.getDeployedName()+IJ2EEModuleConstants.RAR_EXT;
    	}
    	
    	if (aURI !=null && aURI.length()>1 && aURI.startsWith("/")) //$NON-NLS-1$
    		aURI = aURI.substring(1);
    	return aURI;
	}
    
    protected boolean isBinaryModuleArchive(IModule module) {
    	if (module!=null && (module.getName().endsWith(IJ2EEModuleConstants.JAR_EXT) || module.getName().endsWith(IJ2EEModuleConstants.WAR_EXT) ||
    			module.getName().endsWith(IJ2EEModuleConstants.RAR_EXT))) {
    		if (component!=null && JavaEEProjectUtilities.isEARProject(component.getProject()))
    			return true;
    	}
    	return false;
    }
    
    private String getContainedURI(IModule module) {
    	if( isBinaryModuleArchive(module)) {
    		J2EEFlexProjDeployable moduleDelegate = (J2EEFlexProjDeployable)module.loadAdapter(J2EEFlexProjDeployable.class, null);
    		if( moduleDelegate != null && moduleDelegate.component != null
    				&& moduleDelegate.component instanceof J2EEModuleVirtualArchiveComponent) {
    			J2EEModuleVirtualArchiveComponent moduleVirtualArchiveComponent = (J2EEModuleVirtualArchiveComponent)moduleDelegate.component;
    			IPath moduleDeployPath = moduleVirtualArchiveComponent.getDeploymentPath();
    			String moduleName = new Path(moduleVirtualArchiveComponent.getName()).lastSegment();
    			if (moduleName.equals(moduleDeployPath.lastSegment())){
    				return moduleDeployPath.toString();
    			}
    			return moduleDeployPath.append(moduleName).toString();
    		}
    	}

    	if (component instanceof J2EEModuleVirtualArchiveComponent || isBinaryModuleArchive(module)) {
    		return new Path(module.getName()).lastSegment();
    	}

    	IVirtualComponent comp = ComponentCore.createComponent(module.getProject());
    	String aURI = null;
    	if (comp!=null && component!=null && JavaEEProjectUtilities.isEARProject(component.getProject())) {
			EARArtifactEdit earEdit = null;
			try {
				earEdit = EARArtifactEdit.getEARArtifactEditForRead(component);
				if (earEdit != null)
					aURI = earEdit.getModuleURI(comp);
			} catch (Exception e) {
				J2EEPlugin.logError(e);
			} finally {
				if (earEdit != null)
					earEdit.dispose();
			}
    	}
    	if (aURI !=null && aURI.length()>1 && aURI.startsWith("/")) //$NON-NLS-1$
    		aURI = aURI.substring(1);
    	return aURI;
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
    
    @Override
	protected IModule gatherModuleReference(IVirtualComponent component, IVirtualComponent targetComponent ) {
    	IModule module = super.gatherModuleReference(component, targetComponent);
    	// Handle binary module components
    	if (targetComponent instanceof J2EEModuleVirtualArchiveComponent) {
    		if (JavaEEProjectUtilities.isEARProject(component.getProject()) || targetComponent.getProject()!=component.getProject())
    			module = ServerUtil.getModule(J2EEDeployableFactory.ID+":"+targetComponent.getName()); //$NON-NLS-1$
    	}
		return module;
    }
	
    protected IVirtualReference getReferenceNamed(IVirtualReference[] references, String name) {
    	for (int i=0; i<references.length; i++) {
    		if (references[i].getReferencedComponent().getName().equals(name))
    			return references[i];
    	}
    	return null;
    }
    
	
}
