package org.eclipse.jst.jee.ui.internal.navigator.ra;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.ResourceAdapter;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

public class GroupRaContentProvider extends AbstractConnectorGroupProvider {


	private Image RA_IMAGE;

	public GroupRaContentProvider(JavaEEObject javaee1) {
		super(javaee1);
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		Connector connector = (Connector) javaee;
		ResourceAdapter ra = connector.getResourceadapter();
		if (ra == null){
			return children;
		}
		if (ra.getAdminobject() != null){
			for (Object object : ra.getAdminobject() ) {
				children.add(new AdminObjectNode((JavaEEObject) object));				
			}
		}
		if (ra.getConfigProperty() != null){
			children.addAll(ra.getConfigProperty());
		}
		
		if (ra.getSecurityPermission() != null){
			children.addAll(ra.getSecurityPermission());
		}
		if (ra.getInboundResourceadapter() != null){
			children.add(new InboundRaNode(ra.getInboundResourceadapter()));
		}
		if (ra.getOutboundResourceadapter()!= null){
			children.add(new OutboundRaNode(ra.getOutboundResourceadapter()));
			
		}
		return children;
	}

	@Override
	public Image getImage() {
		if (RA_IMAGE == null) {
			RA_IMAGE = JEEUIPlugin.getDefault().getImage(JEEUIPluginIcons.IMG_RESOURCE_ADAPTER);
		}
		return RA_IMAGE;
	}

	@Override
	public String getText() {
		return Messages.GroupRaContentProvider_RESOURCE_ADAPTER;
	}
	
}
