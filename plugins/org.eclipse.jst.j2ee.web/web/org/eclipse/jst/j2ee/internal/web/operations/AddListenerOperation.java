/*******************************************************************************
 * Copyright (c) 2007, 2012 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaEEArtifactClassOperation;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * This class, AddListenerOperation is a IDataModelOperation following the IDataModel wizard and
 * operation framework.
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * This operation subclasses the ArtifactEditProviderOperation so the changes made to the deployment descriptor
 * models are saved to the artifact edit model.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * It is the operation which should be used when adding a new application lifecycle listener to
 * a web app, whether that be an annotated listener or a non annotated listener.  This uses the
 * NewListenerClassDataModelProvider to retrieve properties set by the user in order to create the custom
 * listener class.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassDataModelProvider
 * 
 * In the non annotated case, this operation will add the metadata necessary into the web deployment
 * descriptor. In the annotated case, it will not, it will leave this up to the parsing of the
 * annotations to build the deployment descriptor artifacts. To actually create the java class for
 * the listener, the operation uses the NewListenerClassOperation. The NewListenerClassOperation 
 * shares the same data model provider.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassOperation
 * 
 * Clients may subclass this operation to provide their own behavior on listener creation. The execute
 * method can be extended to do so. Also, generateListenerMetaData and creteListenerClass are exposed.
 *
 */
public class AddListenerOperation extends AddWebClassOperation {
	
	/**
	 * This is the constructor which should be used when creating the operation.
	 * It will not accept null parameter.  It will not return null.
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * 
	 * @param dataModel 
	 * @return AddListenerOperation
	 */
	public AddListenerOperation(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	protected NewJavaEEArtifactClassOperation getNewClassOperation() {
		return new NewListenerClassOperation(getDataModel());
	}

	/**
	 * Subclasses may extend this method to add their own generation steps for the creation of the
	 * metadata for the web deployment descriptor.  This implementation uses the J2EE models to create
	 * the Listener model instance. It then adds these to the web application model. This will then be 
	 * written out to the deployment descriptor file. This method does not accept null parameters.
	 * @see Listener
	 * @see AddListenerOperation#createListener(String)
	 * 
	 * @param aModel
	 * @param qualifiedClassName
	 */
	@Override
	protected void generateMetaData(IDataModel aModel, String qualifiedClassName) {
		// Set up the listener modeled object
		createListener(qualifiedClassName);
	}
	
	/**
	 * This method is intended for private use only. This method is used to create the listener
	 * modeled object, to set any parameters specified in the data model, and then to add the
	 * listener instance to the web application model. This method does not accept null parameters.
	 * It will not return null.
	 * @see AddListenerOperation#generateListenerMetaData(IDataModel, String)
	 * @see CommonFactory#createListener()
	 * @see Listener
	 * 
	 * @param qualifiedClassName
	 * @return Listener instance
	 */
	private Object createListener(String qualifiedClassName) {
		Object modelObject = provider.getModelObject(WEB_APP_XML_PATH);
		if (modelObject instanceof org.eclipse.jst.j2ee.webapplication.WebApp ){
			// Create the listener instance and set up the parameters from data model
			Listener listener = CommonFactory.eINSTANCE.createListener();
			listener.setListenerClassName(qualifiedClassName);

			// Add the listener to the web application model
			WebApp webApp = (WebApp) modelObject;
			webApp.getListeners().add(listener);
			return listener;
		} else if (modelObject instanceof org.eclipse.jst.javaee.web.IWebCommon ){
			// Create the listener instance and set up the parameters from data model
			org.eclipse.jst.javaee.core.Listener listener = JavaeeFactory.eINSTANCE.createListener();
			listener.setListenerClass(qualifiedClassName);

			// Add the listener to the web application model
			org.eclipse.jst.javaee.web.IWebCommon webApp = (org.eclipse.jst.javaee.web.IWebCommon) modelObject;
			webApp.getListeners().add(listener);	
			return listener;
		}
		
		return null;
	}
	
}
