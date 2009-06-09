/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.componentcore;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
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
			ArchiveManifest manifest = J2EEProjectUtilities.readManifest(this);
			if (manifest != null) {
				manifestClasspath = manifest.getClassPathTokenized();
			}
			if (manifestClasspath == null) {
				manifestClasspath = new String[0];
			}
		}
		return manifestClasspath;
	}

	public void setLinkedToEAR(boolean linkedToEAR) {
		this.linkedToEAR = linkedToEAR;
	}

	public boolean isLinkedToEAR() {
		return linkedToEAR;
	}
	
	private IPath deploymentPath;
	
	public IPath getDeploymentPath() {
		return deploymentPath;
	}
	public void setDeploymentPath(IPath path) {
		this.deploymentPath = path;
	}
	
}
