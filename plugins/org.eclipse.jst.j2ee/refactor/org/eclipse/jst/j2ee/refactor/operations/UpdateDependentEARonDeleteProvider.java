/*******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * rfrost@bea.com - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.refactor.operations;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
public class UpdateDependentEARonDeleteProvider extends UpdateDependentProjectDataModelProvider {
	
	@Override
	public IDataModelOperation getDefaultOperation() {
		return new UpdateDependentEARonDeleteOp(model);
	}

}
