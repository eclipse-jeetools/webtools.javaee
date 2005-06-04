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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;


/**
 * Insert the type's description here. Creation date: (7/17/2001 11:57:40 AM)
 * 
 * @author: Administrator
 */
public class EJBPostImportOperation extends WTPOperation {
	private boolean isBatch = false;
	private EJBNatureRuntime ejbNature;
	private EJBEditModel ejbEditModel;

	/**
	 * EJBPostImportOperation constructor comment.
	 */
	public EJBPostImportOperation(EJBNatureRuntime aNatureRuntime) {
		setEjbNature(aNatureRuntime);
	}

	/**
	 * Performs the steps that are to be treated as a single logical workspace change.
	 * <p>
	 * Subclasses must implement this method.
	 * </p>
	 * 
	 * @param monitor
	 *            the progress monitor to use to display progress and field user requests to cancel
	 * @exception CoreException
	 *                if the operation fails due to a CoreException
	 * @exception InvocationTargetException
	 *                if the operation fails due to an exception other than CoreException
	 * @exception InterruptedException
	 *                if the operation detects a request to cancel, using
	 *                <code>IProgressMonitor.isCanceled()</code>, it should exit by throwing
	 *                <code>InterruptedException</code>
	 */
	protected void execute(org.eclipse.core.runtime.IProgressMonitor monitor) throws java.lang.reflect.InvocationTargetException, InterruptedException, org.eclipse.core.runtime.CoreException {
		try {
			splitSchemaResource(monitor);
			saveEditModels();
		} finally {
			releaseEditModels();
		}
	}

	/**
	 * Insert the method's description here. Creation date: (7/17/2001 12:03:47 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBEditModel
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel getEjbEditModel() {
		if (ejbEditModel == null)
			ejbEditModel = getEjbNature().getEJBEditModelForWrite(this);
		return ejbEditModel;
	}

	/**
	 * Insert the method's description here. Creation date: (7/17/2001 12:03:47 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBNatureRuntime
	 */
	protected org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime getEjbNature() {
		return ejbNature;
	}

	/**
	 * Insert the method's description here. Creation date: (9/25/2001 6:19:16 PM)
	 * 
	 * @return boolean
	 */
	public boolean isBatch() {
		return isBatch;
	}

	protected void releaseEditModels() {
		if (ejbEditModel != null)
			ejbEditModel.releaseAccess(this);
	}

	protected void saveEditModels() {
		if (ejbEditModel != null)
			ejbEditModel.saveIfNecessary(this);
	}

	/**
	 * Insert the method's description here. Creation date: (7/17/2001 12:03:47 PM)
	 * 
	 * @param newEjbNature
	 *            org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBNatureRuntime
	 */
	protected void setEjbNature(org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime newEjbNature) {
		ejbNature = newEjbNature;
	}

	/**
	 * Insert the method's description here. Creation date: (9/25/2001 6:19:16 PM)
	 * 
	 * @param newIsBatch
	 *            boolean
	 */
	public void setIsBatch(boolean newIsBatch) {
		isBatch = newIsBatch;
	}

	/**
	 * Split the single Schema resource into multiple schema resources.
	 */
	protected void splitSchemaResource(IProgressMonitor pm) throws java.lang.reflect.InvocationTargetException, InterruptedException {
		IEJBArchiveTransformationOperation op = org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin.getPlugin().getExtendedArchiveOperation();
		op.setPerformSplit(true);
		op.setBatch(isBatch());
		op.setProject(getEjbNature().getProject());
		op.setIsImport(true);
		op.run(pm);
	}
}