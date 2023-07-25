/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class Connector16ModelProviderFactory implements IModelProviderFactory {

	@Override
	public IModelProvider create(IProject project) {
		return new Connector16ModelProvider(project);
	}

	@Override
	public IModelProvider create(IVirtualComponent component) {
		return new Connector16ModelProvider(component.getProject());
	}

}
