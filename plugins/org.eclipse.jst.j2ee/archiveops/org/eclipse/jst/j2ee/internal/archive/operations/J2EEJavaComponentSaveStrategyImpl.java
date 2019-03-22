/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @deprecated this will be deleted post WTP 3.2.  This code is no longer
 * called because the IArchive API is used to handle imports.
 * 
 * @author jsholl
 */
public class J2EEJavaComponentSaveStrategyImpl extends J2EEComponentSaveStrategyImpl {

	public J2EEJavaComponentSaveStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}
}
