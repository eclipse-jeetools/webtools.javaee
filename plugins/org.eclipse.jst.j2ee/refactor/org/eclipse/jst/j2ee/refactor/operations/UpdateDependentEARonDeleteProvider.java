/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
@Deprecated
//This class is being deprecated in 3.1, and is in plan to be removed
//in 3.2, since it is not being used.
public class UpdateDependentEARonDeleteProvider extends UpdateDependentProjectDataModelProvider 
	implements ProjectRefactoringProperties {
	
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new UpdateDependentEARonDeleteOp(model);
	}

}
