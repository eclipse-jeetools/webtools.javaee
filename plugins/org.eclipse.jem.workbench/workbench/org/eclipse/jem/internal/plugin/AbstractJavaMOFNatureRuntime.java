package org.eclipse.jem.internal.plugin;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: AbstractJavaMOFNatureRuntime.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:33:53 $ 
 */

import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaModel;
import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.internal.java.JavaClass;
import org.eclipse.jem.internal.java.adapters.JavaXMIFactoryImpl;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.internal.java.impl.JavaClassImpl;
import org.eclipse.jem.internal.java.init.JavaInit;

import com.ibm.etools.emf.workbench.*;
import com.ibm.etools.emf.workbench.plugin.EMFWorkbenchPlugin;

/**
 * Insert the type's description here.
 * Creation date: (3/11/2001 8:05:35 PM)
 * @author: Administrator
 */
public abstract class AbstractJavaMOFNatureRuntime implements IJavaMOFNature, IProjectNature, EMFNatureContributor {
	
	protected static Set REGISTERED_NATURE_IDS = new HashSet();
	protected IProject project;
	protected EMFNature emfNature;
	protected boolean hasConfigured = false;
	/**
 * JavaMOFNatureRuntime constructor comment.
 */
public AbstractJavaMOFNatureRuntime() {
	super();
}
/**
 * Add Adaptor factories to aContext which is now
 * being used for this nature.
 */
protected void addAdapterFactories(ResourceSet aSet) {
	addJavaReflectionAdapterFactories(aSet);
}
protected void addJavaReflectionAdapterFactories(ResourceSet aSet) {
	List factories = aSet.getAdapterFactories();
	// The context may already have a JavaReflection adaptor factory, so remove it
	if (!factories.isEmpty()) {
		AdapterFactory factory = EcoreUtil.getAdapterFactory(factories, ReadAdaptor.TYPE_KEY);
		if (factory != null)
			factories.remove(factory);
	}
	// This should maybe be considered a logic error, but we can recover easily
	factories.add(new JavaJDOMAdapterFactory(getJavaProject()));
}

/**
 * Adds a nauture to a project
 */
protected static void addNatureToProject(IProject proj, String natureId) throws CoreException {
	ProjectUtilities.addNatureToProject(proj, natureId);
}
/** 
 * Configures the project with this nature.
 * This is called by <code>IProject.addNature</code> and should not
 * be called directly by clients.
 * The nature extension id is added to the list of natures on the project by
 * <code>IProject.addNature</code>, and need not be added here.
 *
 * All subtypes must call super.
 *
 * @exception CoreException if this method fails.
 */
public void configure() throws org.eclipse.core.runtime.CoreException {
	if (!hasConfigured) {
		hasConfigured = true;
		primConfigure();
	}
}

protected void primConfigure() throws org.eclipse.core.runtime.CoreException {

}
protected void createEmfNature() throws CoreException {
	EMFWorkbenchPlugin.getResourceHelper().createEMFNature(getProject(), this);
}

/*
 * @see EMFNatureContributor#contributeToNature(EMFNature)
 */
public void contributeToNature(EMFNature aNature) {
	if (emfNature == aNature) return;
	emfNature = aNature;
	ResourceSet set = aNature.getResourceSet();
	set.getResourceFactoryRegistry().getProtocolToFactoryMap().put(JavaXMIFactoryImpl.SCHEME, JavaXMIFactoryImpl.INSTANCE);	
	WorkbenchURIConverter conv = (WorkbenchURIConverter) set.getURIConverter();
	configureURIConverter(conv);
	addAdapterFactories(set);	
}

/**
 * Create a folder relative to the project based on aProjectRelativePathString.
 * @exception com.ibm.itp.core.api.resources.CoreException The exception description.
 */
public IFolder createFolder(String aProjectRelativePathString) throws CoreException {
	if (aProjectRelativePathString != null && aProjectRelativePathString.length() > 0) 
		return createFolder(new Path(aProjectRelativePathString));
	return null;
}
/**
 * Create a folder relative to the project based on aProjectRelativePathString.
 * @exception com.ibm.itp.core.api.resources.CoreException The exception description.
 */
public IFolder createFolder(IPath aProjectRelativePath) throws CoreException {
	if (aProjectRelativePath != null && !aProjectRelativePath.isEmpty()) {
		IFolder folder = getWorkspace().getRoot().getFolder(getProjectPath().append(aProjectRelativePath));
		if (!folder.exists()) {
			com.ibm.etools.emf.workbench.ProjectUtilities.ensureContainerNotReadOnly(folder);
			folder.create(true, true, null);
		}
		return folder;
	}
	return null;
}
/**
 * Create the folders for the project we have just created.
 * @exception com.ibm.itp.core.api.resources.CoreException The exception description.
 */
protected void createFolders() throws CoreException {
	// build for classpath
	IPath sourcePath = getSourcePath();
	//might be null for binary projects
	if (sourcePath != null) {
		createFolder(sourcePath.toString());
		//If there's no source, then don't create the output folder either
		createFolder(getJavaOutputPath().toString());
	}
}
/**
 * Remove the project as a container from the converter and add
 * the source folder.
 */
protected void configureURIConverter(WorkbenchURIConverter conv) {
	conv.removeInputContainer(getProject());
	conv.addInputContainer(getMofRoot());
}
/** 
 * Removes this nature from the project, performing any required deconfiguration.
 * This is called by <code>IProject.removeNature</code> and should not
 * be called directly by clients.
 * The nature id is removed from the list of natures on the project by
 * <code>IProject.removeNature</code>, and need not be removed here.
 *
 * @exception CoreException if this method fails. 
 */
public void deconfigure() throws org.eclipse.core.runtime.CoreException {
}
/**
 * Delete @aFile in the Workbench.
 */
protected void deleteFile(IFile aFile) throws CoreException {
	if (aFile != null && aFile.exists())
		aFile.delete(true, null);
}
/**
 * Delete @aResource in the Workbench.
 */
public void deleteResource(Resource aResource) throws CoreException {
	if (aResource != null)
		deleteFile(getFile(aResource));
}
/**
 * Return true if the IFile with the given name
 * @aFileName exists in this project.  @aFileName 
 * should be relative to one of the input file paths for the WorkbenchURIConverter.
 */
public boolean fileExists(String aFileName) {
	if (aFileName == null)
		return false;
		
	IPath path = new Path(aFileName);
	if (path.isAbsolute()) 
		return ResourcesPlugin.getWorkspace().getRoot().getFile(path).exists();
	else
		return getWorkbenchURIConverter().canGetUnderlyingResource(aFileName);
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/00 9:33:59 AM)
 * @return org.eclipse.emf.ecore.resource.ResourceSet
 * @deprecated use getResourceSet()
 */
public org.eclipse.emf.ecore.resource.ResourceSet getContext() {
	return getResourceSet();
}

public ResourceSet getResourceSet() {
	return getEmfNature().getResourceSet();
}

/**
 * Used for optimizations; answers whether a mof context for 
 * this nature has exists yet
 * @deprecated use hasResourceSet();
 */
public boolean hasContext() {
	return hasResourceSet();
}
public boolean hasResourceSet() {
	return emfNature != null && emfNature.hasResourceSet();
}
/**
 * Return the location of the source files.
 */
protected IPath getSourcePath() {
	return ProjectUtilities.getSourcePathOrFirst(getProject(), getSourcePathKey());
}
protected IPath getDefaultSourcePath() {
	IPath path = new Path(getProject().getName());
	path = path.append(getDefaultSourcePathString());
	path = path.makeAbsolute();
	return path;
}
protected String getDefaultSourcePathString() {
	return JavaProjectInfo.DEFAULT_SOURCE_PATH;
}
/**
 * Lazy initializer; for migration of existing workspaces where
 * configure will never get called
 */
protected EMFNature getEmfNature() {
	if (emfNature == null) {
		try {
			createEmfNature();
		} catch (CoreException ex) {
			JavaPlugin.getDefault().getMsgLogger().log(ex);
		}
	}
	return emfNature;
}
/**
 * Return an IFile for the given Resource.
 */
public IFile getFile(Resource aResource) {
	if (aResource == null) return null;
	return EMFWorkbenchPlugin.getResourceHelper().getFile(aResource);
}
/**
 * Return an IFile for the file with the given name
 * @aFileName.  @aFileName should be relative to one of the
 * input file paths for the WorkbenchURIConverter.
 */
public IFile getFile(String aFileName) {
	return getWorkbenchURIConverter().getFile(aFileName);
}
/**
 * Return the project for the receiver.
 * @return IProject
 */
public JavaModel getJavaModel() {
	return ProjectUtilities.getJavaModel();
}
public IFolder getJavaOutputFolder() {
	return getProject().getFolder(getJavaProjectOutputLocation().removeFirstSegments(1));
}
protected IPath getJavaOutputPath() {
	return getJavaProjectOutputLocation().removeFirstSegments(1);
}
/**
 * Return the project for the receiver.
 * @return IProject
 */
public IJavaProject getJavaProject() {
	JavaModel jm = getJavaModel();
	return jm.getJavaProject(getProject());
}
/**
 * Return the location of the binary files for the JavaProject.
 */
protected IPath getJavaProjectOutputLocation() {
	try {
		IJavaProject javaProj = getJavaProject();
		if (!javaProj.isOpen())
			javaProj.open(null);
		return javaProj.getOutputLocation();
	} catch (JavaModelException e) {
		return null;
	}
}
/**
 * Return the root location for loading mof resources; defaults to the source folder, subclasses may override
 */
public IContainer getMofRoot() {
	return getSourceFolder();
}
/**
 * Return the nature's ID.
 */
public abstract String getNatureID() ;
/**
 * Return the ID of the plugin that this nature is contained within.
 */
protected abstract String getPluginID() ;
/** 
 * Returns the project to which this project nature applies.
 *
 * @return the project handle
 */
public org.eclipse.core.resources.IProject getProject() {
	return project;
}
/**
 * Return the full path of the project.
 */
protected IPath getProjectPath() {
	return getProject().getFullPath();
}
/**
 * Get the server property of the project from the supplied key
 * @param key java.lang.String
 * @deprecated we cannont use persistent properties because they are not stored in the repository
 */
protected String getProjectServerValue(String key) {
	if (key == null) return null;
	try {
		QualifiedName wholeName = qualifiedKey(key);
		return getProject().getPersistentProperty(wholeName);
	} catch (CoreException exception) {
		//If we can't find it assume it is null
		exception.printStackTrace();
		return null;
	}
}
/**
 * Return a IJavaMOFNature based on the natures that have been configured.
 * @return IJavaMOFNature
 * @param project com.ibm.itp.core.api.resources.IProject
 */
public static IJavaMOFNature getRegisteredRuntime(IProject project) {
	IJavaMOFNature nature = null;
	if (project != null && project.isAccessible()) {
		String natureID;
		Iterator it = REGISTERED_NATURE_IDS.iterator();
		while (it.hasNext()) {
			natureID = (String) it.next();
			try {
				nature = (IJavaMOFNature) project.getNature(natureID);
			} catch (CoreException e) { }
			if (nature != null)
				return nature;
		}
	}
	return nature;
}
/**
 * Return a IJavaMOFNature based on the natures that have been configured.
 * @return IJavaMOFNature
 * @param project com.ibm.itp.core.api.resources.IProject
 */
public static String getRegisteredRuntimeID(IProject project) {
	String natureID = null;
	if (project != null && project.isAccessible()) {
		IJavaMOFNature nature = null;
		Iterator it = REGISTERED_NATURE_IDS.iterator();
		while (it.hasNext()) {
			natureID = (String) it.next();
			try {
				nature = (IJavaMOFNature) project.getNature(natureID);
			} catch (CoreException e) { }
			if (nature != null)
				return natureID;
		}
	}
	return null;
}
public IFolder getSourceFolder() {
	return (IFolder)ProjectUtilities.getSourceFolderOrFirst(getProject(), getSourcePathKey());
}
public List getSourceFolders() {
	return ProjectUtilities.getSourceContainers(getProject());
}

/**
 * Return the key for the sourcePath.
 */
protected java.lang.String getSourcePathKey() {
	return SOURCE_PATH;
}

/**
 * This method assumes the URIConverter on the ResourceSet is the one that was
 * created for the ResourceSet on behalf of this nature runtime.
 */
protected WorkbenchURIConverter getWorkbenchURIConverter() {
	return (WorkbenchURIConverter) getContext().getURIConverter();
}
public IWorkspace getWorkspace() {
	return getProject().getWorkspace();
}

/**
 * @deprecated use getResource(URI)
 */
public Resource getXmiResource(String uri) {
	return getResource(URI.createURI(uri));
}

public Resource getResource(URI uri) {
	try {
		return getResourceSet().getResource(uri, true);
	} catch (WrappedException ex) {
		if (!EMFWorkbenchPlugin.getResourceHelper().isResourceNotFound(ex))
			throw ex;
	}
	return null;
}

/**
 * @deprecated use getResourceSet()
 */
public ResourceSet getXmiResourceSet() {
	return getResourceSet();
}
/**
 * Make sure that all dependent components are initialized before
 * creating the ResourceSet.
 */
protected void initializeDependentComponents() {
	JavaInit.init();
}
/** 
 * Update the receiver from the supplied info and then configure it.
 * @param info IJavaProjctInfo - the info this was created from
 * @exception CoreExeption - thrown if the project cannot be updated
 */
public void initializeFromInfo(IJavaProjectInfo info) throws CoreException {
	createFolders();
	
	//The CVS Ignore file creation is commented out, OTI has a better way of solving this
	//addCVSIgnoreFile();
}

/**
 * @deprecated use createResource(URI)
 */
public Resource makeXmiResource(String uri) {
	return createResource(URI.createURI(uri));
}
/**
 * @deprecated use createResource(URI)
 */
public Resource makeXmiResource(String uri, EList anExtent) {
	Resource res = makeXmiResource(uri);
	if (res != null)
		res.getContents().addAll(anExtent);
	return res;
}
public Resource createResource(URI uri) {
	return getResourceSet().createResource(uri);
}
/**
 * Return the QualifedValue for key for storage in the repository. The key is qualifed
 * with the package name to avoid collision.
 * @return QualifedName
 * @param key java.lang.String
 */
private QualifiedName qualifiedKey(String key) {
	return new QualifiedName(getPluginID(), key);
}
public static void registerNatureID(String natureID) {
	REGISTERED_NATURE_IDS.add(natureID);
}
/**
 * Sets the project to which this nature applies.
 * Used when instantiating this project nature runtime.
 * This is called by <code>IProject.addNature</code>
 * and should not be called directly by clients.
 *
 * @param project the project to which this nature applies
 */
public void setProject(org.eclipse.core.resources.IProject newProject) {
	project = newProject;
}
/**
 * Set the server property of the project from the supplied value
 * @param key java.lang.String
 * @param value String
 * @deprecated we cannont use persistent properties because they are not stored in the repository
 */
protected void setProjectServerValue(String key, String value) {
	if (key != null) {
		try {
			QualifiedName wholeName = qualifiedKey(key);
			getProject().setPersistentProperty(wholeName, value);
		} catch (CoreException exception) {
			//If we can't find it assume it is null
			exception.printStackTrace();
			return;
		}
	}
}
/**
 * Set the servlet path  of the receiver.
 * @param uriSegment java.lang.String
 * @deprecated a project may have multiple source folders, and we cannont use
 * persistent properties because they are not stored in the repository
 */
protected void setSourcePath(String uriSegment) {
	setProjectServerValue(getSourcePathKey(),uriSegment);
}

	/**
	*Shuts down the MOF Nature
	*/
	public void shutdown(){
		if (getResourceSet() != null)
			((ProjectResourceSet)getResourceSet()).release();
	}
	
	
	public List getLibaryFolders() {
		return ProjectUtilities.getLibaryContainers(getProject());
	}
	
	public JavaClass getJavaClass(IFile aFile) {
		if (aFile == null) return null;
		List folders = getSourceFolders();
		folders.addAll(getLibaryFolders());	
		IFolder folder = null;
		IPath folderPath, filePath, javaPath;
		filePath = aFile.getProjectRelativePath();
		if (folders != null)
		{
			for (int i = 0; i < folders.size(); i++) {
				folder = (IFolder) folders.get(i);
				folderPath = folder.getProjectRelativePath();
				int segments = filePath.matchingFirstSegments(folderPath);
				if (segments > 0) {
					javaPath = filePath.removeFirstSegments(segments);
					javaPath = javaPath.removeFileExtension();
					String qualifiedName = javaPath.toString().replace('/', '.');
					return (JavaClass)JavaClassImpl.reflect(qualifiedName, getContext());
				}
			}
		}
		return null;
	}
}
