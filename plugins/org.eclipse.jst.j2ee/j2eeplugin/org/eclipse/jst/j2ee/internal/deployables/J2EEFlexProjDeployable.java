/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.server.core.IApplicationClientModule;
import org.eclipse.jst.server.core.IConnectorModule;
import org.eclipse.jst.server.core.IEJBModule;
import org.eclipse.jst.server.core.IEnterpriseApplication;
import org.eclipse.jst.server.core.IJ2EEModule;
import org.eclipse.jst.server.core.IWebModule;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.internal.ModuleFile;
import org.eclipse.wst.server.core.internal.ModuleFolder;
import org.eclipse.wst.server.core.model.IModuleFolder;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wst.web.internal.deployables.ComponentDeployable;
/**
 * J2EE module superclass.
 */
public class J2EEFlexProjDeployable extends ComponentDeployable implements IJ2EEModule, IEnterpriseApplication, IApplicationClientModule, IConnectorModule, IEJBModule, IWebModule {
	private static final IPath WEB_CLASSES_PATH = new Path("WEB-INF").append("classes"); //$NON-NLS-1$ //$NON-NLS-2$
	private IPackageFragmentRoot[] cachedSourceContainers;

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
	 * Returns the root folders for the resources in this module.
	 * 
	 * @return a possibly-empty array of resource folders
	 */
	public IContainer[] getResourceFolders() {
		List result = new ArrayList();
		IVirtualComponent vc = ComponentCore.createComponent(getProject());
		if (vc != null) {
			IVirtualFolder vFolder = vc.getRootFolder();
			if (vFolder != null) {
				IContainer[] underlyingFolders = vFolder.getUnderlyingFolders();
				result.addAll(Arrays.asList(underlyingFolders));
			}
		}
		return (IContainer[]) result.toArray(new IContainer[result.size()]);
	}

	/**
	 * Returns the root folders containing Java output in this module.
	 * 
	 * @return a possibly-empty array of Java output folders
	 */
	public IContainer[] getJavaOutputFolders() {
		return getJavaOutputFolders(getProject());
	}
	
	public IContainer[] getJavaOutputFolders(IProject project) {
		if (project == null)
			return new IContainer[0];
		return J2EEProjectUtilities.getOutputContainers(project);
	}

	protected boolean shouldIncludeUtilityComponent(IVirtualComponent virtualComp,IVirtualReference[] references, ArtifactEdit edit) {
		// If the component module is an EAR we know all archives are filtered out of virtual component members
		// and we will return only those archives which are not binary J2EE modules in the EAR DD.  These J2EE modules will
		// be returned by getChildModules()
		if (J2EEProjectUtilities.isEARProject(component.getProject()))
			return virtualComp != null && virtualComp.isBinary() && !isNestedJ2EEModule(virtualComp, references, (EARArtifactEdit)edit);
		else 
			return super.shouldIncludeUtilityComponent(virtualComp, references, edit);
	}
	
	protected IModuleResource[] getBinaryModuleMembers() {
		IPath archivePath = ((J2EEModuleVirtualArchiveComponent)component).getWorkspaceRelativePath();
		ModuleFile mf = null;
		if (archivePath != null) { //In Workspace
			IFile utilFile = ResourcesPlugin.getWorkspace().getRoot().getFile(archivePath);
			mf = new ModuleFile(utilFile, utilFile.getName(), ((J2EEModuleVirtualArchiveComponent)component).getRuntimePath().makeRelative());
		} else {
			File extFile = ((J2EEModuleVirtualArchiveComponent)component).getUnderlyingDiskFile();
			mf = new ModuleFile(extFile, extFile.getName(), ((J2EEModuleVirtualArchiveComponent)component).getRuntimePath().makeRelative());
		}
		return new IModuleResource[] {mf};
	}
	
	public IModuleResource[] members() throws CoreException {
		members.clear();
		
		if (component instanceof J2EEModuleVirtualArchiveComponent)
			return getBinaryModuleMembers();
		
		cachedSourceContainers = J2EEProjectUtilities.getSourceContainers(getProject());
		try {
			IPath javaPath = Path.EMPTY;
			if (J2EEProjectUtilities.isDynamicWebProject(component.getProject()))
				javaPath = WEB_CLASSES_PATH;
			
			IVirtualComponent vc = ComponentCore.createComponent(getProject());
			if (vc != null) {
				IVirtualFolder vFolder = vc.getRootFolder();
				IModuleResource[] mr = getMembers(vFolder, Path.EMPTY);
				int size = mr.length;
				for (int j = 0; j < size; j++) {
					members.add(mr[j]);
				}
			}
			
			IContainer[] javaCont = getJavaOutputFolders();		
			int size = javaCont.length;
			for (int i = 0; i < size; i++) {
				IModuleResource[] mr = getMembers(javaCont[i], javaPath, javaPath, javaCont);
				int size2 = mr.length;
				for (int j = 0; j < size2; j++) {
					members.add(mr[j]);
				}
			}
			
			if (vc != null) {
				addUtilMembers(vc);
				List consumableMembers = getConsumableReferencedMembers(vc);
				if (!consumableMembers.isEmpty())
					members.addAll(consumableMembers);
			}
			
			IModuleResource[] mr = new IModuleResource[members.size()];
			members.toArray(mr);
			return mr;
		} finally {
			cachedSourceContainers = null;
		}
	}
	
	protected IModuleResource[] handleJavaPath(IPath path, IPath javaPath, IPath curPath, IContainer[] javaCont, IModuleResource[] mr, IContainer cc) throws CoreException {
		if (curPath.equals(javaPath)) {
			int size = javaCont.length;
			for (int i = 0; i < size; i++) {
				IModuleResource[] mr2 = getMembers(javaCont[i], path.append(cc.getName()), null, null);
				IModuleResource[] mr3 = new IModuleResource[mr.length + mr2.length];
				System.arraycopy(mr, 0, mr3, 0, mr.length);
				System.arraycopy(mr2, 0, mr3, mr.length, mr2.length);
				mr = mr3;
			}
		} else {
			boolean containsFolder = false;
			String name = javaPath.segment(curPath.segmentCount());
			int size = mr.length;
			for (int i = 0; i < size && !containsFolder; i++) {
				if (mr[i] instanceof IModuleFolder) {
					IModuleFolder mf2 = (IModuleFolder) mr[i];
					if (name.equals(mf2.getName())) {
						containsFolder = true;
					}
				}
			}
			
			if (!containsFolder && javaCont.length > 0) {
				ModuleFolder mf2 = new ModuleFolder(javaCont[0], name, curPath);
				IModuleResource[] mrf = new IModuleResource[0];
				size = javaCont.length;
				for (int i = 0; i < size; i++) {
					IModuleResource[] mrf2 = getMembers(javaCont[i], javaPath, null, null);
					IModuleResource[] mrf3 = new IModuleResource[mrf.length + mrf2.length];
					System.arraycopy(mrf, 0, mrf3, 0, mrf.length);
					System.arraycopy(mrf2, 0, mrf3, mrf.length, mrf2.length);
					mrf = mrf3;
				}
				
				mf2.setMembers(mrf);
				
				IModuleResource[] mr3 = new IModuleResource[mr.length + 1];
				System.arraycopy(mr, 0, mr3, 0, mr.length);
				mr3[mr.length] = mf2;
				mr = mr3;
			}
		}
		return mr;
	}

    /**
     * Returns the classpath as a list of absolute IPaths.
     * 
     * @return an array of paths
     */
    public IPath[] getClasspath() {
		List paths = new ArrayList();
        IJavaProject proj = JemProjectUtilities.getJavaProject(getProject());
        URL[] urls = JemProjectUtilities.getClasspathAsURLArray(proj);
		for (int i = 0; i < urls.length; i++) {
			URL url = urls[i];
			paths.add(Path.fromOSString(url.getPath()));
		}
        return  (IPath[]) paths.toArray(new IPath[paths.size()]);
    }
    
    public String getJNDIName(String ejbName) {
    	if (!J2EEProjectUtilities.isEJBProject(component.getProject()))
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
			e.printStackTrace();
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
    	if (component!=null && module!=null && J2EEProjectUtilities.isEARProject(component.getProject()))
 			return getContainedURI(module);

    	IVirtualComponent ear = null;
    	String aURI = null;
    	// If the component is a child module and the module passed in is the ear
    	if (module != null && J2EEProjectUtilities.isEARProject(module.getProject()))
    		ear = ComponentCore.createComponent(module.getProject());
    	// else if the component is a child module and the module passed in is null, search for first ear
    	else if (module==null && component != null) {
    		IProject[] earProjects = J2EEProjectUtilities.getReferencingEARProjects(component.getProject());
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
				e.printStackTrace();
			} finally {
				if (earEdit != null)
					earEdit.dispose();
			}
    	} 
    	// We have an ear component and no child module
    	else if (component!=null && J2EEProjectUtilities.isEARProject(component.getProject())) {
			aURI = component.getDeployedName()+IJ2EEModuleConstants.EAR_EXT;
    	} 
    	// We have child components but could not find valid ears
    	else if (component!=null && J2EEProjectUtilities.isDynamicWebProject(component.getProject())) {
    		if (module != null && J2EEProjectUtilities.isUtilityProject(module.getProject())) {
    			IVirtualComponent webComp = ComponentCore.createComponent(component.getProject());
    			IVirtualReference reference = webComp.getReference(module.getProject().getName());
    			aURI = ComponentUtilities.getDeployUriOfUtilComponent(reference);
    		}else{
    			aURI = component.getDeployedName()+IJ2EEModuleConstants.WAR_EXT;
    		}
    	} 
    	else if (component!=null && (J2EEProjectUtilities.isEJBProject(component.getProject()) || J2EEProjectUtilities.isApplicationClientProject(component.getProject()))) {
    		aURI = component.getDeployedName()+IJ2EEModuleConstants.JAR_EXT;
    	} 
    	else if (component!=null && J2EEProjectUtilities.isJCAProject(component.getProject())) {
    		aURI = component.getDeployedName()+IJ2EEModuleConstants.RAR_EXT;
    	}
    	
    	if (aURI !=null && aURI.length()>1 && aURI.startsWith("/")) //$NON-NLS-1$
    		aURI = aURI.substring(1);
    	return aURI;
	}
    
    private boolean isBinaryModuleArchive(IModule module) {
    	if (module!=null && module.getName().endsWith(IJ2EEModuleConstants.JAR_EXT) || module.getName().endsWith(IJ2EEModuleConstants.WAR_EXT) ||
    			module.getName().endsWith(IJ2EEModuleConstants.RAR_EXT)) {
    		if (component!=null && J2EEProjectUtilities.isEARProject(component.getProject()))
    			return true;
    	}
    	return false;
    }
    
    private String getContainedURI(IModule module) {
    	if (component instanceof J2EEModuleVirtualArchiveComponent || isBinaryModuleArchive(module))
    		return new Path(module.getName()).lastSegment();
    	
    	IVirtualComponent comp = ComponentCore.createComponent(module.getProject());
    	String aURI = null;
    	if (comp!=null && component!=null && J2EEProjectUtilities.isEARProject(component.getProject())) {
			EARArtifactEdit earEdit = null;
			try {
				earEdit = EARArtifactEdit.getEARArtifactEditForRead(component);
				if (earEdit != null)
					aURI = earEdit.getModuleURI(comp);
			} catch (Exception e) {
				e.printStackTrace();
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
    	else if (J2EEProjectUtilities.isEARProject(earModule.getProject()) && J2EEProjectUtilities.isDynamicWebProject(deployProject)) {
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
    
    protected boolean isFileInSourceContainer(IFile file) {
    	boolean result = false;
    	if (file == null)
    		return false;
    	IPackageFragmentRoot[] srcContainers = getSourceContainers();
    	for (int i=0; i<srcContainers.length; i++) {
    		IPath srcPath = srcContainers[i].getPath();
    		result = srcPath.isPrefixOf(file.getFullPath());
    		if (result)
    			break;
    	}
    	return result;
    }

	private IPackageFragmentRoot[] getSourceContainers() {
		if (cachedSourceContainers != null)
			return cachedSourceContainers;
		return J2EEProjectUtilities.getSourceContainers(getProject());
	}
    
    protected List getConsumableReferencedMembers(IVirtualComponent vc) throws CoreException {
		List consumableMembers = new ArrayList();
		IVirtualReference[] refComponents = vc.getReferences();
    	for (int i = 0; i < refComponents.length; i++) {
    		IVirtualReference reference = refComponents[i];
    		if (reference != null && reference.getDependencyType()==IVirtualReference.DEPENDENCY_TYPE_CONSUMES) {
    			IVirtualComponent consumedComponent = reference.getReferencedComponent();
    			if (consumedComponent!=null && isProjectOfType(consumedComponent.getProject(),IModuleConstants.JST_UTILITY_MODULE)) {
    				if (consumedComponent != null && consumedComponent.getRootFolder()!=null) {
    					IVirtualFolder vFolder = consumedComponent.getRootFolder();
    					IModuleResource[] mr = getMembers(vFolder, reference.getRuntimePath().makeRelative());
    					int size = mr.length;
    					for (int j = 0; j < size; j++) {
    						if (!members.contains(mr[j]))
    							members.add(mr[j]);
    					}
    					addUtilMembers(consumedComponent);
    					List childConsumableMembers = getConsumableReferencedMembers(consumedComponent);
    					if (!childConsumableMembers.isEmpty())
    						members.addAll(childConsumableMembers);
    				}
    				
    				IContainer[] javaCont = getJavaOutputFolders(consumedComponent.getProject());		
    				int size = javaCont.length;
    				for (int j = 0; j < size; j++) {
    					IModuleResource[] mr = getMembers(javaCont[j], reference.getRuntimePath(), reference.getRuntimePath(), javaCont);
    					int size2 = mr.length;
    					for (int k = 0; k < size2; k++) {
    						if (!members.contains(mr[k]))
    							members.add(mr[k]);
    					}
    				}
    			}
    		}
    	}
		return consumableMembers;
	}
    
    protected IModule gatherModuleReference(IVirtualComponent component, IVirtualComponent targetComponent ) {
    	IModule module = super.gatherModuleReference(component, targetComponent);
    	// Handle binary module components
    	if (targetComponent instanceof J2EEModuleVirtualArchiveComponent) {
    		if (J2EEProjectUtilities.isEARProject(component.getProject()) || targetComponent.getProject()!=component.getProject())
    			module = ServerUtil.getModule(J2EEDeployableFactory.ID+":"+targetComponent.getName()); //$NON-NLS-1$
    	}
		return module;
    }
    
    /**
     * Determine if the component is nested J2EE module on the application.xml of this EAR
     * @param aComponent
     * @return boolean is passed in component a nested J2EE module on this EAR
     */
    private boolean isNestedJ2EEModule(IVirtualComponent aComponent, IVirtualReference[] references, EARArtifactEdit edit) {
    	if (edit==null) 
			return false;
		Application app = edit.getApplication();
		IVirtualReference reference = getReferenceNamed(references,aComponent.getName());
		// Ensure module URI exists on EAR DD for binary archive
		return app.getFirstModule(reference.getArchiveName()) != null;
    }
    
    private IVirtualReference getReferenceNamed(IVirtualReference[] references, String name) {
    	for (int i=0; i<references.length; i++) {
    		if (references[i].getReferencedComponent().getName().equals(name))
    			return references[i];
    	}
    	return null;
    }
    
    protected ArtifactEdit getComponentArtifactEditForRead() {
		return EARArtifactEdit.getEARArtifactEditForRead(component.getProject());
	}
}