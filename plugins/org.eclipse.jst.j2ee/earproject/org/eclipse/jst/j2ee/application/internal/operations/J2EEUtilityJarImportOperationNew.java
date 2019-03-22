/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.archive.J2EEJavaComponentArchiveSaveAdapter;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class J2EEUtilityJarImportOperationNew extends J2EEArtifactImportOperation {

	public J2EEUtilityJarImportOperationNew(IDataModel dataModel) {
		super(dataModel);
	}

	@Override
	protected ComponentArchiveSaveAdapter getArchiveSaveAdapter(IVirtualComponent virtualComponent){
		return new J2EEJavaComponentArchiveSaveAdapter(virtualComponent);
	}
	
}
