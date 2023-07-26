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
import org.eclipse.jst.javaee.jca.MessageListener;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

public class MessageListenerNode extends AbstractConnectorGroupProvider {

	public MessageListenerNode(JavaEEObject javaee) {
		super(javaee);
	}



	@Override
	public List getChildren() {
		List children = new ArrayList();
		children.add(new ActicationSpecNode(((MessageListener)javaee).getActivationspec()));
		return children;
	}
	
	

	@Override
	public boolean hasChildren() {
		return ((MessageListener)javaee).getActivationspec() != null;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public String getText() {
		return Messages.MessageListenerNode_MessageListener + (((MessageListener)javaee).getMessagelistenerType() != null ? ((MessageListener)javaee).getMessagelistenerType() :"");  //$NON-NLS-1$
	}

}
