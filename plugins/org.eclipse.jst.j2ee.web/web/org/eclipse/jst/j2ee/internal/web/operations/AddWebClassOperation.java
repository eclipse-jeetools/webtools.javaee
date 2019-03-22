/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel.USE_ANNOTATIONS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.GENERATE_DD;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.USE_EXISTING_CLASS;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.REGISTER_IN_WEB_XML;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.operations.AddJavaEEArtifactOperation;
import org.eclipse.jst.jee.project.facet.ICreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.IWebCreateDeploymentFilesDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public abstract class AddWebClassOperation extends AddJavaEEArtifactOperation {
	protected Path WEB_APP_XML_PATH = new Path(J2EEConstants.WEBAPP_DD_URI);
	public AddWebClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Subclasses may extend this method to add their own actions during
	 * execution. The implementation of the execute method drives the running of
	 * the operation. This implementation will create the filter class, and
	 * then it will create the filter metadata for the web deployment descriptor. 
	 * This method will accept null as a parameter.
	 * 
	 * @see org.eclipse.core.commands.operations.AbstractOperation#execute(org.eclipse.core.runtime.IProgressMonitor,
	 *      org.eclipse.core.runtime.IAdaptable)
	 * @see AddFilterOperation#createFilterClass()
	 * @see AddFilterOperation#generateFilterMetaData(NewFilterClassDataModel,
	 *      String)
	 * 
	 * @param monitor
	 *            IProgressMonitor
	 * @param info
	 *            IAdaptable
	 * @throws CoreException
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	@Override
	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		
	    boolean useExisting = model.getBooleanProperty(USE_EXISTING_CLASS);
		String qualifiedClassName = model.getStringProperty(CLASS_NAME);

		// create the java class
		if (!useExisting) 
			qualifiedClassName = createClass();

		// If the filter is not annotated, generate the filter metadata for the DD
		if (useExisting || (!model.getBooleanProperty(USE_ANNOTATIONS) && model.getBooleanProperty(REGISTER_IN_WEB_XML)))
		{
			if (model.getBooleanProperty(GENERATE_DD))
			{
				IDataModel ddModel = DataModelFactory.createDataModel(IWebCreateDeploymentFilesDataModelProperties.class);
				ddModel.setProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT, getTargetProject());
				ddModel.getDefaultOperation().execute(monitor, info);
			}
			generateMetaData(model, qualifiedClassName);
		}
		
		return OK_STATUS;
	}
	
	protected abstract void generateMetaData(IDataModel aModel, String qualifiedClassName);

}
