/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;


import java.util.logging.Level;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.internal.plugin.JavaEMFNature;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.validation.internal.operations.AWorkbenchHelper;
import org.eclipse.wst.validation.internal.plugin.ValidationPlugin;


/**
 * Abstract base class for registration of symbolic model names, and also provides the mechanism for
 * registering the load model method which loads a specific type of MOF model, as identified by the
 * symbolic model name.
 */
public abstract class AWorkbenchMOFHelper extends AWorkbenchHelper {
	public IResource getResource(Object obj) {
		IResource res = null;
		if ((obj != null) && (obj instanceof EObject)) {
			JavaEMFNature nature = getMOFNature(getProject());
			if (nature != null) {
				EObject ro = (EObject) obj;
				res = WorkbenchResourceHelper.getFile(ro.eResource());
			}
		}

		if ((res == null) || (!res.exists())) {
			return super.getResource(obj);
		}

		return res;
	}

	public JavaEMFNature getMOFNature(IProject project) {
		if (project == null) {
			return null;
		}

		IProjectDescription desc = null;
		try {
			desc = project.getDescription();
		} catch (CoreException exc) {
			Logger logger = ValidationPlugin.getPlugin().getMsgLogger();
			if (logger.isLoggingLevel(Level.FINE)) {
				LogEntry entry = ValidationPlugin.getLogEntry();
				entry.setSourceID("AWorkbenchHelper.getMOFNature(IProject)"); //$NON-NLS-1$
				entry.setTargetException(exc);
				logger.write(Level.FINE, entry);
			}
			return null;
		}

		String[] natureIds = desc.getNatureIds();
		for (int i = 0; i < natureIds.length; i++) {
			try {
				IProjectNature nature = project.getNature(natureIds[i]);
				if (nature instanceof JavaEMFNature) {
					return (JavaEMFNature) nature;
				}
			} catch (CoreException exc) {
				// log and attempt next nature
				Logger logger = ValidationPlugin.getPlugin().getMsgLogger();
				if (logger.isLoggingLevel(Level.FINE)) {
					LogEntry entry = ValidationPlugin.getLogEntry();
					entry.setSourceID("AWorkbenchHelper.getMOFNature(IProject)"); //$NON-NLS-1$
					entry.setTargetException(exc);
					logger.write(Level.FINE, entry);
				}
			}
		}
		return null;
	}
}