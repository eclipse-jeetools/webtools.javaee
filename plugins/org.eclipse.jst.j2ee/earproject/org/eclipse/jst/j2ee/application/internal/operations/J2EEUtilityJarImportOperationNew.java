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
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperation;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEJavaComponentSaveStrategyImpl;
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

	protected SaveStrategy createSaveStrategy(IVirtualComponent virtualComponent) {
		J2EEJavaComponentSaveStrategyImpl saveStrat = new J2EEJavaComponentSaveStrategyImpl(virtualComponent);
		return saveStrat;
	}

}
