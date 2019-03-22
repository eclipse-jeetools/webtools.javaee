/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @deprecated use org.eclipse.jst.j2ee.internal.archive.operations.JavaEEComponentExportOperation instead
 */
public class EARComponentExportOperation extends J2EEArtifactExportOperation {

	
	public EARComponentExportOperation(IDataModel model) {
		super(model);
	}

	@Override
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

}
