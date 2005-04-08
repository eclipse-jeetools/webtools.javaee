package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.jst.j2ee.internal.archive.operations.IJavaComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.IComponentCreationDataModelProperties;

public interface IJ2EEComponentCreationDataModelProperties extends IJavaComponentCreationDataModelProperties{

	/**
	 * type Boolean, default false
	 */
	public static final String ADD_TO_EAR = "IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR"; //$NON-NLS-1$
	/**
	 * type String
	 */
	public static final String EAR_MODULE_NAME = "IJ2EEComponentCreationDataModelProperties.EAR_MODULE_NAME"; //$NON-NLS-1$
	
	public static final String EAR_MODULE_DEPLOY_NAME = "IJ2EEComponentCreationDataModelProperties.EAR_MODULE_DEPLOY_NAME"; //$NON-NLS-1$
	
	/**
	 * type boolean
	 */	
	public static final String NESTED_MODEL_ADD_TO_EAR = "IJ2EEComponentCreationDataModelProperties.NESTED_MODEL_ADD_TO_EAR"; //$NON-NLS-1$
	
	public static final String NESTED_MODEL_EAR_CREATION = "IJ2EEComponentCreationDataModelProperties.NESTED_MODEL_EAR_CREATION"; //$NON-NLS-1$
	
	/**
	 * type boolean
	 */		
	public static final String NESTED_MODEL_JAR_DEPENDENCY = "IJ2EEComponentCreationDataModelProperties.NESTED_MODEL_JAR_DEPENDENCY"; //$NON-NLS-1$
	
	/**
	 * type Boolean; default true, UI only
	 */
	public static final String UI_SHOW_EAR_SECTION = "IJ2EEComponentCreationDataModelProperties.UI_SHOW_EAR_SECTION"; //$NON-NLS-1$
	
	/**
	 * type String
	 */
	public static final String DD_FOLDER = "IJ2EEComponentCreationDataModelProperties.DD_FOLDER"; //$NON-NLS-1$
	
	
	/**
	 * This corresponds to the J2EE versions of 1.2, 1.3, 1.4, etc. Each subclass will convert this
	 * version to its corresponding highest module version supported by the J2EE version and set the
	 * J2EE_MODULE_VERSION property.
	 * 
	 * type Integer
	 */
	public static final String J2EE_VERSION = "IJ2EEComponentCreationDataModelProperties.J2EE_VERSION"; //$NON-NLS-1$

	
	

}
