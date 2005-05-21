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
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * This class, AddServlet Operation is a WTPOperation following the WTP wizard data model and
 * operation framework.
 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperation
 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel
 * 
 * This operation subclasses the EditModelOperation so the changes made to the deployment descriptor
 * models are saved to the edit model.
 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation
 * 
 * It is the operation which should be used when adding a new servlet to
 * a web app, whether that be an annotated servlet or a non annotated servlet.  This uses the
 * NewServletClassDataModel to retrieve properties set by the user in order to create the custom
 * servet.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewServletClassDataModel
 * 
 * In the non annotated case, this operation will add the metadata necessary into the web deployment
 * descriptor.  In the annotated case, it will not, it will leave this up to the parsing of the
 * annotations to build the deployment descriptor artifacts.  To actually create the java class for
 * the servlet, the operation uses the NewServletClassOperation. The NewServletClassOperation 
 * shares the same data model.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewServletClassOperation
 * 
 * Clients may subclass this operation to provide their own behaviour on servlet creation.  The execute
 * method can be extended to do so. Also, generateServletMetaData and creteServletClass are exposed.
 * 
 * The use of this class is EXPERIMENTAL and is subject to substantial changes.
 */
public class AddServletOperation extends ArtifactEditOperation {
	
	/**
	 * This is the constructor which should be used when creating the operation.
	 * It will not accept null parameter.  It will not return null.
	 * @see WTPOperation#WTPOperation(WTPOperationDataModel)
	 * 
	 * @param dataModel NewServletClassDataModel
	 * @return AddServletOperation
	 */
	public AddServletOperation(NewServletClassDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Subclasses may extend this method to add their own actions during execution.
	 * The implementation of the execute method drives the running of the operation. This
	 * implementation will create the servlet class, and then if the servlet is not
	 * annotated, it will create the servlet metadata for the web deployment descriptor.
	 * This method will accept null as a parameter.
	 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperation#execute(IProgressMonitor)
	 * @see AddServletOperation#createServletClass()
	 * @see AddServletOperation#generateServletMetaData(NewServletClassDataModel, String, boolean)
	 * 
	 * @param monitor IProgressMonitor
	 * @throws CoreException
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		//Retrieve values set in the newservletclass data model
		NewServletClassDataModel model = (NewServletClassDataModel) operationDataModel;
		boolean isServletType = model.getBooleanProperty(NewServletClassDataModel.IS_SERVLET_TYPE);
		String qualifiedClassName = model.getStringProperty(NewJavaClassDataModel.CLASS_NAME);
		
		// If it is servlet type, create the java class
		if (isServletType)
			qualifiedClassName = createServletClass();

		// If the servlet is not annotated, generate the servlet metadata for the DD
		if (!model.getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			generateServletMetaData(model, qualifiedClassName, isServletType);
	}
	
	/**
	 * Subclasses may extend this method to add their own creation of the actual servlet java class.
	 * This implementation uses the NewServletClassOperation which is a subclass of the NewJavaClassOperation.
	 * The NewServletClassOperation will use the same NewServletClassDataModel to retrieve the properties in
	 * order to create the java class accordingly.  This method will not return null.
	 * @see NewServletClassOperation
	 * @see org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassOperation
	 * @see NewServletClassDataModel
	 * 
	 * @return String qualified servlet classname
	 */
	protected String createServletClass() {
		//	Create servlet java class file using the NewServletClassOperation.  The same data model is shared.
		NewServletClassDataModel model = (NewServletClassDataModel) operationDataModel;
		NewServletClassOperation op = new NewServletClassOperation(model);
		try {
			op.run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().log(e);
		} catch (InterruptedException e) {
			Logger.getLogger().log(e);
		}
		// Return the qualified classname of the newly created java class for the servlet
		return model.getQualifiedClassName();
	}

	/**
	 * Subclasses may extend this method to add their own generation steps for the creation of the
	 * metadata for the web deployment descriptor.  This implementation uses the J2EE models to create
	 * the Servlet model instance, any init params specified, and any servlet mappings.  It then adds 
	 * these to the web application model.  This will then be written out to the deployment descriptor
	 * file.  This method does not accept null parameters.
	 * @see Servlet
	 * @see AddServletOperation#createServlet(String, boolean)
	 * @see AddServletOperation#setUpInitParams(List, Servlet)
	 * @see AddServletOperation#setUpURLMappings(List, Servlet)
	 * 
	 * @param model
	 * @param qualifiedClassName
	 * @param isServletType
	 */
	protected void generateServletMetaData(NewServletClassDataModel model, String qualifiedClassName, boolean isServletType) {
		// Set up the servlet modelled object
		Servlet servlet = createServlet(qualifiedClassName, isServletType);

		// Set up the InitParams if any
		List initParamList = (List) model.getProperty(NewServletClassDataModel.INIT_PARAM);
		if (initParamList != null)
			setUpInitParams(initParamList,servlet);

		// Set up the servlet URL mappings if any
		List urlMappingList = (List) model.getProperty(NewServletClassDataModel.URL_MAPPINGS);
		if (urlMappingList != null)
			setUpURLMappings(urlMappingList, servlet);
	}
	
	/**
	 * This method is intended for private use only.  This method is used to create the servlet
	 * modelled object, to set any parameters specified in the data model, and then to add the
	 * servlet instance to the web application model.  This method does not accpet null parameters.
	 * It will not return null.
	 * @see AddServletOperation#generateServletMetaData(NewServletClassDataModel, String, boolean)
	 * @see WebapplicationFactory#createServlet()
	 * @see Servlet
	 * 
	 * @param qualifiedClassName
	 * @param isServletType
	 * @return Servlet instance
	 */
	private Servlet createServlet(String qualifiedClassName, boolean isServletType) {
		// Get values from data model
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		String displayName = model.getStringProperty(NewServletClassDataModel.DISPLAY_NAME);
		String description = model.getStringProperty(NewServletClassDataModel.DESCRIPTION);
		
		// Create the servlet instance and set up the parameters from data model
		Servlet servlet = WebapplicationFactory.eINSTANCE.createServlet();
		servlet.setDisplayName(displayName);
		servlet.setServletName(displayName);
		servlet.setDescription(description);
		// Handle servlet case
		if (isServletType) {
			ServletType servletType = WebapplicationFactory.eINSTANCE.createServletType();
			servletType.setClassName(qualifiedClassName);
			servlet.setWebType(servletType);
		} 
		// Handle JSP case
		else {
			JSPType jspType = WebapplicationFactory.eINSTANCE.createJSPType();
			jspType.setJspFile(qualifiedClassName);
			servlet.setWebType(jspType);
		}
		// Add the servlet to the web application model
		WebApp webApp = (WebApp) getArtifactEdit().getContentModelRoot();
		webApp.getServlets().add(servlet);
		// Return the servlet instance
		return servlet;
	}
	
	/**
	 * This method is intended for internal use only.  This is used to create any init params
	 * for the new servlet metadata.  It will not accept null parameters.  The init params are
	 * set on the servlet modelled object.
	 * @see AddServletOperation#generateServletMetaData(NewServletClassDataModel, String, boolean)
	 * @see WebapplicationFactory#createInitParam()
	 * 
	 * @param initParamList
	 * @param servlet
	 */
	private void setUpInitParams(List initParamList, Servlet servlet) {
		// Get the web app instance from the data model
		WebApp webApp = (WebApp) getArtifactEdit().getContentModelRoot();
		int nP = initParamList.size();
		// If J2EE 1.4, add the param value and description info instances to the servlet init params
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			for (int iP = 0; iP < nP; iP++) {
				String[] stringArray = (String[]) initParamList.get(iP);
				// Create 1.4 common param value
				ParamValue param = CommonFactory.eINSTANCE.createParamValue();
				param.setName(stringArray[0]);
				param.setValue(stringArray[1]);
				// Create 1.4 common descripton value
				Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(stringArray[2]);
				// Set the description on the param
				param.getDescriptions().add(descriptionObj);
				param.setDescription(stringArray[2]);
				// Add the param to the servlet model list of init params
				servlet.getInitParams().add(param);
			}
		}
		// If J2EE 1.2 or 1.3, use the servlet specific init param instances
		else {
			for (int iP = 0; iP < nP; iP++) {
				String[] stringArray = (String[]) initParamList.get(iP);
				// Create the web init param
				InitParam ip = WebapplicationFactory.eINSTANCE.createInitParam();
				// Set the param name
				ip.setParamName(stringArray[0]);
				// Set the param value
				ip.setParamValue(stringArray[1]);
				// Set the param description
				ip.setDescription(stringArray[2]);
				// Add the init param to the servlet model list of params
				servlet.getParams().add(ip);
			}
		}
	}
	
	/**
	 * This method is intended for internal use only.  This method is used to create the servlet
	 * mapping modelled objects so the metadata for the servlet mappings is store in the web
	 * deployment descriptor.  This method will not accept null parameters.  The servlet mappings
	 * are added to the web application modelled object.
	 * @see AddServletOperation#generateServletMetaData(NewServletClassDataModel, String, boolean)
	 * @see WebapplicationFactory#createServletMapping()
	 * 
	 * @param urlMappingList
	 * @param servlet
	 */
	private void setUpURLMappings(List urlMappingList, Servlet servlet) {
		// Get the web app modelled object from the data model
		WebApp webApp = (WebApp) getArtifactEdit().getContentModelRoot();
		int nM = urlMappingList.size();
		// Create the servlet mappings if any
		for (int iM = 0; iM < nM; iM++) {
			String[] stringArray = (String[]) urlMappingList.get(iM);
			// Create the servlet mapping instance from the web factory
			ServletMapping mapping = WebapplicationFactory.eINSTANCE.createServletMapping();
			// Set the servlet and servlet name
			mapping.setServlet(servlet);
			mapping.setName(servlet.getServletName());
			// Set the URL pattern to map the servlet to
			mapping.setUrlPattern(stringArray[0]);
			// Add the servlet mapping to the web application modelled list
			webApp.getServletMappings().add(mapping);
		}
	}
}