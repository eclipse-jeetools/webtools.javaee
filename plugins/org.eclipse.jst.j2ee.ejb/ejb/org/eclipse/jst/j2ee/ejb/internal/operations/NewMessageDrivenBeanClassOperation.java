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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;

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
public class NewMessageDrivenBeanClassOperation extends NewEnterpriseBeanClassOperation {

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
	@Override
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) throws WFTWrappedException, CoreException {
		// Create the enterprise bean template model
		CreateMessageDrivenBeanTemplateModel tempModel = createTemplateModel();
		// Using the WTPJetEmitter, generate the java source based on the bean template model
		try {
			if (fragment != null) {
				// Create the message-driven bean java file
				MessageDrivenBeanTemplate tempImpl = MessageDrivenBeanTemplate.create(null);
				String source = generateTemplateSource(EjbPlugin.getPlugin(), tempModel, TEMPLATE_FILE, tempImpl, monitor);
				String javaFileName = tempModel.getClassName() + DOT_JAVA;
				createJavaFile(monitor, fragment, source, javaFileName);
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
		return new CreateMessageDrivenBeanTemplateModel(model);
	}

}
