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
package org.eclipse.jst.j2ee.ejb.internal.modulecore.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;

public class EJBEditAdapterFactory implements IAdapterFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == EJBArtifactEdit.ADAPTER_TYPE || adapterType == ArtifactEdit.ADAPTER_TYPE) {
			ArtifactEditModel editModel = (ArtifactEditModel) adaptableObject;
			if (J2EEProjectUtilities.isEJBProject(editModel.getProject()))
				return new EJBArtifactEdit((ArtifactEditModel) adaptableObject);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[]{ArtifactEdit.class, EJBArtifactEdit.class};
	}

}
