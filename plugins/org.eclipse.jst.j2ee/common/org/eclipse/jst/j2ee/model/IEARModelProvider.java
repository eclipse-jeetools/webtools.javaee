package org.eclipse.jst.j2ee.model;

import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * In progress...    Simple interface that serves to unify the various model access api's
 *
 */
public interface IEARModelProvider extends IModelProvider {

	/**
	 * This provides a way to get module URIs from the appropriate model context
	 */
	public String getModuleURI(IVirtualComponent moduleComp);
}
