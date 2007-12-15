/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaModelMarker;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsController;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
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
 * This operation is used by the AddBeansOperation to generate an
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
public class NewSessionBeanClassOperation extends AbstractDataModelOperation {

	private static final String LOCAL_HOME_SUFFIX = "LocalHome"; //$NON-NLS-1$
	private static final String LOCAL_COMPONENT_SUFFIX = "LocalComponent"; //$NON-NLS-1$
	private static final String REMOTE_COMPONENT_SUFFIX = "RemoteComponent"; //$NON-NLS-1$
	private static final String REMOTE_HOME_SUFFIX = "Home"; //$NON-NLS-1$

	/**
	 * The extension name for a java class
	 */
	private static final String DOT_JAVA = ".java"; //$NON-NLS-1$

	/**
	 * variable for the ejb plugin
	 */
	protected static final String EJB_PLUGIN = "EJB_PLUGIN"; //$NON-NLS-1$

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
	 * name of the template emitter to be used to generate the deployment
	 * descriptor from the tags
	 */
	//	protected static final String TEMPLATE_EMITTER = "org.eclipse.jst.j2ee.ejb.annotations.emitter.template"; //$NON-NLS-1$

	/**
	 * id of the builder used to kick off generation of bean metadata based on
	 * parsing of annotations emitter.
	 * 
	 */
	protected static final String BUILDER_ID = "builderId"; //$NON-NLS-1$

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
	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// Create source folder if it does not exist
		createJavaSourceFolder();
		// Create java package if it does not exist
		IPackageFragment pack = createJavaPackage();
		// Generate bean classes using templates
		try {
			generateUsingTemplates(monitor, pack);
		} catch (Exception e) {
			return WTPCommonPlugin.createErrorStatus(e.toString());
		}
		return OK_STATUS;
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
	protected final IPackageFragment createJavaPackage() {
		// Retrieve the package name from the java class data model
		String packageName = model.getStringProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE);
		IPackageFragmentRoot packRoot = (IPackageFragmentRoot) model
		.getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT);
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
				Logger.getLogger().log(e);
			}
		}
		// Return the package
		return pack;
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
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) throws WFTWrappedException, CoreException {
		// Create the enterprise bean template model
		CreateSessionBeanTemplateModel tempModel = createTemplateModel();
		IProject project = getTargetProject();
		String source;
		String source1 = null;
		String source2 = null;
		String localInterfaceName = tempModel.getLocalInterface();
		String remoteInterfaceName = tempModel.getRemoteInterface();
		// Using the WTPJetEmitter, generate the java source based on the bean template model
		try {
			source = generateTemplateSource(tempModel, monitor, TEMPLATE_FILE);
			if (localInterfaceName != null){
				source1 = generateTemplateSource(tempModel, monitor, TEMPLATE_LOCAL_FILE);
				String localBeanName =  localInterfaceName + DOT_JAVA;
				createJavaFile(monitor, fragment, source1, localBeanName);
			}
			if (remoteInterfaceName != null){
				source2 = generateTemplateSource(tempModel, monitor, TEMPLATE_REMOTE_FILE);
				String remoteBeanName = remoteInterfaceName + DOT_JAVA;
				createJavaFile(monitor, fragment, source2, remoteBeanName);
			}
			if ((Boolean) model.getProperty(INewSessionBeanClassDataModelProperties.REMOTE_HOME)){
				String src = generateTemplateSource(tempModel, monitor, TEMPLATE_REMOTEHOME_FILE);
				String localBeanName =  tempModel.getClassName() + REMOTE_HOME_SUFFIX + DOT_JAVA;
				createJavaFile(monitor, fragment, src, localBeanName);
				src = generateTemplateSource(tempModel, monitor, TEMPLATE_REMOTECOMPONENT_FILE);
				localBeanName =  tempModel.getClassName() + REMOTE_COMPONENT_SUFFIX + DOT_JAVA;
				createJavaFile(monitor, fragment, src, localBeanName);
			}
			if ((Boolean) model.getProperty(INewSessionBeanClassDataModelProperties.LOCAL_HOME)){
				String src = generateTemplateSource(tempModel, monitor, TEMPLATE_LOCALHOME_FILE);
				String localBeanName =  tempModel.getClassName() + LOCAL_HOME_SUFFIX + DOT_JAVA;
				createJavaFile(monitor, fragment, src, localBeanName);
				src = generateTemplateSource(tempModel, monitor, TEMPLATE_LOCALCOMPONENT_FILE);
				localBeanName =  tempModel.getClassName() + LOCAL_COMPONENT_SUFFIX + DOT_JAVA;
				createJavaFile(monitor, fragment, src, localBeanName);
			}

		} catch (Exception e) {
			throw new WFTWrappedException(e);
		}
		if (fragment != null) {
			// Create the java file
			String javaFileName = tempModel.getClassName() + DOT_JAVA;
			IFile aFile = createJavaFile(monitor, fragment, source, javaFileName);
			// Let the annotations controller process the annotated resource
			AnnotationsController controller = AnnotationsControllerManager.INSTANCE.getAnnotationsController(project);
			if (controller != null)
				controller.process(aFile);
		}
	}

	private IFile createJavaFile(IProgressMonitor monitor, IPackageFragment fragment, String source1, String localBeanName) throws JavaModelException {
		if (fragment != null) {
			ICompilationUnit cu1 = fragment.getCompilationUnit(localBeanName);
			// Add the compilation unit to the java file
			if (cu1 == null || !cu1.exists())
				cu1 = fragment.createCompilationUnit(localBeanName, source1,
						true, monitor);
			return (IFile) cu1.getResource();
		}
		return null;
	}

	protected void createInheritedMethods (IType type) throws CoreException {
		IMethod[] methods = type.getMethods();
		for (IMethod method : methods){
			boolean resolved = method.isResolved();
			if (!resolved){
				int flags = method.getFlags();
				if (Flags.isAbstract(flags) || Flags.isInterface(flags)){
					System.out.println("method(s) to implement:" + method.getElementName());
					System.out.println("Retrun String should be: " + method.getReturnType());
					method.getParameterTypes();
				}
			}
			System.out.println(resolved);
		}
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

	private void cleanUpOldEmitterProject() {
		IProject project = ProjectUtilities.getProject(WTPJETEmitter.PROJECT_NAME);
		if (project == null || !project.exists())
			return;
		try {
			IMarker[] markers = project.findMarkers(IJavaModelMarker.BUILDPATH_PROBLEM_MARKER, false, IResource.DEPTH_ZERO);
			for (int i = 0, l = markers.length; i < l; i++) {
				if (((Integer) markers[i].getAttribute(IMarker.SEVERITY)).intValue() == IMarker.SEVERITY_ERROR) {
					project.delete(true, new NullProgressMonitor());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return the java source folder as specified in the java
	 * class data model. It will create the java source folder if it does not
	 * exist. This method may return null.
	 * 
	 * @see INewJavaClassDataModelProperties#SOURCE_FOLDER
	 * @see IFolder#create(boolean, boolean,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IFolder the java source folder
	 */
	protected final IFolder createJavaSourceFolder() {
		// Get the source folder name from the data model
		String folderFullPath = model.getStringProperty(INewJavaClassDataModelProperties.SOURCE_FOLDER);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = root.getFolder(new Path(folderFullPath));
		// If folder does not exist, create the folder with the specified path
		if (!folder.exists()) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				Logger.getLogger().log(e);
			}
		}
		// Return the source folder
		return folder;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
	throws ExecutionException {
		return doExecute(monitor, info);
	}

	public IProject getTargetProject() {
		String projectName = model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME);
		return ProjectUtilities.getProject(projectName);
	}	
}
