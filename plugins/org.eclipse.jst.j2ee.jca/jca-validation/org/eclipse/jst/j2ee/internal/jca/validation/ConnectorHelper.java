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
/*
 * Created on Jan 22, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.jca.validation;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.jst.j2ee.internal.validation.J2EEValidationHelper;
import org.eclipse.jst.j2ee.model.internal.validation.ConnectorValidator;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class ConnectorHelper extends J2EEValidationHelper {
	protected RARFile rarFile;

	/**
	 *  
	 */
	public ConnectorHelper() {
		super();
		registerModel(ConnectorValidator.CONNECTOR_MODEL_NAME, "loadRarFile"); //$NON-NLS-1$
	}

	/**
	 * Get the Rar file for validation
	 */
	public EObject loadRarFile() {
		RARFile sRarFile = null;
		ConnectorNatureRuntime connectorNature = null;
		//	openFilesCache = new ArrayList();
		IProject proj = getProject();
		if (proj != null) {
			connectorNature = ConnectorNatureRuntime.getRuntime(getProject());
			if (connectorNature != null) {
				try {
					sRarFile = (RARFile) connectorNature.asArchive(true);
				} catch (Exception e) {
					return null;
				}
			}
		}
		return sRarFile;
	}

	/**
	 * Given a resource, return its non-eclipse-specific location. If this resource, or type of
	 * resource, isn't handled by this helper, return null.
	 */
	public String getPortableName(IResource resource) {
		if (!(resource instanceof IFile)) {
			return null;
		}
		return "rar.xml"; //$NON-NLS-1$
	}
}