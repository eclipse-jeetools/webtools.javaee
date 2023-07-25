/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.validation.J2EEValidationHelper;
import org.eclipse.jst.j2ee.jca.modulecore.util.ConnectorArtifactEdit;
import org.eclipse.jst.j2ee.model.internal.validation.ConnectorMessageConstants;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.validation.internal.operations.WorkbenchReporter;


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
		registerModel(ConnectorMessageConstants.CONNECTOR_MODEL_NAME, "loadRarFile"); //$NON-NLS-1$
	}

	/**
	 * Get the Rar file for validation
	 */
	public EObject loadRarFile() {

		IVirtualComponent comp = ComponentCore.createComponent(getProject());
		ArtifactEdit edit = ComponentUtilities.getArtifactEditForRead(comp);
		
		try {
			rarFile = (RARFile)((ConnectorArtifactEdit) edit).asArchive(false);
			return rarFile;
		} catch (OpenFailureException e1) {
			Logger.getLogger().log(e1);
		}finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		return null;
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
		return "rar.xml"; //$NON-NLS-1$
	}
	@Override
	public void cleanup(WorkbenchReporter reporter) {
		if(rarFile != null){
			rarFile.close();
			rarFile = null;
		}
		
		super.cleanup(reporter);
	}
	
}
