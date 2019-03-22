/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.archive.operations;

import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactExportOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @deprecated use org.eclipse.jst.j2ee.internal.archive.operations.JavaEEComponentExportOperation instead
 */
public class ConnectorComponentExportOperation extends J2EEArtifactExportOperation {

	public ConnectorComponentExportOperation() {
		super();
	}

	public ConnectorComponentExportOperation(IDataModel model) {
		super(model);
	}

}
