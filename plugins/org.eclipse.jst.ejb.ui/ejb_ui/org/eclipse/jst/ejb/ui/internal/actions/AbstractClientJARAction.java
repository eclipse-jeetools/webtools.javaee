/*
 * Created on Jan 21, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.ejb.ui.internal.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.actions.BaseAction;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientJARCreationConstants;


/**
 * @author schacher
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class AbstractClientJARAction extends BaseAction implements ClientJARCreationConstants {

	
	protected IProject getProject() {
		IProject project = null;
		Object element = selection.getFirstElement();
		
		if (element instanceof EJBJar) 
			project = ProjectUtilities.getProject((EJBJar) element);
		else if (element instanceof IProject)
			project = (IProject) element;
		else if (element instanceof IAdaptable)
			project = (IProject) ((IAdaptable)element).getAdapter(IProject.class);
		
		return project;
	}
	

}
