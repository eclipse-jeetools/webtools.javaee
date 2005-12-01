/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.applicationclient.internal.modulecore.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;

public class AppClientEditAdapterFactory implements IAdapterFactory {

	public AppClientEditAdapterFactory() {
		super();
	}

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == AppClientArtifactEdit.ADAPTER_TYPE || adapterType == ArtifactEdit.ADAPTER_TYPE) {
			ArtifactEditModel editModel = (ArtifactEditModel) adaptableObject;
			if (J2EEProjectUtilities.isApplicationClientProject(editModel.getProject()))
				return new AppClientArtifactEdit((ArtifactEditModel) adaptableObject);
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[]{ArtifactEdit.class, AppClientArtifactEdit.class};
	}

}
