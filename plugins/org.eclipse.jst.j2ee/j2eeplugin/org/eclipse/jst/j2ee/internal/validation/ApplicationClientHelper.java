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
package org.eclipse.jst.j2ee.internal.validation;



import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.model.internal.validation.ApplicationClientValidator;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;


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
		registerModel(ApplicationClientValidator.APPCLIENT_MODEL_NAME, "loadApplicationClientFile"); //$NON-NLS-1$
	}

	/**
	 * Given a resource, return its non-eclipse-specific location. If this resource, or type of
	 * resource, isn't handled by this helper, return null.
	 */
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

		ComponentHandle handle = getComponentHandle();
		IVirtualComponent comp = ComponentCore.createComponent(handle.getProject(), handle.getName());
		ArtifactEdit edit = ComponentUtilities.getArtifactEditForRead(comp);
		
		try {
			Archive archive = ((AppClientArtifactEdit) edit).asArchive(false);
			return archive;
		} catch (OpenFailureException e1) {
			Logger.getLogger().log(e1);
		}finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		return null;
	}	
	
	public void closeApplicationClientFile() {
		if (appClientFile != null)
			appClientFile.close();
	}
}