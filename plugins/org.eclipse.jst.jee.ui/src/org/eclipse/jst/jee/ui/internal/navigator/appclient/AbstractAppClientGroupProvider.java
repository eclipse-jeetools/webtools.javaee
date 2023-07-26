/*******************************************************************************
 * Copyright (c) 2010, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.appclient;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractGroupProvider;

public abstract class AbstractAppClientGroupProvider extends
		AbstractGroupProvider {

	public AbstractAppClientGroupProvider(JavaEEObject javaee) {
		super(javaee);
	}

}
