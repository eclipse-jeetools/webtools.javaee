/*
 * Created on Feb 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EMFModelManagerFactory {
	public static EMFModelManager createEMFModelManager(IProject project, EMFRootObjectProvider provider) {
		boolean flexible =  (ModuleCoreNature.getModuleCoreNature(project) != null);
		EMFModelManager modelManager = null;
		if (flexible)
			modelManager = new FlexibleEMFModelManager(project,provider);
		else
			modelManager = new NonFlexibleEMFModelManager(project,provider);
		return modelManager;
	}
}
