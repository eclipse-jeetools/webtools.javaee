/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;

public class J2EEBinaryModulesItemProvider extends J2EEItemProvider {

	public J2EEBinaryModulesItemProvider(Application app, AdapterFactory adapterFactory, Collection children) {
		super(adapterFactory, children);
	}

	@Override
	public String getText(Object obj) {
		return J2EEUIMessages.BINARY;
	}

	@Override
	public Object getImage(Object object) {
		return J2EEPlugin.getPlugin().getImage("folder"); //$NON-NLS-1$
	}

}
