/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.webapplication.DispatcherType;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.UrlPatternType;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * This class, AddFilter Operation is a IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * This operation subclasses the ArtifactEditProviderOperation so the changes
 * made to the deployment descriptor models are saved to the artifact edit
 * model.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * It is the operation which should be used when adding a new filter to a web
 * app. This uses the NewFilterClassDataModelProvider to retrieve properties set by the
 * user in order to create the custom filter.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassDataModelProvider
 * 
 * This operation will add the metadata necessary into the web deployment descriptor. 
 * To actually create the java class for the filter, the operation uses the NewFilterClassOperation. 
 * The NewFilterClassOperation shares the same data model provider.
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassOperation
 * 
 * Clients may subclass this operation to provide their own behavior on filter
 * creation. The execute method can be extended to do so. Also,
 * generateFilterMetaData and creteFilterClass are exposed.
 * 
 * The use of this class is EXPERIMENTAL and is subject to substantial changes.
 */
public class AddFilterOperation extends AddWebClassOperation implements
		INewFilterClassDataModelProperties {

	/**
	 * This is the constructor which should be used when creating the operation.
	 * It will not accept null parameter. It will not return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * 
	 * @param dataModel
	 * @return AddFilterOperation
	 */
	public AddFilterOperation(IDataModel dataModel) {
		super(dataModel);
		provider = ModelProviderManager.getModelProvider(getTargetProject());
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
	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		// Retrieve values set in the newfilterclass data model
	    boolean useExisting = model.getBooleanProperty(USE_EXISTING_CLASS);
		String qualifiedClassName = model.getStringProperty(CLASS_NAME);

		// create the java class
		if (!useExisting) 
			qualifiedClassName = createFilterClass();

		// If the filter is not annotated, generate the filter metadata for the DD
		if (!model.getBooleanProperty(USE_ANNOTATIONS))
			generateFilterMetaData(model, qualifiedClassName);
		
		return OK_STATUS;
	}

	/**
	 * Subclasses may extend this method to add their own creation of the actual
	 * filter java class. This implementation uses the NewFilterClassOperation
	 * which is a subclass of the NewJavaClassOperation. The
	 * NewFilterClassOperation will use the same
	 * NewFilterClassDataModelProvider to retrieve the properties in order to
	 * create the java class accordingly. This method will not return null.
	 * 
	 * @see NewFilterClassOperation
	 * @see org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassOperation
	 * @see NewFilterClassDataModelProvider
	 * 
	 * @return String qualified filter classname
	 */
	protected String createFilterClass() {
		// Create filter java class file using the NewFilterClassOperation.
		// The same data model is shared.
		NewFilterClassOperation op = new NewFilterClassOperation(model);
		try {
			op.execute(new NullProgressMonitor(), null);
		} catch (Exception e) {
			WebPlugin.log(e);
		}
		// Return the qualified classname of the newly created java class for
		// the fitler
		return getQualifiedClassName();
	}

	/**
	 * Subclasses may extend this method to add their own generation steps for
	 * the creation of the metadata for the web deployment descriptor. This
	 * implementation uses the J2EE models to create the Filter model instance,
	 * any init params specified, and any filter mappings. It then adds these
	 * to the web application model. This will then be written out to the
	 * deployment descriptor file. This method does not accept null parameters.
	 * 
	 * @see Filter
	 * @see AddFilterOperation#createFilter(String)
	 * @see AddFilterOperation#setUpInitParams(List, Filter)
	 * @see AddFilterOperation#setUpURLMappings(List, Filter)
	 * 
	 * @param aModel
	 * @param qualifiedClassName
	 */
	protected void generateFilterMetaData(IDataModel aModel,
			String qualifiedClassName) {
		// Set up the filter modelled object
		Object filter = createFilter(qualifiedClassName);

		// Set up the InitParams if any
		List initParamList = 
		    (List) aModel.getProperty(INIT_PARAM);
		if (initParamList != null)
			setUpInitParams(initParamList, filter);

		// Set up the filter mappings if any
		 List filterMappingsList = 
		     (List) aModel.getProperty(FILTER_MAPPINGS);

         if (filterMappingsList != null && !filterMappingsList.isEmpty())
             setUpMappings(filterMappingsList, filter);
	}

	/**
	 * This method is intended for private use only. This method is used to
	 * create the filter modeled object, to set any parameters specified in
	 * the data model, and then to add the filter instance to the web
	 * application model. This method does not accept null parameters. It will
	 * not return null.
	 * 
	 * @see AddFilterOperation#generateFilterMetaData(NewFilterClassDataModel,
	 *      String)
	 * @see WebapplicationFactory#createFilter()
	 * @see Filter
	 * 
	 * @param qualifiedClassName
	 * @return Filter instance
	 */
	/**
	 * @param qualifiedClassName
	 * @return
	 */
	private Object createFilter(String qualifiedClassName) {
		// Get values from data model
		String displayName = 
		    model.getStringProperty(DISPLAY_NAME);
		String description = 
		    model.getStringProperty(DESCRIPTION);

		// Create the filter instance and set up the parameters from data model
		Object modelObject = provider.getModelObject();
		if (modelObject instanceof org.eclipse.jst.j2ee.webapplication.WebApp) {
			Filter filter = WebapplicationFactory.eINSTANCE.createFilter();
			filter.setName(displayName);
			filter.setDisplayName(displayName);
			filter.setDescription(description);
			filter.setFilterClassName(qualifiedClassName);

			// Add the filter to the web application model
			WebApp webApp = (WebApp) modelObject;
			webApp.getFilters().add(filter);
			return filter;
		} else if (modelObject instanceof org.eclipse.jst.javaee.web.WebApp) {
			org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) modelObject;
			org.eclipse.jst.javaee.web.Filter filter = WebFactory.eINSTANCE.createFilter();
			DisplayName displayNameObj = JavaeeFactory.eINSTANCE.createDisplayName();
            displayNameObj.setValue(displayName);
            filter.getDisplayNames().add(displayNameObj);
			filter.setFilterName(displayName);
			filter.setFilterClass(qualifiedClassName);
			if (webApp != null) {
				webApp.getFilters().add(filter);
			}
			// Should be return Filter's instance
			return filter;
		}
		// Return the filter instance
		return null;
	}

	/**
	 * This method is intended for internal use only. This is used to create any
	 * init params for the new filter metadata. It will not accept null
	 * parameters. The init params are set on the filter modeled object.
	 * 
	 * @see AddFilterOperation#generateFilterMetaData(NewFilterClassDataModel,
	 *      String)
	 * @see WebapplicationFactory#createInitParam()
	 * 
	 * @param initParamList
	 * @param filter
	 */
	private void setUpInitParams(List initParamList, Object filterObj) {
		// Get the web app instance from the data model
		Object modelObject = provider.getModelObject();
		if (modelObject instanceof org.eclipse.jst.j2ee.webapplication.WebApp) {
			WebApp webApp = (WebApp) modelObject;
			Filter filter = (Filter) filterObj;

			// If J2EE 1.4, add the param value and description info instances
			// to the filter init params
			if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
				for (int iP = 0; iP < initParamList.size(); iP++) {
					String[] stringArray = (String[]) initParamList.get(iP);
					// Create 1.4 common param value
					InitParam param = WebapplicationFactory.eINSTANCE.createInitParam();
					param.setParamName(stringArray[0]);
					param.setParamValue(stringArray[1]);
					param.setDescription(stringArray[2]);
					// Set the param to the filter model list of init params
					filter.getInitParams().add(param);
				}
			}
			// If J2EE 1.2 or 1.3, use the filter specific init param instances
			else {
				for (int iP = 0; iP < initParamList.size(); iP++) {
					String[] stringArray = (String[]) initParamList.get(iP);
					// Create the web init param
					InitParam ip = WebapplicationFactory.eINSTANCE.createInitParam();
					// Set the param name
					ip.setParamName(stringArray[0]);
					// Set the param value
					ip.setParamValue(stringArray[1]);
					// Set the param description
					ip.setDescription(stringArray[2]);
					// Add the init param to the filter model list of params
					filter.getInitParams().add(ip);
				}
			}
		} else if (modelObject instanceof org.eclipse.jst.javaee.web.WebApp) {
			org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) filterObj;

			for (int iP = 0; iP < initParamList.size(); iP++) {
				String[] stringArray = (String[]) initParamList.get(iP);
				// Create 1.4 common param value
				org.eclipse.jst.javaee.core.ParamValue param = 
				    JavaeeFactory.eINSTANCE.createParamValue();
				param.setParamName(stringArray[0]);
				param.setParamValue(stringArray[1]);

				org.eclipse.jst.javaee.core.Description descriptionObj = 
				    JavaeeFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(stringArray[2]);
				// Set the description on the param
				param.getDescriptions().add(descriptionObj);
				// Add the param to the filter model list of init params
				filter.getInitParams().add(param);
			}
		}
	}

	/**
	 * This method is intended for internal use only. This method is used to
	 * create the filter mapping modelled objects so the metadata for the
	 * filter mappings is store in the web deployment descriptor. This method
	 * will not accept null parameters. The filter mappings are added to the
	 * web application modeled object.
	 * 
	 * @see AddFilterOperation#generateFilterMetaData(NewFilterClassDataModel,
	 *      String)
	 * @see WebapplicationFactory#createFilterMapping()
	 * 
	 * @param urlMappingList
	 * @param filter
	 */
	private void setUpMappings(List filterMappingsList, Object filterObj) {
		// Get the web app modelled object from the data model
		// WebApp webApp = (WebApp) artifactEdit.getContentModelRoot();
		Object modelObject = provider.getModelObject();

		// Create the filter mappings if any
		if (modelObject instanceof org.eclipse.jst.j2ee.webapplication.WebApp) {
			WebApp webApp = (WebApp) modelObject;
			Filter filter = (Filter) filterObj;
			if (filterMappingsList != null) 
			    for (int iM = 0; iM < filterMappingsList.size(); iM++) {
			        IFilterMappingItem filterMapping = (IFilterMappingItem) filterMappingsList.get(iM);
			        // Create the filter mapping instance from the web factory
			        FilterMapping mapping = WebapplicationFactory.eINSTANCE.createFilterMapping();
			        // Set the filter
			        mapping.setFilter(filter);
			        if (filterMapping.isUrlPatternType()) {
			            // Set the URL pattern to map the filter to
			            mapping.setUrlPattern(filterMapping.getName());
			        } else {
                        // Set the Servlet Name to map the filter to
			        	Servlet servlet = webApp.getServletNamed(filterMapping.getName());
                        mapping.setServlet(servlet);
			        }
			        //Set dispatcher options for the filter mapping if any.
			        int dispatchers = filterMapping.getDispatchers();
			        EList dispatcherTypes = mapping.getDispatcherType();
                    if ((dispatchers & IFilterMappingItem.REQUEST) > 0) {
                        dispatcherTypes.add(DispatcherType.REQUEST_LITERAL);
                    }
                    if ((dispatchers & IFilterMappingItem.FORWARD) > 0) {
                        dispatcherTypes.add(DispatcherType.FORWARD_LITERAL);
                    }
                    if ((dispatchers & IFilterMappingItem.INCLUDE) > 0) {
                        dispatcherTypes.add(DispatcherType.INCLUDE_LITERAL);
                    }
                    if ((dispatchers & IFilterMappingItem.ERROR) > 0) {
                        dispatcherTypes.add(DispatcherType.ERROR_LITERAL);
                    }
			        // Add the filter mapping to the web application modelled list
			        webApp.getFilterMappings().add(mapping);
			    }
		} else if (modelObject instanceof org.eclipse.jst.javaee.web.WebApp) {
		    org.eclipse.jst.javaee.web.WebApp webApp = (org.eclipse.jst.javaee.web.WebApp) modelObject;
		    org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) filterObj;

		    // Create the filter mapping instance from the web factory
            org.eclipse.jst.javaee.web.FilterMapping mapping = null;
			// Create the filter mappings if any
			if (filterMappingsList != null) {
				for (int i = 0; i < filterMappingsList.size(); i++) {
	                mapping = WebFactory.eINSTANCE.createFilterMapping();
	                mapping.setFilterName(filter.getFilterName());
	                IFilterMappingItem filterMapping = (IFilterMappingItem) filterMappingsList.get(i);
	                if (filterMapping.getMappingType() == IFilterMappingItem.URL_PATTERN) {
	                    // Set the URL pattern to map the filter to
	                    UrlPatternType url = JavaeeFactory.eINSTANCE.createUrlPatternType();
	                    url.setValue(filterMapping.getName());
	                    mapping.getUrlPatterns().add(url);
	                } else {
	                    mapping.getServletNames().add(filterMapping.getName());
	                }
  			        //Set dispatcher options for the filter mapping if any.	
                    int dispatchers = filterMapping.getDispatchers();
                    if ((dispatchers & IFilterMappingItem.REQUEST) > 0) {
                        mapping.getDispatchers().add(org.eclipse.jst.javaee.web.DispatcherType.REQUEST_LITERAL);
                    }
                    if ((dispatchers & IFilterMappingItem.FORWARD) > 0) {
                        mapping.getDispatchers().add(org.eclipse.jst.javaee.web.DispatcherType.FORWARD_LITERAL);
                    }
                    if ((dispatchers & IFilterMappingItem.INCLUDE) > 0) {
                        mapping.getDispatchers().add(org.eclipse.jst.javaee.web.DispatcherType.INCLUDE_LITERAL);
                    }
                    if ((dispatchers & IFilterMappingItem.ERROR) > 0) {
                        mapping.getDispatchers().add(org.eclipse.jst.javaee.web.DispatcherType.ERROR_LITERAL);
                    }
                    // Add the filter mapping to the web application model list
                    webApp.getFilterMappings().add(mapping);
				}
			}
		}
	}

}
