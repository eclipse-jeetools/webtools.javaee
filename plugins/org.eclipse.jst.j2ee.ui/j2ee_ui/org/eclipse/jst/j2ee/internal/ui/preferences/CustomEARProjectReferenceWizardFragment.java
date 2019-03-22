/******************************************************************************
 * Copyright (c) 2009 IBM
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Aidyl Kareh - initial implementation
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualReference;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.ProjectReferenceWizardFragment;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;

public class CustomEARProjectReferenceWizardFragment extends ProjectReferenceWizardFragment {
	
	public CustomEARProjectReferenceWizardFragment() {
		super();
	}

	@Override
	public void performFinish(IProgressMonitor monitor) throws CoreException {
		super.performFinish(monitor);
		VirtualReference[] refs = (VirtualReference []) getTaskModel().getObject(IReferenceWizardConstants.FINAL_REFERENCE);
		for (int i = 0; i < refs.length; i++) {
			IProject proj = refs[i].getReferencedComponent().getProject();
			if (JavaEEProjectUtilities.isApplicationClientProject(proj) || 
					JavaEEProjectUtilities.isEJBProject(proj) || 
					JavaEEProjectUtilities.isDynamicWebProject(proj) || 
					JavaEEProjectUtilities.isJCAProject(proj)) {
				refs[i].setRuntimePath(new Path("/").makeAbsolute()); //$NON-NLS-1$
			}
		}
	}	
}
