package org.eclipse.jst.jee.ui.internal.navigator.ra;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.jca.InboundResourceAdapter;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

public class InboundRaNode extends AbstractConnectorGroupProvider {

	public InboundRaNode(JavaEEObject javaee) {
		super(javaee);
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		children.add(new MessageAdapterNode(((InboundResourceAdapter)javaee).getMessageadapter()));
		return children;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public String getText() {
		return Messages.InboundRaNode_Inbound_Resource_adapter;
	}

	@Override
	public boolean hasChildren() {
		return ((InboundResourceAdapter)javaee).getMessageadapter() != null;
	}
	
	

}
