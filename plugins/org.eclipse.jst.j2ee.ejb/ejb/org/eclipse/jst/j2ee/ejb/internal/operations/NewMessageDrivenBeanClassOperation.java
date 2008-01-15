/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * The NewMessageDrivenBeanClassOperation is an IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends ArtifactEditProviderOperation to provide enterprise bean specific java
 * class generation.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * This operation is used by the AddMessageDrivenBeanOperation to generate an
 * non annotated java class for an added enterprise bean. It shares the
 * NewMessageDrivenBeanClassDataModelProvider with the AddMessageDrivenBeanOperation to store the
 * appropriate properties required to generate the new message-driven bean.
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.AddMessageDrivenBeanOperation
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.NewMessageDrivenBeanClassDataModelProvider
 * 
 * A WTPJetEmitter bean template is used to create the class with the bean template. 
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.ejb.internal.operations.CreateMessageDrivenBeanTemplateModel
 * 
 * Subclasses may extend this operation to provide their own specific bean
 * java class generation. The execute method may be extended to do so. Also,
 * generateUsingTemplates is exposed.
 * 
 */
public class NewMessageDrivenBeanClassOperation extends NewEnterpriseBeanClassOperation implements INewMessageDrivenBeanClassDataModelProperties {

	/**
	 * folder location of the enterprise bean creation templates directory
	 */
	protected static final String TEMPLATE_FILE = "/templates/messageDrivenBean.javajet"; //$NON-NLS-1$


	/**
	 * This is the constructor which should be used when creating a
	 * NewMessageDrivenBeanClassOperation. An instance of the NewMessageDrivenBeanClassDataModelProvider
	 * should be passed in. This does not accept null parameter. It will not
	 * return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewMessageDrivenClassDataModel
	 * 
	 * @param dataModel
	 * @return NewMessageDrivenClassOperation
	 */
	public NewMessageDrivenBeanClassOperation(IDataModel dataModel) {
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
	 * @see NewMessageDrivenBeanClassOperation#generateUsingTemplates(IProgressMonitor,
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
	 * Subclasses may extend this method to provide their own template based
	 * creation of an annotated bean java class file. This implementation uses
	 * the creation of a CreateMessageDrivenBeanTemplateModel and the WTPJetEmitter to
	 * create the java class with the annotated tags. This method accepts null
	 * for monitor, it does not accept null for fragment. If annotations are not
	 * being used the tags will be omitted from the class.
	 * 
	 * @see CreateMessageDrivenBeanTemplateModel
	 * @see NewMessageDrivenBeanClassOperation#generateTemplateSource(CreateMessageDrivenBeanTemplateModel,
	 *      IProgressMonitor)
	 * 
	 * @param monitor
	 * @param fragment
	 * @throws CoreException
	 * @throws WFTWrappedException
	 */
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) throws WFTWrappedException, CoreException {
		// Create the enterprise bean template model
		CreateMessageDrivenBeanTemplateModel tempModel = createTemplateModel();
		// Using the WTPJetEmitter, generate the java source based on the bean template model
		try {
			if (fragment != null) {
				// Create the session bean java file
				String source = generateTemplateSource(tempModel, monitor, TEMPLATE_FILE);
				String javaFileName = tempModel.getClassName() + DOT_JAVA;
				IFile aFile = createJavaFile(monitor, fragment, source, javaFileName);
			}
		} catch (Exception e) {
			throw new WFTWrappedException(e);
		}
	}

	/**
	 * This method is intended for internal use only. This method will create an
	 * instance of the CreateMessageDrivenBeanTemplate model to be used in conjunction
	 * with the WTPJETEmitter. This method will not return null.
	 * 
	 * @see CreateMessageDrivenBeanTemplateModel
	 * @see NewMessageDrivenBeanClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @return CreateBeanTemplateModel
	 */
	private CreateMessageDrivenBeanTemplateModel createTemplateModel() {
		// Create the CreateBeanTemplateModel instance with the new bean
		// class data model
		CreateMessageDrivenBeanTemplateModel templateModel = new CreateMessageDrivenBeanTemplateModel(model);
		return templateModel;
	}

	/**
	 * This method is intended for internal use only. This will use the
	 * WTPJETEmitter to create an annotated java file based on the passed in
	 * bean class template model. This method does not accept null
	 * parameters. It will not return null. If annotations are not used, it will
	 * use the non annotated template to omit the annotated tags.
	 * 
	 * @see NewMessageDrivenBeanClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * @see JETEmitter#generate(org.eclipse.core.runtime.IProgressMonitor,
	 *      java.lang.Object[])
	 * @see CreateMessageDrivenBeanTemplateModel
	 * 
	 * @param tempModel
	 * @param monitor
	 * @param template_file2 
	 * @return String the source for the java file
	 * @throws JETException
	 */
	private String generateTemplateSource(CreateMessageDrivenBeanTemplateModel tempModel, IProgressMonitor monitor, String template_file) throws JETException {
		URL templateURL = FileLocator.find(EjbPlugin.getDefault().getBundle(), new Path(template_file), null);
		cleanUpOldEmitterProject();
		WTPJETEmitter emitter = new WTPJETEmitter(templateURL.toString(), this.getClass().getClassLoader());
		emitter.setIntelligentLinkingEnabled(true);
		emitter.addVariable(EJB_PLUGIN, EjbPlugin.PLUGIN_ID);
		return emitter.generate(monitor, new Object[] { tempModel });
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
	throws ExecutionException {
		return doExecute(monitor, info);
	}

}
