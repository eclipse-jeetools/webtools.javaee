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
package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.wst.common.modulecore.ArtifactEdit;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 */
public abstract class EnterpriseArtifactEdit extends ArtifactEdit {
	/**
	 * @param model
	 */
	public EnterpriseArtifactEdit(ArtifactEditModel model) {
		super(model);
	}
	public abstract int getJ2EEVersion();
	public abstract Resource getDeploymentDescriptorResource();
	public EObject getDeploymentDescriptorRoot() {
		Resource res = getDeploymentDescriptorResource();
		return (EObject)res.getContents().get(0);
	}
}
