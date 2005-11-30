package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;

public interface IContainerManagedEntityBeanDataModelProperties extends IEnterpriseBeanClassDataModelProperties, IAnnotationsDataModel {

	public static final String DATASOURCE = "EntityBeanDataModel.DATASOURCE"; //$NON-NLS-1$
	public static final String SCHEMA = "EntityBeanDataModel.SCHEMA"; //$NON-NLS-1$
	public static final String TABLE = "EntityBeanDataModel.TABLE"; //$NON-NLS-1$
	public static final String SQLTYPES = "EntityBeanDataModel.SQLTYPES"; //$NON-NLS-1$
	public static final String ATTRIBUTETYPES = "EntityBeanDataModel.ATTRIBUTETYPES"; //$NON-NLS-1$

	public final static String EJB_INTERFACES = "EntityBeanDataModel.EJB_INTERFACES"; //$NON-NLS-1$
													
}
