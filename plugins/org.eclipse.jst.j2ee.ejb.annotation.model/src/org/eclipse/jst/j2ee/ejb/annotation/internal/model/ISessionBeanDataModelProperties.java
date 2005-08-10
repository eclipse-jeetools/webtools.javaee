package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;

public interface ISessionBeanDataModelProperties extends IEnterpriseBeanClassDataModelProperties, IAnnotationsDataModel {

	public static final String STATELESS = "SessionBeanDataModel.STATELESS"; //$NON-NLS-1$
	
	public final static String EJB_INTERFACES = "SessionBeanDataModel.EJB_INTERFACES"; //$NON-NLS-1$
}
