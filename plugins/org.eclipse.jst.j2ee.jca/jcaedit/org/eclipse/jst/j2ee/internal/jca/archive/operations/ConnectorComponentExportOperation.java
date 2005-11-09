/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactExportOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPluginResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ConnectorComponentExportOperation extends J2EEArtifactExportOperation {

	public ConnectorComponentExportOperation() {
		super();
	}

	public ConnectorComponentExportOperation(IDataModel model) {
		super(model);
	}

	protected void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {
		try {
			CommonarchiveFactory caf = ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
			ConnectorComponentLoadStrategyImpl ls = new ConnectorComponentLoadStrategyImpl(getComponent());
			ls.setExportSource(isExportSource());
			setModuleFile(caf.openRARFile(ls, getDestinationPath().toOSString()));
			getModuleFile().saveAsNoReopen(getDestinationPath().toOSString());
		} catch (Exception e) {
			throw new SaveFailureException(J2EEPluginResourceHandler.Error_opening_archive_for_export_2, e);//$NON-NLS-1$
		}
	}

	protected String archiveString() {
		//TODO fill in string
		return "";
	}
}
