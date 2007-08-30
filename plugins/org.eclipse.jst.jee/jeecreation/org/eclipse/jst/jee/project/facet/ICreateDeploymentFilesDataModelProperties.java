package org.eclipse.jst.jee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface ICreateDeploymentFilesDataModelProperties extends
		IDataModelProperties {

	public static final String TARGET_PROJECT = "ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT"; //$NON-NLS-1$
	/**
	 *  boolean property for Java EE 5 projects, to create/not create a deployment descriptor, 
	 *  The default value is false
	 */
	public static final String GENERATE_DD = "ICreateDeploymentFilesDataModelProperties.GENERATE_DD"; //$NON-NLS-1$ 

}
