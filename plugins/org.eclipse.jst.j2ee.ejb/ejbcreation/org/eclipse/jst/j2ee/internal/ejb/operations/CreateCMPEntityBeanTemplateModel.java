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
 * Created on Mar 17, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.util.List;

import org.eclipse.jst.j2ee.internal.ejb.creation.CMPField;


/**
 * @author schacher
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CreateCMPEntityBeanTemplateModel extends CreateEntityBeanTemplateModel {
	/**
	 * @param model
	 */
	public CreateCMPEntityBeanTemplateModel(CreateEntityBeanDataModel model) {
		super(model);
	}

	public boolean isCMP2x() {
		return getCMPModel().isVersion2xOrGreater();
	}

	protected CreateCMPBeanDataModel getCMPModel() {
		return (CreateCMPBeanDataModel) model;
	}

	public List getCMPFields() {
		return getCMPModel().getCMPFields();
	}

	public boolean usesPrimitiveKey() {
		return getCMPModel().getBooleanProperty(CreateCMPBeanDataModel.USE_PRIMITIVE_KEY);
	}

	public String getPrimkeyFieldName() {
		if (usesPrimitiveKey()) {
			CMPField field = getCMPModel().getPrimKeyField();
			if (field != null)
				return field.getName();
		}
		return null;
	}
}