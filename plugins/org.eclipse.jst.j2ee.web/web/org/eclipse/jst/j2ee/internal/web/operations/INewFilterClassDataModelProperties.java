package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;

public interface INewFilterClassDataModelProperties extends INewJavaClassDataModelProperties, IAnnotationsDataModel {
	/**
	 * Optional, boolean property used to specify whether to generate the init method.
	 * The default is false.
	 */
	public static final String INIT = "NewFilterClassDataModel.INIT"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the destroy method.
	 * The default is false.
	 */
	public static final String DESTROY = "NewFilterClassDataModel.DESTROY"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the toString method.
	 * The default is false.
	 */
	public static final String TO_STRING = "NewFilterClassDataModel.TO_STRING"; //$NON-NLS-1$
	
	/**
	 * Optional, boolean property used to specify whether to generate the doFilter method.
	 * The default is true.
	 */
	public static final String DO_FILTER = "NewFilterClassDataModel.DO_FILTER"; //$NON-NLS-1$
	
	/**
	 * Optional, List property used to cache all the init params defined on the filter.
	 */
	public static final String INIT_PARAM = "NewFilterClassDataModel.INIT_PARAM"; //$NON-NLS-1$
	
	/**
	 * Optional, List propety used to cache all the filter mappings for this filter on the web application.
	 */
	public static final String URL_MAPPINGS = "NewFilterClassDataModel.URL_MAPPINGS"; //$NON-NLS-1$

	/**
     * Optional, List propety used to cache all the filter mappings for this filter on the web application.
     */
    public static final String SERVLET_MAPPINGS = "NewFilterClassDataModel.SERVLET_MAPPINGS"; //$NON-NLS-1$

	/**
	 * Required, String property of the display name for the filter
	 */
	public static final String DISPLAY_NAME = "NewFilterClassDataModel.DISPLAY_NAME"; //$NON-NLS-1$
	
	/**
	 * Optional, String property of the description info for the filter
	 */
	public static final String DESCRIPTION = "NewFilterClassDataModel.DESCRIPTION"; //$NON-NLS-1$
	
    /**
	 * Optional, boolean property used to specify whether or not to gen a new java class.
	 * The default is false.
	 */
	public static final String USE_EXISTING_CLASS = "NewFilterClassDataModel.USE_EXISTING_CLASS"; //$NON-NLS-1$
	
}
