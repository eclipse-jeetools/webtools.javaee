/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.project;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.common.internal.modulecore.util.ManifestUtilities;
import org.eclipse.jst.common.jdt.internal.javalite.IJavaProjectLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaCoreLite;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.internal.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJavaProjectMigrationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaProjectMigrationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.JavaProjectMigrationOperation;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.server.core.FacetUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.SimpleValidateEdit;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.server.core.IRuntime;

public class J2EEProjectUtilities extends ProjectUtilities implements IJ2EEFacetConstants {

	/**
	 * Return the absolute path of a loose archive in a J2EE application or WAR file
	 */
	public static IPath getRuntimeLocation(IProject aProject) {
		if (JemProjectUtilities.isBinaryProject(aProject))
			return getBinaryProjectJARLocation(aProject);
		return JemProjectUtilities.getJavaProjectOutputAbsoluteLocation(aProject);
	}

	public static IPath getBinaryProjectJARLocation(IProject aProject) {
		List sources = JemProjectUtilities.getLocalJARPathsFromClasspath(aProject);
		if (!sources.isEmpty()) {
			IPath path = (IPath) sources.get(0);
			return aProject.getFile(path).getLocation();
		}
		return null;
	}

	public static Archive getClientJAR(EJBJarFile file, EARFile earFile) {
		EJBJar jar = null;
		try {
			jar = file.getDeploymentDescriptor();
		} catch (DeploymentDescriptorLoadException exc) {
			return null;
		}
		if (jar == null)
			return null;
		String clientJAR = jar.getEjbClientJar();
		if (clientJAR == null || clientJAR.length() == 0)
			return null;
		String normalized = ArchiveUtil.deriveEARRelativeURI(clientJAR, file.getURI());
		if (normalized != null) {
			try {
				File aFile = earFile.getFile(normalized);
				if (aFile.isArchive() && !aFile.isModuleFile())
					return (Archive) aFile;
			} catch (FileNotFoundException nothingThere) {
			}
		}
		return null;
		// TODO - release the DD here to free up space
	}

	/**
	 * Append one IClasspathEntry to the build path of the passed project. If a classpath entry
	 * having the same path as the parameter already exists, then does nothing.
	 */
	public static void appendJavaClassPath(IProject p, IClasspathEntry newEntry) throws JavaModelException {
		IJavaProject javaProject = JemProjectUtilities.getJavaProject(p);
		if (javaProject == null)
			return;
		IClasspathEntry[] classpath = javaProject.getRawClasspath();
		List newPathList = new ArrayList(classpath.length);
		for (int i = 0; i < classpath.length; i++) {
			IClasspathEntry entry = classpath[i];
			// fix dup class path entry for .JETEmitter project
			// Skip the entry to be added if it already exists
			if (Platform.getOS().equals(Platform.OS_WIN32)) {
				if (!entry.getPath().toString().equalsIgnoreCase(newEntry.getPath().toString()))
					newPathList.add(entry);
				else
					return;
			} else {
				if (!entry.getPath().equals(newEntry.getPath()))
					newPathList.add(entry);
				else
					return;
			}
		}
		newPathList.add(newEntry);
		IClasspathEntry[] newClasspath = (IClasspathEntry[]) newPathList.toArray(new IClasspathEntry[newPathList.size()]);
		javaProject.setRawClasspath(newClasspath, new NullProgressMonitor());
	}

	public static Archive asArchiveFromBinary(String jarUri, IProject aProject) throws OpenFailureException {
		IPath path = getBinaryProjectJARLocation(aProject);
		if (path != null) {
			String location = path.toOSString();
			Archive anArchive = CommonarchiveFactory.eINSTANCE.primOpenArchive(location);
			anArchive.setURI(jarUri);
			return anArchive;
		}
		return null;
	}

	/**
	 * @deprecated Use org.eclipse.jst.common.internal.modulecore.util.ManifestUtilities instead
	 * @param aFile
	 * @return
	 */
	public static ArchiveManifest readManifest(IFile aFile) {
		InputStream in = null;
		try {
			if (aFile == null || !aFile.exists())
				return null;
			in = aFile.getContents();
			return new ArchiveManifestImpl(in);
		} catch (Exception ex) {
			J2EEPlugin.logError(ex);
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException weTried) {
				}
			}
		}
	}

	public static ArchiveManifest readManifest(IProject p) {
		InputStream in = null;
		try {
			IFile aFile = getManifestFile(p);
			if (aFile == null || !aFile.exists())
				return null;
			in = aFile.getContents();
			return new ArchiveManifestImpl(in);
		} catch (Exception ex) {
			J2EEPlugin.logError(ex);
			return null;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException weTried) {
				}
			}
		}
	}

	/**
	 * Equavalent to calling getManifestFile(project, true)
	 * 
	 * @param p
	 * @return
	 */
	public static IFile getManifestFile(IProject project) {
		return getManifestFile(project, true);
	}

	/**
	 * Returns the IFile handle to the J2EE manifest file for the specified
	 * project. If createIfNecessary is true, the MANIFEST.MF file will be
	 * created if it does not already exist.
	 * 
	 * @param p
	 * @param createIfNecessary
	 * @return
	 */
	public static IFile getManifestFile(IProject p, boolean createIfNecessary) {
		IVirtualComponent component = ComponentCore.createComponent(p);
		try {
			IFile file = ComponentUtilities.findFile(component, new Path(J2EEConstants.MANIFEST_URI));
			if (createIfNecessary && file == null) {
				IVirtualFolder virtualFolder = component.getRootFolder();
				file = virtualFolder.getUnderlyingFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));

				try {
					ManifestFileCreationAction.createManifestFile(file, p);
				} catch (CoreException e) {
					J2EEPlugin.logError(e);
				} catch (IOException e) {
					J2EEPlugin.logError(e);
				}
			}
			return file;
		} catch (CoreException ce) {
			J2EEPlugin.logError(ce);
		}
		return null;
	}

	public static void writeManifest(IProject aProject, ArchiveManifest manifest) throws java.io.IOException {
		writeManifest(aProject, getManifestFile(aProject), manifest);
	}

	public static void writeManifest(IFile aFile, ArchiveManifest manifest) throws java.io.IOException {
		writeManifest(aFile.getProject(), aFile, manifest);
	}

	private static void writeManifest(IProject aProject, IFile aFile, ArchiveManifest manifest) throws java.io.IOException {
		if (aFile != null) {
			if(SimpleValidateEdit.validateEdit(new IFile[] { aFile })){
				ManifestUtilities.writeManifest(aFile, manifest);
				J2EEComponentClasspathUpdater.getInstance().queueUpdateModule(aProject);
			}
		}
	}
	
	public static ArchiveManifest readManifest(IVirtualComponent component) {
		if (!component.isBinary()) {
			IVirtualFile vManifest = component.getRootFolder().getFile(J2EEConstants.MANIFEST_URI);
			if (vManifest.exists()) {
				IFile manifestFile = vManifest.getUnderlyingFile();
				InputStream in = null;
				try {
					in = manifestFile.getContents();
					return new ArchiveManifestImpl(in);
				} catch (IOException e) {
					J2EEPlugin.logError(e);
				} catch (CoreException e) {
					J2EEPlugin.logError(e);
				} finally {
					if (in != null) {
						try {
							in.close();
							in = null;
						} catch (IOException e) {
							J2EEPlugin.logError(e);
						}
					}
				}
			}
		} else {
			JavaEEBinaryComponentHelper helper = null;
			try{
				helper = new JavaEEBinaryComponentHelper(component);
				IArchive archive = null;
				InputStream in = null;
				try{
					archive = helper.accessArchive();
					if(null != archive){
						IPath manifestPath = new Path(J2EEConstants.MANIFEST_URI);
						if(archive.containsArchiveResource(manifestPath)){
							IArchiveResource manifestResource = archive.getArchiveResource(manifestPath);
							if(manifestResource != null){
								in = manifestResource.getInputStream();
								ArchiveManifest manifest = new ArchiveManifestImpl(in);
								return manifest;
							}
						}
					}
				} catch (FileNotFoundException e) {
					J2EEPlugin.logError(e);
				} catch (IOException e) {
					J2EEPlugin.logError(e);
				} finally{
					if (in != null) {
						try {
							in.close();
							in = null;
						} catch (IOException e) {
							J2EEPlugin.logError(e);
						}
					}
					if(archive != null){
						helper.releaseArchive(archive);
					}
				}
			} finally{
				if(helper != null){
					helper.dispose();
				}
			}
		}
		return null;
	}
	/**
	 * Keys are the EJB JAR files and the values are the respective client JARs; includes only key
	 * value pairs for which EJB Client JARs are defined and exist.
	 * 
	 * @author schacher
	 */
	public static Map collectEJBClientJARs(EARFile earFile) {
		if (earFile == null)
			return Collections.EMPTY_MAP;
		Map ejbClientJARs = null;
		List ejbJARFiles = earFile.getEJBJarFiles();
		Archive clientJAR = null;
		for (int i = 0; i < ejbJARFiles.size(); i++) {
			EJBJarFile ejbJarFile = (EJBJarFile) ejbJARFiles.get(i);
			clientJAR = getClientJAR(ejbJarFile, earFile);
			if (clientJAR != null) {
				if (ejbClientJARs == null)
					ejbClientJARs = new HashMap();
				ejbClientJARs.put(ejbJarFile, clientJAR);
			}
		}
		return ejbClientJARs == null ? Collections.EMPTY_MAP : ejbClientJARs;
	}

	public static String computeRelativeText(String referencingURI, String referencedURI, EnterpriseBean bean) {
		if (bean == null)
			return null;

		String beanName = bean.getName();
		if (beanName == null)
			return null;

		String relativeUri = computeRelativeText(referencingURI, referencedURI);
		if (relativeUri == null)
			return beanName;
		return relativeUri + "#" + beanName; //$NON-NLS-1$
	}

	public static String computeRelativeText(String referencingURI, String referencedURI) {
		if (referencingURI == null || referencedURI == null)
			return null;
		IPath pPre = new Path(referencingURI);
		IPath pDep = new Path(referencedURI);
		if (pPre.getDevice() != null || pDep.getDevice() != null)
			return null;
		pPre = pPre.makeRelative();
		pDep = pDep.makeRelative(); // referenced Archive path URI

		while (pPre.segmentCount() > 1 && pDep.segmentCount() > 1 && pPre.segment(0).equals(pDep.segment(0))) {
			pPre = pPre.removeFirstSegments(1);
			pDep = pDep.removeFirstSegments(1);
		}

		IPath result = null;
		StringBuffer buf = new StringBuffer();
		String segment = null;
		do {
			segment = pDep.lastSegment();
			pPre = pPre.removeLastSegments(1);
			pDep = pDep.removeLastSegments(1);
			if (segment != null) {
				if (result == null)
					result = new Path(segment);
				else
					result = new Path(segment).append(result);
			}
			if (!pPre.equals(pDep) && !pPre.isEmpty())
				buf.append("../"); //$NON-NLS-1$
		} while (!pPre.equals(pDep));

		if (result != null)
			buf.append(result.makeRelative().toString());

		return buf.toString();
	}

	public static IProject getEJBProjectFromEJBClientProject(IProject ejbClientProject) {
		try {
			if (null != ejbClientProject && ejbClientProject.hasNature(JavaCore.NATURE_ID)) {
				IProject[] allProjects = getAllProjects();
				for (int i = 0; i < allProjects.length; i++) {
					if (null != EarModuleManager.getEJBModuleExtension().getEJBJar(allProjects[i])) {
						if (ejbClientProject == EarModuleManager.getEJBModuleExtension().getDefinedEJBClientJARProject(allProjects[i])) {
							return allProjects[i];
						}
					}
				}
			}
		} catch (CoreException e) {
		}
		return null;
	}

	public static EnterpriseBean getEnterpriseBean(ICompilationUnit cu) {
		IProject proj = cu.getJavaProject().getProject();
		EJBJar jar = EarModuleManager.getEJBModuleExtension().getEJBJar(proj);
		if (null == jar) {
			jar = EarModuleManager.getEJBModuleExtension().getEJBJar(getEJBProjectFromEJBClientProject(proj));
		}
		if (jar != null) {
			int index = cu.getElementName().indexOf('.');
			String className = cu.getElementName();
			if (index > 0)
				className = className.substring(0, index);
			JavaClass javaClass = (JavaClass) JavaRefFactory.eINSTANCE.reflectType(cu.getParent().getElementName(), className, jar.eResource().getResourceSet());
			return jar.getEnterpriseBeanWithReference(javaClass);
		}
		return null;
	}

	public static IContainer getSourceFolderOrFirst(IProject p, String defaultSourceName) {
		try {
			IPath sourcePath = getSourcePathOrFirst(p, defaultSourceName);
			if (sourcePath == null)
				return null;
			else if (sourcePath.isEmpty())
				return p;
			else
				return p.getFolder(sourcePath);
		} catch (IllegalArgumentException ex) {
			return null;
		}
	}

	public static void removeBuilders(IProject project, List builderids) throws CoreException {
		IProjectDescription desc = project.getDescription();
		ICommand[] oldSpec = desc.getBuildSpec();
		int oldLength = oldSpec.length;
		if (oldLength == 0)
			return;
		int remaining = 0;
		// null out all commands that match the builder to remove
		for (int i = 0; i < oldSpec.length; i++) {
			if (builderids.contains(oldSpec[i].getBuilderName()))
				oldSpec[i] = null;
			else
				remaining++;
		}
		// check if any were actually removed
		if (remaining == oldSpec.length)
			return;
		ICommand[] newSpec = new ICommand[remaining];
		for (int i = 0, newIndex = 0; i < oldLength; i++) {
			if (oldSpec[i] != null)
				newSpec[newIndex++] = oldSpec[i];
		}
		desc.setBuildSpec(newSpec);
		project.setDescription(desc, IResource.NONE, null);
	}

	public static IPath getSourcePathOrFirst(IProject p, String defaultSourceName) {
		IJavaProject javaProj = JemProjectUtilities.getJavaProject(p);
		if (javaProj == null)
			return null;
		IClasspathEntry[] cp = null;
		try {
			cp = javaProj.getRawClasspath();
		} catch (JavaModelException ex) {
			J2EEPlugin.logError(ex);
			return null;
		}
		IClasspathEntry firstSource = null;
		IPath defaultSourcePath = null;
		if (defaultSourceName != null)
			defaultSourcePath = createPath(p, defaultSourceName);
		boolean found = false;
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				// check if it contains /META-INF/MANIFEST.MF
				IPath sourceFolderPath = cp[i].getPath().removeFirstSegments(1);
				IFolder sourceFolder = p.getFolder(sourceFolderPath);
				if (isSourceFolderAnInputContainer(sourceFolder)) {
					found = true;
					if (firstSource == null) {
						firstSource = cp[i];
						if (defaultSourcePath == null)
							break;
					}
					if (cp[i].getPath().equals(defaultSourcePath) && defaultSourcePath != null)
						return defaultSourcePath.removeFirstSegments(1);
				}
			}
		}
		if (!found) {
			for (int i = 0; i < cp.length; i++) {
				if (cp[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					if (firstSource == null) {
						firstSource = cp[i];
						if (defaultSourcePath == null)
							break;
					}
					if (cp[i].getPath().equals(defaultSourcePath) && defaultSourcePath != null)
						return defaultSourcePath.removeFirstSegments(1);
				}
			}
		}
		if (firstSource == null)
			return null;
		if (firstSource.getPath().segment(0).equals(p.getName()))
			return firstSource.getPath().removeFirstSegments(1);
		return null;
	}

	public static boolean isSourceFolderAnInputContainer(IFolder sourceFolder) {
		IContainer parent = sourceFolder;
		while (true) {
			parent = parent.getParent();
			if (parent == null)
				return false;
			if (parent instanceof IProject)
				break;
		}
		IProject project = (IProject) parent;
		try {
			if (!project.isAccessible())
				return false;
			if (JavaEEProjectUtilities.isEJBProject(project)) {
				return sourceFolder.findMember(J2EEConstants.EJBJAR_DD_URI) != null;
			} else if (JavaEEProjectUtilities.isApplicationClientProject(project)) {
				return sourceFolder.findMember(J2EEConstants.APP_CLIENT_DD_URI) != null;
			} else if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
				return sourceFolder.findMember(J2EEConstants.WEBAPP_DD_URI) != null;
			} else if (JavaEEProjectUtilities.isJCAProject(project)) {
				return sourceFolder.findMember(J2EEConstants.RAR_DD_URI) != null;
			}
		} catch (Exception e) {
			J2EEPlugin.logError(e);
		}
		return false;
	}

	public static Archive asArchive(String jarUri, IProject project, boolean exportSource) throws OpenFailureException {
		return asArchive(jarUri, project, exportSource, true);		
	}
	
	public static Archive asArchive(String jarUri, IProject project, boolean exportSource, boolean includeClasspathComponents) throws OpenFailureException {
		JavaComponentLoadStrategyImpl strat = new JavaComponentLoadStrategyImpl(ComponentCore.createComponent(project), includeClasspathComponents);
		strat.setExportSource(exportSource);
		return CommonarchiveFactory.eINSTANCE.primOpenArchive(strat, jarUri);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isProjectOfType(IProject project, String typeID)}
	 */
	public static boolean isProjectOfType(IProject project, String typeID) {
		return JavaEEProjectUtilities.isProjectOfType(project,typeID);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isEARProject(IProject project)}
	 */
	public static boolean isEARProject(IProject project) {
		return JavaEEProjectUtilities.isEARProject(project);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isDynamicWebComponent(IVirtualComponent component)}
	 */
	public static boolean isDynamicWebComponent(IVirtualComponent component) {
		return JavaEEProjectUtilities.isDynamicWebComponent(component);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isDynamicWebProject(IProject project)}
	 */
	public static boolean isDynamicWebProject(IProject project) {
		return JavaEEProjectUtilities.isDynamicWebProject(project);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isStaticWebProject(IProject project)}
	 */
	public static boolean isStaticWebProject(IProject project) {
		return JavaEEProjectUtilities.isStaticWebProject(project);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isEJBComponent(IVirtualComponent component)}
	 */
	public static boolean isEJBComponent(IVirtualComponent component) {
		return JavaEEProjectUtilities.isEJBComponent(component);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isEJBProject(IProject project)}
	 */
	public static boolean isEJBProject(IProject project) {
		return JavaEEProjectUtilities.isEJBProject(project);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isJCAComponent(IVirtualComponent component)}
	 */
	public static boolean isJCAComponent(IVirtualComponent component) {
		return JavaEEProjectUtilities.isJCAComponent(component);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isJCAProject(IProject project)}
	 */
	public static boolean isJCAProject(IProject project) {
		return JavaEEProjectUtilities.isJCAProject(project);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isApplicationClientComponent(IVirtualComponent component)}
	 */
	public static boolean isApplicationClientComponent(IVirtualComponent component) {
		return JavaEEProjectUtilities.isApplicationClientComponent(component);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isApplicationClientProject(IProject project)}
	 */
	public static boolean isApplicationClientProject(IProject project) {
		return JavaEEProjectUtilities.isApplicationClientProject(project);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.isUtilityProject(IProject project)}
	 */
	public static boolean isUtilityProject(IProject project) {
		return JavaEEProjectUtilities.isUtilityProject(project);
	}

	/**
	 * @deprecated - see {@link EarUtilities.isStandaloneProject(IProject project)}
	 */
	public static boolean isStandaloneProject(IProject project) {
		return EarUtilities.isStandaloneProject(project);
	}
	
	
	/**
	 * @deprecated use {@link EarUtilities#getReferencingEARProjects(IProject)}
	 * 
	 */
	public static IProject[] getReferencingEARProjects(final IProject project) {
		return EarUtilities.getReferencingEARProjects(project);
	}
	
	/**
	 * Returns all referencing dynamic web projects.
	 * @param project Project to check. If null or a dynamic web project, returns a zero length array.
	 * @return Array of referencing dynamic web projects.
	 */
	public static IProject[] getReferencingWebProjects(final IProject project) {
		if(project != null && JavaEEProjectUtilities.isDynamicWebProject(project)){
			return new IProject[] {project};
		}
		
		List result = new ArrayList();
		IVirtualComponent component = ComponentCore.createComponent(project);
		if (component != null) {
			IVirtualComponent[] refComponents = component.getReferencingComponents();
			for (int i = 0; i < refComponents.length; i++) {
				if (JavaEEProjectUtilities.isDynamicWebProject(refComponents[i].getProject()))
					result.add(refComponents[i].getProject());
			}
		}
		return (IProject[]) result.toArray(new IProject[result.size()]);	
	}

	/**
	 * Return all projects in workspace of the specified type
	 * 
	 * @param type -
	 *            use one of the static strings on this class as a type
	 * @return IProject[]
	 * @deprecated - see {@link JavaEEProjectUtilities.getAllProjectsInWorkspaceOfType(String type)}
	 */
	public static IProject[] getAllProjectsInWorkspaceOfType(String type) {
		return JavaEEProjectUtilities.getAllProjectsInWorkspaceOfType(type);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.getJ2EEComponentType(IVirtualComponent component)}
	 */
	public static String getJ2EEComponentType(IVirtualComponent component) {
		return JavaEEProjectUtilities.getJ2EEComponentType(component);
	}

	/**
	 * @deprecated - see {@link JavaEEProjectUtilities.getJ2EEProjectType(IProject project)}
	 */
	public static String getJ2EEProjectType(IProject project) {
		return JavaEEProjectUtilities.getJ2EEProjectType(project);
	}
	/**
	 * Returns the J2EE Module version based on the DD XML file
	 * @param project
	 * @return version String
	 * @deprecated - see {@link JavaEEProjectUtilities.getJ2EEDDProjectVersion(IProject project)}
	 */
	public static String getJ2EEDDProjectVersion(IProject project) {
		return JavaEEProjectUtilities.getJ2EEDDProjectVersion(project);
	}

	public static IRuntime getServerRuntime(IProject project) throws CoreException {
		if (project == null)
			return null;
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		if (facetedProject == null)
			return null;
		org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime = facetedProject.getRuntime();
		if (runtime == null)
			return null;
		return FacetUtil.getRuntime(runtime);
	}

	/**
	 * Returns the J2EE Module version based on the project Facet installed
	 * @param project
	 * @return version String
	 */
	public static String getJ2EEProjectVersion(IProject project) {
		String type = getJ2EEProjectType(project);
		IFacetedProject facetedProject = null;
		IProjectFacet facet = null;
		try {
			facetedProject = ProjectFacetsManager.create(project);
			facet = ProjectFacetsManager.getProjectFacet(type);
		} catch (Exception e) {
			// Not Faceted project or not J2EE Project
		}
		if (facet != null && facetedProject != null && facetedProject.hasProjectFacet(facet))
			return facetedProject.getInstalledVersion(facet).getVersionString();
		return null;
	}

	public static JavaProjectMigrationOperation createFlexJavaProjectForProjectOperation(IProject project) {
		IDataModel model = DataModelFactory.createDataModel(new JavaProjectMigrationDataModelProvider());
		model.setProperty(IJavaProjectMigrationDataModelProperties.PROJECT_NAME, project.getName());
		return new JavaProjectMigrationOperation(model);
	}
	
	public static JavaProjectMigrationOperation createFlexJavaProjectForProjectOperation(IProject project, boolean addToEAR) {
		IDataModel model = DataModelFactory.createDataModel(new JavaProjectMigrationDataModelProvider());
		model.setProperty(IJavaProjectMigrationDataModelProperties.PROJECT_NAME, project.getName());
		model.setBooleanProperty(IJavaProjectMigrationDataModelProperties.ADD_TO_EAR, addToEAR);
		return new JavaProjectMigrationOperation(model);
	}

	/**
	 * @deprecated use {@link JavaLiteUtilities#getJavaSourceContainers(IVirtualComponent)}
	 * @param project
	 * @return
	 */
	public static IPackageFragmentRoot[] getSourceContainers(IProject project) {
		IJavaProject jProject = JemProjectUtilities.getJavaProject(project);
		if (jProject == null)
			return new IPackageFragmentRoot[0];
		List list = new ArrayList();
		IVirtualComponent vc = ComponentCore.createComponent(project);
		IPackageFragmentRoot[] roots;
		try {
			roots = jProject.getPackageFragmentRoots();
			for (int i = 0; i < roots.length; i++) {
				if (roots[i].getKind() != IPackageFragmentRoot.K_SOURCE)
					continue;
				IResource resource = roots[i].getResource();
				if (null != resource) {
					IVirtualResource[] vResources = ComponentCore.createResources(resource);
					boolean found = false;
					for (int j = 0; !found && j < vResources.length; j++) {
						if (vResources[j].getComponent().equals(vc)) {
							if (!list.contains(roots[i]))
								list.add(roots[i]);
							found = true;
						}
					}
				}
			}
		} catch (JavaModelException e) {
			J2EEPlugin.logError(e);
		}
		return (IPackageFragmentRoot[]) list.toArray(new IPackageFragmentRoot[list.size()]);
	}
	
	/**
	 * @deprecated use {@link JavaLiteUtilities#getJavaOutputContainers(IVirtualComponent)} 
	 * @param project
	 * @return
	 */
	public static IContainer[] getOutputContainers(IProject project) {
		IVirtualComponent virtualComponent = ComponentCore.createComponent(project);
		List <IContainer> containers = JavaLiteUtilities.getJavaOutputContainers(virtualComponent);
		return containers.toArray(new IContainer[containers.size()]);
	}

	/**
	 * @deprecated use {@link JavaLiteUtilities#getJavaOutputContainer(IJavaProjectLite, IClasspathEntry)} 
	 * 
	 * { @link #getJavaOutputContainer(IJavaProjectLite, IClasspathEntry)}
	 * @param project
	 * @param sourceContainer
	 * @return
	 */
	public static IContainer getOutputContainer(IProject project, IPackageFragmentRoot sourceContainer) {
		try {
			return JavaLiteUtilities.getJavaOutputContainer(JavaCoreLite.create(project), sourceContainer.getRawClasspathEntry());
		} catch (JavaModelException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}
		return null;
	}

	/**
	 * @deprecated use {@link JavaLiteUtilities#getJavaOutputContainers(IVirtualComponent)}
	 * @param project
	 * @return
	 */
	public static IContainer[] getAllOutputContainers(IProject project) {
		return getOutputContainers(project);
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 * @description the passed name should have either lib or var as its first segment e.g.
	 *              lib/D:/foo/foo.jar or var/<CLASSPATHVAR>/foo.jar
	 */
	public static IPath getResolvedPathForArchiveComponent(String name) {

		URI uri = URI.createURI(name);

		String resourceType = uri.segment(0);
		URI contenturi = ModuleURIUtil.trimToRelativePath(uri, 1);
		String contentName = contenturi.toString();

		if (resourceType.equals("lib")) { //$NON-NLS-1$
			// module:/classpath/lib/D:/foo/foo.jar
			return Path.fromOSString(contentName);

		} else if (resourceType.equals("var")) { //$NON-NLS-1$

			// module:/classpath/var/<CLASSPATHVAR>/foo.jar
			String classpathVar = contenturi.segment(0);
			URI remainingPathuri = ModuleURIUtil.trimToRelativePath(contenturi, 1);
			String remainingPath = remainingPathuri.toString();

			String[] classpathvars = JavaCore.getClasspathVariableNames();
			boolean found = false;
			for (int i = 0; i < classpathvars.length; i++) {
				if (classpathVar.equals(classpathvars[i])) {
					found = true;
					break;
				}
			}
			if (found) {
				IPath path = JavaCore.getClasspathVariable(classpathVar);
				if (path != null ){
					URI finaluri = URI.createURI(path.toOSString() + IPath.SEPARATOR + remainingPath);
					return Path.fromOSString(finaluri.toString());
				}
			}
		}
		return null;
	}

	public static List getAllJavaNonFlexProjects() throws CoreException {
		List nonFlexJavaProjects = new ArrayList();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
		for (int i = 0; i < projects.length; i++) {
			if (projects[i].isAccessible() && projects[i].hasNature(JavaCore.NATURE_ID) && !projects[i].hasNature(IModuleConstants.MODULE_NATURE_ID)) {
				nonFlexJavaProjects.add(projects[i]);
			}
		}
		return nonFlexJavaProjects;
	}

	/**
	 * This method will retrieve the context root for the associated workbench module which is used
	 * by the server at runtime. This method is not yet completed as the context root has to be
	 * abstracted and added to the workbenchModule model. This API will not change though. Returns
	 * null for now.
	 * 
	 * @return String value of the context root for runtime of the associated module
	 */
	public static String getServerContextRoot(IProject project) {
		return ComponentUtilities.getServerContextRoot(project);
	}

	/**
	 * This method will set the context root on the associated workbench module with the given
	 * string value passed in. This context root is used by the server at runtime. This method is
	 * not yet completed as the context root still needs to be abstracted and added to the workbench
	 * module model. This API will not change though. Does nothing as of now.
	 * 
	 * @param contextRoot
	 *            string
	 */
	public static void setServerContextRoot(IProject project, String contextRoot) {
		ComponentUtilities.setServerContextRoot(project, contextRoot);
	}
	
	/**
	 * @param project
	 * @return true, if jee version 5.0 or later (or their respective ejb, web, app versions)
	 * , it must be noted that this method only looks at the facet & their versions to determine 
	 * the jee level. It does not read deployment descriptors for performance reasons.
	 */
	public static boolean isJEEProject(IProject project){
		IVirtualComponent component = ComponentCore.createComponent(project);
		if(component != null)
			 return JavaEEProjectUtilities.isJEEComponent(component);
		return false;
	}
	
	/**
	 * @param project
	 * @return true, if j2ee version 1.2, 1.3, 1.4 (or their respective ejb, web, app versions)
	 * , it must be noted that this method only looks at the facet & their versions to determine 
	 * the j2ee level. It does not read deployment descriptors for performance reasons.
	 * @deprecated use JavaEEProjectUtilities.isLegacyJ2EEComponent(etc)
	 */
	public static boolean isLegacyJ2EEProject(IProject project){
		IVirtualComponent component = ComponentCore.createComponent(project);
		if(component != null)
			return JavaEEProjectUtilities.isLegacyJ2EEComponent(component);
		return false;
	}

	/**
	 * This method will return the an IVirtualComponent for the given module name. The method take
	 * either moduleName or moduleName + ".module_extension" (module_extension = ".jar" || ".war" ||
	 * ".rar") which allows users to get a IVirtualComponent for a given entry in an application.xml
	 * 
	 * @return - a IVirtualComponent for module name
	 * @deprecated - see {@link EarUtilities.getModule(IVirtualComponent earComponent, String moduleName)}
	 */
	public static IVirtualComponent getModule(IVirtualComponent earComponent, String moduleName) {
		return EarUtilities.getModule(earComponent, moduleName);
	}

	/**
	 * This method will return the list of IVirtualReferences for the J2EE module components
	 * contained in this EAR application.
	 * 
	 * @return - an array of IVirtualReferences for J2EE modules in the EAR
	 * @deprecated - see {@link EarUtilities.getJ2EEModuleReferences(IVirtualComponent earComponent)}
	 */
	public static IVirtualReference[] getJ2EEModuleReferences(IVirtualComponent earComponent) {
		return EarUtilities.getJ2EEModuleReferences(earComponent);
	}

	/**
	 * This method will return the list of IVirtualReferences for all of the components contained in
	 * an EAR application.
	 * 
	 * @return - an array of IVirtualReferences for components in the EAR
	 * @deprecated - see {@link EarUtilities.getComponentReferences(IVirtualComponent earComponent)}
	 */
	public static IVirtualReference[] getComponentReferences(IVirtualComponent earComponent) {
		return EarUtilities.getComponentReferences(earComponent);
	}

	/**
	 * This method will return the IVirtualReference to the component of the given name
	 * 
	 * @return - IVirtualReference or null if not found
	 * @deprecated - see {@link EarUtilities.getComponentReference(IVirtualComponent earComponent, String componentName)}
	 */
	public static IVirtualReference getComponentReference(IVirtualComponent earComponent, String componentName) {
		return EarUtilities.getComponentReference(earComponent, componentName);
	}
}
