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
 * Created on May 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.ejb.ActivationConfig;
import org.eclipse.jst.j2ee.ejb.ActivationConfigProperty;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class MDBNonJMSActivationConfigOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public MDBNonJMSActivationConfigOperation(MDBNonJMSActivationConfigDataModel dataModel) {
		super(dataModel);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() throws CoreException {
		MDBNonJMSActivationConfigDataModel dataModel = (MDBNonJMSActivationConfigDataModel) getOperationDataModel();
		if (dataModel != null) {
			ActivationConfig config = dataModel.getActivationConfig();
			List configProperties = config.getConfigProperties();
			for (int i = 0; i < configProperties.size(); i++) {
				ActivationConfigProperty property = (ActivationConfigProperty) configProperties.get(i);
				if (property != null) {
					createPropertyNameModelHelper(property);
					createPropertyValueModelHelper(property);
				}
			}
		}
	}

	/**
	 * @param property
	 * @return
	 */
	private void createPropertyNameModelHelper(ActivationConfigProperty property) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(property);
		helper.setFeature(EjbFactoryImpl.getPackage().getActivationConfigProperty_Name());
		helper.setValue(property.getName());
		modifier.addHelper(helper);
	}

	/**
	 * @param property
	 * @param helper
	 */
	private void createPropertyValueModelHelper(ActivationConfigProperty property) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(property);
		helper.setFeature(EjbFactoryImpl.getPackage().getActivationConfigProperty_Value());
		helper.setValue(property.getValue());
		modifier.addHelper(helper);
	}
}