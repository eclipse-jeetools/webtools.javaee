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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.model.internal.validation.ApplicationClientValidator;


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
	 * Get the WAR file for validation
	 */
	public EObject loadApplicationClientFile() {
		ApplicationClientNatureRuntime appClientNature = null;
		//	openFilesCache = new ArrayList();
		IProject proj = getProject();
		if (proj != null) {
			appClientNature = ApplicationClientNatureRuntime.getRuntime(getProject());
			if (appClientNature != null) {
				try {
					appClientFile = (ApplicationClientFile) appClientNature.asArchive(true);
					//						openFilesCache.add(appClientFile);
				} catch (Exception e) {
					// nothing to do, we're gonna return null
				}
			}

		}
		return appClientFile;
	}

	public void closeApplicationClientFile() {
		if (appClientFile != null)
			appClientFile.close();
	}
}