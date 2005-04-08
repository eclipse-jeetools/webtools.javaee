package org.eclipse.jst.j2ee.application.internal.operations;


public interface IFlexibleProjectCreationDataModelProperties {
	/**
	 * An optional dataModel property for a <code>Boolean</code> type. The default value is
	 * <code>Boolean.TRUE</code>. If this property is set to <code>Boolean.TRUE</code> then the
	 * server target specified by dataModel property <code>SERVER_TARGET_ID</code> will be set on
	 * the generated artifact.
	 * 
	 * @see SERVER_TARGET_ID
	 */
	public static final String ADD_SERVER_TARGET = "IFlexibleProjectCreationDataModelProperties.ADD_SERVER_TARGET"; //$NON-NLS-1$
	public static final String NESTED_MODEL_SERVER_TARGET = "IFlexibleProjectCreationDataModelProperties.NESTED_MODEL_SERVER_TARGET"; //$NON-NLS-1$
	public static final String PROJECT_NAME = "IFlexibleProjectCreationDataModelProperties.PROJECT_NAME"; //$NON-NLS-1$
	public static final String PROJECT_LOCATION = "IFlexibleProjectCreationDataModelProperties.PROJECT_LOCATION"; //$NON-NLS-1$
	public static final String NESTED_MODEL_PROJECT_CREATION = "IFlexibleProjectCreationDataModelProperties.NESTED_MODEL_PROJECT_CREATION"; //$NON-NLS-1$
}
