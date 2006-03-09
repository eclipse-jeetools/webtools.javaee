/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.workbench.validation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.internal.validation.EJBValidator;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.validation.internal.operations.IWorkbenchContext;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidatorJob;



public class UIEjbValidator extends EJBValidator {
	
	/**
	 * UIEjbValidator constructor comment.
	 */
	public UIEjbValidator() {
		super();
	}
	
	public IStatus validateInJob(IValidationContext inHelper, IReporter inReporter) throws
	    org.eclipse.wst.validation.internal.core.ValidationException {
		

		IProject proj = ((IWorkbenchContext) inHelper).getProject();
		if (J2EEProjectUtilities.isEJBProject(proj)) {
				EJBArtifactEdit edit = null;
				try {
					edit = EJBArtifactEdit.getEJBArtifactEditForRead(proj);
					if(edit != null && edit.getDeploymentDescriptorResource() != null)
						return super.validateInJob(inHelper, inReporter);
				} finally {
					if (edit != null)
						edit.dispose();
				}
		}
		return IValidatorJob.OK_STATUS;
		
	}
	
	public ISchedulingRule getSchedulingRule(IValidationContext helper) {
		
		IProject project = ((IWorkbenchContext) helper).getProject();
		IVirtualComponent comp = ComponentCore.createComponent( project );
		if( comp != null ){
			IVirtualFile vf = comp.getRootFolder().getFile(new Path(J2EEConstants.EJBJAR_DD_URI));
			IPath path = null;
			if( vf!= null ){
				IFile ejbJarFile = vf.getUnderlyingFile();
				path = ejbJarFile.getRawLocation();
			}
			if( path != null ){
				EjbValidatorSchedulingRule rule = new EjbValidatorSchedulingRule(path);
				return rule;
			}
		}
		return null;
	}	
}		


