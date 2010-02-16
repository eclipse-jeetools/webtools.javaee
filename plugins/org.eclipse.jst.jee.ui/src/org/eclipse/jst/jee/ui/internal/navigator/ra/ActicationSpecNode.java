package org.eclipse.jst.jee.ui.internal.navigator.ra;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.jca.ActivationSpec;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

public class ActicationSpecNode extends AbstractConnectorGroupProvider {

	public ActicationSpecNode(JavaEEObject javaee) {
		super(javaee);
	}

	@Override
	public String getText() {
		return Messages.ActicationSpecNode_ActivationSpec + (((ActivationSpec)javaee).getActivationspecClass() != null ? ((ActivationSpec)javaee).getActivationspecClass() :"");  //$NON-NLS-1$
	}

	@Override
	public boolean hasChildren() {
		return ((ActivationSpec)javaee).getRequiredConfigProperty() != null && ((ActivationSpec)javaee).getRequiredConfigProperty().size() > 0;
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		children.addAll(((ActivationSpec)javaee).getRequiredConfigProperty());
		return children;
	}

	@Override
	public Image getImage() {
		return null;
	}

}
