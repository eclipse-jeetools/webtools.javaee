/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class AppClientComponentLoadStrategyImpl extends ComponentLoadStrategyImpl {

	public AppClientComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	@Override
	protected synchronized ArtifactEdit getArtifactEditForRead() {
		if(artifactEdit == null){
			artifactEdit = AppClientArtifactEdit.getAppClientArtifactEditForRead(vComponent);
		}
		return artifactEdit;
	}

}
