/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;

public interface IContainerManagedEntityBeanDataModelProperties extends IEnterpriseBeanClassDataModelProperties, IAnnotationsDataModel {

	public static final String DATASOURCE = "EntityBeanDataModel.DATASOURCE"; //$NON-NLS-1$
	public static final String SCHEMA = "EntityBeanDataModel.SCHEMA"; //$NON-NLS-1$
	public static final String TABLE = "EntityBeanDataModel.TABLE"; //$NON-NLS-1$
	public static final String ATTRIBUTES = "EntityBeanDataModel.ATTRIBUTES"; //$NON-NLS-1$
	public static final String VERSION = "EntityBeanDataModel.VERSION"; //$NON-NLS-1$

	public final static String EJB_INTERFACES = "EntityBeanDataModel.EJB_INTERFACES"; //$NON-NLS-1$
													
}
