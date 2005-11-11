package org.eclipse.jst.j2ee.project.facet;

public interface IAppClientFacetInstallDataModelProperties extends IJ2EEModuleFacetInstallDataModelProperties {

	 /**
     * Optional, type Boolean. the default value is <code>Boolean.TRUE</code>.If this is true and CREATE_DEFAULT_FILES is true, then a default main
     * class will be generated during component creation.
     */
    public static final String CREATE_DEFAULT_MAIN_CLASS = "IAppClientComponentCreationDataModelProperties.CREATE_DEFAULT_MAIN_CLASS"; //$NON-NLS-1$
    
}
