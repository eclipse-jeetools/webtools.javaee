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
import org.eclipse.jst.javaee.jca.MessageAdapter;
import org.eclipse.jst.javaee.jca.MessageListener;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

public class MessageAdapterNode extends AbstractConnectorGroupProvider {

	public MessageAdapterNode(JavaEEObject javaee) {
		super(javaee);
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		List<MessageListener> messagelistener = ((MessageAdapter)javaee).getMessagelistener();
		for (MessageListener messageListener2 : messagelistener) {
			children.add(new MessageListenerNode(messageListener2));	
		}
		return children;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public String getText() {
		return Messages.MessageAdapterNode_MESSAGE_ADAPTER;
	}

	@Override
	public boolean hasChildren() {
		List<MessageListener> messagelistener = ((MessageAdapter)javaee).getMessagelistener();
		return messagelistener != null && messagelistener.size()>0;
	}
	
	

}
