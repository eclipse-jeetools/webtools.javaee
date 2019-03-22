/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public abstract class J2EEFacetInstallDataModelProvider extends FacetInstallDataModelProvider  implements IJ2EEFacetInstallDataModelProperties{

	@Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(RUNTIME_TARGET_ID);
		names.add(FACET_RUNTIME);
		// added for jee projects that make deployment descriptors optional
		names.add(GENERATE_DD);
		return names;
	}

	protected final int getJ2EEVersion() {
		return convertFacetVersionToJ2EEVersion((IProjectFacetVersion) getProperty(FACET_VERSION));
	}

	protected abstract int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version);

	protected IStatus validateFolderName(String folderName) {
		// the directory is not required, but if the name is entered ensure that it 
		// contains only valid characters.
		if (folderName == null || folderName.length() == 0) {
			return OK_STATUS;
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath path = new Path(folderName);
		for (int i = 0, max = path.segmentCount(); i < max; i++) {
			IStatus status = workspace.validateName(path.segment(i), IResource.FOLDER);
			if (! status.isOK())
				return status;
		}

		// all of the potential segments of the folder have been verified
		return OK_STATUS;
	}

}
