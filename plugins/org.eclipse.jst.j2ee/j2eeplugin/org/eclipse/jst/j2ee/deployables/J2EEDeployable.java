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
package org.eclipse.jst.j2ee.deployables;


import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.server.j2ee.IJ2EEModule;
import org.eclipse.wst.server.core.util.ProjectModule;

/**
 * J2EE deployable superclass.
 */
public abstract class J2EEDeployable extends ProjectModule implements IJ2EEModule {
	private String factoryId;
	private J2EENature nature;

	/**
	 * Constructor for J2EEDeployable.
	 * 
	 * @param project
	 */
	public J2EEDeployable(J2EENature aNature, String aFactoryId) {
		super(aNature.getProject());
		factoryId = aFactoryId;
		setNature(aNature);
	}

	public String getJ2EESpecificationVersion() {
		return getNature().getJ2EEVersionText();
	}

	/*
	 * @see IJ2EEModule#getLocation()
	 */
	public IPath getLocation() {
		if (getNature() instanceof J2EEModuleNature)
			return ((J2EEModuleNature) getNature()).computeModuleAbsoluteLocation();
		return null;
	}

	/*
	 * @see IModule#getFactoryId()
	 */
	public String getFactoryId() {
		return factoryId;
	}

	/**
	 * Gets the nature.
	 * 
	 * @return Returns a J2EENature
	 */
	public J2EENature getNature() {
		return nature;
	}

	/**
	 * Sets the nature.
	 * 
	 * @param nature
	 *            The nature to set
	 */
	protected void setNature(J2EENature nature) {
		this.nature = nature;
		nature.setModule(this);
	}

	/**
	 * @see com.ibm.etools.server.j2ee.IJ2EEModule#isBinary()
	 */
	public boolean isBinary() {
		if (nature instanceof J2EEModuleNature)
			return nature != null && ((J2EEModuleNature) nature).isBinaryProject();
		return false;
	}
}