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
package org.eclipse.jst.jee.ui.internal.navigator.ra;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.jca.OutboundResourceAdapter;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

public class OutboundRaNode extends AbstractConnectorGroupProvider {

	public OutboundRaNode(JavaEEObject javaee) {
		super(javaee);
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		children.addAll(((OutboundResourceAdapter)javaee).getConnectionDefinition());
		return children;
	}
	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public boolean hasChildren() {
		return ((OutboundResourceAdapter)javaee).getConnectionDefinition() != null && ((OutboundResourceAdapter)javaee).getConnectionDefinition().size() >0;
	}

	@Override
	public String getText() {
		return Messages.OutboundRaNode_OutboundResourceAdapter;
	}

	
	
}
