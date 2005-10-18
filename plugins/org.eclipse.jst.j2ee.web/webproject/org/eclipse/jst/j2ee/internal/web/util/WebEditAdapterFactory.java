/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;


/**
 * <p>
 * Adapts {@see org.eclipse.wst.common.modulecore.ArtifactEditModel}s to
 * {@see org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit}&nbsp; facades.
 * </p>
 */
public class WebEditAdapterFactory implements IAdapterFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == WebArtifactEdit.ADAPTER_TYPE || adapterType == ArtifactEdit.ADAPTER_TYPE) {
			ArtifactEditModel editModel = (ArtifactEditModel) adaptableObject;
			if (J2EEProjectUtilities.isDynamicWebProject(editModel.getProject()))
				return new WebArtifactEdit((ArtifactEditModel) adaptableObject);
		}
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[]{ArtifactEdit.class, WebArtifactEdit.class};
	}
}