package org.eclipse.jst.j2ee.model;

import org.eclipse.core.runtime.IPath;

/**
 * In progress...    Simple interface that serves to unify the various model access api's
 *
 */
public interface IModelProvider {
	
	/**
	 * This returns the designated "default" model for the given context
	 * @return Object
	 */
	Object getModelObject();
	/**
	 * This returns the model specifically for a given path within the model context, path can also be used
	 * to designate the source of the model (xml, java annotations, etc..)
	 * @param modelPath
	 * @return Object
	 */
	Object getModelObject(IPath modelPath);
	/**
	 * The modify method should be used during a write operation on the model.  
	 * The model will be properly accessed, saved and released
	 * @param runnable {@link Runnable} - User specified code that alters the model. 
	 * @param modelPath {@link IPath} - Optional path to specify which model instance will be modified
	 */
	void modify(Runnable runnable, IPath modelPath);
	

}
