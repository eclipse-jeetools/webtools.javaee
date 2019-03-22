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

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @deprecated use org.eclipse.jst.j2ee.internal.archive.operations.JavaEEComponentExportOperation instead
 */
public class AppClientComponentExportOperation extends J2EEArtifactExportOperation {

	public AppClientComponentExportOperation(IDataModel model) {
		super(model);
	}

}
