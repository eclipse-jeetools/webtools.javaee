/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.jst.j2ee.project.EJBUtilities;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

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
public class NewSessionBeanClassOperation extends NewEnterpriseBeanClassOperation implements INewSessionBeanClassDataModelProperties {

	private static final String LOCAL_HOME_SUFFIX = "LocalHome"; //$NON-NLS-1$
	private static final String LOCAL_COMPONENT_SUFFIX = "LocalComponent"; //$NON-NLS-1$
	private static final String REMOTE_COMPONENT_SUFFIX = "RemoteComponent"; //$NON-NLS-1$
	private static final String REMOTE_HOME_SUFFIX = "Home"; //$NON-NLS-1$

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

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
	throws ExecutionException {
		return doExecute(monitor, info);
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
	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// Create source folder if it does not exist
		createJavaSourceFolder();
		// Create java package if it does not exist
		IPackageFragment pack = createJavaPackage();
		IPackageFragment clientPack = null;
		
		if (hasInterfacesToGenerate() && EJBUtilities.hasEJBClientJARProject(getTargetProject())) {
			createJavaSourceFolderInClientJar();
			clientPack = createJavaPackageInClientJar();
		}
		
		// Generate bean classes using templates
		try {
			generateUsingTemplates(monitor, pack, clientPack);
		} catch (Exception e) {
			return WTPCommonPlugin.createErrorStatus(e.toString());
		}
		return OK_STATUS;
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
		IProject project = getTargetProject();
		// Using the WTPJetEmitter, generate the java source based on the bean template model
		try {
			if (fragment != null) {
				if (hasInterfacesToGenerate() && EJBUtilities.hasEJBClientJARProject(getTargetProject())) {
					generateInterfacesUsingTemplates(monitor, clientFragment, tempModel);
				} else {
					generateInterfacesUsingTemplates(monitor, fragment, tempModel);
				}
				
				// Create the session bean java file
				String source = generateTemplateSource(tempModel, monitor, TEMPLATE_FILE);
				String javaFileName = tempModel.getClassName() + DOT_JAVA;
				IFile aFile = createJavaFile(monitor, fragment, source, javaFileName);
			}
		} catch (Exception e) {
			throw new WFTWrappedException(e);
		}
	}
	
	protected void generateInterfacesUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment, CreateSessionBeanTemplateModel tempModel) throws JETException, JavaModelException {
		List<BusinessInterface> interfaces = tempModel.getBusinessInterfaces();
		for (BusinessInterface iface : interfaces) {
			if (!iface.exists()) {
				if (iface.isLocal()) {
					// Create the java files for the non-exising Local Business interfaces
					String src = generateTemplateSource(tempModel, monitor, TEMPLATE_LOCAL_FILE);
					String fileName = iface.getSimpleName() + DOT_JAVA;
					createJavaFile(monitor, fragment, src, fileName);
				} else if (iface.isRemote()) {
					// Create the java files for the non-exising Remote Business interfaces
					String src = generateTemplateSource(tempModel, monitor, TEMPLATE_REMOTE_FILE);
					String fileName = iface.getSimpleName() + DOT_JAVA;
					createJavaFile(monitor, fragment, src, fileName);
				}
			}
		}

		// Create the EJB 2.x compatible Remote Home and Component interface java files
		if (model.getBooleanProperty(INewSessionBeanClassDataModelProperties.REMOTE_HOME)) {
			String src = generateTemplateSource(tempModel, monitor, TEMPLATE_REMOTEHOME_FILE);
			String fileName =  tempModel.getClassName() + REMOTE_HOME_SUFFIX + DOT_JAVA;
			createJavaFile(monitor, fragment, src, fileName);
			src = generateTemplateSource(tempModel, monitor, TEMPLATE_REMOTECOMPONENT_FILE);
			fileName =  tempModel.getClassName() + REMOTE_COMPONENT_SUFFIX + DOT_JAVA;
			createJavaFile(monitor, fragment, src, fileName);
		}
		
		// Create the EJB 2.x compatible Local Home and Component interface java files
		if (model.getBooleanProperty(INewSessionBeanClassDataModelProperties.LOCAL_HOME)) {
			String src = generateTemplateSource(tempModel, monitor, TEMPLATE_LOCALHOME_FILE);
			String fileName =  tempModel.getClassName() + LOCAL_HOME_SUFFIX + DOT_JAVA;
			createJavaFile(monitor, fragment, src, fileName);
			src = generateTemplateSource(tempModel, monitor, TEMPLATE_LOCALCOMPONENT_FILE);
			fileName =  tempModel.getClassName() + LOCAL_COMPONENT_SUFFIX + DOT_JAVA;
			createJavaFile(monitor, fragment, src, fileName);
		}
	}

	protected IFolder createJavaSourceFolderInClientJar() {
		// Get the source folder name from the data model
		IFolder folder = getClientSourceFolder();
		// If folder does not exist, create the folder with the specified path
		if (!folder.exists()) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				EjbPlugin.getDefault().getLogger().logError(e);
			}
		}
		// Return the source folder
		return folder;
	}

	protected IPackageFragment createJavaPackageInClientJar() {
		// Retrieve the package name from the java class data model
		String packageName = model.getStringProperty(JAVA_PACKAGE);
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
				EjbPlugin.getDefault().getLogger().logError(e);
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

	/**
	 * This method is intended for internal use only. This will use the
	 * WTPJETEmitter to create an annotated java file based on the passed in
	 * bean class template model. This method does not accept null
	 * parameters. It will not return null. If annotations are not used, it will
	 * use the non annotated template to omit the annotated tags.
	 * 
	 * @see NewSessionBeanClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * @see JETEmitter#generate(org.eclipse.core.runtime.IProgressMonitor,
	 *      java.lang.Object[])
	 * @see CreateSessionBeanTemplateModel
	 * 
	 * @param tempModel
	 * @param monitor
	 * @param template_file2 
	 * @return String the source for the java file
	 * @throws JETException
	 */
	private String generateTemplateSource(CreateSessionBeanTemplateModel tempModel, IProgressMonitor monitor, String template_file) throws JETException {
		URL templateURL = FileLocator.find(EjbPlugin.getDefault().getBundle(), new Path(template_file), null);
		cleanUpOldEmitterProject();
		WTPJETEmitter emitter = new WTPJETEmitter(templateURL.toString(), this.getClass().getClassLoader());
		emitter.setIntelligentLinkingEnabled(true);
		emitter.addVariable(EJB_PLUGIN, EjbPlugin.PLUGIN_ID);
		return emitter.generate(monitor, new Object[] { tempModel });
	}
	
	private boolean hasInterfacesToGenerate() {
		List<BusinessInterface> businessInterfaces = (List<BusinessInterface>) model.getProperty(BUSINESS_INTERFACES);
		boolean remoteHome = model.getBooleanProperty(INewSessionBeanClassDataModelProperties.REMOTE_HOME);
		boolean localHome = model.getBooleanProperty(INewSessionBeanClassDataModelProperties.LOCAL_HOME);
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
		
		IProject clientProject = EJBUtilities.getEJBClientJar(getTargetProject()).getProject();
		IJavaProject clientJavaProject = JavaCore.create(clientProject);
		return clientJavaProject.getPackageFragmentRoot(folder);
	}
	
}
