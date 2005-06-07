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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactExportOperationNEW;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EJBModuleExportOperationNEW extends J2EEArtifactExportOperationNEW {

    public EJBModuleExportOperationNEW() {
        super();
        // TODO Auto-generated constructor stub
    }

    public EJBModuleExportOperationNEW(IDataModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

	protected void export() throws SaveFailureException, CoreException, InvocationTargetException, InterruptedException {
		// TODO Auto-generated method stub
		
	}

	protected String archiveString() {
		// TODO Auto-generated method stub
		return null;
	}

}
