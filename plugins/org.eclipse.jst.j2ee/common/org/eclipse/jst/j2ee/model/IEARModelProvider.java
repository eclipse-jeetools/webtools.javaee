package org.eclipse.jst.j2ee.model;


import org.eclipse.core.resources.IProject;
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

	/**
	 * This method will return the context root in this application for the passed in web project.
	 * 
	 * @param webProject
	 * @return contextRoot String
	 */
	public String getWebContextRoot(IProject webProject);

}
