package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jst.j2ee.ejb.internal.util.CMPKeySynchronizationAdapter;

public class CleanBuildCacheCleanerListener implements IResourceChangeListener {

	public static CleanBuildCacheCleanerListener INSTANCE = new CleanBuildCacheCleanerListener();

	private CleanBuildCacheCleanerListener() {
	}

	public void resourceChanged(IResourceChangeEvent event) {
		IResourceDelta delta = event.getDelta();
		if(delta.getFlags() != IResourceDelta.MARKERS && delta.getAffectedChildren().length > 0){
			CMPKeySynchronizationAdapter.flushUnresolvedKeyAttributesOnCleanBuild();
		}
	}
}
