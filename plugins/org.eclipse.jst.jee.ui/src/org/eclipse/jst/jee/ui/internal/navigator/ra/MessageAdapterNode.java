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
