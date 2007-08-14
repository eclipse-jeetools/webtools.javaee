package org.eclipse.jst.jee.ui.internal;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface ICreateDeploymentFilesDataModelProperties extends
		IDataModelProperties {

	/**
	 * This field should not be used.  It is not part of the API and may be modified in the future.
	 */
	public static Class _provider_class = CreateDeploymentFilesDataModelProvider.class;
	public static final String TARGET_PROJECT = "ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT"; //$NON-NLS-1$
	/**
	 *  boolean property for Java EE 5 projects, to create/not create a deployment descriptor, 
	 *  The default value is false
	 */
	public static final String GENERATE_DD = "ICreateDeploymentFilesDataModelProperties.GENERATE_DD"; //$NON-NLS-1$ 

}
