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
/*
 * Created on Aug 26, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.internal.adapters.jdom.JavaReflectionSynchronizer;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.internal.plugin.IJavaProjectInfo;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.CommonarchiveFactory;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservices.WebServiceEditModel;

import com.ibm.wtp.emf.workbench.ProjectResourceSet;
import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEModuleNature extends J2EENature {


	/**
	 *  
	 */
	public J2EEModuleNature() {
		super();
	}

	/**
	 * Method used for adding a j2ee project to an ear project; subclasses must override to create a
	 * new instance of the correct kind of Module
	 */
	public abstract Module createNewModule();

	/**
	 * If this project is a binary project, returns the JAR used as the input for loading the
	 * descriptor resources
	 */
	public IResource getBinaryProjectInputJARResource() {
		J2EEModuleWorkbenchURIConverterImpl conv = (J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter();
		IPath aPath = null;
		if (conv != null)
			aPath = conv.getInputJARProjectRelativePath();
		if (aPath != null)
			return getProject().getFile(aPath);

		return null;
	}

	/**
	 * Only some of the J2EE project types can be run from the JAR file; others must be expanded
	 * 
	 * @return false by default; subclasses should override
	 */
	public boolean canBeBinary() {
		return false;
	}

	/**
	 * Create the folders for the project we have just created.
	 * 
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 *                The exception description.
	 */
	protected void createFolders() throws CoreException {
		// build for classpath
		IPath sourcePath = ProjectUtilities.getSourcePathOrFirst(getProject(), null);
		//might be null for binary projects
		if (sourcePath != null) {
			createFolder(sourcePath.toString());
			IContainer container = ProjectUtilities.getJavaProjectOutputContainer(getProject());
			if (container != null && container.getType() == IResource.FOLDER)
				createFolder(container.getProjectRelativePath().toString());
		}
	}

	/**
	 * Answer the absolute path of the module root
	 * 
	 * @see #getModuleRoot
	 */
	public String computeModuleAbsolutePath() {
		return computeModuleAbsoluteLocation().toOSString();
	}

	public IPath computeModuleAbsoluteLocation() {
		IPath location = null;
		J2EEModuleWorkbenchURIConverterImpl conv = (J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter();
		if (conv != null)
			location = conv.getInputJARLocation();

		if (location != null)
			return location;
		return getModuleServerRoot().getLocation();
	}

	public void recomputeBinaryProject() {
		J2EEModuleWorkbenchURIConverterImpl conv = (J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter();
		if (conv != null)
			conv.recomputeInputJARLocation();
	}

	public static String getModuleAbsolutePath(IProject aJ2EEProject) {
		J2EEModuleNature aNature = (J2EEModuleNature) getRegisteredRuntime(aJ2EEProject);
		return aNature == null ? null : aNature.computeModuleAbsolutePath();
	}

	/**
	 * Return the root location for loading mof resources; defaults to the source folder, subclasses
	 * may override
	 */
	public IContainer getEMFRoot() {
		return ProjectUtilities.getSourceFolderOrFirst(project, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.j2eeproject.J2EENature#primConfigure()
	 */
	protected void primConfigure() throws CoreException {
		super.primConfigure();
		ProjectUtilities.addToBuildSpec(J2EEPlugin.LIBCOPY_BUILDER_ID, project);
	}

	public CommonarchiveFactory getCommonArchiveFactory() {
		return CommonarchiveFactory.eINSTANCE;
	}

	/*
	 * @see IJ2EENature#isBinaryProject()
	 */
	public boolean isBinaryProject() {
		J2EEModuleWorkbenchURIConverterImpl conv = (J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter();
		if (conv == null)
			return false;

		return conv.isBinary();
	}

	protected J2EEWorkbenchURIConverterImpl getJ2EEWorkbenchURIConverter() {
		WorkbenchURIConverter conv = getWorkbenchURIConverter();
		if (conv instanceof J2EEModuleWorkbenchURIConverterImpl)
			return (J2EEModuleWorkbenchURIConverterImpl) conv;
		return null;
	}

	public void initializeFromInfo(IJavaProjectInfo info) throws CoreException {
		//super.initializeFromInfo(info);
		J2EEJavaProjectInfo j2eeInfo = (J2EEJavaProjectInfo) info;
		J2EESettings settings = getJ2EESettings();
		settings.setModuleVersion(j2eeInfo.getModuleVersion());
		settings.write();

		ProjectUtilities.updateClasspath(j2eeInfo.getJavaProject()); //lsr - no monitor, do not
		// update resources
	}

	/**
	 * Return all the ear projects in which this project is a nested module; Applicable to project
	 * (nature) types that can be modules in an ear file
	 */
	public EARNatureRuntime[] getReferencingEARProjects() {
		List earProjects = EARNatureRuntime.getAllEARProjectsInWorkbench();
		List result = new ArrayList();
		for (int i = 0; i < earProjects.size(); i++) {
			IProject earProject = (IProject) earProjects.get(i);
			EARNatureRuntime earNature = EARNatureRuntime.getRuntime(earProject);
			if (earNature.getModule(getProject()) != null) {
				result.add(earNature);
			}
		}
		return (EARNatureRuntime[]) result.toArray(new EARNatureRuntime[result.size()]);
	}

	public String getModuleUriInFirstEAR() {
		EARNatureRuntime[] earNatures = getReferencingEARProjects();
		EARNatureRuntime earNature;
		Module mod;
		for (int i = 0; i < earNatures.length; i++) {
			earNature = earNatures[i];
			mod = earNature.getModule(getProject());
			if (mod != null && mod.getUri() != null && mod.getUri().length() > 0)
				return mod.getUri();
		}
		return null;
	}

	/**
	 * Returns the default source folder for this J2EE project, or null if it can't be determined.
	 * 
	 * If the output location is also a source folder, then returns the output location, otherwise,
	 * looks for the source folder that has the deployment descriptor, otherwise, returns the first
	 * source folder on the java build path.
	 * 
	 * Currently the project as the source is not supported.
	 * 
	 * @see org.eclipse.jem.internal.java.plugin.IJavaMOFNature#getSourceFolder()
	 */
	public IFolder getSourceFolder() {
		IContainer output = ProjectUtilities.getJavaProjectOutputContainer(getProject());
		List sources = ProjectUtilities.getSourceContainers(getProject());
		//TODO: We need to be able to support the project as the source, but this would be a
		// breaking change
		if (sources == null || sources.isEmpty() || ((IContainer) sources.get(0)).getType() != IResource.FOLDER)
			return null;
		if (output != null && sources.contains(output))
			return (IFolder) output;

		for (int i = 0; i < sources.size(); i++) {
			IFolder source = (IFolder) sources.get(i);
			if (source.getFile(getDeploymentDescriptorURI()).isAccessible())
				return source;
		}
		return (IFolder) sources.get(0);
	}


	protected J2EEWorkbenchURIConverterImpl initializeWorbenchURIConverter(ProjectResourceSet set) {
		return new J2EEModuleWorkbenchURIConverterImpl(this, set.getSynchronizer());
	}

	/**
	 * Add Adaptor factories to aContext which is now being used for this nature.
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

		// Override JavaJDOMFactory so we can notify on add of compilation unit for ejb annotation
		// support
		JavaJDOMAdapterFactory jdomFactory = new JavaJDOMAdapterFactory(ProjectUtilities.getJavaProject(project)) {
			protected void initializeSynchronizer() {
				synchronizer = new JavaReflectionSynchronizer(this) {
					// TODO push this up into JavaReflectionSynchronizer
					protected void processJavaElementChanged(ICompilationUnit element, IJavaElementDelta delta) {
						switch (delta.getKind()) {
							case IJavaElementDelta.CHANGED : {
								// A file save had occurred. It doesn't matter if currently working
								// copy or not.
								// It means something has changed to the file on disk, but don't
								// know what.
								if ((delta.getFlags() & IJavaElementDelta.F_PRIMARY_RESOURCE) != 0) {
									flush(element); // Flush everything, including inner classes.
								}
								break;
							}
							case IJavaElementDelta.REMOVED :
							case IJavaElementDelta.ADDED :
								disAssociateSourcePlusInner(getFullNameFromElement(element));
								break;
						}
					}
				};
			}
		};
		factories.add(jdomFactory);
	}

	/**
	 * Return an editing model used to read web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public WebServiceEditModel getWebServiceEditModelForRead(Object accessorKey) {
		return getWebServiceEditModelForRead(accessorKey, null);
	}

	/**
	 * Return an editing model used to read web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public WebServiceEditModel getWebServiceEditModelForRead(Object accessorKey, Map params) {
		return null;
	}

	/**
	 * Return an editing model used to edit web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public WebServiceEditModel getWebServiceEditModelForWrite(Object accessorKey) {
		return getWebServiceEditModelForWrite(accessorKey, null);
	}

	/**
	 * Return an editing model used to edit web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public WebServiceEditModel getWebServiceEditModelForWrite(Object accessorKey, Map params) {
		return null;
	}

}