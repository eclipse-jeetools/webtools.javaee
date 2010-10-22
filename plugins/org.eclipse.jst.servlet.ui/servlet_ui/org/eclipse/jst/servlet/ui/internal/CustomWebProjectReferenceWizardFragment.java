/******************************************************************************
 * Copyright (c) 2010, 2009 Red Hat and Others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - Ongoing improvements
 *    Konstantin Komissarchik - misc. UI cleanup
 ******************************************************************************/

package org.eclipse.jst.servlet.ui.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.ProjectReferenceWizardFragment;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;

public class CustomWebProjectReferenceWizardFragment extends ProjectReferenceWizardFragment {
	
	@Override
	public void performFinish(IProgressMonitor monitor) throws CoreException {
		for (int i = 0; i < selected.length; i++) {
			IProject proj = selected[i];
			if(JavaEEProjectUtilities.getJ2EEProjectType(proj).equals("")) //$NON-NLS-1$
				J2EEProjectUtilities.createFlexJavaProjectForProjectOperation(proj).execute(monitor, null);
		}
		getTaskModel().putObject(IReferenceWizardConstants.DEFAULT_LIBRARY_LOCATION, "/" + J2EEConstants.WEB_INF_LIB); //$NON-NLS-1$
		super.performFinish(monitor);
	}
	
}
