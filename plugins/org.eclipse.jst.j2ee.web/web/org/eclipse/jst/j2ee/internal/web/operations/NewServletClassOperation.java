/*******************************************************************************
 * Copyright (c) 2003, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * Kaloyan Raev, kaloyan.raev@sap.com
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * The NewServletClassOperation is an IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends ArtifactEditProviderOperation to provide servlet specific java
 * class generation.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * This operation is used by the AddServletOperation to generate either an
 * annotated or non annotated java class for an added servlet. It shares the
 * NewServletClassDataModelProvider with the AddServletOperation to store the
 * appropriate properties required to generate the new servlet.
 * @see org.eclipse.jst.j2ee.internal.web.operations.AddServletOperation
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModelProvider
 * 
 * In the annotated case, a WTPJetEmitter servlet template is created and used
 * to generate the servlet java class with the embedded annotated tags.
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.internal.web.operations.CreateServletTemplateModel
 * 
 * In the non annotated case, the same emitter is used to create the class with
 * the non annotated servlet template so the annotated tags are omitted.
 * 
 * Subclasses may extend this operation to provide their own specific servlet
 * java class generation. The execute method may be extended to do so. Also,
 * generateUsingTemplates is exposed.
 * 
 */
public class NewServletClassOperation extends NewWebClassOperation {

	/**
	 * folder location of the servlet creation templates diretory
	 */
	protected static final String TEMPLATE_DIR = "/templates/"; //$NON-NLS-1$
	
	/**
	 * location of the servlet creation template file
	 */
	protected static final String TEMPLATE_FILE = "/templates/servlet.javajet"; //$NON-NLS-1$

	/**
	 * This is the constructor which should be used when creating a
	 * NewServletClassOperation. An instance of the NewServletClassDataModel
	 * should be passed in. This does not accept null parameter. It will not
	 * return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewServletClassDataModel
	 * 
	 * @param dataModel
	 * @return NewServletClassOperation
	 */
	public NewServletClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * This method will create an instance of the CreateServletTemplate model to
	 * be used in conjunction with the WTPJETEmitter. This method will not
	 * return null.
	 * 
	 * @see CreateServletTemplateModel
	 * @see NewServletClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @return CreateServletTemplateModel
	 */
	@Override
	protected CreateServletTemplateModel createTemplateModel() {
		return new CreateServletTemplateModel(model);
	}
	
	@Override
	protected String getTemplateFile() {
		return TEMPLATE_FILE;
	}

	@Override
	protected ServletTemplate getTemplateImplementation() {
		return ServletTemplate.create(null);
	}
	
}
