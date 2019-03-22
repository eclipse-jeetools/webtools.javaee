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
 * The NewFilterClassOperation is an IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends ArtifactEditProviderOperation to provide filter specific java
 * class generation.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * This operation is used by the AddFilterOperation to generate an
 * non annotated java class for an added filter. It shares the
 * NewFilterClassDataModelProvider with the AddFilterOperation to store the
 * appropriate properties required to generate the new filter.
 * @see org.eclipse.jst.j2ee.internal.web.operations.AddFilterOperation
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassDataModelProvider
 * 
 * A WTPJetEmitter filter template is used to create the class with the filter template. 
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.internal.web.operations.CreateFilterTemplateModel
 * 
 * Subclasses may extend this operation to provide their own specific filter
 * java class generation. The execute method may be extended to do so. Also,
 * generateUsingTemplates is exposed.
 * 
 */
public class NewFilterClassOperation extends NewWebClassOperation {

	/**
	 * folder location of the filter creation templates diretory
	 */
	protected static final String TEMPLATE_FILE = "/templates/filter.javajet"; //$NON-NLS-1$

	/**
	 * This is the constructor which should be used when creating a
	 * NewFilterClassOperation. An instance of the NewFilterClassDataModel
	 * should be passed in. This does not accept null parameter. It will not
	 * return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewFilterClassDataModel
	 * 
	 * @param dataModel
	 * @return NewFilterClassOperation
	 */
	public NewFilterClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * This method will create an instance of the CreateFilterTemplate model to
	 * be used in conjunction with the WTPJETEmitter. This method will not
	 * return null.
	 * 
	 * @see CreateFilterTemplateModel
	 * @see NewFilterClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @return CreateFilterTemplateModel
	 */
	@Override
    protected CreateFilterTemplateModel createTemplateModel() {
        return new CreateFilterTemplateModel(model);
    }

	@Override
	protected String getTemplateFile() {
		return TEMPLATE_FILE;
	}

	@Override
	protected FilterTemplate getTemplateImplementation() {
		return FilterTemplate.create(null);
	}
	
}
