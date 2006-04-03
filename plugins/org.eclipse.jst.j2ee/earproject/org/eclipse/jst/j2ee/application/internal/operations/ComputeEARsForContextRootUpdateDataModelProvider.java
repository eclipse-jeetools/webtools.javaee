/*******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     rfrost@bea.com - initial API and impl
 *******************************************************************************/

package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.Set;

import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;

/**
 * <code>AbstractDataModelProvider</code> subclass that supports the <code>ComputeEARsForContextRootUpdateOp</code>.
 */
public class ComputeEARsForContextRootUpdateDataModelProvider extends AbstractDataModelProvider implements IUpdateModuleContextRootProperties {

	public Set getPropertyNames() {
		final Set propertyNames = super.getPropertyNames();
		propertyNames.add(EARS_TO_UPDATE);
		return propertyNames;
	}
}
