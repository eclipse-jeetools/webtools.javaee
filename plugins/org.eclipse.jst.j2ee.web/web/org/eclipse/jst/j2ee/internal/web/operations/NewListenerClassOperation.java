/*******************************************************************************
 * Copyright (c) 2007, 2012 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * The NewListenerClassOperation is an IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends ArtifactEditProviderOperation to provide listener specific java
 * class generation.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * This operation is used by the AddListenerOperation to generate either an
 * annotated or non annotated java class for an added application lifecycle listener. 
 * It shares the NewListenerClassDataModelProvider with the AddListenerOperation to 
 * store the appropriate properties required to generate the new listener.
 * @see org.eclipse.jst.j2ee.internal.web.operations.AddListenerOperation
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassDataModelProvider
 * 
 * In the annotated case, a WTPJetEmitter listener template is created and used
 * to generate the listener java class with the embedded annotated tags.
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.internal.web.operations.CreateListenerTemplateModel
 * 
 * In the non annotated case, the same emitter is used to create the class with
 * the non annotated listener template so the annotated tags are omitted.
 * 
 * Subclasses may extend this operation to provide their own specific listener
 * java class generation. The execute method may be extended to do so. Also,
 * generateUsingTemplates is exposed.
 * 
 */
public class NewListenerClassOperation extends NewWebClassOperation {

	/**
	 * folder location of the listener creation templates directory
	 */
	protected static final String TEMPLATE_FILE = "/templates/listener.javajet"; //$NON-NLS-1$

	/**
	 * This is the constructor which should be used when creating a
	 * NewListenerClassOperation. An instance of the NewListenerClassDataModel
	 * should be passed in. This does not accept null parameter. It will not
	 * return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewListenerClassDataModel
	 * 
	 * @param dataModel
	 * @return NewListenerClassOperation
	 */
	public NewListenerClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * This method will create an instance of the CreateListenerTemplateModel to
	 * be used in conjunction with the WTPJETEmitter. This method will not
	 * return null.
	 * 
	 * @see CreateListenerTemplateModel
	 * @see NewListenerClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @return CreateListenerTemplateModel
	 */
	@Override
	protected CreateListenerTemplateModel createTemplateModel() {
		return new CreateListenerTemplateModel(model);
	}

	@Override
	protected String getTemplateFile() {
		return TEMPLATE_FILE;
	}

	@Override
	protected ListenerTemplate getTemplateImplementation() {
		return ListenerTemplate.create(null);
	}
	
}
