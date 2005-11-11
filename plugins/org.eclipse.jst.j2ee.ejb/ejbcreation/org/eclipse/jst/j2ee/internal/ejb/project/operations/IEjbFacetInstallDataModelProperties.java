package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;

public interface IEjbFacetInstallDataModelProperties 
	extends IJ2EEModuleFacetInstallDataModelProperties {

    public static final String CREATE_CLIENT = "IEjbFacetInstallDataModelProperties.CREATE_CLIENT"; //$NON-NLS-1$	
	public static final String CLIENT_NAME = "IEjbFacetInstallDataModelProperties.CLIENT_NAME ";//$NON-NLS-1$	
	public static final String CLIENT_SOURCE_FOLDER = "IEjbFacetInstallDataModelProperties.CLIENT_SOURCE_FOLDER ";//$NON-NLS-1$
	public static final String CLIENT_URI = "IEjbFacetInstallDataModelProperties.CLIENT_URI ";//$NON-NLS-1$	
}
