package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;

public interface IMessageDrivenBeanDataModelProperties extends IEnterpriseBeanClassDataModelProperties, IAnnotationsDataModel {

	public static final String DESTINATIONNAME = "MessageDrivenBeanDataModel.DESTINATIONNAME"; //$NON-NLS-1$
	public static final String DESTINATIONTYPE = "MessageDrivenBeanDataModel.DESTINATIONTYPE"; //$NON-NLS-1$

	public final static String EJB_INTERFACES = "MessageDrivenBeanDataModel.EJB_INTERFACES"; //$NON-NLS-1$
													
}
