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
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsController;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperation;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;

/**
 * The NewServletClassOperation is a WTPOperation following the WTP wizard data model and
 * operation framework.
 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperation
 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel
 * 
 * It extends EditModelOperation to provide servlet specific java class generation.
 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation
 * 
 * This operation is used by the AddServletOperation to generate either an annotated or
 * non annotated java class for an added servlet.  It shares the NewServletClassDataModel
 * with the AddServletOperation to store the appropriate properties required to generate
 * the new servlet.
 * @see org.eclipse.jst.j2ee.internal.web.operations.AddServletOperation
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModel
 * 
 * In the annotated case, a WTPJetEmitter servlet template is created and used to generate the
 * servlet java class with the embedded annotated tags.
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.internal.web.operations.CreateServletTemplateModel
 * 
 * In the non annotated case, the same emitter is used to create the class with the non annotated
 * servlet template so the annotated tags are omitted.
 * 
 * Subclasses may extend this operation to provide their own specific servlet java class generation.
 * The execute method may be extended to do so.  Also, generateUsingTemplates is exposed.
 *
 * The use of this class is EXPERIMENTAL and is subject to substantial changes.
 */
public class NewServletClassOperation extends ArtifactEditOperation {
	
	/**
	 * The extension name for a java class
	 */
	private static final String DOT_JAVA = ".java"; //$NON-NLS-1$
	/**
	 * platform plugin beginning for URI string
	 */
	private static final String PLATFORM_PLUGIN = "platform:/plugin/"; //$NON-NLS-1$
	
	/**
	 * variable for the web plugin
	 */
	protected static final String WEB_PLUGIN = "WEB_PLUGIN"; //$NON-NLS-1$
	/**
	 * folder location of the servlet creation templates diretory
	 */
	protected static final String TEMPLATE_DIR = "/templates/"; //$NON-NLS-1$
	/**
	 * name of the template emitter to be used to generate the deployment descriptor from the tags
	 */
	protected static final String TEMPLATE_EMITTER = "org.eclipse.jst.j2ee.ejb.annotations.emitter.template"; //$NON-NLS-1$
	/**
	 * id of the builder used to kick off generation of web metadata based on parsing of annotations
	 */
	protected static final String BUILDER_ID = "builderId"; //$NON-NLS-1$
	
	/**
	 * This is the constructor which should be used when creating a NewServletClassOperation.  An instance of
	 * the NewServletClassDataModel should be passed in.  This does not accept null parameter.  It will
	 * not return null.  
	 * @see EditModelOperation#EditModelOperation(EditModelOperationDataModel)
	 * @see NewServletClassDataModel
	 * 
	 * @param dataModel
	 * @return NewServletClassOperation
	 */
	public NewServletClassOperation(ArtifactEditOperationDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Subclasses may extend this method to add their own actions during execution.
	 * The implementation of the execute method drives the running of the operation.
	 * This implemenatation will create the java source folder, create the java package,
	 * and then if using annotations, will use templates to generate an annotated servlet
	 * java class, or if it is not annotated, the servlet java class file will be created
	 * without the annotated tags using templates.
	 * Optionally, subclasses may extend the generateUsingTemplates or createJavaFile method
	 * rather than extend the execute method. This method will accept a null paramter.
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 * @see NewServletClassOperation#generateUsingTemplates(IProgressMonitor, IPackageFragment)
	 * 
	 * @param monitor
	 * @throws CoreException
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		// Create source folder if it does not exist
		createJavaSourceFolder();
		// Create java package if it does not exist
		IPackageFragment pack = createJavaPackage();
		// Generate using templates
		generateUsingTemplates(monitor, pack);
	}
	
	/**
	 * This method will return the java package as specified by the new java class data model.
	 * If the package does not exist, it will create the package.  This method should not return
	 * null.
	 * @see NewJavaClassDataModel#JAVA_PACKAGE
	 * @see IPackageFragmentRoot#createPackageFragment(java.lang.String, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IPackageFragment the java package
	 */
	protected final IPackageFragment createJavaPackage() {
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		// Retrieve the package name from the java class data model
		String packageName = model.getStringProperty(NewJavaClassDataModel.JAVA_PACKAGE);
		IPackageFragmentRoot packRoot = model.getJavaPackageFragmentRoot();
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
	 * Subclasses may extend this method to provide their own template based creation
	 * of an annotated servlet java class file.  This implementation uses the creation of
	 * a CreateServletTemplateModel and the WTPJetEmitter to create the java class with the
	 * annotated tags.  This method accepts null for monitor, it does not accept null for
	 * fragment.  If annotations are not being used the tags will be omitted from the class.
	 * @see CreateServletTemplateModel
	 * @see NewServletClassOperation#generateTemplateSource(CreateServletTemplateModel, IProgressMonitor)
	 * 
	 * @param monitor
	 * @param fragment
	 * @throws CoreException
	 * @throws WFTWrappedException
	 */
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) throws WFTWrappedException, CoreException {
		// Create the servlet template model
		CreateServletTemplateModel tempModel = createTemplateModel();
		IProject project = ((ArtifactEditOperationDataModel)getOperationDataModel()).getTargetProject();
		String source;
		// Using the WTPJetEmitter, generate the java source based on the servlet template model
		try {
			source = generateTemplateSource(tempModel, monitor);
		} catch (JETException e) {
			throw new WFTWrappedException(e);
		}
		if (fragment != null) {
			// Create the java file
			String javaFileName = tempModel.getServletClassName() + DOT_JAVA;
			ICompilationUnit cu = fragment.getCompilationUnit(javaFileName);
			// Add the compilation unit to the java file
			if (cu == null || !cu.exists())
				cu = fragment.createCompilationUnit(javaFileName, source, true, monitor);
			IFile aFile = (IFile) cu.getResource();
			// Let the annotations controller process the annotated resource
			AnnotationsController controller = AnnotationsControllerManager.INSTANCE.getAnnotationsController(project);
			if (controller != null)
				controller.process(aFile);
			//((J2EEEditModel)model.getEditModel()).getWorkingCopy(cu, true); //Track CU.
		}
		// Add the annotations builder to the java project so metadata can be generated.
		//TODO for M4 cannot add builder directly here, needs to be set up more extensibly
		addAnnotationsBuilder();
	}
	
	/**
	 * This method is intended for internal use only.  This method will add the annotations builder
	 * for Xdoclet to the targetted project.  This needs to be removed from the operation and set
	 * up to be more extensible throughout the workbench.
	 * @see NewServletClassOperation#generateUsingTemplates(IProgressMonitor, IPackageFragment)
	 * 
	 * @deprecated
	 */
	private void addAnnotationsBuilder() {
		try {
			NewServletClassDataModel dataModel = (NewServletClassDataModel) operationDataModel;
			// Find the xdoclet builder from the extension registry
			IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(TEMPLATE_EMITTER);
			String builderID = configurationElements[0].getNamespace() + "."+ configurationElements[0].getAttribute(BUILDER_ID); //$NON-NLS-1$
			IProject project = dataModel.getTargetProject(); 
			IProjectDescription description = project.getDescription();
			ICommand[] commands = description.getBuildSpec();
			boolean found = false;
			// Check if the builder is already set on the project
			for (int i = 0; i < commands.length; ++i) {
				if (commands[i].getBuilderName().equals(builderID)) {
					found = true;
					break;
				}
			}
			// If the builder is not on the project, add it
			if (!found) {
				ICommand command = description.newCommand();
				command.setBuilderName(builderID);
				ICommand[] newCommands = new ICommand[commands.length + 1];
				System.arraycopy(commands, 0, newCommands, 0, commands.length);
				newCommands[commands.length] = command;
				IProjectDescription desc = project.getDescription();
				desc.setBuildSpec(newCommands);
				project.setDescription(desc, null);
			}
		} catch (Exception e) {
			//Ignore
		}
	}

	/**
	 * This method is intended for internal use only.  This will use the WTPJETEmitter to create
	 * an annotated java file based on the passed in servlet class template model.  This method
	 * does not accept null parameters.  It will not return null.  If annotations are not used,
	 * it will use the non annotated template to omit the annotated tags.
	 * @see NewServletClassOperation#generateUsingTemplates(IProgressMonitor, IPackageFragment)
	 * @see JETEmitter#generate(org.eclipse.core.runtime.IProgressMonitor, java.lang.Object[])
	 * @see CreateServletTemplateModel
	 * 
	 * @param tempModel
	 * @param monitor
	 * @return String the source for the java file
	 * @throws JETException
	 */
	private String generateTemplateSource(CreateServletTemplateModel tempModel, IProgressMonitor monitor) throws JETException {
		String templateURI;
		// If annotated, use annotated template
		if (((NewServletClassDataModel) getOperationDataModel()).getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			templateURI = PLATFORM_PLUGIN + WebPlugin.PLUGIN_ID + TEMPLATE_DIR + NewServletClassDataModel.TEMPLATE_FILE;
		// Otherwise use non annotated template
		else
			templateURI = PLATFORM_PLUGIN + WebPlugin.PLUGIN_ID + TEMPLATE_DIR + NewServletClassDataModel.NON_ANNOTATED_TEMPLATE_FILE;
		WTPJETEmitter emitter = new WTPJETEmitter(templateURI, this.getClass().getClassLoader());
		emitter.setIntelligentLinkingEnabled(true);
		emitter.addVariable(WEB_PLUGIN, WebPlugin.PLUGIN_ID);
		return emitter.generate(monitor, new Object[]{tempModel});
	}

	/**
	 * This method is intended for internal use only.  This method will create an instance of the
	 * CreateServletTemplate model to be used in conjunction with the WTPJETEmitter.  This method
	 * will not return null.
	 * @see CreateServletTemplateModel
	 * @see NewServletClassOperation#generateUsingTemplates(IProgressMonitor, IPackageFragment)
	 * 
	 * @return CreateServletTemplateModel
	 */
	private CreateServletTemplateModel createTemplateModel() {
		// Create the CreateServletTemplateModel instance with the new servlet class data model
		CreateServletTemplateModel model = new CreateServletTemplateModel((NewServletClassDataModel) getOperationDataModel());
		return model;
	}
	
	/**
	 * This method will return the java source folder as specified in the java class data model. 
	 * It will create the java source folder if it does not exist.  This method may return null.
	 * @see NewJavaClassDataModel#SOURCE_FOLDER
	 * @see IFolder#create(boolean, boolean, org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IFolder the java source folder
	 */
	protected final IFolder createJavaSourceFolder() {
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		// Get the source folder name from the data model
		String folderFullPath = model.getStringProperty(NewJavaClassDataModel.SOURCE_FOLDER);
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
}