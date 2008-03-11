/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @author  Kiril Mitov k.mitov@sap.com
 *
 */
public class Ejb3MergedModelProviderFactory implements IModelProviderFactory {

	public IModelProvider create(IProject project) {
		return new EJB3MergedModelProvider(project);
	}

	public IModelProvider create(IVirtualComponent component) {
		return new EJB3MergedModelProvider(component.getProject());
	}

}
