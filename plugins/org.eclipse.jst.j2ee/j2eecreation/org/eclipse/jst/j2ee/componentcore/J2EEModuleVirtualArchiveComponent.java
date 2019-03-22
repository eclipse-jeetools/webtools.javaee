/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.componentcore;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.util.ManifestUtilities;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class J2EEModuleVirtualArchiveComponent extends VirtualArchiveComponent {

	protected static final IVirtualReference[] NO_REFERENCES = new VirtualReference[0];

	private boolean linkedToEAR = true;
	
	protected String[] manifestClasspath;

	public J2EEModuleVirtualArchiveComponent(IProject aComponentProject, String archiveLocation, IPath aRuntimePath) {
		super(aComponentProject, archiveLocation, aRuntimePath);
	}

	@Override
	public IVirtualReference[] getReferences() {
		List dynamicReferences = J2EEModuleVirtualComponent.getManifestReferences(this, null);
		if (null == dynamicReferences) {
			return NO_REFERENCES;
		}
		return (IVirtualReference[]) dynamicReferences.toArray(new IVirtualReference[dynamicReferences.size()]);
	}

	public String[] getManifestClasspath() {
		if (null == manifestClasspath) {
			manifestClasspath = ManifestUtilities.getManifestClasspath(this , new Path(J2EEConstants.MANIFEST_URI));
		}
		return manifestClasspath;
	}

	public void setLinkedToEAR(boolean linkedToEAR) {
		this.linkedToEAR = linkedToEAR;
	}

	public boolean isLinkedToEAR() {
		return linkedToEAR;
	}
	
	/*
	* The following APIs were temporary to fix some bugs
	* which have now been fixed by a more thorough refactor (flat component model)
	*/
	@Deprecated
	private IPath deploymentPath;
	
	@Deprecated
	public IPath getDeploymentPath() {
		return deploymentPath;
	}
	
	@Deprecated
	public void setDeploymentPath(IPath path) {
		this.deploymentPath = path;
	}
}
