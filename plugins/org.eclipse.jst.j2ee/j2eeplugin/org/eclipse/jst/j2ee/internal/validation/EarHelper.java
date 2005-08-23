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
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.model.internal.validation.EarValidator;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;


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

		ComponentHandle handle = getComponentHandle();
		IVirtualComponent comp = ComponentCore.createComponent(handle.getProject(), handle.getName());
		ArtifactEdit edit = ComponentUtilities.getArtifactEditForRead(comp);
		
		try {
			Archive archive = ((EARArtifactEdit) edit).asArchive(false);
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
	
	public void closeEARFile() {
		if (earFile != null) {
			earFile.close();
			earFile = null;
		}
	}
}