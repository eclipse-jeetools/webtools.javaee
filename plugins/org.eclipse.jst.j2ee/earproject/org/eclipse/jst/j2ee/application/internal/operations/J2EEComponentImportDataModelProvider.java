/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Set;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;

/**
 * This dataModel is a common super class used to import J2EE Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public abstract class J2EEComponentImportDataModelProvider extends J2EEArtifactImportDataModelProvider implements IJ2EEModuleImportDataModelProperties {


	@Override
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		return propertyNames;
	}

	protected int getModuleSpecVersion() {
		ArchiveWrapper wrapper = getArchiveWrapper();
		return wrapper.getJavaEEQuickPeek().getVersion();
	}

	/**
	 * Updates the Java Facet Version so it is compliant with the Java EE Module version 
	 */
	@Override
	protected void updateJavaFacetVersion() {
		super.updateJavaFacetVersion();
	}
}
