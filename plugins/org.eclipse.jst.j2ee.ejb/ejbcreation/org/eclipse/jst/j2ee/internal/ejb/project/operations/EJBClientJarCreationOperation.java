/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerHelper;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationOperation;
import org.eclipse.jst.j2ee.application.operations.AddUtilityProjectToEARDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class EJBClientJarCreationOperation extends AbstractEJBClientJAROperation {
	protected String clientProjectName;
	protected String clientProjectPath;
	protected String clientJARRelativeURI;

	protected Set visitedJavaTypes;
	protected Map javaFilesToMove;
	protected int moveResourceCount = 0;
	protected Resource accessBeanResource = null;
	protected SearchEngine searchEngine;
	private MySearchHelper searchHelper;
	private Object validateContext;
	private EJBClientProjectDataModel ejbClientDataModel;

	private static class MySearchHelper extends SearchRequestor {
		SearchEngine engine;
		Collection results;
		Collection beanTypeNames;

		MySearchHelper(SearchEngine engine, Collection beanTypeNames) {
			this.engine = engine;
			this.beanTypeNames = beanTypeNames;
		}

		void searchForReferences(IType type, Collection new_results) throws JavaModelException {
			this.results = new_results;
			//Hack because of a bug in the search engine in eclipse 2.1.1
			try {
				engine.searchDeclarationsOfReferencedTypes(type, this, null);
			} catch (ClassCastException ex) {
				Logger.getLogger().logError(ex);
			}
		}

		public void acceptSearchMatch(SearchMatch match) throws CoreException {
			if (match.getAccuracy() == SearchMatch.A_ACCURATE && !(match.isInsideDocComment() || isBeanType((IType) match.getElement())))
				results.add(match.getElement());
		}

		/**
		 * @param type
		 * @return
		 */
		private boolean isBeanType(IType type) {
			return beanTypeNames.contains(type.getFullyQualifiedName());
		}

	}

	public EJBClientJarCreationOperation(EJBClientProjectDataModel operationDataModel) {
		super(operationDataModel);
		ejbClientDataModel = operationDataModel;
		setUseAnnotationsOnClientDataModel();
	}

	protected void initialize() {
		ejbProject = getEJBProject(ejbClientDataModel.getStringProperty(EJBClientProjectDataModel.EJB_PROJECT_NAME));
		clientProjectName = ejbClientDataModel.getNestedJavaProjectCreationDM().getStringProperty(JavaProjectCreationDataModel.PROJECT_NAME);
		clientJARRelativeURI = ejbClientDataModel.getStringProperty(EJBClientProjectDataModel.CLIENT_PROJECT_URI);
		clientProjectPath = ejbClientDataModel.getNestedJavaProjectCreationDM().getStringProperty(JavaProjectCreationDataModel.PROJECT_LOCATION);
		if (ejbProject == null)
			return;
		workspace = ejbProject.getWorkspace();
		ejbNature = (EJBNatureRuntime) EJBNatureRuntime.getRegisteredRuntime(ejbProject);
		if (clientJARRelativeURI == null)
			clientJARRelativeURI = clientProjectName + ClientJARCreationConstants.DOT_JAR; //$NON-NLS-1$
		clientProject = workspace.getRoot().getProject(clientProjectName);
		earNatures = ejbNature.getReferencingEARProjects();
		visitedJavaTypes = new HashSet();
		javaFilesToMove = new HashMap();
		searchEngine = new SearchEngine();
		searchHelper = new MySearchHelper(searchEngine, computeBeanTypeNames());
	}

	/**
	 * @return
	 */
	private Collection computeBeanTypeNames() {
		Collection names = null;
		EJBJar jar = ((EJBEditModel) editModel).getEJBJar();
		if (jar != null) {
			List beans = jar.getEnterpriseBeans();
			if (!beans.isEmpty()) {
				names = new HashSet(beans.size());
				for (int i = 0; i < beans.size(); i++) {
					EnterpriseBean bean = (EnterpriseBean) beans.get(i);
					names.add(bean.getEjbClassName());
				}
			}
		}
		if (names == null)
			names = Collections.EMPTY_LIST;
		return names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		monitor = aMonitor;
		initialize();
		if (!verifyFilesInSync()) {
			monitor.done();
			throw new OperationCanceledException();
		}
		monitor.beginTask(ClientJARCreationConstants.CREATING_CLIENT_JAR, 6);


		computeJavaTypes();
		if (!validateEditCheck())
			return;
		if (!ejbClientDataModel.hasExistingClientJar()) {
			updateDD();
			createProjectIfNecessary();
			addClientProjectToEARs();
			moveOutgoingJARDependencies();
			copyOutgoingClasspathEntries(ejbProject, clientProject, true);
			modifyEJBProjectJarDependency();
			moveIncomingJARDependencies();
		}
		EJBClientJarFileMoveDataModel moveModel = new EJBClientJarFileMoveDataModel();
		moveModel.setProperty(EJBClientJarFileMoveDataModel.EJB_PROJECT_NAME, ejbClientDataModel.getStringProperty(EJBClientProjectDataModel.EJB_PROJECT_NAME));
		moveModel.setProperty(EJBClientJarFileMoveDataModel.CLIENT_PROJECT_NAME, clientProjectName);
		moveModel.setProperty(EJBClientJarFileMoveDataModel.FILES_TO_MOVE_MAP, javaFilesToMove);
		EJBClientJarFileMoveOperation moveOp = new EJBClientJarFileMoveOperation(moveModel);
		moveOp.execute(monitor);
		//moveRequiredFiles();

	}

	/**
	 *  
	 */
	private void setUseAnnotationsOnClientDataModel() {
		if (AnnotationsControllerHelper.INSTANCE.hasAnnotationSupport(ejbProject))
			ejbClientDataModel.setBooleanProperty(EJBClientProjectDataModel.USE_ANNOTATIONS, true);
	}

	protected void addAdditionalFilesForValidateEdit(List roFiles) {
		if (javaFilesToMove == null)
			return;
		Iterator iter = javaFilesToMove.values().iterator();
		while (iter.hasNext()) {
			List readOnly = filterReadOnlyFiles((Collection) iter.next());
			roFiles.addAll(readOnly);
		}
	}


	/**
	 * Method getReadOnlyFiles.
	 * 
	 * @param javaClasses
	 * @return IFile[]
	 */
	private List filterReadOnlyFiles(Collection javaClasses) {
		if (javaClasses == null)
			return Collections.EMPTY_LIST;
		List files = new ArrayList(javaClasses.size());
		IFile file;
		Iterator iter = javaClasses.iterator();
		while (iter.hasNext()) {
			file = (IFile) iter.next();
			if (file != null && file.exists() && file.getType() == IResource.FILE && file.isReadOnly())
				files.add(file);
		}
		return files;
	}

	/**
	 * Update the deployment descriptor for the ejb project
	 */
	private void updateDD() {
		((EJBEditModel) editModel).addEJBJarIfNecessary();
		((EJBEditModel) editModel).getEJBJar().setEjbClientJar(clientJARRelativeURI);
	}

	private void createProjectIfNecessary() throws CoreException, InvocationTargetException, InterruptedException {
		if (clientProject.exists())
			return;

		JavaProjectCreationOperation op = new JavaProjectCreationOperation(ejbClientDataModel.getNestedJavaProjectCreationDM());
		IProgressMonitor subMonitor = createSubProgressMonitor(1);
		op.run(subMonitor);

		addServerTarget(new NullProgressMonitor());
	}

	protected void addServerTarget(IProgressMonitor progress_monitor) throws CoreException, InvocationTargetException, InterruptedException {
		IRuntime runtime = ServerCore.getProjectProperties(ejbProject).getRuntimeTarget();
		ServerCore.getProjectProperties(clientProject).setRuntimeTarget(runtime, progress_monitor);
	}

	private void addClientProjectToEARs() {
		for (int i = 0; i < earNatures.length; i++) {
			addClientProjectToEAR(earNatures[i]);
		}
	}

	private void addClientProjectToEAR(EARNatureRuntime runtime) {
		String ejbURI = runtime.getJARUri(ejbProject);
		String earRelativeClientURI = ArchiveUtil.deriveEARRelativeURI(clientJARRelativeURI, ejbURI);
		AddUtilityProjectToEARDataModel utilModel = new AddUtilityProjectToEARDataModel();
		utilModel.setProperty(AddUtilityProjectToEARDataModel.ARCHIVE_PROJECT, ejbClientDataModel.getNestedJavaProjectCreationDM().getProjectHandle(JavaProjectCreationDataModel.PROJECT_NAME));
		utilModel.setProperty(AddUtilityProjectToEARDataModel.ARCHIVE_URI, earRelativeClientURI);
		utilModel.setProperty(AddUtilityProjectToEARDataModel.PROJECT_NAME, runtime.getProject().getName());
		try {
			runNestedDefaultOperation(utilModel, monitor);
		} catch (Exception e) {
			Logger.getLogger().log(e);
		}
	}

	private void computeJavaTypes() throws CoreException {
		List enterpriseBeans = ejbNature.getEJBJar().getEnterpriseBeans();
		for (int i = 0; i < enterpriseBeans.size(); i++) {
			EnterpriseBean ejb = (EnterpriseBean) enterpriseBeans.get(i);
			computeJavaTypes(ejb);
		}
		computeRMICJavaTypes();
	}

	private void computeRMICJavaTypes() throws CoreException {
		List roots = getResourcePackageFragmentRoots();
		for (int i = 0; i < roots.size(); i++) {
			IPackageFragmentRoot root = (IPackageFragmentRoot) roots.get(i);
			computeRMICJavaTypes(root);
		}
	}

	private void computeRMICJavaTypes(IPackageFragmentRoot root) throws JavaModelException {
		IJavaElement[] pkgFragments = root.getChildren();
		IFile aFile = null;
		for (int i = 0; i < pkgFragments.length; i++) {
			IJavaElement[] elements = ((IPackageFragment) pkgFragments[i]).getChildren();
			for (int j = 0; j < elements.length; j++) {
				IJavaElement unit = elements[j];
				aFile = (IFile) unit.getUnderlyingResource();
				if (isRMICStub(aFile))
					cacheFile(root, aFile);
			}
		}
	}

	private boolean isRMICStub(IFile aFile) {
		String name = aFile.getName();
		String ext = aFile.getFileExtension();

		if (name != null && ext != null)
			name = name.substring(0, name.length() - ext.length() - 1);
		return name != null && name.startsWith(ClientJARCreationConstants.UNDERSCORE) && name.endsWith(ClientJARCreationConstants._STUB);
	}

	//Get all the roots that are folders in the project
	private List getResourcePackageFragmentRoots() throws JavaModelException {
		IJavaProject javaProj = ProjectUtilities.getJavaProject(ejbProject);
		List result = new ArrayList();
		IPackageFragmentRoot[] roots = javaProj.getPackageFragmentRoots();
		for (int i = 0; i < roots.length; i++) {
			IPackageFragmentRoot root = roots[i];
			if (root.getKind() == IPackageFragmentRoot.K_SOURCE || isClassesFolder(root))
				result.add(root);
		}
		return result;
	}

	private boolean isClassesFolder(IPackageFragmentRoot root) throws JavaModelException {
		return root.getKind() == IPackageFragmentRoot.K_BINARY && !root.isArchive() && !root.isExternal();
	}

	private void computeJavaTypes(EnterpriseBean ejb) {
		computeJavaTypes(ejb.getHomeInterface());
		computeJavaTypes(ejb.getRemoteInterface());
		computeJavaTypes(ejb.getLocalInterface());
		computeJavaTypes(ejb.getLocalHomeInterface());
		if (ejb.isEntity())
			computeJavaTypes(((Entity) ejb).getPrimaryKey());
		//computeAccessBeanJavaTypes(ejb);
	}


	private void computeJavaTypes(JavaClass javaClass) {
		if (javaClass == null)
			return;
		javaClass.isInterface();//force reflection prior to the move.
		IType type = EJBGenHelpers.getType(javaClass);
		computeJavaTypes(type);
	}

	private void computeJavaTypes(IType type) {
		if (type == null || visitedJavaTypes.contains(type))
			return;
		visitedJavaTypes.add(type);
		try {
			IFile file = (IFile) type.getUnderlyingResource();
			if (file != null && ejbProject.equals(file.getProject())) {
				if (!file.isDerived())
					cacheType(type, file);
				computeRequiredReferencedJavaTypes(type);
			}
		} catch (JavaModelException e) {
			Logger.getLogger().logError(e);
			return;
		}
	}

	/**
	 * @param type
	 */
	private void computeRequiredReferencedJavaTypes(IType type) throws JavaModelException {
		Collection result = new HashSet();
		searchHelper.searchForReferences(type, result);
		if (!result.isEmpty()) {
			Iterator iter = result.iterator();
			while (iter.hasNext())
				computeJavaTypes(((IType) iter.next()));
		}
	}

	private void cacheType(IType type, IFile file) {
		this.moveResourceCount++;
		IPackageFragmentRoot root = (IPackageFragmentRoot) type.getPackageFragment().getParent();
		cacheFile(root, file);
	}

	private void cacheFile(IPackageFragmentRoot root, IFile file) {
		Set files = (Set) javaFilesToMove.get(root);
		if (files == null) {
			files = new HashSet();
			javaFilesToMove.put(root, files);
		}
		files.add(file);
	}

	private void modifyEJBProjectJarDependency() throws InvocationTargetException, InterruptedException {
		JARDependencyDataModel dataModel = new JARDependencyDataModel();
		dataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, ejbProject.getName());
		dataModel.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, clientProject.getName());
		if (earNatures != null && earNatures.length > 0)
			dataModel.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[0].getProject().getName());
		dataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_ADD);
		JARDependencyOperation op = new JARDependencyOperation(dataModel);
		op.run(createSubProgressMonitor(1));
	}

	private void moveOutgoingJARDependencies() throws InvocationTargetException, InterruptedException {
		ArchiveManifest ejbMf = J2EEProjectUtilities.readManifest(ejbProject);
		if (ejbMf == null)
			return;
		String[] mfEntries = ejbMf.getClassPathTokenized();
		if (mfEntries.length == 0)
			return;
		IProgressMonitor sub = createSubProgressMonitor(earNatures.length * 2);
		for (int i = 0; i < earNatures.length; i++) {
			List normalized = normalize(mfEntries, earNatures[i]);
			JARDependencyDataModel addDataModel = new JARDependencyDataModel();
			addDataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_ADD);
			addDataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, clientProject.getName());
			addDataModel.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[i].getProject().getName());
			addDataModel.setProperty(JARDependencyDataModel.JAR_LIST, normalized);
			JARDependencyOperation addOp = new JARDependencyOperation(addDataModel);

			JARDependencyDataModel removeDataModel = new JARDependencyDataModel();
			removeDataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_REMOVE);
			removeDataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, ejbProject.getName());
			removeDataModel.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[i].getProject().getName());
			removeDataModel.setProperty(JARDependencyDataModel.JAR_LIST, normalized);
			JARDependencyOperation removeOp = new JARDependencyOperation(removeDataModel);

			addOp.run(new SubProgressMonitor(sub, 1));
			removeOp.run(new SubProgressMonitor(sub, 1));
		}
	}

	private List normalize(String[] mfEntries, EARNatureRuntime nature) {
		String jarURI = nature.getJARUri(ejbProject);
		if (jarURI == null)
			return Collections.EMPTY_LIST;

		List result = new ArrayList(mfEntries.length);
		for (int i = 0; i < mfEntries.length; i++) {
			String norm = ArchiveUtil.deriveEARRelativeURI(mfEntries[i], jarURI);
			if (norm == null)
				norm = mfEntries[i];
			//We know this is an implementation dependency so we don't want to
			// move it
			if (!SERVICE_LOCATOR_JAR_NAME.equals(norm))
				result.add(ArchiveUtil.deriveEARRelativeURI(mfEntries[i], jarURI));
		}
		return result;
	}

	private boolean validateEditCheck() {
		EJBEditModel ejbEditModel = (EJBEditModel) editModel;
		if (javaFilesToMove == null)
			return true;
		Iterator iter = javaFilesToMove.values().iterator();
		List roFiles = new ArrayList();
		while (iter.hasNext()) {
			List readOnly = filterReadOnlyFiles((Collection) iter.next());
			roFiles.addAll(readOnly);
		}
		List j2eeFiles = ejbEditModel.getReadOnlyAffectedFiles();
		if (j2eeFiles != null) {
			for (int i = 0; i < j2eeFiles.size(); i++)
				roFiles.add(j2eeFiles.get(i));
		}

		if (roFiles.size() == 0)
			return true;
		IFile[] files = (IFile[]) roFiles.toArray(new IFile[roFiles.size()]);
		IStatus new_status = ResourcesPlugin.getWorkspace().validateEdit(files, validateContext);
		return new_status.isOK();
	}

	private void moveIncomingJARDependencies() throws InvocationTargetException, InterruptedException {
		/*
		 * if (CommonEditorPlugin.getPlugin().getEJBClientJARCompatibilityPreference()) return;
		 */
		InvertClientJARDependencyCompoundOperation op = new InvertClientJARDependencyCompoundOperation(earNatures, ejbProject, clientProject);
		op.run(createSubProgressMonitor(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation#doDispose(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void doDispose(IProgressMonitor progress_monitor) {
		super.doDispose(progress_monitor);
		try {
			cleanProjects();
		} catch (CoreException e) {
			Logger.getLogger().log(e);
		}
	}

	protected void cleanProjects() throws CoreException {
		super.cleanProjects();
		cleanProject(clientProject);
	}
}