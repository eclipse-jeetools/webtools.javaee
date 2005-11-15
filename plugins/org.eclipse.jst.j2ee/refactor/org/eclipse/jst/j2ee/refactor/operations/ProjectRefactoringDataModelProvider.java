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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.refactor.RefactorResourceHandler;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;

public abstract class ProjectRefactoringDataModelProvider extends
		AbstractDataModelProvider implements ProjectRefactoringProperties {

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#getPropertyNames()
	 */
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(PROJECT_METADATA);
		return propertyNames;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider#validate(java.lang.String)
	 */
	public IStatus validate(String propertyName) {
		Iterator i = getPropertyNames().iterator();
		boolean valid = false;
		while (i.hasNext()) {
			if (i.next().equals(propertyName)) {
				valid = true;
				break;
			}
		}
		if (!valid) {
			return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, IStatus.OK, 
					RefactorResourceHandler.getString("invalid_property_name", new Object[]{propertyName}), null);
		}
		return OK_STATUS;
	}
	
}
