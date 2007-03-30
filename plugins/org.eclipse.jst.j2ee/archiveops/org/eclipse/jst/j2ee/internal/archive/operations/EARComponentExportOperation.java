/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentExportOperation extends J2EEArtifactExportOperation {

	public EARComponentExportOperation(IDataModel model) {
		super(model);
	}

	protected void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {
		IProgressMonitor subMonitor = new SubProgressMonitor(progressMonitor, EXPORT_WORK);
		EARArtifactEdit artifactEdit = null;
		try {
			artifactEdit = (EARArtifactEdit) ComponentUtilities.getArtifactEditForRead(getComponent());
			EARFile archive = (EARFile) artifactEdit.asArchive(isExportSource());
			String destination = getDestinationPath().toOSString();
			archive.setURI(destination);
			setModuleFile(archive);
			ComponentLoadStrategyImpl ls = (ComponentLoadStrategyImpl)archive.getLoadStrategy();
			ls.setProgressMonitor(subMonitor);
			archive.saveAsNoReopen(destination);
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			throw new SaveFailureException(AppClientArchiveOpsResourceHandler.ARCHIVE_OPERATION_OpeningArchive, e);
		} finally {
			if (null != artifactEdit) {
				artifactEdit.dispose();
			}
			subMonitor.done();
		}
	}

	protected void runNecessaryBuilders(IVirtualComponent component, IProgressMonitor monitor) throws CoreException {
		try {
			IVirtualReference[] refs = component.getReferences();
			int work = (JAVA_BUILDER_WORK + LIB_BUILDER_WORK) / (refs.length + 1); 
			super.runNecessaryBuilders(component, new SubProgressMonitor(monitor, work));
			for (int i = 0; i < refs.length; i++) {
				IVirtualComponent refComp = refs[i].getReferencedComponent();
				super.runNecessaryBuilders(refComp, new SubProgressMonitor(monitor, work));
			} 
		} finally {
			monitor.done();
		}
	}


	protected String archiveString() {
		return "EAR";
	}



}
