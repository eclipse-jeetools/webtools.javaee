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
package org.eclipse.jst.j2ee.internal.archive.operations;


import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.project.IJ2EEProjectTypes;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;


/**
 * This class defines the rules for importing an individual nested JAR in an EAR project
 */
public class ImportOption implements IJ2EEProjectTypes {
	/**
	 * Project info for the individual module/JAR, or null if the file should be placed in the
	 * application
	 */
	protected WTPOperationDataModel model;

	/**
	 * Valid only if the project info is not null
	 * 
	 * @see IJ2EEProjectTypes
	 */
	protected int projectType = IJ2EEProjectTypes.DEFAULT;

	/**
	 * @see IJ2EEProjectTypes#MODULE
	 * @see IJ2EEProjectTypes#UTIL
	 */
	protected int archiveType = IJ2EEProjectTypes.MODULE;

	/**
	 * Constructor for ImportOption.
	 */
	public ImportOption(WTPOperationDataModel model) {
		super();
		this.model = model;
	}

	public ImportOption(WTPOperationDataModel model, int type) {
		this(model);
		projectType = type;
	}

	public WTPOperationDataModel getModel() {
		return model;
	}

	public int getProjectType() {
		return projectType;
	}

	public String getProjectName() {
		return model.getStringProperty(J2EEProjectCreationDataModel.PROJECT_NAME);
	}

	/**
	 * @return Returns the archiveType.
	 */
	public int getArchiveType() {
		return archiveType;
	}

	/**
	 * @param archiveType
	 *            The archiveType to set.
	 */
	public void setArchiveType(int archiveType) {
		this.archiveType = archiveType;
	}

	/**
	 * @param projectType
	 *            The projectType to set.
	 */
	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

}