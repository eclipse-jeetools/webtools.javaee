/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 26, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;



/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CreateEnterpriseBeanTemplateModel {
	protected CreateEnterpriseBeanDataModel model;
	private String beanClassName;

	public CreateEnterpriseBeanTemplateModel(CreateEnterpriseBeanDataModel model) {
		this.model = model;
	}

	private String getQualifiedBeanClassName() {
		if (beanClassName == null)
			beanClassName = getProperty(CreateEnterpriseBeanDataModel.BEAN_CLASS_NAME);
		return beanClassName;
	}

	protected String getProperty(String propertyName) {
		return model.getStringProperty(propertyName);
	}

	public String getBeanClassPackageName() {
		String name = getQualifiedBeanClassName();
		int index = name.lastIndexOf('.');
		return name.substring(0, index);
	}

	public String getBeanName() {
		return getProperty(CreateEnterpriseBeanDataModel.BEAN_NAME);
	}

	public String getSimpleBeanClassName() {
		String name = getQualifiedBeanClassName();
		int index = name.lastIndexOf('.');
		return name.substring(index + 1);
	}

	public boolean is2x() {
		EJBNatureRuntime nature = model.getEJBNature();
		return nature.getModuleVersion() >= J2EEVersionConstants.EJB_2_0_ID;
	}

	public String getQualifiedSuperBeanClassName() {
		return getProperty(CreateEnterpriseBeanDataModel.BEAN_CLASS_SUPERCLASS);
	}

}