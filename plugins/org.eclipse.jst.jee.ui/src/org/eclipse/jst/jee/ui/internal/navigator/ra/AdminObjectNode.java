package org.eclipse.jst.jee.ui.internal.navigator.ra;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.jca.AdminObject;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

public class AdminObjectNode extends AbstractConnectorGroupProvider {

	public AdminObjectNode(JavaEEObject javaee) {
		super(javaee);
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
//		children.addAll(((AdminObject)javaee).getConfigProperty());
		return children;
	}
	
	

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public String getText() {
		return Messages.ConnectorLabelProvider_ADMIN_OBJECT + (((AdminObject)javaee).getAdminobjectInterface() != null ? ((AdminObject)javaee).getAdminobjectInterface() :""); //$NON-NLS-1$
	}
	
	

}
