/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

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
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientJARCreationConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class EJBClientComponentCreationOperation extends WTPOperation {
	protected String ejbModule;
	protected String clientModuleName;
	protected String clientModulePath;
	protected String clientJARRelativeURI;

	protected Set visitedJavaTypes;
	protected Map javaFilesToMove;
	protected int moveResourceCount = 0;
	protected Resource accessBeanResource = null;
	protected SearchEngine searchEngine;
	private MySearchHelper searchHelper;
	private Object validateContext;
	private EJBClientComponentDataModel ejbClientDataModel;
	protected IProgressMonitor monitor;

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

	public EJBClientComponentCreationOperation(EJBClientComponentDataModel operationDataModel) {
		super(operationDataModel);
		ejbClientDataModel = operationDataModel;
		setUseAnnotationsOnClientDataModel();
	}

	protected void initialize() {
		ejbModule = ejbClientDataModel.getStringProperty(EJBClientComponentDataModel.EJB_COMPONENT_NAME);
		clientModuleName = ejbClientDataModel.getStringProperty(ComponentCreationDataModel.COMPONENT_NAME);
		clientJARRelativeURI = ejbClientDataModel.getStringProperty(EJBClientComponentDataModel.CLIENT_COMPONENT_URI);
		//clientModulePath = ejbClientDataModel.getNestedJavaProjectCreationDM().getStringProperty(ProjectCreationDataModel.PROJECT_LOCATION);
		if (ejbModule == null)
			return;
		

		if (clientJARRelativeURI == null)
			clientJARRelativeURI = clientModuleName + ClientJARCreationConstants.DOT_JAR; //$NON-NLS-1$
	

		visitedJavaTypes = new HashSet();
		javaFilesToMove = new HashMap();
		searchEngine = new SearchEngine();
		searchHelper = new MySearchHelper(searchEngine, computeBeanTypeNames());
	}

	/**
	 * @return
	 */
	private Collection computeBeanTypeNames() {
//		Collection names = null;
//		EJBJar jar = ((EJBEditModel) editModel).getEJBJar();
//		if (jar != null) {
//			List beans = jar.getEnterpriseBeans();
//			if (!beans.isEmpty()) {
//				names = new HashSet(beans.size());
//				for (int i = 0; i < beans.size(); i++) {
//					EnterpriseBean bean = (EnterpriseBean) beans.get(i);
//					names.add(bean.getEjbClassName());
//				}
//			}
//		}
//		if (names == null)
//			names = Collections.EMPTY_LIST;
//		return names;
		Collection names = null;
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
//		if (!verifyFilesInSync()) {
//			monitor.done();
//			throw new OperationCanceledException();
//		}
		monitor.beginTask(ClientJARCreationConstants.CREATING_CLIENT_JAR, 6);

		computeJavaTypes();
//		if (!validateEditCheck())
//			return;
		
		if (!ejbClientDataModel.hasExistingClientJar()) {
			updateDD();
			createModuleIfNecessary(aMonitor);
			addSrcFolderToProject();
			addClientModuleToEARs();
			moveOutgoingJARDependencies();
			modifyEJBModuleJarDependency(aMonitor);
			moveIncomingJARDependencies();
			
		}
//		EJBClientJarFileMoveDataModel moveModel = new EJBClientJarFileMoveDataModel();
//		moveModel.setProperty(EJBClientJarFileMoveDataModel.EJB_PROJECT_NAME, ejbClientDataModel.getStringProperty(EJBClientComponentDataModel.EJB_PROJECT_NAME));
//		moveModel.setProperty(EJBClientJarFileMoveDataModel.CLIENT_PROJECT_NAME, clientProjectName);
//		moveModel.setProperty(EJBClientJarFileMoveDataModel.FILES_TO_MOVE_MAP, javaFilesToMove);
//		EJBClientJarFileMoveOperation moveOp = new EJBClientJarFileMoveOperation(moveModel);
//		moveOp.execute(monitor);
		//moveRequiredFiles();

	}

	private void createModuleIfNecessary(IProgressMonitor aMonitor){
		try {
			createProjectStructure();
			createComponent( IModuleConstants.JST_EJB_MODULE, aMonitor);
		}
		catch (CoreException e) {
			Logger.getLogger().logError(e);
		}
		
	}
	
	protected  void createProjectStructure() throws CoreException{
		IFolder moduleFolder = getProject().getFolder( ejbClientDataModel.getStringProperty(ComponentCreationDataModel.COMPONENT_NAME));
		if (!moduleFolder.exists()) {
			moduleFolder.create(true, true, null);
		}
		IFolder ejbModuleFolder = moduleFolder.getFolder( "ejbModule" );
		if (!ejbModuleFolder.exists()) {
			ejbModuleFolder.create(true, true, null);
		}		
	}
	
	protected void createComponent( String moduletype, IProgressMonitor aMonitor )  {
    	ModuleCore moduleCore = null;
		try {
			IProject containingProject = getProject();
			moduleCore = ModuleCore.getModuleCoreForWrite(containingProject);
			moduleCore.prepareProjectModulesIfNecessary(); 
			ProjectComponents projectModules = moduleCore.getModuleModelRoot();
			
			addContent(projectModules, moduletype);
			moduleCore.saveIfNecessary(null); 
		} finally {
			if(moduleCore != null)
				moduleCore.dispose();
		}     
	}
	
	protected IProject getProject() {
		String projName = operationDataModel.getStringProperty(ComponentCreationDataModel.PROJECT_NAME );
		return ProjectUtilities.getProject( projName );
	}
	
	private void addContent(ProjectComponents projectModules, String moduletype) {

	    WorkbenchComponent module = addWorkbenchModule(projectModules, getModuleDeployName(), 
	    			moduletype, createModuleURI()); //$NON-NLS-1$

	    module.getComponentType().setVersion(getVersion());

	    addResources( module );
	}		
	protected WorkbenchComponent addWorkbenchModule(ProjectComponents theModules, String aDeployedName, 
				String moduletype, URI aHandle) {
		WorkbenchComponent module = ModuleCoreFactory.eINSTANCE.createWorkbenchComponent();
		module.setHandle(aHandle);  
		module.setName(aDeployedName);  
		ComponentType type = ModuleCoreFactory.eINSTANCE.createComponentType();
		type.setModuleTypeId(moduletype);
		module.setComponentType(type);
		theModules.getComponents().add(module);
		return module;
	}	
	
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(ComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getEJBTextVersion(version);
	}
	
	private URI createModuleURI() {
		return URI.createURI("module:/resource/"+getProject().getName()+IPath.SEPARATOR+ getModuleDeployName()); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * @return
	 */
	private String getModuleDeployName() {
		return (String)operationDataModel.getProperty(ComponentCreationDataModel.COMPONENT_DEPLOY_NAME);
	}

	protected  void addResources( WorkbenchComponent component ){
		addResource(component, getModuleRelativeFile(getJavaSourceSourcePath(), getProject()), getJavaSourceDeployPath());		
	}
		
	protected void addResource(WorkbenchComponent aModule, IResource aSourceFile, String aDeployPath) {
		ComponentResource resource = ModuleCoreFactory.eINSTANCE.createComponentResource();		
		resource.setSourcePath(URI.createURI(aSourceFile.getFullPath().toString()));
		resource.setRuntimePath(URI.createURI(aDeployPath));
		aModule.getResources().add(resource);
	}

	protected IFile getModuleRelativeFile(String aModuleRelativePath, IProject project) {
		return getProject().getFile(new Path(IPath.SEPARATOR + aModuleRelativePath));
	}
	
	protected String getJavaSourceSourcePath() {
		return getOperationDataModel().getStringProperty(EJBClientComponentDataModel.JAVASOURCE_FOLDER);
	}	
	
	/**
	 * @return
	 */
	protected String getJavaSourceDeployPath() {
		return "/"; //$NON-NLS-1$
	}
	
	
	private IClasspathEntry[] getClasspathEntries() {
		IClasspathEntry[] sourceEntries = null;
		sourceEntries = getSourceClasspathEntries();
		return sourceEntries;
	}	
	
	private IClasspathEntry[] getSourceClasspathEntries() {
		String sourceFolder = getJavaSourceSourcePath();
		ArrayList list = new ArrayList();
		list.add(JavaCore.newSourceEntry(getProject().getFullPath().append(sourceFolder)));
		
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];
		for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
		}
		return classpath;
	}
	
	private void addSrcFolderToProject() {
		IJavaProject javaProject = JavaCore.create( this.getProject());
		try {

			IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = getClasspathEntries();
			
			int oldSize = oldEntries.length;
			int newSize = newEntries.length;
			
			IClasspathEntry[] classpathEnties = new IClasspathEntry[oldSize + newSize];
			int k = 0;
			for (int i = 0; i < oldEntries.length; i++) {
				classpathEnties[i] = oldEntries[i];
				k++;
			}
			for( int j=0; j< newEntries.length; j++){
				classpathEnties[k] = newEntries[j];
				k++;
			}
			javaProject.setRawClasspath(classpathEnties, null);
		}
		catch (JavaModelException e) {
			Logger.getLogger().logError(e);
		}
	}		
	
	
	
	/**
	 *  
	 */
	private void setUseAnnotationsOnClientDataModel() {
//		if (AnnotationsControllerHelper.INSTANCE.hasAnnotationSupport(ejbModule))
//			ejbClientDataModel.setBooleanProperty(EJBClientComponentDataModel.USE_ANNOTATIONS, true);
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
//		((EJBEditModel) editModel).addEJBJarIfNecessary();
//		((EJBEditModel) editModel).getEJBJar().setEjbClientJar(clientJARRelativeURI);
	}



	private void addClientModuleToEARs() {
//		for (int i = 0; i < earNatures.length; i++) {
//			addClientProjectToEAR(earNatures[i]);
//		}
	}

	private void addClientModuleToEAR() {
//		String ejbURI = runtime.getJARUri(ejbProject);
//		String earRelativeClientURI = ArchiveUtil.deriveEARRelativeURI(clientJARRelativeURI, ejbURI);
//		AddUtilityProjectToEARDataModel utilModel = new AddUtilityProjectToEARDataModel();
//		//TODO: In the flexible world Clients are just JST_UTILITYs so we need to instead set ARCHIVE_MODULE to the name of the client
//		//utilModel.setProperty(AddArchiveToEARDataModel.ARCHIVE_PROJECT,  ProjectCreationDataModel.getProjectHandleFromProjectName(ejbClientDataModel.getNestedJavaProjectCreationDM().getStringProperty(ProjectCreationDataModel.PROJECT_NAME)));
//		utilModel.setProperty(AddArchiveToEARDataModel.ARCHIVE_URI, earRelativeClientURI);
//		utilModel.setProperty(EditModelOperationDataModel.PROJECT_NAME, runtime.getProject().getName());
//		try {
//			runNestedDefaultOperation(utilModel, monitor);
//		} catch (Exception e) {
//			Logger.getLogger().log(e);
//		}
	}

	private void computeJavaTypes() throws CoreException {
//		List enterpriseBeans = ejbNature.getEJBJar().getEnterpriseBeans();
//		for (int i = 0; i < enterpriseBeans.size(); i++) {
//			EnterpriseBean ejb = (EnterpriseBean) enterpriseBeans.get(i);
//			computeJavaTypes(ejb);
//		}
//		computeRMICJavaTypes();
	}

	private void computeRMICJavaTypes() throws CoreException {
//		List roots = getResourcePackageFragmentRoots();
//		for (int i = 0; i < roots.size(); i++) {
//			IPackageFragmentRoot root = (IPackageFragmentRoot) roots.get(i);
//			computeRMICJavaTypes(root);
//		}
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
//		computeJavaTypes(type);
	}

//	private void computeJavaTypes(IType type) {
//		if (type == null || visitedJavaTypes.contains(type))
//			return;
//		visitedJavaTypes.add(type);
//		try {
//			IFile file = (IFile) type.getUnderlyingResource();
//			if (file != null && ejbProject.equals(file.getProject())) {
//				if (!file.isDerived())
//					cacheType(type, file);
//				computeRequiredReferencedJavaTypes(type);
//			}
//		} catch (JavaModelException e) {
//			Logger.getLogger().logError(e);
//			return;
//		}
//	}
//
//	/**
//	 * @param type
//	 */
//	private void computeRequiredReferencedJavaTypes(IType type) throws JavaModelException {
//		Collection result = new HashSet();
//		searchHelper.searchForReferences(type, result);
//		if (!result.isEmpty()) {
//			Iterator iter = result.iterator();
//			while (iter.hasNext())
//				computeJavaTypes(((IType) iter.next()));
//		}
//	}

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

	private void modifyEJBModuleJarDependency(IProgressMonitor aMonitor) throws InvocationTargetException, InterruptedException {
		
		String clientDeployName = getOperationDataModel().getStringProperty(ComponentCreationDataModel.COMPONENT_DEPLOY_NAME );
		String ejbComponentName = getOperationDataModel().getStringProperty( EJBClientComponentDataModel.EJB_COMPONENT_NAME);
		String ejbprojectName = getOperationDataModel().getStringProperty(EJBClientComponentDataModel.EJB_PROJECT_NAME);
		String clientprojectName = getOperationDataModel().getStringProperty(ComponentCreationDataModel.PROJECT_NAME);
		String manifestFolder = ejbComponentName + IPath.SEPARATOR + "ejbModule"+IPath.SEPARATOR + J2EEConstants.META_INF;
		
		if( clientprojectName.equals( ejbprojectName )){
			UpdateManifestDataModel updateManifestDataModel = new UpdateManifestDataModel();
			updateManifestDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, ejbprojectName);
			updateManifestDataModel.setBooleanProperty(UpdateManifestDataModel.MERGE, false);
			updateManifestDataModel.setProperty(UpdateManifestDataModel.MANIFEST_FOLDER, manifestFolder);
			updateManifestDataModel.setProperty(UpdateManifestDataModel.JAR_LIST, UpdateManifestDataModel.convertClasspathStringToList(clientDeployName) );
			
			UpdateManifestOperation op = new UpdateManifestOperation(updateManifestDataModel);		
			op.run(aMonitor);
		}else{
	        JARDependencyDataModel dataModel = new JARDependencyDataModel();
	        dataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, ejbprojectName);
//	        dataModel.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, clientprojectName);
//	        if (earNatures !=null && earNatures.length>0)
//	        	dataModel.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[0].getProject().getName());
//	        dataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_ADD);
//	        JARDependencyOperation op = new JARDependencyOperation(dataModel);
//	        op.run(createSubProgressMonitor(1));			
		}
			
		
	}

	private void moveOutgoingJARDependencies() throws InvocationTargetException, InterruptedException {
		//TODO
	}



	private void moveIncomingJARDependencies() throws InvocationTargetException, InterruptedException {
		/*
		 * if (CommonEditorPlugin.getPlugin().getEJBClientJARCompatibilityPreference()) return;
		 */
//		InvertClientJARDependencyCompoundOperation op = new InvertClientJARDependencyCompoundOperation(earNatures, ejbProject, clientProject);
//		op.run(createSubProgressMonitor(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation#doDispose(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void doDispose(IProgressMonitor progress_monitor) {
//		super.doDispose(progress_monitor);
//		try {
//			cleanProjects();
//		} catch (CoreException e) {
//			Logger.getLogger().log(e);
//		}
	}

//	protected void cleanProjects() throws CoreException {
//		cleanProject(clientProject);
//	}
}