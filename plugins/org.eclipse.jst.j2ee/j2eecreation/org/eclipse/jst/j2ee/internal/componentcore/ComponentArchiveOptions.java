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
package org.eclipse.jst.j2ee.internal.componentcore;

import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @deprecated see {@link JavaEEBinaryComponentHelper}
 * @author jasholl
 *
 */
public class ComponentArchiveOptions extends ArchiveOptions {

	private IVirtualComponent component;
	
	public ComponentArchiveOptions(IVirtualComponent component){
		this.component = component;
	}

	public IVirtualComponent getComponent() {
		return component;
	}
	
}
