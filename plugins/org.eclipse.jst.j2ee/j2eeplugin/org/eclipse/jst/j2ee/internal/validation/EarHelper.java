/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;



import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.impl.ApplicationImpl;
import org.eclipse.jst.j2ee.commonarchivecore.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.model.validation.EarValidator;


public class EarHelper extends J2EEValidationHelper {

	protected EARFile earFile;

	/**
	 * WarHelper constructor comment.
	 */
	public EarHelper() {
		super();

		registerModel(EarValidator.EAR_MODEL_NAME, "loadEarFile"); //$NON-NLS-1$
	}

	public String getApplicationXMLFile() {

		return ArchiveConstants.APPL_ID;
	}

	/**
	 * Given a resource, return its non-eclipse-specific location. If this resource, or type of
	 * resource, isn't handled by this helper, return null.
	 */
	public String getPortableName(IResource resource) {
		if (!(resource instanceof IFile)) {
			return null;
		}

		return "application.xml"; //$NON-NLS-1$
	}

	public String getTargetObjectName(Object obj) {
		super.getTargetObjectName(obj);

		if (obj != null && obj instanceof EARFile) {
			return getApplicationXMLFile();
		}
		if (obj != null && obj instanceof ApplicationImpl) {
			return "application.xml"; //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * Get the WAR file for validation
	 */
	public EObject loadEarFile() {
		EARNatureRuntime earNature = null;
		if (earFile != null && earFile.isOpen())
			return earFile;
		//	openFilesCache = new java.util.ArrayList();
		IProject proj = getProject();
		if (proj != null) {

			if (EARNatureRuntime.hasRuntime(proj)) {
				earNature = EARNatureRuntime.getRuntime(getProject());
				if (earNature != null) {
					try {
						earFile = earNature.asEARFile(true, false);
						//						openFilesCache.add(earFile);
					} catch (Exception e) {
						// nothing to do, we're gonna return null
					}
				}
			}
		}
		return earFile;
	}

	public void closeEARFile() {
		if (earFile != null) {
			earFile.close();
			earFile = null;
		}
	}
}