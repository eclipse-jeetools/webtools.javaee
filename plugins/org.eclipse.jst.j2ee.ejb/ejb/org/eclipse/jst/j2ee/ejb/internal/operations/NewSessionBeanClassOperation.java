/*******************************************************************************
 * Copyright (c) 2007, 2012 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 * Roberto Sanchez, rsanchez@mx1.ibm.com - Allow more flexibility for source folder in EJB client
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.project.EJBUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;

/**
 * The NewSessionBeanClassOperation is an IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends ArtifactEditProviderOperation to provide enterprise bean specific java
 * class generation.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * This operation is used by the AddSessionBeanOperation to generate an
 * non annotated java class for an added enterprise bean. It shares the
 * NewSessionBeanClassDataModelProvider with the AddSessionBeanOperation to store the
 * appropriate properties required to generate the new enterprise bean.
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.AddSessionBeanOperation
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.NewSessionBeanClassDataModelProvider
 * 
 * A WTPJetEmitter bean template is used to create the class with the bean template. 
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.CreateSessionBeanTemplateModel
 * 
 * Subclasses may extend this operation to provide their own specific bean
 * java class generation. The execute method may be extended to do so. Also,
 * generateUsingTemplates is exposed.
 * 
 */
public class NewSessionBeanClassOperation extends NewEnterpriseBeanClassOperation {

	/**
	 * folder location of the enterprise bean creation templates directory
	 */
	protected static final String TEMPLATE_FILE = "/templates/sessionBean.javajet"; //$NON-NLS-1$

	protected static final String TEMPLATE_LOCAL_FILE = "/templates/localBusinessInterface.javajet"; //$NON-NLS-1$
	protected static final String TEMPLATE_REMOTE_FILE = "/templates/remoteBusinessInterface.javajet"; //$NON-NLS-1$

	protected static final String TEMPLATE_LOCALHOME_FILE = "/templates/localHomeInterface.javajet"; //$NON-NLS-1$
	protected static final String TEMPLATE_REMOTEHOME_FILE = "/templates/remoteHomeInterface.javajet"; //$NON-NLS-1$

	protected static final String TEMPLATE_LOCALCOMPONENT_FILE = "/templates/localComponentInterface.javajet"; //$NON-NLS-1$
	protected static final String TEMPLATE_REMOTECOMPONENT_FILE = "/templates/remoteComponentInterface.javajet"; //$NON-NLS-1$
	
	protected IPackageFragment clientPack;
	protected IPackageFragment pack;
	
	/**
	 * This is the constructor which should be used when creating a
	 * NewSessionBeanClassOperation. An instance of the NewSessionBeanClassDataModelProvider
	 * should be passed in. This does not accept null parameter. It will not
	 * return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewBeansClassDataModel
	 * 
	 * @param dataModel
	 * @return NewBeanClassOperation
	 */
	public NewSessionBeanClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Subclasses may extend this method to add their own actions during
	 * execution. The implementation of the execute method drives the running of
	 * the operation. This implementation will create the java source folder,
	 * create the java package, and then the enterprise bean java class file will be created 
	 * using templates. Optionally, subclasses may extend the
	 * generateUsingTemplates or createJavaFile method rather than extend the
	 * execute method. This method will accept a null parameter.
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 * @see NewSessionBeanClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @param monitor
	 * @throws CoreException
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	@Override
	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// Retrieve the package name from the java class data model
		String packageName = model.getStringProperty(JAVA_PACKAGE);
		// Create java package if it does not exist
		pack = createJavaPackage(packageName);
		if (hasInterfacesToGenerate() && EJBUtilities.hasEJBClientJARProject(getTargetProject())) {					
			IFolder folder = getClientSourceFolder();
			if (!folder.exists()){
				// If the source folder used by the EJB project does not exist in the
				// client project, try to use the first source folder available
				folder = getFirstJavaSourceFolderInClientJar();
			
				if (folder == null) {
					// If the is no source folder available, create the source
					// folder used by the EJB project.
					createJavaSourceFolderInClientJar();
				}
			}
			clientPack = createJavaPackageInClientJar(packageName);
		}
		
		return super.doExecute(monitor, info);
	}

	@Override
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) 
			throws WFTWrappedException, CoreException {
		this.generateUsingTemplates(monitor, pack, clientPack);
	}

	/**
	 * Subclasses may extend this method to provide their own template based
	 * creation of an annotated bean java class file. This implementation uses
	 * the creation of a CreateSessionBeanTemplateModel and the WTPJetEmitter to
	 * create the java class with the annotated tags. This method accepts null
	 * for monitor, it does not accept null for fragment. If annotations are not
	 * being used the tags will be omitted from the class.
	 * 
	 * @see CreateSessionBeanTemplateModel
	 * @see NewSessionBeanClassOperation#generateTemplateSource(CreateSessionBeanTemplateModel,
	 *      IProgressMonitor)
	 * 
	 * @param monitor
	 * @param fragment
	 * @throws CoreException
	 * @throws WFTWrappedException
	 */
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment, IPackageFragment clientFragment) throws WFTWrappedException, CoreException {
		// Create the enterprise bean template model
		CreateSessionBeanTemplateModel tempModel = createTemplateModel();
		getTargetProject();
		// Using the WTPJetEmitter, generate the java source based on the bean template model
		try {
			if (fragment != null) {
				if (hasInterfacesToGenerate() && EJBUtilities.hasEJBClientJARProject(getTargetProject())) {
					generateInterfacesUsingTemplates(monitor, clientFragment, tempModel);
				} else {
					generateInterfacesUsingTemplates(monitor, fragment, tempModel);
				}
				
				// Create the session bean java file
				SessionBeanTemplate tempImpl = SessionBeanTemplate.create(null);
				String source = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_FILE, tempImpl, monitor);
				String javaFileName = tempModel.getClassName() + DOT_JAVA;
				createJavaFile(monitor, fragment, source, javaFileName);
			}
		} catch (Exception e) {
			throw new WFTWrappedException(e);
		}
	}
	
	protected void generateInterfacesUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment, CreateSessionBeanTemplateModel tempModel) 
			throws JETException, JavaModelException {
		IProject project = getTargetProject();
		boolean useClientJar = false;
		if (JavaEEProjectUtilities.isEJBProject(project))
		{
			useClientJar = EJBUtilities.hasEJBClientJARProject(getTargetProject());
		}
		List<BusinessInterface> interfaces = tempModel.getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			if (!iface.exists()) {
				tempModel.setCurrentBusinessInterface(iface);
				if (iface.isLocal()) {
					// Create the java files for the non-exising Local Business interfaces
					LocalBusinessInterfaceTemplate tempImpl = LocalBusinessInterfaceTemplate.create(null);
					String src = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_LOCAL_FILE, tempImpl, monitor);
					String fileName = iface.getSimpleName() + DOT_JAVA;
					createJavaFile(monitor, getPackageFragment(useClientJar, iface.getFullyQualifiedName()), src, fileName);
				} else if (iface.isRemote()) {
					// Create the java files for the non-exising Remote Business interfaces
					RemoteBusinessInterfaceTemplate tempImpl = RemoteBusinessInterfaceTemplate.create(null);
					String src = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_REMOTE_FILE, tempImpl, monitor);
					String fileName = iface.getSimpleName() + DOT_JAVA;
					createJavaFile(monitor, getPackageFragment(useClientJar, iface.getFullyQualifiedName()), src, fileName);
				}
			}
		}

		// Create the EJB 2.x compatible Remote Home and Component interface java files
		if (model.getBooleanProperty(REMOTE_HOME)) {
			String remoteFullName =  model.getStringProperty(INewSessionBeanClassDataModelProperties.REMOTE_HOME_INTERFACE);
			String remoteComponentFullName = model.getStringProperty(INewSessionBeanClassDataModelProperties.REMOTE_COMPONENT_INTERFACE);
			
			IJavaProject javaProject = JavaCore.create(project);
			IType type = javaProject.findType(remoteFullName);
			
			IPackageFragment remoteFragment = null;
			String fileName = Signature.getSimpleName(remoteFullName)+ DOT_JAVA;
			
			tempModel.setRemoteHomeClassName(remoteFullName);
			tempModel.setRemoteComponentClassName(remoteComponentFullName);
			String src = ""; //$NON-NLS-1$
			if(type == null){
				remoteFragment = getPackageFragment(useClientJar, remoteFullName);
				RemoteHomeInterfaceTemplate tempImpl = RemoteHomeInterfaceTemplate.create(null);
				src = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_REMOTEHOME_FILE, tempImpl, monitor);
				createJavaFile(monitor, remoteFragment, src, fileName);
			}
			
			String fileComponentName = Signature.getSimpleName(remoteComponentFullName)+ DOT_JAVA;			
			
			type = javaProject.findType(remoteComponentFullName);
			if(type == null){
				IPackageFragment remoteComponentFragment = getPackageFragment(useClientJar, remoteComponentFullName);
				RemoteComponentInterfaceTemplate tempImpl = RemoteComponentInterfaceTemplate.create(null);
				src = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_REMOTECOMPONENT_FILE, tempImpl, monitor);
				createJavaFile(monitor, remoteComponentFragment, src, fileComponentName);
			}
		}
		
		// Create the EJB 2.x compatible Local Home and Component interface java files
		if (model.getBooleanProperty(LOCAL_HOME)) {
			String localFullName =  model.getStringProperty(INewSessionBeanClassDataModelProperties.LOCAL_HOME_INTERFACE);
			String localComponentFullName = model.getStringProperty(INewSessionBeanClassDataModelProperties.LOCAL_COMPONENT_INTERFACE);
			
			IJavaProject javaProject = JavaCore.create(project);
			IType type = javaProject.findType(localFullName);			
			
			IPackageFragment localFragment = null;
			String fileName = Signature.getSimpleName(localFullName)+ DOT_JAVA;
			tempModel.setLocalHomeClassName(localFullName);
			tempModel.setLocalComponentClassName(localComponentFullName);
			
			String src = ""; //$NON-NLS-1$
			if(type == null){
				localFragment = getPackageFragment(useClientJar, localFullName);
				LocalHomeInterfaceTemplate tempImpl = LocalHomeInterfaceTemplate.create(null);
				src = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_LOCALHOME_FILE, tempImpl, monitor);
				createJavaFile(monitor, localFragment, src, fileName);
			}
			
			String fileComponentName = Signature.getSimpleName(localComponentFullName)+ DOT_JAVA;
			
			type = javaProject.findType(localComponentFullName);
			if(type == null){
				IPackageFragment localComponentFragment = getPackageFragment(useClientJar, localComponentFullName);
				LocalComponentInterfaceTemplate tempImpl = LocalComponentInterfaceTemplate.create(null);
				src = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_LOCALCOMPONENT_FILE, tempImpl, monitor);
				createJavaFile(monitor, localComponentFragment, src, fileComponentName);
			}
		}
	}

	protected IPackageFragment getPackageFragment(boolean useClientJar, String fullName) {
		IPackageFragment fragment;
		String packageName = Signature.getQualifier(fullName);
		if (useClientJar) {
			fragment = createJavaPackageInClientJar(packageName);
		}
		else
		{
			fragment = createJavaPackage(packageName);
		}
		return fragment;
	}

	// Returns the first java source folder found in the client jar project, or null if no source folder exists.
	private IFolder getFirstJavaSourceFolderInClientJar(){
		IFolder result = null;
		
		IProject clientProject = EJBUtilities.getEJBClientJar(getTargetProject()).getProject();
		
		IVirtualComponent clientVc = ComponentCore.createComponent(clientProject);
		List<IContainer> sourceCointainers = JavaLiteUtilities.getJavaSourceContainers(clientVc);
		
		for (IContainer container:sourceCointainers){
			if (container instanceof IFolder && !container.getName().startsWith(".")) { //$NON-NLS-1$
				// We only want source folders which are not hidden (i.e. its name does not begin with ".")
				result = (IFolder)container;
				break;
			}			
		}
		return result;
	}
	
	protected IFolder createJavaSourceFolderInClientJar() {
		// Get the source folder name from the data model
		IFolder folder = getClientSourceFolder();
		// If folder does not exist, create the folder with the specified path
		if (!folder.exists()) {
			try {
				createFolder( folder, false );
			} catch (CoreException e) {
				EjbPlugin.logError(e);
			}

			IProject  clientProject = EJBUtilities.getEJBClientJar(getTargetProject()).getProject();
			IJavaProject  jProject = JavaCore.create( clientProject );
			if( jProject != null ){
				IClasspathEntry newEntry = JavaCore.newSourceEntry( folder.getFullPath() );
				
				List<IClasspathEntry> cp = new ArrayList<IClasspathEntry>();
				IClasspathEntry[] classPath;
				try {
					classPath = jProject.getRawClasspath();
					cp.addAll( Arrays.asList( classPath ) );
					cp.add( newEntry );
		            jProject.setRawClasspath( cp.toArray( new IClasspathEntry[ cp.size() ] ), null );					
				} catch (JavaModelException e) {
					EjbPlugin.logError(e);
				}

			}
	        
		}
		// Return the source folder
		return folder;
	}	

	protected IPackageFragment createJavaPackageInClientJar(String packageName) {
		IPackageFragmentRoot packRoot = getClientPackageFragmentRoot();
		
		IPackageFragment pack = packRoot.getPackageFragment(packageName);
		// Handle default package
		if (pack == null) {
			pack = packRoot.getPackageFragment(""); //$NON-NLS-1$
		}

		// Create the package fragment if it does not exist
		if (!pack.exists()) {
			String packName = pack.getElementName();
			try {
				pack = packRoot.createPackageFragment(packName, true, null);
			} catch (JavaModelException e) {
				EjbPlugin.logError(e);
			}
		}
		// Return the package
		return pack;
	}

	/**
	 * This method is intended for internal use only. This method will create an
	 * instance of the CreateSessionBeanTemplate model to be used in conjunction
	 * with the WTPJETEmitter. This method will not return null.
	 * 
	 * @see CreateSessionBeanTemplateModel
	 * @see NewSessionBeanClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @return CreateBeanTemplateModel
	 */
	private CreateSessionBeanTemplateModel createTemplateModel() {
		// Create the CreateBeanTemplateModel instance with the new bean
		// class data model
		CreateSessionBeanTemplateModel templateModel = new CreateSessionBeanTemplateModel(model);
		return templateModel;
	}

	private boolean hasInterfacesToGenerate() {
		List<BusinessInterface> businessInterfaces = (List<BusinessInterface>) model.getProperty(INTERFACES);
		boolean remoteHome = model.getBooleanProperty(REMOTE_HOME);
		boolean localHome = model.getBooleanProperty(LOCAL_HOME);
		return businessInterfaces.size() > 0 || remoteHome || localHome;
	}
	
	private IFolder getClientSourceFolder() {
		IFolder folder = getSourceFolder();
		IPath folderRelativePath = folder.getProjectRelativePath();
		IPath clientProjectPath = EJBUtilities.getEJBClientJar(getTargetProject()).getProject().getFullPath();
		return ResourcesPlugin.getWorkspace().getRoot().getFolder(clientProjectPath.append(folderRelativePath));
	}
	
	private IPackageFragmentRoot getClientPackageFragmentRoot() {
		IFolder folder = getClientSourceFolder();
		if (!folder.exists())
			folder = getFirstJavaSourceFolderInClientJar();
		if (folder == null)
			return null;
		IProject clientProject = EJBUtilities.getEJBClientJar(getTargetProject()).getProject();
		IJavaProject clientJavaProject = JavaCore.create(clientProject);
		return clientJavaProject.getPackageFragmentRoot(folder);
	}

	/**
	 * This method will return the java package as specified by the new java
	 * class data model. If the package does not exist, it will create the
	 * package. This method should not return null.
	 * 
	 * @see INewJavaClassDataModelProperties#JAVA_PACKAGE
	 * @see IPackageFragmentRoot#createPackageFragment(java.lang.String,
	 *      boolean, org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IPackageFragment the java package
	 */
	protected IPackageFragment createJavaPackage(String packageName) {
		IPackageFragmentRoot packRoot = (IPackageFragmentRoot) model.getProperty(JAVA_PACKAGE_FRAGMENT_ROOT);
		IPackageFragment pack = packRoot.getPackageFragment(packageName);
		// Handle default package
		if (pack == null) {
			pack = packRoot.getPackageFragment(""); //$NON-NLS-1$
		}

		// Create the package fragment if it does not exist
		if (!pack.exists()) {
			String packName = pack.getElementName();
			try {
				pack = packRoot.createPackageFragment(packName, true, null);
			} catch (JavaModelException e) {
				J2EEPlugin.logError(e);
			}
		}
		// Return the package
		return pack;
	}
	
	private static void createFolder( final IFolder folder,
	            final boolean isDerived )	throws CoreException {
		if( ! folder.exists() ){

			final IContainer parent = folder.getParent();
		
			if( parent instanceof IFolder ){
				createFolder( (IFolder) parent, isDerived );
			}
		
			folder.create( true, true, null );
			folder.setDerived( isDerived );
		}
	}	
}
