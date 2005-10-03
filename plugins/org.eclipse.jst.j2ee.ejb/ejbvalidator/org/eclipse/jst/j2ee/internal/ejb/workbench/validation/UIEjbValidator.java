/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.workbench.validation;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.model.internal.validation.EJBValidator;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.validation.internal.operations.IWorkbenchContext;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;



public class UIEjbValidator extends EJBValidator {
	
	/**
	 * UIEjbValidator constructor comment.
	 */
	public UIEjbValidator() {
		super();
	}
	
	public void validate(IValidationContext inHelper, IReporter inReporter) throws org.eclipse.wst.validation.internal.core.ValidationException {

		IProject proj = ((IWorkbenchContext) inHelper).getProject();
		IVirtualComponent wbModule = ComponentCore.createComponent(proj);
            if( wbModule.getComponentTypeId() != null && wbModule.getComponentTypeId().equals(IModuleConstants.JST_EJB_MODULE)) {
            	
			
			IVirtualFile ejbDD = wbModule.getRootFolder().getFile(J2EEConstants.EJBJAR_DD_URI);
			if( ejbDD.exists()){
				ArtifactEdit edit = null;
				try {
					edit = EJBArtifactEdit.getArtifactEditForRead(wbModule);
					super.validate(inHelper, inReporter);
				} finally {
					if (edit != null)
						edit.dispose();
				}
			}
		}
	}		

}