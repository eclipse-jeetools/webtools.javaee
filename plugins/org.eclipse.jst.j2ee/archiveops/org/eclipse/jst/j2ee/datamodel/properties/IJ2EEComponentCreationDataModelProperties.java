package org.eclipse.jst.j2ee.datamodel.properties;


/**
 * <p>
 * IJ2EEComponentCreationDataModelProperties provides properties to the 
 * J2EEComponentCreationDataModelProvider as well as all extending interfaces extending 
 * IJ2EEComponentCreationDataModelProperties 
 * @see org.eclipse.jst.j2ee.internal.archive.operations.J2EEComponentCreationDataModelProvider
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties
 * @see org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties
 * @since 1.0
 */
public interface IJ2EEComponentCreationDataModelProperties extends IJavaComponentCreationDataModelProperties{

	/**
	 * Required, type Boolean, the default value is <code>Boolean.FALSE</code>.
	 */
	public static final String ADD_TO_EAR = "IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR"; //$NON-NLS-1$
	/**
	 * type String, this property needs to be set if the value of the property ADD_TO_EAR  is set to <code>Boolean.TRUE</code>
	 */
	public static final String EAR_COMPONENT_NAME = "IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME"; //$NON-NLS-1$
	
	/**
	 * type String, this property needs to be set if the value of the property ADD_TO_EAR  is set to <code>Boolean.TRUE</code>
	 */	
	public static final String EAR_COMPONENT_DEPLOY_NAME = "IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_DEPLOY_NAME"; //$NON-NLS-1$
	
	/**
	 * type Boolean; the default value is <code>Boolean.TRUE</code>, this is UI only property
	 */
	public static final String UI_SHOW_EAR_SECTION = "IJ2EEComponentCreationDataModelProperties.UI_SHOW_EAR_SECTION"; //$NON-NLS-1$
	
	/**
	 * Optional, type String
     * String indicates the name of the the folder where the deployment descriptor exists.
     * Each J2EE componenr provider will default the Deployment descriptor folder.
     * 
	 */
	
	public static final String DD_FOLDER = "IJ2EEComponentCreationDataModelProperties.DD_FOLDER"; //$NON-NLS-1$

}
