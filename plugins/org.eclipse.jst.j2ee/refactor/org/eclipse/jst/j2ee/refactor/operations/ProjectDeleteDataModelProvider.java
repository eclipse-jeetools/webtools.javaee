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

public class ProjectDeleteDataModelProvider extends	ProjectRefactoringDataModelProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getDefaultOperation()
	 */
	public IDataModelOperation getDefaultOperation() {
		return new ProjectDeleteOperation(model);
	}

}
