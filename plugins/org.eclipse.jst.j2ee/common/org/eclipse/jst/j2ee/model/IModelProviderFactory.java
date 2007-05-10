package org.eclipse.jst.j2ee.model;

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * Factory interface for creating IModelProvider instances.  
 * These factories are registered for a specific model domain/version
 *
 */
public interface IModelProviderFactory {
	
	/**
	 * @param aProject {@link IProject}
	 * @return {@link IModelProvider}
	 */
	IModelProvider create (IProject aProject);
	/**
	 * @param aComponent {@link IVirtualComponent}
	 * @return {@link IModelProvider}
	 */
	IModelProvider create (IVirtualComponent aComponent);

}
