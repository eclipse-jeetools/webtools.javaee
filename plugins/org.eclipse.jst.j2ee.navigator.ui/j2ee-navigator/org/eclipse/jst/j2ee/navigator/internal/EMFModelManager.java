/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.resources.IProject;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class EMFModelManager {
	private final IProject project;
	private final EMFRootObjectProvider provider;
	public abstract Object[] getModels();
	public final IProject getProject() {
		return project;
	}
	public final EMFRootObjectProvider getEMFRootObjectProvider(){
		return provider;
	}
	public EMFModelManager(IProject aProject, EMFRootObjectProvider aProvider) {
		project = aProject;
		provider = aProvider;
	}
	
	/**
	 * @param affectedProject
	 */
	protected void notifyListeners(IProject affectedProject) {
		provider.notifyListeners(affectedProject);
	}
	/**
	 * 
	 */
	public abstract void dispose();
	
}
