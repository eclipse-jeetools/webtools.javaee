/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Dec 2, 2003
 * 
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.common.operations;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JARDependencyDataModelProvider extends AbstractDataModelProvider implements JARDependencyDataModelProperties {

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EAR_PROJECT_NAME);
		propertyNames.add(REFERENCED_PROJECT_NAME);
		propertyNames.add(JAR_MANIPULATION_TYPE);
		propertyNames.add(OPPOSITE_PROJECT_NAME);
		return propertyNames;
	}

	public void init() {
		super.init();
		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
		model.addNestedModel(NESTED_MODEL_UPDATE_MAINFEST, updateManifestDataModel);
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(JAR_MANIPULATION_TYPE)) {
			return new Integer(JAR_MANIPULATION_ADD);
		}
		return super.getDefaultProperty(propertyName);
	}

	public IProject getEARProject() {
		return ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
	}

	/**
	 * @return
	 */
	public IDataModel getUpdateManifestDataModel() {
		return model.getNestedModel(NESTED_MODEL_UPDATE_MAINFEST);
	}

	public IProject getReferencedProject() {
		return ProjectUtilities.getProject(getStringProperty(REFERENCED_PROJECT_NAME));
	}

	public IProject getOppositeProject() {
		return ProjectUtilities.getProject(getStringProperty(OPPOSITE_PROJECT_NAME));
	}

	public IDataModelOperation getDefaultOperation() {
		return new JARDependencyOperation(model);
	}	
}
