/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jst.j2ee.ejb.internal.util.CMPKeySynchronizationAdapter;

public class CleanBuildCacheCleanerListener implements IResourceChangeListener {

	public static CleanBuildCacheCleanerListener INSTANCE = new CleanBuildCacheCleanerListener();

	private CleanBuildCacheCleanerListener() {
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		if(delta.getFlags() != IResourceDelta.MARKERS && delta.getAffectedChildren().length > 0){
			CMPKeySynchronizationAdapter.flushUnresolvedKeyAttributesOnCleanBuild();
		}
	}
}
