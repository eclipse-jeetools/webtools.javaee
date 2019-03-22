/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;



import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.model.internal.validation.ApplicationClientMessageConstants;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.validation.internal.operations.WorkbenchReporter;


/**
 * Insert the type's description here. Creation date: (4/9/2001 11:22:53 AM)
 * 
 * @author: Administrator
 */
public class ApplicationClientHelper extends J2EEValidationHelper {

	protected ApplicationClientFile appClientFile;

	/**
	 * ApplicationClientHelper constructor comment.
	 */
	public ApplicationClientHelper() {
		super();
		registerModel(ApplicationClientMessageConstants.APPCLIENT_MODEL_NAME, "loadApplicationClientFile"); //$NON-NLS-1$
	}

	/**
	 * Given a resource, return its non-eclipse-specific location. If this resource, or type of
	 * resource, isn't handled by this helper, return null.
	 */
	@Override
	public String getPortableName(IResource resource) {
		if (!(resource instanceof IFile)) {
			return null;
		}

		return "application-client.xml"; //$NON-NLS-1$
	}

	/**
	 * Get the AppClient file for validation
	 */

	public EObject loadApplicationClientFile() {

		IVirtualComponent comp = ComponentCore.createComponent(getProject());
		ArtifactEdit edit = ComponentUtilities.getArtifactEditForRead(comp);
		
		try {
			appClientFile = (ApplicationClientFile)((AppClientArtifactEdit) edit).asArchive(false);
			return appClientFile;
		} catch (OpenFailureException e1) {
			J2EEPlugin.logError(e1);
		}finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		return null;
	}	
	
	@Override
	public void cleanup(WorkbenchReporter reporter) {
		closeApplicationClientFile();
		super.cleanup(reporter);
	}
	
	public void closeApplicationClientFile() {
		if (appClientFile != null)
			appClientFile.close();
	}
}
